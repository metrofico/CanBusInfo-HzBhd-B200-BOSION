package com.hzbhd.canbus.car._159;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private int mDriveDataNow;
    private int mEachId;
    private boolean mIsAirFirst = true;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int[] mVersionInfoNow;
    private int mWheelKeyNow;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 114) {
            setBaseInfo0x72();
        } else if (i == 115) {
            setAirData0x73();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setBaseInfo0x72() {
        if (isDriveDataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " km/h"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        }
        if (isWheelKeyChange()) {
            int i = this.mCanBusInfoInt[4];
            if (i == 0) {
                wheelKeyClick(0);
                return;
            }
            if (i == 1) {
                wheelKeyClick(7);
                return;
            }
            if (i == 2) {
                wheelKeyClick(8);
                return;
            }
            if (i == 3) {
                wheelKeyClick(3);
                return;
            }
            if (i == 5) {
                wheelKeyClick(14);
                return;
            }
            if (i == 6) {
                wheelKeyClick(15);
                return;
            }
            if (i != 32) {
                switch (i) {
                    case 8:
                        wheelKeyClick(45);
                        break;
                    case 9:
                        wheelKeyClick(46);
                        break;
                    case 10:
                        wheelKeyClick(2);
                        break;
                }
                return;
            }
            updateAirActivity(this.mContext, 1001);
            playBeep();
        }
    }

    private void setAirData0x73() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.mono = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        updateAirActivity(this.mContext, 1001);
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -46, (byte) 0));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            str4 = str2 + "KHZ    ";
            while (str4.length() < 12) {
                str4 = " " + str4;
            }
        } else {
            str4 = (str2.length() != 5 ? "" : " ") + str2 + "MHZ   ";
        }
        Log.i("ljq", "radioInfoChange: " + str4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, allBandTypeData}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int i8 = i6 == 1 ? 7 : 6;
        if (i6 == 1) {
            i3 = i4;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) i8}, ("TRA   " + new DecimalFormat("000").format(DataHandleUtils.rangeNumber(i3, 0, 999)) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -46, (byte) 8));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -46, (byte) 20));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        byte[] bArr2 = {22, -46, (byte) (this.mDifferent == 1 ? 23 : 10)};
        String str = new String(bArr);
        while (str.length() < 12) {
            str = str + " ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, str.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        byte[] bArr2 = {22, -46, (byte) (this.mDifferent == 1 ? 23 : 10)};
        String str = new String(bArr);
        while (str.length() < 12) {
            str = str + " ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, str.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        byte[] bArr2 = {22, -46, (byte) (this.mDifferent == 1 ? 23 : 10)};
        String str = new String(bArr);
        while (str.length() < 12) {
            str = str + " ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, str.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        byte[] bArr2 = {22, -46, (byte) (this.mDifferent == 1 ? 23 : 10)};
        String str = " ";
        while (str.length() < 12) {
            str = str + " ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, str.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        Log.i("ljq", "btPhoneStatusInfoChange: " + i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, ("TRA   " + new DecimalFormat("000").format(DataHandleUtils.rangeNumber((b7 * 256) + i, 0, 999)) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, ("TRA   " + new DecimalFormat("000").format(DataHandleUtils.rangeNumber((b6 * 256) + i, 0, 999)) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 21}, (i + decimalFormat.format(i3) + decimalFormat.format(i4) + decimalFormat.format(i8) + decimalFormat.format(i6)).getBytes()));
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._159.MsgMgr.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.sendAfterHangUpMsg(msgMgr.mContext);
                MsgMgr.this.stopTimer();
            }
        };
        startTimer(1000L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 22}, (new DecimalFormat("00").format(i) + "          ").getBytes()));
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._159.MsgMgr.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.sendAfterHangUpMsg(msgMgr.mContext);
                MsgMgr.this.stopTimer();
            }
        };
        startTimer(1000L);
    }

    private void wheelKeyClick(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveAirTemp(int i) {
        return i == 0 ? "" : i == 1 ? "LO" : i == 255 ? "HI" : ((i * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return b4;
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
                return b3;
            default:
                return (byte) 0;
        }
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mVersionInfoNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDriveDataChange() {
        int i = this.mDriveDataNow;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.mDriveDataNow = i2;
        return true;
    }

    private boolean isWheelKeyChange() {
        int i = this.mWheelKeyNow;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return false;
        }
        this.mWheelKeyNow = i2;
        return true;
    }

    private void startTimer(long j) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTimer() {
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
