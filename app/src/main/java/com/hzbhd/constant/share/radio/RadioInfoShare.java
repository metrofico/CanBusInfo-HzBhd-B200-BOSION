package com.hzbhd.constant.share.radio;

/* loaded from: classes2.dex */
public class RadioInfoShare {
    private String current_band;
    private String current_freq;
    private int preset_index;
    private String ps_name;
    private int stereo;

    public void setCurrent_band(String str) {
        this.current_band = str;
    }

    public String getCurrent_band() {
        return this.current_band;
    }

    public void setCurrent_freq(String str) {
        this.current_freq = str;
    }

    public String getCurrent_freq() {
        return this.current_freq;
    }

    public void setPs_name(String str) {
        this.ps_name = str;
    }

    public String getPs_name() {
        return this.ps_name;
    }

    public void setPreset_index(int i) {
        this.preset_index = i;
    }

    public int getPreset_index() {
        return this.preset_index;
    }

    public void setStereo(int i) {
        this.stereo = i;
    }

    public int getStereo() {
        return this.stereo;
    }
}
