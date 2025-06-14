package com.hzbhd.ui.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;

import com.hzbhd.config.use.UIDefault;
import com.hzbhd.ui.util.UiIdObserver;
import com.hzbhd.ui.util.test.TestInterface;
import com.hzbhd.util.ContextUtil;
import com.hzbhd.util.ContextUtilKt;
import com.hzbhd.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public final class BaseUtil {
    private static final String CONFIG_CLASS_NAME = "MyConfig";
    private static long time;
    public static final BaseUtil INSTANCE = new BaseUtil();

    private static SharedPreferences share;
    private static Context context;
    private static UiIdObserver uiIdObserver;
    private static HandlerThread handlerThread;

    private static HashMap<String, Object> configMap = new HashMap<>();

    // Private constructor to avoid instantiating this class
    private BaseUtil() {
        // Initializing the lazy-loaded variables
        share = ContextUtilKt.getBaseShare();
        context = ContextUtilKt.getBaseContext();
        uiIdObserver = new UiIdObserver();
        handlerThread = new HandlerThread("back1");
        handlerThread.start();
    }

    // Getters for shared preferences, context, and UI observer
    public SharedPreferences getShare() {
        return share;
    }

    public Context getContext() {
        return context;
    }

    public UiIdObserver getUiIdObserver() {
        return uiIdObserver;
    }

    // Method to generate a log tag based on the current stack trace
    private String createTag() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String fileName = stackTrace[2].getFileName();
        int lineNumber = stackTrace[2].getLineNumber();
        return "(" + fileName + ":" + lineNumber + ")";
    }

    // Initialization method
    public void init(Context context2) {
        ContextUtil.INSTANCE.init(context2);
        scaleUI(context2);
    }

    // Start test mode with the specified type
    public void startTestMode(int type) {
        try {
            Object objNewInstance = Class.forName("com.hzbhd.ui.test.MyTest").newInstance();
            ((TestInterface) objNewInstance).startTest(type);
        } catch (Exception ignored) {
        }
    }

    // Get screen width and height
    public int getScreenWidth() {
        return ContextUtil.INSTANCE.getScreenSize().x;
    }

    public int getScreenHeight() {
        return ContextUtil.INSTANCE.getScreenSize().y;
    }

    // Get string from resources
    public String getString(int id) throws Resources.NotFoundException {
        return getContext().getResources().getString(id);
    }

    // Convert float to screen width and height in pixels
    public int changeFloatToWidth(float size) {
        return (int) (size * getScreenWidth());
    }

    public int changeFloatToHeight(float size) {
        return (int) (size * getScreenHeight());
    }

    public static void logTime(String type) {
        long currentTimeMillis = System.currentTimeMillis();
        if (LogUtil.log5()) {
            Log.d(INSTANCE.createTag(), "log time :" + type + " = " + (currentTimeMillis - time));
        }
        time = currentTimeMillis;
    }

    // Get layout params to match the parent layout
    public ViewGroup.LayoutParams getMatchLayoutParams() {
        return new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    // Get the name of the top activity in the current task
    public String getTopActivityName() throws SecurityException {
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
        if (runningTasks == null || runningTasks.isEmpty()) {
            return "";
        }
        ComponentName componentName = runningTasks.get(0).topActivity;
        return componentName != null ? componentName.getClassName() : null;
    }

    // Start an activity with the given action
    public void startActivity(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

    // Start an activity with the given component name
    public void startActivity(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
    }

    // Run an action on the UI thread
    // Ejecuta una acción en el hilo principal después de un retraso
    public void runUiDelay(final Runnable action, long delay) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(action, delay);
    }

    // Ejecuta una acción en el hilo principal
    public void runUi(final Runnable action) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(action);
    }

    // Ejecuta una acción en un hilo en segundo plano
    public void runBack(final Runnable action) {
        Handler backgroundHandler = new Handler(getHandlerThread().getLooper());
        backgroundHandler.post(action);
    }

    // Ejecuta una acción en un hilo en segundo plano después de un retraso
    public void runBackDelay(final Runnable action, long delay) {
        Handler backgroundHandler = new Handler(getHandlerThread().getLooper());
        backgroundHandler.postDelayed(action, delay);
    }

    // Get the handler thread
    public HandlerThread getHandlerThread() {
        return handlerThread;
    }

    // Clear UI configuration cache
    public void clearUIConfig() {
        configMap.clear();
    }

    // Get UI configuration based on class and UI ID
    public <T> T getUIConfig(Class<T> configClass, String uiId) throws IllegalAccessException, InstantiationException {
        if (!configMap.containsKey(configClass.getName())) {
            String configClassName = "ui_" + uiId;
            try {
                String className = configClass.getPackage().getName() + '.' + configClassName.toLowerCase() + "." + CONFIG_CLASS_NAME;
                Object objNewInstance = Class.forName(className).newInstance();
                configMap.put(configClass.getName(), objNewInstance);
            } catch (Exception e) {
                LogUtil.d("getConfig not found: " + configClassName);
            }
        }
        return (T) configMap.get(configClass.getName());
    }

    // Get UI configuration with a default class if not found
    public <T> T getUIConfig(Class<T> configClass, String uiId, String defaultClass) throws IllegalAccessException, InstantiationException {
        if (!configMap.containsKey(configClass.getName())) {
            String configClassName = "ui_" + uiId;
            try {
                String className = "com.hzbhd.ui.config." + configClassName.toLowerCase() + "." + defaultClass;
                Object objNewInstance = Class.forName(className).newInstance();
                configMap.put(configClass.getName(), objNewInstance);
            } catch (Exception e) {
                LogUtil.e("getConfig not found defaultClass: " + configClassName);
            }
        }
        return (T) configMap.get(configClass.getName());
    }

    // Scale UI based on screen size
    public void scaleUI(Context context2) {
        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
        int screenSize = Math.min(ContextUtil.INSTANCE.getScreenSize().x, ContextUtil.INSTANCE.getScreenSize().y);
        if (screenSize == 600 || UIDefault.INSTANCE.getUiScale() == 0) {
            displayMetrics.density = 1.0f;
            displayMetrics.scaledDensity = 1.0f;
            displayMetrics.densityDpi = DisplayMetrics.DENSITY_MEDIUM;
        } else {
            float scale = screenSize / UIDefault.INSTANCE.getUiScale();
            displayMetrics.density = scale;
            displayMetrics.scaledDensity = scale;
            displayMetrics.densityDpi = DisplayMetrics.DENSITY_MEDIUM;
        }
    }
}
