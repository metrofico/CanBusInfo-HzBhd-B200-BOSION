package com.hzbhd.canbus.vm.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;

import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.util.LogUtil;

public final class BhdWindowManager {

    public static final BhdWindowManager INSTANCE = new BhdWindowManager();
    private static boolean mIsAddReverseView = false;
    private static Handler mMainHandler;
    private static WindowManager.LayoutParams mReverseViewParams;
    private static WindowManager mWindowManager;

    public enum HANDLER_MAIN_MSG {
        NULL,
        MSG_ADD_REVERSE_VIEW,
        MSG_REMOVE_REVERSE_VIEW
    }

    private BhdWindowManager() {
    }

    // Initialize WindowManager
    public final void init(Context context) {
        if (context != null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
    }

    static {
        // Initialize the handler for main UI thread
        mMainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                HANDLER_MAIN_MSG handlerMainMsg = HANDLER_MAIN_MSG.values()[msg.what];
                if (LogUtil.log5()) {
                    LogUtil.d("BHD Window Manager: " + handlerMainMsg);
                }

                switch (handlerMainMsg) {
                    case MSG_ADD_REVERSE_VIEW:
                        addReverseViewHandler();
                        break;
                    case MSG_REMOVE_REVERSE_VIEW:
                        removeReverseViewHandler();
                        break;
                    default:
                        break;
                }
            }

            private void addReverseViewHandler() {
                if (!mIsAddReverseView) {
                    if (LogUtil.log5()) {
                        LogUtil.d("Handling Add Reverse View: " + Vm.getVm().getReverseMainView() + ", Params: " + mReverseViewParams);
                    }
                    mWindowManager.addView(Vm.getVm().getReverseMainView(), mReverseViewParams);
                    mIsAddReverseView = true;
                }
            }

            private void removeReverseViewHandler() {
                if (mIsAddReverseView) {
                    mWindowManager.removeView(Vm.getVm().getReverseMainView());
                    mIsAddReverseView = false;
                }
            }
        };
    }

    // Initialize the parameters for reverse view
    public final void initReverseWindowParams(WindowManager.LayoutParams params) {
        mReverseViewParams = params;
    }

    // Add the reverse view
    public final void addReverseView() {
        mMainHandler.removeMessages(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
        mMainHandler.removeMessages(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
        mMainHandler.sendEmptyMessage(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
    }

    // Remove the reverse view
    public final void removeReverseView() {
        mMainHandler.removeMessages(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
        mMainHandler.removeMessages(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
        mMainHandler.sendEmptyMessage(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
    }
}
