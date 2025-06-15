package com.hzbhd.canbus.car._202;

import android.content.Context;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int doorData = 0;
    int eachId;
    int[] lastAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr mUiMgr;
    int[] thisAirData;

    private boolean getInOutCycleState(int i) {
        return i != 0;
    }

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
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        MyLog.temporaryTracking("RADIO INFO CHANGE");
        if (!str.equals("FM1") && !str.equals("FM2") && !str.equals("FM3") && !str.equals("AM1")) {
            str.equals("AM2");
        }
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, (byte) 0}, str2.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, (byte) (b == 9 ? 6 : 13)}, str4.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, (byte) (b == 9 ? 6 : 13)}, str.getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bytes = "DTV PLAYING".getBytes(StandardCharsets.UTF_8);
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 8}, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bytes = "ATV PLAYING".getBytes(StandardCharsets.UTF_8);
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 8}, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        byte[] bytes = "Talking".getBytes(StandardCharsets.UTF_8);
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 10}, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "AUX PLAYING".getBytes(StandardCharsets.UTF_8));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        byte[] bytes = "MEDIA OFF".getBytes(StandardCharsets.UTF_8);
        getUiMgr(this.mContext).send0xD2Info(new byte[]{22, -46, 0}, bytes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 114) {
            set0x72VehicleStatus();
        } else {
            if (i != 115) {
                return;
            }
            set0x73HVACStatus();
        }
    }

    private void set0x73HVACStatus() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
            MyLog.temporaryTracking("更新环境温度");
            updateOutDoorTemp(this.mContext, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) + getTempUnitC(this.mContext));
        }
        this.mCanBusInfoInt[8] = 0;
        MyLog.temporaryTracking("0x73进来");
        if (this.thisAirData == null) {
            this.thisAirData = new int[6];
        }
        int[] iArr = this.thisAirData;
        int[] iArr2 = this.mCanBusInfoInt;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        iArr[2] = iArr2[2];
        iArr[3] = iArr2[3];
        iArr[4] = iArr2[4];
        iArr[5] = iArr2[5];
        if (!Arrays.equals(this.lastAirData, iArr)) {
            MyLog.temporaryTracking("空调变化");
            if (this.lastAirData == null) {
                this.lastAirData = new int[6];
            }
            int[] iArr3 = this.lastAirData;
            int[] iArr4 = this.mCanBusInfoInt;
            iArr3[0] = iArr4[0];
            iArr3[1] = iArr4[1];
            iArr3[2] = iArr4[2];
            iArr3[3] = iArr4[3];
            iArr3[4] = iArr4[4];
            iArr3[5] = iArr4[5];
            setAirInfo();
        }
        int i = this.doorData;
        int i2 = this.mCanBusInfoInt[9];
        if (i != i2) {
            this.doorData = i2;
            setDoorInfo();
        }
    }

    private void setDoorInfo() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
            updateDoorView(this.mContext);
        }
    }

    private void setAirInfo() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !getInOutCycleState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            GeneralAirData.front_left_temperature = "LO";
        } else if (i == 255) {
            GeneralAirData.front_left_temperature = "HI";
        } else {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[4] + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
        } else if (i2 == 255) {
            GeneralAirData.front_right_temperature = "HI";
        } else {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[5] + getTempUnitC(this.mContext);
        }
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
        updateAirActivity(this.mContext, 1001);
        MyLog.temporaryTracking("更新空调");
    }

    private void set0x72VehicleStatus() {
        setData0_Data1();
        setData2WheelKeyInfo();
        setData4_Data5_SWA();
        setData6_Data13RadarInfo();
    }

    private void setData6_Data13RadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(10, assignRadarProgress(this.mCanBusInfoInt[8]), assignRadarProgress(this.mCanBusInfoInt[9]), assignRadarProgress(this.mCanBusInfoInt[10]), assignRadarProgress(this.mCanBusInfoInt[11]));
        RadarInfoUtil.setFrontRadarLocationData(10, assignRadarProgress(this.mCanBusInfoInt[12]), assignRadarProgress(this.mCanBusInfoInt[13]), assignRadarProgress(this.mCanBusInfoInt[14]), assignRadarProgress(this.mCanBusInfoInt[15]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setData4_Data5_SWA() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        if (i != 0) {
            GeneralParkData.trackAngle = -(i / 10);
            updateParkUi(null, this.mContext);
            MyLog.temporaryTracking("ID202:左转——转角数据:" + GeneralParkData.trackAngle);
        } else {
            GeneralParkData.trackAngle = iArr[7] / 10;
            updateParkUi(null, this.mContext);
            MyLog.temporaryTracking("ID202:右转——转角数据:" + GeneralParkData.trackAngle);
        }
    }

    private void setData2WheelKeyInfo() {
        switch (this.mCanBusInfoInt[4]) {
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

    private void setData0_Data1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit5"), get0x73Data0Satate(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit4"), get0x73Data0Satate(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit3"), get0x73Data0Satate(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit2"), get0x73Data0Satate(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit1"), get0x73Data0Satate(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit0"), get0x73Data0Satate(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_D1"), this.mCanBusInfoInt[3] + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[3]);
    }

    private int assignRadarProgress(int i) {
        if (i == 255) {
            return 0;
        }
        return (i / 25) + 1;
    }

    private String get0x73Data0Satate(boolean z) {
        if (!z) {
            return this.mContext.getString(R.string._202_state0);
        }
        return this.mContext.getString(R.string._202_state1);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyClick4(this.mContext, i);
    }
}
