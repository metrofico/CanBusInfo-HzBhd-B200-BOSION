package com.hzbhd.canbus.car._94;

import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




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


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_cockpit_fresh_air");
        if (settingUpdateEntity != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
            if (arrayList == null) {

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


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_94_pm_2_5_in_vehicle");
        if (settingUpdateEntity != null) {
            ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
            if (arrayList == null) {

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

                arrayList = null;
            }
            msgMgr.updateGeneralSettingData(arrayList);
            this.this$0.updateSettingActivity(null);
        }
    }
}
