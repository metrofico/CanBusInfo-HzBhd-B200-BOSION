package com.hzbhd.canbus.car._281;

import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;




public final class MsgMgrKt {
    public static final void windDirectionInit() {
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.front_auto_wind_model = false;
        GeneralAirData.rear_auto_wind_model = false;
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        GeneralAirData.rear_left_auto_wind = false;
        GeneralAirData.rear_right_auto_wind = false;
        GeneralAirData.front_auto_wind_speed = false;
        GeneralAirData.rear_auto_wind_speed = false;
    }
}
