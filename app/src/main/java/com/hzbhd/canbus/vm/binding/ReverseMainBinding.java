package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.hzbhd.R;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.binding.ReverseMainBinding;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.ui.life.EmptyBinding;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: ReverseMainBinding.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/vm/binding/ReverseMainBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "__01__reverse_main_panoramic", "Landroid/view/View;", "get__01__reverse_main_panoramic", "()Landroid/view/View;", "__01__reverse_main_panoramic1", "get__01__reverse_main_panoramic1", "__01__reverse_main_radar", "get__01__reverse_main_radar", "back_view_back", "getBack_view_back", "tv_camera_tis", "getTv_camera_tis", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class ReverseMainBinding extends EmptyBinding {
    private final View __01__reverse_main_panoramic;
    private final View __01__reverse_main_panoramic1;
    private final View __01__reverse_main_radar;
    private final View back_view_back;
    private final View tv_camera_tis;

    @Override // com.hzbhd.ui.life.EmptyBinding
    public int getLayoutId() {
        return R.layout.canbus__00__reverse_main;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReverseMainBinding(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        View viewFindViewById = getRoot().findViewById(R.id.__01__reverse_main_panoramic);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id._…__reverse_main_panoramic)");
        this.__01__reverse_main_panoramic = viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.__01__reverse_main_radar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.__01__reverse_main_radar)");
        this.__01__reverse_main_radar = viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.__01__reverse_main_panoramic1);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id._…_reverse_main_panoramic1)");
        this.__01__reverse_main_panoramic1 = viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.tv_camera_tis);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.tv_camera_tis)");
        this.tv_camera_tis = viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.back_view_back);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.back_view_back)");
        this.back_view_back = viewFindViewById5;
    }

    public final View get__01__reverse_main_panoramic() {
        return this.__01__reverse_main_panoramic;
    }

    public final View get__01__reverse_main_radar() {
        return this.__01__reverse_main_radar;
    }

    public final View get__01__reverse_main_panoramic1() {
        return this.__01__reverse_main_panoramic1;
    }

    public final View getTv_camera_tis() {
        return this.tv_camera_tis;
    }

    public final View getBack_view_back() {
        return this.back_view_back;
    }

    /* compiled from: ReverseMainBinding.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
    /* renamed from: com.hzbhd.canbus.vm.binding.ReverseMainBinding$addObserver$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ LifecycleOwner $lifecycleObserver;
        final /* synthetic */ ReverseMainBinding this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LifecycleOwner lifecycleOwner, ReverseMainBinding reverseMainBinding) {
            super(0);
            this.$lifecycleObserver = lifecycleOwner;
            this.this$0 = reverseMainBinding;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            MutableLiveData<Boolean> backBtn = Vm.INSTANCE.getVm().getData().getReverse().getBackBtn();
            LifecycleOwner lifecycleOwner = this.$lifecycleObserver;
            final ReverseMainBinding reverseMainBinding = this.this$0;
            backBtn.observe(lifecycleOwner, new Observer() { // from class: com.hzbhd.canbus.vm.binding.ReverseMainBinding$addObserver$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ReverseMainBinding.AnonymousClass1.m1188invoke$lambda0(reverseMainBinding, (Boolean) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m1188invoke$lambda0(ReverseMainBinding this$0, Boolean it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (LogUtil.log5()) {
                LogUtil.d("ReverseMainBinding: " + it);
            }
            View back_view_back = this$0.getBack_view_back();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            back_view_back.setVisibility(it.booleanValue() ? 0 : 8);
        }
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        Intrinsics.checkNotNullParameter(lifecycleObserver, "lifecycleObserver");
        HandlerThreadUtilKt.runUi(new AnonymousClass1(lifecycleObserver, this));
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void bindAction() {
        this.back_view_back.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.vm.binding.ReverseMainBinding$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ReverseMainBinding.m1187bindAction$lambda0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bindAction$lambda-0, reason: not valid java name */
    public static final void m1187bindAction$lambda0(View view) {
        if (LogUtil.log5()) {
            LogUtil.d("bindAction: back_view_back");
        }
        ShareDataManager.getInstance().reportInt(ShareConstants.SHARE_USER_REVERSE, 0);
    }
}
