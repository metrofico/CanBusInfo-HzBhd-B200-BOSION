package com.hzbhd.constant.audio;

public class AudioConstants {
    public static int EQTYPE_USER0 = 20;
    public static final int FS = 44100;

    public static final int MASK = 0x7F;           // 127
    public static final int MPEG_MSG_NULL = 0xFFF; // 4095

    public static final int SourceVolumeDef_Switch_Off = 0;
    public static final int SourceVolumeDef_Switch_BigVolDef = 1;
    public static final int SourceVolumeDef_Switch_SmallVolDef = 2;
    public static final int SourceVolumeDef_Switch_AllVolDef = 3;

    /**
     * Codifica el volumen compartido combinando varias propiedades en un solo valor entero.
     *
     * @param sourceId      ID de la fuente (por ejemplo: AUX, RADIO, etc.)
     * @param volume        Valor del volumen (0 a 127)
     * @param isMuted       Indica si está silenciado
     * @param shouldDisplay Indica si se debe mostrar el valor
     * @return Entero codificado representando todas las propiedades
     */
    public static int getShareVolume(int sourceId, int volume, boolean isMuted, boolean shouldDisplay) {
        return (shouldDisplay ? 0x100000 : 0) + (sourceId << 8) + (volume & MASK) + (isMuted ? 0x80 : 0);
    }

    /**
     * Extrae el ID de fuente desde el valor codificado.
     */
    public static int getSource(int encodedVolume) {
        return (encodedVolume >> 8) & MPEG_MSG_NULL;
    }

    /**
     * Extrae el volumen desde el valor codificado.
     */
    public static int getVolume(int encodedVolume) {
        return encodedVolume & MASK;
    }

    /**
     * Verifica si el valor codificado indica que está silenciado.
     */
    public static boolean isMute(int encodedVolume) {
        return (encodedVolume & 0x80) != 0;
    }

    /**
     * Verifica si el valor codificado indica que debe mostrarse.
     */
    public static boolean needShow(int encodedVolume) {
        return (encodedVolume & 0x100000) != 0;
    }

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

    public enum USER_EQ {
        USER1,
        USER2,
        USER3,
        USER4;

        public int getId() {
            return this.ordinal() + EQTYPE_USER0;
        }
    }
}
