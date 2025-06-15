package com.hzbhd.canbus.factory.proxy;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.commontools.utils.DocumentTool;
import com.hzbhd.commontools.utils.bhdJsonUtils;
import com.hzbhd.config.use.CanBusDefault;

/* loaded from: classes2.dex */
public class CanSettingProxy {
    private static final String TAG = "CanSettingProxy";
    private bhdJsonUtils mUtils;
    private final String PATH = "/bhd/appconfig/CanBus/";
    private final String FILE_NAME = "can_settings.json";
    private final String CAN_SETTINGS_SHOW = "can_settings_show";
    private final String RADAR_DISPLAY = "radar_display";
    private final String BACK_TRAJECTORY_REVERSAL = "back_trajectiry_reversal";
    private final String DOOR_SHOW_INFO = "door_show_info";
    private final String DOOR_FRONT_SWAP = "door_front_swap";
    private final String DOOR_REAR_SWAP = "door_rear_swap";
    private final String COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH = "count_down_timer_remove_door_view_switch";
    private final String DOOR_SHOW_THE_HOOD = "door_show_the_hood";
    private final String DOOR_SHOW_THE_TRUNK = "door_show_the_trunk";
    private final String AIR_SWITCH_TEMPERATURE = "air_switch_temperature";
    private final String AIR_DISPLAY_SETUP = "air_display_setup";
    private final String AIR_OUTDOOR_TEMPERATURE_DISPLAY = "air_outdoor_temperature_display";
    private final String AIR_TEMPERATURE_UNIT = "air_temperature_unit";
    private final String KEY_SWITCH_UP_DN = "key_switch_up_dn";
    private final String KEY_SWITCH_RIGHT_LEFT = "key_switch_right_left";
    private final String P_KEY_RADAR_DISPLAY = "p.key.radar.display";
    private final String OPEN_CAN_DATA_LOG = "open.can.data.log";

    public CanSettingProxy(Context context) {
        this.mUtils = null;
        synchronized (CanSettingProxy.class) {
            String fileContent = DocumentTool.readFileContent("/bhd/appconfig/CanBus/can_settings.json");
            if (TextUtils.isEmpty(fileContent)) {
                this.mUtils = new bhdJsonUtils("");
                Log.e(TAG, "CanSettingProxy: init --> mUtils=null");
            } else {
                this.mUtils = new bhdJsonUtils(fileContent);
            }
        }
    }

    public boolean getCanSettingShow() {
        return CanBusDefault.INSTANCE.getCanSettingShow();
    }

    public boolean getRadarDispCheck() {
        return this.mUtils.optBoolean(RADAR_DISPLAY, false);
    }

    public void setRadarDispCheck(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject(RADAR_DISPLAY, z);
            save();
        }
    }

    public boolean getBackTrajectiryDispCheck() {
        return this.mUtils.optBoolean(BACK_TRAJECTORY_REVERSAL, false);
    }

    public void setBackTrajectiryDispCheck(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject(BACK_TRAJECTORY_REVERSAL, z);
            save();
        }
    }

    public boolean getSwitchAcTemperature() {
        return this.mUtils.optBoolean(AIR_SWITCH_TEMPERATURE, false);
    }

    public void setSwitchAcTemperature(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject(AIR_SWITCH_TEMPERATURE, z);
            save();
        }
    }

    public int getAirDisplaySetup() {
        return this.mUtils.optInt(AIR_DISPLAY_SETUP, 0);
    }

    public void setAirDisplaySetup(int i) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject(AIR_DISPLAY_SETUP, i);
            save();
        }
    }

    public boolean getSwitchKeyUpDn() {
        return this.mUtils.optBoolean("key_switch_up_dn", false);
    }

    public void setSwitchKeyUpDn(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("key_switch_up_dn", z);
            save();
        }
    }

    public boolean getSwitchKeyRightLeft() {
        return this.mUtils.optBoolean("key_switch_right_left", false);
    }

    public void setSwitchKeyRightLeft(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("key_switch_right_left", z);
            save();
        }
    }

    public boolean getShowOutdoorTemperature() {
        return this.mUtils.optBoolean("air_outdoor_temperature_display", false);
    }

    public void setShowOutdoorTemperature(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("air_outdoor_temperature_display", z);
            save();
            CanbusInfoChangeListener.getInstance().setOutdoorTemperatureVisible(z);
        }
    }

    public int getTemperatureUnit() {
        return this.mUtils.optInt("air_temperature_unit", 0);
    }

    public void setTemperatureUnit(int i) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("air_temperature_unit", i);
            save();
            if (getShowOutdoorTemperature()) {
                CanbusInfoChangeListener.getInstance().reportOutdoorTemperature();
            }
        }
    }

    public boolean getShowDoorInfo() {
        return this.mUtils.optBoolean("door_show_info", false);
    }

    public void setShowDoorInfo(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("door_show_info", z);
            save();
        }
    }

    public boolean getDoorSwapFront() {
        return this.mUtils.optBoolean("door_front_swap", false);
    }

    public void setDoorSwapFront(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("door_front_swap", z);
            save();
        }
    }

    public boolean getDoorSwapRear() {
        return this.mUtils.optBoolean("door_rear_swap", false);
    }

    public void setDoorSwapRear(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("door_rear_swap", z);
            save();
        }
    }

    public boolean getDoorCountDownTimerState() {
        return this.mUtils.optBoolean("count_down_timer_remove_door_view_switch", false);
    }

    public void setDoorCountDownTimerState(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("count_down_timer_remove_door_view_switch", z);
            save();
        }
    }

    public boolean getShowHood() {
        return this.mUtils.optBoolean("door_show_the_hood", false);
    }

    public void setShowHood(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("door_show_the_hood", z);
            save();
        }
    }

    public boolean getShowTrunk() {
        return this.mUtils.optBoolean("door_show_the_trunk", false);
    }

    public void setShowTrunk(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("door_show_the_trunk", z);
            save();
        }
    }

    private void save() {
        if (this.mUtils.checkMainJson()) {
            DocumentTool.writeData("/bhd/appconfig/CanBus/can_settings.json", this.mUtils.ObjectToString());
        }
    }

    public boolean getPKeyRadarDispCheck() {
        return this.mUtils.optBoolean("p.key.radar.display", false);
    }

    public void setPKeyRadarDispCheck(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("p.key.radar.display", z);
            save();
        }
    }

    public boolean getCanDataLogSwith() {
        return this.mUtils.optBoolean("open.can.data.log", false);
    }

    public void setCanDataLogSwith(boolean z) {
        synchronized (CanSettingProxy.class) {
            this.mUtils.putObject("open.can.data.log", z);
            save();
        }
    }
}
