package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0015\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$5", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$5 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$5(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x23】前雷达信息");
        this.this$0 = msgMgr;
        this.$context = context;
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
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1036setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1037setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1038setOnParseListeners$lambda2(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1039setOnParseListeners$lambda3(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1040setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1041setOnParseListeners$lambda5(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1042setOnParseListeners$lambda6(msgMgr7);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$5$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$5.m1043setOnParseListeners$lambda7(msgMgr8);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m1036setOnParseListeners$lambda0(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.FRONT_LEFT, this$0.mCanbusInfoInt[2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m1037setOnParseListeners$lambda1(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.FRONT_MID_LEFT, this$0.mCanbusInfoInt[3]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m1038setOnParseListeners$lambda2(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, this$0.mCanbusInfoInt[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m1039setOnParseListeners$lambda3(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.FRONT_RIGHT, this$0.mCanbusInfoInt[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m1040setOnParseListeners$lambda4(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.RIGHT_FRONT, this$0.mCanbusInfoInt[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m1041setOnParseListeners$lambda5(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.LEFT_FRONT, this$0.mCanbusInfoInt[7]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m1042setOnParseListeners$lambda6(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.LEFT_MID_FRONT, this$0.mCanbusInfoInt[8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m1043setOnParseListeners$lambda7(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, this$0.mCanbusInfoInt[9]);
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            super.parse(dataLength);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.this$0.updateParkUi(null, this.$context);
        }
    }
}
