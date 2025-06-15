package com.hzbhd.canbus.car._277;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.car_cus._277.DiagnosisEntity;
import com.hzbhd.canbus.car_cus._277.DialogUtil;
import com.hzbhd.canbus.car_cus._277._277_GeneralSettingData;
import com.hzbhd.canbus.car_cus._277.activity.VehicleDiagnosisActivity;
import com.hzbhd.canbus.car_cus._277.activity.VehicleMonitorActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] m0x33DataNow;
    private byte[] m0x36DataNow;
    private byte[] m0x38DataNow;
    private byte[] m0x39DataNow;
    private byte[] m0x76DataNow;
    private byte[] m0x7ADataNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private List<DiagnosisEntity> mDiagnosisList;
    private boolean mIsNeedShow;
    private String[] mStrings;
    private String[] mStringsNow;
    private final int VEHICLE_MODE_STOP = 0;
    private final int VEHICLE_MODE_PARK_CHARGE = 1;
    private final int VEHICLE_MODE_ELECT_DRIV = 2;
    private final int VEHICLE_MODE_REGE_BREAK = 3;
    private final int VEHICLE_MODE_SLOPE_MODE = 4;
    private int mVehicleMode = 0;

    private String getDowntimeStatus(boolean z) {
        return z ? "downtime" : "invalid";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        if (this.mDiagnosisList == null) {
            this.mDiagnosisList = new ArrayList();
        }
        if (this.mStrings == null) {
            this.mStrings = new String[10];
        }
        int i = this.mCanBusInfoInt[1];
        if (i == 51) {
            setVehicleStatus0x33();
            return;
        }
        if (i == 118) {
            setVehicleDiagnosisInfo0x76();
            return;
        }
        if (i == 122) {
            setVehicleDiagnosisInfo0x7A();
            return;
        }
        if (i == 240) {
            setVersionInfo();
        } else if (i == 56) {
            setMotorStatus0x38();
        } else {
            if (i != 57) {
                return;
            }
            setBatteryVoltage0x39();
        }
    }

    private void update277GeneralSettingData(List<SettingUpdateEntity> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int i2 = 0; i2 < _277_GeneralSettingData.dataList2.size(); i2++) {
                if (_277_GeneralSettingData.dataList2.get(i2).getLeftListIndex() == list.get(i).getLeftListIndex() && _277_GeneralSettingData.dataList2.get(i2).getRightListIndex() == list.get(i).getRightListIndex()) {
                    _277_GeneralSettingData.dataList2.remove(i2);
                }
            }
            _277_GeneralSettingData.dataList2.add(list.get(i));
        }
    }

    private void update277VehicleMonitorActivity(Bundle bundle) {
        LogUtil.showLog("updateVehicleMonitorActivity");
        if (getActivity() == null) {
            LogUtil.showLog("MonitorActivity handler==null");
        } else if (getActivity() instanceof VehicleMonitorActivity) {
            updateActivity(bundle);
        }
    }

    private void updateVehicleDiagnosisActivity(Bundle bundle) {
        LogUtil.showLog("updateVehicleDiagnosisActivity");
        if (getActivity() == null) {
            LogUtil.showLog("DiagnosisActivity mMgrRefreshUiInterface==null");
        } else if (getActivity() instanceof VehicleDiagnosisActivity) {
            updateActivity(bundle);
        }
    }

    private void setVehicleDiagnosisInfo0x76() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(0, 1, getFaultLevelString(3, 3, 0)));
        arrayList.add(new SettingUpdateEntity(0, 2, getFaultLevelString(0, 3, 6)));
        arrayList.add(new SettingUpdateEntity(0, 3, getStatusString(6, 4)));
        arrayList.add(new SettingUpdateEntity(0, 4, getFaultLevelString(2, 3, 2)));
        arrayList.add(new SettingUpdateEntity(0, 5, getFaultLevelString(1, 3, 4)));
        arrayList.add(new SettingUpdateEntity(0, 6, getStatusString(6, 5)));
        arrayList.add(new SettingUpdateEntity(0, 7, getStatusString(6, 0)));
        arrayList.add(new SettingUpdateEntity(0, 8, getStatusString(7, 1)));
        arrayList.add(new SettingUpdateEntity(0, 9, getStatusString(6, 6)));
        arrayList.add(new SettingUpdateEntity(0, 10, getStatusString(6, 7)));
        arrayList.add(new SettingUpdateEntity(0, 11, getFaultLevelString(7, 4, 0)));
        arrayList.add(new SettingUpdateEntity(0, 12, getStatusString(7, 7)));
        arrayList.add(new SettingUpdateEntity(0, 13, getFaultLevelString(5, 4, 4)));
        arrayList.add(new SettingUpdateEntity(0, 14, getStatusString(7, 5)));
        arrayList.add(new SettingUpdateEntity(0, 15, getFaultLevelString(4, 4, 6)));
        arrayList.add(new SettingUpdateEntity(0, 16, getStatusString(7, 6)));
        arrayList.add(new SettingUpdateEntity(0, 17, getFaultLevelString(6, 4, 2)));
        arrayList.add(new SettingUpdateEntity(0, 18, getStatusString(7, 4)));
        arrayList.add(new SettingUpdateEntity(0, 19, getStatusString(6, 2)));
        arrayList.add(new SettingUpdateEntity(0, 20, getStatusString(7, 2)));
        arrayList.add(new SettingUpdateEntity(0, 21, getStatusString(6, 3)));
        arrayList.add(new SettingUpdateEntity(0, 22, getStatusString(7, 3)));
        arrayList.add(new SettingUpdateEntity(0, 23, getStatusString(5, 0)));
        arrayList.add(new SettingUpdateEntity(0, 24, getStatusString(5, 1)));
        arrayList.add(new SettingUpdateEntity(0, 25, getStatusString(5, 2)));
        arrayList.add(new SettingUpdateEntity(0, 26, getStatusString(5, 3)));
        arrayList.add(new SettingUpdateEntity(0, 27, getStatusString(5, 4)));
        arrayList.add(new SettingUpdateEntity(0, 28, getStatusString(5, 5)));
        arrayList.add(new SettingUpdateEntity(0, 29, getStatusString(5, 6)));
        arrayList.add(new SettingUpdateEntity(0, 30, getStatusString(5, 7)));
        arrayList.add(new SettingUpdateEntity(0, 32, getStatusString(7, 0)));
        arrayList.add(new SettingUpdateEntity(0, 43, getStatusString(6, 1)));
        arrayList.add(new SettingUpdateEntity(1, 0, getStatusString(8, 0)));
        arrayList.add(new SettingUpdateEntity(1, 1, getStatusString(8, 3)));
        arrayList.add(new SettingUpdateEntity(1, 2, getStatusString(8, 4)));
        arrayList.add(new SettingUpdateEntity(1, 3, getStatusString(10, 0)));
        arrayList.add(new SettingUpdateEntity(1, 4, getStatusString(8, 2)));
        arrayList.add(new SettingUpdateEntity(1, 5, getStatusString(8, 1)));
        arrayList.add(new SettingUpdateEntity(1, 6, getStatusString(8, 7)));
        arrayList.add(new SettingUpdateEntity(1, 7, getStatusString(9, 0)));
        arrayList.add(new SettingUpdateEntity(1, 8, getStatusString(9, 1)));
        arrayList.add(new SettingUpdateEntity(1, 9, getStatusString(9, 3)));
        arrayList.add(new SettingUpdateEntity(1, 10, getStatusString(9, 4)));
        arrayList.add(new SettingUpdateEntity(1, 11, getStatusString(8, 5)));
        arrayList.add(new SettingUpdateEntity(1, 12, getStatusString(8, 6)));
        arrayList.add(new SettingUpdateEntity(1, 13, getStatusString(9, 2)));
        arrayList.add(new SettingUpdateEntity(1, 14, getStatusString(10, 2)));
        arrayList.add(new SettingUpdateEntity(1, 15, getStatusString(9, 5)));
        arrayList.add(new SettingUpdateEntity(1, 16, getStatusString(9, 6)));
        arrayList.add(new SettingUpdateEntity(1, 17, getStatusString(9, 7)));
        arrayList.add(new SettingUpdateEntity(1, 18, getStatusString(10, 1)));
        arrayList.add(new SettingUpdateEntity(1, 19, getStatusString(10, 3)));
        arrayList.add(new SettingUpdateEntity(1, 20, getStatusString(10, 4)));
        arrayList.add(new SettingUpdateEntity(1, 21, getStatusString(10, 5)));
        arrayList.add(new SettingUpdateEntity(1, 22, getStatusString(10, 6)));
        arrayList.add(new SettingUpdateEntity(1, 23, getStatusString(10, 7)));
        arrayList.add(new SettingUpdateEntity(2, 0, getStatusString(14, 0)));
        arrayList.add(new SettingUpdateEntity(2, 1, getStatusString(15, 5)));
        arrayList.add(new SettingUpdateEntity(2, 2, getStatusString(14, 2)));
        arrayList.add(new SettingUpdateEntity(2, 3, getStatusString(15, 6)));
        arrayList.add(new SettingUpdateEntity(2, 4, getStatusString(14, 4)));
        arrayList.add(new SettingUpdateEntity(2, 5, getStatusString(14, 5)));
        arrayList.add(new SettingUpdateEntity(2, 6, getStatusString(14, 6)));
        arrayList.add(new SettingUpdateEntity(2, 7, getStatusString(14, 7)));
        arrayList.add(new SettingUpdateEntity(2, 8, getStatusString(14, 3)));
        arrayList.add(new SettingUpdateEntity(2, 9, getStatusString(15, 7)));
        arrayList.add(new SettingUpdateEntity(3, 0, getStatusString(12, 0)));
        arrayList.add(new SettingUpdateEntity(3, 1, getStatusString(12, 1)));
        arrayList.add(new SettingUpdateEntity(3, 2, getStatusString(12, 2)));
        arrayList.add(new SettingUpdateEntity(3, 3, getStatusString(12, 3)));
        arrayList.add(new SettingUpdateEntity(3, 4, getStatusString(12, 4)));
        arrayList.add(new SettingUpdateEntity(3, 5, getStatusString(12, 5)));
        arrayList.add(new SettingUpdateEntity(3, 6, getStatusString(12, 6)));
        arrayList.add(new SettingUpdateEntity(3, 7, getStatusString(12, 7)));
        arrayList.add(new SettingUpdateEntity(3, 8, getStatusString(13, 0)));
        arrayList.add(new SettingUpdateEntity(3, 9, getStatusString(13, 1)));
        arrayList.add(new SettingUpdateEntity(3, 10, getStatusString(13, 2)));
        arrayList.add(new SettingUpdateEntity(3, 11, getStatusString(13, 3)));
        arrayList.add(new SettingUpdateEntity(4, 0, getStatusString(16, 0)));
        arrayList.add(new SettingUpdateEntity(4, 1, getStatusString(16, 1)));
        arrayList.add(new SettingUpdateEntity(4, 2, getStatusString(16, 2)));
        arrayList.add(new SettingUpdateEntity(4, 3, getStatusString(16, 3)));
        arrayList.add(new SettingUpdateEntity(4, 4, getStatusString(16, 4)));
        arrayList.add(new SettingUpdateEntity(4, 5, getStatusString(16, 5)));
        arrayList.add(new SettingUpdateEntity(4, 6, getStatusString(16, 6)));
        arrayList.add(new SettingUpdateEntity(4, 7, getStatusString(16, 7)));
        arrayList.add(new SettingUpdateEntity(5, 0, getStatusString(11, 0)));
        arrayList.add(new SettingUpdateEntity(5, 1, getStatusString(11, 1)));
        arrayList.add(new SettingUpdateEntity(5, 2, getStatusString(11, 2)));
        arrayList.add(new SettingUpdateEntity(5, 3, getStatusString(11, 3)));
        arrayList.add(new SettingUpdateEntity(5, 4, getStatusString(11, 4)));
        arrayList.add(new SettingUpdateEntity(5, 5, getStatusString(11, 5)));
        arrayList.add(new SettingUpdateEntity(5, 6, getStatusString(11, 6)));
        arrayList.add(new SettingUpdateEntity(5, 7, getStatusString(11, 7)));
        arrayList.add(new SettingUpdateEntity(6, 0, getStatusString(15, 0)));
        arrayList.add(new SettingUpdateEntity(6, 1, getStatusString(15, 1)));
        arrayList.add(new SettingUpdateEntity(6, 2, getStatusString(15, 2)));
        arrayList.add(new SettingUpdateEntity(6, 3, getStatusString(15, 3)));
        arrayList.add(new SettingUpdateEntity(6, 4, getStatusString(15, 4)));
        showDiagnosisWindow();
        updateGeneralSettingData(arrayList);
        updateVehicleDiagnosisActivity(getBundle());
    }

    private void setVehicleDiagnosisInfo0x7A() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 33, getStatusString(2, 5)));
        arrayList.add(new SettingUpdateEntity(0, 34, getStatusString(2, 4)));
        arrayList.add(new SettingUpdateEntity(0, 35, getStatusString(2, 3)));
        arrayList.add(new SettingUpdateEntity(0, 36, getStatusString(2, 2)));
        arrayList.add(new SettingUpdateEntity(0, 37, getStatusString(2, 1)));
        arrayList.add(new SettingUpdateEntity(0, 44, getDowntimeStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new SettingUpdateEntity(1, 24, getStatusString(3, 0)));
        arrayList.add(new SettingUpdateEntity(1, 25, getStatusString(3, 1)));
        arrayList.add(new SettingUpdateEntity(1, 26, getStatusString(3, 2)));
        arrayList.add(new SettingUpdateEntity(1, 27, getStatusString(3, 3)));
        arrayList.add(new SettingUpdateEntity(1, 28, getStatusString(3, 4)));
        arrayList.add(new SettingUpdateEntity(1, 29, getStatusString(3, 5)));
        arrayList.add(new SettingUpdateEntity(1, 30, getStatusString(3, 6)));
        arrayList.add(new SettingUpdateEntity(1, 31, getStatusString(3, 7)));
        arrayList.add(new SettingUpdateEntity(1, 32, getStatusString(4, 0)));
        arrayList.add(new SettingUpdateEntity(1, 33, getStatusString(4, 1)));
        arrayList.add(new SettingUpdateEntity(1, 34, getStatusString(4, 2)));
        arrayList.add(new SettingUpdateEntity(1, 35, getStatusString(4, 3)));
        arrayList.add(new SettingUpdateEntity(1, 36, getStatusString(4, 4)));
        arrayList.add(new SettingUpdateEntity(1, 37, getStatusString(4, 5)));
        arrayList.add(new SettingUpdateEntity(1, 38, getStatusString(4, 6)));
        arrayList.add(new SettingUpdateEntity(1, 39, getStatusString(4, 7)));
        arrayList.add(new SettingUpdateEntity(1, 40, getStatusString(5, 0)));
        arrayList.add(new SettingUpdateEntity(1, 41, getStatusString(5, 1)));
        arrayList.add(new SettingUpdateEntity(1, 42, getStatusString(5, 2)));
        arrayList.add(new SettingUpdateEntity(1, 43, getStatusString(5, 3)));
        arrayList.add(new SettingUpdateEntity(1, 44, getStatusString(5, 4)));
        arrayList.add(new SettingUpdateEntity(1, 45, getStatusString(5, 5)));
        arrayList.add(new SettingUpdateEntity(1, 46, getStatusString(5, 6)));
        arrayList.add(new SettingUpdateEntity(1, 47, getStatusString(5, 7)));
        arrayList.add(new SettingUpdateEntity(1, 48, getStatusString(6, 0)));
        arrayList.add(new SettingUpdateEntity(1, 49, getStatusString(6, 1)));
        arrayList.add(new SettingUpdateEntity(1, 50, getStatusString(6, 2)));
        arrayList.add(new SettingUpdateEntity(1, 51, getStatusString(6, 3)));
        arrayList.add(new SettingUpdateEntity(1, 52, getStatusString(6, 4)));
        arrayList.add(new SettingUpdateEntity(1, 53, getStatusString(6, 5)));
        arrayList.add(new SettingUpdateEntity(1, 54, getStatusString(6, 6)));
        arrayList.add(new SettingUpdateEntity(1, 55, getStatusString(6, 7)));
        arrayList.add(new SettingUpdateEntity(1, 56, getStatusString(7, 0)));
        arrayList.add(new SettingUpdateEntity(1, 57, getStatusString(7, 1)));
        arrayList.add(new SettingUpdateEntity(1, 58, getStatusString(7, 2)));
        arrayList.add(new SettingUpdateEntity(1, 59, getStatusString(7, 3)));
        arrayList.add(new SettingUpdateEntity(1, 60, getStatusString(7, 4)));
        arrayList.add(new SettingUpdateEntity(1, 61, getStatusString(7, 5)));
        arrayList.add(new SettingUpdateEntity(1, 62, getStatusString(7, 6)));
        arrayList.add(new SettingUpdateEntity(2, 10, getStatusString(8, 2)));
        arrayList.add(new SettingUpdateEntity(2, 11, getStatusString(8, 3)));
        arrayList.add(new SettingUpdateEntity(2, 12, getStatusString(8, 4)));
        arrayList.add(new SettingUpdateEntity(2, 13, getStatusString(8, 5)));
        arrayList.add(new SettingUpdateEntity(2, 14, getStatusString(8, 6)));
        arrayList.add(new SettingUpdateEntity(2, 15, getStatusString(8, 7)));
        arrayList.add(new SettingUpdateEntity(2, 16, getStatusString(9, 0)));
        arrayList.add(new SettingUpdateEntity(2, 17, getStatusString(9, 1)));
        arrayList.add(new SettingUpdateEntity(2, 18, getStatusString(9, 2)));
        arrayList.add(new SettingUpdateEntity(2, 19, getStatusString(9, 3)));
        arrayList.add(new SettingUpdateEntity(2, 20, getStatusString(9, 4)));
        arrayList.add(new SettingUpdateEntity(2, 21, getStatusString(9, 5)));
        arrayList.add(new SettingUpdateEntity(2, 22, getStatusString(10, 0)));
        arrayList.add(new SettingUpdateEntity(2, 23, getStatusString(10, 1)));
        arrayList.add(new SettingUpdateEntity(2, 24, getStatusString(10, 2)));
        arrayList.add(new SettingUpdateEntity(2, 25, getStatusString(10, 3)));
        arrayList.add(new SettingUpdateEntity(2, 26, getStatusString(10, 4)));
        arrayList.add(new SettingUpdateEntity(2, 27, getStatusString(10, 5)));
        arrayList.add(new SettingUpdateEntity(2, 28, getStatusString(10, 7)));
        arrayList.add(new SettingUpdateEntity(4, 8, getStatusString(11, 4)));
        arrayList.add(new SettingUpdateEntity(4, 9, getStatusString(11, 5)));
        arrayList.add(new SettingUpdateEntity(4, 10, getStatusString(11, 6)));
        arrayList.add(new SettingUpdateEntity(4, 11, getStatusString(11, 7)));
        arrayList.add(new SettingUpdateEntity(5, 8, getStatusString(12, 0)));
        arrayList.add(new SettingUpdateEntity(5, 9, getStatusString(12, 1)));
        arrayList.add(new SettingUpdateEntity(5, 10, getFaultSign(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 4))));
        updateGeneralSettingData(arrayList);
        updateVehicleDiagnosisActivity(getBundle());
    }

    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("head", Integer.toHexString(this.mCanBusInfoInt[1]));
        return bundle;
    }

    private void setVehicleStatus0x33() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        float f = ((iArr[4] * 256) + iArr[5]) / 10.0f;
        arrayList.add(new SettingUpdateEntity(0, 0, Float.valueOf(f)));
        arrayList.add(new SettingUpdateEntity(1, 0, Float.valueOf(f)));
        int[] iArr2 = this.mCanBusInfoInt;
        float f2 = (((iArr2[6] * 256) + iArr2[7]) - 5000) / 10.0f;
        if (f2 > 500.0f) {
            f2 = 500.0f;
        }
        if (f2 < -500.0f) {
            f2 = -500.0f;
        }
        arrayList.add(new SettingUpdateEntity(1, 1, Float.valueOf(f2)));
        Log.i("", "setVehicleStatus0x33: mVehicleMode: " + this.mVehicleMode);
        if (this.mVehicleMode == 1) {
            arrayList.add(new SettingUpdateEntity(0, 1, 0));
            if (f2 > 0.0f) {
                arrayList.add(new SettingUpdateEntity(0, 3, 0));
            } else if (f2 < 0.0f) {
                arrayList.add(new SettingUpdateEntity(0, 3, Float.valueOf(f2)));
            }
        } else {
            arrayList.add(new SettingUpdateEntity(0, 1, Float.valueOf(f2)));
            arrayList.add(new SettingUpdateEntity(0, 3, 0));
        }
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[8])));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(1, 2, Float.valueOf(((iArr3[9] * 256) + iArr3[10]) / 100.0f)));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(1, 3, Float.valueOf(((iArr4[11] * 256) + iArr4[12]) / 100.0f)));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(this.mCanBusInfoInt[13] - 40)));
        arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(this.mCanBusInfoInt[14] - 40)));
        update277GeneralSettingData(arrayList);
        update277VehicleMonitorActivity(getBundle());
    }

    private void setMotorStatus0x38() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.mCanBusInfoInt[6] - 40)));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(this.mCanBusInfoInt[7] - 40)));
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(2, 2, Float.valueOf(((iArr[8] * 256) + iArr[9]) / 10.0f)));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf((iArr2[10] * 256) + iArr2[11])));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(2, 3, Float.valueOf((((iArr3[12] * 256) + iArr3[13]) - 5000) / 10.0f)));
        update277GeneralSettingData(arrayList);
        update277VehicleMonitorActivity(getBundle());
        ArrayList arrayList2 = new ArrayList();
        arrayList2.clear();
        this.mVehicleMode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 3);
        Log.i("_277", "setMotorStatus0x38: mVehicleMode: " + this.mVehicleMode);
        arrayList2.add(new SettingUpdateEntity(0, 31, getStatusString(15, 4)));
        arrayList2.add(new SettingUpdateEntity(0, 38, getStatusString(15, 0)));
        arrayList2.add(new SettingUpdateEntity(0, 39, getStatusString(15, 1)));
        arrayList2.add(new SettingUpdateEntity(0, 40, getStatusString(15, 2)));
        arrayList2.add(new SettingUpdateEntity(0, 41, getStatusString(15, 3)));
        arrayList2.add(new SettingUpdateEntity(0, 42, getFaultLevelString(9, 15, 5)));
        showDiagnosisWindow();
        updateGeneralSettingData(arrayList2);
        updateVehicleDiagnosisActivity(getBundle());
    }

    private void setBatteryVoltage0x39() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(batteryVoltageSupporter(iArr[2], iArr[3], iArr[4]));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(batteryVoltageSupporter(iArr2[5], iArr2[6], iArr2[7]));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(batteryVoltageSupporter(iArr3[8], iArr3[9], iArr3[10]));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(batteryVoltageSupporter(iArr4[11], iArr4[12], iArr4[13]));
        update277GeneralSettingData(arrayList);
        update277VehicleMonitorActivity(getBundle());
    }

    private void setGeneratorControllerFaultInformation0x36() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 8, getFaultLevelString(8, 2, 0)));
        showDiagnosisWindow();
        arrayList.add(new SettingUpdateEntity(9, 0, getStatusString(2, 7)));
        arrayList.add(new SettingUpdateEntity(9, 1, getStatusString(2, 6)));
        arrayList.add(new SettingUpdateEntity(9, 2, getStatusString(2, 5)));
        arrayList.add(new SettingUpdateEntity(9, 3, getStatusString(2, 4)));
        arrayList.add(new SettingUpdateEntity(9, 4, getStatusString(2, 3)));
        arrayList.add(new SettingUpdateEntity(9, 5, getStatusString(2, 2)));
        arrayList.add(new SettingUpdateEntity(9, 6, getStatusString(3, 7)));
        arrayList.add(new SettingUpdateEntity(9, 7, getStatusString(3, 6)));
        arrayList.add(new SettingUpdateEntity(9, 8, getStatusString(3, 5)));
        arrayList.add(new SettingUpdateEntity(9, 9, getStatusString(3, 4)));
        arrayList.add(new SettingUpdateEntity(9, 10, getStatusString(3, 3)));
        arrayList.add(new SettingUpdateEntity(9, 11, getStatusString(3, 2)));
        arrayList.add(new SettingUpdateEntity(9, 12, getStatusString(3, 1)));
        arrayList.add(new SettingUpdateEntity(9, 13, getStatusString(3, 0)));
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(9, 14, Float.valueOf(((iArr[4] * 256) + iArr[5]) / 10.0f)));
        updateGeneralSettingData(arrayList);
        updateVehicleDiagnosisActivity(getBundle());
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private boolean is0x76DataChange() {
        if (Arrays.equals(this.m0x76DataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x76DataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x33DataChange() {
        if (Arrays.equals(this.m0x33DataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x33DataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x38DataChange() {
        if (Arrays.equals(this.m0x38DataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x38DataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x39DataChange() {
        if (Arrays.equals(this.m0x39DataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x39DataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x36DataChange() {
        if (Arrays.equals(this.m0x36DataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x36DataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x7ADataChange() {
        if (Arrays.equals(this.m0x7ADataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x7ADataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private String getFaultLevelString(int i, int i2, int i3) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[i2], i3, 2);
        checkFaultLevel(i, intFromByteWithBit);
        return "geely_e6_item_" + intFromByteWithBit;
    }

    private void checkFaultLevel(int i, int i2) {
        if (i2 == 1) {
            String[] strArr = this.mStrings;
            if (strArr[i] == null) {
                this.mIsNeedShow = true;
            }
            strArr[i] = CommUtil.getStrByResId(this.mContext, "geely_e6_fualt_" + i);
            return;
        }
        this.mStrings[i] = null;
    }

    private void showDiagnosisWindow() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._277.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mDiagnosisList.clear();
                for (String str : MsgMgr.this.mStrings) {
                    if (str != null) {
                        MsgMgr.this.mDiagnosisList.add(new DiagnosisEntity(str));
                    }
                }
                if (MsgMgr.this.mIsNeedShow || DialogUtil.mHasAdded) {
                    Log.i("ljq", "callback: mIsNeedShow: " + MsgMgr.this.mIsNeedShow + ", mHasAdded: " + DialogUtil.mHasAdded);
                    DialogUtil.getInstance().showDiagnosisWindow(MsgMgr.this.mContext, MsgMgr.this.mDiagnosisList, MsgMgr.this.mIsNeedShow);
                    MsgMgr.this.mIsNeedShow = false;
                    Intent intent = new Intent();
                    intent.setFlags(268435456);
                    intent.setComponent(Constant.VehicleDiagnosisActivity);
                    MsgMgr.this.mContext.startActivity(intent);
                }
            }
        });
    }

    private String getStatusString(int i, int i2) {
        return "geely_e6_item_" + (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[i], i2) ? 4 : 0);
    }

    private SettingUpdateEntity batteryVoltageSupporter(int i, int i2, int i3) {
        if (i < 1) {
            i = 8;
        }
        return new SettingUpdateEntity(1, (i - 1) + 7, Float.valueOf(((i2 * 256) + i3) / 100.0f));
    }

    private boolean isAlertChange() {
        if (Arrays.equals(this.mStringsNow, this.mStrings)) {
            return false;
        }
        String[] strArr = this.mStrings;
        this.mStringsNow = (String[]) Arrays.copyOf(strArr, strArr.length);
        return true;
    }

    private String getFaultSign(int i) {
        return i == 0 ? "geely_e6_item_0" : "_268_diagnosis_page6_item11_" + i;
    }
}
