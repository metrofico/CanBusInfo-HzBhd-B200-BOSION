package com.hzbhd.canbus.car._324;

import android.content.Context;
import com.hzbhd.canbus.car._324.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0015\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0016¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"com/hzbhd/canbus/car/_324/MsgMgr$initParsers$1$4", "Lcom/hzbhd/canbus/car/_324/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_324/MsgMgr;", "outDoorTemperature", "", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
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
