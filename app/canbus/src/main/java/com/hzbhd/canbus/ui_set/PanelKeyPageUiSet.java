package com.hzbhd.canbus.ui_set;

import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanelLongKeyPositionListener;
import java.util.List;
import kotlin.Metadata;

/* compiled from: PanelKeyPageUiSet.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/hzbhd/canbus/ui_set/PanelKeyPageUiSet;", "", "()V", "count", "", "getCount", "()I", "setCount", "(I)V", "list", "", "", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "onPanelKeyPositionListener", "Lcom/hzbhd/canbus/interfaces/OnPanelKeyPositionListener;", "getOnPanelKeyPositionListener", "()Lcom/hzbhd/canbus/interfaces/OnPanelKeyPositionListener;", "setOnPanelKeyPositionListener", "(Lcom/hzbhd/canbus/interfaces/OnPanelKeyPositionListener;)V", "onPanelKeyPositionTouchListener", "Lcom/hzbhd/canbus/interfaces/OnPanelKeyPositionTouchListener;", "getOnPanelKeyPositionTouchListener", "()Lcom/hzbhd/canbus/interfaces/OnPanelKeyPositionTouchListener;", "setOnPanelKeyPositionTouchListener", "(Lcom/hzbhd/canbus/interfaces/OnPanelKeyPositionTouchListener;)V", "onPanelLongKeyPositionListener", "Lcom/hzbhd/canbus/interfaces/OnPanelLongKeyPositionListener;", "getOnPanelLongKeyPositionListener", "()Lcom/hzbhd/canbus/interfaces/OnPanelLongKeyPositionListener;", "setOnPanelLongKeyPositionListener", "(Lcom/hzbhd/canbus/interfaces/OnPanelLongKeyPositionListener;)V", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
