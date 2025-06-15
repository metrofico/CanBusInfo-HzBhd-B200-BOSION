package com.hzbhd.canbus.car._386;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_386_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
    static final String SHARE_386_AMPLIFIER_BASS = "share_122_amplifier_bass";
    static final String SHARE_386_AMPLIFIER_FADE = "share_122_amplifier_fade";
    static final String SHARE_386_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
    static final String SHARE_386_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
    static final String SHARE_386_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
    static final int _386_AMPLIFIER_OFFSET = 0;
    static final int _386_AMPLIFIER_RANGE = 9;
    public boolean Down;
    public boolean Left;
    public boolean Right;
    public boolean Up;
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private UiMgr mUiMgr;
    int memory;
    int memory2;
    int volume;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
        initDriveItem(getUiMgr(context).getDriverDataPageUiSet(context));
        initSettingsItem(getUiMgr(this.mContext).getSettingUiSet(context));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        AMapUtils.getInstance().startAMap(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        super.onAMapCallBack(aMapEntity);
        switch (aMapEntity.getCarDirection()) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 0});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 4});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 1});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 5});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 2});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 7});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 3});
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, -28, 0, 0, 52, 52, 6});
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setWheelKey0x11();
            setTrackDate0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
            return;
        }
        if (i == 50) {
            setCarInfo0x32();
            return;
        }
        if (i == 65) {
            setRadarInfo0x41();
            return;
        }
        if (i == 98) {
            setSettingInfo0x62();
            return;
        }
        if (i == 166) {
            setAmplifierInfo0xA6();
        } else if (i == 232) {
            setOriginalCarVideoStatus0xE8();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setOriginalCarVideoStatus0xE8() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
        this.Right = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5])));
        arrayList.add(new PanoramicBtnUpdateEntity(2, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])));
        this.Left = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        arrayList.add(new PanoramicBtnUpdateEntity(3, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7])));
        this.Up = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
        arrayList.add(new PanoramicBtnUpdateEntity(4, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8])));
        this.Down = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]);
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[4] != 0) {
            forceReverse(this.mContext, true);
            return;
        }
        if (iArr[5] != 0) {
            forceReverse(this.mContext, true);
            return;
        }
        if (iArr[6] != 0) {
            forceReverse(this.mContext, true);
            return;
        }
        if (iArr[7] != 0) {
            forceReverse(this.mContext, true);
        } else if (iArr[8] != 0) {
            forceReverse(this.mContext, true);
        } else {
            forceReverse(this.mContext, false);
        }
    }

    private void setSettingInfo0x62() {
        int i;
        int i2;
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        boolean boolBit72 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        boolean boolBit52 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        boolean boolBit32 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) == this.memory) {
            i = 6;
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2) == this.memory2) {
                i2 = 2;
            }
            arrayList.add(checkEntity(helperSetValue("_386_seat_chirapsia_driver", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, i2))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
            arrayList.add(checkEntity(helperSetValue("_386_lumbar_support_driver", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3))));
            arrayList.add(checkEntity(helperSetValue("_386_seat_chirapsia_copilot", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
            arrayList.add(checkEntity(helperSetValue("_386_lumbar_support_copilot", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 3))));
            arrayList.add(checkEntity(helperSetValue("_386_item_14", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2)), boolBit7)));
            arrayList.add(checkEntity(helperSetValue("_386_item_15", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2)), boolBit6)));
            arrayList.add(checkEntity(helperSetValue("_386_item_16", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2)), boolBit5)));
            arrayList.add(checkEntity(helperSetValue("_386_item_17", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)), boolBit4)));
            arrayList.add(checkEntity(helperSetValue("_386_item_18", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)), boolBit3)));
            arrayList.add(checkEntity(helperSetValue("_386_item_19", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)), boolBit72)));
            arrayList.add(checkEntity(helperSetValue("_386_item_20", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2)), boolBit62)));
            arrayList.add(checkEntity(helperSetValue("_386_item_21", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)), boolBit52)));
            arrayList.add(checkEntity(helperSetValue("_386_item_22", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)), boolBit42)));
            arrayList.add(checkEntity(helperSetValue("_386_item_23", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1)), boolBit32)));
            arrayList.add(checkEntity(helperSetValue("_386_item_25", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)), boolBit2)));
            arrayList.add(checkEntity(helperSetValue("_386_item_26", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))));
            arrayList.add(checkEntity(helperSetValue("_386_item_27", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1)), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
        i = 6;
        i2 = 2;
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], i, 2);
        this.memory = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], i, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], i, 2);
        this.memory2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], i, 2);
        updateAirActivity(this.mContext, 1001);
        arrayList.add(checkEntity(helperSetValue("_386_seat_chirapsia_driver", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, i2))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        arrayList.add(checkEntity(helperSetValue("_386_lumbar_support_driver", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3))));
        arrayList.add(checkEntity(helperSetValue("_386_seat_chirapsia_copilot", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
        arrayList.add(checkEntity(helperSetValue("_386_lumbar_support_copilot", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 3))));
        arrayList.add(checkEntity(helperSetValue("_386_item_14", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2)), boolBit7)));
        arrayList.add(checkEntity(helperSetValue("_386_item_15", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2)), boolBit6)));
        arrayList.add(checkEntity(helperSetValue("_386_item_16", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2)), boolBit5)));
        arrayList.add(checkEntity(helperSetValue("_386_item_17", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)), boolBit4)));
        arrayList.add(checkEntity(helperSetValue("_386_item_18", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)), boolBit3)));
        arrayList.add(checkEntity(helperSetValue("_386_item_19", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)), boolBit72)));
        arrayList.add(checkEntity(helperSetValue("_386_item_20", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2)), boolBit62)));
        arrayList.add(checkEntity(helperSetValue("_386_item_21", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)), boolBit52)));
        arrayList.add(checkEntity(helperSetValue("_386_item_22", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)), boolBit42)));
        arrayList.add(checkEntity(helperSetValue("_386_item_23", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1)), boolBit32)));
        arrayList.add(checkEntity(helperSetValue("_386_item_25", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)), boolBit2)));
        arrayList.add(checkEntity(helperSetValue("_386_item_26", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1)), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))));
        arrayList.add(checkEntity(helperSetValue("_386_item_27", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1)), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRadarInfo0x41() {
        getUiMgr(this.mContext).getParkPageUiSet(this.mContext).setShowRadar(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]));
        int i = this.mCanBusInfoInt[13];
        if (i == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationDataType2(4, iArr[2], 10, iArr[3], 10, iArr[4], 4, iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationDataType2(4, iArr2[6], 7, iArr2[7], 7, iArr2[8], 4, iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        } else if (i == 1) {
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            int[] iArr3 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(iArr3[2], iArr3[3], iArr3[4], iArr3[5]);
            int[] iArr4 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarDistanceData(iArr4[6], iArr4[7], iArr4[8], iArr4[9]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        }
        updateParkUi(null, this.mContext);
    }

    private void setCarInfo0x32() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_1", sb.append((iArr[4] << 8) + iArr[5]).append("").toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_2", sb2.append((iArr2[6] << 8) + iArr2[7]).append("").toString())));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_3", (this.mCanBusInfoInt[8] / 10.0f) + "V")));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_4", (this.mCanBusInfoInt[9] * 4.5d) + "hPa")));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_5", (this.mCanBusInfoInt[11] - 40) + getTempUnitC(this.mContext))));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_6", resolveData(this.mCanBusInfoInt[12]))));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_7", resolveDataOne(this.mCanBusInfoInt[13]))));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_8", this.mCanBusInfoInt[14] + getTempUnitC(this.mContext))));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_386_item_9", this.mCanBusInfoInt[15] + "%")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    private String resolveDataOne(int i) {
        String strByResId = CommUtil.getStrByResId(this.mContext, "_386_item_13");
        if (DataHandleUtils.getBoolBit7(i)) {
            strByResId = CommUtil.getStrByResId(this.mContext, "_386_item_12");
        }
        return strByResId + DataHandleUtils.getIntFromByteWithBit(i, 0, 7) + "°";
    }

    private String resolveData(int i) {
        String strByResId = CommUtil.getStrByResId(this.mContext, "_386_item_11");
        if (DataHandleUtils.getBoolBit7(i)) {
            strByResId = CommUtil.getStrByResId(this.mContext, "_386_item_10");
        }
        return strByResId + DataHandleUtils.getIntFromByteWithBit(i, 0, 7) + "°";
    }

    private void setAmplifierInfo0xA6() {
        ArrayList arrayList = new ArrayList();
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        int[] iArr = this.mCanBusInfoInt;
        this.volume = iArr[2];
        GeneralAmplifierData.bandBass = iArr[5];
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 10;
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 10;
        arrayList.add(checkEntity(helperSetValue("speed_linkage_volume_adjustment", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2)))));
        arrayList.add(checkEntity(helperSetValue("surround_sound", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2)))));
        updateAmplifierActivity(null);
        saveAmplifierData();
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, SHARE_386_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_386_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_386_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_386_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_386_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_386_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_386_AMPLIFIER_VOLUME, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_386_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_386_AMPLIFIER_FADE, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_386_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_386_AMPLIFIER_MIDDLE, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_386_AMPLIFIER_TREBLE, 0);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
        final byte[][] bArr = {new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 0 + 9)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 0 + 9)}, new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 0)}, new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 0)}, new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 0)}};
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._386.MsgMgr.1
            int index = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.index;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    MsgMgr.this.finishTimer();
                } else {
                    this.index = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        };
        startTimer(100L, 100);
    }

    private void startTimer(long j, int i) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    private void setAirInfo0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_window = false;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = ResolveTemp(this.mCanBusInfoInt[9]);
        int i2 = this.mCanBusInfoInt[10];
        if (i2 == 0) {
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_window = false;
        } else if (i2 == 1) {
            GeneralAirData.rear_auto_wind_model = true;
        } else if (i2 == 2) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_window = false;
        } else if (i2 == 3) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_window = false;
        } else if (i2 == 4) {
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_window = false;
        }
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        GeneralAirData.rear_temperature = ResolveTemp(this.mCanBusInfoInt[12]);
        updateAirActivity(this.mContext, 1004);
    }

    private String ResolveTemp(int i) {
        String str = (i * 0.5d) + getTempUnitC(this.mContext);
        if (i == 254) {
            str = "LOW_TEMP";
        }
        return i == 255 ? "HIGH_TEMP" : str;
    }

    private void setDoorInfo0x12() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
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
        if (i == 5) {
            realKeyClick0x11(14);
            return;
        }
        if (i == 6) {
            realKeyClick0x11(15);
            return;
        }
        if (i == 8) {
            realKeyClick0x11(45);
        } else if (i == 9) {
            realKeyClick0x11(46);
        } else {
            if (i != 12) {
                return;
            }
            realKeyClick0x11(2);
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
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

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettingPano(int i, boolean z) {
        if (i == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, z));
            arrayList.add(new PanoramicBtnUpdateEntity(1, false));
            arrayList.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
            return;
        }
        if (i == 2) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(2, z));
            arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
            GeneralParkData.dataList = arrayList2;
            updateParkUi(null, this.mContext);
            return;
        }
        if (i == 3) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new PanoramicBtnUpdateEntity(0, false));
            arrayList3.add(new PanoramicBtnUpdateEntity(1, false));
            arrayList3.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList3.add(new PanoramicBtnUpdateEntity(3, z));
            arrayList3.add(new PanoramicBtnUpdateEntity(4, false));
            GeneralParkData.dataList = arrayList3;
            updateParkUi(null, this.mContext);
            return;
        }
        if (i != 4) {
            return;
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new PanoramicBtnUpdateEntity(0, false));
        arrayList4.add(new PanoramicBtnUpdateEntity(1, false));
        arrayList4.add(new PanoramicBtnUpdateEntity(2, false));
        arrayList4.add(new PanoramicBtnUpdateEntity(3, false));
        arrayList4.add(new PanoramicBtnUpdateEntity(4, z));
        GeneralParkData.dataList = arrayList4;
        updateParkUi(null, this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 0, (byte) i8, (byte) i6}, 12));
    }
}
