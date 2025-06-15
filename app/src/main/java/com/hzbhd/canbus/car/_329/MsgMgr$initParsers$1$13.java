package com.hzbhd.canbus.car._329;

import com.hzbhd.canbus.car._329.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$13 extends MsgMgr.Parser {
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$13(MsgMgr msgMgr) {
        super(msgMgr, "0x87 设置信息");
        this.this$0 = msgMgr;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m666setOnParseListeners$lambda3(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m667setOnParseListeners$lambda9(msgMgr2, this);
            }
        }, null, null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m663setOnParseListeners$lambda12(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m664setOnParseListeners$lambda14(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$13$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$13.m665setOnParseListeners$lambda16(msgMgr5, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m666setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_1");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 5) & 7)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_2");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 2) & 7)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_3");
        if (settingUpdateEntity3 != null) {
            this$1.list.add(settingUpdateEntity3.setValue(Integer.valueOf(this$0.mCanbusInfoInt[2] & 3)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-9, reason: not valid java name */
    public static final void m667setOnParseListeners$lambda9(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_4");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf((this$0.mCanbusInfoInt[3] >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_5");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf((this$0.mCanbusInfoInt[3] >> 5) & 3)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_6");
        if (settingUpdateEntity3 != null) {
            this$1.list.add(settingUpdateEntity3.setValue(Integer.valueOf((this$0.mCanbusInfoInt[3] >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_7");
        if (settingUpdateEntity4 != null) {
            this$1.list.add(settingUpdateEntity4.setValue(Integer.valueOf((this$0.mCanbusInfoInt[3] >> 3) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_8");
        if (settingUpdateEntity5 != null) {
            this$1.list.add(settingUpdateEntity5.setValue(Integer.valueOf((this$0.mCanbusInfoInt[3] >> 2) & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-12, reason: not valid java name */
    public static final void m663setOnParseListeners$lambda12(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_9");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(((this$0.mCanbusInfoInt[7] >> 4) & 15) - 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_10");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf((this$0.mCanbusInfoInt[7] & 15) - 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-14, reason: not valid java name */
    public static final void m664setOnParseListeners$lambda14(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_11");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[8])).setProgress(this$0.mCanbusInfoInt[8] - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-16, reason: not valid java name */
    public static final void m665setOnParseListeners$lambda16(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_12");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[9])).setProgress(this$0.mCanbusInfoInt[9] - 1));
        }
    }

    @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list.clear();
            super.parse(dataLength);
            this.this$0.updateGeneralSettingData(this.list);
            this.this$0.updateSettingActivity(null);
        }
    }
}
