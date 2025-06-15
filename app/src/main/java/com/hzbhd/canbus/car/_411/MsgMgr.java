package com.hzbhd.canbus.car._411;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;

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
        if (i == 114) {
            set0x72Swc();
        } else if (i == 115) {
            set0x73Air();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0Version();
        }
    }

    private void set0xF0Version() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x73Air() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
            updateOutDoorTemp(this.mContext, (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) - 40) + getTempUnitC(this.mContext));
        }
        this.mCanBusInfoInt[8] = 0;
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 2;
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        updateAirActivity(this.mContext, 1004);
    }

    private void set0x72Swc() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick4(this.mContext, 0);
            return;
        }
        if (i == 1) {
            realKeyClick4(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyClick4(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyClick4(this.mContext, 3);
            return;
        }
        if (i == 10) {
            realKeyClick4(this.mContext, 2);
        } else if (i == 13) {
            realKeyClick4(this.mContext, 45);
        } else {
            if (i != 14) {
                return;
            }
            realKeyClick4(this.mContext, 46);
        }
    }

    private boolean isNotAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private String getTemperature(int i) {
        return i == 1 ? "LO" : i == 255 ? "HI" : i + getTempUnitC(this.mContext);
    }
}
