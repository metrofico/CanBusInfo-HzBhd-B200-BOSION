package com.hzbhd.ui.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;

import com.hzbhd.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class FullScreenHelper {
    private final long AUTO_HIDE_SYSTEMUI_TIME = 5000;
    private final ArrayList<Activity> activitys;
    private boolean isFullScreen;
    private MAIN_MSG lastMsg;
    private OnFullScreenChangeListener listener;
    private final Handler mainHandler;

    public enum MAIN_MSG {
        NULL,
        REFRESH_SYSTEMUI_VISIABLE,
        SYSTEMUI_HIDE,
        SYSTEMUI_SHOW
    }

    public interface OnFullScreenChangeListener {
        void fullScreen();

        void notFullScreen();
    }

    public FullScreenHelper() {
        Looper mainLooper = Looper.getMainLooper();
        this.mainHandler = new Handler(mainLooper) {
            @Override
            public void handleMessage(Message msg) {
                MAIN_MSG mainMsg = MAIN_MSG.values()[msg.what];
                if (lastMsg != mainMsg) {
                    if (LogUtil.log5()) {
                        LogUtil.d("handleMessage: " + lastMsg + " != " + mainMsg);
                    }
                    return;
                }

                if (LogUtil.log5()) {
                    LogUtil.d("handleMessage: " + mainMsg);
                }

                switch (mainMsg) {
                    case REFRESH_SYSTEMUI_VISIABLE:
                        if (isFullScreen) {
                            if (listener != null) {
                                listener.notFullScreen();
                            }
                            fullScreenChange(false);
                        }
                        lastMsg = MAIN_MSG.SYSTEMUI_HIDE;
                        sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_HIDE.ordinal(), AUTO_HIDE_SYSTEMUI_TIME);
                        break;
                    case SYSTEMUI_HIDE:
                        if (!isFullScreen) {
                            if (listener != null) {
                                listener.fullScreen();
                            }
                            fullScreenChange(true);
                        }
                        break;
                    case SYSTEMUI_SHOW:
                        if (isFullScreen) {
                            if (listener != null) {
                                listener.notFullScreen();
                            }
                            fullScreenChange(false);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        this.activitys = new ArrayList<>();
        this.lastMsg = MAIN_MSG.NULL;
    }

    public long getAUTO_HIDE_SYSTEMUI_TIME() {
        return this.AUTO_HIDE_SYSTEMUI_TIME;
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    public ArrayList<Activity> getActivitys() {
        return this.activitys;
    }

    public void addAvtivity(Activity activity) {
        if (activity == null || activitys.contains(activity)) {
            return;
        }
        activitys.add(activity);
    }

    public void removeAvtivity(Activity activity) {
        if (activity != null) {
            activitys.remove(activity);
        }
    }

    public void finish() {
        for (Activity activity : activitys) {
            activity.finish();
        }
    }

    public OnFullScreenChangeListener getListener() {
        return listener;
    }

    public void setListener(OnFullScreenChangeListener listener) {
        this.listener = listener;
    }

    public boolean getIsFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    public MAIN_MSG getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(MAIN_MSG lastMsg) {
        this.lastMsg = lastMsg;
    }

    public void fullScreenChange(boolean fullScreen) {
        this.isFullScreen = fullScreen;
        if (LogUtil.log5()) {
            LogUtil.d("fullScreenChange: " + fullScreen);
        }
        for (Activity activity : activitys) {
            if (!activity.isInMultiWindowMode()) {
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                if (fullScreen) {
                    attributes.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                } else {
                    attributes.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                }
                activity.getWindow().setAttributes(attributes);
            }
        }
    }

    public void fullScreenChangeInit() {
        if (LogUtil.log5()) {
            LogUtil.d("fullScreenChangeInit");
        }
        for (Activity activity : activitys) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void refreshSystemUI() {
        lastMsg = MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE;
        if (LogUtil.log5()) {
            LogUtil.d("refreshSystemUI: ");
        }
        mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
        mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
        mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
        mainHandler.sendEmptyMessageDelayed(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal(), 10L);
    }

    public void showOrHideSystemUI() {
        if (isFullScreen) {
            refreshSystemUI();
        } else {
            hideSystemUI();
        }
    }

    public void showSystemUI() {
        lastMsg = MAIN_MSG.SYSTEMUI_SHOW;
        if (LogUtil.log5()) {
            LogUtil.d("showSystemUI: ");
        }
        mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
        mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
        mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
        mainHandler.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_SHOW.ordinal(), 10L);
    }

    public void hideSystemUI() {
        lastMsg = MAIN_MSG.SYSTEMUI_HIDE;
        if (LogUtil.log5()) {
            LogUtil.d("hideSystemUI: ");
        }
        mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
        mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
        mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
        mainHandler.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_HIDE.ordinal(), 10L);
    }
}
