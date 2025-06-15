package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$7 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private ArrayList<DriverUpdateEntity> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$7(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x25】雷达状态");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final Context context = this.$context;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m1052setOnParseListeners$lambda3(this.f$0, msgMgr);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$7$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$7.m1053setOnParseListeners$lambda6(this.f$0, msgMgr2, context);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m1052setOnParseListeners$lambda3(MsgMgr$initParsers$1$7 this$0, MsgMgr this$1) {


        ArrayList<DriverUpdateEntity> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$1.mDriveItemIndexHashMap.get("_41_rear_radar");
        if (driverUpdateEntity != null) {
            arrayList.add(driverUpdateEntity.setValue(this$0.getOpenOrClose((this$1.mCanbusInfoInt[2] >> 3) & 1)));
        }
        DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this$1.mDriveItemIndexHashMap.get("_41_front_radar");
        if (driverUpdateEntity2 != null) {
            arrayList.add(driverUpdateEntity2.setValue(this$0.getOpenOrClose((this$1.mCanbusInfoInt[2] >> 2) & 1)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-6, reason: not valid java name */
    public static final void m1053setOnParseListeners$lambda6(MsgMgr$initParsers$1$7 this$0, MsgMgr this$1, Context context) {



        ArrayList<DriverUpdateEntity> arrayList = this$0.list;
        if (arrayList == null) {

            arrayList = null;
        }
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$1.mDriveItemIndexHashMap.get("_94_radar_status");
        if (driverUpdateEntity != null) {
            int i = this$1.mCanbusInfoInt[3];
            arrayList.add(driverUpdateEntity.setValue(CommUtil.getStrByResId(context, i != 0 ? i != 1 ? i != 2 ? i != 3 ? "null_value" : "_94_rear_radar_fault" : "_94_front_radar_fault" : "_94_external_radar_fault" : "_253_normal")));
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<DriverUpdateEntity> arrayList = this.list;
            if (arrayList == null) {

                arrayList = null;
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
        }
    }

    private final String getOpenOrClose(int value) {
        String strByResId = CommUtil.getStrByResId(this.$context, value != 0 ? value != 1 ? "null_value" : "open" : "close");

        return strByResId;
    }
}
