package com.hzbhd.canbus.car._112;

import android.content.Context;
import com.hzbhd.canbus.car._112.MsgMgr;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$7 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$7(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x23】前雷达信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m85setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m86setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m87setOnParseListeners$lambda2(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m88setOnParseListeners$lambda3(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m89setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m90setOnParseListeners$lambda5(msgMgr6);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m85setOnParseListeners$lambda0(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, this$0.mCanbusInfoInt[2], 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m86setOnParseListeners$lambda1(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, this$0.mCanbusInfoInt[3], 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m87setOnParseListeners$lambda2(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, this$0.mCanbusInfoInt[4], 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m88setOnParseListeners$lambda3(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, this$0.mCanbusInfoInt[5], 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m89setOnParseListeners$lambda4(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT_PROBE, this$0.mCanbusInfoInt[6], 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m90setOnParseListeners$lambda5(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT_PROBE, this$0.mCanbusInfoInt[7], 2);
    }

    @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.this$0.updateParkUi(null, this.$context);
        }
    }
}
