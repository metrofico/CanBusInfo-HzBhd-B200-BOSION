package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

/* loaded from: classes2.dex */
public class HybridDataView2 extends RelativeLayout {
    private View mView;
    private TipsTextView tipsTextView1;
    private TipsTextView tipsTextView2;
    private TipsTextView tipsTextView3;
    private TipsTextView tipsTextView4;
    private TipsTextView tipsTextView5;
    private TipsTextView tipsTextView6;

    public HybridDataView2(Context context) {
        this(context, null);
    }

    public HybridDataView2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HybridDataView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_hybrid_data_view_2, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.tipsTextView1 = (TipsTextView) this.mView.findViewById(R.id.tipsTextView1);
        this.tipsTextView2 = (TipsTextView) this.mView.findViewById(R.id.tipsTextView2);
        this.tipsTextView3 = (TipsTextView) this.mView.findViewById(R.id.tipsTextView3);
        this.tipsTextView4 = (TipsTextView) this.mView.findViewById(R.id.tipsTextView4);
        this.tipsTextView5 = (TipsTextView) this.mView.findViewById(R.id.tipsTextView5);
        this.tipsTextView6 = (TipsTextView) this.mView.findViewById(R.id.tipsTextView6);
    }

    public void refreshUi() {
        this.tipsTextView1.setContent(GeneralDzData.hybrid_electricity_data_1);
        this.tipsTextView2.setContent(GeneralDzData.hybrid_travelled_data_1);
        this.tipsTextView3.setContent(GeneralDzData.hybrid_electricity_data_2);
        this.tipsTextView4.setContent(GeneralDzData.hybrid_travelled_data_2);
        this.tipsTextView5.setContent(GeneralDzData.hybrid_electricity_data_3);
        this.tipsTextView6.setContent(GeneralDzData.hybrid_travelled_data_3);
    }
}
