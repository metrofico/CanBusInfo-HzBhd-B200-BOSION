package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class VideoStateView extends LinearLayout {
    private ImageView state_icon;
    private TextView state_txt;
    private TextView time_txt;
    private View view;

    public VideoStateView(Context context) {
        this(context, null);
    }

    public VideoStateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoStateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_video_state, (ViewGroup) this, true);
        this.view = viewInflate;
        this.state_icon = (ImageView) viewInflate.findViewById(R.id.state_icon);
        this.state_txt = (TextView) this.view.findViewById(R.id.state_txt);
        this.time_txt = (TextView) this.view.findViewById(R.id.time_txt);
    }

    public void setIcon(int i) {
        this.state_icon.setBackgroundResource(i);
    }

    public void setState(String str) {
        this.state_txt.setText(str);
    }

    public void setTime(String str) {
        this.time_txt.setText(str);
    }
}
