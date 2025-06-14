package com.hzbhd.canbus.car_cus._299.smart;

import android.view.ViewGroup;
import android.widget.RadioButton;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class SmartPowerActivity extends com.hzbhd.canbus.activity.SmartPowerActivity {
    @Override // com.hzbhd.canbus.activity.SmartPowerActivity
    protected void setActivityContentView() {
        setContentView(R.layout._299_activity_smart_power);
    }

    @Override // com.hzbhd.canbus.activity.SmartPowerActivity
    protected void scaleRadioButton(RadioButton radioButton) {
        for (int i = 0; i < this.radioButtons.size(); i++) {
            if (radioButton == this.radioButtons.get(i)) {
                radioButton.setChecked(true);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) radioButton.getLayoutParams();
                marginLayoutParams.width = (int) getResources().getDimension(R.dimen.dp267);
                marginLayoutParams.height = (int) getResources().getDimension(R.dimen.dp294);
                radioButton.setLayoutParams(marginLayoutParams);
                radioButton.setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.dp45));
                radioButton.setTextSize(getResources().getDimension(R.dimen.dp46));
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.radioButtons.get(i).getLayoutParams();
                marginLayoutParams2.width = (int) getResources().getDimension(R.dimen._299_icon_width);
                marginLayoutParams2.height = (int) getResources().getDimension(R.dimen._299_icon_height);
                this.radioButtons.get(i).setLayoutParams(marginLayoutParams2);
                this.radioButtons.get(i).setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen._299_text_bottom_padding));
                this.radioButtons.get(i).setTextSize(getResources().getDimension(R.dimen.dp25));
            }
        }
    }
}
