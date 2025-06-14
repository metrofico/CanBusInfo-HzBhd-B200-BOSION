package com.hzbhd.ui.base;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;

import com.hzbhd.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public final class BhdViewUtil {
    private static int currFocusId;
    private static int handlerWhatIndex;
    public static final BhdViewUtil INSTANCE = new BhdViewUtil();
    private static final ArrayList<ViewUtilListener> listeners = new ArrayList<>();
    private static TreeSet<Integer> focusIds = new TreeSet<>();
    private static final HashMap<Integer, ViewUtilListener> focusMap = new HashMap<>();
    private static final String COLOR_VIEW_COLOR_STYLE = "color_view_style";
    private static int colorStyle = 1;

    // AtomicReference for lazy initialization of uiHandler
    private static final AtomicReference<Handler> uiHandler = new AtomicReference<>();

    // AtomicReference for lazy initialization of threadHandler
    private static final AtomicReference<Handler> threadHandler = new AtomicReference<>();

    /* compiled from: BhdViewUtil.kt */
    public interface ViewUtilListener {
        void onColorChange();

        void onFocusChange(boolean isFocus);
    }

    private BhdViewUtil() {
    }

    // Method to get uiHandler lazily (using AtomicReference)
    private static Handler getUiHandler() {
        if (uiHandler.get() == null) {
            synchronized (BhdViewUtil.class) {
                if (uiHandler.get() == null) {
                    Handler handler = new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.obj != null && msg.obj instanceof Runnable) {
                                ((Runnable) msg.obj).run();
                            }
                        }
                    };
                    uiHandler.set(handler);
                }
            }
        }
        return uiHandler.get();
    }

    // Method to get threadHandler lazily (using AtomicReference)
    private static Handler getThreadHandler() {
        if (threadHandler.get() == null) {
            synchronized (BhdViewUtil.class) {
                if (threadHandler.get() == null) {
                    HandlerThread handlerThread = new HandlerThread("back");
                    handlerThread.start();
                    Handler handler = new Handler(handlerThread.getLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                            if (msg.obj != null && msg.obj instanceof Runnable) {
                                ((Runnable) msg.obj).run();
                            }
                        }
                    };
                    threadHandler.set(handler);
                }
            }
        }
        return threadHandler.get();
    }

    public static void addListener$default(BhdViewUtil bhdViewUtil, ViewUtilListener viewUtilListener, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        bhdViewUtil.addListener(viewUtilListener, i);
    }

    public void addListener(ViewUtilListener listener, int focusId) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            listener.onColorChange();
        }
        if (focusId > 0) {
            focusIds.add(focusId);
            focusMap.put(focusId, listener);
        }
    }

    public static void removeListener$default(BhdViewUtil bhdViewUtil, ViewUtilListener viewUtilListener, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        bhdViewUtil.removeListener(viewUtilListener, i);
    }

    public void removeListener(ViewUtilListener listener, int focusId) {
        listeners.remove(listener);
        if (focusId > 0) {
            focusIds.remove(focusId);
            focusMap.remove(focusId);
        }
    }

    public void init(Context context) {
        initColor(context);
    }

    public TreeSet<Integer> getFocusIds() {
        return focusIds;
    }

    public void setFocusIds(TreeSet<Integer> treeSet) {
        focusIds = treeSet;
    }

    public HashMap<Integer, ViewUtilListener> getFocusMap() {
        return focusMap;
    }

    public int getCurrFocusId() {
        return currFocusId;
    }

    public void setCurrFocusId(int i) {
        currFocusId = i;
    }

    public void focusAdd() {
        int iIndexOf = new ArrayList<>(focusIds).indexOf(currFocusId) + 1;
        if (iIndexOf >= focusIds.size()) {
            iIndexOf = 0;
        }
        notifyFocusChange(iIndexOf);
    }

    public void focusCut() {
        int iIndexOf = new ArrayList<>(focusIds).indexOf(currFocusId) - 1;
        if (iIndexOf < 0) {
            iIndexOf = 0;
        }
        notifyFocusChange(iIndexOf);
    }

    private void notifyFocusChange(int index) {
        if (!focusIds.isEmpty()) {
            for (ViewUtilListener next : listeners) {
                next.onFocusChange(next.equals(focusMap.get(focusIds.toArray()[index])));
            }
        }
    }

    public int getColorStyle() {
        return colorStyle;
    }

    public void setColorStyle(int i) {
        colorStyle = i;
    }

    public void updateColorStyle(Context context, int style) {
        Settings.System.putInt(context.getContentResolver(), COLOR_VIEW_COLOR_STYLE, style);
    }

    private void initColor(final Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        colorStyle = Settings.System.getInt(contentResolver, COLOR_VIEW_COLOR_STYLE, colorStyle);
        context.getContentResolver().registerContentObserver(Settings.System.getUriFor(COLOR_VIEW_COLOR_STYLE), false, new ContentObserver(new Handler(Looper.getMainLooper())) {
            @Override
            public void onChange(boolean selfChange) {
                int newColorStyle = Settings.System.getInt(context.getContentResolver(), COLOR_VIEW_COLOR_STYLE, 0);
                if (LogUtil.log5()) {
                    LogUtil.d("onChange: newColor = " + newColorStyle + " , colorStyle=" + colorStyle);
                }
                if (colorStyle != newColorStyle) {
                    setColorStyle(newColorStyle);
                    int size = listeners.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        listeners.get(i2).onColorChange();
                    }
                }
            }
        });
    }

    public void runUi(Runnable action) {
        getUiHandler().sendMessage(getUiHandler().obtainMessage(getHandlerWhat(), action));
    }

    public void runUiDelay(Runnable action, long time) {
        getUiHandler().sendMessageDelayed(getUiHandler().obtainMessage(getHandlerWhat(), action), time);
    }

    public void runBack(Runnable action) {
        getThreadHandler().sendMessage(getThreadHandler().obtainMessage(getHandlerWhat(), action));
    }

    public void runBackDelay(Runnable action, long time) {
        getThreadHandler().sendMessageDelayed(getThreadHandler().obtainMessage(getHandlerWhat(), action), time);
    }

    public int getHandlerWhatIndex() {
        return handlerWhatIndex;
    }

    public void setHandlerWhatIndex(int i) {
        handlerWhatIndex = i;
    }

    private int getHandlerWhat() {
        return ++handlerWhatIndex;
    }
}
