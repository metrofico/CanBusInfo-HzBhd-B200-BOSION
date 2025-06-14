package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* loaded from: classes2.dex */
public class SyncKeyBoardView extends LinearLayout {
    private OnKeyBoardBtnClickListener mOnKeyBoardBtnClickListener;

    public interface OnKeyBoardBtnClickListener {
        void onBtnClick(String str);

        void onBtnLongClick(String str);
    }

    public SyncKeyBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initKeyBoard(Context context, String[][] strArr, OnKeyBoardBtnClickListener onKeyBoardBtnClickListener) {
        this.mOnKeyBoardBtnClickListener = onKeyBoardBtnClickListener;
        for (String[] strArr2 : strArr) {
            SyncKeyBoardRowView syncKeyBoardRowView = new SyncKeyBoardRowView(context);
            syncKeyBoardRowView.initView(context, strArr2, onKeyBoardBtnClickListener);
            addView(syncKeyBoardRowView);
        }
    }

    public void rebuild(Context context, String[][] strArr) {
        initKeyBoard(context, strArr, this.mOnKeyBoardBtnClickListener);
    }
}
