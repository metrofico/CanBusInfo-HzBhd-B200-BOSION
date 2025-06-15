package com.hzbhd.canbus.car._322;

import com.hzbhd.canbus.car._322.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$14 extends MsgMgr.Parser {
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$14(MsgMgr msgMgr) {
        super(msgMgr, "【0x40】按摩座椅状态信息");
        this.this$0 = msgMgr;
        this.list = new ArrayList<>();
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
        return new OnParseListener[]{null, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m555setOnParseListeners$lambda1(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m559setOnParseListeners$lambda3(msgMgr2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m560setOnParseListeners$lambda5(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m561setOnParseListeners$lambda7(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m562setOnParseListeners$lambda9(msgMgr5, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m556setOnParseListeners$lambda11(msgMgr6, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m557setOnParseListeners$lambda14(msgMgr7, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._322.MsgMgr$initParsers$1$14$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$14.m558setOnParseListeners$lambda17(msgMgr8, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m555setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getDriver().setHigh(this$0.mCanbusInfoInt[3]);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_driver_high");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getDriver().getHigh())).setProgress(this$0.getDriver().getHigh() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m559setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getDriver().setMiddle(this$0.mCanbusInfoInt[4]);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_driver_mid");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getDriver().getMiddle())).setProgress(this$0.getDriver().getMiddle() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m560setOnParseListeners$lambda5(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getDriver().setLow(this$0.mCanbusInfoInt[5]);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_driver_low");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getDriver().getLow())).setProgress(this$0.getDriver().getLow() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m561setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getPassenger().setHigh(this$0.mCanbusInfoInt[6]);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_passenger_high");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getPassenger().getHigh())).setProgress(this$0.getPassenger().getHigh() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-9, reason: not valid java name */
    public static final void m562setOnParseListeners$lambda9(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getPassenger().setMiddle(this$0.mCanbusInfoInt[7]);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_passenger_mid");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getPassenger().getMiddle())).setProgress(this$0.getPassenger().getMiddle() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m556setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getPassenger().setLow(this$0.mCanbusInfoInt[8]);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_passenger_low");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getPassenger().getLow())).setProgress(this$0.getPassenger().getLow() - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-14, reason: not valid java name */
    public static final void m557setOnParseListeners$lambda14(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getDriver().setBackseat((this$0.mCanbusInfoInt[9] >> 2) & 3);
        this$0.getDriver().setCushion(this$0.mCanbusInfoInt[9] & 3);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_driver_backrest");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getDriver().getBackseat())));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_driver_cushion");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf(this$0.getDriver().getCushion())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-17, reason: not valid java name */
    public static final void m558setOnParseListeners$lambda17(MsgMgr this$0, MsgMgr$initParsers$1$14 this$1) {


        this$0.getPassenger().setBackseat((this$0.mCanbusInfoInt[10] >> 2) & 3);
        this$0.getPassenger().setCushion(this$0.mCanbusInfoInt[10] & 3);
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_passenger_backrest");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.getPassenger().getBackseat())));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_passenger_cushion");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf(this$0.getPassenger().getCushion())));
        }
    }

    @Override // com.hzbhd.canbus.car._322.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list.clear();
            super.parse(dataLength);
            this.this$0.updateGeneralSettingData(this.list);
            this.this$0.updateSettingActivity(null);
        }
    }
}
