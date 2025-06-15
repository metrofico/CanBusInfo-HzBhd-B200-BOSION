package com.hzbhd.canbus.interfaces;

import com.hzbhd.canbus.activity.AmplifierActivity;

public interface OnAmplifierSeekPlusMinListener {
    void min(AmplifierActivity.AmplifierBand amplifierBand);

    void plus(AmplifierActivity.AmplifierBand amplifierBand);
}
