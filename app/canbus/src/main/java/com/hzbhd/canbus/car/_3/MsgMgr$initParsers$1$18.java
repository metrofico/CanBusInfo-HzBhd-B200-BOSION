package com.hzbhd.canbus.car._3;

import android.content.Context;
import com.hzbhd.canbus.car._3.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0015\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0016¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"com/hzbhd/canbus/car/_3/MsgMgr$initParsers$1$18", "Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_3/MsgMgr;", "amplifierData", "", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "mData6", "", "mData6Record", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$18 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private final int[] amplifierData;
    private final ArrayList<SettingUpdateEntity<Object>> list;
    private int mData6;
    private int mData6Record;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$18(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x51】功放信息");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
        this.amplifierData = new int[6];
    }

    @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m366setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m367setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m368setOnParseListeners$lambda2(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m369setOnParseListeners$lambda3(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m370setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m371setOnParseListeners$lambda5(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$18.m372setOnParseListeners$lambda7(this.f$0, msgMgr7);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m366setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.volume = this$0.mCanbusInfoInt[2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m367setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandTreble = this$0.mCanbusInfoInt[3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m368setOnParseListeners$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandMiddle = this$0.mCanbusInfoInt[4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m369setOnParseListeners$lambda3(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.bandBass = this$0.mCanbusInfoInt[5];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m370setOnParseListeners$lambda4(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.frontRear = this$0.mCanbusInfoInt[6] - 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m371setOnParseListeners$lambda5(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAmplifierData.leftRight = this$0.mCanbusInfoInt[7] - 9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m372setOnParseListeners$lambda7(MsgMgr$initParsers$1$18 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        this$0.list.clear();
        this$0.mData6 = this$1.mCanbusInfoInt[8];
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_143_0xAD_setting6");
        if (settingUpdateEntity != null) {
            this$0.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mData6)).setProgress(this$0.mData6));
        }
    }

    @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
    public void parse(int dataLength) {
        super.parse(dataLength);
        int[] iArrCopyOfRange = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 2, 8);
        MsgMgr msgMgr = this.this$0;
        Context context = this.$context;
        if (!Arrays.equals(iArrCopyOfRange, this.amplifierData)) {
            ArraysKt.copyInto$default(iArrCopyOfRange, this.amplifierData, 0, 0, 0, 14, (Object) null);
            msgMgr.updateAmplifierActivity(null);
            msgMgr.saveAmplifierData(context, msgMgr.getCanId());
        }
        int i = this.mData6Record;
        int i2 = this.mData6;
        if (i != i2) {
            this.mData6Record = i2;
            this.this$0.updateGeneralSettingData(this.list);
            this.this$0.updateSettingActivity(null);
        }
    }
}
