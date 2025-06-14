package com.hzbhd.canbus.car._42;

import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private long realtime;
    private String songTitleNow = "";
    private String songAlbumNow = "";
    private String songArtistNow = "";
    private String ONCE_Tag = "once.tag";

    private String getCalibrationStatus(boolean z) {
        return z ? "compass_calibrating" : "compass_calibration_finish";
    }

    private String getDeviceStatus(int i) {
        return i == 1 ? "IPOD" : i == 2 ? "USB" : " ";
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._42.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        new Thread() { // from class: com.hzbhd.canbus.car._42.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(2000L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        initBt(context);
    }

    private void initBt(Context context) {
        if (SharePreUtil.getBoolValue(context, this.ONCE_Tag, true)) {
            SharePreUtil.setBoolValue(context, this.ONCE_Tag, false);
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, 2});
        }
        for (int i = 0; i < 10; i++) {
            CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 2});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
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
            realKeyControl();
            return;
        }
        if (i == 33) {
            setOriginalDevice0x21();
            return;
        }
        if (i == 36) {
            setVehicleDoorInfo0x24();
            return;
        }
        if (i == 41) {
            setTrack0x29();
        } else if (i == 48) {
            setVersionInfo();
        } else {
            if (i != 210) {
                return;
            }
            setCompass0xD2();
        }
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyControl() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(7);
                break;
            case 2:
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(48);
                break;
            case 4:
                realKeyClick(47);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 8:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick(14);
                break;
            case 10:
                realKeyClick(HotKeyConstant.K_PHONE_OFF_RETURN);
                break;
            case 11:
                realKeyClick(39);
                break;
            case 12:
                realKeyClick(40);
                break;
            case 13:
                realKeyClick(41);
                break;
        }
    }

    private void setOriginalDevice0x21() {
        String deviceStatus = getDeviceStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        String runStatus = getRunStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
        if (CommUtil.getStrByResId(this.mContext, "device_run_status_4").equals(runStatus) && runStatus != GeneralOriginalCarDeviceData.runningState) {
            Log.i("ljq", "setOriginalDevice0x21:  enter");
            enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        } else if (CommUtil.getStrByResId(this.mContext, "device_run_status_2").equals(runStatus) && runStatus != GeneralOriginalCarDeviceData.runningState) {
            Log.i("ljq", "setOriginalDevice0x21:  exit");
            enterNoSource();
        }
        DecimalFormat decimalFormat = new DecimalFormat("00");
        GeneralOriginalCarDeviceData.cdStatus = deviceStatus;
        GeneralOriginalCarDeviceData.runningState = runStatus;
        GeneralOriginalCarDeviceData.startTime = decimalFormat.format(this.mCanBusInfoInt[4]) + ":" + decimalFormat.format(this.mCanBusInfoInt[5]);
        GeneralOriginalCarDeviceData.endTime = this.mCanBusInfoInt[11] + "%";
        GeneralOriginalCarDeviceData.progress = this.mCanBusInfoInt[11];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, Integer.toString(this.mCanBusInfoInt[10])));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb.append((iArr[8] * 256) + iArr[9]).append("").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("").toString()));
        if ("IPOD".equals(GeneralOriginalCarDeviceData.cdStatus)) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, ""));
        }
        if (CommUtil.getStrByResId(this.mContext, "device_run_status_0").equals(GeneralOriginalCarDeviceData.runningState) || CommUtil.getStrByResId(this.mContext, "device_run_status_2").equals(GeneralOriginalCarDeviceData.runningState)) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, ""));
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setVehicleDoorInfo0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void setCompass0xD2() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, getCalibrationStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit));
        arrayList.add(new SettingUpdateEntity(0, 2, Float.valueOf((this.mCanBusInfoInt[3] * 3) / 2.0f)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTrack0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5120, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -62, allBandTypeData, this.freqLo, this.freqHi, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        Log.i("_42_MsgMgr", "discInfoChange: mContext = " + this.mContext);
        if (this.mContext == null) {
            return;
        }
        getHour(i);
        int minute = getMinute(i);
        int second = getSecond(i);
        int i8 = (i6 == 1 || i6 == 5) ? i4 : i3;
        if (i7 != 240) {
            if (i7 == 32) {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -64, 2, 16});
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -61, 0, (byte) i8, 0, 0, (byte) minute, (byte) second});
                reportID3InfoSimple(new byte[]{22, -53}, (byte) 2, (byte) 3, (byte) 4, str, str2, str3, false, "unicodeLittle", 53);
                return;
            }
            return;
        }
        if (SystemClock.elapsedRealtime() - this.realtime < 1000) {
            return;
        }
        String string = Settings.System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
        if (!TextUtils.isEmpty(string)) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Base64.decode(string, 0));
        }
        this.realtime = SystemClock.elapsedRealtime();
        reportID3InfoSimple(new byte[]{22, -53}, (byte) 2, (byte) 3, (byte) 4, str, str2, str3, true, "unicodeLittle", 53);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), new byte[]{22, -64, 3, 34});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.addBytes(Util.byteMerger(new byte[]{22, -53, 1}, bArr), (byte) -1), 53));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.addBytes(Util.byteMerger(new byte[]{22, -53, 1}, bArr), (byte) -1), 53));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.addBytes(Util.byteMerger(new byte[]{22, -53, 1}, bArr), (byte) -1), 53));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -64, 7, 48});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte b10 = (byte) (i2 & 255);
        byte b11 = (byte) ((i2 >> 8) & 255);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -64, (byte) (b != 9 ? 8 : 9), 17});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -61, b10, b11, (byte) i, b7, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte b9 = (byte) (i2 & 255);
        byte b10 = (byte) ((i2 >> 8) & 255);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, (byte) (b != 9 ? 8 : 9), 17});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -61, b9, b10, (byte) i, b6, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), new byte[]{22, -64, 10, 34});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -64, 11, 34});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        if (getCurrentCanDifferentId() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, (byte) (!z ? 1 : 0), (byte) i8, (byte) i6});
        }
    }

    private String getRunStatus(int i) {
        return CommUtil.getStrByResId(this.mContext, "device_run_status_" + i);
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return (byte) 16;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            default:
                return (byte) 0;
        }
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.FreqInt = Integer.parseInt(str2);
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
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

    /* JADX WARN: Type inference failed for: r12v0, types: [com.hzbhd.canbus.car._42.MsgMgr$2] */
    private void reportID3InfoSimple(final byte[] bArr, final byte b, final byte b2, final byte b3, final String str, final String str2, final String str3, final boolean z, final String str4, final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._42.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    if (z) {
                        sleep(1000L);
                    }
                    if (str.equals(MsgMgr.this.songTitleNow) && str2.equals(MsgMgr.this.songAlbumNow) && str3.equals(MsgMgr.this.songArtistNow)) {
                        return;
                    }
                    MsgMgr.this.songTitleNow = str;
                    MsgMgr.this.songAlbumNow = str2;
                    MsgMgr.this.songArtistNow = str3;
                    sleep(100L);
                    MsgMgr.this.reportID3InfoSimpleFinal(bArr, b, str, str4, i);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoSimpleFinal(bArr, b2, str2, str4, i);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoSimpleFinal(bArr, b3, str3, str4, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoSimpleFinal(byte[] bArr, byte b, String str, String str2, int i) {
        try {
            CanbusMsgSender.sendMsg(Util.makeBytesFixedLength(Util.byteMerger(Util.addBytes(bArr, b), Util.exceptBOMHead(str.getBytes(str2))), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
