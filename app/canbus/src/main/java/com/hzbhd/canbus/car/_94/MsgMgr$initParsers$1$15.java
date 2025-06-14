package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.util.CommUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u0015\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011H\u0016¢\u0006\u0002\u0010\u0013R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$15", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "hybridUpdateList", "", "", "getHybridUpdateList", "()Ljava/util/List;", "setHybridUpdateList", "(Ljava/util/List;)V", "getValue", "value", "", "parse", "", "dataLength", "setOnParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$15 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    private List<String> hybridUpdateList;
    final /* synthetic */ MsgMgr this$0;

    private final String getValue(int value) {
        switch (value) {
            case 1:
                return "驱动力需求";
            case 2:
                return "速度过快";
            case 3:
                return "加热器需求";
            case 4:
                return "空挡";
            case 5:
                return "引擎温度过低";
            case 6:
                return "蓄电池充电中";
            case 7:
                return "低速档";
            case 8:
                return "正常运行";
            case 9:
                return "低利用率";
            case 10:
                return "燃油保养";
            case 11:
                return "引擎制动启动";
            case 12:
                return "蓄电池温度";
            default:
                return "无";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$15(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x32】整车能量状态");
        this.this$0 = msgMgr;
        this.$context = context;
        this.hybridUpdateList = CollectionsKt.mutableListOf("-", "-", "-", "-", "-");
    }

    public final List<String> getHybridUpdateList() {
        return this.hybridUpdateList;
    }

    public final void setHybridUpdateList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.hybridUpdateList = list;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public OnParseListener[] setOnParseListeners() {
        final MsgMgr msgMgr = this.this$0;
        final Context context = this.$context;
        final MsgMgr msgMgr2 = this.this$0;
        final Context context2 = this.$context;
        final MsgMgr msgMgr3 = this.this$0;
        final Context context3 = this.$context;
        final MsgMgr msgMgr4 = this.this$0;
        final Context context4 = this.$context;
        final MsgMgr msgMgr5 = this.this$0;
        final Context context5 = this.$context;
        return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$15$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$15.m959setOnParseListeners$lambda0(msgMgr, context, this);
            }
        }, null, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$15$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$15.m960setOnParseListeners$lambda2(msgMgr2, context2, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$15$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$15.m961setOnParseListeners$lambda3(msgMgr3, this, context3);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$15$$ExternalSyntheticLambda3
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$15.m962setOnParseListeners$lambda4(msgMgr4, context4, this);
            }
        }, new OnParseListener() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$15$$ExternalSyntheticLambda4
            @Override // com.hzbhd.canbus.interfaces.OnParseListener
            public final void onParse() {
                MsgMgr$initParsers$1$15.m963setOnParseListeners$lambda5(msgMgr5, context5, this);
            }
        }};
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-0, reason: not valid java name */
    public static final void m959setOnParseListeners$lambda0(MsgMgr this$0, Context context, MsgMgr$initParsers$1$15 this$1) {
        String strByResId;
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        switch (this$0.mCanbusInfoInt[2]) {
            case 1:
                strByResId = CommUtil.getStrByResId(context, "_94_values_18");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_18\")");
                break;
            case 2:
                strByResId = CommUtil.getStrByResId(context, "_94_values_19");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_19\")");
                break;
            case 3:
                strByResId = CommUtil.getStrByResId(context, "_94_values_20");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_20\")");
                break;
            case 4:
                strByResId = CommUtil.getStrByResId(context, "_94_values_21");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_21\")");
                break;
            case 5:
                strByResId = CommUtil.getStrByResId(context, "_94_values_22");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_22\")");
                break;
            case 6:
                strByResId = CommUtil.getStrByResId(context, "_94_values_23");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_23\")");
                break;
            case 7:
                strByResId = CommUtil.getStrByResId(context, "_94_values_24");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_24\")");
                break;
            case 8:
                strByResId = CommUtil.getStrByResId(context, "_94_values_25");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_25\")");
                break;
            case 9:
                strByResId = CommUtil.getStrByResId(context, "_94_values_26");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_26\")");
                break;
            case 10:
                strByResId = CommUtil.getStrByResId(context, "_94_values_27");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_27\")");
                break;
            default:
                strByResId = CommUtil.getStrByResId(context, "_94_values_17");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_17\")");
                break;
        }
        this$1.hybridUpdateList.set(0, CommUtil.getStrByResId(context, "_94_values_40") + ':' + strByResId);
        StringBuilder sbAppend = new StringBuilder().append("【0x32】整车能量状态  状态  {");
        switch (this$0.mCanbusInfoInt[2]) {
            case 1:
                str = "混合驱动";
                break;
            case 2:
                str = "正在为高压蓄电池充电";
                break;
            case 3:
                str = "怠速";
                break;
            case 4:
                str = "怠速充电";
                break;
            case 5:
                str = "电力驱动";
                break;
            case 6:
                str = "引擎驱动";
                break;
            case 7:
                str = "已遥控启动";
                break;
            case 8:
                str = "充电完成";
                break;
            case 9:
                str = "快速充电完成";
                break;
            case 10:
                str = "正在快速充电";
                break;
            default:
                str = "无";
                break;
        }
        Log.i(MsgMgr.TAG, sbAppend.append(str).append('}').toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-2, reason: not valid java name */
    public static final void m960setOnParseListeners$lambda2(MsgMgr this$0, Context context, MsgMgr$initParsers$1$15 this$1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        int i = (this$0.mCanbusInfoInt[3] << 8) | this$0.mCanbusInfoInt[4];
        this$1.hybridUpdateList.set(1, CommUtil.getStrByResId(context, "_94_values_41") + ':' + ((this$0.getMsbLsbResult(this$0.mCanbusInfoInt[3], this$0.mCanbusInfoInt[4]) / 10.0f) + CommUtil.getStrByResId(context, "hour")));
        Log.i(MsgMgr.TAG, "setOnParseListeners: 【0x32】整车能量状态  充满电所需时间  {" + (i / 10) + '.' + (i % 10) + "小时}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-3, reason: not valid java name */
    public static final void m961setOnParseListeners$lambda3(MsgMgr this$0, MsgMgr$initParsers$1$15 this$1, Context context) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Intrinsics.checkNotNullParameter(context, "$context");
        this$1.hybridUpdateList.set(2, CommUtil.getStrByResId(context, "_94_values_42") + ':' + new StringBuilder().append(this$0.mCanbusInfoInt[5]).append('%').toString());
        GeneralHybirdData.powerBatteryValue = this$0.mCanbusInfoInt[5] / 10;
        GeneralHybirdData.powerBatteryLevel = this$0.mCanbusInfoInt[5] / 10;
        Log.i(MsgMgr.TAG, "setOnParseListeners: 【0x32】整车能量状态  电池能量  {" + this$0.mCanbusInfoInt[5] + "%}");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-4, reason: not valid java name */
    public static final void m962setOnParseListeners$lambda4(MsgMgr this$0, Context context, MsgMgr$initParsers$1$15 this$1) {
        String strByResId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        switch (this$0.mCanbusInfoInt[6]) {
            case 1:
                strByResId = CommUtil.getStrByResId(context, "_94_values_28");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_28\")");
                break;
            case 2:
                strByResId = CommUtil.getStrByResId(context, "_94_values_29");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_29\")");
                break;
            case 3:
                strByResId = CommUtil.getStrByResId(context, "_94_values_30");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_30\")");
                break;
            case 4:
                strByResId = CommUtil.getStrByResId(context, "_94_values_31");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_31\")");
                break;
            case 5:
                strByResId = CommUtil.getStrByResId(context, "_94_values_32");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_32\")");
                break;
            case 6:
                strByResId = CommUtil.getStrByResId(context, "_94_values_33");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_33\")");
                break;
            case 7:
                strByResId = CommUtil.getStrByResId(context, "_94_values_34");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_34\")");
                break;
            case 8:
                strByResId = CommUtil.getStrByResId(context, "_94_values_35");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_35\")");
                break;
            case 9:
                strByResId = CommUtil.getStrByResId(context, "_94_values_36");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_36\")");
                break;
            case 10:
                strByResId = CommUtil.getStrByResId(context, "_94_values_37");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_37\")");
                break;
            case 11:
                strByResId = CommUtil.getStrByResId(context, "_94_values_38");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_38\")");
                break;
            case 12:
                strByResId = CommUtil.getStrByResId(context, "_94_values_39");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_39\")");
                break;
            default:
                strByResId = CommUtil.getStrByResId(context, "_94_values_17");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_17\")");
                break;
        }
        this$1.hybridUpdateList.set(3, CommUtil.getStrByResId(context, "_94_values_43") + ':' + strByResId);
        Log.i(MsgMgr.TAG, "setOnParseListeners: 【0x32】整车能量状态  启动原因1  {" + this$1.getValue(this$0.mCanbusInfoInt[6]) + '}');
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setOnParseListeners$lambda-5, reason: not valid java name */
    public static final void m963setOnParseListeners$lambda5(MsgMgr this$0, Context context, MsgMgr$initParsers$1$15 this$1) {
        String strByResId;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        switch (this$0.mCanbusInfoInt[7]) {
            case 1:
                strByResId = CommUtil.getStrByResId(context, "_94_values_28");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_28\")");
                break;
            case 2:
                strByResId = CommUtil.getStrByResId(context, "_94_values_29");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_29\")");
                break;
            case 3:
                strByResId = CommUtil.getStrByResId(context, "_94_values_30");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_30\")");
                break;
            case 4:
                strByResId = CommUtil.getStrByResId(context, "_94_values_31");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_31\")");
                break;
            case 5:
                strByResId = CommUtil.getStrByResId(context, "_94_values_32");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_32\")");
                break;
            case 6:
                strByResId = CommUtil.getStrByResId(context, "_94_values_33");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_33\")");
                break;
            case 7:
                strByResId = CommUtil.getStrByResId(context, "_94_values_34");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_34\")");
                break;
            case 8:
                strByResId = CommUtil.getStrByResId(context, "_94_values_35");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_35\")");
                break;
            case 9:
                strByResId = CommUtil.getStrByResId(context, "_94_values_36");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_36\")");
                break;
            case 10:
                strByResId = CommUtil.getStrByResId(context, "_94_values_37");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_37\")");
                break;
            case 11:
                strByResId = CommUtil.getStrByResId(context, "_94_values_38");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_38\")");
                break;
            case 12:
                strByResId = CommUtil.getStrByResId(context, "_94_values_39");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_39\")");
                break;
            default:
                strByResId = CommUtil.getStrByResId(context, "_94_values_17");
                Intrinsics.checkNotNullExpressionValue(strByResId, "getStrByResId(context,\"_94_values_17\")");
                break;
        }
        this$1.hybridUpdateList.set(4, CommUtil.getStrByResId(context, "_94_values_44") + ':' + strByResId);
        Log.i(MsgMgr.TAG, "setOnParseListeners: 【0x32】整车能量状态  启动原因2  {" + this$1.getValue(this$0.mCanbusInfoInt[7]) + '}');
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        if (isDataChange()) {
            super.parse(dataLength);
            GeneralHybirdData.valueList = this.hybridUpdateList;
            this.this$0.updateHybirdActivity(null);
        }
    }
}
