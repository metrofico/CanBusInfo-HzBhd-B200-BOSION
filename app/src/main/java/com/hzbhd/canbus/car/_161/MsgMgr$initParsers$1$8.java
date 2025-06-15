package com.hzbhd.canbus.car._161;

import android.util.Log;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;




public final class MsgMgr$initParsers$1$8 extends MsgMgr.Parser {
    private ArrayList<DriverUpdateEntity> list;
    final /* synthetic */ MsgMgr this$0;

    private final int range(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    private final int range(int i, int i2, int i3) {
        return i > i3 ? i3 : i < i2 ? i2 : i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$8(MsgMgr msgMgr) {
        super(msgMgr, "【0x33】行车电脑信息 Page0");
        this.this$0 = msgMgr;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        return new OnParseListener[]{null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m200setOnParseListeners$lambda3(msgMgr, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m201setOnParseListeners$lambda7(msgMgr2, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m197setOnParseListeners$lambda11(msgMgr3, this);
            }
        }, null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m198setOnParseListeners$lambda13(msgMgr4, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$8$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$8.m199setOnParseListeners$lambda17(msgMgr5, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m200setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$8 this$1) {
        String str;


        Log.d("_18_current_fuel", "----------------");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_18_current_fuel");
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
    public static final void m201setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$8 this$1) {
        String str;


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_186_endurancee_mileage");
        if (driverUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[5] | (this$0.mCanbusInfoInt[4] << 8);
            if (i == 65535) {
                str = "--";
            } else {
                boolean z = false;
                if (i >= 0 && i < 2001) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m197setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$8 this$1) {
        String str;


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("cat_set_mileage");
        if (driverUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[7] | (this$0.mCanbusInfoInt[6] << 8);
            if (i == 65535) {
                str = "--";
            } else {
                boolean z = false;
                if (i >= 0 && i < 6001) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-13, reason: not valid java name */
    public static final void m198setOnParseListeners$lambda13(MsgMgr this$0, MsgMgr$initParsers$1$8 this$1) {


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_start_stop_timing");
        if (driverUpdateEntity != null) {
            if (this$0.mCanbusInfoInt[10] == 255 || this$0.mCanbusInfoInt[9] == 255 || this$0.mCanbusInfoInt[8] == 255) {
                driverUpdateEntity.setValue("--");
            } else {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%d : %02d : %02d", Arrays.copyOf(new Object[]{Integer.valueOf(this$0.mCanbusInfoInt[8]), Integer.valueOf(this$1.range(this$0.mCanbusInfoInt[9], 59)), Integer.valueOf(this$1.range(this$0.mCanbusInfoInt[10], 59))}, 3));

                driverUpdateEntity.setValue(str);
            }
            ArrayList<DriverUpdateEntity> arrayList = this$1.list;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-17, reason: not valid java name */
    public static final void m199setOnParseListeners$lambda17(MsgMgr this$0, MsgMgr$initParsers$1$8 this$1) {


        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_CurrentSpeed");
        if (driverUpdateEntity != null) {
            int i = this$0.mCanbusInfoInt[12] | (this$0.mCanbusInfoInt[11] << 8);
            if (i != 65535) {
                boolean z = false;
                if (i >= 0 && i < 65536) {
                    z = true;
                }
                if (!z) {
                    return;
                } else {
                    String str = i + " km/h";
                }
            }
            ArrayList<DriverUpdateEntity> arrayList = this$1.list;
            if (arrayList == null) {

                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue((i / 100) + " km/h"));
            Log.d("Lai", String.valueOf(i));
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
