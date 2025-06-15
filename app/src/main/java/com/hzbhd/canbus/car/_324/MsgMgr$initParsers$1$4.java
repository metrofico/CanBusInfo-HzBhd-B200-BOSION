package com.hzbhd.canbus.car._324;

import android.content.Context;
import com.hzbhd.canbus.car._324.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$4 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int outDoorTemperature;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$4(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x1A】空调辅助信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$4$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$4.m614setOnParseListeners$lambda0(msgMgr);
            }
        }, null};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m614setOnParseListeners$lambda0(MsgMgr this$0) {

        GeneralAirData.clean_air = ((this$0.mCanbusInfoInt[2] >> 3) & 1) == 1;
        GeneralAirData.swing = ((this$0.mCanbusInfoInt[2] >> 2) & 1) == 1;
        GeneralAirData.negative_ion = ((this$0.mCanbusInfoInt[2] >> 1) & 1) == 1;
        GeneralAirData.front_window_heat = (this$0.mCanbusInfoInt[2] & 1) == 1;
    }

    @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
    public void parse(int dataLength) {
        if (this.outDoorTemperature != this.this$0.mCanbusInfoInt[3]) {
            this.this$0.updateOutDoorTemp(this.$context, ((int) this.this$0.mCanbusInfoByte[3]) + this.this$0.getTempUnitC(this.$context));
        }
        this.this$0.mCanbusInfoInt[3] = 0;
        if (isDataChange()) {
            super.parse(dataLength);
            this.this$0.updateAirActivity(this.$context, 1001);
        }
    }
}
