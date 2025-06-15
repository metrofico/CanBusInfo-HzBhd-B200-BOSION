package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hzbhd.canbus.view.SyncBtnItemView;

/* loaded from: classes2.dex */
public class SyncSoftKeyView extends LinearLayout {

    public interface OnSoftKeyClickListener {
        void onSoftKeyClick(int i);
    }

    public SyncSoftKeyView(Context context) {
        super(context);
    }

    public SyncSoftKeyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initButton(Context context, int i, final OnSoftKeyClickListener onSoftKeyClickListener) {
        for (int i2 = 0; i2 < i; i2++) {
            SyncBtnItemView syncBtnItemView = new SyncBtnItemView(context);
            addView(syncBtnItemView, new LinearLayout.LayoutParams(0, -1, 1.0f));
            syncBtnItemView.setOnClickListener(new SyncBtnItemView.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncSoftKeyView$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.view.SyncBtnItemView.OnClickListener
                public final void onclick() {
                    if (onSoftKeyClickListener != null) {
                        onSoftKeyClickListener.onSoftKeyClick(i);
                    }
                }
            });
        }
    }

    public SyncBtnItemView getItem(int i) {
        return (SyncBtnItemView) getChildAt(i);
    }
}
