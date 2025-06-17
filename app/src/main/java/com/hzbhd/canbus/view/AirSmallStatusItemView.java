package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class AirSmallStatusItemView extends RelativeLayout {
    private String mAction;
    private ImageView mIv;
    private int mOffResId;
    private OnAirInfoChangeListener mOnAirInfoChangeListener;
    private int mOnResId;

    public AirSmallStatusItemView(Context context, String str, int i, int i2, OnAirInfoChangeListener onAirInfoChangeListener) {
        super(context);
        initViews(context, str, i, i2, onAirInfoChangeListener);
    }

    public AirSmallStatusItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void initViews(Context context, String str, int i, int i2, OnAirInfoChangeListener onAirInfoChangeListener) {
        ImageView imageView = LayoutInflater.from(context).inflate(R.layout.view_air_small_line_status_item, this).findViewById(R.id.iv_item);
        this.mIv = imageView;
        this.mAction = str;
        this.mOffResId = i;
        this.mOnResId = i2;
        this.mOnAirInfoChangeListener = onAirInfoChangeListener;
        imageView.setImageResource(i);
    }

    public void turn() {
        OnAirInfoChangeListener onAirInfoChangeListener = this.mOnAirInfoChangeListener;
        if (onAirInfoChangeListener != null) {
            this.mIv.setImageResource(onAirInfoChangeListener.onAirInfoChange() ? this.mOnResId : this.mOffResId);
        }
    }

    public void setImageResource(int i) {
        this.mIv.setImageResource(i);
    }
}
