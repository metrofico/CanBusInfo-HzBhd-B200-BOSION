package com.hzbhd.canbus.car._161;

import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"com/hzbhd/canbus/car/_161/MsgMgr$initParsers$1$9", "Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_161/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m205setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$9 this$1) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-11, reason: not valid java name */
    public static final void m203setOnParseListeners$lambda11(MsgMgr this$0, MsgMgr$initParsers$1$9 this$1) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
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
                Intrinsics.throwUninitializedPropertyAccessException("list");
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
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
        }
    }
}
