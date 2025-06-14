package com.hzbhd.common.settings.constant;

import com.hzbhd.constant.share.lcd.LcdInfoShare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class BodaSysContant {
    public static final String AudioWhiteIntent = "KEY_FORCE_BACKPLAYAUDIO_THRIDAPP_WHITE_LIST";
    public static final String BrowserIntent = "car_browser_intent";
    public static final String BubbleState = "bubble_state";
    public static final String DisableHDMI = "HZBHD_DISABLE_HDMI";
    public static final String DisableRotation = "HZBHD_DISABLE_ROTATION";
    public static final String DvrIntent = "car_dvr_intent";
    public static final String FullScreenIntent = "KEY_FORCE_FULL_SCREEN_THRIDAPP_WHITE_LIST";
    public static final String IRCurVersion = "IRCurVersion";
    public static final String OriginalScreenTest = "ORIGINAL_SCREEN_TEST";
    public static final String ScreenshotLocation = "hzbhd_screenshot_location";
    public static final String ShowVirtualKey = "VIRTUAL_KEY_SHOW";
    public static final String SwitchSeat = "switch_seat";
    public static final String unknown = "unknown";

    public static final class Brightness {
        public static final String ActionKeyCurrentStatus = "currentBrightness";
        public static final String ActionStatus = "com.hzbhd.action.brightnesschange";
        public static final int MAX = 5;
        public static final int MIN = 1;
    }

    public static final class Can {
        public static final String AirDisplaySetup = "hzbhd_air_display_setup";
        public static final String CanEQ = "factory_module_amplifier";
        public static final String CanSystemVersion = "can_system_version";
        public static final String CarType = "hzbhd_car_type";
        public static final String HzbhdRefreshCanVersion = "hzbhdrefreshCanVersion";
        public static final String RadarDisp = "RADAR_DISP_CHECK";
        public static final String RefreshCanVersion = "refreshCanVersion";
        public static final String SwitchAcTemperature = "hzbhd_switch_ac_temperature";
    }

    public static final class CarSettings {

        public static final class Sign {
            public static final String Click = "click";
            public static final String DvrSelect = "dvrselect";
            public static final String Factory = "factory";
            public static final String GeneralFragment = "generalfragment";
            public static final String LedFragment = "ledsetfragment";
            public static final String LogoFragment = "carlogoselectfragment";
            public static final String Main = "main";
            public static final String NaviFragment = "navifragment";
            public static final String SwcFragment = "swcfragment";
            public static final String Tips = "tips";
            public static final String Tips_Click = "tips&click";
            public static final String VolFragment = "volsetfragment";
        }

        public static final class To {
            public static final String KEY = "car.settings.action";
            public static final String NaviValue = "main.navifragment.tips.stvradertrack";
            public static final String NaviValue2 = "main.navifragment.click.stvradertrack";
            public static final String NaviValue3 = "factory.navifragment.tips&click.stvradertrack";
            public static final String SelectCarLogo = "main.carlogoselectfragment";
            public static final String SelectDvr = "main.generalfragment.tips.dvrselect";
            public static final String SelectLedColor = "main.ledsetfragment";
            public static final String SelectNaviApp = "main.navifragment";
            public static final String SelectSwc = "main.swcfragment";
            public static final String SelectVol = "main.volsetfragment";
        }
    }

    public static final class Carema {
        public static final String AvmDelayFinish = "carsettings.avm.delay.finsh";
        public static final String SwitchFrontCamera = "hzbhd_switch_front_camera";
    }

    public static final class Navi {
        public static final String NaviChangedAction = "com.hzbhd.action.NAVI_CHANGED";
        public static final String NaviIntent = "car_navi_intent";
        public static final String NaviPackageNameExtra = "naviPackageName";
    }

    public static final class Radio {

        public static final class Module {
            public static final int MCU_Control = 0;
            public static final int MPU_6686 = 4;
            public static final int MPU_7708 = 2;
            public static final int MPU_7786 = 3;
            public static final int MPU_ST7707 = 1;
        }
    }

    public enum SETTING_SET {
        none,
        reset
    }

    public static final class SplitScreen {
        public static final String ActionToN = "com.hzbhd.split_screen";
        public static final String OpenKey = "split_screen";
        public static final String SwitchSplit = "SWITCH_SPLIT";
    }

    public static final class System {

        public static final class Attributes {
            public static final String _701F = "701F";
            public static final String _N3 = "N3";
            public static final String _NVP6158C = "NVP6158C";
            public static final String _STM32 = "STM32";
            public static final String _STM8 = "STM8";
        }

        public static final class BUILD {
            public static final String BspVersionName = "ro.bhd.bsp.version";
            public static final String BuildVersion = "build.version";
            public static final String FrameworkVersionName = "ro.bhd.framework.version";
            public static final String HardwardBoard = "persist.bhd.hardward_board";
            public static final String SerialNumber = "null";
        }
    }

    public enum TypeDef {
        SETTING,
        GET
    }

    public static final class BT {
        public static final String VerInfo = "hzbhd_bt_version_info";

        public enum Module {
            GOC806A("GOC", 0),
            IVT140("IVT", 1),
            SUD("SUD", 2),
            SUD851("SUD-851", 3),
            BLINK("BLINK", 4),
            IVTI35("IVT-I35", 5),
            SUD828("SUD-828", 6),
            IVTI159("IVT-I159", 7),
            BLINKNETWORK("BLINK_NETWORK", 8),
            GOCWA020("GOC-WA020", 9),
            BLINK161("BLINK161", 10),
            GOCQD041("GOC-QD041", 11),
            GOCRF210("GOC-RF210", 12),
            GOC3008("GOC-3008", 13);

            private int code;
            private String name;

            Module(String str, int i) {
                this.name = str;
                this.code = i;
            }

            public int getCode() {
                return this.code;
            }

            public String getName() {
                return this.name;
            }

            public static List toList() {
                ArrayList arrayList = new ArrayList();
                for (Module module : values()) {
                    HashMap map = new HashMap();
                    map.put("code", Integer.valueOf(module.getCode()));
                    map.put(LcdInfoShare.MediaInfo.name, module.getName());
                    arrayList.add(map);
                }
                return arrayList;
            }
        }
    }
}
