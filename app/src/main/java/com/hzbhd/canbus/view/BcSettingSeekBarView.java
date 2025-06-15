package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class BcSettingSeekBarView extends RelativeLayout {
    private SeekBar mSeekBar;
    private TextView mTitleTv;
    private TextView mValueTv;

    public BcSettingSeekBarView(Context context) {
        super(context);
    }

    public BcSettingSeekBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_back_camera_seek_bar, this);
        this.mSeekBar = viewInflate.findViewById(R.id.seekbar);
        this.mTitleTv = viewInflate.findViewById(R.id.tv_title);
        this.mValueTv = viewInflate.findViewById(R.id.tv_value);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BcSettingSeekBarStyle);
        String string = typedArrayObtainStyledAttributes.getString(0);
        typedArrayObtainStyledAttributes.recycle();
        this.mTitleTv.setText(string);
    }

    public BcSettingSeekBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    public SeekBar getSeekbar() {
        return this.mSeekBar;
    }

    public int getProgress() {
        return this.mSeekBar.getProgress();
    }

    public void setMax(int i) {
        this.mSeekBar.setMax(i);
    }

    public void setMin(int i) {
        this.mSeekBar.setMax(i);
    }

    public void setValue(String str) {
        this.mValueTv.setText(str);
    }

    public void setValue(int i) {
        this.mValueTv.setText(String.valueOf(i));
    }

    public void setProgress(int i) {
        this.mSeekBar.setProgress(i);
        this.mValueTv.setText(String.valueOf(i));
    }
}
