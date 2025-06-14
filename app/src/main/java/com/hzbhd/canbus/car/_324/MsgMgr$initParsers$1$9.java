package com.hzbhd.canbus.car._324;

import android.content.Context;
import com.hzbhd.canbus.car._324.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0016J\u0015\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"com/hzbhd/canbus/car/_324/MsgMgr$initParsers$1$9", "Lcom/hzbhd/canbus/car/_324/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_324/MsgMgr;", "rearLock", "", "getTemperature", "", "value", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$9 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private int rearLock;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$9(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x28】前座空调信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m620setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m621setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m622setOnParseListeners$lambda2(this.f$0, msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m623setOnParseListeners$lambda3(this.f$0, msgMgr4);
            }
        }, null};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m620setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.power = ((this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
        GeneralAirData.ac = ((this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
        GeneralAirData.in_out_cycle = ((this$0.mCanbusInfoInt[2] >> 5) & 1) == 0;
        GeneralAirData.auto = ((this$0.mCanbusInfoInt[2] >> 4) & 1) == 1;
        GeneralAirData.dual = ((this$0.mCanbusInfoInt[2] >> 2) & 1) == 1;
        GeneralAirData.max_front = ((this$0.mCanbusInfoInt[2] >> 1) & 1) == 1;
        GeneralAirData.rear = (this$0.mCanbusInfoInt[2] & 1) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m621setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        GeneralAirData.front_left_blow_window = ((this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
        GeneralAirData.front_left_blow_head = ((this$0.mCanbusInfoInt[3] >> 6) & 1) == 1;
        GeneralAirData.front_left_blow_foot = ((this$0.mCanbusInfoInt[3] >> 5) & 1) == 1;
        GeneralAirData.front_wind_level = this$0.mCanbusInfoInt[3] & 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m622setOnParseListeners$lambda2(MsgMgr$initParsers$1$9 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAirData.front_left_temperature = this$0.getTemperature(this$1.mCanbusInfoInt[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m623setOnParseListeners$lambda3(MsgMgr$initParsers$1$9 this$0, MsgMgr this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        GeneralAirData.front_right_temperature = this$0.getTemperature(this$1.mCanbusInfoInt[5]);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003a  */
    @Override // com.hzbhd.canbus.car._324.MsgMgr.Parser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parse(int r7) {
        /*
            r6 = this;
            com.hzbhd.canbus.car._324.MsgMgr r0 = r6.this$0
            int[] r0 = com.hzbhd.canbus.car._324.MsgMgr.access$getMCanbusInfoInt$p(r0)
            int r0 = r0.length
            r1 = 0
            r2 = 6
            if (r0 <= r2) goto L3a
            com.hzbhd.canbus.car._324.MsgMgr r0 = r6.this$0
            int[] r0 = com.hzbhd.canbus.car._324.MsgMgr.access$getMCanbusInfoInt$p(r0)
            r0 = r0[r2]
            int r0 = r0 >> 2
            r3 = 1
            r0 = r0 & r3
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            int r5 = r6.rearLock
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L3a
            r6.rearLock = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            boolean r0 = r0.equals(r3)
            com.hzbhd.canbus.ui_datas.GeneralAirData.rear_lock = r0
            r0 = 1002(0x3ea, float:1.404E-42)
            goto L3b
        L3a:
            r0 = r1
        L3b:
            com.hzbhd.canbus.car._324.MsgMgr r3 = r6.this$0
            int[] r3 = com.hzbhd.canbus.car._324.MsgMgr.access$getMCanbusInfoInt$p(r3)
            r3[r2] = r1
            com.hzbhd.canbus.car._324.MsgMgr r1 = r6.this$0
            int[] r1 = com.hzbhd.canbus.car._324.MsgMgr.access$getMCanbusInfoInt$p(r1)
            com.hzbhd.canbus.car._324.MsgMgr r2 = r6.this$0
            int[] r2 = com.hzbhd.canbus.car._324.MsgMgr.access$getMCanbusInfoInt$p(r2)
            r3 = 3
            r2 = r2[r3]
            r2 = r2 & 239(0xef, float:3.35E-43)
            r1[r3] = r2
            boolean r1 = r6.isDataChange()
            if (r1 == 0) goto L6d
            super.parse(r7)
            boolean r7 = com.hzbhd.canbus.ui_datas.GeneralAirData.front_left_blow_window
            com.hzbhd.canbus.ui_datas.GeneralAirData.front_right_blow_window = r7
            boolean r7 = com.hzbhd.canbus.ui_datas.GeneralAirData.front_left_blow_head
            com.hzbhd.canbus.ui_datas.GeneralAirData.front_right_blow_head = r7
            boolean r7 = com.hzbhd.canbus.ui_datas.GeneralAirData.front_left_blow_foot
            com.hzbhd.canbus.ui_datas.GeneralAirData.front_right_blow_foot = r7
            r0 = 1001(0x3e9, float:1.403E-42)
        L6d:
            if (r0 == 0) goto L76
            com.hzbhd.canbus.car._324.MsgMgr r7 = r6.this$0
            android.content.Context r1 = r6.$context
            com.hzbhd.canbus.car._324.MsgMgr.access$updateAirActivity(r7, r1, r0)
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._324.MsgMgr$initParsers$1$9.parse(int):void");
    }

    private final String getTemperature(int value) {
        if (value == 0) {
            return "LO";
        }
        if (value == 31) {
            return "HI";
        }
        boolean z = false;
        if (1 <= value && value < 30) {
            z = true;
        }
        return z ? ((value + 35) / 2.0f) + this.this$0.getTempUnitC(this.$context) : "";
    }
}
