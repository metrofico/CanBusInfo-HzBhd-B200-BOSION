package com.hzbhd.canbus.car._434;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr;
import com.hzbhd.canbus.car._434.speech.ISysRxListener;
import com.hzbhd.canbus.car._434.speech.SRxData;
import com.hzbhd.canbus.car._434.speech.STxData;
import com.hzbhd.canbus.car._434.speech.SysRxSpeechReceiver;
import com.hzbhd.canbus.car._434.speech.SysToSpeechReceiver;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.config.constant.ClassName;
import com.hzbhd.constant.share.ShareConstants;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends MsAbstractMsgMgr {
    private static SysToSpeechReceiver.AcCtrl sa;
    private static SysToSpeechReceiver.CarCtrl sc;
    private static SysToSpeechReceiver sysToSpeechReceiver;
    private SparseArray<String> beepVoice;
    int[] mAirData;
    int[] mCanBusInfoInt;
    Context mContext;
    int[] mLightData;
    private UiMgr mUiMgr;
    private SparseArray<String> speechVoice;
    private VoiceManger voiceManger;
    private int nowRadarData11 = 0;
    private int nowRadarData12 = 0;
    private final String PATH_VOICE = "voice/";
    private boolean handsTag = false;

    @Override // com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        getUiMgr(context).registerCanBusAirControlListener();
        getUiMgr(context).registerCanBusBasicControlListener();
        initRadarVoice();
        registerSanYiVoiceReceiver(context);
        SysToSpeechReceiver sysToSpeechReceiver2 = new SysToSpeechReceiver(context);
        sysToSpeechReceiver = sysToSpeechReceiver2;
        sa = sysToSpeechReceiver2.getAc();
        sc = sysToSpeechReceiver.getCar();
    }

    @Override // com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        updateVersionInfo(context, "CANBOX-V1.0.0");
        Settings.System.putInt(context.getContentResolver(), ShareConstants.SHARE_OUT_OF_MISC_TURN_LEFT_RIGHT_TAG, 0);
    }

    @Override // com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
    }

    @Override // com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
    }

    @Override // com.hzbhd.canbus.car._434.impl.MsAbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (getMsDataType(byteArrayToIntArray) == 419404313) {
            SharedAirData(this.mCanBusInfoInt);
            if (this.handsTag && isAirDataChange()) {
                sendAirInfoToSpeech(bArr);
                return;
            }
            return;
        }
        if (getMsDataType(this.mCanBusInfoInt) == 418322406) {
            showRadarView();
            setRadarVoiceData(context);
            ShareBasicInfo(this.mCanBusInfoInt);
        } else {
            if (getMsDataType(this.mCanBusInfoInt) == 419366352) {
                toMute(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2));
                return;
            }
            if (getMsDataType(this.mCanBusInfoInt) == 419427075) {
                updateSpeedInfo(this.mCanBusInfoInt[10]);
            }
            ShareBasicInfo(this.mCanBusInfoInt);
            if (isLightDataChange()) {
                sendLightInfoToSheech(this.mCanBusInfoInt);
            }
        }
    }

    private void toMute(int i) {
        if (i == 1) {
            if (isMute()) {
                return;
            }
            realKeyLongClick1(this.mContext, 3, 1);
            realKeyLongClick1(this.mContext, 3, 0);
            return;
        }
        if (i == 0 && isMute()) {
            realKeyLongClick1(this.mContext, 3, 1);
            realKeyLongClick1(this.mContext, 3, 0);
        }
    }

    private void sendLightInfoToSheech(int[] iArr) {
        int msDataType = getMsDataType(this.mCanBusInfoInt);
        if (msDataType == 419315745) {
            if (DataHandleUtils.getBoolBit2(iArr[7])) {
                sc.sendAlarmLight(true);
                return;
            } else {
                sc.sendAlarmLight(false);
                return;
            }
        }
        if (msDataType != 419316001) {
            if (msDataType != 419367713) {
                return;
            }
            if (DataHandleUtils.getBoolBit2(iArr[10])) {
                sc.sendWiper(true);
                return;
            } else {
                sc.sendWiper(true);
                return;
            }
        }
        if (DataHandleUtils.getBoolBit4(iArr[7])) {
            sc.sendHeadlight(true);
        } else {
            sc.sendHeadlight(false);
        }
        if (DataHandleUtils.getBoolBit6(iArr[7])) {
            sc.sendHighbeam(true);
        } else {
            sc.sendHighbeam(false);
        }
    }

    private void sendAirInfoToSpeech(byte[] bArr) {
        sa.sendTemp(bArr[11] / 2);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(bArr[8], 0, 3);
        if (intFromByteWithBit == 1) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.face);
        } else if (intFromByteWithBit == 2) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.facefoot);
        } else if (intFromByteWithBit == 3) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.foot);
        } else if (intFromByteWithBit == 4) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.footdefrost);
        }
        if (DataHandleUtils.getIntFromByteWithBit(bArr[8], 0, 3) == 5) {
            sa.sendDefrost(true);
        } else {
            sa.sendDefrost(false);
        }
        sa.sendWind(DataHandleUtils.getIntFromByteWithBit(bArr[8], 3, 4));
        if (DataHandleUtils.getIntFromByteWithBit(bArr[7], 1, 2) == 0) {
            sa.sendCold(false);
        } else if (DataHandleUtils.getIntFromByteWithBit(bArr[7], 1, 2) == 1) {
            sa.sendCold(true);
        }
        if (DataHandleUtils.getIntFromByteWithBit(bArr[7], 3, 1) == 0) {
            sa.sendAir(false);
        } else {
            sa.sendAir(true);
        }
        if (DataHandleUtils.getIntFromByteWithBit(bArr[7], 7, 1) == 0) {
            sa.sendLoop(true);
        } else {
            sa.sendLoop(false);
        }
    }

    private void setRadarVoiceData(Context context) {
        int i = this.nowRadarData11;
        int i2 = this.mCanBusInfoInt[13];
        if (i != i2) {
            this.nowRadarData11 = i2;
            setRadarVoice(context, DataHandleUtils.getIntFromByteWithBit(i2, 5, 3));
        }
        int i3 = this.nowRadarData12;
        int i4 = this.mCanBusInfoInt[14];
        if (i3 != i4) {
            this.nowRadarData12 = i4;
            setRadarVoice(context, DataHandleUtils.getIntFromByteWithBit(i4, 0, 3));
        }
    }

    public void showRadarView() {
        try {
            if (Settings.System.getString(this.mContext.getContentResolver(), ShareConstants.SHARE_MS_RADAR_SETTING_SHOW_RADAR_SWITCH) == null || !Settings.System.getString(this.mContext.getContentResolver(), ShareConstants.SHARE_MS_RADAR_SETTING_SHOW_RADAR_SWITCH).equals("ON")) {
                return;
            }
            ComponentName componentName = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningTasks(1).get(0).topActivity;
            if (componentName.getClassName().trim().equals("com.hzbhd.ui.config.ui_l9.activity.MsRadarActivity") || componentName.getClassName().equals(ClassName.bhd_ui_video)) {
                return;
            }
            startOtherActivity(this.mContext, HzbhdComponentName.MsRadarActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRadarVoice() {
        this.voiceManger = new VoiceManger();
        SparseArray<String> sparseArray = new SparseArray<>();
        this.speechVoice = sparseArray;
        sparseArray.put(1, "_434_speech_0x01_stop.mp3");
        this.speechVoice.put(2, "_434_speech_0x02_0x03_0x04_0x07.mp3");
        this.speechVoice.put(3, "_434_speech_0x02_0x03_0x04_0x07.mp3");
        this.speechVoice.put(4, "_434_speech_0x02_0x03_0x04_0x07.mp3");
        this.speechVoice.put(5, "_434_speech_0x05_3second.mp3");
        this.speechVoice.put(6, "_434_speech_0x06_1second.mp3");
        this.speechVoice.put(7, "_434_speech_0x02_0x03_0x04_0x07.mp3");
        SparseArray<String> sparseArray2 = new SparseArray<>();
        this.beepVoice = sparseArray2;
        sparseArray2.put(1, "_434_beep_0x01_long.mp3");
        this.beepVoice.put(2, "_434_beep_0x02_6hz.mp3");
        this.beepVoice.put(3, "_434_beep_0x03_4hz.mp3");
        this.beepVoice.put(4, "_434_beep_0x04_2hz.mp3");
        this.beepVoice.put(5, "_434_speech_0x05_3second.mp3");
        this.beepVoice.put(6, "_434_speech_0x06_1second.mp3");
        this.beepVoice.put(7, "_434_beep_0x07_6beep.mp3");
    }

    private synchronized void setRadarVoice(Context context, int i) {
        if (i != 0) {
            if (!TextUtils.isEmpty(this.speechVoice.get(i))) {
                requestVoiceSource();
                this.voiceManger.setVolume(Integer.parseInt(Settings.System.getString(this.mContext.getContentResolver(), ShareConstants.SHARE_MS_RADAR_VOLUME)));
                if (Settings.System.getString(this.mContext.getContentResolver(), ShareConstants.SHARE_MS_BEEP_SPEECH).equals("BEEP")) {
                    this.voiceManger.play(context, "voice/" + this.beepVoice.get(i));
                } else {
                    this.voiceManger.play(context, "voice/" + this.speechVoice.get(i));
                }
            }
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return false;
        }
        this.mAirData = this.mCanBusInfoInt;
        return true;
    }

    private boolean isLightDataChange() {
        if (Arrays.equals(this.mLightData, this.mCanBusInfoInt)) {
            return false;
        }
        this.mLightData = this.mCanBusInfoInt;
        return true;
    }

    private void registerSanYiVoiceReceiver(Context context) {
        SysRxSpeechReceiver sysRxSpeechReceiver = new SysRxSpeechReceiver(this.mContext, new SanYiVoiceControl());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.hzbhd.speech.to.sys");
        context.registerReceiver(sysRxSpeechReceiver, intentFilter);
    }

    private class SanYiVoiceControl implements ISysRxListener {
        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcAuto(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcWarm(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarClearancelamps(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarReadinglamp(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarToplight(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum modeEnum) {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarWiperMove(int i) {
        }

        private SanYiVoiceControl() {
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcAir(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 64, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 64, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 64, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcCold(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 2, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 2, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 2, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 1, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 1, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 1, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcLoop(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 64, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 64, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 64, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcDefrost(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 32, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 32, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 32, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 16, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 16, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 16, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcTempTo(int i) {
            byte b = (byte) (i * 2);
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, 0, b, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, 0, b, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, 0, b, 1});
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcTempInc(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcTempDec(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 64, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 64, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 64, 0, 0, 0, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcWind(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 32, 0, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 32, 0, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 32, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 16, 0, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 16, 0, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 16, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcWindTo(int i) {
            byte b = (byte) i;
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, b, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, b, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 0, 0, b, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void AcMode(STxData.AcCtrl.Mode.ModeEnum modeEnum) {
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.face)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 8, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 8, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 8, 0, 0, 0, 1});
            }
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.foot)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 24, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 24, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 24, 0, 0, 0, 1});
            }
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.footdefrost)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 32, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 32, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 32, 0, 0, 0, 1});
            }
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.facefoot)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 16, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 16, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 7, -17, 0, 0, 0, 0, 16, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarHeadlight(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarHighBeam(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarAlarmlight(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarWindowClose() {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum typeEnum) {
            if (typeEnum.equals(STxData.CarCtrl.WindowOpen.TypeEnum.leftfront)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            }
            if (typeEnum.equals(STxData.CarCtrl.WindowOpen.TypeEnum.rightfront)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarLock(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -65, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void CarWiper(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -6, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -1, -8, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._434.speech.ISysRxListener
        public void SystemSync() {
            MsgMgr.this.handsTag = true;
        }
    }
}
