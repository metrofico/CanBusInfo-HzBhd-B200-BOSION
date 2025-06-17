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

        this.view_radar = getRoot().findViewById(R.id.view_radar);
    }

    public RadarView getView_radar() {
        return this.view_radar;
    }


    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        HandlerThreadUtilKt.runUi(() -> {
            MutableLiveData<Boolean> mutableLiveDataIsReverse = Vm.getVm().getData().getReverse().isReverse();

            mutableLiveDataIsReverse.observe(lifecycleObserver, new Observer<Boolean>() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean it) {
                    RadarView view_radar;
                    if (!it || (view_radar = getView_radar()) == null) {
                        return;
                    }
                    view_radar.refreshText();
                }
            });
            MutableLiveData<Integer> radarVisible = Vm.getVm().getData().getRadar().getRadarVisible();

            radarVisible.observe(lifecycleObserver, new Observer<Integer>() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public void onChanged(Integer it) {
                    if (LogUtil.log5()) {
                        LogUtil.d("radarVisible: " + ReverseRadarBinding.this + "   " + getView_radar() + "  " + lifecycleObserver + "  " + it);
                    }
                    RadarView view_radar = getView_radar();
                    if (view_radar == null) {
                        return;
                    }

                    view_radar.setVisibility(it);
                }
            });
            MutableLiveData<Boolean> radarScale = Vm.getVm().getData().getRadar().getRadarScale();

            radarScale.observe(lifecycleObserver, new Observer<Boolean>() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean it) {
                    if (it) {
                        RadarView view_radar = getView_radar();
                        if (view_radar != null) {
                            view_radar.showRadarView();
                            return;
                        }
                        return;
                    }
                    RadarView view_radar2 = getView_radar();
                    if (view_radar2 != null) {
                        view_radar2.hideRadarView();
                    }
                }
            });
            MutableLiveData<Boolean> mutableLiveDataIsShowDistanceNotShowLocationUi = Vm.getVm().getData().getRadar().isShowDistanceNotShowLocationUi();

            mutableLiveDataIsShowDistanceNotShowLocationUi.observe(lifecycleObserver, new Observer<Boolean>() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda3
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean it) {
                    if (it) {
                        RadarView view_radar = getView_radar();
                        if (view_radar != null) {
                            view_radar.refreshDistance(GeneralParkData.radar_distance_data);
                            return;
                        }
                        return;
                    }
                    RadarView view_radar2 = getView_radar();
                    if (view_radar2 != null) {
                        view_radar2.refreshLocation(GeneralParkData.radar_location_data);
                    }
                }
            });
            MutableLiveData<Boolean> mutableLiveDataIsShowLeftTopOneDistanceUi = Vm.getVm().getData().getRadar().isShowLeftTopOneDistanceUi();

            mutableLiveDataIsShowLeftTopOneDistanceUi.observe(lifecycleObserver, new Observer<Boolean>() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda4
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean it) {
                    if (it) {
                        RadarView view_radar = getView_radar();
                        if (view_radar != null) {
                            view_radar.setOneRadarDitance(GeneralParkData.strOnlyOneDistance);
                            return;
                        }
                        return;
                    }
                    RadarView view_radar2 = getView_radar();
                    if (view_radar2 != null) {
                        view_radar2.hideOneRadarDistance();
                    }
                }
            });
            MutableLiveData<Boolean> smallRadar = Vm.getVm().getData().getRadar().getSmallRadar();

            smallRadar.observe(lifecycleObserver, new Observer<Boolean>() { // from class: com.hzbhd.canbus.vm.binding.ReverseRadarBinding$addObserver$1$$ExternalSyntheticLambda5
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean it) {
                    RadarView view_radar;
                    if (!it || (view_radar = getView_radar()) == null) {
                        return;
                    }
                    view_radar.setSmallRadarViewWidth();
                }
            });
        });
    }
}
