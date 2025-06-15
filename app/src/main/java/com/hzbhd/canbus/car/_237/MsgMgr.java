package com.hzbhd.canbus.car._237;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
    static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
    public static String UPDATE_SETTING_ACTION = "update_setting_action";
    private BroadcastReceiver mBroadcastReceiver;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private UiMgr uiMgr;
    int presetFreq1 = 0;
    int presetFreq2 = 0;
    int presetFreq3 = 0;
    int presetFreq4 = 0;
    int presetFreq5 = 0;
    int presetFreq6 = 0;
    int nowBandType = 0;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private boolean getIsUseFunit() throws NumberFormatException {
        int i = Integer.parseInt(SharePreUtil.getStringValue(this.mContext, Constant.SHARE_PRE_IS_USE_F_UNIT, "0"));
        MyLog.temporaryTracking("单位状态" + i);
        return i == 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._237.MsgMgr.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MsgMgr.this.setSettingData();
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, new IntentFilter(UPDATE_SETTING_ACTION));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 40) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setDoorData0x28();
            return;
        }
        if (i == 41) {
            set0x29TrackInfo();
            return;
        }
        if (i == 54) {
            setAirData0x36();
            return;
        }
        if (i == 96) {
            setAirData0x60();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    set0x21WheelKeyInfo();
                    break;
                case 34:
                    setRearRadar0x22();
                    break;
                case 35:
                    setFrontRadar0x23();
                    break;
                case 36:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x24();
                        break;
                    }
                    break;
            }
            return;
        }
        setVersionInfo();
    }

    private void set0x29TrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 5570, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x21WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 21, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 20, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 59, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 1, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 151, iArr[3]);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 50, iArr[3]);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 128, iArr[3]);
                break;
            case 14:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_RADIO, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 33, iArr[3]);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 34, iArr[3]);
                break;
            case 19:
                realKeyLongClick1(this.mContext, 35, iArr[3]);
                break;
            case 20:
                realKeyLongClick1(this.mContext, 36, iArr[3]);
                break;
            case 21:
                realKeyLongClick1(this.mContext, 37, iArr[3]);
                break;
            case 22:
                realKeyLongClick1(this.mContext, 38, iArr[3]);
                break;
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
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 10:
                if (getCurrentCanDifferentId() == 0) {
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, this.mCanBusInfoInt[3]);
                    break;
                } else {
                    realKeyLongClick1(this.mContext, 15, this.mCanBusInfoInt[3]);
                    break;
                }
        }
    }

    private void setFrontRadar0x23() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        if (SharePreUtil.getBoolValue(this.mContext, SHARE_KEY_253_FRONT_RADAR_ENABLE, true)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        } else {
            RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
        }
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRearRadar0x22() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        if (SharePreUtil.getBoolValue(this.mContext, SHARE_KEY_253_REAR_RADAR_ENABLE, true)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        } else {
            RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
        }
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
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
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
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
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
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
        updateAirActivity(this.mContext, 1001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSettingData() {
        int i = !getIsUseFunit() ? 1 : 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i)));
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int i3;
        double d;
        super.radioInfoChange(i, str, str2, str3, i2);
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                i3 = 1;
                break;
            case "FM1":
            case "FM2":
            case "FM3":
            default:
                i3 = 0;
                break;
        }
        if (this.nowBandType != i3) {
            this.nowBandType = i3;
            this.presetFreq1 = 0;
            this.presetFreq2 = 0;
            this.presetFreq3 = 0;
            this.presetFreq4 = 0;
            this.presetFreq5 = 0;
            this.presetFreq6 = 0;
        }
        if (i3 == 0) {
            d = Double.parseDouble(str2) * 100.0d;
        } else {
            d = Double.parseDouble(str2);
        }
        int i4 = (int) d;
        if (i == 1) {
            this.presetFreq1 = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(i4), (byte) getLsb(this.presetFreq1), (byte) getMsb(this.presetFreq2), (byte) getLsb(this.presetFreq2), (byte) getMsb(this.presetFreq3), (byte) getLsb(this.presetFreq3), (byte) getMsb(this.presetFreq4), (byte) getLsb(this.presetFreq4), 0, (byte) i, (byte) getMsb(i4), (byte) getLsb(i4), 0});
            return;
        }
        if (i == 2) {
            this.presetFreq2 = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(i4), (byte) getLsb(this.presetFreq2), (byte) getMsb(this.presetFreq3), (byte) getLsb(this.presetFreq3), (byte) getMsb(this.presetFreq4), (byte) getLsb(this.presetFreq4), (byte) getMsb(this.presetFreq5), (byte) getLsb(this.presetFreq5), 0, (byte) i, (byte) getMsb(i4), (byte) getLsb(i4), 0});
            return;
        }
        if (i == 3) {
            this.presetFreq3 = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(i4), (byte) getLsb(this.presetFreq3), (byte) getMsb(this.presetFreq4), (byte) getLsb(this.presetFreq4), (byte) getMsb(this.presetFreq5), (byte) getLsb(this.presetFreq5), (byte) getMsb(this.presetFreq6), (byte) getLsb(this.presetFreq6), 0, (byte) i, (byte) getMsb(i4), (byte) getLsb(i4), 0});
            return;
        }
        if (i == 4) {
            this.presetFreq4 = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(i4), (byte) getLsb(this.presetFreq4), (byte) getMsb(this.presetFreq5), (byte) getLsb(this.presetFreq5), (byte) getMsb(this.presetFreq6), (byte) getLsb(this.presetFreq6), 0, 0, 0, (byte) i, (byte) getMsb(i4), (byte) getLsb(i4), 0});
        } else if (i == 5) {
            this.presetFreq5 = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(i4), (byte) getLsb(this.presetFreq5), (byte) getMsb(this.presetFreq6), (byte) getLsb(this.presetFreq6), 0, 0, 0, 0, 0, (byte) i, (byte) getMsb(i4), (byte) getLsb(i4), 0});
        } else if (i == 6) {
            this.presetFreq6 = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(i4), (byte) getLsb(this.presetFreq6), 0, 0, 0, 0, 0, 0, 0, (byte) i, (byte) getMsb(i4), (byte) getLsb(i4), 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i3, (byte) getMsb(this.presetFreq2), (byte) getLsb(this.presetFreq2), (byte) getMsb(this.presetFreq3), (byte) getLsb(this.presetFreq3), (byte) getMsb(this.presetFreq4), (byte) getLsb(this.presetFreq4), (byte) getMsb(this.presetFreq5), (byte) getLsb(this.presetFreq5), 0, 1, (byte) getMsb(i4), (byte) getLsb(i4), 0});
        }
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

    private String getTemperature(int i) {
        if (i >= 0 && i <= 194) {
            int i2 = i - 40;
            return getIsUseFunit() ? this.df_2Decimal.format((i2 * 1.8d) + 32.0d) + getTempUnitF(this.mContext) : i2 + getTempUnitC(this.mContext);
        }
        if (i == 195 || i == 255) {
            return this.mContext.getString(R.string.set_default);
        }
        return this.mContext.getString(R.string.no_display);
    }

    private double tempCToTempF(double d) {
        LogUtil.showLog("tempCToTempF:" + d);
        try {
            return Double.valueOf(new DecimalFormat("0.0").format((d * 1.8d) + 32.0d)).doubleValue();
        } catch (Exception e) {
            LogUtil.showLog("Exception:" + e);
            return 0.0d;
        }
    }

    private String resolveOutDoorTem() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (getIsUseFunit()) {
            return this.df_2Decimal.format((intFromByteWithBit * 1.8d) + 32.0d) + getTempUnitF(this.mContext);
        }
        return intFromByteWithBit + getTempUnitC(this.mContext);
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

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    public boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == 404 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == 404;
    }

    public void showDialogAndRestartApp(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._237.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new SetAlertView().showDialog(MsgMgr.this.getActivity(), str);
                new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._237.MsgMgr.2.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        System.exit(0);
                    }
                }.start();
            }
        });
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
