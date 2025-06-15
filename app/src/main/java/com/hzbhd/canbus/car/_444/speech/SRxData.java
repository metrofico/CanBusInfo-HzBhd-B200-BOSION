package com.hzbhd.canbus.car._444.speech;


public class SRxData {
    public static final String RxAction = "com.hzbhd.sys.to.speech";
    public static final String RxType = "rx_type";

    public static class Ac {

        public static class Air {
            public static final String action = "action";
            public static final String action_type = "ac.air.status";
            public static final String rx_type = "air";
            public static final String type = "type";
        }

        public static class Auto {
            public static final String action = "action";
            public static final String action_type = "ac.auto.status";
            public static final String rx_type = "auto";
            public static final String type = "type";
        }

        public static class Cold {
            public static final String action = "action";
            public static final String action_type = "ac.cold.status";
            public static final String rx_type = "cold";
            public static final String type = "type";
        }

        public static class Defrost {
            public static final String action = "action";
            public static final String action_type = "ac.defrost.status";
            public static final String rx_type = "defrost";
            public static final String type = "type";
        }

        public static class Loop {
            public static final String action = "action";
            public static final String action_type = "ac.loop";
            public static final String rx_type = "loop";
            public static final String type = "type";
        }

        public static class Mode {
            public static final String action = "action";
            public static final String action_type = "ac.mode";
            public static final String mode = "mode";
            public static final String mode_face = "face";
            public static final String mode_facefoot = "face.foot";
            public static final String mode_foot = "foot";
            public static final String mode_footdefrost = "foot.defrost";
            public static final String mode_other = "other";
            public static final String rx_type = "mode";

            public enum ModeEnum {
                face,
                foot,
                facefoot,
                footdefrost,
                other
            }
        }

        public static class Temp {
            public static final String action = "action";
            public static final String action_type = "ac.temp.change";
            public static final String number = "number";
            public static final String rx_type = "temp";
        }

        public static class Warm {
            public static final String action = "action";
            public static final String action_type = "ac.warm.status";
            public static final String rx_type = "warm";
            public static final String type = "type";
        }

        public static class Wind {
            public static final String action = "action";
            public static final String action_type = "ac.wind.change";
            public static final String number = "number";
            public static final String rx_type = "wind";
        }
    }

    public static class Car {

        public static class AlarmLight {
            public static final String action = "action";
            public static final String action_type = "car.alarm.light.status";
            public static final String rx_type = "alarmlight";
            public static final String type = "type";
        }

        public static class Clearancelamps {
            public static final String action = "action";
            public static final String action_type = "car.clearance.lamps.status";
            public static final String rx_type = "clearancelamps";
            public static final String type = "type";
        }

        public static class Headlight {
            public static final String action = "action";
            public static final String action_type = "car.dipped.headlight.status";
            public static final String rx_type = "headlight";
            public static final String type = "type";
        }

        public static class Highbeam {
            public static final String action = "action";
            public static final String action_type = "car.high.beam.status";
            public static final String rx_type = "highbeam";
            public static final String type = "type";
        }

        public static class Lock {
            public static final String action = "action";
            public static final String action_type = "car.lock.status";
            public static final String rx_type = "lock";
            public static final String type = "type";
        }

        public static class Readinglamp {
            public static final String action = "action";
            public static final String action_type = "car.reading.lamp.status";
            public static final String rx_type = "readinglamp";
            public static final String type = "type";
        }

        public static class Speed {
            public static final String action = "action";
            public static final String action_type = "car.speed.status";
            public static final String rx_type = "speed";
            public static final String type = "type";
        }

        public static class Toplight {
            public static final String action = "action";
            public static final String action_type = "car.toplight.status";
            public static final String rx_type = "toplight";
            public static final String type = "type";
        }

        public static class Window {
            public static final String action = "action";
            public static final String action_type = "car.window.status";
            public static final String leftfront = "left.front";
            public static final String rightfront = "right.front";
            public static final String rx_type = "window";
        }

        public static class Wiper {
            public static final String action = "action";
            public static final String action_type = "car.wiper.status";
            public static final String rx_type = "wiper";
            public static final String type = "type";
        }

        public static class Wipermode {
            public static final String action = "action";
            public static final String action_type = "car.wiper.mode";
            public static final String intermittent = "intermittent";
            public static final String rx_type = "wipermode";
            public static final String speedhigh = "speed.high";
            public static final String speedlow = "speed.low";
            public static final String wash = "wash";
        }
    }

    public static class Speech {

        public static class Speak {
            public static final String action = "action";
            public static final String rx_type = "speech.speak";
        }

        public static class Window {
            public static final String rx_type = "speech.window";
            public static final String type = "type";
        }
    }
}
