package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/* loaded from: classes2.dex */
public class SyncTopIconIconView extends LinearLayout {
    public SyncTopIconIconView(Context context) {
        super(context);
    }

    public SyncTopIconIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initIcon(Context context, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
            addView(imageView);
        }
    }

    public ImageView getItem(int i) {
        return (ImageView) getChildAt(i);
    }
}
