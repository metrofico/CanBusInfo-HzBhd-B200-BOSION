package com.hzbhd.canbus.car._342;

import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SingletonForKt.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/hzbhd/canbus/car/_342/SingletonForKt;", "", "()V", "setCarBodyData", "", "data", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class SingletonForKt {
    public static final SingletonForKt INSTANCE = new SingletonForKt();

    private SingletonForKt() {
    }

    public final void setCarBodyData(int[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(data[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(data[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(data[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(data[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(data[3]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(data[3]);
    }
}
