package com.hzbhd.canbus.car._453;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    private int differentId;
    private int eachId;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    int[] mCarDoorData;
    private Context mContext;
    int[] mDoorInfo;
    int[] mFrontRadarData;
    int[] mRearRadarData;
    int[] mSettingdInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;

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
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14(byteArrayToIntArray);
            return;
        }
        if (i == 32) {
            set0x20Swc(byteArrayToIntArray);
            return;
        }
        if (i == 39) {
            set0x27AirInfo(byteArrayToIntArray);
            return;
        }
        if (i == 41) {
            set0x29(byteArrayToIntArray);
            return;
        }
        if (i == 48) {
            set0x30(bArr);
            return;
        }
        if (i != 56) {
            switch (i) {
                case 34:
                    set0x22(byteArrayToIntArray);
                    break;
                case 35:
                    set0x23(byteArrayToIntArray);
                    break;
                case 36:
                    set0x24DoorInfo(byteArrayToIntArray);
                    break;
            }
            return;
        }
        set0x38();
    }

    private void set0x38() {
        if (isSettingsInfoChange(this.mCanBusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_1", "_453_park_assist_tone"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_1", "_453_park_assist_delay_timer"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_1", "_453_park_assist"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_2", "_453_vehicle_auto_relock"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_2", "_453_door_unlocking"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_exterior_litghts_approach_lamps"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_mode"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_front"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_rear"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_color"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_mood_lighting_lumim"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_453_setting_modular_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_453_setting_modular_3", "_453_drive_away_locking"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x30(byte[] bArr) {
        updateVersionInfo(this.mContext, getVersionStr(bArr));
    }

    private void set0x29(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 21632, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x23(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(6, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22(int[] iArr) {
        if (isRearRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(6, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x27AirInfo(int[] iArr) {
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4);
            int i = iArr[4];
            if (i == 255) {
                GeneralAirData.front_left_temperature = "HI";
            } else if (i == 0) {
                GeneralAirData.front_left_temperature = "LO";
            } else {
                GeneralAirData.front_left_temperature = this.df_1Decimal.format(iArr[4] / 2.0f) + getTempUnitC(this.mContext);
            }
            int i2 = iArr[5];
            if (i2 == 255) {
                GeneralAirData.front_right_temperature = "HI";
            } else if (i2 == 0) {
                GeneralAirData.front_right_temperature = "LO";
            } else {
                GeneralAirData.front_right_temperature = this.df_1Decimal.format(iArr[5] / 2.0f) + getTempUnitC(this.mContext);
            }
            GeneralAirData.eco = DataHandleUtils.getBoolBit1(iArr[6]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x24DoorInfo(int[] iArr) {
        if (isDoorInfoChange(iArr) && DataHandleUtils.getBoolBit0(iArr[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x20Swc(int[] iArr) {
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 15, iArr[3]);
                break;
            case 9:
                int i = iArr[3];
                if (i == 2) {
                    realKeyLongClick1(this.mContext, 1, i);
                    break;
                } else {
                    realKeyLongClick1(this.mContext, 3, i);
                    break;
                }
            case 10:
                realKeyClick4(this.mContext, 7);
                break;
            case 11:
                realKeyClick4(this.mContext, 8);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 52, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 63, iArr[3]);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 5, iArr[3]);
                break;
            case 15:
                realKeyLongClick1(this.mContext, 50, iArr[3]);
                break;
            case 16:
                realKeyClick4(this.mContext, 47);
                break;
            case 17:
                realKeyClick4(this.mContext, 48);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
        }
    }

    private void set0x14(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            setBacklightLevel((iArr[2] / 12) + 1);
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = iArr;
        return true;
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isDoorInfoChange(int[] iArr) {
        if (Arrays.equals(this.mDoorInfo, iArr)) {
            return false;
        }
        this.mDoorInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isSettingsInfoChange(int[] iArr) {
        if (Arrays.equals(this.mSettingdInfo, iArr)) {
            return false;
        }
        this.mSettingdInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
