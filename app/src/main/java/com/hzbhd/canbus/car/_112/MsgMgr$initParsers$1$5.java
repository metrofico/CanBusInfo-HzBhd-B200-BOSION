package com.hzbhd.canbus.car._112;

import android.content.Context;
import com.hzbhd.canbus.car._112.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0015\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"com/hzbhd/canbus/car/_112/MsgMgr$initParsers$1$5", "Lcom/hzbhd/canbus/car/_112/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_112/MsgMgr;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr$initParsers$1$5 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$5(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x21】空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    private static final String setOnParseListeners$getTemperature(MsgMgr msgMgr, Context context, int i) {
        return i != 0 ? i != 255 ? GeneralAirData.fahrenheit_celsius ? i + msgMgr.getTempUnitF(context) : (i / 2) + msgMgr.getTempUnitC(context) : "HIGH" : "LOW";
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final Context context = this.$context;
        final MsgMgr msgMgr4 = this.this$0;
        final Context context2 = this.$context;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m65setOnParseListeners$lambda1(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m67setOnParseListeners$lambda3(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m68setOnParseListeners$lambda4(msgMgr3, context);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m69setOnParseListeners$lambda5(msgMgr4, context2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m70setOnParseListeners$lambda7(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m71setOnParseListeners$lambda9(msgMgr6);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m66setOnParseListeners$lambda11(msgMgr7);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m65setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.mCanbusInfoInt[2];
        GeneralAirData.power = ((i >> 7) & 1) == 1;
        GeneralAirData.ac = ((i >> 6) & 1) == 1;
        GeneralAirData.in_out_cycle = ((i >> 5) & 1) == 0;
        GeneralAirData.auto = ((i >> 3) & 1) == 1;
        GeneralAirData.sync = ((i >> 2) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m67setOnParseListeners$lambda3(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.mCanbusInfoInt[3];
        GeneralAirData.front_left_blow_window = ((i >> 7) & 1) == 1;
        GeneralAirData.front_left_blow_head = ((i >> 6) & 1) == 1;
        GeneralAirData.front_left_blow_foot = ((i >> 5) & 1) == 1;
        GeneralAirData.front_wind_level = i & 15;
        GeneralAirData.front_auto_wind_speed = GeneralAirData.front_wind_level == 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m68setOnParseListeners$lambda4(MsgMgr this$0, Context context) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        GeneralAirData.front_left_temperature = setOnParseListeners$getTemperature(this$0, context, this$0.mCanbusInfoInt[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m69setOnParseListeners$lambda5(MsgMgr this$0, Context context) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        GeneralAirData.front_right_temperature = setOnParseListeners$getTemperature(this$0, context, this$0.mCanbusInfoInt[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m70setOnParseListeners$lambda7(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.mCanbusInfoInt[6];
        GeneralAirData.front_defog = ((i >> 7) & 1) == 1;
        GeneralAirData.rear_defog = ((i >> 6) & 1) == 1;
        GeneralAirData.ac_max = ((i >> 3) & 1) == 1;
        GeneralAirData.fahrenheit_celsius = (i & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-9, reason: not valid java name */
    public static final void m71setOnParseListeners$lambda9(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.mCanbusInfoInt[7];
        GeneralAirData.front_left_seat_heat_level = (i >> 4) & 7;
        GeneralAirData.front_right_seat_heat_level = i & 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m66setOnParseListeners$lambda11(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.mCanbusInfoInt[9];
        GeneralAirData.steering_wheel_heating = ((i >> 7) & 1) == 1;
        GeneralAirData.front_left_seat_cold_level = (i >> 4) & 7;
        GeneralAirData.front_right_seat_cold_level = i & 7;
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public void parse(int dataLength) {
        this.this$0.mCanbusInfoInt[3] = this.this$0.mCanbusInfoInt[3] | 16;
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            this.this$0.updateAirActivity(this.$context, 1001);
        }
    }
}
