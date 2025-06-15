package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.view.SmallBtnItemView;

/* loaded from: classes2.dex */
public class LineSmallBtnView extends LinearLayout {
    public LineSmallBtnView(Context context) {
        super(context);
    }

    public LineSmallBtnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initButton(Context context, int[] iArr, int i, final OnAirBtnClickListener onAirBtnClickListener) {
        if (iArr == null) {
            return;
        }
        setGravity(i);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            SmallBtnItemView smallBtnItemView = new SmallBtnItemView(context, iArr[i2]);
            addView(smallBtnItemView, new LayoutParams(-2, -1));
            int finalI = i2;
            smallBtnItemView.setOnItemClickListener(new SmallBtnItemView.OnItemClickListener() { // from class: com.hzbhd.canbus.view.LineSmallBtnView.1
                @Override // com.hzbhd.canbus.view.SmallBtnItemView.OnItemClickListener
                public void onClick() {
                    if (onAirBtnClickListener != null) {
                        onAirBtnClickListener.onClickItem(finalI);
                    }
                }
            });
        }
    }
}
