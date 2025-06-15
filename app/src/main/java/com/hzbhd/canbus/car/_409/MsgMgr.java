package com.hzbhd.canbus.car._409;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    private boolean[] array = {GeneralDoorData.isLeftFrontDoorOpen, GeneralDoorData.isRightFrontDoorOpen, GeneralDoorData.isLeftRearDoorOpen, GeneralDoorData.isRightRearDoorOpen, GeneralDoorData.isBackOpen, GeneralDoorData.isFrontOpen};
    public int[] frame;

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }

        return null;
    }

    public final void setFrame(int[] iArr) {

        this.frame = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, 1, 18});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
        } else if (i == 26) {
            set0x1AData();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0Data(canbusInfo);
        }
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    public final boolean[] getArray() {
        return this.array;
    }

    public final void setArray(boolean[] zArr) {

        this.array = zArr;
    }

    private final void set0x1AData() {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[5], getFrame()[6]));
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[3]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[3]);
        boolean[] zArr = {DataHandleUtils.getBoolBit7(getFrame()[3]), DataHandleUtils.getBoolBit6(getFrame()[3]), DataHandleUtils.getBoolBit5(getFrame()[3]), DataHandleUtils.getBoolBit4(getFrame()[3]), DataHandleUtils.getBoolBit3(getFrame()[3]), DataHandleUtils.getBoolBit2(getFrame()[3])};
        if (!Arrays.equals(zArr, this.array)) {
            updateDoorView(InitUtilsKt.getMContext());
            this.array = zArr;
        }
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[5], getFrame()[6])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[11], getFrame()[12])));
        }
        updateDriveDataActivity(null);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x11Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = getFrame()[4];
        int i2 = 8;
        if (i == 1) {
            i2 = 7;
        } else if (i != 2) {
            i2 = i != 3 ? i != 4 ? i != 5 ? i != 8 ? i != 9 ? i != 12 ? 0 : 2 : 20 : 21 : HotKeyConstant.K_PHONE_ON_OFF : HotKeyConstant.K_SPEECH : 3;
        }
        realKeyLongClick1(mContext, i2, getFrame()[5]);
    }
}
