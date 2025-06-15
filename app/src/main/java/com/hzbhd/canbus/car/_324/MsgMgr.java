package com.hzbhd.canbus.car._324;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._324.MsgMgr;
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
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
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
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_POSITION_MID = 7;
    public static final int AMPLIFIER_PROGRESS_OFFSET = 2;
    private static final String TAG = "_324_MsgMgr";
    private Integer lastClickKey;
    private Integer lastClickState;
    private Context mContext;
    private int mOrigiRadioPreset;
    private UiMgr mUiMgr;
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private int mCanId = -1;
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private int mOrigiSource = -1;
    private OrigiRadioBand mOrigiRadioBand = OrigiRadioBand.FM;

    /* compiled from: MsgMgr.kt */
    
    public enum OriginalSource {
        OFF(0),
        TUNER(1),
        DISC(2);

        private final int value;

        OriginalSource(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    /* compiled from: MsgMgr.kt */
    
    public enum OrigiRadioBand {
        FM(true),
        FM2(true),
        AM(false);

        private final boolean isFm;

        OrigiRadioBand(boolean z) {
            this.isFm = z;
        }

        /* renamed from: isFm, reason: from getter */
        public final boolean getIsFm() {
            return this.isFm;
        }
    }

    public final int getMOrigiSource() {
        return this.mOrigiSource;
    }

    public final OrigiRadioBand getMOrigiRadioBand() {
        return this.mOrigiRadioBand;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    private final void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final Iterator it = ArrayIteratorKt.iterator(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 1, (byte) (7 - GeneralAmplifierData.frontRear)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}});
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initAmplifier$2$1$1
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
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
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

    public final Integer getLastClickKey() {
        return this.lastClickKey;
    }

    public final void setLastClickKey(Integer num) {
        this.lastClickKey = num;
    }

    public final Integer getLastClickState() {
        return this.lastClickState;
    }

    public final void setLastClickState(Integer num) {
        this.lastClickState = num;
    }

    private final void initParsers(final Context context) {
        SparseArray<Parser> sparseArray = this.mParserArray;
        sparseArray.put(1, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x01】方向盘按键信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.this$0.mCanbusInfoInt[3] != 0) {
                    switch (this.this$0.mCanbusInfoInt[2]) {
                        case 0:
                            this.this$0.realKeyClick4(context, 0);
                            break;
                        case 1:
                            this.this$0.realKeyClick4(context, 7);
                            break;
                        case 2:
                            this.this$0.realKeyClick4(context, 8);
                            break;
                        case 3:
                            this.this$0.realKeyClick4(context, 46);
                            break;
                        case 4:
                            this.this$0.realKeyClick4(context, 45);
                            break;
                        case 5:
                            this.this$0.realKeyClick4(context, 2);
                            break;
                        case 6:
                            this.this$0.realKeyClick4(context, 14);
                            break;
                        case 7:
                            this.this$0.realKeyClick4(context, 15);
                            break;
                        case 8:
                            this.this$0.realKeyClick4(context, HotKeyConstant.K_SPEECH);
                            break;
                        case 9:
                            this.this$0.realKeyClick4(context, 3);
                            break;
                        case 10:
                            this.this$0.realKeyClick4(context, 50);
                            break;
                        case 11:
                            this.this$0.realKeyClick4(context, 151);
                            break;
                        case 12:
                            this.this$0.realKeyClick4(context, 49);
                            break;
                        case 13:
                            this.this$0.realKeyClick4(context, HotKeyConstant.K_PHONE_ON_OFF);
                            break;
                    }
                }
            }
        });
        sparseArray.append(16, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$2
            {
                super(this.this$0, "【0x10】Radio 预存台信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                boolean z;
                String str;
                if (this.this$0.getMOrigiSource() == MsgMgr.OriginalSource.TUNER.getValue() && isDataChange()) {
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 1 || i == 2) {
                        z = true;
                    } else if (i != 3) {
                        return;
                    } else {
                        z = false;
                    }
                    MsgMgr msgMgr = this.this$0;
                    ArrayList arrayList = new ArrayList();
                    int i2 = 1;
                    while (i2 < 7) {
                        StringBuilder sbAppend = new StringBuilder().append('P').append(i2).append("  ");
                        int i3 = i2 * 2;
                        int i4 = (msgMgr.mCanbusInfoInt[i3 + 1] << 8) | msgMgr.mCanbusInfoInt[i3 + 2];
                        if (z) {
                            str = (i4 / 10) + "kHz";
                        } else {
                            str = i4 + "MHz";
                        }
                        arrayList.add(new SongListEntity(sbAppend.append(str).toString()).setSelected(msgMgr.mOrigiRadioPreset == i2));
                        i2++;
                    }
                    GeneralOriginalCarDeviceData.songList = arrayList;
                    Log.i("_324_MsgMgr", "parse: " + GeneralOriginalCarDeviceData.songList.size());
                    List<SongListEntity> songList = GeneralOriginalCarDeviceData.songList;

                    Iterator<T> it = songList.iterator();
                    while (it.hasNext()) {
                        Log.i("_324_MsgMgr", "parse: " + ((SongListEntity) it.next()).getTitle());
                    }
                    msgMgr.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(17, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x11】多媒体按键信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    if (this.this$0.mCanbusInfoInt[3] != 0) {
                        int i = this.this$0.mCanbusInfoInt[2];
                        if (i != 48) {
                            switch (i) {
                                case 0:
                                    this.this$0.realKeyClick4(context, 0);
                                    break;
                                case 1:
                                    this.this$0.realKeyClick4(context, HotKeyConstant.K_SLEEP);
                                    break;
                                case 2:
                                    this.this$0.realKeyClick4(context, 7);
                                    break;
                                case 3:
                                    this.this$0.realKeyClick4(context, 8);
                                    break;
                                case 4:
                                    enterOriginalDevice();
                                    break;
                                case 5:
                                    enterOriginalDevice();
                                    break;
                                case 6:
                                    this.this$0.realKeyClick4(context, 2);
                                    break;
                                case 7:
                                    enterOriginalDevice();
                                    break;
                                case 8:
                                    this.this$0.realKeyClick4(context, 48);
                                    break;
                                case 9:
                                    this.this$0.realKeyClick4(context, 47);
                                    break;
                                default:
                                    switch (i) {
                                        case 16:
                                            this.this$0.realKeyClick4(context, 46);
                                            break;
                                        case 17:
                                            this.this$0.realKeyClick4(context, 45);
                                            break;
                                        case 18:
                                            this.this$0.realKeyClick4(context, 30);
                                            break;
                                        default:
                                            switch (i) {
                                                case 32:
                                                    this.this$0.realKeyClick4(context, 52);
                                                    break;
                                                case 33:
                                                    this.this$0.realKeyClick4(context, 128);
                                                    break;
                                                case 34:
                                                    this.this$0.realKeyClick4(context, 50);
                                                    break;
                                                case 35:
                                                    this.this$0.realKeyClick4(context, 151);
                                                    break;
                                                case 36:
                                                    this.this$0.realKeyClick4(context, 49);
                                                    break;
                                                case 37:
                                                    this.this$0.realKeyClick4(context, 45);
                                                    break;
                                                case 38:
                                                    this.this$0.realKeyClick4(context, 46);
                                                    break;
                                                case 39:
                                                    this.this$0.realKeyClick4(context, 47);
                                                    break;
                                                case 40:
                                                    this.this$0.realKeyClick4(context, 48);
                                                    break;
                                                case 41:
                                                    this.this$0.realKeyClick4(context, 53);
                                                    break;
                                                case 42:
                                                    MsgMgr msgMgr = this.this$0;
                                                    msgMgr.startDrivingDataActivity(msgMgr.mContext, 0);
                                                    break;
                                                case 43:
                                                    this.this$0.realKeyClick4(context, 58);
                                                    break;
                                            }
                                    }
                            }
                        } else {
                            MsgMgr msgMgr2 = this.this$0;
                            msgMgr2.startOtherActivity(msgMgr2.mContext, HzbhdComponentName.CanBusAirActivity);
                        }
                    }
                    MsgMgr msgMgr3 = this.this$0;
                    msgMgr3.setLastClickKey(Integer.valueOf(msgMgr3.mCanbusInfoInt[2]));
                    MsgMgr msgMgr4 = this.this$0;
                    msgMgr4.setLastClickState(Integer.valueOf(msgMgr4.mCanbusInfoInt[3]));
                }
            }

            private final void enterOriginalDevice() {
                this.this$0.enterAuxIn2(context, Constant.OriginalDeviceActivity);
            }
        });
        sparseArray.append(26, new MsgMgr$initParsers$1$4(this, context));
        sparseArray.append(27, new MsgMgr$initParsers$1$5(this, context));
        sparseArray.append(28, new Parser(context) { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$6
            final /* synthetic */ Context $context;
            private final SparseArray<OriginalCarDevicePageUiSet> originalPageArray;
            private OriginalCarDevicePageUiSet pageUiSet;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x1C】Source 信息");
                this.$context = context;
                UiMgr uiMgr = this.this$0.mUiMgr;
                if (uiMgr == null) {

                    uiMgr = null;
                }
                this.pageUiSet = uiMgr.getOriginalCarDevicePageUiSet(context);
                SparseArray<OriginalCarDevicePageUiSet> sparseArray2 = new SparseArray<>();
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet = new OriginalCarDevicePageUiSet();
                originalCarDevicePageUiSet.setHavePlayTimeSeekBar(false);
                originalCarDevicePageUiSet.setHaveSongList(false);
                originalCarDevicePageUiSet.setRowTopBtnAction(null);
                originalCarDevicePageUiSet.setRowBottomBtnAction(null);
                originalCarDevicePageUiSet.setItems(CollectionsKt.emptyList());
                Unit unit = Unit.INSTANCE;
                sparseArray2.put(0, originalCarDevicePageUiSet);
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet2 = new OriginalCarDevicePageUiSet();
                originalCarDevicePageUiSet2.setHavePlayTimeSeekBar(false);
                originalCarDevicePageUiSet2.setHaveSongList(true);
                originalCarDevicePageUiSet2.setRowTopBtnAction(new String[0]);
                originalCarDevicePageUiSet2.setRowBottomBtnAction(new String[0]);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_band", ""));
                arrayList.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_frequency", ""));
                arrayList.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_preset", ""));
                originalCarDevicePageUiSet2.setItems(arrayList);
                Unit unit2 = Unit.INSTANCE;
                sparseArray2.append(1, originalCarDevicePageUiSet2);
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet3 = new OriginalCarDevicePageUiSet();
                originalCarDevicePageUiSet3.setHavePlayTimeSeekBar(false);
                originalCarDevicePageUiSet3.setHaveSongList(true);
                originalCarDevicePageUiSet3.setRowTopBtnAction(new String[]{OriginalBtnAction.DISC_SCAN, OriginalBtnAction.RDM_DISC, OriginalBtnAction.RPT_DISC, OriginalBtnAction.SCAN, OriginalBtnAction.RDM, OriginalBtnAction.RPT});
                originalCarDevicePageUiSet3.setRowBottomBtnAction(new String[]{"up", "down"});
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new OriginalCarDevicePageUiSet.Item("default_image", "_186_current_disc", ""));
                arrayList2.add(new OriginalCarDevicePageUiSet.Item("default_image", "_324_track_info", ""));
                arrayList2.add(new OriginalCarDevicePageUiSet.Item("default_image", "_308_title_20", ""));
                originalCarDevicePageUiSet3.setItems(arrayList2);
                Unit unit3 = Unit.INSTANCE;
                sparseArray2.append(2, originalCarDevicePageUiSet3);
                this.originalPageArray = sparseArray2;
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                Bundle bundle;
                MsgMgr.OrigiRadioBand origiRadioBand;
                if (isDataChange()) {
                    if (this.this$0.getMOrigiSource() != this.this$0.mCanbusInfoInt[2]) {
                        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = this.originalPageArray.get(this.this$0.mCanbusInfoInt[2]);
                        if (originalCarDevicePageUiSet != null) {
                            this.pageUiSet.setHaveSongList(originalCarDevicePageUiSet.isHaveSongList());
                            this.pageUiSet.setRowTopBtnAction(originalCarDevicePageUiSet.getRowTopBtnAction());
                            this.pageUiSet.setRowBottomBtnAction(originalCarDevicePageUiSet.getRowBottomBtnAction());
                            this.pageUiSet.setItems(originalCarDevicePageUiSet.getItems());
                        }
                        GeneralOriginalCarDeviceData.runningState = " ";
                        ArrayList arrayList = new ArrayList();
                        int size = this.pageUiSet.getItems().size();
                        for (int i = 0; i < size; i++) {
                            arrayList.add(new OriginalCarDeviceUpdateEntity(i, ""));
                        }
                        GeneralOriginalCarDeviceData.mList = arrayList;
                        GeneralOriginalCarDeviceData.songList = CollectionsKt.emptyList();
                    }
                    int i2 = this.this$0.mCanbusInfoInt[2];
                    if (i2 == 0) {
                        GeneralOriginalCarDeviceData.cdStatus = "OFF";
                    } else if (i2 == 1) {
                        GeneralOriginalCarDeviceData.cdStatus = "TUNER";
                        int i3 = this.this$0.mCanbusInfoInt[4];
                        if (i3 == 1) {
                            origiRadioBand = MsgMgr.OrigiRadioBand.FM;
                        } else if (i3 == 2) {
                            origiRadioBand = MsgMgr.OrigiRadioBand.FM2;
                        } else if (i3 != 17) {
                            return;
                        } else {
                            origiRadioBand = MsgMgr.OrigiRadioBand.AM;
                        }
                        MsgMgr msgMgr = this.this$0;
                        msgMgr.mOrigiRadioBand = origiRadioBand;
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(new OriginalCarDeviceUpdateEntity(0, origiRadioBand.name()));
                        int i4 = (msgMgr.mCanbusInfoInt[5] << 8) | msgMgr.mCanbusInfoInt[6];
                        arrayList2.add(new OriginalCarDeviceUpdateEntity(1, origiRadioBand.getIsFm() ? (i4 / 10) + "kHz" : i4 + "MHz"));
                        msgMgr.mOrigiRadioPreset = msgMgr.mCanbusInfoInt[7];
                        arrayList2.add(new OriginalCarDeviceUpdateEntity(2, new StringBuilder().append('P').append(msgMgr.mCanbusInfoInt[7]).toString()));
                        GeneralOriginalCarDeviceData.mList = arrayList2;
                        Context context2 = this.$context;
                        int i5 = this.this$0.mCanbusInfoInt[8];
                        GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context2, i5 != 0 ? i5 != 1 ? i5 != 2 ? "null_value" : "_324_searching" : "ford_original_status2" : "ford_original_status1");
                    } else if (i2 == 2) {
                        GeneralOriginalCarDeviceData.cdStatus = "DISC";
                        ArrayList arrayList3 = new ArrayList();
                        Context context3 = this.$context;
                        MsgMgr msgMgr2 = this.this$0;
                        arrayList3.add(new OriginalCarDeviceUpdateEntity(0, CommUtil.getStrByResId(context3, "_279_disc") + ' ' + (msgMgr2.mCanbusInfoInt[4] < 1 ? 1 : msgMgr2.mCanbusInfoInt[4])));
                        arrayList3.add(new OriginalCarDeviceUpdateEntity(1, msgMgr2.mCanbusInfoInt[6] != 0 ? msgMgr2.mCanbusInfoInt[5] + " / " + msgMgr2.mCanbusInfoInt[6] : ""));
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(msgMgr2.mCanbusInfoInt[8]), Integer.valueOf(msgMgr2.mCanbusInfoInt[9])}, 2));

                        arrayList3.add(new OriginalCarDeviceUpdateEntity(2, str));
                        GeneralOriginalCarDeviceData.mList = arrayList3;
                        GeneralOriginalCarDeviceData.disc_scan = ((this.this$0.mCanbusInfoInt[7] >> 6) & 1) == 1;
                        GeneralOriginalCarDeviceData.rdm_disc = ((this.this$0.mCanbusInfoInt[7] >> 5) & 1) == 1;
                        GeneralOriginalCarDeviceData.rpt_disc = ((this.this$0.mCanbusInfoInt[7] >> 4) & 1) == 1;
                        GeneralOriginalCarDeviceData.scan = ((this.this$0.mCanbusInfoInt[7] >> 2) & 1) == 1;
                        GeneralOriginalCarDeviceData.rdm = ((this.this$0.mCanbusInfoInt[7] >> 1) & 1) == 1;
                        GeneralOriginalCarDeviceData.rpt = (this.this$0.mCanbusInfoInt[7] & 1) == 1;
                    }
                    MsgMgr msgMgr3 = this.this$0;
                    if (msgMgr3.getMOrigiSource() != this.this$0.mCanbusInfoInt[2]) {
                        MsgMgr msgMgr4 = this.this$0;
                        msgMgr4.mOrigiSource = msgMgr4.mCanbusInfoInt[2];
                        bundle = new Bundle();
                        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                    } else {
                        bundle = null;
                    }
                    msgMgr3.updateOriginalCarDeviceActivity(bundle);
                }
            }
        });
        sparseArray.append(29, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x1D】DISC 状态信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                String strByResId;
                if (this.this$0.getMOrigiSource() == MsgMgr.OriginalSource.DISC.getValue() && isDataChange()) {
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    int[] iArr = {(msgMgr.mCanbusInfoInt[2] >> 6) & 3, (msgMgr.mCanbusInfoInt[2] >> 4) & 3, (msgMgr.mCanbusInfoInt[2] >> 2) & 3, msgMgr.mCanbusInfoInt[2] & 3, (msgMgr.mCanbusInfoInt[3] >> 6) & 3, (msgMgr.mCanbusInfoInt[3] >> 4) & 3};
                    int i = 0;
                    for (int i2 = 0; i2 < 6; i2++) {
                        int i3 = iArr[i2];
                        i++;
                        StringBuilder sbAppend = new StringBuilder().append(i).append(" - ");
                        if (i3 != 0) {
                            strByResId = i3 != 1 ? i3 != 2 ? "" : "DVD" : "CD";
                        } else {
                            strByResId = CommUtil.getStrByResId(context2, "_123_divice_status_0");
                        }
                        arrayList.add(new SongListEntity(sbAppend.append(strByResId).toString()));
                    }
                    GeneralOriginalCarDeviceData.songList = arrayList;
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(36, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$8
            private int doorStatus;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x24】车辆状态信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.doorStatus != this.this$0.mCanbusInfoInt[2]) {
                    this.doorStatus = this.this$0.mCanbusInfoInt[2];
                    boolean z = true;
                    GeneralDoorData.isRightFrontDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
                    GeneralDoorData.isLeftFrontDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
                    GeneralDoorData.isRightRearDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 5) & 1) == 1;
                    GeneralDoorData.isLeftRearDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 4) & 1) == 1;
                    if (((this.this$0.mCanbusInfoInt[2] >> 3) & 1) != 1 && (((this.this$0.mCanbusInfoInt[2] >> 2) & 1) != 1 || ((this.this$0.mCanbusInfoInt[2] >> 1) & 1) != 0)) {
                        z = false;
                    }
                    GeneralDoorData.isBackOpen = z;
                    this.this$0.updateDoorView(context);
                }
            }
        });
        sparseArray.append(40, new MsgMgr$initParsers$1$9(this, context));
        sparseArray.append(41, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x29】方向盘转角信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 380, 12) * (-1);
                    this.this$0.updateParkUi(null, context);
                }
                super.parse(dataLength);
            }
        });
        sparseArray.append(49, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$11
            private int asl;
            private final ArrayList<SettingUpdateEntity<Object>> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x31】功放 SOUND 信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[6] & 1;
                MsgMgr msgMgr = this.this$0;
                if (!Integer.valueOf(i).equals(Integer.valueOf(this.asl))) {
                    this.asl = i;
                    this.list.clear();
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_186_asl");
                    if (settingUpdateEntity != null) {
                        this.list.add(settingUpdateEntity.setValue(Integer.valueOf(this.asl)));
                    }
                    msgMgr.updateGeneralSettingData(this.list);
                    msgMgr.updateSettingActivity(null);
                }
                this.this$0.mCanbusInfoInt[6] = 0;
                if (isDataChange()) {
                    GeneralAmplifierData.frontRear = 7 - ((this.this$0.mCanbusInfoInt[2] >> 4) & 15);
                    GeneralAmplifierData.leftRight = (this.this$0.mCanbusInfoInt[2] & 15) - 7;
                    GeneralAmplifierData.bandBass = ((this.this$0.mCanbusInfoInt[3] >> 4) & 15) - 2;
                    GeneralAmplifierData.bandTreble = (this.this$0.mCanbusInfoInt[3] & 15) - 2;
                    GeneralAmplifierData.bandMiddle = ((this.this$0.mCanbusInfoInt[4] >> 4) & 15) - 2;
                    GeneralAmplifierData.volume = this.this$0.mCanbusInfoInt[5];
                    this.this$0.updateAmplifierActivity(null);
                    MsgMgr msgMgr2 = this.this$0;
                    msgMgr2.saveAmplifierData(context, msgMgr2.mCanId);
                }
            }
        });
        sparseArray.append(50, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$12
            private final ArrayList<SettingUpdateEntity<Object>> list;

            {
                super(this.this$0, "【0x32】功放 DSP 信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_143_0xAD_setting7");
                    if (settingUpdateEntity != null) {
                        this.list.add(settingUpdateEntity.setValue(Integer.valueOf(this.this$0.mCanbusInfoInt[2])));
                    }
                    SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_143_0xAD_setting4");
                    if (settingUpdateEntity2 != null) {
                        this.list.add(settingUpdateEntity2.setValue(Integer.valueOf(this.this$0.mCanbusInfoInt[3])));
                    }
                    this.this$0.updateGeneralSettingData(this.list);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        sparseArray.append(90, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$13
            private final ArrayList<DriverUpdateEntity> list;

            {
                super(this.this$0, "【0x5A】行车电脑信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                String result;
                String result2;
                String result3;
                String result4;
                String result5;
                if (isDataChange()) {
                    MsgMgr msgMgr = this.this$0;
                    msgMgr.updateSpeedInfo(DataHandleUtils.getMsbLsbResult(msgMgr.mCanbusInfoInt[8], this.this$0.mCanbusInfoInt[9]));
                    this.list.clear();
                    final int[] iArr = this.this$0.mCanbusInfoInt;
                    MsgMgr msgMgr2 = this.this$0;
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) msgMgr2.mDriveItemIndexHashMap.get("_18_current_fuel");
                    if (driverUpdateEntity != null && (result5 = getResult(ArraysKt.copyOfRange(iArr, 2, 4), 300, new Function1<Integer, String>() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$13$parse$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final String invoke(int i) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = new Object[2];
                            objArr[0] = Float.valueOf(i / 10.0f);
                            objArr[1] = ((iArr[12] >> 6) & 3) == 1 ? "km/l" : "l/100km";
                            String str = String.format("%.1f %s", Arrays.copyOf(objArr, 2));

                            return str;
                        }
                    })) != null) {
                        this.list.add(driverUpdateEntity.setValue(result5));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) msgMgr2.mDriveItemIndexHashMap.get("_324_mean_after_oil");
                    if (driverUpdateEntity2 != null && (result4 = getResult(ArraysKt.copyOfRange(iArr, 4, 6), 999, new Function1<Integer, String>() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$13$parse$1$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final String invoke(int i) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = new Object[2];
                            objArr[0] = Float.valueOf(i / 10.0f);
                            int i2 = (iArr[12] >> 4) & 3;
                            objArr[1] = i2 != 1 ? i2 != 2 ? "l/100km" : "MPG" : "km/l";
                            String str = String.format("%.1f %s", Arrays.copyOf(objArr, 2));

                            return str;
                        }
                    })) != null) {
                        this.list.add(driverUpdateEntity2.setValue(result4));
                    }
                    DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) msgMgr2.mDriveItemIndexHashMap.get("_112_available_mileage");
                    if (driverUpdateEntity3 != null && (result3 = getResult(ArraysKt.copyOfRange(iArr, 6, 8), 9999, new Function1<Integer, String>() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$13$parse$1$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final String invoke(int i) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = new Object[2];
                            objArr[0] = Integer.valueOf(i);
                            objArr[1] = ((iArr[12] >> 3) & 1) == 1 ? "miles" : "km";
                            String str = String.format("%d %s", Arrays.copyOf(objArr, 2));

                            return str;
                        }
                    })) != null) {
                        this.list.add(driverUpdateEntity3.setValue(result3));
                    }
                    DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) msgMgr2.mDriveItemIndexHashMap.get("a_average_speed");
                    if (driverUpdateEntity4 != null && (result2 = getResult(ArraysKt.copyOfRange(iArr, 8, 10), 99999, new Function1<Integer, String>() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$13$parse$1$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final String invoke(int i) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = new Object[2];
                            objArr[0] = Float.valueOf(i / 10.0f);
                            objArr[1] = ((iArr[12] >> 2) & 1) == 1 ? "MPH" : "km/h";
                            String str = String.format("%.1f %s", Arrays.copyOf(objArr, 2));

                            return str;
                        }
                    })) != null) {
                        this.list.add(driverUpdateEntity4.setValue(result2));
                    }
                    DriverUpdateEntity driverUpdateEntity5 = (DriverUpdateEntity) msgMgr2.mDriveItemIndexHashMap.get("_306_value_20");
                    if (driverUpdateEntity5 != null && (result = getResult(ArraysKt.copyOfRange(iArr, 10, 12), 65533, new Function1<Integer, String>() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$13$parse$1$5$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ String invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final String invoke(int i) {
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Object[] objArr = new Object[2];
                            objArr[0] = Float.valueOf(i / 10.0f);
                            objArr[1] = ((iArr[12] >> 1) & 1) == 1 ? "miles" : "km";
                            String str = String.format("%.1f %s", Arrays.copyOf(objArr, 2));

                            return str;
                        }
                    })) != null) {
                        this.list.add(driverUpdateEntity5.setValue(result));
                    }
                    this.this$0.updateGeneralDriveData(this.list);
                    this.this$0.updateDriveDataActivity(null);
                }
            }

            private final int toInteger(int[] iArr) {
                if (iArr.length <= 0) {
                    return 0;
                }
                if (iArr.length > 1) {
                    int iFirst = (ArraysKt.first(iArr) << 8) | iArr[1];
                    return iArr.length > 2 ? toInteger(ArraysKt.plus(new int[]{iFirst}, ArraysKt.copyOfRange(iArr, 2, iArr.length))) : iFirst;
                }
                return ArraysKt.first(iArr);
            }

            private final String getResult(int[] params, int max, Function1<? super Integer, String> result) {
                boolean z = false;
                for (int i : params) {
                    if (i != 255) {
                        int integer = toInteger(params);
                        if (integer >= 0 && integer <= max) {
                            z = true;
                        }
                        if (z) {
                            return result.invoke(Integer.valueOf(integer));
                        }
                        return null;
                    }
                }
                return "";
            }
        });
        sparseArray.append(101, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$14
            private final ArrayList<DriverUpdateEntity> list;

            {
                super(this.this$0, "【0x65】车速-转速信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_308_title_6");
                    if (driverUpdateEntity != null) {
                        MsgMgr msgMgr = this.this$0;
                        this.list.add(driverUpdateEntity.setValue((msgMgr.mCanbusInfoInt[3] | (msgMgr.mCanbusInfoInt[2] << 8)) + " r/min"));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_308_title_7");
                    if (driverUpdateEntity2 != null) {
                        MsgMgr msgMgr2 = this.this$0;
                        this.list.add(driverUpdateEntity2.setValue((msgMgr2.mCanbusInfoInt[5] | (msgMgr2.mCanbusInfoInt[4] << 8)) + " Km/h"));
                    }
                }
            }
        });
        sparseArray.append(48, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x30】软件版本信息");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        sparseArray.append(2, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x02】面板按键");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.this$0.mCanbusInfoInt[3] != 0) {
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 0) {
                        this.this$0.realKeyClick4(context, 0);
                    } else if (i == 9) {
                        this.this$0.realKeyClick4(context, HotKeyConstant.K_SLEEP);
                    } else if (i == 10) {
                        this.this$0.realKeyClick4(context, 2);
                    } else if (i == 14) {
                        this.this$0.realKeyClick4(context, 45);
                    } else if (i == 15) {
                        this.this$0.realKeyClick4(context, 46);
                    } else if (i == 80) {
                        realKeyClick31(7);
                    } else if (i != 81) {
                        switch (i) {
                            case 47:
                                this.this$0.realKeyClick4(context, 152);
                                break;
                            case 48:
                                this.this$0.realKeyClick4(context, 4);
                                break;
                            case 49:
                                this.this$0.realKeyClick4(context, 48);
                                break;
                            case 50:
                                this.this$0.realKeyClick4(context, 47);
                                break;
                            case 51:
                                this.this$0.realKeyClick4(context, 31);
                                break;
                        }
                    } else {
                        realKeyClick31(8);
                    }
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.setLastClickKey(Integer.valueOf(msgMgr.mCanbusInfoInt[2]));
                MsgMgr msgMgr2 = this.this$0;
                msgMgr2.setLastClickState(Integer.valueOf(msgMgr2.mCanbusInfoInt[3]));
            }

            private final void realKeyClick31(int value) {
                if (this.this$0.mCanbusInfoInt[3] == 0) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyClick3_1(context, value, msgMgr.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }
        });
        sparseArray.append(25, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x19】前雷达");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                RadarInfoUtil.mMinIsClose = true;
                RadarInfoUtil.setFrontRadarLocationData(4, this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5]);
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                this.this$0.updateParkUi(null, context);
            }
        });
        sparseArray.append(30, new Parser() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x1E】后雷达");
            }

            @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
            public void parse(int dataLength) {
                RadarInfoUtil.mMinIsClose = true;
                RadarInfoUtil.setRearRadarLocationData(4, this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3], this.this$0.mCanbusInfoInt[4], this.this$0.mCanbusInfoInt[5]);
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                this.this$0.updateParkUi(null, context);
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 1, (byte) volValue, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 5, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 6, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
        if (SourceConstantsDef.SOURCE_ID.AUX2.name().equals(source)) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 2, 4, 0});
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
}
