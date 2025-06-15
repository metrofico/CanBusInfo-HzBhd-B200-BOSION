package com.hzbhd.canbus.vm.data;

import androidx.lifecycle.MutableLiveData;

import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.data.CanBusData;
import com.hzbhd.canbus.vm.data.CanBusData.RadarData;
import com.hzbhd.canbus.vm.data.CanBusData.ReverseData;
import com.hzbhd.config.use.CanBusDefault;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public class CanBusData {

    /* renamed from: radar$delegate, reason: from kotlin metadata */
    private final RadarData radar;

    /* renamed from: reverse$delegate, reason: from kotlin metadata */
    private final ReverseData reverse;

    public CanBusData(Vm vm) {
        this.reverse = new ReverseData();
        this.radar = new RadarData();
    }

    public ReverseData getReverse() {
        return this.reverse;
    }

    public RadarData getRadar() {
        return this.radar;
    }

    public class ReverseData {
        private final MutableLiveData<Boolean> isReverse = new MutableLiveData<>(false);
        private final MutableLiveData<Integer> cusPanoramicVisible = new MutableLiveData<>(8);
        private final MutableLiveData<Integer> panoramicVisible = new MutableLiveData<>(8);
        private final MutableLiveData<Integer> panoramicState = new MutableLiveData<>(0);
        private final MutableLiveData<Boolean> backBtn = new MutableLiveData<>(Boolean.valueOf(CanBusDefault.INSTANCE.getCanBusBackBtn()));

        public ReverseData() {
        }

        public MutableLiveData<Boolean> isReverse() {
            return this.isReverse;
        }

        public MutableLiveData<Integer> getCusPanoramicVisible() {
            return this.cusPanoramicVisible;
        }

        public MutableLiveData<Integer> getPanoramicVisible() {
            return this.panoramicVisible;
        }

        public MutableLiveData<Integer> getPanoramicState() {
            return this.panoramicState;
        }

        public MutableLiveData<Boolean> getBackBtn() {
            return this.backBtn;
        }
    }

    public class RadarData {
        private final MutableLiveData<Integer> radarVisible = new MutableLiveData<>(0);
        private final MutableLiveData<Boolean> radarScale = new MutableLiveData<>(true);
        private final MutableLiveData<Boolean> isShowDistanceNotShowLocationUi = new MutableLiveData<>(false);
        private final MutableLiveData<Boolean> isShowLeftTopOneDistanceUi = new MutableLiveData<>(false);
        private final MutableLiveData<Boolean> smallRadar = new MutableLiveData<>(false);

        public RadarData() {
        }

        public MutableLiveData<Integer> getRadarVisible() {
            return this.radarVisible;
        }

        public MutableLiveData<Boolean> getRadarScale() {
            return this.radarScale;
        }

        public MutableLiveData<Boolean> isShowDistanceNotShowLocationUi() {
            return this.isShowDistanceNotShowLocationUi;
        }

        public MutableLiveData<Boolean> isShowLeftTopOneDistanceUi() {
            return this.isShowLeftTopOneDistanceUi;
        }

        public MutableLiveData<Boolean> getSmallRadar() {
            return this.smallRadar;
        }
    }
}
