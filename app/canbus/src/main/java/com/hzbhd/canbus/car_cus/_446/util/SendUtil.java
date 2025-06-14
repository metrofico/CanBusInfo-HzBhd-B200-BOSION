package com.hzbhd.canbus.car_cus._446.util;

import com.hzbhd.canbus.CanbusMsgSender;

/* loaded from: classes2.dex */
public class SendUtil {
    public static void send(byte[] bArr) throws InterruptedException {
        byte[] bArrSplicingByte = SplicingByte(bArr, new byte[]{1});
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(100L);
                CanbusMsgSender.sendMsg(bArrSplicingByte);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
