package com.hzbhd.canbus.car._303;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
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
import java.util.Arrays;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static int mLeftTemp;
    public static int mRightTemp;
    private boolean isBackOpen;
    private boolean isLeftFrontDoorOpen;
    private boolean isLeftRearDoorOpen;
    private boolean isRightFrontDoorOpen;
    private boolean isRightRearDoorOpen;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int[] mFrontRadarDataNow;

    private int getLocationData(int i) {
        if (i >= 0 && i < 25) {
            return 1;
        }
        if (i >= 25 && i < 50) {
            return 2;
        }
        if (i >= 50 && i < 75) {
            return 3;
        }
        if (i >= 75 && i < 100) {
            return 4;
        }
        if (i >= 100 && i < 125) {
            return 5;
        }
        if (i >= 125 && i < 150) {
            return 6;
        }
        if (i >= 150 && i < 175) {
            return 7;
        }
        if (i >= 175 && i < 200) {
            return 8;
        }
        if (i < 200 || i >= 225) {
            return (i < 225 || i >= 255) ? 0 : 10;
        }
        return 9;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        switch (byteArrayToIntArray[1]) {
            case 17:
                setCarInfo0x11();
                setKeyEvent0x11();
                setTrackInfo0x11();
                setDoorData0x11();
                break;
            case 25:
                setMileageInfo0x19();
                break;
            case 49:
                if (!isAirMsgRepeat(bArr)) {
                    setAirInto0x31();
                    break;
                }
                break;
            case 63:
                setVersionInfo0x3F();
                break;
            case 65:
                setRadarInfo0x41();
                break;
            case 70:
                setTyresInfo0x46();
                break;
            case 72:
                setSubInfo0x48();
                break;
            case 100:
                setWindowsAndCenterInfo0x64();
                break;
            case 104:
                setLightInfo0x68();
                break;
            case 113:
                setParkingInfo0x71();
                break;
            case 125:
                setKeyNum0x7D();
                break;
            case 193:
                setUnitInfo0xC1();
                break;
            case 195:
                setUpKeepInfo0xC3();
                break;
            case HotKeyConstant.K_TIME /* 196 */:
                setTyresInfo0xC4();
                break;
        }
    }

    private void setKeyNum0x7D() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 9, String.valueOf(this.mCanBusInfoInt[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTyresInfo0xC4() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo0x3F() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setMileageInfo0x19() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 16) + (iArr[3] << 8) + iArr[4];
        int i2 = (iArr[10] << 8) + iArr[11];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 6, String.valueOf(i)));
        arrayList.add(new DriverUpdateEntity(1, 7, String.valueOf(i2)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setUpKeepInfo0xC3() {
        Context context;
        int i;
        if (this.mCanBusInfoInt[4] == 1) {
            context = this.mContext;
            i = R.string.vm_golf7_vehicle_setup_units_distance_mi_hw;
        } else {
            context = this.mContext;
            i = R.string.vm_golf7_vehicle_setup_units_distance_km;
        }
        String string = context.getString(i);
        String string2 = this.mContext.getString(R.string.time_day);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, String.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(1, 1, string));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb.append(((iArr[5] * 256) + iArr[6]) * 100).append(string).toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, sb2.append((iArr2[7] * 256) + iArr2[8]).append(string2).toString()));
        arrayList.add(new DriverUpdateEntity(1, 4, (this.mCanBusInfoInt[9] * 100) + string));
        arrayList.add(new DriverUpdateEntity(1, 5, this.mCanBusInfoInt[10] + string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(7, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1))));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte b = (byte) 0;
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) DataHandleUtils.setIntByteWithBit(0, 7, z3), (byte) i5, (byte) i6, b, b, z ? (byte) 1 : (byte) 0, (byte) (i2 + HotKeyConstant.K_SOS), (byte) i3, (byte) i4, (byte) i9});
    }

    private void setUnitInfo0xC1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(6, 2, Integer.valueOf(Math.abs(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1) - 1))));
        arrayList.add(new SettingUpdateEntity(6, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
        arrayList.add(new SettingUpdateEntity(6, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))));
        arrayList.add(new SettingUpdateEntity(6, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setParkingInfo0x71() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4] - 1));
        arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5] - 1));
        arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6] - 1));
        arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7] - 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setWindowsAndCenterInfo0x64() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLightInfo0x68() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]));
        arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
        arrayList.add(new SettingUpdateEntity(3, 6, Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRadarInfo0x41() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(10, getLocationData(this.mCanBusInfoInt[6]), getLocationData(this.mCanBusInfoInt[7]), getLocationData(this.mCanBusInfoInt[8]), getLocationData(this.mCanBusInfoInt[9]));
        RadarInfoUtil.setRearRadarLocationData(10, getLocationData(this.mCanBusInfoInt[2]), getLocationData(this.mCanBusInfoInt[3]), getLocationData(this.mCanBusInfoInt[4]), getLocationData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setSubInfo0x48() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTyresInfo0x46() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAirInto0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        setFrontBlowSwitch(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[9]);
        int[] iArr = this.mCanBusInfoInt;
        mLeftTemp = iArr[8];
        mRightTemp = iArr[9];
        if (!GeneralAirData.power) {
            GeneralAirData.front_wind_level = 0;
            GeneralAirData.auto_cycle = false;
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setDoorData0x11() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11]);
        if (this.isLeftFrontDoorOpen == GeneralDoorData.isLeftFrontDoorOpen && this.isRightFrontDoorOpen == GeneralDoorData.isRightFrontDoorOpen && this.isLeftRearDoorOpen == GeneralDoorData.isLeftRearDoorOpen && this.isRightRearDoorOpen == GeneralDoorData.isRightRearDoorOpen && this.isBackOpen == GeneralDoorData.isBackOpen) {
            return;
        }
        this.isLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
        this.isRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
        this.isLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
        this.isRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
        this.isBackOpen = GeneralDoorData.isBackOpen;
        updateDoorView(this.mContext);
    }

    private void setTrackInfo0x11() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 480, 16);
        updateParkUi(null, this.mContext);
    }

    private void setKeyEvent0x11() {
        switch (this.mCanBusInfoInt[4]) {
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
            case 5:
                realKeyClick(14);
                break;
            case 6:
                realKeyClick(15);
                break;
            case 8:
                realKeyClick(45);
                break;
            case 9:
                realKeyClick(46);
                break;
            case 10:
                realKeyClick(2);
                break;
        }
    }

    private void setCarInfo0x11() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getDriveData(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(0, 1, getDriveData(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(0, 2, getDriveData(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(0, 3, getDriveData(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(0, 4, getDriveData(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(0, 5, getDriveData(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[3] + this.mContext.getString(R.string.unit_km_h)));
        arrayList.add(new DriverUpdateEntity(0, 7, getLightNum(this.mCanBusInfoInt[6])));
        arrayList.add(new DriverUpdateEntity(0, 8, getLightNum(this.mCanBusInfoInt[7])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            forceReverse(this.mContext, true);
        } else {
            forceReverse(this.mContext, false);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 64901:
                if (str.equals("AM1")) {
                    c = 0;
                    break;
                }
                break;
            case 64902:
                if (str.equals("AM2")) {
                    c = 1;
                    break;
                }
                break;
            case 69706:
                if (str.equals("FM1")) {
                    c = 2;
                    break;
                }
                break;
            case 69707:
                if (str.equals("FM2")) {
                    c = 3;
                    break;
                }
                break;
            case 69708:
                if (str.equals("FM3")) {
                    c = 4;
                    break;
                }
                break;
        }
        String str4 = "01";
        switch (c) {
            case 0:
                i3 = 4;
                break;
            case 1:
                i3 = 5;
                str4 = "03";
                break;
            case 2:
            default:
                i3 = 1;
                break;
            case 3:
                str4 = "10";
                i3 = 2;
                break;
            case 4:
                str4 = "10";
                i3 = 3;
                break;
        }
        String str5 = (str.contains("FM") && str.length() == 4) ? "  " : " ";
        if (str.contains("FM") && str.length() == 4) {
            str2 = " " + str2;
        } else if (str.contains("AM")) {
            if (str.length() == 3) {
                str2 = str2 + "  ";
            } else {
                str2 = str2 + " ";
            }
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, (byte) i3}, (str4 + str5 + str2 + getBandUnit(str) + " ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (bArr.length < 12) {
            byte[] bArr2 = new byte[12 - bArr.length];
            Arrays.fill(bArr2, (byte) 32);
            bArr = DataHandleUtils.byteMerger(bArr2, bArr);
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, ((((b7 & 255) * 256) + i) + "/" + i2 + " " + str2 + ":" + str3 + "     ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, ((((b6 & 255) * 256) + i) + "/" + i2 + " " + str3 + ":" + str4 + "     ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 7, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, "BT MUSIC    ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    private String getBandUnit(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return "KHz";
            case "FM1":
            case "FM2":
            case "FM3":
                return "MHz";
            default:
                return "";
        }
    }

    private String getDriveData(boolean z) {
        if (z) {
            return this.mContext.getString(R.string.open);
        }
        return this.mContext.getString(R.string.close);
    }

    private String getLightNum(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string.english_off);
        }
        if (i == 100) {
            return this.mContext.getString(R.string.english_on);
        }
        return String.valueOf(i);
    }

    protected void realKeyClick(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private void setFrontBlowSwitch(int i) {
        setFrontBlow(false, false, false, false);
        if (i == 1) {
            setFrontBlow(false, false, false, true);
        }
        if (i == 3) {
            setFrontBlow(false, false, true, false);
            return;
        }
        if (i == 5) {
            setFrontBlow(false, true, true, false);
            return;
        }
        if (i == 6) {
            setFrontBlow(false, true, false, false);
            return;
        }
        switch (i) {
            case 11:
                setFrontBlow(true, false, false, false);
                break;
            case 12:
                setFrontBlow(true, false, true, false);
                break;
            case 13:
                setFrontBlow(true, true, false, false);
                break;
            case 14:
                setFrontBlow(true, true, true, false);
                break;
        }
    }

    private void setFrontBlow(boolean z, boolean z2, boolean z3, boolean z4) {
        GeneralAirData.front_left_blow_window = z;
        GeneralAirData.front_right_blow_window = z;
        GeneralAirData.front_left_blow_head = z2;
        GeneralAirData.front_right_blow_head = z2;
        GeneralAirData.front_left_blow_foot = z3;
        GeneralAirData.front_right_blow_foot = z3;
        GeneralAirData.rear_left_auto_wind = z4;
        GeneralAirData.rear_right_auto_wind = z4;
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarDataNow, iArr)) {
            return false;
        }
        this.mFrontRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
