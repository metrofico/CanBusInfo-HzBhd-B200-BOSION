package com.hzbhd.canbus.car._94;

import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0015\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016¢\u0006\u0002\u0010\u000fR*\u0010\u0003\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$12", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$12 extends MsgMgr.Parser {
    private ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$12(MsgMgr msgMgr) {
        super(msgMgr, "【0x2A】座舱馨风");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m953setOnParseListeners$lambda1(msgMgr, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$12$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$12.m954setOnParseListeners$lambda3(msgMgr2, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m953setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$12 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_cockpit_fresh_air");
        if (settingUpdateEntity != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            int i = this$0.mCanbusInfoInt[2];
            arrayList.add(settingUpdateEntity.setValue(i != 0 ? i != 1 ? "null_value" : "disable" : "enable"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m954setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$12 this$1) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_pm_2_5_in_vehicle");
        if (settingUpdateEntity != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            int i = this$0.mCanbusInfoInt[4] | (this$0.mCanbusInfoInt[3] << 8);
            if (i >= 0 && i < 36) {
                str = "pm_excellent";
            } else {
                if (36 <= i && i < 76) {
                    str = "pm_good";
                } else {
                    if (76 <= i && i < 116) {
                        str = "pm_mild_pollution";
                    } else {
                        if (116 <= i && i < 151) {
                            str = "pm_moderately_polluted";
                        } else {
                            if (151 <= i && i < 251) {
                                str = "pm_heavy_pollution";
                            } else {
                                str = 251 <= i && i < 510 ? "pm_severe_pollution" : "set_default";
                            }
                        }
                    }
                }
            }
            arrayList.add(settingUpdateEntity.setValue(str));
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            msgMgr.updateGeneralSettingData(arrayList);
            this.this$0.updateSettingActivity(null);
        }
    }
}
