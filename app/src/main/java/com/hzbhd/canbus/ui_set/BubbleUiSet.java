package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnBubbleClickListener;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: BubbleUiSet.kt */

public final class BubbleUiSet {
    private String iconDrawable = "default_image";
    private OnBubbleClickListener onBubbleClickListener;

    public final String getIconDrawable() {
        return this.iconDrawable;
    }

    public final void setIconDrawable(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.iconDrawable = str;
    }

    public final OnBubbleClickListener getOnBubbleClickListener() {
        return this.onBubbleClickListener;
    }

    public final void setOnBubbleClickListener(OnBubbleClickListener onBubbleClickListener) {
        this.onBubbleClickListener = onBubbleClickListener;
    }
}
