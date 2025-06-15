package com.hzbhd.canbus.car._160;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
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
        if (byteArrayToIntArray[1] != 85) {
            return;
        }
        set0x55AirInfo();
    }

    private void set0x55AirInfo() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.front_left_temperature = (this.mCanBusInfoInt[2] * 0.5d) + getTempUnitC(this.mContext);
        GeneralAirData.front_right_temperature = (this.mCanBusInfoInt[3] * 0.5d) + getTempUnitC(this.mContext);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])) {
            GeneralAirData.in_out_cycle = false;
        } else if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])) {
            GeneralAirData.in_out_cycle = true;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            if (this.mCanBusInfoInt[7] > 200) {
                updateOutDoorTemp(this.mContext, (-(255 - this.mCanBusInfoInt[7])) + getTempUnitC(this.mContext));
            } else {
                updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + getTempUnitC(this.mContext));
            }
        } else {
            updateOutDoorTemp(this.mContext, "");
        }
        updateAirActivity(this.mContext, 1001);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }
}
