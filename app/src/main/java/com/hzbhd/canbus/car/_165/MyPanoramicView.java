package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes.dex */
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
        if (this.mOnBtnClickListener == null) {
        }
        switch (view.getId()) {
            case R.id.ib_end_bottom /* 2131362388 */:
                this.mOnBtnClickListener.onEndBottomClick();
                break;
            case R.id.ib_end_top /* 2131362389 */:
                this.mOnBtnClickListener.onEndTopClick();
                break;
            case R.id.ib_mid_bottom /* 2131362407 */:
                this.mOnBtnClickListener.onMidBottomClick();
                break;
            case R.id.ib_start_bottom /* 2131362422 */:
                this.mOnBtnClickListener.onStartBottomClick();
                break;
            case R.id.ib_start_top /* 2131362423 */:
                this.mOnBtnClickListener.onStartTopClick();
                break;
        }
    }
}
