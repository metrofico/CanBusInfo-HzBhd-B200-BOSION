package com.hzbhd.canbus.adapter.externel360camera;

import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.commontools.utils.FgeString;

/* loaded from: classes.dex */
public class VZ360Constance {
    public static int CANCEL = 2805;
    public static int DOWN = 1785;
    public static int ENTER = 1275;
    public static int FOCUS_add = 9690;
    public static int FOCUS_minus = 10710;
    public static int INFO = 765;
    public static int IRIS_add = 9945;
    public static int IRIS_minus = 10965;
    public static int LEFT = 2040;
    public static int LOGIN = 510;
    public static int NEXT = 8925;
    public static int PINP = 1020;
    public static int PLAY = 8670;
    public static int POWER = 255;
    public static int PREV = 8415;
    public static int PTZAUTO = 11220;
    public static int RECORD = 9180;
    public static int RETURN = 2550;
    public static int RIGHT = 2295;
    public static int STOP = 10200;
    static String TAG = "VZ360Constance";
    public static int UP = 1530;
    public static int ZOOM_add = 9435;
    public static int ZOOM_minus = 10455;
    public static int comds_0 = 7140;
    public static int comds_1 = 4590;
    public static int comds_2 = 4845;
    public static int comds_3 = 5100;
    public static int comds_4 = 5355;
    public static int comds_5 = 5610;
    public static int comds_6 = 5865;
    public static int comds_7 = 6120;
    public static int comds_8 = 6375;
    public static int comds_9 = 6630;
    public static int comds_add = 4335;
    public static int comds_minus = 6885;
    public static int headCode = 52253;
    public static byte[] mcu_protocol = {-56, 6};

    public static byte[] getBytes(int i) {
        return new byte[]{(byte) (i >> 8), (byte) (i & 255)};
    }

    public static void sendMDs(int i) {
        byte[] bArrByteMerger = byteMerger(mcu_protocol, getBytes(headCode), getBytes(i));
        HzbhdLog.d(TAG, "sendMDs : " + FgeString.bytes2HexString(bArrByteMerger, bArrByteMerger.length));
        FutureUtil.instance.reqMcuKey(bArrByteMerger);
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int length = bArr.length + bArr2.length + bArr3.length;
        byte[] bArr4 = new byte[length];
        HzbhdLog.d(TAG, length + " , byteMerger : " + bArr.length + " , " + bArr2.length + " , " + bArr3.length);
        System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr4, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr4, bArr.length + bArr2.length, bArr3.length);
        return bArr4;
    }

    public static Boolean isVZ360Camera() {
        int iIs360External = FutureUtil.instance.is360External();
        HzbhdLog.d(TAG, "external360CamType " + iIs360External);
        return Boolean.valueOf(iIs360External == 3);
    }
}
