package com.hzbhd.canbus.car_cus._436.util;

import android.os.RemoteException;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.proxy.dvr.DvrManager;

/* loaded from: classes2.dex */
public class DvrSender {
    public static void send(byte[] bArr) throws RemoteException {
        byte[] bArrSplicingByte = SplicingByte(new byte[]{-86, 68, 77, (byte) (bArr.length + 5)}, bArr);
        int i = 0;
        for (byte b : bArrSplicingByte) {
            i += b;
        }
        DvrManager.INSTANCE.sendData(SplicingByte(bArrSplicingByte, new byte[]{(byte) DataHandleUtils.getIntFromByteWithBit(i, 0, 8)}));
    }

    private static byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
