package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.util.CommUtil;

/* loaded from: classes2.dex */
public class TempSetView extends RelativeLayout {
    private ImageView mIvDown;
    private ImageView mIvUp;
    private OnAirTempTouchListener mOnAirTempTouchListener;
    private OnAirTemperatureUpDownClickListener mOnUpDownClickListener;
    private MarqueeTextView mTvValue;

    public TempSetView(Context context) {
        super(context);
    }

    public TempSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_air_temp_set, this);
        this.mIvUp = (ImageView) viewInflate.findViewById(R.id.iv_up);
        this.mIvDown = (ImageView) viewInflate.findViewById(R.id.iv_down);
        this.mTvValue = (MarqueeTextView) viewInflate.findViewById(R.id.tv_value);
        this.mIvUp.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.TempSetView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TempSetView.this.mOnUpDownClickListener != null) {
                    TempSetView.this.mOnUpDownClickListener.onClickUp();
                }
            }
        });
        this.mIvDown.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.TempSetView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TempSetView.this.mOnUpDownClickListener != null) {
                    TempSetView.this.mOnUpDownClickListener.onClickDown();
                }
            }
        });
        this.mIvUp.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.view.TempSetView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (TempSetView.this.mOnAirTempTouchListener == null) {
                    return false;
                }
                TempSetView.this.mOnAirTempTouchListener.onTouchUp(motionEvent);
                return false;
            }
        });
        this.mIvDown.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.view.TempSetView.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (TempSetView.this.mOnAirTempTouchListener == null) {
                    return false;
                }
                TempSetView.this.mOnAirTempTouchListener.onTouchDown(motionEvent);
                return false;
            }
        });
    }

    public TempSetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnUpDownClickListener(OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListener) {
        this.mOnUpDownClickListener = onAirTemperatureUpDownClickListener;
    }

    public void setOnUpDownTouchListener(OnAirTempTouchListener onAirTempTouchListener) {
        this.mOnAirTempTouchListener = onAirTempTouchListener;
    }

    public void setValue(String str) {
        this.mTvValue.setText(CommUtil.temperatureUnitSwitch(str));
    }

    public void setControllable(boolean z) {
        if (z) {
            this.mIvUp.setVisibility(0);
            this.mIvDown.setVisibility(0);
        } else {
            this.mIvUp.setVisibility(8);
            this.mIvDown.setVisibility(8);
        }
    }
}
