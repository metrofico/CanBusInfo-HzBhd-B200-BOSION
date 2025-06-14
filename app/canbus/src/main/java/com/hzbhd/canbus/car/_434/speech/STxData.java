package com.hzbhd.canbus.car._434.speech;

/* loaded from: classes2.dex */
public class STxData {
    public static final String TxAction = "com.hzbhd.speech.to.sys";
    public static final String TxType = "tx_type";

    public static class AcCtrl {
        public static final String type = "airctrl";

        public static class Air {
            public static final String action = "action";
            public static final String action_close = "ac.air.close";
            public static final String action_open = "ac.air.open";
            public static final String tx_type = "air";
        }

        public static class Auto {
            public static final String action = "action";
            public static final String action_close = "ac.auto.close";
            public static final String action_open = "ac.auto.open";
            public static final String tx_type = "auto";
        }

        public static class Cold {
            public static final String action = "action";
            public static final String action_close = "ac.cold.close";
            public static final String action_open = "ac.cold.open";
            public static final String tx_type = "cold";
        }

        public static class Defrost {
            public static final String action = "action";
            public static final String action_close = "ac.defrost.close";
            public static final String action_open = "ac.defrost.open";
            public static final String tx_type = "defrost";
        }

        public static class Loop {
            public static final String action = "action";
            public static final String action_inside = "ac.loop.inside";
            public static final String action_outside = "ac.loop.outside";
            public static final String tx_type = "loop";
        }

        public static class Mode {
            public static final String action = "action";
            public static final String action_type = "ac.mode";
            public static final String mode = "mode";
            public static final String mode_face = "face";
            public static final String mode_facefoot = "face.foot";
            public static final String mode_foot = "foot";
            public static final String mode_footdefrost = "foot.defrost";
            public static final String tx_type = "mode";

            public enum ModeEnum {
                face,
                foot,
                facefoot,
                footdefrost
            }
        }

        public static class TempDec {
            public static final String action = "action";
            public static final String action_type = "ac.temp.dec";
            public static final String number = "number";
            public static final String tx_type = "tempdec";
        }

        public static class TempInc {
            public static final String action = "action";
            public static final String action_type = "ac.temp.inc";
            public static final String number = "number";
            public static final String tx_type = "tempinc";
        }

        public static class TempTo {
            public static final String action = "action";
            public static final String action_type = "ac.temp.to";
            public static final String number = "number";
            public static final String tx_type = "tempto";
        }

        public static class Warm {
            public static final String action = "action";
            public static final String action_close = "ac.warm.close";
            public static final String action_open = "ac.warm.open";
            public static final String tx_type = "warm";
        }

        public static class Wind {
            public static final String action = "action";
            public static final String action_down = "ac.wind.down";
            public static final String action_up = "ac.wind.up";
            public static final String tx_type = "wind";
        }

        public static class WindTo {
            public static final String action = "action";
            public static final String action_type = "ac.wind.to";
            public static final String number = "number";
            public static final String tx_type = "windto";
        }
    }

    public static class CarCtrl {
        public static final String type = "carctrl";

        public static class Alarmlight {
            public static final String action = "action";
            public static final String action_close = "car.alarm.light.close";
            public static final String action_open = "car.alarm.light.open";
            public static final String tx_type = "alarmlight";
        }

        public static class Clearancelamps {
            public static final String action = "action";
            public static final String action_close = "car.clearance.lamp.close";
            public static final String action_open = "car.clearance.lamps.open";
            public static final String tx_type = "clearancelamps";
        }

        public static class Headlight {
            public static final String action = "action";
            public static final String action_close = "car.dipped.headlight.close";
            public static final String action_open = "car.dipped.headlight.open";
            public static final String tx_type = "headlight";
        }

        public static class HighBeam {
            public static final String action = "action";
            public static final String action_close = "car.high.beam.close";
            public static final String action_open = "car.high.beam.open";
            public static final String tx_type = "highbeam";
        }

        public static class Lock {
            public static final String action = "action";
            public static final String action_close = "car.lock.close";
            public static final String action_open = "car.lock.open";
            public static final String tx_type = "lock";
        }

        public static class Readinglamp {
            public static final String action = "action";
            public static final String action_close = "car.indoor.lamp.close";
            public static final String action_open = "car.reading.lamp.open";
            public static final String tx_type = "readinglamp";
        }

        public static class System {
            public static final String action = "action";
            public static final String action_sync = "system.status.sync";
            public static final String tx_type = "system";
        }

        public static class Toplight {
            public static final String action = "action";
            public static final String action_close = "car.toplight.close";
            public static final String action_open = "car.toplight.open";
            public static final String tx_type = "toplight";
        }

        public static class WindowClose {
            public static final String action = "action";
            public static final String action_type = "car.window.close";
            public static final String tx_type = "windowclose";
        }

        public static class WindowOpen {
            public static final String action = "action";
            public static final String action_type = "car.window.open";
            public static final String tx_type = "windowopen";
            public static final String type = "type";
            public static final String type_leftfront = "left.front";
            public static final String type_rightfront = "right.front";

            public enum TypeEnum {
                leftfront,
                rightfront
            }
        }

        public static class Wiper {
            public static final String action = "action";
            public static final String action_close = "car.wiper.close";
            public static final String action_open = "car.wiper.open";
            public static final String tx_type = "wiper";
        }

        public static class WiperCtrl {
            public static final String action = "action";
            public static final String action_type = "car.wiper";
            public static final String mode = "mode";
            public static final String mode_intermittent = "intermittent";
            public static final String mode_speedhigh = "speed.high";
            public static final String mode_speedlow = "speed.low";
            public static final String mode_wash = "wash";
            public static final String tx_type = "wiperctrl";

            public enum ModeEnum {
                intermittent,
                speedlow,
                speedhigh,
                wash
            }
        }

        public static class WiperMove {
            public static final String action = "action";
            public static final String action_type = "car.wiper.move";
            public static final String number = "number";
            public static final String tx_type = "wipermove";
        }
    }
}
