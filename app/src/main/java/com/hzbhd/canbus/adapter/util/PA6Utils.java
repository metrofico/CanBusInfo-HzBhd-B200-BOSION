package com.hzbhd.canbus.adapter.util;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;

/* loaded from: classes.dex */
public class PA6Utils {
    public static final String AIR_SEAT_SHOW = "air_seat_show";
    public static final String AIR_SHOW = "air_show";
    public static final String BLUETOOTH_NAME = "blueTooth_name";
    public static final String RADAR_SHOW = "radar_show";
    public static final String WallpaperPicker_Main = "WallpaperPicker_Main";
    public static final String WallpaperPicker_Now_ID = "WallpaperPicker_Now";

    public static int getIntData() {
        return 1;
    }

    public static void setIntData(int i) {
        SystemProperties.set(WallpaperPicker_Now_ID, String.valueOf(i));
    }

    public static void setAirSeatShow(boolean z) {
        SystemProperties.set(AIR_SEAT_SHOW, z + "");
    }

    public static boolean getAirSeatShow() {
        return SystemProperties.getBoolean(AIR_SEAT_SHOW, true);
    }

    public static void setRadarShow(Context context, boolean z) {
        Settings.System.putString(context.getContentResolver(), RADAR_SHOW, z + "");
    }

    public static boolean getRadarShow(Context context) {
        return Boolean.parseBoolean(Settings.System.getString(context.getContentResolver(), RADAR_SHOW));
    }

    public static void setAirShow(Context context, boolean z) {
        Settings.System.putString(context.getContentResolver(), AIR_SHOW, z + "");
    }

    public static boolean getAirShow(Context context) {
        return Boolean.parseBoolean(Settings.System.getString(context.getContentResolver(), AIR_SHOW));
    }

    public static String getBtName(Context context) {
        return Settings.System.getString(context.getContentResolver(), BLUETOOTH_NAME);
    }

    public static void setBtName(Context context, String str) {
        Settings.System.putString(context.getContentResolver(), BLUETOOTH_NAME, str);
    }
}
