package com.hzbhd.canbus.car._397;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;


public class MsgMgr extends AbstractMsgMgr {
    private static int DownValue;
    private static int LeftValue;
    private static int RightValue;
    private static int UPValue;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int mDifferentID;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        int currentCanDifferentId = getCurrentCanDifferentId();
        this.mDifferentID = currentCanDifferentId;
        if (currentCanDifferentId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 16, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mDifferentID = getCurrentCanDifferentId();
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            setWheelKey0x11();
            setTrackDate0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
        } else if (i == 116) {
            setTurnKey0x74();
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

    private void setTurnKey0x74() {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyClick0x74(0);
                break;
            case 1:
                PanelKnobClick(45, iArr[3]);
                break;
            case 2:
                PanelKnobClick(46, iArr[3]);
                break;
            case 3:
                PanelKnobClick(47, iArr[3]);
                break;
            case 4:
                PanelKnobClick(48, iArr[3]);
                break;
            case 5:
                realKeyClick0x74(33);
                break;
            case 6:
                realKeyClick0x74(34);
                break;
            case 7:
                realKeyClick0x74(35);
                break;
            case 8:
                realKeyClick0x74(36);
                break;
            case 9:
                realKeyClick0x74(49);
                break;
            case 10:
                realKeyClick0x74(50);
                break;
            case 11:
                realKeyClick0x74(151);
                break;
            case 12:
                realKeyClick0x74(76);
                break;
            case 13:
                realKeyClick0x74(68);
                break;
        }
    }

    private void PanelKnobClick(int i, int i2) {
        realKeyClick3(this.mContext, i, i2, 1);
    }

    private void setAirInfo0x31() {
        GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 2) {
            int i2 = this.mDifferentID;
            if (i2 == 2 || i2 == 3) {
                return;
            }
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_defog = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 12) {
            int i3 = this.mDifferentID;
            if (i3 == 2 || i3 == 3) {
                return;
            }
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 13) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
    }

    private String ResolveTemp(int i) {
        String str = (i * 0.5d) + getTempUnitC(this.mContext);
        if (i == 254) {
            str = "LOW_TEMP";
        }
        return i == 255 ? "HIGH_TEMP" : str;
    }

    private void setTrackDate0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick0x11(0);
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
            realKeyClick0x11(HotKeyConstant.K_PHONE_ON_OFF);
            return;
        }
        switch (i) {
            case 8:
                realKeyClick0x11(45);
                break;
            case 9:
                realKeyClick0x11(46);
                break;
            case 10:
                realKeyClick0x11(2);
                break;
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realKeyClick0x74(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private void setDoorInfo0x12() {
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }
}
