package com.hzbhd.canbus.car._322;

import android.content.Context;
import com.hzbhd.canbus.car._322.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




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

        this.level = strByResId;
        String strByResId2 = CommUtil.getStrByResId(context, "close");

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

        GeneralAirData.rear_power = ((this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
        GeneralAirData.rear_lock = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 1;
        int i = this$0.mCanbusInfoInt[2] & 15;
        GeneralAirData.rear_wind_level = Integer.valueOf(i).equals(15) ? 0 : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m585setOnParseListeners$lambda2(MsgMgr this$0) {

        GeneralAirData.rear_left_blow_foot = ((this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
        GeneralAirData.rear_left_blow_head = ((this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
        GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
        GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m586setOnParseListeners$lambda5(MsgMgr this$0, MsgMgr$initParsers$1$6 this$1) {
        String str;


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
