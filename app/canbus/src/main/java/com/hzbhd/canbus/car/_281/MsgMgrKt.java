package com.hzbhd.canbus.car._281;

import com.hzbhd.canbus.ui_datas.GeneralAirData;
import kotlin.Metadata;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"windDirectionInit", "", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
