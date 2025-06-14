package com.hzbhd.canbus.car._443;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._443.info.SystemInfo;
import com.hzbhd.canbus.car._443.speech.ISysRxListener;
import com.hzbhd.canbus.car._443.speech.SRxData;
import com.hzbhd.canbus.car._443.speech.STxData;
import com.hzbhd.canbus.car._443.speech.SysRxSpeechReceiver;
import com.hzbhd.canbus.car._443.speech.SysToSpeechReceiver;
import com.hzbhd.canbus.car._443.util.CycleRequest;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.config.constant.ClassName;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.sourcemanager.SourceManager;
import com.hzbhd.util.LogUtil;
import java.text.DecimalFormat;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static SysToSpeechReceiver.AcCtrl sa;
    private static SysToSpeechReceiver.CarCtrl sc;
    private static SysToSpeechReceiver sysToSpeechReceiver;
    private String airJsonStr;
    private SparseArray<String> beepVoice;
    int[] mAirData;
    int[] mCanBusInfoInt;
    Context mContext;
    int[] mLightData;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private SparseArray<String> speechVoice;
    private VoiceManger voiceManger;
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int nowRadarData11 = 0;
    private int nowRadarData12 = 0;
    private final String PATH_VOICE = "voice/";
    private boolean handsTag = false;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        updateVersionInfo(context, "CANBOX-V1.0.0");
        Settings.System.putInt(context.getContentResolver(), ShareConstants.SHARE_OUT_OF_MISC_TURN_LEFT_RIGHT_TAG, 0);
        cycleNotifyVersionName(context);
    }

    private void cycleNotifyVersionName(Context context) {
        int i;
        SystemInfo systemInfo = new SystemInfo();
        int year = systemInfo.getYear();
        int dayOfYear = systemInfo.getDayOfYear();
        if (2023 == year) {
            if (dayOfYear < 60) {
                return;
            } else {
                i = dayOfYear - 60;
            }
        } else if (year <= 2023) {
            return;
        } else {
            i = dayOfYear + 305 + ((year - 2024) * HotKeyConstant.K_AIR_AUTO);
        }
        final byte[] bArr = {22, 24, -1, -94, 40, -22, 34, (byte) systemInfo.get_MMI_SWYear(), (byte) systemInfo.get_MMI_Swweek(), (byte) ((i / NfDef.STATE_CALL_IS_ACTIVE) + 1), (byte) (i % NfDef.STATE_CALL_IS_ACTIVE), 0, 0, 1};
        CycleRequest.getInstance().start(0, new ActionCallback() { // from class: com.hzbhd.canbus.car._443.MsgMgr.1
            @Override // com.hzbhd.canbus.car_cus._451.Interface.ActionCallback
            public void toDo(Object obj) {
                CanbusMsgSender.sendMsg(bArr);
                CycleRequest.getInstance().reset(1000);
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void medianCalibration(Context context, byte[] bArr) {
        super.medianCalibration(context, bArr);
        String meaningStr = getMeaningStr(getByteArrayToIntArray(bArr));
        String str = "";
        for (int i = 2; i < bArr.length; i++) {
            String strSubstring = Integer.toHexString((bArr[i] & 255) | InputDeviceCompat.SOURCE_ANY).substring(6);
            if (strSubstring.length() == 1) {
                str = str + "    0" + strSubstring;
            } else {
                str = str + "    " + strSubstring;
            }
        }
        Log.d("medianCalibration", str);
        CanbusInfoChangeListener.getInstance().reportMsBasicInfo("medianCalibration:" + str + "  " + meaningStr);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getMeaningStr(int[] r17) {
        /*
            Method dump skipped, instructions count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._443.MsgMgr.getMeaningStr(int[]):java.lang.String");
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mContext = context;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        if (LogUtil.log5()) {
            LogUtil.d("canbusInfoChange(): " + getMsDataType(this.mCanBusInfoInt));
        }
        if (getMsDataType(this.mCanBusInfoInt) == 419393561) {
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
            return;
        }
        if (getMsDataType(this.mCanBusInfoInt) == 218053139) {
            if (isTrackInfoChange(bArr)) {
                return;
            }
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
            DataHandleUtils.getMsbLsbResult_4bit(intFromByteWithBit2, intFromByteWithBit);
            int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4);
            int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 4);
            DataHandleUtils.getMsbLsbResult_4bit(intFromByteWithBit4, intFromByteWithBit3);
            int i = intFromByteWithBit & 255;
            int i2 = (i | (((intFromByteWithBit2 & 255) << 4) | (((intFromByteWithBit3 & 255) << 8) | ((intFromByteWithBit4 & 255) << 12)))) - 1040;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) DataHandleUtils.getLsb(i2), (byte) DataHandleUtils.getMsb(i2), 0, 930, 16);
            updateParkUi(null, this.mContext);
            return;
        }
        if (getMsDataType(this.mCanBusInfoInt) == 218052363) {
            if (isTrackInfoChange(bArr)) {
                return;
            }
            if (LogUtil.log5()) {
                StringBuilder sbAppend = new StringBuilder().append("canbusInfoChange");
                int[] iArr = this.mCanBusInfoInt;
                LogUtil.d(sbAppend.append(DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) * 0.004032258f).toString());
            }
            int[] iArr2 = this.mCanBusInfoInt;
            int msbLsbResult = (int) ((DataHandleUtils.getMsbLsbResult(iArr2[8], iArr2[7]) * 0.004032258f) - 129.374d);
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) DataHandleUtils.getLsb(msbLsbResult), (byte) DataHandleUtils.getMsb(msbLsbResult), 0, 31, 16);
            updateParkUi(null, this.mContext);
            if (LogUtil.log5()) {
                LogUtil.d("canbusInfoChange-------(): " + GeneralParkData.trackAngle);
                return;
            }
            return;
        }
        if (getMsDataType(this.mCanBusInfoInt) == 218050571 && DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
            updateSpeedInfo((int) (this.mCanBusInfoInt[8] * 0.05625d));
            if (LogUtil.log5()) {
                LogUtil.d("updateSpeedInfo " + getMsDataType(this.mCanBusInfoInt));
            }
        }
        if (getMsDataType(this.mCanBusInfoInt) == 419361843) {
            CanbusInfoChangeListener.getInstance().reportAllCanBusData("havaTirePressure");
            if (LogUtil.log5()) {
                LogUtil.d("havaTirePressure ");
            }
        }
        ShareBasicInfo(this.mCanBusInfoInt);
        if (isLightDataChange()) {
            sendLightInfoToSheech(this.mCanBusInfoInt);
        }
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
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
        sa.sendTemp(bArr[8] / 2);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(bArr[9], 3, 3);
        if (intFromByteWithBit == 0) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.face);
        } else if (intFromByteWithBit == 1) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.facefoot);
        } else if (intFromByteWithBit == 2) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.foot);
        } else if (intFromByteWithBit == 3) {
            sa.sendMode(SRxData.Ac.Mode.ModeEnum.footdefrost);
        }
        if (intFromByteWithBit == 4) {
            sa.sendDefrost(true);
        } else {
            sa.sendDefrost(false);
        }
        sa.sendWind(DataHandleUtils.getIntFromByteWithBit(bArr[7], 5, 3) + 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(bArr[7], 1, 2);
        if (intFromByteWithBit2 == 1 || intFromByteWithBit2 == 3) {
            sa.sendCold(true);
        } else {
            sa.sendCold(false);
        }
        if (intFromByteWithBit2 == 2 || intFromByteWithBit2 == 3) {
            sa.sendWarm(true);
        } else {
            sa.sendWarm(false);
        }
        if (DataHandleUtils.getBoolBit0(bArr[7])) {
            sa.sendAir(true);
        } else {
            sa.sendAir(false);
        }
        if (DataHandleUtils.getIntFromByteWithBit(bArr[7], 4, 1) == 1) {
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
        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcAuto(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void CarClearancelamps(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void CarReadinglamp(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void CarToplight(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum modeEnum) {
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void CarWiperMove(int i) {
        }

        private SanYiVoiceControl() {
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcAir(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 8, 0, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 4, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcCold(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 16, 0, 0, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 8, 0, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcWarm(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 64, 0, 0, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 32, 0, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcLoop(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 64, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcDefrost(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, ByteCompanionObject.MIN_VALUE, 2, 0, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 3, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcTempTo(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, (byte) (i * 2), 0, 0, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcTempInc(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 4, 0, 0, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcTempDec(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 2, 0, 0, 0, 0, 0, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcWind(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 1, 0, 0, 0, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, -16, 0, 0, 0, 0, 0, 0, 0, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcWindTo(int i) {
            if (i > 6) {
                i = 6;
            }
            if (i < 1) {
                i = 1;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 0, (byte) i, 0, 0, 0, 0, 1});
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void AcMode(STxData.AcCtrl.Mode.ModeEnum modeEnum) {
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.face)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, ByteCompanionObject.MIN_VALUE, 0, 0, 0, 0, 0, 0, 1});
            }
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.facefoot)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 1, 0, 0, 0, 0, 0, 1});
            }
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.foot)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, ByteCompanionObject.MIN_VALUE, 1, 0, 0, 0, 0, 0, 1});
            }
            if (modeEnum.equals(STxData.AcCtrl.Mode.ModeEnum.footdefrost)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, 25, 40, 112, 0, 2, 0, 0, 0, 0, 0, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
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

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
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

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
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

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void CarWindowClose() {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
        }

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
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

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
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

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
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

        @Override // com.hzbhd.canbus.car._443.speech.ISysRxListener
        public void SystemSync() {
            MsgMgr.this.handsTag = true;
        }
    }

    private boolean isTrackInfoChange(byte[] bArr) {
        if (Arrays.equals(this.mTrackData, bArr)) {
            return true;
        }
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void ShareBasicInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportMsBasicInfo(intArrayToJsonStr(iArr));
    }

    private void SharedAirData(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportMsAirInfo(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) {
        this.airJsonStr = "{";
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + "}";
            } else {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + ",";
            }
        }
        return this.airJsonStr;
    }

    private void requestVoiceSource() {
        SourceManager.getInstance().notifyAppAudio(SourceConstantsDef.SOURCE_ID.VOICE, "com.hzbhd.canbus.car._434.impl", 3, true);
    }
}
