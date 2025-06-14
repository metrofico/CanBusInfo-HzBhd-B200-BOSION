package com.hzbhd.canbus.car._123;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_123_AMPLIFIER_BALANCE = "share_123_amplifier_balance";
    static final String SHARE_123_AMPLIFIER_BASS = "share_123_amplifier_bass";
    static final String SHARE_123_AMPLIFIER_FADE = "share_123_amplifier_fade";
    static final String SHARE_123_AMPLIFIER_MIDDLE = "share_123_amplifier_middle";
    static final String SHARE_123_AMPLIFIER_TREBLE = "share_123_amplifier_treble";
    static final String SHARE_123_AMPLIFIER_VOLUME = "share_123_amplifier_volume";
    static final String SHARE_123_IS_RADAR_ENABLE = "share_123_is_radar_enable";
    static final String SHARE_123_OUTDOOR_TEMPERATURE_UNIT = "share_123_outdoor_temperature_unit";
    static final int _123_AMPLIFIER_OFFSET = 9;
    private static boolean isAirFirst = true;
    private static int tuneKnobValue;
    private static int volKnobValue;
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private int[] m0x2EData;
    private int[] m0x32Data;
    private int[] m0x41Data;
    private int[] m0x43Data;
    private int[] m0x60Data;
    private int[] m0x62Data;
    private int[] m0x9CData;
    private int[] m0xA5Data;
    private int[] m0xA6Data;
    private int[] m0xAEData;
    private int[] m0xC1Data;
    private int[] m0xF0Data;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private boolean mIsBackOpen;
    private boolean mIsBackOpenNow;
    private boolean mIsBeltTie;
    private boolean mIsBeltTieNow;
    private boolean mIsFrontLeftOpen;
    private boolean mIsFrontLeftOpenNow;
    private boolean mIsFrontRightOpen;
    private boolean mIsFrontRightOpenNow;
    private boolean mIsRearLeftOpen;
    private boolean mIsRearLeftOpenNow;
    private boolean mIsRearRightOpen;
    private boolean mIsRearRightOpenNow;
    private boolean mIsSubBeltTie;
    private boolean mIsSubBeltTieNow;
    private ParkPageUiSet mParkPageUiSet;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private List<SongListEntity> mSongList;
    private byte[] mTrackDataNow;
    private UiMgr mUiMgr;
    boolean rdm;
    private long realtime;
    boolean rpt;
    private String songTitleNow = "";
    private String songAlbumNow = "";
    private String songArtistNow = "";
    private int mPlayingIndex = -1;
    private int mCompassDeviation = 1;
    private boolean mCompassDeviationEnable = true;

    private String getServiceStatus(boolean z) {
        return z ? "hiworld_jeep_123_item_38" : "hiworld_jeep_123_item_39";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
        initSettingsItem(getUigMgr(context).getSettingUiSet(context));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mContext = context;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.hzbhd.canbus.car._123.MsgMgr$1] */
    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_123_AMPLIFIER_VOLUME, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_123_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_123_AMPLIFIER_FADE, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_123_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_123_AMPLIFIER_MIDDLE, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_123_AMPLIFIER_TREBLE, 0);
        }
        new Thread() { // from class: com.hzbhd.canbus.car._123.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) GeneralAmplifierData.leftRight});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) GeneralAmplifierData.frontRear});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass - 9)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandTreble - 9)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandMiddle - 9)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        switch (byteArrayToIntArray[1]) {
            case 17:
                realKeyControl0x11();
                break;
            case 18:
                setVehicleDoorInfo0x12();
                break;
            case 33:
                panelKey0x21();
                break;
            case 34:
                panelKnob0x22();
                break;
            case 46:
                setVehicleType0x2E();
                break;
            case 49:
                set0x31RearMirror();
                setAirData0x31();
                break;
            case 50:
                setDriveData0x32();
                break;
            case 52:
                setSRTinfo0x34();
                break;
            case 65:
                setRadar0x41();
                break;
            case 67:
                setCarSetting0x43();
                break;
            case 69:
                setCarSetting0x45();
                break;
            case 96:
                setCarSetting0x60();
                break;
            case 98:
                setCarSetting0x62();
                break;
            case 156:
                setCompass0x9C();
                break;
            case 165:
                setOriginalDeviceInfo0xA5();
                break;
            case MpegConstantsDef.MPEG_PASSWORD_CFM /* 166 */:
                setAmplifierData0xA6();
                break;
            case 174:
                setOriginalDeviceData0xAE();
                break;
            case 193:
                setCarSetting0xC1();
                break;
            case NfDef.STATE_3WAY_M_HOLD /* 240 */:
                setVersionInfo();
                break;
        }
    }

    private void setCarSetting0x45() {
        ArrayList arrayList = new ArrayList();
        Boolean boolValueOf = Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        Boolean boolValueOf2 = Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
        Boolean boolValueOf3 = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        Boolean boolValueOf4 = Boolean.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        Boolean boolValueOf5 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        Boolean boolValueOf6 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        Boolean boolValueOf7 = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1);
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_46", Integer.valueOf(intFromByteWithBit), boolValueOf.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_47", Integer.valueOf(intFromByteWithBit2), boolValueOf2.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_48", Integer.valueOf(intFromByteWithBit3), boolValueOf3.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_49", Integer.valueOf(intFromByteWithBit4), boolValueOf4.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_50", Integer.valueOf(intFromByteWithBit5), boolValueOf5.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_51", Integer.valueOf(intFromByteWithBit6), boolValueOf6.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("hiworld_jeep_123_item_52", Integer.valueOf(intFromByteWithBit7), boolValueOf7.booleanValue())));
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    private void set0x31RearMirror() {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x62_title"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x62_title", "_123_rear_mirror"), "ON").setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x62_title"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x62_title", "_123_rear_mirror"), "OFF").setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSRTinfo0x34() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append((iArr[2] << 8) + iArr[3]).append("NM").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, sb2.append((iArr2[4] << 8) + iArr2[5]).append("KW").toString()));
        arrayList.add(new DriverUpdateEntity(1, 2, (this.mCanBusInfoInt[13] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 3, (this.mCanBusInfoInt[14] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 4, (this.mCanBusInfoInt[15] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 5, (this.mCanBusInfoInt[16] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 6, (this.mCanBusInfoInt[21] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 7, (this.mCanBusInfoInt[22] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 8, (this.mCanBusInfoInt[23] / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(1, 9, (this.mCanBusInfoInt[24] / 10.0f) + ""));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void realKeyShortClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyLongClick1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void sendKnob_1(int i, int i2) {
        realKeyClick3_1(this.mContext, i, 0, i2);
    }

    private void sendKnob(int i, int i2) {
        realKeyClick3(this.mContext, i, 0, i2);
    }

    private void realKeyControl0x11() {
        getmParkPageUiSet();
        ParkPageUiSet parkPageUiSet = this.mParkPageUiSet;
        if (parkPageUiSet != null) {
            parkPageUiSet.setShowRadar(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        }
        int i = this.mCanBusInfoInt[4];
        if (i == 8) {
            realKeyLongClick1(47);
        } else if (i == 9) {
            realKeyLongClick1(48);
        } else if (i == 12) {
            realKeyLongClick1(2);
        } else if (i != 24) {
            switch (i) {
                case 0:
                    realKeyLongClick1(0);
                    break;
                case 1:
                    realKeyLongClick1(7);
                    break;
                case 2:
                    realKeyLongClick1(8);
                    break;
                case 3:
                    realKeyLongClick1(3);
                    break;
                case 4:
                    realKeyLongClick1(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyLongClick1(14);
                    break;
                case 6:
                    realKeyLongClick1(15);
                    break;
            }
        } else {
            realKeyLongClick1(33);
        }
        byte[] bArr = this.mCanBusInfoByte;
        if (isTrackDataChange(new byte[]{bArr[8], bArr[9]})) {
            byte[] bArr2 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr2[9], bArr2[8], 0, 540, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setVehicleDoorInfo0x12() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        this.mIsFrontLeftOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        this.mIsFrontRightOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        this.mIsRearLeftOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        this.mIsRearRightOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        this.mIsBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        this.mIsBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        this.mIsSubBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void panelKey0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyShortClick(0);
            return;
        }
        if (i == 1) {
            realKeyShortClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 6) {
            realKeyShortClick(50);
            return;
        }
        if (i == 9) {
            realKeyShortClick(3);
        } else if (i == 49) {
            realKeyShortClick(152);
        } else {
            if (i != 50) {
                return;
            }
            realKeyShortClick(HotKeyConstant.K_PHONE_ON_OFF);
        }
    }

    private void panelKnob0x22() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            int i2 = volKnobValue - this.mCanBusInfoByte[3];
            if (i2 < 0) {
                sendKnob_1(7, Math.abs(i2));
            } else if (i2 > 0) {
                sendKnob_1(8, Math.abs(i2));
            }
            volKnobValue = this.mCanBusInfoByte[3];
            return;
        }
        if (i != 2) {
            return;
        }
        int i3 = tuneKnobValue - this.mCanBusInfoByte[3];
        if (i3 < 0) {
            sendKnob(46, Math.abs(i3));
        } else if (i3 > 0) {
            sendKnob(45, Math.abs(i3));
        }
        tuneKnobValue = this.mCanBusInfoByte[3];
    }

    private void setOutDoorTem() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setAirData0x31() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 0);
        setOutDoorTem();
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        if (isAirDataNoChange() || isFirst()) {
            return;
        }
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        if (intFromByteWithBit > 2) {
            intFromByteWithBit = 2;
        }
        if (intFromByteWithBit2 > 2) {
            intFromByteWithBit2 = 2;
        }
        GeneralAirData.front_right_seat_heat_level = intFromByteWithBit;
        GeneralAirData.front_left_seat_heat_level = intFromByteWithBit2;
        cleanAllBlow();
        int i = this.mCanBusInfoInt[6];
        if (i == 1) {
            GeneralAirData.front_auto_wind_model = true;
        } else if (i == 3) {
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
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_auto_wind_speed = this.mCanBusInfoInt[7] == 19;
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        int i2 = this.mCanBusInfoInt[10];
        if (i2 == 0) {
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_left_blow_head = false;
        } else if (i2 == 1) {
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i2 == 2) {
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_left_blow_head = false;
        } else if (i2 == 3) {
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_head = true;
        }
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        GeneralAirData.rear_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[12]);
        updateAirActivity(this.mContext, 1004);
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private boolean is0x41DataChange() {
        if (Arrays.equals(this.m0x41Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x41Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setRadar0x41() {
        if (is0x41DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationDataType2(2, iArr[2], 6, iArr[3], 6, iArr[4], 2, iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationDataType2(2, iArr2[6], 4, iArr2[7], 4, iArr2[8], 2, iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            updateParkUi(null, this.mContext);
        }
    }

    private boolean is0x43DataChange() {
        if (Arrays.equals(this.m0x43Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x43Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setCarSetting0x43() {
        if (is0x43DataChange()) {
            for (int i = 0; i < GeneralSettingData.dataList.size(); i++) {
                if (GeneralSettingData.dataList.get(i).getLeftListIndex() == 5) {
                    if (GeneralSettingData.dataList.get(i).getRightListIndex() == 0) {
                        GeneralSettingData.dataList.get(i).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
                    } else if (GeneralSettingData.dataList.get(i).getRightListIndex() == 1) {
                        GeneralSettingData.dataList.get(i).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
                    } else if (GeneralSettingData.dataList.get(i).getRightListIndex() == 2) {
                        GeneralSettingData.dataList.get(i).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
                    }
                }
            }
            this.mCompassDeviationEnable = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(SharePreUtil.getIntValue(this.mContext, "share_123_compass_direct", 0))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(this.mCompassDeviation)).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(5, 2, 0).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(0, 13, getServiceStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(0, 15, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(0, 16, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(0, 17, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean is0x62DataChange() {
        if (Arrays.equals(this.m0x62Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x62Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setCarSetting0x62() {
        if (is0x62DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) - 1, 0, 2))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 3)).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(1, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(1, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean is0x60DataChange() {
        if (Arrays.equals(this.m0x60Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x60Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setCarSetting0x60() {
        if (is0x60DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean is0xC1DataChange() {
        if (Arrays.equals(this.m0xC1Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xC1Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setCarSetting0xC1() {
        if (is0xC1DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))));
            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[3] - 1, 0, 1))));
            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(this.mCanBusInfoInt[4] - 1)));
            arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(this.mCanBusInfoInt[5] - 1)));
            arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 1)));
            int i = this.mCanBusInfoInt[3];
            if (i == 1) {
                SharePreUtil.setBoolValue(this.mContext, SHARE_123_OUTDOOR_TEMPERATURE_UNIT, false);
            } else if (i == 2) {
                SharePreUtil.setBoolValue(this.mContext, SHARE_123_OUTDOOR_TEMPERATURE_UNIT, true);
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean is0x32DataChange() {
        if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x32Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDriveData0x32() {
        if (is0x32DataChange()) {
            ArrayList arrayList = new ArrayList();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 0, resolveDriveData(iArr[4], iArr[5], " r/min")));
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 1, resolveDriveData(iArr2[6], iArr2[7], " km/h")));
            arrayList.add(new DriverUpdateEntity(0, 2, (this.mCanBusInfoInt[8] / 10.0f) + "V"));
            arrayList.add(new DriverUpdateEntity(0, 3, (this.mCanBusInfoInt[10] - 40) + getTempUnitC(this.mContext)));
            arrayList.add(new DriverUpdateEntity(0, 4, (this.mCanBusInfoInt[11] - 40) + getTempUnitC(this.mContext)));
            StringBuilder sb = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 5, sb.append(((iArr3[12] << 8) + iArr3[13]) / 10.0f).append("").toString()));
            arrayList.add(new DriverUpdateEntity(0, 6, (this.mCanBusInfoInt[14] - 40) + getTempUnitC(this.mContext)));
            arrayList.add(new DriverUpdateEntity(0, 7, (this.mCanBusInfoInt[15] - 40) + getTempUnitC(this.mContext)));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int[] iArr4 = this.mCanBusInfoInt;
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr4[6], iArr4[7]));
        }
    }

    private boolean is0xAEDataChange() {
        if (Arrays.equals(this.m0xAEData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xAEData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setOriginalDeviceData0xAE() {
        if (is0xAEDataChange()) {
            DecimalFormat decimalFormat = new DecimalFormat("00");
            GeneralOriginalCarDeviceData.cdStatus = "CD";
            GeneralOriginalCarDeviceData.runningState = getRunStatus(this.mCanBusInfoInt[5]);
            if (this.mCanBusInfoInt[5] == 4 && GeneralOriginalCarDeviceData.songList != null) {
                GeneralOriginalCarDeviceData.songList.clear();
            }
            GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
            this.rpt = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
            this.rdm = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            int[] iArr = this.mCanBusInfoInt;
            int i = (iArr[13] * 256) + iArr[14];
            GeneralOriginalCarDeviceData.startTime = decimalFormat.format(getMinute(i)) + ":" + decimalFormat.format(getSecond(i));
            int[] iArr2 = this.mCanBusInfoInt;
            int i2 = (iArr2[11] * 256) + iArr2[12];
            GeneralOriginalCarDeviceData.endTime = decimalFormat.format(getMinute(i2)) + ":" + decimalFormat.format(getSecond(i2));
            if (i2 == 0) {
                GeneralOriginalCarDeviceData.progress = 0;
            } else {
                GeneralOriginalCarDeviceData.progress = (i * 100) / i2;
            }
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr3[7] * 256) + iArr3[8]).append("").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append((iArr4[9] * 256) + iArr4[10]).append("").toString()));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private boolean is0xA5DataChange() {
        if (Arrays.equals(this.m0xA5Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xA5Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setOriginalDeviceInfo0xA5() {
        if (is0xA5DataChange()) {
            getSongList();
            try {
                int[] iArr = this.mCanBusInfoInt;
                int i = iArr[2];
                boolean z = true;
                if (i == 1 || i == 2 || i == 3) {
                    songListSetSelected();
                    ArrayList arrayList = new ArrayList();
                    byte[] bArr = this.mCanBusInfoByte;
                    arrayList.add(new OriginalCarDeviceUpdateEntity(this.mCanBusInfoInt[2] + 1, new String(bArr, 5, bArr.length - 5, "UNICODE")));
                    GeneralOriginalCarDeviceData.mList = arrayList;
                } else if (i == 4) {
                    int i2 = (iArr[3] * 256) + iArr[4];
                    byte[] bArr2 = this.mCanBusInfoByte;
                    String str = new String(bArr2, 5, bArr2.length - 5, "UNICODE");
                    try {
                        this.mSongList.remove(i2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    List<SongListEntity> list = this.mSongList;
                    SongListEntity songListEntity = new SongListEntity(str);
                    if (i2 != this.mPlayingIndex) {
                        z = false;
                    }
                    list.add(i2, songListEntity.setSelected(z));
                    GeneralOriginalCarDeviceData.songList = this.mSongList;
                }
                updateOriginalCarDeviceActivity(null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void songListSetSelected() {
        int[] iArr = this.mCanBusInfoInt;
        this.mPlayingIndex = (iArr[3] * 256) + iArr[4];
        if (GeneralOriginalCarDeviceData.songList != null) {
            Iterator<SongListEntity> it = GeneralOriginalCarDeviceData.songList.iterator();
            while (it.hasNext()) {
                it.next().setSelected(false);
            }
            GeneralOriginalCarDeviceData.songList.get(this.mPlayingIndex).setSelected(true);
        }
    }

    private boolean is0x9CDataChange() {
        if (Arrays.equals(this.m0x9CData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x9CData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setCompass0x9C() {
        if (is0x9CDataChange()) {
            ArrayList arrayList = new ArrayList();
            this.mCompassDeviation = this.mCanBusInfoInt[3];
            arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(this.mCompassDeviation)).setProgress(this.mCompassDeviation - 1).setEnable(this.mCompassDeviationEnable));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean is0x2EDataChange() {
        if (Arrays.equals(this.m0x2EData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x2EData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setVehicleType0x2E() {
        if (is0x2EDataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(7, 0, getVehicleSeries(this.mCanBusInfoInt[2])).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(7, 1, getVehicleType(this.mCanBusInfoInt[3])).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean is0xA6DataChange() {
        if (Arrays.equals(this.m0xA6Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xA6Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setAmplifierData0xA6() {
        if (is0xA6DataChange()) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.leftRight = this.mCanBusInfoByte[3];
            GeneralAmplifierData.frontRear = this.mCanBusInfoByte[4];
            GeneralAmplifierData.bandBass = this.mCanBusInfoByte[5] + 9;
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoByte[6] + 9;
            GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[7] + 9;
            updateAmplifierActivity(null);
            saveAmplifierData();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
            arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, SHARE_123_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_123_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_123_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_123_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_123_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_123_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
    }

    private boolean is0xF0DataChange() {
        if (Arrays.equals(this.m0xF0Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xF0Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setVersionInfo() {
        if (is0xF0DataChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        String str4 = str + " " + str2 + getBandUnit(str);
        byte[] bArrByteMerger = {22, -107, allBandTypeData};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, DataHandleUtils.exceptBOMHead(str4.getBytes("Unicode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bArrMakeBytesFixedLength = DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 39);
        CanbusMsgSender.sendMsg(bArrMakeBytesFixedLength);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), bArrMakeBytesFixedLength);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String str4 = i4 + "/" + i5 + "  " + decimalFormat.format(getHour(i)) + ":" + decimalFormat.format(getMinute(i)) + ":" + decimalFormat.format(getSecond(i));
        byte[] bArrByteMerger = {22, -107, 6};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, str4.getBytes("Unicode"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bArrMakeBytesFixedLength = DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 39);
        CanbusMsgSender.sendMsg(bArrMakeBytesFixedLength);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), bArrMakeBytesFixedLength);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -107, 10}, DataHandleUtils.phoneNum2UnicodeBig(bArr)), 39));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() throws UnsupportedEncodingException {
        super.auxInInfoChange();
        byte[] bytes = new byte[36];
        try {
            bytes = DataHandleUtils.makeMediaInfoCenteredInBytes(18, "AUX").getBytes("unicode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -107, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, DataHandleUtils.exceptBOMHead(bytes));
        CanbusMsgSender.sendMsg(bArrByteMerger);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArrByteMerger = b == 9 ? new byte[]{22, -107, 14} : new byte[]{22, -107, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, DataHandleUtils.exceptBOMHead(((((b7 & 255) * 256) + i) + "/" + i2 + "  " + str2 + ":" + str3).getBytes("Unicode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bArrMakeBytesFixedLength = DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 39);
        CanbusMsgSender.sendMsg(bArrMakeBytesFixedLength);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), bArrMakeBytesFixedLength);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bArrByteMerger = b == 9 ? new byte[]{22, -107, 14} : new byte[]{22, -107, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED};
        try {
            bArrByteMerger = Util.byteMerger(bArrByteMerger, Util.exceptBOMHead(((((b6 & 255) * 256) + i) + "/" + i2 + "  " + str2 + ":" + str3 + ":" + str4).getBytes("Unicode")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bArrMakeBytesFixedLength = Util.makeBytesFixedLength(bArrByteMerger, 39);
        CanbusMsgSender.sendMsg(bArrMakeBytesFixedLength);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), bArrMakeBytesFixedLength);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() throws UnsupportedEncodingException {
        super.btMusicInfoChange();
        byte[] bytes = new byte[36];
        try {
            bytes = DataHandleUtils.makeMediaInfoCenteredInBytes(18, "BT MUISC").getBytes("unicode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -107, 10}, DataHandleUtils.exceptBOMHead(bytes));
        CanbusMsgSender.sendMsg(bArrByteMerger);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i8, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, (byte) i9});
    }

    private String getRunStatus(int i) {
        return CommUtil.getStrByResId(this.mContext, "_123_divice_status_" + i);
    }

    private byte getAllBandTypeData(String str) {
        return (!str.contains("FM") && str.contains("AM")) ? (byte) 4 : (byte) 1;
    }

    private String getBandUnit(String str) {
        return (!str.contains("FM") && str.contains("AM")) ? " KKz" : " MKz";
    }

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private String resolveOutDoorTem() {
        GeneralAirData.fahrenheit_celsius = SharePreUtil.getBoolValue(this.mContext, SHARE_123_OUTDOOR_TEMPERATURE_UNIT, false);
        int i = this.mCanBusInfoInt[13];
        if (GeneralAirData.fahrenheit_celsius) {
            return new DecimalFormat("0.0").format(((((i * 0.5f) - 40.0f) * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(this.mContext);
        }
        return ((i * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            if (254 != i) {
                return 255 == i ? "HI" : new DecimalFormat("0.0").format((((i * 0.5f) * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(this.mContext);
            }
        } else if (254 != i) {
            return 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
        }
        return "LO";
    }

    private String resolveDriveData(int i, int i2, String str) {
        int i3 = (i * 256) + i2;
        return i3 != 65535 ? i3 + str : "---" + str;
    }

    private boolean isFirst() {
        if (isAirFirst) {
            isAirFirst = false;
            if (!GeneralAirData.power) {
                return true;
            }
        }
        return false;
    }

    private String getVehicleSeries(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string.null_value);
        }
        return i == 19 ? this.mContext.getString(R.string.vehicle_series_jeep) : "";
    }

    private String getVehicleType(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string.null_value);
        }
        if (i == 1) {
            return this.mContext.getString(R.string.vehicle_type_jeep_cherokee);
        }
        if (i != 2) {
            return i != 3 ? "" : this.mContext.getString(R.string.vehicle_type_jeep_compass);
        }
        return this.mContext.getString(R.string.vehicle_type_jeep_renegade);
    }

    void updateSettingActivity(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isDoorDataChange() {
        boolean z = this.mIsFrontLeftOpen;
        if (z == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsBeltTie == this.mIsBeltTieNow && this.mIsSubBeltTie == this.mIsSubBeltTieNow) {
            return false;
        }
        this.mIsFrontLeftOpenNow = z;
        this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
        this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
        this.mIsRearRightOpenNow = this.mIsRearRightOpen;
        this.mIsBackOpenNow = this.mIsBackOpen;
        this.mIsBeltTieNow = this.mIsBeltTie;
        this.mIsSubBeltTieNow = this.mIsSubBeltTie;
        return true;
    }

    private boolean isTrackDataChange(byte[] bArr) {
        if (Arrays.equals(this.mTrackDataNow, bArr)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private ParkPageUiSet getmParkPageUiSet() {
        if (this.mParkPageUiSet == null) {
            this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getParkPageUiSet(this.mContext);
        }
        return this.mParkPageUiSet;
    }

    private List getSongList() {
        if (this.mSongList == null) {
            this.mSongList = new ArrayList();
        }
        return this.mSongList;
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        this.mSettingItemHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                this.mSettingItemHashMap.put(itemListBean.getTitleSrn(), new SettingUpdateHelper(new SettingUpdateEntity(i, i2, "null_value"), itemListBean.getMin()));
            }
        }
    }

    private void initDriveItem(DriverDataPageUiSet driverDataPageUiSet) {
        this.mDriveItemHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = driverDataPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemHashMap.put(itemList.get(i2).getTitleSrn(), new DriveDataUpdateHelper(new DriverUpdateEntity(i, i2, "null_value")));
            }
        }
    }

    private SettingUpdateEntity checkEntity(SettingUpdateEntity settingUpdateEntity) {
        if (settingUpdateEntity.getLeftListIndex() == -1 || settingUpdateEntity.getRightListIndex() == -1) {
            return null;
        }
        return settingUpdateEntity;
    }

    private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity driverUpdateEntity) {
        if (driverUpdateEntity.getPage() == -1 || driverUpdateEntity.getIndex() == -1) {
            return null;
        }
        return driverUpdateEntity;
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj);
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj, boolean z) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value").setEnable(false)));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj).setEnable(z);
    }

    private DriverUpdateEntity helperSetDriveDataValue(String str, String str2) {
        if (!this.mDriveItemHashMap.containsKey(str)) {
            this.mDriveItemHashMap.put(str, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
        }
        return this.mDriveItemHashMap.get(str).setValue(str2);
    }

    private static class DriveDataUpdateHelper {
        private DriverUpdateEntity entity;

        public DriveDataUpdateHelper(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public void setEntity(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public DriverUpdateEntity getEntity() {
            return this.entity;
        }

        public DriverUpdateEntity setValue(String str) {
            this.entity.setValue(str);
            return this.entity;
        }
    }

    private static class SettingUpdateHelper {
        private SettingUpdateEntity entity;
        private int progressMin;

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity) {
            this(settingUpdateEntity, 0);
        }

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity, int i) {
            this.entity = settingUpdateEntity;
            this.progressMin = i;
        }

        public SettingUpdateEntity getEntity() {
            return this.entity;
        }

        public SettingUpdateEntity setValue(Object obj) {
            if (obj instanceof Integer) {
                Integer num = (Integer) obj;
                this.entity.setValue(Integer.valueOf(num.intValue() + this.progressMin));
                this.entity.setProgress(num.intValue());
            } else {
                this.entity.setValue(obj);
            }
            return this.entity;
        }

        public SettingUpdateEntity setEnable(boolean z) {
            this.entity.setEnable(z);
            return this.entity;
        }
    }
}
