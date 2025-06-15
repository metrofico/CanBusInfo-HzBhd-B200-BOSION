package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnSyncItemClickListener;
import com.hzbhd.canbus.interfaces.OnSyncStateChangeListener;


public final class SyncPageUiSet {
    private boolean isShowScreemNumber;
    private String[][] keyboardActions;
    private String[] leftBtnActions;
    private OnSyncItemClickListener onSyncItemClickListener;
    private OnSyncStateChangeListener onSyncStateChangeListener;

    public final String[] getLeftBtnActions() {
        return this.leftBtnActions;
    }

    public final void setLeftBtnActions(String[] strArr) {
        this.leftBtnActions = strArr;
    }

    public final String[][] getKeyboardActions() {
        return this.keyboardActions;
    }

    public final void setKeyboardActions(String[][] strArr) {
        this.keyboardActions = strArr;
    }

    public final OnSyncStateChangeListener getOnSyncStateChangeListener() {
        return this.onSyncStateChangeListener;
    }

    public final void setOnSyncStateChangeListener(OnSyncStateChangeListener onSyncStateChangeListener) {
        this.onSyncStateChangeListener = onSyncStateChangeListener;
    }

    public final OnSyncItemClickListener getOnSyncItemClickListener() {
        return this.onSyncItemClickListener;
    }

    public final void setOnSyncItemClickListener(OnSyncItemClickListener onSyncItemClickListener) {
        this.onSyncItemClickListener = onSyncItemClickListener;
    }

    /* renamed from: isShowScreemNumber, reason: from getter */
    public final boolean getIsShowScreemNumber() {
        return this.isShowScreemNumber;
    }

    public final void setShowScreemNumber(boolean z) {
        this.isShowScreemNumber = z;
    }
}
