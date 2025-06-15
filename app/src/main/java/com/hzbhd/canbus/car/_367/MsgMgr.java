package com.hzbhd.canbus.car._367;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;




public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;

    private final void set0x76Data() {
    }

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }

        return null;
    }

    public final void setFrame(int[] iArr) {

        this.frame = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    /* compiled from: MsgMgr.kt */
    
    @DebugMetadata(c = "com.hzbhd.canbus.car._367.MsgMgr$afterServiceNormalSetting$1", f = "MsgMgr.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.hzbhd.canbus.car._367.MsgMgr$afterServiceNormalSetting$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MsgMgr.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0029 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0027 -> B:12:0x002a). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L18
                if (r1 != r2) goto L10
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                goto L2a
            L10:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L18:
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
            L1c:
                r3 = 1000(0x3e8, double:4.94E-321)
                r1 = r7
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r7.label = r2
                java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r3, r1)
                if (r1 != r0) goto L2a
                return r0
            L2a:
                r1 = 4
                byte[] r1 = new byte[r1]
                r3 = 22
                r4 = 0
                r1[r4] = r3
                r3 = -45
                r1[r2] = r3
                r3 = 2
                com.hzbhd.canbus.car._367.MsgMgr r5 = com.hzbhd.canbus.car._367.MsgMgr.this
                int r5 = com.hzbhd.canbus.car._367.MsgMgr.access$getCurrentCanDifferentId(r5)
                byte r5 = (byte) r5
                r1[r3] = r5
                r3 = 3
                r1[r3] = r4
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r1)
                goto L1c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._367.MsgMgr.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final void sendSourceData(int d0) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -46}, MsgMgrKt.restrict$default(new byte[]{(byte) d0}, 13, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendSourceData(0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        int i;
        if (currBand != null) {
            switch (currBand.hashCode()) {
                case 64901:
                    if (currBand.equals("AM1")) {
                        i = 4;
                        break;
                    } else {
                        return;
                    }
                case 64902:
                    if (currBand.equals("AM2")) {
                        i = 5;
                        break;
                    } else {
                        return;
                    }
                case 69706:
                    if (currBand.equals("FM1")) {
                        i = 1;
                        break;
                    } else {
                        return;
                    }
                case 69707:
                    if (currBand.equals("FM2")) {
                        i = 2;
                        break;
                    } else {
                        return;
                    }
                case 69708:
                    if (currBand.equals("FM3")) {
                        i = 3;
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            sendSourceData(i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        sendSourceData(stoagePath == 9 ? 14 : 15);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        sendSourceData(11);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        sendSourceData(10);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendSourceData(12);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i != 240) {
            switch (i) {
                case 114:
                    set0x72Data();
                    break;
                case 115:
                    set0x73Data();
                    break;
                case 116:
                    set0x74Data();
                    break;
                case 117:
                    set0x75Data();
                    break;
                case 118:
                    set0x76Data();
                    break;
            }
        }
        set0xF0Data(canbusInfo);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x75Data() {
        String str;
        String string = "";
        switch (getFrame()[2]) {
            case 1:
                str = "RADIO";
                break;
            case 2:
                str = "CD";
                break;
            case 3:
                str = "USB";
                break;
            case 4:
                str = "BT";
                break;
            case 5:
                str = "PHONE";
                break;
            case 6:
                str = "NAVI";
                break;
            case 7:
                str = "AUX IN";
                break;
            case 8:
                str = "OFF";
                break;
            case 9:
                str = "AM";
                break;
            default:
                str = "";
                break;
        }
        GeneralOriginalCarDeviceData.cdStatus = str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 4);
        if (intFromByteWithBit == 1) {
            string = InitUtilsKt.getMContext().getString(R.string.O367_x75_1);
        } else if (intFromByteWithBit == 2) {
            string = InitUtilsKt.getMContext().getString(R.string.O367_x75_2);
        } else if (intFromByteWithBit == 3) {
            string = InitUtilsKt.getMContext().getString(R.string.O367_x75_3);
        }
        GeneralOriginalCarDeviceData.runningState = string;
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private final void set0x74Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = 0;
        switch (getFrame()[2]) {
            case 1:
            case 5:
                i = 45;
                break;
            case 2:
            case 6:
                i = 46;
                break;
            case 3:
            case 7:
                i = 47;
                break;
            case 4:
            case 8:
                i = 48;
                break;
            case 9:
                i = 49;
                break;
            case 10:
                i = 43;
                break;
            case 11:
                i = 50;
                break;
        }
        DataHandleUtils.knob(mContext, i, getFrame()[3]);
    }

    private final void set0x73Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 2);
        GeneralAirData.in_out_auto_cycle = intFromByteWithBit != 0 ? (intFromByteWithBit == 1 || intFromByteWithBit != 3) ? 0 : 2 : 1;
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrame()[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit7(getFrame()[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[3]);
        int i = getFrame()[4];
        String str2 = "HIGH";
        if (i == 0) {
            str = "";
        } else if (i != 1) {
            str = i != 255 ? i + " °C" : "HIGH";
        } else {
            str = "LOW";
        }
        GeneralAirData.front_left_temperature = str;
        int i2 = getFrame()[5];
        if (i2 == 0) {
            str2 = "";
        } else if (i2 == 1) {
            str2 = "LOW";
        } else if (i2 != 255) {
            str2 = i2 + " °C";
        }
        GeneralAirData.front_right_temperature = str2;
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(getFrame()[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(getFrame()[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(getFrame()[6]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(getFrame()[7]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(getFrame()[7]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(getFrame()[7]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 0, 4);
        if (DataHandleUtils.getBoolBit7(getFrame()[8])) {
            updateOutDoorTemp(InitUtilsKt.getMContext(), (DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 0, 7) - 40) + " °C");
        }
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
        if (DataHandleUtils.getBoolBit0(getFrame()[9])) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[9]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[9]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[9]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[9]);
            updateDoorView(InitUtilsKt.getMContext());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x72Data() {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._367.MsgMgr.set0x72Data():void");
    }
}
