package com.hzbhd.canbus.config;

import com.android.internal.util.ArrayUtils;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

/* loaded from: classes2.dex */
public class CanIdSpecialConfig {
    private static int[] uiDifferentId = {283, 291, 206, 436};
    private static int[] have_SpeechModule_ID = {283, MpegConstantsDef.MPEG_INFO_SUBTITLE_CFM, 439, 455, HotKeyConstant.K_1_PICKUP};
    private static int[] canType = {446};
    private static int[] supplement0x00InCanDataEndList = {HotKeyConstant.K_DOWN_HANGUP};

    public static boolean isNewVoiceCanID(int i) {
        return ArrayUtils.contains(uiDifferentId, i);
    }

    public static boolean haveSpeechModule(int i) {
        return ArrayUtils.contains(have_SpeechModule_ID, i);
    }

    public static boolean hideRadarLayoutCanID(int i) {
        return ArrayUtils.contains(canType, i);
    }

    public static boolean isCanNotSupplement0x00InCanDataEnd(int i) {
        return ArrayUtils.contains(supplement0x00InCanDataEndList, i);
    }
}
