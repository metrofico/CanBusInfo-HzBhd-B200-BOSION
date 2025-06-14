package com.hzbhd.canbus.car._1;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Priority;

/* loaded from: classes.dex */
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
    int differentId;
    int eachId;
    int front_radar_key;
    int[] mAirData;
    int[] mBackLightInt;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private int[] talkingPhoneNumber;
    DecimalFormat df = new DecimalFormat("#0");
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    String MediaInfo0 = "NULL INFO";
    String MediaInfo1 = "NULL INFO";
    String MediaInfo2 = "NULL INFO";
    String MediaInfo3 = "NULL INFO";
    String MediaInfo4 = "NULL INFO";
    String MediaInfo5 = "NULL INFO";

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
        this.front_radar_key = SharePreUtil.getIntValue(context, "FRONT_RADAR_KEY", 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUigMgr(context).makeConnection();
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        getUigMgr(this.mContext).sendSourceInfo(1, 1);
        getUigMgr(this.mContext).sendRadioInfo(getBandAmFM(str), getFreqLsb(str, str2), getFreqMsb(str, str2), i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        getUigMgr(this.mContext).sendSourceInfo(0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUigMgr(this.mContext).sendSourceInfo(2, 33);
        getUigMgr(this.mContext).sendMediaPalyInfo(i4, i5, 0, 0, i / 60, i % 60);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUigMgr(this.mContext).sendSourceInfo(8, 17);
        getUigMgr(this.mContext).sendMediaPalyInfo(getLsb(i2), getMsb(i2), getLsb(i), getMsb(i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        getUigMgr(this.mContext).sendSourceInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUigMgr(this.mContext).sendSourceInfo(7, 48);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUigMgr(this.mContext).sendSourceInfo(11, 34);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        getUigMgr(this.mContext).sendSourceInfo(0, 0);
        getUigMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.comingPhoneNumber)) {
            return;
        }
        this.comingPhoneNumber = byteArrayToIntArray;
        getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.OutGoingPhoneNumber)) {
            return;
        }
        this.OutGoingPhoneNumber = byteArrayToIntArray;
        getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 2, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        getUigMgr(this.mContext).sendSourceInfo(5, 64);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.talkingPhoneNumber)) {
            return;
        }
        this.talkingPhoneNumber = byteArrayToIntArray;
        getUigMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 4, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        this.comingPhoneNumber = null;
        this.OutGoingPhoneNumber = null;
        this.talkingPhoneNumber = null;
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._1.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(2000L, 500L) { // from class: com.hzbhd.canbus.car._1.MsgMgr.1.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneNumber();
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneNumber();
                    }
                }.start();
            }
        });
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
        if (i == 55) {
            set0x37TireInfo();
            return;
        }
        if (i == 65) {
            set0x41CarInfo();
            return;
        }
        if (i != 149) {
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
                    set0x27AmplifierInfo();
                    break;
                case 40:
                    set0x28BtInfo();
                    break;
                case 41:
                    set0x29MediaInfo();
                    break;
                case 42:
                    set0x2AMediaStrInfo();
                    break;
            }
            return;
        }
        set0x95data();
    }

    private void set0x2AMediaStrInfo() {
        String str = "%";
        for (int i = 4; i < this.mCanBusInfoInt.length; i++) {
            if (i != 4) {
                str = str + "%";
            }
            str = str + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i]));
        }
        String uTF8Result = !str.equals("%") ? getUTF8Result(str) : "NULL INFO";
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)) {
            case 12:
                this.MediaInfo3 = uTF8Result;
                break;
            case 13:
                this.MediaInfo4 = uTF8Result;
                break;
            case 14:
                this.MediaInfo5 = uTF8Result;
                break;
        }
        setDataToOriginalCar();
    }

    private void set0x37TireInfo() {
        GeneralTireData.isHaveSpareTire = false;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2) == 0) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_tire_3"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_tire_3", "_1001_tire_3"), this.mContext.getString(R.string._1001_tire_4)).setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_tire_3"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_tire_3", "_1001_tire_3"), this.mContext.getString(R.string._1001_tire_5)).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        List<TireUpdateEntity> list = this.tyreInfoList;
        if (list != null) {
            list.clear();
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            this.arr0[0] = this.mContext.getString(R.string._1001_tire_1);
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
            this.arr0[0] = this.mContext.getString(R.string._1001_tire_2);
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.arr1[0] = this.mContext.getString(R.string._1001_tire_1);
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
        } else {
            this.arr1[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
            this.arr2[0] = this.mContext.getString(R.string._1001_tire_1);
        } else {
            this.arr2[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
            this.arr3[0] = this.mContext.getString(R.string._1001_tire_1);
        } else {
            this.arr3[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            this.arr0[1] = this.mContext.getString(R.string._1001_tire_8);
            this.arr1[1] = this.mContext.getString(R.string._1001_tire_8);
            this.arr2[1] = this.mContext.getString(R.string._1001_tire_8);
            this.arr3[1] = this.mContext.getString(R.string._1001_tire_8);
        } else {
            this.arr0[1] = this.mContext.getString(R.string._1001_tire_7);
            this.arr1[1] = this.mContext.getString(R.string._1001_tire_7);
            this.arr2[1] = this.mContext.getString(R.string._1001_tire_7);
            this.arr3[1] = this.mContext.getString(R.string._1001_tire_7);
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x95data() {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 0) {
            arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_driver_1"), "OFF"));
        } else {
            arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_driver_1"), "ON"));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x29MediaInfo() {
        GeneralOriginalCarDeviceData.cdStatus = getCdStatus();
        GeneralOriginalCarDeviceData.runningState = getRunningState();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        StringBuilder sbAppend = sb.append(getMsbLsbResult(iArr[4], iArr[5])).append("/");
        int[] iArr2 = this.mCanBusInfoInt;
        this.MediaInfo0 = sbAppend.append(getMsbLsbResult(iArr2[6], iArr2[7])).toString();
        int[] iArr3 = this.mCanBusInfoInt;
        int msbLsbResult = getMsbLsbResult(iArr3[8], iArr3[9]);
        int[] iArr4 = this.mCanBusInfoInt;
        int msbLsbResult2 = getMsbLsbResult(iArr4[10], iArr4[11]);
        int i = msbLsbResult / 60;
        int i2 = msbLsbResult2 / 60;
        this.MediaInfo1 = (i / 60) + ":" + (i % 60) + ":" + (msbLsbResult % 60) + "/" + (i2 / 60) + ":" + (i2 % 60) + ":" + (msbLsbResult2 % 60);
        this.MediaInfo2 = getModelState(this.mCanBusInfoInt);
        setDataToOriginalCar();
    }

    private void set0x28BtInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_1"), getBtState1()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_2"), getBtState2()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_3"), getBtState3()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_4"), getBtState4()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_5"), getBtState5()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_6"), getBtState6()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_7"), getBtState7()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_1001_bt"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1001_bt_8"), getBtState8()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            arrayList2.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_bt2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_bt2", "_1001_bt_7"), 1));
        } else {
            arrayList2.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1001_bt2"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_1001_bt2", "_1001_bt_7"), 0));
        }
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    private void set0x41CarInfo() {
        int i = this.mCanBusInfoInt[2];
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
            GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
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

    private void set0x26EspInfo() {
        if (isTrackInfoChange()) {
            return;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x27AmplifierInfo() {
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
        updateAmplifierActivity(new Bundle());
        AmpUtil.saveAmpUIValue(this.mContext);
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
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        this.mCanBusInfoInt[2] = 0;
        if (isBasicInfoChange()) {
            return;
        }
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void reverseStateChange(boolean z) {
        super.reverseStateChange(z);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x23FrontRadarInfo() {
        int[] iArr;
        int i;
        int i2;
        int i3;
        int i4;
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr2[2], iArr2[3], iArr2[4], iArr2[5]);
        this.front_radar_key = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
        Log.d("front_radar_key", this.front_radar_key + "");
        if ((this.front_radar_key == 1 && (i4 = this.mCanBusInfoInt[2]) <= 5 && i4 != 0) || (((i = (iArr = this.mCanBusInfoInt)[3]) <= 5 && i != 0) || (((i2 = iArr[4]) <= 5 && i2 != 0) || ((i3 = iArr[5]) <= 5 && i3 != 0)))) {
            forceReverse(this.mContext, true);
        } else {
            forceReverse(this.mContext, false);
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
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 0.5d, 17.5d, "C", 0, 31);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 17.5d, "C", 0, 31);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 19) {
            buttonKey(20);
            return;
        }
        if (i != 20) {
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
            }
            return;
        }
        buttonKey(21);
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
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr2[3], iArr2[2]) / 16);
    }

    private void set0x14BackLightInfo() {
        if (isBackLightIntChange()) {
            return;
        }
        adjustBrightness();
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

    private void setDataToOriginalCar() {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(42);
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = getOriginalDeviceInfo();
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.MediaInfo0));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.MediaInfo1));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.MediaInfo2));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.MediaInfo3));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.MediaInfo4));
        arrayList.add(new OriginalCarDeviceUpdateEntity(5, this.MediaInfo5));
        return arrayList;
    }

    private String getCdStatus() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_media_2);
        }
        return this.mContext.getString(R.string._1001_media_1);
    }

    private String getModelState(int[] iArr) {
        String str = DataHandleUtils.getBoolBit3(iArr[2]) ? "" + this.mContext.getString(R.string._1001_media_12) + "/" : "";
        if (DataHandleUtils.getBoolBit2(iArr[2])) {
            str = str + this.mContext.getString(R.string._1001_media_13) + "/";
        }
        if (DataHandleUtils.getBoolBit1(iArr[2])) {
            str = str + this.mContext.getString(R.string._1001_media_14) + "/";
        }
        return DataHandleUtils.getBoolBit0(iArr[2]) ? str + this.mContext.getString(R.string._1001_media_15) + "/" : str;
    }

    private String getRunningState() {
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            return this.mContext.getString(R.string._1001_media_10);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._1001_media_3);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._1001_media_4);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._1001_media_5);
        }
        if (i == 5) {
            return this.mContext.getString(R.string._1001_media_6);
        }
        return this.mContext.getString(R.string._1001_media_7);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._1.MsgMgr$2] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._1.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_8", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_9", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_11", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_16", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_17", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_1001_media_18", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(42, new OriginalDeviceData(arrayList, new String[]{OriginalBtnAction.PREV_DISC, "left", OriginalBtnAction.RANDOM, OriginalBtnAction.PLAY_PAUSE, "cycle", "right", OriginalBtnAction.NEXT_DISC}));
    }

    private static class OriginalDeviceData {
        private final String[] bottomBtns;
        private final List<OriginalCarDevicePageUiSet.Item> list;

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
            this(list, null);
        }

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
            this.list = list;
            this.bottomBtns = strArr;
        }

        public List<OriginalCarDevicePageUiSet.Item> getItemList() {
            return this.list;
        }

        public String[] getBottomBtns() {
            return this.bottomBtns;
        }
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUigMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private String getBtState1() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_1_1);
        }
        return this.mContext.getString(R.string._1001_bt_1_0);
    }

    private String getBtState2() {
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_2_1);
        }
        return this.mContext.getString(R.string._1001_bt_2_0);
    }

    private String getBtState3() {
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_3_1);
        }
        return this.mContext.getString(R.string._1001_bt_3_0);
    }

    private String getBtState4() {
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_4_1);
        }
        return this.mContext.getString(R.string._1001_bt_4_0);
    }

    private String getBtState5() {
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_5_1);
        }
        return this.mContext.getString(R.string._1001_bt_5_0);
    }

    private String getBtState6() {
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_6_1);
        }
        return this.mContext.getString(R.string._1001_bt_6_0);
    }

    private String getBtState7() {
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_7_1);
        }
        return this.mContext.getString(R.string._1001_bt_7_0);
    }

    private String getBtState8() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._1001_bt_8_1);
        }
        return this.mContext.getString(R.string._1001_bt_8_0);
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

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
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
        int brightness = FutureUtil.instance.getBrightness();
        if (brightness == 5) {
            setBacklightLevel(1);
        } else {
            setBacklightLevel(brightness + 1);
        }
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String tenToSixTeen(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    private int sixteenToTen(String str) {
        return Integer.parseInt(("0x" + str).replaceAll("^0[x|X]", ""), 16);
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void showDialog(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._1.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new AlertView().showDialog(MsgMgr.this.getActivity(), str);
            }
        });
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

    private boolean is404(String str, String str2) {
        return getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUigMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
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

    public void updateAmp() {
        updateAmplifierActivity(null);
    }
}
