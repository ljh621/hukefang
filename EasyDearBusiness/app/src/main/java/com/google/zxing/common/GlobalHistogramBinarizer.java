/*
 * Copyright 2009 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.exception.NotFoundException;

/**
 * This Binarizer implementation uses the old ZXing global histogram approach. It is suitable
 * for low-end mobile devices which don't have enough CPU or memory to use a local thresholding
 * algorithm. However, because it picks a global black point, it cannot handle difficult shadows
 * and gradients.
 *
 * Faster mobile devices and all desktop applications should probably use HybridBinarizer instead.
 * GlobalHistogramBinarizer算法适合于低端的设备，对手机的CPU和内存要求不高。
 * 但它选择了全部的黑点来计算，因此无法处理阴影和渐变这两种情况。
 * 更快的移动设备和桌面应用程序应该使用所有HybridBinarizer代替。
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 *
 * 二值化的关键就是定义出黑白的界限，我们的图像已经转化为了灰度图像，每个点都是由一个灰度值来表示，就需要定义出一个灰度值，
 * 大于这个值就为白（0），低于这个值就为黑（1）。在 GlobalHistogramBinarizer中，是从图像中均匀取5行（覆盖整个图像高度），
 * 每行取中间五分之四作为样本；以灰度值为X轴，每个灰度值的像素个数为Y轴建立一个直方图，从直方图中取点数最多的一个灰度值，
 * 然后再去给其他的灰度值进行分数计算，按照点数乘以与最多点数灰度值的距离的平方来进行打分，选分数最高的一个灰度值。
 * 接下来在这两个灰度值中间选取一个区分界限，取的原则是尽量靠近中间并且要点数越少越好。
 * 界限有了以后就容易了，与整幅图像的每个点进行比较，如果灰度值比界限小的就是黑，
 * 在新的矩阵中将该点置1，其余的就是白，为0。
 *
 */
public class GlobalHistogramBinarizer extends Binarizer {

  private static final int LUMINANCE_BITS = 5;
  private static final int LUMINANCE_SHIFT = 8 - LUMINANCE_BITS;
  private static final int LUMINANCE_BUCKETS = 1 << LUMINANCE_BITS;
  private static final byte[] EMPTY = new byte[0];

  private byte[] luminances;//灰度化之后的亮度数据
  private final int[] buckets;

  public GlobalHistogramBinarizer(LuminanceSource source) {
    super(source);
    luminances = EMPTY;
    buckets = new int[LUMINANCE_BUCKETS];
  }

  // Applies simple sharpening to the row data to improve performance of the 1D Readers.
  // 简单的锐化适用于一维的行数据来提高性能，便于1D图形码的解析。
  @Override
  public BitArray getBlackRow(int y, BitArray row) throws NotFoundException {
    LuminanceSource source = getLuminanceSource();
    int width = source.getWidth();
    if (row == null || row.getSize() < width) {
      row = new BitArray(width);
    } else {
      row.clear();
    }

    initArrays(width);
    //在这里 luminances  被赋值
    byte[] localLuminances = source.getRow(y, luminances);
    //统计并建立直方图
    int[] localBuckets = buckets;
    for (int x = 0; x < width; x++) {
      localBuckets[(localLuminances[x] & 0xff) >> LUMINANCE_SHIFT]++;
    }
    int blackPoint = estimateBlackPoint(localBuckets);

    if (width < 3) {
      // Special case for very small images
      for (int x = 0; x < width; x++) {
        // & 0xff  将byte 转为 int 类型
        if ((localLuminances[x] & 0xff) < blackPoint) {
          row.set(x);
        }
      }
    } else {
      int left = localLuminances[0] & 0xff;
      int center = localLuminances[1] & 0xff;
      for (int x = 1; x < width - 1; x++) {
        int right = localLuminances[x + 1] & 0xff;
        // A simple -1 4 -1 box filter with a weight of 2.
        if (((center * 4) - left - right) / 2 < blackPoint) {
          row.set(x);
        }
        left = center;
        center = right;
      }
    }
    return row;
  }

