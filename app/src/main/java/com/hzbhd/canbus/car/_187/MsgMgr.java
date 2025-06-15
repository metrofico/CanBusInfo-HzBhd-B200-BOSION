package com.hzbhd.canbus.car._187;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    private static final String SHARE_AMPLIFIER_ASL = "share_amplifier_asl";
    private static final String SHARE_AMPLIFIER_BALANCE = "share_amplifier_balance";
    private static final String SHARE_AMPLIFIER_BASS = "share_amplifier_bass";
    private static final String SHARE_AMPLIFIER_BOSE = "share_amplifier_bose";
    private static final String SHARE_AMPLIFIER_FADE = "share_amplifier_fade";
    private static final String SHARE_AMPLIFIER_FIELD = "share_amplifier_field";
    private static final String SHARE_AMPLIFIER_SURROUND = "share_amplifier_surroubd";
    private static final String SHARE_AMPLIFIER_TREBLE = "share_amplifier_treble";
    private static final String SHARE_AMPLIFIER_VOLUME = "share_amplifier_volume";
    private static final int _187_AMPLIFIER_BAND_OFFSET = 5;
    private static final int _187_AMPLIFIER_VOLUME_OFFSET = 20;
    int mAmpAslValueNow;
    int mAmpBoseValueNow;
    int mAmpFieldValueNow;
    int mAmpSurroundValueNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
        initAmplifierCmd();
        startTimer(0, 100);
        changeAmplifierSettings();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            realKeyControl0x20();
            return;
        }
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 106) {
            setCarSpeed0x6A();
            return;
        }
        if (i == 147) {
            setAmplifierInfo0x93();
        } else if (i == 34) {
            setRearRadar0x22();
        } else {
            if (i != 35) {
                return;
            }
            setFrontRadar0x23();
        }
    }

    private void setCarSpeed0x6A() {
        int[] iArr = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]));
    }

    private void realKeyControl0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick1(0);
        }
        if (i == 1) {
            realKeyClick1(7);
            return;
        }
        if (i == 2) {
            realKeyClick1(8);
            return;
        }
        if (i == 3) {
            realKeyClick1(45);
            return;
        }
        if (i == 4) {
            realKeyClick1(46);
            return;
        }
        if (i == 7) {
            realKeyClick1(2);
            return;
        }
        if (i == 96) {
            realKeyClick1(45);
            return;
        }
        if (i == 98) {
            realKeyClick1(48);
            return;
        }
        if (i == 100) {
            realKeyClick1(46);
            return;
        }
        if (i == 102) {
            realKeyClick1(47);
            return;
        }
        if (i == 135) {
            realKeyClick1(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 9) {
            realKeyClick1(14);
            return;
        }
        if (i == 10) {
            realKeyClick1(15);
            return;
        }
        if (i == 21) {
            realKeyClick1(50);
            return;
        }
        if (i == 22) {
            realKeyClick1(49);
            return;
        }
        switch (i) {
            case 32:
                realKeyClick1(HotKeyConstant.K_HOUR);
                break;
            case 33:
                realKeyClick1(HotKeyConstant.K_HOUR);
                break;
            case 34:
                realKeyClick1(128);
                break;
            case 35:
                realKeyClick1(128);
                break;
            case 36:
                realKeyClick1(76);
                break;
            case 37:
                realKeyClick1(130);
                break;
            case 38:
                realKeyClick1(77);
                break;
            case 39:
                realKeyClick1(33);
                break;
            case 40:
                realKeyClick1(34);
                break;
            case 41:
                realKeyClick1(35);
                break;
            case 42:
                realKeyClick1(36);
                break;
            case 43:
                realKeyClick1(37);
                break;
            case 44:
                realKeyClick1(38);
                break;
            case 45:
                sendKnob_1(7);
                break;
            case 46:
                sendKnob_1(8);
                break;
            case 47:
                sendKnob_2(47);
                break;
            case 48:
                sendKnob_2(48);
                break;
            case 49:
                realKeyClick1(45);
                break;
            case 50:
                realKeyClick1(46);
                break;
            case 51:
                realKeyClick1(17);
                break;
            default:
                switch (i) {
                    case 112:
                        realKeyClick1(49);
                        break;
                    case 113:
                        realKeyClick1(50);
                        break;
                    case 114:
                        realKeyClick1(128);
                        break;
                    case 115:
                        realKeyClick1(52);
                        break;
                    case 116:
                        sendKnob_1(7);
                        break;
                    case 117:
                        sendKnob_1(8);
                        break;
                }
        }
    }

    private void setRearRadar0x22() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar0x23() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[6], iArr[7], iArr[8], iArr[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        updateParkUi(null, this.mContext);
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[3], bArr[2], 0, 5448, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAmplifierInfo0x93() {
        Log.i("ljq", "setAmplifierInfo0x93: ");
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.bandBass = this.mCanBusInfoByte[3] + 5;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[4] + 5;
        GeneralAmplifierData.leftRight = this.mCanBusInfoByte[5];
        GeneralAmplifierData.frontRear = this.mCanBusInfoByte[6];
        updateAmplifierActivity(null);
        this.mAmpAslValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[7], 0, 5);
        this.mAmpBoseValueNow = this.mCanBusInfoInt[8];
        this.mAmpSurroundValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[9], -5, 5);
        this.mAmpFieldValueNow = this.mCanBusInfoInt[10];
        changeAmplifierSettings();
        saveAmplifierData();
    }

    private void changeAmplifierSettings() {
        Log.i("ljq", "changeAmplifierSettings: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mAmpAslValueNow)).setProgress(this.mAmpAslValueNow));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mAmpBoseValueNow)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mAmpSurroundValueNow)).setProgress(this.mAmpSurroundValueNow + 5));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mAmpFieldValueNow)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void saveAmplifierData() {
        Log.i("ljq", "saveAmplifierData: ");
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_ASL, this.mAmpAslValueNow);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_BOSE, this.mAmpBoseValueNow);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_SURROUND, this.mAmpSurroundValueNow);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_FIELD, this.mAmpFieldValueNow);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_VOLUME, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_TREBLE, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_AMPLIFIER_FADE, 0);
            this.mAmpAslValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_ASL, 0);
            this.mAmpBoseValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_BOSE, 0);
            this.mAmpSurroundValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_SURROUND, 0);
            this.mAmpFieldValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_FIELD, 0);
        }
        Log.i("ljq", "initAmplifierData: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
    }

    private void initAmplifierCmd() {
        Log.i("ljq", "initAmplifierCmd: ");
        final byte[][] bArr = {new byte[]{22, -125, 33, (byte) (GeneralAmplifierData.volume - 20)}, new byte[]{22, -125, 34, (byte) (GeneralAmplifierData.bandBass - 5)}, new byte[]{22, -125, 35, (byte) (GeneralAmplifierData.bandTreble - 5)}, new byte[]{22, -125, 36, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -125, 36, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -125, 37, (byte) GeneralAmplifierData.frontRear}, new byte[]{22, -125, 38, (byte) this.mAmpAslValueNow}, new byte[]{22, -125, 39, (byte) this.mAmpBoseValueNow}, new byte[]{22, -125, 40, (byte) this.mAmpSurroundValueNow}, new byte[]{22, -125, 41, (byte) this.mAmpFieldValueNow}};
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._187.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    MsgMgr.this.finishTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        };
    }

    private void startTimer(int i, int i2) {
        Log.i("ljq", "startTimer: ");
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask == null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer == null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        initAmplifierData(this.mContext);
        initAmplifierCmd();
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void sendKnob_1(int i) {
        realKeyClick3_1(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }

    private void sendKnob_2(int i) {
        realKeyClick3_2(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }
}
