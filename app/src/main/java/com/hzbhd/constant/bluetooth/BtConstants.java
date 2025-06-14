package com.hzbhd.constant.bluetooth;

/* loaded from: classes2.dex */
public class BtConstants {

    public enum BT_ACTION {
        SCAN,
        STOP_SCAN,
        A2DP_PLAY,
        A2DP_PAUSE,
        A2DP_PREV,
        A2DP_NEXT,
        A2DP_REQUEST,
        A2DP_STOP,
        ANSWER,
        HANDUP,
        AUTOCONN,
        NOT_AUTOCONN,
        MUTE_MIC,
        UNMUTE_MIC,
        MIC_OUT,
        MIC_IN,
        MIC_CAR,
        MIC_PHONE,
        SYNC_PHONE_BOOK,
        CONN_HFP,
        DISCONN_HFP,
        CONN_A2DP,
        DISCONN_A2DP,
        PAIR,
        UNPAIR,
        AS_KEY,
        ENABLE_BT,
        DISABLE_BT,
        ENABLE_A2DP,
        DISABLE_A2DP,
        ENABLE_HFP,
        DISABLE_HFP,
        SET_AA_DEVICE,
        SET_CP_DEVICE,
        AUTO_ANSWER,
        NOT_AUTO_ANSWER
    }

    public enum BT_STATUS {
        ENABLE,
        HFP_CONN,
        A2DP_CONN,
        SCANNING,
        PAIRING,
        CONNING,
        IN_HFP,
        IN_A2DP,
        A2DP_PLAYING,
        CALLING,
        INCOMING,
        OUTGOING,
        SYNC_PB_ING,
        SYNC_PB_FINISH,
        DISABLE
    }

    public enum CallDevice {
        Phone,
        SWITCHING,
        Car
    }

    public enum CallState {
        NORMAL,
        OUT_CALLING,
        IN_CALLING,
        CALLING,
        END_CALLING
    }

    public enum SETTING_GET {
        getBtPhoneBook
    }

    public enum SETTING_SET {
        setBtCall
    }
}
