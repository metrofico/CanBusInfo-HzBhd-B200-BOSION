package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.view.OriginalBottomBtnItemView;

/* loaded from: classes2.dex */
public class RowBottomBtnView extends LinearLayout {
    public RowBottomBtnView(Context context) {
        super(context);
    }

    public RowBottomBtnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initButton(Context context, String[] strArr, final OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener) {
        if (strArr == null) {
            return;
        }
        removeAllViews();
        for (final int i = 0; i < strArr.length; i++) {
            OriginalBottomBtnItemView originalBottomBtnItemView = new OriginalBottomBtnItemView(context, strArr[i]);
            addView(originalBottomBtnItemView, new LinearLayout.LayoutParams(0, -1, 1.0f));
            originalBottomBtnItemView.setOnItemClickListener(new OriginalBottomBtnItemView.OnItemClickListener() { // from class: com.hzbhd.canbus.view.RowBottomBtnView.1
                @Override // com.hzbhd.canbus.view.OriginalBottomBtnItemView.OnItemClickListener
                public void onClick() {
                    OnOriginalBottomBtnClickListener onOriginalBottomBtnClickListener2 = onOriginalBottomBtnClickListener;
                    if (onOriginalBottomBtnClickListener2 != null) {
                        onOriginalBottomBtnClickListener2.onClickBottomBtnItem(i);
                    }
                }
            });
        }
    }

    public OriginalBottomBtnItemView getBtnItemView(int i) {
        return (OriginalBottomBtnItemView) getChildAt(i);
    }
}
