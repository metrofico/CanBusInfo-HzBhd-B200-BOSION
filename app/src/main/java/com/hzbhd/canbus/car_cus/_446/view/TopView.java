package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class TopView extends LinearLayout {
    private ImageView icon;
    private TextView name;
    private ImageView power;
    private View view;

    public TopView(Context context) {
        this(context, null);
    }

    public TopView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_top_view, (ViewGroup) this, true);
        this.view = viewInflate;
        this.icon = (ImageView) viewInflate.findViewById(R.id.icon);
        this.power = (ImageView) this.view.findViewById(R.id.power);
        this.name = (TextView) this.view.findViewById(R.id.name);
    }

    public void turnOn(int i, String str, int i2) {
        this.icon.setBackgroundResource(i);
        this.power.setBackgroundResource(i2);
        this.name.setText(str);
        this.name.setTextColor(Color.parseColor("#f8f8ff"));
    }

    public void turnOff(int i, String str, int i2) {
        this.icon.setBackgroundResource(i);
        this.power.setBackgroundResource(i2);
        this.name.setText(str);
        this.name.setTextColor(Color.parseColor("#808080"));
    }
}
