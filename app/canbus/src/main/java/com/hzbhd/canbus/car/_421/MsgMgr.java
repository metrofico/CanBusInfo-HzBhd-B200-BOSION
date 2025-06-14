package com.hzbhd.canbus.car._421;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int mDifferentID;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private UiMgr mUiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferentID = getCurrentCanDifferentId();
        initSettingsItem(getUiMgr(context).getSettingUiSet(context));
        initDriveItem(getUiMgr(context).getDriverDataPageUiSet(context));
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) this.mDifferentID, 19});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mDifferentID = getCurrentCanDifferentId();
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            setWheelKey0x11();
            setTrackDate0x11();
            setBacklightLevel((this.mCanBusInfoInt[7] / 20) + 1);
            setDirveInfo0x11();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 33) {
            setPanelKey0x21();
            return;
        }
        if (i == 65) {
            setRadarInfo0x41();
            return;
        }
        if (i == 69) {
            setBackPanoramic0x45();
            return;
        }
        if (i == 135) {
            setSettingInfo0x87();
            return;
        }
        if (i == 232) {
            setPanoramicVideo0xE8();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
        } else if (i == 49) {
            setAirInfo0x31();
        } else {
            if (i != 50) {
                return;
            }
            setDriveInfo0x32();
        }
    }

    private void setDirveInfo0x11() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_421_Item_11", this.mCanBusInfoInt[3] + "km/h")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveInfo0x32() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("engine_speed", sb.append((iArr[4] << 8) + iArr[5]).append("r/min").toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("a_current_speed", sb2.append((iArr2[6] << 8) + iArr2[7]).append("km/h").toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSettingInfo0x87() {
        Boolean boolValueOf = Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        Boolean boolValueOf2 = Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
        Boolean boolValueOf3 = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        Boolean boolValueOf4 = Boolean.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        Boolean boolValueOf5 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        Boolean boolValueOf6 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        Boolean boolValueOf7 = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        Boolean boolValueOf8 = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        Boolean boolValueOf9 = Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        Boolean boolValueOf10 = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkEntity(helperSetValue("_421_Item_01", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)), boolValueOf4.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_02", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3)), boolValueOf5.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_03", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)), boolValueOf6.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_04", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1)), boolValueOf7.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_05", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1)), boolValueOf8.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_06", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)), boolValueOf9.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_07", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)), boolValueOf10.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_08", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)), boolValueOf.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_09", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)), boolValueOf2.booleanValue())));
        arrayList.add(checkEntity(helperSetValue("_421_Item_10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)), boolValueOf3.booleanValue())));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setPanoramicVideo0xE8() {
        if (isDataChange(this.mCanBusInfoInt)) {
            switchFCamera(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        }
    }

    private void setBackPanoramic0x45() {
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        if (intFromByteWithBit == 0) {
            arrayList.add(new PanoramicBtnUpdateEntity(0, true));
            arrayList.add(new PanoramicBtnUpdateEntity(1, false));
        } else if (intFromByteWithBit == 1) {
            arrayList.add(new PanoramicBtnUpdateEntity(0, false));
            arrayList.add(new PanoramicBtnUpdateEntity(1, true));
        } else if (intFromByteWithBit == 2) {
            arrayList.add(new PanoramicBtnUpdateEntity(2, true));
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setRadarInfo0x41() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationDataType2(2, iArr[2], 3, iArr[3], 3, iArr[4], 2, iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationDataType2(2, iArr2[6], 3, iArr2[7], 3, iArr2[8], 2, iArr2[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setPanelKey0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick0x21(0);
            return;
        }
        if (i == 1) {
            realKeyClick0x21(1);
            return;
        }
        if (i == 9) {
            realKeyClick0x21(3);
            return;
        }
        if (i == 43) {
            realKeyClick0x21(52);
            return;
        }
        if (i == 45) {
            realKeyClick0x21(59);
        } else if (i == 69) {
            realKeyClick0x21(7);
        } else {
            if (i != 70) {
                return;
            }
            realKeyClick0x21(8);
        }
    }

    private void realKeyClick0x21(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void setWheelKey0x11() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyClick0x11(0);
                return;
            case 1:
                realKeyClick0x11(7);
                return;
            case 2:
                realKeyClick0x11(8);
                return;
            case 3:
                realKeyClick0x11(3);
                return;
            case 4:
                realKeyClick0x11(HotKeyConstant.K_SPEECH);
                return;
            case 5:
                realKeyClick0x11(14);
                return;
            case 6:
                realKeyClick0x11(15);
                return;
            case 7:
            case 10:
            case 11:
            default:
                return;
            case 8:
                realKeyClick0x11(90);
                return;
            case 9:
                realKeyClick0x11(91);
                break;
            case 12:
                realKeyClick0x11(2);
                return;
            case 13:
                break;
            case 14:
                realKeyClick0x11(45);
                return;
            case 15:
                realKeyClick0x11(49);
                return;
            case 16:
                realKeyClick0x11(50);
                return;
        }
        realKeyClick0x11(46);
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setTrackDate0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setDoorInfo0x12() {
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

    private void setAirInfo0x31() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            int i = this.mCanBusInfoInt[6];
            if (i == 0) {
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 12) {
                int i2 = this.mDifferentID;
                if (i2 == 2 || i2 == 3) {
                    return;
                }
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_defog = true;
            } else if (i == 2) {
                GeneralAirData.front_defog = true;
            } else if (i == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 5) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 6) {
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            }
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private String ResolveTemp(int i) {
        String str;
        str = "LOW_TEMP";
        if (this.mDifferentID != 3) {
            if (this.mCanBusInfoInt[8] >= 9) {
                return i == 16 ? "HIGH_TEMP" : CommUtil.getStrByResId(this.mContext, "_421_Item_13") + this.mCanBusInfoInt[8];
            }
            String str2 = CommUtil.getStrByResId(this.mContext, "_421_Item_12") + this.mCanBusInfoInt[8];
            if (i != 1) {
                return str2;
            }
        } else {
            str = i != 1 ? ((i * 0.5d) + 17.0d) + getTempUnitC(this.mContext) : "LOW_TEMP";
            if (i == 31) {
                return "HIGH_TEMP";
            }
        }
        return str;
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

    public void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }
}
