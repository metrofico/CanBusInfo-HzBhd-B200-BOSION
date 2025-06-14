package com.hzbhd.canbus.car._377;

import android.content.Context;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int volKnobValue;
    private static int volKnobValueRadio;
    String ACUnit;
    String CurrentOrHistoryACUnit;
    int DoorMemory;
    String InstantConsumptionUnit;
    String RMUnit;
    String TripAUnit;
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private int[] mRadarData;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private UiMgr mUiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initDriveItem(getUiMgr(this.mContext).getDriverDataPageUiSet(this.mContext));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKey0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 22) {
            ResolveUnit();
            setFuelConsumptionMileage0x16();
            return;
        }
        if (i == 23) {
            setHistoryConsumptionMileage0x17();
            return;
        }
        if (i == 33) {
            setPanelKey0x21();
            return;
        }
        if (i == 34) {
            setPanelKey0x22();
        } else if (i == 65) {
            setRadar0x41();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setHistoryConsumptionMileage0x17() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_8", sb.append((((iArr[2] << 16) + (iArr[3] << 8)) + iArr[4]) / 10.0f).append(this.TripAUnit).toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_9", sb2.append(((iArr2[5] << 8) + iArr2[6]) / 10.0f).append(this.ACUnit).toString())));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_10", sb3.append((((iArr3[7] << 16) + (iArr3[8] << 8)) + iArr3[9]) / 10.0f).append(this.TripAUnit).toString())));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_11", sb4.append(((iArr4[10] << 8) + iArr4[11]) / 10.0f).append(this.ACUnit).toString())));
        StringBuilder sb5 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_12", sb5.append((((iArr5[12] << 16) + (iArr5[13] << 8)) + iArr5[14]) / 10.0f).append(this.TripAUnit).toString())));
        StringBuilder sb6 = new StringBuilder();
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_13", sb6.append(((iArr6[15] << 8) + iArr6[16]) / 10.0f).append(this.ACUnit).toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void ResolveUnit() {
        this.RMUnit = " km";
        this.TripAUnit = " km";
        this.ACUnit = " mpg";
        this.CurrentOrHistoryACUnit = " mpg";
        this.InstantConsumptionUnit = " mpg";
        int i = this.mCanBusInfoInt[14];
        if (DataHandleUtils.getBoolBit7(i)) {
            this.RMUnit = " mile";
        }
        if (DataHandleUtils.getBoolBit6(i)) {
            this.TripAUnit = " mile";
        }
        if (DataHandleUtils.getIntFromByteWithBit(i, 4, 2) == 1) {
            this.ACUnit = " km/L";
        }
        if (DataHandleUtils.getIntFromByteWithBit(i, 4, 2) == 2) {
            this.ACUnit = " l/100km";
        }
        if (DataHandleUtils.getIntFromByteWithBit(i, 2, 2) == 1) {
            this.CurrentOrHistoryACUnit = " km/L";
        }
        if (DataHandleUtils.getIntFromByteWithBit(i, 2, 2) == 2) {
            this.CurrentOrHistoryACUnit = " l/100km";
        }
        if (DataHandleUtils.getIntFromByteWithBit(i, 0, 2) == 1) {
            this.InstantConsumptionUnit = " km/L";
        }
        if (DataHandleUtils.getIntFromByteWithBit(i, 0, 2) == 2) {
            this.InstantConsumptionUnit = " l/100km";
        }
    }

    private void setFuelConsumptionMileage0x16() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_1", this.mCanBusInfoInt[2] + "")));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_2", sb.append(((iArr[3] << 8) + iArr[4]) / 10.0f).append(this.CurrentOrHistoryACUnit).toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_3", sb2.append(((iArr2[5] << 8) + iArr2[6]) / 10.0f).append(this.CurrentOrHistoryACUnit).toString())));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_4", sb3.append(((iArr3[7] << 8) + iArr3[8]) / 10.0f).append(this.ACUnit).toString())));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_5", sb4.append((((iArr4[9] << 16) + (iArr4[10] << 8)) + iArr4[11]) / 10.0f).append(this.TripAUnit).toString())));
        StringBuilder sb5 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_6", sb5.append(((iArr5[12] << 8) + iArr5[13]) / 10.0f).append(this.RMUnit).toString())));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_377_item_7", resolveData13(this.mCanBusInfoInt[15]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveData13(int i) {
        return new int[]{60, 10, 12, 20, 30, 40, 50, 70, 80, 90, 100}[i] + "";
    }

    private void setRadar0x41() {
        if (isRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private boolean isRadarDataChange() {
        Arrays.equals(this.mRadarData, this.mCanBusInfoInt);
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setPanelKey0x22() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            int i2 = volKnobValue - iArr[3];
            if (i2 < 0) {
                PanelKnobClick(7, Math.abs(i2));
            } else if (i2 > 0) {
                PanelKnobClick(8, Math.abs(i2));
            }
            volKnobValue = this.mCanBusInfoInt[3];
            return;
        }
        if (i != 2) {
            return;
        }
        int i3 = volKnobValueRadio - iArr[3];
        if (i3 < 0) {
            PanelKnobClick(46, Math.abs(i3));
        } else if (i3 > 0) {
            PanelKnobClick(45, Math.abs(i3));
        }
        volKnobValueRadio = this.mCanBusInfoInt[3];
    }

    private void PanelKnobClick(int i, int i2) {
        realKeyClick3(this.mContext, i, i2, 1);
    }

    private void setPanelKey0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realPanelKeyClick(0);
            return;
        }
        if (i == 1) {
            realPanelKeyClick(1);
            return;
        }
        if (i == 2) {
            realPanelKeyClick(21);
            return;
        }
        if (i == 3) {
            realPanelKeyClick(20);
            return;
        }
        if (i == 6) {
            realPanelKeyClick(50);
            return;
        }
        if (i == 44) {
            realPanelKeyClick(2);
            return;
        }
        if (i == 47) {
            realPanelKeyClick(151);
            return;
        }
        if (i == 49) {
            realPanelKeyClick(HotKeyConstant.K_DARK_MODE);
            return;
        }
        if (i == 55) {
            realPanelKeyClick(2);
            return;
        }
        if (i == 61) {
            realPanelKeyClick(139);
            return;
        }
        if (i == 97) {
            realPanelKeyClick(35);
            return;
        }
        if (i == 71) {
            realPanelKeyClick(76);
            return;
        }
        if (i == 72) {
            realPanelKeyClick(77);
            return;
        }
        if (i == 75) {
            realPanelKeyClick(HotKeyConstant.K_ACTION_RADIO);
            return;
        }
        if (i != 76) {
            switch (i) {
                case 8:
                    realPanelKeyClick(59);
                    break;
                case 9:
                    realPanelKeyClick(3);
                    break;
                case 10:
                    realPanelKeyClick(33);
                    break;
                case 11:
                    realPanelKeyClick(34);
                    break;
                case 12:
                    realPanelKeyClick(35);
                    break;
                case 13:
                    realPanelKeyClick(36);
                    break;
                case 14:
                    realPanelKeyClick(37);
                    break;
                case 15:
                    realPanelKeyClick(38);
                    break;
                case 16:
                    realPanelKeyClick(95);
                    break;
                default:
                    switch (i) {
                        case 22:
                            realPanelKeyClick(49);
                            break;
                        case 23:
                            realPanelKeyClick(45);
                            break;
                        case 24:
                            realPanelKeyClick(46);
                            break;
                    }
            }
            return;
        }
        realPanelKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
    }

    private void setDoorInfo0x12() {
        int i = this.mCanBusInfoInt[4];
        if (i == this.DoorMemory) {
            return;
        }
        this.DoorMemory = i;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 4) {
            realKeyClick(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            realKeyClick(HotKeyConstant.K_1_PICKUP);
            return;
        }
        if (i == 6) {
            realKeyClick(HotKeyConstant.K_2_HANGUP);
            return;
        }
        if (i != 16) {
            switch (i) {
                case 8:
                    realKeyClick(45);
                    break;
                case 9:
                    realKeyClick(46);
                    break;
                case 10:
                    realKeyClick(2);
                    break;
            }
            return;
        }
        realKeyClick(33);
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realPanelKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        this.mSettingItemHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                this.mSettingItemHashMap.put(itemListBean.getTitleSrn(), new SettingUpdateHelper(new SettingUpdateEntity(i, i2, "null_value"), itemListBean.getMin()));
            }
        }
    }

    private void initDriveItem(DriverDataPageUiSet driverDataPageUiSet) {
        this.mDriveItemHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = driverDataPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemHashMap.put(itemList.get(i2).getTitleSrn(), new DriveDataUpdateHelper(new DriverUpdateEntity(i, i2, "null_value")));
            }
        }
    }

    private SettingUpdateEntity checkEntity(SettingUpdateEntity settingUpdateEntity) {
        if (settingUpdateEntity.getLeftListIndex() == -1 || settingUpdateEntity.getRightListIndex() == -1) {
            return null;
        }
        return settingUpdateEntity;
    }

    private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity driverUpdateEntity) {
        if (driverUpdateEntity.getPage() == -1 || driverUpdateEntity.getIndex() == -1) {
            return null;
        }
        return driverUpdateEntity;
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj);
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj, boolean z) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value").setEnable(false)));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj).setEnable(z);
    }

    private DriverUpdateEntity helperSetDriveDataValue(String str, String str2) {
        if (!this.mDriveItemHashMap.containsKey(str)) {
            this.mDriveItemHashMap.put(str, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
        }
        return this.mDriveItemHashMap.get(str).setValue(str2);
    }

    private static class DriveDataUpdateHelper {
        private DriverUpdateEntity entity;

        public DriveDataUpdateHelper(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public void setEntity(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public DriverUpdateEntity getEntity() {
            return this.entity;
        }

        public DriverUpdateEntity setValue(String str) {
            this.entity.setValue(str);
            return this.entity;
        }
    }

    private static class SettingUpdateHelper {
        private SettingUpdateEntity entity;
        private int progressMin;

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity) {
            this(settingUpdateEntity, 0);
        }

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity, int i) {
            this.entity = settingUpdateEntity;
            this.progressMin = i;
        }

        public SettingUpdateEntity getEntity() {
            return this.entity;
        }

        public SettingUpdateEntity setValue(Object obj) {
            if (obj instanceof Integer) {
                Integer num = (Integer) obj;
                this.entity.setValue(Integer.valueOf(num.intValue() + this.progressMin));
                this.entity.setProgress(num.intValue());
            } else {
                this.entity.setValue(obj);
            }
            return this.entity;
        }

        public SettingUpdateEntity setEnable(boolean z) {
            this.entity.setEnable(z);
            return this.entity;
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
