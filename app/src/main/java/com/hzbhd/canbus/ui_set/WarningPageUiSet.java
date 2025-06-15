package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener;

public final class WarningPageUiSet {
    private OnWarningStatusChangeListener onWarningStatusChangeListener;

    public final OnWarningStatusChangeListener getOnWarningStatusChangeListener() {
        return this.onWarningStatusChangeListener;
    }

    public final void setOnWarningStatusChangeListener(OnWarningStatusChangeListener onWarningStatusChangeListener) {
        this.onWarningStatusChangeListener = onWarningStatusChangeListener;
    }
}
