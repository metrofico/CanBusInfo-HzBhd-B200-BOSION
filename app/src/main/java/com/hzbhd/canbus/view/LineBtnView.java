package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.view.BtnItemView;

/* loaded from: classes2.dex */
public class LineBtnView extends LinearLayout {

    public LineBtnView(Context context) {
        super(context);
    }

    public LineBtnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initButton(Context context, String[] strArr, boolean z, String[] strArr2, final OnAirBtnClickListener onAirBtnClickListener) {
        if (strArr == null) {
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            boolean canClick = z && !isInDisableBtnArray(strArr2, strArr[i]);

            BtnItemView btnItemView = new BtnItemView(context, strArr[i], canClick);
            addView(btnItemView, new LinearLayout.LayoutParams(0, -1, 1.0f));

            final int index = i;  // Capturar el índice actual
            btnItemView.setOnItemClickListener(new BtnItemView.OnItemClickListener() {
                @Override
                public void onClick() {
                    if (onAirBtnClickListener != null) {
                        onAirBtnClickListener.onClickItem(index);
                    }
                }
            });
        }
    }

    private boolean isInDisableBtnArray(String[] strArr, String str) {
        if (strArr == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public BtnItemView getBtnItemView(int i) {
        return (BtnItemView) getChildAt(i);
    }
}
