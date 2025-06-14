package com.hzbhd.canbus.vm;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.hzbhd.canbus.vm.Vm$Companion$vmOwner$2;
import com.hzbhd.canbus.vm.action.CanBusAction;
import com.hzbhd.canbus.vm.data.CanBusData;
import com.hzbhd.canbus.vm.listener.ReverseListener;
import com.hzbhd.canbus.vm.view.main.ReverseMainView;
import com.hzbhd.config.use.UI;
import com.hzbhd.ui.binding.BaseViewModel;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Vm.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/hzbhd/canbus/vm/Vm;", "Lcom/hzbhd/ui/binding/BaseViewModel;", "()V", "action", "Lcom/hzbhd/canbus/vm/action/CanBusAction;", "getAction", "()Lcom/hzbhd/canbus/vm/action/CanBusAction;", "data", "Lcom/hzbhd/canbus/vm/data/CanBusData;", "getData", "()Lcom/hzbhd/canbus/vm/data/CanBusData;", "reverseListener", "Lcom/hzbhd/canbus/vm/listener/ReverseListener;", "getReverseListener", "()Lcom/hzbhd/canbus/vm/listener/ReverseListener;", "reverseMainView", "Lcom/hzbhd/canbus/vm/view/main/ReverseMainView;", "getReverseMainView", "()Lcom/hzbhd/canbus/vm/view/main/ReverseMainView;", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public class Vm extends BaseViewModel {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static Vm vm;
    private static final Lazy<Vm$Companion$vmOwner$2.AnonymousClass1> vmOwner$delegate;
    private final CanBusData data = new CanBusData(this);
    private final CanBusAction action = new CanBusAction(this);
    private final ReverseListener reverseListener = new ReverseListener();
    private final ReverseMainView reverseMainView = new ReverseMainView(BaseUtil.INSTANCE.getContext());

    public CanBusData getData() {
        return this.data;
    }

    public CanBusAction getAction() {
        return this.action;
    }

    public ReverseListener getReverseListener() {
        return this.reverseListener;
    }

    public ReverseMainView getReverseMainView() {
        return this.reverseMainView;
    }

    /* compiled from: Vm.kt */
    @Metadata(d1 = {"\u0000'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\b\b\u0000\u0010\u0011*\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/canbus/vm/Vm$Companion;", "", "()V", "vm", "Lcom/hzbhd/canbus/vm/Vm;", "getVm", "()Lcom/hzbhd/canbus/vm/Vm;", "setVm", "(Lcom/hzbhd/canbus/vm/Vm;)V", "vmOwner", "com/hzbhd/canbus/vm/Vm$Companion$vmOwner$2$1", "getVmOwner", "()Lcom/hzbhd/canbus/vm/Vm$Companion$vmOwner$2$1;", "vmOwner$delegate", "Lkotlin/Lazy;", "getVmClass", "Ljava/lang/Class;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Vm$Companion$vmOwner$2.AnonymousClass1 getVmOwner() {
            return (Vm$Companion$vmOwner$2.AnonymousClass1) Vm.vmOwner$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <T extends BaseViewModel> Class<T> getVmClass() {
            try {
                Class<T> cls = (Class<T>) Class.forName("com.hzbhd.canbus.vm" + UI.INSTANCE.getUIId() + ".VM");
                Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<T of com.hzbhd.canbus.vm.Vm.Companion.getVmClass>");
                return cls;
            } catch (Exception e) {
                e.printStackTrace();
                return Vm.class;
            }
        }

        public final Vm getVm() {
            return Vm.vm;
        }

        public final void setVm(Vm vm) {
            Intrinsics.checkNotNullParameter(vm, "<set-?>");
            Vm.vm = vm;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        vmOwner$delegate = LazyKt.lazy(new Function0<Vm$Companion$vmOwner$2.AnonymousClass1>() { // from class: com.hzbhd.canbus.vm.Vm$Companion$vmOwner$2
            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.vm.Vm$Companion$vmOwner$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                return new ViewModelStoreOwner() { // from class: com.hzbhd.canbus.vm.Vm$Companion$vmOwner$2.1
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
        vm = (Vm) viewModel;
    }
}
