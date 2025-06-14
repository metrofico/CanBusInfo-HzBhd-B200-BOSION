package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class TipsTextView extends RelativeLayout {
    private TextView tv_content;
    private TextView tv_title;

    public TipsTextView(Context context) {
        this(context, null);
    }

    public TipsTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TipsTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_tips_textview, (ViewGroup) this, true);
        this.tv_title = (TextView) viewInflate.findViewById(R.id.tv_title);
        this.tv_content = (TextView) viewInflate.findViewById(R.id.tv_content);
        String string = context.obtainStyledAttributes(attributeSet, R.styleable.SettingSelectStyle, 0, 0).getString(0);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.tv_title.setText(string);
    }

    public void setContent(String str) {
        this.tv_content.setText(str);
    }
}
