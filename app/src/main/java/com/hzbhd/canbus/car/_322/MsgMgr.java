package com.hzbhd.canbus.car._322;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;




public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_DATA_MID = 7;
    public static final int AMPLIFIER_DATA_MIN = 3;
    public static final int ORIGINAL_DEVICE_STATUS_CD = 2;
    public static final int ORIGINAL_DEVICE_STATUS_FM = 1;
    public static final int ORIGINAL_DEVICE_STATUS_NAVI = 32;
    public static final int ORIGINAL_DEVICE_STATUS_OFF = 0;
    private static final int RADAR_RANGE = 7;
    private static final String SHARE_322_AMPLIFIER_DATAS = "sahre_322_amplifier_datas";
    private static final String TAG = "_322_MsgMgr";
    private ActivePark mActivePark;
    private Context mContext;
    private int mOriginalDeviceStatus;
    private UiMgr mUiMgr;
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private int mCanId = -1;
    private byte[] mAmplifierData = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String mOriginalRadioBand = "";
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final SeatData driver = new SeatData(0, 0, 0, 0, 0, 31, null);
    private final SeatData passenger = new SeatData(0, 0, 0, 0, 0, 31, null);
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final String AMP_VOL_TAG_322 = "AMP_VOL_TAG_322";
    private final String AMP_LR_TAG_322 = "AMP_LR_TAG_322";
    private final String AMP_FR_TAG_322 = "AMP_FR_TAG_322";
    private final String AMP_BASS_TAG_322 = "AMP_BASS_TAG_322";
    private final String AMP_MID_TAG_322 = "AMP_MID_TAG_322";
    private final String AMP_TREBLE_TAG_322 = "AMP_TREBLE_TAG_322";

    public final byte[] getMAmplifierData() {
        return this.mAmplifierData;
    }

    public final int getMOriginalDeviceStatus() {
        return this.mOriginalDeviceStatus;
    }

    public final String getMOriginalRadioBand() {
        return this.mOriginalRadioBand;
    }

    public final SeatData getDriver() {
        return this.driver;
    }

    public final SeatData getPassenger() {
        return this.passenger;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        GeneralTireData.isHaveSpareTire = false;
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
        this.mActivePark = getActivePark(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
        resetAfterMute();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
        if (!isPowerOff) {
            initAmplifier(this.mContext);
        }
        toMuteAmp(isPowerOff);
    }

    public final String getAMP_VOL_TAG_322() {
        return this.AMP_VOL_TAG_322;
    }

    public final String getAMP_LR_TAG_322() {
        return this.AMP_LR_TAG_322;
    }

    public final String getAMP_FR_TAG_322() {
        return this.AMP_FR_TAG_322;
    }

    public final String getAMP_BASS_TAG_322() {
        return this.AMP_BASS_TAG_322;
    }

    public final String getAMP_MID_TAG_322() {
        return this.AMP_MID_TAG_322;
    }

    public final String getAMP_TREBLE_TAG_322() {
        return this.AMP_TREBLE_TAG_322;
    }

    private final void toMuteAmp(boolean isPowerOff) {
        if (isPowerOff) {
            SharePreUtil.setIntValue(this.mContext, this.AMP_VOL_TAG_322, GeneralAmplifierData.volume);
            SharePreUtil.setIntValue(this.mContext, this.AMP_LR_TAG_322, GeneralAmplifierData.leftRight);
            SharePreUtil.setIntValue(this.mContext, this.AMP_FR_TAG_322, GeneralAmplifierData.frontRear);
            SharePreUtil.setIntValue(this.mContext, this.AMP_BASS_TAG_322, GeneralAmplifierData.bandBass);
            SharePreUtil.setIntValue(this.mContext, this.AMP_MID_TAG_322, GeneralAmplifierData.bandMiddle);
            SharePreUtil.setIntValue(this.mContext, this.AMP_TREBLE_TAG_322, GeneralAmplifierData.bandTreble);
            CanbusMsgSender.sendMsg(new byte[]{22, -109, 0, (byte) (GeneralAmplifierData.leftRight + 10), (byte) (GeneralAmplifierData.frontRear + 10), (byte) (GeneralAmplifierData.bandBass + 3), (byte) (GeneralAmplifierData.bandMiddle + 3), (byte) (GeneralAmplifierData.bandTreble + 3), 8});
            return;
        }
        resetAfterMute();
    }

    private final void resetAfterMute() {
        CanbusMsgSender.sendMsg(new byte[]{22, -109, (byte) SharePreUtil.getIntValue(this.mContext, this.AMP_VOL_TAG_322, 15), (byte) SharePreUtil.getIntValue(this.mContext, this.AMP_LR_TAG_322, 10), (byte) SharePreUtil.getIntValue(this.mContext, this.AMP_FR_TAG_322, 10), (byte) SharePreUtil.getIntValue(this.mContext, this.AMP_BASS_TAG_322, 10), (byte) SharePreUtil.getIntValue(this.mContext, this.AMP_MID_TAG_322, 10), (byte) SharePreUtil.getIntValue(this.mContext, this.AMP_TREBLE_TAG_322, 10), 8});
    }

    private final void initAmplifier(Context context) {
        List listSplit$default;
        String stringValue = null;
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
            stringValue = SharePreUtil.getStringValue(context, SHARE_322_AMPLIFIER_DATAS, null);
        }
        byte[][] bArr = new byte[2][];
        bArr[0] = new byte[]{22, -127, 1};
        byte[] bArr2 = {22, -109, 0, 0, 0, 0, 0, 0, 0};
        if (stringValue == null || (listSplit$default = StringsKt.split$default((CharSequence) stringValue, new String[]{","}, false, 0, 6, (Object) null)) == null || listSplit$default.size() <= 8) {
            Log.i(TAG, "initAmplifier: ampData[" + stringValue + ']');
            bArr2[2] = (byte) GeneralAmplifierData.volume;
            bArr2[3] = (byte) (GeneralAmplifierData.leftRight + 7 + 3);
            bArr2[4] = (byte) (GeneralAmplifierData.frontRear + 7 + 3);
            bArr2[5] = (byte) (GeneralAmplifierData.bandBass + 3);
            bArr2[6] = (byte) (GeneralAmplifierData.bandMiddle + 3);
            bArr2[7] = (byte) (GeneralAmplifierData.bandTreble + 3);
            bArr2[8] = 4;
        } else {
            for (int i = 2; i < 9; i++) {
                bArr2[i] = Byte.parseByte((String) listSplit$default.get(i));
            }
            bArr2[8] = (byte) (bArr2[8] + 4);
        }
        Unit unit = Unit.INSTANCE;
        bArr[1] = bArr2;
        final Iterator it = ArrayIteratorKt.iterator(bArr);
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initAmplifier$3$1$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (it.hasNext()) {
                    CanbusMsgSender.sendMsg(it.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = canbusInfo;
        Parser parser = this.mParserArray.get(byteArrayToIntArray[1]);
        if (parser != null) {
            parser.parse(this.mCanbusInfoInt.length - 2);
        }
    }

    private final void initParsers(final Context context) {
        SparseArray<Parser> sparseArray = this.mParserArray;
        sparseArray.put(1, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x01】方向盘按键");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                switch (this.this$0.mCanbusInfoInt[2]) {
                    case 0:
                        realKeyLongClick1(0);
                        break;
                    case 1:
                        realKeyLongClick1(7);
                        break;
                    case 2:
                        realKeyLongClick1(8);
                        break;
                    case 3:
                        realKeyLongClick1(HotKeyConstant.K_DOWN_HANGUP);
                        break;
                    case 4:
                        realKeyLongClick1(HotKeyConstant.K_UP_PICKUP);
                        break;
                    case 5:
                        realKeyLongClick1(2);
                        break;
                    case 6:
                        realKeyLongClick1(HotKeyConstant.K_SPEECH);
                        break;
                    case 7:
                        realKeyLongClick1(3);
                        break;
                    case 8:
                        realKeyLongClick1(HotKeyConstant.K_PHONE_ON_OFF);
                        break;
                    case 9:
                        realKeyLongClick1(45);
                        break;
                    case 10:
                        realKeyLongClick1(46);
                        break;
                    case 11:
                        realKeyLongClick1(47);
                        break;
                    case 12:
                        realKeyLongClick1(48);
                        break;
                    case 13:
                        realKeyLongClick1(49);
                        break;
                }
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }
        });
        sparseArray.put(2, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x02】面板按键");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                if (i == 0) {
                    realKeyLongClick1(0);
                    return;
                }
                if (i == 9) {
                    realKeyLongClick1(HotKeyConstant.K_SLEEP);
                    return;
                }
                if (i == 10) {
                    realKeyLongClick1(2);
                    return;
                }
                if (i == 14) {
                    realKeyLongClick1(45);
                    return;
                }
                if (i == 15) {
                    realKeyLongClick1(46);
                    return;
                }
                if (i == 80) {
                    realKeyClick31(7);
                    return;
                }
                if (i != 81) {
                    switch (i) {
                        case 47:
                            realKeyLongClick1(152);
                            break;
                        case 48:
                            realKeyLongClick1(4);
                            break;
                        case 49:
                            realKeyLongClick1(48);
                            break;
                        case 50:
                            realKeyLongClick1(47);
                            break;
                        case 51:
                            realKeyLongClick1(31);
                            break;
                    }
                    return;
                }
                realKeyClick31(8);
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }

            private final void realKeyClick31(int value) {
                if (this.this$0.mCanbusInfoInt[3] == 0) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyClick3_1(context, value, msgMgr.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }
        });
        sparseArray.append(3, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$3
            {
                super(this.this$0, "【0x03】车速信息");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    MsgMgr msgMgr = this.this$0;
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr2 = this.this$0;
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) msgMgr2.mDriveItemIndexHashMap.get("_283_meter_value_2");
                    if (driverUpdateEntity != null) {
                        arrayList.add(driverUpdateEntity.setValue((msgMgr2.mCanbusInfoInt[3] | (msgMgr2.mCanbusInfoInt[2] << 8)) + " km/h"));
                    }
                    msgMgr.updateGeneralDriveData(arrayList);
                    this.this$0.updateDriveDataActivity(null);
                    MsgMgr msgMgr3 = this.this$0;
                    msgMgr3.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(msgMgr3.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[2]));
                }
            }
        });
        sparseArray.append(4, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$4
            private int doorStatus;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x04】车辆状态信息");
                GeneralDoorData.isShowSeatBelt = true;
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDoorStatusChange()) {
                    GeneralDoorData.isLeftFrontDoorOpen = ((this.this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
                    GeneralDoorData.isRightFrontDoorOpen = ((this.this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
                    GeneralDoorData.isLeftRearDoorOpen = ((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1;
                    GeneralDoorData.isRightRearDoorOpen = ((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1;
                    GeneralDoorData.isBackOpen = ((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1;
                    GeneralDoorData.isFrontOpen = ((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1;
                    GeneralDoorData.isSeatBeltTie = (this.this$0.mCanbusInfoInt[2] & 1) == 1;
                    this.this$0.updateDoorView(context);
                }
            }

            private final boolean isDoorStatusChange() {
                int i = this.this$0.mCanbusInfoInt[3] | this.this$0.mCanbusInfoInt[2];
                if (Integer.valueOf(i).equals(Integer.valueOf(this.doorStatus))) {
                    return false;
                }
                this.doorStatus = i;
                return true;
            }
        });
        sparseArray.append(5, new MsgMgr$initParsers$1$5(this, context));
        sparseArray.append(6, new MsgMgr$initParsers$1$6(context, this));
        sparseArray.append(7, new MsgMgr$initParsers$1$7(this));
        sparseArray.append(9, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x09】方向盘转角信息");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], (byte) 0, 128, 255, 16);
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        sparseArray.append(33, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$9
            {
                super(this.this$0, "【0x21】原车Radio状态信息");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.this$0.getMOriginalDeviceStatus() == 1 && isDataChange()) {
                    String str = "";
                    GeneralOriginalCarDeviceData.runningState = ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1 ? "SCAN" : "";
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    int i = msgMgr.mCanbusInfoInt[2] & 7;
                    if (i == 1) {
                        str = "AM1";
                    } else if (i == 2) {
                        str = "AM2";
                    } else if (i == 3) {
                        str = "FM1";
                    } else if (i == 4) {
                        str = "FM2";
                    } else if (i == 5) {
                        str = "FM-AST";
                    }
                    msgMgr.mOriginalRadioBand = str;
                    arrayList.add(new OriginalCarDeviceUpdateEntity(0, msgMgr.getMOriginalRadioBand()));
                    arrayList.add(new OriginalCarDeviceUpdateEntity(1, MsgMgr.m530initParsers$lambda5$getFreq(msgMgr, (msgMgr.mCanbusInfoInt[3] << 8) | msgMgr.mCanbusInfoInt[4])));
                    GeneralOriginalCarDeviceData.mList = arrayList;
                    if (StringsKt.contains$default((CharSequence) this.this$0.getMOriginalRadioBand(), (CharSequence) "FM", false, 2, (Object) null)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 1, 0});
                    } else if (StringsKt.contains$default((CharSequence) this.this$0.getMOriginalRadioBand(), (CharSequence) "AM", false, 2, (Object) null)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 2, 0});
                    }
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(34, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$10
            {
                super(this.this$0, "【0x22】原车Radio预置台信息");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.this$0.getMOriginalDeviceStatus() == 1 && isDataChange()) {
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    int i = msgMgr.mCanbusInfoInt[2] & 7;
                    for (int i2 = 1; i2 < 7; i2++) {
                        int i3 = i2 * 2;
                        arrayList.add(new SongListEntity('P' + i2 + " - " + MsgMgr.m530initParsers$lambda5$getFreq(msgMgr, (msgMgr.mCanbusInfoInt[i3 + 1] << 8) | msgMgr.mCanbusInfoInt[i3 + 2])).setSelected(Integer.valueOf(i).equals(Integer.valueOf(i2))));
                    }
                    GeneralOriginalCarDeviceData.songList = arrayList;
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(35, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x23】原车CD信息");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                String str;
                if (this.this$0.getMOriginalDeviceStatus() == 2 && isDataChange()) {
                    Context context2 = context;
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 1) {
                        str = "device_run_status_4";
                    } else if (i == 2) {
                        str = "device_run_status_5";
                    } else if (i == 3) {
                        str = "_16_value_32";
                    } else if (i != 128) {
                        switch (i) {
                            case 5:
                                str = "_143_0xA2_status6";
                                break;
                            case 6:
                                str = "_118_setting_value_91";
                                break;
                            case 7:
                                str = "_118_setting_value_92";
                                break;
                            case 8:
                                str = "_322_eject";
                                break;
                            case 9:
                                str = "_123_divice_status_9";
                                break;
                            default:
                                str = "null_value";
                                break;
                        }
                    } else {
                        str = "_118_setting_value_89";
                    }
                    GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context2, str);
                    GeneralOriginalCarDeviceData.rdm = ((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1;
                    GeneralOriginalCarDeviceData.rpt = (this.this$0.mCanbusInfoInt[3] & 1) == 1;
                    int[] iArr = {(this.this$0.mCanbusInfoInt[4] << 8) | this.this$0.mCanbusInfoInt[5], (this.this$0.mCanbusInfoInt[6] << 8) | this.this$0.mCanbusInfoInt[7]};
                    GeneralOriginalCarDeviceData.startTime = getTime(iArr[0]);
                    GeneralOriginalCarDeviceData.endTime = getTime(iArr[1]);
                    int i2 = iArr[1];
                    if (i2 == 0) {
                        GeneralOriginalCarDeviceData.progress = 0;
                    } else {
                        GeneralOriginalCarDeviceData.progress = (int) ((iArr[0] * 100.0f) / i2);
                    }
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    arrayList.add(new OriginalCarDeviceUpdateEntity(0, String.valueOf((msgMgr.mCanbusInfoInt[8] << 8) | msgMgr.mCanbusInfoInt[9])));
                    arrayList.add(new OriginalCarDeviceUpdateEntity(1, String.valueOf(msgMgr.mCanbusInfoInt[11] | (msgMgr.mCanbusInfoInt[10] << 8))));
                    GeneralOriginalCarDeviceData.mList = arrayList;
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }

            private final String getTime(int value) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                int i = value / 60;
                String str = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i / 60), Integer.valueOf(i % 60), Integer.valueOf(value % 60)}, 3));

                return str;
            }
        });
        RadarInfoUtil.mMinIsClose = true;
        sparseArray.append(42, new MsgMgr$initParsers$1$12(this, context));
        sparseArray.append(43, new MsgMgr$initParsers$1$13(this, context));
        sparseArray.append(64, new MsgMgr$initParsers$1$14(this));
        sparseArray.append(80, new MsgMgr$initParsers$1$15(this, context));
        sparseArray.append(96, new Parser(context) { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$16
            final /* synthetic */ Context $context;
            private final SparseArray<OriginalCarDevicePageUiSet> originalPageArray;
            private OriginalCarDevicePageUiSet pageUiSet;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x60】原车主机状态信息");
                this.$context = context;
                UiMgr uiMgr = this.this$0.mUiMgr;
                if (uiMgr == null) {

                    uiMgr = null;
                }
                this.pageUiSet = uiMgr.getOriginalCarDevicePageUiSet(context);
                SparseArray<OriginalCarDevicePageUiSet> sparseArray2 = new SparseArray<>();
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet = new OriginalCarDevicePageUiSet();
                originalCarDevicePageUiSet.setHavePlayTimeSeekBar(false);
                originalCarDevicePageUiSet.setHaveSongList(true);
                originalCarDevicePageUiSet.setRowTopBtnAction(new String[]{OriginalBtnAction.FM, OriginalBtnAction.AM, "band"});
                originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"up", OriginalBtnAction.PREV_DISC, OriginalBtnAction.NEXT_DISC, "down"});
                ArrayList arrayList = new ArrayList();
                arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
                arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", ""));
                originalCarDevicePageUiSet.setItems(arrayList);
                Unit unit = Unit.INSTANCE;
                sparseArray2.put(1, originalCarDevicePageUiSet);
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet2 = new OriginalCarDevicePageUiSet();
                originalCarDevicePageUiSet2.setHavePlayTimeSeekBar(true);
                originalCarDevicePageUiSet2.setHaveSongList(false);
                originalCarDevicePageUiSet2.setRowTopBtnAction(new String[]{OriginalBtnAction.RDM, OriginalBtnAction.RPT});
                originalCarDevicePageUiSet2.setRowBottomBtnAction(new String[]{"left", "up", OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, "down", "right"});
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_track", ""));
                arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "total_file", ""));
                originalCarDevicePageUiSet2.setItems(arrayList2);
                Unit unit2 = Unit.INSTANCE;
                sparseArray2.append(2, originalCarDevicePageUiSet2);
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet3 = new OriginalCarDevicePageUiSet();
                originalCarDevicePageUiSet3.setHavePlayTimeSeekBar(false);
                originalCarDevicePageUiSet3.setHaveSongList(false);
                originalCarDevicePageUiSet3.setRowTopBtnAction(null);
                originalCarDevicePageUiSet3.setRowBottomBtnAction(null);
                originalCarDevicePageUiSet3.setItems(CollectionsKt.emptyList());
                sparseArray2.append(0, originalCarDevicePageUiSet3);
                sparseArray2.append(3, originalCarDevicePageUiSet3);
                this.originalPageArray = sparseArray2;
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                String str;
                int i = this.this$0.mCanbusInfoInt[2] & 7;
                MsgMgr msgMgr = this.this$0;
                Context context2 = this.$context;
                if (!(i >= 0 && i < 4) || Integer.valueOf(i).equals(Integer.valueOf(msgMgr.getMOriginalDeviceStatus()))) {
                    return;
                }
                msgMgr.mOriginalDeviceStatus = i;
                if (i == 0) {
                    str = "OFF";
                } else if (i == 1) {
                    str = "RADIO";
                } else if (i != 2) {
                    str = i != 3 ? " " : "NAVI";
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 3, 0});
                    str = "CD";
                }
                GeneralOriginalCarDeviceData.cdStatus = str;
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet = this.originalPageArray.get(i);
                if (originalCarDevicePageUiSet != null) {

                    this.pageUiSet.setHavePlayTimeSeekBar(originalCarDevicePageUiSet.isHavePlayTimeSeekBar());
                    this.pageUiSet.setHaveSongList(originalCarDevicePageUiSet.isHaveSongList());
                    this.pageUiSet.setRowTopBtnAction(originalCarDevicePageUiSet.getRowTopBtnAction());
                    this.pageUiSet.setRowBottomBtnAction(originalCarDevicePageUiSet.getRowBottomBtnAction());
                    this.pageUiSet.setItems(originalCarDevicePageUiSet.getItems());
                }
                GeneralOriginalCarDeviceData.runningState = " ";
                ArrayList arrayList = new ArrayList();
                int size = this.pageUiSet.getItems().size();
                for (int i2 = 0; i2 < size; i2++) {
                    arrayList.add(new OriginalCarDeviceUpdateEntity(i2, ""));
                }
                GeneralOriginalCarDeviceData.mList = arrayList;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                msgMgr.updateOriginalCarDeviceActivity(bundle);
                if (i != 0) {
                    if (i == 1 || i == 2) {
                        msgMgr.enterAuxIn2(context2, Constant.OriginalDeviceActivity);
                        return;
                    } else if (i != 3) {
                        return;
                    }
                }
                msgMgr.exitAuxIn2();
            }
        });
        sparseArray.append(112, new MsgMgr$initParsers$1$17(this, context));
        sparseArray.append(113, new Parser() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x71】软件版本信息");
            }

            @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initParsers$lambda-5$getFreq, reason: not valid java name */
    public static final String m530initParsers$lambda5$getFreq(MsgMgr msgMgr, int i) {
        return StringsKt.contains$default((CharSequence) msgMgr.mOriginalRadioBand, (CharSequence) "FM", false, 2, (Object) null) ? (i / 100) + " MHz" : StringsKt.contains$default((CharSequence) msgMgr.mOriginalRadioBand, (CharSequence) "AM", false, 2, (Object) null) ? i + " KHz" : "";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
        Log.i(TAG, "sourceSwitchChange: " + source);
        if (Intrinsics.areEqual("AUX2", source)) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 4, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -104, isFormat24H ? (byte) 1 : (byte) 0, (byte) bHours24H, (byte) bMins, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._322.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m529dateTimeRepCanbus$lambda6();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dateTimeRepCanbus$lambda-6, reason: not valid java name */
    public static final void m529dateTimeRepCanbus$lambda6() {
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 113});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    
    abstract class Parser {
        private final int PARSE_LISTENERS_LENGTH;
        private int[] canbusInfo;
        private final OnParseListener[] onParseListeners;
        final /* synthetic */ MsgMgr this$0;

        public Parser(MsgMgr msgMgr, String msg) {

            this.this$0 = msgMgr;
            OnParseListener[] onParseListeners = setOnParseListeners();
            this.onParseListeners = onParseListeners;
            int length = onParseListeners.length;
            this.PARSE_LISTENERS_LENGTH = length;
            Log.i(MsgMgr.TAG, "Parser: " + msg + " length " + length);
        }

        public final int[] getCanbusInfo() {
            return this.canbusInfo;
        }

        public final void setCanbusInfo(int[] iArr) {
            this.canbusInfo = iArr;
        }

        public void parse(int dataLength) {
            for (int iMin = Math.min(dataLength, this.PARSE_LISTENERS_LENGTH); iMin > 0; iMin--) {
                OnParseListener onParseListener = this.onParseListeners[iMin - 1];
                if (onParseListener != null) {
                    onParseListener.onParse();
                }
            }
        }

        public final boolean isDataChange() {
            if (Arrays.equals(this.canbusInfo, this.this$0.mCanbusInfoInt)) {
                return false;
            }
            int[] iArr = this.this$0.mCanbusInfoInt;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);

            this.canbusInfo = iArrCopyOf;
            return true;
        }

        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[0];
        }
    }

    private final void initItemsIndexHashMap(Context context) {
        Log.i(TAG, "initItems: ");
        UiMgr uiMgr = this.mUiMgr;
        if (uiMgr == null) {

            uiMgr = null;
        }
        Iterator<SettingPageUiSet.ListBean> it = uiMgr.getSettingUiSet(context).getList().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (SettingPageUiSet.ListBean.ItemListBean itemListBean : it.next().getItemList()) {
                HashMap<String, SettingUpdateEntity<Object>> map = this.mSettingItemIndexHashMap;
                String titleSrn = itemListBean.getTitleSrn();

                map.put(titleSrn, new SettingUpdateEntity<>(i, i3));
                i3++;
            }
            i = i2;
        }
        Iterator<DriverDataPageUiSet.Page> it2 = uiMgr.getDriverDataPageUiSet(context).getList().iterator();
        int i4 = 0;
        while (it2.hasNext()) {
            int i5 = i4 + 1;
            int i6 = 0;
            for (DriverDataPageUiSet.Page.Item item : it2.next().getItemList()) {
                HashMap<String, DriverUpdateEntity> map2 = this.mDriveItemIndexHashMap;
                String titleSrn2 = item.getTitleSrn();

                map2.put(titleSrn2, new DriverUpdateEntity(i4, i6, "null_value"));
                i6++;
            }
            i4 = i5;
        }
    }

    public final ActivePark getActivePark(Context context) {

        if (this.mActivePark == null) {
            this.mActivePark = new ActivePark(context);
        }
        ActivePark activePark = this.mActivePark;

        return activePark;
    }

    /* compiled from: MsgMgr.kt */
    
    public static final /* data */ class SeatData {
        private int backseat;
        private int cushion;
        private int high;
        private int low;
        private int middle;

        public SeatData() {
            this(0, 0, 0, 0, 0, 31, null);
        }

        public static /* synthetic */ SeatData copy$default(SeatData seatData, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
            if ((i6 & 1) != 0) {
                i = seatData.high;
            }
            if ((i6 & 2) != 0) {
                i2 = seatData.middle;
            }
            int i7 = i2;
            if ((i6 & 4) != 0) {
                i3 = seatData.low;
            }
            int i8 = i3;
            if ((i6 & 8) != 0) {
                i4 = seatData.backseat;
            }
            int i9 = i4;
            if ((i6 & 16) != 0) {
                i5 = seatData.cushion;
            }
            return seatData.copy(i, i7, i8, i9, i5);
        }

        /* renamed from: component1, reason: from getter */
        public final int getHigh() {
            return this.high;
        }

        /* renamed from: component2, reason: from getter */
        public final int getMiddle() {
            return this.middle;
        }

        /* renamed from: component3, reason: from getter */
        public final int getLow() {
            return this.low;
        }

        /* renamed from: component4, reason: from getter */
        public final int getBackseat() {
            return this.backseat;
        }

        /* renamed from: component5, reason: from getter */
        public final int getCushion() {
            return this.cushion;
        }

        public final SeatData copy(int high, int middle, int low, int backseat, int cushion) {
            return new SeatData(high, middle, low, backseat, cushion);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeatData)) {
                return false;
            }
            SeatData seatData = (SeatData) other;
            return this.high == seatData.high && this.middle == seatData.middle && this.low == seatData.low && this.backseat == seatData.backseat && this.cushion == seatData.cushion;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.high) * 31) + Integer.hashCode(this.middle)) * 31) + Integer.hashCode(this.low)) * 31) + Integer.hashCode(this.backseat)) * 31) + Integer.hashCode(this.cushion);
        }

        public String toString() {
            return "SeatData(high=" + this.high + ", middle=" + this.middle + ", low=" + this.low + ", backseat=" + this.backseat + ", cushion=" + this.cushion + ')';
        }

        public SeatData(int i, int i2, int i3, int i4, int i5) {
            this.high = i;
            this.middle = i2;
            this.low = i3;
            this.backseat = i4;
            this.cushion = i5;
        }

        public /* synthetic */ SeatData(int i, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
            this((i6 & 1) != 0 ? 0 : i, (i6 & 2) != 0 ? 0 : i2, (i6 & 4) != 0 ? 0 : i3, (i6 & 8) != 0 ? 0 : i4, (i6 & 16) != 0 ? 0 : i5);
        }

        public final int getBackseat() {
            return this.backseat;
        }

        public final int getCushion() {
            return this.cushion;
        }

        public final int getHigh() {
            return this.high;
        }

        public final int getLow() {
            return this.low;
        }

        public final int getMiddle() {
            return this.middle;
        }

        public final void setBackseat(int i) {
            this.backseat = i;
        }

        public final void setCushion(int i) {
            this.cushion = i;
        }

        public final void setHigh(int i) {
            this.high = i;
        }

        public final void setLow(int i) {
            this.low = i;
        }

        public final void setMiddle(int i) {
            this.middle = i;
        }
    }
}
