package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class CarIdView extends LinearLayout {
    private static String KEY_446_CAR_NUMBER = "KEY_446_CAR_NUMBER";
    private TextView name;
    private TextView ok;
    private LinearLayout set_lin;
    private View view;

    public CarIdView(Context context) {
        this(context, null);
    }

    public CarIdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarIdView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.view = LayoutInflater.from(context).inflate(R.layout.__446_view_car_id, (ViewGroup) this, true);
        this.name = (TextView) findViewById(R.id.name);
        this.ok = (TextView) findViewById(R.id.ok);
        this.set_lin = (LinearLayout) this.view.findViewById(R.id.set_lin);
        this.name.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.view.CarIdView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarIdView.this.set_lin.setVisibility(0);
            }
        });
        this.ok.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.view.CarIdView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (((EditText) CarIdView.this.view.findViewById(R.id.content)).getText().toString().trim().length() != 0) {
                    SharePreUtil.setStringValue(context, CarIdView.KEY_446_CAR_NUMBER, ((EditText) CarIdView.this.view.findViewById(R.id.content)).getText().toString().trim());
                    CarIdView.this.set_lin.setVisibility(8);
                    CarIdView.this.name.setText(((EditText) CarIdView.this.view.findViewById(R.id.content)).getText().toString().trim());
                } else {
                    Context context2 = context;
                    Toast.makeText(context2, context2.getString(R.string._446_wm_car_11), 0).show();
                }
            }
        });
        String stringValue = SharePreUtil.getStringValue(context, KEY_446_CAR_NUMBER, "FFFF");
        if (stringValue.equals("FFFF")) {
            this.name.setText(context.getString(R.string._446_wm_car_11));
        } else {
            this.name.setText(stringValue);
        }
    }
}
