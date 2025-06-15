package com.hzbhd.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.GravityCompat;

import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


@SuppressLint("AppCompatCustomView")
public final class EndFocusTextView extends TextView {
    private boolean refreshText;

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public EndFocusTextView(Context context) {
        super(context);
    }

    public EndFocusTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EndFocusTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        if (LogUtil.log5()) {
            LogUtil.d("onLayout: " + ((Object) getText()));
        }
        if (this.refreshText) {
            this.refreshText = false;
            setGravity(getPaint().measureText(getText().toString()) > ((float) getMeasuredWidth()) ? GravityCompat.START : GravityCompat.END);
            super.setText(getText(), TextView.BufferType.NORMAL);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.refreshText = true;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        if (LogUtil.log5()) {
            LogUtil.d("onVisibilityChanged: " + visibility);
        }
        if (visibility == 0) {
            this.refreshText = true;
            requestLayout();
        }
    }

    @Override // android.widget.TextView
    public void setText(CharSequence text, TextView.BufferType type) {
        if (!TextUtils.isEmpty(text)) {
            boolean z = false;
            if (text != null && !text.equals(getText())) {
                z = true;
            }
            if (z) {
                setGravity(getPaint().measureText(text.toString()) > ((float) getMeasuredWidth()) ? GravityCompat.START : GravityCompat.END);
                super.setText(text, type);
                return;
            }
            return;
        }
        super.setText(text, type);
    }
}
