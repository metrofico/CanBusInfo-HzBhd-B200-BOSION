package com.hzbhd.canbus.car._322;

import android.content.Context;
import com.hzbhd.canbus.car._322.MsgMgr;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$12 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$12(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x2A】后雷达指示");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        final MsgMgr msgMgr8 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m533setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m534setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m535setOnParseListeners$lambda2(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m536setOnParseListeners$lambda3(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m537setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m538setOnParseListeners$lambda5(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m539setOnParseListeners$lambda6(msgMgr7);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m540setOnParseListeners$lambda7(msgMgr8);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m533setOnParseListeners$lambda0(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.LEFT_MID_REAR, this$0.mCanbusInfoInt[2], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m534setOnParseListeners$lambda1(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.LEFT_REAR, this$0.mCanbusInfoInt[3], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m535setOnParseListeners$lambda2(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, this$0.mCanbusInfoInt[4], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m536setOnParseListeners$lambda3(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, this$0.mCanbusInfoInt[5], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m537setOnParseListeners$lambda4(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_REAR, this$0.mCanbusInfoInt[6], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m538setOnParseListeners$lambda5(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.RIGHT_REAR, this$0.mCanbusInfoInt[7], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m539setOnParseListeners$lambda6(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, this$0.mCanbusInfoInt[8], 7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m540setOnParseListeners$lambda7(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, this$0.mCanbusInfoInt[9], 7);
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.this$0.updateParkUi(null, this.$context);
        }
    }
}
