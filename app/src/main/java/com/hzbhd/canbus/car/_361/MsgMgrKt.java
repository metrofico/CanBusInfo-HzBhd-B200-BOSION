package com.hzbhd.canbus.car._361;

import com.hzbhd.canbus.CanbusMsgSender;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgrKt {
    public static final int getAnotherMsbLsbResult(int i, int i2, int i3) {
        return (i << 16) | (i2 << 8) | i3;
    }

    public static /* synthetic */ void sendMediaInfo$default(int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            byte[] bArr2 = new byte[6];
            for (int i4 = 0; i4 < 6; i4++) {
                bArr2[i4] = 0;
            }
            bArr = bArr2;
        }
        sendMediaInfo(i, i2, bArr);
    }

    public static final void sendMediaInfo(int i, int i2, byte[] data2_7) {

        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -126, (byte) i, (byte) i2}, data2_7));
    }

    public static /* synthetic */ byte[] restrict$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return restrict(bArr, i, i2);
    }

    public static final byte[] restrict(byte[] param, int i, int i2) {

        if (param.length > i) {
            return ArraysKt.copyOfRange(param, 0, i);
        }
        int length = i - param.length;
        byte[] bArr = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3] = (byte) i2;
        }
        return ArraysKt.plus(param, bArr);
    }
}
