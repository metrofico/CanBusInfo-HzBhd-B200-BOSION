package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




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
