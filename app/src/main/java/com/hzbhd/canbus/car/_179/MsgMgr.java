package com.hzbhd.canbus.car._179;

import android.content.Context;
import android.util.SparseArray;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import kotlinx.coroutines.scheduling.WorkQueueKt;


public class MsgMgr extends AbstractMsgMgr {
    private String mAirUnit;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");

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
        if (i == 32) {
            set0x20WheelKey();
            return;
        }
        if (i == 33) {
            set0x21AirData(context);
            return;
        }
        if (i == 36) {
            set0x24BaseData(context);
            return;
        }
        if (i == 38) {
            set0x26TrackData();
        } else if (i != 48) {
            return;
        }
        set0X30VersionData();
    }

    private void set0x20WheelKey() {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 0) {
            realKeyClick(0);
            return;
        }
        if (i2 == 1) {
            realKeyClick(7);
        } else if (i2 == 2) {
            realKeyClick(8);
        } else {
            if (i2 != 5) {
                return;
            }
            realKeyClick(14);
        }
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x21AirData(Context context) {
        if (this.mCanBusInfoInt[6] == 240) {
            this.mAirUnit = getTempUnitC(context);
        } else {
            this.mAirUnit = getTempUnitF(context);
        }
        updateOutDoorTemp(context, resolveOutdoorTemperature(this.mCanBusInfoInt[5]));
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        bArr[5] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        updateAirActivity(context, 1001);
    }

    private String resolveAirTemperature(int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (i < 1 || i > 17) ? "" : this.mDecimalFormat0p0.format((i + 35) / 2.0f) + getTempUnitC(this.mContext);
    }

    private String resolveOutdoorTemperature(int i) {
        if (this.mCanBusInfoInt[6] == 240) {
            return (DataHandleUtils.rangeNumber(i, 0x7F) - 40) + this.mAirUnit;
        }
        return this.mDecimalFormat0p0.format((((DataHandleUtils.rangeNumber(i, 0x7F) - 40) * 9) / 5.0f) + 32.0f) + this.mAirUnit;
    }

    private void set0x24BaseData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
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

    private void set0x26TrackData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) iArr[2], (byte) iArr[3], 0, 540, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0X30VersionData() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }
}
