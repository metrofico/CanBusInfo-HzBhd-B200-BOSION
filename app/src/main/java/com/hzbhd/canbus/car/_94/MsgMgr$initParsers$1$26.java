package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000G\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0018\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\rH\u0016J\u0015\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015H\u0016¢\u0006\u0002\u0010\u0017R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0004j\b\u0012\u0004\u0012\u00020\b`\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$26", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "driveUpdateList", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "Lkotlin/collections/ArrayList;", "tireUpdateList", "Lcom/hzbhd/canbus/entity/TireUpdateEntity;", "getState", "", "kotlin.jvm.PlatformType", "value", "", "getTemperature", "getTireStatus", "getTirepress", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$26 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private ArrayList<DriverUpdateEntity> driveUpdateList;
    final /* synthetic */ MsgMgr this$0;
    private ArrayList<TireUpdateEntity> tireUpdateList;

    private final int getTireStatus(int value) {
        switch (value) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$26(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x63】车辆信息");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final MsgMgr msgMgr2 = this.this$0;
        final MsgMgr msgMgr3 = this.this$0;
        final MsgMgr msgMgr4 = this.this$0;
        final MsgMgr msgMgr5 = this.this$0;
        final MsgMgr msgMgr6 = this.this$0;
        return new OnParseListener[]{null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$26$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$26.m988setOnParseListeners$lambda1(msgMgr, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$26$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$26.m992setOnParseListeners$lambda4(msgMgr2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$26$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$26.m993setOnParseListeners$lambda7(msgMgr3, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$26$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$26.m989setOnParseListeners$lambda10(msgMgr4, this);
            }
        }, null, null, null, null, null, null, null, null, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$26$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$26.m990setOnParseListeners$lambda12(msgMgr5, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$26$$ExternalSyntheticLambda5
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$26.m991setOnParseListeners$lambda14(msgMgr6, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-1, reason: not valid java name */
    public static final void m988setOnParseListeners$lambda1(MsgMgr this$0, MsgMgr$initParsers$1$26 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_306_value_20");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue((this$0.mCanbusInfoInt[4] | (((this$0.mCanbusInfoInt[2] << 8) | this$0.mCanbusInfoInt[3]) << 8)) + " KM"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m992setOnParseListeners$lambda4(MsgMgr this$0, MsgMgr$initParsers$1$26 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_306_value_21");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                arrayList = null;
            }
            int i = this$0.mCanbusInfoInt[6] | (this$0.mCanbusInfoInt[5] << 8);
            arrayList.add(driverUpdateEntity.setValue(i > 999 ? "--" : i + " KM"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-7, reason: not valid java name */
    public static final void m993setOnParseListeners$lambda7(MsgMgr this$0, MsgMgr$initParsers$1$26 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_283_meter_value_5");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                arrayList = null;
            }
            float f = ((this$0.mCanbusInfoInt[7] * 2) + 1) / 10;
            arrayList.add(driverUpdateEntity.setValue(f > 30.0f ? "--" : f + " L/100km"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-10, reason: not valid java name */
    public static final void m989setOnParseListeners$lambda10(MsgMgr this$0, MsgMgr$initParsers$1$26 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("remaining_oil");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                arrayList = null;
            }
            float f = this$0.mCanbusInfoInt[8] / 2;
            arrayList.add(driverUpdateEntity.setValue(f > 100.0f ? "--" : new StringBuilder().append(f).append('%').toString()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-12, reason: not valid java name */
    public static final void m990setOnParseListeners$lambda12(MsgMgr this$0, MsgMgr$initParsers$1$26 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int[] iArr = this$0.mCanbusInfoInt;
        this$1.tireUpdateList = CollectionsKt.arrayListOf(new TireUpdateEntity(0, this$1.getTireStatus((iArr[17] >> 4) & 15), new String[]{this$1.getTirepress(iArr[9]), this$1.getTemperature(iArr[13]), this$1.getState((iArr[17] >> 4) & 15)}), new TireUpdateEntity(1, this$1.getTireStatus(iArr[17] & 15), new String[]{this$1.getTirepress(iArr[10]), this$1.getTemperature(iArr[14]), this$1.getState(iArr[17] & 15)}), new TireUpdateEntity(2, this$1.getTireStatus((iArr[18] >> 4) & 15), new String[]{this$1.getTirepress(iArr[11]), this$1.getTemperature(iArr[15]), this$1.getState((iArr[18] >> 4) & 15)}), new TireUpdateEntity(3, this$1.getTireStatus(iArr[18] & 15), new String[]{this$1.getTirepress(iArr[12]), this$1.getTemperature(iArr[16]), this$1.getState(iArr[18] & 15)}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-14, reason: not valid java name */
    public static final void m991setOnParseListeners$lambda14(MsgMgr this$0, MsgMgr$initParsers$1$26 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_94_oil_life");
        if (driverUpdateEntity != null) {
            ArrayList<DriverUpdateEntity> arrayList = this$1.driveUpdateList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(new StringBuilder().append(this$0.mCanbusInfoInt[19]).append('%').toString()));
        }
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            GeneralTireData.isHaveSpareTire = false;
            this.driveUpdateList = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<DriverUpdateEntity> arrayList = this.driveUpdateList;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("driveUpdateList");
                arrayList = null;
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
            ArrayList<TireUpdateEntity> arrayList2 = this.tireUpdateList;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tireUpdateList");
                arrayList2 = null;
            }
            GeneralTireData.dataList = arrayList2;
            this.this$0.updateTirePressureActivity(null);
        }
    }

    private final String getTirepress(int value) {
        if (value == 255) {
            return "--";
        }
        double d = 10;
        return (Math.floor((value * 2.75d) * d) / d) + " KPA";
    }

    private final String getTemperature(int value) {
        return value == 255 ? "--" : (value - 60) + " ℃";
    }

    private final String getState(int value) {
        switch (value) {
            case 0:
                return CommUtil.getStrByResId(this.$context, "_253_normal");
            case 1:
                return CommUtil.getStrByResId(this.$context, "high_pressure_warning");
            case 2:
                return CommUtil.getStrByResId(this.$context, "low_pressure_warning");
            case 3:
                return CommUtil.getStrByResId(this.$context, "quick_leak");
            case 4:
                return CommUtil.getStrByResId(this.$context, "loss_signal");
            case 5:
                return CommUtil.getStrByResId(this.$context, "low_sensor_power");
            case 6:
                return CommUtil.getStrByResId(this.$context, "sensor_error");
            case 7:
                return CommUtil.getStrByResId(this.$context, "low_pressure_and_leak");
            default:
                return "ERROR";
        }
    }
}
