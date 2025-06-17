package com.hzbhd.canbus.car._72;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.proxy.service.ServiceConstants;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private SparseArray<int[]> mCanbusDataArray;
    private boolean mHoodStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private boolean mTrunkSataus;
    private String mVersionInfo;
    private final String TAG = "_72_MsgMgr";
    private final int DATA_TYPE = 1;

    private void setBacklightData0x14() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mCanbusDataArray = new SparseArray<>();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setBacklightData0x14();
            return;
        }
        if (i == 22) {
            setVehicleSpeedData0x16();
            return;
        }
        if (i == 32) {
            setWheelKeyData0x20(context);
            return;
        }
        if (i == 36) {
            setDoorData0x24(context);
            return;
        }
        if (i == 39) {
            setOutDoorTemperaure0x27(context);
            return;
        }
        if (i == 41) {
            setTrackData0x29(context);
        } else if (i == 48) {
            setVersionInfo0x30(context);
        } else {
            if (i != 57) {
                return;
            }
            setOnStarData0x39(context);
        }
    }

    private void setVehicleSpeedData0x16() {
        int[] iArr = this.mCanBusInfoInt;
        updateSpeedInfo(((iArr[3] * 256) + iArr[2]) / 16);
    }

    private void setWheelKeyData0x20(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick2(context, 0);
            return;
        }
        if (i == 1) {
            realKeyClick2(context, 7);
            return;
        }
        if (i == 2) {
            realKeyClick2(context, 8);
            return;
        }
        if (i == 7) {
            realKeyClick2(context, 2);
            return;
        }
        if (i == 9) {
            realKeyClick2(context, 14);
            return;
        }
        if (i != 24) {
            switch (i) {
                case 18:
                    realKeyClick2(context, 49);
                    break;
                case 19:
                    realKeyClick2(context, 45);
                    break;
                case 20:
                    realKeyClick2(context, 46);
                    break;
            }
            return;
        }
        realKeyClick2(context, 75);
    }

    private void setDoorData0x24(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private void setOutDoorTemperaure0x27(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            String tempUnitC = getTempUnitC(context);
            String str = "";
            if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
                tempUnitC = getTempUnitF(context);
                if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
                    str = "-";
                }
            }
            updateOutDoorTemp(context, str + ((int) this.mCanBusInfoByte[2]) + tempUnitC);
        }
    }

    private void setTrackData0x29(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 8500, 16);
            updateParkUi(null, context);
        }
    }

    private void setVersionInfo0x30(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            String versionStr = getVersionStr(this.mCanBusInfoByte);
            this.mVersionInfo = versionStr;
            updateVersionInfo(context, versionStr);
        }
    }

    private void setOnStarData0x39(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                exitAuxIn2();
                return;
            }
            if (i == 1) {
                enterAuxIn2(context, Constant.OnStarActivity);
                Intent intent = new Intent();
                intent.setAction(ServiceConstants.SONG_TITLE_CHANGE_ACTION);
                intent.putExtra(ServiceConstants.SONG_TITLE, context.getString(R.string.on_start));
                context.sendBroadcast(intent);
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        Log.i("_72_MsgMgr", "dateTimeRepCanbus: " + this.mVersionInfo);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    private void realKeyClick2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mTrunkSataus == GeneralDoorData.isBackOpen && this.mHoodStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mTrunkSataus = GeneralDoorData.isBackOpen;
        this.mHoodStatus = GeneralDoorData.isFrontOpen;
        return true;
    }
}
