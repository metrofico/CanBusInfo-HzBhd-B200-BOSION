package com.hzbhd.canbus.canCustom.canBase;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.hzbhd.canbus.canCustom.canBase.CanVm$Companion$vmOwner$2;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.ui.binding.BaseViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CanVm.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanVm;", "Lcom/hzbhd/ui/binding/BaseViewModel;", "()V", "action", "Lcom/hzbhd/canbus/canCustom/canBase/CanAction;", "getAction", "()Lcom/hzbhd/canbus/canCustom/canBase/CanAction;", "canDocking", "Lcom/hzbhd/canbus/canCustom/canBase/CanDocking;", "getCanDocking", "()Lcom/hzbhd/canbus/canCustom/canBase/CanDocking;", "data", "Lcom/hzbhd/canbus/canCustom/canBase/CanData;", "getData", "()Lcom/hzbhd/canbus/canCustom/canBase/CanData;", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public class CanVm extends BaseViewModel {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static CanVm vm;
    private static final Lazy<CanVm$Companion$vmOwner$2.AnonymousClass1> vmOwner$delegate;
    private final CanData data = new CanData(this);
    private final CanAction action = new CanAction(this);
    private final CanDocking canDocking = new CanDocking();

    public CanData getData() {
        return this.data;
    }

    public CanAction getAction() {
        return this.action;
    }

    public CanDocking getCanDocking() {
        return this.canDocking;
    }

    /* compiled from: CanVm.kt */
    @Metadata(d1 = {"\u0000'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\b\b\u0000\u0010\u0011*\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanVm$Companion;", "", "()V", "vm", "Lcom/hzbhd/canbus/canCustom/canBase/CanVm;", "getVm", "()Lcom/hzbhd/canbus/canCustom/canBase/CanVm;", "setVm", "(Lcom/hzbhd/canbus/canCustom/canBase/CanVm;)V", "vmOwner", "com/hzbhd/canbus/canCustom/canBase/CanVm$Companion$vmOwner$2$1", "getVmOwner", "()Lcom/hzbhd/canbus/canCustom/canBase/CanVm$Companion$vmOwner$2$1;", "vmOwner$delegate", "Lkotlin/Lazy;", "getVmClass", "Ljava/lang/Class;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final CanVm$Companion$vmOwner$2.AnonymousClass1 getVmOwner() {
            return (CanVm$Companion$vmOwner$2.AnonymousClass1) CanVm.vmOwner$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <T extends BaseViewModel> Class<T> getVmClass() {
            try {
                Class<T> cls = (Class<T>) Class.forName("com.hzbhd.canbus._" + CanbusConfig.INSTANCE.getCanType() + ".Vm" + CanbusConfig.INSTANCE.getCanType());
                Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<T of com.hzbhd.canbus.canCustom.canBase.CanVm.Companion.getVmClass>");
                return cls;
            } catch (Exception e) {
                e.printStackTrace();
                return CanVm.class;
            }
        }

        public final CanVm getVm() {
            return CanVm.vm;
        }

        public final void setVm(CanVm canVm) {
            Intrinsics.checkNotNullParameter(canVm, "<set-?>");
            CanVm.vm = canVm;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        vmOwner$delegate = LazyKt.lazy(new Function0<CanVm$Companion$vmOwner$2.AnonymousClass1>() { // from class: com.hzbhd.canbus.canCustom.canBase.CanVm$Companion$vmOwner$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.canCustom.canBase.CanVm$Companion$vmOwner$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                return new ViewModelStoreOwner() { // from class: com.hzbhd.canbus.canCustom.canBase.CanVm$Companion$vmOwner$2.1
                    private final ViewModelStore mViewModelStore = new ViewModelStore();

                    @Override // androidx.lifecycle.ViewModelStoreOwner
                    /* renamed from: getViewModelStore, reason: from getter */
                    public ViewModelStore getMViewModelStore() {
                        return this.mViewModelStore;
                    }
                };
            }
        });
        ViewModel viewModel = new ViewModelProvider(companion.getVmOwner()).get(companion.getVmClass());
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(vmOwner).get(getVmClass())");
        vm = (CanVm) viewModel;
    }
}
