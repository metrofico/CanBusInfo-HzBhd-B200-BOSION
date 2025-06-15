package com.hzbhd.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.util.Locale;

public class HandlerThreadUtilKt {
    private static int handlerWhatIndex;
    private static int threadIndex;

    private static Handler uiHandler;
    private static Handler msgUIHandler;
    private static HandlerThread backThread;
    private static HandlerThreadPoolExecutor threadPoolExecutor;
    private static Handler threadHandler;
    private static Handler threadMsgHandler;

    public static int getHandlerWhat() {
        int i = handlerWhatIndex + 1;
        handlerWhatIndex = i;
        return i;
    }

    public static Handler getUiHandler() {
        if (uiHandler == null) {
            uiHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    try {
                        Object obj = msg.obj;
                        if (obj instanceof Runnable) {
                            ((Runnable) obj).run();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return uiHandler;
    }

    public static Handler getMsgUIHandler() {
        if (msgUIHandler == null) {
            msgUIHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    try {
                        Object obj = msg.obj;
                        if (obj instanceof Runnable) {
                            ((Runnable) obj).run();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return msgUIHandler;
    }

    public static HandlerThread getBackThread() {
        if (backThread == null) {
            backThread = new HandlerThread("back");
            backThread.setPriority(8);
            backThread.start();
        }
        return backThread;
    }

    public static HandlerThreadPoolExecutor getThreadPoolExecutor() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new HandlerThreadPoolExecutor();
        }
        return threadPoolExecutor;
    }

    public static Handler getThreadHandler() {
        if (threadHandler == null) {
            threadHandler = new Handler(getBackThread().getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    try {
                        Object obj = msg.obj;
                        if (obj instanceof Runnable) {
                            ((Runnable) obj).run();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return threadHandler;
    }

    public static Handler getThreadMsgHandler() {
        if (threadMsgHandler == null) {
            threadMsgHandler = new Handler(getBackThread().getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    try {
                        Object obj = msg.obj;
                        if (obj instanceof Runnable) {
                            ((Runnable) obj).run();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        return threadMsgHandler;
    }

    public static void runUi(Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        getUiHandler().sendMessage(getUiHandler().obtainMessage(getHandlerWhat(), action));
    }

    public static void runUiDelay(long time, Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        getUiHandler().sendMessageDelayed(getUiHandler().obtainMessage(getHandlerWhat(), action), time);
    }

    public static void runBack(Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        getThreadHandler().sendMessage(getThreadHandler().obtainMessage(getHandlerWhat(), action));
    }

    public static void runBackDelay(long time, Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        getThreadHandler().sendMessageDelayed(getThreadHandler().obtainMessage(getHandlerWhat(), action), time);
    }

    public static void runFast(Runnable action) {
        if (action == null) {
            throw new IllegalArgumentException("Action cannot be null");
        }
        getThreadPoolExecutor().execute(action);
    }

    public static void runUiMsg(String msgString, Runnable action) throws Exception {
        if (msgString == null || action == null) {
            throw new IllegalArgumentException("msgString or action cannot be null");
        }

        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }

        Handler msgUIHandler = getMsgUIHandler();
        Locale locale = Locale.getDefault();
        String lowerCase = msgString.toLowerCase(locale);
        msgUIHandler.removeMessages(Integer.parseInt(lowerCase, 36));
        getMsgUIHandler().sendMessage(getMsgUIHandler().obtainMessage(Integer.parseInt(msgString, 36), action));
    }

    public static void runUiMsgDelay(String msgString, long time, Runnable action) throws Exception {
        if (msgString == null || action == null) {
            throw new IllegalArgumentException("msgString or action cannot be null");
        }

        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }

        Handler msgUIHandler = getMsgUIHandler();
        Locale locale = Locale.getDefault();
        String lowerCase = msgString.toLowerCase(locale);
        msgUIHandler.removeMessages(Integer.parseInt(lowerCase, 36));
        getMsgUIHandler().sendMessageDelayed(getMsgUIHandler().obtainMessage(Integer.parseInt(msgString, 36), action), time);
    }

    public static void removeUiMsg(String msgString) throws Exception {
        if (msgString == null) {
            throw new IllegalArgumentException("msgString cannot be null");
        }

        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }

        Handler msgUIHandler = getMsgUIHandler();
        Locale locale = Locale.getDefault();
        String lowerCase = msgString.toLowerCase(locale);
        msgUIHandler.removeMessages(Integer.parseInt(lowerCase, 36));
    }

    public static void runBackMsg(String msgString, Runnable action) throws Exception {
        if (msgString == null || action == null) {
            throw new IllegalArgumentException("msgString or action cannot be null");
        }

        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }

        Handler threadMsgHandler = getThreadMsgHandler();
        Locale locale = Locale.getDefault();
        String lowerCase = msgString.toLowerCase(locale);
        threadMsgHandler.removeMessages(Integer.parseInt(lowerCase, 36));
        getThreadMsgHandler().sendMessage(getThreadMsgHandler().obtainMessage(Integer.parseInt(msgString, 36), action));
    }

    public static void removeBackMsg(String msgString) throws Exception {
        if (msgString == null) {
            throw new IllegalArgumentException("msgString cannot be null");
        }

        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }

        Handler threadMsgHandler = getThreadMsgHandler();
        Locale locale = Locale.getDefault();
        String lowerCase = msgString.toLowerCase(locale);
        threadMsgHandler.removeMessages(Integer.parseInt(lowerCase, 36));
    }

    public static void runBackMsgDelay(String msgString, long time, Runnable action) throws Exception {
        if (msgString == null || action == null) {
            throw new IllegalArgumentException("msgString or action cannot be null");
        }

        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }

        Handler threadMsgHandler = getThreadMsgHandler();
        Locale locale = Locale.getDefault();
        String lowerCase = msgString.toLowerCase(locale);
        threadMsgHandler.removeMessages(Integer.parseInt(lowerCase, 36));
        getThreadMsgHandler().sendMessageDelayed(getThreadMsgHandler().obtainMessage(Integer.parseInt(msgString, 36), action), time);
    }
}
