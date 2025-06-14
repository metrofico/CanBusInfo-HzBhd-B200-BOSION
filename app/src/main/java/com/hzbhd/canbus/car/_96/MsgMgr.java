package com.hzbhd.canbus.car._96;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.view.ParkAssistFloatView;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String ORIGINAL_RADIO_CD = "original_radio_cd";
    static final String Temp_Unit = "Temp_Unit";
    static boolean angleWide = false;
    private static boolean isDoorFirst = true;
    private static boolean isParkingFirst = true;
    private static boolean isWarnFirst = true;
    private static int outDoorTemp;
    static boolean overlook;
    private static int rearTemp;
    int FactoryCode;
    private int ford_park_assist_info;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwcInfoCopy;
    private byte[] mCanBusWarnInfoCopy;
    private int mCanId;
    private byte[] mCanbusSync0xD3InfoCopy;
    private Context mContext;
    private byte[] mDoorInfoCopy;
    private ID3[] mId3s;
    private byte[] mOriginalCdCopy;
    private byte[] mOriginalCdInfoCopy;
    private byte[] mOriginalRadioCopy;
    private byte[] mOriginalRadioInfoCopy;
    private ParkAssistFloatView mParkAssistFloatView;
    private byte[] mParkingInfoCopy;
    private int mSyncBtStatus;
    private SparseIntArray mSyncIconResIdArray;
    private UiMgr mUiMgr;
    private int value;
    private static ArrayList<Boolean> warningList = new ArrayList<>();
    private static int[] fordHiworldWarinIds = {R.string.ford_hiword_alert_2_0, R.string.ford_hiword_alert_2_1, -1, -1, -1, -1, -1, -1, R.string.ford_hiword_alert_3_0, R.string.ford_hiword_alert_3_1, R.string.ford_hiword_alert_3_2, -1, -1, -1, -1, -1, R.string.ford_hiword_alert_4_0, R.string.ford_hiword_alert_4_1, R.string.ford_hiword_alert_4_2, R.string.ford_hiword_alert_4_3, R.string.ford_hiword_alert_4_4, R.string.ford_hiword_alert_4_5, R.string.ford_hiword_alert_4_6, R.string.ford_hiword_alert_4_7, R.string.ford_hiword_alert_5_0, R.string.ford_hiword_alert_5_1, R.string.ford_hiword_alert_5_2, R.string.ford_hiword_alert_5_3, R.string.ford_hiword_alert_5_4, -1, -1, -1, R.string.ford_hiword_alert_6_0, R.string.ford_hiword_alert_6_1, R.string.ford_hiword_alert_6_2, R.string.ford_hiword_alert_6_3, R.string.ford_hiword_alert_6_4, R.string.ford_hiword_alert_6_5, R.string.ford_hiword_alert_6_6, -1, R.string.ford_hiword_alert_7_0, R.string.ford_hiword_alert_7_1, R.string.ford_hiword_alert_7_2, R.string.ford_hiword_alert_7_3, R.string.ford_hiword_alert_7_4, R.string.ford_hiword_alert_7_5, R.string.ford_hiword_alert_7_6, R.string.ford_hiword_alert_7_7, R.string.ford_hiword_alert_8_0, R.string.ford_hiword_alert_8_1, R.string.ford_hiword_alert_8_2, R.string.ford_hiword_alert_8_3, R.string.ford_hiword_alert_8_4, -1, -1, -1, R.string.ford_hiword_alert_9_0, R.string.ford_hiword_alert_9_1, R.string.ford_hiword_alert_9_2, R.string.ford_hiword_alert_9_3, R.string.ford_hiword_alert_9_4, R.string.ford_hiword_alert_9_5, R.string.ford_hiword_alert_9_6, R.string.ford_hiword_alert_9_7, -1, -1, R.string.ford_hiword_alert_10_2, R.string.ford_hiword_alert_10_3, R.string.ford_hiword_alert_10_4, R.string.ford_hiword_alert_10_5, R.string.ford_hiword_alert_10_6, -1, R.string.ford_hiword_alert_11_0, -1, -1, R.string.ford_hiword_alert_11_3, R.string.ford_hiword_alert_11_4, R.string.ford_hiword_alert_11_5, R.string.ford_hiword_alert_11_6, R.string.ford_hiword_alert_11_7};
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_REAR_AIR_TEMP_CHANGE_FORD = "is_rear_air_temp_change_ford";
    private final String IS_REAR_AIR_WIND_LV_CHANGE = "is_rear_air_wind_lv_change";
    private final String IS_REAR_AIR_POWER = "is_rear_air_power";
    private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
    private final String IS_REAR_LOCK = "is_rear_lock";
    private final int DATA_TYPE = 1;
    private boolean isRadioFirst = true;
    private boolean isCdFirst = true;
    private boolean mIsAirFirst = true;
    String data1 = "";
    String data2 = "";
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
    private List<OriginalCarDeviceUpdateEntity> mList1 = new ArrayList();
    private List<OriginalCarDeviceUpdateEntity> mList2 = new ArrayList();

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -108});
        setSelectCarType();
        initId3();
        updateOrignalSetting();
        initAmplifierData(context);
        Log.d("lai", "initCommand: " + getCurrentEachCanId());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        if (getCurrentEachCanId() == 17) {
            SelectCanTypeUtil.enableApp(context, Constant.SyncActivity);
        } else {
            SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        }
        Log.d("lai", "afterServiceNormalSetting: " + getCurrentEachCanId());
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, getUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._96.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -83, 4, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -83, 6, (byte) GeneralAmplifierData.bandTreble}, new byte[]{22, -83, 5, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume}, new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 7)}}).iterator();

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

    private void setSelectCarType() {
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 3});
    }

    public MsgMgr() {
        GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(0));
        GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(1));
        GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(2));
        GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(3));
        GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(4));
        GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(0));
        GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(1));
        GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(2));
        GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(3));
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.mSyncIconResIdArray = sparseIntArray;
        sparseIntArray.put(0, R.drawable.ford_sync_null);
        this.mSyncIconResIdArray.append(1, R.drawable.ford_sync_null);
        this.mSyncIconResIdArray.append(2, R.drawable.ford_sync_null);
        this.mSyncIconResIdArray.append(3, R.drawable.ford_sync_icon_131);
        this.mSyncIconResIdArray.append(4, R.drawable.ford_sync_icon_205);
        this.mSyncIconResIdArray.append(5, R.drawable.ford_sync_icon_11);
        this.mSyncIconResIdArray.append(6, R.drawable.ford_sync_icon_145);
        this.mSyncIconResIdArray.append(7, R.drawable.ford_sync_icon_130);
        this.mSyncIconResIdArray.append(8, R.drawable.ford_sync_icon_40);
        this.mSyncIconResIdArray.append(9, R.drawable.ford_sync_icon_39);
        this.mSyncIconResIdArray.append(16, R.drawable.ford_hiword_sync_icon_97);
        this.mSyncIconResIdArray.append(17, R.drawable.ford_hiword_sync_icon_98);
        this.mSyncIconResIdArray.append(18, R.drawable.ford_hiword_sync_icon_99);
        this.mSyncIconResIdArray.append(19, R.drawable.ford_hiword_sync_icon_100);
        this.mSyncIconResIdArray.append(20, R.drawable.ford_hiword_sync_icon_101);
        this.mSyncIconResIdArray.append(21, R.drawable.ford_hiword_sync_icon_102);
        this.mSyncIconResIdArray.append(32, R.drawable.ford_hiword_sync_icon_103);
        this.mSyncIconResIdArray.append(33, R.drawable.ford_hiword_sync_icon_104);
        this.mSyncIconResIdArray.append(34, R.drawable.ford_hiword_sync_icon_105);
        this.mSyncIconResIdArray.append(35, R.drawable.ford_hiword_sync_icon_106);
        this.mSyncIconResIdArray.append(36, R.drawable.ford_hiword_sync_icon_107);
        this.mSyncIconResIdArray.append(37, R.drawable.ford_hiword_sync_icon_108);
        GeneralSyncData.mSyncTopIconResIdArray = new int[6];
        GeneralSyncData.mSyncTopIconCount = 6;
        GeneralSyncData.mInfoLineShowAmount = 5;
        GeneralSyncData.mIsShowSoftKey = true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            setPanelBtnInfo();
            dccOffOnCtrl(bArr);
            return;
        }
        if (i == 34) {
            setOriginalPanel();
            return;
        }
        if (i == 38) {
            setCarType();
            return;
        }
        if (i == 56) {
            setCarNumber();
            return;
        }
        if (i == 97) {
            if (isParkingMsgReturn(bArr)) {
                return;
            }
            setEnvironmentLight();
            refreshFordParkAssistFlowView(this.mCanBusInfoInt);
            return;
        }
        if (i == 116) {
            if (isWarningMsgReturn(bArr)) {
                return;
            }
            Intent intent = new Intent(this.mContext, (Class<?>) WarningActivity.class);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
            setWarningInfo();
            return;
        }
        if (i == 136) {
            if (isRadioInfoRepeat(bArr)) {
                return;
            }
            setRadioPresetInfo();
            setOriginalRadioPage();
            return;
        }
        if (i == 148) {
            languageSettingInfo();
            return;
        }
        if (i == 174) {
            if (isCdMsgRepeat(bArr)) {
                return;
            }
            SharePreUtil.setIntValue(this.mContext, ORIGINAL_RADIO_CD, 0);
            setOriginalCdPage();
            setCdDataInfo();
            return;
        }
        if (i == 208) {
            set0xD0SyncDisplayInfo();
            return;
        }
        if (i == 232) {
            setReversingVideo();
            setOriginalCameraStatusInfo();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 49) {
            setFrontAirData();
            return;
        }
        if (i == 50) {
            setCarStatus();
            return;
        }
        if (i == 65) {
            setFrontRearRadar();
            return;
        }
        if (i == 66) {
            setLeftRightRadar();
            return;
        }
        if (i == 103) {
            setLightSetting();
            return;
        }
        if (i == 104) {
            setTipsInfo();
            return;
        }
        if (i == 132) {
            if (isRadioMsgRepeat(bArr)) {
                return;
            }
            SharePreUtil.setIntValue(this.mContext, ORIGINAL_RADIO_CD, 1);
            setRadioInfo();
            setOriginalRadioPage();
            return;
        }
        if (i == 133) {
            setSportSettingInfo();
            return;
        }
        if (i == 165) {
            if (isCdInfoRepeat(bArr)) {
                return;
            }
            setCdInfo();
            return;
        }
        if (i == 166) {
            setAmplifierData();
            return;
        }
        if (i == 210) {
            set0xD2SyncPlayInfo();
            return;
        }
        if (i != 211) {
            switch (i) {
                case 17:
                    keyControl0x11();
                    setTrackInfo();
                    updateSpeedInfo(this.mCanBusInfoInt[3]);
                    break;
                case 18:
                    if (!isDoorMsgReturn(bArr)) {
                        setCarDoorInfo();
                        setCarStatusInfo();
                        break;
                    }
                    break;
                case 19:
                    setDriveData();
                    break;
            }
            return;
        }
        set0xD3SyncStatus(context);
    }

    private void set0xD0SyncDisplayInfo() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralSyncData.mSyncTopIconCount = 6;
            GeneralSyncData.mSyncScreemNumber = this.mCanBusInfoInt[2];
            int i = this.mCanBusInfoInt[3];
            int i2 = i >> 4;
            int i3 = i & 15;
            if (i2 >= 1 && i2 <= 5) {
                String syncInfo = getSyncInfo(this.mCanBusInfoByte);
                for (SyncListUpdateEntity syncListUpdateEntity : GeneralSyncData.mInfoUpdateEntityList) {
                    if (syncListUpdateEntity.getIndex() == i2 - 1) {
                        if (i3 == 0) {
                            syncListUpdateEntity.setInfo("");
                        }
                        syncListUpdateEntity.setInfo(syncListUpdateEntity.getInfo() + syncInfo);
                        updateSyncActivity(null);
                        return;
                    }
                }
                return;
            }
            if (i2 >= 10 && i2 <= 13) {
                String syncInfo2 = getSyncInfo(this.mCanBusInfoByte);
                for (SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity : GeneralSyncData.mSoftKeyUpdateEntityList) {
                    if (syncSoftKeyUpdateEntity.getIndex() == i2 - 10) {
                        if (i3 == 0) {
                            syncSoftKeyUpdateEntity.setName("");
                        }
                        syncSoftKeyUpdateEntity.setName(syncSoftKeyUpdateEntity.getName() + syncInfo2);
                        updateSyncActivity(null);
                        return;
                    }
                }
                return;
            }
            if (i2 == 15) {
                GeneralSyncData.mSelectedLineIndex = -1;
                for (SyncListUpdateEntity syncListUpdateEntity2 : GeneralSyncData.mInfoUpdateEntityList) {
                    int index = syncListUpdateEntity2.getIndex();
                    int[] iArr = this.mCanBusInfoInt;
                    int i4 = index * 2;
                    int i5 = iArr[i4 + 10];
                    int i6 = iArr[i4 + 11];
                    syncListUpdateEntity2.setLeftIconResId(this.mSyncIconResIdArray.get(i5));
                    syncListUpdateEntity2.setRightIconResId(this.mSyncIconResIdArray.get(i6));
                    if (i5 == 2 && i6 == 2) {
                        GeneralSyncData.mSelectedLineIndex = index;
                    }
                }
                for (int i7 = 0; i7 < GeneralSyncData.mSyncTopIconCount; i7++) {
                    GeneralSyncData.mSyncTopIconResIdArray[i7] = this.mSyncIconResIdArray.get(this.mCanBusInfoInt[i7 + 4]);
                }
                updateSyncActivity(null);
            }
        }
    }

    private void set0xD2SyncPlayInfo() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            int[] time = getTime(iArr[4] + (iArr[5] * 256));
            GeneralSyncData.mSyncTime = this.mDecimalFormat00.format(time[0]) + ":" + this.mDecimalFormat00.format(time[1]) + ":" + this.mDecimalFormat00.format(time[2]);
            GeneralSyncData.mSyncScreemNumber = this.mCanBusInfoInt[2];
            GeneralSyncData.mIsSyncTimeChange = true;
            updateSyncActivity(null);
        }
    }

    private void set0xD3SyncStatus(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            if (this.mCanBusInfoInt[2] == 0) {
                exitAuxIn2();
            } else {
                enterAuxIn2(context, Constant.SyncActivity);
            }
            int i = this.mSyncBtStatus;
            int i2 = this.mCanBusInfoInt[4];
            if (i != i2) {
                this.mSyncBtStatus = i2;
                getUiMgr(context).updateBtStatus(context, this.mSyncBtStatus);
            }
        }
    }

    private boolean isSync0xD3MsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusSync0xD3InfoCopy)) {
            return true;
        }
        this.mCanbusSync0xD3InfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void keyControl0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 8) {
            realKeyClick(48);
            return;
        }
        if (i == 9) {
            realKeyClick(47);
            return;
        }
        if (i == 23) {
            realKeyClick(128);
            return;
        }
        if (i == 45) {
            realKeyClick(59);
            return;
        }
        if (i == 55) {
            realKeyClick(58);
            return;
        }
        if (i == 98) {
            realKeyClick(95);
            return;
        }
        if (i != 101) {
            switch (i) {
                case 0:
                    realKeyClick(0);
                    break;
                case 1:
                    realKeyClick(7);
                    break;
                case 2:
                    realKeyClick(8);
                    break;
                case 3:
                    realKeyClick(3);
                    break;
                case 4:
                    realKeyClick(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick(HotKeyConstant.K_NEXT_HANGUP);
                    break;
                case 6:
                    realKeyClick(206);
                    break;
                default:
                    switch (i) {
                        case 11:
                            realKeyClick(2);
                            break;
                        case 12:
                            realKeyClick(57);
                            break;
                        case 13:
                            realKeyClick(45);
                            break;
                        case 14:
                            realKeyClick(46);
                            break;
                        case 15:
                            realKeyClick(49);
                            break;
                    }
            }
            return;
        }
        realKeyClick(31);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setFrontAirData() {
        GeneralAirData.fahrenheit_celsius = SharePreUtil.getBoolValue(this.mContext, Temp_Unit, false);
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_heat = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.center_wheel = "Lv." + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 2) {
            GeneralAirData.front_defog = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_lock = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[12];
        rearTemp = i2;
        outDoorTemp = iArr[13];
        GeneralAirData.rear_temperature = resolveRearTemp(i2);
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        boolean zIsOnlyRearAirDataChange = isOnlyRearAirDataChange();
        boolean zIsOnlyOutDoorDataChange = isOnlyOutDoorDataChange();
        if (zIsOnlyRearAirDataChange && !isOnlyOutDoorDataChange()) {
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_temp_change_ford", rearTemp);
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_lv_change", GeneralAirData.rear_wind_level);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_air_power", GeneralAirData.rear_power);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_lock", GeneralAirData.rear_lock);
            updateAirActivity(this.mContext, 1002);
        }
        if (!zIsOnlyRearAirDataChange && !zIsOnlyOutDoorDataChange) {
            updateAirActivity(this.mContext, 1001);
        }
        if (zIsOnlyOutDoorDataChange) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[13];
        if (GeneralAirData.fahrenheit_celsius) {
            return ((float) ((i * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
        }
        return ((float) ((((i * 0.5d) - 40.0d) * 1.8d) + 32.0d)) + getTempUnitF(this.mContext);
    }

    private void setOriginalCameraStatusInfo() {
        angleWide = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        overlook = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRearRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        int[] iArr3 = this.mCanBusInfoInt;
        RadarInfoUtil.setProbeRadarLocationData(7, iArr3[10], iArr3[11], 0, 0);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setLeftRightRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRightRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(7, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void languageSettingInfo() {
        int i = this.mCanBusInfoInt[2];
        int i2 = i != 27 ? i != 0 ? i - 1 : 0 : 2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i2)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTipsInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])))));
        SharePreUtil.setBoolValue(this.mContext, Temp_Unit, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLightSetting() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new SettingUpdateEntity(2, i, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit(i, this.mCanBusInfoInt[3])))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSportSettingInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setReversingVideo() {
        forceReverse(this.mContext, this.mCanBusInfoInt[5] == 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setEnvironmentLight() {
        ArrayList arrayList = new ArrayList();
        this.FactoryCode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8))));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x62_data3_765"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x62_data3_765", "_94_atmosphere_lamp"), Integer.valueOf(this.mCanBusInfoInt[11])).setProgress(this.mCanBusInfoInt[11]));
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._96.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                TypeInView.toJudge(MsgMgr.this.FactoryCode);
            }
        });
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarRangeInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        byte[] bArr = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(0, 2, sb.append((float) (bytesToLong(new byte[]{bArr[8], bArr[7], bArr[6]}) * 0.1d)).append("Km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCarNumber() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(0, 3, new String(Arrays.copyOfRange(bArr, 2, bArr.length))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarStatus() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        byte[] bArr = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(0, 4, sb.append(bytesToLong(new byte[]{bArr[5], bArr[4]})).append("Rpm").toString()));
        StringBuilder sb2 = new StringBuilder();
        byte[] bArr2 = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(0, 5, sb2.append(bytesToLong(new byte[]{bArr2[7], bArr2[6]})).append("Km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setWarningInfo() {
        warningList = (ArrayList) getArrayEachBitsBool(this.mCanBusInfoInt, 0).clone();
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        for (int i = 0; i < warningList.size(); i++) {
            if (warningList.get(i).booleanValue() && fordHiworldWarinIds[i] != -1) {
                arrayList.add(new WarningEntity(this.mContext.getResources().getString(fordHiworldWarinIds[i])));
            }
        }
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
    }

    private void setCarDoorInfo() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setPanelBtnInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick2(0);
                break;
            case 1:
                realKeyClick2(HotKeyConstant.K_SLEEP);
                break;
            case 2:
                realKeyClick2(45);
                break;
            case 3:
                realKeyClick2(46);
                break;
            case 5:
                realKeyClick2(4);
                break;
            case 6:
                realKeyClick2(50);
                break;
            case 9:
                realKeyClick2(3);
                break;
            case 10:
                realKeyClick2(33);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 21});
                break;
            case 11:
                realKeyClick2(34);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 22});
                break;
            case 12:
                realKeyClick2(35);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 23});
                break;
            case 13:
                realKeyClick2(36);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 24});
                break;
            case 14:
                realKeyClick2(37);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 25});
                break;
            case 15:
                realKeyClick2(38);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 26});
                break;
            case 16:
                realKeyClick2(95);
                break;
            case 17:
                realKeyClick2(31);
                break;
            case 19:
                realKeyClick2(HotKeyConstant.K_TIME);
                break;
            case 23:
                realKeyClick2(45);
                break;
            case 24:
                realKeyClick2(46);
                break;
            case 25:
                realKeyClick2(47);
                break;
            case 26:
                realKeyClick2(48);
                break;
            case 31:
                realKeyClick2(141);
                break;
            case 32:
                realKeyClick2(128);
                break;
            case 33:
                realKeyClick2(HotKeyConstant.K_DARK_MODE);
                break;
            case 36:
                realKeyClick2(59);
                break;
            case 40:
                realKeyClick2(68);
                break;
            case 42:
                realKeyClick2(49);
                break;
            case 44:
                realKeyClick2(2);
                break;
            case 45:
                realKeyClick2(129);
                break;
            case 46:
                realKeyClick2(94);
                break;
            case 48:
                realKeyClick2(39);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 27});
                break;
            case 49:
                realKeyClick2(40);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 28});
                break;
            case 50:
                realKeyClick2(41);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 29});
                break;
            case 51:
                realKeyClick2(32);
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 2, 20});
                break;
            case 52:
                realKeyClick2(63);
                break;
            case 53:
                realKeyClick2(64);
                break;
            case 54:
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 1});
                break;
            case 55:
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 2});
                break;
            case 56:
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 3});
                break;
            case 57:
                CanbusMsgSender.sendMsg(new byte[]{22, -38, 36, 1, 4});
                break;
            case 58:
                realKeyClick2(130);
                break;
            case 59:
                realKeyClick2(59);
                break;
            case 60:
                realKeyClick2(48);
                break;
            case 61:
                realKeyClick2(47);
                break;
            case 62:
                realKeyClick2(75);
                break;
            case 63:
                realKeyClick2(151);
                break;
            case 64:
                realKeyClick2(152);
                break;
        }
    }

    private void dccOffOnCtrl(byte[] bArr) {
        if (this.mCanBusInfoInt[2] != 18 || isSwcMsgReturn(bArr)) {
            return;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) MainActivity.class);
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
    }

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isPanelBtnKeygRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void setOriginalPanel() {
        if (this.mCanBusInfoInt[2] == 1) {
            if (this.mCanBusInfoByte[3] > 0) {
                realKeyClick3_1(7);
            }
            if (this.mCanBusInfoByte[3] < 0) {
                realKeyClick3_1(8);
            }
        }
        if (this.mCanBusInfoInt[2] == 2) {
            if (this.mCanBusInfoByte[3] > 0) {
                realKeyClick3_2(48);
            }
            if (this.mCanBusInfoByte[3] < 0) {
                realKeyClick3_2(47);
            }
        }
    }

    private void realKeyClick3_1(int i) {
        realKeyClick3_1(this.mContext, i, this.mCanBusInfoInt[2], Math.abs((int) this.mCanBusInfoByte[3]));
    }

    private void realKeyClick3_2(int i) {
        realKeyClick3_2(this.mContext, i, this.mCanBusInfoInt[2], Math.abs((int) this.mCanBusInfoByte[3]));
    }

    private void setCarStatusInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            this.data1 = this.mContext.getResources().getString(R.string._96_flame_out);
        } else if (i == 1) {
            this.data1 = this.mContext.getResources().getString(R.string._96_acc_on_mode);
        } else if (i == 2) {
            this.data1 = this.mContext.getResources().getString(R.string._96_operating_mode);
        } else if (i == 3) {
            this.data1 = this.mContext.getResources().getString(R.string._96_fire_mode);
        } else if (i == 255) {
            this.data1 = this.mContext.getResources().getString(R.string._96_invalid_mode);
        }
        int i2 = this.mCanBusInfoInt[3];
        if (i2 == 0) {
            this.data2 = this.mContext.getResources().getString(R.string.invalid);
        } else if (i2 == 1) {
            this.data2 = "P";
        } else if (i2 == 2) {
            this.data2 = "N";
        } else if (i2 == 3) {
            this.data2 = "R";
        } else if (i2 == 4) {
            this.data2 = "D";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, this.data1));
        arrayList.add(new DriverUpdateEntity(0, 1, this.data2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarVolumeCtrl() {
        try {
            int[] iArr = this.mCanBusInfoInt;
            switch (iArr[2]) {
                case 1:
                    int i = iArr[3];
                    if (i == 1) {
                        changeBandFm1();
                        break;
                    } else if (i == 2) {
                        changeBandFm2();
                        break;
                    } else if (i == 3) {
                        changeBandAm1();
                        break;
                    } else if (i == 4) {
                        changeBandAm2();
                        break;
                    } else {
                        break;
                    }
                case 2:
                    changeBandAm1();
                    SystemClock.sleep(500L);
                    FutureUtil.instance.setCurrentFreq((this.mCanBusInfoByte[3] & 255) + "" + (this.mCanBusInfoByte[4] & 255) + "");
                    break;
                case 3:
                    changeBandFm1();
                    SystemClock.sleep(500L);
                    FutureUtil.instance.setCurrentFreq((this.mCanBusInfoByte[3] & 255) + "." + (this.mCanBusInfoByte[4] & 255));
                    break;
                case 4:
                    playPresetChann((this.mCanBusInfoByte[3] - 1) & 255);
                    break;
                case 5:
                    if (iArr[3] == 1) {
                        realKeyClick4(this.mContext, 45);
                        break;
                    } else {
                        realKeyClick4(this.mContext, 46);
                        break;
                    }
                case 6:
                    FutureUtil.instance.gotoTrack(this.mCanBusInfoInt[3]);
                    break;
                case 7:
                    if (iArr[3] == 1) {
                        FutureUtil.instance.playMpeg();
                        break;
                    } else {
                        FutureUtil.instance.pauseMpeg();
                        break;
                    }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setAmplifierData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 7;
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 7;
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
            saveAmplifierData(this.mContext, this.mCanId);
            updateAmplifierActivity(new Bundle());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
        arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarType() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i != 1 && i != 2) {
            arrayList.add(new DriverUpdateEntity(0, 6, this.mContext.getResources().getString(R.string.ford_car_type)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setRadioInfo() {
        GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.radio);
        GeneralOriginalCarDeviceData.runningState = getRadioStatus(this.mCanBusInfoInt[6]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mCanBusInfoInt[2] == 1 ? "AM" : "FM"));
        if (this.mCanBusInfoInt[2] == 1) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb.append(DataHandleUtils.getMsbLsbResult(iArr[4], iArr[3])).append("KHz").toString()));
        } else {
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append(DataHandleUtils.getMsbLsbResult(iArr2[4], iArr2[3]) / 10.0f).append("MHz").toString()));
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, getPreStorage(this.mCanBusInfoInt[5])));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setRadioPresetInfo() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            for (int i2 = 1; i2 <= 18; i2++) {
                int[] iArr = this.mCanBusInfoInt;
                int i3 = i2 * 2;
                arrayList.add(new SongListEntity(((iArr[i3 + 2] * 256) + iArr[i3 + 1]) + "KHz"));
            }
        } else if (i == 2) {
            for (int i4 = 1; i4 <= 12; i4++) {
                int[] iArr2 = this.mCanBusInfoInt;
                int i5 = i4 * 2;
                arrayList.add(new SongListEntity((((iArr2[i5 + 2] * 256) + iArr2[i5 + 1]) / 10.0f) + "MHz"));
            }
        }
        GeneralOriginalCarDeviceData.songList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setCdDataInfo() {
        GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.cd);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr[7] * 256) + iArr[8]).append("").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append((iArr2[9] * 256) + iArr2[10]).append("").toString()));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]) ? this.mContext.getResources().getString(R.string.open) : this.mContext.getResources().getString(R.string.close)));
        arrayList.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]) ? this.mContext.getResources().getString(R.string.open) : this.mContext.getResources().getString(R.string.close)));
        this.mList1.clear();
        this.mList1.addAll(arrayList);
        GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
        GeneralOriginalCarDeviceData.runningState = getCdStatus(this.mCanBusInfoInt[5]);
        GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralOriginalCarDeviceData.startTime = startEndTimeMethod((iArr3[13] * 256) + iArr3[14]);
        int[] iArr4 = this.mCanBusInfoInt;
        GeneralOriginalCarDeviceData.endTime = startEndTimeMethod((iArr4[11] * 256) + iArr4[12]);
        int[] iArr5 = this.mCanBusInfoInt;
        int i = iArr5[11];
        int i2 = iArr5[12];
        if ((i * 256) + i2 == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
        } else {
            GeneralOriginalCarDeviceData.progress = (((iArr5[13] * 256) + iArr5[14]) * 100) / ((i * 256) + i2);
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setCdInfo() {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 1) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, getCdTrackInfo()));
        }
        if (this.mCanBusInfoInt[2] == 2) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, getCdTrackInfo()));
        }
        this.mList2.clear();
        this.mList2.addAll(arrayList);
        GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
        updateOriginalCarDeviceActivity(null);
    }

    private void setDriveData() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, sb.append((iArr[8] * 256 * 256) + (iArr[9] * 256) + iArr[10]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private static <T> List<T> mergeLists(List<T>... listArr) {
        List<T> list;
        try {
            list = (List) listArr[0].getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (List<T> list2 : listArr) {
            list.addAll(list2);
        }
        return list;
    }

    private void cleanSongList() {
        if (GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
        }
    }

    private String getCdTrackInfo() {
        byte b;
        ArrayList arrayList = new ArrayList();
        int i = 4;
        while (true) {
            byte[] bArr = this.mCanBusInfoByte;
            if (i >= bArr.length || ((b = bArr[i - 1]) == 0 && bArr[i] == 0)) {
                break;
            }
            arrayList.add(Byte.valueOf(b));
            arrayList.add(Byte.valueOf(this.mCanBusInfoByte[i]));
            i += 2;
        }
        int size = arrayList.size();
        byte[] bArr2 = new byte[size];
        for (int i2 = 0; i2 < size; i2++) {
            bArr2[i2] = ((Byte) arrayList.get(i2)).byteValue();
        }
        try {
            return new String(bArr2, "unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getCdStatus(int i) {
        Context context = this.mContext;
        return context.getString(CommUtil.getStrIdByResId(context, "_123_divice_status_" + i));
    }

    private String getPreStorage(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.ford_original_yct);
        }
        return " P" + i;
    }

    private void setOriginalRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        changeOriginalDevicePage(arrayList, new String[]{"band", "up", "left", "right", "down", OriginalBtnAction.RADIO_SCAN}, false, true);
    }

    private void setOriginalCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "total_file", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_song_list", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "song_name", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_artist", "singer", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "ford_original_rdm", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "ford_original_rep", ""));
        String[] strArr = {"up", OriginalBtnAction.RANDOM, OriginalBtnAction.PLAY, "cycle", "down"};
        int i = this.mCanBusInfoInt[5];
        if (i == 1) {
            strArr = new String[]{"up", OriginalBtnAction.RANDOM, OriginalBtnAction.PAUSE, "cycle", "down"};
        } else if (i == 5) {
            strArr = new String[]{"up", OriginalBtnAction.RANDOM, OriginalBtnAction.PLAY, "cycle", "down"};
        }
        changeOriginalDevicePage(arrayList, strArr, true, false);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, boolean z, boolean z2) {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        if (originalCarDevicePageUiSet == null) {
            return;
        }
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowBottomBtnAction(strArr);
        originalCarDevicePageUiSet.setHavePlayTimeSeekBar(z);
        originalCarDevicePageUiSet.setHaveSongList(z2);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
        enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
    }

    private void cleanDevice() {
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.am_st = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.scan = false;
        GeneralOriginalCarDeviceData.disc_scan = false;
        GeneralOriginalCarDeviceData.rpt = false;
        GeneralOriginalCarDeviceData.rpt_disc = false;
        GeneralOriginalCarDeviceData.rdm_off = false;
        GeneralOriginalCarDeviceData.rdm_disc = false;
        GeneralOriginalCarDeviceData.startTime = "     ";
        GeneralOriginalCarDeviceData.endTime = "     ";
        GeneralOriginalCarDeviceData.progress = 0;
        updateOriginalCarDeviceActivity(null);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (254 == i) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return (i * 0.5f) + getTempUnitC(this.mContext);
        }
        return ((int) (i * 0.5f)) + getTempUnitF(this.mContext);
    }

    private String resolveRearTemp(int i) {
        return i == 0 ? "Close" : 1 == i ? "Lo" : 9 == i ? "Hi" : 5 == i ? "Mid" : "" + i;
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen) ? false : true;
    }

    private boolean isOnlyRearAirDataChange() {
        return (SharePreUtil.getIntValue(this.mContext, "is_rear_air_temp_change_ford", 0) == rearTemp && SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_lv_change", 0) == GeneralAirData.rear_wind_level && SharePreUtil.getBoolValue(this.mContext, "is_rear_air_power", false) == GeneralAirData.rear_power && SharePreUtil.getBoolValue(this.mContext, "is_rear_lock", false) == GeneralAirData.rear_lock) ? false : true;
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mDoorInfoCopy)) {
            return true;
        }
        this.mDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean isRadioMsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mOriginalRadioCopy)) {
            return true;
        }
        this.mOriginalRadioCopy = Arrays.copyOf(bArr, bArr.length);
        if (!this.isRadioFirst) {
            return false;
        }
        this.isRadioFirst = false;
        return true;
    }

    private boolean isCdMsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mOriginalCdCopy)) {
            return true;
        }
        this.mOriginalCdCopy = Arrays.copyOf(bArr, bArr.length);
        if (!this.isCdFirst) {
            return false;
        }
        this.isCdFirst = false;
        return true;
    }

    private boolean isRadioInfoRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mOriginalRadioInfoCopy)) {
            return true;
        }
        this.mOriginalRadioInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isCdInfoRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mOriginalCdInfoCopy)) {
            return true;
        }
        this.mOriginalCdInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isParkingMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mParkingInfoCopy)) {
            return true;
        }
        this.mParkingInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isParkingFirst) {
            return false;
        }
        isParkingFirst = false;
        return true;
    }

    private boolean isWarningMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusWarnInfoCopy)) {
            return true;
        }
        this.mCanBusWarnInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isWarnFirst) {
            return false;
        }
        isWarnFirst = false;
        return true;
    }

    void updateOrignalSetting() {
        this.value = SharePreUtil.getIntValue(this.mContext, "_96_park_assess_item", 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(this.value)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return true;
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realKeyClick1(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void realKeyClick2(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private long bytesToLong(byte[] bArr) {
        long jPow = 0;
        for (int i = 0; i < bArr.length; i++) {
            jPow = (long) (jPow + ((bArr[i] & 255) * Math.pow(256.0d, i)));
        }
        return jPow;
    }

    private static ArrayList<Boolean> getArrayEachBitsBool(int[] iArr, int i) {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        arrayList.clear();
        int i2 = i + 2;
        int length = iArr.length - i2;
        int[] iArr2 = new int[length];
        for (int i3 = 0; i3 < iArr.length - i2; i3++) {
            iArr2[i3] = iArr[i3 + i2];
        }
        for (int i4 = 0; i4 < length; i4++) {
            for (int i5 = 0; i5 < 8; i5++) {
                arrayList.add(getBooleanFromByteWithBit(iArr2[i4], i5, 1));
            }
        }
        return arrayList;
    }

    private static Boolean getBooleanFromByteWithBit(int i, int i2, int i3) {
        if ((((i & 255) >> i2) & ((1 << i3) - 1)) == 1) {
            return true;
        }
        return false;
    }

    private void refreshFordParkAssistFlowView(int[] iArr) {
        final int i = iArr[3];
        if (i == 0 || this.ford_park_assist_info == i) {
            return;
        }
        this.ford_park_assist_info = i;
        if (this.mParkAssistFloatView == null) {
            this.mParkAssistFloatView = new ParkAssistFloatView(this.mContext);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._96.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Bundle bundle = new Bundle();
                bundle.putByte(ParkAssistFloatView.REFRESH_UI_BUNDLE_KEY, (byte) i);
                MsgMgr.this.mParkAssistFloatView.refreshUi(bundle);
            }
        });
    }

    private void playPresetChann(int i) throws RemoteException {
        FutureUtil.instance.playPresetFreq(i);
        Intent intent = new Intent();
        intent.setAction("com.hzbhd.intent.action.fm");
        this.mContext.startActivity(intent);
    }

    private String getRadioStatus(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.ford_original_status1);
        }
        if (i != 1) {
            return i != 2 ? "" : this.mContext.getResources().getString(R.string.ford_original_status3);
        }
        return this.mContext.getResources().getString(R.string.ford_original_status2);
    }

    void updateOriginalCarDevice(Bundle bundle) {
        updateOriginalCarDeviceActivity(bundle);
    }

    private void initId3() {
        this.mId3s = new ID3[]{new ID3(146, "Unicode", 34), new ID3(147, "Unicode", 34), new ID3(150, "Unicode", 34)};
    }

    class ID3 {
        private String charsetName;
        private int head;
        private int length;
        private String id3 = new String();
        private String record = new String();

        public ID3(int i, String str, int i2) {
            this.head = i;
            this.charsetName = str;
            this.length = i2;
        }

        public int getHead() {
            return this.head;
        }

        public String getId3() {
            return this.id3;
        }

        public void setId3(String str) {
            this.id3 = str;
        }

        public boolean isId3Change() {
            if (this.record.equals(this.id3)) {
                return false;
            }
            this.record = this.id3;
            return true;
        }

        public String getCharsetName() {
            return this.charsetName;
        }

        public void setCharsetName(String str) {
            this.charsetName = str;
        }

        public int getLength() {
            return this.length;
        }

        public void setLength(int i) {
            this.length = i;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._96.MsgMgr$4] */
    private void reportID3Info(final ID3[] id3Arr, final boolean z) {
        new Thread() { // from class: com.hzbhd.canbus.car._96.MsgMgr.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    ID3[] id3Arr2 = id3Arr;
                    if (id3Arr2.length > 0) {
                        if (id3Arr2[0].isId3Change()) {
                            if (z) {
                                sleep(900L);
                            }
                            for (ID3 id3 : id3Arr) {
                                sleep(100L);
                                MsgMgr.this.reportID3InfoFinal((byte) id3.getHead(), id3.getId3(), id3.getCharsetName(), id3.getLength());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str, String str2, int i) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, b}, DataHandleUtils.exceptBOMHead(str.getBytes(str2))), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendId3() {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -110}, 34));
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -109}, 34));
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -106}, 34));
    }

    private void sendSourceMsg2(String str, int i) {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte) i}, str.getBytes()), 34));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, (byte) Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -110}, 34));
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -109}, 34));
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -106}, 34));
        }
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
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        String str5;
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
        str4 = " ";
        if (isBandAm(str)) {
            str5 = str2.length() == 4 ? "     " : "      ";
            i3 = 0;
        } else {
            str4 = str2.length() == 5 ? "  " : " ";
            str5 = "    ";
            i3 = 1;
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, allBandTypeData, (byte) intByteWithBit}, (new DecimalFormat("00").format(i) + str4 + str2.substring(0, str2.length() - i3) + str5).getBytes()));
        sendId3();
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, (byte) (i6 == 1 ? 7 : 6), (byte) Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sendId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sendId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sendId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 8, (byte) Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sendId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte b10 = b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        int intByteWithBit = Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true);
        int i4 = i3 <= 998 ? i3 : 998;
        int i5 = i2;
        if (i5 > 999) {
            i5 = 999;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), Util.byteMerger(new byte[]{22, -111, b10, (byte) intByteWithBit}, (new DecimalFormat("000").format(i4) + "      " + new DecimalFormat("000").format(i5)).getBytes()));
        this.mId3s[0].setId3(str4);
        this.mId3s[1].setId3(str5);
        this.mId3s[2].setId3(str6);
        reportID3Info(this.mId3s, false);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mId3s[0].setId3("");
        this.mId3s[1].setId3("");
        this.mId3s[2].setId3("");
        reportID3Info(this.mId3s, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), Util.byteMerger(new byte[]{22, -111, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) Util.setIntByteWithBit(Util.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true)}, (new DecimalFormat("000").format(i) + "      " + new DecimalFormat("000").format(i2)).getBytes()));
        sendId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = (byte) (z3 ? 0 : 1);
        bArr[3] = (byte) i5;
        bArr[4] = (byte) i6;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = (byte) (z ? 1 : 0);
        bArr[8] = (byte) i2;
        bArr[9] = (byte) i3;
        bArr[10] = (byte) i4;
        bArr[11] = (byte) i9;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 0, FutureUtil.instance.isDiscExist()), 1, true)}, ("    " + new DecimalFormat("00").format(i / 60) + new DecimalFormat("00").format(i % 60) + "    ").getBytes()));
        sendId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -51, 4});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -51, 2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -51, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -51, 5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
    }

    private class TimerHelper {
        private Timer mTimer;
        private TimerTask mTimerTask;

        private TimerHelper() {
        }

        public void startTimer(TimerTask timerTask, long j, long j2) {
            Log.i("TimerUtil", "startTimer: " + this);
            if (timerTask == null) {
                return;
            }
            this.mTimerTask = timerTask;
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            this.mTimer.schedule(this.mTimerTask, j, j2);
        }

        public void stopTimer() {
            Log.i("TimerUtil", "stopTimer: " + this);
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
    }

    private String getSyncInfo(byte[] bArr) {
        int length = bArr.length;
        int i = 5;
        while (true) {
            if (i >= bArr.length) {
                break;
            }
            int i2 = i - 1;
            if (bArr[i2] == 0 && bArr[i] == 0) {
                length = i2;
                break;
            }
            i += 2;
        }
        try {
            return new String(Arrays.copyOfRange(bArr, 4, length), "unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    void updateSync(Bundle bundle) {
        updateSyncActivity(bundle);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public Activity getCurrentActivity() {
        return getActivity();
    }
}
