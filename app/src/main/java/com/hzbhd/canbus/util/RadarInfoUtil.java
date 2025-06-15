package com.hzbhd.canbus.util;

import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.ui.util.BaseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/* loaded from: classes2.dex */
public class RadarInfoUtil {
    public static HashMap<Constant.RadarLocation, int[]> mLocationMap = new HashMap<>();
    public static HashMap<Constant.RadarLocation, Integer> mDistanceMap = new HashMap<>();
    public static int mDisableData = 0;
    public static int mDisableData2 = 0;
    public static boolean mMinIsClose = false;
    private static byte mBackLeftRadarInfo = -1;
    private static byte mBackLeftMidRadarInfo = -1;
    private static byte mBackRightMidRadarInfo = -1;
    private static byte mBackRightRadarInfo = -1;
    private static byte mFrontLeftRadarInfo = -1;
    private static byte mFrontLeftMidRadarInfo = -1;
    private static byte mFrontRightMidRadarInfo = -1;
    private static byte mfrontRightRadarInfo = -1;
    private static byte mLeftFrontRadarInfo = -1;
    private static byte mLeftFrontMidRadarInfo = -1;
    private static byte mLeftRearMidRadarInfo = -1;
    private static byte mLeftRearRadarInfo = -1;
    private static byte mRightFrontRadarInfo = -1;
    private static byte mRightFrontMidRadarInfo = -1;
    private static byte mRightRearMidRadarInfo = -1;
    private static byte mRightRearRadarInfo = -1;
    private static byte mFrontRightProbeInfo = -1;
    private static byte mFrontLeftProbeInfo = -1;
    private static byte mRearLeftProbeInfo = -1;
    private static byte mRearRightProbeInfo = -1;
    private static byte[] mRadarData = new byte[Constant.RadarLocation.values().length];
    private static String mRadarFrontShareInfo = null;
    private static String mRadarRearShareInfo = null;
    private static String mRadarLeftShareInfo = null;
    private static String mRadarRightShareInfo = null;

    public static void setRearRadarDistanceData(int i, int i2, int i3, int i4) {
        mDistanceMap.put(Constant.RadarLocation.REAR_LEFT, Integer.valueOf(i));
        mDistanceMap.put(Constant.RadarLocation.REAR_MID_LEFT, Integer.valueOf(i2));
        mDistanceMap.put(Constant.RadarLocation.REAR_MID_RIGHT, Integer.valueOf(i3));
        mDistanceMap.put(Constant.RadarLocation.REAR_RIGHT, Integer.valueOf(i4));
    }

    public static void setFrontRadarDistanceData(int i, int i2, int i3, int i4) {
        mDistanceMap.put(Constant.RadarLocation.FRONT_LEFT, Integer.valueOf(i));
        mDistanceMap.put(Constant.RadarLocation.FRONT_MID_LEFT, Integer.valueOf(i2));
        mDistanceMap.put(Constant.RadarLocation.FRONT_MID_RIGHT, Integer.valueOf(i3));
        mDistanceMap.put(Constant.RadarLocation.FRONT_RIGHT, Integer.valueOf(i4));
    }

    public static void setRightRadarDistanceData(int i, int i2, int i3, int i4) {
        mDistanceMap.put(Constant.RadarLocation.RIGHT_FRONT, i);
        mDistanceMap.put(Constant.RadarLocation.RIGHT_MID_FRONT, i2);
        mDistanceMap.put(Constant.RadarLocation.RIGHT_MID_REAR, i3);
        mDistanceMap.put(Constant.RadarLocation.RIGHT_REAR, i4);
    }

    public static void setLeftRadarDistanceData(int i, int i2, int i3, int i4) {
        mDistanceMap.put(Constant.RadarLocation.LEFT_FRONT, Integer.valueOf(i));
        mDistanceMap.put(Constant.RadarLocation.LEFT_MID_FRONT, Integer.valueOf(i2));
        mDistanceMap.put(Constant.RadarLocation.LEFT_MID_REAR, Integer.valueOf(i3));
        mDistanceMap.put(Constant.RadarLocation.LEFT_REAR, Integer.valueOf(i4));
    }

