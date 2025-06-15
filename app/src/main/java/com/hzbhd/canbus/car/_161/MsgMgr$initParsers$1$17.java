package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$17 extends MsgMgr.Parser {
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$17(MsgMgr msgMgr) {
        super(msgMgr, "【0x3D】巡航速度、速度限值");
        this.this$0 = msgMgr;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        final MsgMgr msgMgr7 = this.this$0;
        final MsgMgr msgMgr8 = this.this$0;
        final MsgMgr msgMgr9 = this.this$0;
        final MsgMgr msgMgr10 = this.this$0;
        final MsgMgr msgMgr11 = this.this$0;
        final MsgMgr msgMgr12 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m165setOnParseListeners$lambda1(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m173setOnParseListeners$lambda3(msgMgr2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda6
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m174setOnParseListeners$lambda5(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda7
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m175setOnParseListeners$lambda7(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda8
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m176setOnParseListeners$lambda9(msgMgr5, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda9
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m166setOnParseListeners$lambda11(msgMgr6, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda10
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m167setOnParseListeners$lambda13(msgMgr7, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda11
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m168setOnParseListeners$lambda15(msgMgr8, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m169setOnParseListeners$lambda17(msgMgr9, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m170setOnParseListeners$lambda19(msgMgr10, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m171setOnParseListeners$lambda21(msgMgr11, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$17$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$17.m172setOnParseListeners$lambda23(msgMgr12, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m165setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_1");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[2]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[2]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m173setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_2");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[3]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[3]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m174setOnParseListeners$lambda5(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_3");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[4]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[4]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m175setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_4");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[5]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[5]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-9, reason: not valid java name */
    public static final void m176setOnParseListeners$lambda9(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_5");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[6]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[6]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m166setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_6");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[7]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[7]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-13, reason: not valid java name */
    public static final void m167setOnParseListeners$lambda13(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_limit_1");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[8]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[8]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-15, reason: not valid java name */
    public static final void m168setOnParseListeners$lambda15(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_limit_2");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[9]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[9]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-17, reason: not valid java name */
    public static final void m169setOnParseListeners$lambda17(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_limit_3");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[10]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[10]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-19, reason: not valid java name */
    public static final void m170setOnParseListeners$lambda19(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_limit_4");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[11]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[11]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-21, reason: not valid java name */
    public static final void m171setOnParseListeners$lambda21(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_limit_5");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[12]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[12]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-23, reason: not valid java name */
    public static final void m172setOnParseListeners$lambda23(MsgMgr this$0, MsgMgr$initParsers$1$17 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_limit_6");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[13]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[13]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            MsgMgr msgMgr = this.this$0;
            msgMgr.m0x3DDatas = (byte[]) msgMgr.mCanbusInfoByte.clone();
            this.list.clear();
            super.parse(dataLength);
            this.this$0.updateGeneralSettingData(this.list);
            this.this$0.updateSettingActivity(null);
        }
    }
}
