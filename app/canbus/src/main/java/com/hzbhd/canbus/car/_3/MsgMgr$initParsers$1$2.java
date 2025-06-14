package com.hzbhd.canbus.car._3;

import android.content.Context;
import com.hzbhd.canbus.car._3.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016J\u0015\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0016¢\u0006\u0002\u0010\u0015J\f\u0010\u0016\u001a\u00020\u0017*\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"com/hzbhd/canbus/car/_3/MsgMgr$initParsers$1$2", "Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_3/MsgMgr;", "data8", "", "frontData", "", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "rearData", "rearDataRecord", "getWhat", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "toTemperature", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$2 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int data8;
    private int[] frontData;
    private final ArrayList<SettingUpdateEntity<Object>> list;
    private int rearData;
    private int rearDataRecord;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$2(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x21】空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
        this.frontData = new int[0];
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
        final MsgMgr msgMgr8 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m375setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m376setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m377setOnParseListeners$lambda2(this.f$0, msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m378setOnParseListeners$lambda3(this.f$0, msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m379setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m380setOnParseListeners$lambda5(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m381setOnParseListeners$lambda6(msgMgr7);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$2.m382setOnParseListeners$lambda8(this.f$0, msgMgr8);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m375setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.power = ((this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
        GeneralAirData.ac = ((this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
        GeneralAirData.in_out_cycle = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 0;
        GeneralAirData.auto_1_2 = (this$0.mCanbusInfoInt[2] >> 3) & 3;
        GeneralAirData.dual = ((this$0.mCanbusInfoInt[2] >> 2) & 1) == 1;
        GeneralAirData.max_front = ((this$0.mCanbusInfoInt[2] >> 1) & 1) == 1;
        GeneralAirData.rear = (this$0.mCanbusInfoInt[2] & 1) == 1;
        GeneralAirData.manual = (GeneralAirData.auto_1_2 != 0 || GeneralAirData.ac_max || GeneralAirData.max_front) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m376setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.front_left_blow_window = ((this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
        GeneralAirData.front_left_blow_head = ((this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
        GeneralAirData.front_left_blow_foot = ((this$0.mCanbusInfoInt[3] >> 5) & 1) == 1;
        GeneralAirData.front_wind_level = this$0.mCanbusInfoInt[3] & 15;
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m377setOnParseListeners$lambda2(MsgMgr$initParsers$1$2 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAirData.front_left_temperature = this$0.toTemperature(this$1.mCanbusInfoInt[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m378setOnParseListeners$lambda3(MsgMgr$initParsers$1$2 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAirData.front_right_temperature = this$0.toTemperature(this$1.mCanbusInfoInt[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m379setOnParseListeners$lambda4(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.front_defog = ((this$0.mCanbusInfoInt[6] >> 7) & 1) == 1;
        GeneralAirData.rear_defog = ((this$0.mCanbusInfoInt[6] >> 6) & 1) == 1;
        GeneralAirData.aqs = ((this$0.mCanbusInfoInt[6] >> 5) & 1) == 1;
        GeneralAirData.eco = ((this$0.mCanbusInfoInt[6] >> 4) & 1) == 1;
        GeneralAirData.ac_max = ((this$0.mCanbusInfoInt[6] >> 3) & 1) == 1;
        GeneralAirData.clean_air = ((this$0.mCanbusInfoInt[6] >> 2) & 1) == 1;
        GeneralAirData.fahrenheit_celsius = (this$0.mCanbusInfoInt[6] & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m380setOnParseListeners$lambda5(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.steering_wheel_heating = ((this$0.mCanbusInfoInt[7] >> 7) & 1) == 1;
        GeneralAirData.front_left_seat_heat_level = (this$0.mCanbusInfoInt[7] >> 4) & 7;
        GeneralAirData.front_right_seat_heat_level = this$0.mCanbusInfoInt[7] & 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m381setOnParseListeners$lambda6(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.front_left_seat_cold_level = (this$0.mCanbusInfoInt[8] >> 6) & 3;
        GeneralAirData.front_right_seat_cold_level = (this$0.mCanbusInfoInt[8] >> 4) & 3;
        GeneralAirData.auto_wind_lv = this$0.mCanbusInfoInt[8] & 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-8, reason: not valid java name */
    public static final void m382setOnParseListeners$lambda8(MsgMgr$initParsers$1$2 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int i = this$1.mCanbusInfoInt[9];
        this$0.rearData = i;
        GeneralAirData.rear_temperature = this$0.toTemperature(i);
        this$1.mCanbusInfoInt[9] = 0;
    }

    @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
    public void parse(int dataLength) {
        if (dataLength > 8) {
            if (this.data8 != this.this$0.mCanbusInfoInt[10]) {
                this.data8 = this.this$0.mCanbusInfoInt[10];
                this.list.clear();
                SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_21h_d8_b7");
                if (settingUpdateEntity != null) {
                    this.list.add(settingUpdateEntity.setValue(Integer.valueOf((this.this$0.mCanbusInfoInt[10] >> 7) & 1)));
                }
                SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_21h_d8_b65");
                if (settingUpdateEntity2 != null) {
                    this.list.add(settingUpdateEntity2.setValue(Integer.valueOf((this.this$0.mCanbusInfoInt[10] >> 5) & 3)));
                }
                this.this$0.updateGeneralSettingData(this.list);
                this.this$0.updateSettingActivity(null);
            }
            this.this$0.mCanbusInfoInt[10] = 0;
        }
        this.this$0.mCanbusInfoInt[3] = this.this$0.mCanbusInfoInt[3] & 239;
        if (this.this$0.isAirDataChange()) {
            super.parse(dataLength);
            this.this$0.updateAirActivity(this.$context, getWhat());
        }
    }

    private final String toTemperature(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (!(1 <= i && i < 29)) {
            return "";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return new StringBuilder().append(i + 59).append((char) 8457).toString();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.1f℃", Arrays.copyOf(new Object[]{Float.valueOf((i + 31) / 2.0f)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }

    private final int getWhat() {
        if (!Arrays.equals(this.frontData, this.this$0.mCanbusInfoInt)) {
            int[] iArr = this.this$0.mCanbusInfoInt;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, size)");
            this.frontData = iArrCopyOf;
            return 1004;
        }
        int i = this.rearDataRecord;
        int i2 = this.rearData;
        if (i == i2) {
            return 0;
        }
        this.rearDataRecord = i2;
        return 1003;
    }
}
