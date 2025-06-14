package com.hzbhd.canbus.car._340;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: UiMgr.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.hzbhd.canbus.car._340.UiMgr$1$sendAirConditionerModeData$1", f = "UiMgr.kt", i = {}, l = {HotKeyConstant.K_BAND_PICKUP}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UiMgr$1$sendAirConditionerModeData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte $d1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UiMgr$1$sendAirConditionerModeData$1(byte b, Continuation<? super UiMgr$1$sendAirConditionerModeData$1> continuation) {
        super(2, continuation);
        this.$d1 = b;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UiMgr$1$sendAirConditionerModeData$1(this.$d1, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UiMgr$1$sendAirConditionerModeData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 9, this.$d1});
            this.label = 1;
            if (DelayKt.delay(200L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -88, 8, 9, 0});
        return Unit.INSTANCE;
    }
}
