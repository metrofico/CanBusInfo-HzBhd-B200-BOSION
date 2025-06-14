package com.hzbhd.canbus.car_cus._439.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class WindModeView extends LinearLayout {
    private ImageView body_wind;
    private ImageView foot_wind;
    private ImageView head_wind;
    private View view;

    public WindModeView(Context context) {
        this(context, null);
    }

    public WindModeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WindModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._439_wind_mode_view, (ViewGroup) this, true);
        this.view = viewInflate;
        this.head_wind = (ImageView) viewInflate.findViewById(R.id.head_wind);
        this.body_wind = (ImageView) this.view.findViewById(R.id.body_wind);
        this.foot_wind = (ImageView) this.view.findViewById(R.id.foot_wind);
    }

    public void setWindowWind(boolean z) {
        if (z) {
            this.head_wind.setBackgroundResource(R.drawable._439_wind_window_on);
        } else {
            this.head_wind.setBackgroundResource(R.drawable._439_wind_window_off);
        }
    }

    public void setBodyWind(boolean z) {
        if (z) {
            this.body_wind.setBackgroundResource(R.drawable._439_wind_body_on);
        } else {
            this.body_wind.setBackgroundResource(R.drawable._439_wind_body_off);
        }
    }

    public void setFootWind(boolean z) {
        if (z) {
            this.foot_wind.setBackgroundResource(R.drawable._439_wind_foot_on);
        } else {
            this.foot_wind.setBackgroundResource(R.drawable._439_wind_foot_off);
        }
    }
}
