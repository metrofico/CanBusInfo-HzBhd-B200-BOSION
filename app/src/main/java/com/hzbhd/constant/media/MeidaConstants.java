package com.hzbhd.constant.media;

/* loaded from: classes2.dex */
public class MeidaConstants {

    public enum ACTION {
        favour,
        unfavour,
        favourplay,
        play_list_open,
        play_list_close,
        favour_list_open,
        favour_list_close
    }

    public enum CLOSE_REASON {
        VOICE,
        SOURCE_RELEASE
    }

    public enum MEDIA_TYPE {
        MUSIC,
        VIDEO,
        IMAGE
    }

    public enum PLAY_MODE {
        REPEAT_ALL,
        SHUFFLE,
        REPEAT_ONE,
        REPEAT_FOLDER
    }

    public enum PLAY_STATE {
        IDLE,
        PREPARING,
        PREPARED,
        STARTED,
        PAUSED,
        STOPPED,
        COMPLETED,
        ERROR,
        RELEASED
    }
}
