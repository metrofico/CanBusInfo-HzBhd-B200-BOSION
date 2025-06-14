package com.hzbhd.canbus.car._118;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final String SHARE_118_AMPLIFIER_BALANCE = "share_118_amplifier_balance";
    private static final String SHARE_118_AMPLIFIER_BASS = "share_118_amplifier_bass";
    private static final String SHARE_118_AMPLIFIER_FADE = "share_118_amplifier_fade";
    private static final String SHARE_118_AMPLIFIER_MIDDLE = "share_118_amplifier_middle";
    private static final String SHARE_118_AMPLIFIER_TREBLE = "share_118_amplifier_treble";
    private static final String SHARE_118_AMPLIFIER_VOLUME = "share_118_amplifier_volume";
    private static final String SHARE_118_CAR_MODEL = "share_118_car_model";
    static final int _118_AMPLIFIER_BAND_MAX = 1;
    static final int _118_AMPLIFIER_HALF_MAX = 10;
    int PlanStatus;
    int WeekendUtilFull;
    int WorkUtilFull;
    private int[] mAmplifierDataNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    int mCurrentCanDifferentId;
    private Handler mHandler;
    private boolean mIsBackOpen;
    private boolean mIsBackOpenNow;
    private boolean mIsFrontLeftOpen;
    private boolean mIsFrontLeftOpenNow;
    private boolean mIsFrontOpen;
    private boolean mIsFrontOpenNow;
    private boolean mIsFrontRightOpen;
    private boolean mIsFrontRightOpenNow;
    private boolean mIsRearLeftOpen;
    private boolean mIsRearLeftOpenNow;
    private boolean mIsRearRightOpen;
    private boolean mIsRearRightOpenNow;
    private int mOutdoorTemperature;
    private List<SongListEntity> mSongList;
    boolean rdm;
    boolean rpt;
    private UiMgr uiMgr;
    private final int MEDIA_SOURCE_ID_OFF = 0;
    private final int MEDIA_SOURCE_ID_TUNER = 1;
    private final int MEDIA_SOURCE_ID_DISC = 3;
    private final int MEDIA_SOURCE_ID_USB = 4;
    private final int MEDIA_SOURCE_ID_BTMUSIC = 5;
    private final int MEDIA_SOURCE_ID_SD = 6;
    private final int MEDIA_SOURCE_ID_AUX = 7;
    private final int MEDIA_SOURCE_ID_DVBT = 10;
    private final int MEDIA_SOURCE_ID_A2DP = 11;
    private int mPlayingIndex = -1;
    private final int SEND_GIVEN_MEDIA_MESSAGE = 101;

    private int getData1(int i) {
        if (i < 0 || i > 18) {
            return 0;
        }
        return i;
    }

    private int getData2(int i) {
        if (i < 0 || i > 59) {
            return 0;
        }
        return i;
    }

    private int resolveMode(int i) {
        return i == 0 ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
        this.mCurrentCanDifferentId = getCurrentCanDifferentId();
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 96, (byte) SharePreUtil.getIntValue(context, SHARE_118_CAR_MODEL, 0)});
        updateCarModel(context, SharePreUtil.getIntValue(context, SHARE_118_CAR_MODEL, 0));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            setSteeringWheelControls0x01();
            return;
        }
        if (i == 2) {
            setLights0x02();
            return;
        }
        if (i == 4) {
            setPanelButton0x04();
            return;
        }
        if (i == 5) {
            setAir0x05(context);
            return;
        }
        if (i == 6) {
            rearAirConditionerInfo(context);
            return;
        }
        if (i == 7) {
            int i2 = this.mCurrentCanDifferentId;
            if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 14) {
                return;
            }
            setCarSetting0x07();
            return;
        }
        if (i == 16) {
            setOriginal0x10();
            return;
        }
        if (i == 17) {
            setOriginalData0x11();
            return;
        }
        if (i == 21) {
            setOutTemp0x15();
            return;
        }
        if (i == 25) {
            int i3 = this.mCurrentCanDifferentId;
            if (i3 != 5 && i3 != 6 && i3 != 7 && i3 != 8) {
                setCarSetting0x19();
            }
        } else if (i != 34) {
            if (i != 35) {
                switch (i) {
                    case 9:
                        setTrack0x09();
                        break;
                    case 10:
                        setCarInfo0x0A(bArr);
                        break;
                    case 11:
                        setCompass0x0B(byteArrayToIntArray);
                        break;
                    default:
                        switch (i) {
                            case 48:
                                setVersionInfo();
                                break;
                            case 49:
                                setAmplifier0x31();
                                break;
                            case 50:
                                setPerformanceControl0x32();
                                break;
                            case 51:
                                hybridInfo(context);
                                break;
                        }
                }
                return;
            }
            setFrontRadar0x23();
            return;
        }
        setRearRadar0x22();
    }

    private void setCarSetting0x19() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title19"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title20"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title21"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title22"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title23"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title24"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title25"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_12"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_12", "_118_setting_Title26"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setPerformanceControl0x32() throws Resources.NotFoundException {
        String string;
        String string2;
        String string3;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_111"), Integer.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_112"), Integer.valueOf(resolveMode(this.mCanBusInfoInt[3]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_114"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_115"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_116"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_10", "_118_setting_title_117"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 2))));
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_114");
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            string = this.mContext.getResources().getString(R.string._118_setting_title_114_1);
        } else {
            string = this.mContext.getResources().getString(R.string._118_setting_title_114_2);
        }
        arrayList2.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, string));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_115");
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
            string2 = this.mContext.getResources().getString(R.string._118_setting_value_7);
        } else {
            string2 = this.mContext.getResources().getString(R.string._118_setting_value_8);
        }
        arrayList2.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, string2));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_116");
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
            string3 = this.mContext.getResources().getString(R.string._118_setting_title_114_1);
        } else {
            string3 = this.mContext.getResources().getString(R.string._118_setting_title_114_2);
        }
        arrayList2.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, string3));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_setting_title_118"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_title_117"), resolveSteering(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2))));
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveSteering(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._118_setting_title_114_1);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._118_setting_title_114_2);
        }
        return this.mContext.getResources().getString(R.string._118_setting_title_114_3);
    }

    private void rearAirConditionerInfo(Context context) {
        int i = this.mCurrentCanDifferentId;
        if (i == 4 || i == 13) {
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_right_blow_foot = false;
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
            if (intFromByteWithBit == 0) {
                GeneralAirData.rear_left_blow_head = false;
                GeneralAirData.rear_left_blow_foot = false;
                GeneralAirData.rear_right_blow_head = false;
                GeneralAirData.rear_right_blow_foot = false;
            } else if (intFromByteWithBit == 1) {
                GeneralAirData.rear_left_blow_head = true;
                GeneralAirData.rear_left_blow_foot = false;
                GeneralAirData.rear_right_blow_head = true;
                GeneralAirData.rear_right_blow_foot = false;
            } else if (intFromByteWithBit == 2) {
                GeneralAirData.rear_left_blow_head = true;
                GeneralAirData.rear_left_blow_foot = true;
                GeneralAirData.rear_right_blow_head = true;
                GeneralAirData.rear_right_blow_foot = true;
            } else if (intFromByteWithBit == 3) {
                GeneralAirData.rear_left_blow_head = false;
                GeneralAirData.rear_left_blow_foot = true;
                GeneralAirData.rear_right_blow_head = false;
                GeneralAirData.rear_right_blow_foot = true;
            }
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.rear_temperature = resolveRearTemp(this.mCanBusInfoInt[4]);
            updateAirActivity(context, 1002);
        }
    }

    private void hybridInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoByte[2] == 0) {
            ArrayList arrayList2 = new ArrayList();
            String strByResId = CommUtil.getStrByResId(context, "set_default");
            int i = this.mCanBusInfoInt[3];
            if (i == 0) {
                strByResId = CommUtil.getStrByResId(context, "_118_drivingInfo_0_0_0");
            } else if (i == 1) {
                strByResId = CommUtil.getStrByResId(context, "_118_drivingInfo_0_0_1");
            } else if (i == 2) {
                strByResId = CommUtil.getStrByResId(context, "_118_drivingInfo_0_0_2");
            } else if (i == 4) {
                strByResId = CommUtil.getStrByResId(context, "_118_drivingInfo_0_0_3");
            } else if (i == 8) {
                strByResId = CommUtil.getStrByResId(context, "_118_drivingInfo_0_0_4");
            }
            arrayList2.add(new DriverUpdateEntity(1, 0, strByResId));
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
                arrayList2.add(new DriverUpdateEntity(1, 1, "--"));
            } else {
                StringBuilder sb = new StringBuilder();
                int[] iArr = this.mCanBusInfoInt;
                arrayList2.add(new DriverUpdateEntity(1, 1, sb.append((iArr[4] * 256) + iArr[5]).append("kW").toString()));
            }
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
                arrayList2.add(new DriverUpdateEntity(1, 2, "--"));
            } else {
                arrayList2.add(new DriverUpdateEntity(1, 2, (this.mCanBusInfoInt[6] - 125) + "kW"));
            }
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
                arrayList2.add(new DriverUpdateEntity(1, 3, "--"));
            } else {
                arrayList2.add(new DriverUpdateEntity(1, 3, this.mCanBusInfoInt[7] + "kW"));
            }
            arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_drivingInfo_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_Title16"), resolveStatus(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]))));
            arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_drivingInfo_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_Title17"), resolveStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]))));
            arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_drivingInfo_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_setting_Title18"), resolveStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]))));
            updateGeneralDriveData(arrayList2);
            updateDriveDataActivity(null);
        } else {
            int i2 = this.mCanBusInfoInt[2];
            if (i2 == 1) {
                ArrayList arrayList3 = new ArrayList();
                int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_DriveInfo_HeadTitle_01");
                int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_DriveInfo_Title02");
                StringBuilder sb2 = new StringBuilder();
                int[] iArr2 = this.mCanBusInfoInt;
                arrayList3.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb2.append((iArr2[3] * 256) + iArr2[4]).append("KM").toString()));
                int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_118_DriveInfo_HeadTitle_01");
                int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_118_DriveInfo_Title03");
                StringBuilder sb3 = new StringBuilder();
                int[] iArr3 = this.mCanBusInfoInt;
                arrayList3.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb3.append((iArr3[5] * 256) + iArr3[6]).append("KM").toString()));
                updateGeneralDriveData(arrayList3);
                updateDriveDataActivity(null);
            } else if (i2 == 2) {
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title01"), Integer.valueOf(this.mCanBusInfoInt[3])));
                this.PlanStatus = this.mCanBusInfoInt[3];
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title023"), resolveTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 5))).setValueStr(true));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title04"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
                this.WorkUtilFull = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title056"), resolveEndTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))).setValueStr(true));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title078"), resolveTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 5))).setValueStr(true));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title09"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
                this.WeekendUtilFull = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title1011"), resolveEndTime(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 5), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]))).setValueStr(true));
                int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02");
                int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title12");
                int[] iArr4 = this.mCanBusInfoInt;
                arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, resolveBatteryTime(iArr4[8], iArr4[9])).setValueStr(true));
                int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02");
                int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title13");
                int[] iArr5 = this.mCanBusInfoInt;
                arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, resolveBatteryTime(iArr5[10], iArr5[11])).setValueStr(true));
            } else if (i2 == 3) {
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title14"), Integer.valueOf(this.mCanBusInfoInt[3])));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_DriveInfo_HeadTitle_02"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_DriveInfo_HeadTitle_02", "_118_setting_Title15"), this.mCanBusInfoInt[4] + "%").setValueStr(true));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveStatus(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._118_setting_value02);
        }
        return this.mContext.getResources().getString(R.string._118_setting_value01);
    }

    private String resolveBatteryTime(int i, int i2) {
        int i3 = (i * 256) + i2;
        return (i3 / 60) + this.mContext.getResources().getString(R.string.hour) + (i3 % 60) + this.mContext.getResources().getString(R.string.min);
    }

    private String resolveTime(int i, int i2) {
        return i2 + ":" + (i == 0 ? "00" : i == 1 ? "15" : i == 2 ? "30" : i == 3 ? "45" : "");
    }

    private String resolveEndTime(int i, int i2, boolean z) {
        return z ? this.mContext.getResources().getString(R.string._118_setting_Title27) : i2 + ":" + (i == 0 ? "00" : i == 1 ? "15" : i == 2 ? "30" : i == 3 ? "45" : "");
    }

    public void updateCarModel(Context context, int i) {
        SharePreUtil.setIntValue(context, SHARE_118_CAR_MODEL, i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(9, 0, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setOriginal0x10() {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        GeneralOriginalCarDeviceData.cdStatus = "CD";
        GeneralOriginalCarDeviceData.runningState = getRunStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
        GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        this.rdm = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        this.rpt = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        int data1 = (getData1(this.mCanBusInfoInt[9]) * 60 * 60) + (getData2(this.mCanBusInfoInt[10]) * 60) + getData2(this.mCanBusInfoInt[11]);
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[7] * 256) + iArr[8];
        GeneralOriginalCarDeviceData.startTime = decimalFormat.format(getData1(this.mCanBusInfoInt[9])) + ":" + decimalFormat.format(getData2(this.mCanBusInfoInt[10])) + ":" + decimalFormat.format(getData2(this.mCanBusInfoInt[11]));
        GeneralOriginalCarDeviceData.endTime = decimalFormat.format(getHour(i)) + ":" + decimalFormat.format(getMinute(i)) + ":" + decimalFormat.format(getSecond(i));
        if (i >= data1 && i > 0) {
            GeneralOriginalCarDeviceData.progress = (data1 * 100) / i;
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, getOriginalCount(iArr2[3], iArr2[4])));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, getOriginalCount(iArr3[5], iArr3[6])));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private List getSongList() {
        if (this.mSongList == null) {
            this.mSongList = new ArrayList();
        }
        return this.mSongList;
    }

    private void songListSetSelected() {
        int[] iArr = this.mCanBusInfoInt;
        this.mPlayingIndex = (iArr[3] * 256) + iArr[4];
        if (GeneralOriginalCarDeviceData.songList != null) {
            Iterator<SongListEntity> it = GeneralOriginalCarDeviceData.songList.iterator();
            while (it.hasNext()) {
                it.next().setSelected(false);
            }
            GeneralOriginalCarDeviceData.songList.get(this.mPlayingIndex).setSelected(true);
        }
    }

    private void setOriginalData0x11() {
        try {
            getSongList();
            int[] iArr = this.mCanBusInfoInt;
            if (iArr.length < 4) {
                return;
            }
            int i = iArr[2];
            boolean z = true;
            if (i == 1 || i == 2 || i == 3) {
                songListSetSelected();
                ArrayList arrayList = new ArrayList();
                byte[] bArr = this.mCanBusInfoByte;
                arrayList.add(new OriginalCarDeviceUpdateEntity(this.mCanBusInfoInt[2] + 1, new String(bArr, 5, bArr.length - 5, "UNICODE")));
                GeneralOriginalCarDeviceData.mList = arrayList;
            } else if (i == 128) {
                int i2 = (iArr[3] * 256) + iArr[4];
                byte[] bArr2 = this.mCanBusInfoByte;
                String str = new String(bArr2, 5, bArr2.length - 5, "UNICODE");
                try {
                    this.mSongList.remove(i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                List<SongListEntity> list = this.mSongList;
                SongListEntity songListEntity = new SongListEntity(str);
                if (i2 != this.mPlayingIndex) {
                    z = false;
                }
                list.add(i2, songListEntity.setSelected(z));
                GeneralOriginalCarDeviceData.songList = this.mSongList;
            }
            updateOriginalCarDeviceActivity(null);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
    }

    private void setSteeringWheelControls0x01() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        switch (i) {
            case 17:
                realKeyClick(7);
                break;
            case 18:
                realKeyClick(8);
                break;
            case 19:
                realKeyClick(45);
                break;
            case 20:
                realKeyClick(46);
                break;
            case 21:
                realKeyClick(200);
                break;
            case 22:
                realKeyClick(20);
                break;
            case 23:
                realKeyClick(HotKeyConstant.K_VOICE_PICKUP);
                break;
            case 24:
                realKeyClick(14);
                break;
            case 25:
                realKeyClick(15);
                break;
            case 26:
                realKeyClick(2);
                break;
            case 27:
                realKeyClick(62);
                break;
            case 28:
            case 31:
                realKeyClick(48);
                break;
            case 29:
            case 30:
                realKeyClick(47);
                break;
            case 32:
                realKeyClick(49);
                break;
        }
    }

    private void setPanelButton0x04() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(152);
                break;
            case 2:
                realKeyClick(50);
                break;
            case 3:
                realKeyClick(3);
                break;
            case 4:
                realKeyClick(49);
                break;
            case 5:
                realKeyClick(8);
                break;
            case 6:
                realKeyClick(7);
                break;
            case 7:
                realKeyClick(47);
                break;
            case 8:
                realKeyClick(48);
                break;
            case 9:
                realKeyClick(1);
                break;
            case 10:
                realKeyClick(1);
                break;
            case 11:
                Intent intent = new Intent(this.mContext, (Class<?>) SettingActivity.class);
                intent.addFlags(268435456);
                intent.setAction(Constant.SETTING_OPEN_TARGET_PAGE);
                intent.putExtra(Constant.LEFT_INDEX, 0);
                intent.putExtra(Constant.RIGHT_INDEX, 9);
                this.mContext.startActivity(intent);
                break;
        }
    }

    private void setLights0x02() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, setLightData(this.mCanBusInfoInt[2])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAir0x05(Context context) {
        int i = this.mOutdoorTemperature;
        int i2 = this.mCanBusInfoInt[9];
        if (i != i2) {
            this.mOutdoorTemperature = i2;
            updateOutDoorTemp(context, (this.mCanBusInfoInt[9] - 40) + getTempUnitC(context));
        }
        byte[] bArr = this.mCanBusInfoByte;
        bArr[9] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_auto_wind = boolBit7;
        GeneralAirData.front_right_auto_wind = boolBit7;
        if (boolBit7) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
        } else {
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setCarSetting0x07() {
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2);
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1);
        int intFromByteWithBit15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1);
        int intFromByteWithBit16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 2);
        int intFromByteWithBit17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
        int intFromByteWithBit18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1);
        int intFromByteWithBit19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1);
        int intFromByteWithBit20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1);
        int intFromByteWithBit21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1);
        int intFromByteWithBit22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 2);
        int intFromByteWithBit23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 1);
        int intFromByteWithBit24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1);
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1);
        int intFromByteWithBit25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1);
        int intFromByteWithBit26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
        int intFromByteWithBit27 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2);
        int intFromByteWithBit28 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
        int intFromByteWithBit29 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
        int intFromByteWithBit30 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1);
        int intFromByteWithBit31 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 3);
        int intFromByteWithBit32 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1);
        int intFromByteWithBit33 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 1);
        int intFromByteWithBit34 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 1);
        int intFromByteWithBit35 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 1);
        int intFromByteWithBit36 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2);
        int intFromByteWithBit37 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 2);
        int intFromByteWithBit38 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 3, 2);
        int intFromByteWithBit39 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 1);
        int intFromByteWithBit40 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1);
        int intFromByteWithBit41 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1);
        int intFromByteWithBit42 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 5, 2);
        int intFromByteWithBit43 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 1);
        int intFromByteWithBit44 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 3, 1);
        int intFromByteWithBit45 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 1);
        int intFromByteWithBit46 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2);
        int intFromByteWithBit47 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 7, 1);
        int intFromByteWithBit48 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 5, 2);
        int intFromByteWithBit49 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 3, 2);
        int intFromByteWithBit50 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 3);
        int intFromByteWithBit51 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1);
        int intFromByteWithBit52 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 5, 2);
        int intFromByteWithBit53 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 1);
        int intFromByteWithBit54 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 3, 1);
        int intFromByteWithBit55 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 1);
        int intFromByteWithBit56 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 1, 1);
        int intFromByteWithBit57 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 1);
        int intFromByteWithBit58 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1);
        int intFromByteWithBit59 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr.length > 16 ? iArr[16] - 1 : 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_36"), Integer.valueOf(intFromByteWithBit28)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_37"), Integer.valueOf(intFromByteWithBit29)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_38"), Integer.valueOf(intFromByteWithBit30)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_46"), Integer.valueOf(intFromByteWithBit38)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_39"), Integer.valueOf(intFromByteWithBit31)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_40"), Integer.valueOf(intFromByteWithBit32)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_61"), Integer.valueOf(intFromByteWithBit53)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_34"), Integer.valueOf(intFromByteWithBit26)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_35"), Integer.valueOf(intFromByteWithBit27)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_33"), Integer.valueOf(intFromByteWithBit25)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_1", "_118_setting_title_47"), Integer.valueOf(intFromByteWithBit39)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_9"), Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_45"), Integer.valueOf(intFromByteWithBit37)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_10"), Integer.valueOf(intFromByteWithBit3)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_11"), Integer.valueOf(intFromByteWithBit4)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_12"), Integer.valueOf(intFromByteWithBit5)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_41"), Integer.valueOf(intFromByteWithBit33)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_13"), Integer.valueOf(intFromByteWithBit6)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_53"), Integer.valueOf(intFromByteWithBit45)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_58"), Integer.valueOf(intFromByteWithBit50)).setProgress(intFromByteWithBit50));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_57"), Integer.valueOf(intFromByteWithBit49)).setProgress(intFromByteWithBit49));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_62"), Integer.valueOf(intFromByteWithBit54)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_2", "_118_setting_title_110"), Integer.valueOf(intFromByteWithBit2)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_42"), Integer.valueOf(intFromByteWithBit34)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_14"), Integer.valueOf(intFromByteWithBit7)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_15"), Integer.valueOf(intFromByteWithBit8)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_16"), Integer.valueOf(intFromByteWithBit9)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_17"), Integer.valueOf(intFromByteWithBit10)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_18"), Integer.valueOf(intFromByteWithBit11)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_43"), Integer.valueOf(intFromByteWithBit35)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_63"), Integer.valueOf(intFromByteWithBit55)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_22"), Integer.valueOf(intFromByteWithBit15)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_3", "_118_setting_title_56"), Integer.valueOf(intFromByteWithBit48)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_4", "_118_setting_title_19"), Integer.valueOf(intFromByteWithBit12)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_4", "_118_setting_title_20"), Integer.valueOf(intFromByteWithBit13)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_4", "_118_setting_title_23"), Integer.valueOf(intFromByteWithBit16)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_24"), Integer.valueOf(intFromByteWithBit17)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_25"), Integer.valueOf(intFromByteWithBit18)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_26"), Integer.valueOf(intFromByteWithBit19)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_27"), Integer.valueOf(intFromByteWithBit20)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_28"), Integer.valueOf(intFromByteWithBit21)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_29"), Integer.valueOf(intFromByteWithBit22)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_5", "_118_setting_title_30"), Integer.valueOf(intFromByteWithBit23)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_68"), Integer.valueOf(i)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_44"), Integer.valueOf(intFromByteWithBit36)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_48"), Integer.valueOf(intFromByteWithBit40)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_49"), Integer.valueOf(intFromByteWithBit41)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_52"), Integer.valueOf(intFromByteWithBit44)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_6", "_118_setting_title_55"), Integer.valueOf(intFromByteWithBit47)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_7", "_118_setting_title_66"), Integer.valueOf(intFromByteWithBit58)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_7", "_118_setting_title_67"), Integer.valueOf(intFromByteWithBit59)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_54"), Integer.valueOf(intFromByteWithBit46)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_59"), Integer.valueOf(intFromByteWithBit51)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_60"), Integer.valueOf(intFromByteWithBit52)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_50"), Integer.valueOf(intFromByteWithBit42)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_51"), Integer.valueOf(intFromByteWithBit43)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_8", "_118_setting_title_64"), Integer.valueOf(intFromByteWithBit56)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_31"), Integer.valueOf(intFromByteWithBit24)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_65"), Integer.valueOf(intFromByteWithBit57)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_big_title_9"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_118_setting_big_title_9", "_118_setting_title_21"), Integer.valueOf(intFromByteWithBit14)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTrack0x09() {
        if (this.mCurrentCanDifferentId == 3) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 1792, 2991, 16);
            updateParkUi(null, this.mContext);
        } else {
            byte[] bArr2 = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr2[3], bArr2[2], 0, 540, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setCarInfo0x0A(byte[] bArr) {
        Context context;
        int i;
        Context context2;
        int i2;
        Context context3;
        int i3;
        if (isDoorMsgRepeat(bArr)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            context = this.mContext;
            i = R.string._118_setting_value_8;
        } else {
            context = this.mContext;
            i = R.string._118_setting_value_7;
        }
        arrayList.add(new DriverUpdateEntity(0, 1, context.getString(i)));
        arrayList.add(new DriverUpdateEntity(0, 2, getAccData(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3))));
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            context2 = this.mContext;
            i2 = R.string.reversing;
        } else {
            context2 = this.mContext;
            i2 = R.string.non_reverse;
        }
        arrayList.add(new DriverUpdateEntity(0, 3, context2.getString(i2)));
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            context3 = this.mContext;
            i3 = R.string.pull_up;
        } else {
            context3 = this.mContext;
            i3 = R.string.put_down;
        }
        arrayList.add(new DriverUpdateEntity(0, 4, context3.getString(i3)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftFrontDoorOpen = boolBit7;
        this.mIsFrontLeftOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = boolBit6;
        this.mIsFrontRightOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = boolBit5;
        this.mIsRearLeftOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = boolBit4;
        this.mIsRearRightOpen = boolBit4;
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = boolBit3;
        this.mIsBackOpen = boolBit3;
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setCompass0x0B(int[] iArr) {
        if (isDataChange(iArr)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_91"), 0, getCompassData(iArr[2])).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_91"), 1, getCompassState(DataHandleUtils.getIntFromByteWithBit(iArr[3], 6, 2))).setValueStr(true));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 6);
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_91"), 2, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit - 1));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setOutTemp0x15() {
        int i = this.mCurrentCanDifferentId;
        if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8) {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private void setRearRadar0x22() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr.length > 4 ? iArr[4] - 1 : 0;
        int i2 = iArr.length > 5 ? iArr[5] - 1 : 0;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(5, iArr2[2], iArr2[3], i, i2);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar0x23() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(5, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAmplifier0x31() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        int i2 = iArr[4];
        int i3 = iArr[5];
        int i4 = iArr[6];
        int i5 = iArr[7];
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr[8], 1, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 0, Integer.valueOf(intFromByteWithBit6)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 1, Integer.valueOf(intFromByteWithBit5)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 2, Integer.valueOf(intFromByteWithBit4)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 3, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_118_setting_title_93"), 4, Integer.valueOf(intFromByteWithBit3)).setProgress(intFromByteWithBit3));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAmplifierData.frontRear = 10 - i2;
        GeneralAmplifierData.leftRight = i - 10;
        GeneralAmplifierData.volume = intFromByteWithBit2;
        GeneralAmplifierData.bandBass = i3 - 1;
        GeneralAmplifierData.bandMiddle = i4 - 1;
        GeneralAmplifierData.bandTreble = i5 - 1;
        if (isAmplifierDataChange(new int[]{GeneralAmplifierData.leftRight, GeneralAmplifierData.frontRear, GeneralAmplifierData.bandBass, GeneralAmplifierData.bandMiddle, GeneralAmplifierData.bandTreble, GeneralAmplifierData.volume})) {
            updateAmplifierActivity(null);
        }
        SharePreUtil.setIntValue(this.mContext, SHARE_118_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_118_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_118_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_118_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
        SharePreUtil.setIntValue(this.mContext, SHARE_118_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_118_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte) DataHandleUtils.setIntByteWithBit(i8, 7, !z), (byte) i6, (byte) i7, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 1, (byte) 1, (byte) 2, (byte) 2);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -112, radioCurrentBand});
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
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            i3 = i4;
        }
        int[] timeWithSeconds = getTimeWithSeconds(i);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -112, 3, (byte) 255}, new byte[]{0, (byte) i3, (byte) i5, (byte) timeWithSeconds[0], (byte) timeWithSeconds[1], (byte) timeWithSeconds[2]}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte b10 = (byte) (b == 9 ? 6 : 4);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, b10});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -112, b10, (byte) 255}, new byte[]{(byte) i, b7, 0, b3, b4, b5}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 8});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 9});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 10});
    }

    private int[] getTimeWithSeconds(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.sendMessageDelayed(handler.obtainMessage(101, i, 0, obj), j);
    }

    private String getCompassState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._118_setting_value_83);
        }
        if (i != 1) {
            return i != 2 ? "" : this.mContext.getString(R.string._118_setting_value_84);
        }
        return this.mContext.getString(R.string.compass_calibrating);
    }

    private String getCompassData(int i) {
        switch (i) {
            case 15:
                return this.mContext.getString(R.string._118_setting_value_82);
            case 16:
                return this.mContext.getString(R.string.north);
            case 17:
                return this.mContext.getString(R.string.northeast);
            case 18:
                return this.mContext.getString(R.string.east);
            case 19:
                return this.mContext.getString(R.string.southeast);
            case 20:
                return this.mContext.getString(R.string.south);
            case 21:
                return this.mContext.getString(R.string.southwest);
            case 22:
                return this.mContext.getString(R.string.weat);
            case 23:
                return this.mContext.getString(R.string.northwest);
            default:
                return "";
        }
    }

    private String getAccData(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._118_setting_value_80);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._118_setting_value_81) + " " + this.mContext.getString(R.string._118_setting_value_7);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._118_setting_value_81);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._118_setting_value_81) + " " + this.mContext.getString(R.string._118_setting_value_8);
        }
        return this.mContext.getString(R.string.default_value);
    }

    private boolean isDoorDataChange() {
        boolean z = this.mIsFrontLeftOpen;
        if (z == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsFrontOpen == this.mIsFrontOpenNow) {
            return false;
        }
        this.mIsFrontLeftOpenNow = z;
        this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
        this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
        this.mIsRearRightOpenNow = this.mIsRearRightOpen;
        this.mIsBackOpenNow = this.mIsBackOpen;
        this.mIsFrontOpenNow = this.mIsFrontOpen;
        return true;
    }

    private void realKeyClick(int i) {
        realKeyClick1(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    private String setSpeedData(int i, int i2) {
        return String.format("%.1f", Float.valueOf(((i * 256) + i2) / 10.0f));
    }

    private String setLightData(int i) {
        if (i == 34) {
            return this.mContext.getString(R.string._118_setting_value_78);
        }
        if (i == 200) {
            return this.mContext.getString(R.string._118_setting_value_79);
        }
        if (i > 34 && i < 200) {
            return String.valueOf(i);
        }
        return this.mContext.getString(R.string.default_value);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        String str;
        if (i >= 1 && i <= 40) {
            switch (this.mCurrentCanDifferentId) {
                case 0:
                case 1:
                case 5:
                case 13:
                    str = (i * 1.0f) + getTempUnitC(this.mContext);
                    break;
                case 2:
                case 3:
                case 4:
                case 8:
                case 10:
                case 11:
                case 12:
                case 14:
                    str = ((i / 2.0f) + 15.5d) + getTempUnitC(this.mContext);
                    break;
                case 6:
                case 7:
                case 9:
                    str = ((i / 2.0f) + 8.0f) + getTempUnitC(this.mContext);
                    break;
                default:
                    str = "";
                    break;
            }
        } else {
            str = (i * 1.0f) + getTempUnitF(this.mContext);
        }
        if (i == 0) {
            str = "LO";
        }
        return 127 == i ? "HI" : str;
    }

    private String resolveRearTemp(int i) {
        return i == 0 ? "LO" : 126 == i ? "HI" : (i < 12 || i > 44) ? "" : ((i / 2.0f) + 8.0f) + getTempUnitC(this.mContext);
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[2];
        return this.mCurrentCanDifferentId == 3 ? (i < 0 || i > 255) ? "" : (i - 85) + this.mContext.getString(R.string.str_temp_c_unit) : (i < 0 || i > 125) ? "" : (i - 40) + this.mContext.getString(R.string.str_temp_c_unit);
    }

    private boolean isAmplifierDataChange(int[] iArr) {
        if (Arrays.equals(iArr, this.mAmplifierDataNow)) {
            return false;
        }
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.hzbhd.canbus.car._118.MsgMgr$1] */
    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_118_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_118_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_118_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_118_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_118_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_118_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        }
        new Thread() { // from class: com.hzbhd.canbus.car._118.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 10)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 10)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 1)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 1)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 1)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String getRunStatus(int i) {
        switch (i) {
            case 0:
                return this.mContext.getString(R.string._118_setting_value_89);
            case 1:
                return this.mContext.getString(R.string._118_setting_value_90);
            case 2:
                return this.mContext.getString(R.string._118_setting_value_91);
            case 3:
                return this.mContext.getString(R.string._118_setting_value_92);
            case 4:
                return this.mContext.getString(R.string._118_setting_value_93);
            case 5:
                return this.mContext.getString(R.string._118_setting_value_94);
            case 6:
                return this.mContext.getString(R.string._118_setting_value_95);
            case 7:
            default:
                return "";
            case 8:
                return this.mContext.getString(R.string._118_setting_value_96);
            case 9:
                return this.mContext.getString(R.string._118_setting_value_97);
        }
    }

    private String getOriginalCount(int i, int i2) {
        return String.valueOf((i * 256) + i2);
    }

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    public Activity getCurrentActivity() {
        return getActivity();
    }
}