    public static void setRearRadarLocationData(final int i, final int i2, final int i3, final int i4, final int i5) {
        setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, i3, i);
        setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, i4, i);
        setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, i5, i);
        try {
            sendRadarShareInfoFR(ShareConstants.SHARE_CANBUS_RADAR_REAR, i, i2, i3, i4, i5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (GeneralParkData.pKeyRadarState) {
            BaseUtil.INSTANCE.runUi(new Runnable() {
                @Override
                public void run() {
                    PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).refreshRearRadar(i, i2, i3, i4, i5);
                }
            });

        }
    }

    public static void setFrontRadarLocationData(final int i, final int i2, final int i3, final int i4, final int i5) {
        setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, i3, i);
        setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, i4, i);
        setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, i5, i);
        try {
            sendRadarShareInfoFR(ShareConstants.SHARE_CANBUS_RADAR_FRONT, i, i2, i3, i4, i5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (GeneralParkData.pKeyRadarState) {
            BaseUtil.INSTANCE.runUi(new Runnable() {
                @Override
                public void run() {
                    PKeyUtil.getInstance(ContextUtil.getInstance().getContext()).refreshFrontRadar(i, i2, i3, i4, i5);
                }
            });
        }
    }

    public static void setProbeRadarLocationData(int i, int i2, int i3, int i4, int i5) {
        setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT_PROBE, i2, i);
        setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT_PROBE, i3, i);
        setGeneralRadarData(Constant.RadarLocation.REAR_LEFT_PROBE, i4, i);
        setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT_PROBE, i5, i);
    }

    public static void setLeftRadarLocationData(int i, int i2, int i3, int i4, int i5) {
        setGeneralRadarData(Constant.RadarLocation.LEFT_FRONT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.LEFT_MID_FRONT, i3, i);
        setGeneralRadarData(Constant.RadarLocation.LEFT_MID_REAR, i4, i);
        setGeneralRadarData(Constant.RadarLocation.LEFT_REAR, i5, i);
        try {
            sendRadarShareInfoLR(ShareConstants.SHARE_CANBUS_RADAR_LEFT, i, i2, i3, i4, i5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setRightRadarLocationData(int i, int i2, int i3, int i4, int i5) {
        setGeneralRadarData(Constant.RadarLocation.RIGHT_FRONT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, i3, i);
        setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_REAR, i4, i);
        setGeneralRadarData(Constant.RadarLocation.RIGHT_REAR, i5, i);
        try {
            sendRadarShareInfoLR(ShareConstants.SHARE_CANBUS_RADAR_RIGHT, i, i2, i3, i4, i5);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setRearRadarLocationDataType2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        setGeneralRadarData(Constant.RadarLocation.REAR_LEFT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.REAR_MID_LEFT, i4, i3);
        setGeneralRadarData(Constant.RadarLocation.REAR_MID_RIGHT, i6, i5);
        setGeneralRadarData(Constant.RadarLocation.REAR_RIGHT, i8, i7);
    }

    public static void setFrontRadarLocationDataType2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        setGeneralRadarData(Constant.RadarLocation.FRONT_LEFT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.FRONT_MID_LEFT, i4, i3);
        setGeneralRadarData(Constant.RadarLocation.FRONT_MID_RIGHT, i6, i5);
        setGeneralRadarData(Constant.RadarLocation.FRONT_RIGHT, i8, i7);
    }

    public static void setLeftRadarLocationDataType2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        setGeneralRadarData(Constant.RadarLocation.LEFT_FRONT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.LEFT_MID_FRONT, i4, i3);
        setGeneralRadarData(Constant.RadarLocation.LEFT_MID_REAR, i6, i5);
        setGeneralRadarData(Constant.RadarLocation.LEFT_REAR, i8, i7);
    }

    public static void setRightRadarLocationDataType2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        setGeneralRadarData(Constant.RadarLocation.RIGHT_FRONT, i2, i);
        setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_FRONT, i4, i3);
        setGeneralRadarData(Constant.RadarLocation.RIGHT_MID_REAR, i6, i5);
        setGeneralRadarData(Constant.RadarLocation.RIGHT_REAR, i8, i7);
    }

    public static String getRadarFrontShareInfo() {
        return mRadarFrontShareInfo;
    }

    public static String getRadarRearShareInfo() {
        return mRadarRearShareInfo;
    }

    public static String getRadarLeftShareInfo() {
        return mRadarLeftShareInfo;
    }

    public static String getRadarRightShareInfo() {
        return mRadarRightShareInfo;
    }

    private static void sendRadarShareInfoFR(String str, int i, int i2, int i3, int i4, int i5) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mMinIsClose", mMinIsClose);
        jSONObject.put("disableData", mDisableData);
        jSONObject.put("range", i);
        jSONObject.put("left", i2);
        jSONObject.put("left_mid", i3);
        jSONObject.put("right_mid", i4);
        jSONObject.put("right", i5);
        String string = jSONObject.toString();
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("<sendRadarShareInfo> " + str + ": " + string);
        }
        if (ShareConstants.SHARE_CANBUS_RADAR_FRONT.equals(str)) {
            mRadarFrontShareInfo = string;
        } else {
            mRadarRearShareInfo = string;
        }
        ShareDataServiceImpl.setString(str, string);
    }

    private static void sendRadarShareInfoLR(String str, int i, int i2, int i3, int i4, int i5) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mMinIsClose", mMinIsClose);
        jSONObject.put("disableData", mDisableData);
        jSONObject.put("range", i);
        jSONObject.put("front", i2);
        jSONObject.put("front_mid", i3);
        jSONObject.put("rear_mid", i4);
        jSONObject.put(AirBtnAction.REAR, i5);
        String string = jSONObject.toString();
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("<sendRadarShareInfo> " + str + ": " + string);
        }
        if (ShareConstants.SHARE_CANBUS_RADAR_LEFT.equals(str)) {
            mRadarLeftShareInfo = string;
        } else {
            mRadarRightShareInfo = string;
        }
        ShareDataServiceImpl.setString(str, string);
    }

    public static void setGeneralRadarData(Constant.RadarLocation radarLocation, int i, int i2) {
        double dCeil;
        byte b = (byte) i;
        if (mRadarData[radarLocation.ordinal()] != b) {
            mRadarData[radarLocation.ordinal()] = b;
            int i3 = i & 255;
            if (i3 == mDisableData || i3 == mDisableData2) {
                setFordImageResourceByName(radarLocation, 0, 10);
                return;
            }
            if (mMinIsClose) {
                dCeil = Math.ceil(((((i - 1.0f) * 10.0f) / i2) + 1.0f) * 1.0f);
            } else {
                dCeil = Math.ceil((10 - ((i * 10) / i2)) + 1);
            }
            int i4 = (int) dCeil;
            if (i2 < 10 && i4 >= 9 && i2 != 7) {
                i4 = 10;
            }
            setFordImageResourceByName(radarLocation, (byte) DataHandleUtils.rangeNumber(i4, 10), 10);
        }
    }

    private static void setFordImageResourceByName(Constant.RadarLocation radarLocation, int i, int i2) {
        mLocationMap.put(radarLocation, new int[]{i, i2});
    }
}
