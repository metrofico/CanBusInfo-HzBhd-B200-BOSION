package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$16 extends MsgMgr.Parser {
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$16(MsgMgr msgMgr) {
        super(msgMgr, "【0x3B】记忆速度值");
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
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$16$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$16.m159setOnParseListeners$lambda6(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$16$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$16.m160setOnParseListeners$lambda8(msgMgr2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$16$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$16.m155setOnParseListeners$lambda10(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$16$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$16.m156setOnParseListeners$lambda12(msgMgr4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$16$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$16.m157setOnParseListeners$lambda14(msgMgr5, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$16$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$16.m158setOnParseListeners$lambda16(msgMgr6, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m159setOnParseListeners$lambda6(MsgMgr this$0, MsgMgr$initParsers$1$16 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_remember");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_1_enable");
        if (settingUpdateEntity2 != null) {
            this$1.list.add(settingUpdateEntity2.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_2_enable");
        if (settingUpdateEntity3 != null) {
            this$1.list.add(settingUpdateEntity3.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_3_enable");
        if (settingUpdateEntity4 != null) {
            this$1.list.add(settingUpdateEntity4.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_4_enable");
        if (settingUpdateEntity5 != null) {
            this$1.list.add(settingUpdateEntity5.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 3) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_5_enable");
        if (settingUpdateEntity6 != null) {
            this$1.list.add(settingUpdateEntity6.setValue(Integer.valueOf((this$0.mCanbusInfoInt[2] >> 2) & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-8, reason: not valid java name */
    public static final void m160setOnParseListeners$lambda8(MsgMgr this$0, MsgMgr$initParsers$1$16 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_remember_1");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[3]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[3]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-10, reason: not valid java name */
    public static final void m155setOnParseListeners$lambda10(MsgMgr this$0, MsgMgr$initParsers$1$16 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_remember_2");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[4]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[4]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-12, reason: not valid java name */
    public static final void m156setOnParseListeners$lambda12(MsgMgr this$0, MsgMgr$initParsers$1$16 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_remember_3");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[5]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[5]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-14, reason: not valid java name */
    public static final void m157setOnParseListeners$lambda14(MsgMgr this$0, MsgMgr$initParsers$1$16 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_remember_4");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[6]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[6]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-16, reason: not valid java name */
    public static final void m158setOnParseListeners$lambda16(MsgMgr this$0, MsgMgr$initParsers$1$16 this$1) {


        SettingUpdateEntity<Object> settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_speed_remember_5");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[7]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[7]);
            this$1.list.add(settingUpdateEntity);
        }
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            MsgMgr msgMgr = this.this$0;
            msgMgr.m0x3BDatas = (byte[]) msgMgr.mCanbusInfoByte.clone();
            this.list.clear();
            super.parse(dataLength);
            this.this$0.updateGeneralSettingData(this.list);
            this.this$0.updateSettingActivity(null);
        }
    }
}
