package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MsgMgr$initParsers$1$3 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private final String close;
    private final String level;
    private byte outdoorTemperature;
    private int[] rearAirDatas;
    final /* synthetic */ MsgMgr this$0;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m1019setOnParseListeners$lambda5() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$3(Context context, MsgMgr msgMgr) {
        super(msgMgr, "【0x21】空调信息");
        this.$context = context;
        this.this$0 = msgMgr;
        this.outdoorTemperature = (byte) -1;
        String strByResId = CommUtil.getStrByResId(context, "_318_level");

        this.level = strByResId;
        String strByResId2 = CommUtil.getStrByResId(context, "close");

        this.close = strByResId2;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        final MsgMgr msgMgr8 = this.this$0;
        final MsgMgr msgMgr9 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1012setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1013setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1016setOnParseListeners$lambda2(this.f$0, msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1017setOnParseListeners$lambda3(this.f$0, msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1018setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1019setOnParseListeners$lambda5();
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1020setOnParseListeners$lambda6(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1021setOnParseListeners$lambda7(msgMgr7, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1014setOnParseListeners$lambda10(msgMgr8);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$3$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$3.m1015setOnParseListeners$lambda11(msgMgr9);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m1012setOnParseListeners$lambda0(MsgMgr this$0) {

        GeneralAirData.power = ((this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
        GeneralAirData.ac = ((this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
        GeneralAirData.in_out_cycle = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 0;
        GeneralAirData.auto = ((this$0.mCanbusInfoInt[2] >> 3) & 1) == 1;
        GeneralAirData.dual = ((this$0.mCanbusInfoInt[2] >> 2) & 1) == 1;
        GeneralAirData.max_front = ((this$0.mCanbusInfoInt[2] >> 1) & 1) == 1;
        GeneralAirData.rear_defog = (this$0.mCanbusInfoInt[2] & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m1013setOnParseListeners$lambda1(MsgMgr this$0) {

        GeneralAirData.front_left_blow_window = ((this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
        GeneralAirData.front_left_blow_head = ((this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
        GeneralAirData.front_left_blow_foot = ((this$0.mCanbusInfoInt[3] >> 5) & 1) == 1;
        GeneralAirData.front_wind_level = this$0.mCanbusInfoInt[3] & 15;
        GeneralAirData.front_auto_wind_speed = GeneralAirData.front_wind_level == 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m1016setOnParseListeners$lambda2(MsgMgr$initParsers$1$3 this$0, MsgMgr this$1) {


        GeneralAirData.front_left_temperature = this$0.resolveTemperature(this$1.mCanbusInfoInt[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m1017setOnParseListeners$lambda3(MsgMgr$initParsers$1$3 this$0, MsgMgr this$1) {


        GeneralAirData.front_right_temperature = this$0.resolveTemperature(this$1.mCanbusInfoInt[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m1018setOnParseListeners$lambda4(MsgMgr this$0) {

        GeneralAirData.rear_power = ((this$0.mCanbusInfoInt[6] >> 7) & 1) == 1;
        GeneralAirData.fahrenheit_celsius = ((this$0.mCanbusInfoInt[6] >> 6) & 1) == 1;
        GeneralAirData.front_defog = ((this$0.mCanbusInfoInt[6] >> 5) & 1) == 1;
        GeneralAirData.rear_lock = ((this$0.mCanbusInfoInt[6] >> 4) & 1) == 1;
        GeneralAirData.steering_wheel_heating = ((this$0.mCanbusInfoInt[6] >> 3) & 1) == 1;
        GeneralAirData.ac_max = ((this$0.mCanbusInfoInt[6] >> 2) & 1) == 1;
        GeneralAirData.rear_left_blow_head = ((this$0.mCanbusInfoInt[6] >> 1) & 1) == 1;
        GeneralAirData.rear_left_blow_foot = ((this$0.mCanbusInfoInt[6] >> 0) & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m1020setOnParseListeners$lambda6(MsgMgr this$0) {

        GeneralAirData.rear_wind_level = this$0.mCanbusInfoInt[8];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m1021setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$3 this$1) {
        String str;


        if (this$0.mCanbusInfoInt[9] != 0) {
            str = this$1.level + this$0.mCanbusInfoInt[9];
        } else {
            str = this$1.close;
        }
        GeneralAirData.rear_temperature = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-10, reason: not valid java name */
    public static final void m1014setOnParseListeners$lambda10(MsgMgr this$0) {

        int i = (this$0.mCanbusInfoInt[10] >> 4) & 15;
        if (i > 3) {
            GeneralAirData.front_right_seat_cold_level = 0;
            GeneralAirData.front_right_seat_heat_level = i - 3;
        } else {
            GeneralAirData.front_right_seat_cold_level = i;
            GeneralAirData.front_right_seat_heat_level = 0;
        }
        int i2 = this$0.mCanbusInfoInt[10] & 15;
        if (i2 > 3) {
            GeneralAirData.front_left_seat_cold_level = 0;
            GeneralAirData.front_left_seat_heat_level = i2 - 3;
        } else {
            GeneralAirData.front_left_seat_cold_level = i2;
            GeneralAirData.front_left_seat_heat_level = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m1015setOnParseListeners$lambda11(MsgMgr this$0) {

        GeneralAirData.auto_wind_lv = (this$0.mCanbusInfoInt[11] >> 6) & 3;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (this.outdoorTemperature != this.this$0.mCanbusInfoByte[7]) {
            byte b = this.this$0.mCanbusInfoByte[7];
            this.outdoorTemperature = b;
            this.this$0.updateOutDoorTemp(this.$context, -40 <= b && b < 87 ? ((int) this.outdoorTemperature) + this.this$0.getTempUnitC(this.$context) : "--");
        }
        this.this$0.mCanbusInfoInt[3] = this.this$0.mCanbusInfoInt[3] | 16;
        this.this$0.mCanbusInfoInt[7] = 0;
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
            MsgMgr msgMgr = this.this$0;
            Context context = this.$context;
            int[] iArr = {msgMgr.mCanbusInfoInt[6] & 147, this.this$0.mCanbusInfoInt[8], this.this$0.mCanbusInfoInt[9]};
            int[] iArr2 = this.rearAirDatas;
            int i = (iArr2 == null || Arrays.equals(iArr2, iArr)) ? 1001 : 1002;
            int[] iArrCopyOf = Arrays.copyOf(iArr, 3);

            this.rearAirDatas = iArrCopyOf;
            msgMgr.updateAirActivity(context, i);
        }
    }

    private final String resolveTemperature(int value) {
        if (value == 0) {
            return "LO";
        }
        if (value == 127) {
            return "HI";
        }
        if (!(31 <= value && value < 60)) {
            return "";
        }
        if (!GeneralAirData.fahrenheit_celsius) {
            return (value / 2.0f) + this.this$0.getTempUnitC(this.$context);
        }
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((((value / 2.0f) * 9) / 5) + 32)}, 1));

        return sb.append(str).append(this.this$0.getTempUnitF(this.$context)).toString();
    }
}
