package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.HandlerUtils;

/* loaded from: classes2.dex */
public class CarImageView extends ImageView {
    public static final String CARTYPE = "CarType";
    public static final String CARTYPE_Main = "car_type";
    HandlerUtils.IFreshUICallback mIFreshUICallback;

    public CarImageView(Context context) {
        this(context, null);
    }

    public CarImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIFreshUICallback = new HandlerUtils.IFreshUICallback() { // from class: com.hzbhd.canbus.car_cus._283.view.CarImageView.1
            @Override // com.hzbhd.canbus.car_cus._283.HandlerUtils.IFreshUICallback
            public void callback() {
                CarImageView.this.refreshUi();
            }
        };
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        refreshUi();
        HandlerUtils.getInstance().registerCallBack(this.mIFreshUICallback);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        HandlerUtils.getInstance().unregisterCallBack(this.mIFreshUICallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUi() {
        int i = getContext().getSharedPreferences(CARTYPE_Main, 0).getInt(CARTYPE, 0);
        if (i == 1) {
            setImageResource(R.drawable.dz_vehicle_information_car_2);
            return;
        }
        if (i == 2) {
            setImageResource(R.drawable.dz_vehicle_information_car_suv);
        } else if (i == 3) {
            setImageResource(R.drawable.dz_vehicle_information_car_mpv);
        } else {
            setImageResource(R.drawable.dz_vehicle_information_car_3);
        }
    }
}
