package com.hzbhd.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.util.LogUtil;

@SuppressLint("AppCompatCustomView")

public final class FocusTextView extends TextView {
    private boolean refreshText;

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public FocusTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        String string;
        TypedArray typedArrayObtainStyledAttributes = context != null ? context.obtainStyledAttributes(attributeSet, R.styleable.fontAttr) : null;
        if (typedArrayObtainStyledAttributes != null && (string = typedArrayObtainStyledAttributes.getString(R.styleable.fontAttr_font_path)) != null) {
            setTypeface(Typeface.createFromAsset(context.getAssets(), string));
        }
        if (typedArrayObtainStyledAttributes != null) {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public FocusTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public FocusTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FocusTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        if (LogUtil.log2()) {
            LogUtil.d("onLayout: " + ((Object) getText()));
        }
        if (this.refreshText) {
            this.refreshText = false;
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
                super.setText(text, type);
                return;
            }
            return;
        }
        super.setText(text, type);
    }
}
