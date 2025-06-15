package com.hzbhd.canbus.car._343;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr mUiMgr;

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
        getUigMgr(this.mContext).makeConnection();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            set0x21WheelKeyInfo();
        } else if (i == 35) {
            set0x23AirInfo();
        } else {
            if (i != 127) {
                return;
            }
            set0x7FVersionInfo();
        }
    }

    private void set0x7FVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x23AirInfo() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        int i = this.mCanBusInfoInt[3];
        if (i == 1) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        if (GeneralAirData.front_wind_level == 1) {
            GeneralAirData.power = false;
        } else {
            GeneralAirData.power = true;
        }
        GeneralAirData.center_wheel = this.mCanBusInfoInt[5] + "LEVEL";
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x21WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 6) {
            buttonKey(3);
            return;
        }
        if (i == 7) {
            buttonKey(2);
            return;
        }
        switch (i) {
            case 9:
                buttonKey(14);
                break;
            case 10:
                buttonKey(15);
                break;
            case 11:
                buttonKey(45);
                break;
            case 12:
                buttonKey(46);
                break;
            default:
                switch (i) {
                    case 32:
                        buttonKey(30);
                        break;
                    case 33:
                        buttonKey(129);
                        break;
                    case 34:
                        buttonKey(75);
                        break;
                    case 35:
                        buttonKey(47);
                        break;
                    case 36:
                        buttonKey(48);
                        break;
                    case 37:
                        buttonKey(27);
                        break;
                    case 38:
                        buttonKey(29);
                        break;
                    case 39:
                        buttonKey(15);
                        break;
                    case 40:
                        buttonKey(14);
                        break;
                    case 41:
                        buttonKey(45);
                        break;
                    case 42:
                        buttonKey(46);
                        break;
                    case 43:
                        buttonKey(7);
                        break;
                    case 44:
                        buttonKey(8);
                        break;
                    case 45:
                        buttonKey(HotKeyConstant.K_SLEEP);
                        break;
                    case 46:
                        buttonKey(31);
                        break;
                    case 47:
                        buttonKey(4);
                        break;
                    case 48:
                        buttonKey(58);
                        break;
                }
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }
}
