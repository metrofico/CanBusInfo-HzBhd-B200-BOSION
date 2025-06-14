package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0015\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0016¢\u0006\u0002\u0010\u000bR*\u0010\u0003\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"com/hzbhd/canbus/car/_161/MsgMgr$initParsers$1$1", "Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_161/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "Lkotlin/collections/ArrayList;", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr$initParsers$1$1 extends MsgMgr.Parser {
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$1(MsgMgr msgMgr) {
        super(msgMgr, "【0x01】灯光命令");
        this.this$0 = msgMgr;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        return new OnParseListener[]{null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$1$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$1.m137setOnParseListeners$lambda1(msgMgr, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m137setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$1 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int i = this$0.mCanbusInfoInt[4];
        if (1 <= i && i < 4) {
            this$0.setBacklightLevel(1);
        }
        int i2 = this$0.mCanbusInfoInt[4];
        if (4 <= i2 && i2 < 7) {
            this$0.setBacklightLevel(2);
        }
        int i3 = this$0.mCanbusInfoInt[4];
        if (7 <= i3 && i3 < 10) {
            this$0.setBacklightLevel(3);
        }
        int i4 = this$0.mCanbusInfoInt[4];
        if (10 <= i4 && i4 < 13) {
            this$0.setBacklightLevel(4);
        }
        int i5 = this$0.mCanbusInfoInt[4];
        if (13 <= i5 && i5 < 16) {
            this$0.setBacklightLevel(5);
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("light_info");
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[4]));
            settingUpdateEntity.setProgress(this$0.mCanbusInfoInt[4]);
            this$1.list.add(settingUpdateEntity.setValue(settingUpdateEntity.getValue()));
            this$0.updateGeneralSettingData(this$1.list);
            this$0.updateSettingActivity(null);
        }
    }
}
