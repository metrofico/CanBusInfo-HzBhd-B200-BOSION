package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;

/* loaded from: classes2.dex */
public class CarSettingUpKeepView extends RelativeLayout {
    private Context mContext;
    private View mView;
    private TextView tv_Imei;
    private TextView tv_checkCar;
    private TextView tv_checkOil;
    private int[] units;

    public CarSettingUpKeepView(Context context) {
        this(context, null);
    }

    public CarSettingUpKeepView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingUpKeepView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.units = new int[]{R.string._283_unit_2, R.string._283_unit_3};
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_upkeep, (ViewGroup) this, true);
        initView();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUpKeepView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1120x7d055937();
            }
        }, 1000L);
    }

    private void initView() {
        this.tv_Imei = (TextView) this.mView.findViewById(R.id.tv_Imei);
        this.tv_checkCar = (TextView) this.mView.findViewById(R.id.tv_checkCar);
        this.tv_checkOil = (TextView) this.mView.findViewById(R.id.tv_checkOil);
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1120x7d055937() {
        String str;
        String str2;
        String str3;
        String string = this.mContext.getString(this.units[GeneralDzData.upkeep_unit]);
        String str4 = "-";
        if (GeneralDzData.upkeep_car_day_mark == 0) {
            str = "-";
        } else if (GeneralDzData.upkeep_car_day_mark == 1) {
            str = GeneralDzData.upkeep_car_day + this.mContext.getString(R.string._283_day);
        } else {
            str = GeneralDzData.upkeep_car_day_mark == 2 ? this.mContext.getString(R.string._283_past) + GeneralDzData.upkeep_car_day + this.mContext.getString(R.string._283_day) : "";
        }
        if (GeneralDzData.upkeep_car_distance_mark == 0) {
            str2 = "-";
        } else if (GeneralDzData.upkeep_car_distance_mark == 1) {
            str2 = this.mContext.getString(R.string._283_or) + GeneralDzData.upkeep_car_distance + string;
        } else {
            str2 = GeneralDzData.upkeep_car_distance_mark == 2 ? this.mContext.getString(R.string._283_or) + this.mContext.getString(R.string._283_past) + GeneralDzData.upkeep_car_distance + string : "";
        }
        if (GeneralDzData.upkeep_oil_day_mark == 0) {
            str3 = "-";
        } else if (GeneralDzData.upkeep_oil_day_mark == 1) {
            str3 = GeneralDzData.upkeep_oil_day + this.mContext.getString(R.string._283_day);
        } else {
            str3 = GeneralDzData.upkeep_oil_day_mark == 2 ? this.mContext.getString(R.string._283_past) + GeneralDzData.upkeep_oil_day + this.mContext.getString(R.string._283_day) : "";
        }
        if (GeneralDzData.upkeep_oil_distance_mark != 0) {
            if (GeneralDzData.upkeep_oil_distance_mark == 1) {
                str4 = this.mContext.getString(R.string._283_or) + GeneralDzData.upkeep_oil_distance + string;
            } else {
                str4 = GeneralDzData.upkeep_oil_distance_mark == 2 ? this.mContext.getString(R.string._283_or) + this.mContext.getString(R.string._283_past) + GeneralDzData.upkeep_oil_distance + string : "";
            }
        }
        this.tv_Imei.setText(GeneralDzData.car_imei);
        this.tv_checkCar.setText(str + str2);
        this.tv_checkOil.setText(str3 + str4);
    }
}
