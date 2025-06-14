package com.hzbhd.canbus.adapter.constant;

/* loaded from: classes.dex */
public interface RadioConstantsDef {

    public enum BandType {
        BAND_FM1,
        BAND_FM2,
        BAND_FM3,
        BAND_AM1,
        BAND_AM2,
        BAND_OIRT,
        BAND_MW,
        BAND_LW
    }

    public enum PTYType {
        NONE,
        NEWS,
        AFFAIRS,
        INFO,
        SPORT,
        EDUCATE,
        DRAMA,
        CULTURE,
        SCIENCE,
        VARIED,
        POP_M,
        ROCK_M,
        EASY_M,
        LIGHT_M,
        CLASSICS,
        OTHER_M,
        WEATHER,
        FINANCE,
        CHILDREN,
        SOCIAL,
        RELIGION,
        PHONE_IN,
        TRAVEL,
        LEISURE,
        JAZZ,
        COUNTRY,
        NATION_M,
        OLDIES,
        FOLK_M,
        DOCUMENT,
        TEST,
        ALARM
    }

    public enum RADIO_FAR_TYPE {
        LOCALE,
        REMOTE
    }

    public enum RADIO_TYPE {
        FM,
        AM
    }

    public enum RDSType {
        NORDS,
        RDS,
        RBDS
    }

    public enum RFMode {
        RF_MODE_LOC,
        RF_MODE_DX
    }

    public enum RadioArea {
        USA,
        ASIA,
        JAPAN,
        EUROPE,
        EUROPE_LW,
        OIRT,
        S_AMER1,
        S_AMER2,
        KOREA,
        AUSTRALIA,
        SOUTHEAST_ASIA,
        BRAZIL
    }

    public enum SearchType {
        SEARCH_DEFAULT,
        SEARCH_ALL_BAND_FROM_MIN_FREQ,
        SEARCH_CURRENT_BAND_FROM_CURRENT_FREQ,
        SEARCH_CURRENT_BAND_FROM_MIN_FREQ
    }

    public enum StepType {
        STEP_DEFAULT,
        STEP_AUTO,
        SETP_MANUAL
    }

    public enum VoiceChannelType {
        STEREO,
        MONO
    }
}
