package com.hzbhd.constant.camera;

public final class CameraFlag {
    public static final int ATTR_TYPE_CAMERA_TYPE = 1;
    public static final int ATTR_TYPE_FORMAT = 0;
    public static final int ATTR_TYPE_RECORDING = 5;
    public static final int ATTR_TYPE_SIGNAL = 2;
    public static final int ATTR_TYPE_SUPPORT_COLOR = 3;
    public static final int ATTR_TYPE_SUPPORT_FORMAT = 4;
    public static final int CAMERA_TYPE_4_CAMERA = 3;
    public static final int CAMERA_TYPE_AUX_CAMERA = 2;
    public static final int CAMERA_TYPE_AVM = 1;
    public static final int CAMERA_TYPE_BACK_CAMERA = 0;
    public static final int CAMERA_TYPE_CVBS_CAMERA = 4;
    public static final int FLAG_4_CAMERA_0 = 1000;
    public static final int FLAG_4_CAMERA_1 = 1001;
    public static final int FLAG_4_CAMERA_2 = 1002;
    public static final int FLAG_4_CAMERA_3 = 1003;
    public static final int FLAG_4_CAMERA_All = 1004;
    public static final int FLAG_4_CAMERA_Big_0 = 0;
    public static final int FLAG_4_CAMERA_Big_1 = 1;
    public static final int FLAG_4_CAMERA_Big_2 = 2;
    public static final int FLAG_4_CAMERA_Big_3 = 3;
    public static final int FLAG_4_CAMERA_CanBus_Big = 9;
    public static final int FLAG_4_CAMERA_NOT_Big = 4;
    public static final int FLAG_4_CAMERA_Reverse_Big_0 = 5;
    public static final int FLAG_4_CAMERA_Reverse_Big_1 = 6;
    public static final int FLAG_4_CAMERA_Reverse_Big_2 = 7;
    public static final int FLAG_4_CAMERA_Reverse_Big_3 = 8;
    public static final int FLAG_4_CAMERA_jm = 10;
    public static final int FLAG_AUX = 2;
    public static final int FLAG_AUX2 = 6;
    public static final int FLAG_BACK_CAMERA = 0;
    public static final int FLAG_DTV = 4;
    public static final int FLAG_FRONT_CAMERA = 1;
    public static final int FLAG_HDMI = 5;
    public static final int FLAG_MPEG = 3;
    public static final int FORMAT_AHD_1080_NTSC = 5;
    public static final int FORMAT_AHD_1080_PAL = 6;
    public static final int FORMAT_AHD_720_NTSC = 3;
    public static final int FORMAT_AHD_720_PAL = 4;
    public static final int FORMAT_AUTO = 0;
    public static final int FORMAT_CVBS_NTSC = 1;
    public static final int FORMAT_CVBS_PAL = 2;
    public static final int FORMAT_HDMI_IN = 11;
    public static final int FORMAT_TVI_1080_NTSC = 9;
    public static final int FORMAT_TVI_1080_PAL = 10;
    public static final int FORMAT_TVI_720_NTSC = 7;
    public static final int FORMAT_TVI_720_PAL = 8;
    public static final CameraFlag INSTANCE = new CameraFlag();

    private CameraFlag() {
    }
}
