package com.hzbhd.canbus.car._269;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private boolean mIsClickReset = false;
    private Timer mTimer;
    private MsgMgr msgMgr;

    private void intFirstUi() {
        TimerTask timerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._269.UiMgr.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    if (SharePreUtil.getIntValue(UiMgr.this.mContext, "CAN_269_SAVE_IS_AMP_FIRST", 1) == 1) {
                        UiMgr.this.ampReset();
                        UiMgr.this.msgMgr.updateAmpUi(UiMgr.this.mContext);
                        SharePreUtil.setIntValue(UiMgr.this.mContext, "CAN_269_SAVE_IS_AMP_FIRST", 0);
                    }
                } catch (Exception unused) {
                }
                UiMgr.this.mTimer.cancel();
            }
        };
        Timer timer = new Timer();
        this.mTimer = timer;
        timer.schedule(timerTask, 1500L, 500L);
    }

    public UiMgr(Context context) {
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        this.mContext = context;
        AmplifierPageUiSet amplifierPageUiSet = getAmplifierPageUiSet(context);
        amplifierPageUiSet.setOnAmplifierSeekBarListener(new OnAmplifierSeekBarListener() { // from class: com.hzbhd.canbus.car._269.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener
            public void progress(AmplifierActivity.AmplifierBand amplifierBand, int i) {
                int i2 = AnonymousClass5.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[amplifierBand.ordinal()];
                if (i2 == 1) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "__269_SAVE_AMP_BASS", i);
                    GeneralAmplifierData.bandBass = i;
                    UiMgr.this.sendAmpCmds();
                    return;
                }
                if (i2 == 2) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "__269_SAVE_AMP_TRE", i);
                    GeneralAmplifierData.bandTreble = i;
                    UiMgr.this.sendAmpCmds();
                    return;
                }
                if (i2 == 3) {
                    UiMgr.this.mIsClickReset = false;
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "__269_SAVE_AMP_MID", i);
                    GeneralAmplifierData.bandMiddle = i;
                    UiMgr.this.sendAmpCmds();
                    return;
                }
                if (i2 != 4) {
                    return;
                }
                UiMgr.this.mIsClickReset = false;
                SharePreUtil.setIntValue(UiMgr.this.mContext, "__269_SAVE_AMP_VOL", i);
                GeneralAmplifierData.volume = i;
                UiMgr.this.sendAmpCmds();
            }
        });
        amplifierPageUiSet.setOnAmplifierPositionListener(new OnAmplifierPositionListener() { // from class: com.hzbhd.canbus.car._269.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierPositionListener
            public void position(AmplifierActivity.AmplifierPosition amplifierPosition, int i) {
                int i2 = AnonymousClass5.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[amplifierPosition.ordinal()];
                if (i2 == 1) {
                    if (UiMgr.this.mIsClickReset) {
                        return;
                    }
                    SharePreUtil.setIntValue(UiMgr.this.mContext, "__269_SAVE_AMP_FR", i);
                    GeneralAmplifierData.frontRear = i;
                    UiMgr.this.sendAmpCmds();
                    return;
                }
                if (i2 != 2) {
                    return;
                }
                if (UiMgr.this.mIsClickReset) {
                    UiMgr.this.mIsClickReset = false;
                    return;
                }
                SharePreUtil.setIntValue(UiMgr.this.mContext, "__269_SAVE_AMP_LR", i);
                GeneralAmplifierData.leftRight = i;
                UiMgr.this.sendAmpCmds();
            }
        });
        amplifierPageUiSet.setOnAmplifierResetPositionListener(new OnAmplifierResetPositionListener() { // from class: com.hzbhd.canbus.car._269.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnAmplifierResetPositionListener
            public void resetClick() {
                UiMgr.this.ampReset();
                UiMgr.this.sendAmpCmds();
                UiMgr.this.mIsClickReset = true;
                UiMgr.this.msgMgr.updateAmpUi(null);
            }
        });
        intFirstUi();
    }

    /* renamed from: com.hzbhd.canbus.car._269.UiMgr$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand;
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition;

        static {
            int[] iArr = new int[AmplifierActivity.AmplifierPosition.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition = iArr;
            try {
                iArr[AmplifierActivity.AmplifierPosition.FRONT_REAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[AmplifierActivity.AmplifierPosition.LEFT_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[AmplifierActivity.AmplifierBand.values().length];
            $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand = iArr2;
            try {
                iArr2[AmplifierActivity.AmplifierBand.BASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.TREBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[AmplifierActivity.AmplifierBand.VOLUME.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmpCmds() {
        CanbusMsgSender.sendMsg(new byte[]{22, -93, (byte) (((byte) GeneralAmplifierData.volume) + 22), (byte) (((byte) GeneralAmplifierData.frontRear) + 10), (byte) (((byte) GeneralAmplifierData.leftRight) + 10), (byte) (((byte) GeneralAmplifierData.bandBass) + 1), (byte) (((byte) GeneralAmplifierData.bandMiddle) + 1), (byte) (((byte) GeneralAmplifierData.bandTreble) + 1)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ampReset() {
        GeneralAmplifierData.bandBass = 9;
        GeneralAmplifierData.bandMiddle = 9;
        GeneralAmplifierData.bandTreble = 9;
        GeneralAmplifierData.volume = 8;
        GeneralAmplifierData.frontRear = 0;
        GeneralAmplifierData.leftRight = 0;
        SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_VOL", GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_BASS", GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_MID", GeneralAmplifierData.bandMiddle);
        SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_TRE", GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_FR", GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, "__269_SAVE_AMP_LR", GeneralAmplifierData.leftRight);
    }
}
