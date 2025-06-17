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

        this.view_panoramic = getRoot().findViewById(R.id.view_panoramic);
    }

    public PanoramicView getView_panoramic() {
        return this.view_panoramic;
    }


    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {

        HandlerThreadUtilKt.runUi(new Runnable() {
            @Override
            public void run() {
                MutableLiveData<Integer> panoramicState = Vm.getVm().getData().getReverse().getPanoramicState();
                panoramicState.observe(lifecycleObserver, new Observer<Integer>() { // from class: com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding$addObserver$1$$ExternalSyntheticLambda0
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(Integer num) {
                        PanoramiceBtnLvAdapter adapter;
                        PanoramicView view_panoramic = getView_panoramic();
                        if (view_panoramic == null || (adapter = view_panoramic.getAdapter()) == null) {
                            return;
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                MutableLiveData<Integer> panoramicVisible = Vm.getVm().getData().getReverse().getPanoramicVisible();
                panoramicVisible.observe(lifecycleObserver, new Observer<Integer>() { // from class: com.hzbhd.canbus.vm.binding.ReversePanoramicCommonBinding$addObserver$1$$ExternalSyntheticLambda1
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(Integer it) {
                        PanoramicView view_panoramic;
                        PanoramicView view_panoramic2 = getView_panoramic();
                        if (view_panoramic2 != null) {
                            view_panoramic2.setVisibility(it);
                        }
                        if (it == null || it != 0 || (view_panoramic = getView_panoramic()) == null) {
                            return;
                        }
                        view_panoramic.setBtnList(UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getParkPageUiSet(BaseUtil.INSTANCE.getContext()).getPanoramicBtnList(), UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getParkPageUiSet(BaseUtil.INSTANCE.getContext()).getOnPanoramicItemClickListener());

                    }
                });
            }
        });
    }
}
