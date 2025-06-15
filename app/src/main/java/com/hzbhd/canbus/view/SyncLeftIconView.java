package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SyncLeftIconView extends LinearLayout {

    public interface OnLeftIconClickListener {
        void onIconClick(String str);
    }

    public SyncLeftIconView(Context context) {
        super(context);
    }

    public SyncLeftIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initButton(Context context, String[] strArr, final OnLeftIconClickListener onLeftIconClickListener) throws Resources.NotFoundException {
        ImageButton imageButton;
        for (final String str : strArr) {
            imageButton = new ImageButton(context);
            str.hashCode();
            switch (str) {
                case "media":
                    imageButton.setImageResource(R.drawable.ford_sync_left_media);
                    break;
                case "phone":
                    imageButton.setImageResource(R.drawable.ford_sync_left_phone);
                    break;
                case "voice":
                    imageButton.setImageResource(R.drawable.ford_sync_left_voice);
                    break;
                case "keyboard":
                    imageButton.setImageResource(R.drawable.ford_sync_left_keyboard);
                    break;
            }
            imageButton.setBackgroundResource(R.drawable.selector_sync_btn);
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SyncLeftIconView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (onLeftIconClickListener != null) {
                        onLeftIconClickListener.onIconClick(str);
                    }
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.dp167), -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.dp10);
            layoutParams.topMargin = dimensionPixelOffset;
            layoutParams.bottomMargin = dimensionPixelOffset;
            imageButton.setLayoutParams(layoutParams);
            addView(imageButton);
        }
    }
}
