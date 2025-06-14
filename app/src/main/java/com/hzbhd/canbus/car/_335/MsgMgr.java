package com.hzbhd.canbus.car._335;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
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
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static String mediaTag = "";
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    private int mMediaType;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private int radioMediaType;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    public final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
    public final String MEDIA_TYPE_RADIO0 = "RADIO0";
    public final String MEDIA_TYPE_RADIO1 = "RADIO1";
    public final String MEDIA_TYPE_CD_DVD = "CD/DVD";
    public final String MEDIA_TYPE_AUX = "AUX";
    public final String MEDIA_OTHERS = "OTHERS";
    public final String MEDIA_USB = "USB";
    private int AmTag = 1;
    private int setUsbInfo0x21Data0 = 0;

    private int getAslState(int i) {
        return i == 8 ? 1 : 0;
    }

    private int getCarType(int i) {
        if (i == 32) {
            return 0;
        }
        if (i == 33) {
            return 1;
        }
        if (i == 48) {
            return 2;
        }
        if (i != 49) {
            return i != 80 ? 5 : 4;
        }
        return 3;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
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
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
        initAmplifier(context);
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._335.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -124, 8, 1}, new byte[]{22, -124, 1, (byte) ((-GeneralAmplifierData.frontRear) + 7)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}}).iterator();

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            if (this.eachId != 2) {
                return;
            }
            setSpeedInfo0x16();
            return;
        }
        if (i == 36) {
            set0x24BaseInfo();
            return;
        }
        if (i == 80) {
            if (this.eachId != 2) {
                return;
            }
            setSpeedInfo0x50();
            return;
        }
        if (i == 32) {
            setWheelKeyInfo0x20();
            return;
        }
        if (i == 33) {
            if (this.eachId == 3) {
                return;
            }
            setUsbInfo0x21();
            return;
        }
        if (i == 40) {
            setAirInfo0x28();
            return;
        }
        if (i == 41) {
            setEPSInfo0x29();
            return;
        }
        switch (i) {
            case 48:
                set0x30VersionInfo();
                break;
            case 49:
                setAmplifierInfo0x31(context);
                break;
            case 50:
                setSystemInfo0x32();
                break;
            default:
                switch (i) {
                    case 96:
                        if (this.eachId != 2) {
                            setSteeringWheelControlMode0x60();
                            break;
                        }
                        break;
                    case 97:
                        if (this.eachId != 2) {
                            setMediaState0x61();
                            break;
                        }
                        break;
                    case 98:
                        if (this.eachId != 2) {
                            setMediaSourceInfo0x62();
                            break;
                        }
                        break;
                    case 99:
                        set0x63ModelSetting();
                        break;
                    case 100:
                        if (this.eachId != 2) {
                            set0x64WheelKey();
                            break;
                        }
                        break;
                    case 101:
                        if (this.eachId == 3) {
                            set0x65RockerData(this.mContext);
                            break;
                        }
                        break;
                    case 102:
                        if (this.eachId == 3) {
                            setSoundEffectsInfo();
                            break;
                        }
                        break;
                }
        }
    }

    private void setSoundEffectsInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_vol_data0"), 0, getSoundType(this.mCanBusInfoInt[2])).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x65RockerData(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(context, 0, iArr[3]);
        }
        if (i == 1) {
            realKeyLongClick1(context, 45, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 48, iArr[3]);
            return;
        }
        if (i == 5) {
            realKeyLongClick1(context, 46, iArr[3]);
            return;
        }
        if (i == 7) {
            realKeyLongClick1(context, 47, iArr[3]);
            return;
        }
        switch (i) {
            case 16:
                realKeyLongClick1(context, 7, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(context, 8, iArr[3]);
                break;
            case 18:
                realKeyLongClick1(context, 49, iArr[3]);
                break;
            case 19:
                realKeyLongClick1(context, 50, iArr[3]);
                break;
            case 20:
                realKeyLongClick1(context, 151, iArr[3]);
                break;
            case 21:
                realKeyLongClick1(context, 4, iArr[3]);
                break;
            case 22:
                realKeyLongClick1(context, 128, iArr[3]);
                break;
            case 23:
                realKeyLongClick1(context, 52, iArr[3]);
                break;
        }
    }

    private void set0x64WheelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 77, iArr[3]);
            return;
        }
        if (i == 2) {
            changeBandFm1();
            return;
        }
        if (i == 3) {
            changeBandFm2();
        } else if (i == 4) {
            realKeyLongClick1(this.mContext, 141, iArr[3]);
        } else {
            if (i != 32) {
                return;
            }
            realKeyLongClick1(this.mContext, 4, iArr[3]);
        }
    }

    private void set0x63ModelSetting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_car_data0"), 0, Integer.valueOf(getCarType(this.mCanBusInfoInt[2]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setMediaSourceInfo0x62() {
        OriginalDeviceData originalDeviceData;
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt.length >= 6) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_cd_data"), 0, getCdOrDvdSate1(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))).setValueStr(true));
        }
        if (this.mCanBusInfoInt.length >= 6) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_cd_data"), 1, getCdOrDvdSate2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        Bundle bundle = null;
        updateSettingActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            if (iArr[3] == 0) {
                originalDeviceData = this.mOriginalDeviceDataArray.get(256);
            } else {
                originalDeviceData = this.mOriginalDeviceDataArray.get(InputDeviceCompat.SOURCE_KEYBOARD);
            }
        } else {
            originalDeviceData = this.mOriginalDeviceDataArray.get(i);
        }
        GeneralOriginalCarDeviceData.mList = null;
        int[] iArr2 = this.mCanBusInfoInt;
        int i2 = iArr2[2];
        if (i2 == 0) {
            mediaTag = "MEDIA OFF";
            GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
            GeneralOriginalCarDeviceData.runningState = "MEDIA OFF";
        } else if (i2 != 1) {
            if (i2 == 2) {
                mediaTag = "CD/DVD";
                GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
                GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
            } else if (i2 == 3) {
                mediaTag = "AUX";
                GeneralOriginalCarDeviceData.cdStatus = "AUX";
                GeneralOriginalCarDeviceData.runningState = "AUX";
            } else if (i2 == 255) {
                mediaTag = "OTHERS";
                GeneralOriginalCarDeviceData.cdStatus = "OTHERS";
                GeneralOriginalCarDeviceData.runningState = "OTHERS";
            }
        } else if (iArr2[3] == 0) {
            mediaTag = "RADIO0";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO0";
            GeneralOriginalCarDeviceData.mList = setNowFrequencyInfo(this.mCanBusInfoInt);
        } else {
            mediaTag = "RADIO1";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO1";
            GeneralOriginalCarDeviceData.mList = setPresuppositionFrequencyInfo(this.mCanBusInfoInt);
        }
        int[] iArr3 = this.mCanBusInfoInt;
        int i3 = iArr3[2];
        if (i3 == 1) {
            int i4 = this.radioMediaType;
            int i5 = iArr3[3];
            if (i4 != i5) {
                this.radioMediaType = i5;
                this.mMediaType = 9;
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
                originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
                originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
                bundle = new Bundle();
                bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
            }
        } else {
            this.radioMediaType = 9;
            if (this.mMediaType != i3) {
                this.mMediaType = i3;
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet2 = getOriginalCarDevicePageUiSet(this.mContext);
                originalCarDevicePageUiSet2.setItems(originalDeviceData.getItemList());
                originalCarDevicePageUiSet2.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
                bundle = new Bundle();
                bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
            }
        }
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setMediaState0x61() {
        if (getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state") != -1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_1"), getDiscYesOrNo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_2"), getDiscYesOrNo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_3"), getDiscYesOrNo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_4"), getDiscYesOrNo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_5"), getDiscYesOrNo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_6"), getDiscYesOrNo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_status"), getDiscState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_currently_selected_disc"), getNowDisc(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setSteeringWheelControlMode0x60() {
        ArrayList arrayList = new ArrayList();
        if (getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_Mode_selection") != -1) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_Mode_selection"), 0, Integer.valueOf(this.mCanBusInfoInt[2])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSpeedInfo0x50() {
        ArrayList arrayList = new ArrayList();
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 2, sb.append(getMsbLsbResult(iArr[3], iArr[2])).append("km/h").toString()).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed"), 3, Integer.valueOf(this.mCanBusInfoInt[4] * 100)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSystemInfo0x32() {
        ArrayList arrayList = new ArrayList();
        if (getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info") != -1) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 2, getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getNullHaveState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._330_no);
        }
        return this.mContext.getString(R.string._330_yes);
    }

    private void setAmplifierInfo0x31(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 3, Integer.valueOf(getAslState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 4, getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAmplifierData.frontRear = (-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)) + 7;
        GeneralAmplifierData.leftRight = -((-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)) + 7);
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        MyLog.temporaryTracking(GeneralAmplifierData.volume + "");
        updateAmplifierActivity(null);
        saveAmplifierData(context, getCanId());
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setEPSInfo0x29() {
        if (isTrackInfoChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 380, 12);
            updateParkUi(null, this.mContext);
        }
    }

    private void setAirInfo0x28() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 4);
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 0.5d, 17.5d, "F", 0, 31);
            GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 17.5d, "F", 0, 31);
        } else {
            GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 0.5d, 17.5d, "C", 0, 31);
            GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 17.5d, "C", 0, 31);
        }
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x24BaseInfo() {
        if (isBasicInfoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setUsbInfo0x21() {
        mediaTag = "USB";
        GeneralOriginalCarDeviceData.mList = getOriginalDeviceUsbUpdateEntityList();
        Bundle bundle = null;
        updateOriginalCarDeviceActivity(null);
        int i = this.setUsbInfo0x21Data0;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.setUsbInfo0x21Data0 = i2;
            GeneralOriginalCarDeviceData.runningState = getRunningState(DataHandleUtils.getIntFromByteWithBit(i2, 0, 4));
            GeneralOriginalCarDeviceData.cdStatus = getCdStatu(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
            OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(getUiModel());
            OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
            originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
            originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
            bundle = new Bundle();
            bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        }
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setWheelKeyInfo0x20() {
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
        if (i == 19) {
            buttonKey(45);
            return;
        }
        if (i != 20) {
            switch (i) {
                case 7:
                    buttonKey(2);
                    return;
                case 8:
                    buttonKey(3);
                    break;
                case 9:
                    break;
                case 10:
                    buttonKey(HotKeyConstant.K_2_HANGUP);
                    return;
                default:
                    return;
            }
            buttonKey(HotKeyConstant.K_1_PICKUP);
            return;
        }
        buttonKey(46);
    }

    private void setSpeedInfo0x16() {
        ArrayList arrayList = new ArrayList();
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 0, sb.append(getMsbLsbResult(iArr[3], iArr[2])).append("km/h").toString()).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_335_setting_data_speed"), 1, Integer.valueOf(this.mCanBusInfoInt[4] * 100)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._335.MsgMgr$2] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._335.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data8", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data9", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data10", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data11", null));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data8", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data9", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_335_sub_data10", null));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_band", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_frequency", null));
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_band", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_1", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_2", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_3", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_4", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_5", null));
        arrayList5.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_6", null));
        ArrayList arrayList6 = new ArrayList();
        arrayList6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_currently_selected_disc", null));
        arrayList6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", null));
        arrayList6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", null));
        arrayList6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_time", null));
        arrayList6.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(8449, new OriginalDeviceData(arrayList2, new String[]{OriginalBtnAction.PREV_DISC, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.NEXT_DISC}));
        this.mOriginalDeviceDataArray.put(8451, new OriginalDeviceData(arrayList3, new String[]{OriginalBtnAction.PREV_DISC, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.NEXT_DISC}));
        this.mOriginalDeviceDataArray.put(8450, new OriginalDeviceData(arrayList, new String[0]));
        this.mOriginalDeviceDataArray.put(0, new OriginalDeviceData(arrayList, new String[0]));
        this.mOriginalDeviceDataArray.put(256, new OriginalDeviceData(arrayList4, new String[]{"left", "up", OriginalBtnAction.PLAY_PAUSE, "down", "right"}));
        this.mOriginalDeviceDataArray.put(InputDeviceCompat.SOURCE_KEYBOARD, new OriginalDeviceData(arrayList5, new String[0]));
        this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(arrayList6, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", "down", "right", OriginalBtnAction.NEXT_DISC}));
        this.mOriginalDeviceDataArray.put(3, new OriginalDeviceData(arrayList, new String[0]));
        this.mOriginalDeviceDataArray.put(255, new OriginalDeviceData(arrayList, new String[0]));
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

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceUsbUpdateEntityList() {
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        StringBuilder sbAppend = sb.append(getMsbLsbResult(iArr[6], iArr[7])).append("/");
        int[] iArr2 = this.mCanBusInfoInt;
        String string = sbAppend.append(getMsbLsbResult(iArr2[8], iArr2[9])).toString();
        String str = this.df_2Integer.format(this.mCanBusInfoInt[4]) + ":" + this.df_2Integer.format(this.mCanBusInfoInt[5]);
        String str2 = this.mCanBusInfoInt[11] + "%";
        String str3 = this.mCanBusInfoInt[10] + "";
        ArrayList arrayList = new ArrayList();
        if (string != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, string));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, str));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str2));
        }
        if (str3 != null && DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) != 1) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str3));
        }
        return arrayList;
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private String getCdStatu(int i) {
        return i != 1 ? i != 2 ? this.mContext.getString(R.string._335_sub_data1) : "USB" : "IPOD";
    }

    private String getRunningState(int i) {
        switch (i) {
            case 1:
                return "LOADING";
            case 2:
                return this.mContext.getString(R.string._335_sub_data3);
            case 3:
                return this.mContext.getString(R.string._335_sub_data4);
            case 4:
                return this.mContext.getString(R.string._335_sub_data5);
            case 5:
                return this.mContext.getString(R.string._335_sub_data6);
            case 6:
                return this.mContext.getString(R.string._335_sub_data7);
            default:
                return this.mContext.getString(R.string._335_sub_data2);
        }
    }

    private int getUiModel() {
        if (GeneralOriginalCarDeviceData.runningState.equals(this.mContext.getString(R.string._335_sub_data2)) || GeneralOriginalCarDeviceData.runningState.equals(this.mContext.getString(R.string._335_sub_data3))) {
            return 8450;
        }
        return GeneralOriginalCarDeviceData.cdStatus.equals("USB") ? 8449 : 8451;
    }

    private String getDiscYesOrNo(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._335_drive_data1);
        }
        return this.mContext.getString(R.string._335_drive_data2);
    }

    private String getDiscState(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._335_drive_data3);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._335_drive_data4);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._335_drive_data5);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._335_drive_data6);
        }
        if (i == 5) {
            return this.mContext.getString(R.string._335_drive_data7);
        }
        if (i == 15) {
            return this.mContext.getString(R.string._335_drive_data8) + "(CODE:" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + ")";
        }
        return this.mContext.getString(R.string._335_drive_data9);
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
                return this.mContext.getString(R.string._335_drive_data10);
        }
    }

    private List<OriginalCarDeviceUpdateEntity> setNowFrequencyInfo(int[] iArr) {
        String str;
        String str2;
        String str3;
        if (iArr[4] == 16) {
            str3 = getStereoState(DataHandleUtils.getBoolBit7(iArr[5])) + "/" + getScanState(DataHandleUtils.getBoolBit5(iArr[5])) + "/" + getPresetStationNumber(DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4));
            str2 = getMsbLsbResult(iArr[7], iArr[6]) + "KHz";
            str = "AM";
        } else {
            str = "FM" + iArr[4];
            str2 = this.df_2Decimal.format(getMsbLsbResult(iArr[7], iArr[6]) * 0.01d) + "MHz";
            str3 = null;
        }
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, str3));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str2));
        }
        GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> setPresuppositionFrequencyInfo(int[] iArr) {
        String appointmentBand;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int i = iArr[4];
        if (i == 16 || i == 17 || i == 18) {
            appointmentBand = getAppointmentBand(i);
            str = getMsbLsbResult(iArr[6], iArr[5]) + "KHz";
            str2 = getMsbLsbResult(iArr[8], iArr[7]) + "KHz";
            str3 = getMsbLsbResult(iArr[10], iArr[9]) + "KHz";
            str4 = getMsbLsbResult(iArr[12], iArr[11]) + "KHz";
            str5 = getMsbLsbResult(iArr[14], iArr[13]) + "KHz";
            str6 = getMsbLsbResult(iArr[16], iArr[15]) + "KHz";
        } else {
            appointmentBand = getAppointmentBand(i);
            str = this.df_2Decimal.format(getMsbLsbResult(iArr[6], iArr[5]) * 0.01d) + "MHz";
            str2 = this.df_2Decimal.format(getMsbLsbResult(iArr[8], iArr[7]) * 0.01d) + "MHz";
            str3 = this.df_2Decimal.format(getMsbLsbResult(iArr[10], iArr[9]) * 0.01d) + "MHz";
            str4 = this.df_2Decimal.format(getMsbLsbResult(iArr[12], iArr[11]) * 0.01d) + "MHz";
            str5 = this.df_2Decimal.format(getMsbLsbResult(iArr[14], iArr[13]) * 0.01d) + "MHz";
            str6 = this.df_2Decimal.format(getMsbLsbResult(iArr[16], iArr[15]) * 0.01d) + "MHz";
        }
        ArrayList arrayList = new ArrayList();
        if (appointmentBand != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, appointmentBand));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, str));
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
        if (str6 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, str6));
        }
        GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceCdDvdUsbUpdateEntityList(int[] iArr) {
        String str = DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4) + "";
        String cdOrDvdSate = getCdOrDvdSate(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4), DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4));
        String str2 = getMsbLsbResult(iArr[7], iArr[6]) + "";
        String str3 = getMsbLsbResult(iArr[9], iArr[8]) + "";
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
        MyLog.temporaryTracking("CD/DVD运行正常");
        return arrayList;
    }

    private String getCdOrDvdSate(int i, int i2) {
        String string;
        String string2;
        if (i == 1) {
            string = this.mContext.getString(R.string._335_drive_data11);
        } else if (i == 2) {
            string = this.mContext.getString(R.string._335_drive_data12);
        } else {
            string = this.mContext.getString(R.string._335_drive_data13);
        }
        if (i2 == 1) {
            string2 = this.mContext.getString(R.string._335_drive_data14);
        } else {
            string2 = this.mContext.getString(R.string._335_drive_data15);
        }
        return string + "/" + string2;
    }

    private String getCdOrDvdSate1(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._335_drive_data11);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._335_drive_data12);
        }
        return this.mContext.getString(R.string._335_drive_data13);
    }

    private String getCdOrDvdSate2(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._335_drive_data14);
        }
        return this.mContext.getString(R.string._335_drive_data15);
    }

    private String getStereoState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._335_drive_data16);
        }
        return this.mContext.getString(R.string._335_drive_data17);
    }

    private String getScanState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._335_drive_data18);
        }
        return this.mContext.getString(R.string._335_drive_data19);
    }

    private String getPresetStationNumber(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._335_drive_data20);
        }
        return this.mContext.getString(R.string._335_drive_data21) + i;
    }

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
                return this.mContext.getString(R.string._335_drive_data10);
        }
    }

    private String getSoundType(int i) {
        switch (i) {
            case 1:
                return "BOSS";
            case 2:
                return "MID";
            case 3:
                return "TER";
            case 4:
                return "FAD";
            case 5:
                return "BAL";
            case 6:
                return "LOUD";
            case 7:
                return "ASL";
            default:
                return this.mContext.getString(R.string._335_drive_data10);
        }
    }

    private UiMgr getUiMgr(Context context) {
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

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
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

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }
}
