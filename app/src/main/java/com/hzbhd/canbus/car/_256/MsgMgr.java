package com.hzbhd.canbus.car._256;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    static final int SHARE_256_AMP_BAS_TRE_OFFSET = 10;
    static final int SHARE_256_AMP_FAD_BAL_OFFSET = 16;
    private boolean ac;
    private boolean auto;
    private boolean eco;
    private boolean fornt_left_blow_foot;
    private boolean fornt_left_blow_head;
    private boolean fornt_right_blow_foot;
    private boolean fornt_right_blow_head;
    private boolean front_defog;
    private int front_wind_level;
    private boolean in_out_cycle;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private int mDiscExsit;
    private int mDiscRadom;
    private int mDiscRepeat;
    private int mRadioSt;
    private int mSdCardIn;
    private TimerTask mTimeTask;
    private Timer mTimer;
    private boolean rear_defog;
    private boolean mIsFirstTime = true;
    private String front_left_temperature = "";
    private String front_right_temperature = "";

    private int getIntByBoolan(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        initAmplifier(context);
        startReportDeviceInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 41) {
            setTrack();
            return;
        }
        if (i == 49) {
            setSetting0x31();
            setAmplifier();
            return;
        }
        if (i != 50) {
            switch (i) {
                case 16:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x10(this.mCanBusInfoInt);
                        break;
                    }
                    break;
                case 17:
                    bluetooth0x11();
                    break;
                case 18:
                    keyControl0x12();
                    break;
            }
            return;
        }
        setRadar();
    }

    private void setRadar() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = this.mCanBusInfoInt[3];
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(iArr[3], iArr[4] + 1, iArr[5] + 1, iArr[6] + 1, iArr[7] + 1);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            updateParkUi(null, this.mContext);
            return;
        }
        if (i == 1) {
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            GeneralParkData.radar_distance_disable = new int[]{255};
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(iArr2[4], iArr2[5], iArr2[6], iArr2[7]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setTrack() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 7744, 13424, 16);
        updateParkUi(null, this.mContext);
    }

    private void setSetting0x31() {
        int intByBoolan = getIntByBoolan(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(intByBoolan)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAmplifier() {
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[2] - 16;
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 16;
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[4] - 10;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[5] - 10;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[6];
        saveAmplifierData(this.mContext, this.mCanId);
        updateAmplifierActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            reportCanbusInfo(" ", SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name());
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        reportCanbusInfo(str + " " + str2 + getBandUnit(str), SourceConstantsDef.SOURCE_ID.FM.name());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        reportCanbusInfo("BT MUSIC", SourceConstantsDef.SOURCE_ID.BTAUDIO.name());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        reportCanbusInfo("MUSIC", SourceConstantsDef.SOURCE_ID.MUSIC.name());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        reportCanbusInfo("VIDEO", SourceConstantsDef.SOURCE_ID.VIDEO.name());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i7 == 240) {
            sendDiscEjectMsg(this.mContext);
        } else if (i7 == 32) {
            reportCanbusInfo("DISC", SourceConstantsDef.SOURCE_ID.MPEG.name());
        }
    }

    private boolean isDeviceMsgChange(int i, int i2, int i3, int i4, int i5) {
        return (this.mDiscRadom == i && this.mDiscRepeat == i2 && this.mRadioSt == i3 && this.mSdCardIn == i4 && this.mDiscExsit == i5) ? false : true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        if (isDeviceMsgChange(i2, i3, i7, i5, i4)) {
            this.mDiscRadom = i2;
            this.mDiscRepeat = i3;
            this.mRadioSt = i7;
            this.mSdCardIn = i5;
            this.mDiscExsit = i4;
            UiMgr.m0x06Byte = (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 4, i2 == 1);
            UiMgr.m0x06Byte = (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 3, i3 == 1);
            UiMgr.m0x06Byte = (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 2, i7 == 1);
            UiMgr.m0x06Byte = (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 1, i5 == 1);
            UiMgr.m0x06Byte = (byte) DataHandleUtils.setIntByteWithBit(UiMgr.m0x06Byte, 0, i4 == 1);
        }
    }

    private void reportCanbusInfo(String str, String str2) {
        sendMediaMsg(this.mContext, str2, Util.byteMerger(new byte[]{22, 7}, Util.makeMediaInfoCenteredInBytes(15, str).getBytes()));
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

    private void keyControl0x12() {
        int i = this.mCanBusInfoInt[2];
        if (i == 48) {
            realKeyClick(14);
            return;
        }
        if (i != 49) {
            switch (i) {
                case 1:
                    realKeyClick(75);
                    break;
                case 2:
                    realKeyClick(129);
                    break;
                case 3:
                    realKeyClick(52);
                    break;
                case 4:
                    realKeyClick(30);
                    break;
                case 5:
                    realKeyClick(4);
                    break;
                case 6:
                    realKeyClick(31);
                    break;
                case 7:
                    realKeyClick(HotKeyConstant.K_SLEEP);
                    break;
                default:
                    switch (i) {
                        case 17:
                            realKeyClick(2);
                            break;
                        case 18:
                            realKeyClick(48);
                            break;
                        case 19:
                            realKeyClick(47);
                            break;
                        case 20:
                            realKeyClick(7);
                            break;
                        case 21:
                            realKeyClick(8);
                            break;
                        case 22:
                            realKeyClick(3);
                            break;
                        case 23:
                            realKeyClick(206);
                            break;
                        case 24:
                            realKeyClick(HotKeyConstant.K_NEXT_HANGUP);
                            break;
                    }
            }
            return;
        }
        realKeyClick(15);
    }

    private void realKeyClick(int i) {
        realKeyClick(this.mContext, i);
    }

    private void bluetooth0x11() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            exitAuxIn2();
        } else {
            if (i != 1) {
                return;
            }
            enterAuxIn2();
        }
    }

    private void setAirData0x10(int[] iArr) {
        if (DataHandleUtils.getBoolBit7(iArr[4])) {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        } else {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(iArr[2]);
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(iArr[2]);
        }
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit7(iArr[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(iArr[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(iArr[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(iArr[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(iArr[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(iArr[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit3(iArr[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit2(iArr[3]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit1(iArr[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(iArr[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4);
        if (isAirMsgChange()) {
            this.front_left_temperature = GeneralAirData.front_left_temperature;
            this.front_right_temperature = GeneralAirData.front_right_temperature;
            this.in_out_cycle = GeneralAirData.in_out_cycle;
            this.front_defog = GeneralAirData.front_defog;
            this.rear_defog = GeneralAirData.rear_defog;
            this.fornt_left_blow_foot = GeneralAirData.front_left_blow_foot;
            this.fornt_right_blow_foot = GeneralAirData.front_right_blow_foot;
            this.fornt_left_blow_head = GeneralAirData.front_left_blow_head;
            this.fornt_right_blow_head = GeneralAirData.front_right_blow_head;
            this.auto = GeneralAirData.auto;
            this.eco = GeneralAirData.eco;
            this.ac = GeneralAirData.ac;
            this.front_wind_level = GeneralAirData.front_wind_level;
            if (this.mIsFirstTime) {
                this.mIsFirstTime = false;
            } else {
                updateAirActivity(this.mContext, 1001);
            }
        }
    }

    private boolean isAirMsgChange() {
        return (this.front_left_temperature.equals(GeneralAirData.front_left_temperature) && this.front_right_temperature.equals(GeneralAirData.front_right_temperature) && this.in_out_cycle == GeneralAirData.in_out_cycle && this.front_defog == GeneralAirData.front_defog && this.rear_defog == GeneralAirData.rear_defog && this.fornt_left_blow_foot == GeneralAirData.front_left_blow_foot && this.fornt_right_blow_foot == GeneralAirData.front_right_blow_foot && this.fornt_left_blow_head == GeneralAirData.front_left_blow_head && this.fornt_right_blow_head == GeneralAirData.front_right_blow_head && this.auto == GeneralAirData.auto && this.eco == GeneralAirData.eco && this.ac == GeneralAirData.ac && this.front_wind_level == GeneralAirData.front_wind_level) ? false : true;
    }

    private String resolveOutDoorTem() {
        if (this.mCanBusInfoInt[2] < 0) {
            return (r1 - 100) + "." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + getTempUnitC(this.mContext);
        }
        return ((r1 - 100) + (DataHandleUtils.getIntFromByteWithBit(r0[5], 0, 4) * 0.1d)) + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (254 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        return (1 > i || i > 254) ? "" : i + "." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + getTempUnitC(this.mContext);
    }

    private void startReportDeviceInfo() {
        new TimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._256.MsgMgr.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, 6, UiMgr.m0x06Byte});
            }
        }, 0L, 1500L);
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -3, 7, (byte) GeneralAmplifierData.volume});
        final byte[][] bArr = {new byte[]{22, -3, 1, (byte) (GeneralAmplifierData.frontRear + 16)}, new byte[]{22, -3, 2, (byte) (GeneralAmplifierData.leftRight + 16)}, new byte[]{22, -3, 4, (byte) (GeneralAmplifierData.bandBass + 10)}, new byte[]{22, -3, 5, (byte) (GeneralAmplifierData.bandTreble + 10)}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._256.MsgMgr.2
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    timerUtil.stopTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        }, 0L, 100L);
    }

    private class TimerUtil {
        private Timer mTimer;
        private TimerTask mTimerTask;

        private TimerUtil() {
        }

        public void startTimer(TimerTask timerTask, long j, long j2) {
            Log.i("TimerUtil", "startTimer: " + this);
            if (timerTask == null) {
                return;
            }
            this.mTimerTask = timerTask;
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            this.mTimer.schedule(this.mTimerTask, j, j2);
        }

        public void stopTimer() {
            Log.i("TimerUtil", "stopTimer: " + this);
            TimerTask timerTask = this.mTimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimerTask = null;
            }
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
            }
        }
    }
}
