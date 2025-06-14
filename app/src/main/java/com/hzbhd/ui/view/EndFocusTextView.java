package com.hzbhd.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EndFocusTextView.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0014J0\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\u0014J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\tH\u0014J\u001c\u0010\u001a\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/hzbhd/ui/view/EndFocusTextView;", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "refreshText", "", "isFocused", "onAttachedToWindow", "", "onLayout", "changed", "left", "top", "right", "bottom", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "setText", "text", "", "type", "Landroid/widget/TextView$BufferType;", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
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
