package com.hzbhd.canbus.vm.action;

import android.view.WindowManager;
import androidx.lifecycle.MutableLiveData;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.action.CanBusAction;
import com.hzbhd.canbus.vm.action.CanBusAction.Main;
import com.hzbhd.canbus.vm.action.CanBusAction.Radar;
import com.hzbhd.canbus.vm.action.CanBusAction.Reverse;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CanBusAction.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001:\u0003\u0015\u0016\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0005\u001a\u00060\u0006R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u000b\u001a\u00060\fR\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0010\u001a\u00060\u0011R\u00020\u00008FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction;", "", "vm", "Lcom/hzbhd/canbus/vm/Vm;", "(Lcom/hzbhd/canbus/vm/Vm;)V", BodaSysContant.CarSettings.Sign.Main, "Lcom/hzbhd/canbus/vm/action/CanBusAction$Main;", "getMain", "()Lcom/hzbhd/canbus/vm/action/CanBusAction$Main;", "main$delegate", "Lkotlin/Lazy;", "radar", "Lcom/hzbhd/canbus/vm/action/CanBusAction$Radar;", "getRadar", "()Lcom/hzbhd/canbus/vm/action/CanBusAction$Radar;", "radar$delegate", "reverse", "Lcom/hzbhd/canbus/vm/action/CanBusAction$Reverse;", "getReverse", "()Lcom/hzbhd/canbus/vm/action/CanBusAction$Reverse;", "reverse$delegate", "Main", "Radar", "Reverse", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public class CanBusAction {

    /* renamed from: main$delegate, reason: from kotlin metadata */
    private final Lazy main;

    /* renamed from: radar$delegate, reason: from kotlin metadata */
    private final Lazy radar;

    /* renamed from: reverse$delegate, reason: from kotlin metadata */
    private final Lazy reverse;

    public CanBusAction(Vm vm) {
        Intrinsics.checkNotNullParameter(vm, "vm");
        this.main = LazyKt.lazy(new Function0<Main>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$main$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CanBusAction.Main invoke() {
                return this.this$0.new Main();
            }
        });
        this.reverse = LazyKt.lazy(new Function0<Reverse>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$reverse$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CanBusAction.Reverse invoke() {
                return this.this$0.new Reverse();
            }
        });
        this.radar = LazyKt.lazy(new Function0<Radar>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$radar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CanBusAction.Radar invoke() {
                return this.this$0.new Radar();
            }
        });
    }

    public final Main getMain() {
        return (Main) this.main.getValue();
    }

    public final Reverse getReverse() {
        return (Reverse) this.reverse.getValue();
    }

    public final Radar getRadar() {
        return (Radar) this.radar.getValue();
    }

    /* compiled from: CanBusAction.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction$Main;", "", "(Lcom/hzbhd/canbus/vm/action/CanBusAction;)V", "init", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public class Main {
        public Main() {
        }

        public void init() {
            BhdWindowManager.INSTANCE.init(BaseUtil.INSTANCE.getContext());
            BhdWindowManager.INSTANCE.initReverseWindowParams(CanBusAction.this.getReverse().getReverseParams());
        }
    }

    /* compiled from: CanBusAction.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction$Reverse;", "", "(Lcom/hzbhd/canbus/vm/action/CanBusAction;)V", "getReverseParams", "Landroid/view/WindowManager$LayoutParams;", "setCustomPanoramicVisible", "", "visible", "", "setPanoramicVisible", "", "updatePanoramic", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public class Reverse {
        public Reverse() {
        }

        public WindowManager.LayoutParams getReverseParams() {
            if (LogUtil.log5()) {
                LogUtil.d("initWindowParam mReverseViewParams: ");
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = 2024;
            layoutParams.flags = 16777512;
            layoutParams.x = 0;
            layoutParams.y = 0;
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.format = 1;
            layoutParams.setTitle("ReverseWindow");
            return layoutParams;
        }

        public void setCustomPanoramicVisible(final boolean visible) {
            if (LogUtil.log5()) {
                LogUtil.d("setCustomPanoramicVisible: " + visible);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Reverse$setCustomPanoramicVisible$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getReverse().getCusPanoramicVisible().setValue(Integer.valueOf(visible ? 0 : 8));
                }
            });
        }

        public void setPanoramicVisible(final int visible) {
            if (LogUtil.log5()) {
                LogUtil.d("setPanoramicVisible: " + visible);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Reverse$setPanoramicVisible$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getReverse().getPanoramicVisible().setValue(Integer.valueOf(visible));
                }
            });
        }

        public void updatePanoramic() {
            if (LogUtil.log5()) {
                LogUtil.d("updatePanoramic: ");
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Reverse$updatePanoramic$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MutableLiveData<Integer> panoramicState = Vm.INSTANCE.getVm().getData().getReverse().getPanoramicState();
                    Integer value = Vm.INSTANCE.getVm().getData().getReverse().getPanoramicState().getValue();
                    Intrinsics.checkNotNull(value);
                    panoramicState.setValue(Integer.valueOf(value.intValue() + 1));
                }
            });
        }
    }

    /* compiled from: CanBusAction.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/hzbhd/canbus/vm/action/CanBusAction$Radar;", "", "(Lcom/hzbhd/canbus/vm/action/CanBusAction;)V", "setRadarScale", "", "scale", "", "setRadarVisible", "visible", "", "setShowDistanceNotShowLocationUi", "show", "setShowLeftTopOneDistanceUi", "setSmallRadar", "small", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public class Radar {
        public Radar() {
        }

        public void setRadarVisible(final int visible) {
            if (LogUtil.log5()) {
                LogUtil.d("setRadarVisible: " + visible);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Radar$setRadarVisible$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getRadar().getRadarVisible().setValue(Integer.valueOf(visible));
                }
            });
        }

        public void setRadarScale(final boolean scale) {
            if (LogUtil.log5()) {
                LogUtil.d("setRadarScale: " + scale);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Radar$setRadarScale$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getRadar().getRadarScale().setValue(Boolean.valueOf(scale));
                }
            });
        }

        public void setShowDistanceNotShowLocationUi(final boolean show) {
            if (LogUtil.log5()) {
                LogUtil.d("setShowDistanceNotShowLocationUi: " + show);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Radar$setShowDistanceNotShowLocationUi$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getRadar().isShowDistanceNotShowLocationUi().setValue(Boolean.valueOf(show));
                }
            });
        }

        public void setShowLeftTopOneDistanceUi(final boolean show) {
            if (LogUtil.log5()) {
                LogUtil.d("setShowLeftTopOneDistanceUi: " + show);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Radar$setShowLeftTopOneDistanceUi$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getRadar().isShowLeftTopOneDistanceUi().setValue(Boolean.valueOf(show));
                }
            });
        }

        public void setSmallRadar(final boolean small) {
            if (LogUtil.log5()) {
                LogUtil.d("setSmallRadar: " + small);
            }
            HandlerThreadUtilKt.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.vm.action.CanBusAction$Radar$setSmallRadar$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Vm.INSTANCE.getVm().getData().getRadar().getSmallRadar().setValue(Boolean.valueOf(small));
                }
            });
        }
    }
}
