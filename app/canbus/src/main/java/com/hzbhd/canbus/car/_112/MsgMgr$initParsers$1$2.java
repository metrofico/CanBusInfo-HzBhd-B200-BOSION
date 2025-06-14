package com.hzbhd.canbus.car._112;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.car._112.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0015\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0016¢\u0006\u0002\u0010\u0015R*\u0010\u0003\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"com/hzbhd/canbus/car/_112/MsgMgr$initParsers$1$2", "Lcom/hzbhd/canbus/car/_112/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_112/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "mAmplifierDatas", "", "mSettingDatas", "isAmplifierDatasChange", "", "isSettingDatasChange", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr$initParsers$1$2 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private final ArrayList<SettingUpdateEntity<Object>> list;
    private int[] mAmplifierDatas;
    private int[] mSettingDatas;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$2(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x17】功放状态");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        final Context context = this.$context;
        final MsgMgr msgMgr8 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m55setOnParseListeners$lambda1(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m57setOnParseListeners$lambda2(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m58setOnParseListeners$lambda3(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m59setOnParseListeners$lambda4(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m60setOnParseListeners$lambda5(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m61setOnParseListeners$lambda6(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m62setOnParseListeners$lambda7(msgMgr7);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m56setOnParseListeners$lambda12(context, msgMgr8, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m55setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$2 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("amplifier_switch");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[2])));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m57setOnParseListeners$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.volume = this$0.mCanbusInfoInt[3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m58setOnParseListeners$lambda3(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.frontRear = -(this$0.mCanbusInfoInt[4] - 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m59setOnParseListeners$lambda4(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.leftRight = this$0.mCanbusInfoInt[5] - 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m60setOnParseListeners$lambda5(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandBass = this$0.mCanbusInfoInt[6] - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m61setOnParseListeners$lambda6(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandTreble = this$0.mCanbusInfoInt[7] - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m62setOnParseListeners$lambda7(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandMiddle = this$0.mCanbusInfoInt[8] - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-12, reason: not valid java name */
    public static final void m56setOnParseListeners$lambda12(Context context, MsgMgr this$0, MsgMgr$initParsers$1$2 this$1) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        boolean z = ((this$0.mCanbusInfoInt[9] >> 7) & 1) == 1;
        Log.i(MsgMgr.TAG, "setOnParseListeners: SHARE_112_IS_HAVE_AMPLIFIER " + z);
        Unit unit = Unit.INSTANCE;
        SharePreUtil.setBoolValue(context, MsgMgr.SHARE_112_IS_HAVE_AMPLIFIER, z);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_118_setting_title_96");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(1 & (this$0.mCanbusInfoInt[9] >> 6))));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("speed_linkage_volume_adjustment");
        if (settingUpdateEntity2 != null) {
            int i = this$0.mCanbusInfoInt[9] & 15;
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf(i)).setProgress(i));
        }
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list.clear();
            super.parse(dataLength);
            if (isAmplifierDatasChange()) {
                this.this$0.updateAmplifierActivity(null);
                MsgMgr msgMgr = this.this$0;
                msgMgr.saveAmplifierData(this.$context, msgMgr.mCanId);
            }
            if (isSettingDatasChange()) {
                this.this$0.updateGeneralSettingData(this.list);
                this.this$0.updateSettingActivity(null);
            }
        }
    }

    private final boolean isSettingDatasChange() {
        int[] iArr = {this.this$0.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[9] & WorkQueueKt.MASK};
        if (Arrays.equals(this.mSettingDatas, iArr)) {
            return false;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, 2);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, size)");
        this.mSettingDatas = iArrCopyOf;
        return true;
    }

    private final boolean isAmplifierDatasChange() {
        int[] iArrCopyOfRange = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 3, 9);
        if (Arrays.equals(this.mSettingDatas, iArrCopyOfRange)) {
            return false;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArrCopyOfRange, iArrCopyOfRange.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, size)");
        this.mAmplifierDatas = iArrCopyOf;
        return true;
    }
}
