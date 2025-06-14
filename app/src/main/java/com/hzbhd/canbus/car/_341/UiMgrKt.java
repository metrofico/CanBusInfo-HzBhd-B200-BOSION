package com.hzbhd.canbus.car._341;

import com.hzbhd.canbus.CanbusMsgSender;
import kotlin.Metadata;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0002\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, d2 = {"param", "", "getParam", "()I", "setParam", "(I)V", "sendAirConditionData", "", "data0", "sendSettingsData", "data1", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiMgrKt {
    private static int param;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendSettingsData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    public static final int getParam() {
        return param;
    }

    public static final void setParam(int i) {
        param = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendAirConditionData(int i) {
        int i2 = param + 1;
        param = i2;
        if (i2 % 2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte) i, -52});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte) i, 34});
        }
    }
}
