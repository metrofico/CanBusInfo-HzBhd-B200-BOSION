package com.hzbhd.canbus.car._348;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000f\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u001a\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020'H\u0016J\u000e\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020\u0004J\u0018\u0010<\u001a\u0002082\u0006\u00109\u001a\u00020'2\u0006\u0010=\u001a\u00020\u001eH\u0016Jp\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u00042\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u000b2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020\u0004H\u0016J\u0010\u0010L\u001a\u00020-2\u0006\u0010M\u001a\u00020NH\u0002J\u0018\u0010O\u001a\u0002082\u0006\u0010M\u001a\u00020N2\u0006\u0010P\u001a\u00020NH\u0002J8\u0010Q\u001a\u00020N2\u0006\u0010R\u001a\u00020\u00042\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020T2\u0006\u0010V\u001a\u00020N2\u0006\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u0004H\u0002J\u0014\u0010Y\u001a\u0004\u0018\u0001062\b\u00109\u001a\u0004\u0018\u00010'H\u0002J\u0010\u0010Z\u001a\u0002082\u0006\u00109\u001a\u00020'H\u0016J\b\u0010[\u001a\u00020\u000bH\u0002J\b\u0010\\\u001a\u00020\u000bH\u0002J\b\u0010]\u001a\u00020\u000bH\u0002J\b\u0010^\u001a\u00020\u000bH\u0002J\u0006\u0010_\u001a\u00020\u0004J¸\u0001\u0010`\u001a\u0002082\u0006\u0010a\u001a\u00020-2\u0006\u0010b\u001a\u00020-2\u0006\u0010c\u001a\u00020\u00042\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020-2\u0006\u0010g\u001a\u00020-2\u0006\u0010h\u001a\u00020-2\u0006\u0010i\u001a\u00020-2\u0006\u0010j\u001a\u00020-2\u0006\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020N2\u0006\u0010m\u001a\u00020N2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020-2\u0006\u0010q\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020o2\u0006\u0010t\u001a\u00020N2\u0006\u0010u\u001a\u00020N2\u0006\u0010v\u001a\u00020N2\u0006\u0010w\u001a\u00020\u000bH\u0016J0\u0010x\u001a\u0002082\u0006\u0010y\u001a\u00020\u00042\u0006\u0010M\u001a\u00020N2\u0006\u0010z\u001a\u00020N2\u0006\u0010{\u001a\u00020N2\u0006\u0010|\u001a\u00020\u0004H\u0016J\n\u0010}\u001a\u0004\u0018\u00010NH\u0002J\u0017\u0010~\u001a\u0002082\r\u0010\u007f\u001a\t\u0012\u0004\u0012\u00020-0\u0080\u0001H\u0002J\t\u0010\u0081\u0001\u001a\u000208H\u0002J\t\u0010\u0082\u0001\u001a\u000208H\u0002J\t\u0010\u0083\u0001\u001a\u000208H\u0002J\t\u0010\u0084\u0001\u001a\u000208H\u0002J\t\u0010\u0085\u0001\u001a\u000208H\u0002J\t\u0010\u0086\u0001\u001a\u000208H\u0002J\t\u0010\u0087\u0001\u001a\u000208H\u0002J\t\u0010\u0088\u0001\u001a\u000208H\u0002J\t\u0010\u0089\u0001\u001a\u000208H\u0002J\t\u0010\u008a\u0001\u001a\u000208H\u0002J\t\u0010\u008b\u0001\u001a\u000208H\u0002J\u0012\u0010\u008c\u0001\u001a\u0002082\u0007\u0010\u008d\u0001\u001a\u00020\u000bH\u0016J\"\u0010\u008e\u0001\u001a\u0002082\u0007\u0010\u008f\u0001\u001a\u00020\u00042\u0007\u0010\u0090\u0001\u001a\u00020\u00042\u0007\u0010\u0091\u0001\u001a\u00020\u0004J\u0093\u0001\u0010\u0092\u0001\u001a\u0002082\u0006\u0010a\u001a\u00020-2\u0006\u0010b\u001a\u00020-2\u0006\u0010c\u001a\u00020\u00042\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020-2\u0006\u0010g\u001a\u00020-2\u0006\u0010h\u001a\u00020N2\u0006\u0010i\u001a\u00020-2\u0006\u0010j\u001a\u00020-2\u0006\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020N2\u0006\u0010m\u001a\u00020N2\u0006\u0010n\u001a\u00020\u00042\u0007\u0010\u0093\u0001\u001a\u00020-2\u0006\u0010r\u001a\u00020\u000b2\u0007\u0010\u0094\u0001\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0095\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_348/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "eachId", "", "i", "getI", "()I", "setI", "(I)V", "isDoorChange", "", "()Z", "m0x24FrontRadarData", "", "getM0x24FrontRadarData", "()[I", "setM0x24FrontRadarData", "([I)V", "m0x25FrontRadarData", "getM0x25FrontRadarData", "setM0x25FrontRadarData", "m0x26FrontRadarData", "getM0x26FrontRadarData", "setM0x26FrontRadarData", "mAirData", "getMAirData", "setMAirData", "mBackStatus", "mCanBusInfoByte", "", "getMCanBusInfoByte", "()[B", "setMCanBusInfoByte", "([B)V", "mCanBusInfoInt", "getMCanBusInfoInt", "setMCanBusInfoInt", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mFreqHi", "", "mFreqLo", "mFrontStatus", "mHandBrake", "mLeftFrontStatus", "mLeftRearStatus", "mRightFrontStatus", "mRightRearStatus", "uiMgr", "Lcom/hzbhd/canbus/car/_348/UiMgr;", "afterServiceNormalSetting", "", "context", "buttonKey", "whatKey", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "getAllBandTypeData", "currBand", "", "getFreqByteHiLo", "mCurrentFreq", "getTemperature", "temperatureData", "stepping", "", "temperatureBase", "unit", "LO", "HI", "getUiMgr", "initCommand", "is0x24RadarDataNoChange", "is0x25RadarDataNoChange", "is0x26RadarDataNoChange", "isAirDataNoChange", "isMute", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currentFreq", "psName", "isStereo", "resolveOutDoorTem", "sendMediaSourceData", "list", "", "set0x26RadarInfo", "setAirdata0x23", "setDoorData0x28", "setFrontRadar0x25", "setOutdoortemp0x36", "setPanelKeyInfo0x22", "setRearRadar0x24", "setSettingState0x40", "setTrackData0x30", "setVersionInfo0x7F", "setWheelKeyInfo0x21", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSetting", "leftPos", "rightPos", "selectPos", "videoInfoChange", "playMode", "duation", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private int eachId;
    private int i;
    private boolean mBackStatus;
    public byte[] mCanBusInfoByte;
    public int[] mCanBusInfoInt;
    private Context mContext;
    private byte mFreqHi;
    private byte mFreqLo;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr uiMgr;
    private int[] mAirData = new int[0];
    private int[] m0x25FrontRadarData = new int[0];
    private int[] m0x26FrontRadarData = new int[0];
    private int[] m0x24FrontRadarData = new int[0];

    public final int[] getMCanBusInfoInt() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr != null) {
            return iArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoInt");
        return null;
    }

    public final void setMCanBusInfoInt(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mCanBusInfoInt = iArr;
    }

    public final byte[] getMCanBusInfoByte() {
        byte[] bArr = this.mCanBusInfoByte;
        if (bArr != null) {
            return bArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCanBusInfoByte");
        return null;
    }

    public final void setMCanBusInfoByte(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.mCanBusInfoByte = bArr;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        int currentEachCanId = getCurrentEachCanId();
        if (currentEachCanId == 4) {
            i = 1;
        } else if (currentEachCanId == 5) {
            i = 2;
        } else if (currentEachCanId != 6) {
            return;
        } else {
            i = 3;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(i, null), 3, null);
    }

    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.hzbhd.canbus.car._348.MsgMgr$initCommand$1", f = "MsgMgr.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.hzbhd.canbus.car._348.MsgMgr$initCommand$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $d0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$d0 = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$d0, continuation);
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
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L18
                if (r1 != r2) goto L10
                kotlin.ResultKt.throwOnFailure(r6)
                r6 = r5
                goto L2a
            L10:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L18:
                kotlin.ResultKt.throwOnFailure(r6)
                r6 = r5
            L1c:
                r3 = 2000(0x7d0, double:9.88E-321)
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r6.label = r2
                java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r3, r1)
                if (r1 != r0) goto L2a
                return r0
            L2a:
                r1 = 3
                byte[] r1 = new byte[r1]
                r3 = 0
                r4 = 22
                r1[r3] = r4
                r3 = -18
                r1[r2] = r3
                r3 = 2
                int r4 = r6.$d0
                byte r4 = (byte) r4
                r1[r3] = r4
                com.hzbhd.canbus.CanbusMsgSender.sendMsg(r1)
                goto L1c
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._348.MsgMgr.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        super.canbusInfoChange(context, canbusInfo);
        setMCanBusInfoByte(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setMCanBusInfoInt(byteArrayToIntArray);
        int i = getMCanBusInfoInt()[1];
        if (i == 40) {
            setDoorData0x28();
            return;
        }
        if (i == 48) {
            setTrackData0x30();
            return;
        }
        if (i == 54) {
            int i2 = this.eachId;
            if (i2 == 1 || i2 == 3) {
                setOutdoortemp0x36();
                return;
            }
            return;
        }
        if (i == 64) {
            int i3 = this.eachId;
            if (i3 == 1 || i3 == 3) {
                setSettingState0x40();
                return;
            }
            return;
        }
        if (i != 127) {
            switch (i) {
                case 33:
                    if (this.eachId != 2) {
                        setWheelKeyInfo0x21();
                        break;
                    }
                    break;
                case 34:
                    setPanelKeyInfo0x22();
                    break;
                case 35:
                    if (this.eachId != 2) {
                        setAirdata0x23();
                        break;
                    }
                    break;
                case 36:
                    setRearRadar0x24();
                    break;
                case 37:
                    if (this.eachId == 3) {
                        setFrontRadar0x25();
                        break;
                    }
                    break;
                case 38:
                    set0x26RadarInfo();
                    break;
            }
            return;
        }
        setVersionInfo0x7F();
    }

    private final void set0x26RadarInfo() {
        if (is0x26RadarDataNoChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationDataType2(3, getMCanBusInfoInt()[2], 4, getMCanBusInfoInt()[2], 4, getMCanBusInfoInt()[3], 3, getMCanBusInfoInt()[3]);
        RadarInfoUtil.setRearRadarLocationDataType2(3, getMCanBusInfoInt()[4], 4, getMCanBusInfoInt()[4], 4, getMCanBusInfoInt()[5], 3, getMCanBusInfoInt()[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private final void setWheelKeyInfo0x21() {
        switch (getMCanBusInfoInt()[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(21);
                break;
            case 4:
                buttonKey(20);
                break;
            case 6:
                buttonKey(3);
                break;
            case 7:
                buttonKey(2);
                break;
            case 8:
                buttonKey(HotKeyConstant.K_VOICE_PICKUP);
                break;
            case 9:
                buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 10:
                buttonKey(15);
                break;
            case 11:
                buttonKey(HotKeyConstant.K_NEXT_HANGUP);
                break;
            case 12:
                buttonKey(206);
                break;
            case 15:
                buttonKey(HotKeyConstant.K_MUTE_PHONE_ON_OUT);
                break;
        }
    }

    private final void setPanelKeyInfo0x22() {
        switch (getMCanBusInfoInt()[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(1);
                break;
            case 2:
                buttonKey(220);
                break;
            case 3:
                buttonKey(3);
                break;
            case 4:
                buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 5:
                buttonKey(HotKeyConstant.K_DISP);
                break;
            case 6:
                buttonKey(151);
                break;
            case 7:
                buttonKey(50);
                break;
            case 8:
                buttonKey(76);
                break;
            case 9:
                buttonKey(139);
                break;
            case 10:
                buttonKey(49);
                break;
            case 11:
                buttonKey(47);
                break;
            case 12:
                buttonKey(48);
                break;
            case 13:
                buttonKey(4);
                break;
            case 14:
                buttonKey(8);
                break;
            case 15:
                buttonKey(7);
                break;
            case 16:
                buttonKey(58);
                break;
            case 17:
                buttonKey(21);
                break;
            case 18:
                buttonKey(20);
                break;
            case 19:
                buttonKey(128);
                break;
            case 20:
                buttonKey(HotKeyConstant.K_DISP);
                break;
            case 21:
                buttonKey(12);
                break;
            case 22:
                buttonKey(2);
                break;
            case 23:
                buttonKey(75);
                break;
            case 25:
                buttonKey(4);
                break;
            case 26:
                buttonKey(70);
                break;
            case 27:
                buttonKey(30);
                break;
            case 28:
                buttonKey(HotKeyConstant.K_4_REPEAT);
                break;
            case 30:
                buttonKey(47);
                break;
            case 31:
                buttonKey(48);
                break;
        }
    }

    private final void setAirdata0x23() {
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getMCanBusInfoInt()[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(getMCanBusInfoInt()[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit4(getMCanBusInfoInt()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getMCanBusInfoInt()[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(getMCanBusInfoInt()[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(getMCanBusInfoInt()[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(getMCanBusInfoInt()[2]);
        int i = getMCanBusInfoInt()[3];
        if (i == 0) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 1) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 2) {
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = getMCanBusInfoInt()[4];
        GeneralAirData.front_left_temperature = getTemperature(getMCanBusInfoInt()[5], 1.0d, 0.0d, "C", 0, 255);
        GeneralAirData.front_right_temperature = getTemperature(getMCanBusInfoInt()[6], 1.0d, 0.0d, "C", 0, 255);
        updateAirActivity(this.mContext, 1001);
    }

    public final int[] getMAirData() {
        return this.mAirData;
    }

    public final void setMAirData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData = iArr;
    }

    private final boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, getMCanBusInfoInt())) {
            return true;
        }
        this.mAirData = getMCanBusInfoInt();
        return false;
    }

    private final void setRearRadar0x24() {
        if (is0x24RadarDataNoChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, getMCanBusInfoInt()[2], 3);
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, getMCanBusInfoInt()[3], 4);
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, getMCanBusInfoInt()[4], 4);
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, getMCanBusInfoInt()[5], 3);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private final void setFrontRadar0x25() {
        if (is0x25RadarDataNoChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, getMCanBusInfoInt()[2], 3);
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, getMCanBusInfoInt()[3], 4);
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, getMCanBusInfoInt()[4], 4);
        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, getMCanBusInfoInt()[5], 3);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private final void setDoorData0x28() {
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getMCanBusInfoInt()[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(getMCanBusInfoInt()[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(getMCanBusInfoInt()[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(getMCanBusInfoInt()[2]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private final void setTrackData0x30() {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(getMCanBusInfoByte()[3], getMCanBusInfoByte()[2], 7784, 13784, 16);
        updateParkUi(null, this.mContext);
    }

    private final void setOutdoortemp0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private final void setSettingState0x40() {
        ArrayList arrayList = new ArrayList();
        UiMgr uiMgr = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr);
        int settingLeftIndexes = uiMgr.getSettingLeftIndexes(this.mContext, "car_light_set");
        UiMgr uiMgr2 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr2);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, uiMgr2.getSettingRightIndex(this.mContext, "car_light_set", "ceiling_light_delay"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[2], 6, 2))));
        UiMgr uiMgr3 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr3);
        int settingLeftIndexes2 = uiMgr3.getSettingLeftIndexes(this.mContext, "car_light_set");
        UiMgr uiMgr4 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr4);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, uiMgr4.getSettingRightIndex(this.mContext, "car_light_set", "power_saving_time"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[2], 4, 2))));
        UiMgr uiMgr5 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr5);
        int settingLeftIndexes3 = uiMgr5.getSettingLeftIndexes(this.mContext, "car_lock_set");
        UiMgr uiMgr6 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr6);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes3, uiMgr6.getSettingRightIndex(this.mContext, "car_lock_set", "speed_lock"), Boolean.valueOf(DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[3]))));
        UiMgr uiMgr7 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr7);
        int settingLeftIndexes4 = uiMgr7.getSettingLeftIndexes(this.mContext, "car_lock_set");
        UiMgr uiMgr8 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr8);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes4, uiMgr8.getSettingRightIndex(this.mContext, "car_lock_set", "automatic_relock"), Boolean.valueOf(DataHandleUtils.getBoolBit6(getMCanBusInfoInt()[3]))));
        UiMgr uiMgr9 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr9);
        int settingLeftIndexes5 = uiMgr9.getSettingLeftIndexes(this.mContext, "car_lock_set");
        UiMgr uiMgr10 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr10);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes5, uiMgr10.getSettingRightIndex(this.mContext, "car_lock_set", "remote_lock_feedback"), Boolean.valueOf(DataHandleUtils.getBoolBit5(getMCanBusInfoInt()[3]))));
        UiMgr uiMgr11 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr11);
        int settingLeftIndexes6 = uiMgr11.getSettingLeftIndexes(this.mContext, "car_lock_set");
        UiMgr uiMgr12 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr12);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes6, uiMgr12.getSettingRightIndex(this.mContext, "car_lock_set", "_282_07_0_6"), Boolean.valueOf(DataHandleUtils.getBoolBit4(getMCanBusInfoInt()[3]))));
        UiMgr uiMgr13 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr13);
        int settingLeftIndexes7 = uiMgr13.getSettingLeftIndexes(this.mContext, "car_lock_set");
        UiMgr uiMgr14 = getUiMgr(this.mContext);
        Intrinsics.checkNotNull(uiMgr14);
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes7, uiMgr14.getSettingRightIndex(this.mContext, "car_lock_set", "_1193_setting_1_8"), Boolean.valueOf(DataHandleUtils.getBoolBit3(getMCanBusInfoInt()[3]))));
        updateGeneralSettingData(arrayList);
        updateActivity(null);
    }

    private final void setVersionInfo0x7F() {
        updateVersionInfo(this.mContext, getVersionStr(getMCanBusInfoByte()));
    }

    public final void buttonKey(int whatKey) {
        realKeyLongClick1(this.mContext, whatKey, getMCanBusInfoInt()[3]);
    }

    public final int[] getM0x25FrontRadarData() {
        return this.m0x25FrontRadarData;
    }

    public final void setM0x25FrontRadarData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.m0x25FrontRadarData = iArr;
    }

    private final boolean is0x25RadarDataNoChange() {
        if (Arrays.equals(this.m0x25FrontRadarData, getMCanBusInfoInt())) {
            return true;
        }
        this.m0x25FrontRadarData = getMCanBusInfoInt();
        return false;
    }

    public final int[] getM0x26FrontRadarData() {
        return this.m0x26FrontRadarData;
    }

    public final void setM0x26FrontRadarData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.m0x26FrontRadarData = iArr;
    }

    private final boolean is0x26RadarDataNoChange() {
        if (Arrays.equals(this.m0x26FrontRadarData, getMCanBusInfoInt())) {
            return true;
        }
        this.m0x26FrontRadarData = getMCanBusInfoInt();
        return false;
    }

    public final int[] getM0x24FrontRadarData() {
        return this.m0x24FrontRadarData;
    }

    public final void setM0x24FrontRadarData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.m0x24FrontRadarData = iArr;
    }

    private final boolean is0x24RadarDataNoChange() {
        if (Arrays.equals(this.m0x24FrontRadarData, getMCanBusInfoInt())) {
            return true;
        }
        this.m0x24FrontRadarData = getMCanBusInfoInt();
        return false;
    }

    private final boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        return true;
    }

    private final String resolveOutDoorTem() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getMCanBusInfoInt()[2], 0, 7);
        if (DataHandleUtils.getBoolBit7(getMCanBusInfoInt()[2])) {
            intFromByteWithBit *= -1;
        }
        if (intFromByteWithBit > 120 || intFromByteWithBit < -40) {
            updateOutDoorTemp(this.mContext, " ");
            return null;
        }
        return intFromByteWithBit + getTempUnitC(this.mContext);
    }

    public final int getI() {
        return this.i;
    }

    public final void setI(int i) {
        this.i = i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, 118, (byte) bHours24H, (byte) bMins, (byte) bSecond});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
        CanbusMsgSender.sendMsg(new byte[]{22, 117, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004b, code lost:
    
        if (r15.equals("FM1") == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004e, code lost:
    
        r4 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0054, code lost:
    
        if (r15.equals("AM2") == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
    
        if (r15.equals("AM1") == false) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005e, code lost:
    
        r4 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
    
        if (r15.equals("FM3") != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007c, code lost:
    
        if (r15.equals("FM2") == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
    
        if (r15.equals("FM1") == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0086, code lost:
    
        r0 = r16;
        r4 = r16.substring(0, kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r0, ".", 0, false, 6, (java.lang.Object) null));
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, "this as java.lang.String…ing(startIndex, endIndex)");
        r12 = java.lang.Integer.parseInt(r4) * 10;
        r0 = r16.substring(kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r0, ".", 0, false, 6, (java.lang.Object) null) + 1, kotlin.text.StringsKt.getLastIndex(r0));
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "this as java.lang.String…ing(startIndex, endIndex)");
        r12 = r12 + java.lang.Integer.parseInt(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c0, code lost:
    
        if (r15.equals("AM2") == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c7, code lost:
    
        if (r15.equals("AM1") == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ca, code lost:
    
        r12 = java.lang.Integer.parseInt(r16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ce, code lost:
    
        android.util.Log.i("lyn", java.lang.String.valueOf(r12));
        r2.add(java.lang.Byte.valueOf((byte) com.hzbhd.canbus.util.DataHandleUtils.getMsb(r12)));
        r2.add(java.lang.Byte.valueOf((byte) com.hzbhd.canbus.util.DataHandleUtils.getLsb(r12)));
        r2.add((byte) 0);
        r2.add((byte) 0);
        r2.add((byte) 0);
        r2.add(java.lang.Byte.valueOf((byte) isMute()));
        sendMediaSourceData(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003b, code lost:
    
        if (r15.equals("FM3") != false) goto L15;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, int r18) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._348.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    public final int isMute() {
        return getVolume() == 0 ? 2 : 1;
    }

    private final void sendMediaSourceData(List<Byte> list) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 117}, CollectionsKt.toByteArray(list)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duation) {
        Intrinsics.checkNotNullParameter(currentAllMinuteStr, "currentAllMinuteStr");
        Intrinsics.checkNotNullParameter(currentHourStr, "currentHourStr");
        Intrinsics.checkNotNullParameter(currentMinuteStr, "currentMinuteStr");
        Intrinsics.checkNotNullParameter(currentSecondStr, "currentSecondStr");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(stoagePath == 9 ? (byte) 4 : (byte) 3));
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add(Byte.valueOf((byte) isMute()));
        sendMediaSourceData(arrayList);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        Intrinsics.checkNotNullParameter(currentHourStr, "currentHourStr");
        Intrinsics.checkNotNullParameter(currentMinuteStr, "currentMinuteStr");
        Intrinsics.checkNotNullParameter(currentSecondStr, "currentSecondStr");
        Intrinsics.checkNotNullParameter(songTitle, "songTitle");
        Intrinsics.checkNotNullParameter(songAlbum, "songAlbum");
        Intrinsics.checkNotNullParameter(songArtist, "songArtist");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(stoagePath == 9 ? (byte) 4 : (byte) 3));
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        arrayList.add(Byte.valueOf((byte) isMute()));
        sendMediaSourceData(arrayList);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final byte getAllBandTypeData(String currBand) {
        int iHashCode = currBand.hashCode();
        if (iHashCode != 2092) {
            switch (iHashCode) {
                case 69706:
                    if (currBand.equals("FM1")) {
                        return (byte) 1;
                    }
                    break;
                case 69707:
                    if (currBand.equals("FM2")) {
                        return (byte) 2;
                    }
                    break;
            }
        } else if (currBand.equals("AM")) {
            return (byte) 3;
        }
        return (byte) 0;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    private final void getFreqByteHiLo(String currBand, String mCurrentFreq) {
        int iHashCode = currBand.hashCode();
        if (iHashCode == 2092) {
            if (currBand.equals("AM")) {
                this.mFreqHi = (byte) (Integer.parseInt(mCurrentFreq) >> 8);
                this.mFreqLo = (byte) (Integer.parseInt(mCurrentFreq) & 255);
                return;
            }
            return;
        }
        switch (iHashCode) {
            case 69706:
                if (!currBand.equals("FM1")) {
                    return;
                }
                break;
            case 69707:
                if (!currBand.equals("FM2")) {
                    return;
                }
                break;
            default:
                return;
        }
        int i = (int) (Double.parseDouble(mCurrentFreq) * 100);
        this.mFreqHi = (byte) (i >> 8);
        this.mFreqLo = (byte) (i & 255);
    }

    public final void updateSetting(int leftPos, int rightPos, int selectPos) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(leftPos, rightPos, Integer.valueOf(selectPos)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
            Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._348.UiMgr");
            this.uiMgr = (UiMgr) canUiMgr;
        }
        return this.uiMgr;
    }

    private final String getTemperature(int temperatureData, double stepping, double temperatureBase, String unit, int LO, int HI) {
        if (temperatureData == LO) {
            return "LO";
        }
        if (temperatureData == HI) {
            return "HI";
        }
        if (temperatureData == 254) {
            return "无效";
        }
        String str = unit;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        if (Intrinsics.areEqual(str.subSequence(i, length + 1).toString(), "C")) {
            return ((temperatureData * stepping) + temperatureBase) + getTempUnitC(this.mContext);
        }
        return ((temperatureData * stepping) + temperatureBase) + getTempUnitF(this.mContext);
    }
}
