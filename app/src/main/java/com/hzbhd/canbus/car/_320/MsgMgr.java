package com.hzbhd.canbus.car._320;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
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


public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x26Radar;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mHandBrakeUp;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private boolean mSeatBeltTie;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

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
        if (i == 33) {
            set0x21WheelData();
            return;
        }
        if (i == 35) {
            set0x12AirData(context);
            return;
        }
        if (i == 38) {
            set0x26RadarInfo(context);
            return;
        }
        if (i == 64) {
            set0x40CarInfo(context);
            return;
        }
        if (i == 127) {
            set0x7fVersionDate();
        } else if (i == 40) {
            set0x28BaseData(context);
        } else {
            if (i != 41) {
                return;
            }
            set0x29TrackDate();
        }
    }

    private void set0x21WheelData() {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        switch (i2) {
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
                realKeyClick(21);
                break;
            case 4:
                realKeyClick(20);
                break;
            case 6:
                realKeyClick(184);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 9:
                realKeyClick(14);
                break;
            case 10:
                realKeyClick(15);
                break;
            case 11:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
        }
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x12AirData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            int i = this.mCanBusInfoInt[3];
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            if (i == 1) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else if (i == 2) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else if (i == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (i == 4) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (i == 5) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            }
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
            GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
            updateAirActivity(this.mContext, 1001);
        }
    }

    private String resolveLeftAndRightTemp(int i) {
        String str;
        if (i == 0) {
            str = "LO";
        } else if (i == 30) {
            str = "HI";
        } else {
            str = (i <= 0 || i >= 30) ? "" : ((i / 2.0f) + 17.5f) + getTempUnitC(this.mContext);
        }
        Log.i("cbc", "value is: " + i);
        return str;
    }

    private void set0x28BaseData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isDoorDateChage()) {
            updateDoorView(context);
        }
    }

    private boolean isDoorDateChage() {
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

    private void set0x29TrackDate() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 7744, 13449, 16);
            updateParkUi(null, this.mContext);
            updateSpeedInfo(this.mCanBusInfoInt[5]);
        }
    }

    private void set0x40CarInfo(Context context) {
        GeneralDoorData.isShowMasterDriverSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isDoorDateChage1()) {
            updateDoorView(context);
        }
        updateOutDoorTemp(context, resolveOutDoorTemp());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, resolveWaterTemp()));
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[5];
        int i2 = (iArr[6] << 8) | iArr[7];
        int i3 = (iArr[8] << 8) | iArr[9];
        int i4 = iArr[12] | (iArr[11] << 8) | (iArr[10] << 16);
        arrayList.add(new DriverUpdateEntity(0, 1, i2 + " RPM"));
        arrayList.add(new DriverUpdateEntity(0, 2, i3 + " KM"));
        arrayList.add(new DriverUpdateEntity(0, 3, i4 + " KM"));
        arrayList.add(new DriverUpdateEntity(0, 4, i + " KM/H"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveOutDoorTemp() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            str = "-" + (127 - intFromByteWithBit);
        } else {
            str = intFromByteWithBit + "";
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveWaterTemp() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            str = "-" + (127 - intFromByteWithBit);
        } else {
            str = intFromByteWithBit + "";
        }
        return str + getTempUnitC(this.mContext);
    }

    private void set0x7fVersionDate() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x26RadarInfo(Context context) {
        if (is0x26DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private boolean is0x26DataChange() {
        if (Arrays.equals(this.m0x26Radar, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x26Radar = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDoorDateChage1() {
        if (this.mSeatBeltTie == GeneralDoorData.isSeatMasterDriverBeltTie && this.mHandBrakeUp == GeneralDoorData.isHandBrakeUp) {
            return false;
        }
        this.mSeatBeltTie = GeneralDoorData.isSeatMasterDriverBeltTie;
        this.mHandBrakeUp = GeneralDoorData.isHandBrakeUp;
        return true;
    }
}
