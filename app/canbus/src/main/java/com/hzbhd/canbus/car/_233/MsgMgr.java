package com.hzbhd.canbus.car._233;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private int mEachId;
    private boolean mFrontStatus;
    private boolean mIsDoorFirst = true;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int[] mRadarDataNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;

    private void setIllumination0x67() {
    }

    private void setpanelrotary0x22() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKey0x11();
        }
        if (i == 18) {
            setBaseInfo0x12();
            return;
        }
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 65) {
            setRadarData0x41(context);
            return;
        }
        if (i == 103) {
            setIllumination0x67();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
            return;
        }
        if (i == 49) {
            setAirData0x31();
            return;
        }
        if (i == 50) {
            setSpeedData0x26();
            return;
        }
        switch (i) {
            case 33:
                setPanelButton0x21();
                break;
            case 34:
                setpanelrotary0x22();
                break;
            case 35:
                setFrontRadarData0x23();
                break;
        }
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 8) {
            realKeyClick(45);
        } else if (i == 9) {
            realKeyClick(46);
        } else if (i != 12 && i != 44) {
            switch (i) {
                case 0:
                    realKeyClick(0);
                    break;
                case 1:
                    realKeyClick(7);
                    break;
                case 2:
                    realKeyClick(8);
                    break;
                case 3:
                    realKeyClick(3);
                    break;
                case 4:
                    realKeyClick(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick(14);
                    break;
                case 6:
                    realKeyClick(15);
                    break;
            }
        } else {
            realKeyClick(2);
        }
        byte[] bArr = this.mCanBusInfoByte;
        if (DataHandleUtils.getMsbLsbResult(bArr[8], bArr[9]) <= 5500) {
            byte[] bArr2 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = (DataHandleUtils.getMsbLsbResult(bArr2[8], bArr2[9]) / 220) + 1;
        } else {
            byte[] bArr3 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = ((DataHandleUtils.getMsbLsbResult(bArr3[8], bArr3[9]) - 65536) / 220) + 1;
        }
        updateParkUi(null, this.mContext);
    }

    private void setBaseInfo0x12() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setSpeedData0x26() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(iArr[5] + (iArr[4] * 256)).append(" rpm").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[6] * 256) + iArr2[7]).append(" km/h").toString()));
        arrayList.add(new DriverUpdateEntity(0, 2, (this.mCanBusInfoInt[8] * 0.1d) + "V"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    private void setPanelButton0x21() {
        switch (this.mCanBusInfoInt[2]) {
            case 6:
                realKeyClick(50);
                break;
            case 9:
                realKeyClick(3);
                break;
            case 32:
                realKeyClick(128);
                break;
            case 36:
                realKeyClick(59);
                break;
            case 40:
                realKeyClick(15);
                break;
            case 42:
                realKeyClick(49);
                break;
            case 47:
                realKeyClick(52);
                break;
            case 51:
                realKeyClick(76);
                break;
            case 55:
                realKeyClick(58);
                break;
            case 64:
                realKeyClick(HotKeyConstant.K_CAN_CONFIG);
                break;
            case 66:
                realKeyClick(4);
                break;
        }
    }

    private void setRadarData0x41(Context context) {
        if (this.mCanBusInfoInt[13] == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(3, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr3 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr3[2], iArr3[3], iArr3[4], iArr3[5]);
        int[] iArr4 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr4[6], iArr4[7], iArr4[8], iArr4[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setAirData0x31() {
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1) == 1;
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setRearRadarData0x22() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarData0x23() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[3], bArr[2], 0, 540, 16);
        updateParkUi(null, this.mContext);
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

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private String resolveAirTemp(int i) {
        return i == 0 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }
}
