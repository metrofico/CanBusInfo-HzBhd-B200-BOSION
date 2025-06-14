package com.hzbhd.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public class BhdFrameLayout extends FrameLayout {
    private ViewDrawAble viewDrawAble;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdFrameLayout(Context context) {
        super(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.viewDrawAble = attributeSet == null ? null : new ViewDrawAble(this, context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.viewDrawAble = attributeSet == null ? null : new ViewDrawAble(this, context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.viewDrawAble = attributeSet == null ? null : new ViewDrawAble(this, context, attributeSet);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewDrawAble viewDrawAble = this.viewDrawAble;
        if (viewDrawAble == null) {
            return;
        }
        viewDrawAble.resetBackground();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewDrawAble viewDrawAble = this.viewDrawAble;
        if (viewDrawAble == null) {
            return;
        }
        viewDrawAble.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewDrawAble viewDrawAble = this.viewDrawAble;
        if (viewDrawAble == null) {
            return;
        }
        viewDrawAble.onDetachedFromWindow();
    }
}
