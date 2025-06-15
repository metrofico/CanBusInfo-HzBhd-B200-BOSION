package com.hzbhd.canbus.car._448.speech;

import kotlin.Metadata;




public final class SpeechAction {
    public static final String AC_CLOSE = "ac.close";
    public static final String AC_COOL_MAX = "ac.cool.max";
    public static final String AC_DEFROST_BEHIND_CLOSE = "ac.defrost.behind.close";
    public static final String AC_DEFROST_BEHIND_OPEN = "ac.defrost.behind.open";
    public static final String AC_DEFROST_FRONT_CLOSE = "ac.defrost.front.close";
    public static final String AC_DEFROST_FRONT_MAX = "ac.defrost.front.max";
    public static final String AC_DEFROST_FRONT_OPEN = "ac.defrost.front.open";
    public static final String AC_HEAT_MAX = "ac.heat.max";
    public static final String AC_LEFT_RIGHT_SYNC_CLOSE = "ac.left.right.sync.close";
    public static final String AC_LEFT_RIGHT_SYNC_OPEN = "ac.left.right.sync.open";
    public static final String AC_MODE = "ac.mode";
    public static final String AC_OPEN = "ac.open";
    public static final String AC_TEMPERATURE_DEC = "ac.temperature.dec";
    public static final String AC_TEMPERATURE_DOWN = "ac.temperature.down";
    public static final String AC_TEMPERATURE_INC = "ac.temperature.inc";
    public static final String AC_TEMPERATURE_MAX = "ac.temperature.max";
    public static final String AC_TEMPERATURE_MIN = "ac.temperature.min";
    public static final String AC_TEMPERATURE_TO = "ac.temperature.to";
    public static final String AC_TEMPERATURE_UP = "ac.temperature.up";
    public static final String AC_WIND_LEVEL_DOWN = "ac.windlevel.down";
    public static final String AC_WIND_LEVEL_MAX = "ac.windlevel.max";
    public static final String AC_WIND_LEVEL_MIN = "ac.windlevel.min";
    public static final String AC_WIND_LEVEL_TO = "ac.windlevel.to";
    public static final String AC_WIND_LEVEL_UP = "ac.windlevel.up";
    public static final String AIR_AC_OFF = "air.ac.off";
    public static final String AIR_AC_ON = "air.ac.on";
    public static final String AIR_IN_OUT_CYCLE_OFF = "air.in.out.cycle.off";
    public static final String AIR_IN_OUT_CYCLE_ON = "air.in.out.cycle.on";
    public static final String ALL_WINDOWS_CLOSE = "all.windows.close";
    public static final String ALL_WINDOWS_OPEN = "all.windows.open";
    public static final String COLD_CLOSE = "cold.close";
    public static final String COLD_OPEN = "cold.open";
    public static final String DVR_CLOSE = "dvr.close";
    public static final String DVR_OPEN = "dvr.open";
    public static final String DVR_RECORD_START = "dvr.record.start";
    public static final String DVR_RECORD_STOP = "dvr.record.stop";
    public static final String DVR_TAKE_PICTURE = "dvr.take.picture";
    public static final String FRONT_LEFT_SEAT_HEAT_CLOSE = "front.left.seat.heat.close";
    public static final String FRONT_LEFT_SEAT_HEAT_OPEN = "front.left.seat.heat.open";
    public static final String FRONT_LEFT_WINDOW_CLOSE = "front.left.window.close";
    public static final String FRONT_LEFT_WINDOW_OPEN = "front.left.window.open";
    public static final String FRONT_RIGHT_SEAT_HEAT_CLOSE = "front.right.seat.heat.close";
    public static final String FRONT_RIGHT_SEAT_HEAT_OPEN = "front.right.seat.heat.open";
    public static final String FRONT_RIGHT_WINDOW_CLOSE = "front.right.window.close";
    public static final String FRONT_RIGHT_WINDOW_OPEN = "front.right.window.open";
    public static final String FRONT_WINDOWS_CLOSE = "front.windows.close";
    public static final String FRONT_WINDOWS_OPEN = "front.windows.open";
    public static final SpeechAction INSTANCE = new SpeechAction();
    public static final String MIRROR_FOLD = "mirror.fold";
    public static final String MIRROR_UNFOLD = "mirror.unfold";
    public static final String REAR_LEFT_WINDOW_CLOSE = "rear.left.window.close";
    public static final String REAR_LEFT_WINDOW_OPEN = "rear.left.window.open";
    public static final String REAR_RIGHT_WINDOW_CLOSE = "rear.right.window.close";
    public static final String REAR_RIGHT_WINDOW_OPEN = "rear.right.window.open";
    public static final String REAR_WINDOWS_CLOSE = "rear.windows.close";
    public static final String REAR_WINDOWS_OPEN = "rear.windows.open";
    public static final String SKYLIGHT_CLOSE = "skylight.close";
    public static final String SKYLIGHT_CLOSE_HALF = "skylight.close.half";
    public static final String SKYLIGHT_OPEN = "skylight.open";
    public static final String SKYLIGHT_OPEN_HALF = "skylight.open.half";
    public static final String SKYLIGHT_STOP = "skylight.stop";
    public static final String SKYLIGHT_TILTUP = "skylight.tiltup";
    public static final String SUN_VISOR_CLOSE = "sun.visor.close";
    public static final String SUN_VISOR_CLOSE_HALF = "sun.visor.close.half";
    public static final String SUN_VISOR_OPEN = "sun.visor.open";
    public static final String SUN_VISOR_OPEN_HALF = "sun.visor.open.half";
    public static final String SUN_VISOR_STOP = "sun.visor.stop";
    public static final String TAILGATE_CLOSE = "tailgate.close";
    public static final String TAILGATE_OPEN = "tailgate.open";

    private SpeechAction() {
    }

    /* compiled from: SpeechAction.kt */
    
    public enum TypeEnum {
        add("+"),
        less("-"),
        absolute("absolute"),
        left("left"),
        right("right"),
        empty("empty");

        private final String value;

        TypeEnum(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    /* compiled from: SpeechAction.kt */
    
    public enum WindValueEnum {
        face("face"),
        foot("foot"),
        facefoot("face.foot"),
        defrost("defrost"),
        footdefrost("foot.defrost"),
        auto("auto");

        private final String value;

        WindValueEnum(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
