package com.hzbhd.canbus.control.air_control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
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
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AirControlHelper.kt */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 R2\u00020\u0001:\u0001RB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0011J\u0010\u0010C\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010EJ\u0010\u0010F\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010EJ$\u0010G\u001a\u00020A2\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010H\u001a\u0004\u0018\u00010\u00112\b\u0010I\u001a\u0004\u0018\u00010\u0011J\b\u0010J\u001a\u00020AH\u0002J0\u0010K\u001a\u00020A2\u0006\u0010L\u001a\u00020\u00112\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020N2\u0006\u0010P\u001a\u00020E2\u0006\u0010Q\u001a\u00020EH\u0002R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000Rr\u0010\u000f\u001ad\u0012*\u0012(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00100\u0010 \u0012*2\u0012,\b\u0001\u0012(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011 \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u00110\u0011\u0018\u00010\u00100\u0010\u0018\u00010\u00100\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R6\u0010\u0014\u001a(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00150\u0015 \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u00150\u0015\u0018\u00010\u00100\u0010X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010\u001b\u001a(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u001c0\u001c \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010\u001c0\u001c\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R6\u0010#\u001a(\u0012\f\u0012\n \u0012*\u0004\u0018\u00010!0! \u0012*\u0014\u0012\u000e\b\u0001\u0012\n \u0012*\u0004\u0018\u00010!0!\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u0016\u0010%\u001a\n \u0012*\u0004\u0018\u00010&0&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\nR\u0011\u0010*\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\nR\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010.\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020100\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00102\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\nR\u0011\u00104\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\nR\u0011\u00106\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\nR\u0011\u00108\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010<\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b=\u0010;R\u0011\u0010>\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b?\u0010;¨\u0006S"}, d2 = {"Lcom/hzbhd/canbus/control/air_control/AirControlHelper;", "", "mContext", "Landroid/content/Context;", "mAirPageUiSet", "Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;", "(Landroid/content/Context;Lcom/hzbhd/canbus/adapter/bean/AirPageUiSet;)V", "leftTemperatureDown", "Lcom/hzbhd/canbus/control/air_control/temperature/AirTemperatureControl;", "getLeftTemperatureDown", "()Lcom/hzbhd/canbus/control/air_control/temperature/AirTemperatureControl;", "leftTemperatureUp", "getLeftTemperatureUp", "mAirBtnControl", "Lcom/hzbhd/canbus/control/air_control/button/AirBtnControl;", "mFrontAirBtnActions", "", "", "kotlin.jvm.PlatformType", "[[Ljava/lang/String;", "mFrontOnAirBtnClickListeners", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "[Lcom/hzbhd/canbus/adapter/interfaces/OnAirBtnClickListener;", "mHandler", "Landroid/os/Handler;", "mMaxWindLevel", "", "mOnAirTempTouchListeners", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTempTouchListener;", "[Lcom/hzbhd/canbus/adapter/interfaces/OnAirTempTouchListener;", "mOnAirTempTouchListenersLeft", "mOnAirTempTouchListenersRight", "mOnUpDownClickListenerLeft", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "mOnUpDownClickListenerRight", "mOnUpDownClickListeners", "[Lcom/hzbhd/canbus/adapter/interfaces/OnAirTemperatureUpDownClickListener;", "mOnWindSpeedClickListener", "Lcom/hzbhd/canbus/adapter/interfaces/OnAirWindSpeedUpDownClickListener;", "mTemperatureSwitch", "rightTemperatureDown", "getRightTemperatureDown", "rightTemperatureUp", "getRightTemperatureUp", "targetDelay", "", "targetTimers", "Lkotlin/Pair;", "", "Lcom/hzbhd/canbus/util/TimerUtil;", "temperatureDown", "getTemperatureDown", "temperatureTarget", "getTemperatureTarget", "temperatureUp", "getTemperatureUp", "windDecrease", "Lcom/hzbhd/canbus/control/air_control/wind/AirWindControl;", "getWindDecrease", "()Lcom/hzbhd/canbus/control/air_control/wind/AirWindControl;", "windIncrease", "getWindIncrease", "windTarget", "getWindTarget", "airControlAction", "", "action", "airControlMost", "control", "Lcom/hzbhd/canbus/interfaces/AirControlInterface;", "airControlStep", "airControlTarget", "type", "value", "checkTemperatureSwitch", "target", "tag", "step", "", "times", "up", "down", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class AirControlHelper {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float TEMPERATURE_STEP = 0.5f;
    private static final float WIND_STEP = 1.0f;
    private static Timer mTimer;
    private static TimerTask mTimerTask;
    private final AirTemperatureControl leftTemperatureDown;
    private final AirTemperatureControl leftTemperatureUp;
    private AirBtnControl mAirBtnControl;
    private final Context mContext;
    private String[][] mFrontAirBtnActions;
    private OnAirBtnClickListener[] mFrontOnAirBtnClickListeners;
    private final Handler mHandler;
    private int mMaxWindLevel;
    private final OnAirTempTouchListener[] mOnAirTempTouchListeners;
    private OnAirTempTouchListener mOnAirTempTouchListenersLeft;
    private OnAirTempTouchListener mOnAirTempTouchListenersRight;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight;
    private final OnAirTemperatureUpDownClickListener[] mOnUpDownClickListeners;
    private OnAirWindSpeedUpDownClickListener mOnWindSpeedClickListener;
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
            @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                if (ArrayUtils.isEmpty(this.this$0.mOnUpDownClickListeners)) {
                    return;
                }
                OnAirTemperatureUpDownClickListener[] mOnUpDownClickListeners = this.this$0.mOnUpDownClickListeners;
                Intrinsics.checkNotNullExpressionValue(mOnUpDownClickListeners, "mOnUpDownClickListeners");
                for (OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener : mOnUpDownClickListeners) {
                    if (onAirTemperatureUpDownClickListener != null) {
                        onAirTemperatureUpDownClickListener.onClickUp();
                    }
                }
            }
        };
        this.temperatureDown = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$temperatureDown$1
            @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                if (ArrayUtils.isEmpty(this.this$0.mOnUpDownClickListeners)) {
                    return;
                }
                OnAirTemperatureUpDownClickListener[] mOnUpDownClickListeners = this.this$0.mOnUpDownClickListeners;
                Intrinsics.checkNotNullExpressionValue(mOnUpDownClickListeners, "mOnUpDownClickListeners");
                for (OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener : mOnUpDownClickListeners) {
                    if (onAirTemperatureUpDownClickListener != null) {
                        onAirTemperatureUpDownClickListener.onClickDown();
                    }
                }
            }
        };
        this.leftTemperatureUp = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$leftTemperatureUp$1
            @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                this.this$0.checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.this$0.mOnUpDownClickListenerLeft;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickUp();
                }
            }
        };
        this.leftTemperatureDown = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$leftTemperatureDown$1
            @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                this.this$0.checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.this$0.mOnUpDownClickListenerLeft;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickDown();
                }
            }
        };
        this.rightTemperatureUp = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$rightTemperatureUp$1
            @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                this.this$0.checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.this$0.mOnUpDownClickListenerRight;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickUp();
                }
            }
        };
        this.rightTemperatureDown = new AirTemperatureControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$rightTemperatureDown$1
            @Override // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                this.this$0.checkTemperatureSwitch();
                OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener = this.this$0.mOnUpDownClickListenerRight;
                if (onAirTemperatureUpDownClickListener != null) {
                    onAirTemperatureUpDownClickListener.onClickDown();
                }
            }
        };
        this.temperatureTarget = new AirControlHelper$temperatureTarget$1(this);
        this.windDecrease = new AirWindControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$windDecrease$1
            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public boolean isComplete() {
                return GeneralAirData.front_wind_level <= 1;
            }

            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = this.this$0.mOnWindSpeedClickListener;
                if (onAirWindSpeedUpDownClickListener != null) {
                    onAirWindSpeedUpDownClickListener.onClickLeft();
                }
            }
        };
        this.windIncrease = new AirWindControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$windIncrease$1
            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public boolean isComplete() {
                return GeneralAirData.front_wind_level >= this.this$0.mMaxWindLevel;
            }

            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = this.this$0.mOnWindSpeedClickListener;
                if (onAirWindSpeedUpDownClickListener != null) {
                    onAirWindSpeedUpDownClickListener.onClickRight();
                }
            }
        };
        this.windTarget = new AirWindControl() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$windTarget$1
            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public boolean isComplete() {
                return false;
            }

            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void most() {
            }

            @Override // com.hzbhd.canbus.control.air_control.wind.AirWindControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void step() {
            }

            @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
            public void target(String type, String value) throws NumberFormatException {
                float f;
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(value, "value");
                AirControlHelper airControlHelper = this.this$0;
                String str = "AirWindControl" + SystemClock.elapsedRealtime();
                try {
                    int iHashCode = type.hashCode();
                    if (iHashCode != 43) {
                        if (iHashCode != 45) {
                            if (iHashCode == 1728122231 && type.equals("absolute")) {
                                f = Float.parseFloat(value) - GeneralAirData.front_wind_level;
                                airControlHelper.target(str, 1.0f, f, this.this$0.getWindIncrease(), this.this$0.getWindDecrease());
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
                    airControlHelper.target(str, 1.0f, f, this.this$0.getWindIncrease(), this.this$0.getWindDecrease());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /* compiled from: AirControlHelper.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/hzbhd/canbus/control/air_control/AirControlHelper$Companion;", "", "()V", "TEMPERATURE_STEP", "", "WIND_STEP", "mTimer", "Ljava/util/Timer;", "mTimerTask", "Ljava/util/TimerTask;", "startTimer", "", "timerTask", "delay", "", "period", "", "stopTimer", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ void startTimer$default(Companion companion, TimerTask timerTask, long j, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                i = 0;
            }
            companion.startTimer(timerTask, j, i);
        }

        public final void startTimer(TimerTask timerTask, long delay, int period) {
            Intrinsics.checkNotNullParameter(timerTask, "timerTask");
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

        public final void stopTimer() {
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
    }

    public final void airControlStep(AirControlInterface control) {
        if (control == null) {
            return;
        }
        control.reset();
        control.step();
    }

    public final void airControlMost(AirControlInterface control) {
        if (control == null) {
            return;
        }
        control.reset();
        control.most();
    }

    public final void airControlTarget(AirControlInterface control, String type, String value) {
        if (control == null) {
            return;
        }
        control.reset();
        control.target(type, value);
    }

    public final void airControlAction(String action) {
        this.mAirBtnControl.action(action);
    }

    public final AirTemperatureControl getTemperatureUp() {
        return this.temperatureUp;
    }

    public final AirTemperatureControl getTemperatureDown() {
        return this.temperatureDown;
    }

    public final AirTemperatureControl getLeftTemperatureUp() {
        return this.leftTemperatureUp;
    }

    public final AirTemperatureControl getLeftTemperatureDown() {
        return this.leftTemperatureDown;
    }

    public final AirTemperatureControl getRightTemperatureUp() {
        return this.rightTemperatureUp;
    }

    public final AirTemperatureControl getRightTemperatureDown() {
        return this.rightTemperatureDown;
    }

    public final AirTemperatureControl getTemperatureTarget() {
        return this.temperatureTarget;
    }

    public final AirWindControl getWindDecrease() {
        return this.windDecrease;
    }

    public final AirWindControl getWindIncrease() {
        return this.windIncrease;
    }

    public final AirWindControl getWindTarget() {
        return this.windTarget;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkTemperatureSwitch() {
        if (this.mTemperatureSwitch != CommUtil.isAirTemperatureSwitch(this.mContext)) {
            this.mTemperatureSwitch = CommUtil.isAirTemperatureSwitch(this.mContext);
            if (!ArrayUtils.isEmpty(this.mOnUpDownClickListeners)) {
                OnAirTemperatureUpDownClickListener[] onAirTemperatureUpDownClickListenerArr = this.mOnUpDownClickListeners;
                int i = this.mTemperatureSwitch;
                this.mOnUpDownClickListenerLeft = onAirTemperatureUpDownClickListenerArr[i == 1 ? (char) 2 : (char) 0];
                this.mOnUpDownClickListenerRight = onAirTemperatureUpDownClickListenerArr[i == 1 ? (char) 0 : (char) 2];
            }
            if (ArrayUtils.isEmpty(this.mOnAirTempTouchListeners)) {
                return;
            }
            OnAirTempTouchListener[] onAirTempTouchListenerArr = this.mOnAirTempTouchListeners;
            int i2 = this.mTemperatureSwitch;
            this.mOnAirTempTouchListenersLeft = onAirTempTouchListenerArr[i2 == 1 ? (char) 2 : (char) 0];
            this.mOnAirTempTouchListenersRight = onAirTempTouchListenerArr[i2 == 1 ? (char) 0 : (char) 2];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void target(String tag, float step, float times, final AirControlInterface up, AirControlInterface down) {
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
                Iterator<T> it = second.iterator();
                while (it.hasNext()) {
                    ((TimerUtil) it.next()).stopTimer();
                }
            }
            this.targetTimers = new Pair<>(tag, CollectionsKt.arrayListOf(timerUtil));
        }
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.control.air_control.AirControlHelper$target$2$1
            private int i;

            public final int getI() {
                return this.i;
            }

            public final void setI(int i) {
                this.i = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.i < iAbs) {
                    up.step();
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                    this.targetDelay -= 200;
                    Log.i("SpeechDebug", "target: finish delay " + this.targetDelay);
                }
            }
        }, this.targetDelay, 500L);
    }
}
