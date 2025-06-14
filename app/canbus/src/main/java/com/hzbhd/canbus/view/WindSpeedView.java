package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WindSpeedView extends LinearLayout {
    private List<ImageView> mImageViewList;
    private int mTotalWindLevel;

    public WindSpeedView(Context context) {
        super(context);
        this.mTotalWindLevel = 0;
        setOrientation(0);
        setGravity(17);
    }

    public WindSpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTotalWindLevel = 0;
    }

    public void setTotalWindLevel(Context context, int i) {
        this.mImageViewList = new ArrayList();
        this.mTotalWindLevel = i;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, this.mTotalWindLevel);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        for (int i2 = 0; i2 < this.mTotalWindLevel; i2++) {
            ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.view_wind_speed_item, (ViewGroup) null).findViewById(R.id.iv_item);
            ((ViewGroup) imageView.getParent()).removeAllViews();
            addView(imageView);
            this.mImageViewList.add(imageView);
        }
    }

    public void setCurWindSpeed(int i) {
        for (int i2 = 0; i2 < this.mImageViewList.size(); i2++) {
            if (i2 < i) {
                this.mImageViewList.get(i2).setImageResource(R.drawable.img_air_f_wind_f);
            } else {
                this.mImageViewList.get(i2).setImageResource(R.drawable.img_air_f_wind_n);
            }
        }
    }
}
