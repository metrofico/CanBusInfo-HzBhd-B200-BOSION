package com.hzbhd.canbus.config;

/* loaded from: classes2.dex */
public class GeneralSettingsConfig {
    public boolean AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG;
    public boolean AIR_SHOW_TAG;
    public boolean BACK_TRAJECTORY_REVERSAL;
    public boolean CAN_BUS_TEST_TAG;
    public boolean COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH;
    public boolean DOOR_SHOW_TAG;
    public boolean FRONT_CAMERA_TAG;
    public boolean FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG;
    public boolean GENERAL_SETTING_PAGE_SHOW;
    public boolean OPEN_CAN_DATA_LOG;
    public boolean OUT_TEMPERATURE_SHOW_TAG;
    public boolean RADAR_SHOW_TAG;
    public boolean REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG;
    public boolean SHOW_HIDE_SMART_PANEL_PAGE;
    public boolean SHOW_P_KEY_RADAR_WINDOW;
    public boolean SWC_STUDY_TAG;
    public boolean TEMPERATURE_UNIT_TAG;
    public boolean THE_HOOD_SHOW_TAG;
    public boolean TOUCH_PAD_SETTING_TAG;
    public boolean TRUNK_SHOW_TAG;
    public boolean WIKA_CAR_SELECT;

    private static class Holder {
        private static final GeneralSettingsConfig INSTANCE = new GeneralSettingsConfig();

        private Holder() {
        }
    }

    public static GeneralSettingsConfig getInstance(int i) {
        Holder.INSTANCE.setConfig(i);
        return Holder.INSTANCE;
    }

    private GeneralSettingsConfig() {
        this.GENERAL_SETTING_PAGE_SHOW = true;
        this.RADAR_SHOW_TAG = true;
        this.BACK_TRAJECTORY_REVERSAL = true;
        this.DOOR_SHOW_TAG = true;
        this.FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG = true;
        this.REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG = true;
        this.THE_HOOD_SHOW_TAG = true;
        this.TRUNK_SHOW_TAG = true;
        this.AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG = true;
        this.AIR_SHOW_TAG = true;
        this.OUT_TEMPERATURE_SHOW_TAG = true;
        this.TEMPERATURE_UNIT_TAG = true;
        this.SWC_STUDY_TAG = true;
        this.FRONT_CAMERA_TAG = true;
        this.TOUCH_PAD_SETTING_TAG = false;
        this.CAN_BUS_TEST_TAG = false;
        this.COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH = true;
        this.SHOW_HIDE_SMART_PANEL_PAGE = false;
        this.SHOW_P_KEY_RADAR_WINDOW = false;
        this.WIKA_CAR_SELECT = false;
        this.OPEN_CAN_DATA_LOG = true;
    }

    private void setConfig(int i) {
        if (i == 1) {
            set1Config();
            return;
        }
        if (i == 2) {
            set2Config();
            return;
        }
        if (i == 3) {
            set3Config();
            return;
        }
        if (i == 4) {
            set4Config();
            return;
        }
        if (i == 161) {
            set161Config();
            return;
        }
        if (i == 213) {
            set213Config();
            return;
        }
        if (i == 434) {
            set436Config();
            return;
        }
        if (i == 437) {
            set437Config();
            return;
        }
        if (i == 439) {
            set439Config();
            return;
        }
        if (i != 445) {
            if (i != 455) {
                if (i == 461) {
                    set0x461Config();
                } else {
                    switch (i) {
                        case 451:
                            set451Config();
                            break;
                        case 452:
                            set452Config();
                            break;
                        case 453:
                            set0x453Config();
                            break;
                    }
                    return;
                }
            }
            set0x455Config();
            return;
        }
        set445Config();
    }

    private void set0x461Config() {
        this.OUT_TEMPERATURE_SHOW_TAG = false;
        this.FRONT_CAMERA_TAG = false;
    }

    private void set0x455Config() {
        this.RADAR_SHOW_TAG = false;
        this.OUT_TEMPERATURE_SHOW_TAG = false;
        this.FRONT_CAMERA_TAG = false;
    }

    private void set0x453Config() {
        this.OUT_TEMPERATURE_SHOW_TAG = false;
        this.FRONT_CAMERA_TAG = false;
    }

    private void set452Config() {
        this.OUT_TEMPERATURE_SHOW_TAG = false;
        this.FRONT_CAMERA_TAG = false;
    }

    private void set451Config() {
        this.OUT_TEMPERATURE_SHOW_TAG = false;
        this.FRONT_CAMERA_TAG = false;
    }

    private void set4Config() {
        this.SHOW_P_KEY_RADAR_WINDOW = true;
    }

    private void set3Config() {
        this.SHOW_P_KEY_RADAR_WINDOW = true;
    }

    private void set2Config() {
        this.SHOW_P_KEY_RADAR_WINDOW = true;
    }

    private void set1Config() {
        this.SHOW_P_KEY_RADAR_WINDOW = true;
    }

    private void set445Config() {
        this.GENERAL_SETTING_PAGE_SHOW = false;
    }

    private void set439Config() {
        this.COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH = false;
        this.OUT_TEMPERATURE_SHOW_TAG = false;
        this.AIR_SHOW_TAG = false;
        this.RADAR_SHOW_TAG = false;
        this.DOOR_SHOW_TAG = false;
        this.FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
        this.REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
        this.AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG = false;
        this.THE_HOOD_SHOW_TAG = false;
        this.TRUNK_SHOW_TAG = false;
        this.TEMPERATURE_UNIT_TAG = false;
        this.FRONT_CAMERA_TAG = false;
        this.SHOW_HIDE_SMART_PANEL_PAGE = true;
        this.WIKA_CAR_SELECT = true;
    }

    private void set213Config() {
        this.TOUCH_PAD_SETTING_TAG = true;
    }

    private void set437Config() {
        this.FRONT_CAMERA_TAG = false;
    }

    private void set161Config() {
        this.TOUCH_PAD_SETTING_TAG = false;
    }

    private void set436Config() {
        this.RADAR_SHOW_TAG = false;
        this.DOOR_SHOW_TAG = false;
        this.FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
        this.REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
        this.THE_HOOD_SHOW_TAG = false;
        this.TRUNK_SHOW_TAG = false;
        this.SWC_STUDY_TAG = false;
        this.TOUCH_PAD_SETTING_TAG = false;
        this.CAN_BUS_TEST_TAG = false;
    }
}
