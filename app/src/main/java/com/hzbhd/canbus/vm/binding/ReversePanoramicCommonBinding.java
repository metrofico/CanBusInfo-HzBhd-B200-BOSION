package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.PanoramiceBtnLvAdapter;
import com.hzbhd.canbus.park.panoramic.PanoramicView;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ReversePanoramicCommonBinding.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/vm/binding/ReversePanoramicCommonBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "view_panoramic", "Lcom/hzbhd/canbus/park/panoramic/PanoramicView;", "getView_panoramic", "()Lcom/hzbhd/canbus/park/panoramic/PanoramicView;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ReversePanoramicCommonBinding extends EmptyBinding {
    private final PanoramicView view_panoramic;

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void bindAction() {
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public int getLayoutId() {
        return R.layout.canbus__00__reverse_panoramic1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReversePanoramicCommonBinding(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.view_panoramic = (PanoramicView) getRoot().findViewById(R.id.view_panoramic);
    }

    public final PanoramicView getView_panoramic() {
        return this.view_panoramic;
    }

    /* compiled from: ReversePanoramicCommonBinding.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding$addObserver$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ LifecycleOwner $lifecycleObserver;
        final /* synthetic */ ReversePanoramicCommonBinding this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LifecycleOwner lifecycleOwner, ReversePanoramicCommonBinding reversePanoramicCommonBinding) {
            super(0);
            this.$lifecycleObserver = lifecycleOwner;
            this.this$0 = reversePanoramicCommonBinding;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            MutableLiveData<Integer> panoramicState = Vm.INSTANCE.getVm().getData().getReverse().getPanoramicState();
            LifecycleOwner lifecycleOwner = this.$lifecycleObserver;
            final ReversePanoramicCommonBinding reversePanoramicCommonBinding = this.this$0;
            panoramicState.observe(lifecycleOwner, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding$addObserver$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReversePanoramicCommonBinding.AnonymousClass1.m1190invoke$lambda0(reversePanoramicCommonBinding, (Integer) obj);
                }
            });
            MutableLiveData<Integer> panoramicVisible = Vm.INSTANCE.getVm().getData().getReverse().getPanoramicVisible();
            LifecycleOwner lifecycleOwner2 = this.$lifecycleObserver;
            final ReversePanoramicCommonBinding reversePanoramicCommonBinding2 = this.this$0;
            panoramicVisible.observe(lifecycleOwner2, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding$addObserver$1$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReversePanoramicCommonBinding.AnonymousClass1.m1191invoke$lambda1(reversePanoramicCommonBinding2, (Integer) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m1190invoke$lambda0(ReversePanoramicCommonBinding this$0, Integer num) {
            PanoramiceBtnLvAdapter adapter;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            PanoramicView view_panoramic = this$0.getView_panoramic();
            if (view_panoramic == null || (adapter = view_panoramic.getAdapter()) == null) {
                return;
            }
            adapter.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-1, reason: not valid java name */
        public static final void m1191invoke$lambda1(ReversePanoramicCommonBinding this$0, Integer it) {
            PanoramicView view_panoramic;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            PanoramicView view_panoramic2 = this$0.getView_panoramic();
            if (view_panoramic2 != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                view_panoramic2.setVisibility(it.intValue());
            }
            if (it == null || it.intValue() != 0 || (view_panoramic = this$0.getView_panoramic()) == null) {
                return;
            }
            view_panoramic.setBtnList(UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getParkPageUiSet(BaseUtil.INSTANCE.getContext()).getPanoramicBtnList(), UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getParkPageUiSet(BaseUtil.INSTANCE.getContext()).getOnPanoramicItemClickListener());
        }
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        Intrinsics.checkNotNullParameter(lifecycleObserver, "lifecycleObserver");
        HandlerThreadUtilKt.runUi(new AnonymousClass1(lifecycleObserver, this));
    }
}
