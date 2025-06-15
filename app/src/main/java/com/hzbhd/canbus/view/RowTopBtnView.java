package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.view.OriginalTopBtnItemView;

/* loaded from: classes2.dex */
public class RowTopBtnView extends LinearLayout {
    public RowTopBtnView(Context context) {
        super(context);
    }

    public RowTopBtnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initButton(Context context, String[] strArr, boolean z, final OnOriginalTopBtnClickListener onOriginalTopBtnClickListener) {
        if (strArr == null) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            OriginalTopBtnItemView originalTopBtnItemView = new OriginalTopBtnItemView(context, strArr[i]);
            addView(originalTopBtnItemView, new LinearLayout.LayoutParams(0, -1, 1.0f));
            final int index = i;
            originalTopBtnItemView.setOnItemClickListener(new OriginalTopBtnItemView.OnItemClickListener() { // from class: com.hzbhd.canbus.view.RowTopBtnView.1


                @Override // com.hzbhd.canbus.view.OriginalTopBtnItemView.OnItemClickListener
                public void onClick() {
                    OnOriginalTopBtnClickListener onOriginalTopBtnClickListener2 = onOriginalTopBtnClickListener;
                    if (onOriginalTopBtnClickListener2 != null) {
                        onOriginalTopBtnClickListener2.onClickTopBtnItem(index);
                    }
                }
            });
        }
    }

    public OriginalTopBtnItemView getBtnItemView(int i) {
        return (OriginalTopBtnItemView) getChildAt(i);
    }

    public void clean() {
        removeAllViews();
    }
}
