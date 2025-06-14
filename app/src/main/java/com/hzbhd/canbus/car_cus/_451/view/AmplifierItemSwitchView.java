package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class AmplifierItemSwitchView extends LinearLayout {
    private TextView name;
    private TextView off_txt;
    private TextView on_txt;
    private View view;

    public AmplifierItemSwitchView(Context context) {
        this(context, null);
    }

    public AmplifierItemSwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AmplifierItemSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_amplifier_item_switch, (ViewGroup) this, true);
        this.view = viewInflate;
        this.name = (TextView) viewInflate.findViewById(R.id.name);
        this.off_txt = (TextView) this.view.findViewById(R.id.off_txt);
        this.on_txt = (TextView) this.view.findViewById(R.id.on_txt);
    }

    public void setTitle(String str) {
        this.name.setText(str);
    }

    public void setData(boolean z) {
        if (z) {
            this.off_txt.setBackgroundResource(R.color.transport);
            this.on_txt.setBackgroundResource(R.color.orange);
        } else {
            this.off_txt.setBackgroundResource(R.color.orange);
            this.on_txt.setBackgroundResource(R.color.transport);
        }
    }
}
