package com.hzbhd.canbus.car._104;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x1CData;
    private int[] m0x24Data;
    private boolean mBackStatus;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private boolean mFrontStatus;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private final String TAG = "_1104_MsgMgt";
    private byte[] m0x04Datas = new byte[7];
    private byte[] m0x07Datas = new byte[5];
    private String mDistanceUnit = "KM";
    private String mFuelUnit = "L/100KM";
    private String mTemperatureUnit = "℃";
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanbusInfoInt = getByteArrayToIntArray(bArr);
        this.mCanbusInfoByte = bArr;
        byte b = bArr[1];
        if (b == 3) {
            set0x03VehicleInfo(context);
            return;
        }
        if (b == 4) {
            set0x04SystemInfoDate(context);
            return;
        }
        if (b == 6) {
            set0x06WheelKeyData(context);
            return;
        }
        if (b == 7) {
            set0x07ControlInfo(context);
            return;
        }
        if (b == 8) {
            set0x08DoorInfo(context);
            return;
        }
        if (b == 20) {
            setBackLight();
        } else if (b == 28) {
            set0x1CRadarDate(context);
        } else {
            if (b != 36) {
                return;
            }
            set0x24TrackData(context);
        }
    }

    private void setBackLight() {
        int i = this.mCanbusInfoInt[2];
        if (i != 128 && DataHandleUtils.getBoolBit7(i)) {
            setBacklightLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 7));
        }
    }

    private void set0x06WheelKeyData(Context context) {
        int[] iArr = this.mCanbusInfoInt;
        int i = 2;
        switch (iArr[2]) {
            case 1:
                i = 7;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                break;
            case 4:
                i = 49;
                break;
            case 5:
                i = 46;
                break;
            case 6:
                i = 45;
                break;
            case 7:
                i = HotKeyConstant.K_SPEECH;
                break;
            case 8:
                i = 14;
                break;
            default:
                i = 0;
                break;
        }
        realKeyLongClick1(context, i, iArr[3]);
    }

    private void set0x03VehicleInfo(Context context) {
        String str;
        int[] iArr = this.mCanbusInfoInt;
        int i = (iArr[2] << 8) | iArr[3];
        int i2 = (iArr[4] << 8) | iArr[5];
        int i3 = (iArr[6] << 8) | iArr[7];
        int i4 = iArr[9] | (this.mCanbusInfoByte[8] << 8);
        if (this.mTemperatureUnit.equals(getTempUnitC(context))) {
            str = (i * 0.5f) + this.mTemperatureUnit;
        } else {
            str = i + " " + this.mTemperatureUnit;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, str));
        arrayList.add(new DriverUpdateEntity(0, 1, i2 + " " + this.mDistanceUnit));
        arrayList.add(new DriverUpdateEntity(0, 2, (i3 / 10.0f) + " " + this.mDistanceUnit + "/h"));
        arrayList.add(new DriverUpdateEntity(0, 3, (i4 / 10.0f) + " " + this.mFuelUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x04SystemInfoDate(Context context) {
        byte[] bArr = this.mCanbusInfoByte;
        this.m0x04Datas = Arrays.copyOf(bArr, bArr.length);
        if (isDataChange(this.mCanbusInfoInt)) {
            int[] iArr = this.mCanbusInfoInt;
            int i = iArr[2];
            if (i == 0) {
                this.mDistanceUnit = "km";
            } else if (i == 1) {
                this.mDistanceUnit = "mls";
            }
            int i2 = iArr[4];
            if (i2 == 0) {
                this.mFuelUnit = "l/100km";
            } else if (i2 == 1) {
                this.mFuelUnit = "mpg(us)";
            } else if (i2 == 2) {
                this.mFuelUnit = "mpg(uk)";
            } else if (i2 == 3) {
                this.mFuelUnit = "km/l";
            }
            int i3 = iArr[5];
            if (i3 == 0) {
                this.mTemperatureUnit = getTempUnitC(context);
            } else if (i3 == 1) {
                this.mTemperatureUnit = getTempUnitF(context);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanbusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanbusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanbusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanbusInfoInt[5])));
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanbusInfoInt[6])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    byte[] get0x04Datas() {
        return this.m0x04Datas;
    }

    private void set0x08DoorInfo(Context context) {
        Log.i("_1104_MsgMgt", "set0x08DoorInfo: in");
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        return true;
    }

    private void set0x24TrackData(Context context) {
        if (is0x24DataChange()) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanbusInfoByte[2], (byte) 0, 0, 50, 8);
            Log.i("_1104_MsgMgt", "set0x24TrackData: GeneralParkData.trackAngle <--> " + GeneralParkData.trackAngle);
            updateParkUi(null, context);
        }
    }

    private boolean is0x24DataChange() {
        if (Arrays.equals(this.m0x24Data, this.mCanbusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanbusInfoInt;
        this.m0x24Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void set0x1CRadarDate(Context context) {
        if (is0x1CDataChange()) {
            int[] iArr = this.mCanbusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanbusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(10, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private boolean is0x1CDataChange() {
        if (Arrays.equals(this.m0x1CData, this.mCanbusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanbusInfoInt;
        this.m0x1CData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void set0x07ControlInfo(Context context) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 4);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 4);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 1);
        String str = intFromByteWithBit == 0 ? "close" : "_18_level_" + intFromByteWithBit;
        String str2 = intFromByteWithBit2 != 0 ? "_18_level_" + intFromByteWithBit2 : "close";
        String str3 = intFromByteWithBit3 == 0 ? "_1104_radar_close" : "_1104_radar_open";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, str));
        arrayList.add(new SettingUpdateEntity(3, 1, str2));
        arrayList.add(new SettingUpdateEntity(1, 0, str3));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i8, (byte) i6, (byte) i4, (byte) i3, (byte) i2});
    }

    void updateSettingsItem(int i, int i2, Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, obj));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
