/*
 * Copyright (C) 2008 ZXing authors
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

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.client.android.camera.CameraManager;

import java.util.Collection;
import java.util.Map;

/**
 * This class handles all the messaging which comprises the state machine for capture.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 *         <p>
 *         解码处理类，负责调用另外的线程进行解码
 */
public final class CaptureActivityHandler extends Handler {

    private static final String TAG = CaptureActivityHandler.class.getSimpleName();

    public static final int KEY_HANDLER_DECODE = 0x0004;
    public static final int KEY_HANDLER_DECODE_FAIL = 0x0005;
    public static final int KEY_HANDLER_DECODE_SUCCESS = 0x0006;
    public static final int KEY_HANDLER_QUIT = 0x0007;
    public static final int KEY_HANDLER_RESTART_PREVIRE = 0x0008;
    public static final int KEY_HANDLER_SCAN_RESULT = 0x0009;


    private final CaptureActivity activity;
    private final DecodeThread decodeThread;
    private State state;
    private final CameraManager cameraManager;

    private enum State {
        PREVIEW, SUCCESS,  DONE
    }

    CaptureActivityHandler(CaptureActivity activity,
                           Collection<BarcodeFormat> decodeFormats,
                           Map<DecodeHintType, ?> baseHints,
                           ZxingConfig config,
                           CameraManager cameraManager) {
        this.activity = activity;
        decodeThread = new DecodeThread(activity, decodeFormats, baseHints, config,
                new ViewfinderResultPointCallback(activity.getViewfinderView()));
        decodeThread.start();
        state = State.SUCCESS;

        // Start ourselves capturing previews and decoding.
        this.cameraManager = cameraManager;
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            //启动扫描
            case KEY_HANDLER_RESTART_PREVIRE:
                restartPreviewAndDecode();
                break;

            //扫描成功
            case KEY_HANDLER_DECODE_SUCCESS:
                //解码成功，获取到界面的结果和原来的二维码数据
                state = State.SUCCESS;
                activity.handleDecode((Result) message.obj);
                break;

            //扫描失败
            case KEY_HANDLER_DECODE_FAIL:
                // We're decoding as fast as possible, so when one decode fails, start another.
                state = State.PREVIEW;
                cameraManager.requestPreviewFrame(decodeThread.getHandler(), CaptureActivityHandler.KEY_HANDLER_DECODE);
                break;

            //返回扫描结果
            case KEY_HANDLER_SCAN_RESULT:
                activity.setResult(Activity.RESULT_OK, (Intent) message.obj);
                activity.finish();
                break;

        }
    }

    public void quitSynchronously() {
        state = State.DONE;
        cameraManager.stopPreview();
        Message quit = Message.obtain(decodeThread.getHandler(), KEY_HANDLER_QUIT);
        quit.sendToTarget();
        try {
            // Wait at most half a second; should be enough time, and onPause() will timeout quickly
            decodeThread.join(500L);
        } catch (InterruptedException e) {
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(KEY_HANDLER_DECODE_SUCCESS);
        removeMessages(KEY_HANDLER_DECODE_FAIL);
    }

    //重新绘制
    private void restartPreviewAndDecode() {
        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            //decodeThread.getHandler()拿到DecodeHandler
            cameraManager.requestPreviewFrame(decodeThread.getHandler(), KEY_HANDLER_DECODE);
            activity.drawViewfinder();
        }
    }

}
