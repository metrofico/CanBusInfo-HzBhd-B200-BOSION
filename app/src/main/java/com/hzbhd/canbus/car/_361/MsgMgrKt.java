package com.hzbhd.canbus.car._361;

import com.hzbhd.canbus.CanbusMsgSender;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001\u001a \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"}, d2 = {"getAnotherMsbLsbResult", "", "MSB_16", "MSB_8", "LSB", "restrict", "", "param", "max", "default", "sendMediaInfo", "", "data0", "data1", "data2_7", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(data2_7, "data2_7");
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -126, (byte) i, (byte) i2}, data2_7));
    }

    public static /* synthetic */ byte[] restrict$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return restrict(bArr, i, i2);
    }

    public static final byte[] restrict(byte[] param, int i, int i2) {
        Intrinsics.checkNotNullParameter(param, "param");
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
