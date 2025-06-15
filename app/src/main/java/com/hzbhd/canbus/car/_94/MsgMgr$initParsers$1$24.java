package com.hzbhd.canbus.car._94;

import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;




public final class MsgMgr$initParsers$1$24 extends MsgMgr.Parser {
    private ArrayList<SettingUpdateEntity<Object>> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$24(MsgMgr msgMgr) {
        super(msgMgr, "【0x61】氛围灯状态");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$24$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$24.m977setOnParseListeners$lambda4(this.f$0, msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$24$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$24.m978setOnParseListeners$lambda7(msgMgr2, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m977setOnParseListeners$lambda4(MsgMgr$initParsers$1$24 this$0, MsgMgr this$1) {


        ArrayList<SettingUpdateEntity<Object>> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_94_self_adaption");
        if (settingUpdateEntity != null) {
            int i = (this$1.mCanbusInfoInt[2] >> 7) & 1;
            arrayList.add(settingUpdateEntity.setValue(i != 0 ? i != 1 ? "null_value" : "enable" : "disable"));
        }
        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this$1.mSettingItemIndexHashMap.get("_270_setting_12");
        if (settingUpdateEntity2 != null) {
            int i2 = this$1.mCanbusInfoInt[2] & WorkQueueKt.MASK;
            if (this$1.mDifferent != 13 && i2 != 0) {
                i2--;
            }
            arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf(i2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m978setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$24 this$1) {


        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_270_setting_13");
        if (settingUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[3];
            if (i % 5 == 0) {
                ArrayList<SettingUpdateEntity<Object>> arrayList = this$1.list;
                if (arrayList == null) {

                    arrayList = null;
                }
                int i2 = i / 5;
                arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(i2)).setProgress(i2));
            }
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            MsgMgr msgMgr = this.this$0;
            byte[] bArrCopyOf = Arrays.copyOf(msgMgr.mCanbusInfoByte, this.this$0.mCanbusInfoByte.length);

            msgMgr.m0x61DataRecord = bArrCopyOf;
            this.list = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr2 = this.this$0;
            ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
            if (arrayList == null) {

                arrayList = null;
            }
            msgMgr2.updateGeneralSettingData(arrayList);
            this.this$0.updateSettingActivity(null);
        }
    }
}
