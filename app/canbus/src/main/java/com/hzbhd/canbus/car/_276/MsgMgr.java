package com.hzbhd.canbus.car._276;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static boolean isDoorFirst = true;
    int BacksightDelay;
    int ComeringLights;
    int CourtesyLights;
    int CourtesyLightsDelay;
    int DayLight;
    int DoorAutoLock;
    int FlashLightsWithLock;
    int HeadLightOffDelay;
    int LightSense;
    int ParkSense;
    int RearParkSenseVOl;
    int RearViewCameraGuidelines;
    int TripB;
    int WiperSensorSwitch;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferentId;
    private int mEachId;
    private UiMgr mUiMgr;
    private String mUnit1 = " l/100km";
    private String mUnit2 = " km";
    Boolean DoorAutoLockEnable = true;
    Boolean DayLightEnable = true;
    Boolean RearViewCameraGuidelinesEnable = true;
    Boolean RearParkSenseVOlEnable = true;
    Boolean FlashLightsWithLockEnable = true;
    Boolean ComeringLightsEnable = true;
    Boolean CourtesyLightsEnable = true;
    Boolean ParkSenseEnable = true;
    Boolean TripBEnable = true;
    Boolean LightSenseEnable = true;
    Boolean WiperSensorSwitchEnable = true;
    Boolean CourtesyLightsDelayEnable = true;
    Boolean BacksightDelayEnable = true;
    Boolean HeadLightOffDelayEnable = true;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap = new HashMap<>();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        UiMgrFactory.getCanUiMgr(context);
        this.mEachId = getCurrentEachCanId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferentId = getCurrentCanDifferentId();
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 27});
        initSettingsItem(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 38) {
            set0x26CarInfo();
            return;
        }
        if (i == 49) {
            setOutDoorTemp();
            return;
        }
        if (i == 65) {
            if (this.mDifferentId != 1) {
                radarInfo();
                return;
            }
            return;
        }
        if (i == 113) {
            VehicleSettingInformationEnable();
            return;
        }
        if (i == 118) {
            int i2 = this.mDifferentId;
            if (i2 == 1 || i2 == 0) {
                return;
            }
            CarSetting();
            return;
        }
        if (i == 148) {
            int i3 = this.mDifferentId;
            if (i3 == 1 || i3 == 0) {
                return;
            }
            setLanguage();
            return;
        }
        if (i == 193) {
            int i4 = this.mDifferentId;
            if (i4 == 1 || i4 == 0) {
                return;
            }
            settingData();
            return;
        }
        if (i != 240) {
            switch (i) {
                case 17:
                    keyControl0x11();
                    break;
                case 18:
                    if (!isDoorMsgReturn(bArr)) {
                        setDoorData();
                        break;
                    }
                    break;
                case 19:
                    if (this.mDifferentId != 1) {
                        setDriveData0x13();
                        break;
                    }
                    break;
                case 20:
                    if (this.mDifferentId != 1) {
                        setDriveDataTrip0x14();
                        break;
                    }
                    break;
                case 21:
                    if (this.mDifferentId != 1) {
                        setDriveDataTrip0x15();
                        break;
                    }
                    break;
            }
            return;
        }
        setVersionInfo();
    }

    private void keyControl0x11() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyClick1(0);
                break;
            case 1:
                realKeyClick1(7);
                break;
            case 2:
                realKeyClick1(8);
                break;
            case 3:
                realKeyClick1(3);
                break;
            case 4:
                realKeyClick1(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                realKeyClick1(14);
                break;
            case 6:
                realKeyClick1(15);
                break;
            case 8:
                realKeyClick1(46);
                break;
            case 9:
                realKeyClick1(45);
                break;
            case 11:
                realKeyClick1(2);
                break;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[4], iArr[5]);
    }

    private void setDoorData() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setDriveData0x13() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append(this.mUnit1).toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append(this.mUnit2).toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, sb3.append((iArr3[6] * 256 * 256) + (iArr3[7] * 256) + iArr3[8]).append(this.mUnit2).toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveDataTrip0x14() throws Resources.NotFoundException {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[10] * 256) + iArr[11];
        int i2 = iArr[9];
        String string = this.mContext.getResources().getString(R.string.hour);
        String string2 = this.mContext.getResources().getString(R.string.min);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append((float) (((iArr2[2] * 256) + iArr2[3]) * 0.1d)).append(this.mUnit1).toString()));
        arrayList.add(new DriverUpdateEntity(1, 1, this.mCanBusInfoInt[4] + " km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb2.append((float) (((iArr3[5] * 256 * 256) + (iArr3[6] * 256) + iArr3[7]) * 0.1d)).append(this.mUnit2).toString()));
        arrayList.add(new DriverUpdateEntity(1, 3, (i + (i2 / 60)) + string + (i2 % 60) + string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[4]);
    }

    private void setDriveDataTrip0x15() throws Resources.NotFoundException {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[10] * 256) + iArr[11];
        int i2 = iArr[9];
        String string = this.mContext.getResources().getString(R.string.hour);
        String string2 = this.mContext.getResources().getString(R.string.min);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 0, sb.append((float) (((iArr2[2] * 256) + iArr2[3]) * 0.1d)).append(this.mUnit1).toString()));
        arrayList.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[4] + " km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 2, sb2.append((float) (((iArr3[5] * 256 * 256) + (iArr3[6] * 256) + iArr3[7]) * 0.1d)).append(this.mUnit2).toString()));
        arrayList.add(new DriverUpdateEntity(2, 3, (i + (i2 / 60)) + string + (i2 % 60) + string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setOutDoorTemp() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void radarInfo() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2] + 1, iArr[3] + 1, iArr[4] + 1, iArr[5] + 1);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void settingData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2);
        if (intFromByteWithBit == 0) {
            this.mUnit1 = "l/100km";
        } else if (intFromByteWithBit == 1) {
            this.mUnit1 = "km/l";
        } else if (intFromByteWithBit == 2) {
            this.mUnit1 = "mpg(us)";
        } else if (intFromByteWithBit == 3) {
            this.mUnit1 = "mpg(uk)";
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            this.mUnit2 = "km";
        } else {
            this.mUnit2 = "mile";
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLanguage() {
        int i = this.mCanBusInfoInt[2];
        int i2 = i != 8 ? i != 7 ? i != 5 ? i != 4 ? i == 3 ? 1 : 0 : 2 : 3 : 4 : 5;
        if (i == 9) {
            i2 = 6;
        }
        int i3 = i != 21 ? i != 16 ? i2 : 7 : 8;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void VehicleSettingInformationEnable() {
        this.DoorAutoLockEnable = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        this.DayLightEnable = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]));
        this.RearViewCameraGuidelinesEnable = Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
        this.RearParkSenseVOlEnable = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
        this.FlashLightsWithLockEnable = Boolean.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
        this.ComeringLightsEnable = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
        this.CourtesyLightsEnable = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
        this.ParkSenseEnable = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]));
        this.TripBEnable = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
        this.LightSenseEnable = Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]));
        this.WiperSensorSwitchEnable = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]));
        this.CourtesyLightsDelayEnable = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
        this.BacksightDelayEnable = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
        this.HeadLightOffDelayEnable = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
        carSetting();
    }

    private void CarSetting() {
        this.DoorAutoLock = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
        this.DayLight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
        this.RearViewCameraGuidelines = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
        this.RearParkSenseVOl = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2);
        this.FlashLightsWithLock = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1);
        this.ComeringLights = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
        this.CourtesyLights = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
        this.ParkSense = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1);
        this.TripB = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
        this.LightSense = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        this.WiperSensorSwitch = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1);
        this.CourtesyLightsDelay = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2);
        this.BacksightDelay = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
        this.HeadLightOffDelay = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        carSetting();
    }

    private void carSetting() {
        ArrayList arrayList = new ArrayList();
        int i = this.mDifferentId;
        if (i == 2 || i == 7 || i == 9 || i == 10) {
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(this.DayLight)).setEnable(this.DayLightEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(this.TripB)).setEnable(this.TripBEnable.booleanValue()));
        } else if (i == 3) {
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(this.ParkSense)).setEnable(this.ParkSenseEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(this.RearParkSenseVOl)).setEnable(this.RearParkSenseVOlEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(this.CourtesyLights)).setEnable(this.CourtesyLightsEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(this.ComeringLights)).setEnable(this.ComeringLightsEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(this.FlashLightsWithLock)).setEnable(this.FlashLightsWithLockEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(this.LightSense)).setEnable(this.LightSenseEnable.booleanValue()));
        } else if (i == 4) {
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(this.ParkSense)).setEnable(this.ParkSenseEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(this.RearParkSenseVOl)).setEnable(this.RearParkSenseVOlEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(this.CourtesyLights)).setEnable(this.CourtesyLightsEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(this.ComeringLights)).setEnable(this.ComeringLightsEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(this.FlashLightsWithLock)).setEnable(this.FlashLightsWithLockEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(this.RearViewCameraGuidelines)).setEnable(this.RearViewCameraGuidelinesEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 7, Integer.valueOf(this.HeadLightOffDelay)).setEnable(this.HeadLightOffDelayEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 8, Integer.valueOf(this.BacksightDelay)).setEnable(this.BacksightDelayEnable.booleanValue()));
        } else if (i == 5 || i == 6) {
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.DoorAutoLock)).setEnable(this.DoorAutoLockEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(this.DayLight)).setEnable(this.DayLightEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(this.TripB)).setEnable(this.TripBEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(this.CourtesyLights)).setEnable(this.CourtesyLightsEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(this.ComeringLights)).setEnable(this.ComeringLightsEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(this.CourtesyLightsDelay)).setEnable(this.CourtesyLightsDelayEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(this.LightSense)).setEnable(this.LightSenseEnable.booleanValue()));
            arrayList.add(new SettingUpdateEntity(2, 7, Integer.valueOf(this.WiperSensorSwitch)).setEnable(this.WiperSensorSwitchEnable.booleanValue()));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private boolean isHave0xe1Info() {
        int i = this.mDifferentId;
        return (i == 0 || i == 1 || i == 2) ? false : true;
    }

    private void set0x26CarInfo() {
        String str;
        int i = this.mCanBusInfoInt[2];
        if (i != 0) {
            switch (i) {
                case 32:
                    str = "16款 Fiorino";
                    break;
                case 33:
                    str = "16款 Doblo";
                    break;
                case 34:
                    str = "16款 Aegea";
                    break;
                case 35:
                    str = "18款 Aegea";
                    break;
                case 36:
                    str = "19款 Aegea";
                    break;
                case 37:
                    str = "13款 500L";
                    break;
                case 38:
                    str = "19款 500L";
                    break;
                case 39:
                    str = "17款 Panda";
                    break;
                case 40:
                    str = "09款 Linea(FT05)";
                    break;
                default:
                    str = "未知";
                    break;
            }
        } else {
            str = "无效";
        }
        Log.i("ljq", "set0x26CarInfo: 车型： " + str);
    }

    private void initSettingsItem(Context context) {
        this.mSettingItemIndeHashMap = new HashMap<>();
        SparseArray sparseArray = new SparseArray();
        List<SettingPageUiSet.ListBean> list = getUiMgr(context).getSettingUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                String titleSrn = itemList.get(i2).getTitleSrn();
                sparseArray.append((i << 8) | i2, titleSrn);
                this.mSettingItemIndeHashMap.put(titleSrn, new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        this.mSettingItemIndeHashMap.put(str, new SettingUpdateEntity(-1, -1, null));
        return getSettingUpdateEntity(str);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, z ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = new DecimalFormat("00").format(i) + " " + str2 + "     ";
            } else {
                str4 = new DecimalFormat("00").format(i) + " 0" + str2 + "     ";
            }
        } else if (str2.length() == 5) {
            str4 = new DecimalFormat("00").format(i) + "  " + str2.substring(0, str2.length() - 1) + "    ";
        } else {
            str4 = new DecimalFormat("00").format(i) + " " + str2.substring(0, str2.length() - 1) + "    ";
        }
        byte[] bArr = {22, -31, allBandTypeData};
        if (isHave0xe1Info()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(bArr, str4.getBytes()));
        }
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return (byte) 4;
            case "MW":
            case "AM2":
                return (byte) 5;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
            case "OIRT":
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = String.format(new DecimalFormat("000").format(((b7 & 255) * 256) + (i & 255)), new Object[0]) + " " + new DecimalFormat("00").format(b4) + new DecimalFormat("00").format(b5) + "    ";
        byte b10 = b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        if (isHave0xe1Info()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -31, b10}, str7.getBytes()));
            try {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -28}, str4.getBytes("unicode")), 34));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -31}, 15));
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -28}, 34));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + " " + new DecimalFormat("00").format(b4) + new DecimalFormat("00").format(b5) + "    ";
        byte[] bArr = {22, -31, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED};
        if (isHave0xe1Info()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(bArr, str5.getBytes()));
        }
        FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        String str = "    " + new DecimalFormat("00").format(i / 60) + new DecimalFormat("00").format(i % 60) + "    ";
        byte[] bArr2 = {22, -31, 10};
        if (isHave0xe1Info()) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, str.getBytes()));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        if (isHave0xe1Info()) {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, "000".getBytes()), 15, 32));
        }
    }

    private String resolveOutDoorTem() {
        return ((float) ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusDoorInfoCopy)) {
            return true;
        }
        this.mCanBusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private int getData(int... iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i += iArr[i2] << (i2 * 8);
        }
        if (i == Math.pow(2.0d, iArr.length * 8) - 1.0d) {
            return -1;
        }
        return i;
    }

    private String getInfo(int i, float f, String str) {
        return i == -1 ? "---" : new DecimalFormat("0.0").format(i * f) + str;
    }
}
