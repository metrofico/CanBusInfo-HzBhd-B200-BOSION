package com.hzbhd.canbus.car._459.settings;

import android.os.CountDownTimer;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;

/* loaded from: classes2.dex */
public class SettingsDataBuffer {
    private boolean bufferTag;
    private boolean cw;
    private boolean ld;
    private boolean sa;
    private boolean sps;
    private boolean swa;
    private CountDownTimer timer;
    private String vdm;

    private static class Holder {
        private static final SettingsDataBuffer INSTANCE = new SettingsDataBuffer();

        private Holder() {
        }
    }

    private SettingsDataBuffer() {
        this.bufferTag = false;
        this.sps = false;
        this.sa = true;
        this.ld = true;
        this.cw = true;
        this.swa = false;
        this.vdm = "MID";
    }

    public static SettingsDataBuffer getInstance() {
        return Holder.INSTANCE;
    }

    public void resetTimer() {
        this.bufferTag = true;
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._459.settings.SettingsDataBuffer.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                SettingsDataBuffer.this.bufferTag = false;
                SettingsDataBuffer settingsDataBuffer = SettingsDataBuffer.this;
                settingsDataBuffer.notifyOtherModule("SPS", Boolean.valueOf(settingsDataBuffer.sps));
                SettingsDataBuffer settingsDataBuffer2 = SettingsDataBuffer.this;
                settingsDataBuffer2.notifyOtherModule("SA", Boolean.valueOf(settingsDataBuffer2.sa));
                SettingsDataBuffer settingsDataBuffer3 = SettingsDataBuffer.this;
                settingsDataBuffer3.notifyOtherModule("LD", Boolean.valueOf(settingsDataBuffer3.ld));
                SettingsDataBuffer settingsDataBuffer4 = SettingsDataBuffer.this;
                settingsDataBuffer4.notifyOtherModule("CW", Boolean.valueOf(settingsDataBuffer4.cw));
                SettingsDataBuffer settingsDataBuffer5 = SettingsDataBuffer.this;
                settingsDataBuffer5.notifyOtherModule("VDM", settingsDataBuffer5.vdm);
                SettingsDataBuffer settingsDataBuffer6 = SettingsDataBuffer.this;
                settingsDataBuffer6.notifyOtherModule("SWA", Boolean.valueOf(settingsDataBuffer6.swa));
                OptionSettingsCmd459.getInstance().spsState = SettingsDataBuffer.this.sps;
                OptionSettingsCmd459.getInstance().setSA(SettingsDataBuffer.this.sa);
                OptionSettingsCmd459.getInstance().setLD(Boolean.valueOf(SettingsDataBuffer.this.ld));
                OptionSettingsCmd459.getInstance().setCW(Boolean.valueOf(SettingsDataBuffer.this.cw));
                OptionSettingsCmd459.getInstance().setVDM(SettingsDataBuffer.this.vdm);
            }
        };
        this.timer = countDownTimer2;
        countDownTimer2.start();
    }

    public void setSpsBuffer(boolean z) {
        if (!this.bufferTag && OptionSettingsCmd459.getInstance().spsState != z) {
            notifyOtherModule("SPS", Boolean.valueOf(z));
        }
        this.sps = z;
        OptionSettingsCmd459.getInstance().spsState = z;
        OptionSettingsCmd459.getInstance().booleanSaver(OptionSettingsCmd459.getInstance().TAG_SPS, Boolean.valueOf(z));
    }

    public void setSaBuffer(boolean z) {
        if (!this.bufferTag && OptionSettingsCmd459.getInstance().saState != z) {
            notifyOtherModule("SA", Boolean.valueOf(z));
        }
        this.sa = z;
        OptionSettingsCmd459.getInstance().saState = z;
        OptionSettingsCmd459.getInstance().booleanSaver(OptionSettingsCmd459.getInstance().TAG_SA, Boolean.valueOf(z));
    }

    public void setAwsBuffer(boolean z) {
        if (!this.bufferTag && OptionSettingsCmd459.getInstance().awsState != z) {
            notifyOtherModule("AWS", Boolean.valueOf(z));
        }
        this.swa = z;
        OptionSettingsCmd459.getInstance().awsState = z;
        OptionSettingsCmd459.getInstance().booleanSaver(OptionSettingsCmd459.getInstance().TAG_AWS, Boolean.valueOf(z));
    }

    public void setLdBuffer(boolean z) {
        if (!this.bufferTag && OptionSettingsCmd459.getInstance().ldState != z) {
            notifyOtherModule("LD", Boolean.valueOf(z));
        }
        this.ld = z;
        OptionSettingsCmd459.getInstance().ldState = z;
    }

    public void setCwBuffer(boolean z) {
        if (!this.bufferTag && OptionSettingsCmd459.getInstance().cwState != z) {
            notifyOtherModule("CW", Boolean.valueOf(z));
        }
        this.cw = z;
        OptionSettingsCmd459.getInstance().cwState = z;
    }

    public void setVdmBuffer(String str) {
        if (!this.bufferTag && OptionSettingsCmd459.getInstance().vdmState != str) {
            notifyOtherModule("VDM", str);
        }
        this.vdm = str;
        OptionSettingsCmd459.getInstance().vdmState = str;
        OptionSettingsCmd459.getInstance().stringSaver(OptionSettingsCmd459.getInstance().TAG_VDM, str);
    }

    public void notifyOtherModule(String str, Object obj) {
        ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"" + str + "\",\"VALUE\":\"" + obj + "\"}");
    }
}
