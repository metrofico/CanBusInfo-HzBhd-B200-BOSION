package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SettingProgressView extends RelativeLayout implements View.OnClickListener {
    private SeekBar bigSeekBar;
    private RelativeLayout bigSeekbarLayout;
    private float interval;
    private String mBigValue;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private String mSmallValue;
    private View mView;
    private int maxProgress;
    private int minProgress;
    private RelativeLayout relativeLayout;
    private SeekBar smallSeekbar;
    private TextView tv_title;
    private TextView tv_value;
    private String unit;

    public interface OnItemClickListener {
        void onProgressChanged(View view, int i);
    }

    public SettingProgressView(Context context) {
        this(context, null);
    }

    public SettingProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxProgress = 1;
        this.minProgress = 0;
        this.interval = 1.0f;
        this.unit = "";
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_setting_progress_view, (ViewGroup) this, true);
        this.mBigValue = context.getString(R.string._283_bigvalue);
        this.mSmallValue = context.getString(R.string._283_smallvalue);
        initView();
        initData();
        String string = context.obtainStyledAttributes(attributeSet, R.styleable.SettingSelectStyle).getString(0);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        this.tv_title.setText(string);
    }

    private void initView() {
        this.relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.relativeLayout);
        this.bigSeekbarLayout = (RelativeLayout) this.mView.findViewById(R.id.bigSeekbarLayout);
        this.tv_title = (TextView) this.mView.findViewById(R.id.tv_title);
        this.tv_value = (TextView) this.mView.findViewById(R.id.tv_value);
        this.smallSeekbar = (SeekBar) this.mView.findViewById(R.id.smallSeekbar);
        this.bigSeekBar = (SeekBar) this.mView.findViewById(R.id.bigSeekBar);
        this.smallSeekbar.setEnabled(false);
        this.relativeLayout.setOnClickListener(this);
    }

    private void initData() {
        this.bigSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.view.SettingProgressView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (SettingProgressView.this.mOnItemClickListener != null && z) {
                    OnItemClickListener onItemClickListener = SettingProgressView.this.mOnItemClickListener;
                    SettingProgressView settingProgressView = SettingProgressView.this;
                    onItemClickListener.onProgressChanged(settingProgressView, settingProgressView.minProgress + i);
                }
                SettingProgressView.this.setValue(i);
            }
        });
    }

    public void setAll(int i, float f, String str) {
        this.maxProgress = i;
        this.interval = f;
        this.unit = str;
        this.smallSeekbar.setMax(i);
        this.bigSeekBar.setMax(i);
    }

    public void setMaxProgress(int i) {
        this.maxProgress = i;
        this.smallSeekbar.setMax(i);
        this.bigSeekBar.setMax(i);
    }

    public void setMaxAndMinProgress(int i, int i2) {
        int i3 = i2 - i;
        this.minProgress = i;
        this.maxProgress = i3;
        this.smallSeekbar.setMax(i3);
        this.bigSeekBar.setMax(i3);
    }

    public void setInterval(float f) {
        this.interval = f;
    }

    public float getInterval() {
        return this.interval;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public void setValue(int i) {
        if (i == 0 && !TextUtils.isEmpty(this.mSmallValue)) {
            this.tv_value.setText(this.mSmallValue);
        } else if (i == this.maxProgress && !TextUtils.isEmpty(this.mBigValue)) {
            this.tv_value.setText(this.mBigValue);
        } else {
            this.tv_value.setText((this.minProgress + (i * this.interval)) + this.unit);
        }
    }

    public void setValueProgress(int i) {
        int i2 = ((int) (i / this.interval)) - this.minProgress;
        setValue(i2);
        this.bigSeekBar.setProgress(i2);
        this.smallSeekbar.setProgress(i2);
    }

    public void setProgressNoInterval(int i) {
        setValue(i);
        this.bigSeekBar.setProgress(i);
        this.smallSeekbar.setProgress(i);
    }

    public void setBigAndSmallValueText(String str, String str2) {
        this.mBigValue = str2;
        this.mSmallValue = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.relativeLayout) {
            return;
        }
        if (this.bigSeekbarLayout.getVisibility() == 0) {
            this.bigSeekbarLayout.setVisibility(8);
            this.tv_value.setVisibility(8);
            this.smallSeekbar.setVisibility(0);
            this.smallSeekbar.setProgress(this.bigSeekBar.getProgress());
            return;
        }
        this.bigSeekbarLayout.setVisibility(0);
        this.tv_value.setVisibility(0);
        this.smallSeekbar.setVisibility(8);
    }

    public void setMinProgress(int i) {
        this.minProgress = i;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
