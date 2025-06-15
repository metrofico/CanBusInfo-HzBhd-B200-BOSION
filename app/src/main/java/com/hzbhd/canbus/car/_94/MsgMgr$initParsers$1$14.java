package com.hzbhd.canbus.car._94;

import android.util.Log;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$14 extends MsgMgr.Parser {
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$14(MsgMgr msgMgr) {
        super(msgMgr, "【0x31】混动示意图");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        GeneralHybirdData.isBatteryDriveMotor = false;
        GeneralHybirdData.isMotorDriveBattery = false;
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m955setOnParseListeners$lambda0(msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m956setOnParseListeners$lambda1(msgMgr2);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m955setOnParseListeners$lambda0(MsgMgr this$0) {
        String strValueOf;

        for (int i = 2; i < 8; i++) {
            if (((this$0.mCanbusInfoInt[2] >> i) & 1) == 1) {
                if (i == 6) {
                    GeneralHybirdData.isBatteryDriveMotor = true;
                } else if (i == 7) {
                    GeneralHybirdData.isMotorDriveBattery = true;
                }
                StringBuilder sbAppend = new StringBuilder().append("【0x31】混动示意图 {");
                switch (i) {
                    case 2:
                        strValueOf = "电池-插电";
                        break;
                    case 3:
                        strValueOf = "电池-空调";
                        break;
                    case 4:
                        strValueOf = "电池-其他";
                        break;
                    case 5:
                        strValueOf = "电池-电机";
                        break;
                    case 6:
                        strValueOf = "电机-引擎";
                        break;
                    case 7:
                        strValueOf = "驱动-电机";
                        break;
                    default:
                        strValueOf = String.valueOf(i);
                        break;
                }
                Log.i(MsgMgr.TAG, sbAppend.append(strValueOf).append('}').toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m956setOnParseListeners$lambda1(MsgMgr this$0) {
        String strValueOf;

        int i = 5;
        while (i < 8) {
            if (((this$0.mCanbusInfoInt[2] >> i) & 1) == 1) {
                StringBuilder sbAppend = new StringBuilder().append("【0x31】混动示意图 {");
                if (i == 5) {
                    strValueOf = "电机-引擎";
                } else if (i != 6) {
                    strValueOf = i != 7 ? String.valueOf(i) : "燃油-引擎";
                } else {
                    strValueOf = "驱动-引擎";
                }
                Log.i(MsgMgr.TAG, sbAppend.append(strValueOf).append('}').toString());
            }
            i++;
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
        }
    }
}
