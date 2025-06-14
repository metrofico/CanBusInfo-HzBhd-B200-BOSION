package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnWarningStatusChangeListener;
import kotlin.Metadata;

/* compiled from: WarningPageUiSet.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/ui_set/WarningPageUiSet;", "", "()V", "onWarningStatusChangeListener", "Lcom/hzbhd/canbus/interfaces/OnWarningStatusChangeListener;", "getOnWarningStatusChangeListener", "()Lcom/hzbhd/canbus/interfaces/OnWarningStatusChangeListener;", "setOnWarningStatusChangeListener", "(Lcom/hzbhd/canbus/interfaces/OnWarningStatusChangeListener;)V", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class WarningPageUiSet {
    private OnWarningStatusChangeListener onWarningStatusChangeListener;

    public final OnWarningStatusChangeListener getOnWarningStatusChangeListener() {
        return this.onWarningStatusChangeListener;
    }

    public final void setOnWarningStatusChangeListener(OnWarningStatusChangeListener onWarningStatusChangeListener) {
        this.onWarningStatusChangeListener = onWarningStatusChangeListener;
    }
}
