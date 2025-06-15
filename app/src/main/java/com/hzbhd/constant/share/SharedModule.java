package com.hzbhd.constant.share;

import com.hzbhd.commontools.SourceConstantsDef;

import java.util.HashMap;
import java.util.Map;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* compiled from: SharedModule.kt */

public final class SharedModule {
    public static final SharedModule INSTANCE = new SharedModule();

    private static final HashMap<String, Integer> shared = new HashMap<>() {{
        put(ShareConstants.SHARE_CURRENT_SOURCE_INFO, SourceConstantsDef.MODULE_ID.SOURCE_MANAGER.getValue());
        put(ShareConstants.SHARE_POWER_OFF_SOURCE, SourceConstantsDef.MODULE_ID.SOURCE_MANAGER.getValue());
        put(ShareConstants.SHARE_CURRENT_CAMERA, SourceConstantsDef.MODULE_ID.SOURCE_MANAGER.getValue());
        put(ShareConstants.SHARE_NAVI_STATE, SourceConstantsDef.MODULE_ID.SOURCE_MANAGER.getValue());
        put(ShareConstants.SHARE_RADIO_INFO, SourceConstantsDef.MODULE_ID.HARDWARE.getValue());
        put(ShareConstants.SHARE_RADIO_BAND, SourceConstantsDef.MODULE_ID.HARDWARE.getValue());
        put(ShareConstants.SHARE_RADIO_FAVOUR, SourceConstantsDef.MODULE_ID.HARDWARE.getValue());
        put("sys.Reverse", SourceConstantsDef.MODULE_ID.HARDWARE.getValue());
        put(ShareConstants.SHARE_SYS_APPCAMERA_READY, SourceConstantsDef.MODULE_ID.HARDWARE.getValue());
        put(ShareConstants.SHARE_SYS_POWER, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SYS_PARKING, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SYS_MOTOR_MOVING, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SYS_LIGHT, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SYS_DISC_EXIST, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SYS_SCREEN_ORIENTATION, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_MCU_POWERON_MODE, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SYS_BACKLIGHT_STATUS, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SCREEN_BACKLIGHT, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SCREEN_BACKLIGHT_MODE, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SCREEN_BACKLIGHT_DAY, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_SCREEN_BACKLIGHT_NIGHT, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_MCU_INIT_STATUS, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_MCU_RESET_STATUS, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_MCU_SCREENSAVER_MODE, SourceConstantsDef.MODULE_ID.MCU.getValue());
        put(ShareConstants.SHARE_AUDIO_VOLUME_MAX, SourceConstantsDef.MODULE_ID.AUDIO.getValue());
        put(ShareConstants.SHARE_AUDIO_VOLUME, SourceConstantsDef.MODULE_ID.AUDIO.getValue());
        put(ShareConstants.SHARE_AUDIO_MUTE, SourceConstantsDef.MODULE_ID.AUDIO.getValue());
        put(ShareConstants.SHARE_AUDIO_LOUDNESS, SourceConstantsDef.MODULE_ID.AUDIO.getValue());
        put(ShareConstants.SHARE_MUSIC_PLAY_STATE, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_MUSIC_SONG_INFO, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_MUSIC_TIME_INFO, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_MUSIC_NUM_INFO, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_VIDEO_PLAY_STATE, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_VIDEO_MOVIE_INFO, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_VIDEO_NUM_INFO, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_VIDEO_TIME_INFO, SourceConstantsDef.MODULE_ID.MEDIA.getValue());
        put(ShareConstants.SHARE_BT_CALL_STATE, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_INFO, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_MUSIC_INFO, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_STATE, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_ADDRESS_INFO, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_CALL_INFO, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_CALL_MIC_MUTE, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_BT_CALL_DEVICE, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_MISC_REVERSE, SourceConstantsDef.MODULE_ID.MISC.getValue());
        put(ShareConstants.SHARE_USER_REVERSE, SourceConstantsDef.MODULE_ID.MISC.getValue());
        put(ShareConstants.SHARE_MISC_DUAL_SCREEN_WINDOW_VISIBLE, SourceConstantsDef.MODULE_ID.MISC.getValue());
        put(ShareConstants.SHARE_MISC_SCREEN_SAVER_MODE_STATUS, SourceConstantsDef.MODULE_ID.MISC.getValue());
        put(ShareConstants.SHARE_UI_ID, SourceConstantsDef.MODULE_ID.MISC.getValue());
        put(ShareConstants.SHARE_CHANGE_UI_ID, SourceConstantsDef.MODULE_ID.MISC.getValue());
        put(ShareConstants.SHARE_AUTO_CONNECT, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_AUTO_MEDIA_STATUS, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_CARPLAY_CONNECT, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_CARPLAY_MEDIA_STATUS, SourceConstantsDef.MODULE_ID.BLUETOOTH.getValue());
        put(ShareConstants.SHARE_CANBUS_AIR_INFO, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_DOOR_INFO, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_RADAR_FRONT, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_RADAR_REAR, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_VOICE_BROADCAST, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_ANGLE, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_OUTDOOR_TEMPERATURE, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_CAN_VERSION, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_RADAR_LEFT, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_RADAR_RIGHT, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_SPEED_INFO, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_MS_AIR_JSON, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_MS_AIR_CONTROL_JSON, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_MS_BISIC_JSON, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CANBUS_MS_BISIC_CONTROL_JSON, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_CAN_BUS_ALL_DATA, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_REQUEST_ALL_DATA, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_MS_BLIND_AREA_UPDATE_LAUNCHER, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_D4_AMPL_CONFIG, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_D4_AMPL_CMD_TO_CAN, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_D4_FUNCTION_CONFIG_CHANGE, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_D4_SXM_VISIBIKITY, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_LCD_MUSIC_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_DISC_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_VIDEO_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_BTPHONE_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_BTMUSIC_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_RADIO_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_CARPLAY_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_ANDROIDAUTO_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_LCD_DSP_INFO, SourceConstantsDef.MODULE_ID.LCD.getValue());
        put(ShareConstants.SHARE_DISC_EXIT_INFO, SourceConstantsDef.MODULE_ID.DISC.getValue());
        put(ShareConstants.SHARE_DISC_PLAYSTATE_INFO, SourceConstantsDef.MODULE_ID.DISC.getValue());
        put(ShareConstants.SHARE_DISC_PLAY_INFO, SourceConstantsDef.MODULE_ID.DISC.getValue());
        put(ShareConstants.SHARE_DAB_PLAYSERVICE_INFO, SourceConstantsDef.MODULE_ID.DAB.getValue());
        put(ShareConstants.SHARE_OUT_OF_MISC_TURN_LEFT_RIGHT_TAG, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_SLD_CANBUS_CAR_SPEED, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_SLD_CANBUS_DOOR_JSON_INFO, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_SLD_CANBUS_RADAR_FRONT_JSON_INFO, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_SLD_CANBUS_RADAR_REAR_JSON_INFO, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_SLD_CANBUS_ANGLE, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_SLD_CANBUS_TURN_SIGNALS, SourceConstantsDef.MODULE_ID.CANBUS.getValue());
        put(ShareConstants.SHARE_ZERON_USER, SourceConstantsDef.MODULE_ID.MISC.getValue());
    }};

    private SharedModule() {
    }

    public HashMap<String, Integer> getShared() {
        return shared;
    }
}
