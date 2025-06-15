package com.hzbhd.canbus.car._94;

import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$28 extends MsgMgr.Parser {
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$28(MsgMgr msgMgr) {
        super(msgMgr, "【0x64】座椅按摩");
        this.this$0 = msgMgr;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$28$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$28.m1002setOnParseListeners$lambda20(this.f$0, msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$28$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$28.m1003setOnParseListeners$lambda21(msgMgr2);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$28$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$28.m1004setOnParseListeners$lambda22(msgMgr3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$28$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$28.m1005setOnParseListeners$lambda23(msgMgr4);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$28$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$28.m1006setOnParseListeners$lambda24(msgMgr5);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-20, reason: not valid java name */
    public static final void m1002setOnParseListeners$lambda20(MsgMgr$initParsers$1$28 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        boolean z = ((this$1.mCanbusInfoInt[2] >> 7) & 1) == 1;
        int i = (this$1.mCanbusInfoInt[2] >> 5) & 3;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_driver");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(i)).setEnable(z));
        }
        boolean z2 = z && i == 1;
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_driver_high");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf(this$1.getDriver().getHigh())).setProgress(this$1.getDriver().getHigh()).setEnable(z2));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_driver_mid");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf(this$1.getDriver().getMiddle())).setProgress(this$1.getDriver().getMiddle()).setEnable(z2));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_driver_low");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf(this$1.getDriver().getLow())).setProgress(this$1.getDriver().getLow()).setEnable(z2));
        }
        boolean z3 = z && i == 2;
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_driver_backrest");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(this$1.getDriver().getBackseat())).setEnable(z3));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_driver_cushion");
        if (settingUpdateEntity6 != null) {
            arrayList.add(settingUpdateEntity6.setValue(Integer.valueOf(this$1.getDriver().getCushion())).setEnable(z3));
        }
        int i2 = (this$1.mCanbusInfoInt[2] >> 3) & 3;
        SettingUpdateEntity settingUpdateEntity7 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_passenger");
        if (settingUpdateEntity7 != null) {
            arrayList.add(settingUpdateEntity7.setValue(Integer.valueOf(i2)).setEnable(z));
        }
        boolean z4 = z && i2 == 1;
        SettingUpdateEntity settingUpdateEntity8 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_passenger_high");
        if (settingUpdateEntity8 != null) {
            arrayList.add(settingUpdateEntity8.setValue(Integer.valueOf(this$1.getPassenger().getHigh())).setProgress(this$1.getPassenger().getHigh()).setEnable(z4));
        }
        SettingUpdateEntity settingUpdateEntity9 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_passenger_mid");
        if (settingUpdateEntity9 != null) {
            arrayList.add(settingUpdateEntity9.setValue(Integer.valueOf(this$1.getPassenger().getMiddle())).setProgress(this$1.getPassenger().getMiddle()).setEnable(z4));
        }
        SettingUpdateEntity settingUpdateEntity10 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_passenger_low");
        if (settingUpdateEntity10 != null) {
            arrayList.add(settingUpdateEntity10.setValue(Integer.valueOf(this$1.getPassenger().getLow())).setProgress(this$1.getPassenger().getLow()).setEnable(z4));
        }
        boolean z5 = z && i2 == 2;
        SettingUpdateEntity settingUpdateEntity11 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_passenger_backrest");
        if (settingUpdateEntity11 != null) {
            arrayList.add(settingUpdateEntity11.setValue(Integer.valueOf(this$1.getPassenger().getBackseat())).setEnable(z5));
        }
        SettingUpdateEntity settingUpdateEntity12 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_passenger_cushion");
        if (settingUpdateEntity12 != null) {
            arrayList.add(settingUpdateEntity12.setValue(Integer.valueOf(this$1.getPassenger().getCushion())).setEnable(z5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-21, reason: not valid java name */
    public static final void m1003setOnParseListeners$lambda21(MsgMgr this$0) {

        this$0.getDriver().setHigh((this$0.mCanbusInfoInt[3] >> 4) & 15);
        this$0.getDriver().setMiddle(this$0.mCanbusInfoInt[3] & 15);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-22, reason: not valid java name */
    public static final void m1004setOnParseListeners$lambda22(MsgMgr this$0) {

        this$0.getDriver().setLow((this$0.mCanbusInfoInt[4] >> 4) & 15);
        this$0.getDriver().setBackseat((this$0.mCanbusInfoInt[4] >> 2) & 3);
        this$0.getDriver().setCushion(this$0.mCanbusInfoInt[4] & 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-23, reason: not valid java name */
    public static final void m1005setOnParseListeners$lambda23(MsgMgr this$0) {

        this$0.getPassenger().setHigh((this$0.mCanbusInfoInt[5] >> 4) & 15);
        this$0.getPassenger().setMiddle(this$0.mCanbusInfoInt[5] & 15);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-24, reason: not valid java name */
    public static final void m1006setOnParseListeners$lambda24(MsgMgr this$0) {

        this$0.getPassenger().setLow((this$0.mCanbusInfoInt[6] >> 4) & 15);
        this$0.getPassenger().setBackseat((this$0.mCanbusInfoInt[6] >> 2) & 3);
        this$0.getPassenger().setCushion(this$0.mCanbusInfoInt[6] & 3);
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list.clear();
            super.parse(dataLength);
            this.this$0.updateGeneralSettingData(this.list);
            this.this$0.updateSettingActivity(null);
        }
    }
}
