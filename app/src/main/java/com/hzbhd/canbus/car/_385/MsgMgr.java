package com.hzbhd.canbus.car._385;

import android.content.Context;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr uiMgr;

    private void setCarModel0x26() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(context).sendCarModel();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws SecurityException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKey0x11();
            return;
        }
        if (i == 38) {
            setCarModel0x26();
        } else if (i == 49) {
            setAirInfo0x31();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirInfo0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_window = false;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
    }

    private String ResolveTemp(int i) {
        String str = (i * 0.5d) + getTempUnitC(this.mContext);
        if (i == 254) {
            str = "LOW_TEMP";
        }
        return i == 255 ? "HIGH_TEMP" : str;
    }

    private void setWheelKey0x11() throws SecurityException {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick0x11(0);
            return;
        }
        if (i == 1) {
            realKeyClick0x11(7);
            return;
        }
        if (i == 2) {
            realKeyClick0x11(8);
            return;
        }
        if (i == 3) {
            realKeyClick0x11(3);
            return;
        }
        if (i == 5) {
            realKeyClick0x11(14);
            return;
        }
        if (i == 6) {
            realKeyClick0x11(15);
            return;
        }
        if (i != 57) {
            switch (i) {
                case 12:
                    realKeyClick0x11(2);
                    break;
                case 13:
                    realKeyClick0x11(45);
                    break;
                case 14:
                    realKeyClick0x11(46);
                    break;
            }
            return;
        }
        setCameraStatus0xD1();
    }

    private void setCameraStatus0xD1() throws SecurityException {
        switchFCamera(this.mContext, !SystemUtil.isForeground(this.mContext, Constant.FCameraActivity.getClassName()));
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
