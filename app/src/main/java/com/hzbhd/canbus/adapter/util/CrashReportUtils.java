package com.hzbhd.canbus.adapter.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.util.LogUtil;
import com.tencent.bugly.crashreport.CrashReport;
import java.util.Date;

/* loaded from: classes.dex */
public class CrashReportUtils {
    public static final String ACTION_CARSHREPORT = ".crashreport";
    public static final String BUGLY_APPID_BLINK = "77f7e10704";
    public static final String BUGLY_APPID_BLUETOOTH = "f36c398ba5";
    public static final String BUGLY_APPID_CANBUS = "10389eeeba";
    public static final String BUGLY_APPID_DSP = "d23c2be472";
    public static final String BUGLY_APPID_EQ = "f4c3dda65a";
    public static final String BUGLY_APPID_LAUNCHER = "bca81f4f68";
    public static final String BUGLY_APPID_MEDIA = "6add284758";
    public static final String BUGLY_APPID_RADIO = "e0380c76e8";
    public static final String BUGLY_APPID_SETTING = "994f39c7c6";
    public static final String BUGLY_APPID_SWC = "503561c667";
    public static final String BUGLY_APPID_SYSTEMUI = "4115c8cdf7";
    public static final String PACKAGENAME_BLINK = "com.hzbhd.blink";
    public static final String PACKAGENAME_BLUETOOTH = "com.hzbhd.bluetooth";
    public static final String PACKAGENAME_CANBUS = "com.hzbhd.canbus";
    public static final String PACKAGENAME_DSP = "com.hzbhd.dsp";
    public static final String PACKAGENAME_EQ = "com.hzbhd.eq";
    public static final String PACKAGENAME_LAUNCHER = "com.android.launcher3";
    public static final String PACKAGENAME_MEDIA = "com.hzbhd.media";
    public static final String PACKAGENAME_RADIO = "com.hzbhd.radio";
    public static final String PACKAGENAME_SETTING = "com.android.settings";
    public static final String PACKAGENAME_SWC = "com.hzbhd.swc";
    public static final String PACKAGENAME_SYSTEMUI = "com.android.systemui";
    private static final String TAG = "com.hzbhd.canbus.adapter.util.CrashReportUtils";

    public static void init(Context context) {
        if (LogUtil.log5()) {
            LogUtil.d("init---------------->" + context.getPackageName());
        }
        String packageName = context.getPackageName();
        packageName.hashCode();
        switch (packageName) {
            case "com.hzbhd.dsp":
                initCrashReport(context, BUGLY_APPID_DSP);
                break;
            case "com.hzbhd.swc":
                initCrashReport(context, BUGLY_APPID_SWC);
                break;
            case "com.hzbhd.bluetooth":
                initCrashReport(context, BUGLY_APPID_BLUETOOTH);
                break;
            case "com.hzbhd.canbus":
                initCrashReport(context, BUGLY_APPID_CANBUS);
                break;
            case "com.android.launcher3":
                initCrashReport(context, BUGLY_APPID_LAUNCHER);
                break;
            case "994f39c7c6":
                initCrashReport(context, BUGLY_APPID_SETTING);
                break;
            case "com.hzbhd.eq":
                initCrashReport(context, BUGLY_APPID_EQ);
                break;
            case "com.android.systemui":
                initCrashReport(context, BUGLY_APPID_SYSTEMUI);
                break;
            case "com.hzbhd.blink":
                initCrashReport(context, BUGLY_APPID_BLINK);
                break;
            case "com.hzbhd.media":
                initCrashReport(context, BUGLY_APPID_MEDIA);
                break;
            case "com.hzbhd.radio":
                initCrashReport(context, BUGLY_APPID_RADIO);
                break;
        }
    }

    private static void initCrashReport(Context context, String str) {
        String packageName = context.getPackageName();
        if (getCrashReportIsOpen(context, packageName)) {
            if (LogUtil.log5()) {
                LogUtil.d(packageName + "<---------> is open bugly");
            }
            CrashReport.initCrashReport(context, str, false);
            CrashReport.putUserData(context, "OS版本", getOSVersion());
            CrashReport.putUserData(context, "MCU版本", getMCUVersion());
        }
    }

    public static void setCrashReportIsOpen(Context context, String str, boolean z) {
        if (LogUtil.log5()) {
            LogUtil.d(str + "<-----set---->" + z);
        }
        Settings.System.putString(context.getContentResolver(), str + ACTION_CARSHREPORT, String.valueOf(z));
    }

    public static boolean getCrashReportIsOpen(Context context, String str) {
        String string = Settings.System.getString(context.getContentResolver(), str + ACTION_CARSHREPORT);
        if (LogUtil.log5()) {
            LogUtil.d(str + "<-----get---->" + string);
        }
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.equals("true");
    }

    public static void openAll(Context context) {
        if (LogUtil.log5()) {
            LogUtil.d("--------->openAllBugly");
        }
        setCrashReportIsOpen(context, "com.android.launcher3", true);
        setCrashReportIsOpen(context, PACKAGENAME_BLUETOOTH, true);
        setCrashReportIsOpen(context, "com.hzbhd.canbus", true);
        setCrashReportIsOpen(context, PACKAGENAME_RADIO, true);
        setCrashReportIsOpen(context, PACKAGENAME_MEDIA, true);
        setCrashReportIsOpen(context, PACKAGENAME_DSP, true);
        setCrashReportIsOpen(context, PACKAGENAME_EQ, true);
        setCrashReportIsOpen(context, "com.android.systemui", true);
        setCrashReportIsOpen(context, PACKAGENAME_SETTING, true);
        setCrashReportIsOpen(context, PACKAGENAME_SWC, true);
        setCrashReportIsOpen(context, "com.hzbhd.blink", true);
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        activityManager.killBackgroundProcesses("com.android.launcher3");
        activityManager.killBackgroundProcesses(PACKAGENAME_BLUETOOTH);
        activityManager.killBackgroundProcesses("com.hzbhd.canbus");
        activityManager.killBackgroundProcesses(PACKAGENAME_RADIO);
        activityManager.killBackgroundProcesses(PACKAGENAME_MEDIA);
        activityManager.killBackgroundProcesses(PACKAGENAME_DSP);
        activityManager.killBackgroundProcesses(PACKAGENAME_EQ);
        activityManager.killBackgroundProcesses("com.android.systemui");
        activityManager.killBackgroundProcesses(PACKAGENAME_SETTING);
        activityManager.killBackgroundProcesses(PACKAGENAME_SWC);
        activityManager.killBackgroundProcesses("com.hzbhd.blink");
    }

    public static void openCanBusBugly(Context context, boolean z) {
        Log.e(TAG, "开启BUGLY" + z);
        setCrashReportIsOpen(context, "com.hzbhd.canbus", z);
        ((ActivityManager) context.getSystemService("activity")).killBackgroundProcesses("com.hzbhd.canbus");
    }

    public static void testJavaCrash() {
        CrashReport.testJavaCrash();
    }

    private static String getMCUVersion() {
        return SystemProperties.get("persist.bhd.mcu.version");
    }

    private static String getOSVersion() {
        return ConfigUtil.getDeviceId() + " _ " + new Date(Build.TIME).toGMTString();
    }
}
