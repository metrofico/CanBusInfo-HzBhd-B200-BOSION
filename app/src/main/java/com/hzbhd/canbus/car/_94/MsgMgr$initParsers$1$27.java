package com.hzbhd.canbus.car._94;

import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$27 extends MsgMgr.Parser {
    private ArrayList<DriverUpdateEntity> driveUpdateList;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$27(MsgMgr msgMgr) {
        super(msgMgr, "【0x69】车辆信息2");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$27$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$27.m997setOnParseListeners$lambda1(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$27$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$27.m998setOnParseListeners$lambda3(msgMgr2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$27$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$27.m999setOnParseListeners$lambda5(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$27$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$27.m1000setOnParseListeners$lambda7(msgMgr4, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m997setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$27 this$1) {


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("average_speed");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(this$0.mCanbusInfoInt[2] != 255 ? this$0.mCanbusInfoInt[2] + " Km/H" : "--"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m998setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$27 this$1) {


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_94_maintenance_mileage");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue((((this$0.mCanbusInfoInt[3] * 256) + this$0.mCanbusInfoInt[4]) / 2) + " Km"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m999setOnParseListeners$lambda5(MsgMgr this$0, MsgMgr$initParsers$1$27 this$1) {


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_94_battery_capacity");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(this$0.mCanbusInfoInt[5] + " %"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m1000setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$27 this$1) {


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_94_battery_voltage");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {

                arrayList = null;
            }
            double d = 10;
            arrayList.add(driverUpdateEntity.setValue(((Math.rint(((this$0.mCanbusInfoInt[6] * 47) / 755.0d) * d) / d) + 3) + " V"));
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.driveUpdateList = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<DriverUpdateEntity> arrayList = this.driveUpdateList;
            if (arrayList == null) {

                arrayList = null;
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
        }
    }
}
