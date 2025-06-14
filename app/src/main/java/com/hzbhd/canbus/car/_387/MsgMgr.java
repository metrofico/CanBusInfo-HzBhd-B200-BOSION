package com.hzbhd.canbus.car._387;

import android.content.Context;
import android.util.Log;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public boolean Down;
    public boolean Left;
    public boolean Right;
    public boolean Up;
    int differentId;
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
        initDriveItem(getUiMgr(context).getDriverDataPageUiSet(context));
        initSettingsItem(getUiMgr(context).getSettingUiSet(context));
        this.differentId = getCurrentCanDifferentId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            setPanelKey0x21();
            return;
        }
        if (i == 65) {
            setRadar0x41();
            return;
        }
        if (i == 120) {
            setSetting0x78();
            return;
        }
        if (i == 232) {
            setOriginalCarVideoStatus0xE8();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
            return;
        }
        if (i != 50) {
            switch (i) {
                case 17:
                    setWheelKey0x11();
                    setTrackDate0x11();
                    break;
                case 18:
                    setDoorInfo0x12();
                    break;
                case 19:
                    setDriveInfo0x13();
                    break;
            }
            return;
        }
        setDriveInfo0x32();
    }

    private void setTrackDate0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick0x11(0);
            return;
        }
        if (i == 1) {
            realKeyClick0x11(7);
            return;
        }
        if (i == 2) {
            realKeyClick0x11(8);
            return;
        }
        if (i == 3) {
            realKeyClick0x11(3);
            return;
        }
        if (i == 4) {
            realKeyClick0x11(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            realKeyClick0x11(14);
            return;
        }
        if (i == 8) {
            realKeyClick0x11(45);
            return;
        }
        if (i == 9) {
            realKeyClick0x11(46);
        } else if (i == 11) {
            realKeyClick0x11(2);
        } else {
            if (i != 24) {
                return;
            }
            realKeyClick0x11(33);
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realKeyClick0x21(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void setDoorInfo0x12() {
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setDriveInfo0x13() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("total_mileage", sb.append((((iArr[9] << 16) + (iArr[10] << 8)) + iArr[11]) / 10.0f).append("KM").toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setPanelKey0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick0x21(0);
        } else if (i != 37) {
            if (i == 43) {
                realKeyClick0x21(52);
                return;
            }
            if (i == 45) {
                realKeyClick0x21(59);
                return;
            }
            if (i == 55) {
                realKeyClick0x21(58);
                return;
            } else if (i == 69) {
                realKeyClick0x21(7);
                return;
            } else {
                if (i != 70) {
                    return;
                }
                realKeyClick0x21(8);
                return;
            }
        }
        realKeyClick0x21(128);
    }

    private void setAirInfo0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_window = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
        updateOutDoorTemp(this.mContext, ResolveOutDoorTemp(this.mCanBusInfoInt[13]));
        updateAirActivity(this.mContext, 1004);
    }

    private String ResolveOutDoorTemp(int i) {
        return i == 255 ? "---" : ((i * 0.5d) - 40.0d) + getTempUnitC(this.mContext);
    }

    private String ResolveTemp(int i) {
        String str = (i * 0.5d) + getTempUnitC(this.mContext);
        if (i == 254) {
            str = "LOW_TEMP";
        }
        return i == 255 ? "HIGH_TEMP" : str;
    }

    private void setRadar0x41() {
        GeneralParkData.strOnlyOneDistance = CommUtil.getStrByResId(this.mContext, "_387_item_12") + ":" + this.mCanBusInfoInt[11] + "CM";
        GeneralParkData.isShowLeftTopOneDistanceUi = true;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private boolean isRadarDataChange() {
        Arrays.equals(this.mRadarData, this.mCanBusInfoInt);
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setSetting0x78() {
        ArrayList arrayList = new ArrayList();
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        boolean boolBit22 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
        boolean boolBit12 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
        boolean boolBit02 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
        arrayList.add(checkEntity(helperSetValue("_387_item_01", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2)), boolBit5)));
        arrayList.add(checkEntity(helperSetValue("_387_item_02", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)), boolBit4)));
        arrayList.add(checkEntity(helperSetValue("_387_item_03", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1)), boolBit3)));
        arrayList.add(checkEntity(helperSetValue("_387_item_04", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2)), boolBit2)));
        arrayList.add(checkEntity(helperSetValue("_387_item_05", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 1)), boolBit1)));
        arrayList.add(checkEntity(helperSetValue("_387_item_06", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 1)), boolBit0)));
        arrayList.add(checkEntity(helperSetValue("_387_item_07", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1)), boolBit22)));
        arrayList.add(checkEntity(helperSetValue("_387_item_08", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 1)), boolBit12)));
        arrayList.add(checkEntity(helperSetValue("_387_item_09", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2)), boolBit02)));
        arrayList.add(checkEntity(helperSetValue("_387_item_10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 1)), boolBit7)));
        arrayList.add(checkEntity(helperSetValue("_387_item_11", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1)), boolBit6)));
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDriveInfo0x32() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("engine_speed", sb.append((iArr[4] << 8) + iArr[5]).append("").toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("a_current_speed", sb2.append(((iArr2[6] << 8) + iArr2[7]) / 10.0f).append("Km/h").toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setOriginalCarVideoStatus0xE8() {
        ArrayList arrayList = new ArrayList();
        forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
        switch (this.mCanBusInfoInt[3]) {
            case 5:
                arrayList.add(new PanoramicBtnUpdateEntity(0, true));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
                break;
            case 6:
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, true));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
                break;
            case 7:
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, true));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
                break;
            case 8:
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, true));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
                break;
            case 9:
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, true));
                break;
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        Log.d("lai", "dateTimeRepCanbus: " + (z ? 1 : 0));
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 0, (byte) i5, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0}, 12));
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
