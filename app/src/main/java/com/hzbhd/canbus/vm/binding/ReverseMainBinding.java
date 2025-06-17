package com.hzbhd.canbus.vm.binding;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hzbhd.canbus.R;
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

    public ReverseMainBinding(Context context) {
        super(context);

        View viewFindViewById = getRoot().findViewById(R.id.__01__reverse_main_panoramic);

        this.__01__reverse_main_panoramic = viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.__01__reverse_main_radar);

        this.__01__reverse_main_radar = viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.__01__reverse_main_panoramic1);

        this.__01__reverse_main_panoramic1 = viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.tv_camera_tis);

        this.tv_camera_tis = viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.back_view_back);

        this.back_view_back = viewFindViewById5;
    }

    public View get__01__reverse_main_panoramic() {
        return this.__01__reverse_main_panoramic;
    }

    public View get__01__reverse_main_radar() {
        return this.__01__reverse_main_radar;
    }

    public View get__01__reverse_main_panoramic1() {
        return this.__01__reverse_main_panoramic1;
    }

    public View getTv_camera_tis() {
        return this.tv_camera_tis;
    }

    public View getBack_view_back() {
        return this.back_view_back;
    }


    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleOwner) {
        HandlerThreadUtilKt.runUi(() -> {
            MutableLiveData<Boolean> backBtn = Vm.getVm().getData().getReverse().getBackBtn();
            final ReverseMainBinding reverseMainBinding = ReverseMainBinding.this;
            backBtn.observe(lifecycleOwner, aBoolean -> {
                if (LogUtil.log5()) {
                    LogUtil.d("ReverseMainBinding: " + aBoolean);
                }
                View back_view_back = reverseMainBinding.getBack_view_back();
                back_view_back.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            });
        });
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void bindAction() {
        this.back_view_back.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.vm.binding.ReverseMainBinding$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReverseMainBinding.m1187bindAction$lambda0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: bindAction$lambda-0, reason: not valid java name */
    public static void m1187bindAction$lambda0(View view) {
        if (LogUtil.log5()) {
            LogUtil.d("bindAction: back_view_back");
        }
        ShareDataManager.getInstance().reportInt(ShareConstants.SHARE_USER_REVERSE, 0);
    }
}
