package com.hzbhd.canbus.car._329;

import android.content.Context;
import com.hzbhd.canbus.car._329.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$7 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int mOutDoorTemperature;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$7(MsgMgr msgMgr, Context context) {
        super(msgMgr, "0x31 空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m679setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m680setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m681setOnParseListeners$lambda2(msgMgr3);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m682setOnParseListeners$lambda3(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m683setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m684setOnParseListeners$lambda5(this.f$0, msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m685setOnParseListeners$lambda6(this.f$0, msgMgr7);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m679setOnParseListeners$lambda0(MsgMgr this$0) {

        GeneralAirData.power = ((this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
        GeneralAirData.dual = ((this$0.mCanbusInfoInt[2] >> 2) & 1) == 1;
        GeneralAirData.ac = (this$0.mCanbusInfoInt[2] & 3) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m680setOnParseListeners$lambda1(MsgMgr this$0) {

        GeneralAirData.in_out_cycle = ((this$0.mCanbusInfoInt[3] >> 4) & 1) == 0;
        GeneralAirData.auto = ((this$0.mCanbusInfoInt[3] >> 3) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m681setOnParseListeners$lambda2(MsgMgr this$0) {

        GeneralAirData.rear_defog = ((this$0.mCanbusInfoInt[4] >> 5) & 1) == 1;
        GeneralAirData.front_defog = ((this$0.mCanbusInfoInt[4] >> 4) & 1) == 1;
        GeneralAirData.front_right_seat_heat_level = (this$0.mCanbusInfoInt[4] >> 2) & 3;
        GeneralAirData.front_left_seat_heat_level = this$0.mCanbusInfoInt[4] & 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m682setOnParseListeners$lambda3(MsgMgr this$0) {

        GeneralAirData.front_left_auto_wind = this$0.mCanbusInfoInt[6] == 1;
        GeneralAirData.front_left_blow_foot = ArraysKt.contains(new int[]{3, 5, 12, 14}, this$0.mCanbusInfoInt[6]);
        GeneralAirData.front_left_blow_head = ArraysKt.contains(new int[]{5, 6, 13, 14}, this$0.mCanbusInfoInt[6]);
        GeneralAirData.front_left_blow_window = ArraysKt.contains(new int[]{11, 12, 13, 14}, this$0.mCanbusInfoInt[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m683setOnParseListeners$lambda4(MsgMgr this$0) {

        GeneralAirData.front_wind_level = this$0.mCanbusInfoInt[7];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m684setOnParseListeners$lambda5(MsgMgr$initParsers$1$7 this$0, MsgMgr this$1) {


        GeneralAirData.front_left_temperature = this$0.getTemperature(this$1.mCanbusInfoInt[8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m685setOnParseListeners$lambda6(MsgMgr$initParsers$1$7 this$0, MsgMgr this$1) {


        GeneralAirData.front_right_temperature = this$0.getTemperature(this$1.mCanbusInfoInt[9]);
    }

    @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
    public void parse(int dataLength) {
        if (this.this$0.mCanbusInfoInt.length > 13 && this.this$0.mCanbusInfoInt[13] < 255 && this.mOutDoorTemperature != this.this$0.mCanbusInfoInt[13]) {
            int i = this.this$0.mCanbusInfoInt[13];
            this.mOutDoorTemperature = i;
            MsgMgr msgMgr = this.this$0;
            Context context = this.$context;
            msgMgr.updateOutDoorTemp(context, MsgMgr.initParsers$getTemperature((i / 2.0f) - 40, msgMgr, context));
        }
        this.this$0.mCanbusInfoInt[13] = 0;
        this.this$0.mCanbusInfoInt[2] = this.this$0.mCanbusInfoInt[2] & 71;
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            this.this$0.updateAirActivity(this.$context, 1001);
        }
    }

    private final String getTemperature(int value) {
        return value != 254 ? value != 255 ? MsgMgr.initParsers$getTemperature(value / 2.0f, this.this$0, this.$context) : "High" : "Low";
    }
}
