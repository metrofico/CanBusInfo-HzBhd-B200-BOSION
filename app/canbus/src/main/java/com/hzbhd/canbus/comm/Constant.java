package com.hzbhd.canbus.comm;

import android.content.ComponentName;

import com.hzbhd.config.constant.ClassName;
import com.hzbhd.config.constant.PackageName;

public class Constant {
    public static final String BUNDLE_KEY_ORINAL_INIT_VIEW = "bundle_key_orinal_init_view";
    public static final String CURRENT_ITEM = "current_item";
    public static final String DRIVE_DATA_OPEN_TARGET_PAGE = "drive_data_open_target_page";
    public static final String INTENT_BROADCAST_FINISH_AIR_ACTIVITY = "intent_broadcast_finish_air_activity";
    public static final String LEFT_INDEX = "left_index ";
    public static final String RIGHT_INDEX = "right_index";
    public static final String SETTING_OPEN_TARGET_PAGE = "setting_open_target_page";
    public static final String SHARE_HOR_PROGRESS = "share_hor_progress";
    public static final String SHARE_IS_OPEN_FRONT_CAMERA = "share_is_open_F.Camera";
    public static final String SHARE_IS_PANORAMIC = "share_is_panoramic";
    public static final String SHARE_LR_PROGRESS = "share_lr_progress";
    public static final String SHARE_PRE_CAN_DIFFERENT_ID = "share_pre_can_different_id";
    public static final String SHARE_PRE_CAN_TYPE_ID = "share_pre_can_type_id";
    public static final String SHARE_PRE_EACH_CAN_ID = "share_pre_each_can_id";
    public static final String SHARE_PRE_IS_DEBUG_MODEL = "share_pre_is_debug_model";
    public static final String SHARE_PRE_IS_FIRST_TIME_LAUNCHER = "share_pre_is_first_time_launcher";
    public static final String SHARE_PRE_IS_USE_F_UNIT = "share_pre_is_use_f_unit";
    public static final String SHARE_PRE_IS_USE_NEW_APP = "share_pre_is_use_new_app";
    public static final String SHARE_PRE_LAST_VERSION_CODE = "share_pre_last_version_code";
    public static final String SHARE_SELECT_CAR_POSITION = "share_select_car_position";
    public static final String SHARE_VER_PROGRESS = "share_ver_progress";
    public static final ComponentName CanBusMainActivity = new ComponentName("com.hzbhd.canbus", ClassName.bhd_canbus);
    public static final ComponentName SwcActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.SwcActivity");
    public static final ComponentName FrontCameraSettingActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.FrontCameraSettingActivity");
    public static final ComponentName CanBusDiagnosisActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.CanBusDiagnosisActivity");
    public static final ComponentName ChengWeiMainActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._273.MainActivity");
    public static final ComponentName DZMainActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._283.MainActivity");
    public static final ComponentName DZMainActivity291 = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._291.MainActivity");
    public static final ComponentName MdDvrActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._436.activity.DvrActivity");
    public static final ComponentName ID448DvrActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._448.activity.DvrActivity");
    public static final ComponentName VehicleMonitorActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._277.activity.VehicleMonitorActivity");
    public static final ComponentName VehicleDiagnosisActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._277.activity.VehicleDiagnosisActivity");
    public static final ComponentName EnergyFeedbackActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._277.activity.EnergyFeedbackActivity");
    public static final ComponentName ChengWeiMainActivity290 = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._290.MainActivity");
    public static final ComponentName FCameraActivity = new ComponentName(PackageName.bhd_ui_camera, ClassName.bhd_ui_fCamera);
    public static final ComponentName OriginalDeviceActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.OriginalCarDeviceActivity");
    public static final ComponentName SyncActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.SyncActivity");
    public static final ComponentName OnStarActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.OnStarActivity");
    public static final ComponentName TireInfoActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.TirePressureActivity");
    public static final ComponentName AirActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.AirActivity");
    public static final ComponentName Launcher = new ComponentName("com.android.launcher3", "com.android.launcher3.activity.MainActivity");
    public static final ComponentName AuxInActivity = new ComponentName("com.hzbhd.ui.activity", ClassName.bhd_ui_auxIn);

    public enum RadarLocation {
        FRONT_LEFT,
        FRONT_MID_LEFT,
        FRONT_MID_RIGHT,
        FRONT_RIGHT,
        FRONT_LEFT_PROBE,
        FRONT_RIGHT_PROBE,
        REAR_LEFT,
        REAR_MID_LEFT,
        REAR_MID_RIGHT,
        REAR_RIGHT,
        REAR_LEFT_PROBE,
        REAR_RIGHT_PROBE,
        LEFT_FRONT,
        LEFT_MID_FRONT,
        LEFT_MID_REAR,
        LEFT_REAR,
        RIGHT_FRONT,
        RIGHT_MID_FRONT,
        RIGHT_MID_REAR,
        RIGHT_REAR
    }
}
