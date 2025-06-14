package com.hzbhd.canbus.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes2.dex */
public class RealKeyTimerManager {
    private static final int MSG_DELAY_KEY_A = 1;
    private static final int MSG_DELAY_KEY_B = 2;
    private static final int MSG_DELAY_KEY_C = 3;
    private static final int MSG_DELAY_KEY_D = 4;
    private static final int MSG_DELAY_KEY_E = 5;
    private static final int MSG_DELAY_KEY_F = 6;
    private static final int MSG_DELAY_KEY_G = 7;
    private static final int Time10 = 10000;
    private static Handler mHander = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.util.RealKeyTimerManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    MyLog.temporaryTracking("delay 10S A");
                    RealKeyUtil.realKeyLongClick((Context) message.obj, message.arg1, 0);
                    break;
                case 2:
                    MyLog.temporaryTracking("delay 10S B");
                    RealKeyUtil.realKeyClick5((Context) message.obj, message.arg1, message.arg2, 0);
                    break;
                case 3:
                    MyLog.temporaryTracking("delay 10S C");
                    RealKeyUtil.realKeyClick3_2((Context) message.obj, message.arg1, message.arg2, 0);
                    break;
                case 4:
                    MyLog.temporaryTracking("delay 10S D");
                    RealKeyUtil.realKeyClick3_1((Context) message.obj, message.arg1, message.arg2, 0);
                    break;
                case 5:
                    MyLog.temporaryTracking("delay 10S E");
                    RealKeyUtil.realKeyClick3((Context) message.obj, message.arg1, message.arg2, 0);
                    break;
                case 6:
                    MyLog.temporaryTracking("delay 10S F");
                    RealKeyUtil.realKeyClick2((Context) message.obj, message.arg1, message.arg2, 0);
                    break;
                case 7:
                    MyLog.temporaryTracking("delay 10S G");
                    RealKeyUtil.realKeyClick1((Context) message.obj, message.arg1, message.arg2, 0);
                    break;
            }
        }
    };

    public static void realKeyLongClick(Context context, int i, int i2) {
        if (i == 8 || i == 7) {
            if (i2 == 1 || i2 == 2) {
                MyLog.temporaryTracking("count down A");
                mHander.removeMessages(1);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 1;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.obj = context;
                mHander.sendEmptyMessageDelayed(1, 10000L);
            }
            if (i2 == 0) {
                MyLog.temporaryTracking("clear A");
                mHander.removeMessages(1);
            }
        }
    }

    public static void realKeyClick5(Context context, int i, int i2, int i3) {
        if (i == 8 || i == 7) {
            if (i3 == 1 || i3 == 2) {
                MyLog.temporaryTracking("count down B");
                mHander.removeMessages(2);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 2;
                messageObtainMessage.obj = context;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.arg2 = i2;
                mHander.sendEmptyMessageDelayed(2, 10000L);
            }
            if (i3 == 0) {
                MyLog.temporaryTracking("clear B");
                mHander.removeMessages(2);
            }
        }
    }

    public static void realKeyClick3_2(Context context, int i, int i2, int i3) {
        if (i == 8 || i == 7) {
            if (i3 == 1 || i3 == 2) {
                MyLog.temporaryTracking("count down C");
                mHander.removeMessages(3);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 3;
                messageObtainMessage.obj = context;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.arg2 = i2;
                mHander.sendEmptyMessageDelayed(3, 10000L);
            }
            if (i3 == 0) {
                MyLog.temporaryTracking("clear C");
                mHander.removeMessages(3);
            }
        }
    }

    public static void realKeyClick3_1(Context context, int i, int i2, int i3) {
        if (i == 8 || i == 7) {
            if (i3 == 1 || i3 == 2) {
                MyLog.temporaryTracking("count down D");
                mHander.removeMessages(4);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 4;
                messageObtainMessage.obj = context;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.arg2 = i2;
                mHander.sendEmptyMessageDelayed(4, 10000L);
            }
            if (i3 == 0) {
                MyLog.temporaryTracking("clear D");
                mHander.removeMessages(4);
            }
        }
    }

    public static void realKeyClick3(Context context, int i, int i2, int i3) {
        if (i == 8 || i == 7) {
            if (i3 == 1 || i3 == 2) {
                MyLog.temporaryTracking("count down E");
                mHander.removeMessages(5);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 5;
                messageObtainMessage.obj = context;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.arg2 = i2;
                mHander.sendEmptyMessageDelayed(5, 10000L);
            }
            if (i3 == 0) {
                MyLog.temporaryTracking("clear E");
                mHander.removeMessages(5);
            }
        }
    }

    public static void realKeyClick2(Context context, int i, int i2, int i3) {
        if (i == 8 || i == 7) {
            if (i3 == 1 || i3 == 2) {
                MyLog.temporaryTracking("count down F");
                mHander.removeMessages(6);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 6;
                messageObtainMessage.obj = context;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.arg2 = i2;
                mHander.sendEmptyMessageDelayed(6, 10000L);
            }
            if (i3 == 0) {
                MyLog.temporaryTracking("clear F");
                mHander.removeMessages(6);
            }
        }
    }

    public static void realKeyClick1(Context context, int i, int i2, int i3) {
        if (i == 8 || i == 7) {
            if (i3 == 1 || i3 == 2) {
                MyLog.temporaryTracking("count down G");
                mHander.removeMessages(7);
                Message messageObtainMessage = mHander.obtainMessage();
                messageObtainMessage.what = 7;
                messageObtainMessage.obj = context;
                messageObtainMessage.arg1 = i;
                messageObtainMessage.arg2 = i2;
                mHander.sendEmptyMessageDelayed(7, 10000L);
            }
            if (i3 == 0) {
                MyLog.temporaryTracking("clear G");
                mHander.removeMessages(7);
            }
        }
    }
}
