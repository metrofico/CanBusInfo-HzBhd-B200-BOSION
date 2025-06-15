package com.hzbhd.canbus.car._246;

import android.content.Context;
import android.os.CountDownTimer;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int[] OutGoingPhoneNumber;
    int alarmInfo1;
    int alarmInfo2;
    int carDoorInt;
    int[] comingPhoneNumber;
    int differentId;
    int eachId;
    int[] mAirData;
    int[] mBackLightInt;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
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
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUigMgr(this.mContext).makeConnection();
        ((UiMgr) UiMgrFactory.getCanUiMgr(context)).initAmplifierInfo(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        ((UiMgr) UiMgrFactory.getCanUiMgr(this.mContext)).initAmplifierInfo(this.mContext);
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
        if (i == 41) {
            set0x29WheelKeyInfo();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo();
            return;
        }
        if (i != 65) {
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
                    set0x27WheelKeyInfo();
                    break;
            }
            return;
        }
        set0x41CarInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2 || i3 == 9) {
            getUigMgr(this.mContext).sendSourceInfo(1, 1);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 1);
        int i4 = this.eachId;
        if (i4 == 1 || i4 == 2) {
            getUigMgr(this.mContext).sendRadioInfo(getBandAmFM(str), getFreqLsb(str, str2), getFreqMsb(str, str2), i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUigMgr(this.mContext).sendSourceInfo(0, 0);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int i8 = this.eachId;
        if (i8 == 1 || i8 == 2 || i8 == 9) {
            getUigMgr(this.mContext).sendSourceInfo(2, 33);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        int i9 = this.eachId;
        if (i9 == 1 || i9 == 2) {
            getUigMgr(this.mContext).sendMediaPalyInfo(i4, i5, 0, 0, i / 60, i % 60);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = this.eachId;
        if (i4 == 1 || i4 == 2 || i4 == 9) {
            getUigMgr(this.mContext).sendSourceInfo(8, 17);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        MyLog.e("stoagePath", ((int) b) + "");
        MyLog.e("playRatio", ((int) b2) + "");
        MyLog.e("currentPlayingIndexLow", i + "");
        MyLog.e("totalCount", i2 + "");
        MyLog.e("currentPlayingIndexHigh", ((int) b7) + "");
        MyLog.e("currentPos", j + "");
        MyLog.e("playIndex", i3 + "");
        int i5 = this.eachId;
        if (i5 == 1 || i5 == 2) {
            getUigMgr(this.mContext).sendMediaPalyInfo(getLsb(i2), getMsb(i2), getLsb(i), getMsb(i), b4, b5);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUigMgr(this.mContext).sendSourceInfo(0, 0);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUigMgr(this.mContext).sendSourceInfo(7, 48);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUigMgr(this.mContext).sendSourceInfo(11, 34);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUigMgr(this.mContext).sendSourceInfo(0, 0);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
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
        int i = this.eachId;
        if (i == 1 || i == 2) {
            getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 1, 1}, bArr));
        }
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
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2) {
            getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 2, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2 || i2 == 9) {
            getUigMgr(this.mContext).sendSourceInfo(5, 64);
        }
        getUigMgr(this.mContext).sendIconInfo(0, 0);
        MyLog.temporaryTracking("通话中");
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.talkingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传递 通话中");
        this.talkingPhoneNumber = byteArrayToIntArray;
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2) {
            getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 4, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("挂断");
        this.comingPhoneNumber = null;
        this.OutGoingPhoneNumber = null;
        this.talkingPhoneNumber = null;
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._246.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(2000L, 500L) { // from class: com.hzbhd.canbus.car._246.MsgMgr.1.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        MyLog.temporaryTracking("挂断中");
                        if (MsgMgr.this.eachId == 1 || MsgMgr.this.eachId == 2) {
                            MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneNumber();
                        }
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        MyLog.temporaryTracking("挂断完成");
                        if (MsgMgr.this.eachId == 1 || MsgMgr.this.eachId == 2) {
                            MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneNumber();
                        }
                    }
                }.start();
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
    }

    private void set0x41CarInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            set0x41CarInfo0x01Data();
            return;
        }
        if (i == 2) {
            set0x41CarInfo0x02Data();
            return;
        }
        if (i == 3) {
            set0x41CarInfo0x03Data();
        } else if (i == 16) {
            set0x41CarInfo0x10Data();
        } else {
            if (i != 17) {
                return;
            }
            set0x41CarInfo0x11Data();
        }
    }

    private void set0x41CarInfo0x11Data() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info"), 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x41CarInfo0x10Data() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 0, Integer.valueOf(this.mCanBusInfoInt[3] + SystemConstant.THREAD_SLEEP_TIME_2000)));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 1, Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 2, Integer.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 3, Integer.valueOf(this.mCanBusInfoInt[6])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 4, Integer.valueOf(this.mCanBusInfoInt[7])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 5, Integer.valueOf(this.mCanBusInfoInt[8])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_time_info"), 6, (this.mCanBusInfoInt[3] + SystemConstant.THREAD_SLEEP_TIME_2000) + this.mContext.getString(R.string._246_setting_time_info9) + this.mCanBusInfoInt[4] + this.mContext.getString(R.string._246_setting_time_info10) + this.mCanBusInfoInt[5] + this.mContext.getString(R.string._246_setting_time_info11) + this.mCanBusInfoInt[6] + ":" + this.mCanBusInfoInt[7] + ":" + this.mCanBusInfoInt[8]).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x41CarInfo0x01Data() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 5);
        if (isCarDoorInfoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralDoorData.isSubShowSeatBelt = true;
            GeneralDoorData.isSubSeatBeltTie = true ^ DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
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
        Context context = this.mContext;
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        updateOutDoorTemp(context, sb4.append(((iArr4[9] * 256) + iArr4[10]) * 0.1d).append(getTempUnitC(this.mContext)).toString());
        int drivingPageIndexes4 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes4 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
        StringBuilder sb5 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb5.append((iArr5[11] * 65536) + (iArr5[12] * 256) + iArr5[13]).append(" Km").toString()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info17"), this.mCanBusInfoInt[14] + " L"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x29WheelKeyInfo() {
        int i = this.eachId;
        if (i == 1 || i == 8 || i == 9) {
            int[] iArr = this.mCanBusInfoInt;
            if (iArr[2] == 2 && iArr[3] / 6 <= 40) {
                FutureUtil.instance.setVolumeVal(this.mCanBusInfoInt[3] / 6);
            }
        }
    }

    private void set0x27WheelKeyInfo() {
        int i = this.eachId;
        if (i == 1 || i == 8 || i == 9) {
            switch (this.mCanBusInfoInt[2]) {
                case 0:
                    buttonKey(0);
                    break;
                case 1:
                    buttonKey(4);
                    break;
                case 2:
                    buttonKey(151);
                    break;
                case 3:
                    buttonKey(14);
                    break;
                case 4:
                    buttonKey(50);
                    break;
                case 5:
                    buttonKey(58);
                    break;
                case 6:
                    buttonKey(47);
                    break;
                case 7:
                    buttonKey(48);
                    break;
                case 8:
                    buttonKey(49);
                    break;
                case 9:
                    buttonKey(45);
                    break;
                case 10:
                    buttonKey(46);
                    break;
                case 11:
                    buttonKey(141);
                    break;
                case 12:
                    buttonKey(62);
                    break;
                case 13:
                    buttonKey(128);
                    break;
            }
        }
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
        int i = this.eachId;
        if (i == 8 || i == 9) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 0, getSwitchState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x24BaseInfo() {
        if (isBasicInfoChange()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info3"), getSwitchState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        adjustBrightness();
    }

    private void set0x23FrontRadarInfo() {
        int i = this.eachId;
        if ((i == 1 || i == 8 || i == 9) && !isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
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
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 0.5d, 0.0d, "C", 0, com.hzbhd.canbus.car._0.MsgMgr.DVD_MODE);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 0.0d, "C", 0, com.hzbhd.canbus.car._0.MsgMgr.DVD_MODE);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i != 22) {
            switch (i) {
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
                    buttonKey(45);
                    break;
                case 4:
                    buttonKey(46);
                    break;
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
                default:
                    switch (i) {
                        case 33:
                            buttonKey(33);
                            break;
                        case 34:
                            buttonKey(34);
                            break;
                        case 35:
                            buttonKey(35);
                            break;
                        case 36:
                            buttonKey(36);
                            break;
                        case 37:
                            buttonKey(37);
                            break;
                        case 38:
                            buttonKey(38);
                            break;
                        case 39:
                            buttonKey(39);
                            break;
                        case 40:
                            buttonKey(40);
                            break;
                    }
            }
        }
        buttonKey(49);
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
    }

    private void set0x14BackLightInfo() {
        if (isBackLightIntChange()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info1"), this.mCanBusInfoInt[2] + ""));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
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

    public void updateAmplifier() {
        updateAmplifierActivity(null);
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
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._246.MsgMgr.2
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
