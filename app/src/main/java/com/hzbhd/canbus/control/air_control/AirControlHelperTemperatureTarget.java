package com.hzbhd.canbus.control.air_control;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl;
import com.hzbhd.canbus.interfaces.AirControlInterface;
import com.hzbhd.canbus.ui_datas.GeneralAirData;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;

public final class AirControlHelperTemperatureTarget extends AirTemperatureControl {
    final AirControlHelper airecontrollerHelper;
    private final Regex unitRegex = new Regex("[℃℉]");
    private final Regex lowRegex = new Regex("[Ll][Oo].*");
    private final Regex highRegex = new Regex("[Hh][Ii].*");

    @Override
    // com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl, com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public void step() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AirControlHelperTemperatureTarget(AirControlHelper airControlHelper) {
        this.airecontrollerHelper = airControlHelper;
    }

    @Override
    public void target(String type, String value) {
        Handler handler = new Handler();
        final String str = "AirTemperatureControl" + SystemClock.elapsedRealtime();

        if (type.equals("absolute")) {
            try {
                targetAbsolute(str, value, new Function0<String>() {
                    @Override
                    public String invoke() {
                        String front_left_temperature = GeneralAirData.front_left_temperature;

                        return front_left_temperature;
                    }
                }, this.airecontrollerHelper.getLeftTemperatureUp(), this.airecontrollerHelper.getLeftTemperatureDown());
                targetAbsolute(str, value, new Function0<String>() {
                    @Override
                    public String invoke() {
                        String front_right_temperature = GeneralAirData.front_right_temperature;

                        return front_right_temperature;
                    }
                }, this.airecontrollerHelper.getRightTemperatureUp(), this.airecontrollerHelper.getRightTemperatureDown());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (type.equals("+") || type.equals("-")) {
            final Ref.FloatRef floatRef = new Ref.FloatRef();
            floatRef.element = Float.parseFloat(type + value);
            long j = 500;
            String front_left_temperature = GeneralAirData.front_left_temperature;

            if (this.lowRegex.matches(front_left_temperature)) {
                String front_right_temperature = GeneralAirData.front_right_temperature;

                if (this.lowRegex.matches(front_right_temperature)) {
                    if (floatRef.element < 0.0f) {
                        return;
                    }
                    this.airecontrollerHelper.getTemperatureUp().step();
                    floatRef.element -= 0.5f;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            airecontrollerHelper.target(str, 0.5f, floatRef.element, airecontrollerHelper.getTemperatureUp(), airecontrollerHelper.getTemperatureDown());
                        }
                    }, j);
                }
            }
            String front_left_temperature2 = GeneralAirData.front_left_temperature;

            if (this.highRegex.matches(front_left_temperature2)) {
                String front_right_temperature2 = GeneralAirData.front_right_temperature;

                if (this.highRegex.matches(front_right_temperature2)) {
                    if (floatRef.element > 0.0f) {
                        return;
                    }
                    this.airecontrollerHelper.getTemperatureDown().step();
                    floatRef.element += 0.5f;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            airecontrollerHelper.target(str, 0.5f, floatRef.element, airecontrollerHelper.getTemperatureUp(), airecontrollerHelper.getTemperatureDown());
                        }
                    }, j);
                }
            }
            j = 0;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    airecontrollerHelper.target(str, 0.5f, floatRef.element, airecontrollerHelper.getTemperatureUp(), airecontrollerHelper.getTemperatureDown());

                }
            }, j);
        }
    }

    private void targetAbsolute(final String tag, final String target, final Function0<String> current, final AirControlInterface up, final AirControlInterface down) {
        float offset;
        long delay = 500;
        if (this.lowRegex.matches(current.invoke())) {
            this.airecontrollerHelper.getTemperatureUp().step();
            offset = 0.5f;
        } else if (this.highRegex.matches(current.invoke())) {
            this.airecontrollerHelper.getTemperatureDown().step();
            offset = -0.5f;
        } else {
            delay = 0;
            offset = 0.0f;
        }

        Handler handler = new Handler();
        final long longDelay = delay;
        handler.postDelayed(() -> {
            float times = Float.parseFloat(target) - Float.parseFloat(this.unitRegex.replace(current.invoke(), ""));
            Log.i("SpeechDebug", "targetAbsolute: delay[" + longDelay + "], offset[" + offset + "], times[" + times + ']');
            this.airecontrollerHelper.target(tag, 0.5f, times - offset, up, down);
        }, delay);
    }
}