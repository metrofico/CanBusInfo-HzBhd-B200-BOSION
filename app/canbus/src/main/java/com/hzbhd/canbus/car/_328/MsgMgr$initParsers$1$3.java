package com.hzbhd.canbus.car._328;

import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.car._328.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J-\u0010\u000b\u001a\u00020\n2\n\u0010\f\u001a\u00020\r\"\u00020\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\f\u0010\u0015\u001a\u00020\u000e*\u00020\rH\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"com/hzbhd/canbus/car/_328/MsgMgr$initParsers$1$3", "Lcom/hzbhd/canbus/car/_328/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_328/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "Lkotlin/collections/ArrayList;", "unitArray", "Landroid/util/SparseArray;", "Lkotlin/Pair;", "", "getResult", "params", "", "", "result", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "parse", "", "dataLength", "toInteger", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$3 extends MsgMgr.Parser {
    private final ArrayList<DriverUpdateEntity> list;
    final /* synthetic */ MsgMgr this$0;
    private final SparseArray<Pair<String, String>> unitArray;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$3(MsgMgr msgMgr) {
        super(msgMgr, "【0x40】油耗信息");
        this.this$0 = msgMgr;
        this.list = new ArrayList<>();
        SparseArray<Pair<String, String>> sparseArray = new SparseArray<>();
        sparseArray.put(0, new Pair<>("km", "km/L"));
        sparseArray.append(1, new Pair<>("km", "L/100km"));
        sparseArray.append(2, new Pair<>("mile", "mpg(US)"));
        sparseArray.append(3, new Pair<>("mile", "mpg(UK)"));
        this.unitArray = sparseArray;
    }

    @Override // com.hzbhd.canbus.car._328.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            final Pair<String, String> pair = this.unitArray.get(this.this$0.mCanbusInfoInt[2]);
            MsgMgr msgMgr = this.this$0;
            ArrayList<DriverUpdateEntity> arrayList = this.list;
            arrayList.clear();
            int[] iArr = msgMgr.mCanbusInfoInt;
            DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) msgMgr.mDriveItemIndexHashMap.get("_207_data_3");
            if (driverUpdateEntity != null) {
                arrayList.add(driverUpdateEntity.setValue(getResult(new int[]{iArr[4], iArr[3]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$3$parse$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(int[] getResult) {
                        Intrinsics.checkNotNullParameter(getResult, "$this$getResult");
                        return this.this$0.toInteger(getResult) + ' ' + pair.getFirst();
                    }
                })));
            }
            DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) msgMgr.mDriveItemIndexHashMap.get("_18_current_fuel");
            if (driverUpdateEntity2 != null) {
                arrayList.add(driverUpdateEntity2.setValue(getResult(new int[]{iArr[6], iArr[5]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$3$parse$1$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(int[] getResult) {
                        Intrinsics.checkNotNullParameter(getResult, "$this$getResult");
                        return (this.this$0.toInteger(getResult) / 10.0f) + ' ' + pair.getSecond();
                    }
                })));
            }
            DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) msgMgr.mDriveItemIndexHashMap.get("_328_avg_fuel_cons_auto");
            if (driverUpdateEntity3 != null) {
                arrayList.add(driverUpdateEntity3.setValue(getResult(new int[]{iArr[8], iArr[7]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$3$parse$1$1$3$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(int[] getResult) {
                        Intrinsics.checkNotNullParameter(getResult, "$this$getResult");
                        return (this.this$0.toInteger(getResult) / 10.0f) + ' ' + pair.getSecond();
                    }
                })));
            }
            DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) msgMgr.mDriveItemIndexHashMap.get("_328_avg_fuel_cons_mual");
            if (driverUpdateEntity4 != null) {
                arrayList.add(driverUpdateEntity4.setValue(getResult(new int[]{iArr[10], iArr[9]}, (Function1) new Function1<int[], String>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$3$parse$1$1$4$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final String invoke(int[] getResult) {
                        Intrinsics.checkNotNullParameter(getResult, "$this$getResult");
                        return (this.this$0.toInteger(getResult) / 10.0f) + ' ' + pair.getSecond();
                    }
                })));
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toInteger(int[] iArr) {
        Log.i(com.hzbhd.canbus.car._112.MsgMgr.TAG, "toInteger: " + iArr.length);
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            i2 |= iArr[i] << (i3 * 8);
            i++;
            i3++;
        }
        return i2;
    }

    private final String getResult(int[] params, Function1<? super int[], String> result) {
        for (int i : params) {
            if (i != 255) {
                return result.invoke(params);
            }
        }
        return "--";
    }
}
