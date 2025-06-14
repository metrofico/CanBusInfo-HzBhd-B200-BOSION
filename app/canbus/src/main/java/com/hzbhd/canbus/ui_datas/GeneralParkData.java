package com.hzbhd.canbus.ui_datas;

import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class GeneralParkData {
    public static List<PanoramicBtnUpdateEntity> dataList = null;
    public static boolean isShowDistanceAndShowLocationUi = false;
    public static boolean isShowDistanceNotShowLocationUi = false;
    public static boolean isShowLeftTopOneDistanceUi = false;
    public static boolean pKeyRadarState = false;
    public static HashMap<Constant.RadarLocation, Integer> radar_distance_data = null;
    public static int[] radar_distance_disable = null;
    public static HashMap<Constant.RadarLocation, int[]> radar_location_data = null;
    public static String strOnlyOneDistance = "";
    public static int trackAngle;
    public static int trackAngleRecord;
}
