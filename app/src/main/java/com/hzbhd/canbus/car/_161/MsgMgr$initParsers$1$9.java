package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr$initParsers$1$9 extends MsgMgr.Parser {
    private ArrayList<DriverUpdateEntity> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$9(MsgMgr msgMgr) {
        super(msgMgr, "【0x34】行车电脑信息 Page1");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        return new OnParseListener[]{null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m204setOnParseListeners$lambda3(msgMgr, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m205setOnParseListeners$lambda7(msgMgr2, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$9.m203setOnParseListeners$lambda11(msgMgr3, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m204setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$9 this$1) {
        String str;


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_55_0x17_data34");
        if (driverUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[3] | (this$0.mCanbusInfoInt[2] << 8);
            if (i == 65535) {
                str = "--";
            } else {
                boolean z = false;
                if (i >= 0 && i < 301) {
                    z = true;
                }
                if (!z) {
                    return;
                } else {
                    str = (i / 10) + " L/100KM";
                }
            }
            ArrayList<DriverUpdateEntity> arrayList = this$1.list;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m205setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$9 this$1) {
        String str;


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_speed");
        if (driverUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[5] | (this$0.mCanbusInfoInt[4] << 8);
            if (i == 65535) {
                str = "--";
            } else {
                boolean z = false;
                if (i >= 0 && i < 251) {
                    z = true;
                }
                if (!z) {
                    return;
                } else {
                    str = i + " KM/H";
                }
            }
            ArrayList<DriverUpdateEntity> arrayList = this$1.list;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m203setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$9 this$1) {
        String str;


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("accumulated_mileage");
        if (driverUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[7] | (this$0.mCanbusInfoInt[6] << 8);
            if (i == 65535) {
                str = "--";
            } else {
                boolean z = false;
                if (i >= 0 && i < 10000) {
                    z = true;
                }
                if (!z) {
                    return;
                } else {
                    str = i + " KM";
                }
            }
            ArrayList<DriverUpdateEntity> arrayList = this$1.list;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(str));
        }
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
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
}
