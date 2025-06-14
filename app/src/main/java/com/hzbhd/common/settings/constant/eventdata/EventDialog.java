package com.hzbhd.common.settings.constant.eventdata;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EventDialog.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/hzbhd/common/settings/constant/eventdata/EventDialog;", "", "click_id", "", "action", "Lcom/hzbhd/common/settings/constant/eventdata/Action;", "view", "Landroid/view/View;", "(Ljava/lang/String;Lcom/hzbhd/common/settings/constant/eventdata/Action;Landroid/view/View;)V", "getAction", "()Lcom/hzbhd/common/settings/constant/eventdata/Action;", "getClick_id", "()Ljava/lang/String;", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "settings-constant_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class EventDialog {
    private final Action action;
    private final String click_id;
    private View view;

    public static /* synthetic */ EventDialog copy$default(EventDialog eventDialog, String str, Action action, View view, int i, Object obj) {
        if ((i & 1) != 0) {
            str = eventDialog.click_id;
        }
        if ((i & 2) != 0) {
            action = eventDialog.action;
        }
        if ((i & 4) != 0) {
            view = eventDialog.view;
        }
        return eventDialog.copy(str, action, view);
    }

    /* renamed from: component1, reason: from getter */
    public final String getClick_id() {
        return this.click_id;
    }

    /* renamed from: component2, reason: from getter */
    public final Action getAction() {
        return this.action;
    }

    /* renamed from: component3, reason: from getter */
    public final View getView() {
        return this.view;
    }

    public final EventDialog copy(String click_id, Action action, View view) {
        Intrinsics.checkNotNullParameter(click_id, "click_id");
        Intrinsics.checkNotNullParameter(action, "action");
        return new EventDialog(click_id, action, view);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventDialog)) {
            return false;
        }
        EventDialog eventDialog = (EventDialog) other;
        return Intrinsics.areEqual(this.click_id, eventDialog.click_id) && this.action == eventDialog.action && Intrinsics.areEqual(this.view, eventDialog.view);
    }

    public int hashCode() {
        int iHashCode = ((this.click_id.hashCode() * 31) + this.action.hashCode()) * 31;
        View view = this.view;
        return iHashCode + (view == null ? 0 : view.hashCode());
    }

    public String toString() {
        return "EventDialog(click_id=" + this.click_id + ", action=" + this.action + ", view=" + this.view + ')';
    }

    public EventDialog(String click_id, Action action, View view) {
        Intrinsics.checkNotNullParameter(click_id, "click_id");
        Intrinsics.checkNotNullParameter(action, "action");
        this.click_id = click_id;
        this.action = action;
        this.view = view;
    }

    public /* synthetic */ EventDialog(String str, Action action, View view, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, action, (i & 4) != 0 ? null : view);
    }

    public final String getClick_id() {
        return this.click_id;
    }

    public final Action getAction() {
        return this.action;
    }

    public final View getView() {
        return this.view;
    }

    public final void setView(View view) {
        this.view = view;
    }
}
