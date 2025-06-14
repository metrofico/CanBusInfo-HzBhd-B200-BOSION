package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.hzbhd.R;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.binding.ReversePanoramicCustomBinding;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ReversePanoramicCustomBinding.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/vm/binding/ReversePanoramicCustomBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "view_cus_panoramic", "Landroid/widget/RelativeLayout;", "getView_cus_panoramic", "()Landroid/widget/RelativeLayout;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ReversePanoramicCustomBinding extends EmptyBinding {
    private final RelativeLayout view_cus_panoramic;

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void bindAction() {
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public int getLayoutId() {
        return R.layout.canbus__00__reverse_panoramic;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReversePanoramicCustomBinding(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.view_cus_panoramic = (RelativeLayout) getRoot().findViewById(R.id.view_cus_panoramic);
    }

    public final RelativeLayout getView_cus_panoramic() {
        return this.view_cus_panoramic;
    }

    /* compiled from: ReversePanoramicCustomBinding.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.hzbhd.canbus.vm.binding.ReversePanoramicCustomBinding$addObserver$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ LifecycleOwner $lifecycleObserver;
        final /* synthetic */ ReversePanoramicCustomBinding this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LifecycleOwner lifecycleOwner, ReversePanoramicCustomBinding reversePanoramicCustomBinding) {
            super(0);
            this.$lifecycleObserver = lifecycleOwner;
            this.this$0 = reversePanoramicCustomBinding;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            MutableLiveData<Integer> cusPanoramicVisible = Vm.INSTANCE.getVm().getData().getReverse().getCusPanoramicVisible();
            LifecycleOwner lifecycleOwner = this.$lifecycleObserver;
            final ReversePanoramicCustomBinding reversePanoramicCustomBinding = this.this$0;
            cusPanoramicVisible.observe(lifecycleOwner, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReversePanoramicCustomBinding$addObserver$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReversePanoramicCustomBinding.AnonymousClass1.m1193invoke$lambda0(reversePanoramicCustomBinding, (Integer) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m1193invoke$lambda0(ReversePanoramicCustomBinding this$0, Integer it) {
            View cusPanoramicView;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            RelativeLayout view_cus_panoramic = this$0.getView_cus_panoramic();
            if (view_cus_panoramic != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                view_cus_panoramic.setVisibility(it.intValue());
            }
            if (it == null || it.intValue() != 0 || (cusPanoramicView = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getCusPanoramicView(BaseUtil.INSTANCE.getContext())) == null) {
                return;
            }
            RelativeLayout view_cus_panoramic2 = this$0.getView_cus_panoramic();
            if ((view_cus_panoramic2 != null ? Integer.valueOf(view_cus_panoramic2.getChildCount()) : null) == 0) {
                if (cusPanoramicView.getParent() != null) {
                    ViewParent parent = cusPanoramicView.getParent();
                    Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                    ((ViewGroup) parent).removeAllViews();
                }
                RelativeLayout view_cus_panoramic3 = this$0.getView_cus_panoramic();
                if (view_cus_panoramic3 != null) {
                    view_cus_panoramic3.addView(cusPanoramicView);
                }
            }
        }
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        Intrinsics.checkNotNullParameter(lifecycleObserver, "lifecycleObserver");
        HandlerThreadUtilKt.runUi(new AnonymousClass1(lifecycleObserver, this));
    }
}
