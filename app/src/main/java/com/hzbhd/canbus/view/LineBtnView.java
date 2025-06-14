package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.view.BtnItemView;

/* loaded from: classes2.dex */
public class LineBtnView extends LinearLayout {
    private boolean mCanClick;

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
        for (final int i = 0; i < strArr.length; i++) {
            if (z) {
                if (isInDisableBtnArray(strArr2, strArr[i])) {
                    this.mCanClick = false;
                } else {
                    this.mCanClick = true;
                }
            }
            BtnItemView btnItemView = new BtnItemView(context, strArr[i], this.mCanClick);
            addView(btnItemView, new LinearLayout.LayoutParams(0, -1, 1.0f));
            btnItemView.setOnItemClickListener(new BtnItemView.OnItemClickListener() { // from class: com.hzbhd.canbus.view.LineBtnView.1
                @Override // com.hzbhd.canbus.view.BtnItemView.OnItemClickListener
                public void onClick() {
                    OnAirBtnClickListener onAirBtnClickListener2 = onAirBtnClickListener;
                    if (onAirBtnClickListener2 != null) {
                        onAirBtnClickListener2.onClickItem(i);
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
