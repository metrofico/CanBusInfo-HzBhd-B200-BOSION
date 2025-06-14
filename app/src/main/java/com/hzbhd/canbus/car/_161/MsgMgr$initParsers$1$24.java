package com.hzbhd.canbus.car._161;

import android.content.Context;
import com.hzbhd.canbus.car._161.MsgMgr;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0015\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"com/hzbhd/canbus/car/_161/MsgMgr$initParsers$1$24", "Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_161/MsgMgr;", "list", "Ljava/util/ArrayList;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "list1", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "parse", "", "dataLength", "", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr$initParsers$1$24 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private ArrayList<DriverUpdateEntity> list;
    private ArrayList<SettingUpdateEntity<Object>> list1;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$24(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x52】混动信息-充电");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final Context context = this.$context;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$24$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$24.m181setOnParseListeners$lambda3(msgMgr, this, context);
            }
        }, null};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m181setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$24 this$1, Context context) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Intrinsics.checkNotNullParameter(context, "$context");
        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this$0.mDriveItemIndexHashMap.get("_161_driveInfo_0_2_0");
        ArrayList<SettingUpdateEntity<Object>> arrayList = null;
        if (driverUpdateEntity != null) {
            String str = (((this$0.mCanbusInfoInt[2] * 256) + this$0.mCanbusInfoInt[3]) / 60) + " : " + (((this$0.mCanbusInfoInt[2] * 256) + this$0.mCanbusInfoInt[3]) % 60);
            ArrayList<DriverUpdateEntity> arrayList2 = this$1.list;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList2 = null;
            }
            arrayList2.add(driverUpdateEntity.setValue(str));
        }
        SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this$0.mSettingItemIndexHashMap.get("_161_Delay_Charge");
        if (settingUpdateEntity != null) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str2 = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(MsgMgrKt.range(((this$0.mCanbusInfoInt[2] * 256) + this$0.mCanbusInfoInt[3]) % 60, 59))}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str3 = String.format("%2d", Arrays.copyOf(new Object[]{Integer.valueOf(MsgMgrKt.range(((this$0.mCanbusInfoInt[2] * 256) + this$0.mCanbusInfoInt[3]) / 60, 23))}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
            settingUpdateEntity.setValue(str3 + CommUtil.getStrByResId(context, "_161_driveInfo_0_2_1") + str2 + CommUtil.getStrByResId(context, "_161_driveInfo_0_2_2"));
            settingUpdateEntity.setValueStr(true);
            ArrayList<SettingUpdateEntity<Object>> arrayList3 = this$1.list1;
            if (arrayList3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list1");
            } else {
                arrayList = arrayList3;
            }
            arrayList.add(settingUpdateEntity.setValue(settingUpdateEntity.getValue()));
        }
    }

    @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            this.list = new ArrayList<>();
            this.list1 = new ArrayList<>();
            super.parse(dataLength);
            MsgMgr msgMgr = this.this$0;
            ArrayList<DriverUpdateEntity> arrayList = this.list;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                arrayList = null;
            }
            msgMgr.updateGeneralDriveData(arrayList);
            this.this$0.updateDriveDataActivity(null);
            MsgMgr msgMgr2 = this.this$0;
            ArrayList<SettingUpdateEntity<Object>> arrayList2 = this.list1;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list1");
                arrayList2 = null;
            }
            msgMgr2.updateGeneralSettingData(arrayList2);
            this.this$0.updateSettingActivity(null);
        }
    }
}
