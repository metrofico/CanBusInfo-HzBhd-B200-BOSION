package com.hzbhd.canbus.car._284;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.view.MotionEventCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x24Data;
    private int[] m0x25Data;
    private int[] m0x26Data;
    private int[] m0x27Data;
    private int[] m0x29Data;
    private int m0x30Data;
    private int m0x31Data;
    private int[] m0x32Data;
    private int[] m0x38Data;
    private int[] m0x39Data;
    private int[] m0x3AData;
    private int[] m0x52Data;
    private int[] m0xFFData;
    private boolean mBackStatus;
    private int mBtStatus;
    byte[] mBytes0x75;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mEachId;
    private boolean mFrontRadarEnterRightViewStatsu;
    private boolean mFrontStatus;
    private Handler mHandler;
    private int mIsMute;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private ID3[] mMusicId3s;
    private int mMusicSource;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private HashMap<String, Integer> mSettingItemIndeHashMap;
    private SparseArray<String> mSettingItemSpareArray;
    private int mSettingLeftIndex;
    private int mSettingRightIndex;
    private int mSkyWindowStatus;
    private TimerUtil mTimerUtil;
    private List<List<String>> mTireInfoList;
    private List<TireUpdateEntity> mTireUpdateEntityList;
    private List<List<String>> mTireWarningList;
    private UiMgr mUiMgr;
    private final int _284_BT_STATUS_INCOMING = 1;
    private final int _284_BT_STATUS_OUTGOING = 2;
    private final String SHARE_IS_FRONT_RADAR_ENTER_RIGHTVIEW = "share_is_front_radar_enter_rightview";
    private final int SEND_GIVEN_MEDIA_MESSAGE = 16;
    private final int SEND_NORMAL_MESSAGE = 17;
    private final long MEDIA_MESSAGE_SEND_DELAY = 80;

    private void openEasyConnect() {
    }

    public MsgMgr() {
        byte[] bArr = new byte[10];
        this.mBytes0x75 = bArr;
        bArr[0] = 22;
        bArr[1] = 117;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(final Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mEachId = getCurrentEachCanId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 82});
        int i = this.mEachId;
        if (i != 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 3, (byte) i});
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        GeneralParkData.radar_distance_disable = new int[]{255};
        GeneralTireData.isHaveSpareTire = false;
        this.mTireUpdateEntityList = new ArrayList();
        this.mTireInfoList = new ArrayList();
        this.mTireWarningList = new ArrayList();
        for (int i2 = 0; i2 < 4; i2++) {
            this.mTireUpdateEntityList.add(new TireUpdateEntity(i2, 0, new String[0]));
            ArrayList arrayList = new ArrayList();
            arrayList.add(CommUtil.getStrByResId(context, "_284_tire_temperature_default"));
            arrayList.add(CommUtil.getStrByResId(context, "_284_tire_pressure_default"));
            this.mTireInfoList.add(arrayList);
            this.mTireWarningList.add(new ArrayList());
        }
        initID3();
        initSettingItemIndexSparseArray();
        getUiMgr().initSettingItem();
        initSettingsItem(getUiMgr().getSettingUiSet(context));
        GeneralSettingData.dataList = new ArrayList();
        initSettingsPnormaicAndRightViewItem();
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._284.MsgMgr.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 16) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 17) {
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 82) {
            setVehicleSetup0x52();
            return;
        }
        if (i != 255) {
            switch (i) {
                case 32:
                    setWheelKey0x20();
                    break;
                case 33:
                    setAirData0x21();
                    break;
                case 34:
                    setRearRadarData0x22();
                    break;
                case 35:
                    setFrontRadarData0x23();
                    break;
                case 36:
                    setLeftRadarData0x24();
                    break;
                case 37:
                    setRightRadarData0x25();
                    break;
                case 38:
                    setRearRadarDistanceData0x26();
                    break;
                case 39:
                    setFrontRadarDistanceData0x27();
                    break;
                default:
                    switch (i) {
                        case 48:
                            setRightViewSwtich0x30();
                            break;
                        case 49:
                            setPanoramicSwtich0x31();
                            break;
                        case 50:
                            setPanoramicViewStatus0x32();
                            break;
                        default:
                            switch (i) {
                                case 56:
                                    setTirePressureData0x38();
                                    break;
                                case 57:
                                    setTirePressureWarnning0x39();
                                    break;
                                case 58:
                                    setVehicleBaseInfo0x3A();
                                    break;
                            }
                    }
            }
            return;
        }
        setVersionInfo0xFF();
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i != 136) {
            switch (i) {
                case 0:
                    realKeyClick1(0);
                    break;
                case 1:
                    realKeyClick1(7);
                    break;
                case 2:
                    realKeyClick1(8);
                    break;
                case 3:
                    realKeyClick1(45);
                    break;
                case 4:
                    realKeyClick1(46);
                    break;
                case 5:
                    realKeyClick1(3);
                    break;
                case 6:
                    realKeyClick1(2);
                    break;
                case 7:
                    realKeyClick1(14);
                    break;
                case 8:
                    realKeyClick1(15);
                    break;
                case 9:
                    realKeyClick1(129);
                    break;
                case 10:
                    realKeyClick1(HotKeyConstant.K_SLEEP);
                    break;
                default:
                    switch (i) {
                        case 17:
                            realKeyClick1(7);
                            break;
                        case 18:
                            realKeyClick1(8);
                            break;
                        case 19:
                            realKeyClick1(52);
                            break;
                        case 20:
                            realKeyClick1(59);
                            break;
                        default:
                            switch (i) {
                                case 26:
                                    realKeyClick1(HotKeyConstant.K_1_PICKUP);
                                    break;
                                case 27:
                                    realKeyClick1(HotKeyConstant.K_2_HANGUP);
                                    break;
                                case 28:
                                    realKeyClick1(HotKeyConstant.K_3_SHUFFLE);
                                    break;
                                case 29:
                                    realKeyClick1(HotKeyConstant.K_4_REPEAT);
                                    break;
                                case 30:
                                    realKeyClick1(HotKeyConstant.K_5_REPEAT);
                                    break;
                                case 31:
                                    realKeyClick1(HotKeyConstant.K_6_SHUFFLE);
                                    break;
                                default:
                                    switch (i) {
                                        case 33:
                                            realKeyClick3_1(7);
                                            break;
                                        case 34:
                                            realKeyClick3_1(8);
                                            break;
                                        case 35:
                                            realKeyClick1(49);
                                            break;
                                        case 36:
                                            realKeyClick3_2(48);
                                            break;
                                        case 37:
                                            realKeyClick3_2(47);
                                            break;
                                        case 38:
                                            realKeyClick1(45);
                                            break;
                                        case 39:
                                            realKeyClick1(46);
                                            break;
                                        case 40:
                                            realKeyClick1(58);
                                            break;
                                        case 41:
                                            realKeyClick1(128);
                                            break;
                                        case 42:
                                            realKeyClick1(152);
                                            break;
                                        case 43:
                                            realKeyClick1(30);
                                            break;
                                        case 44:
                                            realKeyClick1(68);
                                            break;
                                        case 45:
                                            realKeyClick1(14);
                                            break;
                                        case 46:
                                            openEasyConnect();
                                            break;
                                        case 47:
                                            realKeyClick1(4);
                                            break;
                                        case 48:
                                            realKeyClick1(52);
                                            break;
                                        case 49:
                                            realKeyClick1(49);
                                            break;
                                    }
                            }
                    }
            }
        }
        realKeyClick1(HotKeyConstant.K_SPEECH);
    }

    private void setAirData0x21() {
        if (isAirMsgRepeat(this.mCanBusInfoByte)) {
            return;
        }
        GeneralAirData.rear = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[3];
        int i = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_blow_window = i == 3 || i == 4;
        int i2 = this.mCanBusInfoInt[4];
        GeneralAirData.front_right_blow_window = i2 == 3 || i2 == 4;
        int i3 = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_blow_head = i3 == 0 || i3 == 1;
        int i4 = this.mCanBusInfoInt[4];
        GeneralAirData.front_right_blow_head = i4 == 0 || i4 == 1;
        int i5 = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_blow_foot = i5 == 1 || i5 == 2 || i5 == 4;
        int i6 = this.mCanBusInfoInt[4];
        GeneralAirData.front_right_blow_foot = i6 == 1 || i6 == 2 || i6 == 4;
        String strResolveAirTemperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_temperature = strResolveAirTemperature;
        GeneralAirData.front_right_temperature = strResolveAirTemperature;
        GeneralAirData.power = GeneralAirData.front_wind_level != 0;
        GeneralAirData.front_defog = GeneralAirData.front_left_blow_window;
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadarData0x22() {
        if (is0x22DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (is0x23DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            if (CommUtil.isPanoramic(this.mContext)) {
                if (SharePreUtil.getBoolValue(this.mContext, "share_is_front_radar_enter_rightview", false)) {
                    boolean z = this.mCanBusInfoInt[5] != 0;
                    if (this.mFrontRadarEnterRightViewStatsu ^ z) {
                        this.mFrontRadarEnterRightViewStatsu = z;
                        switchFCamera(this.mContext, z);
                    }
                }
            }
        }
    }

    private void setLeftRadarData0x24() {
        if (is0x24DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setLeftRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRightRadarData0x25() {
        if (is0x25DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRightRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadarDistanceData0x26() {
        if (is0x26DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarDistanceData0x27() {
        if (is0x27DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarDistanceData(iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setTrackData0x29() {
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 5500, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setRightViewSwtich0x30() {
        if (is0x30DataChange()) {
            switchFCamera(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        }
    }

    private void setPanoramicSwtich0x31() {
        if (is0x31DataChange()) {
            forceReverse(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        }
    }

    private void setPanoramicViewStatus0x32() {
        if (is0x32DataChange()) {
            ArrayList arrayList = new ArrayList();
            int i = this.mCanBusInfoInt[2];
            arrayList.add(new PanoramicBtnUpdateEntity(0, i == 1 || i == 5));
            int i2 = this.mCanBusInfoInt[2];
            arrayList.add(new PanoramicBtnUpdateEntity(1, i2 == 4 || i2 == 8));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[2] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[2] == 3));
            arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[2] == 7));
            int i3 = this.mCanBusInfoInt[2];
            arrayList.add(new PanoramicBtnUpdateEntity(5, i3 == 5 || i3 == 8));
            arrayList.add(new PanoramicBtnUpdateEntity(6, this.mCanBusInfoInt[2] == 6));
            arrayList.add(new PanoramicBtnUpdateEntity(7, this.mCanBusInfoInt[2] == 9));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void setTirePressureData0x38() {
        if (is0x38DataChange()) {
            Iterator<TireUpdateEntity> it = this.mTireUpdateEntityList.iterator();
            while (it.hasNext()) {
                int whichTire = it.next().getWhichTire();
                List<String> list = this.mTireInfoList.get(whichTire);
                list.clear();
                int i = this.mCanBusInfoInt[whichTire + 2];
                String strByResId = CommUtil.getStrByResId(this.mContext, "_284_tire_temperature_default");
                if (i != 255) {
                    strByResId = (i - 40) + getTempUnitC(this.mContext);
                }
                list.add(strByResId);
                int i2 = this.mCanBusInfoInt[whichTire + 6];
                String strByResId2 = CommUtil.getStrByResId(this.mContext, "_284_tire_pressure_default");
                if (i2 != 255) {
                    strByResId2 = new DecimalFormat("0.0").format((DataHandleUtils.rangeNumber(i2, 7, 252) / 70.0f) - 0.1f) + "bar";
                }
                list.add(strByResId2);
                list.addAll(this.mTireWarningList.get(whichTire));
                this.mTireUpdateEntityList.get(whichTire).setList(list);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void setTirePressureWarnning0x39() {
        if (is0x39DataChange()) {
            for (TireUpdateEntity tireUpdateEntity : this.mTireUpdateEntityList) {
                int whichTire = tireUpdateEntity.getWhichTire();
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.mTireInfoList.get(whichTire));
                List<String> list = this.mTireWarningList.get(whichTire);
                list.clear();
                tireUpdateEntity.setTireStatus(0);
                for (int i = 0; i < 8; i++) {
                    if ((this.mCanBusInfoInt[whichTire + 2] & (1 << i)) != 0) {
                        list.add(CommUtil.getStrByResId(this.mContext, "_284_tire_warning_" + i));
                        tireUpdateEntity.setTireStatus(1);
                    }
                }
                int size = list.size();
                for (int i2 = 0; i2 < 8 - size; i2++) {
                    list.add("");
                }
                arrayList.addAll(list);
                tireUpdateEntity.setList(arrayList);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void setVehicleBaseInfo0x3A() {
        if (is0x3ADataChange()) {
            GeneralDoorData.skyWindowOpenLevel = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? 2 : 0;
            boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            this.mLeftFrontRec = boolBit5;
            GeneralDoorData.isLeftFrontDoorOpen = boolBit5;
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mRightFrontRec = boolBit4;
            GeneralDoorData.isRightFrontDoorOpen = boolBit4;
            boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            this.mLeftRearRec = boolBit3;
            GeneralDoorData.isLeftRearDoorOpen = boolBit3;
            boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            this.mRightRearRec = boolBit2;
            GeneralDoorData.isRightRearDoorOpen = boolBit2;
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            if (isDoorDataChange()) {
                updateDoorView(this.mContext);
            }
            ArrayList arrayList = new ArrayList();
            int[] iArr = this.mCanBusInfoInt;
            int i = (iArr[3] * 256) + iArr[4];
            if (i > 0 && i < 8190) {
                arrayList.add(new DriverUpdateEntity(0, 0, i + " km"));
            }
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setVehicleSetup0x52() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._284.MsgMgr.setVehicleSetup0x52():void");
    }

    private void setVersionInfo0xFF() {
        if (is0xFFDataChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i6, (byte) i5, z ? (byte) 1 : (byte) 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendNormalMessage(new byte[]{22, -64, 0});
        byte[] bArr = this.mBytes0x75;
        bArr[2] = 0;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = (byte) this.mIsMute;
        sendNormalMessage(bArr, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte bandData = (byte) getBandData(str);
        int[] freqByteHiLo = getFreqByteHiLo(100, str, str2);
        byte b = (byte) i;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), new byte[]{22, -64, 1, bandData, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], b});
        int[] freqByteHiLo2 = getFreqByteHiLo(10, str, str2);
        int i3 = str.contains("AM") ? 3 : 1;
        int i4 = freqByteHiLo2[0];
        int i5 = freqByteHiLo2[1];
        byte[] bArr = this.mBytes0x75;
        bArr[2] = 1;
        bArr[3] = (byte) i3;
        bArr[4] = (byte) i4;
        bArr[5] = (byte) i5;
        bArr[6] = b;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = (byte) this.mIsMute;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), this.mBytes0x75, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        reportBtPhoneInfo(0, bArr, 0, 0);
        byte[] bArr2 = this.mBytes0x75;
        bArr2[2] = 6;
        bArr2[3] = 0;
        bArr2[4] = 0;
        bArr2[5] = 0;
        bArr2[6] = 0;
        bArr2[7] = 0;
        bArr2[8] = 0;
        bArr2[9] = (byte) this.mIsMute;
        sendNormalMessage(bArr2, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        this.mBtStatus = 1;
        reportBtPhoneInfo(1, bArr, 0, 0);
        byte[] bArr2 = this.mBytes0x75;
        bArr2[2] = 6;
        bArr2[3] = 0;
        bArr2[4] = 0;
        bArr2[5] = 0;
        bArr2[6] = 0;
        bArr2[7] = 0;
        bArr2[8] = 0;
        bArr2[9] = (byte) this.mIsMute;
        sendNormalMessage(bArr2, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        reportBtPhoneInfo(this.mBtStatus == 2 ? 4 : 2, bArr, (byte) (i >> 8), (byte) (i & 255));
        byte[] bArr2 = this.mBytes0x75;
        bArr2[2] = 6;
        bArr2[3] = 0;
        bArr2[4] = 0;
        bArr2[5] = 0;
        bArr2[6] = 0;
        bArr2[7] = 0;
        bArr2[8] = 0;
        bArr2[9] = (byte) this.mIsMute;
        sendNormalMessage(bArr2, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        this.mBtStatus = 2;
        reportBtPhoneInfo(3, bArr, 0, 0);
        byte[] bArr2 = this.mBytes0x75;
        bArr2[2] = 6;
        bArr2[3] = 0;
        bArr2[4] = 0;
        bArr2[5] = 0;
        bArr2[6] = 0;
        bArr2[7] = 0;
        bArr2[8] = 0;
        bArr2[9] = (byte) this.mIsMute;
        sendNormalMessage(bArr2, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, -1, 18, 0}, 80L);
        byte[] bArr = this.mBytes0x75;
        bArr[2] = 5;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = (byte) this.mIsMute;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), this.mBytes0x75, 160L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = b == 9 ? 9 : 8;
        this.mMusicSource = i4;
        long j3 = j2 / 1000;
        long j4 = (b3 * 60 * 60) + (b4 * 60) + b5;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -64, (byte) i4, 0, (byte) (i2 >> 8), (byte) (i2 & 255), b7, (byte) i, (byte) (j3 >> 8), (byte) (j3 & 255), (byte) (j4 >> 8), (byte) (255 & j4)});
        this.mMusicId3s[0].info = str4;
        this.mMusicId3s[1].info = str6;
        reportID3Info(this.mMusicSource, this.mMusicId3s);
        int i5 = b == 8 ? 3 : 4;
        byte[] bArr = this.mBytes0x75;
        bArr[2] = (byte) i5;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = (byte) (b9 + 1);
        bArr[8] = 0;
        bArr[9] = (byte) this.mIsMute;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), this.mBytes0x75, 240L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mMusicId3s[0].info = "";
        this.mMusicId3s[1].info = "";
        reportID3Info(this.mMusicSource, this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        int i5 = (b3 * 60 * 60) + (b4 * 60) + b5;
        int i6 = i4 / 1000;
        byte b9 = (byte) (b == 9 ? 9 : 8);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, b9, 0, (byte) (i2 >> 8), (byte) (i2 & 255), b6, (byte) i, (byte) (i6 >> 8), (byte) (i6 & 255), (byte) (i5 >> 8), (byte) (i5 & 255)});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, b9, -1, 18, 0}, 80L);
        byte[] bArr = this.mBytes0x75;
        bArr[2] = 3;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = (byte) (b8 + 1);
        bArr[8] = 0;
        bArr[9] = (byte) this.mIsMute;
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), this.mBytes0x75, 160L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), new byte[]{22, -64, 11, -1, 18, 0}, 80L);
        byte[] bArr = this.mBytes0x75;
        bArr[2] = 6;
        bArr[3] = 0;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = (byte) this.mIsMute;
        sendNormalMessage(bArr, 160L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        int i2 = z ? 2 : 1;
        this.mIsMute = i2;
        byte[] bArr = this.mBytes0x75;
        bArr[9] = (byte) i2;
        sendNormalMessage(bArr);
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }

    private String resolveAirTemperature(int i) {
        return i == 0 ? "LOW" : i == 30 ? "HIGH" : ((i + 35) / 2.0f) + getTempUnitC(this.mContext);
    }

    private TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updatePanoramicItem(boolean z) {
        ArrayList arrayList = new ArrayList();
        getLeftAndRight("_284_setting_item_25");
        arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, 0).setEnable(z));
        getLeftAndRight("_284_setting_item_26");
        arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, 0).setEnable(z));
        getLeftAndRight("_55_0xE8_data4");
        arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, "null_value").setEnable(z));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateRightViewItem(boolean z) {
        ArrayList arrayList = new ArrayList();
        getLeftAndRight("_284_setting_item_33");
        arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, 0).setEnable(z));
        int i = !SharePreUtil.getBoolValue(this.mContext, "share_is_front_radar_enter_rightview", false) ? 1 : 0;
        getLeftAndRight("_284_setting_item_34");
        arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, Integer.valueOf(i)).setEnable(z));
        getLeftAndRight("_284_open_right_view");
        arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, "null_value").setEnable(z));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mSkyWindowStatus == GeneralDoorData.skyWindowOpenLevel && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mSkyWindowStatus = GeneralDoorData.skyWindowOpenLevel;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean is0x30DataChange() {
        int i = this.m0x30Data;
        int i2 = this.mCanBusInfoInt[2];
        if (i == i2) {
            return false;
        }
        this.m0x30Data = i2;
        return true;
    }

    private boolean is0x32DataChange() {
        if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x32Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x29DataChange() {
        if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x29Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x24DataChange() {
        if (Arrays.equals(this.m0x24Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x24Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x25DataChange() {
        if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x25Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x26DataChange() {
        if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x26Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x27DataChange() {
        if (Arrays.equals(this.m0x27Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x27Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x31DataChange() {
        int i = this.m0x31Data;
        int i2 = this.mCanBusInfoInt[2];
        if (i == i2) {
            return false;
        }
        this.m0x31Data = i2;
        return true;
    }

    private boolean is0x38DataChange() {
        if (Arrays.equals(this.m0x38Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x38Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x39DataChange() {
        if (Arrays.equals(this.m0x39Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x39Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x3ADataChange() {
        if (Arrays.equals(this.m0x3AData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x3AData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x52DataChange() {
        if (Arrays.equals(this.m0x52Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x52Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0xFFDataChange() {
        if (Arrays.equals(this.m0xFFData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xFFData = Arrays.copyOf(iArr, iArr.length);
        return true;
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
            return 17;
        }
        return "AM2".equals(str) ? 18 : 0;
    }

    private int[] getFreqByteHiLo(int i, String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i2 = (int) (Double.parseDouble(str2) * i);
            iArr[0] = (byte) (i2 >> 8);
            iArr[1] = (byte) (i2 & 255);
        }
        return iArr;
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private void reportBtPhoneInfo(int i, byte[] bArr, int i2, int i3) {
        byte[] bytes = (new String(bArr) + "  ").getBytes(StandardCharsets.UTF_8);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte) i, (byte) i2, (byte) i3, 1, (byte) bytes.length}, bytes));
    }

    private class ID3 {
        private String info;
        private int line;
        private String record;

        private ID3(int i) {
            this.line = i;
            this.info = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            recordId3Info();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordId3Info() {
            this.record = this.info;
        }
    }

    private void initID3() {
        this.mMusicId3s = new ID3[]{new ID3(1), new ID3(2)};
    }

    private void reportID3Info(int i, ID3[] id3Arr) {
        for (ID3 id3 : id3Arr) {
            if (id3.isId3Change()) {
                for (ID3 id32 : id3Arr) {
                    id32.recordId3Info();
                    reportID3InfoFinal(i, id32.line, id32.info);
                }
                return;
            }
        }
    }

    private void reportID3InfoFinal(int i, int i2, String str) {
        byte[] bArrExceptBOMHead = DataHandleUtils.exceptBOMHead((str + "  ").getBytes(StandardCharsets.UTF_8));
        sendNormalMessage(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) i, -1, 18, (byte) i2, (byte) bArrExceptBOMHead.length}, bArrExceptBOMHead), i2 * 80);
    }

    private String getItem(int i) {
        return this.mSettingItemSpareArray.get(i, "null_value");
    }

    private void getLeftAndRight(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            int iIntValue = this.mSettingItemIndeHashMap.get(str).intValue();
            this.mSettingLeftIndex = iIntValue >> 8;
            this.mSettingRightIndex = iIntValue & 255;
            Log.i("ljq", "getLeftAndRigth: left:" + this.mSettingLeftIndex + ", right:" + this.mSettingRightIndex);
            return;
        }
        this.mSettingLeftIndex = -1;
        this.mSettingRightIndex = -1;
    }

    private void initSettingItemIndexSparseArray() {
        SparseArray<String> sparseArray = new SparseArray<>();
        this.mSettingItemSpareArray = sparseArray;
        sparseArray.put(1, "_250_automatic_folding_mirror");
        this.mSettingItemSpareArray.append(2, "_284_setting_item_02");
        this.mSettingItemSpareArray.append(3, "_284_setting_item_03");
        this.mSettingItemSpareArray.append(4, "auto_lock_when_drive");
        this.mSettingItemSpareArray.append(5, "auto_lock_when_stop_car");
        this.mSettingItemSpareArray.append(6, "_284_setting_item_06");
        this.mSettingItemSpareArray.append(7, "_284_setting_item_07");
        this.mSettingItemSpareArray.append(8, "_284_setting_item_08");
        this.mSettingItemSpareArray.append(9, "_284_setting_item_09");
        this.mSettingItemSpareArray.append(10, "_284_setting_item_0A");
        this.mSettingItemSpareArray.append(11, "_284_setting_item_0B");
        this.mSettingItemSpareArray.append(13, "_284_setting_item_0D");
        this.mSettingItemSpareArray.append(14, "_284_setting_item_0E");
        this.mSettingItemSpareArray.append(15, "_284_setting_item_0F");
        this.mSettingItemSpareArray.append(16, "auto_door_unlock");
        this.mSettingItemSpareArray.append(17, "_284_setting_item_11");
        this.mSettingItemSpareArray.append(18, "_284_setting_item_12");
        this.mSettingItemSpareArray.append(19, "_284_setting_item_13");
        this.mSettingItemSpareArray.append(20, "_284_setting_item_14");
        this.mSettingItemSpareArray.append(21, "_284_setting_item_15");
        this.mSettingItemSpareArray.append(22, "_284_setting_item_16");
        this.mSettingItemSpareArray.append(23, "_284_setting_item_17");
        this.mSettingItemSpareArray.append(24, "light_ctrl_3");
        this.mSettingItemSpareArray.append(25, "_163_setting_6");
        this.mSettingItemSpareArray.append(26, "_176_car_setting_title_25");
        this.mSettingItemSpareArray.append(27, "_284_setting_item_1B");
        this.mSettingItemSpareArray.append(28, "_284_setting_item_1C");
        this.mSettingItemSpareArray.append(29, "_284_setting_item_1D");
        this.mSettingItemSpareArray.append(30, "_284_setting_item_1E");
        this.mSettingItemSpareArray.append(31, "geely_emergency_brake_auto");
        this.mSettingItemSpareArray.append(32, "_284_setting_item_20");
        this.mSettingItemSpareArray.append(33, "_284_setting_item_21");
        this.mSettingItemSpareArray.append(34, "_284_setting_item_22");
        this.mSettingItemSpareArray.append(35, "_284_setting_item_23");
        this.mSettingItemSpareArray.append(36, "_284_setting_item_24");
        this.mSettingItemSpareArray.append(37, "_284_setting_item_25");
        this.mSettingItemSpareArray.append(38, "_284_setting_item_26");
        this.mSettingItemSpareArray.append(39, "_250_ambient_light_brightness");
        this.mSettingItemSpareArray.append(40, "_284_setting_item_28");
        this.mSettingItemSpareArray.append(41, "_284_setting_item_29");
        this.mSettingItemSpareArray.append(42, "_284_setting_item_2A");
        this.mSettingItemSpareArray.append(43, "_284_setting_item_2B");
        this.mSettingItemSpareArray.append(44, "_284_setting_item_2C");
        this.mSettingItemSpareArray.append(45, "_284_setting_item_2D");
        this.mSettingItemSpareArray.append(46, "_284_setting_item_2E");
        this.mSettingItemSpareArray.append(47, "_284_setting_item_2F");
        this.mSettingItemSpareArray.append(48, "_284_setting_item_30");
        this.mSettingItemSpareArray.append(49, "_284_setting_item_31");
        this.mSettingItemSpareArray.append(50, "_163_setting_1");
        this.mSettingItemSpareArray.append(51, "_284_setting_item_33");
        this.mSettingItemSpareArray.append(52, "_284_setting_item_34");
        this.mSettingItemSpareArray.append(53, "_284_setting_item_35");
        this.mSettingItemSpareArray.append(54, "_284_setting_item_36");
        this.mSettingItemSpareArray.append(55, "_284_setting_item_37");
        this.mSettingItemSpareArray.append(56, "_284_setting_item_38");
        this.mSettingItemSpareArray.append(57, "_250_language");
        this.mSettingItemSpareArray.append(58, "_284_setting_item_3A");
        this.mSettingItemSpareArray.append(59, "_284_setting_item_3B");
        this.mSettingItemSpareArray.append(60, "_284_setting_item_3C");
        this.mSettingItemSpareArray.append(61, "_284_setting_item_3D");
        this.mSettingItemSpareArray.append(62, "_284_setting_item_3E");
        this.mSettingItemSpareArray.append(63, "_284_setting_item_3F");
        this.mSettingItemSpareArray.append(64, "_220_steering_modes");
        this.mSettingItemSpareArray.append(65, "_194_window");
        this.mSettingItemSpareArray.append(66, "seatDriveProfile");
        this.mSettingItemSpareArray.append(67, "_284_setting_item_43");
        this.mSettingItemSpareArray.append(68, "_284_setting_item_44");
        this.mSettingItemSpareArray.append(69, "_284_setting_item_45");
        this.mSettingItemSpareArray.append(70, "_284_setting_item_46");
        this.mSettingItemSpareArray.append(71, "_284_setting_item_47");
        this.mSettingItemSpareArray.append(72, "_284_setting_item_48");
        this.mSettingItemSpareArray.append(73, "_284_setting_item_49");
        Log.i("ljq", "initSettingItemIndexSparseArray: ");
    }

    private UiMgr getUiMgr() {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgr;
    }

    private void initSettingsPnormaicAndRightViewItem() {
        int intValue = SharePreUtil.getIntValue(this.mContext, "share_284_support_panoramic", 0);
        int intValue2 = SharePreUtil.getIntValue(this.mContext, "share_284_support_rightview", 0);
        ArrayList arrayList = new ArrayList();
        getLeftAndRight("support_panorama");
        if (this.mSettingLeftIndex != -1 && this.mSettingRightIndex != -1) {
            arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, Integer.valueOf(intValue)));
        }
        getLeftAndRight("_284_support_right_view");
        if (this.mSettingLeftIndex != -1 && this.mSettingRightIndex != -1) {
            arrayList.add(new SettingUpdateEntity(this.mSettingLeftIndex, this.mSettingRightIndex, Integer.valueOf(intValue2)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updatePanoramicItem(intValue == 1);
        updateRightViewItem(intValue2 == 1);
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        Log.i("ljq", "initSettingsItem: ");
        this.mSettingItemIndeHashMap = new HashMap<>();
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingPageUiSet.getList().get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), Integer.valueOf(((i << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | i2));
            }
        }
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
        } else {
            handler.sendMessageDelayed(handler.obtainMessage(17, obj), j);
        }
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
        } else {
            handler.sendMessageDelayed(handler.obtainMessage(16, i, 0, obj), j);
        }
    }
}
