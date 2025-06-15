package com.hzbhd.canbus.car._122;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_122_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
    static final String SHARE_122_AMPLIFIER_BASS = "share_122_amplifier_bass";
    static final String SHARE_122_AMPLIFIER_FADE = "share_122_amplifier_fade";
    static final String SHARE_122_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
    static final String SHARE_122_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
    static final String SHARE_122_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
    static final int _122_AMPLIFIER_OFFSET = 1;
    static final int _122_AMPLIFIER_RANGE = 9;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCarSpeedData;
    private Context mContext;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 18) {
            setDoorData0x12();
            return;
        }
        if (i == 50) {
            setEnineSpeed();
            return;
        }
        if (i == 114) {
            realKeyControl0x72();
            int i2 = this.mCanBusInfoInt[3];
            if (i2 == 0) {
                updateSpeedInfo(i2);
                return;
            }
            return;
        }
        if (i == 166) {
            setAmplifierData0xA6();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setEnineSpeed() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[4] * 256) + iArr[5]).append(" Rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDoorData0x12() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void realKeyLongClick2(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private void realKeyControl0x72() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyLongClick2(0);
                break;
            case 1:
                realKeyLongClick2(7);
                break;
            case 2:
                realKeyLongClick2(8);
                break;
            case 3:
                realKeyLongClick2(3);
                break;
            case 5:
                realKeyLongClick2(14);
                break;
            case 6:
                realKeyLongClick2(15);
                break;
            case 7:
                realKeyLongClick2(HotKeyConstant.K_SPEECH);
                break;
            case 8:
                realKeyLongClick2(46);
                break;
            case 9:
                realKeyLongClick2(45);
                break;
            case 11:
                realKeyLongClick2(2);
                break;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[7], bArr[6], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAmplifierData0xA6() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.leftRight = (this.mCanBusInfoByte[3] - 1) - 9;
        GeneralAmplifierData.frontRear = (this.mCanBusInfoByte[4] - 1) - 9;
        GeneralAmplifierData.bandBass = this.mCanBusInfoByte[5] - 1;
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoByte[6] - 1;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[7] - 1;
        updateAmplifierActivity(null);
        saveAmplifierData();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String strSubstring;
        super.radioInfoChange(i, str, str2, str3, i2);
        int radioBandData = getRadioBandData(str);
        if (str.contains("FM")) {
            strSubstring = str2.substring(0, str2.length() - 1);
        } else if (!str.contains("AM")) {
            return;
        } else {
            strSubstring = str2 + " ";
        }
        for (int length = strSubstring.length(); length < 8; length++) {
            strSubstring = " " + strSubstring;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) radioBandData}, (strSubstring + "    ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int i8 = i6 == 1 ? 7 : 6;
        if (i6 == 1) {
            i3 = i4;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) i8}, ((new DecimalFormat("000").format(DataHandleUtils.rangeNumber(i3, 0, 999)) + "     ") + "    ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) 8}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) 8}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) 12}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, ((new DecimalFormat("000").format(DataHandleUtils.rangeNumber((b7 * 256) + i, 0, 999)) + "     ") + "    ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, ((new DecimalFormat("000").format(DataHandleUtils.rangeNumber((b6 * 256) + i, 0, 999)) + "     ") + "    ").getBytes()));
    }

    private int getRadioBandData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return 4;
            case "AM2":
                return 5;
            case "FM2":
                return 2;
            case "FM3":
                return 3;
            default:
                return 1;
        }
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, SHARE_122_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_122_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_122_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_122_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_122_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_122_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_122_AMPLIFIER_VOLUME, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_122_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_122_AMPLIFIER_FADE, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_122_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_122_AMPLIFIER_MIDDLE, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_122_AMPLIFIER_TREBLE, 0);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
        final byte[][] bArr = {new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 1 + 9)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 1 + 9)}, new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 1)}, new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 1)}, new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 1)}};
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._122.MsgMgr.1
            int index = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.index;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    MsgMgr.this.finishTimer();
                } else {
                    this.index = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        };
        startTimer(100L, 100);
    }

    private void startTimer(long j, int i) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishTimer() {
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
