package com.hzbhd.canbus.vm.listener;

import android.os.SystemProperties;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReverseListener.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0005H\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/hzbhd/canbus/vm/listener/ReverseListener;", "", "()V", "actionBefortViewInit", "Lkotlin/Function0;", "", "getActionBefortViewInit", "()Lkotlin/jvm/functions/Function0;", "setActionBefortViewInit", "(Lkotlin/jvm/functions/Function0;)V", "mReverseState", "", "sysReverse", "getSysReverse", "()I", "setSysReverse", "(I)V", "userReverse", "getUserReverse", "setUserReverse", "isReversing", "", "updateReverse", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ReverseListener {
    private Function0<Unit> actionBefortViewInit;
    private int sysReverse;
    private int mReverseState = -1;
    private int userReverse = -1;

    public ReverseListener() {
        this.sysReverse = -1;
        this.sysReverse = ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MISC_REVERSE, new IShareIntListener() { // from class: com.hzbhd.canbus.vm.listener.ReverseListener$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
            public final void onInt(int i) {
                ReverseListener.m1202_init_$lambda0(this.f$0, i);
            }
        });
        updateReverse();
    }

    public final Function0<Unit> getActionBefortViewInit() {
        return this.actionBefortViewInit;
    }

    public final void setActionBefortViewInit(Function0<Unit> function0) {
        this.actionBefortViewInit = function0;
    }

    public final int getUserReverse() {
        return this.userReverse;
    }

    public final void setUserReverse(int i) {
        this.userReverse = i;
    }

    public final int getSysReverse() {
        return this.sysReverse;
    }

    public final void setSysReverse(int i) {
        this.sysReverse = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1202_init_$lambda0(ReverseListener this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sysReverse = i;
        this$0.updateReverse();
    }

    public final boolean isReversing() {
        return this.mReverseState == 1;
    }

    private final void updateReverse() {
        int i = (this.sysReverse == 1 || this.userReverse == 1) ? 1 : 0;
        if (LogUtil.log5()) {
            LogUtil.d("reverseState : " + i + ", mIsCarBackGpioLevel=" + this.mReverseState);
        }
        if (this.mReverseState != i) {
            if (LogUtil.log5()) {
                LogUtil.d("GpioOperator=detectCarBackState: mIsCarBackGpioLevel=" + this.mReverseState + ", level=" + i);
            }
            this.mReverseState = i;
            if (i == 0) {
                if (LogUtil.log5()) {
                    LogUtil.d("stop camera ");
                }
                BhdWindowManager.INSTANCE.removeReverseView();
                SystemProperties.set("BackCameraUI", "false");
                HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.listener.ReverseListener.updateReverse.2
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Vm.INSTANCE.getVm().getData().getReverse().isReverse().setValue(false);
                    }
                });
                return;
            }
            if (i != 1) {
                return;
            }
            if (LogUtil.log5()) {
                LogUtil.d("start camera ");
            }
            Function0<Unit> function0 = this.actionBefortViewInit;
            if (function0 != null) {
                function0.invoke();
            }
            BhdWindowManager.INSTANCE.addReverseView();
            SystemProperties.set("BackCameraUI", "true");
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.listener.ReverseListener.updateReverse.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getReverse().isReverse().setValue(true);
                }
            });
        }
    }
}
