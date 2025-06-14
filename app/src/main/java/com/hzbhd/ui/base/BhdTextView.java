package com.hzbhd.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BhdTextView.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\b\u0010\u0012\u001a\u00020\u0010H\u0014R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/ui/base/BhdTextView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "viewDrawAble", "Lcom/hzbhd/ui/base/ViewDrawAble;", "viewText", "Lcom/hzbhd/ui/base/ViewText;", "onAttachedToWindow", "", "onDetachedFromWindow", "onFinishInflate", "bhdview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public class BhdTextView extends TextView {
    private ViewDrawAble viewDrawAble;
    private ViewText viewText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdTextView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.viewDrawAble = attributeSet == null ? null : new ViewDrawAble(this, context, attributeSet);
        this.viewText = attributeSet != null ? new ViewText(this, context, attributeSet) : null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.viewDrawAble = attributeSet == null ? null : new ViewDrawAble(this, context, attributeSet);
        this.viewText = attributeSet != null ? new ViewText(this, context, attributeSet) : null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewDrawAble viewDrawAble = this.viewDrawAble;
        if (viewDrawAble != null) {
            viewDrawAble.resetBackground();
        }
        ViewText viewText = this.viewText;
        if (viewText == null) {
            return;
        }
        viewText.resetTextColor();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewDrawAble viewDrawAble = this.viewDrawAble;
        if (viewDrawAble != null) {
            viewDrawAble.onAttachedToWindow();
        }
        ViewText viewText = this.viewText;
        if (viewText == null) {
            return;
        }
        viewText.onAttachedToWindow();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewDrawAble viewDrawAble = this.viewDrawAble;
        if (viewDrawAble != null) {
            viewDrawAble.onDetachedFromWindow();
        }
        ViewText viewText = this.viewText;
        if (viewText == null) {
            return;
        }
        viewText.onDetachedFromWindow();
    }
}
