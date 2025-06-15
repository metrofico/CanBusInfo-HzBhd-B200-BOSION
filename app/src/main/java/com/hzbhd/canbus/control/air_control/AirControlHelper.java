package com.hzbhd.canbus.control.air_control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.control.air_control.button.AirBtnControl;
import com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl;
import com.hzbhd.canbus.control.air_control.wind.AirWindControl;
import com.hzbhd.canbus.interfaces.AirControlInterface;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.TimerUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;


public final class AirControlHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    private static final float TEMPERATURE_STEP = 0.5f;
    private static final float WIND_STEP = 1.0f;
    private static Timer mTimer;
    private static TimerTask mTimerTask;
    private final AirTemperatureControl leftTemperatureDown;
    private final AirTemperatureControl leftTemperatureUp;
    private final AirBtnControl mAirBtnControl;
    private final Context mContext;
    private final String[][] mFrontAirBtnActions;
    private final OnAirBtnClickListener[] mFrontOnAirBtnClickListeners;
    private final Handler mHandler;
    private final int mMaxWindLevel;
    private final OnAirTempTouchListener[] mOnAirTempTouchListeners;
    private OnAirTempTouchListener mOnAirTempTouchListenersLeft;
    private OnAirTempTouchListener mOnAirTempTouchListenersRight;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight;
    private final OnAirTemperatureUpDownClickListener[] mOnUpDownClickListeners;
    private final OnAirWindSpeedUpDownClickListener mOnWindSpeedClickListener;
    private int mTemperatureSwitch;
    private final AirTemperatureControl rightTemperatureDown;
    private final AirTemperatureControl rightTemperatureUp;
    private long targetDelay;
    private Pair<String, ? extends List<TimerUtil>> targetTimers;
    private final AirTemperatureControl temperatureDown;
    private final AirTemperatureControl temperatureTarget;
    private final AirTemperatureControl temperatureUp;
    private final AirWindControl windDecrease;
    private final AirWindControl windIncrease;
    private final AirWindControl windTarget;

    public AirControlHelper(Context mContext, AirPageUiSet mAirPageUiSet) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mAirPageUiSet, "mAirPageUiSet");
        this.mContext = mContext;
        this.mTemperatureSwitch = -1;
        this.targetDelay = -200L;
        this.mOnUpDownClickListeners = mAirPageUiSet.getFrontArea().getTempSetViewOnUpDownClickListeners();
        this.mOnAirTempTouchListeners = mAirPageUiSet.getFrontArea().getTempTouchListeners();
        this.mOnWindSpeedClickListener = mAirPageUiSet.getFrontArea().getSetWindSpeedViewOnClickListener();
        this.mMaxWindLevel = mAirPageUiSet.getFrontArea().getWindMaxLevel();
        this.mFrontAirBtnActions = mAirPageUiSet.getFrontArea().getLineBtnAction();
        this.mFrontOnAirBtnClickListeners = mAirPageUiSet.getFrontArea().getOnAirBtnClickListeners();
        this.mAirBtnControl = new AirBtnControl(this.mFrontAirBtnActions, this.mFrontOnAirBtnClickListeners);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.temperatureUp = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$temperatureUp$1
            @Override
            // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                for (OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener : mOnUpDownClickListeners) {
                    if (onAirTemperatureUpDownClickListener != null) {
                        onAirTemperatureUpDownClickListener.onClickUp();
                    }
                }
            }
        };
        this.temperatureDown = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$temperatureDown$1
            @Override
            // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                for (OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener : mOnUpDownClickListeners) {
                    if (onAirTemperatureUpDownClickListener != null) {
                        onAirTemperatureUpDownClickListener.onClickDown();
                    }
                }
            }
        };
        this.leftTemperatureUp = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$leftTemperatureUp$1
            @Override
            // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                checkTemperatureSwitch();
                if (mOnUpDownClickListenerLeft != null) {
                    mOnUpDownClickListenerLeft.onClickUp();
                }
            }
        };
        this.leftTemperatureDown = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$leftTemperatureDown$1
            @Override
            // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = mOnUpDownClickListenerLeft;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickDown();
                }
            }
        };
        this.rightTemperatureUp = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$rightTemperatureUp$1
            @Override
            // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = mOnUpDownClickListenerRight;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickUp();
                }
            }
        };
        this.rightTemperatureDown = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$rightTemperatureDown$1
            @Override
            // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = mOnUpDownClickListenerRight;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickDown();
                }
            }
        };
        this.temperatureTarget = new AirControlHelperTemperatureTarget(this);
        this.windDecrease = new AirWindControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$windDecrease$1
            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public boolean isComplete() {
                return GeneralAirData.front_wind_level <= 1;
            }

            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = mOnWindSpeedClickListener;
                if (onAirWindSpeedUpDownClickListener != null) {
                    onAirWindSpeedUpDownClickListener.onClickLeft();
                }
            }
        };
        this.windIncrease = new AirWindControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$windIncrease$1
            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public boolean isComplete() {
                return GeneralAirData.front_wind_level >= mMaxWindLevel;
            }

            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = mOnWindSpeedClickListener;
                if (onAirWindSpeedUpDownClickListener != null) {
                    onAirWindSpeedUpDownClickListener.onClickRight();
                }
            }
        };
        this.windTarget = new AirWindControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$windTarget$1
            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public boolean isComplete() {
                return false;
            }

            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void most() {
            }

            @Override
            // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
            }

            @Override
            // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void target(String type, String value) throws NumberFormatException {
                float f;
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(value, "value");
                String str = "AirWindControl" + SystemClock.elapsedRealtime();
                try {
                    int iHashCode = type.hashCode();
                    if (iHashCode != 43) {
                        if (iHashCode != 45) {
                            if (iHashCode == 1728122231 && type.equals("absolute")) {
                                f = Float.parseFloat(value) - GeneralAirData.front_wind_level;
                                AirControlHelper.this.target(str, 1.0f, f, getWindIncrease(), getWindDecrease());
                            }
                            return;
                        }
                        if (!type.equals("-")) {
                            return;
                        }
                    } else if (!type.equals("+")) {
                        return;
                    }
                    StringBuilder sbAppend = new StringBuilder().append(type);
                    if (Intrinsics.areEqual(value, "")) {
                        value = "1";
                    }
                    f = Float.parseFloat(sbAppend.append(value).toString());
                    AirControlHelper.this.target(str, 1.0f, f, getWindIncrease(), getWindDecrease());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void startTimer(TimerTask timerTask, long delay, int period) {
        if (AirControlHelper.mTimer == null) {
            AirControlHelper.mTimer = new Timer();
        }
        AirControlHelper.mTimerTask = timerTask;
        if (period == 0) {
            Timer timer = AirControlHelper.mTimer;
            Intrinsics.checkNotNull(timer);
            timer.schedule(AirControlHelper.mTimerTask, delay);
        } else {
            Timer timer2 = AirControlHelper.mTimer;
            Intrinsics.checkNotNull(timer2);
            timer2.schedule(AirControlHelper.mTimerTask, delay, period);
        }
    }

    public static void stopTimer() {
        TimerTask timerTask = AirControlHelper.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
        }
        AirControlHelper.mTimerTask = null;
        Timer timer = AirControlHelper.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        AirControlHelper.mTimer = null;
    }

    public void airControlStep(AirControlInterface control) {
        if (control == null) {
            return;
        }
        control.reset();
        control.step();
    }

    public void airControlMost(AirControlInterface control) {
        if (control == null) {
            return;
        }
        control.reset();
        control.most();
    }

    public void airControlTarget(AirControlInterface control, String type, String value) {
        if (control == null) {
            return;
        }
        control.reset();
        control.target(type, value);
    }

    public void airControlAction(String action) {
        this.mAirBtnControl.action(action);
    }

    public AirTemperatureControl getTemperatureUp() {
        return this.temperatureUp;
    }

    public AirTemperatureControl getTemperatureDown() {
        return this.temperatureDown;
    }

    public AirTemperatureControl getLeftTemperatureUp() {
        return this.leftTemperatureUp;
    }

    public AirTemperatureControl getLeftTemperatureDown() {
        return this.leftTemperatureDown;
    }

    public AirTemperatureControl getRightTemperatureUp() {
        return this.rightTemperatureUp;
    }

    public AirTemperatureControl getRightTemperatureDown() {
        return this.rightTemperatureDown;
    }

    public AirTemperatureControl getTemperatureTarget() {
        return this.temperatureTarget;
    }

    public AirWindControl getWindDecrease() {
        return this.windDecrease;
    }

    public AirWindControl getWindIncrease() {
        return this.windIncrease;
    }

    public AirWindControl getWindTarget() {
        return this.windTarget;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkTemperatureSwitch() {
        if (this.mTemperatureSwitch != CommUtil.isAirTemperatureSwitch(this.mContext)) {
            this.mTemperatureSwitch = CommUtil.isAirTemperatureSwitch(this.mContext);
            if (mOnUpDownClickListeners.length > 0) {
                OnAirTemperatureUpDownClickListener[] onAirTemperatureUpDownClickListenerArr = this.mOnUpDownClickListeners;
                int i = this.mTemperatureSwitch;
                this.mOnUpDownClickListenerLeft = onAirTemperatureUpDownClickListenerArr[i == 1 ? (char) 2 : (char) 0];
                this.mOnUpDownClickListenerRight = onAirTemperatureUpDownClickListenerArr[i == 1 ? (char) 0 : (char) 2];
            }
            if (mOnAirTempTouchListeners.length == 0) {
                return;
            }
            OnAirTempTouchListener[] onAirTempTouchListenerArr = this.mOnAirTempTouchListeners;
            int i2 = this.mTemperatureSwitch;
            this.mOnAirTempTouchListenersLeft = onAirTempTouchListenerArr[i2 == 1 ? (char) 2 : (char) 0];
            this.mOnAirTempTouchListenersRight = onAirTempTouchListenerArr[i2 == 1 ? (char) 0 : (char) 2];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void target(String tag, float step, float times, AirControlInterface up, AirControlInterface down) {
        List<TimerUtil> second;
        List<TimerUtil> second2;
        if (times <= 0.0f) {
            if (times >= 0.0f) {
                return;
            } else {
                up = down;
            }
        }
        up.reset();
        final int iAbs = (int) (Math.abs(times) / step);
        this.targetDelay += 200;
        Log.i("SpeechDebug", "target: start delay " + this.targetDelay);
        final TimerUtil timerUtil = new TimerUtil();
        Pair<String, ? extends List<TimerUtil>> pair = this.targetTimers;
        if (Intrinsics.areEqual(pair != null ? pair.getFirst() : null, tag)) {
            Pair<String, ? extends List<TimerUtil>> pair2 = this.targetTimers;
            if (pair2 != null && (second2 = pair2.getSecond()) != null) {
                second2.add(timerUtil);
            }
        } else {
            Pair<String, ? extends List<TimerUtil>> pair3 = this.targetTimers;
            if (pair3 != null && (second = pair3.getSecond()) != null) {
                for (TimerUtil util : second) {
                    util.stopTimer();
                }
            }
            this.targetTimers = new Pair<>(tag, CollectionsKt.arrayListOf(timerUtil));
        }
        AirControlInterface finalUp = up;
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$target$2$1
            private int i;

            public int getI() {
                return this.i;
            }

            public void setI(int i) {
                this.i = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.i < iAbs) {
                    finalUp.step();
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                    AirControlHelper
                            .this.targetDelay -= 200;
                    Log.i("SpeechDebug", "target: finish delay " + AirControlHelper
                            .this.targetDelay);
                }
            }
        }, this.targetDelay, 500L);
    }
}
