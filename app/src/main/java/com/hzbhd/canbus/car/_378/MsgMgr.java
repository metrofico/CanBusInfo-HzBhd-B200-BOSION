package com.hzbhd.canbus.car._378;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    int Memory;
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
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
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 50) {
            setDriveInfo0x32();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
            return;
        }
        if (i != 114) {
            if (i != 115) {
                return;
            }
            setAirInfo0x73(byteArrayToIntArray);
        } else {
            updateSpeedInfo(byteArrayToIntArray[3]);
            setWheelKey0x72();
            setSpeedInfo0x72();
            setTrackData0x72();
            setRadarInfo0x72();
        }
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirInfo0x73(int[] iArr) {
        int iBlockBit = DataHandleUtils.blockBit(iArr[2], 7);
        iArr[2] = iBlockBit;
        int iBlockBit2 = DataHandleUtils.blockBit(iBlockBit, 5);
        iArr[2] = iBlockBit2;
        int iBlockBit3 = DataHandleUtils.blockBit(iBlockBit2, 1);
        iArr[2] = iBlockBit3;
        iArr[2] = DataHandleUtils.blockBit(iBlockBit3, 0);
        int iBlockBit4 = DataHandleUtils.blockBit(iArr[3], 7);
        iArr[3] = iBlockBit4;
        int iBlockBit5 = DataHandleUtils.blockBit(iBlockBit4, 5);
        iArr[3] = iBlockBit5;
        int iBlockBit6 = DataHandleUtils.blockBit(iBlockBit5, 3);
        iArr[3] = iBlockBit6;
        int iBlockBit7 = DataHandleUtils.blockBit(iBlockBit6, 2);
        iArr[3] = iBlockBit7;
        int iBlockBit8 = DataHandleUtils.blockBit(iBlockBit7, 1);
        iArr[3] = iBlockBit8;
        iArr[3] = DataHandleUtils.blockBit(iBlockBit8, 0);
        iArr[6] = DataHandleUtils.blockBit(iArr[6], 7);
        iArr[7] = 0;
        iArr[8] = 0;
        iArr[9] = 0;
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.mono = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr[3]);
            GeneralAirData.front_left_temperature = resolveTemp(iArr[4]);
            GeneralAirData.front_right_temperature = resolveTemp(iArr[5]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(iArr[6]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(iArr[6]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(iArr[6]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(iArr[6]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 4);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = iArr;
        return true;
    }

    private String resolveTemp(int i) {
        String str = ((i * 0.5d) - 40.0d) + getTempUnitC(this.mContext);
        if (i == 1) {
            str = "LOW";
        }
        return i == 255 ? "HIGH" : str;
    }

    private void setRadarInfo0x72() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[8], iArr[9], iArr[10], iArr[11]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackData0x72() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        if (i != 0) {
            GeneralParkData.trackAngle = -(i / 10);
        } else {
            GeneralParkData.trackAngle = iArr[7] / 10;
        }
        updateParkUi(null, this.mContext);
    }

    private void setSpeedInfo0x72() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("car_speed", this.mCanBusInfoInt[3] + "")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setWheelKey0x72() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            RealKeyClick(0);
            return;
        }
        if (i == 1) {
            RealKeyClick(7);
            return;
        }
        if (i == 2) {
            RealKeyClick(8);
            return;
        }
        if (i == 3) {
            RealKeyClick(3);
            return;
        }
        if (i == 5) {
            RealKeyClick(HotKeyConstant.K_1_PICKUP);
            return;
        }
        if (i == 6) {
            RealKeyClick(HotKeyConstant.K_2_HANGUP);
            return;
        }
        if (i != 32) {
            switch (i) {
                case 8:
                    RealKeyClick(21);
                    break;
                case 9:
                    RealKeyClick(20);
                    break;
                case 10:
                    RealKeyClick(151);
                    break;
            }
            return;
        }
        startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
    }

    private void RealKeyClick(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setDriveInfo0x32() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("engine_speed", sb.append((iArr[4] << 8) + iArr[5]).append("").toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_279_dashboard_data2", sb2.append((iArr2[6] << 8) + iArr2[7]).append("").toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDoorInfo0x12() {
        int i = this.Memory;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return;
        }
        this.Memory = i2;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i2);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 0}, 14));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int bandData = getBandData(str);
        getFreqByteHiLo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -46, (byte) bandData});
    }

    private int getBandData(String str) {
        if ("FM1".equals(str)) {
            return 1;
        }
        if ("FM2".equals(str)) {
            return 2;
        }
        if ("FM3".equals(str)) {
            return 3;
        }
        if ("AM1".equals(str)) {
            return 4;
        }
        return "AM2".equals(str) ? 5 : 0;
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, String.format("     %04d   ", Integer.valueOf((b7 << 8) | i)).getBytes());
        CanbusMsgSender.sendMsg(bArrByteMerger);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, String.format("     %04d   ", Integer.valueOf((b6 << 8) | i)).getBytes());
        CanbusMsgSender.sendMsg(bArrByteMerger);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, 14));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 11}, 14));
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
