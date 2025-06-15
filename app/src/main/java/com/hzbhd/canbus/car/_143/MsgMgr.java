package com.hzbhd.canbus.car._143;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_143_ENABLE_INFO = "share_143_enable_info";
    static final int VEHICLE_TYPE_13_C5 = 5;
    static final int VEHICLE_TYPE_14_408 = 16;
    static final int VEHICLE_TYPE_15_508 = 21;
    static final int VEHICLE_TYPE_17_4008 = 20;
    static final int VEHICLE_TYPE_19_301 = 23;
    static final int VEHICLE_TYPE_19_RIFTER_HIGH = 24;
    static final int VEHICLE_TYPE_19_RIFTER_LOW = 25;
    static final int VEHICLE_TYPE_2008 = 14;
    static final int VEHICLE_TYPE_3008_CUT = 11;
    static final int VEHICLE_TYPE_3008_SCREEN = 17;
    static final int VEHICLE_TYPE_301_Elysee = 18;
    static final int VEHICLE_TYPE_307 = 6;
    static final int VEHICLE_TYPE_308 = 7;
    static final int VEHICLE_TYPE_408 = 8;
    static final int VEHICLE_TYPE_508_HIGH = 10;
    static final int VEHICLE_TYPE_508_LOW = 9;
    static final int VEHICLE_TYPE_AIRCROSS = 26;
    static final int VEHICLE_TYPE_C3_XR = 19;
    static final int VEHICLE_TYPE_C4 = 2;
    static final int VEHICLE_TYPE_C4L = 3;
    static final int VEHICLE_TYPE_C5 = 4;
    static final int VEHICLE_TYPE_DS4_HIGH = 15;
    static final int VEHICLE_TYPE_DS4_LOW = 65;
    static final int VEHICLE_TYPE_DS5LS_DS6_HIGH = 13;
    static final int VEHICLE_TYPE_DS5LS_DS6_LOW = 53;
    static final int VEHICLE_TYPE_DS5_HING = 12;
    static final int VEHICLE_TYPE_DS5_LOW = 52;
    static final int VEHICLE_TYPE_QUATRA = 1;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static boolean isWarnFirst = true;
    static int language;
    private static int outDoorTemp;
    private static int swc_data3;
    private static int up_dn_btn_data;
    private static int voice_btn_data;
    private byte[] bytes;
    TextView content;
    String contentMsg;
    AlertDialog dialog;
    Button i_know;
    private boolean isDGear;
    private byte[] m0x83InfoCopy;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusBtnPanelInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusRadarInfoCopy;
    private byte[] mCanBusSwcDataCopy;
    private byte[] mCanBusSwcInfoCopy;
    private byte[] mCanBusWarnInfoCopy;
    private Context mContext;
    private int mDifferentId;
    private int mEachId;
    private byte[] mOriginalRadioCopy;
    private byte[] mOriginalRadioInfoCopy;
    private SparseArray<SparseIntArray> mPanelKeyArray;
    private Timer mTimer;
    private UiMgr mUiMgr;
    private int nextItem;
    View view;
    private final String SWC_DATA_3 = "swc_data_3";
    private final String SWC_BTN_DATA_3 = "swc_btn_data_3";
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
    private final String IS_SEAT_MASTER_DRIVER_BELT_TIE = "is_seat_master_driver_belt_tie";
    private final String IS_SEAT_CO_PILOT_BELT_TIE = "is_seat_co_pilot_belt_tie";
    private final String IS_SEAT_R_L_BELT_TIE = "is_seat_r_l_belt_tie";
    private final String IS_SEAT_R_M_BELT_TIE = "is_seat_r_m_belt_tie";
    private final String IS_SEAT_R_R_BELT_TIE = "is_seat_r_r_belt_tie";
    private final int MSG_CLOSE_WARNING_ACTIVITY = 1000;
    private byte[] m0x81Command = new byte[12];
    private byte[] m0x82Command = new byte[12];
    private byte[] m0x85Command = new byte[4];
    private byte[] m0xC1Command = new byte[4];
    private byte[] m0x72Enable1 = new byte[4];
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String mFuelUnit = "L/100KM";
    private String mTemperatureUnit = "℃";
    private String mId3SongTitle = "";
    private boolean isRadioFirst = true;
    private int[] setting_0x71_canBusInfo_Enable = {255, 255, 255, 255, 255, 255, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] setting_0x76_canBusInfo_Enable = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private byte[] setting_0x72_canBusInfo_Enable = {22, 114, -1, -1, 0, 0, 0, 0, 0, 0};
    private int[] setting_0x79_canBusInfo_Enable = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    boolean pinBlackTag = false;
    private int first0x97 = 1;
    int backLight = 0;
    int nowPcState = 1;
    int nowLight = 3;
    int nowLightMode = 0;
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._143.MsgMgr.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("selectPos1", 0);
            if ("REVERSING_SOUND".equals(intent.getAction())) {
                MsgMgr.this.settingLeft0Right12Data(intExtra);
            }
        }
    };
    private int currentItem = 0;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap = new HashMap<>();

    private String getCdState(int i) {
        switch (i) {
            case 0:
                return "Reading TOC";
            case 1:
                return "Pause";
            case 2:
                return "Play";
            case 3:
                return "Fast";
            case 4:
                return "User search";
            case 5:
                return "Internal search";
            case 6:
                return "Stop";
            case 7:
                return "Rom read";
            case 8:
                return "Rom search";
            case 9:
                return "Retrieving";
            case 10:
                return "Disc changing（user）";
            case 11:
                return "Disc changing(internal)";
            case 12:
                return "Eject";
            case 13:
                return "Load";
            case 14:
                return "Disc Error";
            default:
                return "Invalid";
        }
    }

    private String getCdType(boolean z) {
        return z ? "     ♬ Type: Rom Disc" : "     ♬ Type: CD Disc";
    }

    private String getHaveNoState(boolean z) {
        return z ? " ●Have Disc  " : " ○No Disc      ";
    }

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private String getModel(int i) {
        switch (i) {
            case 1:
                return "Radio";
            case 2:
                return "CD";
            case 3:
                return "AUX";
            case 4:
                return "NAVI";
            case 5:
                return "CDC";
            case 6:
                return "USB";
            case 7:
                return "TEL";
            case 8:
                return "TV";
            case 9:
                return "IPOD";
            default:
                return "NO MEDIA";
        }
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private String getNowCd(int i, int i2) {
        return (i2 == 15 && i == 1) ? " ☑Select            " : i == i2 ? "     ☑Select            " : "     □Unchecked  ";
    }

    private String getState(int i) {
        return i == 1 ? "FM1" : i == 2 ? "FM2" : i == 3 ? "FM3" : i == 4 ? "AM1" : i == 5 ? "AM2" : i == 6 ? "MW" : i == 7 ? "LW" : "";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mEachId = getCurrentEachCanId();
        this.mDifferentId = getCurrentCanDifferentId();
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mUiMgr = getUiMgr(context);
        initSettingsItem(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("REVERSING_SOUND");
        context.registerReceiver(this.receiver, intentFilter);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 0});
        initAmplifierData();
        getUiMgr(context).D0B7_0xA1 = 1;
        getUiMgr(context).send0xA1HostState();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            setPanelButton(byteArrayToIntArray);
            return;
        }
        if (i == 34) {
            if (isBtnPanelMsgReturn(bArr)) {
                return;
            }
            setOriginalPanel();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
            return;
        }
        if (i == 118) {
            if (equal(Integer.valueOf(this.mDifferentId), 1, 2, 3, 11, 13, 53, 14, 15, 65, 16, 18, 19, 20, 24, 25, 26)) {
                set0x76VehicleSet();
            }
            this.setting_0x76_canBusInfo_Enable = this.mCanBusInfoInt;
            return;
        }
        if (i == 121) {
            if (equal(Integer.valueOf(this.mDifferentId), 1, 2, 3, 11, 16, 19, 20, 21, 13, 53, 24, 25, 26)) {
                set0x79VehicleSet2();
                return;
            }
            return;
        }
        if (i == 133) {
            if (equal(Integer.valueOf(this.mDifferentId), 16)) {
                set0x85SportDate();
                return;
            }
            return;
        }
        if (i == 151) {
            set0x97CDInfo();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 65) {
            setFrontRearRadar();
            showRadarOrNot(bArr);
            return;
        }
        if (i == 66) {
            if (equal(Integer.valueOf(this.mDifferentId), 4, 5, 12, 52, 13, 53, 23) || isWarningMsgReturn(bArr)) {
                return;
            }
            setWarningInfo(context);
            return;
        }
        if (i == 113) {
            this.setting_0x71_canBusInfo_Enable = byteArrayToIntArray;
            this.mCanBusInfoInt = this.setting_0x76_canBusInfo_Enable;
            set0x76VehicleSet();
            return;
        }
        if (i == 114) {
            set0x72CarSetEnable(context);
            return;
        }
        if (i == 147) {
            set0x93CDInfo();
            return;
        }
        if (i == 148) {
            setLanguage();
            return;
        }
        if (i == 193) {
            set0xC1Unit(context);
            return;
        }
        if (i != 194) {
            switch (i) {
                case 17:
                    keyControl0x11();
                    setBack();
                    setEpsTrackInfo0x11();
                    setBlackLight0x11();
                    break;
                case 18:
                    if (!isDoorMsgReturn(bArr)) {
                        setCarDoorInfo();
                        setCarStatusInfo();
                        break;
                    }
                    break;
                case 19:
                    setDrivingComputerInfo0();
                    break;
                case 20:
                    setDrivingComputerInfo1();
                    break;
                case 21:
                    setDrivingComputerInfo2();
                    break;
                default:
                    switch (i) {
                        case 129:
                            if (equal(Integer.valueOf(this.mDifferentId), 3, 14, 16, 26)) {
                                set0x81RememberedSpeedData();
                                break;
                            }
                            break;
                        case 130:
                            if (equal(Integer.valueOf(this.mDifferentId), 16, 26)) {
                                set0x82CruiseSpeedData();
                                break;
                            }
                            break;
                        case 131:
                            if (equal(Integer.valueOf(this.mDifferentId), 16) && !is0x83MsgRepeat(bArr)) {
                                set0x83Setting();
                                break;
                            }
                            break;
                    }
            }
            return;
        }
        set0xC2TimeInfo();
    }

    private void set0x97CDInfo() {
        if (this.first0x97 == 1) {
            this.first0x97 = 2;
            if (getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd") != -1) {
                Context context = this.mContext;
                startDrivingDataActivity(context, getUiMgr(context).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"));
            }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_time"), this.mCanBusInfoInt[7] + ":" + this.mCanBusInfoInt[8]));
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_number");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        StringBuilder sbAppend = sb.append(getMsbLsbResult(iArr[6], iArr[5])).append("/");
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sbAppend.append(getMsbLsbResult(iArr2[12], iArr2[11])).toString()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_state"), getCdState(this.mCanBusInfoInt[10])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_model"), getCdPlayModel(this.mCanBusInfoInt[9])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc1"), getHaveNoState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) + getNowCd(1, this.mCanBusInfoInt[2]) + getCdType(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc2"), getHaveNoState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) + getNowCd(2, this.mCanBusInfoInt[2]) + getCdType(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc3"), getHaveNoState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) + getNowCd(3, this.mCanBusInfoInt[2]) + getCdType(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc4"), getHaveNoState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) + getNowCd(4, this.mCanBusInfoInt[2]) + getCdType(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc5"), getHaveNoState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) + getNowCd(5, this.mCanBusInfoInt[2]) + getCdType(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x97_cd"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x97_cd_disc6"), getHaveNoState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) + getNowCd(6, this.mCanBusInfoInt[2]) + getCdType(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_143_add_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_143_add_function", "_143_add_function1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_143_add_function"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_143_add_function", "_143_add_function2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1))));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    private String getCdPlayModel(int i) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (DataHandleUtils.getBoolBit7(i)) {
            str = "Scan:ON    \n";
        } else {
            str = "Scan:OFF    \n";
        }
        if (DataHandleUtils.getBoolBit6(i)) {
            str2 = str + "Disc scan:ON    \n";
        } else {
            str2 = str + "Disc scan:OFF    \n";
        }
        if (DataHandleUtils.getBoolBit5(i)) {
            str3 = str2 + "Repeat:ON    \n";
        } else {
            str3 = str2 + "Repeat:OFF    \n";
        }
        if (DataHandleUtils.getBoolBit4(i)) {
            str4 = str3 + "Disc repeat:ON    \n";
        } else {
            str4 = str3 + "Disc repeat:OFF    \n";
        }
        if (DataHandleUtils.getBoolBit3(i)) {
            str5 = str4 + "Random:ON    \n";
        } else {
            str5 = str4 + "Random:OFF    \n";
        }
        if (DataHandleUtils.getBoolBit2(i)) {
            return str5 + "Disc random:ON    \n";
        }
        return str5 + "Disc random:OFF    \n";
    }

    private void set0x93CDInfo() {
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            Context context = this.mContext;
            startDrivingDataActivity(context, getUiMgr(context).getDrivingPageIndexes(this.mContext, "_143_0x93_state"));
        }
        ArrayList arrayList = new ArrayList();
        if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state1"), "POWER OFF"));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state2"), "POWER OFF"));
        } else {
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state1"), getModel(this.mCanBusInfoInt[3])));
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
                arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state2"), this.mCanBusInfoInt[4] + "LEVER"));
            } else {
                arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_143_0x93_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_0x93_state2"), "Don't show"));
            }
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setBack() {
        int i = this.backLight;
        int i2 = this.mCanBusInfoInt[7];
        if (i != i2) {
            this.backLight = i2;
            setBacklightLevel((i2 / 3) + 1);
        }
    }

    private void set0xC2TimeInfo() {
        String str = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]) ? "TimeFormat:24H  " : "TimeFormat:12H  ";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_143_0xc2_time"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_143_0xc2_time", "_143_0xc2_time1"), str + "20" + this.mCanBusInfoInt[2] + "/" + this.mCanBusInfoInt[3] + "/" + this.mCanBusInfoInt[4] + "   " + this.mCanBusInfoInt[5] + ":" + this.mCanBusInfoInt[6]).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setBlackLight0x11() {
        if (this.pinBlackTag != DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10])) {
            if (!this.pinBlackTag) {
                setBacklightLevel(1);
            } else {
                setBacklightLevel(5);
            }
            this.pinBlackTag = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        }
    }

    private void setEpsTrackInfo0x11() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void showRadarOrNot(byte[] bArr) {
        boolean z;
        if (isRadarMsgReturn(bArr) || !(z = this.isDGear)) {
            return;
        }
        forceReverse(this.mContext, z);
        startTask();
    }

    private void startTask() {
        Timer timer = this.mTimer;
        if (timer == null) {
            Timer timer2 = new Timer();
            this.mTimer = timer2;
            timer2.schedule(new TimerTask() { // from class: com.hzbhd.canbus.car._143.MsgMgr.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.forceReverse(msgMgr.mContext, false);
                }
            }, 5000L);
        } else {
            timer.cancel();
            this.mTimer = null;
            startTask();
        }
    }

    private void setOriginalPanel() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            if (iArr[3] > voice_btn_data) {
                realKeyClick4(7);
                voice_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < voice_btn_data) {
                realKeyClick4(8);
                voice_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (i == 2) {
            if (iArr[3] > up_dn_btn_data) {
                realKeyClick4(46);
                up_dn_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
                realKeyClick4(45);
                up_dn_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (i == 3) {
            if (iArr[3] > up_dn_btn_data) {
                realKeyClick4(20);
                up_dn_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
                realKeyClick4(21);
                up_dn_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (i == 4) {
            if (iArr[3] > up_dn_btn_data) {
                realKeyClick4(48);
                up_dn_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
                realKeyClick4(47);
                up_dn_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (i == 5) {
            if (iArr[3] > up_dn_btn_data) {
                realKeyClick4(33);
                up_dn_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
                realKeyClick4(34);
                up_dn_btn_data = this.mCanBusInfoInt[3];
            }
        }
    }

    private void keyControl0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick1(0);
            return;
        }
        if (i == 1) {
            realKeyClick1(7);
            return;
        }
        if (i == 2) {
            realKeyClick1(8);
            return;
        }
        if (i == 3) {
            realKeyClick1(3);
            return;
        }
        if (i == 4) {
            realKeyClick1(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            realKeyClick1(14);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 8:
                    realKeyClick1(46);
                    break;
                case 9:
                    realKeyClick1(45);
                    break;
                case 10:
                    realKeyClick1(52);
                    break;
                case 11:
                    realKeyClick1(2);
                    break;
                default:
                    switch (i) {
                        case 13:
                            realKeyClick1(47);
                            break;
                        case 14:
                            realKeyClick1(48);
                            break;
                        case 15:
                            realKeyClick1(49);
                            break;
                        case 16:
                            realKeyClick1(50);
                            break;
                        case 17:
                            realKeyClick1(21);
                            break;
                        case 18:
                            realKeyClick1(20);
                            break;
                        case 19:
                            realKeyClick1(HotKeyConstant.K_CAN_CONFIG);
                            break;
                        case 20:
                            realKeyClick1(39);
                            break;
                        case 21:
                            Intent intent = new Intent(this.mContext, (Class<?>) WarningActivity.class);
                            intent.addFlags(268435456);
                            this.mContext.startActivity(intent);
                            break;
                        case 22:
                            startDrivingPage();
                            break;
                    }
            }
            return;
        }
        realKeyClick1(68);
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick5(context, i, iArr[4], iArr[5]);
    }

    private void panelButtonClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.mono = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1;
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        if (getCurrentCanDifferentId() == 27) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            if (intFromByteWithBit == 3) {
                GeneralAirData.front_left_blow_foot = true;
            } else if (intFromByteWithBit == 5) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
            } else if (intFromByteWithBit == 6) {
                GeneralAirData.front_left_blow_head = true;
            } else {
                switch (intFromByteWithBit) {
                    case 11:
                        GeneralAirData.front_left_blow_window = true;
                        break;
                    case 12:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        break;
                    case 13:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        break;
                    case 14:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        break;
                }
            }
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
            if (intFromByteWithBit2 == 3) {
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit2 == 5) {
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_head = true;
            } else if (intFromByteWithBit2 == 6) {
                GeneralAirData.front_right_blow_head = true;
            } else {
                switch (intFromByteWithBit2) {
                    case 11:
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 12:
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 13:
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 14:
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_right_blow_head = true;
                        break;
                }
            }
        } else {
            int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            if (intFromByteWithBit3 == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit3 == 5) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else if (intFromByteWithBit3 == 6) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else {
                switch (intFromByteWithBit3) {
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
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
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
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        outDoorTemp = this.mCanBusInfoInt[13];
        if (isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setFrontRearRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 8;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2] + 1, iArr[3] + 1, iArr[4] + 1, iArr[5] + 1);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr2[6] + 1, iArr2[7] + 1, iArr2[8] + 1, iArr2[9] + 1);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setWarningInfo(final Context context) {
        String hexString = Integer.toHexString(this.mCanBusInfoInt[2]);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        String hexString2 = Integer.toHexString(this.mCanBusInfoInt[3]);
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        String str = "psa_hiword_" + hexString + hexString2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WarningEntity(CommUtil.getStrByResId(this.mContext, str)));
        if (arrayList.size() > 0 && !str.equals(context.getString(R.string.psa_hiword_000b)) && CommUtil.getStrByResId(this.mContext, str) != null) {
            startWarningActivity(this.mContext);
        }
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._143.MsgMgr.2
            @Override // java.lang.Runnable
            public void run() {
                if (SystemUtil.isForeground(context, WarningActivity.class.getName())) {
                    MsgMgr.this.finishActivity();
                }
            }
        }, 5000L);
    }

    private void set0x76VehicleSet() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_143_0x76_d0_b7").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))).setEnable(DataHandleUtils.getBoolBit7(this.setting_0x71_canBusInfo_Enable[2])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d0_b6").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])))).setEnable(DataHandleUtils.getBoolBit6(this.setting_0x71_canBusInfo_Enable[2])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d0_b45").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))).setEnable(DataHandleUtils.getBoolBit5(this.setting_0x71_canBusInfo_Enable[2])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d0_b3").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])))).setEnable(DataHandleUtils.getBoolBit3(this.setting_0x71_canBusInfo_Enable[2])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d0_b02").setValue(Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b7").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])))).setEnable(DataHandleUtils.getBoolBit7(this.setting_0x71_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b6").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])))).setEnable(DataHandleUtils.getBoolBit6(this.setting_0x71_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b5").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])))).setEnable(DataHandleUtils.getBoolBit5(this.setting_0x71_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b4").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])))).setEnable(DataHandleUtils.getBoolBit4(this.setting_0x71_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b3").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])))).setEnable(DataHandleUtils.getBoolBit3(this.setting_0x71_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b2").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])))).setEnable(DataHandleUtils.getBoolBit2(this.setting_0x71_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_0x76_d1_b01").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))).setEnable(DataHandleUtils.getBoolBit1(this.setting_0x71_canBusInfo_Enable[3])));
        Settings.System.putInt(this.mContext.getContentResolver(), "left0right3", getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        Settings.System.putInt(this.mContext.getContentResolver(), "left0right4", intFromByteWithBit);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x72CarSetEnable(Context context) {
        this.setting_0x72_canBusInfo_Enable = this.mCanBusInfoByte;
        this.mCanBusInfoInt = this.setting_0x79_canBusInfo_Enable;
        set0x79VehicleSet2();
    }

    private void set0x79VehicleSet2() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_143_setting_1").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 7) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_2").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 6) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 6) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_4").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 5) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_5").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 4) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 4) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_6").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 3) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 3) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_7").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 2) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 2) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_8").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 1) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 1) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_9").setValue(Integer.valueOf((this.mCanBusInfoInt[2] >> 0) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 0) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_10").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_11").setValue(Integer.valueOf((this.mCanBusInfoInt[3] >> 5) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_12").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 6) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_13").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 4) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_14").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 4) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_16").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 4)).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 3) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_20").setValue(Integer.valueOf((this.mCanBusInfoInt[4] >> 1) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 2) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_21").setValue(Integer.valueOf((this.mCanBusInfoInt[4] >> 0) & 1)).setEnable(((this.setting_0x72_canBusInfo_Enable[3] >> 1) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_19").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))).setEnable(((this.setting_0x72_canBusInfo_Enable[2] >> 0) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_143_setting_22").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(DataHandleUtils.getBoolBit0(this.setting_0x72_canBusInfo_Enable[3])));
        arrayList.add(getSettingUpdateEntity("_143_setting_23").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit7(this.setting_0x72_canBusInfo_Enable[4])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        this.setting_0x79_canBusInfo_Enable = this.mCanBusInfoInt;
    }

    private void setLanguage() {
        int i = this.mCanBusInfoInt[2];
        int i2 = 16;
        if (i > 16 || i == 0) {
            switch (i) {
                case 18:
                    break;
                case 19:
                case 21:
                case 24:
                case 27:
                default:
                    i2 = 0;
                    break;
                case 20:
                    i2 = 17;
                    break;
                case 22:
                    i2 = 18;
                    break;
                case 23:
                    i2 = 19;
                    break;
                case 25:
                    i2 = 21;
                    break;
                case 26:
                    i2 = 20;
                    break;
                case 28:
                    i2 = 22;
                    break;
                case 29:
                    i2 = 23;
                    break;
                case 30:
                    i2 = 24;
                    break;
                case 31:
                    i2 = 25;
                    break;
                case 32:
                    i2 = 26;
                    break;
                case 33:
                    i2 = 27;
                    break;
            }
        } else {
            i2 = i - 1;
        }
        language = i;
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_143_0x94_language").setValue(Integer.valueOf(i2)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0xC1Unit(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        this.m0xC1Command = Arrays.copyOf(bArr, bArr.length);
        int i = this.mCanBusInfoInt[3] >> 5;
        if (i == 0) {
            this.mTemperatureUnit = getTempUnitF(context);
        } else if (i == 1) {
            this.mTemperatureUnit = getTempUnitC(context);
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2);
        if (intFromByteWithBit == 0) {
            this.mFuelUnit = "L/100km";
        } else if (intFromByteWithBit == 1) {
            this.mFuelUnit = "km/l";
        } else if (intFromByteWithBit == 2) {
            this.mFuelUnit = "mpg";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("vm_golf7_vehicle_setup_units_temperature").setValue(Integer.valueOf((this.mCanBusInfoInt[3] >> 5) & 1)).setEnable(((this.mCanBusInfoInt[2] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("vm_golf7_vehicle_setup_units_consumption").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(((this.mCanBusInfoInt[2] >> 3) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarDoorInfo() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowMasterDriverSeatBelt = true;
        GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        if (equal(Integer.valueOf(this.mDifferentId), 12, 52)) {
            GeneralDoorData.isShowCoPilotSeatBelt = true;
            GeneralDoorData.isShowRLSeatBelt = true;
            GeneralDoorData.isShowRMSeatBelt = true;
            GeneralDoorData.isShowRRSeatBelt = true;
            GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralDoorData.isSeatRLBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralDoorData.isSeatRMBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralDoorData.isSeatRRBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        }
        if (isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_seat_master_driver_belt_tie", GeneralDoorData.isSeatMasterDriverBeltTie);
            SharePreUtil.setBoolValue(this.mContext, "is_seat_co_pilot_belt_tie", GeneralDoorData.isSeatCoPilotBeltTie);
            SharePreUtil.setBoolValue(this.mContext, "is_seat_r_l_belt_tie", GeneralDoorData.isSeatRLBeltTie);
            SharePreUtil.setBoolValue(this.mContext, "is_seat_r_m_belt_tie", GeneralDoorData.isSeatRMBeltTie);
            SharePreUtil.setBoolValue(this.mContext, "is_seat_r_r_belt_tie", GeneralDoorData.isSeatRRBeltTie);
            updateDoorView(this.mContext);
        }
    }

    private void setCarStatusInfo() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[3];
        if (i != 0) {
            string = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "D" : "R" : "N" : "P";
        } else {
            string = this.mContext.getResources().getString(R.string.invalid);
        }
        if (this.mCanBusInfoInt[3] == 4) {
            this.isDGear = true;
        } else {
            this.isDGear = false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrivingComputerInfo0() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append(this.mFuelUnit).toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrivingComputerInfo1() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append(this.mFuelUnit).toString()));
        arrayList.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[5] + "km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[5]);
    }

    private void setDrivingComputerInfo2() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append(this.mFuelUnit).toString()));
        arrayList.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[5]);
    }

    private void setPanelButton(int[] iArr) {
        int i = this.mDifferentId;
        if (i == 3) {
            car_C4L();
            return;
        }
        if (i == 4 || i == 5) {
            car_C5();
            return;
        }
        if (i == 9) {
            car_508_Low();
            return;
        }
        if (i == 10) {
            car_508_High();
            return;
        }
        if (i == 12) {
            car_DS5_High();
            return;
        }
        if (i == 13) {
            car_DS5L_And_DS6_High();
            return;
        }
        if (i == 15) {
            car_DS4_High();
            return;
        }
        if (i == 16) {
            car_408_Year_2014();
            return;
        }
        if (i == 20) {
            car_4008_Year_2017();
            return;
        }
        if (i == 21) {
            car_508_Year_2015_have_amplifier_High();
            return;
        }
        if (i == 27) {
            car_407_Year_2006();
            return;
        }
        if (i == 65) {
            car_DS4_Low();
            return;
        }
        if (i == 52) {
            car_DS5_Low();
        } else if (i == 53) {
            car_DS5L_And_DS6_Low();
        } else {
            otherCar();
        }
    }

    private void otherCar() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 64) {
            panelButtonClick(42);
            return;
        }
        if (i == 36) {
            panelButtonClick(49);
            return;
        }
        if (i != 37) {
            switch (i) {
                case 6:
                    panelButtonClick(50);
                    break;
                case 7:
                    panelButtonClick(46);
                    break;
                case 8:
                    panelButtonClick(2);
                    break;
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 22:
                            panelButtonClick(129);
                            break;
                        case 23:
                            panelButtonClick(21);
                            break;
                        case 24:
                            panelButtonClick(20);
                            break;
                        case 25:
                            panelButtonClick(47);
                            break;
                        case 26:
                            panelButtonClick(48);
                            break;
                        default:
                            switch (i) {
                                case 39:
                                    panelButtonClick(39);
                                    break;
                                case 40:
                                    panelButtonClick(94);
                                    break;
                                case 41:
                                    panelButtonClick(59);
                                    break;
                                case 42:
                                    panelButtonClick(40);
                                    break;
                                case 43:
                                    panelButtonClick(14);
                                    break;
                                case 44:
                                    panelButtonClick(52);
                                    break;
                                case 45:
                                    panelButtonClick(15);
                                    break;
                                case 46:
                                    panelButtonClick(128);
                                    break;
                                case 47:
                                    panelButtonClick(151);
                                    break;
                                default:
                                    switch (i) {
                                        case 49:
                                            panelButtonClick(41);
                                            break;
                                        case 50:
                                            panelButtonClick(1);
                                            break;
                                        case 51:
                                            panelButtonClick(58);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        panelButtonClick(HotKeyConstant.K_SET_BACKLIGHT);
    }

    private void car_407_Year_2006() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 7) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 40) {
            startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
            return;
        }
        if (i == 43) {
            panelButtonClick(14);
            return;
        }
        if (i == 46) {
            panelButtonClick(58);
            return;
        }
        if (i == 49) {
            panelButtonClick(2);
            return;
        }
        if (i == 64) {
            toCarPc();
            return;
        }
        if (i == 36) {
            panelButtonClick(49);
            return;
        }
        if (i != 37) {
            switch (i) {
                case 23:
                    panelButtonClick(45);
                    break;
                case 24:
                    panelButtonClick(46);
                    break;
                case 25:
                    panelButtonClick(47);
                    break;
                case 26:
                    panelButtonClick(48);
                    break;
            }
            return;
        }
        panelButtonClick(1);
    }

    private void toCarPc() {
        int i = this.nowPcState;
        if (i == 1) {
            startDrivingDataActivity(this.mContext, 1);
            this.nowPcState = 2;
        } else if (i == 2) {
            startDrivingDataActivity(this.mContext, 2);
            this.nowPcState = 3;
        } else if (i == 3) {
            startDrivingDataActivity(this.mContext, 3);
            this.nowPcState = 1;
        }
    }

    private void car_C5() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 40) {
            startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
            return;
        }
        if (i != 51) {
            switch (i) {
                case 43:
                    panelButtonClick(14);
                    break;
                case 44:
                    panelButtonClick(52);
                    break;
                case 45:
                    panelButtonClick(59);
                    break;
                case 46:
                    panelButtonClick(58);
                    break;
                case 47:
                    panelButtonClick(151);
                    break;
            }
            return;
        }
        panelButtonClick(128);
    }

    private void car_508_Year_2015_have_amplifier_High() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            startWarningActivity(this.mContext);
            return;
        }
        if (i == 7) {
            panelButtonClick(21);
            return;
        }
        if (i != 40) {
            switch (i) {
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            setBackLightUP(false);
                            break;
                        case 26:
                            setBackLightUP(true);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(HotKeyConstant.K_SPEECH);
                                    break;
                                case 46:
                                    panelButtonClick(62);
                                    break;
                                case 47:
                                    panelButtonClick(20);
                                    break;
                            }
                    }
            }
            return;
        }
        panelButtonClick(HotKeyConstant.K_SLEEP);
    }

    private void setBackLightUP(boolean z) {
        if (z) {
            int i = this.nowLight;
            if (i != 5) {
                setBacklightLevel(i + 1);
                this.nowLight++;
                return;
            }
            return;
        }
        int i2 = this.nowLight;
        if (i2 != 1) {
            setBacklightLevel(i2 - 1);
            this.nowLight--;
        }
    }

    private void car_4008_Year_2017() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 40) {
            startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
            return;
        }
        if (i == 41) {
            setLightMode();
            return;
        }
        if (i != 51) {
            switch (i) {
                case 43:
                    panelButtonClick(14);
                    break;
                case 44:
                    panelButtonClick(52);
                    break;
                case 45:
                    panelButtonClick(59);
                    break;
                case 46:
                    panelButtonClick(58);
                    break;
            }
            return;
        }
        panelButtonClick(128);
    }

    private void setLightMode() {
        int i = this.nowLightMode;
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 125, 10, 0});
            this.nowLightMode = 1;
        } else if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 125, 10, 1});
            this.nowLightMode = 2;
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 125, 10, 2});
            this.nowLightMode = 0;
        }
    }

    private void car_C4L() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 7) {
            panelButtonClick(151);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i != 39) {
            switch (i) {
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(21);
                            break;
                        case 24:
                            panelButtonClick(20);
                            break;
                        case 25:
                            panelButtonClick(45);
                            break;
                        case 26:
                            panelButtonClick(46);
                            break;
                        default:
                            switch (i) {
                                case 42:
                                    panelButtonClick(49);
                                    break;
                                case 43:
                                    panelButtonClick(68);
                                    break;
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(59);
                                    break;
                                case 46:
                                    startMainActivity(this.mContext);
                                    break;
                                case 47:
                                    panelButtonClick(62);
                                    break;
                            }
                    }
            }
            return;
        }
        toCarPc();
    }

    private void car_508_Low() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 36) {
            panelButtonClick(49);
            return;
        }
        if (i != 37) {
            switch (i) {
                case 7:
                    panelButtonClick(62);
                    break;
                case 8:
                    panelButtonClick(2);
                    break;
                case 9:
                    panelButtonClick(59);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 22:
                            panelButtonClick(151);
                            break;
                        case 23:
                            panelButtonClick(21);
                            break;
                        case 24:
                            panelButtonClick(20);
                            break;
                        case 25:
                            panelButtonClick(45);
                            break;
                        case 26:
                            panelButtonClick(46);
                            break;
                        default:
                            switch (i) {
                                case 39:
                                    toCarPc();
                                    break;
                                case 40:
                                    panelButtonClick(94);
                                    break;
                                case 41:
                                    startMainActivity(this.mContext);
                                    break;
                            }
                    }
            }
            return;
        }
        panelButtonClick(HotKeyConstant.K_SLEEP);
    }

    private void car_508_High() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 36) {
            panelButtonClick(49);
            return;
        }
        if (i != 37) {
            switch (i) {
                case 7:
                    panelButtonClick(HotKeyConstant.K_SLEEP);
                    break;
                case 8:
                    panelButtonClick(2);
                    break;
                case 9:
                    panelButtonClick(59);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 22:
                            panelButtonClick(62);
                            break;
                        case 23:
                            panelButtonClick(21);
                            break;
                        case 24:
                            panelButtonClick(20);
                            break;
                        case 25:
                            panelButtonClick(45);
                            break;
                        case 26:
                            panelButtonClick(46);
                            break;
                        default:
                            switch (i) {
                                case 39:
                                    toCarPc();
                                    break;
                                case 40:
                                    panelButtonClick(94);
                                    break;
                                case 41:
                                    startMainActivity(this.mContext);
                                    break;
                                case 42:
                                    panelButtonClick(62);
                                    break;
                                case 43:
                                    panelButtonClick(14);
                                    break;
                                default:
                                    switch (i) {
                                        case 45:
                                            panelButtonClick(15);
                                            break;
                                        case 46:
                                            panelButtonClick(58);
                                            break;
                                        case 47:
                                            startWarningActivity(this.mContext);
                                            break;
                                        default:
                                            switch (i) {
                                                case 49:
                                                    panelButtonClick(2);
                                                    break;
                                                case 50:
                                                    panelButtonClick(1);
                                                    break;
                                                case 51:
                                                    panelButtonClick(128);
                                                    break;
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        panelButtonClick(HotKeyConstant.K_SLEEP);
    }

    private void car_DS4_Low() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 7) {
            panelButtonClick(151);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 42) {
            panelButtonClick(49);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 9:
                    panelButtonClick(94);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            panelButtonClick(20);
                            break;
                        case 26:
                            panelButtonClick(21);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(59);
                                    break;
                                case 46:
                                    startMainActivity(this.mContext);
                                    break;
                                case 47:
                                    panelButtonClick(62);
                                    break;
                            }
                    }
            }
            return;
        }
        toCarPc();
    }

    private void car_DS4_High() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 7) {
            panelButtonClick(59);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 42) {
            panelButtonClick(49);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 9:
                    panelButtonClick(58);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            panelButtonClick(20);
                            break;
                        case 26:
                            panelButtonClick(21);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    startDrivingDataActivity(this.mContext, 0);
                                    break;
                                case 45:
                                    panelButtonClick(59);
                                    break;
                                case 46:
                                    panelButtonClick(128);
                                    break;
                                case 47:
                                    panelButtonClick(62);
                                    break;
                            }
                    }
            }
            return;
        }
        toCarPc();
    }

    private void car_408_Year_2014() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
        } else if (i == 1) {
            panelButtonClick(1);
        } else {
            if (i != 17) {
                return;
            }
            panelButtonClick(31);
        }
    }

    private void car_DS5L_And_DS6_Low() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            panelButtonClick(50);
            return;
        }
        if (i == 7) {
            panelButtonClick(151);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 42) {
            panelButtonClick(49);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            panelButtonClick(20);
                            break;
                        case 26:
                            panelButtonClick(21);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(59);
                                    break;
                                case 46:
                                    startMainActivity(this.mContext);
                                    break;
                                case 47:
                                    panelButtonClick(62);
                                    break;
                            }
                    }
            }
            return;
        }
        toCarPc();
    }

    private void car_DS5L_And_DS6_High() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            setBackLightUP(true);
            return;
        }
        if (i == 7) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 42) {
            panelButtonClick(49);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(20);
                            break;
                        case 24:
                            panelButtonClick(21);
                            break;
                        case 25:
                            panelButtonClick(45);
                            break;
                        case 26:
                            panelButtonClick(46);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    panelButtonClick(3);
                                    break;
                                case 45:
                                    startWarningActivity(this.mContext);
                                    break;
                                case 46:
                                    panelButtonClick(62);
                                    break;
                                case 47:
                                    setBackLightUP(false);
                                    break;
                            }
                    }
            }
            return;
        }
        panelButtonClick(HotKeyConstant.K_SPEECH);
    }

    private void car_DS5_Low() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            panelButtonClick(50);
            return;
        }
        if (i == 7) {
            panelButtonClick(62);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 42) {
            panelButtonClick(49);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            panelButtonClick(21);
                            break;
                        case 26:
                            panelButtonClick(20);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(59);
                                    break;
                                case 46:
                                    startMainActivity(this.mContext);
                                    break;
                                case 47:
                                    panelButtonClick(151);
                                    break;
                            }
                    }
            }
            return;
        }
        toCarPc();
    }

    private void car_DS5_High() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(1);
            return;
        }
        if (i == 6) {
            startWarningActivity(this.mContext);
            return;
        }
        if (i == 7) {
            panelButtonClick(21);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 42) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            setBackLightUP(false);
                            break;
                        case 26:
                            setBackLightUP(true);
                            break;
                        default:
                            switch (i) {
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(HotKeyConstant.K_SPEECH);
                                    break;
                                case 46:
                                    panelButtonClick(62);
                                    break;
                                case 47:
                                    panelButtonClick(20);
                                    break;
                            }
                    }
            }
            return;
        }
        panelButtonClick(HotKeyConstant.K_SPEECH);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x81RememberedSpeedData() {
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x81Command = Arrays.copyOf(bArr, bArr.length);
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        boolean z = ((i >> 7) & 1) == 1;
        int i2 = (i >> 7) & 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 6, 1);
        int[] iArr = this.mCanBusInfoInt;
        int i3 = iArr[2];
        int i4 = (i3 >> 5) & 1;
        int i5 = (i3 >> 4) & 1;
        int i6 = (i3 >> 3) & 1;
        int i7 = (i3 >> 2) & 1;
        int i8 = (i3 >> 1) & 1;
        int i9 = iArr[3];
        int i10 = iArr[4];
        int i11 = iArr[5];
        int i12 = iArr[6];
        int i13 = iArr[7];
        int i14 = iArr[8];
        int i15 = iArr[11];
        boolean z2 = ((i15 >> 7) & 1) == 1;
        boolean z3 = ((i15 >> 6) & 1) == 1;
        boolean z4 = ((i15 >> 5) & 1) == 1;
        boolean z5 = ((i15 >> 4) & 1) == 1;
        boolean z6 = ((i15 >> 3) & 1) == 1;
        boolean z7 = ((i15 >> 2) & 1) == 1;
        boolean z8 = ((i15 >> 1) & 1) == 1;
        arrayList.add(getSettingUpdateEntity("_143_speed_setting2").setValue(Integer.valueOf(i2)).setEnable(z2));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting3").setValue(Integer.valueOf(intFromByteWithBit)).setEnable(z && z3));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting4").setValue(Integer.valueOf(i4)).setEnable(z && z4));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting5").setValue(Integer.valueOf(i5)).setEnable(z && z5));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting6").setValue(Integer.valueOf(i6)).setEnable(z && z6));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting7").setValue(Integer.valueOf(i7)).setEnable(z && z7));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting8").setValue(Integer.valueOf(i8)).setEnable(z && z8));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting9").setValue(Integer.valueOf(i9)).setProgress(this.mCanBusInfoInt[3]).setEnable(z && z3 && intFromByteWithBit == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting10").setValue(Integer.valueOf(i10)).setProgress(this.mCanBusInfoInt[4]).setEnable(z && z4 && i4 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting11").setValue(Integer.valueOf(i11)).setProgress(this.mCanBusInfoInt[5]).setEnable(z && z5 && i5 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting12").setValue(Integer.valueOf(i12)).setProgress(this.mCanBusInfoInt[6]).setEnable(z && z6 && i6 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting13").setValue(Integer.valueOf(i13)).setProgress(this.mCanBusInfoInt[7]).setEnable(z && z7 && i7 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x81_setting14").setValue(Integer.valueOf(i14)).setProgress(this.mCanBusInfoInt[8]).setEnable(z && z8 && i8 == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x82CruiseSpeedData() {
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x82Command = Arrays.copyOf(bArr, bArr.length);
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        boolean z = ((i >> 7) & 1) == 1;
        int i2 = (i >> 7) & 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 6, 1);
        int[] iArr = this.mCanBusInfoInt;
        int i3 = iArr[2];
        int i4 = (i3 >> 5) & 1;
        int i5 = (i3 >> 4) & 1;
        int i6 = (i3 >> 3) & 1;
        int i7 = (i3 >> 2) & 1;
        int i8 = (i3 >> 1) & 1;
        int i9 = iArr[3];
        int i10 = iArr[4];
        int i11 = iArr[5];
        int i12 = iArr[6];
        int i13 = iArr[7];
        int i14 = iArr[8];
        int i15 = iArr[11];
        boolean z2 = ((i15 >> 7) & 1) == 1;
        boolean z3 = ((i15 >> 6) & 1) == 1;
        boolean z4 = ((i15 >> 5) & 1) == 1;
        boolean z5 = ((i15 >> 4) & 1) == 1;
        boolean z6 = ((i15 >> 3) & 1) == 1;
        boolean z7 = ((i15 >> 2) & 1) == 1;
        boolean z8 = ((i15 >> 1) & 1) == 1;
        arrayList.add(getSettingUpdateEntity("_143_0x82_setting_1").setValue(Integer.valueOf(i2)).setEnable(z2));
        arrayList.add(getSettingUpdateEntity("_143_spee_0x82d_setting3").setValue(Integer.valueOf(intFromByteWithBit)).setEnable(z && z3));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting4").setValue(Integer.valueOf(i4)).setEnable(z && z4));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting5").setValue(Integer.valueOf(i5)).setEnable(z && z5));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting6").setValue(Integer.valueOf(i6)).setEnable(z && z6));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting7").setValue(Integer.valueOf(i7)).setEnable(z && z7));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting8").setValue(Integer.valueOf(i8)).setEnable(z && z8));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting9").setValue(Integer.valueOf(i9)).setProgress(this.mCanBusInfoInt[3]).setEnable(z && z3 && intFromByteWithBit == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting10").setValue(Integer.valueOf(i10)).setProgress(i10).setEnable(z && z4 && i4 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting11").setValue(Integer.valueOf(i11)).setProgress(this.mCanBusInfoInt[5]).setEnable(z && z5 && i5 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting12").setValue(Integer.valueOf(i12)).setProgress(this.mCanBusInfoInt[6]).setEnable(z && z6 && i6 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting13").setValue(Integer.valueOf(i13)).setProgress(this.mCanBusInfoInt[7]).setEnable(z && z7 && i7 == 1));
        arrayList.add(getSettingUpdateEntity("_143_speed_0x82_setting14").setValue(Integer.valueOf(i14)).setProgress(this.mCanBusInfoInt[8]).setEnable(z && z8 && i8 == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [com.hzbhd.canbus.car._143.MsgMgr$4] */
    private void set0x83Setting() {
        int i = this.mCanBusInfoInt[2];
        if (i != 0) {
            if (i == 1) {
                this.contentMsg = this.mContext.getString(R.string._143_0x83_setting_1);
            } else {
                this.contentMsg = this.mContext.getString(R.string._143_0x83_setting_2);
            }
            if (this.view == null) {
                this.view = LayoutInflater.from(getActivity()).inflate(R.layout._333_alert_dialog, (ViewGroup) null, true);
            }
            if (this.dialog == null) {
                this.dialog = new AlertDialog.Builder(getActivity()).setView(this.view).create();
            }
            if (this.content == null) {
                this.content = (TextView) this.view.findViewById(R.id.alert_content);
            }
            this.content.setText(this.contentMsg);
            if (this.i_know == null) {
                this.i_know = (Button) this.view.findViewById(R.id.i_know);
            }
            this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._143.MsgMgr.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MsgMgr.this.dialog.dismiss();
                }
            });
            new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._143.MsgMgr.4
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    MsgMgr.this.dialog.dismiss();
                }
            }.start();
            this.dialog.setCancelable(false);
            this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            this.dialog.getWindow().setType(2003);
            this.dialog.show();
            return;
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void set0x85SportDate() {
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x85Command = Arrays.copyOf(bArr, bArr.length);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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

    private String getLogo() {
        return DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]) ? "ST" : DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) ? "RDS" : DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]) ? "AF" : DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]) ? "TP" : DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]) ? "TA" : DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]) ? "EON" : DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]) ? "PTY" : DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]) ? "ST" : "";
    }

    private String getUnit() {
        return (getState(this.mCanBusInfoInt[2]) == "FM1" || getState(this.mCanBusInfoInt[2]) == "FM2" || getState(this.mCanBusInfoInt[2]) == "FM3") ? "MHz" : (getState(this.mCanBusInfoInt[2]) == "AM1" || getState(this.mCanBusInfoInt[2]) == "AM2") ? "kHz" : "";
    }

    private String getPreStorage(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.ford_original_yct);
        }
        return " P" + i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, z ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingLeft0Right12Data(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        this.bytes = new byte[]{22, -31};
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.compensateZero(this.bytes, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        switch0xE1Method(str, str2, i);
        getUiMgr(this.mContext).Data1_0xA1 = 1;
        getUiMgr(this.mContext).send0xA1HostState();
        switch0xA2Method(str, str2, i);
        switch0xA3Method(str, str2, i);
    }

    private void switch0xE1Method(String str, String str2, int i) {
        String str3;
        if (i == 256) {
            i = 0;
        }
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str3 = new DecimalFormat("00").format(i) + " " + str2 + "     ";
            } else {
                str3 = new DecimalFormat("00").format(i) + " 0" + str2 + "     ";
            }
        } else if (str2.length() == 5) {
            str3 = new DecimalFormat("00").format(i) + "  " + str2.substring(0, str2.length() - 1) + "    ";
        } else {
            str3 = new DecimalFormat("00").format(i) + " " + str2.substring(0, str2.length() - 1) + "    ";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, allBandTypeData}, str3.getBytes()));
    }

    private void switch0xA2Method(String str, String str2, int i) throws NumberFormatException {
        int i2;
        double d;
        int i3 = 1;
        if (str.equals("FM1")) {
            d = Double.parseDouble(str2);
        } else if (str.equals("FM2")) {
            i3 = 2;
            d = Double.parseDouble(str2);
        } else if (str.equals("FM3")) {
            i3 = 3;
            d = Double.parseDouble(str2);
        } else {
            if (str.equals("AM1")) {
                i3 = 4;
                i2 = Integer.parseInt(str2);
            } else if (str.equals("AM2")) {
                i3 = 5;
                i2 = Integer.parseInt(str2);
            } else {
                i2 = 0;
            }
            getUiMgr(this.mContext).send0xA2Info(i3, getLsb(i2), getMsb(i2), i);
        }
        i2 = (int) (d * 100.0d);
        getUiMgr(this.mContext).send0xA2Info(i3, getLsb(i2), getMsb(i2), i);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void switch0xA3Method(java.lang.String r5, java.lang.String r6, int r7) throws java.lang.NumberFormatException {
        /*
            r4 = this;
            java.lang.String r0 = "FM1"
            boolean r0 = r5.equals(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lf
            r1 = 2
        Lb:
            r3 = r2
            r2 = r1
            r1 = r3
            goto L34
        Lf:
            java.lang.String r0 = "FM2"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L19
            r1 = 3
            goto Lb
        L19:
            java.lang.String r0 = "FM3"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L23
        L21:
            r1 = r2
            goto L34
        L23:
            java.lang.String r0 = "AM1"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L33
            java.lang.String r0 = "AM2"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L21
        L33:
            r2 = r1
        L34:
            if (r1 == 0) goto L3b
            int r5 = java.lang.Integer.parseInt(r6)
            goto L43
        L3b:
            double r5 = java.lang.Double.parseDouble(r6)
            r0 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r5 = r5 * r0
            int r5 = (int) r5
        L43:
            switch(r7) {
                case 1: goto Lc6;
                case 2: goto Lad;
                case 3: goto L94;
                case 4: goto L7b;
                case 5: goto L62;
                case 6: goto L48;
                default: goto L46;
            }
        L46:
            goto Lde
        L48:
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r7 = r4.getLsb(r5)
            r6.data11_0xA3 = r7
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r5 = r4.getMsb(r5)
            r6.data12_0xA3 = r5
            goto Lde
        L62:
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r7 = r4.getLsb(r5)
            r6.data9_0xA3 = r7
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r5 = r4.getMsb(r5)
            r6.data10_0xA3 = r5
            goto Lde
        L7b:
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r7 = r4.getLsb(r5)
            r6.data7_0xA3 = r7
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r5 = r4.getMsb(r5)
            r6.data8_0xA3 = r5
            goto Lde
        L94:
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r7 = r4.getLsb(r5)
            r6.data5_0xA3 = r7
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r5 = r4.getMsb(r5)
            r6.data6_0xA3 = r5
            goto Lde
        Lad:
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r7 = r4.getLsb(r5)
            r6.data3_0xA3 = r7
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r5 = r4.getMsb(r5)
            r6.data4_0xA3 = r5
            goto Lde
        Lc6:
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r7 = r4.getLsb(r5)
            r6.data1_0xA3 = r7
            android.content.Context r6 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r6 = r4.getUiMgr(r6)
            int r5 = r4.getMsb(r5)
            r6.data2_0xA3 = r5
        Lde:
            android.content.Context r5 = r4.mContext
            com.hzbhd.canbus.car._143.UiMgr r5 = r4.getUiMgr(r5)
            r5.send0xA3Info(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._143.MsgMgr.switch0xA3Method(java.lang.String, java.lang.String, int):void");
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
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format((b7 * 256) + i), new Object[0]) + " " + new DecimalFormat("00").format(b4) + new DecimalFormat("00").format(b5) + "    ").getBytes()));
        reportID3(str4);
        if (b == 9) {
            getUiMgr(this.mContext).Data1_0xA1 = 2;
            getUiMgr(this.mContext).send0xA1HostState();
        } else {
            getUiMgr(this.mContext).Data1_0xA1 = 6;
            getUiMgr(this.mContext).send0xA1HostState();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + " " + new DecimalFormat("00").format(b4) + new DecimalFormat("00").format(b5) + "    ").getBytes()));
        if (b == 9) {
            getUiMgr(this.mContext).Data1_0xA1 = 2;
            getUiMgr(this.mContext).send0xA1HostState();
        } else {
            getUiMgr(this.mContext).Data1_0xA1 = 6;
            getUiMgr(this.mContext).send0xA1HostState();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        reportID3(str);
        getUiMgr(this.mContext).Data1_0xA1 = 9;
        getUiMgr(this.mContext).send0xA1HostState();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        byte[] bArr = new byte[9];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("A2DP".getBytes(), 0, bArr, 0, "A2DP".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, DataHandleUtils.byteMerger("000".getBytes(), bArr)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, ("    " + new DecimalFormat("00").format((i / 60) % 60) + new DecimalFormat("00").format(i % 60) + "    ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        getUiMgr(this.mContext).Data1_0xA1 = 7;
        getUiMgr(this.mContext).send0xA1HostState();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        getUiMgr(this.mContext).D0B5_0xA1 = 1;
        getUiMgr(this.mContext).D0B6_0xA1 = 1;
        if (z) {
            getUiMgr(this.mContext).Data2_0xA1 = 0;
        } else {
            getUiMgr(this.mContext).Data2_0xA1 = i * 6;
        }
        getUiMgr(this.mContext).send0xA1HostState();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -92, 1, 1, 0, (byte) getLsb(i4), (byte) getMsb(i4), (byte) (i / 60), (byte) (i % 60), 32, 2, (byte) getLsb(i5), (byte) getMsb(i5)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).Data1_0xA1 = 3;
        getUiMgr(this.mContext).send0xA1HostState();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        getUiMgr(this.mContext).Data1_0xA1 = 8;
        getUiMgr(this.mContext).send0xA1HostState();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        getUiMgr(this.mContext).Data1_0xA1 = 8;
        getUiMgr(this.mContext).send0xA1HostState();
    }

    private void reportID3(String str) {
        if (TextUtils.isEmpty(str) || str.equals(this.mId3SongTitle)) {
            return;
        }
        this.mId3SongTitle = str;
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -28}, DataHandleUtils.exceptBOMHead(str.getBytes("utf-8"))), 34, 32));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAmplifierData() {
        final byte[][] bArr = {new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -83, 16, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -83, 15, (byte) GeneralAmplifierData.bandTreble}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._143.MsgMgr.6
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    CanbusMsgSender.sendMsg(bArr2[i]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    private boolean equal(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_seat_master_driver_belt_tie", false) == GeneralDoorData.isSeatMasterDriverBeltTie && SharePreUtil.getBoolValue(this.mContext, "is_seat_co_pilot_belt_tie", false) == GeneralDoorData.isSeatCoPilotBeltTie && SharePreUtil.getBoolValue(this.mContext, "is_seat_r_l_belt_tie", false) == GeneralDoorData.isSeatRLBeltTie && SharePreUtil.getBoolValue(this.mContext, "is_seat_r_m_belt_tie", false) == GeneralDoorData.isSeatRMBeltTie && SharePreUtil.getBoolValue(this.mContext, "is_seat_r_r_belt_tie", false) == GeneralDoorData.isSeatRRBeltTie) ? false : true;
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusAirInfoCopy)) {
            return true;
        }
        this.mCanBusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusDoorInfoCopy)) {
            return true;
        }
        this.mCanBusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isRadarMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusRadarInfoCopy)) {
            return true;
        }
        this.mCanBusRadarInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isSwcDataMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcDataCopy)) {
            return true;
        }
        this.mCanBusSwcDataCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isBtnPanelMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusBtnPanelInfoCopy)) {
            return true;
        }
        this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[13];
        return (i < 0 || i > 250) ? "" : ((float) ((i * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
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

    private boolean is0x83MsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.m0x83InfoCopy)) {
            return true;
        }
        this.m0x83InfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void startDrivingPage() {
        Log.d("cwh", "startDrivingPage");
        int i = this.nextItem;
        if (i == 0) {
            this.currentItem = 1;
            this.nextItem = 1;
            startDrivingDataActivity(this.mContext, 1);
        } else if (i == 1) {
            this.currentItem = 2;
            this.nextItem = 2;
            startDrivingDataActivity(this.mContext, 2);
        } else {
            if (i != 2) {
                return;
            }
            this.currentItem = 3;
            this.nextItem = 0;
            startDrivingDataActivity(this.mContext, 3);
        }
    }

    byte[] get0x81Data() {
        return this.m0x81Command;
    }

    byte[] get0x82Data() {
        return this.m0x82Command;
    }

    byte[] get0x72Enable() {
        return this.m0x72Enable1;
    }

    private void initSettingsItem(Context context) {
        this.mSettingItemIndeHashMap = new HashMap<>();
        SparseArray sparseArray = new SparseArray();
        List<SettingPageUiSet.ListBean> list = getUiMgr(context).getSettingUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                String titleSrn = itemList.get(i2).getTitleSrn();
                sparseArray.append((i << 8) | i2, titleSrn);
                this.mSettingItemIndeHashMap.put(titleSrn, new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        this.mSettingItemIndeHashMap.put(str, new SettingUpdateEntity(-1, -1, null));
        return getSettingUpdateEntity(str);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void toast(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._143.MsgMgr.7
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.getActivity(), str, 0).show();
            }
        });
    }
}
