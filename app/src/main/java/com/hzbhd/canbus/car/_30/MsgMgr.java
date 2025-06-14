package com.hzbhd.canbus.car._30;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.car._30.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
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
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String SHARE_30_LANGUAGE = "share_30_language";
    private static final String TAG = "_1030_MsgMgr";
    static final int[] THEME_COLOR_INDEXES = {3, 6, 9, 12, 15, 18};
    DecimalFormat decimalFormat;
    private boolean is15Carnival;
    private boolean is1819KiaSportage;
    private boolean is18SonataHybrid;
    private boolean is1920Tucson;
    private boolean is19K3NewEnergy;
    private boolean is19K5NewEnergy;
    private boolean is19KiaK3;
    private boolean is19Lafesta;
    private boolean is19Santafe;
    private boolean is20EncinoElectrical;
    private boolean is20K3;
    private boolean isK4;
    private boolean isKx5Top;
    int mCanId;
    private SparseArray<int[]> mCanbusDataArray;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private Context mContext;
    int mDifferent;
    private HashMap<String, DriverUpdateEntity> mDriveItemIndeHashMap;
    int mEachId;
    private boolean mIsBackOpen;
    private boolean mIsFrontOpen;
    private boolean mIsLeftFrontOpen;
    private boolean mIsLeftRearOpen;
    private boolean mIsRightFrontOpen;
    private boolean mIsRightRearOpen;
    private SparseArray<Parser> mParserArray;
    private SeatStatusView mSeatStatusView;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap;
    private Page mSettingPage;
    private UiMgr mUiMgr;
    Handler mHandler = new Handler(Looper.getMainLooper());
    Runnable mVersionRequestRunnable = new Runnable() { // from class: com.hzbhd.canbus.car._30.MsgMgr$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE, 0});
        }
    };
    private int mVolume = 28;
    private String TirePressure1000 = "NO INFO";
    private String TirePressure0100 = "NO INFO";
    private String TirePressure0010 = "NO INFO";
    private String TirePressure0001 = "NO INFO";
    private String TireTempture1000 = "NO INFO";
    private String TireTempture0100 = "NO INFO";
    private String TireTempture0010 = "NO INFO";
    private String TireTempture0001 = "NO INFO";
    private boolean TireSense1000 = false;
    private boolean TireSense0100 = false;
    private boolean TireSense0010 = false;
    private boolean TireSense0001 = false;
    private boolean BatteryStatus1000 = false;
    private boolean BatteryStatus0100 = false;
    private boolean BatteryStatus0010 = false;
    private boolean BatteryStatus0001 = false;
    private int SenseTempture1000 = 0;
    private int SenseTempture0100 = 0;
    private int SenseTempture0010 = 0;
    private int SenseTempture0001 = 0;
    private int SensePressure1000 = 0;
    private int SensePressure0100 = 0;
    private int SensePressure0010 = 0;
    private int SensePressure0001 = 0;
    private boolean Leak1000 = false;
    private boolean Leak0100 = false;
    private boolean Leak0010 = false;
    private boolean Leak0001 = false;
    List<TireUpdateEntity> MTire0 = new ArrayList();
    List<TireUpdateEntity> MTire1 = new ArrayList();
    List<TireUpdateEntity> MTire2 = new ArrayList();
    List<TireUpdateEntity> MTire3 = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    interface OnParseListener {
        void onParse();
    }

    private interface Sentence {
        void execute();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
        sendCarInfo();
        sendLanguageInit(context);
        GeneralTireData.isHaveSpareTire = false;
    }

    public void sendCarInfo() {
        if (this.mEachId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 1});
        }
        if (this.mEachId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 2});
        }
        if (this.mEachId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 3});
        }
        if (this.mEachId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 4});
        }
        if (this.mEachId == 129) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, -127});
        }
        if (this.mEachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 5});
        }
        if (this.mEachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 6});
        }
        if (this.mEachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 7});
        }
        if (this.mEachId == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 8});
        }
        if (this.mEachId == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 9});
        }
        if (this.mEachId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 10});
        }
        if (this.mEachId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 11});
        }
        if (this.mEachId == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
        }
        if (this.mEachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
        }
        if (this.mEachId == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, -124});
        }
        if (this.mEachId == 18) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 14});
        }
        if (this.mEachId == 19) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 15});
        }
        if (this.mEachId == 20) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 16});
        }
        if (this.mEachId == 21) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 17});
        }
        if (this.mEachId == 23) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 18});
        }
        if (this.mEachId == 22) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 32, 19});
        }
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._30.MsgMgr.1
            final Iterator<byte[]> iterator;

            {
                this.iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1, 1}, new byte[]{22, 5, (byte) MsgMgr.this.mVolume}, new byte[]{22, 7, (byte) (GeneralAmplifierData.frontRear + 7), (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, 8, (byte) GeneralAmplifierData.bandBass, (byte) GeneralAmplifierData.bandMiddle, (byte) GeneralAmplifierData.bandTreble}}).iterator();
            }

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
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        initVehicleType();
        initSettingsItem(context);
        initDriveItem(context);
        this.mCanbusDataArray = new SparseArray<>();
        initParser(context);
        this.mSeatStatusView = new SeatStatusView(context);
        initPgae();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = bArr;
        if (this.mParserArray.get(byteArrayToIntArray[1]) != null) {
            this.mParserArray.get(this.mCanbusInfoInt[1]).parse(this.mCanbusInfoInt.length - 2);
        }
        int[] iArr = this.mCanbusInfoInt;
        if (iArr[1] == 125 && iArr[2] == 8) {
            set0x7D0x08TrackData(context);
        }
        int i = this.mCanbusInfoInt[1];
        if (i == 8) {
            setTirePresAndTemp0x08();
        } else {
            if (i != 9) {
                return;
            }
            setTireSenseStatus0x09();
        }
    }

    private void setTireSenseStatus0x09() {
        this.TireSense0001 = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[3]);
        this.TireSense0010 = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[3]);
        this.TireSense0100 = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[3]);
        this.TireSense1000 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[3]);
        this.BatteryStatus1000 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[5]);
        this.BatteryStatus0100 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6]);
        this.BatteryStatus0010 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[7]);
        this.BatteryStatus0001 = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[8]);
        this.SenseTempture1000 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 4, 3);
        this.SenseTempture0100 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 4, 3);
        this.SenseTempture0010 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 4, 3);
        this.SenseTempture0001 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[8], 4, 3);
        this.SensePressure1000 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 1, 3);
        this.SensePressure0100 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 1, 3);
        this.SensePressure0010 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 1, 3);
        this.SensePressure0001 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[8], 1, 3);
        this.Leak1000 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[5]);
        this.Leak0100 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[6]);
        this.Leak0010 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[7]);
        this.Leak0001 = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[8]);
        setTireInfo();
    }

    private void setTirePresAndTemp0x08() {
        this.decimalFormat = new DecimalFormat("0.0");
        this.TirePressure1000 = this.decimalFormat.format((this.mCanbusInfoInt[2] * 1.378d) + 100.0d) + "Kpa";
        this.TirePressure0100 = this.decimalFormat.format((this.mCanbusInfoInt[4] * 1.378d) + 100.0d) + "Kpa";
        this.TirePressure0010 = this.decimalFormat.format((this.mCanbusInfoInt[6] * 1.378d) + 100.0d) + "Kpa";
        this.TirePressure0001 = this.decimalFormat.format((this.mCanbusInfoInt[8] * 1.378d) + 100.0d) + "Kpa";
        this.TireTempture1000 = this.decimalFormat.format((this.mCanbusInfoInt[3] * 1) - 40) + getTempUnitC(this.mContext);
        this.TireTempture0100 = this.decimalFormat.format((this.mCanbusInfoInt[5] * 1) - 40) + getTempUnitC(this.mContext);
        this.TireTempture0010 = this.decimalFormat.format((this.mCanbusInfoInt[7] * 1) - 40) + getTempUnitC(this.mContext);
        this.TireTempture0001 = this.decimalFormat.format((this.mCanbusInfoInt[9] * 1) - 40) + getTempUnitC(this.mContext);
        setTireInfo();
    }

    private void setTireInfo() {
        this.MTire0.add(getTireEntity(0, this.TirePressure1000, this.TireTempture1000, this.TireSense1000, this.BatteryStatus1000, this.SenseTempture1000, this.SensePressure1000, this.Leak1000));
        this.MTire1.add(getTireEntity(1, this.TirePressure0100, this.TireTempture0100, this.TireSense0100, this.BatteryStatus0100, this.SenseTempture0100, this.SensePressure0100, this.Leak0100));
        this.MTire2.add(getTireEntity(2, this.TirePressure0010, this.TireTempture0010, this.TireSense0010, this.BatteryStatus0010, this.SenseTempture0010, this.SensePressure0010, this.Leak0010));
        this.MTire3.add(getTireEntity(3, this.TirePressure0001, this.TireTempture0001, this.TireSense0001, this.BatteryStatus0001, this.SenseTempture0001, this.SensePressure0001, this.Leak0001));
        GeneralTireData.dataList = mergeList(this.MTire0, this.MTire1, this.MTire2, this.MTire3);
        updateTirePressureActivity(null);
    }

    private void initParser(final Context context) {
        SparseArray<Parser> sparseArray = new SparseArray<>();
        this.mParserArray = sparseArray;
        sparseArray.put(1, new AnonymousClass2("0x01 室外温度信息", context));
        this.mParserArray.append(2, new AnonymousClass3("0x02 方控面板按键信息", context));
        this.mParserArray.append(3, new AnonymousClass4("0x03 空调信息", context));
        this.mParserArray.append(4, new AnonymousClass5("0x04 雷达信息", context));
        this.mParserArray.append(5, new AnonymousClass6("0x05 车门信息", context));
        this.mParserArray.append(6, new AnonymousClass7("0x06 空调Climate按键信息", context));
        this.mParserArray.append(7, new AnonymousClass8("0x07 背光调节信息"));
        this.mParserArray.append(WorkQueueKt.MASK, new Parser("0x7F 版本信息") { // from class: com.hzbhd.canbus.car._30.MsgMgr.9
            @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
            public OnParseListener[] setOnParseListeners() {
                return null;
            }

            @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
            public void parse(int i) {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        this.mParserArray.append(64, new AnonymousClass10("0x40 全景影像状态", context));
        this.mParserArray.append(65, new AnonymousClass11("0x41 泊车导航设置状态"));
        this.mParserArray.append(66, new AnonymousClass12("0x42 后置摄像头影像状态"));
        this.mParserArray.append(81, new AnonymousClass13("0x51 座椅位置变化信息"));
        this.mParserArray.append(82, new Parser("0x52 车辆设置信息") { // from class: com.hzbhd.canbus.car._30.MsgMgr.14
            @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
            public OnParseListener[] setOnParseListeners() {
                return null;
            }

            @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
            public void parse(int i) {
                ArrayList arrayList = new ArrayList();
                switch (MsgMgr.this.mCanbusInfoInt[2]) {
                    case 1:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_213_car_set15_76").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 2:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_auto_temp_circulation").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 3:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_third_left_backrest_fold").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[3] - 1)));
                        break;
                    case 4:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_third_right_backrest_fold").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[3] - 1)));
                        break;
                    case 5:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_29_steering_wheel_heater").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 6:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_29_seat_heat_ventil").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 7:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_seat_status_change_tip").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 8:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_back_camera_open_hold").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 10:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_internal_circulation").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 11:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_284_setting_item_47").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[3] - 1)));
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_theme_color").setEnable(MsgMgr.this.mCanbusInfoInt[3] == 1));
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_single_color").setEnable(MsgMgr.this.mCanbusInfoInt[3] == 2));
                        break;
                    case 12:
                        SettingUpdateEntity settingUpdateEntity = MsgMgr.this.getSettingUpdateEntity("_30_theme_color");
                        MsgMgr msgMgr = MsgMgr.this;
                        arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(msgMgr.getThemeColorIndex(msgMgr.mCanbusInfoInt[3]))));
                        break;
                    case 13:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_single_color").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[3] - 1)));
                        break;
                    case 14:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("_283_media_titls_2").setValue(Integer.valueOf(2 - MsgMgr.this.mCanbusInfoInt[3])));
                        break;
                    case 15:
                        arrayList.add(MsgMgr.this.getSettingUpdateEntity("brightness").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[3] - 1)).setProgress(MsgMgr.this.mCanbusInfoInt[3] - 2));
                        break;
                }
                MsgMgr.this.updateGeneralSettingData(arrayList);
                MsgMgr.this.updateSettingActivity(null);
            }
        });
        this.mParserArray.append(83, new AnonymousClass15("0x53 新能源设置信息1"));
        this.mParserArray.append(84, new AnonymousClass16("0x54 新能源设置信息2"));
        this.mParserArray.append(85, new AnonymousClass17("0x55 新能源设置信息3", context));
        this.mParserArray.append(86, new AnonymousClass18("0x56 新能源设置信息4", context));
        this.mParserArray.append(87, new AnonymousClass19("0x57 新能源设置信息5"));
        this.mParserArray.append(255, new AnonymousClass20("0xFF"));
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$2, reason: invalid class name */
    class AnonymousClass2 extends Parser {
        String temperature;
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            final Context context = this.val$context;
            final Context context2 = this.val$context;
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$2$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m453lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$2(context);
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$2$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m454lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$2(context2);
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$2, reason: not valid java name */
        /* synthetic */ void m453lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$2(Context context) {
            if (MsgMgr.this.mCanbusInfoInt[2] != 255) {
                if (MsgMgr.this.mCanbusInfoInt[2] != 128) {
                    this.temperature = (MsgMgr.this.mCanbusInfoInt[2] & WorkQueueKt.MASK) + MsgMgr.this.getTempUnitC(context);
                    if (DataHandleUtils.getBoolBit7(MsgMgr.this.mCanbusInfoInt[2])) {
                        this.temperature = "-" + this.temperature;
                        return;
                    }
                    return;
                }
                this.temperature = "-";
            }
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$2, reason: not valid java name */
        /* synthetic */ void m454lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$2(Context context) {
            if (MsgMgr.this.mCanbusInfoInt[3] != 255) {
                this.temperature = MsgMgr.this.mCanbusInfoInt[3] + MsgMgr.this.getTempUnitF(context);
            }
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                this.temperature = " ";
                super.parse(i);
                if (this.temperature.equals("-")) {
                    return;
                }
                MsgMgr.this.updateOutDoorTemp(this.val$context, this.temperature);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$3, reason: invalid class name */
    class AnonymousClass3 extends Parser {
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            final Context context = this.val$context;
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$3$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m455lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$3(context);
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$3, reason: not valid java name */
        /* synthetic */ void m455lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$3(Context context) {
            int i = MsgMgr.this.mCanbusInfoInt[2];
            if (i == 0) {
                MsgMgr.this.realKeyLongClick1(context, 0);
                return;
            }
            if (i == 132) {
                MsgMgr.this.realKeyClick3_1(context, 7);
                return;
            }
            if (i != 133) {
                switch (i) {
                    case 16:
                        MsgMgr.this.realKeyLongClick1(context, 3);
                        break;
                    case 17:
                        MsgMgr.this.realKeyLongClick1(context, 2);
                        break;
                    case 18:
                        MsgMgr.this.realKeyLongClick1(context, 46);
                        break;
                    case 19:
                        MsgMgr.this.realKeyLongClick1(context, 45);
                        break;
                    case 20:
                        MsgMgr.this.realKeyLongClick1(context, 7);
                        break;
                    case 21:
                        MsgMgr.this.realKeyLongClick1(context, 8);
                        break;
                    case 22:
                        MsgMgr.this.realKeyLongClick1(context, 14);
                        break;
                    case 23:
                        MsgMgr.this.realKeyLongClick1(context, 15);
                        break;
                    case 24:
                        MsgMgr.this.realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
                        break;
                    case 25:
                        MsgMgr.this.realKeyClick3_1(context, 7);
                        break;
                    case 26:
                        MsgMgr.this.realKeyClick3_1(context, 8);
                        break;
                    case 27:
                        if (MsgMgr.this.isKx5Top()) {
                            MsgMgr.this.realKeyLongClick1(context, 59);
                            break;
                        } else if (MsgMgr.this.isK4()) {
                            MsgMgr.this.realKeyLongClick1(context, 129);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 129);
                            break;
                        }
                    case 28:
                        if (MsgMgr.this.isKx5Top()) {
                            MsgMgr.this.realKeyLongClick1(context, 128);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 59);
                            break;
                        }
                    case 29:
                        if (MsgMgr.this.isK4()) {
                            MsgMgr.this.realKeyLongClick1(context, 21);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 14);
                            break;
                        }
                    case 30:
                        if (MsgMgr.this.isKx5Top()) {
                            MsgMgr.this.realKeyLongClick1(context, HotKeyConstant.K_CAN_CONFIG);
                            break;
                        } else if (MsgMgr.this.isK4()) {
                            MsgMgr.this.realKeyLongClick1(context, 14);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 152);
                            break;
                        }
                    case 31:
                        if (MsgMgr.this.isK4()) {
                            MsgMgr.this.realKeyLongClick1(context, 20);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 21);
                            break;
                        }
                    case 32:
                        MsgMgr.this.realKeyLongClick1(context, 20);
                        break;
                    case 33:
                        MsgMgr.this.realKeyLongClick1(context, 128);
                        break;
                    case 34:
                        MsgMgr.this.realKeyLongClick1(context, 128);
                        break;
                    case 35:
                        MsgMgr.this.realKeyLongClick1(context, 128);
                        break;
                    case 36:
                        if (MsgMgr.this.isK4()) {
                            MsgMgr.this.realKeyLongClick1(context, 152);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 58);
                            break;
                        }
                    case 37:
                        MsgMgr.this.realKeyLongClick1(context, 49);
                        break;
                    case 38:
                        MsgMgr.this.realKeyClick3_2(context, 48);
                        break;
                    case 39:
                        MsgMgr.this.realKeyClick3_2(context, 47);
                        break;
                    case 40:
                        MsgMgr.this.realKeyLongClick1(context, HotKeyConstant.K_CAN_CONFIG);
                        break;
                    case 41:
                        if (MsgMgr.this.isKx5Top()) {
                            MsgMgr.this.realKeyLongClick1(context, 129);
                            break;
                        } else {
                            MsgMgr.this.realKeyLongClick1(context, 52);
                            break;
                        }
                    case 42:
                        MsgMgr.this.realKeyLongClick1(context, 76);
                        break;
                    case 43:
                        MsgMgr.this.realKeyLongClick1(context, 77);
                        break;
                    case 44:
                        MsgMgr.this.realKeyLongClick1(context, 58);
                        break;
                    case 45:
                        MsgMgr.this.realKeyLongClick1(context, 52);
                        break;
                    case 46:
                        MsgMgr.this.realKeyLongClick1(context, 50);
                        break;
                    case 47:
                        MsgMgr.this.realKeyLongClick1(context, 45);
                        break;
                    case 48:
                        MsgMgr.this.realKeyLongClick1(context, HotKeyConstant.K_SPEECH);
                        break;
                    case 49:
                        MsgMgr.this.realKeyLongClick1(context, 128);
                        break;
                    case 50:
                        MsgMgr.this.realKeyLongClick1(context, HotKeyConstant.K_CAN_CONFIG);
                        break;
                    case 51:
                        MsgMgr.this.realKeyLongClick1(context, 14);
                        break;
                    case 52:
                        MsgMgr.this.realKeyLongClick1(context, 46);
                        break;
                    case 53:
                        MsgMgr.this.realKeyLongClick1(context, 39);
                        break;
                }
                return;
            }
            MsgMgr.this.realKeyClick3_1(context, 8);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            if (MsgMgr.this.mCanbusInfoInt.length > 3) {
                MsgMgr msgMgr = MsgMgr.this;
                if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                    super.parse(i);
                }
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$4, reason: invalid class name */
    class AnonymousClass4 extends Parser {
        private final int[] canbusInfo;
        private int[] frontDatas;
        private int[] rearDatas;
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(String str, Context context) {
            super(str);
            this.val$context = context;
            this.canbusInfo = new int[8];
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            OnParseListener onParseListener;
            OnParseListener onParseListener2;
            OnParseListener onParseListener3;
            if (MsgMgr.this.is15Carnival()) {
                onParseListener = new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                    public final void onParse() {
                        this.f$0.m456lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$4();
                    }
                };
                final Context context = this.val$context;
                onParseListener2 = new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda1
                    @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                    public final void onParse() {
                        this.f$0.m457lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$4(context);
                    }
                };
                onParseListener3 = new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda2
                    @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                    public final void onParse() {
                        this.f$0.m458lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$4();
                    }
                };
            } else {
                onParseListener = new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda3
                    @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                    public final void onParse() {
                        this.f$0.m459lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$4();
                    }
                };
                onParseListener2 = new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda4
                    @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                    public final void onParse() {
                        this.f$0.m460lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$4();
                    }
                };
                onParseListener3 = new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda5
                    @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                    public final void onParse() {
                        this.f$0.m461lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$4();
                    }
                };
            }
            final Context context2 = this.val$context;
            final Context context3 = this.val$context;
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m462lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$4(context2);
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda7
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m463lambda$setOnParseListeners$7$comhzbhdcanbuscar_30MsgMgr$4(context3);
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$4$$ExternalSyntheticLambda8
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m464lambda$setOnParseListeners$8$comhzbhdcanbuscar_30MsgMgr$4();
                }
            }, onParseListener, onParseListener2, onParseListener3};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m456lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$4() {
            this.canbusInfo[5] = 0;
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m457lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$4(Context context) {
            GeneralAirData.rear_temperature = getAirTemperature(context, this.canbusInfo[6]);
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m458lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$4() {
            GeneralAirData.rear_wind_level = this.canbusInfo[7];
        }

        /* renamed from: lambda$setOnParseListeners$3$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m459lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$4() {
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit7(this.canbusInfo[5]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.canbusInfo[5]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.canbusInfo[5]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.canbusInfo[5]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(this.canbusInfo[5]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit2(this.canbusInfo[5]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit1(this.canbusInfo[5]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.canbusInfo[5]);
        }

        /* renamed from: lambda$setOnParseListeners$4$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m460lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$4() {
            this.canbusInfo[6] = 0;
        }

        /* renamed from: lambda$setOnParseListeners$5$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m461lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$4() {
            this.canbusInfo[7] = 0;
        }

        /* renamed from: lambda$setOnParseListeners$6$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m462lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$4(Context context) {
            GeneralAirData.front_left_temperature = getAirTemperature(context, this.canbusInfo[2]);
        }

        /* renamed from: lambda$setOnParseListeners$7$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m463lambda$setOnParseListeners$7$comhzbhdcanbuscar_30MsgMgr$4(Context context) {
            GeneralAirData.front_right_temperature = getAirTemperature(context, this.canbusInfo[3]);
        }

        /* renamed from: lambda$setOnParseListeners$8$com-hzbhd-canbus-car-_30-MsgMgr$4, reason: not valid java name */
        /* synthetic */ void m464lambda$setOnParseListeners$8$comhzbhdcanbuscar_30MsgMgr$4() {
            GeneralAirData.front_wind_level = this.canbusInfo[4];
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            System.arraycopy(MsgMgr.this.mCanbusInfoInt, 0, this.canbusInfo, 0, Math.min(MsgMgr.this.mCanbusInfoInt.length, this.canbusInfo.length));
            super.parse(i);
            if (MsgMgr.this.isDataChange(this.canbusInfo)) {
                GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
                GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
                MsgMgr.this.updateAirActivity(this.val$context, getAirWhat());
            }
        }

        private String getAirTemperature(Context context, int i) {
            if (i == 0) {
                return "Low";
            }
            if (i == 30) {
                return "High";
            }
            if (i == 255) {
                return "--";
            }
            if (i <= 0 || i >= 30) {
                return (i < 62 || i > 90) ? "" : i + MsgMgr.this.getTempUnitF(context);
            }
            return ((i * 0.5f) + 17.0f) + MsgMgr.this.getTempUnitC(context);
        }

        private int getAirWhat() {
            if (!MsgMgr.this.is15Carnival()) {
                return 1001;
            }
            int[] iArr = this.canbusInfo;
            if (isFrontDataChange(iArr[2], iArr[3], iArr[4])) {
                return 1001;
            }
            int[] iArr2 = this.canbusInfo;
            return isRearDataChange(iArr2[6], iArr2[7]) ? 1002 : 0;
        }

        private boolean isFrontDataChange(int... iArr) {
            if (Arrays.equals(this.frontDatas, iArr)) {
                return false;
            }
            this.frontDatas = Arrays.copyOf(iArr, iArr.length);
            return true;
        }

        private boolean isRearDataChange(int... iArr) {
            if (Arrays.equals(this.rearDatas, iArr)) {
                return false;
            }
            this.rearDatas = Arrays.copyOf(iArr, iArr.length);
            return true;
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$5, reason: invalid class name */
    class AnonymousClass5 extends Parser {
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$5$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m465lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$5();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$5$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m466lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$5();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$5, reason: not valid java name */
        /* synthetic */ void m465lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$5() {
            RadarInfoUtil.setFrontRadarLocationData(3, DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[2], 6, 2), DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[2], 4, 2), DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[2], 2, 2), DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[2], 0, 2));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$5, reason: not valid java name */
        /* synthetic */ void m466lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$5() {
            RadarInfoUtil.setRearRadarLocationData(3, DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[3], 6, 2), DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[3], 4, 2), DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[3], 2, 2), DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[3], 0, 2));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                super.parse(i);
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                MsgMgr.this.updateParkUi(null, this.val$context);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$6, reason: invalid class name */
    class AnonymousClass6 extends Parser {
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$6$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m467lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$6();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$6, reason: not valid java name */
        /* synthetic */ void m467lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$6() {
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(MsgMgr.this.mCanbusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(MsgMgr.this.mCanbusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(MsgMgr.this.mCanbusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(MsgMgr.this.mCanbusInfoInt[2]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(MsgMgr.this.mCanbusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(MsgMgr.this.mCanbusInfoInt[2]);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            super.parse(i);
            if (MsgMgr.this.isDoorStatusChange()) {
                MsgMgr.this.updateDoorView(this.val$context);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$7, reason: invalid class name */
    class AnonymousClass7 extends Parser {
        private int climateBtnStatus;
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass7(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            final Context context = this.val$context;
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$7$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m468lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$7(context);
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$7, reason: not valid java name */
        /* synthetic */ void m468lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$7(Context context) {
            if (this.climateBtnStatus == 1 && MsgMgr.this.mCanbusInfoInt[2] == 0) {
                if (SystemUtil.isForeground(context, AirActivity.class.getName())) {
                    MsgMgr.this.finishActivity();
                } else {
                    Intent intent = new Intent(context, (Class<?>) AirActivity.class);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                }
            }
            this.climateBtnStatus = MsgMgr.this.mCanbusInfoInt[2];
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            super.parse(i);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$8, reason: invalid class name */
    class AnonymousClass8 extends Parser {
        AnonymousClass8(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$8$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m469lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$8();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$8, reason: not valid java name */
        /* synthetic */ void m469lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$8() {
            if (MsgMgr.this.mCanbusInfoInt[2] < 1 || MsgMgr.this.mCanbusInfoInt[2] >= 5) {
                if (MsgMgr.this.mCanbusInfoInt[2] < 5 || MsgMgr.this.mCanbusInfoInt[2] >= 9) {
                    if (MsgMgr.this.mCanbusInfoInt[2] < 9 || MsgMgr.this.mCanbusInfoInt[2] >= 13) {
                        if (MsgMgr.this.mCanbusInfoInt[2] < 13 || MsgMgr.this.mCanbusInfoInt[2] >= 17) {
                            if (MsgMgr.this.mCanbusInfoInt[2] < 13 || MsgMgr.this.mCanbusInfoInt[2] >= 17) {
                                return;
                            }
                            MsgMgr.this.setBacklightLevel(5);
                            return;
                        }
                        MsgMgr.this.setBacklightLevel(4);
                        return;
                    }
                    MsgMgr.this.setBacklightLevel(3);
                    return;
                }
                MsgMgr.this.setBacklightLevel(2);
                return;
            }
            MsgMgr.this.setBacklightLevel(1);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$10, reason: invalid class name */
    class AnonymousClass10 extends Parser {
        private int combi;
        private boolean isEnterPanoramic;
        final /* synthetic */ Context val$context;
        private int view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass10(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$10$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m402lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$10();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$10$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m403lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$10();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$10, reason: not valid java name */
        /* synthetic */ void m402lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$10() {
            this.view = MsgMgr.this.mCanbusInfoInt[2];
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$10, reason: not valid java name */
        /* synthetic */ void m403lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$10() {
            this.combi = MsgMgr.this.mCanbusInfoInt[3];
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                super.parse(i);
                boolean z = (this.view == 0 || this.combi == 0) ? false : true;
                if (this.isEnterPanoramic ^ z) {
                    this.isEnterPanoramic = z;
                    MsgMgr msgMgr2 = MsgMgr.this;
                    msgMgr2.forceReverse(msgMgr2.mContext, z);
                }
                boolean zIsMiscReverse = CommUtil.isMiscReverse();
                if (z || zIsMiscReverse) {
                    MsgMgr msgMgr3 = MsgMgr.this;
                    final Context context = this.val$context;
                    msgMgr3.runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._30.MsgMgr$10$$ExternalSyntheticLambda2
                        @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                        public final void callback() {
                            this.f$0.m401lambda$parse$2$comhzbhdcanbuscar_30MsgMgr$10(context);
                        }
                    });
                }
            }
        }

        /* renamed from: lambda$parse$2$com-hzbhd-canbus-car-_30-MsgMgr$10, reason: not valid java name */
        /* synthetic */ void m401lambda$parse$2$comhzbhdcanbuscar_30MsgMgr$10(Context context) {
            int i;
            int i2;
            ((MyPanoramicView) MsgMgr.this.getUiMgr(context).getCusPanoramicView(context)).updateSide(this.view - 1);
            int i3 = this.view;
            ((MyPanoramicView) MsgMgr.this.getUiMgr(context).getCusPanoramicView(context)).updateBtns((i3 != 1 || (i2 = this.combi) >= 6) ? (i3 != 2 || (i = this.combi) <= 5) ? -1 : i - 6 : i2 - 1);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$11, reason: invalid class name */
    class AnonymousClass11 extends Parser {
        private List<SettingUpdateEntity> list;

        AnonymousClass11(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$11$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m404lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$11();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$11$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m405lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$11();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$11$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m406lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$11();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$11, reason: not valid java name */
        /* synthetic */ void m404lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$11() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_29_rear_view_parking_guide_line").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[2], 7, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_29_park_distance_warning").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[2], 6, 1))));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$11, reason: not valid java name */
        /* synthetic */ void m405lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$11() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("vm_golf7_front_mode").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[3], 0, 4) - 1)));
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$11, reason: not valid java name */
        /* synthetic */ void m406lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$11() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("vm_golf7_rear_mode").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[4], 0, 4) - 1)));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                this.list = new ArrayList();
                super.parse(i);
                MsgMgr.this.updateGeneralSettingData(this.list);
                MsgMgr.this.updateSettingActivity(null);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$12, reason: invalid class name */
    class AnonymousClass12 extends Parser {
        private int isEnterBackCamera;

        static /* synthetic */ void lambda$setOnParseListeners$1() {
        }

        AnonymousClass12(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$12$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m407lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$12();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$12$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    MsgMgr.AnonymousClass12.lambda$setOnParseListeners$1();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$12, reason: not valid java name */
        /* synthetic */ void m407lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$12() {
            if (this.isEnterBackCamera != MsgMgr.this.mCanbusInfoInt[2]) {
                this.isEnterBackCamera = MsgMgr.this.mCanbusInfoInt[2];
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.forceReverse(msgMgr.mContext, this.isEnterBackCamera == 1);
            }
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            super.parse(i);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$13, reason: invalid class name */
    class AnonymousClass13 extends Parser {
        AnonymousClass13(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$13$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m409lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$13();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$13$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m410lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$13();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$13, reason: not valid java name */
        /* synthetic */ void m409lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$13() {
            boolean z = true;
            MsgMgr.this.mSeatStatusView.setSeatSelect(MsgMgr.this.mCanbusInfoInt[2] != 0);
            MsgMgr.this.mSeatStatusView.setSeatForward(DataHandleUtils.getBoolBit0(MsgMgr.this.mCanbusInfoInt[2]));
            MsgMgr.this.mSeatStatusView.setSeatBack(DataHandleUtils.getBoolBit1(MsgMgr.this.mCanbusInfoInt[2]));
            MsgMgr.this.mSeatStatusView.setSeatUp(DataHandleUtils.getBoolBit2(MsgMgr.this.mCanbusInfoInt[2]) || DataHandleUtils.getBoolBit4(MsgMgr.this.mCanbusInfoInt[2]));
            SeatStatusView seatStatusView = MsgMgr.this.mSeatStatusView;
            if (!DataHandleUtils.getBoolBit3(MsgMgr.this.mCanbusInfoInt[2]) && !DataHandleUtils.getBoolBit5(MsgMgr.this.mCanbusInfoInt[2])) {
                z = false;
            }
            seatStatusView.setSeatDown(z);
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$13, reason: not valid java name */
        /* synthetic */ void m410lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$13() {
            MsgMgr.this.mSeatStatusView.setBackrestSelelct(MsgMgr.this.mCanbusInfoInt[3] != 0);
            MsgMgr.this.mSeatStatusView.setBackrestForward(DataHandleUtils.getBoolBit0(MsgMgr.this.mCanbusInfoInt[3]));
            MsgMgr.this.mSeatStatusView.setBackrestBackward(DataHandleUtils.getBoolBit1(MsgMgr.this.mCanbusInfoInt[3]));
            MsgMgr.this.mSeatStatusView.setBackrestUp(DataHandleUtils.getBoolBit2(MsgMgr.this.mCanbusInfoInt[3]));
            MsgMgr.this.mSeatStatusView.setBackrestDown(DataHandleUtils.getBoolBit3(MsgMgr.this.mCanbusInfoInt[3]));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(final int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                MsgMgr.this.runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._30.MsgMgr$13$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                    public final void callback() {
                        this.f$0.m408lambda$parse$2$comhzbhdcanbuscar_30MsgMgr$13(i);
                    }
                });
            }
        }

        /* renamed from: lambda$parse$2$com-hzbhd-canbus-car-_30-MsgMgr$13, reason: not valid java name */
        /* synthetic */ void m408lambda$parse$2$comhzbhdcanbuscar_30MsgMgr$13(int i) {
            super.parse(i);
            MsgMgr.this.mSeatStatusView.addViewToWindow();
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$15, reason: invalid class name */
    class AnonymousClass15 extends Parser {
        private final int CHARGE_TYPE_1_START_100;
        private final int CHARGE_TYPE_2_START_END;
        private final int CHARGE_TYPE_CLOSE;
        private List<SettingUpdateEntity> list;

        AnonymousClass15(String str) {
            super(str);
            this.CHARGE_TYPE_CLOSE = 1;
            this.CHARGE_TYPE_1_START_100 = 2;
            this.CHARGE_TYPE_2_START_END = 3;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        void init() {
            ArrayList arrayList = new ArrayList();
            this.list = arrayList;
            arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_repeat_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_sunday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_monday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_tuesday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_wednesday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_thursday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_friday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_saturday_1").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_repeat_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_sunday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_monday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_tuesday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_wednesday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_thursday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_friday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_saturday_2").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_repeat_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_sunday_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_monday_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_tuesday_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_wednesday_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_thursday_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_friday_3").setEnable(false));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_saturday_3").setEnable(false));
            MsgMgr.this.updateGeneralSettingData(this.list);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m411lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m412lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m415lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda7
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m416lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda8
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m417lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda9
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m418lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda10
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m419lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda11
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m420lambda$setOnParseListeners$7$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m421lambda$setOnParseListeners$8$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m422lambda$setOnParseListeners$9$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m413lambda$setOnParseListeners$10$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$15$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m414lambda$setOnParseListeners$11$comhzbhdcanbuscar_30MsgMgr$15();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m411lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_0").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[2] - 1)));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m412lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_start_time_1").setValue(Integer.valueOf(getTime(MsgMgr.this.mCanbusInfoInt[3]))).setProgress(getTime(MsgMgr.this.mCanbusInfoInt[3])));
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m415lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_end_time_1").setValue(Integer.valueOf(getTime(MsgMgr.this.mCanbusInfoInt[4]))).setProgress(getTime(MsgMgr.this.mCanbusInfoInt[4])).setEnable(MsgMgr.this.mCanbusInfoInt[2] == 3));
        }

        /* renamed from: lambda$setOnParseListeners$3$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m416lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_sunday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 6, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_monday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 5, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_tuesday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 4, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_wednesday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 3, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_thursday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 2, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_friday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 1, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_saturday_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[5], 0, 1))));
        }

        /* renamed from: lambda$setOnParseListeners$4$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m417lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_4").setValue(Integer.valueOf(getTime(MsgMgr.this.mCanbusInfoInt[6]))).setProgress(getTime(MsgMgr.this.mCanbusInfoInt[6])));
        }

        /* renamed from: lambda$setOnParseListeners$5$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m418lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_monday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 0, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_tuesday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 1, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_wednesday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 2, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_thursday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 3, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_friday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 4, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_saturday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 5, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_sunday_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 6, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_B_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 7, 1))));
        }

        /* renamed from: lambda$setOnParseListeners$6$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m419lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_6").setValue(Integer.valueOf(getTime(MsgMgr.this.mCanbusInfoInt[8]))).setProgress(getTime(MsgMgr.this.mCanbusInfoInt[8])));
        }

        /* renamed from: lambda$setOnParseListeners$7$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m420lambda$setOnParseListeners$7$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_monday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 0, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_tuesday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 1, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_wednesday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 2, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_thursday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 3, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_friday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 4, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_saturday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 5, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_sunday_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 6, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_B_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[9], 7, 1))));
        }

        /* renamed from: lambda$setOnParseListeners$8$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m421lambda$setOnParseListeners$8$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_start_time_2").setValue(Integer.valueOf(getTime(MsgMgr.this.mCanbusInfoInt[10]))).setProgress(getTime(MsgMgr.this.mCanbusInfoInt[10])));
        }

        /* renamed from: lambda$setOnParseListeners$9$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m422lambda$setOnParseListeners$9$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_end_time_2").setValue(Integer.valueOf(getTime(MsgMgr.this.mCanbusInfoInt[11]))).setProgress(getTime(MsgMgr.this.mCanbusInfoInt[11])));
        }

        /* renamed from: lambda$setOnParseListeners$10$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m413lambda$setOnParseListeners$10$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_C_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[12], 7, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_C_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[12], 6, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_B_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[12], 5, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_B_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[12], 4, 1))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_B_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[12], 2, 2))));
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_B_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[12], 0, 2))));
        }

        /* renamed from: lambda$setOnParseListeners$11$com-hzbhd-canbus-car-_30-MsgMgr$15, reason: not valid java name */
        /* synthetic */ void m414lambda$setOnParseListeners$11$comhzbhdcanbuscar_30MsgMgr$15() {
            this.list.add(MsgMgr.this.getSettingUpdateEntity("_30_new_energy_53_C_10").setValue(Integer.valueOf(MsgMgr.this.mCanbusInfoInt[13])).setProgress(MsgMgr.this.mCanbusInfoInt[13] - 6));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                ArrayList arrayList = new ArrayList();
                this.list = arrayList;
                arrayList.addAll(MsgMgr.this.mSettingPage.getChildByTitle("_30_new_energy_53_0").setChildsEnable(MsgMgr.this.mCanbusInfoInt[2] != 1));
                this.list.addAll(MsgMgr.this.mSettingPage.getChildByTitle("_30_new_energy_53_B_1").setChildsEnable(((MsgMgr.this.mCanbusInfoInt[7] >> 7) & 1) == 1));
                this.list.addAll(MsgMgr.this.mSettingPage.getChildByTitle("_30_new_energy_53_B_2").setChildsEnable(((MsgMgr.this.mCanbusInfoInt[9] >> 7) & 1) == 1));
                this.list.addAll(MsgMgr.this.mSettingPage.getChildByTitle("_30_new_energy_53_B_3").setChildsEnable(((MsgMgr.this.mCanbusInfoInt[12] >> 5) & 1) == 1));
                this.list.addAll(MsgMgr.this.mSettingPage.getChildByTitle("_30_new_energy_53_C_1").setChildsEnable(((MsgMgr.this.mCanbusInfoInt[12] >> 7) & 1) == 1));
                super.parse(i);
                MsgMgr.this.updateGeneralSettingData(this.list);
                MsgMgr.this.updateSettingActivity(null);
            }
        }

        private int getTime(int i) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 0, 5) * 6) + DataHandleUtils.getIntFromByteWithBit(i, 5, 3);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$16, reason: invalid class name */
    class AnonymousClass16 extends Parser {
        private List<DriverUpdateEntity> list;

        AnonymousClass16(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m423lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m424lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m425lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m426lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m427lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }, null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m428lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$16$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m429lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$16();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m423lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$16() {
            this.list.add(MsgMgr.this.getDriveUpdateEntity("_30_eco_level").setValue(Integer.toString(MsgMgr.this.mCanbusInfoInt[2])));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m424lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$16() {
            int i = (MsgMgr.this.mCanbusInfoInt[3] << 8) | MsgMgr.this.mCanbusInfoInt[4];
            if (i >= 999) {
                return;
            }
            this.list.add(MsgMgr.this.getDriveUpdateEntity("vm_golf7_vehicle_setup_mfd_avg_consumption").setValue((i / 10.0f) + " L/100KM"));
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m425lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$16() {
            int i = (MsgMgr.this.mCanbusInfoInt[5] << 8) | MsgMgr.this.mCanbusInfoInt[6];
            if (i >= 999) {
                return;
            }
            this.list.add(MsgMgr.this.getDriveUpdateEntity("_30_hybrid_fuel_consumption").setValue((i / 10.0f) + " L/100KM"));
        }

        /* renamed from: lambda$setOnParseListeners$3$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m426lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$16() {
            this.list.add(MsgMgr.this.getDriveUpdateEntity("_30_motor_energy_consumption").setValue(MsgMgr.this.mCanbusInfoInt[7] + " KW"));
        }

        /* renamed from: lambda$setOnParseListeners$4$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m427lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$16() {
            this.list.add(MsgMgr.this.getDriveUpdateEntity("_279_dashboard_data2").setValue(MsgMgr.this.mCanbusInfoInt[8] + " KM/H"));
            MsgMgr msgMgr = MsgMgr.this;
            msgMgr.updateSpeedInfo(msgMgr.mCanbusInfoInt[8]);
        }

        /* renamed from: lambda$setOnParseListeners$5$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m428lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$16() {
            this.list.add(MsgMgr.this.getDriveUpdateEntity("_197_odo").setValue(((((MsgMgr.this.mCanbusInfoInt[9] << 16) | (MsgMgr.this.mCanbusInfoInt[10] << 8)) | MsgMgr.this.mCanbusInfoInt[11]) / 10.0f) + " KM"));
        }

        /* renamed from: lambda$setOnParseListeners$6$com-hzbhd-canbus-car-_30-MsgMgr$16, reason: not valid java name */
        /* synthetic */ void m429lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$16() {
            int i = (MsgMgr.this.mCanbusInfoInt[12] << 8) | MsgMgr.this.mCanbusInfoInt[13];
            if (i >= 999) {
                return;
            }
            this.list.add(MsgMgr.this.getDriveUpdateEntity("_30_energy_consumption_record").setValue((i / 10.0f) + " KWH/100KM"));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            Log.i(MsgMgr.TAG, "parse: 0x54");
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                this.list = new ArrayList();
                super.parse(i);
                MsgMgr.this.updateGeneralDriveData(this.list);
                MsgMgr.this.updateDriveDataActivity(null);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$17, reason: invalid class name */
    class AnonymousClass17 extends Parser {
        private int[] driveDataRecord;
        List<DriverUpdateEntity> driverUpdateList;
        private int[] hybridDataRecord;
        List<String> hybridUpdateList;
        final /* synthetic */ Context val$context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass17(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            final Context context = this.val$context;
            final Context context2 = this.val$context;
            final Context context3 = this.val$context;
            final Context context4 = this.val$context;
            return new OnParseListener[]{null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m430lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$17(context);
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m431lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$17(context2);
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m432lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$17();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m433lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$17();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m434lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$17(context3);
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m435lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$17(context4);
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$17$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m436lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$17();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m430lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$17(Context context) {
            this.hybridUpdateList.add(String.format("%s: %d KM", CommUtil.getStrByResId(context, "_30_power"), Integer.valueOf((MsgMgr.this.mCanbusInfoInt[2] << 8) | MsgMgr.this.mCanbusInfoInt[3])));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m431lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$17(Context context) {
            this.hybridUpdateList.add(String.format("%s: %d KM", CommUtil.getStrByResId(context, "_30_gasoline"), Integer.valueOf((MsgMgr.this.mCanbusInfoInt[4] << 8) | MsgMgr.this.mCanbusInfoInt[5])));
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m432lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$17() {
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_total_mileage_available").setValue(((MsgMgr.this.mCanbusInfoInt[6] << 8) | MsgMgr.this.mCanbusInfoInt[7]) + " KM"));
        }

        /* renamed from: lambda$setOnParseListeners$3$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m433lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$17() {
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_battery_percentage").setValue(MsgMgr.this.mCanbusInfoInt[8] + "%"));
        }

        /* renamed from: lambda$setOnParseListeners$4$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m434lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$17(Context context) {
            int i = (MsgMgr.this.mCanbusInfoInt[9] << 8) | MsgMgr.this.mCanbusInfoInt[10];
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_estimated_charging_time_charger").setValue((i / 60) + CommUtil.getStrByResId(context, "unit_hour") + (i % 60) + CommUtil.getStrByResId(context, "unit_minute")));
        }

        /* renamed from: lambda$setOnParseListeners$5$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m435lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$17(Context context) {
            int i = (MsgMgr.this.mCanbusInfoInt[11] << 8) | MsgMgr.this.mCanbusInfoInt[12];
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_estimated_charging_time_portables").setValue((i / 60) + CommUtil.getStrByResId(context, "unit_hour") + (i % 60) + CommUtil.getStrByResId(context, "unit_minute")));
        }

        /* renamed from: lambda$setOnParseListeners$6$com-hzbhd-canbus-car-_30-MsgMgr$17, reason: not valid java name */
        /* synthetic */ void m436lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$17() {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[13], 4, 4);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[13], 0, 4);
            GeneralHybirdData.powerBatteryLevel = intFromByteWithBit;
            GeneralHybirdData.isWheelDriveMotor = false;
            GeneralHybirdData.isBatteryDriveMotor = false;
            GeneralHybirdData.isEngineDriveWheel = false;
            GeneralHybirdData.isEngineDriveMotor = false;
            GeneralHybirdData.isMotorDriveWheel = false;
            GeneralHybirdData.isMotorDriveBattery = false;
            switch (intFromByteWithBit2) {
                case 0:
                case 3:
                    GeneralHybirdData.isBatteryDriveMotor = true;
                    GeneralHybirdData.isMotorDriveWheel = true;
                    GeneralHybirdData.isEngineDriveWheel = true;
                    GeneralHybirdData.isEngineDriveMotor = true;
                    break;
                case 1:
                    GeneralHybirdData.isEngineDriveWheel = true;
                    GeneralHybirdData.isEngineDriveMotor = true;
                    GeneralHybirdData.isMotorDriveWheel = true;
                    break;
                case 2:
                    GeneralHybirdData.isBatteryDriveMotor = true;
                    GeneralHybirdData.isMotorDriveWheel = true;
                    break;
                case 4:
                    GeneralHybirdData.isEngineDriveWheel = true;
                    break;
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 11:
                    GeneralHybirdData.isWheelDriveMotor = true;
                    GeneralHybirdData.isMotorDriveBattery = true;
                    break;
                case 7:
                    GeneralHybirdData.isEngineDriveWheel = true;
                    GeneralHybirdData.isEngineDriveMotor = true;
                    break;
            }
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                this.hybridUpdateList = new ArrayList();
                this.driverUpdateList = new ArrayList();
                super.parse(i);
                GeneralHybirdData.title = CommUtil.getStrByResId(this.val$context, "_30_energy_info");
                GeneralHybirdData.valueList = this.hybridUpdateList;
                MsgMgr.this.updateHybirdActivity(null);
                MsgMgr.this.updateGeneralDriveData(this.driverUpdateList);
                MsgMgr.this.updateDriveDataActivity(null);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$18, reason: invalid class name */
    class AnonymousClass18 extends Parser {
        List<DriverUpdateEntity> driverUpdateList;
        List<SettingUpdateEntity> settingUpdateList;
        final /* synthetic */ Context val$context;

        private int[] getRangeAndPercent(int i) {
            return new int[]{i >> 6, i & 63};
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass18(String str, Context context) {
            super(str);
            this.val$context = context;
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        void init() {
            ArrayList arrayList = new ArrayList();
            this.settingUpdateList = arrayList;
            arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_set_charge_amount_1").setValue(5).setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_set_charge_amount_2").setValue(5).setEnable(false));
            MsgMgr.this.updateGeneralSettingData(this.settingUpdateList);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            final Context context = this.val$context;
            return new OnParseListener[]{null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m437lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$18();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m438lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$18();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m439lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$18(context);
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m440lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$18();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m441lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$18();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m442lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$18();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$18$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m443lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$18();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m437lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$18() {
            int[] rangeAndPercent = getRangeAndPercent((MsgMgr.this.mCanbusInfoInt[2] << 8) | MsgMgr.this.mCanbusInfoInt[3]);
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_approximate_range_fast_charge").setValue(Integer.valueOf(rangeAndPercent[0])));
            int i = rangeAndPercent[1] / 5;
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_set_charge_amount_1").setValue(Integer.valueOf(i)).setProgress(i - 5));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m438lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$18() {
            int[] rangeAndPercent = getRangeAndPercent((MsgMgr.this.mCanbusInfoInt[4] << 8) | MsgMgr.this.mCanbusInfoInt[5]);
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_approximate_range_normal").setValue(Integer.valueOf(rangeAndPercent[0])));
            int i = rangeAndPercent[1] / 5;
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_set_charge_amount_2").setValue(Integer.valueOf(i)).setProgress(i - 5));
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m439lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$18(Context context) {
            int i = (MsgMgr.this.mCanbusInfoInt[6] << 8) | MsgMgr.this.mCanbusInfoInt[7];
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_estimated_charging_time_fast_charging").setValue((i / 60) + CommUtil.getStrByResId(context, "unit_hour") + (i % 60) + CommUtil.getStrByResId(context, "unit_minute")));
        }

        /* renamed from: lambda$setOnParseListeners$3$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m440lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$18() {
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_traditional_system").setValue(((MsgMgr.this.mCanbusInfoInt[8] << 8) | MsgMgr.this.mCanbusInfoInt[9]) + " KW"));
        }

        /* renamed from: lambda$setOnParseListeners$4$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m441lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$18() {
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_air_conditioning_system").setValue((((MsgMgr.this.mCanbusInfoInt[10] << 8) | MsgMgr.this.mCanbusInfoInt[11]) / 100.0f) + " KW"));
        }

        /* renamed from: lambda$setOnParseListeners$5$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m442lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$18() {
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_electronic_equipment").setValue((MsgMgr.this.mCanbusInfoInt[12] / 100.0f) + " KW"));
        }

        /* renamed from: lambda$setOnParseListeners$6$com-hzbhd-canbus-car-_30-MsgMgr$18, reason: not valid java name */
        /* synthetic */ void m443lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$18() {
            this.driverUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_battery_maintenance").setValue((MsgMgr.this.mCanbusInfoInt[13] / 100.0f) + " KW"));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                this.driverUpdateList = new ArrayList();
                this.settingUpdateList = new ArrayList();
                super.parse(i);
                MsgMgr.this.updateGeneralSettingData(this.settingUpdateList);
                MsgMgr.this.updateSettingActivity(null);
                MsgMgr.this.updateGeneralDriveData(this.driverUpdateList);
                MsgMgr.this.updateDriveDataActivity(null);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$19, reason: invalid class name */
    class AnonymousClass19 extends Parser {
        List<DriverUpdateEntity> driveDataUpdateList;
        List<SettingUpdateEntity> settingUpdateList;

        static /* synthetic */ void lambda$setOnParseListeners$9() {
        }

        AnonymousClass19(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        void init() {
            ArrayList arrayList = new ArrayList();
            this.settingUpdateList = arrayList;
            arrayList.add(MsgMgr.this.getSettingUpdateEntity("_30_maximum_speed_limit").setValue(8).setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_taxi_energy_regeneration_1").setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_conditioning_control_1").setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_taxi_energy_regeneration_2").setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_conditioning_control_2").setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_taxi_energy_regeneration_3").setEnable(false));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_conditioning_control_3").setEnable(false));
            MsgMgr.this.updateGeneralSettingData(this.settingUpdateList);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m444lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m445lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda2
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m446lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda3
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m447lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda4
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m448lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda5
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m449lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda6
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m450lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda7
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m451lambda$setOnParseListeners$7$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda8
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    this.f$0.m452lambda$setOnParseListeners$8$comhzbhdcanbuscar_30MsgMgr$19();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$19$$ExternalSyntheticLambda9
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    MsgMgr.AnonymousClass19.lambda$setOnParseListeners$9();
                }
            }};
        }

        /* renamed from: lambda$setOnParseListeners$0$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m444lambda$setOnParseListeners$0$comhzbhdcanbuscar_30MsgMgr$19() {
            this.driveDataUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_sport_mode_ceo").setValue(MsgMgr.this.mCanbusInfoInt[2] + "%"));
        }

        /* renamed from: lambda$setOnParseListeners$1$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m445lambda$setOnParseListeners$1$comhzbhdcanbuscar_30MsgMgr$19() {
            this.driveDataUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_com_mode_ceo").setValue(MsgMgr.this.mCanbusInfoInt[3] + "%"));
        }

        /* renamed from: lambda$setOnParseListeners$2$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m446lambda$setOnParseListeners$2$comhzbhdcanbuscar_30MsgMgr$19() {
            this.driveDataUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_eco_mode_ceo").setValue(MsgMgr.this.mCanbusInfoInt[4] + "%"));
        }

        /* renamed from: lambda$setOnParseListeners$3$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m447lambda$setOnParseListeners$3$comhzbhdcanbuscar_30MsgMgr$19() {
            int i = MsgMgr.this.mCanbusInfoInt[5] / 10;
            if (MsgMgr.this.mCanbusInfoInt[5] == 254) {
                i = 8;
            }
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_maximum_speed_limit").setValue(Integer.valueOf(i)).setProgress(i - 8));
        }

        /* renamed from: lambda$setOnParseListeners$4$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m448lambda$setOnParseListeners$4$comhzbhdcanbuscar_30MsgMgr$19() {
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_taxi_energy_regeneration_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[6], 6, 2))));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_taxi_energy_regeneration_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[6], 4, 2))));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_taxi_energy_regeneration_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[6], 2, 2))));
        }

        /* renamed from: lambda$setOnParseListeners$5$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m449lambda$setOnParseListeners$5$comhzbhdcanbuscar_30MsgMgr$19() {
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_conditioning_control_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 5, 2))));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_conditioning_control_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 3, 2))));
            this.settingUpdateList.add(MsgMgr.this.getSettingUpdateEntity("_30_air_conditioning_control_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanbusInfoInt[7], 1, 2))));
        }

        /* renamed from: lambda$setOnParseListeners$6$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m450lambda$setOnParseListeners$6$comhzbhdcanbuscar_30MsgMgr$19() {
            int i = MsgMgr.this.mCanbusInfoInt[8];
            this.driveDataUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_143_setting_11").setValue(i != 0 ? i != 1 ? i != 2 ? "" : "ECO" : "SPORT" : "COMFORT"));
        }

        /* renamed from: lambda$setOnParseListeners$7$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m451lambda$setOnParseListeners$7$comhzbhdcanbuscar_30MsgMgr$19() {
            this.driveDataUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_eco_driving_distance").setValue(((MsgMgr.this.mCanbusInfoInt[9] << 8) | MsgMgr.this.mCanbusInfoInt[10]) + " KM"));
        }

        /* renamed from: lambda$setOnParseListeners$8$com-hzbhd-canbus-car-_30-MsgMgr$19, reason: not valid java name */
        /* synthetic */ void m452lambda$setOnParseListeners$8$comhzbhdcanbuscar_30MsgMgr$19() {
            this.driveDataUpdateList.add(MsgMgr.this.getDriveUpdateEntity("_30_carbon_dioxide_emission_reduction").setValue((((MsgMgr.this.mCanbusInfoInt[11] << 8) | MsgMgr.this.mCanbusInfoInt[12]) / 10.0f) + " Kg"));
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            MsgMgr msgMgr = MsgMgr.this;
            if (msgMgr.isDataChange(msgMgr.mCanbusInfoInt)) {
                this.driveDataUpdateList = new ArrayList();
                this.settingUpdateList = new ArrayList();
                super.parse(i);
                MsgMgr.this.updateGeneralSettingData(this.settingUpdateList);
                MsgMgr.this.updateSettingActivity(null);
                MsgMgr.this.updateGeneralDriveData(this.driveDataUpdateList);
                MsgMgr.this.updateDriveDataActivity(null);
            }
        }
    }

    /* renamed from: com.hzbhd.canbus.car._30.MsgMgr$20, reason: invalid class name */
    class AnonymousClass20 extends Parser {
        static /* synthetic */ void lambda$setOnParseListeners$0() {
        }

        static /* synthetic */ void lambda$setOnParseListeners$1() {
        }

        AnonymousClass20(String str) {
            super(str);
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$20$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    MsgMgr.AnonymousClass20.lambda$setOnParseListeners$0();
                }
            }, new OnParseListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr$20$$ExternalSyntheticLambda1
                @Override // com.hzbhd.canbus.car._30.MsgMgr.OnParseListener
                public final void onParse() {
                    MsgMgr.AnonymousClass20.lambda$setOnParseListeners$1();
                }
            }};
        }

        @Override // com.hzbhd.canbus.car._30.MsgMgr.Parser
        public void parse(int i) {
            super.parse(i);
        }
    }

    private void set0x7D0x08TrackData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            byte[] bArr = this.mCanbusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[4], 0, 540, 16);
            updateParkUi(null, context);
        }
    }

    protected static <T> List<T> mergeList(List<T>... listArr) {
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

    /* JADX WARN: Removed duplicated region for block: B:31:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.hzbhd.canbus.entity.TireUpdateEntity getTireEntity(int r17, java.lang.String r18, java.lang.String r19, boolean r20, boolean r21, int r22, int r23, boolean r24) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._30.MsgMgr.getTireEntity(int, java.lang.String, java.lang.String, boolean, boolean, int, int, boolean):com.hzbhd.canbus.entity.TireUpdateEntity");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        int i2 = (int) ((i / 40.0f) * 35.0f);
        this.mVolume = i2;
        CanbusMsgSender.sendMsg(new byte[]{22, 5, (byte) i2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte) i6, (byte) i8, z ? (byte) 1 : (byte) 0});
        this.mHandler.postDelayed(this.mVersionRequestRunnable, 1000L);
        sendCarInfo();
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
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, 9, -126, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        int i8;
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
            i8 = 1;
        } else {
            i8 = 19;
        }
        int[] time = getTime(i);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, 9, (byte) i8, (byte) (i3 >> 8), (byte) (i3 & 255), (byte) time[0], (byte) time[1], (byte) time[2]});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v7, types: [int] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int i3;
        boolean z;
        ?? r8;
        boolean zEquals = str.equals("FM2");
        if (str.equals("FM3")) {
            zEquals = 2;
        }
        if (str.contains("AM")) {
            z = 3;
            i3 = 9;
        } else {
            i3 = 2;
            z = zEquals;
        }
        boolean z2 = z;
        if (str.equals("AM1")) {
            z2 = 4;
        }
        int i4 = 10;
        boolean z3 = z2;
        if (str.equals("LW")) {
            z3 = 5;
            i3 = 10;
        }
        if (str.equals("WB")) {
            r8 = 6;
        } else {
            i4 = i3;
            r8 = z3;
        }
        float f = Float.parseFloat(str2);
        if (str.contains("FM")) {
            f *= 100.0f;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, 9, (byte) i4, (byte) r8, (byte) (f / 100.0f), (byte) (f % 100.0f)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, 9, 5, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, 9, 5, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 3});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 6});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        if (!z) {
            CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 5});
        } else if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 9, 14, 4});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, 9, 11, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, 9, (byte) (b == 9 ? 12 : 22), b7, (byte) i, b3, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, 9, (byte) (b == 9 ? 12 : 21), b6, (byte) i, b3, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, 9, 18, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanbusInfoInt[3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realKeyClick3_1(Context context, int i) {
        realKeyClick3_1(context, i, 0, this.mCanbusInfoInt[3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realKeyClick3_2(Context context, int i) {
        realKeyClick3_2(context, i, 0, this.mCanbusInfoInt[3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDoorStatusChange() {
        if (!(this.mIsLeftFrontOpen ^ GeneralDoorData.isLeftFrontDoorOpen) && !(this.mIsRightFrontOpen ^ GeneralDoorData.isRightFrontDoorOpen) && !(this.mIsLeftRearOpen ^ GeneralDoorData.isLeftRearDoorOpen) && !(this.mIsRightRearOpen ^ GeneralDoorData.isRightRearDoorOpen) && !(this.mIsBackOpen ^ GeneralDoorData.isBackOpen) && !(this.mIsFrontOpen ^ GeneralDoorData.isFrontOpen)) {
            return false;
        }
        this.mIsLeftFrontOpen = GeneralDoorData.isLeftFrontDoorOpen;
        this.mIsRightFrontOpen = GeneralDoorData.isRightFrontDoorOpen;
        this.mIsLeftRearOpen = GeneralDoorData.isLeftRearDoorOpen;
        this.mIsRightRearOpen = GeneralDoorData.isRightRearDoorOpen;
        this.mIsBackOpen = GeneralDoorData.isBackOpen;
        this.mIsFrontOpen = GeneralDoorData.isFrontOpen;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void sendLanguageInit(Context context) {
        SettingUpdateEntity settingUpdateEntity = getSettingUpdateEntity("_306_value_11");
        getUiMgr(context).getSettingUiSet(context).getOnSettingItemSelectListener().onClickItem(settingUpdateEntity.getLeftListIndex(), settingUpdateEntity.getRightListIndex(), SharePreUtil.getIntValue(context, SHARE_30_LANGUAGE, 0));
    }

    void updateSettingItem(String str, Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity(str).setValue(obj));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void saveAmplifierData(Context context) {
        saveAmplifierData(context, this.mCanId);
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private void switchPanoramic(Context context) {
        forceReverse(context, !CommUtil.isPanoramic(context));
    }

    private void initVehicleType() {
        int i = this.mDifferent;
        if (i == 20) {
            this.isK4 = true;
            return;
        }
        if (i != 21) {
            switch (i) {
                case 1:
                    this.is1819KiaSportage = true;
                    break;
                case 2:
                    this.is19Lafesta = true;
                    break;
                case 3:
                    this.is19Santafe = true;
                    break;
                case 4:
                    this.is1920Tucson = true;
                    break;
                case 5:
                    this.isKx5Top = true;
                    break;
                case 6:
                    this.is19KiaK3 = true;
                    break;
                case 7:
                    this.is18SonataHybrid = true;
                    break;
                case 8:
                    this.is20K3 = true;
                    break;
                case 9:
                    this.is19K3NewEnergy = true;
                    break;
                case 10:
                    this.is19K5NewEnergy = true;
                    break;
                case 11:
                    this.is20EncinoElectrical = true;
                    break;
            }
            return;
        }
        this.is15Carnival = true;
    }

    private void initSettingsItem(Context context) {
        Log.i(TAG, "initSettingsItem: ");
        this.mSettingItemIndeHashMap = new HashMap<>();
        SparseArray<String> sparseArray = new SparseArray<>();
        List<SettingPageUiSet.ListBean> list = getUiMgr(context).getSettingUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                String titleSrn = itemList.get(i2).getTitleSrn();
                sparseArray.append((i << 8) | i2, titleSrn);
                this.mSettingItemIndeHashMap.put(titleSrn, new SettingUpdateEntity(i, i2, null));
            }
        }
        getUiMgr(context).setSettingsItemArray(sparseArray);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        this.mSettingItemIndeHashMap.put(str, new SettingUpdateEntity(-1, -1, null));
        return getSettingUpdateEntity(str);
    }

    private void initDriveItem(Context context) {
        this.mDriveItemIndeHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = getUiMgr(context).getDriverDataPageUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), new DriverUpdateEntity(i, i2, "set_default"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DriverUpdateEntity getDriveUpdateEntity(String str) {
        if (this.mDriveItemIndeHashMap.containsKey(str)) {
            return this.mDriveItemIndeHashMap.get(str);
        }
        this.mDriveItemIndeHashMap.put(str, new DriverUpdateEntity(-1, -1, "set_default"));
        return getDriveUpdateEntity(str);
    }

    private abstract class Parser {
        final String MSG;
        private final int PARSE_LISTENERS_LENGTH;
        final OnParseListener[] onParseListeners;

        void init() {
        }

        public abstract OnParseListener[] setOnParseListeners();

        public Parser(String str) {
            this.MSG = str;
            init();
            OnParseListener[] onParseListeners = setOnParseListeners();
            this.onParseListeners = onParseListeners;
            if (onParseListeners == null) {
                this.PARSE_LISTENERS_LENGTH = 0;
            } else {
                this.PARSE_LISTENERS_LENGTH = onParseListeners.length;
            }
            Log.i(MsgMgr.TAG, "Parser: " + str + " length " + this.PARSE_LISTENERS_LENGTH);
        }

        public void parse(int i) {
            for (int iMin = Math.min(i, this.PARSE_LISTENERS_LENGTH); iMin > 0; iMin--) {
                OnParseListener onParseListener = this.onParseListeners[iMin - 1];
                if (onParseListener != null) {
                    onParseListener.onParse();
                }
            }
        }

        public void log(String str) {
            Log.i(MsgMgr.TAG, this.MSG + "<-->" + str);
        }
    }

    boolean isK4() {
        return this.isK4;
    }

    boolean is15Carnival() {
        return this.is15Carnival;
    }

    boolean is1819KiaSportage() {
        return this.is1819KiaSportage;
    }

    boolean is19Lafesta() {
        return this.is19Lafesta;
    }

    boolean is19Santafe() {
        return this.is19Santafe;
    }

    boolean is1920Tucson() {
        return this.is1920Tucson;
    }

    boolean isKx5Top() {
        return this.isKx5Top;
    }

    boolean is19KiaK3() {
        return this.is19KiaK3;
    }

    boolean is18SonataHybrid() {
        return this.is18SonataHybrid;
    }

    boolean is20K3() {
        return this.is20K3;
    }

    boolean is19K3NewEnergy() {
        return this.is19K3NewEnergy;
    }

    boolean is19K5NewEnergy() {
        return this.is19K5NewEnergy;
    }

    boolean is20EncinoElectrical() {
        return this.is20EncinoElectrical;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getThemeColorIndex(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = THEME_COLOR_INDEXES;
            if (i2 >= iArr.length) {
                return 0;
            }
            if (iArr[i2] == i) {
                return i2;
            }
            i2++;
        }
    }

    int[] getMsgDatas(int i) {
        SparseArray<int[]> sparseArray = this.mCanbusDataArray;
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(i);
    }

    private class Page {
        private Page[] childs;
        private final String title;

        public Page(String str) {
            this.title = str;
        }

        public Page(String str, Page[] pageArr) {
            this.title = str;
            this.childs = pageArr;
        }

        public String getTitle() {
            return this.title;
        }

        public Page getChildByTitle(String str) {
            Page[] pageArr = this.childs;
            if (pageArr == null) {
                return null;
            }
            for (Page page : pageArr) {
                if (page.getTitle().equals(str)) {
                    return page;
                }
            }
            return null;
        }

        public List<SettingUpdateEntity> setChildsEnable(boolean z) {
            List<SettingUpdateEntity> childsEnable;
            if (this.childs == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Page page : this.childs) {
                SettingUpdateEntity settingUpdateEntity = MsgMgr.this.getSettingUpdateEntity(page.getTitle());
                if (settingUpdateEntity.isEnable() ^ z) {
                    arrayList.add(settingUpdateEntity.setEnable(z));
                    if (!z && (childsEnable = page.setChildsEnable(z)) != null) {
                        arrayList.addAll(childsEnable);
                    }
                }
            }
            return arrayList;
        }

        public List<SettingUpdateEntity> onSwitch() {
            if (this.childs == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Page page : this.childs) {
                arrayList.add(MsgMgr.this.getSettingUpdateEntity(page.getTitle()).setEnable(!r4.isEnable()));
            }
            return arrayList;
        }
    }

    private void initPgae() {
        this.mSettingPage = new Page("settings", new Page[]{new Page("_30_new_energy_53_0", new Page[]{new Page("_30_start_time_1"), new Page("_30_end_time_1"), new Page("_30_new_energy_repeat_1", new Page[]{new Page("_30_new_energy_sunday_1"), new Page("_30_new_energy_monday_1"), new Page("_30_new_energy_tuesday_1"), new Page("_30_new_energy_wednesday_1"), new Page("_30_new_energy_thursday_1"), new Page("_30_new_energy_friday_1"), new Page("_30_new_energy_saturday_1")})}), new Page("_30_new_energy_53_B_1", new Page[]{new Page("_30_new_energy_53_4"), new Page("_30_new_energy_repeat_2", new Page[]{new Page("_30_new_energy_sunday_2"), new Page("_30_new_energy_monday_2"), new Page("_30_new_energy_tuesday_2"), new Page("_30_new_energy_wednesday_2"), new Page("_30_new_energy_thursday_2"), new Page("_30_new_energy_friday_2"), new Page("_30_new_energy_saturday_2")})}), new Page("_30_new_energy_53_B_2", new Page[]{new Page("_30_new_energy_53_6"), new Page("_30_new_energy_repeat_3", new Page[]{new Page("_30_new_energy_sunday_3"), new Page("_30_new_energy_monday_3"), new Page("_30_new_energy_tuesday_3"), new Page("_30_new_energy_wednesday_3"), new Page("_30_new_energy_thursday_3"), new Page("_30_new_energy_friday_3"), new Page("_30_new_energy_saturday_3")})}), new Page("_30_new_energy_53_B_3", new Page[]{new Page("_30_start_time_2"), new Page("_30_end_time_2")}), new Page("_30_new_energy_53_C_1", new Page[]{new Page("_30_new_energy_53_C_10")}), new Page("_30_approximate_range_fast_charge", new Page[]{new Page("_30_set_charge_amount_1")}), new Page("_30_approximate_range_normal", new Page[]{new Page("_30_set_charge_amount_2")}), new Page("_30_eco_mode", new Page[]{new Page("_30_maximum_speed_limit"), new Page("_30_taxi_energy_regeneration_1"), new Page("_30_air_conditioning_control_1")}), new Page("_30_com_mode", new Page[]{new Page("_30_taxi_energy_regeneration_2"), new Page("_30_air_conditioning_control_2")}), new Page("_30_sport_mode", new Page[]{new Page("_30_taxi_energy_regeneration_3"), new Page("_30_air_conditioning_control_3")})});
    }

    void pageSwitch(String... strArr) {
        List<SettingUpdateEntity> listOnSwitch;
        if (ArrayUtils.isEmpty(strArr)) {
            return;
        }
        Page childByTitle = this.mSettingPage.getChildByTitle(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            childByTitle = childByTitle.getChildByTitle(strArr[i]);
        }
        if (childByTitle == null || (listOnSwitch = childByTitle.onSwitch()) == null) {
            return;
        }
        updateGeneralSettingData(listOnSwitch);
        updateSettingActivity(null);
    }

    private static class SeatStatusView {
        private boolean isShowing;
        private final Runnable mDismissRunnable = new Runnable() { // from class: com.hzbhd.canbus.car._30.MsgMgr.SeatStatusView.1
            @Override // java.lang.Runnable
            public void run() {
                SeatStatusView.this.dismissView();
            }
        };
        private RelativeLayout mFloatView;
        private ViewHelper mIvBackrestBack;
        private ViewHelper mIvBackrestDown;
        private ViewHelper mIvBackrestForward;
        private ViewHelper mIvBackrestSelelct;
        private ViewHelper mIvBackrestUp;
        private ViewHelper mIvSeatBack;
        private ViewHelper mIvSeatDown;
        private ViewHelper mIvSeatForward;
        private ViewHelper mIvSeatSelect;
        private ViewHelper mIvSeatUp;
        private WindowManager.LayoutParams mLayoutParams;
        private final WindowManager mWindowManager;

        public SeatStatusView(Context context) {
            this.mWindowManager = (WindowManager) context.getSystemService("window");
            findView(context);
            initView();
        }

        private void findView(Context context) {
            this.mFloatView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.layout_30_seat_status, (ViewGroup) null);
            this.mIvSeatSelect = new ViewHelper(this.mFloatView.findViewById(R.id.iv_seat_select));
            this.mIvBackrestSelelct = new ViewHelper(this.mFloatView.findViewById(R.id.iv_backrest_select));
            this.mIvSeatForward = new ViewHelper(this.mFloatView.findViewById(R.id.iv_seat_forward));
            this.mIvSeatBack = new ViewHelper(this.mFloatView.findViewById(R.id.iv_seat_back));
            this.mIvSeatUp = new ViewHelper(this.mFloatView.findViewById(R.id.iv_seat_up));
            this.mIvSeatDown = new ViewHelper(this.mFloatView.findViewById(R.id.iv_seat_down));
            this.mIvBackrestUp = new ViewHelper(this.mFloatView.findViewById(R.id.iv_backrest_up));
            this.mIvBackrestDown = new ViewHelper(this.mFloatView.findViewById(R.id.iv_backrest_down));
            this.mIvBackrestForward = new ViewHelper(this.mFloatView.findViewById(R.id.iv_backrest_forward));
            this.mIvBackrestBack = new ViewHelper(this.mFloatView.findViewById(R.id.iv_backrest_back));
        }

        private void initView() {
            this.mFloatView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._30.MsgMgr.SeatStatusView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SeatStatusView.this.mFloatView.removeCallbacks(SeatStatusView.this.mDismissRunnable);
                    SeatStatusView.this.mFloatView.post(SeatStatusView.this.mDismissRunnable);
                }
            });
        }

        public void addViewToWindow() {
            if (this.mLayoutParams == null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                this.mLayoutParams = layoutParams;
                layoutParams.type = 2002;
                this.mLayoutParams.gravity = 17;
                this.mLayoutParams.width = -2;
                this.mLayoutParams.height = -2;
            }
            if (!this.isShowing) {
                this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
                this.isShowing = true;
            }
            this.mFloatView.removeCallbacks(this.mDismissRunnable);
            this.mFloatView.postDelayed(this.mDismissRunnable, 5000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dismissView() {
            RelativeLayout relativeLayout;
            WindowManager windowManager = this.mWindowManager;
            if (windowManager == null || (relativeLayout = this.mFloatView) == null) {
                return;
            }
            windowManager.removeView(relativeLayout);
            this.isShowing = false;
        }

        public void setSeatSelect(boolean z) {
            this.mIvSeatSelect.setShowView(z);
        }

        public void setBackrestSelelct(boolean z) {
            this.mIvBackrestSelelct.setShowView(z);
        }

        public void setSeatForward(boolean z) {
            this.mIvSeatForward.setShowView(z);
        }

        public void setSeatBack(boolean z) {
            this.mIvSeatBack.setShowView(z);
        }

        public void setSeatUp(boolean z) {
            this.mIvSeatUp.setShowView(z);
        }

        public void setSeatDown(boolean z) {
            this.mIvSeatDown.setShowView(z);
        }

        public void setBackrestUp(boolean z) {
            this.mIvBackrestUp.setShowView(z);
        }

        public void setBackrestDown(boolean z) {
            this.mIvBackrestDown.setShowView(z);
        }

        public void setBackrestForward(boolean z) {
            this.mIvBackrestForward.setShowView(z);
        }

        public void setBackrestBackward(boolean z) {
            this.mIvBackrestBack.setShowView(z);
        }

        private class ViewHelper {
            private final View view;

            public ViewHelper(View view) {
                this.view = view;
            }

            public void setShowView(boolean z) {
                this.view.setVisibility(z ? 0 : 4);
            }
        }
    }
}
