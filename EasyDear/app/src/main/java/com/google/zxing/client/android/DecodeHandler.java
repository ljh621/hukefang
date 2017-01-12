/*
 * Copyright (C) 2010 ZXing authors
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

package com.google.zxing.client.android;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.exception.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.Map;

/**
 * ZXing解码的核心类
 * zxing当然会考虑到这种情况。
 * 因此针对YUV编码的数据，有PlanarYUVLuminanceSource这个类去处理，
 * 而针对RGB编码的数据，则使用RGBLuminanceSource去处理。
 * */

final class DecodeHandler extends Handler {

  private static final String TAG = DecodeHandler.class.getSimpleName();

  private final CaptureActivity activity;
  private final MultiFormatReader multiFormatReader;
  private boolean running = true;

  DecodeHandler(CaptureActivity activity, Map<DecodeHintType,Object> hints) {
    multiFormatReader = new MultiFormatReader();
    multiFormatReader.setHints(hints);
    this.activity = activity;
  }

  @Override
  public void handleMessage(Message message) {
    if (!running) {
      return;
    }
    switch (message.what) {
      case CaptureActivityHandler.KEY_HANDLER_DECODE:
        decode((byte[]) message.obj, message.arg1, message.arg2);
        break;
      case CaptureActivityHandler.KEY_HANDLER_QUIT:
        running = false;
        Looper.myLooper().quit();
        break;
    }
  }

  /**
   * Decode the data within the viewfinder rectangle, and time how long it took. For efficiency,
   * reuse the same reader objects from one decode to the next.
   *
   * @param data   The YUV preview frame.
   * @param width  The width of the preview frame.
   * @param height The height of the preview frame.
   */
  //解码实际是对摄像头获得的数据的yuv数据进行解码
  private void decode(byte[] data, int width, int height) {
    //竖屏解码的处理转换
    byte[] rotatedData = new byte[data.length];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++)
        rotatedData[x * height + height - y - 1] = data[x + y * width];
    }
    int tmp = width;
    width = height;
    height = tmp;
    data = rotatedData;
    //开始解码
    long start = System.currentTimeMillis();
    Result rawResult = null;
    // 构造基于平面的YUV亮度源，即包含二维码区域的数据源，进行灰度化
    PlanarYUVLuminanceSource source = activity.getCameraManager().buildLuminanceSource(data, width, height);
    if (source != null) {
      // 构造二值图像比特流，使用HybridBinarizer算法解析数据源
      BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
      try {
        // 采用MultiFormatReader解析图像，可以解析多种数据格式
        rawResult = multiFormatReader.decodeWithState(bitmap);
      } catch (ReaderException re) {
        // continue
      } finally {
        multiFormatReader.reset();
      }
    }

    Handler handler = activity.getHandler();
    if (rawResult != null) {
      // Don't log the barcode contents for security.
      long end = System.currentTimeMillis();
      Log.d(TAG, "Found barcode in " + (end - start) + " ms");
      if (handler != null) {
        //给CaptureActivityHandler发消息
        Message message = Message.obtain(handler,CaptureActivityHandler.KEY_HANDLER_DECODE_SUCCESS, rawResult);
        message.sendToTarget();
      }
    } else {
      if (handler != null) {
        Message message = Message.obtain(handler,CaptureActivityHandler.KEY_HANDLER_DECODE_FAIL);
        message.sendToTarget();
      }
    }
  }


}
