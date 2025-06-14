package com.hzbhd.canbus.view;

import android.content.Context;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.view.SyncKeyBoardItemView;
import com.hzbhd.canbus.view.SyncKeyBoardView;

/* loaded from: classes2.dex */
public class SyncKeyBoardRowView extends LinearLayout {
    public SyncKeyBoardRowView(Context context) {
        super(context);
    }

    public void initView(Context context, String[] strArr, final SyncKeyBoardView.OnKeyBoardBtnClickListener onKeyBoardBtnClickListener) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, context.getResources().getDimensionPixelOffset(R.dimen.dp74), 1.0f);
        for (final String str : strArr) {
            SyncKeyBoardItemView syncKeyBoardItemView = new SyncKeyBoardItemView(context);
            syncKeyBoardItemView.initView(str, new SyncKeyBoardItemView.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncKeyBoardRowView.1
                @Override // com.hzbhd.canbus.view.SyncKeyBoardItemView.OnClickListener
                public void onClick() {
                    SyncKeyBoardView.OnKeyBoardBtnClickListener onKeyBoardBtnClickListener2 = onKeyBoardBtnClickListener;
                    if (onKeyBoardBtnClickListener2 != null) {
                        onKeyBoardBtnClickListener2.onBtnClick(str);
                    }
                }

                @Override // com.hzbhd.canbus.view.SyncKeyBoardItemView.OnClickListener
                public void onLongClick() {
                    SyncKeyBoardView.OnKeyBoardBtnClickListener onKeyBoardBtnClickListener2 = onKeyBoardBtnClickListener;
                    if (onKeyBoardBtnClickListener2 != null) {
                        onKeyBoardBtnClickListener2.onBtnLongClick(str);
                    }
                }
            });
            syncKeyBoardItemView.setLayoutParams(layoutParams);
            addView(syncKeyBoardItemView);
        }
    }
}
