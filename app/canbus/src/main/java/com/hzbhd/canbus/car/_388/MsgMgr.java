package com.hzbhd.canbus.car._388;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKey0x11();
            setTrackDate0x11();
        } else if (i == 18) {
            setDoorInfo0x12();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setTrackDate0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick0x11(0);
        }
        if (i == 1) {
            realKeyClick0x11(7);
            return;
        }
        if (i == 2) {
            realKeyClick0x11(8);
            return;
        }
        if (i == 6) {
            realKeyClick0x11(HotKeyConstant.K_PHONE_ON_OFF);
            return;
        }
        switch (i) {
            case 8:
                realKeyClick0x11(45);
                break;
            case 9:
                realKeyClick0x11(46);
                break;
            case 10:
                realKeyClick0x11(2);
                break;
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setDoorInfo0x12() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte) i5, (byte) i6, (byte) i7});
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }
}
