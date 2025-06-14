package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes2.dex */
public class EverScrollTextView extends AppCompatTextView {
    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public EverScrollTextView(Context context) {
        super(context);
    }

    public EverScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EverScrollTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
