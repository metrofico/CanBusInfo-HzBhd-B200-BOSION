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
        this.view_cus_panoramic = getRoot().findViewById(R.id.view_cus_panoramic);
    }

    public RelativeLayout getView_cus_panoramic() {
        return this.view_cus_panoramic;
    }


    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        HandlerThreadUtilKt.runUi(new Runnable() {
            @Override
            public void run() {
                MutableLiveData<Integer> cusPanoramicVisible = Vm.getVm().getData().getReverse().getCusPanoramicVisible();

                cusPanoramicVisible.observe(lifecycleObserver, new Observer<Integer>() { // from class: com.hzbhd.canbus.vm.binding.ReversePanoramicCustomBinding$addObserver$1$$ExternalSyntheticLambda0
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(Integer it) {
                        View cusPanoramicView;
                        RelativeLayout view_cus_panoramic = getView_cus_panoramic();
                        if (view_cus_panoramic != null) {
                            view_cus_panoramic.setVisibility(it);
                        }
                        if (it == null || it != 0 || (cusPanoramicView = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getCusPanoramicView(BaseUtil.INSTANCE.getContext())) == null) {
                            return;
                        }
                        RelativeLayout view_cus_panoramic2 = getView_cus_panoramic();
                        if ((view_cus_panoramic2 != null ? view_cus_panoramic2.getChildCount() : null) == 0) {
                            if (cusPanoramicView.getParent() != null) {
                                ViewParent parent = cusPanoramicView.getParent();
                                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                                ((ViewGroup) parent).removeAllViews();
                            }
                            RelativeLayout view_cus_panoramic3 = getView_cus_panoramic();
                            if (view_cus_panoramic3 != null) {
                                view_cus_panoramic3.addView(cusPanoramicView);
                            }
                        }
                    }
                });
            }
        });
    }
}
