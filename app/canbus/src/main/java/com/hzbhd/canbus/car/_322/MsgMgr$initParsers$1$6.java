package com.hzbhd.canbus.car._322;

import android.content.Context;
import com.hzbhd.canbus.car._322.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"com/hzbhd/canbus/car/_322/MsgMgr$initParsers$1$6", "Lcom/hzbhd/canbus/car/_322/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_322/MsgMgr;", "close", "", "level", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$6 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private final String close;
    private final String level;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$6(Context context, MsgMgr msgMgr) {
        super(msgMgr, "【0x06】后座空调信息");
        this.$context = context;
        this.this$0 = msgMgr;
        String strByResId = CommUtil.getStrByResId(context, "_318_level");
        Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context, \"_318_level\")");
        this.level = strByResId;
        String strByResId2 = CommUtil.getStrByResId(context, "close");
        Intrinsics.checkNotNullExpressionValue(strByResId2, "getStrByResId(context, \"close\")");
        this.close = strByResId2;
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m584setOnParseListeners$lambda1(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m585setOnParseListeners$lambda2(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m586setOnParseListeners$lambda5(msgMgr3, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m584setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.rear_power = ((this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
        GeneralAirData.rear_lock = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 1;
        int i = this$0.mCanbusInfoInt[2] & 15;
        GeneralAirData.rear_wind_level = Integer.valueOf(i).equals(15) ? 0 : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m585setOnParseListeners$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.rear_left_blow_foot = ((this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
        GeneralAirData.rear_left_blow_head = ((this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
        GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
        GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m586setOnParseListeners$lambda5(MsgMgr this$0, MsgMgr$initParsers$1$6 this$1) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int i = this$0.mCanbusInfoInt[4];
        int i2 = this$0.mCanbusInfoInt[4] & 15;
        boolean z = false;
        if (1 <= i2 && i2 < 10) {
            z = true;
        }
        if (z) {
            str = this$1.level + ' ' + i2;
        } else {
            str = i2 == 0 ? this$1.close : " ";
        }
        GeneralAirData.rear_temperature = str;
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            this.this$0.updateAirActivity(this.$context, 1002);
        }
    }
}
