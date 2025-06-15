package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SmallBtnItemView extends LinearLayout {
    private boolean mIsOn;
    private OnItemClickListener mOnItemClickListener;
    private TextView mTv;

    public interface OnItemClickListener {
        void onClick();
    }

    public SmallBtnItemView(Context context, int i) {
        super(context);
        initViews(context, i);
    }

    public SmallBtnItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void initViews(Context context, int i) {
        TextView textView = LayoutInflater.from(context).inflate(R.layout.view_air_line_small_btn_item, this).findViewById(R.id.btn);
        this.mTv = textView;
        textView.setText(context.getString(i));
        this.mTv.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SmallBtnItemView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SmallBtnItemView.this.mOnItemClickListener != null) {
                    SmallBtnItemView.this.mOnItemClickListener.onClick();
                }
            }
        });
    }

    public boolean ismIsOn() {
        return this.mIsOn;
    }

    public void turn(boolean z) {
        if (z) {
            this.mTv.setTextColor(getResources().getColor(android.R.color.white, null));
            this.mTv.setBackgroundResource(R.drawable.ic_air_m2_wind_p);
        } else {
            this.mTv.setTextColor(getResources().getColor(android.R.color.black, null));
            this.mTv.setBackgroundResource(R.drawable.ic_air_m2_wind_n);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
