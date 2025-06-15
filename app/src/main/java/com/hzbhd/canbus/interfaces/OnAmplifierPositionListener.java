package com.hzbhd.canbus.interfaces;

import com.hzbhd.canbus.activity.AmplifierActivity;


public interface OnAmplifierPositionListener {
    void position(AmplifierActivity.AmplifierPosition amplifierPosition, int value);
}
