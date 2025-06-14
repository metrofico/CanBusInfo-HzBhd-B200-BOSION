package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnOnStarPhoneMoreInfoClickListener;
import com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener;

/* loaded from: classes2.dex */
public class OnStartPageUiSet {
    private String[] btnAction;
    private OnOnStarPhoneMoreInfoClickListener mOnOnStarPhoneMoreInfoClickListener;
    private OnOnStartStatusChangeListener mOnOnStartStatusChangeListener;
    private OnOnStarClickListener onOnStarClickListener;

    public OnOnStarClickListener getOnOnStarClickListener() {
        return this.onOnStarClickListener;
    }

    public void setOnOnStarClickListener(OnOnStarClickListener onOnStarClickListener) {
        this.onOnStarClickListener = onOnStarClickListener;
    }

    public String[] getBtnAction() {
        return this.btnAction;
    }

    public void setBtnAction(String[] strArr) {
        this.btnAction = strArr;
    }

    public OnOnStarPhoneMoreInfoClickListener getOnOnStarPhoneMoreInfoClickListener() {
        return this.mOnOnStarPhoneMoreInfoClickListener;
    }

    public void setmOnOnStarPhoneMoreInfoClickListener(OnOnStarPhoneMoreInfoClickListener onOnStarPhoneMoreInfoClickListener) {
        this.mOnOnStarPhoneMoreInfoClickListener = onOnStarPhoneMoreInfoClickListener;
    }

    public OnOnStartStatusChangeListener getOnOnStartStatusChangeListener() {
        return this.mOnOnStartStatusChangeListener;
    }

    public void setOnOnStartStatusChangeListener(OnOnStartStatusChangeListener onOnStartStatusChangeListener) {
        this.mOnOnStartStatusChangeListener = onOnStartStatusChangeListener;
    }
}
