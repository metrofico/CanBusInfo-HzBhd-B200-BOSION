package com.hzbhd.canbus.car_cus._283;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class HandlerUtils {
    private static HandlerUtils mHandlerUtils;
    List<IFreshUICallback> mIFreshUICallback = new ArrayList();
    private final MyHandler mHandler = new MyHandler(Looper.getMainLooper());

    public interface IFreshUICallback {
        void callback();
    }

    class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (HandlerUtils.this.mIFreshUICallback != null) {
                for (IFreshUICallback iFreshUICallback : HandlerUtils.this.mIFreshUICallback) {
                    iFreshUICallback.callback();
                }
            }
        }
    }

    public static synchronized HandlerUtils getInstance() {
        if (mHandlerUtils == null) {
            mHandlerUtils = new HandlerUtils();
        }
        return mHandlerUtils;
    }

    private HandlerUtils() {
    }

    public void refreshUI() {
        this.mHandler.sendEmptyMessage(1);
    }

    public void registerCallBack(IFreshUICallback iFreshUICallback) {
        if (iFreshUICallback != null) {
            this.mIFreshUICallback.add(iFreshUICallback);
        }
    }

    public void unregisterCallBack(IFreshUICallback iFreshUICallback) {
        if (iFreshUICallback != null) {
            this.mIFreshUICallback.remove(iFreshUICallback);
        }
    }
}