package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.data.WmCarData;

/* loaded from: classes2.dex */
public class CarLogoView extends RelativeLayout {
    private Context context;
    private ImageView logoIcon;
    private TextView name;
    private View view;

    public CarLogoView(Context context) {
        this(context, null);
    }

    public CarLogoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarLogoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446__view_car_logo, (ViewGroup) this, true);
        this.view = viewInflate;
        this.logoIcon = (ImageView) viewInflate.findViewById(R.id.icon);
        TextView textView = (TextView) this.view.findViewById(R.id.name);
        this.name = textView;
        textView.setText(context.getString(R.string._446_wm_car_13));
        updateLogo();
    }

    public void getAction(final ActionDo actionDo) {
        this.logoIcon.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.view.CarLogoView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WmCarData.logo) {
                    actionDo.toDo("TURN_OFF");
                } else {
                    actionDo.toDo("TURN_ON");
                }
            }
        });
    }

    public void updateLogo() {
        if (WmCarData.logo) {
            this.logoIcon.setBackgroundResource(R.drawable.__446_logo_on_icon);
        } else {
            this.logoIcon.setBackgroundResource(R.drawable._446_logo_off_icon);
        }
    }
}
