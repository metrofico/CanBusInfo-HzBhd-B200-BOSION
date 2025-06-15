package com.hzbhd.canbus.car._272;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueueKt;


public class MsgMgr extends AbstractMsgMgr {
    static final String DEVICE_WORK_MODE_AUX_NAVI = "AUX/Navi";
    static final String DEVICE_WORK_MODE_CD = "CD";
    static final String DEVICE_WORK_MODE_IPOD = "IPOD";
    static final String DEVICE_WORK_MODE_RADIO = "Radio";
    static final String DEVICE_WORK_MODE_REAL_AUX = "Real AUX";
    static final String DEVICE_WORK_MODE_TEL_BT = "TEL/BT";
    static final String DEVICE_WORK_MODE_TV = "TV";
    static final String DEVICE_WORK_MODE_USB = "USB";
    private static final String SHARE_272_AMPLIFIER_BALANCE = "share_11_amplifier_balance";
    private static final String SHARE_272_AMPLIFIER_BASS = "share_11_amplifier_bass";
    static final int SHARE_272_AMPLIFIER_DATA_OFFSET = -1;
    private static final String SHARE_272_AMPLIFIER_FADE = "share_11_amplifier_fade";
    private static final String SHARE_272_AMPLIFIER_MIDDLE = "share_11_amplifier_middle";
    static final int SHARE_272_AMPLIFIER_RANGE = 30;
    private static final String SHARE_272_AMPLIFIER_TREBLE = "share_11_amplifier_treble";
    int currentPtore;
    private boolean isOnlyBit7Change;
    private boolean isShowOriginalDeviceStatusMenu;
    private boolean mAirFirst = true;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusOriginalInfoCopy;
    private Context mContext;
    private String mDeviceStatusNow;

    private String discInfo(boolean z, boolean z2) {
        return z ? z2 ? "Rom Disk" : "CD Disc" : " ";
    }

    private String discStatus(boolean z) {
        return z ? "Have Disk" : "No Disk";
    }

    private String getDeviceWorkModle(int i) {
        switch (i) {
            case 0:
                return DEVICE_WORK_MODE_RADIO;
            case 1:
                return DEVICE_WORK_MODE_CD;
            case 2:
                return DEVICE_WORK_MODE_AUX_NAVI;
            case 3:
                return DEVICE_WORK_MODE_USB;
            case 4:
                return DEVICE_WORK_MODE_TEL_BT;
            case 5:
                return DEVICE_WORK_MODE_IPOD;
            case 6:
                return DEVICE_WORK_MODE_TV;
            case 7:
                return DEVICE_WORK_MODE_REAL_AUX;
            default:
                return " ";
        }
    }

    private String getUsbStatus(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "Invalid" : "Reading" : "Stop" : "Play" : "Pause";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        cleanDevice();
        initAmplifierData(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        switch (byteArrayToIntArray[1]) {
            case 129:
                setBaseTrack0x81();
                setDrivingData();
                updateSpeedInfo(this.mCanBusInfoInt[4]);
                break;
            case 130:
                setAirData0x82();
                break;
            case 131:
                this.isShowOriginalDeviceStatusMenu = DataHandleUtils.getBoolBit6(byteArrayToIntArray[2]);
                this.isOnlyBit7Change = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
                if (!is0x83MsgRepeat(bArr) && this.isOnlyBit7Change) {
                    setOriginalHeadUnit0x83();
                    break;
                }
                break;
            case 132:
                if (this.isShowOriginalDeviceStatusMenu) {
                    setRadioData0x84();
                    break;
                }
                break;
            case 133:
                if (this.isShowOriginalDeviceStatusMenu) {
                    setPresetData0x85();
                    break;
                }
                break;
            case HotKeyConstant.K_SLEEP /* 134 */:
                if (this.isShowOriginalDeviceStatusMenu) {
                    setCdcData0x86();
                    break;
                }
                break;
            case 135:
                setAmplifierData0x87();
                break;
            case 136:
                setVersionData0x88();
                break;
            case 137:
                if (this.isShowOriginalDeviceStatusMenu) {
                    setUsbData0x89();
                    break;
                }
                break;
        }
    }

    private void setBaseTrack0x81() {
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[7], (byte) 0, 0, 0x7F, 8);
        updateParkUi(null, this.mContext);
    }

