package com.hzbhd.canbus.interfaces;

import com.hzbhd.canbus.activity.AmplifierActivity;


public interface OnAmplifierSeekBarListener {
    void progress(AmplifierActivity.AmplifierBand amplifierBand, int progress);
}
