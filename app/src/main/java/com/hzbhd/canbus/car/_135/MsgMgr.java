package com.hzbhd.canbus.car._135;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
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
        int[] iArr;
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        MyLog.temporaryTracking("—————HIWORLD DATA COMING—————");
        int i = 0;
        while (true) {
            iArr = this.mCanBusInfoInt;
            if (i >= iArr.length) {
                break;
            }
            MyLog.temporaryTracking("mCanBusInfoInt[" + i + "]=" + this.mCanBusInfoInt[i]);
            i++;
        }
        int i2 = iArr[1];
        if (i2 == 18) {
            set0xFCWheelKeyInfo();
        } else {
            if (i2 != 19) {
                return;
            }
            set0xFDAirInfo();
        }
    }

    private void set0xFDAirInfo() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.center_wheel = this.mCanBusInfoInt[2] + "." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + getTempUnitC(this.mContext);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0xFCWheelKeyInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(3);
                break;
            case 5:
                buttonKey(14);
                break;
            case 6:
                buttonKey(15);
                break;
            case 8:
                buttonKey(45);
                break;
            case 9:
                buttonKey(46);
                break;
            case 10:
                buttonKey(2);
                break;
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }
}
