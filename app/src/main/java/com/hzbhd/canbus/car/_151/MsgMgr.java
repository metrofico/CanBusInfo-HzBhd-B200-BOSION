package com.hzbhd.canbus.car._151;

import android.content.Context;
import android.util.SparseArray;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;


public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private Context mContext;
    private boolean mFrontStatus;
    private int mKey;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            set0x11CarBase(context);
            return;
        }
        if (i == 18) {
            set0x12DoorData(context);
        } else if (i == 65) {
            set0x41RearRadarData(context);
        } else {
            if (i != 240) {
                return;
            }
            set0xf0VersionInfo();
        }
    }

    private void set0x11CarBase(Context context) {
        int i = this.mKey;
        int i2 = this.mCanBusInfoInt[4];
        if (i != i2) {
            this.mKey = i2;
            if (i2 == 0) {
                realKeyClick2(this.mContext, 0);
            }
            if (i2 == 1) {
                realKeyClick2(this.mContext, 7);
                return;
            }
            if (i2 == 2) {
                realKeyClick2(this.mContext, 8);
                return;
            }
            if (i2 == 5) {
                realKeyClick2(this.mContext, 14);
                return;
            }
            if (i2 == 6) {
                realKeyClick2(this.mContext, 15);
                return;
            }
            switch (i2) {
                case 8:
                    realKeyClick2(this.mContext, 45);
                    break;
                case 9:
                    realKeyClick2(this.mContext, 46);
                    break;
                case 10:
                    realKeyClick2(this.mContext, 2);
                    break;
            }
        }
    }

    private void set0x12DoorData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private void set0x41RearRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(255, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0xf0VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void realKeyClick2(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[5]);
    }
}
