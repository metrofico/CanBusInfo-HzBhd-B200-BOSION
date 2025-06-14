package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class VehicleInfoView extends LinearLayout {
    private View mView;

    private void findViews() {
    }

    private void initViews() {
    }

    public VehicleInfoView(Context context) {
        super(context);
        this.mView = LayoutInflater.from(context).inflate(R.layout._304_view_vehicle_info, this);
        findViews();
        initViews();
    }
}
