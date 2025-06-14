package com.hzbhd.canbus.car_cus._283;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class MessageSender {
    public static void sendMsg(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public static void sendMsg(byte b, byte b2, boolean z) {
        byte[] bArr = {22, b, b2, (byte) (!z ? 1 : 0)};
        CanbusMsgSender.sendMsg(bArr);
        if (b == 74) {
            CommUtil.printHexString("111111发：", bArr);
        }
    }

    public static void sendDzMsg(int i, int i2, int i3, int i4, int i5) {
        CanbusMsgSender.sendMsg(new byte[]{23, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5});
    }

    public static void sendVoiceMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{24, -103, (byte) i});
    }

    public static void getMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 10, 1, (byte) i});
    }

    public static void sendMsg(byte b, byte[] bArr) {
        FutureUtil.instance.reportCanbusInfoOther(b, bArr);
    }

    public static void sendMsg0x78(byte[] bArr) {
        FutureUtil.instance.reportCanbusInfoOther((byte) 120, bArr);
        CommUtil.printHexString("scyscyscy 0x78 send2:", bArr);
    }
}
