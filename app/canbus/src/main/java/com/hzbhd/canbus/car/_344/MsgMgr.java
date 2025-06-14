package com.hzbhd.canbus.car._344;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr mUiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
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
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        switch (this.mCanBusInfoInt[3]) {
            case 1:
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = false;
                break;
            case 2:
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = false;
                break;
            case 6:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = false;
                break;
            case 7:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        int i = this.mCanBusInfoInt[5];
        if (i != 127) {
            GeneralAirData.front_left_temperature = getTemperature(i, 1.0d, 17.0d, "C", 0, 31);
        } else {
            GeneralAirData.front_left_temperature = "X";
        }
        int i2 = this.mCanBusInfoInt[6];
        if (i2 != 127) {
            GeneralAirData.front_right_temperature = getTemperature(i2, 1.0d, 17.0d, "C", 0, 31);
        } else {
            GeneralAirData.front_right_temperature = "X";
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
            updateOutDoorTemp(this.mContext, (-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7)) + getTempUnitC(this.mContext));
        } else {
            updateOutDoorTemp(this.mContext, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) + getTempUnitC(this.mContext));
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x21WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
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
        if (i == 9) {
            buttonKey(14);
            return;
        }
        if (i == 10) {
            buttonKey(15);
        } else if (i == 13) {
            buttonKey(48);
        } else {
            if (i != 14) {
                return;
            }
            buttonKey(47);
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

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }
}
