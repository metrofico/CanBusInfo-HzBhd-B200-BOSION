package com.hzbhd.canbus.config;

import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.Arrays;

/* loaded from: classes2.dex */
public class CanIdSpecialConfig {
    private static int[] uiDifferentId = {283, 291, 206, 436};
    private static int[] have_SpeechModule_ID = {283, MpegConstantsDef.MPEG_INFO_SUBTITLE_CFM, 439, 455, HotKeyConstant.K_1_PICKUP};
    private static int[] canType = {446};
    private static int[] supplement0x00InCanDataEndList = {HotKeyConstant.K_DOWN_HANGUP};

    public static boolean isNewVoiceCanID(int i) {
        return contains(uiDifferentId, i);
    }

    public static boolean haveSpeechModule(int i) {
        return contains(have_SpeechModule_ID, i);
    }

    public static boolean hideRadarLayoutCanID(int i) {
        return contains(canType, i);
    }

    public static boolean isCanNotSupplement0x00InCanDataEnd(int i) {
        return contains(supplement0x00InCanDataEndList, i);
    }

    // Helper method to check if an array contains a specific integer
    private static boolean contains(int[] array, int value) {
        return Arrays.stream(array).anyMatch(x -> x == value);
    }
}
