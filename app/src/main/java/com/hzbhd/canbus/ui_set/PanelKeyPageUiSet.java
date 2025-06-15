package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;

import java.util.List;


public final class PanelKeyPageUiSet {
    private int count = 3;
    private List<String> list;
    private OnPanelKeyPositionListener onPanelKeyPositionListener;
    private OnPanelKeyPositionTouchListener onPanelKeyPositionTouchListener;
    private OnPanelLongKeyPositionListener onPanelLongKeyPositionListener;

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    public final List<String> getList() {
        return this.list;
    }

    public final void setList(List<String> list) {
        this.list = list;
    }

    public final OnPanelKeyPositionListener getOnPanelKeyPositionListener() {
        return this.onPanelKeyPositionListener;
    }

    public final void setOnPanelKeyPositionListener(OnPanelKeyPositionListener onPanelKeyPositionListener) {
        this.onPanelKeyPositionListener = onPanelKeyPositionListener;
    }

    public final OnPanelLongKeyPositionListener getOnPanelLongKeyPositionListener() {
        return this.onPanelLongKeyPositionListener;
    }

    public final void setOnPanelLongKeyPositionListener(OnPanelLongKeyPositionListener onPanelLongKeyPositionListener) {
        this.onPanelLongKeyPositionListener = onPanelLongKeyPositionListener;
    }

    public final OnPanelKeyPositionTouchListener getOnPanelKeyPositionTouchListener() {
        return this.onPanelKeyPositionTouchListener;
    }

    public final void setOnPanelKeyPositionTouchListener(OnPanelKeyPositionTouchListener onPanelKeyPositionTouchListener) {
        this.onPanelKeyPositionTouchListener = onPanelKeyPositionTouchListener;
    }
}
