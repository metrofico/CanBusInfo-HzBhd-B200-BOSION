package com.hzbhd.canbus.car._168;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.fragment.OnStartPhoneMoreInfoFragment;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.service.ServiceConstants;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final String SOURCE_REAL_CHANGE_ACTION = "com.hzbhd.action.sourcerealchange";
    private static boolean isAirFirst = true;
    private static byte isDiscExist = 0;
    private static boolean isDoorFirst = true;
    private static boolean isLeftSeatCool = false;
    private static boolean isRightSeatCool = false;
    private static boolean mIsKonbClockwise = true;
    private static int up_dn_btn_data;
    private static int voice_btn_data;
    int[] mAirData;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackStatus;
    private boolean mBatteryStatus;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferentId;
    private boolean mFrontStatus;
    private boolean mFuelStatus;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int[] mTrackData;
    byte[] mcuByteArrayNoByte1;
    private boolean mIsAirFirst = true;
    private String mCurSource = "";
    private int mKonbCount = 0;
    private int mSelKonbCount = 0;
    private ArrayList<PanelKeyList> panelKeyLists = new ArrayList<>();
    private ArrayList<PanelKeyList> swcKeyLists = new ArrayList<>();
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._168.MsgMgr.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.hzbhd.action.sourcerealchange".equals(intent.getAction())) {
                MsgMgr.this.mCurSource = intent.getExtras().getString(ServiceConstants.ToSource_Flag);
            }
        }
    };

    static /* synthetic */ int access$210(MsgMgr msgMgr) {
        int i = msgMgr.mSelKonbCount;
        msgMgr.mSelKonbCount = i - 1;
        return i;
    }

    private class PanelKeyList {
        private static final int INVALID_KEY_ID = -1;
        private int canCmdId;
        private int panelKeyId;

        PanelKeyList(int i, int i2) {
            this.canCmdId = i;
            this.panelKeyId = i2;
        }

        void sendPanelKey(Context context, int i) {
            int i2 = this.panelKeyId;
            if (i2 != -1) {
                MsgMgr.this.realKeyLongClick1(context, i2, i);
            }
        }
    }

    private void addPanelKeyList() {
        ArrayList<PanelKeyList> arrayList = this.panelKeyLists;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.panelKeyLists.add(new PanelKeyList(0, 0));
        this.panelKeyLists.add(new PanelKeyList(1, HotKeyConstant.K_SLEEP));
        this.panelKeyLists.add(new PanelKeyList(2, 21));
        this.panelKeyLists.add(new PanelKeyList(3, 20));
        this.panelKeyLists.add(new PanelKeyList(4, 58));
        this.panelKeyLists.add(new PanelKeyList(5, -1));
        this.panelKeyLists.add(new PanelKeyList(6, 50));
        this.panelKeyLists.add(new PanelKeyList(7, 129));
        this.panelKeyLists.add(new PanelKeyList(8, 141));
        this.panelKeyLists.add(new PanelKeyList(9, 3));
        this.panelKeyLists.add(new PanelKeyList(10, 33));
        this.panelKeyLists.add(new PanelKeyList(11, 34));
        this.panelKeyLists.add(new PanelKeyList(12, 35));
        this.panelKeyLists.add(new PanelKeyList(13, 36));
        this.panelKeyLists.add(new PanelKeyList(14, 37));
        this.panelKeyLists.add(new PanelKeyList(15, 38));
        this.panelKeyLists.add(new PanelKeyList(16, 17));
        this.panelKeyLists.add(new PanelKeyList(17, 31));
        this.panelKeyLists.add(new PanelKeyList(18, HotKeyConstant.K_CAN_CONFIG));
        this.panelKeyLists.add(new PanelKeyList(19, HotKeyConstant.K_TIME));
        this.panelKeyLists.add(new PanelKeyList(20, 61));
        this.panelKeyLists.add(new PanelKeyList(21, 75));
        this.panelKeyLists.add(new PanelKeyList(22, 49));
        this.panelKeyLists.add(new PanelKeyList(23, 45));
        this.panelKeyLists.add(new PanelKeyList(24, 46));
        this.panelKeyLists.add(new PanelKeyList(25, 47));
        this.panelKeyLists.add(new PanelKeyList(26, 48));
        this.panelKeyLists.add(new PanelKeyList(27, 45));
        this.panelKeyLists.add(new PanelKeyList(28, 46));
        this.panelKeyLists.add(new PanelKeyList(29, 47));
        this.panelKeyLists.add(new PanelKeyList(30, 48));
        this.panelKeyLists.add(new PanelKeyList(31, 141));
        this.panelKeyLists.add(new PanelKeyList(32, -1));
        this.panelKeyLists.add(new PanelKeyList(33, -1));
        this.panelKeyLists.add(new PanelKeyList(34, -1));
        this.panelKeyLists.add(new PanelKeyList(35, -1));
        this.panelKeyLists.add(new PanelKeyList(36, 139));
        this.panelKeyLists.add(new PanelKeyList(37, -1));
        this.panelKeyLists.add(new PanelKeyList(38, 139));
        this.panelKeyLists.add(new PanelKeyList(39, 4));
        this.panelKeyLists.add(new PanelKeyList(40, 68));
        this.panelKeyLists.add(new PanelKeyList(41, 94));
        this.panelKeyLists.add(new PanelKeyList(42, 49));
        this.panelKeyLists.add(new PanelKeyList(43, 52));
        this.panelKeyLists.add(new PanelKeyList(44, 2));
        this.panelKeyLists.add(new PanelKeyList(45, 52));
    }

    private void addSwcKeyList() {
        ArrayList<PanelKeyList> arrayList = this.swcKeyLists;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.swcKeyLists.add(new PanelKeyList(0, 0));
        this.swcKeyLists.add(new PanelKeyList(1, 7));
        this.swcKeyLists.add(new PanelKeyList(2, 8));
        this.swcKeyLists.add(new PanelKeyList(3, 3));
        this.swcKeyLists.add(new PanelKeyList(4, HotKeyConstant.K_SPEECH));
        this.swcKeyLists.add(new PanelKeyList(5, 14));
        this.swcKeyLists.add(new PanelKeyList(6, 15));
        this.swcKeyLists.add(new PanelKeyList(7, -1));
        this.swcKeyLists.add(new PanelKeyList(8, 21));
        this.swcKeyLists.add(new PanelKeyList(9, 20));
        this.swcKeyLists.add(new PanelKeyList(10, 2));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._168.MsgMgr$1] */
    private void startSelKonbIncrease() {
        new Thread() { // from class: com.hzbhd.canbus.car._168.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        if (MsgMgr.this.mCurSource.equals("FM")) {
                            sleep(20L);
                        } else {
                            sleep(1200L);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MsgMgr.access$210(MsgMgr.this) <= 0) {
                        return;
                    } else {
                        MsgMgr.this.realKeyClick4(48);
                    }
                }
            }
        }.start();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._168.MsgMgr$2] */
    private void startSelKonbDecrease() {
        new Thread() { // from class: com.hzbhd.canbus.car._168.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        if (MsgMgr.this.mCurSource.equals("FM")) {
                            sleep(20L);
                        } else {
                            sleep(1200L);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MsgMgr.access$210(MsgMgr.this) == 0) {
                        return;
                    } else {
                        MsgMgr.this.realKeyClick4(47);
                    }
                }
            }
        }.start();
    }

    private void sendPanelKey(int i, int i2) {
        ArrayList<PanelKeyList> arrayList = this.panelKeyLists;
        if (arrayList != null) {
            arrayList.get(i).sendPanelKey(this.mContext, i2);
        } else {
            addPanelKeyList();
            this.panelKeyLists.get(i).sendPanelKey(this.mContext, i2);
        }
    }

    private void sendPanelNaviKey() {
        int i = this.mCanBusInfoInt[2];
        if (i == 32 || i == 33 || i == 37) {
            realKeyClick4(128);
        }
    }

    private void sendToneKey() {
        if (this.mCanBusInfoInt[2] == 5) {
            if (this.mCurSource.equals("FM")) {
                panelKeyClick(75);
            } else {
                panelKeyClick(17);
            }
        }
    }

    private void panelKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick5(context, i, iArr[2], iArr[3]);
    }

    private void sendSwcKey(int i, int i2) {
        ArrayList<PanelKeyList> arrayList = this.swcKeyLists;
        if (arrayList != null) {
            arrayList.get(i).sendPanelKey(this.mContext, i2);
        } else {
            addSwcKeyList();
            this.swcKeyLists.get(i).sendPanelKey(this.mContext, i2);
        }
    }

    private void sendPanelInitCmds() {
        int i = this.mDifferentId;
        if (i == 255) {
            CanbusMsgSender.sendMsg(new byte[]{22, 42, 0, 0});
            return;
        }
        if (i == 254) {
            CanbusMsgSender.sendMsg(new byte[]{22, 42, 1, 0});
        } else if (i == 253) {
            CanbusMsgSender.sendMsg(new byte[]{22, 42, 2, 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 42, (byte) i, 0});
        }
    }

    private boolean isDoorMsgReturn() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mBatteryStatus == GeneralDoorData.isBatteryWarning && this.mFuelStatus == GeneralDoorData.isFuelWarning) {
            return true;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mBatteryStatus = GeneralDoorData.isBatteryWarning;
        this.mFuelStatus = GeneralDoorData.isFuelWarning;
        return false;
    }

    private void setDoorData() {
        GeneralDoorData.isShowBatteryWarning = true;
        GeneralDoorData.isShowFuelWarning = true;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        this.mRightFrontRec = boolBit6;
        GeneralDoorData.isRightFrontDoorOpen = boolBit6;
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        this.mLeftFrontRec = boolBit7;
        GeneralDoorData.isLeftFrontDoorOpen = boolBit7;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        this.mRightRearRec = boolBit4;
        GeneralDoorData.isRightRearDoorOpen = boolBit4;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        this.mLeftRearRec = boolBit5;
        GeneralDoorData.isLeftRearDoorOpen = boolBit5;
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
        GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        if (isDoorMsgReturn()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setFrontRearRadar() {
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private boolean isTrackDataChange() {
        if (Arrays.equals(this.mCanBusInfoInt, this.mTrackData)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCanBusInfoInt = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setPanelKnob() {
        boolean z = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        mIsKonbClockwise = z;
        int i = z ? this.mCanBusInfoInt[3] : (255 - this.mCanBusInfoInt[3]) + 1;
        this.mKonbCount = i;
        if (i == 0) {
            return;
        }
        int i2 = this.mCanBusInfoInt[2];
        if (i2 == 1) {
            konbKeyVol(i);
            return;
        }
        if (i2 == 2 || i2 == 3) {
            if (i != 0) {
                this.mSelKonbCount = i;
            }
            if (z) {
                startSelKonbIncrease();
            } else {
                startSelKonbDecrease();
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferentId = getCurrentCanDifferentId();
        this.mContext = context;
        addPanelKeyList();
        addSwcKeyList();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.hzbhd.action.sourcerealchange");
        context.registerReceiver(this.receiver, intentFilter);
        SingletonForKt.INSTANCE.sendCarData(this.mDifferentId);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OnStarActivity);
        SingletonForKt.INSTANCE.init(context, (UiMgr) UiMgrFactory.getCanUiMgr(context));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        byte[] bArr2 = this.mCanBusInfoByte;
        byte[] bArr3 = new byte[bArr2.length - 2];
        this.mcuByteArrayNoByte1 = bArr3;
        if (bArr2.length - 2 >= 0) {
            System.arraycopy(bArr2, 2, bArr3, 0, bArr2.length - 2);
        }
        this.mContext = context;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[1];
        if (i == 33) {
            realKeyLongClick1(context, SingletonForKt.INSTANCE.set0x21Data(this.mCanBusInfoInt), this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 34) {
            SingletonForKt.INSTANCE.set0x22Data(bArr, context);
            return;
        }
        if (i == 49) {
            setAirInfo(iArr);
            return;
        }
        if (i == 50) {
            if (isDataChange(iArr)) {
                setCarInfo_0x32();
                return;
            }
            return;
        }
        if (i == 52) {
            if (isDataChange(iArr)) {
                setConsumptionTrip_0x34();
                return;
            }
            return;
        }
        if (i == 53) {
            if (isDataChange(iArr)) {
                setAcSystem_0x35();
                SingletonForKt.INSTANCE.set0x35Data(this.mCanBusInfoInt);
                updateSettingActivity(null);
                return;
            }
            return;
        }
        if (i == 65) {
            if (isDataChange(iArr)) {
                setFrontRearRadar();
                return;
            }
            return;
        }
        if (i == 117) {
            if (isDataChange(iArr)) {
                setDashboardDisplay_0x75();
                return;
            }
            return;
        }
        if (i == 133) {
            if (isDataChange(iArr)) {
                setSportMode_0x85();
                return;
            }
            return;
        }
        if (i == 180) {
            if (isDataChange(iArr)) {
                onStartCurrentNum_0xb4();
                return;
            }
            return;
        }
        if (i == 189) {
            if (isDataChange(iArr)) {
                onStarMyNum_0xbd();
                return;
            }
            return;
        }
        if (i == 240) {
            if (isDataChange(iArr)) {
                setVersionInfo();
                return;
            }
            return;
        }
        if (i == 69) {
            if (isDataChange(iArr)) {
                setCollisionDetetion_0x45();
                return;
            }
            return;
        }
        if (i == 70) {
            if (isDataChange(iArr)) {
                setCollisionDetetion_0x46();
                return;
            }
            return;
        }
        if (i == 85) {
            if (isDataChange(iArr)) {
                setComfort_0x55();
                return;
            }
            return;
        }
        if (i == 86) {
            if (isDataChange(iArr)) {
                setComfort_0x56();
                return;
            }
            return;
        }
        if (i == 177) {
            if (isDataChange(iArr)) {
                OnstarInfo_0xb1();
                return;
            }
            return;
        }
        if (i == 178) {
            onStarCallInfo_0xb2();
            return;
        }
        if (i == 194) {
            if (isDataChange(iArr)) {
                setBtPincode_0xc2();
                return;
            }
            return;
        }
        if (i != 195) {
            switch (i) {
                case 17:
                    sendSwcKey(iArr[4], iArr[5]);
                    setTrackInfo();
                    SingletonForKt.INSTANCE.set0x11Data(this.mCanBusInfoInt, (MsgMgr) Objects.requireNonNull(MsgMgrFactory.getCanMsgMgr(context)));
                    break;
                case 18:
                    SingletonForKt.INSTANCE.set0x12Data(this.mCanBusInfoInt);
                    setDoorData();
                    setCarStatusInfo();
                    updateDriveDataActivity(null);
                    break;
                case 19:
                    if (isDataChange(iArr)) {
                        setDrivingComputerInfo0();
                        break;
                    }
                    break;
                case 20:
                    if (isDataChange(iArr)) {
                        setDrivingComputerInfo1();
                        break;
                    }
                    break;
                case 21:
                    if (isDataChange(iArr)) {
                        setDrivingComputerInfo2();
                        break;
                    }
                    break;
                default:
                    switch (i) {
                        case 101:
                            if (isDataChange(iArr)) {
                                setLock_0x65();
                                break;
                            }
                            break;
                        case 102:
                            if (isDataChange(iArr)) {
                                setRemote_0x66();
                                break;
                            }
                            break;
                        case 103:
                            if (isDataChange(iArr)) {
                                setLight_0x67();
                                SingletonForKt.INSTANCE.set0x67Data(this.mCanBusInfoInt);
                                updateSettingActivity(null);
                                break;
                            }
                            break;
                        case 104:
                            if (isDataChange(iArr)) {
                                setTpms_0x68();
                                break;
                            }
                            break;
                        case 105:
                            if (isDataChange(iArr)) {
                                setCompass_0x69();
                                break;
                            }
                            break;
                    }
            }
            return;
        }
        if (isDataChange(iArr)) {
            setBtPhoneName_0xc3();
        }
    }

    private void setAirInfo(int[] iArr) {
        updateOutDoorTemp(this.mContext, ((iArr[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        iArr[13] = 0;
        if (isAirDataNoChange(iArr)) {
            return;
        }
        SingletonForKt.INSTANCE.set0x31Data(iArr);
        updateAirActivity(this.mContext, 1004);
        updateAirActivity(this.mContext, 1003);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(new byte[]{22, -111, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, isDiscExist}, (new DecimalFormat("000").format((b6 * 256) + (i & 255)) + " " + str3 + ":" + new DecimalFormat("00").format(b5 & 255) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandFm(str)) {
            if (str2.length() == 5) {
                str4 = "0" + i + " 0" + str2.substring(0, 4) + " MHz";
            } else if (str2.length() == 4) {
                str4 = "0" + i + "  " + str2.substring(0, 4) + " MHz";
            } else {
                str4 = "0" + i + " " + str2.substring(0, 5) + " MHz";
            }
        } else if (str2.length() == 3) {
            str4 = "0" + i + " " + str2 + " KHz  ";
        } else {
            str4 = "0" + i + " " + str2 + " KHz ";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -111, radioCurrentBand, isDiscExist}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        isDiscExist = (byte) i4;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        String str4 = new DecimalFormat("000").format(i4) + " " + new DecimalFormat("00").format((i / 60) % 60) + ":" + new DecimalFormat("00").format(i % 60) + "   ";
        byte b2 = 7;
        if (i6 != 1 && i6 != 2 && i6 != 3) {
            b2 = (i6 == 6 || i6 == 7) ? (byte) 6 : (byte) 0;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), DataHandleUtils.byteMerger(new byte[]{22, -111, b2, isDiscExist}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -111, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, isDiscExist}, (new DecimalFormat("000").format((b7 * 256) + (i & 255)) + " " + str2 + ":" + new DecimalFormat("00").format(b5 & 255) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -111, 10, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -111, 8, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -111, 8, isDiscExist, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, isDiscExist}, bArr), 16, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, isDiscExist}, bArr), 16, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -111, 10, isDiscExist}, bArr), 16, 32));
    }

    private int getAirWhatFrontOnly() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[2] & HotKeyConstant.K_DARK_MODE, iArr[3] & 248, iArr[4], iArr[5], iArr[6], iArr[7], iArr[8], iArr[9]};
        int i = !Arrays.equals(this.mAirFrontDataNow, iArr2) ? 1001 : -1;
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 8);
        return i;
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = i & HotKeyConstant.K_DARK_MODE;
        int i3 = iArr[3];
        int i4 = 0;
        int[] iArr2 = {i2, i3 & 248, iArr[4], iArr[5], iArr[6], iArr[7], iArr[8], iArr[9]};
        int[] iArr3 = {i & 16, i3 & 128, iArr[10], iArr[11], iArr[12]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i4 = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr3)) {
            i4 = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 8);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 5);
        return i4;
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (i == length) {
            return Arrays.copyOf(bArr, length);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void setOutDoorTem() {
        if (UiMgr.mDiffid == 254) {
            updateOutDoorTemp(this.mContext, " ");
        } else {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private String resolveOutDoorTem() {
        return ((this.mCanBusInfoInt[13] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_left_auto_wind = false;
        GeneralAirData.rear_right_auto_wind = false;
        GeneralAirData.rear_auto = false;
    }

    private String resolveRearAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i < 34 || i > 64) ? " " : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private String resolveFrontAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private void setAirData_0x31() {
        if (this.mDifferentId == 254) {
            getAirWhatFrontOnly();
        } else {
            getAirWhat();
        }
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 13);
        setOutDoorTem();
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.ac_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) == 1;
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) > 0;
        GeneralAirData.center_wheel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) >= 2 ? "Hybrid" : "";
        isRightSeatCool = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        isLeftSeatCool = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        if (isRightSeatCool) {
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_right_seat_heat_level = 0;
        } else {
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
            GeneralAirData.front_right_seat_cold_level = 0;
        }
        if (isLeftSeatCool) {
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_left_seat_heat_level = 0;
        } else {
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
            GeneralAirData.front_left_seat_cold_level = 0;
        }
        cleanAllBlow();
        int i = this.mCanBusInfoInt[6];
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
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
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        if (UiMgr.mDiffid == 255) {
            if (GeneralAirData.front_wind_level == 19 && GeneralAirData.front_left_auto_wind && GeneralAirData.aqs && GeneralAirData.ac_auto) {
                z = true;
            }
            GeneralAirData.auto = z;
        } else {
            GeneralAirData.auto = GeneralAirData.front_wind_level == 19;
        }
        GeneralAirData.front_left_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[9]);
    }

    private void setCarInfo_0x32() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[4] * 256) + iArr[5]).append(" RPM").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[6] * 256) + iArr2[7]).append(" KM/H").toString()));
        arrayList.add(new DriverUpdateEntity(0, 2, (this.mCanBusInfoInt[8] * 0.1f) + " V"));
        arrayList.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[9] + " %"));
        arrayList.add(new DriverUpdateEntity(0, 4, this.mCanBusInfoInt[10] + " L"));
        arrayList.add(new DriverUpdateEntity(0, 5, this.mCanBusInfoInt[11] + " °C"));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 6, sb3.append((iArr3[12] * 256) + iArr3[13]).append(" °KPa").toString()));
        arrayList.add(new DriverUpdateEntity(0, 7, this.mCanBusInfoInt[14] + " Pa"));
        arrayList.add(new DriverUpdateEntity(0, 8, this.mCanBusInfoInt[15] + " %"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setConsumptionTrip_0x34() {
        String str;
        String str2;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[24], 2, 1) == 1) {
            str = " " + this.mContext.getResources().getString(R.string._96_range_unit_mile);
        } else {
            str = " " + this.mContext.getResources().getString(R.string.unit_km);
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[24], 0, 2);
        if (intFromByteWithBit == 0) {
            str2 = " " + this.mContext.getResources().getString(R.string.unit_mpg);
        } else if (intFromByteWithBit == 1) {
            str2 = " " + this.mContext.getResources().getString(R.string.unit_kml);
        } else if (intFromByteWithBit == 2) {
            str2 = " " + this.mContext.getResources().getString(R.string.unit_l_100_km);
        } else {
            str2 = intFromByteWithBit != 3 ? "" : " " + this.mContext.getResources().getString(R.string.unit_l_h);
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append(DataHandleUtils.getRound(((iArr[2] * 256) + iArr[3]) * 0.1f, 1)).append(str2).toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, sb2.append(DataHandleUtils.getRound(((iArr2[4] * 256) + iArr2[5]) * 0.1f, 1)).append(str).toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb3.append(DataHandleUtils.getRound(((iArr3[6] * 256 * 256) + (iArr3[7] * 256) + iArr3[8]) * 0.1f, 1)).append(str).toString()));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, sb4.append(DataHandleUtils.getRound(((iArr4[9] * 256) + iArr4[10]) * 0.1f, 1)).append(str2).toString()));
        StringBuilder sb5 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 6, sb5.append(DataHandleUtils.getRound(((iArr5[11] * 256 * 256) + (iArr5[12] * 256) + iArr5[13]) * 0.1f, 1)).append(str).toString()));
        StringBuilder sb6 = new StringBuilder();
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, sb6.append(DataHandleUtils.getRound(((iArr6[14] * 256) + iArr6[15]) * 0.1f, 1)).append(str2).toString()));
        StringBuilder sb7 = new StringBuilder();
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 7, sb7.append(DataHandleUtils.getRound(((iArr7[16] * 256 * 256) + (iArr7[17] * 256) + iArr7[18]) * 0.1f, 1)).append(str).toString()));
        StringBuilder sb8 = new StringBuilder();
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 5, sb8.append(DataHandleUtils.getRound(((iArr8[19] * 256) + iArr8[20]) * 0.1f, 1)).append(str2).toString()));
        StringBuilder sb9 = new StringBuilder();
        int[] iArr9 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 8, sb9.append(DataHandleUtils.getRound(((iArr9[21] * 256 * 256) + (iArr9[22] * 256) + iArr9[23]) * 0.1f, 1)).append(str).toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAcSystem_0x35() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2))));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCollisionDetetion_0x45() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCollisionDetetion_0x46() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
        arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setComfort_0x55() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
        arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
        arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setComfort_0x56() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        SingletonForKt.INSTANCE.set0x56Data(this.mCanBusInfoInt);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLock_0x65() {
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        int i = this.mDifferentId;
        if (i != 252 && i != 251) {
            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2))));
        } else if (intFromByteWithBit == 2) {
            arrayList.add(new SettingUpdateEntity(3, 2, 1));
        } else if (intFromByteWithBit == 0) {
            arrayList.add(new SettingUpdateEntity(3, 2, 0));
        }
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        int i2 = this.mDifferentId;
        if (i2 != 252 && i2 != 251) {
            arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        } else if (intFromByteWithBit2 == 2) {
            arrayList.add(new SettingUpdateEntity(3, 4, 1));
        } else if (intFromByteWithBit2 == 0) {
            arrayList.add(new SettingUpdateEntity(3, 4, 0));
        }
        arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRemote_0x66() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))));
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
        arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
        arrayList.add(new SettingUpdateEntity(4, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(4, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(4, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
        arrayList.add(new SettingUpdateEntity(4, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
        arrayList.add(new SettingUpdateEntity(4, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLight_0x67() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
        arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCompass_0x69() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 0, getCalibrationStatus(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(6, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) + "").setValueStr(true));
        arrayList.add(new SettingUpdateEntity(6, 2, Float.valueOf((this.mCanBusInfoInt[3] * 3) / 2.0f)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDashboardDisplay_0x75() {
        if (this.mDifferentId != 253) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(7, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(7, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
            arrayList.add(new SettingUpdateEntity(7, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setSportMode_0x85() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(8, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(8, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void setLanguage_recNull(Context context) {
        int intValue = SharePreUtil.getIntValue(context, "__1168_SAVE_LANGUAGE", 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(9, 0, Integer.valueOf(intValue)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTpms_0x68() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            ArrayList arrayList = new ArrayList();
            String tpmsWarningStr = getTpmsWarningStr(this.mCanBusInfoInt[13]);
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(getTireEntity(0, tpmsWarningStr, getTpmsPressureNumStr(iArr[3], iArr[4]), getTpmsCheckWarningStr(this.mCanBusInfoInt[13])));
            String tpmsWarningStr2 = getTpmsWarningStr(this.mCanBusInfoInt[14]);
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(getTireEntity(1, tpmsWarningStr2, getTpmsPressureNumStr(iArr2[5], iArr2[6]), getTpmsCheckWarningStr(this.mCanBusInfoInt[14])));
            String tpmsWarningStr3 = getTpmsWarningStr(this.mCanBusInfoInt[15]);
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(getTireEntity(2, tpmsWarningStr3, getTpmsPressureNumStr(iArr3[7], iArr3[8]), getTpmsCheckWarningStr(this.mCanBusInfoInt[15])));
            String tpmsWarningStr4 = getTpmsWarningStr(this.mCanBusInfoInt[16]);
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList.add(getTireEntity(3, tpmsWarningStr4, getTpmsPressureNumStr(iArr4[9], iArr4[10]), getTpmsCheckWarningStr(this.mCanBusInfoInt[16])));
            StringBuilder sb = new StringBuilder();
            int[] iArr5 = this.mCanBusInfoInt;
            arrayList.add(getTireEntitySpare(sb.append((iArr5[11] * 256) + iArr5[12]).append("").toString()));
            GeneralTireData.dataList = arrayList;
            updateTirePressureActivity(null);
        }
    }

    private void OnstarInfo_0xb1() {
        int i = GeneralOnStartData.mOnStarStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            if (i2 == 1 || i2 == 2) {
                enterAuxIn2(this.mContext, Constant.OnStarActivity);
                openOnStarPhoneMoreInfoFragment();
            } else {
                startMainActivity(this.mContext);
                exitAuxIn2();
            }
        }
        GeneralOnStartData.mOnStarStatus = this.mCanBusInfoInt[2];
        GeneralOnStartData.mOnStarCallType = getOnStarCallType(this.mCanBusInfoInt[3]);
        GeneralOnStartData.mOnStarCallMuteSt = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1);
        updateOnStarActivity(1004);
    }

    private void onStarCallInfo_0xb2() {
        GeneralOnStartData.mOnStarCallTime = getOnStarCallTime();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        GeneralOnStartData.mOnStarRemainTime = sb.append((iArr[5] * 256) + iArr[6]).append("").toString();
        GeneralOnStartData.mOnStarEffectTime = getOnStarEffectTime();
        updateOnStarActivity(1004);
    }

    private void onStartCurrentNum_0xb4() {
        String str;
        try {
            str = new String(this.mcuByteArrayNoByte1, "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "";
        }
        GeneralOnStartData.mOnStarPhoneNum = str;
        updateOnStarActivity(1004);
    }

    private void onStarMyNum_0xbd() {
        String str;
        try {
            str = new String(this.mcuByteArrayNoByte1, "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "";
        }
        GeneralOnStartData.mOnStarMyNumber = str;
        updateOnStarActivity(1004);
    }

    private void setBtPincode_0xc2() {
        String str;
        try {
            str = new String(this.mcuByteArrayNoByte1, "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(10, 0, str).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setBtPhoneName_0xc3() {
        String str;
        try {
            str = new String(this.mcuByteArrayNoByte1, "ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(10, 1, str).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTwoDigitStr(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return i + "";
    }

    private String getOnStarCallTime() {
        return this.mCanBusInfoInt[2] + ":" + getTwoDigitStr(this.mCanBusInfoInt[3]) + ":" + getTwoDigitStr(this.mCanBusInfoInt[4]);
    }

    private String getOnStarCallType(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.normal_call);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string.collision_call);
        }
        if (i != 2) {
            return i != 3 ? "" : this.mContext.getResources().getString(R.string.roadside_assistance_call);
        }
        return this.mContext.getResources().getString(R.string.emergency_call);
    }

    private String getOnStarEffectTime() {
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        return sb.append((iArr[7] * 256) + iArr[8]).append("").toString() + this.mContext.getResources().getString(R.string.time_year) + (this.mCanBusInfoInt[9] + "") + this.mContext.getResources().getString(R.string.time_month) + (this.mCanBusInfoInt[10] + "") + this.mContext.getResources().getString(R.string.time_day);
    }

    private String getTpmsPressureNumStr(int i, int i2) {
        return ((i * 256) + i2) + "";
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, String str3) {
        String[] strArr;
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            strArr = new String[]{"", str2, str3};
        } else {
            strArr = new String[]{str, str2, str3};
            i2 = 1;
        }
        return new TireUpdateEntity(i, i2, strArr);
    }

    private TireUpdateEntity getTireEntitySpare(String str) {
        return new TireUpdateEntity(4, 0, new String[]{str});
    }

    private String getTpmsCheckWarningStr(int i) {
        return DataHandleUtils.getBoolBit2(i) ? this.mContext.getResources().getString(R.string.vm_golf7__direct_tpms_warn_check_pressure) : "";
    }

    private String getTpmsWarningStr(int i) {
        boolean boolBit1 = DataHandleUtils.getBoolBit1(i);
        if (DataHandleUtils.getBoolBit0(i)) {
            return this.mContext.getResources().getString(R.string.high_pressure_warm);
        }
        return boolBit1 ? this.mContext.getResources().getString(R.string.low_pressure_warm) : "";
    }

    private String getCalibrationStatus(boolean z) {
        Resources resources;
        int i;
        if (z) {
            resources = this.mContext.getResources();
            i = R.string.compass_calibrating;
        } else {
            resources = this.mContext.getResources();
            i = R.string.compass_calibration_finish;
        }
        return resources.getString(i);
    }

    private void setOriginalPanel() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
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
        if (iArr[3] > up_dn_btn_data) {
            realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
        if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void konbKeyVol(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (mIsKonbClockwise) {
                realKeyClick4(7);
            } else {
                realKeyClick4(8);
            }
        }
    }

    private void konbKeySel(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (mIsKonbClockwise) {
                realKeyClick4(46);
            } else {
                realKeyClick4(45);
            }
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
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
    }

    private void setDrivingComputerInfo0() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append("L/100km").toString()));
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
        arrayList.add(new DriverUpdateEntity(2, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append("L/100km").toString()));
        arrayList.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[5] + "km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrivingComputerInfo2() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append("L/100km").toString()));
        arrayList.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, z ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }

    private void settingLeft0Right12Data(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isAirDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return true;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private void openOnStarPhoneMoreInfoFragment() {
        if (SystemUtil.isForeground(this.mContext, Constant.OnStarActivity.getClassName())) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(Constant.OnStarActivity);
        intent.setFlags(268435456);
        intent.putExtra(OnStarActivity.BUNDLE_OPEN_FRAGMENT, OnStartPhoneMoreInfoFragment.class);
        this.mContext.startActivity(intent);
    }
}
