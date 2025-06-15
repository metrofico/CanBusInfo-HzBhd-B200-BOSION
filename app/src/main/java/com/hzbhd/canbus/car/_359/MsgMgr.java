package com.hzbhd.canbus.car._359;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    public static String mediaTag = "";
    DecimalFormat decimalFormat;
    private int eachId;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mMediaType;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    private UiMgr uiMgr;
    private final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
    private final String MEDIA_TYPE_RADIO = "RADIO";
    private final String MEDIA_TYPE_CD_DVD = "CD/DVD";
    private final String MEDIA_TYPE_AUX = "AUX";
    private final String MEDIA_REAR_DISC = "后排DISC";
    private final String MEDIA_USB = "USB";
    int data2FrontLeft = 0;
    int data3FrontRight = 0;
    int data6RearLeft = 0;
    int data8RearRight = 0;
    int data5OurDoorTem = 0;

    private String getAppointmentBand(int i) {
        if (i == 0) {
            return "FM";
        }
        if (i == 1) {
            return "FM1";
        }
        if (i == 2) {
            return "FM2";
        }
        switch (i) {
            case 16:
                return "AM";
            case 17:
                return "AM1";
            case 18:
                return "AM2";
            default:
                return "无";
        }
    }

    private int getAslState(int i) {
        return i == 8 ? 1 : 0;
    }

    private int getCarType(int i) {
        return i == 32 ? 0 : 1;
    }

    private String getDiscTypoe(boolean z) {
        return !z ? "CD" : "DVD";
    }

    private int getMsbLsbResult(int i, int i2) {
        return (i & 255) | ((i2 & 255) << 8);
    }

    private String getRearLockState(int i) {
        return i != 1 ? "未锁止" : "锁止";
    }

    private String getRearMediaState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 15 ? "空闲状态" : "ERROR状态" : "EJECT状态" : "播放状态" : "DISC READING " : "WAIT状态" : "LOAD状态";
    }

    private String getScanState(boolean z) {
        return z ? "扫描状态" : "非扫描状态";
    }

    private String getStereoState(boolean z) {
        return z ? "立体声" : "非立体声";
    }

    private String getUsbState1(int i) {
        switch (i) {
            case 1:
                return "LAODING";
            case 2:
                return "没有连接 USB 设备";
            case 3:
                return "设备已经连接";
            case 4:
                return "播放中";
            case 5:
                return "暂停(LOAD IMAGE)";
            case 6:
                return "其它状态";
            default:
                return "关闭/停止状态";
        }
    }

    private String getUsbState2(int i) {
        return i != 1 ? i != 2 ? "无媒体信息" : "USB" : "IPOD";
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.hzbhd.canbus.car._359.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
        new Thread() { // from class: com.hzbhd.canbus.car._359.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            setWheelKey0x20();
        }
        if (i == 36) {
            setDoorInfo0x24();
            return;
        }
        if (i == 40) {
            setAirInfo0x28();
            return;
        }
        if (i == 41) {
            setTrackInfo0x29();
            return;
        }
        switch (i) {
            case 48:
                setVersionInfo0x30();
                break;
            case 49:
                setAmplifierInfo0x31();
                break;
            case 50:
                setSystemInfo0x32();
                break;
            default:
                switch (i) {
                    case 96:
                        ControlMode0x60();
                        break;
                    case 97:
                        setMediaStatus0x61();
                        break;
                    case 98:
                        setMediaSourceInfo0x62();
                        break;
                    case 99:
                        setCarTypeInfo0x63();
                        break;
                }
        }
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 7) {
            buttonKey(2);
        } else if (i == 19) {
            buttonKey(45);
        } else {
            if (i != 20) {
                return;
            }
            buttonKey(46);
        }
    }

    private void setDoorInfo0x24() {
        int i = this.mCanBusInfoInt[2];
        new ArrayList();
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(i);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(i);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(i);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(i);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(i);
        updateDoorView(this.mContext);
    }

    private void setAirInfo0x28() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = iArr[6];
        GeneralAirData.power = DataHandleUtils.getBoolBit7(i);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(i);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(i);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(i);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(i);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(i);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(i2);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(i2);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(i2);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(i2);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(i2);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(i2);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(i2, 0, 4);
        if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]) && SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
            GeneralAirData.front_left_temperature = getAirTemperatureC(this.mContext, this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = getAirTemperatureC(this.mContext, this.mCanBusInfoInt[5]);
        } else {
            GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, this.mCanBusInfoInt[5]);
        }
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(i3);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(i3);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(i3);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(i3);
        if (isAirDataChange()) {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return false;
        }
        this.mAirData = this.mCanBusInfoInt;
        return true;
    }

    private void setTrackInfo0x29() {
        if (isDataChange(this.mCanBusInfoInt)) {
            if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
                byte[] bArr = this.mCanBusInfoByte;
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 4046, 3696, 16);
            } else {
                byte[] bArr2 = this.mCanBusInfoByte;
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr2[2], bArr2[3], 0, 380, 16);
            }
            updateParkUi(null, this.mContext);
        }
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAmplifierInfo0x31() {
        ArrayList arrayList = new ArrayList();
        GeneralAmplifierData.frontRear = (-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)) + 7;
        GeneralAmplifierData.leftRight = -((-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)) + 7);
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_asl"), Integer.valueOf(getAslState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_Vehicle_volume_follows_ASL", "_330_asl"), getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))).setValueStr(true));
        updateAmplifierActivity(null);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSystemInfo0x32() {
        int i = this.mCanBusInfoInt[2];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_Original_power_amplifier_node"), getNullHaveState(DataHandleUtils.getIntFromByteWithBit(i, 0, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_amplifier_switch"), Boolean.valueOf(DataHandleUtils.getBoolBit6(i))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_Power_amplifier_mute"), Boolean.valueOf(DataHandleUtils.getBoolBit7(i))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void ControlMode0x60() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_Mode_selection"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_Mode_selection", "_330_Steering_wheel_control_mode"), Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setMediaStatus0x61() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_1"), getDiscYesOrNo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_2"), getDiscYesOrNo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_3"), getDiscYesOrNo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_4"), getDiscYesOrNo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_5"), getDiscYesOrNo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_6"), getDiscYesOrNo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_status"), getDiscState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_currently_selected_disc"), getNowDisc(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_1"), getDiscTypoe(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_2"), getDiscTypoe(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_3"), getDiscTypoe(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_4"), getDiscTypoe(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_5"), getDiscTypoe(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_6"), getDiscTypoe(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Subtitle_language"), getDiscLanguage(this.mCanBusInfoInt[6])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Voice_language"), getDiscLanguage(this.mCanBusInfoInt[7])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_dvd_language"), getDiscLanguage(this.mCanBusInfoInt[8])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setMediaSourceInfo0x62() {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(this.mCanBusInfoInt[2]);
        Bundle bundle = null;
        GeneralOriginalCarDeviceData.mList = null;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            mediaTag = "MEDIA OFF";
            GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
            GeneralOriginalCarDeviceData.runningState = "MEDIA OFF";
        } else if (i == 1) {
            mediaTag = "RADIO";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceRadioUpdateEntityList(this.mCanBusInfoInt);
        } else if (i == 2) {
            mediaTag = "CD";
            GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
        } else if (i == 3) {
            mediaTag = "AUX";
            GeneralOriginalCarDeviceData.cdStatus = "AUX";
            GeneralOriginalCarDeviceData.runningState = "AUX运行正常";
        } else if (i == 4) {
            mediaTag = "DISC";
            GeneralOriginalCarDeviceData.cdStatus = "后排DISC";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceRearDiscUpdateEntityList(this.mCanBusInfoInt);
        }
        int i2 = this.mMediaType;
        int i3 = this.mCanBusInfoInt[2];
        if (i2 != i3) {
            this.mMediaType = i3;
            OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
            originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
            originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
            bundle = new Bundle();
            bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        }
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setCarTypeInfo0x63() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_setting_info"), 0, Integer.valueOf(getCarType(this.mCanBusInfoInt[2]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveIGAndAcc(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._332_setting_state1);
        }
        return this.mContext.getResources().getString(R.string._332_setting_state2);
    }

    private String getAirTemperatureC(Context context, int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (i <= 0 || i >= 30) ? "" : (((i - 1) * 0.5d) + 18.0d) + getTempUnitC(context);
    }

    private String getAirTemperatureF(Context context, int i) {
        this.decimalFormat = new DecimalFormat("00.00");
        return i == 0 ? "LO" : i == 31 ? "HI" : (i <= 0 || i >= 30) ? "" : this.decimalFormat.format(((((i - 1) * 0.5d) + 18.0d) * 1.8d) + 32.0d) + getTempUnitF(context);
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (i < 1 || i > 29) {
            return null;
        }
        return (((i - 1) * 0.5d) + 18.0d) + getTempUnitC(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        OriginalDeviceData originalDeviceData = new OriginalDeviceData(new ArrayList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_band", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_frequency", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_band", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_1", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_2", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_3", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_4", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_5", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_6", null));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_currently_selected_disc", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_time", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", null));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Back_row_media_status", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Rear_lock_status", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", null));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_sub_status", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_media_status", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_folaer_status", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_play_time", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Playback_progress", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(0, originalDeviceData);
        this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(arrayList, new String[]{"left", "up", OriginalBtnAction.PLAY_PAUSE, "down", "right"}));
        this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(arrayList2, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.RANDOM, OriginalBtnAction.REPEAT, "down", "right", OriginalBtnAction.NEXT_DISC}));
        this.mOriginalDeviceDataArray.put(3, originalDeviceData);
        this.mOriginalDeviceDataArray.put(4, new OriginalDeviceData(arrayList3));
        this.mOriginalDeviceDataArray.put(33, new OriginalDeviceData(arrayList4, new String[]{OriginalBtnAction.PREV_DISC, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.NEXT_DISC}));
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
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceRadioUpdateEntityList(int[] iArr) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        if (iArr[3] == 0) {
            if (iArr[4] == 16) {
                String str11 = getStereoState(DataHandleUtils.getBoolBit7(iArr[5])) + "/" + getScanState(DataHandleUtils.getBoolBit5(iArr[5])) + "/" + getPresetStationNumber(DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4));
                str10 = getMsbLsbResult(iArr[6], iArr[7]) + "KHz";
                str9 = str11;
                str6 = "AM";
            } else {
                str6 = "FM" + iArr[4];
                str10 = this.decimalFormat.format(getMsbLsbResult(iArr[6], iArr[7]) * 0.01d) + "MHz";
                str9 = null;
            }
            str7 = null;
            str5 = null;
            str = null;
            str8 = null;
            str2 = null;
            str4 = null;
            str3 = null;
        } else {
            int i = iArr[4];
            if (i == 16 || i == 17 || i == 18) {
                String appointmentBand = getAppointmentBand(i);
                str = getMsbLsbResult(iArr[5], iArr[6]) + "KHz";
                String str12 = getMsbLsbResult(iArr[7], iArr[8]) + "KHz";
                String str13 = getMsbLsbResult(iArr[9], iArr[10]) + "KHz";
                str2 = getMsbLsbResult(iArr[11], iArr[12]) + "KHz";
                String str14 = getMsbLsbResult(iArr[13], iArr[14]) + "KHz";
                str3 = getMsbLsbResult(iArr[15], iArr[16]) + "KHz";
                str4 = str14;
                str5 = str13;
                str6 = null;
                str7 = appointmentBand;
                str8 = str12;
                str9 = null;
                str10 = null;
            } else {
                String appointmentBand2 = getAppointmentBand(i);
                String str15 = this.decimalFormat.format(getMsbLsbResult(iArr[5], iArr[6]) * 0.01d) + "MHz";
                str8 = this.decimalFormat.format(getMsbLsbResult(iArr[7], iArr[8]) * 0.01d) + "MHz";
                String str16 = this.decimalFormat.format(getMsbLsbResult(iArr[9], iArr[10]) * 0.01d) + "MHz";
                String str17 = this.decimalFormat.format(getMsbLsbResult(iArr[11], iArr[12]) * 0.01d) + "MHz";
                String str18 = this.decimalFormat.format(getMsbLsbResult(iArr[13], iArr[14]) * 0.01d) + "MHz";
                str3 = this.decimalFormat.format(getMsbLsbResult(iArr[15], iArr[16]) * 0.01d) + "MHz";
                str4 = str18;
                str5 = str16;
                str2 = str17;
                str = str15;
                str6 = null;
                str10 = null;
                str7 = appointmentBand2;
                str9 = null;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (str6 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str6));
        }
        if (str9 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, str9));
        }
        if (str10 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str10));
        }
        if (str7 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str7));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str));
        }
        if (str8 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, str8));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, str5));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(7, str2));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(8, str4));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(9, str3));
        }
        GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceCdDvdUsbUpdateEntityList(int[] iArr) {
        String str = DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4) + "";
        String cdOrDvdSate = getCdOrDvdSate(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4), DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4));
        String str2 = getMsbLsbResult(iArr[6], iArr[7]) + "";
        String str3 = getMsbLsbResult(iArr[8], iArr[9]) + "";
        String str4 = iArr[10] + ":" + iArr[11] + ":" + iArr[12];
        String str5 = iArr[13] + ":" + iArr[14] + ":" + iArr[15];
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        }
        if (cdOrDvdSate != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, cdOrDvdSate));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str2));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str3));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str4));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, str5));
        }
        GeneralOriginalCarDeviceData.runningState = "CD/DVD运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceRearDiscUpdateEntityList(int[] iArr) {
        String rearMediaState = getRearMediaState(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4));
        String rearLockState = getRearLockState(DataHandleUtils.getIntFromByteWithBit(iArr[6], 6, 1));
        String str = getMsbLsbResult(iArr[7], iArr[8]) + "";
        String str2 = iArr[9] + ":" + iArr[10] + ":" + iArr[11];
        ArrayList arrayList = new ArrayList();
        if (rearMediaState != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, rearMediaState));
        }
        if (rearLockState != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, rearLockState));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str2));
        }
        GeneralOriginalCarDeviceData.runningState = "DISC运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceUsbUpdateEntityList(int[] iArr) {
        String usbState1 = getUsbState1(DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4));
        String usbState2 = getUsbState2(DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 2));
        String str = getMsbLsbResult(iArr[7], iArr[6]) + "";
        String str2 = getMsbLsbResult(iArr[9], iArr[8]) + "";
        String str3 = iArr[10] + "";
        String str4 = iArr[4] + ":" + iArr[5];
        String str5 = iArr[11] + "%";
        ArrayList arrayList = new ArrayList();
        if (usbState1 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, usbState1));
        }
        if (usbState2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, usbState2));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str2));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str3));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, str4));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, str5));
        }
        GeneralOriginalCarDeviceData.runningState = "SUB运行正常";
        return arrayList;
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getPresetStationNumber(int i) {
        return i == 0 ? "无预设电台" : "预设电台" + i;
    }

    private String getCdOrDvdSate(int i, int i2) {
        return (i == 1 ? "碟循环" : i == 2 ? "单曲循环" : "循环关") + "/" + (i2 == 1 ? "乱序" : "顺序");
    }

    private String getDiscYesOrNo(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._335_drive_data1);
        }
        return this.mContext.getResources().getString(R.string._335_drive_data2);
    }

    private String getDiscState(int i) {
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._288_divice_status_1);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._288_divice_status_2);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._288_divice_status_3);
        }
        if (i == 4) {
            return this.mContext.getResources().getString(R.string._288_divice_status_4);
        }
        if (i == 5) {
            return this.mContext.getResources().getString(R.string._288_divice_status_5);
        }
        if (i == 15) {
            return this.mContext.getResources().getString(R.string._288_divice_status_15);
        }
        return this.mContext.getResources().getString(R.string._288_divice_status_0);
    }

    private String getNowDisc(int i) {
        switch (i) {
            case 1:
                return "DISC 1";
            case 2:
                return "DISC 2";
            case 3:
                return "DISC 3";
            case 4:
                return "DISC 4";
            case 5:
                return "DISC 5";
            case 6:
                return "DISC 6";
            default:
                return this.mContext.getResources().getString(R.string._103_car_setting_value_38);
        }
    }

    private String getDiscLanguage(int i) {
        switch (i) {
            case 1:
                return this.mContext.getResources().getString(R.string.language_55);
            case 2:
                return this.mContext.getResources().getString(R.string.language_34);
            case 3:
                return this.mContext.getResources().getString(R.string.language_50);
            case 4:
                return this.mContext.getResources().getString(R.string.language_35);
            case 5:
                return this.mContext.getResources().getString(R.string.language_40);
            case 6:
                return this.mContext.getResources().getString(R.string.language_41);
            default:
                return this.mContext.getResources().getString(R.string.language_56);
        }
    }

    public void updateAirInfo() {
        int intValue = SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0);
        if (intValue == 1) {
            int i = this.data2FrontLeft;
            if (i != 0) {
                GeneralAirData.front_left_temperature = getAirTemperatureC(this.mContext, i);
            }
            int i2 = this.data3FrontRight;
            if (i2 != 0) {
                GeneralAirData.front_right_temperature = getAirTemperatureC(this.mContext, i2);
            }
        } else {
            int i3 = this.data2FrontLeft;
            if (i3 != 0) {
                GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, i3);
            }
            int i4 = this.data3FrontRight;
            if (i4 != 0) {
                GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, i4);
            }
        }
        if (intValue == 1) {
            int i5 = this.data6RearLeft;
            if (i5 != 0) {
                GeneralAirData.rear_left_temperature = getAirTemperatureC(this.mContext, i5);
            }
            int i6 = this.data8RearRight;
            if (i6 != 0) {
                GeneralAirData.rear_right_temperature = getAirTemperatureC(this.mContext, i6);
            }
        } else {
            int i7 = this.data6RearLeft;
            if (i7 != 0) {
                GeneralAirData.rear_left_temperature = getAirTemperatureF(this.mContext, i7);
            }
            int i8 = this.data8RearRight;
            if (i8 != 0) {
                GeneralAirData.rear_right_temperature = getAirTemperatureF(this.mContext, i8);
            }
        }
        updateAirActivity(this.mContext, 1001);
    }

    private String getNullHaveState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._330_no);
        }
        return this.mContext.getString(R.string._330_yes);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
