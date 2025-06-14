package com.hzbhd.canbus.car_cus._451.data;

/* loaded from: classes2.dex */
public class EquipData {
    public static String btSongNumber;
    public static String btTimeStr;
    public static String cdNumber;
    public static String cdSongNumber;
    public static String cdTimeStr;
    public static String usbFileNumber;
    public static String usbSongNumber;
    public static String usbTimeStr;
    public static Enum mode = MODE.AMPL;
    public static boolean isShowVolume = false;
    public static int volume = 0;
    public static boolean isMute = false;
    public static String txtInfo = "";
    public static int bas = -5;
    public static int mid = -3;
    public static int tre = 4;
    public static int fad = -6;
    public static int bal = 7;
    public static boolean asl = false;
    public static String band = "AM2";
    public static String frequency = "000KHz";
    public static boolean isShowSt = true;
    public static String presetStr = "预设:";
    public static boolean isShowAslIcon = true;
    public static boolean isShowBtIcon = true;
    public static boolean isShowPhoneIcon = true;
    public static int scan = 2;
    public static int rpt = 2;
    public static int rand = 2;
    public static int signal = 0;
    public static int disc_in = 2;
    public static int disc1 = 2;
    public static int disc2 = 2;
    public static int disc3 = 2;
    public static int disc4 = 2;
    public static int disc5 = 2;
    public static int disc6 = 2;

    public enum MODE {
        AMPL,
        CD,
        BT,
        USB,
        PHONE,
        AUX
    }
}
