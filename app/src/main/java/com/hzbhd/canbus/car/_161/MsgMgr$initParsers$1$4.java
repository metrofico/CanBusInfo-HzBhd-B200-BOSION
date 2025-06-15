package com.hzbhd.canbus.car._161;

import android.content.Context;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MsgMgr$initParsers$1$4 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private final ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$4(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x22】空调预设状态");
        this.this$0 = msgMgr;
        this.$context = context;
        this.list = new ArrayList<>();
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final Context context = this.$context;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$4$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$4.m193setOnParseListeners$lambda11(msgMgr, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$4$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$4.m194setOnParseListeners$lambda16(msgMgr2, this, context);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m193setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$4 this$1) {


        int i = this$0.mCanbusInfoInt[2];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        HashMap map = this$0.mSettingItemIndexHashMap;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get("_161_Preset");
        if (settingUpdateEntity != null) {
            arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 7) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) map.get("_161_Monday");
        if (settingUpdateEntity2 != null) {
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 6) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) map.get("_161_Tuesday");
        if (settingUpdateEntity3 != null) {
            arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 5) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) map.get("_161_Wednesday");
        if (settingUpdateEntity4 != null) {
            arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 4) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) map.get("_161_Thursday");
        if (settingUpdateEntity5 != null) {
            arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf((i >> 3) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) map.get("_161_Friday");
        if (settingUpdateEntity6 != null) {
            arrayList.add(settingUpdateEntity6.setValue(Integer.valueOf((i >> 2) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity7 = (SettingUpdateEntity) map.get("_161_Saturday");
        if (settingUpdateEntity7 != null) {
            arrayList.add(settingUpdateEntity7.setValue(Integer.valueOf((i >> 1) & 1)));
        }
        SettingUpdateEntity settingUpdateEntity8 = (SettingUpdateEntity) map.get("_161_Sunday");
        if (settingUpdateEntity8 != null) {
            arrayList.add(settingUpdateEntity8.setValue(Integer.valueOf((i >> 0) & 1)));
        }
        this$0.updateGeneralSettingData(this$1.list);
        this$0.updateSettingActivity(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-16, reason: not valid java name */
    public static final void m194setOnParseListeners$lambda16(MsgMgr this$0, MsgMgr$initParsers$1$4 this$1, Context context) {



        int i = this$0.mCanbusInfoInt[3];
        ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_Time");
        if (settingUpdateEntity != null) {
            int i2 = (this$0.mCanbusInfoInt[3] * 256) + this$0.mCanbusInfoInt[4];
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%2d", Arrays.copyOf(new Object[]{Integer.valueOf(MsgMgrKt.range(i2 / 60, 23))}, 1));

            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(MsgMgrKt.range(i2 % 60, 59))}, 1));

            settingUpdateEntity.setValue(str + CommUtil.getStrByResId(context, "_161_driveInfo_0_2_1") + str2 + CommUtil.getStrByResId(context, "_161_driveInfo_0_2_2"));
            settingUpdateEntity.setValueStr(true);
            arrayList.add(settingUpdateEntity.setValue(settingUpdateEntity.getValue()));
        }
        this$0.updateGeneralSettingData(this$1.list);
        this$0.updateSettingActivity(null);
    }
}
