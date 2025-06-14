package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._291.GeneralDzPQData;

/* loaded from: classes2.dex */
public class DataView extends RelativeLayout {
    private View mView;
    private TextView tv_distance;
    private TextView tv_fuel;
    private TextView tv_light;
    private TextView tv_oil;
    private TextView tv_rev;
    private TextView tv_v;

    public DataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mView = LayoutInflater.from(context).inflate(R.layout._291_data_view, this);
        initView();
    }

    private void initView() {
        this.tv_rev = (TextView) this.mView.findViewById(R.id.tv_rev);
        this.tv_fuel = (TextView) this.mView.findViewById(R.id.tv_fuel);
        this.tv_distance = (TextView) this.mView.findViewById(R.id.tv_distance);
        this.tv_oil = (TextView) this.mView.findViewById(R.id.tv_oil);
        this.tv_v = (TextView) this.mView.findViewById(R.id.tv_v);
        this.tv_light = (TextView) this.mView.findViewById(R.id.tv_light);
    }

    public void refreshUi() {
        this.tv_rev.setText(GeneralDzPQData.vehicleRev);
        this.tv_fuel.setText(GeneralDzPQData.vehicleFuel);
        this.tv_distance.setText(GeneralDzPQData.vehicleDistance);
        this.tv_oil.setText(GeneralDzPQData.vehicleOil);
        this.tv_v.setText(GeneralDzPQData.vehicleV);
        this.tv_light.setText(GeneralDzPQData.vehicleLight);
    }
}
