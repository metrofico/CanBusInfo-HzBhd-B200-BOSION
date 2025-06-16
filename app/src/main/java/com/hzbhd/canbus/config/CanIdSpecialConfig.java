package com.hzbhd.canbus.config;

import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.Arrays;

/* loaded from: classes2.dex */
public class CanIdSpecialConfig {
    private static int[] uiDifferentId = {283, 291, 206, 436};
    private static int[] have_SpeechModule_ID = {283, 163, 439, 455, 467};
    private static int[] canType = {446};
    private static int[] supplement0x00InCanDataEndList = {466};

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
