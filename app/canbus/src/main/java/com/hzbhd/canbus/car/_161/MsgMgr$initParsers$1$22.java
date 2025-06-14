package com.hzbhd.canbus.car._161;

import android.content.Context;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0016¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"com/hzbhd/canbus/car/_161/MsgMgr$initParsers$1$22", "Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_161/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr$initParsers$1$22 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private ArrayList<DriverUpdateEntity> list;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$22(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x50】混动信息-能量流");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final Context context = this.$context;
        final MsgMgr msgMgr2 = this.this$0;
        final Context context2 = this.$context;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$22$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$22.m179setOnParseListeners$lambda3(msgMgr, context, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$22$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$22.m178setOnParseListeners$lambda25(msgMgr2, context2, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m179setOnParseListeners$lambda3(MsgMgr this$0, Context context, MsgMgr$initParsers$1$22 this$1) {
        String strByResId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_0");
        if (driverUpdateEntity != null) {
            switch (DataHandleUtils.getIntFromByteWithBit(this$0.mCanbusInfoInt[2], 4, 4)) {
                case 0:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_0");
                    break;
                case 1:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_1");
                    break;
                case 2:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_2");
                    break;
                case 3:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_3");
                    break;
                case 4:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_4");
                    break;
                case 5:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_5");
                    break;
                case 6:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_6");
                    break;
                case 7:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_7");
                    break;
                case 8:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_8");
                    break;
                case 9:
                    strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_0_0_9");
                    break;
                default:
                    return;
            }
            ArrayList<DriverUpdateEntity> arrayList = this$1.list;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            arrayList.add(driverUpdateEntity.setValue(strByResId));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-25, reason: not valid java name */
    public static final void m178setOnParseListeners$lambda25(MsgMgr this$0, Context context, MsgMgr$initParsers$1$22 this$1) {
        String strByResId;
        String strByResId2;
        String strByResId3;
        String strByResId4;
        String strByResId5;
        String strByResId6;
        String strByResId7;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_0");
        ArrayList<DriverUpdateEntity> arrayList = null;
        if (driverUpdateEntity != null) {
            if (DataHandleUtils.getBoolBit7(this$0.mCanbusInfoInt[3])) {
                strByResId7 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId7 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList2 = this$1.list;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList2 = null;
            }
            arrayList2.add(driverUpdateEntity.setValue(strByResId7));
        }
        DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_1");
        if (driverUpdateEntity2 != null) {
            if (DataHandleUtils.getBoolBit6(this$0.mCanbusInfoInt[3])) {
                strByResId6 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId6 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList3 = this$1.list;
            if (arrayList3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList3 = null;
            }
            arrayList3.add(driverUpdateEntity2.setValue(strByResId6));
        }
        DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_2");
        if (driverUpdateEntity3 != null) {
            if (DataHandleUtils.getBoolBit5(this$0.mCanbusInfoInt[3])) {
                strByResId5 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId5 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList4 = this$1.list;
            if (arrayList4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList4 = null;
            }
            arrayList4.add(driverUpdateEntity3.setValue(strByResId5));
        }
        DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_3");
        if (driverUpdateEntity4 != null) {
            if (DataHandleUtils.getBoolBit4(this$0.mCanbusInfoInt[3])) {
                strByResId4 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId4 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList5 = this$1.list;
            if (arrayList5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList5 = null;
            }
            arrayList5.add(driverUpdateEntity4.setValue(strByResId4));
        }
        DriverUpdateEntity driverUpdateEntity5 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_4");
        if (driverUpdateEntity5 != null) {
            if (DataHandleUtils.getBoolBit3(this$0.mCanbusInfoInt[3])) {
                strByResId3 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId3 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList6 = this$1.list;
            if (arrayList6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList6 = null;
            }
            arrayList6.add(driverUpdateEntity5.setValue(strByResId3));
        }
        DriverUpdateEntity driverUpdateEntity6 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_5");
        if (driverUpdateEntity6 != null) {
            if (DataHandleUtils.getBoolBit2(this$0.mCanbusInfoInt[3])) {
                strByResId2 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId2 = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList7 = this$1.list;
            if (arrayList7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList7 = null;
            }
            arrayList7.add(driverUpdateEntity6.setValue(strByResId2));
        }
        DriverUpdateEntity driverUpdateEntity7 = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_0_1_6");
        if (driverUpdateEntity7 != null) {
            if (DataHandleUtils.getBoolBit1(this$0.mCanbusInfoInt[3])) {
                strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_1");
            } else {
                strByResId = CommUtil.getStrByResId(context, "_161_driveInfo_0_3_0_0");
            }
            ArrayList<DriverUpdateEntity> arrayList8 = this$1.list;
            if (arrayList8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
            } else {
                arrayList = arrayList8;
            }
            arrayList.add(driverUpdateEntity7.setValue(strByResId));
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
