package com.hzbhd.canbus.car._329;

import com.hzbhd.canbus.car._329.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0015\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016¢\u0006\u0002\u0010\u000fR*\u0010\u0003\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"com/hzbhd/canbus/car/_329/MsgMgr$initParsers$1$13", "Lcom/hzbhd/canbus/car/_329/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_329/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_329_setting_11");
        if (settingUpdateEntity != null) {
            this$1.list.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[8])).setProgress(this$0.mCanbusInfoInt[8] - 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-16, reason: not valid java name */
    public static final void m665setOnParseListeners$lambda16(MsgMgr this$0, MsgMgr$initParsers$1$13 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
