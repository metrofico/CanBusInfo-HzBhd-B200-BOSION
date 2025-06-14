package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnBubbleClickListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BubbleUiSet.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/hzbhd/canbus/ui_set/BubbleUiSet;", "", "()V", "iconDrawable", "", "getIconDrawable", "()Ljava/lang/String;", "setIconDrawable", "(Ljava/lang/String;)V", "onBubbleClickListener", "Lcom/hzbhd/canbus/interfaces/OnBubbleClickListener;", "getOnBubbleClickListener", "()Lcom/hzbhd/canbus/interfaces/OnBubbleClickListener;", "setOnBubbleClickListener", "(Lcom/hzbhd/canbus/interfaces/OnBubbleClickListener;)V", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
