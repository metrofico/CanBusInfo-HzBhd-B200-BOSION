package com.hzbhd.canbus.car._307;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean rearPower;
    private String rearTemperature;
    private int rearWind;
    private int mCallStatus = -1;
    private boolean isFirstDoor = true;
    private boolean isFirstAir = true;

    private int getRadarInt(int i) {
        if (i == 1) {
            return 7;
        }
        if (i == 2) {
            return 6;
        }
        if (i == 3) {
            return 5;
        }
        if (i == 4) {
            return 4;
        }
        if (i == 5) {
            return 3;
        }
        if (i == 6) {
            return 2;
        }
        return i == 7 ? 1 : 255;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            signalInfo0x11();
            keyEvent0x11();
            track0x11();
            return;
        }
        if (i == 18) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            if (this.isFirstDoor) {
                this.isFirstDoor = false;
                return;
            } else {
                doorStatus0x12();
                return;
            }
        }
        if (i == 33) {
            panelEvent0x21();
            return;
        }
        if (i == 65) {
            radarInfo0x41();
            return;
        }
        if (i == 69) {
            setPhoneState0x45();
            return;
        }
        if (i == 97) {
            carSettings0x61();
            return;
        }
        if (i == 197) {
            setPhoneState0xC5();
            return;
        }
        if (i == 224) {
            setMedia0xE0();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i != 49) {
            if (i != 50) {
                return;
            }
            carInfo0x32();
        } else {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            airInfo0x31();
        }
    }

    private void setPhoneState0x45() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0 || i == 9 || i == 10) {
            realKeyClick4(this.mContext, 14);
        }
    }

    private void setPhoneState0xC5() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick4(this.mContext, 0);
            return;
        }
        if (i == 1) {
            realKeyClick(this.mContext, 14);
        } else if (i == 2) {
            realKeyClick(this.mContext, 15);
        } else {
            if (i != 5) {
                return;
            }
            realKeyClick(this.mContext, 15);
        }
    }

    private void setMedia0xE0() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            enterNoSource();
            realKeyClick(this.mContext, 52);
        } else if (i == 32) {
            realKeyClick(this.mContext, 77);
        } else {
            if (i != 33) {
                return;
            }
            realKeyClick(this.mContext, 76);
        }
    }

    private void carSettings0x61() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1);
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(intFromByteWithBit2)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit3)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(intFromByteWithBit4)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(intFromByteWithBit5)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(intFromByteWithBit6)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(intFromByteWithBit7)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(intFromByteWithBit8)));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(intFromByteWithBit9)));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(intFromByteWithBit10)));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(intFromByteWithBit11)));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(intFromByteWithBit12)));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(intFromByteWithBit13)));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(intFromByteWithBit14)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void carInfo0x32() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[4] * 256) + iArr[5];
        int i2 = (iArr[6] * 256) + iArr[7];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 7, i + " rmp/min"));
        arrayList.add(new DriverUpdateEntity(0, 8, i2 + " km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(i2);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void radarInfo0x41() {
        RadarInfoUtil.setRearRadarLocationData(7, getRadarInt(this.mCanBusInfoInt[2]), getRadarInt(this.mCanBusInfoInt[3]), getRadarInt(this.mCanBusInfoInt[4]), getRadarInt(this.mCanBusInfoInt[5]));
        RadarInfoUtil.setFrontRadarLocationData(7, getRadarInt(this.mCanBusInfoInt[6]), getRadarInt(this.mCanBusInfoInt[7]), getRadarInt(this.mCanBusInfoInt[8]), getRadarInt(this.mCanBusInfoInt[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void airInfo0x31() {
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        boolean z = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.power = boolBit6;
        GeneralAirData.rear_power = boolBit4;
        GeneralAirData.auto = boolBit3;
        GeneralAirData.sync = boolBit2;
        GeneralAirData.ac = boolBit0;
        GeneralAirData.in_out_cycle = z;
        GeneralAirData.rear_defog = boolBit5;
        GeneralAirData.front_defog = boolBit42;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        GeneralAirData.rear_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[12]);
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        if (this.isFirstAir) {
            this.isFirstAir = false;
            this.rearPower = GeneralAirData.rear_power;
            this.rearWind = GeneralAirData.rear_wind_level;
            this.rearTemperature = GeneralAirData.rear_temperature;
            return;
        }
        if (this.rearPower != boolBit4 || this.rearWind != this.mCanBusInfoInt[11] || !GeneralAirData.rear_temperature.equals(this.rearTemperature)) {
            updateAirActivity(this.mContext, 1002);
        } else {
            updateAirActivity(this.mContext, 1001);
        }
        this.rearPower = GeneralAirData.rear_power;
        this.rearWind = GeneralAirData.rear_wind_level;
        this.rearTemperature = GeneralAirData.rear_temperature;
    }

    private void panelEvent0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelKeyClick(0);
            return;
        }
        if (i == 1) {
            panelKeyClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 3) {
            panelKeyClick(46);
            return;
        }
        if (i == 4) {
            startActivity(Constant.CanBusMainActivity);
            return;
        }
        if (i == 6) {
            panelKeyClick(50);
            return;
        }
        if (i == 40) {
            panelKeyClick(68);
            return;
        }
        if (i == 43) {
            panelKeyClick(52);
            return;
        }
        if (i == 44) {
            panelKeyClick(2);
        } else if (i == 69) {
            panelKeyClick(7);
        } else {
            if (i != 70) {
                return;
            }
            panelKeyClick(8);
        }
    }

    private void doorStatus0x12() {
        GeneralDoorData.isShowMasterDriverSeatBelt = true;
        GeneralDoorData.isShowCoPilotSeatBelt = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void track0x11() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void keyEvent0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 8) {
            realKeyClick(45);
            return;
        }
        if (i == 9) {
            realKeyClick(46);
            return;
        }
        if (i == 24) {
            realKeyClick(4);
            return;
        }
        if (i != 34) {
            switch (i) {
                case 0:
                    realKeyClick(0);
                    break;
                case 1:
                    realKeyClick(7);
                    break;
                case 2:
                    realKeyClick(8);
                    break;
                case 3:
                    realKeyClick(3);
                    break;
                case 4:
                    realKeyClick(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick(14);
                    break;
                case 6:
                    realKeyClick(15);
                    break;
                default:
                    switch (i) {
                        case 13:
                            realKeyClick(45);
                            break;
                        case 14:
                            realKeyClick(46);
                            break;
                        case 15:
                            realKeyClick(49);
                            break;
                        case 16:
                            realKeyClick(50);
                            break;
                    }
            }
            return;
        }
        realKeyClick(52);
    }

    private void signalInfo0x11() {
        Context context;
        int i;
        Context context2;
        int i2;
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2);
        boolean boolBit12 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10]);
        boolean boolBit02 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
        ArrayList arrayList = new ArrayList();
        if (boolBit3) {
            context = this.mContext;
            i = R.string.hand_brake_up;
        } else {
            context = this.mContext;
            i = R.string.hand_brake_down;
        }
        arrayList.add(new DriverUpdateEntity(0, 0, context.getString(i)));
        if (boolBit2) {
            context2 = this.mContext;
            i2 = R.string.reversing;
        } else {
            context2 = this.mContext;
            i2 = R.string.non_reverse;
        }
        arrayList.add(new DriverUpdateEntity(0, 1, context2.getString(i2)));
        arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getString(boolBit1 ? R.string.headlights_on : R.string.headlights_off)));
        arrayList.add(new DriverUpdateEntity(0, 3, this.mContext.getString(boolBit0 ? R.string.acc_electric_on : R.string.acc_electric_off)));
        arrayList.add(new DriverUpdateEntity(0, 4, getBigLight(intFromByteWithBit)));
        arrayList.add(new DriverUpdateEntity(0, 5, getLight(boolBit12, boolBit02)));
        arrayList.add(new DriverUpdateEntity(0, 6, getLockStatus(intFromByteWithBit2)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.stringGetBytes(str, "UTF-8"), 20));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendMsg1(getRadioData1(str), str2 + " " + getRadioUnit(str));
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        sendMsg1(0, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMsg1(13, str4);
        sendMsg2(str6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        sendMsg1(0, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMsg1(13, i + "/" + i2);
        sendMsg2(str2 + ":" + str + ":" + str4);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        sendMsg1(0, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i8, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, (byte) i9});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z) {
            if (this.mCallStatus != -1) {
                sendPhone(0, bArr);
                return;
            }
            return;
        }
        sendPhone(6, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        this.mCallStatus = 1;
        sendPhone(1, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        this.mCallStatus = 2;
        sendPhone(2, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        this.mCallStatus = -1;
        sendPhone(5, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        sendPhone(4, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        sendMsg1(10, str);
        sendMsg2(str2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        sendMsg1(0, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMsg1(8, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        super.atvDestdroy();
        sendMsg1(0, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMsg1(8, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1 || i6 == 5) {
            sendMsg1(7, " ");
        } else {
            sendMsg1(6, " ");
        }
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMsg1(12, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        sendMsg1(0, " ");
        sendMsg2(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            sendMsg1_off(0, " ");
        } else {
            sendMsg1(0, " ");
        }
        sendMsg2(" ");
    }

    private void sendPhone(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -51, (byte) i, 0, 0}, DataHandleUtils.makeBytesFixedLength(bArr, 24)));
    }

    private void sendMsg1(int i, String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 1, (byte) i}, DataHandleUtils.makeBytesFixedLength(str.getBytes(), 30)));
    }

    private void sendMsg1_off(int i, String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, (byte) i}, DataHandleUtils.makeBytesFixedLength(str.getBytes(), 30)));
    }

    private void sendMsg2(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeBytesFixedLength(str.getBytes(), 32)));
    }

    private void sendMsg3(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeBytesFixedLength(str.getBytes(), 32)));
    }

    private void send0xE0(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0, 0});
    }

    private void panelKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private String getRadioUnit(String str) {
        return str.contains("AM") ? "KHz" : "MHz";
    }

    private int getRadioData1(String str) {
        if (str.contains("FM2")) {
            return 2;
        }
        if (str.contains("FM3")) {
            return 3;
        }
        if (str.contains("AM1")) {
            return 4;
        }
        return str.contains("AM2") ? 5 : 1;
    }

    private String getLockStatus(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._307_value_8);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._307_value_9);
        }
        return this.mContext.getString(R.string._307_value_7);
    }

    private String getLight(boolean z, boolean z2) {
        if (z2 && z) {
            return this.mContext.getString(R.string._307_value_6);
        }
        if (z) {
            return this.mContext.getString(R.string._307_value_4);
        }
        if (z2) {
            return this.mContext.getString(R.string._307_value_5);
        }
        return this.mContext.getString(R.string._16_value_29);
    }

    private String getBigLight(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._307_value_2);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._307_value_3);
        }
        return this.mContext.getString(R.string._307_value_1);
    }

    private void startActivity(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mContext.startActivity(intent);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[13];
        return (i < 0 || i > 250) ? "" : ((i * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
