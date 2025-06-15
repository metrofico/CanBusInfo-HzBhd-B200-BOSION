package com.hzbhd.canbus.vm.action;

import android.annotation.SuppressLint;
import android.view.WindowManager;

import androidx.lifecycle.MutableLiveData;

import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.action.CanBusAction;
import com.hzbhd.canbus.vm.action.CanBusAction.Main;
import com.hzbhd.canbus.vm.action.CanBusAction.Radar;
import com.hzbhd.canbus.vm.action.CanBusAction.Reverse;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public class CanBusAction {

    /* renamed from: main$delegate, reason: from kotlin metadata */
    private final Main main;

    /* renamed from: radar$delegate, reason: from kotlin metadata */
    private final Radar radar;

    /* renamed from: reverse$delegate, reason: from kotlin metadata */
    private final Reverse reverse;

    public CanBusAction(Vm vm) {
        this.main = new Main();
        this.radar = new Radar();
        this.reverse = new Reverse();

    }

    public final Main getMain() {
        return this.main;
    }

    public final Reverse getReverse() {
        return this.reverse;
    }

    public final Radar getRadar() {
        return this.radar;
    }

    public class Main {
        public Main() {
        }

        public void init() {
            BhdWindowManager.INSTANCE.init(BaseUtil.INSTANCE.getContext());
            BhdWindowManager.INSTANCE.initReverseWindowParams(CanBusAction.this.getReverse().getReverseParams());
        }
    }

    public class Reverse {
        public Reverse() {
        }

        @SuppressLint("WrongConstant")
        public WindowManager.LayoutParams getReverseParams() {
            if (LogUtil.log5()) {
                LogUtil.d("initWindowParam mReverseViewParams: ");
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = 2024;
            layoutParams.flags = 16777512;
            layoutParams.x = 0;
            layoutParams.y = 0;
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.format = 1;
            layoutParams.setTitle("ReverseWindow");
            return layoutParams;
        }

        public void setCustomPanoramicVisible(final boolean visible) {
            if (LogUtil.log5()) {
                LogUtil.d("setCustomPanoramicVisible: " + visible);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getReverse().getCusPanoramicVisible().setValue(visible ? 0 : 8);
                }
            });

        }

        public void setPanoramicVisible(final int visible) {
            if (LogUtil.log5()) {
                LogUtil.d("setPanoramicVisible: " + visible);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getReverse().getPanoramicVisible().setValue(visible);
                }
            });

        }

        public void updatePanoramic() {
            if (LogUtil.log5()) {
                LogUtil.d("updatePanoramic: ");
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    MutableLiveData<Integer> panoramicState = Vm.getVm().getData().getReverse().getPanoramicState();
                    Integer value = Vm.getVm().getData().getReverse().getPanoramicState().getValue();
                    panoramicState.setValue(value.intValue() + 1);
                }
            });

        }
    }

    public class Radar {
        public Radar() {
        }

        public void setRadarVisible(final int visible) {
            if (LogUtil.log5()) {
                LogUtil.d("setRadarVisible: " + visible);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getRadar().getRadarVisible().setValue(visible);
                }
            });

        }

        public void setRadarScale(final boolean scale) {
            if (LogUtil.log5()) {
                LogUtil.d("setRadarScale: " + scale);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getRadar().getRadarScale().setValue(scale);
                }
            });

        }

        public void setShowDistanceNotShowLocationUi(final boolean show) {
            if (LogUtil.log5()) {
                LogUtil.d("setShowDistanceNotShowLocationUi: " + show);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getRadar().isShowDistanceNotShowLocationUi().setValue(show);
                }
            });

        }

        public void setShowLeftTopOneDistanceUi(final boolean show) {
            if (LogUtil.log5()) {
                LogUtil.d("setShowLeftTopOneDistanceUi: " + show);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getRadar().isShowLeftTopOneDistanceUi().setValue(show);
                }
            });

        }

        public void setSmallRadar(final boolean small) {
            if (LogUtil.log5()) {
                LogUtil.d("setSmallRadar: " + small);
            }
            HandlerThreadUtilKt.runUi(new Runnable() {
                @Override
                public void run() {
                    Vm.getVm().getData().getRadar().getSmallRadar().setValue(small);
                }
            });

        }
    }
}
