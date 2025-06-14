package com.hzbhd.canbus.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class EqSeekBarView extends RelativeLayout {
    private TextView mChannelTv;
    private TextView mChannelValueTv;
    private ImageView mIvMin;
    private ImageView mIvPlus;
    private OnMinPlusClickListener mOnMinAddClickListener;
    private SeekBar mSeekBar;

    public interface OnMinPlusClickListener {
        void onClickMin(SeekBar seekBar);

        void onClickPlus(SeekBar seekBar);
    }

    public EqSeekBarView(Context context) {
        super(context);
    }

    public EqSeekBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_eq_seekbar, this);
        this.mIvMin = (ImageView) viewInflate.findViewById(R.id.iv_reduce);
        this.mIvPlus = (ImageView) viewInflate.findViewById(R.id.iv_add);
        this.mSeekBar = (SeekBar) viewInflate.findViewById(R.id.seekbar);
        this.mChannelTv = (TextView) viewInflate.findViewById(R.id.tv_channel);
        this.mChannelValueTv = (TextView) viewInflate.findViewById(R.id.tv_channel_value);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.EqSeekBarStyle);
        String string = typedArrayObtainStyledAttributes.getString(0);
        typedArrayObtainStyledAttributes.recycle();
        this.mChannelTv.setText(string);
        this.mIvMin.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.EqSeekBarView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EqSeekBarView.this.mOnMinAddClickListener != null) {
                    EqSeekBarView.this.mOnMinAddClickListener.onClickMin(EqSeekBarView.this.mSeekBar);
                }
            }
        });
        this.mIvPlus.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.EqSeekBarView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (EqSeekBarView.this.mOnMinAddClickListener != null) {
                    EqSeekBarView.this.mOnMinAddClickListener.onClickPlus(EqSeekBarView.this.mSeekBar);
                }
            }
        });
    }

    public EqSeekBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    public void setOnPlusMinClickListener(OnMinPlusClickListener onMinPlusClickListener) {
        this.mOnMinAddClickListener = onMinPlusClickListener;
    }

    public void setValue(String str) {
        this.mChannelValueTv.setText(str);
    }

    public void setProgress(int i) {
        this.mSeekBar.setProgress(i);
    }

    public void setCanMinPlus(boolean z) {
        if (z) {
            this.mIvMin.setVisibility(0);
            this.mIvPlus.setVisibility(0);
        } else {
            this.mIvMin.setVisibility(8);
            this.mIvPlus.setVisibility(8);
        }
    }

    public void setTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mChannelTv.setText(str);
    }

    public SeekBar getSeekBar() {
        return this.mSeekBar;
    }

    public void setSeekBarTouchable(boolean z) {
        if (z) {
            return;
        }
        this.mSeekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.view.EqSeekBarView.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mSeekBar.setEnabled(z);
    }
}
