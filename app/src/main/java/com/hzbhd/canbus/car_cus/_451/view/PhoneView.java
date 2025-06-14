package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class PhoneView extends LinearLayout {
    private View view;

    public PhoneView(Context context) {
        this(context, null);
    }

    public PhoneView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhoneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.view = LayoutInflater.from(context).inflate(R.layout._451_view_phone, (ViewGroup) this, true);
    }
}
