package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class VehicleEnergyView extends RelativeLayout {
    private View mView;

    private void findViews() {
    }

    private void initViews() {
    }

    public VehicleEnergyView(Context context) {
        super(context);
        this.mView = LayoutInflater.from(context).inflate(R.layout._304_view_vehicle_energy, this);
        findViews();
        initViews();
    }
}
