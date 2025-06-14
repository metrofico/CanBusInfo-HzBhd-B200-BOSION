package com.hzbhd.canbus.car._322;

import android.content.Context;
import com.hzbhd.canbus.car._322.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0002J\u0015\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"com/hzbhd/canbus/car/_322/MsgMgr$initParsers$1$5", "Lcom/hzbhd/canbus/car/_322/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_322/MsgMgr;", "outDoorTemperature", "", "parse", "", "dataLength", "resolveTemperature", "", "value", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$5 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int outDoorTemperature;
    final /* synthetic */ MsgMgr this$0;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-8, reason: not valid java name */
    public static final void m582setOnParseListeners$lambda8() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$5(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x05】前座空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m576setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m577setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m578setOnParseListeners$lambda2(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m579setOnParseListeners$lambda3(this.f$0, msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m580setOnParseListeners$lambda4(this.f$0, msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m581setOnParseListeners$lambda7(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m582setOnParseListeners$lambda8();
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m576setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.power = ((this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
        GeneralAirData.ac = ((this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
        GeneralAirData.dual = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 1;
        GeneralAirData.auto = ((this$0.mCanbusInfoInt[2] >> 4) & 1) == 1;
        GeneralAirData.in_out_cycle = ((this$0.mCanbusInfoInt[2] >> 2) & 3) == 0;
        GeneralAirData.rear_defog = ((this$0.mCanbusInfoInt[2] >> 1) & 1) == 1;
        GeneralAirData.front_defog = (this$0.mCanbusInfoInt[2] & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m577setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.max_front = ((this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
        GeneralAirData.ac_max = ((this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
        GeneralAirData.steering_wheel_heating = ((this$0.mCanbusInfoInt[3] >> 5) & 1) == 1;
        GeneralAirData.front_auto_wind_speed = ((this$0.mCanbusInfoInt[3] >> 4) & 1) == 1;
        if (GeneralAirData.front_auto_wind_speed) {
            return;
        }
        GeneralAirData.front_wind_level = this$0.mCanbusInfoInt[3] & 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m578setOnParseListeners$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.front_left_auto_wind = ((this$0.mCanbusInfoInt[4] >> 7) & 1) == 1;
        GeneralAirData.front_left_blow_window = ((this$0.mCanbusInfoInt[4] >> 6) & 1) == 1;
        GeneralAirData.front_left_blow_foot = ((this$0.mCanbusInfoInt[4] >> 5) & 1) == 1;
        GeneralAirData.front_left_blow_head = ((this$0.mCanbusInfoInt[4] >> 4) & 1) == 1;
        GeneralAirData.front_window_heat = ((this$0.mCanbusInfoInt[4] >> 3) & 1) == 1;
        GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m579setOnParseListeners$lambda3(MsgMgr$initParsers$1$5 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAirData.front_left_temperature = this$0.resolveTemperature(this$1.mCanbusInfoInt[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m580setOnParseListeners$lambda4(MsgMgr$initParsers$1$5 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAirData.front_right_temperature = this$0.resolveTemperature(this$1.mCanbusInfoInt[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m581setOnParseListeners$lambda7(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = (this$0.mCanbusInfoInt[7] >> 5) & 7;
        if (((this$0.mCanbusInfoInt[7] >> 1) & 1) == 0) {
            GeneralAirData.front_left_seat_heat_level = i;
        } else {
            GeneralAirData.front_left_seat_cold_level = i;
        }
        int i2 = (this$0.mCanbusInfoInt[7] >> 2) & 7;
        if ((this$0.mCanbusInfoInt[7] & 1) == 0) {
            GeneralAirData.front_right_seat_heat_level = i2;
        } else {
            GeneralAirData.front_right_seat_cold_level = i2;
        }
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public void parse(int dataLength) {
        try {
            boolean z = true;
            GeneralAirData.fahrenheit_celsius = (this.this$0.mCanbusInfoInt[4] & 1) == 1;
            if (this.outDoorTemperature != this.this$0.mCanbusInfoInt[8]) {
                this.outDoorTemperature = this.this$0.mCanbusInfoInt[8];
                MsgMgr msgMgr = this.this$0;
                Context context = this.$context;
                int i = msgMgr.mCanbusInfoInt[8];
                String str = " ";
                if (i == 254) {
                    str = "--";
                } else if (i != 255) {
                    if (i < 0 || i >= 254) {
                        z = false;
                    }
                    if (z) {
                        str = GeneralAirData.fahrenheit_celsius ? (this.this$0.mCanbusInfoInt[8] - 40) + this.this$0.getTempUnitF(this.$context) : ((this.this$0.mCanbusInfoInt[8] / 2) - 40) + this.this$0.getTempUnitC(this.$context);
                    }
                }
                msgMgr.updateOutDoorTemp(context, str);
            }
            this.this$0.mCanbusInfoInt[8] = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isDataChange()) {
            super.parse(dataLength);
            this.this$0.updateAirActivity(this.$context, 1001);
        }
    }

    private final String resolveTemperature(int value) {
        if (value == 0) {
            return "LO";
        }
        if (value == 253) {
            return "HI";
        }
        if (value == 254) {
            return "OFF";
        }
        if (value == 255) {
            return "";
        }
        if (!(28 <= value && value < 65) || GeneralAirData.fahrenheit_celsius) {
            return ((57 <= value && value < 91) && GeneralAirData.fahrenheit_celsius) ? value + this.this$0.getTempUnitF(this.$context) : "";
        }
        return (value / 2) + this.this$0.getTempUnitC(this.$context);
    }
}
