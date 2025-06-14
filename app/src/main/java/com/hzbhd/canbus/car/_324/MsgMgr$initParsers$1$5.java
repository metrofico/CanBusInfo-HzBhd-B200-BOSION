package com.hzbhd.canbus.car._324;

import android.content.Context;
import com.hzbhd.canbus.car._324.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0015\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"com/hzbhd/canbus/car/_324/MsgMgr$initParsers$1$5", "Lcom/hzbhd/canbus/car/_324/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_324/MsgMgr;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$5 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$5(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x1B】后座空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final Context context = this.$context;
        final MsgMgr msgMgr3 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m616setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m617setOnParseListeners$lambda1(msgMgr2, context);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m618setOnParseListeners$lambda2(msgMgr3);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m616setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.rear_ac = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 1;
        GeneralAirData.rear_auto = ((this$0.mCanbusInfoInt[2] >> 4) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m617setOnParseListeners$lambda1(MsgMgr this$0, Context context) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        int i = this$0.mCanbusInfoInt[3];
        if (i == 0) {
            str = "LO";
        } else if (i == 31) {
            str = "HI";
        } else {
            boolean z = false;
            if (1 <= i && i < 30) {
                z = true;
            }
            str = z ? ((this$0.mCanbusInfoInt[3] + 35) / 2.0f) + this$0.getTempUnitC(context) : "";
        }
        GeneralAirData.rear_temperature = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m618setOnParseListeners$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.rear_left_blow_window = ((this$0.mCanbusInfoInt[4] >> 7) & 1) == 1;
        GeneralAirData.rear_left_blow_head = ((this$0.mCanbusInfoInt[4] >> 6) & 1) == 1;
        GeneralAirData.rear_left_blow_foot = ((this$0.mCanbusInfoInt[4] >> 5) & 1) == 1;
        GeneralAirData.rear_wind_level = this$0.mCanbusInfoInt[4] & 15;
    }

    @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralAirData.rear_power = GeneralAirData.rear_wind_level > 0;
            GeneralAirData.rear_right_blow_window = GeneralAirData.rear_left_blow_window;
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
            this.this$0.updateAirActivity(this.$context, 1002);
        }
    }
}
