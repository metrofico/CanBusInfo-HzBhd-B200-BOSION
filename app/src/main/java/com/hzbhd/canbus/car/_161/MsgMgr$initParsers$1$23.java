package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$23 extends MsgMgr.Parser {
    private ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$23(MsgMgr msgMgr) {
        super(msgMgr, "【0x51】混动信息-统计");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        return new OnParseListener[]{null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$23$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$23.m180setOnParseListeners$lambda4(msgMgr, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m180setOnParseListeners$lambda4(MsgMgr this$0, MsgMgr$initParsers$1$23 this$1) {


        int i = this$0.mCanbusInfoInt[3];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_Time_Interval");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(this$0.mCanbusInfoInt[3])));
        }
        ArrayList<SettingUpdateEntity<Object>> arrayList2 = this$1.list;
        if (arrayList2 == null) {

            arrayList2 = null;
        }
        this$0.updateGeneralSettingData(arrayList2);
        this$0.updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
            if (arrayList == null) {

                arrayList = null;
            }
            msgMgr.updateGeneralSettingData(arrayList);
            this.this$0.updateSettingActivity(null);
        }
    }
}
