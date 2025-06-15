package com.hzbhd.canbus.car._290;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.car_cus._290.GeneralCwData;
import com.hzbhd.canbus.car_cus._290.MainActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;


public class MsgMgr extends AbstractMsgMgr {
    private static final int GET_10_0x18FEA217 = 419340823;
    private static final int GET_10_0x18FFC119 = 419414297;
    private static final int GET_10_0x18FFC219 = 419414553;
    public static final String SAVE_AIRINFO_1 = "_290_airInfo1";
    public static final String SAVE_AIRINFO_2 = "_290_airInfo2";
    public static final String SAVE_COMMON_SWITCH = "_290_commonSwitch";
    public static final String SAVE_SPLIT = ",";
    private static final String TAG = "_290_MsgMgr";
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        start290MainActivity(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        CommUtil.printHexString(TAG, bArr);
        int idFromCanbusInfo = getIdFromCanbusInfo(bArr);
        if (idFromCanbusInfo == GET_10_0x18FEA217) {
            switchCamera();
        } else if (idFromCanbusInfo == GET_10_0x18FFC119) {
            updateTemperature();
        } else {
            if (idFromCanbusInfo != GET_10_0x18FFC219) {
                return;
            }
            updateWind();
        }
    }

    private void switchCamera() {
        if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[9], 2)) {
            updateMainActivity(this.mContext, 1006);
        }
        if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 2)) {
            updateMainActivity(this.mContext, 1005);
        }
    }

    private void updateTemperature() {
        GeneralCwData.air_temperature = this.mCanBusInfoInt[6];
        GeneralCwData.in_car_temperature = this.mCanBusInfoInt[7];
        GeneralCwData.out_car_temperature = this.mCanBusInfoInt[8];
        GeneralCwData.air_power = DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[10], 0);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 2);
        if (intFromByteWithBit == 1) {
            GeneralCwData.air_cold = true;
            GeneralCwData.air_light = false;
            GeneralCwData.air_wind = false;
        } else if (intFromByteWithBit == 2) {
            GeneralCwData.air_cold = false;
            GeneralCwData.air_light = true;
            GeneralCwData.air_wind = false;
        } else if (intFromByteWithBit == 3) {
            GeneralCwData.air_cold = false;
            GeneralCwData.air_light = false;
            GeneralCwData.air_wind = true;
        }
        GeneralCwData.air_in_out = DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[10], 3);
        if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 0)) {
            GeneralCwData.error = "09";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 1)) {
            GeneralCwData.error = "06";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 2)) {
            GeneralCwData.error = "07";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 3)) {
            GeneralCwData.error = "08";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 4)) {
            GeneralCwData.error = "02";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 5)) {
            GeneralCwData.error = "03";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 6)) {
            GeneralCwData.error = "21";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[12], 7)) {
            GeneralCwData.error = "22";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[13], 0)) {
            GeneralCwData.error = "33";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[13], 1)) {
            GeneralCwData.error = "31";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[13], 2)) {
            GeneralCwData.error = "32";
        } else {
            GeneralCwData.error = "";
        }
        updateMainActivity(this.mContext, 1003);
    }

    private void updateWind() {
        GeneralCwData.air_wind_power = this.mCanBusInfoInt[9];
        GeneralCwData.air_auto = DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[9], 3);
        GeneralCwData.air_window_wind = DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[9], 1);
        if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 0)) {
            GeneralCwData.error = "00";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 1)) {
            GeneralCwData.error = "01";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 2)) {
            GeneralCwData.error = "04";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 3)) {
            GeneralCwData.error = "05";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 4)) {
            GeneralCwData.error = "10";
        } else if (DataHandleUtils.getIntByteWithBit(this.mCanBusInfoInt[8], 5)) {
            GeneralCwData.error = "99";
        } else {
            GeneralCwData.error = "";
        }
        updateMainActivity(this.mContext, 1003);
    }

    private void updateMainActivity(Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_chengwei_what", i);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            updateActivity(bundle);
        }
    }

    private int getIdFromCanbusInfo(byte[] bArr) {
        return (bArr[4] & 255) | ((bArr[1] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 8);
    }

    private void start290MainActivity(Context context) {
        Intent intent = new Intent();
        intent.setComponent(Constant.ChengWeiMainActivity290);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }
}
