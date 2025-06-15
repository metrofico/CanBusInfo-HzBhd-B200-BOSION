package com.hzbhd.canbus.car._396;

import android.content.Context;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
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
    SystemButton systemButton;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    private int nowVol = -1;

    private Object getEnergyState(int i) {
        return i == 0 ? "LOW" : i == 1 ? "MID" : i == 2 ? "HI" : "NO STATE";
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private String getPmState(int i) {
        return i <= 37 ? "☺ ■□□□□ ☹" : (i <= 37 || i > 75) ? (i <= 75 || i > 125) ? (i <= 125 || i > 250) ? (i <= 250 || i > 255) ? "NO STATE" : "☺ ■■■■■ ☹" : "☺ ■■■■□ ☹" : "☺ ■■■□□ ☹" : "☺ ■■□□□ ☹";
    }

    private String getSettingState(boolean z) {
        return z ? "ON" : "OFF";
    }

    private int getSwitch(boolean z) {
        return z ? 1 : 0;
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
        getUiMgr(this.mContext).sendCarType0x24(getCurrentEachCanId());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).sendTime0xCB(0, i5, i6, 0, 0, z ? 1 : 0, i - 2000, i3, i4, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            set0x11SWC();
            set0x11Track();
            return;
        }
        if (i == 18) {
            set0x12Door();
            return;
        }
        if (i == 20) {
            set0x14Oli();
            return;
        }
        if (i == 21) {
            set0x15Power();
            return;
        }
        if (i == 26) {
            set0x1ADoor();
            set0x1ASpeed();
            set0x1ATrack();
            set0x1APanoro();
            return;
        }
        if (i == 63) {
            set0x3Fpower();
            return;
        }
        if (i == 65) {
            set0x41RadarInfo();
            return;
        }
        if (i == 72) {
            set0x48TireInfo();
            return;
        }
        if (i == 102) {
            set0x66Setting();
            return;
        }
        if (i == 232) {
            set0xE8Panoro();
            return;
        }
        if (i == 240) {
            set0xF0Version();
            return;
        }
        if (i == 33) {
            set0x21PanoraKey();
            return;
        }
        if (i == 34) {
            set0x04PanoraKnoy();
            return;
        }
        if (i == 49) {
            set0x31Air();
            return;
        }
        if (i == 50) {
            set0x32CarBody();
        } else if (i == 52) {
            set0x34Oli();
        } else {
            if (i != 53) {
                return;
            }
            set0x35AirSetting();
        }
    }

    private void set0xF0Version() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x1APanoro() {
        forceReverse(this.mContext, this.mCanBusInfoInt[10] == 1);
    }

    private void set0x1ATrack() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x1ASpeed() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive_speed");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df_2Decimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getMsbLsbResult(iArr[5], iArr[6]) * 0.1d)).append("km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x1ADoor() {
        if (isNotDoorInfoChange()) {
            return;
        }
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void set0xE8Panoro() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_panoro"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_panoro", "_396_panoro1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_panoro"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_panoro", "_396_panoro2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_panoro"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_panoro", "_396_panoro3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        forceReverse(this.mContext, this.mCanBusInfoInt[5] == 1);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 5));
        arrayList2.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 6));
        arrayList2.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 7));
        arrayList2.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[3] == 8));
        arrayList2.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[3] == 10));
        arrayList2.add(new PanoramicBtnUpdateEntity(5, this.mCanBusInfoInt[3] == 11));
        arrayList2.add(new PanoramicBtnUpdateEntity(6, this.mCanBusInfoInt[3] == 12));
        arrayList2.add(new PanoramicBtnUpdateEntity(7, this.mCanBusInfoInt[3] == 13));
        arrayList2.add(new PanoramicBtnUpdateEntity(8, !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        arrayList2.add(new PanoramicBtnUpdateEntity(9, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        GeneralParkData.dataList = arrayList2;
        updateParkUi(null, this.mContext);
    }

    private void set0x66Setting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting1"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting2"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting3"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting5"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting6"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[0])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting7"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[0])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting8"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[0])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting9"), Integer.valueOf(this.mCanBusInfoInt[15])).setProgress(this.mCanBusInfoInt[15]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting10"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting11"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting12"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting13"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting14"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting15"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting16"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting17"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting18"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting19"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting20"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting21"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting22"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting23"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting24"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting25"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting26"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting27"), Integer.valueOf(this.mCanBusInfoInt[16])).setProgress(this.mCanBusInfoInt[16] - 51));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting28"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting29"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting30"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting31"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting33"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting34"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting35"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting36"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting37"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting38"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting39"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting40"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting41"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting42"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting43"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting44"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting45"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting46"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting47"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting48"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting49"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting50"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting51"), Integer.valueOf(getSwitch(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12])))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting", "_396_setting52"), Integer.valueOf(this.mCanBusInfoInt[13] / 5)).setProgress((this.mCanBusInfoInt[13] / 5) - 6));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting55_0"), getSettingState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting55"), getSettingState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting56"), getSettingState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting57"), getSettingState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting58"), getSettingState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting59"), getSettingState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting60"), getSettingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting61"), getEnergyState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_setting_state"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_setting_state", "_396_setting62"), getPmState(this.mCanBusInfoInt[14])).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x48TireInfo() {
        GeneralTireData.isHaveSpareTire = false;
        this.arr0[0] = this.df_2Decimal.format(this.mCanBusInfoInt[4] * 0.1d) + "BAR";
        this.arr0[1] = (this.mCanBusInfoInt[9] - 60) + getTempUnitC(this.mContext);
        this.arr1[0] = this.df_2Decimal.format(this.mCanBusInfoInt[5] * 0.1d) + "BAR";
        this.arr1[1] = (this.mCanBusInfoInt[10] - 60) + getTempUnitC(this.mContext);
        this.arr2[0] = this.df_2Decimal.format(this.mCanBusInfoInt[6] * 0.1d) + "BAR";
        this.arr2[1] = (this.mCanBusInfoInt[11] - 60) + getTempUnitC(this.mContext);
        this.arr3[0] = this.df_2Decimal.format(this.mCanBusInfoInt[7] * 0.1d) + "BAR";
        this.arr3[1] = (this.mCanBusInfoInt[12] - 60) + getTempUnitC(this.mContext);
        this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
        this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x3Fpower() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd1");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df_1Decimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getMsbLsbResult(iArr[3], iArr[4]) * 0.1d)).append("V").toString()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd2"), getModel(this.mCanBusInfoInt[5])));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd3");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[8], iArr2[9])).append("RPM").toString()));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_hd");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_hd4");
        StringBuilder sb3 = new StringBuilder();
        DecimalFormat decimalFormat2 = this.df_1Decimal;
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(decimalFormat2.format(getMsbLsbResult(iArr3[10], iArr3[11]) * 0.1d)).append("A").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x15Power() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d0"), getProgressOil(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d1"), getProgressOil(this.mCanBusInfoInt[3])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d2"), getProgressOil(this.mCanBusInfoInt[4])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d3"), getProgressOil(this.mCanBusInfoInt[5])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d4"), getProgressOil(this.mCanBusInfoInt[6])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d5"), getProgressOil(this.mCanBusInfoInt[7])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d6"), getProgressOil(this.mCanBusInfoInt[8])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d7"), getProgressOil(this.mCanBusInfoInt[9])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d8"), getProgressOil(this.mCanBusInfoInt[10])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d9"), getProgressOil(this.mCanBusInfoInt[11])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d10"), getProgressOil(this.mCanBusInfoInt[12])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d11"), getProgressOil(this.mCanBusInfoInt[13])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d12"), getProgressOil(this.mCanBusInfoInt[14])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d13"), getProgressOil(this.mCanBusInfoInt[15])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d14"), getProgressOil(this.mCanBusInfoInt[16])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d15"), getProgressOil(this.mCanBusInfoInt[17])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d16"), getProgressOil(this.mCanBusInfoInt[18])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d17"), getProgressOil(this.mCanBusInfoInt[19])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d18"), getProgressOil(this.mCanBusInfoInt[20])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d19"), getProgressOil(this.mCanBusInfoInt[21])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d20"), getProgressOil(this.mCanBusInfoInt[22])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d21"), getProgressOil(this.mCanBusInfoInt[23])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d22"), getProgressOil(this.mCanBusInfoInt[24])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d23"), getProgressOil(this.mCanBusInfoInt[25])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d24"), getProgressOil(this.mCanBusInfoInt[26])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d25"), getProgressOil(this.mCanBusInfoInt[27])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d26"), getProgressOil(this.mCanBusInfoInt[28])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d27"), getProgressOil(this.mCanBusInfoInt[29])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d28"), getProgressOil(this.mCanBusInfoInt[30])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d29"), getProgressOil(this.mCanBusInfoInt[31])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d30"), getProgressOil(this.mCanBusInfoInt[32])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d31"), getProgressOil(this.mCanBusInfoInt[33])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d32"), getProgressOil(this.mCanBusInfoInt[34])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d33"), getProgressOil(this.mCanBusInfoInt[35])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d34"), getProgressOil(this.mCanBusInfoInt[36])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d35"), getProgressOil(this.mCanBusInfoInt[37])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d36"), getProgressOil(this.mCanBusInfoInt[38])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d37"), getProgressOil(this.mCanBusInfoInt[39])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d38"), getProgressOil(this.mCanBusInfoInt[40])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d39"), getProgressOil(this.mCanBusInfoInt[41])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d40"), getProgressOil(this.mCanBusInfoInt[42])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d41"), getProgressOil(this.mCanBusInfoInt[43])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d42"), getProgressOil(this.mCanBusInfoInt[44])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d43"), getProgressOil(this.mCanBusInfoInt[45])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d44"), getProgressOil(this.mCanBusInfoInt[46])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d45"), getProgressOil(this.mCanBusInfoInt[47])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d46"), getProgressOil(this.mCanBusInfoInt[48])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d47"), getProgressOil(this.mCanBusInfoInt[49])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d48"), getProgressOil(this.mCanBusInfoInt[50])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d49"), getProgressOil(this.mCanBusInfoInt[51])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_d"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_d50"), getProgressOil(this.mCanBusInfoInt[52])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x14Oli() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy0"), getProgress(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy1"), getProgress(this.mCanBusInfoInt[3])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy2"), getProgress(this.mCanBusInfoInt[4])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy3"), getProgress(this.mCanBusInfoInt[5])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy4"), getProgress(this.mCanBusInfoInt[6])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy5"), getProgress(this.mCanBusInfoInt[7])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy6"), getProgress(this.mCanBusInfoInt[8])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy7"), getProgress(this.mCanBusInfoInt[9])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy8"), getProgress(this.mCanBusInfoInt[10])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy9"), getProgress(this.mCanBusInfoInt[11])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy10"), getProgress(this.mCanBusInfoInt[12])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy11"), getProgress(this.mCanBusInfoInt[13])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy12"), getProgress(this.mCanBusInfoInt[14])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy13"), getProgress(this.mCanBusInfoInt[15])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy14"), getProgress(this.mCanBusInfoInt[16])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy15"), getProgress(this.mCanBusInfoInt[17])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy16"), getProgress(this.mCanBusInfoInt[18])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy17"), getProgress(this.mCanBusInfoInt[19])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy18"), getProgress(this.mCanBusInfoInt[20])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy19"), getProgress(this.mCanBusInfoInt[21])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy20"), getProgress(this.mCanBusInfoInt[22])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy21"), getProgress(this.mCanBusInfoInt[23])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy22"), getProgress(this.mCanBusInfoInt[24])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy23"), getProgress(this.mCanBusInfoInt[25])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy24"), getProgress(this.mCanBusInfoInt[26])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy25"), getProgress(this.mCanBusInfoInt[27])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy26"), getProgress(this.mCanBusInfoInt[28])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy27"), getProgress(this.mCanBusInfoInt[29])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy28"), getProgress(this.mCanBusInfoInt[30])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy29"), getProgress(this.mCanBusInfoInt[31])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy30"), getProgress(this.mCanBusInfoInt[32])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy31"), getProgress(this.mCanBusInfoInt[33])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy32"), getProgress(this.mCanBusInfoInt[34])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy33"), getProgress(this.mCanBusInfoInt[35])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy34"), getProgress(this.mCanBusInfoInt[36])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy35"), getProgress(this.mCanBusInfoInt[37])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy36"), getProgress(this.mCanBusInfoInt[38])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy37"), getProgress(this.mCanBusInfoInt[39])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy38"), getProgress(this.mCanBusInfoInt[40])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy39"), getProgress(this.mCanBusInfoInt[41])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy40"), getProgress(this.mCanBusInfoInt[42])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy41"), getProgress(this.mCanBusInfoInt[43])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy42"), getProgress(this.mCanBusInfoInt[44])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy43"), getProgress(this.mCanBusInfoInt[45])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy44"), getProgress(this.mCanBusInfoInt[46])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy45"), getProgress(this.mCanBusInfoInt[47])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy46"), getProgress(this.mCanBusInfoInt[48])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy47"), getProgress(this.mCanBusInfoInt[49])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy48"), getProgress(this.mCanBusInfoInt[50])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy49"), getProgress(this.mCanBusInfoInt[51])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_dy"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_dy50"), getProgress(this.mCanBusInfoInt[52])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x34Oli() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive1");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df_2Decimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(((iArr[8] & 255) | ((iArr[6] & 255) << 16) | ((iArr[7] & 255) << 8)) * 0.1d)).append("KM").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive2");
        StringBuilder sb2 = new StringBuilder();
        DecimalFormat decimalFormat2 = this.df_2Decimal;
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(decimalFormat2.format(getMsbLsbResult(iArr2[9], iArr2[10]) * 0.1d)).append("L/100KM").toString()));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive3");
        StringBuilder sb3 = new StringBuilder();
        DecimalFormat decimalFormat3 = this.df_2Decimal;
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(decimalFormat3.format(getMsbLsbResult(iArr3[12], iArr3[13]))).append("KM").toString()));
        int drivingPageIndexes4 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
        int drivingItemIndexes4 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive4");
        StringBuilder sb4 = new StringBuilder();
        DecimalFormat decimalFormat4 = this.df_2Decimal;
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb4.append(decimalFormat4.format(getMsbLsbResult(iArr4[14], iArr4[15]) * 0.1d)).append("kWh/100KM").toString()));
        int drivingPageIndexes5 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
        int drivingItemIndexes5 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive5");
        StringBuilder sb5 = new StringBuilder();
        DecimalFormat decimalFormat5 = this.df_2Decimal;
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, drivingItemIndexes5, sb5.append(decimalFormat5.format(getMsbLsbResult(iArr5[17], iArr5[18]))).append("KM").toString()));
        int drivingPageIndexes6 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_396_drive");
        int drivingItemIndexes6 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_396_drive6");
        StringBuilder sb6 = new StringBuilder();
        DecimalFormat decimalFormat6 = this.df_2Decimal;
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes6, drivingItemIndexes6, sb6.append(decimalFormat6.format(getMsbLsbResult(iArr6[22], iArr6[23]))).append("KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x32CarBody() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[4], iArr[5])).append(" RPM").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[6], iArr2[7])).append(" KM/H").toString()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "vehicle_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_394_drive_v"), this.df_1Decimal.format(this.mCanBusInfoInt[8] * 0.1d) + "3 V"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    private void set0x41RadarInfo() {
        if (this.mCanBusInfoInt[12] == 0) {
            return;
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        GeneralParkData.isShowLeftTopOneDistanceUi = true;
        GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[11] + "CM";
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 255) {
            iArr[2] = 0;
        }
        if (iArr[3] == 255) {
            iArr[3] = 0;
        }
        if (iArr[4] == 255) {
            iArr[4] = 0;
        }
        if (iArr[5] == 255) {
            iArr[5] = 0;
        }
        if (iArr[6] == 255) {
            iArr[6] = 0;
        }
        if (iArr[7] == 255) {
            iArr[7] = 0;
        }
        if (iArr[8] == 255) {
            iArr[8] = 0;
        }
        if (iArr[9] == 255) {
            iArr[8] = 0;
        }
        RadarInfoUtil.setRearRadarLocationData(15, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(15, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x35AirSetting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))).setEnable(isI5() || isRX3()));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))).setEnable(isRX3()));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))).setEnable(isI5() || isRX3()));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_396_air"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_396_air", "_396_air4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))).setEnable(isI5() || isRX3()));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x31Air() {
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[9]);
        updateOutDoorTemp(this.mContext, this.df_1Decimal.format((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        updateAirActivity(this.mContext, 1004);
    }

    private void set0x04PanoraKnoy() {
        int i = this.nowVol;
        if (i == -1) {
            this.nowVol = this.mCanBusInfoInt[3];
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[2];
        if (i2 == 1) {
            if (i < iArr[3]) {
                realKeyClick4(this.mContext, 7);
                return;
            } else {
                realKeyClick4(this.mContext, 8);
                return;
            }
        }
        if (i2 == 2) {
            if (i < iArr[3]) {
                realKeyClick4(this.mContext, 48);
            } else {
                realKeyClick4(this.mContext, 47);
            }
        }
    }

    private void set0x21PanoraKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 1, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 21, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 20, iArr[3]);
            return;
        }
        if (i == 6) {
            realKeyLongClick1(this.mContext, 50, iArr[3]);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i == 32) {
            realKeyLongClick1(this.mContext, 128, iArr[3]);
            return;
        }
        if (i == 47) {
            realKeyLongClick1(this.mContext, 151, iArr[3]);
            return;
        }
        if (i == 55) {
            realKeyLongClick1(this.mContext, 58, iArr[3]);
            return;
        }
        if (i == 69) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i != 70) {
            switch (i) {
                case 43:
                    realKeyLongClick1(this.mContext, 52, iArr[3]);
                    break;
                case 44:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 45:
                    realKeyLongClick1(this.mContext, 59, iArr[3]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 8, iArr[3]);
    }

    private void set0x12Door() {
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void set0x11Track() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x11SWC() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        if (i == 24) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, iArr[5]);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[5]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[5]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[5]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 3, iArr[5]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[5]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 14, iArr[5]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 15, iArr[5]);
                    break;
                default:
                    switch (i) {
                        case 8:
                            realKeyLongClick1(this.mContext, 45, iArr[5]);
                            break;
                        case 9:
                            realKeyLongClick1(this.mContext, 46, iArr[5]);
                            break;
                        case 10:
                            realKeyLongClick1(this.mContext, 58, iArr[5]);
                            break;
                        case 11:
                            realKeyLongClick1(this.mContext, 2, iArr[5]);
                            break;
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 39, iArr[5]);
    }

    private String getProgress(int i) {
        int i2;
        String str = " ";
        int i3 = 0;
        while (true) {
            i2 = i / 10;
            if (i3 >= i2) {
                break;
            }
            str = str + "■";
            i3++;
        }
        for (int i4 = 0; i4 < 25 - i2; i4++) {
            str = str + "□";
        }
        return str + "【" + this.df_1Decimal.format(i * 0.1d) + "l/100km】";
    }

    private String getProgressOil(int i) {
        int i2;
        String str = " ";
        int i3 = 0;
        while (true) {
            i2 = i / 10;
            if (i3 >= i2) {
                break;
            }
            str = str + "■";
            i3++;
        }
        for (int i4 = 0; i4 < 12 - i2; i4++) {
            str = str + "□";
        }
        return str + "【" + (i - 25) + "kwh/km】";
    }

    private String getModel(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._396_hd21);
            case 2:
                return this.mContext.getString(R.string._396_hd22);
            case 3:
                return this.mContext.getString(R.string._396_hd23);
            case 4:
                return this.mContext.getString(R.string._396_hd24);
            case 5:
                return this.mContext.getString(R.string._396_hd25);
            case 6:
                return this.mContext.getString(R.string._396_hd26);
            case 7:
                return this.mContext.getString(R.string._396_hd27);
            default:
                return this.mContext.getString(R.string._396_hd20);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
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

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : this.df_1Decimal.format(i * 0.5d) + getTempUnitC(this.mContext);
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

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isNotDoorInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
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
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }

    private boolean isRX3() {
        return getCurrentEachCanId() == 20;
    }

    private boolean isI5() {
        return getCurrentEachCanId() == 16;
    }

    public void showP360Button() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._396.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "360", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._396.MsgMgr.1.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoro0xF2(15, 1);
                        }
                    });
                }
                MsgMgr.this.systemButton.show();
            }
        });
    }

    public void hideP360Button() {
        if (this.systemButton == null) {
            this.systemButton = new SystemButton(getActivity(), "360", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._396.MsgMgr.2
                @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                public void clickEvent() {
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.getUiMgr(msgMgr.mContext).sendPanoro0xF2(15, 1);
                }
            });
        }
        this.systemButton.hide();
    }
}