  // Does not sharpen the data, as this call is intended to only be used by 2D Readers.
  // 作为2D数据的解析的时候，不要锐化数据。
  @Override
  public BitMatrix getBlackMatrix() throws NotFoundException {
    LuminanceSource source = getLuminanceSource();
    int width = source.getWidth();
    int height = source.getHeight();
    BitMatrix matrix = new BitMatrix(width, height);

    // Quickly calculates the histogram by sampling four rows from the image. This proved to be
    // more robust on the blackbox tests than sampling a diagonal as we used to do.
    initArrays(width);
    int[] localBuckets = buckets;
    for (int y = 1; y < 5; y++) {
      int row = height * y / 5;
      byte[] localLuminances = source.getRow(row, luminances);
      int right = (width * 4) / 5;
      for (int x = width / 5; x < right; x++) {
        int pixel = localLuminances[x] & 0xff;
        localBuckets[pixel >> LUMINANCE_SHIFT]++;
      }
    }
    int blackPoint = estimateBlackPoint(localBuckets);

    // We delay reading the entire image luminance until the black point estimation succeeds.
    // Although we end up reading four rows twice, it is consistent with our motto of
    // "fail quickly" which is necessary for continuous scanning.
    byte[] localLuminances = source.getMatrix();
    for (int y = 0; y < height; y++) {
      int offset = y * width;
      for (int x = 0; x < width; x++) {
        int pixel = localLuminances[offset + x] & 0xff;
        if (pixel < blackPoint) {
          matrix.set(x, y);
        }
      }
    }

    return matrix;
  }

  @Override
  public Binarizer createBinarizer(LuminanceSource source) {
    return new GlobalHistogramBinarizer(source);
  }

  private void initArrays(int luminanceSize) {
    if (luminances.length < luminanceSize) {
      luminances = new byte[luminanceSize];
    }
    for (int x = 0; x < LUMINANCE_BUCKETS; x++) {
      buckets[x] = 0;
    }
  }

  private static int estimateBlackPoint(int[] buckets) throws NotFoundException {
    // Find the tallest peak in the histogram.
    // 找到直方图的最高峰
    int numBuckets = buckets.length;
    int maxBucketCount = 0;
    int firstPeak = 0;
    int firstPeakSize = 0;
    //找到  数组中最多像素点的个数(maxBucketCount)  和  对应的 序号,序号其实就是亮度值(区间)firstPeak.
    //这里的 firstPeak 即可能是  黑色的区间,也可能是白色的区间.
    for (int x = 0; x < numBuckets; x++) {
      if (buckets[x] > firstPeakSize) {
        firstPeak = x;
        firstPeakSize = buckets[x];
      }
      if (buckets[x] > maxBucketCount) {
        maxBucketCount = buckets[x];
      }
    }

    // Find the second-tallest peak which is somewhat far from the tallest peak.
    int secondPeak = 0;
    int secondPeakScore = 0;
    for (int x = 0; x < numBuckets; x++) {
      int distanceToBiggest = x - firstPeak;
      // Encourage more distant second peaks by multiplying by square of distance.
      //这里找 到两个峰比较远,同时又点少的区间.这里  fromFist 采用平方的形式,可以理解更接近于白色.
      int score = buckets[x] * distanceToBiggest * distanceToBiggest;
      if (score > secondPeakScore) {
        secondPeak = x;
        secondPeakScore = score;
      }
    }

    // Make sure firstPeak corresponds to the black peak.
    if (firstPeak > secondPeak) {
      int temp = firstPeak;
      firstPeak = secondPeak;
      secondPeak = temp;
    }

    // If there is too little contrast in the image to pick a meaningful black point, throw rather
    // than waste time trying to decode the image, and risk false positives.
    if (secondPeak - firstPeak <= numBuckets / 16) {
      throw NotFoundException.getNotFoundInstance();
    }

    // Find a valley between them that is low and closer to the white peak.
    int bestValley = secondPeak - 1;
    int bestValleyScore = -1;
    for (int x = secondPeak - 1; x > firstPeak; x--) {
      int fromFirst = x - firstPeak;
      int score = fromFirst * fromFirst * (secondPeak - x) * (maxBucketCount - buckets[x]);
      if (score > bestValleyScore) {
        bestValley = x;
        bestValleyScore = score;
      }
    }

    return bestValley << LUMINANCE_SHIFT;
  }

}
