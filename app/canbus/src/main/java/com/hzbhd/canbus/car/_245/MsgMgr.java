package com.hzbhd.canbus.car._245;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private double avgFuelConsumption;
    private int avgSpeed;
    public int currentCanDifferentId;
    private double currentFuelConsumption;
    private String distance;
    public int[] leftIndex;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int range;
    public int[] rightIndex;
    private double travelDistance;
    private String travelTime;
    public final Map<String, Integer> settingPageIndex = new HashMap();
    public final Map<Integer, List<Integer>> differentIdMap = new HashMap();
    private final DecimalFormat df0 = new DecimalFormat("0.0");
    private final DecimalFormat df00 = new DecimalFormat("0.00");
    private int kmOrMi = 0;
    private UiMgr uiMgr = null;

    private int getLsb(int i) {
        return i & 65535 & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    public int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.currentCanDifferentId = getCurrentCanDifferentId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i != 20) {
            if (i == 38) {
                instructTimeInfo();
                return;
            }
            if (i == 48) {
                version(context);
                return;
            }
            if (i == 64) {
                settingItem(context);
                return;
            }
            if (i == 80) {
                tripInfo(context);
                return;
            }
            if (i == 40) {
                outdoorTemp(context);
                return;
            }
            if (i != 41) {
                switch (i) {
                    case 33:
                        if (!isAirMsgRepeat(bArr)) {
                            setAirData0x21();
                            break;
                        }
                        break;
                    case 34:
                        rearRadar(context);
                        break;
                    case 35:
                        frontRadar(context);
                        break;
                    case 36:
                        generalInfo(context);
                        break;
                }
                return;
            }
            cornerInfo(context);
            return;
        }
        backLightInfo();
        keyClick();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        int i4;
        int lsb;
        int msb;
        int i5;
        int i6;
        super.radioInfoChange(i, str, str2, str3, i2);
        i3 = (int) (Double.parseDouble(str2) * 100.0d);
        i4 = (int) Double.parseDouble(str2);
        str.hashCode();
        switch (str) {
            case "AM":
                lsb = getLsb(i4);
                msb = getMsb(i4);
                i5 = 0;
                i6 = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "FM":
                lsb = getLsb(i3);
                msb = getMsb(i3);
                i5 = 0;
                i6 = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "AM1":
                lsb = getLsb(i4);
                msb = getMsb(i4);
                i6 = 2;
                i5 = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "AM2":
                lsb = getLsb(i4);
                msb = getMsb(i4);
                i6 = 2;
                i5 = i6;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "AM3":
                lsb = getLsb(i4);
                msb = getMsb(i4);
                i5 = 3;
                i6 = 2;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "FM1":
                lsb = getLsb(i3);
                msb = getMsb(i3);
                i6 = 1;
                i5 = i6;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "FM2":
                lsb = getLsb(i3);
                msb = getMsb(i3);
                i5 = 2;
                i6 = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            case "FM3":
                lsb = getLsb(i3);
                msb = getMsb(i3);
                i5 = 3;
                i6 = 1;
                CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i6, (byte) msb, (byte) lsb, (byte) i, (byte) i5, 0});
                return;
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, (byte) getLsb(i5), (byte) getMsb(i5), (byte) getLsb(i4), (byte) getMsb(i4), 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 6, (byte) getMsb(i2), (byte) getLsb(i2), (byte) getMsb(i3), (byte) getLsb(i3), z ? (byte) 1 : (byte) 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 4, (byte) getMsb(i2), (byte) getLsb(i2), (byte) getMsb(i3), (byte) getLsb(i3), z ? (byte) 1 : (byte) 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 6, (byte) getMsb(i2), (byte) getLsb(i2), b6, (byte) i, z ? (byte) 1 : (byte) 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 4, (byte) getMsb(i2), (byte) getLsb(i2), b6, (byte) i, z ? (byte) 1 : (byte) 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 0, 0, 0, 0, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        sendPhoneNumber(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 0, 1)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) getDecimalFrom8Bit(0, 0, 0, 1, 0, 0, 1, 0)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) getDecimalFrom8Bit(0, 0, 0, 1, 0, 1, 0, 0)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) getDecimalFrom8Bit(0, 0, 0, 1, 0, 1, 0, 1)});
    }

    private void sendPhoneNumber(byte[] bArr) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (byteArrayToIntArray.length == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 16, 0, (byte) byteArrayToIntArray[0], 0, (byte) byteArrayToIntArray[1], 0, (byte) byteArrayToIntArray[2], 0, (byte) byteArrayToIntArray[3], 0, (byte) byteArrayToIntArray[4], 0, (byte) byteArrayToIntArray[5], 0, (byte) byteArrayToIntArray[6], 0, (byte) byteArrayToIntArray[7], 0, (byte) byteArrayToIntArray[8], 0, (byte) byteArrayToIntArray[9], 0, (byte) byteArrayToIntArray[10]});
        }
        if (byteArrayToIntArray.length == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 16, 0, (byte) byteArrayToIntArray[0], 0, (byte) byteArrayToIntArray[1], 0, (byte) byteArrayToIntArray[2], 0, (byte) byteArrayToIntArray[3], 0, (byte) byteArrayToIntArray[4]});
        }
        if (byteArrayToIntArray.length == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 16, 0, (byte) byteArrayToIntArray[0], 0, (byte) byteArrayToIntArray[1], 0, (byte) byteArrayToIntArray[2], 0, (byte) byteArrayToIntArray[3], 0, (byte) byteArrayToIntArray[4], 0, (byte) byteArrayToIntArray[5], 0, (byte) byteArrayToIntArray[6]});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -89, (byte) getMsb(Integer.parseInt(String.valueOf(i), 16)), (byte) getLsb(Integer.parseInt(String.valueOf(i), 16)), (byte) Integer.parseInt(String.valueOf(i3), 16), (byte) Integer.parseInt(String.valueOf(i4), 16), (byte) Integer.parseInt(String.valueOf(i8), 16), (byte) Integer.parseInt(String.valueOf(i6), 16), (byte) Integer.parseInt(String.valueOf(i7), 16)});
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    private void backLightInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, String.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        setBacklightLevel((this.mCanBusInfoInt[2] / 51) + 1);
    }

    private void keyClick() {
        int i = this.mCanBusInfoInt[2];
        switch (i) {
            case 0:
                reaKeyClick1(0);
                break;
            case 1:
                reaKeyClick1(7);
                break;
            case 2:
                reaKeyClick1(8);
                break;
            case 3:
                reaKeyClick1(45);
                break;
            case 4:
                reaKeyClick1(46);
                break;
            case 5:
                reaKeyClick1(14);
                break;
            case 6:
                reaKeyClick1(3);
                break;
            case 7:
                reaKeyClick1(2);
                break;
            case 8:
                reaKeyClick1(49);
                break;
            case 9:
                reaKeyClick1(14);
                break;
            case 10:
                reaKeyClick1(15);
                break;
            default:
                switch (i) {
                    case 16:
                        reaKeyClick1(HotKeyConstant.K_SPEECH);
                        break;
                    case 17:
                        reaKeyClick1(129);
                        break;
                    case 18:
                        reaKeyClick1(151);
                        break;
                }
        }
    }

    private void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.big_wind_light = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.small_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        if (intFromByteWithBit == 9) {
            GeneralAirData.front_wind_level = 0;
        } else {
            GeneralAirData.front_wind_level = intFromByteWithBit;
        }
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void rearRadar(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void frontRadar(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[5], iArr[4]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void generalInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        updateDoorView(context);
    }

    private void instructTimeInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_245_driveInfo_0");
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_245_driveInfo_0_1");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[2], iArr[3])).append("/").append(Integer.toHexString(this.mCanBusInfoByte[4] & 255)).append("/").append(Integer.toHexString(this.mCanBusInfoByte[5] & 255)).append("  ").append(Integer.toHexString(this.mCanBusInfoByte[6] & 255)).append(":").append(Integer.toHexString(this.mCanBusInfoByte[7] & 255)).append(":").append(Integer.toHexString(this.mCanBusInfoByte[8] & 255)).toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        setBacklightLevel((this.mCanBusInfoInt[2] / 51) + 1);
    }

    private void outdoorTemp(Context context) {
        if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            updateOutDoorTemp(context, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°C");
        } else {
            updateOutDoorTemp(context, "-" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°C");
        }
    }

    private void cornerInfo(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 540, 16);
        updateParkUi(null, context);
    }

    private String resolveLeftAndRightTemp(int i) {
        return i == 0 ? "LO" : 31 == i ? "HI" : (1 > i || 17 < i) ? "" : ((i * 0.5d) + 17.5d) + getTempUnitC(this.mContext);
    }

    private void version(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    private void settingItem(Context context) {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_0"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_0", "_245_setting_0_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i == 1) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i == 2) {
            int i2 = iArr[3];
            if (i2 == 0) {
                arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_1"), 0));
                this.distance = "KM";
            } else if (i2 == 1) {
                arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_1"), 1));
                this.distance = "MI";
            }
        } else if (i == 3) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i == 4) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_1", "_245_setting_1_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i == 32) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_3"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_3", "_245_setting_3_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i == 33) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_3"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_3", "_245_setting_3_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i != 57) {
            switch (i) {
                case 16:
                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 17:
                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 18:
                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 19:
                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 20:
                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_4"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 21:
                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_2", "_245_setting_2_5"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                default:
                    switch (i) {
                        case 48:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 49:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 50:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 51:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 52:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_4"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 53:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_5"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 54:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_6"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        case 55:
                            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_4"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_4", "_245_setting_4_7"), Integer.valueOf(this.mCanBusInfoInt[3])));
                            break;
                        default:
                            switch (i) {
                                case NfDef.STATE_3WAY_M_HOLD /* 240 */:
                                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                                    break;
                                case 241:
                                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                                    break;
                                case 242:
                                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                                    break;
                                case 243:
                                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                                    break;
                                case NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD /* 244 */:
                                    arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_4"), Integer.valueOf(this.mCanBusInfoInt[3])));
                                    break;
                            }
                    }
            }
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_245_setting_5"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_245_setting_5", "_245_setting_5_5"), Integer.valueOf(this.mCanBusInfoInt[3])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void tripInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            this.range = (iArr[3] * 256) + iArr[4];
            this.currentFuelConsumption = ((iArr[5] * 256) + iArr[6]) * 0.1d;
            arrayList.add(new DriverUpdateEntity(1, 0, CommUtil.getStrByResId(context, "_245_driveInfo_1_00")));
            arrayList.add(new DriverUpdateEntity(1, 1, this.range + "km"));
            arrayList.add(new DriverUpdateEntity(1, 2, this.df0.format(this.currentFuelConsumption)));
        } else if (i == 1) {
            this.avgFuelConsumption = ((iArr[3] * 256) + iArr[4]) * 0.1d;
            this.avgSpeed = iArr[5];
            this.travelDistance = ((iArr[6] * 256 * 256) + (iArr[7] * 256) + iArr[8]) * 0.1d;
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            this.travelTime = sb.append((iArr2[11] * 256) + iArr2[9]).append(":").append(this.mCanBusInfoInt[10]).toString();
            arrayList.add(new DriverUpdateEntity(2, 0, CommUtil.getStrByResId(context, "_245_driveInfo_1_01")));
            arrayList.add(new DriverUpdateEntity(2, 1, this.df0.format(this.avgFuelConsumption) + "km/l"));
            arrayList.add(new DriverUpdateEntity(2, 2, this.df0.format(this.avgSpeed) + "km/h"));
            arrayList.add(new DriverUpdateEntity(2, 3, this.df0.format(this.travelDistance) + "km"));
            arrayList.add(new DriverUpdateEntity(2, 4, this.travelTime));
        } else if (i == 2) {
            this.avgFuelConsumption = ((iArr[3] * 256) + iArr[4]) * 0.1d;
            this.avgSpeed = iArr[5];
            this.travelDistance = ((iArr[6] * 256 * 256) + (iArr[7] * 256) + iArr[8]) * 0.1d;
            StringBuilder sb2 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            this.travelTime = sb2.append((iArr3[11] * 256) + iArr3[9]).append(":").append(this.mCanBusInfoInt[10]).toString();
            arrayList.add(new DriverUpdateEntity(3, 0, CommUtil.getStrByResId(context, "_245_driveInfo_1_02")));
            arrayList.add(new DriverUpdateEntity(3, 1, this.df0.format(this.avgFuelConsumption) + "km/l"));
            arrayList.add(new DriverUpdateEntity(3, 2, this.df0.format(this.avgSpeed) + "km/h"));
            arrayList.add(new DriverUpdateEntity(3, 3, this.df0.format(this.travelDistance) + "km"));
            arrayList.add(new DriverUpdateEntity(3, 4, this.travelTime));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void reaKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private UiMgr getUigMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    private void initIdMap() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(7);
        this.differentIdMap.put(64, arrayList);
        this.differentIdMap.put(128, arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(5);
        this.differentIdMap.put(148, arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(5);
        arrayList3.add(6);
        arrayList3.add(7);
        arrayList3.add(8);
        arrayList3.add(9);
        this.differentIdMap.put(192, arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(7);
        this.differentIdMap.put(Integer.valueOf(HotKeyConstant.K_TTM), arrayList4);
    }
}
