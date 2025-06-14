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

/* compiled from: CanBusData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001f\u0010\u0005\u001a\u00060\u0006R\u00020\u00008VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\u000b\u001a\u00060\fR\u00020\u00008VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/canbus/vm/data/CanBusData;", "", "vm", "Lcom/hzbhd/canbus/vm/Vm;", "(Lcom/hzbhd/canbus/vm/Vm;)V", "radar", "Lcom/hzbhd/canbus/vm/data/CanBusData$RadarData;", "getRadar", "()Lcom/hzbhd/canbus/vm/data/CanBusData$RadarData;", "radar$delegate", "Lkotlin/Lazy;", "reverse", "Lcom/hzbhd/canbus/vm/data/CanBusData$ReverseData;", "getReverse", "()Lcom/hzbhd/canbus/vm/data/CanBusData$ReverseData;", "reverse$delegate", "RadarData", "ReverseData", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public class CanBusData {

    /* renamed from: radar$delegate, reason: from kotlin metadata */
    private final Lazy radar;

    /* renamed from: reverse$delegate, reason: from kotlin metadata */
    private final Lazy reverse;

    public CanBusData(Vm vm) {
        Intrinsics.checkNotNullParameter(vm, "vm");
        this.reverse = LazyKt.lazy(new Function0<ReverseData>() { // from class: com.hzbhd.canbus.vm.data.CanBusData$reverse$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CanBusData.ReverseData invoke() {
                return this.this$0.new ReverseData();
            }
        });
        this.radar = LazyKt.lazy(new Function0<RadarData>() { // from class: com.hzbhd.canbus.vm.data.CanBusData$radar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CanBusData.RadarData invoke() {
                return this.this$0.new RadarData();
            }
        });
    }

    public ReverseData getReverse() {
        return (ReverseData) this.reverse.getValue();
    }

    public RadarData getRadar() {
        return (RadarData) this.radar.getValue();
    }

    /* compiled from: CanBusData.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\n0\n0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\"\u0010\f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\"\u0010\r\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\n0\n0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\"\u0010\u000f\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\n0\n0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/vm/data/CanBusData$ReverseData;", "", "(Lcom/hzbhd/canbus/vm/data/CanBusData;)V", "backBtn", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getBackBtn", "()Landroidx/lifecycle/MutableLiveData;", "cusPanoramicVisible", "", "getCusPanoramicVisible", "isReverse", "panoramicState", "getPanoramicState", "panoramicVisible", "getPanoramicVisible", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

    /* compiled from: CanBusData.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0096\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0007R\"\u0010\b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007R\"\u0010\t\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\"\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\f0\f0\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\"\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/hzbhd/canbus/vm/data/CanBusData$RadarData;", "", "(Lcom/hzbhd/canbus/vm/data/CanBusData;)V", "isShowDistanceNotShowLocationUi", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "()Landroidx/lifecycle/MutableLiveData;", "isShowLeftTopOneDistanceUi", "radarScale", "getRadarScale", "radarVisible", "", "getRadarVisible", "smallRadar", "getSmallRadar", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
