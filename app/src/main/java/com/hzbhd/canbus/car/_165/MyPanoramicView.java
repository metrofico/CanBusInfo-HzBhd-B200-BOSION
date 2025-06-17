package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.R;


public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "_165_MyPanoramicView";
    private ImageButton mIbEndBottom;
    private ImageButton mIbEndTop;
    private ImageButton mIbMidBottom;
    private ImageButton mIbStartBottom;
    private ImageButton mIbStartTop;
    private OnBtnClickListener mOnBtnClickListener;

    interface OnBtnClickListener {
        void onEndBottomClick();

        void onEndTopClick();

        void onMidBottomClick();

        void onStartBottomClick();

        void onStartTopClick();
    }

    public MyPanoramicView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_panoramice_165_view, this);
        findView();
        initView();
    }

    private void findView() {
        this.mIbStartTop = (ImageButton) findViewById(R.id.ib_start_top);
        this.mIbStartBottom = (ImageButton) findViewById(R.id.ib_start_bottom);
        this.mIbMidBottom = (ImageButton) findViewById(R.id.ib_mid_bottom);
        this.mIbEndTop = (ImageButton) findViewById(R.id.ib_end_top);
        this.mIbEndBottom = (ImageButton) findViewById(R.id.ib_end_bottom);
    }

    private void initView() {
        this.mIbStartTop.setOnClickListener(this);
        this.mIbStartBottom.setOnClickListener(this);
        this.mIbMidBottom.setOnClickListener(this);
        this.mIbEndTop.setOnClickListener(this);
        this.mIbEndBottom.setOnClickListener(this);
    }

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.mOnBtnClickListener = onBtnClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ib_end_bottom) {
            this.mOnBtnClickListener.onEndBottomClick();
        } else if (id == R.id.ib_end_top) {
            this.mOnBtnClickListener.onEndTopClick();
        } else if (id == R.id.ib_mid_bottom) {
            this.mOnBtnClickListener.onMidBottomClick();
        } else if (id == R.id.ib_start_bottom) {
            this.mOnBtnClickListener.onStartBottomClick();
        } else if (id == R.id.ib_start_top) {
            this.mOnBtnClickListener.onStartTopClick();
        }
    }
}
