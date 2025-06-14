package com.hzbhd.canbus.car._283;

import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;

/* loaded from: classes2.dex */
public class MeterManager {
    public static byte[] m0xE6Data = {22, -26, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static void send0xE6Null(int i) {
        Log.d("scyscyscy", "---------->" + i);
        m0xE6Data[6] = (byte) DataHandleUtils.setIntByteWithBit(0, 7, true);
        send0xE6();
    }

    public static void send0xE6() {
        CommUtil.printHexString("scyscyscy0xE6：", m0xE6Data);
        MessageSender.sendMsg(m0xE6Data);
    }

    public static void sendMediaMeterData(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            str = " ";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = " ";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = " ";
        }
        if (TextUtils.isEmpty(str4)) {
            str4 = " ";
        }
        sendSourceMsg1(str);
        sendSourceMsg2(str2, 146);
        sendSourceMsg2(str3, 147);
        sendSourceMsg2(str4, 148);
    }

    private static void sendSourceMsg1(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, str.getBytes()), 28));
    }

    private static void sendSourceMsg2(String str, int i) {
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, (byte) i, 0}, str.getBytes()), 27));
    }
}
