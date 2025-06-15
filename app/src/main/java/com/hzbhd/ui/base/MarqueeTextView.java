package com.hzbhd.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView {
    private boolean refreshText;

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarqueeTextView(Context context) {
        super(context);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setSingleLine();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setSingleLine();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarqueeTextView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setSingleLine();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        if (LogUtil.log5()) {

        }
        if (this.refreshText) {
            this.refreshText = false;
            super.setText(getText(), BufferType.NORMAL);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.refreshText = true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (LogUtil.log5()) {

        }
        if (visibility == 0) {
            this.refreshText = true;
            requestLayout();
        }
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            boolean z = !text.equals(getText());
            if (z) {
                super.setText(text, type);
                return;
            }
            return;
        }
        super.setText(text, type);
    }
}
