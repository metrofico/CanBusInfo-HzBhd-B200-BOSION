package com.hzbhd.canbus.car._368;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    private Integer color;
    public int[] frame;
    private Integer lastD2;

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

    public final Integer getColor() {
        return this.color;
    }

    public final void setColor(Integer num) {
        this.color = num;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = (byte) bYear2Dig;
        bArr[3] = (byte) bMonth;
        bArr[4] = (byte) bDay;
        bArr[5] = (byte) bHours;
        bArr[6] = (byte) bMins;
        bArr[7] = (byte) bSecond;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = 0;
        Integer num = this.color;
        if (num != null) {
            bArr[11] = (byte) num.intValue();
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        UiMgr uiMgr = (UiMgr) canUiMgr;
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, uiMgr, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 114) {
            set0x72Data();
        } else {
            if (i != 115) {
                return;
            }
            set0x73Data();
        }
    }

    private final void set0x73Data() {
        updateOutDoorTemp(InitUtilsKt.getMContext(), ((getFrame()[8] * 0.5d) - 40) + " °C");
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[9]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[9]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[9]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[9]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[9]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[9]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    public final Integer getLastD2() {
        return this.lastD2;
    }

    public final void setLastD2(Integer num) {
        this.lastD2 = num;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x72Data() {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._368.MsgMgr.set0x72Data():void");
    }
}
