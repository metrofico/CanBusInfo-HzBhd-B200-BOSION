package com.hzbhd.canbus.car._342;

import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class SingletonForKt {
    public static final SingletonForKt INSTANCE = new SingletonForKt();

    private SingletonForKt() {
    }

    public final void setCarBodyData(int[] data) {

        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(data[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(data[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(data[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(data[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(data[3]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(data[3]);
    }
}
