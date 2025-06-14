package com.hzbhd.ui.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import com.hzbhd.config.use.UIDefault;
import com.hzbhd.ui.util.test.TestInterface;
import com.hzbhd.util.ContextUtil;
import com.hzbhd.util.ContextUtilKt;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseUtil.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.J\u0006\u00100\u001a\u000201J\b\u00102\u001a\u00020\u0004H\u0002J\u000e\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0018J+\u00105\u001a\u0002H6\"\b\b\u0000\u00106*\u00020\u00012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H6082\u0006\u00109\u001a\u00020\u0004¢\u0006\u0002\u0010:J3\u00105\u001a\u0002H6\"\b\b\u0000\u00106*\u00020\u00012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H6082\u0006\u00109\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004¢\u0006\u0002\u0010<J\u000e\u0010=\u001a\u0002012\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010>\u001a\u0002012\u0006\u0010?\u001a\u00020\u0004H\u0007J\u0014\u0010@\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010BJ\u001c\u0010C\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010B2\u0006\u0010\"\u001a\u00020#J\u0014\u0010D\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010BJ\u001c\u0010E\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010B2\u0006\u0010\"\u001a\u00020#J\u000e\u0010F\u001a\u0002012\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010G\u001a\u0002012\u0006\u0010H\u001a\u00020IJ\u000e\u0010G\u001a\u0002012\u0006\u0010A\u001a\u00020\u0004J\u000e\u0010J\u001a\u0002012\u0006\u0010?\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\r\u001a\u0004\b\u001f\u0010 R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010$\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001b\u0010'\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\r\u001a\u0004\b)\u0010*¨\u0006K"}, d2 = {"Lcom/hzbhd/ui/util/BaseUtil;", "", "()V", "CONFIG_CLASS_NAME", "", "configMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "handlerThread", "Landroid/os/HandlerThread;", "getHandlerThread", "()Landroid/os/HandlerThread;", "handlerThread$delegate", "matchLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getMatchLayoutParams", "()Landroid/view/ViewGroup$LayoutParams;", "screenHeight", "", "getScreenHeight", "()I", "screenWidth", "getScreenWidth", "share", "Landroid/content/SharedPreferences;", "getShare", "()Landroid/content/SharedPreferences;", "share$delegate", "time", "", "topActivityName", "getTopActivityName", "()Ljava/lang/String;", "uiIdObserver", "Lcom/hzbhd/ui/util/UiIdObserver;", "getUiIdObserver", "()Lcom/hzbhd/ui/util/UiIdObserver;", "uiIdObserver$delegate", "changeFloatToHeight", "size", "", "changeFloatToWidth", "clearUIConfig", "", "createTag", "getString", "id", "getUIConfig", "T", "configClass", "Ljava/lang/Class;", "uiId", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "defaultClass", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "init", "logTime", "type", "runBack", "action", "Lkotlin/Function0;", "runBackDelay", "runUi", "runUiDelay", "scaleUI", "startActivity", "componentName", "Landroid/content/ComponentName;", "startTestMode", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class BaseUtil {
    private static final String CONFIG_CLASS_NAME = "MyConfig";
    private static long time;
    public static final BaseUtil INSTANCE = new BaseUtil();

    /* renamed from: share$delegate, reason: from kotlin metadata */
    private static final Lazy share = LazyKt.lazy(new Function0<SharedPreferences>() { // from class: com.hzbhd.ui.util.BaseUtil$share$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SharedPreferences invoke() {
            return ContextUtilKt.getBaseShare();
        }
    });

    /* renamed from: context$delegate, reason: from kotlin metadata */
    private static final Lazy context = LazyKt.lazy(new Function0<Context>() { // from class: com.hzbhd.ui.util.BaseUtil$context$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Context invoke() {
            return ContextUtilKt.getBaseContext();
        }
    });

    /* renamed from: uiIdObserver$delegate, reason: from kotlin metadata */
    private static final Lazy uiIdObserver = LazyKt.lazy(new Function0<UiIdObserver>() { // from class: com.hzbhd.ui.util.BaseUtil$uiIdObserver$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UiIdObserver invoke() {
            return new UiIdObserver();
        }
    });
    private static HashMap<String, Object> configMap = new HashMap<>();

    /* renamed from: handlerThread$delegate, reason: from kotlin metadata */
    private static final Lazy handlerThread = LazyKt.lazy(new Function0<HandlerThread>() { // from class: com.hzbhd.ui.util.BaseUtil$handlerThread$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HandlerThread invoke() {
            HandlerThread handlerThread2 = new HandlerThread("back1");
            handlerThread2.start();
            return handlerThread2;
        }
    });

    private BaseUtil() {
    }

    public final SharedPreferences getShare() {
        return (SharedPreferences) share.getValue();
    }

    public final Context getContext() {
        return (Context) context.getValue();
    }

    public final UiIdObserver getUiIdObserver() {
        return (UiIdObserver) uiIdObserver.getValue();
    }

    private final String createTag() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        String fileName = stackTrace[2].getFileName();
        int lineNumber = stackTrace[2].getLineNumber();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(").append(fileName).append(":").append(lineNumber).append(")");
        String string = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "buffer.toString()");
        return string;
    }

    public final void init(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        ContextUtil.INSTANCE.init(context2);
        scaleUI(context2);
    }

    public final void startTestMode(int type) {
        try {
            Object objNewInstance = Class.forName("com.hzbhd.ui.test.MyTest").newInstance();
            Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type com.hzbhd.ui.util.test.TestInterface");
            ((TestInterface) objNewInstance).startTest(type);
        } catch (Exception unused) {
        }
    }

    public final int getScreenWidth() {
        return ContextUtil.INSTANCE.getScreenSize().x;
    }

    public final int getScreenHeight() {
        return ContextUtil.INSTANCE.getScreenSize().y;
    }

    public final String getString(int id) throws Resources.NotFoundException {
        String string = getContext().getResources().getString(id);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(id)");
        return string;
    }

    public final int changeFloatToWidth(float size) {
        return (int) (size * getScreenWidth());
    }

    public final int changeFloatToHeight(float size) {
        return (int) (size * getScreenHeight());
    }

    @JvmStatic
    public static final void logTime(String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (LogUtil.log5()) {
            Log.d(INSTANCE.createTag(), "log time :" + type + " = " + (jCurrentTimeMillis - time));
        }
        time = jCurrentTimeMillis;
    }

    public final ViewGroup.LayoutParams getMatchLayoutParams() {
        return new ViewGroup.LayoutParams(-1, -1);
    }

    public final String getTopActivityName() throws SecurityException {
        Object systemService = getContext().getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) systemService).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() <= 0) {
            return "";
        }
        ComponentName componentName = runningTasks.get(0).topActivity;
        if (componentName != null) {
            return componentName.getClassName();
        }
        return null;
    }

    public final void startActivity(String action) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intent intent = new Intent();
        intent.setAction(action);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    public final void startActivity(ComponentName componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.addFlags(268435456);
        getContext().startActivity(intent);
    }

    public final void runUi(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerThreadUtilKt.runUi(action);
    }

    public final void runUiDelay(Function0<Unit> action, long time2) {
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerThreadUtilKt.runUiDelay(time2, action);
    }

    public final void runBack(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerThreadUtilKt.runBack(action);
    }

    public final void runBackDelay(Function0<Unit> action, long time2) {
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerThreadUtilKt.runBackDelay(time2, action);
    }

    public final HandlerThread getHandlerThread() {
        return (HandlerThread) handlerThread.getValue();
    }

    public final void clearUIConfig() {
        configMap.clear();
    }

    public final <T> T getUIConfig(Class<T> configClass, String uiId) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(configClass, "configClass");
        Intrinsics.checkNotNullParameter(uiId, "uiId");
        if (!configMap.containsKey(configClass.getName())) {
            String str = "ui_" + uiId;
            try {
                StringBuilder sbAppend = new StringBuilder().append(configClass.getPackage().getName()).append('.');
                String lowerCase = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String string = sbAppend.append(lowerCase).append('.').toString();
                HashMap<String, Object> map = configMap;
                String name = configClass.getName();
                Intrinsics.checkNotNullExpressionValue(name, "configClass.name");
                Object objNewInstance = Class.forName(string + CONFIG_CLASS_NAME).newInstance();
                Intrinsics.checkNotNullExpressionValue(objNewInstance, "forName(configPackage + …CLASS_NAME).newInstance()");
                map.put(name, objNewInstance);
            } catch (Exception unused) {
                StringBuilder sbAppend2 = new StringBuilder().append(configClass.getPackage().getName()).append('.');
                String lowerCase2 = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                String string2 = sbAppend2.append(lowerCase2).append('.').toString();
                if (LogUtil.log5()) {
                    LogUtil.d("getConfig not found: " + string2);
                }
            }
            try {
                if (configMap.get(configClass.getName()) == null) {
                    HashMap<String, Object> map2 = configMap;
                    String name2 = configClass.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "configClass.name");
                    T tNewInstance = configClass.newInstance();
                    Intrinsics.checkNotNullExpressionValue(tNewInstance, "configClass.newInstance()");
                    map2.put(name2, tNewInstance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        T t = (T) configMap.get(configClass.getName());
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of com.hzbhd.ui.util.BaseUtil.getUIConfig");
        return t;
    }

    public final <T> T getUIConfig(Class<T> configClass, String uiId, String defaultClass) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(configClass, "configClass");
        Intrinsics.checkNotNullParameter(uiId, "uiId");
        Intrinsics.checkNotNullParameter(defaultClass, "defaultClass");
        if (!configMap.containsKey(configClass.getName())) {
            String str = "ui_" + uiId;
            try {
                StringBuilder sbAppend = new StringBuilder().append("com.hzbhd.ui.config.");
                String lowerCase = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String string = sbAppend.append(lowerCase).append('.').append(defaultClass).toString();
                HashMap<String, Object> map = configMap;
                String name = configClass.getName();
                Intrinsics.checkNotNullExpressionValue(name, "configClass.name");
                Object objNewInstance = Class.forName(string).newInstance();
                Intrinsics.checkNotNullExpressionValue(objNewInstance, "forName(configPackage).newInstance()");
                map.put(name, objNewInstance);
            } catch (Exception unused) {
                StringBuilder sbAppend2 = new StringBuilder().append("com.hzbhd.ui.config.");
                String lowerCase2 = str.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                String string2 = sbAppend2.append(lowerCase2).append('.').append(defaultClass).toString();
                if (LogUtil.log5()) {
                    LogUtil.e("getConfig not found defaultClass: " + string2);
                }
            }
            if (configMap.get(configClass.getName()) == null) {
                try {
                    StringBuilder sbAppend3 = new StringBuilder().append("com.hzbhd.ui.config.");
                    String lowerCase3 = str.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                    String string3 = sbAppend3.append(lowerCase3).append(".MyConfig").toString();
                    HashMap<String, Object> map2 = configMap;
                    String name2 = configClass.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "configClass.name");
                    Object objNewInstance2 = Class.forName(string3).newInstance();
                    Intrinsics.checkNotNullExpressionValue(objNewInstance2, "forName(configPackage).newInstance()");
                    map2.put(name2, objNewInstance2);
                } catch (Exception unused2) {
                    StringBuilder sbAppend4 = new StringBuilder().append("com.hzbhd.ui.config.");
                    String lowerCase4 = str.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                    String string4 = sbAppend4.append(lowerCase4).append(".MyConfig").toString();
                    if (LogUtil.log5()) {
                        LogUtil.e("getConfig not found myconfig: " + string4);
                    }
                }
            }
            try {
                if (configMap.get(configClass.getName()) == null) {
                    HashMap<String, Object> map3 = configMap;
                    String name3 = configClass.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "configClass.name");
                    T tNewInstance = configClass.newInstance();
                    Intrinsics.checkNotNullExpressionValue(tNewInstance, "configClass.newInstance()");
                    map3.put(name3, tNewInstance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        T t = (T) configMap.get(configClass.getName());
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of com.hzbhd.ui.util.BaseUtil.getUIConfig");
        return t;
    }

    public final void scaleUI(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
        int i = ContextUtil.INSTANCE.getScreenSize().x > ContextUtil.INSTANCE.getScreenSize().y ? ContextUtil.INSTANCE.getScreenSize().y : ContextUtil.INSTANCE.getScreenSize().x;
        if (i == 600 || UIDefault.INSTANCE.getUiScale() == 0) {
            displayMetrics.density = 1.0f;
            displayMetrics.scaledDensity = 1.0f;
            displayMetrics.densityDpi = 160;
        } else {
            if (LogUtil.log5()) {
                LogUtil.d("scaleUI: " + UIDefault.INSTANCE.getUiScale() + " to " + i);
            }
            float f = i;
            displayMetrics.density = f / UIDefault.INSTANCE.getUiScale();
            displayMetrics.scaledDensity = f / UIDefault.INSTANCE.getUiScale();
            displayMetrics.densityDpi = 160;
        }
    }
}
