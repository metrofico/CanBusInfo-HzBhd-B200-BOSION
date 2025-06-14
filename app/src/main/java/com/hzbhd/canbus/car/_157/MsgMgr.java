package com.hzbhd.canbus.car._157;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int AMPLIFIER_FADE_OFFSET = -9;
    static final int VEHICLE_TYPE_1 = 16;
    static final int VEHICLE_TYPE_2 = 32;
    static final int VEHICLE_TYPE_3 = 48;
    static final int VEHICLE_TYPE_4 = 64;
    static final int VEHICLE_TYPE_5 = 80;
    static final int VEHICLE_TYPE_ACCORD_GEN_10 = 35;
    static final int VEHICLE_TYPE_ACCORD_HIGH = 49;
    static final int VEHICLE_TYPE_AVANCIER = 33;
    static final int VEHICLE_TYPE_BREEZE_20 = 36;
    static final int VEHICLE_TYPE_CITY_15_17 = 17;
    static final int VEHICLE_TYPE_CRIDER_13_19 = 18;
    static final int VEHICLE_TYPE_CROSSTOUR = 19;
    static final int VEHICLE_TYPE_CRV_17 = 34;
    Button EXIT;
    TextView content0;
    TextView content1;
    TextView content2;
    TextView content3;
    AlertDialog dialog;
    private SongListEntity[] disc;
    private String disc_status_off;
    private String disc_status_on;
    private int[] m0x33DataIndexOne;
    private int[] m0x33DataIndexTwo;
    int[] mAirDataInt;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackStatus;
    private int mBacklight;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private SparseArray<int[]> mCanbusDataArray;
    private Context mContext;
    private int mCurrentDisc;
    private DecimalFormat mDecimalFormat00;
    private DecimalFormat mDecimalFormat0p0;
    private int mDifferentId;
    private int mEachId;
    private boolean mFrontStatus;
    private boolean mFrontViewBtnStatus;
    private Handler mHandler;
    private boolean mIsFrontCameraOpen;
    private boolean mIsOriginalRadioBandAm;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private ID3[] mMusicId3s;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private String mOriginalStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private boolean mTurnSignalStatus;
    private int mTurnSignalStatus2;
    private UiMgr mUiMgr;
    View view;
    private final String TAG = "_157_MsgMgr";
    private final int DATA_TYPE = 1;
    private final int DATA_0 = 2;
    private final int AIR_DATA_MAX_LENGTH = 10;
    private final int BACKLIGHT_DATA_MAX = 256;
    private final int BACKLIGHT_SEGMENTS = 5;
    private final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private final int SEND_NORMAL_MESSAGE = 2;
    private byte[] mAirData = new byte[12];
    private String mCharsetName = "utf-8";
    private List<SongListEntity> mFmPresetList = new ArrayList();
    private List<SongListEntity> mAmPresetList = new ArrayList();
    private List<SongListEntity> mFmStorageList = new ArrayList();
    private List<SongListEntity> mAmStorageList = new ArrayList();
    private SongListEntity[] mFmStorageArray = new SongListEntity[40];
    private SongListEntity[] mAmStorageArray = new SongListEntity[40];
    String contentZero = "Untitled";
    String contentOne = "No first line information";
    String contentTow = "No second line information";
    String contentThree = "No third line information";
    final String ORIGINAL_STATUS_NULL = "original_status_null";
    final String ORIGINAL_STATUS_RADIO = "original_status_radio";
    final String ORIGINAL_STATUS_CD = "original_status_cd";
    final String ORIGINAL_STATUS_AUX = "original_status_aux";
    final String ORIGINAL_STATUS_USB = "original_status_usb";

    private String getDistanceUnit(int i) {
        return i != 0 ? i != 1 ? "" : " mile" : " km";
    }

    private String getFuelUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : " l/100km" : " km/l" : " mpg";
    }

    private int getRange(int i) {
        switch (i) {
            case 0:
                return 60;
            case 1:
                return 10;
            case 2:
                return 12;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return (i - 1) * 10;
            default:
                return 0;
        }
    }

    private void set0x79Data() {
    }

    private void set0xD0Data() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 9)});
        arrayList.add(new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 9)});
        arrayList.add(new byte[]{22, -124, 3, (byte) GeneralAmplifierData.bandTreble});
        arrayList.add(new byte[]{22, -124, 4, (byte) GeneralAmplifierData.bandMiddle});
        arrayList.add(new byte[]{22, -124, 5, (byte) GeneralAmplifierData.bandBass});
        arrayList.add(new byte[]{22, -124, 6, (byte) GeneralAmplifierData.megaBass});
        arrayList.add(new byte[]{22, -124, 9, (byte) GeneralAmplifierData.volume});
        final Iterator it = arrayList.iterator();
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._157.MsgMgr.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (it.hasNext()) {
                    CanbusMsgSender.sendMsg((byte[]) it.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(final Context context) {
        int i;
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        Log.i("_157_MsgMgr", "afterServiceNormalSetting: in");
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        Log.i("_157_MsgMgr", "afterServiceNormalSetting: 0x81");
        this.mDifferentId = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        Log.i("_157_MsgMgr", "afterServiceNormalSetting: diff <--> " + this.mDifferentId + ", each <--> " + this.mEachId + ", canId <--> " + this.mCanId);
        if (isShowOriginalRadio()) {
            Log.i("_157_MsgMgr", "afterServiceNormalSetting: enable original activity");
            SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        } else {
            Log.i("_157_MsgMgr", "afterServiceNormalSetting: disable original activity");
        }
        Log.i("_157_MsgMgr", "afterServiceNormalSetting: initSetting");
        initSettingsItem(getUiMgr(context).getSettingUiSet(context));
        this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        initID3();
        this.mDecimalFormat0p0 = new DecimalFormat("0.0");
        this.mDecimalFormat00 = new DecimalFormat("00");
        this.mCanbusDataArray = new SparseArray<>();
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._157.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    Log.i("_157_MsgMgr", "handleMessage: meida");
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 2) {
                    Log.i("_157_MsgMgr", "handleMessage: normal");
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
        this.mFmStorageArray[0] = new SongListEntity(CommUtil.getStrByResId(context, "_157_storage_station"));
        this.mAmStorageArray[0] = new SongListEntity(CommUtil.getStrByResId(context, "_157_storage_station"));
        if (this.mDifferentId == 34) {
            this.mCharsetName = "utf-8";
        }
        if (context.getResources().getConfiguration().orientation != 1 || (i = this.mEachId) == 0) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 3) {
            AlertDialog alertDialog = this.dialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            set0x03RadioFreqData();
            return;
        }
        if (i == 4) {
            set0x04RadioPresetData(context);
            return;
        }
        if (i == 7) {
            set0x07RadioStorageData(context);
            return;
        }
        if (i == 20) {
            Log.i("_157_MsgMgr", "canbusInfoChange: 0x14");
            set0x14Data();
            return;
        }
        if (i == 41) {
            set0x29TrackData(context);
            return;
        }
        if (i == 80) {
            set0x50PanoramicData(context);
            return;
        }
        if (i != 82) {
            switch (i) {
                case 32:
                    set0x20WheelKeyData(context);
                    break;
                case 33:
                    set0x21AirData(context);
                    break;
                case 34:
                    set0x22RearRadarData(context);
                    break;
                case 35:
                    set0x23FrontRadarData(context);
                    break;
                case 36:
                    set0x24Data(context);
                    break;
                case 37:
                    set0x25Data(context);
                    break;
                default:
                    switch (i) {
                        case 48:
                            set0x30VersionInfoData(context);
                            break;
                        case 49:
                            set0x31AmplifierData(context);
                            break;
                        case 50:
                            set0x32VehicleSettingData();
                            break;
                        case 51:
                            set0x33FuelComsuptionData();
                            break;
                        default:
                            switch (i) {
                                case 119:
                                    set0x77Data();
                                    break;
                                case 120:
                                    set0x78OriginalVolumeData(context);
                                    break;
                                case 121:
                                    set0x79Data();
                                    break;
                                case 122:
                                    set0x7ASoundtrackData(context);
                                    break;
                                case 123:
                                    AlertDialog alertDialog2 = this.dialog;
                                    if (alertDialog2 != null) {
                                        alertDialog2.dismiss();
                                    }
                                    set0x7BOriginalStatusData(context);
                                    break;
                                default:
                                    switch (i) {
                                        case HotKeyConstant.K_SOS /* 208 */:
                                            set0xD0Data();
                                            break;
                                        case HotKeyConstant.K_AVM /* 209 */:
                                            set0xD1CameraData(context);
                                            break;
                                        case 210:
                                            set0xD2CompassData(context);
                                            break;
                                        case 211:
                                            set0xD3SourceSwitch(context);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        set0x52Data();
    }

    private void set0x20WheelKeyData(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(context, 45);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(context, 45);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(context, 46);
            return;
        }
        if (i == 129) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i == 130) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i != 134) {
            switch (i) {
                case 7:
                    realKeyLongClick1(context, 2);
                    break;
                case 8:
                    realKeyLongClick1(context, HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    realKeyLongClick1(context, 14);
                    break;
                case 10:
                    realKeyLongClick1(context, HotKeyConstant.K_PHONE_OFF_RETURN);
                    break;
                default:
                    switch (i) {
                        case 22:
                            realKeyLongClick1(context, 49);
                            break;
                        case 23:
                            resolve0x17KeyMenu(context);
                            break;
                        case 24:
                            realKeyLongClick1(context, 52);
                            break;
                        default:
                            switch (i) {
                                case 32:
                                    realKeyLongClick1(context, 7);
                                    break;
                                case 33:
                                    realKeyLongClick1(context, 8);
                                    break;
                                case 34:
                                    realKeyLongClick1(context, 46);
                                    break;
                                case 35:
                                    realKeyLongClick1(context, 45);
                                    break;
                                case 36:
                                    realKeyLongClick1(context, 20);
                                    break;
                                case 37:
                                    realKeyLongClick1(context, 21);
                                    break;
                                case 38:
                                    realKeyLongClick1(context, 46);
                                    break;
                                case 39:
                                    realKeyLongClick1(context, 45);
                                    break;
                                default:
                                    switch (i) {
                                        case 41:
                                            switchFrontCamera();
                                            break;
                                        case 42:
                                            realKeyLongClick1(context, 17);
                                            break;
                                        case 43:
                                            realKeyLongClick1(context, 18);
                                            break;
                                        case 44:
                                            realKeyLongClick1(context, 19);
                                            break;
                                        default:
                                            switch (i) {
                                                case 48:
                                                    realKeyLongClick1(context, 3);
                                                    break;
                                                case 49:
                                                    realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
                                                    break;
                                                case 50:
                                                    realKeyLongClick1(context, 50);
                                                    break;
                                                case 51:
                                                    realKeyLongClick1(context, 76);
                                                    break;
                                                case 52:
                                                    realKeyLongClick1(context, 77);
                                                    break;
                                                case 53:
                                                    realKeyLongClick1(context, 130);
                                                    break;
                                                case 54:
                                                    realKeyLongClick1(context, 141);
                                                    break;
                                                case 55:
                                                    realKeyLongClick1(context, 14);
                                                    break;
                                                case 56:
                                                    realKeyLongClick1(context, 33);
                                                    break;
                                                case 57:
                                                    realKeyLongClick1(context, 34);
                                                    break;
                                                case 58:
                                                    realKeyLongClick1(context, 35);
                                                    break;
                                                case 59:
                                                    realKeyLongClick1(context, 36);
                                                    break;
                                                case 60:
                                                    realKeyLongClick1(context, 37);
                                                    break;
                                                case 61:
                                                    realKeyLongClick1(context, 38);
                                                    break;
                                                case 62:
                                                    realKeyLongClick1(context, 49);
                                                    break;
                                                case 63:
                                                    adjustBrightness();
                                                    break;
                                                case 64:
                                                    realKeyLongClick1(context, 52);
                                                    break;
                                                case 65:
                                                    realKeyLongClick1(context, 17);
                                                    break;
                                                case 66:
                                                    realKeyLongClick1(context, 4);
                                                    break;
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(context, 3);
    }

    private void set0x21AirData(Context context) {
        if (!GeneralAirData.climate && DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            if (SystemUtil.isForeground(context, AirActivity.class.getName())) {
                finishActivity();
            } else {
                AirActivity.mIsClickOpen = true;
                Intent intent = new Intent(context, (Class<?>) AirActivity.class);
                intent.setFlags(268435456);
                context.startActivity(intent);
            }
        }
        GeneralAirData.climate = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = iArr[3] & 239;
        iArr[6] = iArr[6] & 191;
        iArr[7] = 0;
        iArr[9] = iArr[9] & 239;
        if (isAirDataChange()) {
            int airWhat = getAirWhat();
            GeneralAirData.auto_manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[4] & 255);
            GeneralAirData.front_right_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[5] & 255);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[8] & 255);
            GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralAirData.rear_right_blow_window = GeneralAirData.rear_left_blow_window;
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 2);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
            updateAirActivity(context, airWhat);
        }
    }

    private void set0x24Data(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
        this.mTurnSignalStatus2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
    }

    private void set0x30VersionInfoData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x31AmplifierData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[3] + AMPLIFIER_FADE_OFFSET;
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] + AMPLIFIER_FADE_OFFSET;
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[5];
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[7];
            GeneralAmplifierData.megaBass = this.mCanBusInfoInt[8];
            updateAmplifierActivity(null);
            saveAmplifierData(context, this.mCanId);
            ArrayList arrayList = new ArrayList();
            arrayList.add(helperSetValue("_301_volume_and_speed_linkage", Integer.valueOf(this.mCanBusInfoInt[9])));
            arrayList.add(helperSetValue("_301_dts_audio", Integer.valueOf(this.mCanBusInfoInt[10])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x32VehicleSettingData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data1_bit65", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data1_bit43", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data1_bit20", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x67_data1_bit64", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x67_data1_bit32", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x67_data1_bit10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x65_data1_bit0", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)))));
            arrayList.add(checkEntity(helperSetValue("_41_key_remote_unlock", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x65_data1_bit21", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x65_data1_bit76", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x65_data1_bit54", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x66_data1_bit3", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x66_data1_bit0", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)))));
            arrayList.add(checkEntity(helperSetValue("_194_unlock_mode", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x66_data1_bit2", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x67_data0_bit20", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data0_bit10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data0_bit2", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data0_bit4", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1)))));
            arrayList.add(checkEntity(helperSetValue("_41_speed_distance_units", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1)))));
            arrayList.add(checkEntity(helperSetValue("_41_tachometer", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x65_data1_bit3", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x67_data0_bit3", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x66_data1_bit1", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data0_bit3", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x68_data1_bit2", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x68_data1_bit3", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x68_data1_bit10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x68_data1_bit54", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2)))));
            arrayList.add(checkEntity(helperSetValue("_41_tachometer_switch", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x68_data1_bit76", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x75_data1_bit0", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x75_data1_bit1", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data1_bit7", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x68_data0_bit0", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1)))));
            arrayList.add(checkEntity(helperSetValue("_157_memory_position", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x64_data2_bit0", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0xE8_data6_bit0", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0xE8_data6_bit1", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0xE8_data6_bit2", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0xE8_data6_bit3", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0xE8_data5", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1)))));
            arrayList.add(checkEntity(helperSetValue("_157_rear_entertainment_system", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 1)))));
            arrayList.add(checkEntity(helperSetValue("_55_0x69_data0_bit6", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 1)))));
            int[] iArr = this.mCanBusInfoInt;
            if (iArr.length > 10) {
                arrayList.add(checkEntity(helperSetValue("_157_auto_trunk", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[10], 7, 1)))));
                arrayList.add(checkEntity(helperSetValue("_157_seat_auto_move", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1)))));
                arrayList.add(checkEntity(helperSetValue("_157_Rear_seat_reminder", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1)))));
            }
            int[] iArr2 = this.mCanBusInfoInt;
            if (iArr2.length > 11) {
                arrayList.add(checkEntity(helperSetValue("_55_0xE8_data0_bit10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr2[11], 6, 2)))));
                arrayList.add(checkEntity(helperSetValue("_55_0xE8_data0_bit32", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2)))));
                arrayList.add(checkEntity(helperSetValue("_55_0xE8_data6_bit4", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 3, 1)))));
                arrayList.add(checkEntity(helperSetValue("_55_0xE8_data6_bit5", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 1)))));
                arrayList.add(checkEntity(helperSetValue("_157_revers_mode", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 1, 1)))));
                arrayList.add(checkEntity(helperSetValue("_55_0xE8_data0_bit4", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 1)))));
            }
            int[] iArr3 = this.mCanBusInfoInt;
            if (iArr3.length > 12) {
                arrayList.add(checkEntity(helperSetValue("_157_trunk_sense_switch", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr3[12], 7, 1)))));
                arrayList.add(checkEntity(helperSetValue("_157_back_mirror_fold", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 6, 1)))));
                arrayList.add(checkEntity(helperSetValue("_157_turn_guide_signal", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 5, 1)))));
                arrayList.add(checkEntity(helperSetValue("_157_Straight_line_driving_assistance_reminder", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 1)))));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x33FuelComsuptionData() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            if (is0x33IndexOneDataChange()) {
                String fuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2));
                String fuelUnit2 = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 2, 2));
                String fuelUnit3 = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2));
                String distanceUnit = getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1));
                String distanceUnit2 = getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1));
                int range = getRange(this.mCanBusInfoInt[16]);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DriverUpdateEntity(0, 0, getData(new int[]{this.mCanBusInfoInt[3]}, range * 0.04761905f, fuelUnit)));
                int[] iArr = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 1, getData(new int[]{iArr[5], iArr[4]}, 0.1f, fuelUnit2)));
                int[] iArr2 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 2, getData(new int[]{iArr2[7], iArr2[6]}, 0.1f, fuelUnit2)));
                int[] iArr3 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 3, getData(new int[]{iArr3[9], iArr3[8]}, 0.1f, fuelUnit3)));
                int[] iArr4 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 4, getData(new int[]{iArr4[12], iArr4[11], iArr4[10]}, 0.1f, distanceUnit)));
                int[] iArr5 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 5, getData(new int[]{iArr5[14], iArr5[13]}, 1.0f, distanceUnit2)));
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
                return;
            }
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[3] + "%"));
            updateGeneralDriveData(arrayList2);
            updateDriveDataActivity(null);
            return;
        }
        if (is0x33IndexTwoDataChange()) {
            String fuelUnit4 = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 4, 2));
            String distanceUnit3 = getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 6, 1));
            ArrayList arrayList3 = new ArrayList();
            int[] iArr6 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(1, 1, getData(new int[]{iArr6[5], iArr6[4], iArr6[3]}, 0.1f, distanceUnit3)));
            int[] iArr7 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(1, 2, getData(new int[]{iArr7[7], iArr7[6]}, 0.1f, fuelUnit4)));
            int[] iArr8 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(1, 4, getData(new int[]{iArr8[10], iArr8[9], iArr8[8]}, 0.1f, distanceUnit3)));
            int[] iArr9 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(1, 5, getData(new int[]{iArr9[12], iArr9[11]}, 0.1f, fuelUnit4)));
            int[] iArr10 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(1, 7, getData(new int[]{iArr10[15], iArr10[14], iArr10[13]}, 0.1f, distanceUnit3)));
            int[] iArr11 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(1, 8, getData(new int[]{iArr11[17], iArr11[16]}, 0.1f, fuelUnit4)));
            updateGeneralDriveData(arrayList3);
            updateDriveDataActivity(null);
        }
    }

    private void set0x29TrackData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, context);
        }
    }

    private void set0xD2CompassData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            String strByResId = CommUtil.getStrByResId(context, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "compass_calibrating" : "compass_calibration_finish");
            int iRangeNumber = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4), 1, 15);
            arrayList.add(checkEntity(helperSetValue("compass_calibration_status", strByResId).setValueStr(true)));
            arrayList.add(checkEntity(helperSetValue("compass_zoom", Integer.toString(iRangeNumber)).setProgress(iRangeNumber - 1).setValueStr(true)));
            arrayList.add(checkEntity(helperSetValue("magnetic_field_angle", ((this.mCanBusInfoInt[3] * 3) / 2.0f) + "°").setValueStr(true)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void set0x14Data() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int i = this.mCanBusInfoInt[2];
            if (i >= 0 && i < 51) {
                setBacklightLevel(1);
            } else if (i >= 51 && i < 102) {
                setBacklightLevel(2);
            } else if (i >= 102 && i < 153) {
                setBacklightLevel(3);
            } else if (i >= 153 && i < 204) {
                setBacklightLevel(4);
            } else if (i >= 204 && i < 255) {
                setBacklightLevel(5);
            }
            if (this.mCanBusInfoInt.length > 3) {
                ArrayList arrayList = new ArrayList();
                int[] iArr = this.mCanBusInfoInt;
                switch (iArr.length) {
                    case 4:
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_mode", Integer.valueOf(this.mCanBusInfoInt[3]))));
                        break;
                    case 5:
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_color", Integer.valueOf(this.mCanBusInfoInt[4]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_mode", Integer.valueOf(this.mCanBusInfoInt[3]))));
                        break;
                    case 6:
                        arrayList.add(checkEntity(helperSetValue("_157_brightness", Integer.valueOf(this.mCanBusInfoInt[5]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_color", Integer.valueOf(this.mCanBusInfoInt[4]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_mode", Integer.valueOf(this.mCanBusInfoInt[3]))));
                        break;
                    case 7:
                        arrayList.add(checkEntity(helperSetValue("_157_contrast", Integer.valueOf(this.mCanBusInfoInt[6]))));
                        arrayList.add(checkEntity(helperSetValue("_157_brightness", Integer.valueOf(this.mCanBusInfoInt[5]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_color", Integer.valueOf(this.mCanBusInfoInt[4]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_mode", Integer.valueOf(this.mCanBusInfoInt[3]))));
                        break;
                    case 8:
                        arrayList.add(checkEntity(helperSetValue("_157_saturation", Integer.valueOf(iArr[7]))));
                        arrayList.add(checkEntity(helperSetValue("_157_contrast", Integer.valueOf(this.mCanBusInfoInt[6]))));
                        arrayList.add(checkEntity(helperSetValue("_157_brightness", Integer.valueOf(this.mCanBusInfoInt[5]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_color", Integer.valueOf(this.mCanBusInfoInt[4]))));
                        arrayList.add(checkEntity(helperSetValue("_157_backlight_mode", Integer.valueOf(this.mCanBusInfoInt[3]))));
                        break;
                }
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
            }
        }
    }

    private void set0x22RearRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x25Data(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(checkEntity(helperSetValue("_41_rear_radar", getOpenClose(context, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))).setValueStr(true)));
            arrayList.add(checkEntity(helperSetValue("_41_front_radar", getOpenClose(context, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))).setValueStr(true)));
            arrayList.add(checkEntity(helperSetValue("_41_park_assist", getOpenClose(context, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true)));
            arrayList.add(checkEntity(helperSetValue("_41_radar_sound", getOpenClose(context, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))).setValueStr(true)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x23FrontRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x03RadioFreqData() {
        if ("original_status_radio".equals(this.mOriginalStatus) && isDataChange(this.mCanBusInfoInt)) {
            GeneralOriginalCarDeviceData.cdStatus = "Radio";
            GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.refresh = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mIsOriginalRadioBandAm = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            if (this.mIsOriginalRadioBandAm) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(0, "AM"));
                StringBuilder sb = new StringBuilder();
                int[] iArr = this.mCanBusInfoInt;
                arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb.append((iArr[3] << 8) | iArr[4]).append(" KHz").toString()));
                GeneralOriginalCarDeviceData.songList = getAmList();
            } else {
                arrayList.add(new OriginalCarDeviceUpdateEntity(0, "FM"));
                StringBuilder sb2 = new StringBuilder();
                int[] iArr2 = this.mCanBusInfoInt;
                arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb2.append(((iArr2[3] << 8) | iArr2[4]) / 10.0f).append(" MHz").toString()));
                GeneralOriginalCarDeviceData.songList = getFmList();
            }
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "P" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void set0x04RadioPresetData(Context context) {
        if ("original_status_radio".equals(this.mOriginalStatus) && isDataChange(this.mCanBusInfoInt)) {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            int i = 0;
            if (boolBit7) {
                ArrayList arrayList = new ArrayList();
                this.mAmPresetList = arrayList;
                arrayList.add(new SongListEntity(CommUtil.getStrByResId(context, "_209_preset_station")));
                while (i < 6) {
                    int[] iArr = this.mCanBusInfoInt;
                    int i2 = i * 2;
                    i++;
                    this.mAmPresetList.add(new SongListEntity("\t" + (this.mDecimalFormat00.format(i) + "#") + "\t" + (iArr[i2 + 2 + 2] | (iArr[(i2 + 1) + 2] << 8)) + " KHz"));
                }
                if (boolBit7 != this.mIsOriginalRadioBandAm) {
                    return;
                } else {
                    GeneralOriginalCarDeviceData.songList = getAmList();
                }
            } else {
                ArrayList arrayList2 = new ArrayList();
                this.mFmPresetList = arrayList2;
                arrayList2.add(new SongListEntity(CommUtil.getStrByResId(context, "_209_preset_station")));
                while (i < 12) {
                    int[] iArr2 = this.mCanBusInfoInt;
                    int i3 = i * 2;
                    i++;
                    this.mFmPresetList.add(new SongListEntity("\t" + (this.mDecimalFormat00.format(i) + "#") + "\t" + ((iArr2[(i3 + 2) + 2] | (iArr2[(i3 + 1) + 2] << 8)) / 10.0f) + " MHz"));
                }
                if (boolBit7 != this.mIsOriginalRadioBandAm) {
                    return;
                } else {
                    GeneralOriginalCarDeviceData.songList = getFmList();
                }
            }
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void set0x07RadioStorageData(Context context) {
        if ("original_status_radio".equals(this.mOriginalStatus)) {
            if (isDataChange(this.mCanBusInfoInt)) {
                boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
                String str = (this.mCanBusInfoInt[4] + 1) + "#";
                if (str.length() == 2) {
                    str = "0" + str;
                }
                int i = 0;
                if (boolBit7) {
                    int[] iArr = this.mCanBusInfoInt;
                    this.mAmStorageArray[iArr[4] + 1] = new SongListEntity("\t" + str + "\t" + (iArr[6] | (iArr[5] << 8)) + " KHz");
                    this.mAmStorageList = new ArrayList();
                    SongListEntity[] songListEntityArr = this.mAmStorageArray;
                    int length = songListEntityArr.length;
                    while (i < length) {
                        SongListEntity songListEntity = songListEntityArr[i];
                        if (songListEntity != null && !TextUtils.isEmpty(songListEntity.getTitle())) {
                            this.mAmStorageList.add(songListEntity);
                        }
                        i++;
                    }
                    if (boolBit7 != this.mIsOriginalRadioBandAm) {
                        return;
                    } else {
                        GeneralOriginalCarDeviceData.songList = getAmList();
                    }
                } else {
                    this.mFmStorageArray[this.mCanBusInfoInt[4] + 1] = new SongListEntity("\t" + str + "\t" + ((r6[6] | (r6[5] << 8)) / 10.0f) + " MHz");
                    this.mFmStorageList = new ArrayList();
                    SongListEntity[] songListEntityArr2 = this.mFmStorageArray;
                    int length2 = songListEntityArr2.length;
                    while (i < length2) {
                        SongListEntity songListEntity2 = songListEntityArr2[i];
                        if (songListEntity2 != null && !TextUtils.isEmpty(songListEntity2.getTitle())) {
                            this.mFmStorageList.add(songListEntity2);
                        }
                        i++;
                    }
                    if (boolBit7 != this.mIsOriginalRadioBandAm) {
                        return;
                    } else {
                        GeneralOriginalCarDeviceData.songList = getFmList();
                    }
                }
            }
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void set0xD1CameraData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            if (this.mFrontViewBtnStatus && !boolBit6) {
                switchFCamera(this.mContext, !isPreCameraOrAuxForeground(context));
            }
            this.mFrontViewBtnStatus = boolBit6;
            boolean z = ((1 << (SharePreUtil.getBoolValue(context, "share_157_front_camera_switch", false) ? 3 : 7)) & this.mCanBusInfoInt[3]) != 0;
            if (this.mTurnSignalStatus ^ z) {
                this.mTurnSignalStatus = z;
                if (isPreCameraOrAuxForeground(context) ^ z) {
                    switchFCamera(this.mContext, z);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.hzbhd.canbus.car._157.MsgMgr$3] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.hzbhd.canbus.car._157.MsgMgr$4] */
    private void set0xD3SourceSwitch(final Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                realKeyClick(context, 52);
            }
            switch (i) {
                case 32:
                    realKeyClick(context, 129);
                    new CountDownTimer(500L, 100L) { // from class: com.hzbhd.canbus.car._157.MsgMgr.3
                        @Override // android.os.CountDownTimer
                        public void onTick(long j) {
                        }

                        @Override // android.os.CountDownTimer
                        public void onFinish() {
                            MsgMgr.this.realKeyClick(context, 77);
                        }
                    }.start();
                    break;
                case 33:
                    realKeyClick(context, 129);
                    new CountDownTimer(500L, 100L) { // from class: com.hzbhd.canbus.car._157.MsgMgr.4
                        @Override // android.os.CountDownTimer
                        public void onTick(long j) {
                        }

                        @Override // android.os.CountDownTimer
                        public void onFinish() {
                            MsgMgr.this.realKeyClick(context, 76);
                        }
                    }.start();
                    break;
                case 34:
                    realKeyClick(context, 59);
                    break;
                case 35:
                    realKeyClick(context, 140);
                    break;
                case 36:
                    realKeyClick(context, 141);
                    break;
                case 37:
                    realKeyClick(context, 59);
                    break;
            }
        }
    }

    private void set0x50PanoramicData(Context context) {
        if (isShowOriginalRadio()) {
            return;
        }
        if (this.mCanBusInfoInt[2] == 3) {
            enterAuxIn2();
        } else {
            exitAuxIn2();
        }
    }

    private void set0x52Data() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralHybirdData.isEngineDriveMotor = boolBit7;
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralHybirdData.isBatteryDriveMotor = boolBit5;
        GeneralHybirdData.isMotorDriveWheel = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralHybirdData.isWheelDriveMotor = boolBit4;
        GeneralHybirdData.isMotorDriveBattery = boolBit7 || boolBit4;
        updateHybirdActivity(null);
    }

    private void set0x77Data() {
        int[] iArr;
        int i = this.mCanBusInfoInt[3];
        if (i != 0) {
            return;
        }
        if (i == 0) {
            MyLog.temporaryTracking("ASCII码");
            int i2 = 5;
            String str = "%";
            while (true) {
                iArr = this.mCanBusInfoInt;
                if (i2 >= iArr.length) {
                    break;
                }
                if (i2 != 5) {
                    str = str + "%";
                }
                str = str + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i2]));
                i2++;
            }
            int i3 = iArr[2];
            if (i3 == 0) {
                this.contentZero = getUTF8Result(str);
            } else if (i3 == 1) {
                this.contentOne = getUTF8Result(str);
            } else if (i3 == 2) {
                this.contentTow = getUTF8Result(str);
            } else if (i3 == 3) {
                this.contentThree = getUTF8Result(str);
            }
        }
        showDialog(getActivity(), this.contentZero, this.contentOne, this.contentTow, this.contentThree);
    }

    public void showDialog(Context context, String str, String str2, String str3, String str4) {
        if (this.view == null) {
            this.view = LayoutInflater.from(context).inflate(R.layout._157_menu_info, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(context).setView(this.view).create();
        }
        if (this.content0 == null) {
            this.content0 = (TextView) this.view.findViewById(R.id.content0);
        }
        if (this.content1 == null) {
            this.content1 = (TextView) this.view.findViewById(R.id.content1);
        }
        if (this.content2 == null) {
            this.content2 = (TextView) this.view.findViewById(R.id.content2);
        }
        if (this.content3 == null) {
            this.content3 = (TextView) this.view.findViewById(R.id.content3);
        }
        if (this.EXIT == null) {
            this.EXIT = (Button) this.view.findViewById(R.id.EXIT);
        }
        this.content0.setText(str);
        this.content1.setText(str2);
        this.content2.setText(str3);
        this.content3.setText(str4);
        this.EXIT.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._157.MsgMgr.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MsgMgr.this.dialog.dismiss();
            }
        });
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.show();
    }

    private void set0x78OriginalVolumeData(Context context) {
        GeneralOriginalCarDeviceData.discStatus = context.getString(R.string.volume) + "\t" + this.mCanBusInfoInt[3];
        updateOriginalCarDeviceActivity(null);
    }

    private void set0x7ASoundtrackData(Context context) {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 0) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "amplifier_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "amplifier_setting", "_157_sheng"), this.mContext.getString(R.string._157_sheng1)).setValueStr(true));
            updateGeneralSettingData(arrayList);
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "amplifier_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "amplifier_setting", "_157_sheng"), this.mContext.getString(R.string._157_sheng2)).setValueStr(true));
            updateGeneralSettingData(arrayList);
        }
        updateSettingActivity(null);
        if (isDataChange(this.mCanBusInfoInt)) {
            if (this.mCanBusInfoInt[2] == 0) {
                enterAuxIn2();
            } else {
                exitAuxIn2();
            }
        }
    }

    private void set0x7BOriginalStatusData(Context context) {
        char c;
        if (isShowOriginalRadio()) {
            switch (this.mCanBusInfoInt[2]) {
                case 0:
                    GeneralOriginalCarDeviceData.cdStatus = "";
                    this.mOriginalStatus = "original_status_null";
                    setNullPage();
                    exitAuxIn2();
                    break;
                case 1:
                case 2:
                    GeneralOriginalCarDeviceData.songList = getFmList();
                    GeneralOriginalCarDeviceData.cdStatus = "RADIO";
                    this.mOriginalStatus = "original_status_radio";
                    setRadioPage();
                    enterAuxIn2(context, Constant.OriginalDeviceActivity);
                    break;
                case 3:
                    GeneralOriginalCarDeviceData.songList = getAmList();
                    GeneralOriginalCarDeviceData.cdStatus = "RADIO";
                    this.mOriginalStatus = "original_status_radio";
                    setRadioPage();
                    enterAuxIn2(context, Constant.OriginalDeviceActivity);
                    break;
                case 4:
                    if (!"original_status_cd".equals(this.mOriginalStatus)) {
                        this.mOriginalStatus = "original_status_cd";
                        setCdPage();
                        this.disc_status_on = CommUtil.getStrByResId(context, "_157_disc_status_on");
                        this.disc_status_off = CommUtil.getStrByResId(context, "_157_disc_status_off");
                    }
                    GeneralOriginalCarDeviceData.cdStatus = "CD";
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new OriginalCarDeviceUpdateEntity(0, ": " + this.mCanBusInfoInt[3]));
                    arrayList.add(new OriginalCarDeviceUpdateEntity(1, ": " + this.mCanBusInfoInt[4]));
                    arrayList.add(new OriginalCarDeviceUpdateEntity(2, ": " + getCdStatus(context, this.mCanBusInfoInt[5])));
                    arrayList.add(new OriginalCarDeviceUpdateEntity(3, ": " + this.mDecimalFormat00.format(this.mCanBusInfoInt[7]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[8])));
                    if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1) == 1) {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(4, ": " + CommUtil.getStrByResId(context, "_157_disc_status_on")));
                    } else {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(4, ": " + CommUtil.getStrByResId(context, "_157_disc_status_off")));
                    }
                    if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1) == 1) {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(5, ": " + CommUtil.getStrByResId(context, "_157_disc_status_on")));
                    } else {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(5, ": " + CommUtil.getStrByResId(context, "_157_disc_status_off")));
                    }
                    if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1) == 1) {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(6, ": " + CommUtil.getStrByResId(context, "_157_disc_status_on")));
                    } else {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(6, ": " + CommUtil.getStrByResId(context, "_157_disc_status_off")));
                    }
                    if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1) == 1) {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(7, ": " + CommUtil.getStrByResId(context, "_157_disc_status_on")));
                    } else {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(7, ": " + CommUtil.getStrByResId(context, "_157_disc_status_off")));
                    }
                    if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1) == 1) {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(8, ": " + CommUtil.getStrByResId(context, "_157_disc_status_on")));
                    } else {
                        arrayList.add(new OriginalCarDeviceUpdateEntity(8, ": " + CommUtil.getStrByResId(context, "_157_disc_status_off")));
                    }
                    if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1) == 1) {
                        c = '\t';
                        arrayList.add(new OriginalCarDeviceUpdateEntity(9, ": " + CommUtil.getStrByResId(context, "_157_disc_status_on")));
                    } else {
                        c = '\t';
                        arrayList.add(new OriginalCarDeviceUpdateEntity(9, ": " + CommUtil.getStrByResId(context, "_157_disc_status_off")));
                    }
                    int i = this.mCanBusInfoInt[c];
                    if (i == 0) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_cd_status_playing");
                    } else if (i == 1) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_cd_status_reading");
                    } else if (i == 2) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_cd_status_changing");
                    } else if (i == 3) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_cd_status_eject");
                    }
                    GeneralOriginalCarDeviceData.mList = arrayList;
                    updateOriginalCarDeviceActivity(null);
                    enterAuxIn2(context, Constant.OriginalDeviceActivity);
                    break;
                case 5:
                    GeneralOriginalCarDeviceData.cdStatus = "AUX";
                    this.mOriginalStatus = "original_status_aux";
                    setNullPage();
                    exitAuxIn2();
                    break;
                case 6:
                    if (!"original_status_usb".equals(this.mOriginalStatus)) {
                        this.mOriginalStatus = "original_status_usb";
                        setUsbPage();
                    }
                    GeneralOriginalCarDeviceData.cdStatus = "USB";
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new OriginalCarDeviceUpdateEntity(0, ": " + this.mCanBusInfoInt[3]));
                    arrayList2.add(new OriginalCarDeviceUpdateEntity(1, ": " + this.mCanBusInfoInt[4]));
                    arrayList2.add(new OriginalCarDeviceUpdateEntity(2, ": " + getCdStatus(context, this.mCanBusInfoInt[5])));
                    arrayList2.add(new OriginalCarDeviceUpdateEntity(3, ": " + this.mDecimalFormat00.format(this.mCanBusInfoInt[7]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[8])));
                    int i2 = this.mCanBusInfoInt[9];
                    if (i2 == 0) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_usb_status_playing");
                    } else if (i2 == 1) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_usb_status_connected");
                    } else if (i2 == 2) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_usb_status_disconnected");
                    } else if (i2 == 3) {
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context, "_157_usb_status_reading");
                    }
                    GeneralOriginalCarDeviceData.mList = arrayList2;
                    updateOriginalCarDeviceActivity(null);
                    enterAuxIn2(context, Constant.OriginalDeviceActivity);
                    break;
            }
        }
    }

    private String getCdStatus(Context context, int i) {
        if (i == 0) {
            return CommUtil.getStrByResId(context, "_288_divice_cd_mode1");
        }
        if (i == 1) {
            return CommUtil.getStrByResId(context, "_288_divice_cd_mode5");
        }
        if (i != 2) {
            return i != 3 ? "" : CommUtil.getStrByResId(context, "_157_scan_status");
        }
        return CommUtil.getStrByResId(context, "_288_divice_cd_mode2");
    }

    private void setRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.ST, OriginalBtnAction.SCAN, OriginalBtnAction.REFRESH}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", "band", "down", "right", OriginalBtnAction.NEXT_DISC}, true);
    }

    private void setCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_number_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", LcdInfoShare.MediaInfo.current_track, ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_cd_state", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "current_play_time", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_1", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_2", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_3", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_4", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_5", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "disc_6", ""));
        changeOriginalDevicePage(arrayList, null, null, false);
    }

    private void setUsbPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_number_folder", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", LcdInfoShare.MediaInfo.current_track, ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "current_usb_play_state", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "current_play_time", ""));
        changeOriginalDevicePage(arrayList, null, null, false);
    }

    private void setNullPage() {
        changeOriginalDevicePage(new ArrayList(), null, null, false);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, String[] strArr2, boolean z) {
        this.mOriginalCarDevicePageUiSet.setItems(list);
        this.mOriginalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        this.mOriginalCarDevicePageUiSet.setRowBottomBtnAction(strArr2);
        this.mOriginalCarDevicePageUiSet.setHaveSongList(z);
        GeneralOriginalCarDeviceData.mList = null;
        updateOriginalDeviceWithInit();
    }

    private void updateOriginalDeviceWithInit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendNormalMessage(new byte[]{22, -126, 0});
            sendNormalMessage(new byte[]{22, -64, 0, 48});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -126, 1});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -64, 1, 1});
        int bandData = getBandData(str);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -62, (byte) bandData, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -126, 2});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -64, 2, 33});
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        if (i3 > 255) {
            i3 = 255;
        }
        int[] time = getTime(i);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -61, (byte) i3, (byte) i4, 0, 0, (byte) time[1], (byte) time[2]});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), new byte[]{22, -64, 3, 34});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendNormalMessage(new byte[]{22, -126, 5});
        sendNormalMessage(new byte[]{22, -64, 5, 64});
        sendNormalMessage(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, bArr), 35, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendNormalMessage(new byte[]{22, -126, 5});
        sendNormalMessage(new byte[]{22, -64, 5, 64});
        sendNormalMessage(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, bArr), 35, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 1}, 35, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -126, 3});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, 48});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = 9;
        if (b == 9) {
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -126, -1});
        } else {
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -126, 4});
            i4 = 8;
        }
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -64, (byte) i4, 19});
        int i5 = i2;
        if (i5 > 99) {
            i5 = 99;
        }
        int i6 = (b7 * 256) + i;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -61, (byte) i5, b2, (byte) (i6 <= 99 ? i6 : 99), 0, b4, b5});
        this.mMusicId3s[0].info = str4;
        this.mMusicId3s[1].info = str5;
        this.mMusicId3s[2].info = str6;
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mMusicId3s[0].info = " ";
        this.mMusicId3s[1].info = " ";
        this.mMusicId3s[2].info = " ";
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        int i5 = 9;
        if (b != 9) {
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -126, 4});
            i5 = 8;
        }
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, (byte) i5, 19});
        int i6 = i2;
        if (i6 > 99) {
            i6 = 99;
        }
        int i7 = (b6 * 256) + i;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -61, (byte) i6, b2, (byte) (i7 <= 99 ? i7 : 99), 0, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), new byte[]{22, -64, 10, 34});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -126, 6});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -64, 11, 17});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        this.mMusicId3s[0].info = str;
        this.mMusicId3s[1].info = str3;
        this.mMusicId3s[2].info = str2;
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        this.mMusicId3s[0].info = " ";
        this.mMusicId3s[1].info = " ";
        this.mMusicId3s[2].info = " ";
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        if (!z) {
            i8 = i5 | 128;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte) i8, (byte) i6, (byte) i7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) (i | (z ? 128 : 0))});
    }

    private boolean equal(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void resolve0x17KeyMenu(Context context) {
        if (equal(Integer.valueOf(this.mDifferentId), 17, 18)) {
            realKeyLongClick1(context, 3);
        } else {
            realKeyLongClick1(context, 52);
        }
    }

    private void switchFrontCamera() {
        switchFCamera(this.mContext, !this.mIsFrontCameraOpen);
        this.mIsFrontCameraOpen = !this.mIsFrontCameraOpen;
    }

    private void adjustBrightness() {
        int screenBacklight = MediaShareData.Screen.INSTANCE.getScreenBacklight() + 1;
        int i = screenBacklight != 6 ? screenBacklight : 1;
        Log.i("_157_MsgMgr", "adjustBrightness: " + i);
        setBacklightLevel(i);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirDataInt, this.mCanBusInfoInt)) {
            return false;
        }
        this.mAirDataInt = this.mCanBusInfoInt;
        return true;
    }

    private int getAirWhat() {
        int i;
        byte[] bArr = this.mAirData;
        byte b = bArr[6];
        int[] iArr = {bArr[2], bArr[3], bArr[4], bArr[5], b & 240, bArr[11]};
        int[] iArr2 = {b & 4, bArr[8], bArr[9], bArr[10]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr)) {
            i = 1001;
        } else {
            if (Arrays.equals(this.mAirRearDataNow, iArr2)) {
                return 0;
            }
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr, 6);
        this.mAirRearDataNow = Arrays.copyOf(iArr2, 4);
        return i;
    }

    private String resolveAirTemperature(Context context, int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return i + getTempUnitF(context);
        }
        return (i / 2.0f) + getTempUnitC(context);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
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

    private SettingUpdateEntity checkEntity(SettingUpdateEntity settingUpdateEntity) {
        if (settingUpdateEntity.getLeftListIndex() == -1 || settingUpdateEntity.getRightListIndex() == -1) {
            return null;
        }
        return settingUpdateEntity;
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj);
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
    }

    private boolean is0x33IndexOneDataChange() {
        if (Arrays.equals(this.m0x33DataIndexOne, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x33DataIndexOne = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x33IndexTwoDataChange() {
        if (Arrays.equals(this.m0x33DataIndexTwo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x33DataIndexTwo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String getData(int[] iArr, float f, String str) {
        int iPow = 0;
        for (int i = 0; i < iArr.length; i++) {
            iPow = (int) (iPow + (iArr[i] * Math.pow(2.0d, i * 8)));
        }
        int i2 = 1;
        for (int i3 = 0; i3 < (iArr.length * 8) - 1; i3++) {
            i2 = (i2 << 1) + 1;
        }
        return iPow == i2 ? "- - -" : this.mDecimalFormat0p0.format(iPow * f) + str;
    }

    private String getOpenClose(Context context, boolean z) {
        return CommUtil.getStrByResId(context, z ? "open" : "close");
    }

    private boolean isPreCameraOrAuxForeground(Context context) {
        return SystemUtil.isForeground(context, Constant.FCameraActivity.getClassName());
    }

    private List<SongListEntity> getFmList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mFmPresetList);
        arrayList.addAll(this.mFmStorageList);
        return arrayList;
    }

    private List<SongListEntity> getAmList() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mAmPresetList);
        arrayList.addAll(this.mAmStorageList);
        return arrayList;
    }

    boolean isShowOriginalRadio() {
        return equal(Integer.valueOf(this.mDifferentId), 19, 49, 80);
    }

    int getOriginalRadioPresetSize() {
        return this.mIsOriginalRadioBandAm ? 6 : 12;
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private int getBandData(String str) {
        if ("FM1".equals(str)) {
            return 1;
        }
        if ("FM2".equals(str)) {
            return 2;
        }
        if ("FM3".equals(str)) {
            return 0;
        }
        return ("AM1".equals(str) || "AM2".equals(str)) ? 16 : 0;
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

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private class ID3 {
        private int command;
        private String info;
        private String record;

        private ID3(int i) {
            this.command = i;
            this.info = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            this.record = this.info;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordId3Info() {
            this.record = this.info;
        }
    }

    private void initID3() {
        this.mMusicId3s = new ID3[]{new ID3(2), new ID3(3), new ID3(4)};
    }

    private void reportID3Info(ID3[] id3Arr) {
        for (ID3 id3 : id3Arr) {
            if (id3.isId3Change()) {
                for (ID3 id32 : id3Arr) {
                    id32.recordId3Info();
                    reportID3InfoFinal(id32.command, id32.info);
                }
                return;
            }
        }
    }

    private void reportID3InfoFinal(int i, String str) {
        try {
            sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, (byte) i}, DataHandleUtils.exceptBOMHead(str.getBytes(this.mCharsetName))), 35), i * 80);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message message = new Message();
        message.what = 2;
        message.obj = obj;
        this.mHandler.sendMessageDelayed(message, j);
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message message = new Message();
        message.what = 1;
        message.arg1 = i;
        message.obj = obj;
        this.mHandler.sendMessageDelayed(message, j);
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

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
