package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class VolumeView extends LinearLayout {
    private ImageView mute_img;
    private SeekBar my_seek;
    private View view;

    public VolumeView(Context context) {
        this(context, null);
    }

    public VolumeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VolumeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_volume, (ViewGroup) this, true);
        this.view = viewInflate;
        SeekBar seekBar = (SeekBar) viewInflate.findViewById(R.id.my_seek);
        this.my_seek = seekBar;
        seekBar.setEnabled(false);
        this.mute_img = (ImageView) this.view.findViewById(R.id.mute_img);
        setMax(100);
        setProgress(45);
    }

    public void setMax(int i) {
        this.my_seek.setMax(i);
    }

    public void setProgress(int i) {
        this.my_seek.setProgress(i);
    }

    public void setMute(boolean z) {
        this.mute_img.setBackgroundResource(z ? R.drawable._451_mute_on : R.drawable._451_mute_off);
    }
}
