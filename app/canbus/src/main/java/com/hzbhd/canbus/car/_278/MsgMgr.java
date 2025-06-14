package com.hzbhd.canbus.car._278;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.service.ServiceConstants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    protected static final byte AMP_BAL_OFFSET = 0;
    protected static final byte AMP_EQ_OFFSET = 1;
    protected static final byte AMP_FRONT_REAR_OFFSET = 10;
    protected static final byte AMP_VOL_OFFSET = 22;
    protected static final String CAN_278_SAVE_AMP_BASS = "__278_SAVE_AMP_BASS";
    protected static final String CAN_278_SAVE_AMP_FR = "__278_SAVE_AMP_FR";
    protected static final String CAN_278_SAVE_AMP_LR = "__278_SAVE_AMP_LR";
    protected static final String CAN_278_SAVE_AMP_MID = "__278_SAVE_AMP_MID";
    protected static final String CAN_278_SAVE_AMP_TRE = "__278_SAVE_AMP_TRE";
    protected static final String CAN_278_SAVE_RADAR_DISP = "__278_SAVE_RADAR_DISP";
    private static final String SOURCE_REAL_CHANGE_ACTION = "com.hzbhd.action.sourcerealchange";
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static boolean isSeatFirst = true;
    private static boolean isWarnFirst = true;
    private static boolean mIsKonbClockwise = true;
    protected static int maxRadarLen = 7;
    protected static int trackType;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
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
    private int mFrontCameraStatusNow;
    private int[] mFrontSeatNew;
    private int mMediaStatusNow;
    private int mPanoramicStatusNow;
    private int[] mRearSeatNew;
    byte[] mcuByteArrayNoByte1;
    private boolean mIsAirFirst = true;
    private boolean mIsAirRearFirst = true;
    private boolean mIsRightCameraFirst = true;
    private String mCurSource = "";
    private int mKonbCount = 0;
    private int mLastKonbCount = 0;
    private int mSelKonbCount = 0;
    private int mLackClickSt = 0;
    private int seatRearData = 0;
    private ArrayList<PanelKeyList> panelKeyLists = new ArrayList<>();
    private ArrayList<PanelKeyList> swcKeyLists = new ArrayList<>();
    private int panleMuteKeySt = 0;
    private int panleMuteKeyId = 0;
    private boolean mIsPowerOff = false;
    int[] stringLevIds = {R.string._278_lev0, R.string._278_lev1, R.string._278_lev2, R.string._278_lev3, R.string._278_lev4, R.string._278_lev5};
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._278.MsgMgr.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.hzbhd.action.sourcerealchange".equals(intent.getAction())) {
                MsgMgr.this.mCurSource = intent.getExtras().getString(ServiceConstants.ToSource_Flag);
            }
        }
    };

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    int getLimitVal(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    static /* synthetic */ int access$410(MsgMgr msgMgr) {
        int i = msgMgr.mSelKonbCount;
        msgMgr.mSelKonbCount = i - 1;
        return i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.hzbhd.canbus.car._278.MsgMgr$1] */
    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, CAN_278_SAVE_AMP_FR, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, CAN_278_SAVE_AMP_LR, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, CAN_278_SAVE_AMP_BASS, 0);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, CAN_278_SAVE_AMP_MID, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, CAN_278_SAVE_AMP_TRE, 0);
        }
        new Thread() { // from class: com.hzbhd.canbus.car._278.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -122, 6, (byte) (GeneralAmplifierData.frontRear + 10)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -122, 5, (byte) (GeneralAmplifierData.leftRight + 10)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -122, 4, (byte) (GeneralAmplifierData.bandBass + 0)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -122, 3, (byte) (GeneralAmplifierData.bandTreble + 0)});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -122, 2, (byte) (GeneralAmplifierData.bandMiddle + 0)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{MsgMgr.AMP_VOL_OFFSET, -122, 1, 32});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        this.mIsPowerOff = z;
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private void answerAndPhoneOffIsOneKey(Context context) {
        FutureUtil.instance.setIsOneKey(this.mDifferentId == 4 ? 1 : 0);
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
            if (i2 == -1 || i == 0) {
                return;
            }
            if (i2 == 7 || i2 == 8) {
                if (i >= 1) {
                    MsgMgr.this.realKeyClick(context, i2);
                }
            } else if (i2 == 45 || i2 == 46) {
                MsgMgr.this.realKeyClick4(i2);
            } else {
                MsgMgr.this.realKeyClick5(context, i2, this.canCmdId, i);
            }
        }
    }

    private void sendPanelNaviKey() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 54 && iArr[3] == 1) {
            realKeyClick4(128);
        }
    }

    private void addPanelKeyList() {
        ArrayList<PanelKeyList> arrayList = this.panelKeyLists;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.panelKeyLists.add(new PanelKeyList(0, 0));
        this.panelKeyLists.add(new PanelKeyList(1, HotKeyConstant.K_SLEEP));
        this.panelKeyLists.add(new PanelKeyList(2, -1));
        this.panelKeyLists.add(new PanelKeyList(3, -1));
        this.panelKeyLists.add(new PanelKeyList(4, -1));
        this.panelKeyLists.add(new PanelKeyList(5, -1));
        this.panelKeyLists.add(new PanelKeyList(6, -1));
        this.panelKeyLists.add(new PanelKeyList(7, 129));
        this.panelKeyLists.add(new PanelKeyList(8, -1));
        this.panelKeyLists.add(new PanelKeyList(9, -1));
        this.panelKeyLists.add(new PanelKeyList(10, -1));
        this.panelKeyLists.add(new PanelKeyList(11, -1));
        this.panelKeyLists.add(new PanelKeyList(12, -1));
        this.panelKeyLists.add(new PanelKeyList(13, -1));
        this.panelKeyLists.add(new PanelKeyList(14, -1));
        this.panelKeyLists.add(new PanelKeyList(15, -16));
        this.panelKeyLists.add(new PanelKeyList(16, -1));
        this.panelKeyLists.add(new PanelKeyList(17, -1));
        this.panelKeyLists.add(new PanelKeyList(18, -1));
        this.panelKeyLists.add(new PanelKeyList(19, -1));
        this.panelKeyLists.add(new PanelKeyList(20, -1));
        this.panelKeyLists.add(new PanelKeyList(21, -1));
        this.panelKeyLists.add(new PanelKeyList(22, -1));
        this.panelKeyLists.add(new PanelKeyList(23, -1));
        this.panelKeyLists.add(new PanelKeyList(24, -1));
        this.panelKeyLists.add(new PanelKeyList(25, -1));
        this.panelKeyLists.add(new PanelKeyList(26, -1));
        this.panelKeyLists.add(new PanelKeyList(27, -1));
        this.panelKeyLists.add(new PanelKeyList(28, -1));
        this.panelKeyLists.add(new PanelKeyList(29, -1));
        this.panelKeyLists.add(new PanelKeyList(30, -1));
        this.panelKeyLists.add(new PanelKeyList(31, -1));
        this.panelKeyLists.add(new PanelKeyList(32, -1));
        this.panelKeyLists.add(new PanelKeyList(33, 7));
        this.panelKeyLists.add(new PanelKeyList(34, 8));
        this.panelKeyLists.add(new PanelKeyList(35, -1));
        this.panelKeyLists.add(new PanelKeyList(36, -1));
        this.panelKeyLists.add(new PanelKeyList(37, -1));
        this.panelKeyLists.add(new PanelKeyList(38, -1));
        this.panelKeyLists.add(new PanelKeyList(39, -1));
        this.panelKeyLists.add(new PanelKeyList(40, -1));
        this.panelKeyLists.add(new PanelKeyList(41, 47));
        this.panelKeyLists.add(new PanelKeyList(42, -1));
        this.panelKeyLists.add(new PanelKeyList(43, -1));
        this.panelKeyLists.add(new PanelKeyList(44, -1));
        this.panelKeyLists.add(new PanelKeyList(45, -1));
        this.panelKeyLists.add(new PanelKeyList(46, -1));
        this.panelKeyLists.add(new PanelKeyList(47, -1));
        this.panelKeyLists.add(new PanelKeyList(48, 48));
        this.panelKeyLists.add(new PanelKeyList(49, 2));
        this.panelKeyLists.add(new PanelKeyList(50, 52));
        this.panelKeyLists.add(new PanelKeyList(51, 14));
        this.panelKeyLists.add(new PanelKeyList(52, 15));
        this.panelKeyLists.add(new PanelKeyList(53, 58));
        this.panelKeyLists.add(new PanelKeyList(54, -1));
        this.panelKeyLists.add(new PanelKeyList(55, -1));
        this.panelKeyLists.add(new PanelKeyList(56, -1));
        this.panelKeyLists.add(new PanelKeyList(57, -1));
        this.panelKeyLists.add(new PanelKeyList(58, 50));
        this.panelKeyLists.add(new PanelKeyList(59, 27));
        this.panelKeyLists.add(new PanelKeyList(60, 4));
        this.panelKeyLists.add(new PanelKeyList(61, -1));
        this.panelKeyLists.add(new PanelKeyList(62, -1));
        this.panelKeyLists.add(new PanelKeyList(63, -1));
        this.panelKeyLists.add(new PanelKeyList(64, 45));
        this.panelKeyLists.add(new PanelKeyList(65, 46));
        this.panelKeyLists.add(new PanelKeyList(66, 47));
        this.panelKeyLists.add(new PanelKeyList(67, 48));
        this.panelKeyLists.add(new PanelKeyList(68, 17));
    }

    private void sndPanelMuteKey(int i, int i2) {
        if (this.mIsPowerOff) {
            if (this.panleMuteKeyId == 9 && this.panleMuteKeySt == 1 && i2 == 0) {
                realKeyClick(this.mContext, HotKeyConstant.K_SLEEP);
            }
        } else if (this.panleMuteKeyId == 9 && this.panleMuteKeySt == 1 && i2 == 0) {
            realKeyClick(this.mContext, 3);
        } else if (i == 9 && i2 == 2 && this.panleMuteKeySt != 2) {
            realKeyClick(this.mContext, HotKeyConstant.K_SLEEP);
        }
        this.panleMuteKeyId = i;
        this.panleMuteKeySt = i2;
    }

    private void addSwcKeyList() {
        ArrayList<PanelKeyList> arrayList = this.swcKeyLists;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.swcKeyLists.add(new PanelKeyList(0, -1));
        this.swcKeyLists.add(new PanelKeyList(1, 7));
        this.swcKeyLists.add(new PanelKeyList(2, 8));
        this.swcKeyLists.add(new PanelKeyList(3, -1));
        this.swcKeyLists.add(new PanelKeyList(4, -1));
        this.swcKeyLists.add(new PanelKeyList(5, -1));
        this.swcKeyLists.add(new PanelKeyList(6, -1));
        this.swcKeyLists.add(new PanelKeyList(7, 2));
        this.swcKeyLists.add(new PanelKeyList(8, -1));
        this.swcKeyLists.add(new PanelKeyList(9, 14));
        this.swcKeyLists.add(new PanelKeyList(10, 15));
        this.swcKeyLists.add(new PanelKeyList(11, 21));
        this.swcKeyLists.add(new PanelKeyList(12, 20));
        this.swcKeyLists.add(new PanelKeyList(13, 3));
        this.swcKeyLists.add(new PanelKeyList(14, 3));
    }

    private void startSelKonbIncrease() {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._278.MsgMgr.2
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (true) {
                    try {
                        if (MsgMgr.this.mCurSource.equals("FM")) {
                            Thread.sleep(20L);
                        } else {
                            Thread.sleep(1200L);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MsgMgr.access$410(MsgMgr.this) <= 0) {
                        return;
                    } else {
                        MsgMgr.this.realKeyClick4(48);
                    }
                }
            }
        }).start();
    }

    private void startSelKonbDecrease() {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._278.MsgMgr.3
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                while (true) {
                    try {
                        if (MsgMgr.this.mCurSource.equals("FM")) {
                            Thread.sleep(20L);
                        } else {
                            Thread.sleep(1200L);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (MsgMgr.access$410(MsgMgr.this) == 0) {
                        return;
                    } else {
                        MsgMgr.this.realKeyClick4(47);
                    }
                }
            }
        }).start();
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

    private void sendPanelAirKey() {
        if (this.mLackClickSt == 0) {
            int[] iArr = this.mCanBusInfoInt;
            if (iArr[3] == 1) {
                switch (iArr[2]) {
                    case 55:
                        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -124, 28, 1});
                        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -124, 28, 0});
                        break;
                    case 56:
                        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -124, 29, 1});
                        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -124, 29, 0});
                        break;
                    case 57:
                        UiMgr.sendAirCommandFrontWindMode();
                        break;
                }
            }
        }
        this.mLackClickSt = this.mCanBusInfoInt[3];
    }

    private boolean isFrontCameraStatusChange() {
        int i = this.mCanBusInfoInt[2];
        if (this.mFrontCameraStatusNow == i) {
            if (this.mIsRightCameraFirst) {
                this.mIsRightCameraFirst = false;
            }
            return false;
        }
        this.mFrontCameraStatusNow = i;
        return true;
    }

    private void enterRightCam() {
        CommUtil.isMiscReverse();
        if (isFrontCameraStatusChange()) {
            switchFCamera(this.mContext, this.mCanBusInfoInt[2] == 1);
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
        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -123, (byte) this.mDifferentId});
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

    private void setDoorData() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void setFrontRadar() {
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRearRadar() {
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int i = maxRadarLen;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(i, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = (i * 256) + i2;
        if (trackType == 0) {
            if (i3 > 0 && i3 <= 5500) {
                GeneralParkData.trackAngle = (i3 * 40) / 5500;
            } else {
                if (i3 <= 60036 || i3 >= 65535) {
                    if (i3 == 0) {
                        GeneralParkData.trackAngle = 0;
                        updateParkUi(null, this.mContext);
                        return;
                    }
                    return;
                }
                GeneralParkData.trackAngle = ((-(65535 - i3)) * 40) / 5500;
            }
            updateParkUi(null, this.mContext);
            return;
        }
        int i4 = ((i * 256) + i2) / 20;
        if (DataHandleUtils.getBoolBit0(i2)) {
            GeneralParkData.trackAngle = (i4 * 40) / 510;
        } else {
            GeneralParkData.trackAngle = -((i4 * 40) / 510);
        }
        if (GeneralParkData.trackAngle <= -40) {
            GeneralParkData.trackAngle = -40;
        }
        if (GeneralParkData.trackAngle >= 40) {
            GeneralParkData.trackAngle = 40;
        }
        updateParkUi(null, this.mContext);
    }

    private void setPanelKnob() {
        int i = this.mCanBusInfoInt[2];
        int i2 = this.mLastKonbCount;
        mIsKonbClockwise = i > i2;
        if (i2 >= 255 && i > 0) {
            mIsKonbClockwise = true;
        }
        if (i2 == 1 && i == 255) {
            mIsKonbClockwise = false;
        }
        boolean z = mIsKonbClockwise;
        if (!z) {
            i = (255 - i) + 1;
        }
        this.mKonbCount = i;
        if (i == 0) {
            return;
        }
        if (i != 0) {
            this.mSelKonbCount = i;
        }
        if (z) {
            startSelKonbIncrease();
        } else {
            startSelKonbDecrease();
        }
        this.mLastKonbCount = this.mCanBusInfoInt[2];
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        int currentCanDifferentId = getCurrentCanDifferentId();
        this.mDifferentId = currentCanDifferentId;
        if (currentCanDifferentId == 3 || currentCanDifferentId == 1 || currentCanDifferentId == 2 || currentCanDifferentId == 4) {
            maxRadarLen = 4;
        }
        if (currentCanDifferentId == 4 || currentCanDifferentId == 8 || currentCanDifferentId == 12) {
            trackType = 1;
        }
        initAmplifierData(context);
        initRadarDisp(context);
        answerAndPhoneOffIsOneKey(context);
        sendPanelInitCmds();
        addPanelKeyList();
        addSwcKeyList();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.hzbhd.action.sourcerealchange");
        context.registerReceiver(this.receiver, intentFilter);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        byte[] bArr2;
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mcuByteArrayNoByte1 = new byte[this.mCanBusInfoByte.length - 2];
        int i = 0;
        while (true) {
            bArr2 = this.mCanBusInfoByte;
            if (i >= bArr2.length - 2) {
                break;
            }
            this.mcuByteArrayNoByte1[i] = bArr2[i + 2];
            i++;
        }
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[1];
        if (i2 == 48) {
            if (isDataChange(iArr)) {
                setTrackInfo();
                return;
            }
            return;
        }
        if (i2 == 49) {
            if (isDataChange(iArr)) {
                setCarSet_0x31();
                return;
            }
            return;
        }
        if (i2 == 64) {
            enterRightCam();
            return;
        }
        if (i2 != 127) {
            switch (i2) {
                case 33:
                    sendSwcKey(iArr[2], iArr[3]);
                    break;
                case 34:
                    sendPanelKey(iArr[2], iArr[3]);
                    sendPanelNaviKey();
                    sendPanelAirKey();
                    int[] iArr2 = this.mCanBusInfoInt;
                    sndPanelMuteKey(iArr2[2], iArr2[3]);
                    break;
                case 35:
                    setAirData_0x23();
                    break;
                default:
                    switch (i2) {
                        case 38:
                            if (isDataChange(iArr)) {
                                setRearRadar();
                                break;
                            }
                            break;
                        case 39:
                            if (!isRadarMsgReturn(bArr2)) {
                                setFrontRadar();
                                break;
                            }
                            break;
                        case 40:
                            if (!isDoorMsgReturn(bArr)) {
                                setDoorData();
                                break;
                            }
                            break;
                        case 41:
                            if (isDataChange(iArr)) {
                                setCarInfo_1_0x29();
                                break;
                            }
                            break;
                        default:
                            switch (i2) {
                                case 53:
                                    if (isDataChange(iArr)) {
                                        setSeatSet_0x35();
                                        break;
                                    }
                                    break;
                                case 54:
                                    if (isDataChange(iArr)) {
                                        setCarInfo_2_0x36();
                                        break;
                                    }
                                    break;
                                case 55:
                                    if (isDataChange(iArr)) {
                                        setAmplifierData0x37();
                                        break;
                                    }
                                    break;
                            }
                    }
            }
            return;
        }
        if (isDataChange(iArr)) {
            setVersionInfo();
        }
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
        if (this.mCanBusInfoInt[7] == 255) {
            updateOutDoorTemp(this.mContext, " ");
        } else {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[7];
        return (i < 0 || i > 250) ? "" : ((this.mCanBusInfoInt[7] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private boolean isRearFirst() {
        if (!this.mIsAirRearFirst) {
            return false;
        }
        this.mIsAirRearFirst = false;
        return !GeneralAirData.rear_power;
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
    }

    private void cleanRearBlow() {
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_left_auto_wind = false;
        GeneralAirData.rear_right_auto_wind = false;
    }

    private String resolveRearAirTemp(int i) {
        return i == 0 ? "LO" : i == 255 ? "HI" : (((i - 116) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
    }

    private String resolveFrontAirTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        if (i >= 1 && i <= 17) {
            return "LEV: " + i;
        }
        if (i >= 112 && i <= 144) {
            return (((i - 116) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
        }
        return "LEV: " + i;
    }

    private void setAirData_0x23() {
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 7);
        setOutDoorTem();
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        cleanAllBlow();
        switch (this.mCanBusInfoInt[3]) {
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearAirDatas_0x34() {
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isRearFirst()) {
            return;
        }
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        cleanRearBlow();
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        }
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[3];
        GeneralAirData.rear_temperature = resolveRearAirTemp(this.mCanBusInfoInt[12]);
        if (!Arrays.equals(this.mAirRearDataNow, this.mCanBusInfoInt)) {
            updateAirActivity(this.mContext, 1002);
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mAirRearDataNow = Arrays.copyOf(iArr, iArr.length);
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, CAN_278_SAVE_AMP_FR, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, CAN_278_SAVE_AMP_LR, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, CAN_278_SAVE_AMP_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, CAN_278_SAVE_AMP_TRE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, CAN_278_SAVE_AMP_MID, GeneralAmplifierData.bandMiddle);
    }

    private void setAmplifierData0x37() {
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[7] - 10;
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[6] - 10;
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[3];
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[4];
        updateAmplifierActivity(null);
        saveAmplifierData();
        Log.i("setAmplifierData0x37", "setAmplifierData0x37  mCanBusInfoInt[9]=" + this.mCanBusInfoInt[9]);
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[8];
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(i + (-1) >= 0 ? i - 1 : 0)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(this.mCanBusInfoInt[9])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarInfo_2_0x36() throws Resources.NotFoundException {
        String string;
        String string2;
        String string3;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            string = this.mContext.getResources().getString(R.string._278_down);
        } else {
            string = this.mContext.getResources().getString(R.string._278_up);
        }
        arrayList.add(new DriverUpdateEntity(1, 0, string + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°"));
        arrayList.add(new DriverUpdateEntity(1, 1, this.mCanBusInfoInt[3] == 255 ? "--" : this.mCanBusInfoInt[3] + "%"));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            string2 = this.mContext.getResources().getString(R.string._278_right);
        } else {
            string2 = this.mContext.getResources().getString(R.string._278_left);
        }
        arrayList.add(new DriverUpdateEntity(1, 2, string2 + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7) + "°"));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            string3 = this.mContext.getResources().getString(R.string._278_mount);
        } else {
            string3 = this.mContext.getResources().getString(R.string._278_unmount);
        }
        arrayList.add(new DriverUpdateEntity(1, 3, string3));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, sb.append((iArr[6] * 256) + iArr[7]).append(" KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private double getRoundHalfUp(double d, int i) {
        return new BigDecimal(d).setScale(i, RoundingMode.HALF_DOWN).doubleValue();
    }

    private float getRound(float f) {
        float round = DataHandleUtils.getRound(f, 3);
        int i = (int) (100.0f * round);
        if (Math.abs(i) % 10 >= 5) {
            round = i > 0 ? round + 0.05f : round - 0.05f;
        }
        return DataHandleUtils.getRound(round, 1);
    }

    private void setCarInfo_1_0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getRound((this.mCanBusInfoInt[2] * 0.75f) - 48.0f) + "℃"));
        arrayList.add(new DriverUpdateEntity(0, 1, getRound(this.mCanBusInfoInt[3] - 40) + " ℃"));
        arrayList.add(new DriverUpdateEntity(0, 2, getRound(this.mCanBusInfoInt[4] * 0.25f) + "V"));
        arrayList.add(new DriverUpdateEntity(0, 3, getRound(this.mCanBusInfoInt[5] * 0.59f) + "Kpa"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSet_0x31() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getLimitVal(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(getLimitVal(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(getLimitVal(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4)))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
        arrayList.add(new SettingUpdateEntity(0, 15, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 16, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(0, 17, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 18, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void initRadarDisp(Context context) {
        int intValue = SharePreUtil.getIntValue(context, CAN_278_SAVE_RADAR_DISP, 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 20, Integer.valueOf(intValue)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    String getResString(int i) {
        return this.mContext.getResources().getString(i);
    }

    private void setSeatSet_0x35() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4)])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(2, 1, getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(2, 2, getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4)])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(2, 3, getResString(this.stringLevIds[DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)])).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        if (GeneralAirData.front_left_seat_heat_level > 3) {
            GeneralAirData.front_left_seat_heat_level = 0;
        }
        if (GeneralAirData.front_right_seat_heat_level > 3) {
            GeneralAirData.front_right_seat_heat_level = 0;
        }
        if (GeneralAirData.rear_left_seat_heat_level > 3) {
            GeneralAirData.rear_left_seat_heat_level = 0;
        }
        if (GeneralAirData.front_left_seat_cold_level > 3) {
            GeneralAirData.front_left_seat_cold_level = 0;
        }
        if (GeneralAirData.front_right_seat_cold_level > 3) {
            GeneralAirData.front_right_seat_cold_level = 0;
        }
        this.mFrontSeatNew = new int[]{GeneralAirData.front_left_seat_heat_level, GeneralAirData.front_right_seat_heat_level, GeneralAirData.front_left_seat_cold_level, GeneralAirData.front_right_seat_cold_level};
        int[] iArr = {GeneralAirData.front_left_seat_heat_level, GeneralAirData.front_right_seat_heat_level, GeneralAirData.front_left_seat_cold_level, GeneralAirData.front_right_seat_cold_level};
        if (isSeatFirst) {
            this.mAirFrontDataNow = Arrays.copyOf(iArr, 4);
            isSeatFirst = false;
        } else {
            if (Arrays.equals(this.mAirFrontDataNow, iArr)) {
                return;
            }
            this.mAirFrontDataNow = Arrays.copyOf(iArr, 4);
            updateAirActivity(this.mContext, 1001);
        }
    }

    public void setLanguage_recNull(Context context) {
        int intValue = SharePreUtil.getIntValue(context, "__1168_SAVE_LANGUAGE", 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(9, 0, Integer.valueOf(intValue)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realKeyClick4(int i) {
        realKeyClick(this.mContext, i);
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

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{AMP_VOL_OFFSET, -125, 3, (byte) i8, (byte) i6});
    }

    private void settingLeft0Right12Data(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
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
}
