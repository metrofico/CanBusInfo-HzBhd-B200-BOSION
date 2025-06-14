package com.hzbhd.canbus.car._141;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes.dex */
public class AmpUtil {
    private static String _141_AMP_BASS = "_141_AMP_BASS";
    private static String _141_AMP_FR = "_141_AMP_FR";
    private static String _141_AMP_LR = "_141_AMP_LR";
    private static String _141_AMP_MIDDLE = "_141_AMP_MIDDLE";
    private static String _141_AMP_TREBLE = "_141_AMP_TER";
    private static String _141_AMP_UI_BASS = "_141_AMP_UI_BASS";
    private static String _141_AMP_UI_FR = "_141_AMP_UI_FR";
    private static String _141_AMP_UI_LR = "_141_AMP_UI_LR";
    private static String _141_AMP_UI_MIDDLE = "_141_AMP_UI_MIDDLE";
    private static String _141_AMP_UI_TREBLE = "_141_AMP_UI_TER";
    private static String _141_AMP_UI_VOL = "_141_AMP_UI_VOL";
    private static String _141_AMP_VOL = "_141_AMP_VOL";
    MsgMgr mMsgMgr;

    public void intiAmpUi(Context context) {
        GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, _141_AMP_UI_TREBLE, 0);
        GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, _141_AMP_UI_BASS, 0);
        GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, _141_AMP_UI_FR, 0);
        GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, _141_AMP_UI_LR, 0);
        getMsgMgr(context).updateAmp();
    }

    public void initAmpData(Context context) {
        sendAmplifierInfo(3, SharePreUtil.getIntValue(context, _141_AMP_FR, 0));
        sendAmplifierInfo(4, SharePreUtil.getIntValue(context, _141_AMP_LR, 0));
        sendAmplifierInfo(6, SharePreUtil.getIntValue(context, _141_AMP_TREBLE, 0));
        sendAmplifierInfo(5, SharePreUtil.getIntValue(context, _141_AMP_BASS, 0));
    }

    public static void saveAmpSendValue(Context context, int i, int i2) {
        switch (i) {
            case 1:
                SharePreUtil.setIntValue(context, _141_AMP_VOL, i2);
                break;
            case 2:
                SharePreUtil.setIntValue(context, _141_AMP_TREBLE, i2);
                break;
            case 3:
                SharePreUtil.setIntValue(context, _141_AMP_MIDDLE, i2);
                break;
            case 4:
                SharePreUtil.setIntValue(context, _141_AMP_BASS, i2);
                break;
            case 5:
                SharePreUtil.setIntValue(context, _141_AMP_FR, i2);
                break;
            case 6:
                SharePreUtil.setIntValue(context, _141_AMP_LR, i2);
                break;
        }
    }

    public static void saveAmpUIValue(Context context) {
        SharePreUtil.setIntValue(context, _141_AMP_UI_VOL, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(context, _141_AMP_UI_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(context, _141_AMP_UI_MIDDLE, GeneralAmplifierData.bandMiddle);
        SharePreUtil.setIntValue(context, _141_AMP_UI_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(context, _141_AMP_UI_FR, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(context, _141_AMP_UI_LR, GeneralAmplifierData.leftRight);
    }

    private void sendAmplifierInfo(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
