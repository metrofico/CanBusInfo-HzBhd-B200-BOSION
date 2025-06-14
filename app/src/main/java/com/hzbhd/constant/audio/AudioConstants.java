package com.hzbhd.constant.audio;

import com.hzbhd.constant.disc.MpegConstantsDef;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes2.dex */
public class AudioConstants {
    public static int EQTYPE_USER0 = 20;
    public static final int FS = 44100;
    public static final int SourceVolumeDef_Switch_AllVolDef = 3;
    public static final int SourceVolumeDef_Switch_BigVolDef = 1;
    public static final int SourceVolumeDef_Switch_Off = 0;
    public static final int SourceVolumeDef_Switch_SmallVolDef = 2;

    public enum BEEP_TYPE {
        MCUBEEP,
        AudioChipBEEP
    }

    public enum DEVICE_TYPE {
        AKM7738,
        BU32107,
        TDA7719,
        BU37534,
        AKM7739,
        AKM7739_HORN10
    }

    public enum GEQ {
        FREQ,
        Q,
        GAIN
    }

    public enum HORN_TYPE {
        FL,
        FR,
        RL,
        RR,
        SUB,
        CENTER,
        AMP_CH1,
        AMP_CH2,
        AMP_CH3,
        AMP_CH4,
        AMP_CH5,
        AMP_CH6
    }

    public enum SETTING_GET {
        getSourceVolume,
        getVolume,
        getReverseVolume,
        getDelay,
        getSub,
        getFaderBalance,
        getLoudness,
        getLpf,
        getHpf
    }

    public enum SETTING_SET {
        setGain,
        updateGainConfig,
        updateSourceEq,
        updateFaderBalance,
        setLoundness,
        enableSubwoof,
        updateSubwoof,
        setVolume,
        updateSpeakerDelay,
        updateNaviAudioConfig,
        setReverseMute,
        setSourceVolume,
        setReverseVolume,
        saveSourceVolume,
        sourceSetVolume,
        testAudioChanel,
        setTempMute,
        setSourceVolumeNotTip,
        setMicrophoneInputChannel,
        setBootMute,
        updateHornEq,
        setLpf,
        setHpf
    }

    public enum STD_EQ {
        FLAT,
        POP,
        EASY,
        ROCK,
        CLASSIC,
        JAZZ,
        SITE,
        DANCE,
        MAX
    }

    public static int getShareVolume(int i, int i2, boolean z, boolean z2) {
        return (z2 ? 1048576 : 0) + (i << 8) + (i2 & WorkQueueKt.MASK) + (z ? 128 : 0);
    }

    public static int getSource(int i) {
        return (i >> 8) & MpegConstantsDef.MPEG_MSG_NULL;
    }

    public static int getVolume(int i) {
        return i & WorkQueueKt.MASK;
    }

    public static boolean isMute(int i) {
        return (i & 128) != 0;
    }

    public static boolean needShow(int i) {
        return (i & 1048576) != 0;
    }

    public enum USER_EQ {
        USER1,
        USER2,
        USER3,
        USER4;

        public int getId() {
            return ordinal() + AudioConstants.EQTYPE_USER0;
        }
    }
}
