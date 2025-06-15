package com.hzbhd.canbus.car._161;

import android.content.Context;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MsgMgr$initParsers$1$3 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$3(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x21】空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m185setOnParseListeners$lambda1(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m187setOnParseListeners$lambda4(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m188setOnParseListeners$lambda5(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m189setOnParseListeners$lambda6(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m190setOnParseListeners$lambda8(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m191setOnParseListeners$lambda9(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m186setOnParseListeners$lambda10(msgMgr7);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m185setOnParseListeners$lambda1(MsgMgr this$0) {

        int i = this$0.mCanbusInfoInt[2];
        GeneralAirData.power = ((i >> 7) & 1) == 1;
        GeneralAirData.ac = ((i >> 6) & 1) == 1;
        GeneralAirData.in_out_cycle = ((i >> 5) & 1) == 0;
        GeneralAirData.aqs = ((i >> 4) & 1) == 1;
        GeneralAirData.auto = ((i >> 3) & 1) == 1;
        GeneralAirData.dual = ((i >> 2) & 1) == 1;
        GeneralAirData.rear_defog = ((i >> 0) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m187setOnParseListeners$lambda4(MsgMgr this$0) {

        int i = this$0.mCanbusInfoInt[3];
        GeneralAirData.front_left_blow_window = ((i >> 7) & 1) == 1;
        GeneralAirData.front_left_blow_head = ((i >> 6) & 1) == 1;
        GeneralAirData.front_left_blow_foot = ((i >> 5) & 1) == 1;
        int i2 = i & 15;
        GeneralAirData.front_auto_wind_speed = Integer.valueOf(i2).equals(0);
        GeneralAirData.front_wind_level = i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m188setOnParseListeners$lambda5(MsgMgr this$0, MsgMgr$initParsers$1$3 this$1) {


        if (this$0.getCurrentCanDifferentId() == 31) {
            if (this$0.mCanbusInfoInt[4] != 2 && this$0.mCanbusInfoInt[4] != 0) {
                if (this$0.mCanbusInfoInt[4] != 253 && this$0.mCanbusInfoInt[4] != 255) {
                    GeneralAirData.front_left_temperature = new StringBuilder().append((this$0.mCanbusInfoInt[4] / 2.0f) - 2).append((char) 8451).toString();
                    return;
                } else {
                    GeneralAirData.front_left_temperature = "HI";
                    return;
                }
            }
            GeneralAirData.front_left_temperature = "LO";
            return;
        }
        GeneralAirData.front_left_temperature = this$1.getTemperature(this$0.mCanbusInfoInt[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m189setOnParseListeners$lambda6(MsgMgr this$0, MsgMgr$initParsers$1$3 this$1) {


        if (this$0.getCurrentCanDifferentId() == 31) {
            if (this$0.mCanbusInfoInt[5] != 2 && this$0.mCanbusInfoInt[5] != 0) {
                if (this$0.mCanbusInfoInt[5] != 253 && this$0.mCanbusInfoInt[5] != 255) {
                    GeneralAirData.front_right_temperature = new StringBuilder().append((this$0.mCanbusInfoInt[5] / 2.0f) - 2).append((char) 8451).toString();
                    return;
                } else {
                    GeneralAirData.front_right_temperature = "HI";
                    return;
                }
            }
            GeneralAirData.front_right_temperature = "LO";
            return;
        }
        GeneralAirData.front_right_temperature = this$1.getTemperature(this$0.mCanbusInfoInt[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-8, reason: not valid java name */
    public static final void m190setOnParseListeners$lambda8(MsgMgr this$0) {

        int i = this$0.mCanbusInfoInt[6];
        GeneralAirData.front_defog = ((i >> 7) & 1) == 1;
        GeneralAirData.ac_max = ((i >> 3) & 1) == 1;
        GeneralAirData.fahrenheit_celsius = ((i >> 0) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-9, reason: not valid java name */
    public static final void m191setOnParseListeners$lambda9(MsgMgr this$0) {

        GeneralAirData.rear = ((this$0.mCanbusInfoInt[7] >> 7) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-10, reason: not valid java name */
    public static final void m186setOnParseListeners$lambda10(MsgMgr this$0) {

        GeneralAirData.auto_wind_lv = (this$0.mCanbusInfoInt[8] >> 6) & 3;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            this.this$0.updateAirActivity(this.$context, 1001);
        }
    }

    private final String getTemperature(int value) {
        MsgMgr msgMgr = this.this$0;
        Context context = this.$context;
        if (Integer.valueOf(value).equals(0)) {
            return "LO";
        }
        if (Integer.valueOf(value).equals(255)) {
            return "HI";
        }
        float f = value / 2;
        if (!GeneralAirData.fahrenheit_celsius) {
            return f + msgMgr.getTempUnitC(context);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((f * 9) / 5)}, 1));

        return sb.append(str).append(msgMgr.getTempUnitF(context)).toString();
    }
}
