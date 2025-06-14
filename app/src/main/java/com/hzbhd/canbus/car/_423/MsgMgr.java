package com.hzbhd.canbus.car._423;

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
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int TuneValue;
    private static int VolumeValue;
    Boolean PanoramicVideo = false;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int mDifferentID;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private UiMgr mUiMgr;
    SystemButton systemButton;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        initDriveItem(getUiMgr(context).getDriverDataPageUiSet(context));
        int currentCanDifferentId = getCurrentCanDifferentId();
        this.mDifferentID = currentCanDifferentId;
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) currentCanDifferentId, 49});
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
            setBacklightLevel((this.mCanBusInfoInt[7] / 20) + 1);
            return;
        }
        if (i == 26) {
            setDoorInfo0x1A();
            setDirveInfo0x1A();
            setTrackDate0x1A();
            forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]));
            int[] iArr = this.mCanBusInfoInt;
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[5], iArr[6]));
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
            return;
        }
        if (i == 65) {
            setRadarInfo0x41();
            return;
        }
        if (i == 232) {
            setPanoramicVideo0xE8();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
        } else if (i == 33) {
            setPanelKey0x21();
        } else {
            if (i != 34) {
                return;
            }
            setPanelKey0x22();
        }
    }

    private void setPanoramicVideo0xE8() {
        if (isDataChange(this.mCanBusInfoInt)) {
            switchFCamera(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
        }
        ArrayList arrayList = new ArrayList();
        this.PanoramicVideo = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]));
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setPanelKey0x22() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyClick0x22(0);
            return;
        }
        if (i == 1) {
            int i2 = VolumeValue - iArr[3];
            if (i2 < 0) {
                PanelKnobClick(7, Math.abs(i2));
            } else if (i2 > 0) {
                PanelKnobClick(8, Math.abs(i2));
            }
            VolumeValue = this.mCanBusInfoInt[3];
            return;
        }
        if (i != 2) {
            return;
        }
        int i3 = TuneValue - iArr[3];
        if (i3 < 0) {
            PanelKnobClick(7, Math.abs(i3));
        } else if (i3 > 0) {
            PanelKnobClick(8, Math.abs(i3));
        }
        TuneValue = this.mCanBusInfoInt[3];
    }

    private void PanelKnobClick(int i, int i2) {
        realKeyClick3(this.mContext, i, i2, 1);
    }

    private void realKeyClick0x22(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private void setDirveInfo0x1A() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("engine_speed", sb.append((iArr[11] << 8) + iArr[12]).append("r/min").toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("a_current_speed", sb2.append((iArr2[5] << 8) + iArr2[6]).append("km/h").toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setRadarInfo0x41() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(3, iArr2[6], 255, 255, iArr2[9]);
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
        if (i == 6) {
            realKeyClick0x21(50);
            return;
        }
        if (i == 43) {
            realKeyClick0x21(52);
            return;
        }
        if (i == 65) {
            realKeyClick0x21(33);
            return;
        }
        if (i == 69) {
            realKeyClick0x21(7);
            return;
        }
        if (i != 70) {
            switch (i) {
                case 22:
                    realKeyClick0x21(49);
                    break;
                case 23:
                    realKeyClick0x21(45);
                    break;
                case 24:
                    realKeyClick0x21(46);
                    break;
                case 25:
                    realKeyClick0x21(47);
                    break;
                case 26:
                    realKeyClick0x21(48);
                    break;
            }
            return;
        }
        realKeyClick0x21(8);
    }

    private void realKeyClick0x21(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void setWheelKey0x11() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyClick0x11(0);
                break;
            case 1:
                realKeyClick0x11(7);
                break;
            case 2:
                realKeyClick0x11(8);
                break;
            case 3:
                realKeyClick0x11(3);
                break;
            case 4:
                realKeyClick0x11(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                realKeyClick0x11(14);
                break;
            case 6:
                realKeyClick0x11(15);
                break;
            case 8:
                realKeyClick0x11(45);
                break;
            case 9:
                realKeyClick0x11(46);
                break;
            case 12:
                realKeyClick0x11(2);
                break;
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setTrackDate0x1A() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setDoorInfo0x1A() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            updateDoorView(this.mContext);
        }
    }

    private void setAirInfo0x31() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
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
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
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
        int i2 = this.mCanBusInfoInt[8];
        str = "LOW_TEMP";
        if (i2 > 16) {
            str = i != 254 ? (i * 0.5d) + getTempUnitC(this.mContext) : "LOW_TEMP";
            if (i == 255) {
                return "HIGH_TEMP";
            }
        } else {
            if (i2 >= 9) {
                return i == 16 ? "HIGH_TEMP" : CommUtil.getStrByResId(this.mContext, "_421_Item_13") + this.mCanBusInfoInt[8];
            }
            String str2 = CommUtil.getStrByResId(this.mContext, "_421_Item_12") + this.mCanBusInfoInt[8];
            if (i != 1) {
                return str2;
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

    public void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void showButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._423.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._423.MsgMgr.1.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicInfo0xFD();
                            MsgMgr.this.switchFCamera(MsgMgr.this.mContext, !MsgMgr.this.PanoramicVideo.booleanValue());
                        }
                    });
                }
                MsgMgr.this.systemButton.show();
            }
        });
    }

    public void hideButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._423.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._423.MsgMgr.2.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicInfo0xFD();
                        }
                    });
                }
                MsgMgr.this.systemButton.hide();
            }
        });
    }
}
