package com.hzbhd.canbus.car._263;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_FRONT_OPEN = "is_front_open";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setCarSetData0x14();
            return;
        }
        if (i == 32) {
            realKeyControl();
            return;
        }
        if (i == 34) {
            setRadarInfo();
            return;
        }
        if (i == 36) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData0x24();
        } else {
            if (i == 39) {
                setAirData0x27();
                return;
            }
            if (i == 48) {
                setVersionInfo();
                return;
            }
            if (i != 51) {
                return;
            }
            setDriveDataInfo();
            int i2 = this.mCanBusInfoInt[2];
            if (i2 != 255) {
                updateSpeedInfo(i2);
            }
        }
    }

    private void setRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyControl() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick3(this.mContext, 46, i, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyClick3(this.mContext, 45, i, iArr[3]);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 128) {
            Intent intent = new Intent(this.mContext, (Class<?>) MainActivity.class);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        } else if (i == 19) {
            realKeyClick(45);
        } else {
            if (i != 20) {
                return;
            }
            realKeyClick(46);
        }
    }

    private void setDriveDataInfo() {
        float f = ((float) ((((r0[3] * 256) + r0[4]) * 0.1d) * 10.0d)) / 10.0f;
        String str = this.mCanBusInfoInt[2] == 255 ? "--KM/h" : this.mCanBusInfoInt[2] + "KM/h";
        int[] iArr = this.mCanBusInfoInt;
        String str2 = (iArr[3] == 255 && iArr[4] == 255) ? "----.-KM" : f + "KM";
        int[] iArr2 = this.mCanBusInfoInt;
        String str3 = (iArr2[5] == 255 && iArr2[6] == 255) ? "----" : this.mCanBusInfoInt[5] + "h : " + this.mCanBusInfoInt[6] + "m";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, str));
        arrayList.add(new DriverUpdateEntity(1, 1, str2));
        arrayList.add(new DriverUpdateEntity(1, 2, str3));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x27() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        String str;
        double intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (intFromByteWithBit >= 127.0d) {
            intFromByteWithBit = 127.0d;
        }
        if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = (0.5d * intFromByteWithBit) + "";
        } else {
            str = "-" + (0.5d * intFromByteWithBit);
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            str = tempCToTempF(intFromByteWithBit) + "";
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            return str + getTempUnitF(this.mContext);
        }
        return str + getTempUnitC(this.mContext);
    }

    private double tempCToTempF(double d) {
        LogUtil.showLog("tempCToTempF:" + d);
        try {
            return Double.valueOf(new DecimalFormat("0.0").format((d * 1.8d) + 32.0d)).doubleValue();
        } catch (Exception e) {
            LogUtil.showLog("Exception:" + e);
            return 0.0d;
        }
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        String string2;
        GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (GeneralDoorData.isShowCarDoor && isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
            updateDoorView(this.mContext);
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        String str = intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? "" : "D" : "R" : "P";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, str));
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            string2 = this.mContext.getResources().getString(R.string.open);
        } else {
            string2 = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 2, string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) == GeneralDoorData.isFrontOpen) ? false : true;
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            string = this.mContext.getResources().getString(R.string.minimum);
        } else if (i == 7) {
            string = this.mContext.getResources().getString(R.string.max);
        } else {
            string = this.mCanBusInfoInt[2] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 3, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
