package com.hzbhd.canbus.car._4;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.ui.util.BaseUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static int bandBassTag = 1;
    public static int bandMiddleTag = 1;
    public static int bandTrebleTag = 1;
    public static int frontRearTag = 1;
    public static int leftRightTag = 1;
    int[] OutGoingPhoneNumber;
    int alarmInfo1;
    int alarmInfo2;
    int carDoorInt;
    int[] comingPhoneNumber;
    DecimalFormat df = new DecimalFormat("#0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    int differentId;
    int[] mAirData;
    int[] mBackLightInt;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mLeftRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mRightRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    int[] now0x41;
    private int[] talkingPhoneNumber;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUigMgr(this.mContext).makeConnection();
        initAmplifier(context);
        resetImplVolume();
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), getUigMgr(context).getAmplifierPageUiSet(context));
        }
        final byte[][] bArr = {new byte[]{22, -96, 1, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -96, 2, (byte) GeneralAmplifierData.frontRear}, new byte[]{22, -96, 3, (byte) (GeneralAmplifierData.bandBass - 9)}, new byte[]{22, -96, 3, (byte) (GeneralAmplifierData.bandMiddle - 9)}, new byte[]{22, -96, 3, (byte) (GeneralAmplifierData.bandTreble - 9)}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._4.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        initAmplifier(BaseUtil.INSTANCE.getContext());
        resetImplVolume();
    }

    private void resetImplVolume() {
        BaseUtil.INSTANCE.runBack(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._4.MsgMgr.2
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() throws InterruptedException {
                int i = GeneralAmplifierData.volume;
                if (i == 0) {
                    i = 15;
                }
                for (int i2 = 0; i2 <= i; i2++) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -96, 0, (byte) i2});
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
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
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14BackLightInfo();
            return;
        }
        if (i == 22) {
            set0x16SpeedInfo();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo();
            return;
        }
        if (i == 65) {
            set0x41CarInfo();
            return;
        }
        if (i == 50) {
            set0x32LeftRadarInfo();
            return;
        }
        if (i != 51) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    set0x21AirInfo();
                    break;
                case 34:
                    set0x22RearRadarInfo();
                    break;
                case 35:
                    set0x23FrontRadarInfo();
                    break;
                case 36:
                    set0x24BaseInfo();
                    break;
                case 37:
                    set0x25ParkingInfo();
                    break;
                case 38:
                    set0x26EspInfo();
                    break;
                case 39:
                    set0x27AmplifierInfo(context);
                    break;
                case 40:
                    set0x28CherInfo();
                    break;
            }
            return;
        }
        set0x33RightRadarInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        getUigMgr(this.mContext).sendSourceInfo(1, 1);
        getUigMgr(this.mContext).sendIconInfo(0, 1);
        getUigMgr(this.mContext).sendRadioInfo(getBandAmFM(str), getFreqLsb(str, str2), getFreqMsb(str, str2), i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        getUigMgr(this.mContext).sendSourceInfo(0, 0);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUigMgr(this.mContext).sendSourceInfo(2, 33);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(i4, i5, 0, 0, i / 60, i % 60);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUigMgr(this.mContext).sendSourceInfo(8, 17);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(getLsb(i2), getMsb(i2), getLsb(i), getMsb(i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        getUigMgr(this.mContext).sendSourceInfo(8, 17);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(getLsb(i2), getMsb(i2), getLsb(i), getMsb(i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        getUigMgr(this.mContext).sendSourceInfo(0, 0);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUigMgr(this.mContext).sendSourceInfo(7, 48);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUigMgr(this.mContext).sendSourceInfo(11, 34);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        getUigMgr(this.mContext).sendSourceInfo(0, 0);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("来电");
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.comingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传 来电");
        this.comingPhoneNumber = byteArrayToIntArray;
        getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("拨号");
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            MyLog.e("号码" + i2, ((int) bArr[i]) + "");
            i = i2;
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.OutGoingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传 拨号");
        this.OutGoingPhoneNumber = byteArrayToIntArray;
        getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 2, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        getUigMgr(this.mContext).sendSourceInfo(5, 64);
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        MyLog.temporaryTracking("通话中");
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.talkingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传递 通话中");
        this.talkingPhoneNumber = byteArrayToIntArray;
        getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 4, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("挂断");
        this.comingPhoneNumber = null;
        this.OutGoingPhoneNumber = null;
        this.talkingPhoneNumber = null;
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._4.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(2000L, 500L) { // from class: com.hzbhd.canbus.car._4.MsgMgr.3.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        MyLog.temporaryTracking("挂断中");
                        MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneNumber();
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        MyLog.temporaryTracking("挂断完成");
                        MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneNumber();
                    }
                }.start();
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        getUigMgr(this.mContext).sendVolInfo(i, z);
    }

    private void set0x41CarInfo() {
        if (Arrays.equals(this.now0x41, this.mCanBusInfoInt)) {
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.now0x41 = iArr;
        int i = iArr[2];
        if (i == 1) {
            set0x41CarInfo0x01Data();
        } else if (i == 2) {
            set0x41CarInfo0x02Data();
        } else {
            if (i != 3) {
                return;
            }
            set0x41CarInfo0x03Data();
        }
    }

    private void set0x41CarInfo0x01Data() {
        if (isCarDoorInfoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralDoorData.isShowWashingFluidWarning = true;
            GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralDoorData.isShowHandBrake = true;
            GeneralDoorData.isHandBrakeUp = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x41CarInfo0x03Data() {
        boolean z;
        int i = 0;
        String str = "";
        if (isAlarm1InfoChange() && DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            str = "【" + this.mContext.getString(R.string._246_car_info20) + "1：" + this.mContext.getString(R.string._246_car_info18) + "】";
            z = true;
            i = 1;
        } else {
            z = false;
        }
        if (isAlarm2InfoChange() && DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            str = str + "【" + this.mContext.getString(R.string._246_car_info20) + (i + 1) + "：" + this.mContext.getString(R.string._246_car_info19) + "】";
            z = true;
        }
        if (z) {
            showDialog(str);
        }
    }

    private void set0x41CarInfo0x02Data() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append((iArr[3] * 256) + iArr[4]).append(" RPM").toString()));
        int drivingPageIndexes2 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes2 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(((iArr2[5] * 256) + iArr2[6]) * 0.01d).append(" Km/h").toString()));
        int drivingPageIndexes3 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes3 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info15");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(((iArr3[7] * 256) + iArr3[8]) * 0.01d).append(" V").toString()));
        int[] iArr4 = this.mCanBusInfoInt;
        if (((iArr4[9] * 256) + iArr4[10]) * 0.1d > 6000.0d) {
            Context context = this.mContext;
            StringBuilder sb4 = new StringBuilder();
            DecimalFormat decimalFormat = this.df;
            int[] iArr5 = this.mCanBusInfoInt;
            updateOutDoorTemp(context, sb4.append(decimalFormat.format((((iArr5[9] * 256) + iArr5[10]) * 0.1d) - 6554.0d)).append(getTempUnitC(this.mContext)).toString());
        } else {
            Context context2 = this.mContext;
            StringBuilder sb5 = new StringBuilder();
            DecimalFormat decimalFormat2 = this.df;
            int[] iArr6 = this.mCanBusInfoInt;
            updateOutDoorTemp(context2, sb5.append(decimalFormat2.format(((iArr6[9] * 256) + iArr6[10]) * 0.1d)).append(getTempUnitC(this.mContext)).toString());
        }
        int drivingPageIndexes4 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes4 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
        StringBuilder sb6 = new StringBuilder();
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb6.append((iArr7[11] * 65536) + (iArr7[12] * 256) + iArr7[13]).append(" Km").toString()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info17"), this.mCanBusInfoInt[14] + " L"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x33RightRadarInfo() {
        if (isRightRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRightRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x32LeftRadarInfo() {
        if (isLeftRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x28CherInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1004_chair_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1004_chair_info", "_1004_chair_info1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 3))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1004_chair_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1004_chair_info", "_1004_chair_info2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x27AmplifierInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1004_amp"), 0, Integer.valueOf(this.mCanBusInfoInt[9])).setProgress(this.mCanBusInfoInt[9]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
        if (leftRightTag == -1) {
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] + InputDeviceCompat.SOURCE_ANY;
        } else {
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4];
        }
        if (frontRearTag == -1) {
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[5] + InputDeviceCompat.SOURCE_ANY;
        } else {
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[5];
        }
        if (bandBassTag == -1) {
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6] - 247;
        } else {
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6] + 9;
        }
        if (bandMiddleTag == -1) {
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] - 247;
        } else {
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] + 9;
        }
        if (bandTrebleTag == -1) {
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8] - 247;
        } else {
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8] + 9;
        }
        saveAmplifierData(context, getCanId());
        updateAmplifierActivity(new Bundle());
    }

    private void set0x26EspInfo() {
        if (isTrackInfoChange()) {
            return;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x25ParkingInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 0, getSwitchState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        updatePKeyRadar();
    }

    private void set0x24BaseInfo() {
        if (isBasicInfoChange()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        adjustBrightness();
    }

    private void set0x23FrontRadarInfo() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x22RearRadarInfo() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x21AirInfo() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 4);
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 0.5d, 17.5d, "C", 0, 31);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 17.5d, "C", 0, 31);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x20WheelKeyInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                if (getCurrentEachCanId() == 1) {
                    buttonKey(45);
                    break;
                } else {
                    buttonKey(46);
                    break;
                }
            case 4:
                if (getCurrentEachCanId() == 1) {
                    buttonKey(46);
                    break;
                } else {
                    buttonKey(45);
                    break;
                }
            case 5:
                buttonKey(14);
                break;
            case 6:
                buttonKey(3);
                break;
            case 7:
                buttonKey(2);
                break;
            case 8:
                buttonKey(136);
                break;
            case 9:
                buttonKey(14);
                break;
            case 10:
                buttonKey(15);
                break;
            case 11:
                buttonKey(135);
                break;
        }
    }

    private void set0x16SpeedInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info2");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[3], iArr[2]) / 16).append("km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        DecimalFormat decimalFormat = this.df_2Integer;
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(Integer.parseInt(decimalFormat.format(((iArr2[5] * 256) + iArr2[6]) * 0.01d)));
    }

    private void set0x14BackLightInfo() {
        if (isBackLightIntChange()) {
            return;
        }
        adjustBrightness();
    }

    private String getSwitchState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info7);
        }
        return this.mContext.getString(R.string._246_car_info6);
    }

    private String getReversingState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info8);
        }
        return this.mContext.getString(R.string._246_car_info9);
    }

    private String getP_GearState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info12);
        }
        return this.mContext.getString(R.string._246_car_info11);
    }

    private int getBandAmFM(String str) {
        str.hashCode();
        switch (str) {
            case "AM":
                return 16;
            case "AM1":
                return 17;
            case "AM2":
                return 18;
            case "AM3":
                return 19;
            case "FM1":
                return 1;
            case "FM2":
                return 2;
            case "FM3":
                return 3;
            default:
                return 0;
        }
    }

    private int getFreqMsb(String str, String str2) {
        if (getBandAmFM(str) == 0 || getBandAmFM(str) == 1 || getBandAmFM(str) == 2 || getBandAmFM(str) == 3) {
            return getMsb((int) (Double.parseDouble(str2) * 100.0d));
        }
        return getMsb((int) Double.parseDouble(str2));
    }

    private int getFreqLsb(String str, String str2) {
        if (getBandAmFM(str) == 0 || getBandAmFM(str) == 1 || getBandAmFM(str) == 2 || getBandAmFM(str) == 3) {
            return getLsb((int) (Double.parseDouble(str2) * 100.0d));
        }
        return getLsb((int) Double.parseDouble(str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
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

    private void adjustBrightness() {
        int i = this.mCanBusInfoInt[2];
        if (i <= 50) {
            setBacklightLevel(5);
            return;
        }
        if (i > 50 && i <= 100) {
            setBacklightLevel(4);
            return;
        }
        if (i > 100 && i <= 150) {
            setBacklightLevel(3);
            return;
        }
        if (i > 150 && i <= 200) {
            setBacklightLevel(2);
        } else if (i > 200) {
            setBacklightLevel(1);
        }
    }

    private void showDialog(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._4.MsgMgr.4
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new AlertView().showDialog(MsgMgr.this.getActivity(), str);
            }
        });
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        int length = bArr.length + bArr2.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        MyLog.e("长度", length + "");
        for (int i = 0; i < length; i++) {
            MyLog.e("内容" + i, ((int) bArr3[i]) + "");
        }
        return bArr3;
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isRightRadarDataChange() {
        if (Arrays.equals(this.mRightRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRightRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isLeftRadarDataChange() {
        if (Arrays.equals(this.mLeftRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mLeftRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return true;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBackLightIntChange() {
        if (Arrays.equals(this.mBackLightInt, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mBackLightInt = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isCarDoorInfoChange() {
        int i = this.carDoorInt;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.carDoorInt = i2;
        return true;
    }

    private boolean isAlarm1InfoChange() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
        if (this.alarmInfo1 == intFromByteWithBit) {
            return false;
        }
        this.alarmInfo1 = intFromByteWithBit;
        return true;
    }

    private boolean isAlarm2InfoChange() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
        if (this.alarmInfo2 == intFromByteWithBit) {
            return false;
        }
        this.alarmInfo2 = intFromByteWithBit;
        return true;
    }
}
