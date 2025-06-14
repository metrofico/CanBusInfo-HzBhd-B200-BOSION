package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.park.radar.RadarView;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.binding.ReverseRadarBinding;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ReverseRadarBinding.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/vm/binding/ReverseRadarBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "view_radar", "Lcom/hzbhd/canbus/park/radar/RadarView;", "getView_radar", "()Lcom/hzbhd/canbus/park/radar/RadarView;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ReverseRadarBinding extends EmptyBinding {
    private final RadarView view_radar;

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void bindAction() {
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public int getLayoutId() {
        return R.layout.canbus__00__reverse_radar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReverseRadarBinding(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.view_radar = (RadarView) getRoot().findViewById(R.id.view_radar);
    }

    public final RadarView getView_radar() {
        return this.view_radar;
    }

    /* compiled from: ReverseRadarBinding.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ LifecycleOwner $lifecycleObserver;
        final /* synthetic */ ReverseRadarBinding this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LifecycleOwner lifecycleOwner, ReverseRadarBinding reverseRadarBinding) {
            super(0);
            this.$lifecycleObserver = lifecycleOwner;
            this.this$0 = reverseRadarBinding;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            MutableLiveData<Boolean> mutableLiveDataIsReverse = Vm.INSTANCE.getVm().getData().getReverse().isReverse();
            LifecycleOwner lifecycleOwner = this.$lifecycleObserver;
            final ReverseRadarBinding reverseRadarBinding = this.this$0;
            mutableLiveDataIsReverse.observe(lifecycleOwner, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseRadarBinding.AnonymousClass1.m1196invoke$lambda0(reverseRadarBinding, (Boolean) obj);
                }
            });
            MutableLiveData<Integer> radarVisible = Vm.INSTANCE.getVm().getData().getRadar().getRadarVisible();
            final LifecycleOwner lifecycleOwner2 = this.$lifecycleObserver;
            final ReverseRadarBinding reverseRadarBinding2 = this.this$0;
            radarVisible.observe(lifecycleOwner2, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseRadarBinding.AnonymousClass1.m1197invoke$lambda1(reverseRadarBinding2, lifecycleOwner2, (Integer) obj);
                }
            });
            MutableLiveData<Boolean> radarScale = Vm.INSTANCE.getVm().getData().getRadar().getRadarScale();
            LifecycleOwner lifecycleOwner3 = this.$lifecycleObserver;
            final ReverseRadarBinding reverseRadarBinding3 = this.this$0;
            radarScale.observe(lifecycleOwner3, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseRadarBinding.AnonymousClass1.m1198invoke$lambda2(reverseRadarBinding3, (Boolean) obj);
                }
            });
            MutableLiveData<Boolean> mutableLiveDataIsShowDistanceNotShowLocationUi = Vm.INSTANCE.getVm().getData().getRadar().isShowDistanceNotShowLocationUi();
            LifecycleOwner lifecycleOwner4 = this.$lifecycleObserver;
            final ReverseRadarBinding reverseRadarBinding4 = this.this$0;
            mutableLiveDataIsShowDistanceNotShowLocationUi.observe(lifecycleOwner4, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseRadarBinding.AnonymousClass1.m1199invoke$lambda3(reverseRadarBinding4, (Boolean) obj);
                }
            });
            MutableLiveData<Boolean> mutableLiveDataIsShowLeftTopOneDistanceUi = Vm.INSTANCE.getVm().getData().getRadar().isShowLeftTopOneDistanceUi();
            LifecycleOwner lifecycleOwner5 = this.$lifecycleObserver;
            final ReverseRadarBinding reverseRadarBinding5 = this.this$0;
            mutableLiveDataIsShowLeftTopOneDistanceUi.observe(lifecycleOwner5, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseRadarBinding.AnonymousClass1.m1200invoke$lambda4(reverseRadarBinding5, (Boolean) obj);
                }
            });
            MutableLiveData<Boolean> smallRadar = Vm.INSTANCE.getVm().getData().getRadar().getSmallRadar();
            LifecycleOwner lifecycleOwner6 = this.$lifecycleObserver;
            final ReverseRadarBinding reverseRadarBinding6 = this.this$0;
            smallRadar.observe(lifecycleOwner6, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda5
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseRadarBinding.AnonymousClass1.m1201invoke$lambda5(reverseRadarBinding6, (Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m1196invoke$lambda0(ReverseRadarBinding this$0, Boolean it) {
            RadarView view_radar;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!it.booleanValue() || (view_radar = this$0.getView_radar()) == null) {
                return;
            }
            view_radar.refreshText();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-1, reason: not valid java name */
        public static final void m1197invoke$lambda1(ReverseRadarBinding this$0, LifecycleOwner lifecycleObserver, Integer it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(lifecycleObserver, "$lifecycleObserver");
            if (LogUtil.log5()) {
                LogUtil.d("radarVisible: " + this$0 + "   " + this$0.getView_radar() + "  " + lifecycleObserver + "  " + it);
            }
            RadarView view_radar = this$0.getView_radar();
            if (view_radar == null) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(it, "it");
            view_radar.setVisibility(it.intValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-2, reason: not valid java name */
        public static final void m1198invoke$lambda2(ReverseRadarBinding this$0, Boolean it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (it.booleanValue()) {
                RadarView view_radar = this$0.getView_radar();
                if (view_radar != null) {
                    view_radar.showRadarView();
                    return;
                }
                return;
            }
            RadarView view_radar2 = this$0.getView_radar();
            if (view_radar2 != null) {
                view_radar2.hideRadarView();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-3, reason: not valid java name */
        public static final void m1199invoke$lambda3(ReverseRadarBinding this$0, Boolean it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (it.booleanValue()) {
                RadarView view_radar = this$0.getView_radar();
                if (view_radar != null) {
                    view_radar.refreshDistance(GeneralParkData.radar_distance_data);
                    return;
                }
                return;
            }
            RadarView view_radar2 = this$0.getView_radar();
            if (view_radar2 != null) {
                view_radar2.refreshLocation(GeneralParkData.radar_location_data);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-4, reason: not valid java name */
        public static final void m1200invoke$lambda4(ReverseRadarBinding this$0, Boolean it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (it.booleanValue()) {
                RadarView view_radar = this$0.getView_radar();
                if (view_radar != null) {
                    view_radar.setOneRadarDitance(GeneralParkData.strOnlyOneDistance);
                    return;
                }
                return;
            }
            RadarView view_radar2 = this$0.getView_radar();
            if (view_radar2 != null) {
                view_radar2.hideOneRadarDistance();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-5, reason: not valid java name */
        public static final void m1201invoke$lambda5(ReverseRadarBinding this$0, Boolean it) {
            RadarView view_radar;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!it.booleanValue() || (view_radar = this$0.getView_radar()) == null) {
                return;
            }
            view_radar.setSmallRadarViewWidth();
        }
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        Intrinsics.checkNotNullParameter(lifecycleObserver, "lifecycleObserver");
        HandlerThreadUtilKt.runUi(new AnonymousClass1(lifecycleObserver, this));
    }
}
