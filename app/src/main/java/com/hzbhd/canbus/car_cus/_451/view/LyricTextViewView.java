package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class LyricTextViewView extends LinearLayout {
    private TextView txt;
    private View view;

    public LyricTextViewView(Context context) {
        this(context, null);
    }

    public LyricTextViewView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LyricTextViewView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_lyric_text, (ViewGroup) this, true);
        this.view = viewInflate;
        this.txt = (TextView) viewInflate.findViewById(R.id.txt);
    }

    public void setTxt(String str) {
        this.txt.setText(str);
    }
}
