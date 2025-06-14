package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.car._94.ActivePark;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/hzbhd/canbus/car/_94/MsgMgr$initParsers$1$23", "Lcom/hzbhd/canbus/car/_94/MsgMgr$Parser;", "Lcom/hzbhd/canbus/car/_94/MsgMgr;", "parse", "", "dataLength", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr$initParsers$1$23 extends MsgMgr.Parser {
    final /* synthetic */ Context $context;
    final /* synthetic */ MsgMgr this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MsgMgr$initParsers$1$23(MsgMgr msgMgr, Context context) {
        super(msgMgr, "【0x60】泊车辅助");
        this.this$0 = msgMgr;
        this.$context = context;
    }

    @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
    public void parse(int dataLength) {
        final ActivePark activePark = this.this$0.mActivePark;
        if (activePark != null) {
            final MsgMgr msgMgr = this.this$0;
            final Context context = this.$context;
            if (isDataChange()) {
                msgMgr.runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$23$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                    public final void callback() {
                        MsgMgr$initParsers$1$23.m976parse$lambda3$lambda2(activePark, msgMgr, context);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: parse$lambda-3$lambda-2, reason: not valid java name */
    public static final void m976parse$lambda3$lambda2(ActivePark this_run, MsgMgr this$0, Context context) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        this_run.stopTimer();
        boolean z = true;
        if (this$0.mCanbusInfoInt[2] != 0 && this$0.mCanbusInfoInt[3] != 0 && this$0.mCanbusInfoInt[3] != 1) {
            z = false;
        }
        this_run.setActiveParkActive(!z);
        this$0.updateParkUi(null, context);
        if (z) {
            return;
        }
        Log.i(MsgMgr.TAG, "parse: CallBackInterface   continue  " + this$0.mCanbusInfoInt[3] + "   " + this$0.mActiveParkBeamArray.get(this$0.mCanbusInfoInt[3]));
        ActivePark.ActiveParkBeam activeParkBeam = (ActivePark.ActiveParkBeam) this$0.mActiveParkBeamArray.get(this$0.mCanbusInfoInt[3]);
        if (activeParkBeam != null) {
            this_run.update(activeParkBeam);
            if (this$0.mCanbusInfoInt[3] == 28) {
                this_run.countDown(context, activeParkBeam.getMessageResId());
            }
        }
    }
}