    private void setDrivingData() {
        String str;
        int i = this.mCanBusInfoInt[5];
        if (i == 0) {
            str = "OFF";
        } else {
            str = i == 100 ? "ON" : this.mCanBusInfoInt[5] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[4] + "Km/h"));
        arrayList.add(new DriverUpdateEntity(0, 1, str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirData0x82() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.cycle_in_out_close = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.swing = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_temperature = resolveTemp(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_temperature = resolveTemp(this.mCanBusInfoInt[4]);
        cleanAllBlow();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
        GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setOriginalHeadUnit0x83() {
        if (this.isShowOriginalDeviceStatusMenu) {
            GeneralOriginalCarDeviceData.cdStatus = getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
            if (isDeviceStatusSame(DEVICE_WORK_MODE_RADIO)) {
                cleanSongList();
                setOriginalRadioPage();
            }
            if (isDeviceStatusSame(DEVICE_WORK_MODE_CD)) {
                cleanSongList();
                setOriginalCdPage();
            }
            if (isDeviceStatusSame(DEVICE_WORK_MODE_USB)) {
                cleanSongList();
                setOriginalUsbPage();
            }
            if (!DEVICE_WORK_MODE_RADIO.equals(GeneralOriginalCarDeviceData.cdStatus) && !DEVICE_WORK_MODE_CD.equals(GeneralOriginalCarDeviceData.cdStatus) && !DEVICE_WORK_MODE_USB.equals(GeneralOriginalCarDeviceData.cdStatus)) {
                setOriginalOtherPage();
            }
            if (this.mDeviceStatusNow != GeneralOriginalCarDeviceData.cdStatus) {
                this.mDeviceStatusNow = GeneralOriginalCarDeviceData.cdStatus;
                cleanDevice();
            }
            ArrayList arrayList = new ArrayList();
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
            if (intFromByteWithBit == 127) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(0, CommUtil.getStrByResId(this.mContext, "mute")));
            } else if (intFromByteWithBit >= 0 && intFromByteWithBit <= 40) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(0, Integer.toString(intFromByteWithBit)));
            }
            if (!isCurrentStatus(DEVICE_WORK_MODE_AUX_NAVI)) {
                enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.VIDEO.name().equals(str) || SourceConstantsDef.SOURCE_ID.FM.name().equals(str) || SourceConstantsDef.SOURCE_ID.MUSIC.name().equals(str) || SourceConstantsDef.SOURCE_ID.BTAUDIO.name().equals(str) || SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str) || SourceConstantsDef.SOURCE_ID.NAVIAUDIO.name().equals(str) || SourceConstantsDef.SOURCE_ID.AUX1.name().equals(str) || SourceConstantsDef.SOURCE_ID.ATV.name().equals(str) || SourceConstantsDef.SOURCE_ID.DTV.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -13, 1, 5});
        }
    }

    private void setRadioData0x84() {
        String str = this.mCanBusInfoInt[5] != 0 ? this.mCanBusInfoInt[5] + "" : "";
        this.currentPtore = this.mCanBusInfoInt[5];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, getRadioBand()));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, "P" + str));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, getRadioFreq()));
        GeneralOriginalCarDeviceData.am_st = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (isCurrentStatus(DEVICE_WORK_MODE_RADIO)) {
            GeneralOriginalCarDeviceData.runningState = getRadioStatus(this.mCanBusInfoInt[7]);
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setPresetData0x85() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            for (int i2 = 1; i2 < 7; i2++) {
                int[] iArr = this.mCanBusInfoInt;
                int i3 = i2 * 2;
                arrayList.add(new SongListEntity(((iArr[i3 + 2] * 256) + iArr[i3 + 1]) + "KHz"));
            }
        } else if (i == 2 || i == 3) {
            for (int i4 = 1; i4 < 7; i4++) {
                int[] iArr2 = this.mCanBusInfoInt;
                int i5 = i4 * 2;
                arrayList.add(new SongListEntity((((iArr2[i5 + 2] * 256) + iArr2[i5 + 1]) / 10.0f) + "MHz"));
            }
        }
        if (isCurrentStatus(DEVICE_WORK_MODE_RADIO)) {
            GeneralOriginalCarDeviceData.songList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCdcData0x86() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        if (intFromByteWithBit == 15) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "Single CD"));
        } else if (intFromByteWithBit >= 1 && intFromByteWithBit <= 6) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "Disc" + intFromByteWithBit));
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, Integer.toString(this.mCanBusInfoInt[5])));
        DecimalFormat decimalFormat = new DecimalFormat("00");
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, decimalFormat.format(this.mCanBusInfoInt[6]) + ":" + decimalFormat.format(this.mCanBusInfoInt[7])));
        GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralOriginalCarDeviceData.disc_scan = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
        GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
        GeneralOriginalCarDeviceData.rpt_disc = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
        GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8]);
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]);
        String strDiscStatus = discStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
        String strDiscStatus2 = discStatus(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]));
        String strDiscStatus3 = discStatus(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]));
        String strDiscStatus4 = discStatus(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
        String strDiscStatus5 = discStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        String strDiscStatus6 = discStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        String strDiscInfo = discInfo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
        String strDiscInfo2 = discInfo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]));
        String strDiscInfo3 = discInfo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
        String strDiscInfo4 = discInfo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
        String strDiscInfo5 = discInfo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
        String strDiscInfo6 = discInfo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
        arrayList2.add(new SongListEntity(strDiscStatus + "              " + strDiscInfo));
        arrayList2.add(new SongListEntity(strDiscStatus2 + "              " + strDiscInfo2));
        arrayList2.add(new SongListEntity(strDiscStatus3 + "              " + strDiscInfo3));
        arrayList2.add(new SongListEntity(strDiscStatus4 + "              " + strDiscInfo4));
        arrayList2.add(new SongListEntity(strDiscStatus5 + "              " + strDiscInfo5));
        arrayList2.add(new SongListEntity(strDiscStatus6 + "              " + strDiscInfo6));
        if (isCurrentStatus(DEVICE_WORK_MODE_CD)) {
            GeneralOriginalCarDeviceData.songList = arrayList2;
            GeneralOriginalCarDeviceData.mList = arrayList;
            GeneralOriginalCarDeviceData.runningState = getChangerStatus(this.mCanBusInfoInt[9]);
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setAmplifierData0x87() {
        GeneralAmplifierData.leftRight = (this.mCanBusInfoInt[3] - 1) - 15;
        GeneralAmplifierData.frontRear = (this.mCanBusInfoInt[4] - 1) - 15;
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 1;
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 1;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 1;
        updateAmplifierActivity(null);
        saveAmplifierData();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[8])));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[10])));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[11])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionData0x88() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setUsbData0x89() {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, Integer.toString(this.mCanBusInfoInt[2])));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, Integer.toString(this.mCanBusInfoInt[3])));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, decimalFormat.format(this.mCanBusInfoInt[4]) + ":" + decimalFormat.format(this.mCanBusInfoInt[5])));
        GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.disc_scan = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.rpt_disc = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.insert = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.aux_insert = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.runningState = getUsbStatus(this.mCanBusInfoInt[7]);
        if (isCurrentStatus(DEVICE_WORK_MODE_USB)) {
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private boolean isFirst() {
        if (!this.mAirFirst) {
            return false;
        }
        this.mAirFirst = false;
        return !GeneralAirData.power;
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private String resolveTemp(int i) {
        return i == 1 ? "LO" : i > 64 ? "HI" : (i != 0 && i >= 36) ? (i / 2.0f) + getTempUnitC(this.mContext) : " ";
    }

    private String getRadioBand() {
        int i = this.mCanBusInfoInt[2];
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? " " : "LW" : "MW" : "FM2" : "FM1" : "AM" : "FM";
    }

    private String getRadioFreq() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[4] * 256) + iArr[3];
        int i2 = iArr[2];
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2 && i2 != 3) {
                    if (i2 != 4 && i2 != 5) {
                        return " ";
                    }
                }
            }
            return i + "KHz";
        }
        return (i / 10.0f) + "MHz";
    }

    private String getRadioStatus(int i) {
        if (i == 255) {
            return this.mContext.getResources().getString(R.string.invalid);
        }
        switch (i) {
            case 1:
                return "SEEK+";
            case 2:
                return "SEEK-";
            case 3:
                return "Auto SEEK";
            case 4:
                return "Tune+";
            case 5:
                return "Tune-";
            case 6:
                return "SCAN";
            default:
                return " ";
        }
    }

    private String getChangerStatus(int i) {
        return CommUtil.getStrByResId(this.mContext, "_272_divice_status_" + i);
    }

    private boolean isDeviceStatusSame(String str) {
        if (!isCurrentStatus(str) || str.equals(this.mDeviceStatusNow)) {
            return false;
        }
        this.mDeviceStatusNow = str;
        cleanDevice();
        return true;
    }

    private boolean isCurrentStatus(String str) {
        return str.equals(GeneralOriginalCarDeviceData.cdStatus);
    }

    private void cleanDevice() {
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.am_st = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.scan = false;
        GeneralOriginalCarDeviceData.disc_scan = false;
        GeneralOriginalCarDeviceData.rpt = false;
        GeneralOriginalCarDeviceData.rpt_disc = false;
        GeneralOriginalCarDeviceData.rdm = false;
        GeneralOriginalCarDeviceData.rdm_disc = false;
        GeneralOriginalCarDeviceData.startTime = "     ";
        GeneralOriginalCarDeviceData.endTime = "     ";
        GeneralOriginalCarDeviceData.progress = 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, " "));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, " "));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, " "));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, " "));
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.AM, OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.CDC, OriginalBtnAction.NAVI, OriginalBtnAction.USB_BOX, "aux", OriginalBtnAction.AM_ST, OriginalBtnAction.ST}, new String[]{"band", OriginalBtnAction.RADIO_SCAN, "up", "left", "right", "down", OriginalBtnAction.PRESET_SCAN, OriginalBtnAction.AUTO_STORE}, true);
    }

    private void setOriginalCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_number_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", LcdInfoShare.MediaInfo.current_track, ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_play_time", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.AM, OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.CDC, OriginalBtnAction.NAVI, OriginalBtnAction.USB_BOX, "aux", OriginalBtnAction.SCAN, OriginalBtnAction.DISC_SCAN, OriginalBtnAction.RPT, OriginalBtnAction.RPT_DISC, OriginalBtnAction.RDM, OriginalBtnAction.RDM_DISC}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.PLAY, "down", "right", OriginalBtnAction.NEXT_DISC}, true);
    }

    private void setOriginalUsbPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_file", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_song_list", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_play_time", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.AM, OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.CDC, OriginalBtnAction.NAVI, OriginalBtnAction.USB_BOX, "aux", OriginalBtnAction.SCAN, OriginalBtnAction.DISC_SCAN, OriginalBtnAction.RPT, OriginalBtnAction.RPT_DISC, OriginalBtnAction.RDM, OriginalBtnAction.RDM_DISC, OriginalBtnAction.INSERT, OriginalBtnAction.AUX_INSERT}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.PLAY, "down", "right", OriginalBtnAction.NEXT_DISC}, false);
    }

    private void setOriginalOtherPage() {
        GeneralOriginalCarDeviceData.mList = null;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.AM, OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.CDC, OriginalBtnAction.NAVI, OriginalBtnAction.USB_BOX, "aux"}, new String[0], false);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, String[] strArr2, boolean z) {
        cleanDevice();
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        if (originalCarDevicePageUiSet == null) {
            return;
        }
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        originalCarDevicePageUiSet.setRowBottomBtnAction(strArr2);
        originalCarDevicePageUiSet.setHaveSongList(z);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
        if (isDeviceStatusSame(DEVICE_WORK_MODE_AUX_NAVI)) {
            return;
        }
        enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
    }

    private void cleanSongList() {
        if (GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
        }
    }

    void updateOriginalCarDevice(Bundle bundle) {
        updateOriginalCarDeviceActivity(bundle);
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, SHARE_272_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_272_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_272_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_272_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_272_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.hzbhd.canbus.car._272.MsgMgr$1] */
    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_272_AMPLIFIER_FADE, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_272_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_272_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_272_AMPLIFIER_MIDDLE, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_272_AMPLIFIER_TREBLE, 0);
        }
        new Thread() { // from class: com.hzbhd.canbus.car._272.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 2, (byte) (GeneralAmplifierData.leftRight + 1 + 15)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 3, (byte) (GeneralAmplifierData.frontRear + 1 + 15)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 4, (byte) (GeneralAmplifierData.bandBass + 1)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 5, (byte) (GeneralAmplifierData.bandMiddle + 1)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -16, 6, (byte) (GeneralAmplifierData.bandTreble + 1)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private boolean is0x83MsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusOriginalInfoCopy)) {
            return true;
        }
        this.mCanBusOriginalInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
