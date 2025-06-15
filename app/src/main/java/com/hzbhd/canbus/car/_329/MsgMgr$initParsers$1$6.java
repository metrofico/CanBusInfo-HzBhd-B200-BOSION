package com.hzbhd.canbus.car._329;

import android.content.Context;
import com.hzbhd.canbus.car._329.MsgMgr;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$6 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$6(MsgMgr msgMgr, Context context) {
        super(msgMgr, "0x41 雷达信息");
        this.this$0 = msgMgr;
        this.$context = context;
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.mDisableData2 = 255;
        RadarInfoUtil.mMinIsClose = true;
    }

    @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        final MsgMgr msgMgr8 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m670setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m671setOnParseListeners$lambda1(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m672setOnParseListeners$lambda2(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m673setOnParseListeners$lambda3(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m674setOnParseListeners$lambda4(msgMgr5);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m675setOnParseListeners$lambda5(msgMgr6);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m676setOnParseListeners$lambda6(msgMgr7);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$6$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$6.m677setOnParseListeners$lambda7(msgMgr8);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m670setOnParseListeners$lambda0(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, this$0.mCanbusInfoInt[2], 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m671setOnParseListeners$lambda1(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, this$0.mCanbusInfoInt[3], 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m672setOnParseListeners$lambda2(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, this$0.mCanbusInfoInt[4], 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m673setOnParseListeners$lambda3(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, this$0.mCanbusInfoInt[5], 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m674setOnParseListeners$lambda4(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, this$0.mCanbusInfoInt[6], 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m675setOnParseListeners$lambda5(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, this$0.mCanbusInfoInt[7], 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m676setOnParseListeners$lambda6(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, this$0.mCanbusInfoInt[8], 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m677setOnParseListeners$lambda7(MsgMgr this$0) {

        RadarInfoUtil.setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, this$0.mCanbusInfoInt[9], 4);
    }

    @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.this$0.updateParkUi(null, this.$context);
        }
    }
}
