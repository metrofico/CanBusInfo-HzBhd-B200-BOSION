package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SetWindSpeedView extends RelativeLayout {
    private ImageView mAutoIv;
    private ImageButton mDownIb;
    private List<ImageView> mImageViewList;
    private int mTotalWindLevel;
    private ImageButton mUpIb;
    private float mWeight;
    private LinearLayout mWindSpeedLl;

    public SetWindSpeedView(Context context) {
        super(context);
        this.mWeight = 0.4f;
        this.mTotalWindLevel = 0;
    }

    public SetWindSpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWeight = 0.4f;
        this.mTotalWindLevel = 0;
    }

    public void initViews(Context context, boolean z, int i, final OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_air_set_speed_view, this);
        this.mWindSpeedLl = (LinearLayout) viewInflate.findViewById(R.id.ll_layout);
        this.mUpIb = (ImageButton) viewInflate.findViewById(R.id.ib_up);
        this.mDownIb = (ImageButton) viewInflate.findViewById(R.id.ib_down);
        this.mAutoIv = (ImageView) viewInflate.findViewById(R.id.iv_auto);
        this.mUpIb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SetWindSpeedView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener2 = onAirWindSpeedUpDownClickListener;
                if (onAirWindSpeedUpDownClickListener2 != null) {
                    onAirWindSpeedUpDownClickListener2.onClickRight();
                }
            }
        });
        this.mDownIb.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SetWindSpeedView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener2 = onAirWindSpeedUpDownClickListener;
                if (onAirWindSpeedUpDownClickListener2 != null) {
                    onAirWindSpeedUpDownClickListener2.onClickLeft();
                }
            }
        });
        if (z) {
            this.mUpIb.setVisibility(0);
            this.mDownIb.setVisibility(0);
        } else {
            this.mUpIb.setVisibility(4);
            this.mDownIb.setVisibility(4);
        }
        if (context.getResources().getConfiguration().orientation == 1) {
            this.mWeight = 0.5f;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, (int) (i * this.mWeight));
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        setTotalWindLevel(context, i);
    }

    private void setTotalWindLevel(Context context, int i) {
        this.mImageViewList = new ArrayList();
        this.mTotalWindLevel = i;
        for (int i2 = 0; i2 < this.mTotalWindLevel; i2++) {
            ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.view_wind_speed_item, (ViewGroup) null).findViewById(R.id.iv_item);
            ((ViewGroup) imageView.getParent()).removeAllViews();
            this.mWindSpeedLl.addView(imageView);
            this.mImageViewList.add(imageView);
        }
    }

    public void setAuto(boolean z) {
        if (z) {
            this.mWindSpeedLl.setVisibility(4);
            this.mAutoIv.setVisibility(0);
        } else {
            this.mWindSpeedLl.setVisibility(0);
            this.mAutoIv.setVisibility(4);
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
