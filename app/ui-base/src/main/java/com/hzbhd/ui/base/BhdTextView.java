package com.hzbhd.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint("AppCompatCustomView")
public class BhdTextView extends TextView {
    private ViewDrawAble viewDrawAble;
    private ViewText viewText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdTextView(Context context) {
        super(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.viewDrawAble = attributeSet == null ? null : new ViewDrawAble(this, context, attributeSet);
        this.viewText = attributeSet != null ? new ViewText(this, context, attributeSet) : null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
