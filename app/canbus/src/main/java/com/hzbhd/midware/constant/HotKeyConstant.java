package com.hzbhd.midware.constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class HotKeyConstant {
    public static final int K_0 = 32;
    public static final int K_1 = 33;
    public static final int K_10 = 42;
    public static final int K_1_LONG = 79;
    public static final int K_1_PICKUP = 467;
    public static final int K_2 = 34;
    public static final int K_2_HANGUP = 468;
    public static final int K_2_LONG = 80;
    public static final int K_3 = 35;
    public static final int K_360_CAMERA_ACTION = 4117;
    public static final int K_3_LONG = 81;
    public static final int K_3_SHUFFLE = 469;
    public static final int K_4 = 36;
    public static final int K_4_LONG = 82;
    public static final int K_4_REPEAT = 470;
    public static final int K_5 = 37;
    public static final int K_5_LONG = 83;
    public static final int K_5_REPEAT = 471;
    public static final int K_6 = 38;
    public static final int K_6_LONG = 84;
    public static final int K_6_SHUFFLE = 472;
    public static final int K_7 = 39;
    public static final int K_8 = 40;
    public static final int K_9 = 41;
    public static final int K_AA = 4121;
    public static final int K_AC = 253;
    public static final int K_ACTION_AA = 4126;
    public static final int K_ACTION_APP = 4110;
    public static final int K_ACTION_BT = 4111;
    public static final int K_ACTION_CP = 4127;
    public static final int K_ACTION_MEDIA = 4113;
    public static final int K_ACTION_RADIO = 4112;
    public static final int K_AIR_AC = 366;
    public static final int K_AIR_AUTO = 365;
    public static final int K_AIR_FRONT_DEFOG = 363;
    public static final int K_AIR_IN_OUT_CYCLE = 364;
    public static final int K_AIR_POWER = 367;
    public static final int K_AIR_TEMP_DOWN = 361;
    public static final int K_AIR_TEMP_UP = 362;
    public static final int K_AIR_WIND_DEC = 359;
    public static final int K_AIR_WIND_INC = 360;
    public static final int K_AM = 77;
    public static final int K_ANDROIDAUTO = 226;
    public static final int K_ANGLE = 55;
    public static final int K_APPS = 146;
    public static final int K_APP_HOTKEY = 4099;
    public static final int K_APP_MUTE = 4098;
    public static final int K_APP_PROCESS = 4120;
    public static final int K_AS = 75;
    public static final int K_AS_PREVIEW = 74;
    public static final int K_AUDIO = 53;
    public static final int K_AUX = 141;
    public static final int K_AVM = 209;
    public static final int K_BAND = 62;
    public static final int K_BAND_HANGUP = 203;
    public static final int K_BAND_PICKUP = 199;
    public static final int K_BCAMERA = 153;
    public static final int K_BEEP = 4096;
    public static final int K_BK_LIGHT = 133;
    public static final int K_BK_LIGHT_ON_OFF = 152;
    public static final int K_BLINK = 220;
    public static final int K_BRIGHTNESS_DECREASE = 222;
    public static final int K_BRIGHTNESS_INCREASE = 221;
    public static final int K_BT = 68;
    public static final int K_BT_MUSIC = 140;
    public static final int K_CALL = 4118;
    public static final int K_CAN_CONFIG = 185;
    public static final int K_CARPLAY = 227;
    public static final int K_CARPLAY_SIRI = 198;
    public static final int K_CLEAR = 43;
    public static final int K_CMD_REVERSE = 4114;
    public static final int K_DAB = 210;
    public static final int K_DARK_MODE = 223;
    public static final int K_DBB = 6;
    public static final int K_DELETE = 150;
    public static final int K_DISP = 182;
    public static final int K_DISPLAY = 57;
    public static final int K_DOWN = 46;
    public static final int K_DOWN_HANGUP = 466;
    public static final int K_DVD = 130;
    public static final int K_DVD_MENU = 16;
    public static final int K_EJECT = 31;
    public static final int K_ENTER = 49;
    public static final int K_EQ = 4;
    public static final int K_FAD_BAL = 5;
    public static final int K_FASTB = 23;
    public static final int K_FASTF = 22;
    public static final int K_FCAMERA = 149;
    public static final int K_FF = 90;
    public static final int K_FIRST_RUNNING = 4107;
    public static final int K_FM = 76;
    public static final int K_GOTO = 44;
    public static final int K_HANDFREE = 138;
    public static final int K_HOME = 52;
    public static final int K_HOST = 132;
    public static final int K_HOUR = 183;
    public static final int K_LEFT = 47;
    public static final int K_LOC = 89;
    public static final int K_LONG_SIRI = 228;
    public static final int K_LOUDNESS = 148;
    public static final int K_MENU = 151;
    public static final int K_MIC_OFF = 135;
    public static final int K_MIC_ON = 136;
    public static final int K_MODE_HANGUP = 200;
    public static final int K_MPU_RESET = 4102;
    public static final int K_MUSIC = 59;
    public static final int K_MUTE = 3;
    public static final int K_MUTE_HANGUP = 184;
    public static final int K_MUTE_PHONE_ON_OUT = 464;
    public static final int K_NAVI = 128;
    public static final int K_NEWCANBUS_ACTIVITY = 217;
    public static final int K_NEWCANBUS_AIR_ACTIVITY = 218;
    public static final int K_NEXT = 20;
    public static final int K_NEXT_HANGUP = 207;
    public static final int K_NEXT_REAR = 72;
    public static final int K_NONE = 0;
    public static final int K_NUMBER = 64;
    public static final int K_OPEN_CLOSE = 11;
    public static final int K_OSD = 195;
    public static final int K_PAUSE = 18;
    public static final int K_PAUSE_REAR = 70;
    public static final int K_PHONE_OFF = 15;
    public static final int K_PHONE_OFF_RETURN = 189;
    public static final int K_PHONE_ON = 14;
    public static final int K_PHONE_ON_OFF = 188;
    public static final int K_PHOTO = 60;
    public static final int K_PIC = 12;
    public static final int K_PLAY = 17;
    public static final int K_PLAY_PAUSE = 95;
    public static final int K_POWER = 1;
    public static final int K_PP = 91;
    public static final int K_PRAVITE = 137;
    public static final int K_PRESET_DOWN = 66;
    public static final int K_PRESET_UP = 65;
    public static final int K_PREV = 21;
    public static final int K_PREV_PICKUP = 206;
    public static final int K_PREV_REAR = 71;
    public static final int K_PTT = 67;
    public static final int K_REAR_SYSTEM = 145;
    public static final int K_RECENTS_DEL_TASK = 4124;
    public static final int K_RECENT_APPS = 147;
    public static final int K_REC_OFF = 99;
    public static final int K_REC_ON = 98;
    public static final int K_RELEASE_RADIO = 4115;
    public static final int K_REPEAT = 27;
    public static final int K_REP_AB = 28;
    public static final int K_RETURN = 50;
    public static final int K_REVERSE_SETUP = 4108;
    public static final int K_RIGHT = 48;
    public static final int K_ROTATE = 205;
    public static final int K_SCAN = 30;
    public static final int K_SEARCH_K_AS = 131;
    public static final int K_SEND_MCU_KEY = 4125;
    public static final int K_SETUP = 58;
    public static final int K_SET_BACKLIGHT = 4105;
    public static final int K_SET_PASSWRD = 229;
    public static final int K_SET_SOURCE_BLUETOOTH = 4106;
    public static final int K_SET_VOLUME = 4104;
    public static final int K_SHOWVOLUME = 73;
    public static final int K_SHOW_ALLAPP_VIEW = 4123;
    public static final int K_SHOW_THIRD_APP_VIEW = 4116;
    public static final int K_SHOW_VIEW = 4109;
    public static final int K_SHUFFLE = 29;
    public static final int K_SLEEP = 134;
    public static final int K_SLOWB = 25;
    public static final int K_SLOWF = 24;
    public static final int K_SOS = 208;
    public static final int K_SOURCE = 2;
    public static final int K_SOURCE_PREV = 230;
    public static final int K_SOURCE_REAR = 69;
    public static final int K_SPEECH = 187;
    public static final int K_SPEECH_ACTION = 4119;
    public static final int K_STAR = 63;
    public static final int K_START_SOURCE = 4097;
    public static final int K_STEP = 26;
    public static final int K_STOP = 19;
    public static final int K_SUBTITLE = 54;
    public static final int K_SXM = 144;
    public static final int K_SYS_BLACKOUT = 4101;
    public static final int K_SYS_SLEEP = 4100;
    public static final int K_SYS_TOAST = 4103;
    public static final int K_TA = 94;
    public static final int K_THIRD_KUWO = 219;
    public static final int K_TILT_DN = 10;
    public static final int K_TILT_UP = 9;
    public static final int K_TIME = 196;
    public static final int K_TITLE = 51;
    public static final int K_TOUCH_ACTION = 4122;
    public static final int K_TS_RELEASE = 225;
    public static final int K_TS_UNLOCK = 224;
    public static final int K_TTM = 197;
    public static final int K_TUNER = 129;
    public static final int K_TV = 142;
    public static final int K_UP = 45;
    public static final int K_UP_PICKUP = 465;
    public static final int K_USB = 139;
    public static final int K_VIDEO = 61;
    public static final int K_VOICE_HANGUP = 202;
    public static final int K_VOICE_OUT = 204;
    public static final int K_VOICE_PICKUP = 201;
    public static final int K_VOL_DN = 8;
    public static final int K_VOL_UP = 7;
    public static final int K_VOL_VAL = 96;
    public static final int K_WIDE = 13;
    public static final int K_XM = 143;
    public static final int K_ZOOM = 56;
    public static final int MCU_INIT_REQ_0x00 = 0;
    public static HashMap<Byte, String> MCU_KEY_STRING = null;
    public static final int MUTE_TYPE_AUDIO_SWITCH_MUTE = 2;
    public static final int MUTE_TYPE_BEEP_UNMUTE = 9;
    public static final int MUTE_TYPE_DAB_MUTE = 10;
    public static final int MUTE_TYPE_EASYCONNECT_MUTE = 8;
    public static final int MUTE_TYPE_POWER_MUTE = 5;
    public static final int MUTE_TYPE_POWER_OFF_UNMUTE = 4;
    public static final int MUTE_TYPE_RADIO_MUTE = 7;
    public static final int MUTE_TYPE_REVERSE_MUTE = 6;
    public static final int MUTE_TYPE_TEMP_MUTE = 3;
    public static final int MUTE_TYPE_TEST_UNMUTE = 0;
    public static final int MUTE_TYPE_USER_MUTE = 1;
    public static final Map<String, Integer> hotKeyMap;
    public static Map<String, int[]> regHotKeyMap;

    public enum AA_ACTION {
        ConnectDevice, DisconnectDevice, NewDevicePaired, UnpairDevice
    }

    public enum ACTION_360CAMERA_TYPE {
        none, open_panorama, open_front_view, open_rear_view, open_left_view, open_right_view, open_width, open_left_front, open_right_front, toggle_2d, toggle_3d, open_prestreamer, open_post_streaming, chameleon_or_spin, turn_on_the_front_radar, turn_on_the_rear_radar, turn_on_the_full_vehicle_radar, close_panorama, with_top_view, no_view, photograph
    }

    public enum ACTION_APP_TYPE {
        none, close, open
    }

    public enum ACTION_BT_BUNDLE {
        none, name, number
    }

    public enum ACTION_BT_TYPE {
        none, call, handup, accept, reject, dialback, redial, contact
    }

    public enum ACTION_MEDIA_TYPE {
        none, pause, continue_play, prev, next, loop_all, loop_single, loop_order, loop_random, fm, am, fmto, amto, search, favour, unfavour, favourplay, preset, searchup, searchdown, play_list_open, play_list_close, favour_list_open, favour_list_close
    }

    public enum ACTION_RADIO_BUNDLE {
        none, frequency, band, index
    }

    public enum ACTION_RADIO_TYPE {
        none, fm, am, fmto, amto, prev, next, search, favour, unfavour, favourplay, preset, searchup, searchdown
    }

    public enum ACTION_RECENTS_DEL_TASK_BUNDLE {
        pkg, cls
    }

    public static class ACTION_SPEECH_TYPE {
        public static final int PHONE_ANSWER = 1;
    }

    public enum APP_PROCESS {
        KillPackage
    }

    public enum BT_ACTION {
        OPEN_BT, CLOSE_BT, MUTE_A2DP, UNMUTE_A2DP, CALLIN_ON, CALLIN_OFF
    }

    public enum CP_ACTION {
        PairStateChange, ConnectDevice, DisconnectDevice, GotoNowplaying
    }

    public enum KeyState {
        CLICK, PRESS_DOWN, PRESS_UP, LONG_EVENT, LONG_UP
    }

    public enum MEMORY_CLEAN_TYPE {
        CLEAR_MEMORY, SHOW_MEMORY_DIALOG
    }

    public enum RESET_MODE {
        NORMAL, FACTORY
    }

    public enum SHOW_VIEW {
        NONE, BRIGHTNESS, VOLUME, MEMORY_CLEAN
    }

    static {
        HashMap<String, Integer> map = new HashMap<>(0);
        hotKeyMap = map;
        regHotKeyMap = new HashMap<>(0);
        map.put("K_NONE", 0);
        map.put("K_POWER", 1);
        map.put("K_SOURCE", 2);
        map.put("K_MUTE", 3);
        map.put("K_EQ", 4);
        map.put("K_FAD_BAL", 5);
        map.put("K_DBB", 6);
        map.put("K_VOL_UP", 7);
        map.put("K_VOL_DN", 8);
        map.put("K_TILT_UP", 9);
        map.put("K_TILT_DN", 10);
        map.put("K_OPEN_CLOSE", 11);
        map.put("K_PIC", 12);
        map.put("K_WIDE", 13);
        map.put("K_PHONE_ON", 14);
        map.put("K_PHONE_OFF", 15);
        map.put("K_DVD_MENU", 16);
        map.put("K_PLAY", 17);
        map.put("K_PAUSE", 18);
        map.put("K_STOP", 19);
        map.put("K_NEXT", 20);
        map.put("K_PREV", 21);
        map.put("K_FASTF", 22);
        map.put("K_FASTB", 23);
        map.put("K_SLOWF", 24);
        map.put("K_SLOWB", 25);
        map.put("K_STEP", 26);
        map.put("K_REPEAT", 27);
        map.put("K_REP_AB", 28);
        map.put("K_SHUFFLE", 29);
        map.put("K_SCAN", 30);
        map.put("K_EJECT", 31);
        map.put("K_0", 32);
        map.put("K_1", 33);
        map.put("K_2", 34);
        map.put("K_3", 35);
        map.put("K_4", 36);
        map.put("K_5", 37);
        map.put("K_6", 38);
        map.put("K_7", 39);
        map.put("K_8", 40);
        map.put("K_9", 41);
        map.put("K_10", 42);
        map.put("K_CLEAR", 43);
        map.put("K_GOTO", 44);
        map.put("K_UP", 45);
        map.put("K_DOWN", 46);
        map.put("K_LEFT", 47);
        map.put("K_RIGHT", 48);
        map.put("K_ENTER", 49);
        map.put("K_RETURN", 50);
        map.put("K_TITLE", 51);
        map.put("K_HOME", 52);
        map.put("K_AUDIO", 53);
        map.put("K_SUBTITLE", 54);
        map.put("K_ANGLE", 55);
        map.put("K_ZOOM", 56);
        map.put("K_DISPLAY", 57);
        map.put("K_SETUP", 58);
        map.put("K_MUSIC", 59);
        map.put("K_PHOTO", 60);
        map.put("K_VIDEO", 61);
        map.put("K_BAND", 62);
        map.put("K_STAR", 63);
        map.put("K_NUMBER", 64);
        map.put("K_PRESET_UP", 65);
        map.put("K_PRESET_DOWN", 66);
        map.put("K_PTT", 67);
        map.put("K_BT", 68);
        map.put("K_SHOWVOLUME", 73);
        map.put("K_RECENT_APPS", 147);
        map.put("K_FCAMERA", 149);
        map.put("K_DELETE", 150);
        map.put("K_NAVI", 128);
        map.put("K_TUNER", 129);
        map.put("K_DVD", 130);
        map.put("K_SEARCH_K_AS", 131);
        map.put("K_HOST", 132);
        map.put("K_BK_LIGHT", 133);
        map.put("K_SOURCE_REAR", 69);
        map.put("K_PAUSE_REAR", 70);
        map.put("K_PREV_REAR", 71);
        map.put("K_NEXT_REAR", 72);
        map.put("K_MIC_OFF", 135);
        map.put("K_MIC_ON", 136);
        map.put("K_PRAVITE", 137);
        map.put("K_HANDFREE", 138);
        map.put("K_MENU", 151);
        map.put("K_AS_PREVIEW", 74);
        map.put("K_AS", 75);
        map.put("K_FM", 76);
        map.put("K_AM", 77);
        map.put("K_1_LONG", 79);
        map.put("K_2_LONG", 80);
        map.put("K_3_LONG", 81);
        map.put("K_4_LONG", 82);
        map.put("K_5_LONG", 83);
        map.put("K_6_LONG", 84);
        map.put("K_LOC", 89);
        map.put("K_TA", 94);
        map.put("K_VOICE_HANGUP", K_VOICE_HANGUP);
        map.put("K_VOICE_PICKUP", K_VOICE_PICKUP);
        map.put("K_VOICE_OUT", 204);
        map.put("K_LOUDNESS", 148);
        map.put("K_AIR_POWER", K_AIR_POWER);
        map.put("K_AIR_AUTO", K_AIR_AUTO);
        map.put("K_AIR_IN_OUT_CYCLE", K_AIR_IN_OUT_CYCLE);
        map.put("K_AIR_FRONT_DEFOG", K_AIR_FRONT_DEFOG);
        map.put("K_AIR_TEMP_UP", K_AIR_TEMP_UP);
        map.put("K_AIR_TEMP_DOWN", K_AIR_TEMP_DOWN);
        map.put("K_AIR_WIND_INC", K_AIR_WIND_INC);
        map.put("K_AIR_WIND_DEC", K_AIR_WIND_DEC);
        MCU_KEY_STRING = new HashMap<Byte, String>() { // from class: com.hzbhd.midware.constant.HotKeyConstant.1
            private static final long serialVersionUID = 1;

            {
                put((byte) 1, "POWER");
                put((byte) 2, "SOURCE");
                put((byte) 3, "MUTE");
                put((byte) 4, "EQ");
                put((byte) 5, "FAD_BAL");
                put((byte) 6, "DBB");
                put((byte) 7, "VOL_ADD");
                put((byte) 8, "VOL_LOSS");
                put((byte) 9, "TILT_UP");
                put((byte) 10, "TILT_DN");
                put((byte) 11, "OPEN_CLOSE");
                put(NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, "PIC");
                put(NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, "WIDE");
                put((byte) 14, "ANSWER");
                put((byte) 15, "HANGUP");
                put((byte) 16, "SOURCE_MENU");
                put((byte) 17, "PLAY");
                put((byte) 18, "PAUSE");
                put((byte) 19, "STOP");
                put((byte) 20, "NEXT");
                put((byte) 21, "PREV");
                put((byte) 22, "FASTF");
                put((byte) 23, "FASTB");
                put((byte) 24, "SLOWF");
                put((byte) 25, "SLOWB");
                put((byte) 26, "STEP");
                put((byte) 27, "REPEAT");
                put((byte) 28, "REP_AB");
                put((byte) 29, "SHUFFLE");
                put((byte) 30, "SCAN");
                put((byte) 31, "EJECT");
                put((byte) 32, "0");
                put((byte) 33, "1");
                put((byte) 34, "2");
                put((byte) 35, "3");
                put((byte) 36, "4");
                put((byte) 37, "5");
                put((byte) 38, "6");
                put((byte) 39, "7");
                put((byte) 40, "8");
                put((byte) 41, "9");
                put((byte) 42, "10");
                put((byte) 43, "CLEAR");
                put((byte) 44, "GOTO");
                put((byte) 45, "UP");
                put((byte) 46, "DOWN");
                put((byte) 47, "LEFT");
                put((byte) 48, "RIGHT");
                put((byte) 49, "ENTER");
                put((byte) 50, "RETURN");
                put((byte) 51, "TITLE");
                put((byte) 52, "HOME");
                put((byte) 53, "AUDIO");
                put((byte) 54, "SUBTITLE");
                put((byte) 55, "ANGLE");
                put((byte) 56, "ZOOM");
                put((byte) 57, "DISPLAY");
                put((byte) 58, "SETUP");
                put((byte) 59, "MUSIC");
                put((byte) 60, "PHOTO");
                put((byte) 61, "VIDEO");
                put((byte) 62, "BAND");
                put((byte) 63, "STAR");
                put((byte) 64, "NUMBER");
                put((byte) 65, "UP");
                put((byte) 66, "DOWN");
                put((byte) 67, "PTT");
                put((byte) 68, "BT");
                put((byte) 69, "SOURCE_REAR");
                put((byte) 70, "PAUSE_REAR");
                put((byte) 71, "PREV_REAR");
                put((byte) 72, "NEXT_REAR");
                put((byte) 75, "AS");
                put((byte) 76, "BAND_FM");
                put((byte) 77, "BAND_AM");
                put((byte) 79, "1_LONG");
                put((byte) 80, "2_LONG");
                put((byte) 81, "3_LONG");
                put((byte) 82, "4_LONG");
                put((byte) 83, "5_LONG");
                put((byte) 84, "6_LONG");
                put((byte) 89, "LOC");
                put((byte) 90, "K_FF");
                put((byte) 91, "K_PP");
                put((byte) 94, "TA");
                put(Byte.MIN_VALUE, "NAVIGATION");
                put((byte) -127, "TUNER");
                put((byte) -126, "DISC");
                put((byte) -125, "SEARCH");
                put((byte) -124, "HOST");
                put((byte) -123, "BK_LIGHT");
                put((byte) -96, "RADIO_SEEK_DOWN");
                put((byte) -95, "RADIO_SEEK_UP");
                put((byte) -61, "OSD");
                put((byte) -59, "TTM");
                put((byte) -58, "CARPLAY_SIRI");
                put((byte) -108, "LOUDNESS");
                put((byte) -107, "FCAMEEA");
                put((byte) -106, "DELETE");
                put((byte) 111, "K_AIR_POWER");
                put((byte) 110, "K_AIR_AUTO");
                put((byte) 109, "K_AIR_IN_OUT_CYCLE");
                put((byte) 108, "K_AIR_FRONT_DEFOG");
                put((byte) 107, "K_AIR_TEMP_UP");
                put((byte) 106, "K_AIR_TEMP_DOWN");
            }
        };
    }

    public static void RegHotKey(String str, int[] iArr) {
        regHotKeyMap.put(str, iArr);
    }

    public static void unRegHotKey(String str) {
        regHotKeyMap.remove(str);
    }

    public static Set<Integer> checkRegHotKeyApp(int i) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (String str : regHotKeyMap.keySet()) {
            for (int i2 : Objects.requireNonNull(regHotKeyMap.get(str))) {
                if (i2 == i) {
                    hashSet.add(Integer.parseInt(str));
                }
            }
        }
        return hashSet;
    }
}
