package com.hzbhd.canbus.car._61;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.core.view.MotionEventCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int VEHICLE_TYPE_17_REGAL = 22;
    static final int VEHICLE_TYPE_ASTRA = 7;
    static final int VEHICLE_TYPE_CRUZ = 1;
    static final int VEHICLE_TYPE_ENCORE_LOW = 8;
    static final int VEHICLE_TYPE_EXCELLE = 2;
    static final int VEHICLE_TYPE_GL8_HIGH = 24;
    static final int VEHICLE_TYPE_GMC = 25;
    static final int VEHICLE_TYPE_GM_SERIES = 0;
    static final int VEHICLE_TYPE_MALIBU_XL = 20;
    static final int VEHICLE_TYPE_MOKKA = 6;
    static final int VEHICLE_TYPE_NEW_LACROSSE = 5;
    static final int VEHICLE_TYPE_NEW_REGAL = 4;
    static final int VEHICLE_TYPE_ORLANDO = 3;
    static final int VEHICLE_TYPE_TAHOE = 21;
    static final int VEHICLE_TYPE_VERANO = 23;
    private String[] m0x05ItemTitleArray;
    private String[] m0x06ItemTitleArray;
    private String[] m0x0AItemTitleArray;
    private SparseArray<String[]> m0x1AItemTitleArray;
    private String[] m0x44ItemTitleArray;
    private String[] m0xD2ItemTitleArray;
    private int mAcType;
    private boolean mAirTmeperatureUnit;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private SparseArray<int[]> mCanbusDataArray;
    private Context mContext;
    private DecimalFormat mDecimalFormat0p0;
    private int mDifferentId;
    private int mDiscExist;
    private int mEachId;
    private boolean mFrontStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int mOutDoorTemperature;
    private SparseArray<SparseIntArray> mPanelKeyArray;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, Integer> mSettingItemIndeHashMap;
    private SparseArray<List<SettingRightItem>> mSettingRightItemArray;
    private String[] mTireAlarm;
    private UiMgr mUiMgr;
    private UiMgrInterface mUiMgrInterface;
    private String TAG = "ljqdebug";
    private final int DATA_TYPE = 1;
    private final int INVAILE_VALUE = -1;
    private final String CHARSET_GB2312 = "GB2312";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.mContext = context;
        this.mDifferentId = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        GeneralTireData.isHaveSpareTire = false;
        RadarInfoUtil.mMinIsClose = true;
        initSparseArray();
        initData(context);
        initSettingsItem(getUiMgrInterface().getSettingUiSet(context));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OnStarActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        try {
            setCanInfo();
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setCanInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[1];
        if (i == 1) {
            setWheelKey0x01();
            return;
        }
        if (i == 2) {
            setPanelkey0x02(iArr);
            return;
        }
        if (i == 3) {
            setAirData0x03();
            return;
        }
        if (i == 26) {
            setSettingEable0x1A();
            return;
        }
        if (i == 31) {
            setWirelessInfo0x1F();
            return;
        }
        if (i == 38) {
            setTrackData0x26();
            return;
        }
        if (i == 210) {
            setCompassData0xD2();
            return;
        }
        if (i == 65) {
            setWirelessPoint0x41();
            return;
        }
        if (i == 66) {
            setWirelessPassword0x42();
            return;
        }
        if (i == 68) {
            setVehicleAirSetting0x44();
            return;
        }
        if (i != 69) {
            switch (i) {
                case 5:
                    setAirSettings0x05();
                    break;
                case 6:
                    setVehicleSettings0x06();
                    break;
                case 7:
                    setRadarSettings0x07();
                    break;
                case 8:
                    setOnStarPhoneInfo0x08();
                    break;
                case 9:
                    setOnStarStatus0x09();
                    break;
                case 10:
                    setVehicleSettings0x0A();
                    break;
                case 11:
                    setVehicleSpeed0x0B();
                    break;
                case 12:
                    setLanguageData0x0C();
                    break;
                case 13:
                    setWarnVolume0x0D();
                    break;
                default:
                    switch (i) {
                        case 34:
                            setRearRadarData0x22();
                            break;
                        case 35:
                            setFrontRadarData0x23();
                            break;
                        case 36:
                            setDoorData0x24();
                            break;
                        default:
                            switch (i) {
                                case 48:
                                    setVersionInfo0x30();
                                    break;
                                case 49:
                                    setVehicleInfo0x31();
                                    break;
                                case 50:
                                    setVehicleInfo0x32();
                                    break;
                                case 51:
                                    setTirePressure0x33();
                                    break;
                            }
                    }
            }
            return;
        }
        setRearAirData0x45();
    }

    private void setWheelKey0x01() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                wheelKeyClick(0);
                break;
            case 1:
                wheelKeyClick(7);
                break;
            case 2:
                wheelKeyClick(8);
                break;
            case 3:
                wheelKeyClick(45);
                break;
            case 4:
                wheelKeyClick(46);
                break;
            case 5:
                wheelKeyClick(2);
                break;
            case 6:
                wheelKeyClick(HotKeyConstant.K_VOICE_PICKUP);
                break;
            case 7:
                wheelKeyClick(184);
                break;
            case 8:
                wheelKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                wheelKeyClick(184);
                break;
        }
    }

    private void setPanelkey0x02(int[] iArr) {
        if (getCurrentEachCanId() == 9) {
            setYingLangPanel(iArr);
            return;
        }
        if (getCurrentEachCanId() == 10) {
            setKeLuZi(iArr);
            return;
        }
        if (getCurrentEachCanId() == 11) {
            setAoLanDuo(iArr);
            return;
        }
        if (getCurrentEachCanId() == 12) {
            setNewJunWei(iArr);
            return;
        }
        if (getCurrentEachCanId() == 13) {
            setNewJunYun(iArr);
            return;
        }
        if (getCurrentEachCanId() == 14) {
            setLeFeng(iArr);
            return;
        }
        int i = this.mPanelKeyArray.get(this.mEachId).get(iArr[2]);
        Log.i(this.TAG, "setPanelkey0x02: diff:" + this.mEachId + ", key:" + i);
        int i2 = iArr[2];
        if (i2 != 52 && i2 != 53) {
            switch (i2) {
                case 23:
                case 24:
                    panelKeyKnobVol(i);
                    break;
                case 25:
                case 26:
                    break;
                default:
                    wheelKeyClick(i);
                    break;
            }
        }
        panelKeyKnobSel(i);
    }

    private void setLeFeng(int[] iArr) {
        int i = iArr[3];
        if (i == 2) {
            return;
        }
        int i2 = iArr[2];
        if (i2 == 0) {
            realKeyLongClick1(this.mContext, 0, i);
            return;
        }
        if (i2 == 1) {
            realKeyLongClick1(this.mContext, 1, i);
            return;
        }
        if (i2 == 6) {
            realKeyLongClick1(this.mContext, 50, i);
            return;
        }
        if (i2 == 7) {
            realKeyLongClick1(this.mContext, 62, i);
            return;
        }
        if (i2 == 28) {
            realKeyLongClick1(this.mContext, 45, i);
            return;
        }
        if (i2 == 29) {
            realKeyLongClick1(this.mContext, 46, i);
            return;
        }
        if (i2 == 80) {
            realKeyLongClick1(this.mContext, 52, i);
            return;
        }
        if (i2 == 83) {
            realKeyLongClick1(this.mContext, 151, i);
            return;
        }
        if (i2 != 84) {
            switch (i2) {
                case 23:
                    realKeyClick4(this.mContext, 7);
                    break;
                case 24:
                    realKeyClick4(this.mContext, 8);
                    break;
                case 25:
                    realKeyClick4(this.mContext, 47);
                    break;
                case 26:
                    realKeyClick4(this.mContext, 48);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 2, i);
    }

    private void setNewJunYun(int[] iArr) {
        int i = iArr[3];
        if (i == 2) {
            return;
        }
        int i2 = iArr[2];
        if (i2 == 17) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_CARPLAY, i);
            return;
        }
        if (i2 == 18) {
            realKeyLongClick1(this.mContext, 2, i);
            return;
        }
        if (i2 == 52) {
            realKeyClick4(this.mContext, 45);
            return;
        }
        if (i2 != 53) {
            switch (i2) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, i);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 21, i);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 37, i);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 36, i);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 35, i);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 1, i);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 58, i);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 62, i);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 33, i);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 34, i);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 20, i);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 128, i);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 50, i);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_TOUCH_ACTION, i);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 31, i);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 31, i);
                    break;
                default:
                    switch (i2) {
                        case 20:
                            realKeyLongClick1(this.mContext, 62, i);
                            break;
                        case 21:
                            realKeyLongClick1(this.mContext, 184, i);
                            break;
                        case 22:
                            realKeyLongClick1(this.mContext, 38, i);
                            break;
                        case 23:
                            realKeyClick4(this.mContext, 7);
                            break;
                        case 24:
                            realKeyClick4(this.mContext, 8);
                            break;
                        case 25:
                            realKeyClick4(this.mContext, 47);
                            break;
                        case 26:
                            realKeyClick4(this.mContext, 48);
                            break;
                    }
            }
            return;
        }
        realKeyClick4(this.mContext, 46);
    }

    private void setNewJunWei(int[] iArr) {
        int i = iArr[3];
        if (i == 2) {
        }
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, i);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 1, i);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 21, i);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 20, i);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 58, i);
                break;
            case 5:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_TOUCH_ACTION, i);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 50, i);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 62, i);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 141, i);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 3, i);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 33, i);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 34, i);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 35, i);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 36, i);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 37, i);
                break;
            case 15:
                realKeyLongClick1(this.mContext, 38, i);
                break;
            case 16:
                realKeyLongClick1(this.mContext, 128, i);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 31, i);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 39, i);
                break;
            case 19:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_TIME, i);
                break;
            case 20:
                realKeyLongClick1(this.mContext, 76, i);
                break;
            case 21:
                realKeyLongClick1(this.mContext, 75, i);
                break;
            case 22:
                realKeyLongClick1(this.mContext, 49, i);
                break;
            case 23:
                realKeyClick4(this.mContext, 7);
                break;
            case 24:
                realKeyClick4(this.mContext, 8);
                break;
            case 25:
                realKeyClick4(this.mContext, 47);
                break;
            case 26:
                realKeyClick4(this.mContext, 48);
                break;
        }
    }

    private void setAoLanDuo(int[] iArr) {
        int i = iArr[3];
        if (i == 2) {
        }
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, i);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 1, i);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 21, i);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 20, i);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 58, i);
                break;
            case 5:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_TOUCH_ACTION, i);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 50, i);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 62, i);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 141, i);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 184, i);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 33, i);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 34, i);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 35, i);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 36, i);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 37, i);
                break;
            case 15:
                realKeyLongClick1(this.mContext, 38, i);
                break;
            case 16:
                realKeyLongClick1(this.mContext, 128, i);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 31, i);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 39, i);
                break;
            case 19:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_TIME, i);
                break;
            case 20:
                realKeyLongClick1(this.mContext, 0, i);
                break;
            case 21:
                realKeyLongClick1(this.mContext, 75, i);
                break;
            case 22:
                realKeyLongClick1(this.mContext, 49, i);
                break;
            case 23:
                realKeyClick4(this.mContext, 7);
                break;
            case 24:
                realKeyClick4(this.mContext, 8);
                break;
            case 25:
                realKeyClick4(this.mContext, 47);
                break;
            case 26:
                realKeyClick4(this.mContext, 48);
                break;
            case 27:
                realKeyLongClick1(this.mContext, 95, i);
                break;
            case 28:
                realKeyLongClick1(this.mContext, 45, i);
                break;
            case 29:
                realKeyLongClick1(this.mContext, 46, i);
                break;
        }
    }

    private void setKeLuZi(int[] iArr) {
        int i = iArr[3];
        if (i == 2) {
        }
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, i);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 21, i);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 21, i);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 20, i);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 58, i);
                break;
            case 5:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_TOUCH_ACTION, i);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 50, i);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 62, i);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 141, i);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 184, i);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 33, i);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 34, i);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 35, i);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 36, i);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 37, i);
                break;
            case 15:
                realKeyLongClick1(this.mContext, 38, i);
                break;
            case 16:
                realKeyLongClick1(this.mContext, 89, i);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 31, i);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 128, i);
                break;
            case 19:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_TIME, i);
                break;
            case 20:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SHOW_ALLAPP_VIEW, i);
                break;
            case 21:
                realKeyLongClick1(this.mContext, 75, i);
                break;
            case 22:
                realKeyLongClick1(this.mContext, 49, i);
                break;
            case 23:
                realKeyClick4(this.mContext, 7);
                break;
            case 24:
                realKeyClick4(this.mContext, 8);
                break;
            case 25:
                realKeyClick4(this.mContext, 47);
                break;
            case 26:
                realKeyClick4(this.mContext, 48);
                break;
            case 27:
                realKeyLongClick1(this.mContext, 95, i);
                break;
            case 28:
                realKeyLongClick1(this.mContext, 45, i);
                break;
            case 29:
                realKeyLongClick1(this.mContext, 46, i);
                break;
        }
    }

    private void setYingLangPanel(int[] iArr) {
        int i = iArr[3];
        if (i == 2) {
            return;
        }
        int i2 = iArr[2];
        if (i2 == 0) {
            realKeyLongClick1(this.mContext, 0, i);
            return;
        }
        if (i2 != 64) {
            switch (i2) {
                case 2:
                    realKeyLongClick1(this.mContext, 1, i);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 21, i);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 20, i);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 58, i);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 50, i);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 62, i);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_MEDIA, i);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 184, i);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 33, i);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 34, i);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 35, i);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 36, i);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 37, i);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 38, i);
                    break;
                case 16:
                    realKeyLongClick1(this.mContext, 4, i);
                    break;
                case 17:
                    realKeyLongClick1(this.mContext, 31, i);
                    break;
                case 18:
                    realKeyLongClick1(this.mContext, 128, i);
                    break;
                case 19:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_TIME, i);
                    break;
                case 20:
                    realKeyLongClick1(this.mContext, 76, i);
                    break;
                case 21:
                    realKeyLongClick1(this.mContext, 75, i);
                    break;
                case 22:
                    realKeyLongClick1(this.mContext, 49, i);
                    break;
                case 23:
                    realKeyClick4(this.mContext, 7);
                    break;
                case 24:
                    realKeyClick4(this.mContext, 8);
                    break;
                case 25:
                    realKeyClick4(this.mContext, 45);
                    break;
                case 26:
                    realKeyClick4(this.mContext, 46);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 141, i);
    }

    private void setAirData0x03() {
        if (isDataChange(this.mCanBusInfoInt)) {
            setSettingData(new String[]{"_279_temperature_unit", "_61_vehicle_type_setup"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))});
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            setOutDoorTemperature();
            byte[] bArr = this.mCanBusInfoByte;
            bArr[7] = 0;
            bArr[8] = (byte) (bArr[8] & ByteCompanionObject.MAX_VALUE);
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = getAirAcStatus();
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3) | (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1) << 3);
            GeneralAirData.eco = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            boolean z = intFromByteWithBit == 1;
            GeneralAirData.front_left_auto_wind = z;
            GeneralAirData.front_right_auto_wind = z;
            GeneralAirData.front_defog = intFromByteWithBit == 2;
            boolean zIsBlowModeMatch = isBlowModeMatch(intFromByteWithBit, 3, 4, 8, 9);
            GeneralAirData.front_left_blow_foot = zIsBlowModeMatch;
            GeneralAirData.front_right_blow_foot = zIsBlowModeMatch;
            boolean zIsBlowModeMatch2 = isBlowModeMatch(intFromByteWithBit, 4, 5, 6, 9);
            GeneralAirData.front_left_blow_head = zIsBlowModeMatch2;
            GeneralAirData.front_right_blow_head = zIsBlowModeMatch2;
            boolean zIsBlowModeMatch3 = isBlowModeMatch(intFromByteWithBit, 6, 7, 8, 9);
            GeneralAirData.front_left_blow_window = zIsBlowModeMatch3;
            GeneralAirData.front_right_blow_window = zIsBlowModeMatch3;
            GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
            GeneralAirData.ac_auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]);
            GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setAirSettings0x05() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
            if (intFromByteWithBit == 3) {
                intFromByteWithBit = 2;
            }
            setSettingData(this.m0x05ItemTitleArray, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)), Integer.valueOf(intFromByteWithBit), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))});
        }
    }

    private void setVehicleSettings0x06() {
        if (isDataChange(this.mCanBusInfoInt)) {
            setSettingData(this.m0x06ItemTitleArray, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))});
        }
    }

    private void setRadarSettings0x07() {
        if (isDataChange(this.mCanBusInfoInt)) {
            setSettingData(new String[]{"_143_0x76_d0_b6", "_61_radar_switch"}, new Object[]{Integer.valueOf(this.mCanBusInfoInt[2]), Integer.valueOf(this.mCanBusInfoInt[2])});
        }
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setOnStarStatus0x09() {
        GeneralOnStartData.mOnStarStatus = this.mCanBusInfoInt[2];
        updateOnStarActivity(1001);
    }

    private void setOnStarPhoneInfo0x08() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralOnStartData.mOnStarPhoneNum = DataHandleUtils.bcd2Str(Arrays.copyOfRange(bArr, 2, bArr.length));
        updateOnStarActivity(1001);
    }

    private void setVehicleSettings0x0A() {
        if (isDataChange(this.mCanBusInfoInt)) {
            setSettingData(this.m0x0AItemTitleArray, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))});
        }
    }

    private void setVehicleSpeed0x0B() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            int data = getData(iArr[3], iArr[2]) / 16;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, data + " km/h"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(data);
        }
    }

    private void setLanguageData0x0C() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setWarnVolume0x0D() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] leftAndRight = getLeftAndRight("warning_volume_set");
            if (leftAndRight[0] == -1 || leftAndRight[1] == -1) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], Integer.valueOf(this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[2]));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setRearRadarData0x22() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setDoorData0x24() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
            if (isDoorDataChange()) {
                updateDoorView(this.mContext);
            }
            DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        }
    }

    private void setTrackData0x26() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 7800, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setSettingEable0x1A() {
        boolean[] zArr;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            zArr = new boolean[]{DataHandleUtils.getBoolBit0(iArr[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])};
        } else if (i == 1) {
            zArr = new boolean[]{DataHandleUtils.getBoolBit1(iArr[3])};
        } else if (i == 2) {
            zArr = new boolean[]{DataHandleUtils.getBoolBit0(iArr[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])};
        } else if (i == 3) {
            zArr = new boolean[]{DataHandleUtils.getBoolBit0(iArr[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])};
        } else if (i == 4) {
            zArr = new boolean[]{DataHandleUtils.getBoolBit0(iArr[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])};
        } else if (i != 5) {
            return;
        } else {
            zArr = new boolean[]{DataHandleUtils.getBoolBit0(iArr[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])};
        }
        setSettingData(this.m0x1AItemTitleArray.get(this.mCanBusInfoInt[2]), zArr);
    }

    private void setVehicleInfo0x31() {
        if (isDataChange(this.mCanBusInfoInt)) {
            String str = (this.mCanBusInfoInt[3] / 10.0f) + CommUtil.getStrByResId(this.mContext, "unit_v");
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            String string = sb.append(getData(iArr[9], iArr[8], iArr[7], iArr[6])).append(" km/h").toString();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 1, str));
            arrayList.add(new DriverUpdateEntity(0, 2, string));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setVehicleInfo0x32() {
        if (isDataChange(this.mCanBusInfoInt)) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            String string = sb.append(getData(iArr[3], iArr[2])).append(" rpm").toString();
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            String string2 = sb2.append(getData(iArr2[5], iArr2[4]) / 10.0f).append(" l").toString();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 3, string));
            arrayList.add(new DriverUpdateEntity(0, 4, string2));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setTirePressure0x33() {
        if (isDataChange(this.mCanBusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 4; i++) {
                ArrayList arrayList2 = new ArrayList();
                StringBuilder sb = new StringBuilder();
                int[] iArr = this.mCanBusInfoInt;
                int i2 = i * 2;
                int i3 = iArr[i2 + 2];
                int i4 = 1;
                arrayList2.add(sb.append(getData(iArr[i2 + 3], i3)).append("").toString());
                int i5 = this.mCanBusInfoInt[i + 12];
                int i6 = 0;
                while (true) {
                    String[] strArr = this.mTireAlarm;
                    if (i6 >= strArr.length) {
                        break;
                    }
                    if (((1 << i6) & i5) != 0) {
                        arrayList2.add(strArr[i6]);
                    }
                    i6++;
                }
                if (arrayList2.size() == 1) {
                    arrayList2.add("");
                }
                if (i5 == 0) {
                    i4 = 0;
                }
                arrayList.add(new TireUpdateEntity(i, i4, (String[]) arrayList2.toArray(new String[0])));
            }
            GeneralTireData.dataList = arrayList;
            updateTirePressureActivity(null);
        }
    }

    private void setCompassData0xD2() {
        Object[] objArr = {CommUtil.getStrByResId(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "compass_calibrating" : "compass_calibration_finish"), Integer.toString(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)), ((this.mCanBusInfoInt[3] * 3) / 2.0f) + "°"};
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = this.m0xD2ItemTitleArray;
            if (i < strArr.length) {
                String str = strArr[i];
                int[] leftAndRight = getLeftAndRight(str);
                if (leftAndRight[0] != -1 && leftAndRight[1] != -1) {
                    Object obj = objArr[i];
                    SettingUpdateEntity settingUpdateEntity = new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], obj);
                    if ("compass_zoom".equals(str)) {
                        settingUpdateEntity = settingUpdateEntity.setProgress(((Integer) obj).intValue());
                    }
                    arrayList.add(settingUpdateEntity);
                }
                i++;
            } else {
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                return;
            }
        }
    }

    private void setWirelessInfo0x1F() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            try {
                GeneralOnStartData.mOnStarWirelessInfo = new String(Arrays.copyOfRange(bArr, 2, bArr.length), "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            updateOnStarActivity(1002);
        }
    }

    private void setWirelessPoint0x41() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            try {
                GeneralOnStartData.mOnStarWirelessPoint = new String(Arrays.copyOfRange(bArr, 2, bArr.length), "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            updateOnStarActivity(1002);
        }
    }

    private void setWirelessPassword0x42() {
        if (isDataChange(this.mCanBusInfoInt)) {
            try {
                GeneralOnStartData.mOnStarWirelessPassword = new String(getAsciiDatas(this.mCanBusInfoByte), "GB2312");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            updateOnStarActivity(1002);
        }
    }

    private void setVehicleAirSetting0x44() {
        if (isDataChange(this.mCanBusInfoInt)) {
            setSettingData(this.m0x44ItemTitleArray, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))});
        }
    }

    private void setRearAirData0x45() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.rear_temperature = resolveAirTemperatureRear(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
            GeneralAirData.rear_auto_wind_speed = GeneralAirData.rear_wind_level == 15;
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.rear_left_auto_wind = intFromByteWithBit == 1;
            GeneralAirData.rear_left_blow_head = isBlowModeMatch(intFromByteWithBit, 2, 3);
            GeneralAirData.rear_left_blow_foot = isBlowModeMatch(intFromByteWithBit, 3, 4);
            GeneralAirData.rear_right_auto_wind = GeneralAirData.rear_left_auto_wind;
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            updateAirActivity(this.mContext, 1002);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        if (this.mDiscExist != i4) {
            this.mDiscExist = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte) i4});
        }
    }

    private int getData(int... iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i += iArr[i2] << (i2 * 8);
        }
        return i;
    }

    private void initSparseArray() {
        this.mPanelKeyArray = new SparseArray<>();
        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, HotKeyConstant.K_SLEEP);
        sparseIntArray.append(2, 21);
        sparseIntArray.append(3, 20);
        sparseIntArray.append(4, 58);
        sparseIntArray.append(5, 4);
        sparseIntArray.append(6, 50);
        sparseIntArray.append(7, 129);
        sparseIntArray.append(8, 130);
        sparseIntArray.append(9, 3);
        sparseIntArray.append(10, 33);
        sparseIntArray.append(11, 34);
        sparseIntArray.append(12, 35);
        sparseIntArray.append(13, 36);
        sparseIntArray.append(14, 37);
        sparseIntArray.append(15, 38);
        sparseIntArray.append(16, 31);
        sparseIntArray.append(17, 31);
        sparseIntArray.append(18, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray.append(19, HotKeyConstant.K_TIME);
        sparseIntArray.append(20, 129);
        sparseIntArray.append(21, 75);
        sparseIntArray.append(22, 49);
        sparseIntArray.append(23, 7);
        sparseIntArray.append(24, 8);
        sparseIntArray.append(25, 46);
        sparseIntArray.append(26, 45);
        sparseIntArray.append(27, 17);
        sparseIntArray.append(28, 45);
        sparseIntArray.append(29, 46);
        sparseIntArray.append(30, 0);
        sparseIntArray.append(31, 0);
        sparseIntArray.append(64, 141);
        sparseIntArray.append(52, 48);
        sparseIntArray.append(53, 47);
        sparseIntArray.append(80, 52);
        sparseIntArray.append(81, 2);
        sparseIntArray.append(82, 94);
        sparseIntArray.append(83, 52);
        sparseIntArray.append(84, 59);
        sparseIntArray.append(85, 128);
        sparseIntArray.append(86, 128);
        sparseIntArray.append(87, 128);
        sparseIntArray.append(88, 47);
        sparseIntArray.append(89, 48);
        sparseIntArray.append(90, 45);
        sparseIntArray.append(91, 46);
        this.mPanelKeyArray.put(0, sparseIntArray);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, HotKeyConstant.K_SLEEP);
        sparseIntArray2.append(2, 21);
        sparseIntArray2.append(3, 20);
        sparseIntArray2.append(4, 58);
        sparseIntArray2.append(5, 4);
        sparseIntArray2.append(6, 50);
        sparseIntArray2.append(7, 129);
        sparseIntArray2.append(8, 130);
        sparseIntArray2.append(9, 184);
        sparseIntArray2.append(10, 33);
        sparseIntArray2.append(11, 34);
        sparseIntArray2.append(12, 35);
        sparseIntArray2.append(13, 36);
        sparseIntArray2.append(14, 37);
        sparseIntArray2.append(15, 38);
        sparseIntArray2.append(16, 31);
        sparseIntArray2.append(17, 31);
        sparseIntArray2.append(18, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray2.append(19, HotKeyConstant.K_TIME);
        sparseIntArray2.append(20, 129);
        sparseIntArray2.append(21, 75);
        sparseIntArray2.append(22, 49);
        sparseIntArray2.append(23, 7);
        sparseIntArray2.append(24, 8);
        sparseIntArray2.append(25, 48);
        sparseIntArray2.append(26, 47);
        sparseIntArray2.append(27, 17);
        sparseIntArray2.append(28, 45);
        sparseIntArray2.append(29, 46);
        sparseIntArray2.append(30, 0);
        sparseIntArray2.append(31, 0);
        sparseIntArray2.append(64, 0);
        sparseIntArray2.append(52, 0);
        sparseIntArray2.append(53, 0);
        sparseIntArray2.append(80, 0);
        sparseIntArray2.append(81, 0);
        sparseIntArray2.append(82, 0);
        sparseIntArray2.append(83, 0);
        sparseIntArray2.append(84, 0);
        sparseIntArray2.append(85, 0);
        sparseIntArray2.append(86, 0);
        sparseIntArray2.append(87, 0);
        sparseIntArray2.append(88, 0);
        sparseIntArray2.append(89, 0);
        sparseIntArray2.append(90, 0);
        sparseIntArray2.append(91, 0);
        this.mPanelKeyArray.append(1, sparseIntArray2);
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray3.put(1, HotKeyConstant.K_SLEEP);
        sparseIntArray3.append(2, 21);
        sparseIntArray3.append(3, 20);
        sparseIntArray3.append(4, 58);
        sparseIntArray3.append(5, 4);
        sparseIntArray3.append(6, 50);
        sparseIntArray3.append(7, 129);
        sparseIntArray3.append(8, 130);
        sparseIntArray3.append(9, 184);
        sparseIntArray3.append(10, 33);
        sparseIntArray3.append(11, 34);
        sparseIntArray3.append(12, 35);
        sparseIntArray3.append(13, 36);
        sparseIntArray3.append(14, 37);
        sparseIntArray3.append(15, 38);
        sparseIntArray3.append(16, 31);
        sparseIntArray3.append(17, 31);
        sparseIntArray3.append(18, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray3.append(19, HotKeyConstant.K_TIME);
        sparseIntArray3.append(20, 129);
        sparseIntArray3.append(21, 75);
        sparseIntArray3.append(22, 49);
        sparseIntArray3.append(23, 7);
        sparseIntArray3.append(24, 8);
        sparseIntArray3.append(25, 48);
        sparseIntArray3.append(26, 47);
        sparseIntArray3.append(27, 0);
        sparseIntArray3.append(28, 0);
        sparseIntArray3.append(29, 0);
        sparseIntArray3.append(30, 0);
        sparseIntArray3.append(31, 0);
        sparseIntArray3.append(64, 141);
        sparseIntArray3.append(52, 0);
        sparseIntArray3.append(53, 0);
        sparseIntArray3.append(80, 0);
        sparseIntArray3.append(81, 0);
        sparseIntArray3.append(82, 0);
        sparseIntArray3.append(83, 52);
        sparseIntArray3.append(84, 59);
        sparseIntArray3.append(85, 0);
        sparseIntArray3.append(86, 0);
        sparseIntArray3.append(87, 0);
        sparseIntArray3.append(88, 0);
        sparseIntArray3.append(89, 0);
        sparseIntArray3.append(90, 0);
        sparseIntArray3.append(91, 0);
        this.mPanelKeyArray.append(2, sparseIntArray3);
        SparseIntArray sparseIntArray4 = new SparseIntArray();
        sparseIntArray4.put(1, HotKeyConstant.K_SLEEP);
        sparseIntArray4.append(2, 21);
        sparseIntArray4.append(3, 20);
        sparseIntArray4.append(4, 58);
        sparseIntArray4.append(5, 4);
        sparseIntArray4.append(6, 50);
        sparseIntArray4.append(7, 129);
        sparseIntArray4.append(8, 130);
        sparseIntArray4.append(9, 184);
        sparseIntArray4.append(10, 33);
        sparseIntArray4.append(11, 34);
        sparseIntArray4.append(12, 35);
        sparseIntArray4.append(13, 36);
        sparseIntArray4.append(14, 37);
        sparseIntArray4.append(15, 38);
        sparseIntArray4.append(16, 31);
        sparseIntArray4.append(17, 31);
        sparseIntArray4.append(18, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray4.append(19, HotKeyConstant.K_TIME);
        sparseIntArray4.append(20, 129);
        sparseIntArray4.append(21, 75);
        sparseIntArray4.append(22, 49);
        sparseIntArray4.append(23, 7);
        sparseIntArray4.append(24, 8);
        sparseIntArray4.append(25, 48);
        sparseIntArray4.append(26, 47);
        sparseIntArray4.append(27, 17);
        sparseIntArray4.append(28, 45);
        sparseIntArray4.append(29, 46);
        sparseIntArray4.append(30, 0);
        sparseIntArray4.append(31, 0);
        sparseIntArray4.append(64, 0);
        sparseIntArray4.append(52, 0);
        sparseIntArray4.append(53, 0);
        sparseIntArray4.append(80, 0);
        sparseIntArray4.append(81, 0);
        sparseIntArray4.append(82, 0);
        sparseIntArray4.append(83, 0);
        sparseIntArray4.append(84, 0);
        sparseIntArray4.append(85, 0);
        sparseIntArray4.append(86, 0);
        sparseIntArray4.append(87, 0);
        sparseIntArray4.append(88, 0);
        sparseIntArray4.append(89, 0);
        sparseIntArray4.append(90, 0);
        sparseIntArray4.append(91, 0);
        this.mPanelKeyArray.append(3, sparseIntArray4);
        SparseIntArray sparseIntArray5 = new SparseIntArray();
        sparseIntArray5.put(1, HotKeyConstant.K_SLEEP);
        sparseIntArray5.append(2, 21);
        sparseIntArray5.append(3, 20);
        sparseIntArray5.append(4, 58);
        sparseIntArray5.append(5, 4);
        sparseIntArray5.append(6, 50);
        sparseIntArray5.append(7, 129);
        sparseIntArray5.append(8, 130);
        sparseIntArray5.append(9, 3);
        sparseIntArray5.append(10, 33);
        sparseIntArray5.append(11, 34);
        sparseIntArray5.append(12, 35);
        sparseIntArray5.append(13, 36);
        sparseIntArray5.append(14, 37);
        sparseIntArray5.append(15, 38);
        sparseIntArray5.append(16, 31);
        sparseIntArray5.append(17, 31);
        sparseIntArray5.append(18, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray5.append(19, HotKeyConstant.K_TIME);
        sparseIntArray5.append(20, 129);
        sparseIntArray5.append(21, 75);
        sparseIntArray5.append(22, 49);
        sparseIntArray5.append(23, 7);
        sparseIntArray5.append(24, 8);
        sparseIntArray5.append(25, 48);
        sparseIntArray5.append(26, 47);
        sparseIntArray5.append(27, 0);
        sparseIntArray5.append(28, 0);
        sparseIntArray5.append(29, 0);
        sparseIntArray5.append(30, 0);
        sparseIntArray5.append(31, 0);
        sparseIntArray5.append(64, 0);
        sparseIntArray5.append(52, 0);
        sparseIntArray5.append(53, 0);
        sparseIntArray5.append(80, 0);
        sparseIntArray5.append(81, 0);
        sparseIntArray5.append(82, 0);
        sparseIntArray5.append(83, 0);
        sparseIntArray5.append(84, 0);
        sparseIntArray5.append(85, 0);
        sparseIntArray5.append(86, 0);
        sparseIntArray5.append(87, 0);
        sparseIntArray5.append(88, 0);
        sparseIntArray5.append(89, 0);
        sparseIntArray5.append(90, 0);
        sparseIntArray5.append(91, 0);
        this.mPanelKeyArray.append(4, sparseIntArray5);
        SparseIntArray sparseIntArray6 = new SparseIntArray();
        sparseIntArray6.put(1, 21);
        sparseIntArray6.append(2, 37);
        sparseIntArray6.append(3, 36);
        sparseIntArray6.append(4, 35);
        sparseIntArray6.append(5, HotKeyConstant.K_SLEEP);
        sparseIntArray6.append(6, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray6.append(7, 129);
        sparseIntArray6.append(8, 33);
        sparseIntArray6.append(9, 34);
        sparseIntArray6.append(10, 20);
        sparseIntArray6.append(11, 49);
        sparseIntArray6.append(12, 50);
        sparseIntArray6.append(13, 4);
        sparseIntArray6.append(14, 31);
        sparseIntArray6.append(15, 31);
        sparseIntArray6.append(16, 0);
        sparseIntArray6.append(17, 58);
        sparseIntArray6.append(18, 2);
        sparseIntArray6.append(19, 0);
        sparseIntArray6.append(20, 129);
        sparseIntArray6.append(21, 184);
        sparseIntArray6.append(22, 38);
        sparseIntArray6.append(23, 7);
        sparseIntArray6.append(24, 8);
        sparseIntArray6.append(25, 48);
        sparseIntArray6.append(26, 47);
        sparseIntArray6.append(27, 0);
        sparseIntArray6.append(28, 0);
        sparseIntArray6.append(29, 0);
        sparseIntArray6.append(30, 0);
        sparseIntArray6.append(31, 0);
        sparseIntArray6.append(64, 0);
        sparseIntArray6.append(52, 48);
        sparseIntArray6.append(53, 47);
        sparseIntArray6.append(80, 0);
        sparseIntArray6.append(81, 0);
        sparseIntArray6.append(82, 0);
        sparseIntArray6.append(83, 0);
        sparseIntArray6.append(84, 0);
        sparseIntArray6.append(85, 0);
        sparseIntArray6.append(86, 0);
        sparseIntArray6.append(87, 0);
        sparseIntArray6.append(88, 0);
        sparseIntArray6.append(89, 0);
        sparseIntArray6.append(90, 0);
        sparseIntArray6.append(91, 0);
        this.mPanelKeyArray.append(5, sparseIntArray6);
        SparseIntArray sparseIntArray7 = new SparseIntArray();
        sparseIntArray7.put(1, HotKeyConstant.K_SLEEP);
        sparseIntArray7.append(2, 21);
        sparseIntArray7.append(3, 20);
        sparseIntArray7.append(4, 58);
        sparseIntArray7.append(5, 17);
        sparseIntArray7.append(6, 50);
        sparseIntArray7.append(7, 0);
        sparseIntArray7.append(8, 0);
        sparseIntArray7.append(9, 184);
        sparseIntArray7.append(10, 33);
        sparseIntArray7.append(11, 34);
        sparseIntArray7.append(12, 35);
        sparseIntArray7.append(13, 36);
        sparseIntArray7.append(14, 37);
        sparseIntArray7.append(15, 38);
        sparseIntArray7.append(16, 0);
        sparseIntArray7.append(17, 31);
        sparseIntArray7.append(18, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray7.append(19, 0);
        sparseIntArray7.append(20, 129);
        sparseIntArray7.append(21, 75);
        sparseIntArray7.append(22, 49);
        sparseIntArray7.append(23, 7);
        sparseIntArray7.append(24, 8);
        sparseIntArray7.append(25, 48);
        sparseIntArray7.append(26, 47);
        sparseIntArray7.append(27, 0);
        sparseIntArray7.append(28, 0);
        sparseIntArray7.append(29, 0);
        sparseIntArray7.append(30, 0);
        sparseIntArray7.append(31, 0);
        sparseIntArray7.append(64, 0);
        sparseIntArray7.append(52, 0);
        sparseIntArray7.append(53, 0);
        sparseIntArray7.append(80, 52);
        sparseIntArray7.append(81, 2);
        sparseIntArray7.append(82, 94);
        sparseIntArray7.append(83, 0);
        sparseIntArray7.append(84, 0);
        sparseIntArray7.append(85, 128);
        sparseIntArray7.append(86, 128);
        sparseIntArray7.append(87, 128);
        sparseIntArray7.append(88, 47);
        sparseIntArray7.append(89, 48);
        sparseIntArray7.append(90, 45);
        sparseIntArray7.append(91, 46);
        this.mPanelKeyArray.append(6, sparseIntArray7);
        SparseIntArray sparseIntArray8 = new SparseIntArray();
        sparseIntArray8.append(1, 52);
        sparseIntArray8.append(2, 50);
        sparseIntArray8.append(3, 49);
        sparseIntArray8.append(4, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray8.append(5, 129);
        sparseIntArray8.append(6, 20);
        sparseIntArray8.append(7, 0);
        sparseIntArray8.append(8, 0);
        sparseIntArray8.append(9, 21);
        sparseIntArray8.append(10, 75);
        sparseIntArray8.append(11, HotKeyConstant.K_SLEEP);
        sparseIntArray8.append(12, 33);
        sparseIntArray8.append(13, 34);
        sparseIntArray8.append(14, 35);
        sparseIntArray8.append(15, 36);
        sparseIntArray8.append(16, 0);
        sparseIntArray8.append(17, 31);
        sparseIntArray8.append(18, 14);
        sparseIntArray8.append(19, 0);
        sparseIntArray8.append(20, 58);
        sparseIntArray8.append(21, 0);
        sparseIntArray8.append(22, 2);
        sparseIntArray8.append(23, 7);
        sparseIntArray8.append(24, 8);
        sparseIntArray8.append(25, 48);
        sparseIntArray8.append(26, 47);
        sparseIntArray8.append(27, 17);
        sparseIntArray8.append(28, 0);
        sparseIntArray8.append(29, 0);
        sparseIntArray8.append(30, 37);
        sparseIntArray8.append(31, 38);
        sparseIntArray8.append(64, 0);
        sparseIntArray8.append(52, 0);
        sparseIntArray8.append(53, 0);
        sparseIntArray8.append(80, 4);
        sparseIntArray8.append(81, 0);
        sparseIntArray8.append(82, 94);
        sparseIntArray8.append(83, 0);
        sparseIntArray8.append(84, 0);
        sparseIntArray8.append(85, 0);
        sparseIntArray8.append(86, 0);
        sparseIntArray8.append(87, 0);
        sparseIntArray8.append(88, 0);
        sparseIntArray8.append(89, 0);
        sparseIntArray8.append(90, 0);
        sparseIntArray8.append(91, 0);
        this.mPanelKeyArray.append(7, sparseIntArray8);
        SparseIntArray sparseIntArray9 = new SparseIntArray();
        sparseIntArray9.put(1, 34);
        sparseIntArray9.append(2, 3);
        sparseIntArray9.append(3, 50);
        sparseIntArray9.append(4, 129);
        sparseIntArray9.append(5, 129);
        sparseIntArray9.append(6, 21);
        sparseIntArray9.append(7, HotKeyConstant.K_SLEEP);
        sparseIntArray9.append(8, 49);
        sparseIntArray9.append(9, HotKeyConstant.K_CAN_CONFIG);
        sparseIntArray9.append(10, 35);
        sparseIntArray9.append(11, 36);
        sparseIntArray9.append(12, 37);
        sparseIntArray9.append(13, 38);
        sparseIntArray9.append(14, 0);
        sparseIntArray9.append(15, 0);
        sparseIntArray9.append(16, 75);
        sparseIntArray9.append(17, 31);
        sparseIntArray9.append(18, 58);
        sparseIntArray9.append(19, HotKeyConstant.K_TIME);
        sparseIntArray9.append(20, 4);
        sparseIntArray9.append(21, 33);
        sparseIntArray9.append(22, 20);
        sparseIntArray9.append(23, 7);
        sparseIntArray9.append(24, 8);
        sparseIntArray9.append(25, 48);
        sparseIntArray9.append(26, 47);
        sparseIntArray9.append(27, 17);
        sparseIntArray9.append(28, 0);
        sparseIntArray9.append(29, 0);
        sparseIntArray9.append(30, 0);
        sparseIntArray9.append(31, 0);
        sparseIntArray9.append(64, 141);
        sparseIntArray9.append(52, 0);
        sparseIntArray9.append(53, 0);
        sparseIntArray9.append(80, 0);
        sparseIntArray9.append(81, 0);
        sparseIntArray9.append(82, 0);
        sparseIntArray9.append(83, 0);
        sparseIntArray9.append(84, 0);
        sparseIntArray9.append(85, 0);
        sparseIntArray9.append(86, 0);
        sparseIntArray9.append(87, 0);
        sparseIntArray9.append(88, 0);
        sparseIntArray9.append(89, 0);
        sparseIntArray9.append(90, 0);
        sparseIntArray9.append(91, 0);
        this.mPanelKeyArray.append(8, sparseIntArray9);
    }

    private void wheelKeyClick(int i) {
        if (this.mCanBusInfoInt[3] == 1) {
            realKeyClick4(this.mContext, i);
        }
    }

    private void panelKeyKnobVol(int i) {
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[3];
        if (i2 == 0) {
            return;
        }
        realKeyClick3_1(this.mContext, i, iArr[2], i2);
    }

    private void panelKeyKnobSel(int i) {
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[3];
        if (i2 == 0) {
            return;
        }
        realKeyClick3_2(this.mContext, i, iArr[2], i2);
    }

    private void setOutDoorTemperature() {
        if (this.mOutDoorTemperature == this.mCanBusInfoInt[7] && this.mAirTmeperatureUnit == GeneralAirData.fahrenheit_celsius) {
            return;
        }
        this.mOutDoorTemperature = this.mCanBusInfoInt[7];
        this.mAirTmeperatureUnit = GeneralAirData.fahrenheit_celsius;
        if (GeneralAirData.fahrenheit_celsius) {
            updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + getTempUnitF(this.mContext));
        } else {
            updateOutDoorTemp(this.mContext, ((int) this.mCanBusInfoByte[7]) + getTempUnitC(this.mContext));
        }
    }

    private boolean isBlowModeMatch(int i, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LO";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return i == 254 ? "HI" : (i < 1 || i > 253) ? "" : i + getTempUnitF(this.mContext);
        }
        if (i == 30) {
            return "HI";
        }
        if (i == 29) {
            return "16.0" + getTempUnitC(this.mContext);
        }
        if (i == 31) {
            return "16.5" + getTempUnitC(this.mContext);
        }
        if (i == 32) {
            return "15.0" + getTempUnitC(this.mContext);
        }
        if (i == 33) {
            return "15.5" + getTempUnitC(this.mContext);
        }
        if (i == 34) {
            return "31.0" + getTempUnitC(this.mContext);
        }
        return (i < 1 || i > 28) ? "" : ((i + 33) / 2.0f) + getTempUnitC(this.mContext);
    }

    private boolean getAirAcStatus() {
        if (this.mDifferentId == 20) {
            return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) != 0;
        }
        return DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
    }

    private String resolveAirTemperatureRear(int i) {
        return i == 0 ? "LO" : i == 254 ? "HI" : GeneralAirData.fahrenheit_celsius ? (i < 1 || i > 253) ? "" : i + getTempUnitF(this.mContext) : (i < 1 || i > 128) ? "" : (i / 2.0f) + getTempUnitC(this.mContext);
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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

    private byte[] getAsciiDatas(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == 0) {
                return Arrays.copyOfRange(bArr, 2, i);
            }
        }
        return Arrays.copyOfRange(bArr, 2, bArr.length);
    }

    private UiMgrInterface getUiMgrInterface() {
        if (this.mUiMgrInterface == null) {
            this.mUiMgrInterface = UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgrInterface;
    }

    private int[] getLeftAndRight(String str) {
        int[] iArr = new int[2];
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            int iIntValue = this.mSettingItemIndeHashMap.get(str).intValue();
            iArr[0] = iIntValue >> 8;
            iArr[1] = iIntValue & 255;
        } else {
            iArr[0] = -1;
            iArr[1] = -1;
        }
        Log.i(this.TAG, "getLeftAndRigth: left:" + iArr[0] + ", right:" + iArr[1]);
        return iArr;
    }

    private void initData(Context context) {
        this.m0x05ItemTitleArray = new String[]{"_194_automatic_mode_wind", "_220_air_quality_sensor", "air_partition_temperature", "back_windows_auto_defog", "front_windows_auto_defog", "seat_auto_heat", "remote_control_seat_auto_heat", "_1168_set_ac_system_ac_rmote_start_seat_auto_heat", "air_launcher_mode"};
        this.m0x06ItemTitleArray = new String[]{"find_car_light_function", "lock_big_light_delay_set", "prevent_open_door_auto_lock", "start_auto_lock", "stop_auto_unlock", "delay_lock", "remote_control_unlock_light_feedback", "remote_control_unlock_light_or_horn_feedback", "remote_unlock_set", "_61_revers_back_wiper_auto_launcher"};
        this.m0x0AItemTitleArray = new String[]{"close_unlock_set", "key_forgot_remain", "personalization_by_driver", "auto_re_lock_doors", "auto_wiper", "flank_blind_zone_warning", "leave_car_auto_lock", "left_or_right_hand_traffic", "adaptive_forward_lighting", "auto_rear_view_mirror_fold", "_61_reverse_tilt_mirror", "convenience_get_off_option", "call_memory_position", "_61_rear_seat_reminder", "adaptive_cruise_start_reminder", "reverse_rear_view_mirror_auto_tilt", "_61_advanced_hill_start_assist", "remote_window_control", "_61_hands_free_liftgate_control", "_61_adapter_front_light", "_61_movement_steering", "_61_rear_pass_warning", "_61_line_change_warning", "_61_alert_type", "_61_forward_collision_system"};
        this.m0xD2ItemTitleArray = new String[]{"compass_calibration_status", "compass_zoom", "magnetic_field_angle"};
        this.m0x44ItemTitleArray = new String[]{"remote_start_seat_heat", "remote_start_seat_cold", "remote_start_air", "remote_start_rear_air", "_61_evlevated_idle"};
        SparseArray<String[]> sparseArray = new SparseArray<>();
        this.m0x1AItemTitleArray = sparseArray;
        sparseArray.put(0, new String[]{"_194_automatic_mode_wind", "_220_air_quality_sensor", "air_launcher_mode", "front_windows_auto_defog", "back_windows_auto_defog", "air_partition_temperature"});
        this.m0x1AItemTitleArray.append(1, new String[]{"flank_blind_zone_warning"});
        this.m0x1AItemTitleArray.append(2, new String[]{"_61_revers_back_wiper_auto_launcher", "personalization_by_driver", "call_memory_position", "convenience_get_off_option", "_61_reverse_tilt_mirror", "reverse_rear_view_mirror_auto_tilt", "auto_rear_view_mirror_fold", "auto_wiper"});
        this.m0x1AItemTitleArray.append(3, new String[]{"find_car_light_function", "lock_big_light_delay_set"});
        this.m0x1AItemTitleArray.append(4, new String[]{"prevent_open_door_auto_lock", "stop_auto_unlock", "start_auto_lock", "delay_lock"});
        this.m0x1AItemTitleArray.append(5, new String[]{"remote_control_unlock_light_feedback", "remote_control_unlock_light_or_horn_feedback", "remote_unlock_set", "auto_re_lock_doors", "close_unlock_set", "key_forgot_remain", "leave_car_auto_lock", "remote_window_control"});
        this.mDecimalFormat0p0 = new DecimalFormat("0.0");
        this.mTireAlarm = new String[]{CommUtil.getStrByResId(context, "_61_tire_pressure_was_higher"), CommUtil.getStrByResId(context, "_61_tire_pressure_was_lower"), CommUtil.getStrByResId(context, "_61_please_check_tire_pressure")};
        this.mCanbusDataArray = new SparseArray<>();
    }

    void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        Log.i(this.TAG, "initSettingsItem: ");
        this.mSettingRightItemArray = new SparseArray<>();
        this.mSettingItemIndeHashMap = new HashMap<>();
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingPageUiSet.getList().get(i).getItemList();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), Integer.valueOf(((i << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | i2));
                arrayList.add(new SettingRightItem(i2));
            }
            this.mSettingRightItemArray.append(i, arrayList);
        }
    }

    void startSettingActivity() {
        int[] leftAndRight = getLeftAndRight("_194_automatic_mode_wind");
        startSettingActivity(this.mContext, leftAndRight[0], leftAndRight[1]);
    }

    private class SettingRightItem {
        boolean enable;
        int index;
        Object value;

        private SettingRightItem(int i) {
            this.index = i;
            this.value = new Object();
            this.enable = true;
        }

        public int getIndex() {
            return this.index;
        }

        public Object getValue() {
            return this.value;
        }

        public SettingRightItem setValue(Object obj) {
            this.value = obj;
            return this;
        }

        public boolean isEnable() {
            return this.enable;
        }

        public SettingRightItem setEnable(boolean z) {
            this.enable = z;
            return this;
        }
    }

    private void setSettingData(String[] strArr, Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            int[] leftAndRight = getLeftAndRight(strArr[i]);
            int i2 = leftAndRight[0];
            if (i2 != -1 && leftAndRight[1] != -1) {
                Object obj = objArr[i];
                SettingRightItem settingRightItem = this.mSettingRightItemArray.get(i2).get(leftAndRight[1]);
                settingRightItem.setValue(obj);
                arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], obj).setEnable(settingRightItem.isEnable()));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingData(String[] strArr, boolean[] zArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            int[] leftAndRight = getLeftAndRight(strArr[i]);
            int i2 = leftAndRight[0];
            if (i2 != -1 && leftAndRight[1] != -1) {
                boolean z = zArr[i];
                SettingRightItem settingRightItem = this.mSettingRightItemArray.get(i2).get(leftAndRight[1]);
                settingRightItem.setEnable(z);
                arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], settingRightItem.getValue()).setEnable(z));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
