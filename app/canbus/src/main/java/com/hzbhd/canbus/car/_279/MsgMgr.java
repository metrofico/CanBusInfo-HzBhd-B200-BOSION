package com.hzbhd.canbus.car._279;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private boolean mFrontStatus;
    private boolean mIsDoorFirst = true;
    private boolean mLeftFrontNow;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearNow;
    private boolean mLeftRearStatus;
    private byte[] mOutDoorTempDataNow;
    private boolean mRightFrontNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearNow;
    private boolean mRightRearStatus;
    private String mTempertureUnit;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private byte[] mVersionInfoNow;

    private String getConsumptionUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : "km/l" : "mpg" : "l/100km";
    }

    private String getSpeedUnit(int i) {
        return i != 0 ? i != 1 ? "" : "mpg" : "km/h";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferent = getCurrentCanDifferentId();
        this.mTempertureUnit = getTempUnitC(context);
        initAmplifier(context);
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._279.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -56, 0, (byte) GeneralAmplifierData.volume}, new byte[]{22, -56, 1, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -56, 2, (byte) GeneralAmplifierData.bandTreble}, new byte[]{22, -56, 3, (byte) (GeneralAmplifierData.frontRear + 10)}, new byte[]{22, -56, 4, (byte) (GeneralAmplifierData.leftRight + 10)}}).iterator();

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
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
        try {
            int i = byteArrayToIntArray[1];
            if (i == 32) {
                setWheelKey0x20();
            } else if (i == 36) {
                setDoorData0x24();
            } else if (i == 48) {
                setVersionInfo0x30();
            } else if (i != 53) {
                switch (i) {
                    case 55:
                        setFuelDataChange0x37();
                        break;
                    case 56:
                        setcentralControlData0x38();
                        break;
                    case 57:
                        setAmplifier0x39(context);
                        break;
                    case 58:
                        setOriginalSourceInfo0x3A();
                        break;
                    case 59:
                        setCdcData0x3B();
                        break;
                    case 60:
                        setOriginalTimeData0x3C();
                        break;
                }
            } else {
                setDashboardData0x35();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setWheelKey0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                wheelKeyClick(0);
                break;
            case 1:
                wheelKeyClick(7);
                break;
            case 2:
                wheelKeyClick(8);
                break;
            case 3:
                wheelKeyClick(14);
                break;
            case 4:
                wheelKeyClick(3);
                break;
            case 5:
                wheelKeyClick(46);
                break;
            case 6:
                wheelKeyClick(45);
                break;
            case 7:
                wheelKeyClick(2);
                break;
            case 8:
                wheelKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                wheelKeyClick(15);
                break;
        }
    }

    private void setDoorData0x24() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mRightFrontNow = boolBit7;
            GeneralDoorData.isRightFrontDoorOpen = boolBit7;
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            this.mLeftFrontNow = boolBit6;
            GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
            boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            this.mRightRearNow = boolBit5;
            GeneralDoorData.isRightRearDoorOpen = boolBit5;
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mLeftRearNow = boolBit4;
            GeneralDoorData.isLeftRearDoorOpen = boolBit4;
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            if (isDoorDataChange()) {
                updateDoorView(this.mContext);
            }
        }
    }

    private void setVersionInfo0x30() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setFuelDataChange0x37() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] * 256) + iArr[3];
        arrayList.add(new DriverUpdateEntity(0, 0, i == 65535 ? "---" : (i / 10.0f) + " " + getConsumptionUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
        int[] iArr2 = this.mCanBusInfoInt;
        int i2 = (iArr2[4] * 256) + iArr2[5];
        arrayList.add(new DriverUpdateEntity(0, 1, i2 == 65535 ? "---" : (i2 / 10.0f) + " " + getConsumptionUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
        int[] iArr3 = this.mCanBusInfoInt;
        int i3 = (iArr3[6] * 256) + iArr3[7];
        String str = i3 != 65535 ? (i3 / 10.0f) + " " + getSpeedUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)) : "---";
        Log.i("ljq", "setFuelDataChange0x37: " + str);
        arrayList.add(new DriverUpdateEntity(0, 2, str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setcentralControlData0x38() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
        int[] iArr = this.mCanBusInfoInt;
        SettingUpdateEntity settingUpdateEntity = new SettingUpdateEntity(0, 0, Integer.valueOf((iArr[2] * 256) + iArr[3]));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(settingUpdateEntity.setProgress(((iArr2[2] * 256) + iArr2[3]) - 6));
        this.mTempertureUnit = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]) ? getTempUnitF(this.mContext) : getTempUnitC(this.mContext);
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(0, 6, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]) ? "12h" : "24h").setValueStr(true));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(this.mCanBusInfoInt[9])));
        int[] iArr3 = this.mCanBusInfoInt;
        SettingUpdateEntity settingUpdateEntity2 = new SettingUpdateEntity(0, 8, Integer.valueOf((iArr3[5] * 256) + iArr3[6]));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(settingUpdateEntity2.setProgress((iArr4[5] * 256) + iArr4[6]));
        arrayList.add(new SettingUpdateEntity(0, 9, new DecimalFormat("00").format(this.mCanBusInfoInt[7]) + ":" + new DecimalFormat("00").format(this.mCanBusInfoInt[8])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAmplifier0x39(Context context) {
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[3] - 10;
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 10;
        updateAmplifierActivity(null);
        saveAmplifierData(context, getCanId());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(this.mCanBusInfoInt[5])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setOriginalSourceInfo0x3A() {
        GeneralOriginalCarDeviceData.cdStatus = "Line " + this.mCanBusInfoInt[2];
        GeneralOriginalCarDeviceData.discStatus = new String(Arrays.copyOfRange(this.mCanBusInfoByte, 3, r1.length - 1));
        updateOriginalCarDeviceActivity(null);
    }

    private void setDashboardData0x35() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = this.mCanBusInfoByte;
        int i = bArr[2] | (bArr[8] << 8);
        Log.i("ljq", "setDashboardData0x35: data: " + i);
        arrayList.add(new DriverUpdateEntity(1, 0, i + " " + (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]) ? getTempUnitF(this.mContext) : getTempUnitC(this.mContext))));
        arrayList.add(new DriverUpdateEntity(1, 1, this.mCanBusInfoInt[3] + " " + (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]) ? "mil" : "km") + "/h"));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb.append(getDriveData(new int[]{iArr[5], iArr[4]})).append(" ").append(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]) ? "mil" : "km").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, sb2.append(getDriveData(new int[]{iArr2[7], iArr2[6]})).append(" rpm").toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, sb3.append(getDriveData(new int[]{iArr3[11], iArr3[10], iArr3[9]})).append(" ").append(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]) ? "mil" : "km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[3]);
    }

    private void setCdcData0x3B() {
        GeneralOriginalCarDeviceData.runningState = getPlayState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4));
        GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (true) {
            boolean z = false;
            if (i > 6) {
                break;
            }
            SongListEntity songListEntity = new SongListEntity(CommUtil.getStrByResId(this.mContext, "_279_disc") + " " + i);
            if (this.mCanBusInfoInt[3] == i) {
                z = true;
            }
            arrayList.add(songListEntity.setSelected(z));
            i++;
        }
        GeneralOriginalCarDeviceData.songList = arrayList;
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sbAppend = new StringBuilder().append(": ");
        int i2 = this.mCanBusInfoInt[3];
        String string = sbAppend.append(i2 == 255 ? "---" : Integer.toString(i2)).toString();
        StringBuilder sbAppend2 = new StringBuilder().append(": ");
        int i3 = this.mCanBusInfoInt[4];
        String string2 = sbAppend2.append(i3 != 255 ? Integer.toString(i3) : "---").toString();
        arrayList2.add(new OriginalCarDeviceUpdateEntity(0, string));
        arrayList2.add(new OriginalCarDeviceUpdateEntity(1, string2));
        GeneralOriginalCarDeviceData.mList = arrayList2;
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalTimeData0x3C() {
        if (this.mCanBusInfoInt[2] == 2) {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0, 0, 0, 0});
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
        int i3 = str.contains("AM") ? 16 : 0;
        float fFloatValue = Float.valueOf(str2).floatValue();
        if (str.contains("FM")) {
            fFloatValue *= 100.0f;
        }
        int i4 = (int) fFloatValue;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, (byte) i3, (byte) ((i4 >> 8) & 255), (byte) (i4 & 255), (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, (byte) DataHandleUtils.rangeNumber(i3, 0, 255), 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, (byte) DataHandleUtils.rangeNumber((b7 * 256) + i, 0, 255), 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, (byte) DataHandleUtils.rangeNumber((b6 * 256) + i, 0, 255), 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte) i2, (byte) i3, (byte) i4, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte) i8, (byte) i6, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte) (!z ? 1 : 0), 0, 0, 0, 0});
    }

    private void wheelKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontNow && this.mRightFrontStatus == this.mRightFrontNow && this.mLeftRearStatus == this.mLeftRearNow && this.mRightRearStatus == this.mRightRearNow && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontNow;
        this.mRightFrontStatus = this.mRightFrontNow;
        this.mLeftRearStatus = this.mLeftRearNow;
        this.mRightRearStatus = this.mRightRearNow;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private String getDriveData(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return "";
        }
        int iPow = iArr[0];
        for (int i = 1; i < iArr.length; i++) {
            iPow = (int) (iPow + (iArr[i] * Math.pow(256.0d, i)));
        }
        int i2 = 1;
        for (int i3 = 0; i3 < (iArr.length * 8) - 1; i3++) {
            i2 = (i2 << 1) + 1;
        }
        return iPow == i2 ? "---" : Integer.toString(iPow);
    }

    private String getPlayState(int i) {
        if (i == 0) {
            return CommUtil.getStrByResId(this.mContext, "nissan_infor_s");
        }
        if (i == 1) {
            return CommUtil.getStrByResId(this.mContext, "device_run_status_4");
        }
        if (i == 2) {
            return CommUtil.getStrByResId(this.mContext, "device_run_status_5");
        }
        if (i != 3) {
            return i != 4 ? "" : CommUtil.getStrByResId(this.mContext, "_123_divice_status_3");
        }
        return CommUtil.getStrByResId(this.mContext, "device_run_status_1");
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

    private void startTimer(long j, long j2) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j, j2);
    }

    private void stopTimer() {
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

    private boolean isOutDoorTempChange() {
        if (Arrays.equals(this.mOutDoorTempDataNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mOutDoorTempDataNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private String resolveOutDoorTem() {
        int length = this.mCanBusInfoByte.length - 3;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = this.mCanBusInfoByte[i + 3];
        }
        return new String(bArr) + getTempUnitC(this.mContext);
    }
}
