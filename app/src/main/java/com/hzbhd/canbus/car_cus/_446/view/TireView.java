package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;
import java.text.DecimalFormat;

/* loaded from: classes2.dex */
public class TireView extends RelativeLayout implements CanInfoObserver {
    private DecimalFormat df_2Decimal;
    private TextView front_left_temp;
    private TextView front_left_tire;
    private TextView front_right_temp;
    private TextView front_right_tire;
    private TextView rear_left_temp;
    private TextView rear_left_tire;
    private TextView rear_right_temp;
    private TextView rear_right_tire;
    private View view;

    public TireView(Context context) {
        this(context, null);
    }

    public TireView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TireView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.df_2Decimal = new DecimalFormat("###0.00");
        this.view = LayoutInflater.from(context).inflate(R.layout.__446_view_tire_info, (ViewGroup) this, true);
        this.front_left_tire = (TextView) findViewById(R.id.front_left_tire);
        this.front_right_tire = (TextView) findViewById(R.id.front_right_tire);
        this.rear_left_tire = (TextView) findViewById(R.id.rear_left_tire);
        this.rear_right_tire = (TextView) findViewById(R.id.rear_right_tire);
        this.front_left_temp = (TextView) findViewById(R.id.front_left_temp);
        this.front_right_temp = (TextView) findViewById(R.id.front_right_temp);
        this.rear_left_temp = (TextView) findViewById(R.id.rear_left_temp);
        this.rear_right_temp = (TextView) findViewById(R.id.rear_right_temp);
        updateUi();
    }

    public void updateUi() {
        if (WmCarData.tire_unit.equals("KPA")) {
            this.front_left_tire.setText(WmCarData.tire_front_left + "KPA");
            this.front_right_tire.setText(WmCarData.tire_front_right + "KPA");
            this.rear_left_tire.setText(WmCarData.tire_rear_left + "KPA");
            this.rear_right_tire.setText(WmCarData.tire_rear_right + "KPA");
        } else if (WmCarData.tire_unit.equals("PSI")) {
            this.front_left_tire.setText(this.df_2Decimal.format(WmCarData.tire_front_left / 6.894999980926514d) + "PSI");
            this.front_right_tire.setText(this.df_2Decimal.format(WmCarData.tire_front_right / 6.894999980926514d) + "PSI");
            this.rear_left_tire.setText(this.df_2Decimal.format(WmCarData.tire_rear_left / 6.894999980926514d) + "PSI");
            this.rear_right_tire.setText(this.df_2Decimal.format(WmCarData.tire_rear_right / 6.894999980926514d) + "PSI");
        }
        this.front_left_temp.setText(WmCarData.tire_temp_front_left);
        this.front_right_temp.setText(WmCarData.tire_temp_front_right);
        this.rear_left_temp.setText(WmCarData.tire_temp_rear_left);
        this.rear_right_temp.setText(WmCarData.tire_temp_rear_right);
    }

    @Override // com.hzbhd.canbus.car_cus._446.Interface.CanInfoObserver
    public void dataChange() {
        updateUi();
    }
}
