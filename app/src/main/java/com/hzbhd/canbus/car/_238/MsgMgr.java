package com.hzbhd.canbus.car._238;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;


public class MsgMgr extends AbstractMsgMgr {
    public static String UPDATE_SETTING_ACTION = "update_setting_action";
    private BroadcastReceiver mBroadcastReceiver;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._238.MsgMgr.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MsgMgr.this.setSettingData();
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, new IntentFilter(UPDATE_SETTING_ACTION));
    }

    private boolean getIsUseFunit() {
        return Integer.parseInt(SharePreUtil.getStringValue(this.mContext, Constant.SHARE_PRE_IS_USE_F_UNIT, "0")) == 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            set0x20WheelKeyInfo();
            return;
        }
        if (i == 36) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            setAirData0x24();
        } else if (i == 40) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setDoorData0x28();
        } else if (i == 54) {
            setAirData0x36();
        } else if (i == 96) {
            setAirData0x60();
        } else {
            if (i != 127) {
                return;
            }
            setVersionInfo();
        }
    }

    private void set0x20WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 20, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 21, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_DARK_MODE, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 15, iArr[3]);
                break;
        }
    }

    private void setAirData0x60() {
        GeneralTireData.isNoTirePressureInfo = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getTireEntity(0, getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4)), getTirePressure(this.mCanBusInfoInt[5]), getTemperature(this.mCanBusInfoInt[9])));
            arrayList.add(getTireEntity(1, getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)), getTirePressure(this.mCanBusInfoInt[6]), getTemperature(this.mCanBusInfoInt[10])));
            arrayList.add(getTireEntity(2, getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)), getTirePressure(this.mCanBusInfoInt[7]), getTemperature(this.mCanBusInfoInt[11])));
            arrayList.add(getTireEntity(3, getTisWarmMsg(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)), getTirePressure(this.mCanBusInfoInt[8]), getTemperature(this.mCanBusInfoInt[12])));
            GeneralTireData.dataList = arrayList;
        }
        updateTirePressureActivity(null);
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, String str3) {
        String[] strArr;
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            strArr = new String[]{"", str2, str3};
        } else {
            strArr = new String[]{str, str2, str3};
            i2 = 1;
        }
        return new TireUpdateEntity(i, i2, strArr);
    }

    private String getTirePressure(int i) {
        String strValueOf;
        if (i == 255) {
            strValueOf = this.mContext.getString(R.string.set_default);
        } else {
            strValueOf = String.valueOf(Math.floor(i * 1.37d));
        }
        return strValueOf + this.mContext.getString(R.string.pressure_unit);
    }

    private String getTisWarmMsg(int i) {
        int i2 = i != 2 ? i != 4 ? i != 6 ? i != 8 ? i != 10 ? i != 12 ? 0 : R.string.useless_sensor : R.string.sensor_low_battery : R.string.sensor_missing : R.string.air_leak_warm : R.string.low_pressure_warm : R.string.high_pressure_warm;
        return i2 == 0 ? "" : this.mContext.getString(i2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        int i2 = i >= 30 ? 40 : i;
        if (i < 0) {
            i2 = 0;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDorrTem());
    }

    private void setAirData0x24() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
        GeneralAirData.front_wind_level = intFromByteWithBit;
        if (intFromByteWithBit == 0) {
            GeneralAirData.power = false;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (intFromByteWithBit2 == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit2 == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 7));
        } else {
            GeneralAirData.front_left_temperature = resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7));
        } else {
            GeneralAirData.front_right_temperature = resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
        }
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            updateAirActivity(this.mContext, 1001);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSettingData() {
        boolean isUseFunit = getIsUseFunit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(isUseFunit ? 1 : 0)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        int i4 = (!isBandFm(str) && isBandAm(str)) ? 1 : 0;
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
        switch (c) {
            case 0:
                i3 = 3;
                break;
            case 1:
                i3 = 4;
                break;
            case 2:
            default:
                i3 = 0;
                break;
            case 3:
                i3 = 1;
                break;
            case 4:
                i3 = 2;
                break;
        }
        String[] validFreqList = FutureUtil.instance.getValidFreqList(i3);
        int i5 = FutureUtil.instance.getAutoSearchStatus() ? 4 : 0;
        if (FutureUtil.instance.getPSSwitchStatus()) {
            i5 = 3;
        }
        if (FutureUtil.instance.getSeekDownStatus()) {
            i5 = 2;
        }
        if (FutureUtil.instance.getSeekUpStatus()) {
            i5 = 1;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i4, getFreqByteHi2(str, validFreqList[0]), getFreqByteLo(str, validFreqList[0]), getFreqByteHi2(str, validFreqList[1]), getFreqByteLo(str, validFreqList[1]), getFreqByteHi2(str, validFreqList[2]), getFreqByteLo(str, validFreqList[2]), getFreqByteHi2(str, validFreqList[3]), getFreqByteLo(str, validFreqList[3]), 0, 0, getFreqByteHi(str, str2), getFreqByteLo(str, str2), (byte) i5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, (byte) (b == 9 ? 3 : 2), b7, (byte) i}, 16));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, (byte) (b == 9 ? 3 : 2), b6, (byte) i}, 16));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, 4}, 16));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, 9}, 16));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -58, 7}, 16));
    }

    private byte getFreqByteHi2(String str, String str2) throws NumberFormatException {
        int i;
        if (isBandAm(str)) {
            i = Integer.parseInt(str2);
        } else {
            if (!isBandFm(str)) {
                return (byte) 0;
            }
            i = (int) Double.parseDouble(str2);
        }
        return (byte) (i >> 8);
    }

    private byte getFreqByteHi(String str, String str2) throws NumberFormatException {
        int i;
        if (isBandAm(str)) {
            i = Integer.parseInt(str2);
        } else {
            if (!isBandFm(str)) {
                return (byte) 0;
            }
            i = (int) (Double.parseDouble(str2) * 100.0d);
        }
        return (byte) (i >> 8);
    }

    private byte getFreqByteLo(String str, String str2) throws NumberFormatException {
        int i;
        if (isBandAm(str)) {
            i = Integer.parseInt(str2);
        } else {
            if (!isBandFm(str)) {
                return (byte) 0;
            }
            i = (int) (Double.parseDouble(str2) * 100.0d);
        }
        return (byte) (i & 255);
    }

    private double tempCToTempF(double d) {
        try {
            return Double.valueOf(new DecimalFormat("0.0").format((d * 1.8d) + 32.0d)).doubleValue();
        } catch (Exception e) {
            LogUtil.showLog("Exception:" + e);
            return 0.0d;
        }
    }

    private String getTemperature(int i) {
        if (i >= 0 && i <= 194) {
            int i2 = i - 40;
            return getIsUseFunit() ? tempCToTempF(i2) + "" : i2 + this.mContext.getString(R.string.str_temp_c_unit);
        }
        if (i == 195 || i == 255) {
            return this.mContext.getString(R.string.set_default);
        }
        return this.mContext.getString(R.string.no_display);
    }

    private String resolveOutDorrTem() {
        String str;
        double intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (intFromByteWithBit >= 127.0d) {
            intFromByteWithBit = 127.0d;
        }
        if (getIsUseFunit()) {
            intFromByteWithBit = tempCToTempF(intFromByteWithBit);
        }
        if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = intFromByteWithBit + "";
        } else {
            str = "-" + intFromByteWithBit;
        }
        if (getIsUseFunit()) {
            return str + getTempUnitF(this.mContext);
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveTempLevel(int i) {
        return 1 == i ? "LO" : 15 == i ? "HI" : (2 > i || i > 14) ? "" : i + "";
    }

    private String resolveLeftAndRightTemp(int i) {
        if (36 == i) {
            return "LO";
        }
        if (64 == i) {
            return "HI";
        }
        if (255 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        if (37 > i || i > 63) {
            return "";
        }
        if (getIsUseFunit()) {
            return tempCToTempF(((i - 1) * 0.5d) + 0.5d) + getTempUnitF(this.mContext);
        }
        return (((i - 1) * 0.5d) + 0.5d) + getTempUnitC(this.mContext);
    }
}
