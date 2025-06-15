package com.hzbhd.canbus.car._310;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hzbhd.R;


public class MyPanoramicView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "_310_MyPanoramicView";
    private OnBtnClickListener mOnBtnClickListener;

    interface OnBtnClickListener {
        void onEndBottomClick();

        void onEndTopClick();

        void onStartBottomClick();

        void onStartTopClick();
    }

    public MyPanoramicView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_panoramice_165_view, this);
        initView();
    }

    private void initView() {
        findViewById(R.id.ib_start_top).setOnClickListener(this);
        findViewById(R.id.ib_start_bottom).setOnClickListener(this);
        findViewById(R.id.ib_end_top).setOnClickListener(this);
        findViewById(R.id.ib_end_bottom).setOnClickListener(this);
    }

    public void setmOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.mOnBtnClickListener = onBtnClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mOnBtnClickListener == null) {
        }
        if (view.getId() == R.id.ib_end_bottom) { // 2131362388
            this.mOnBtnClickListener.onEndBottomClick();
        } else if (view.getId() == R.id.ib_end_top) { // 2131362389
            this.mOnBtnClickListener.onEndTopClick();
        } else if (view.getId() == R.id.ib_start_bottom) { // 2131362422
            this.mOnBtnClickListener.onStartBottomClick();
        } else if (view.getId() == R.id.ib_start_top) { // 2131362423
            this.mOnBtnClickListener.onStartTopClick();
        }
    }
}
