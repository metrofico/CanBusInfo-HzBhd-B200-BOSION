package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class BasicInfoView extends LinearLayout {
    public TextView asl_txt;
    public TextView band_txt;
    public TextView band_value_txt;
    public TextView bt_txt;
    public DiscView disc1;
    public DiscView disc2;
    public DiscView disc3;
    public DiscView disc4;
    public DiscView disc5;
    public DiscView disc6;
    public DiscView disc_in;
    public TextView phone_txt;
    public TextView preset_txt;
    public TextView rand_txt;
    public TextView rpt_txt;
    public TextView scan_txt;
    public TextView signal_txt;
    public TextView st_txt;
    private View view;

    public BasicInfoView(Context context) {
        this(context, null);
    }

    public BasicInfoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BasicInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_basic_info, (ViewGroup) this, true);
        this.view = viewInflate;
        this.disc_in = (DiscView) viewInflate.findViewById(R.id.disc_in);
        this.disc1 = (DiscView) this.view.findViewById(R.id.disc1);
        this.disc2 = (DiscView) this.view.findViewById(R.id.disc2);
        this.disc3 = (DiscView) this.view.findViewById(R.id.disc3);
        this.disc4 = (DiscView) this.view.findViewById(R.id.disc4);
        this.disc5 = (DiscView) this.view.findViewById(R.id.disc5);
        this.disc6 = (DiscView) this.view.findViewById(R.id.disc6);
        this.disc_in.setText("DISC IN");
        this.disc1.setText("DISC 1");
        this.disc2.setText("DISC 2");
        this.disc3.setText("DISC 3");
        this.disc4.setText("DISC 4");
        this.disc5.setText("DISC 5");
        this.disc6.setText("DISC 6");
        this.band_txt = (TextView) this.view.findViewById(R.id.band_txt);
        this.asl_txt = (TextView) this.view.findViewById(R.id.asl_txt);
        this.bt_txt = (TextView) this.view.findViewById(R.id.bt_txt);
        this.phone_txt = (TextView) this.view.findViewById(R.id.phone_txt);
        this.scan_txt = (TextView) this.view.findViewById(R.id.scan_txt);
        this.rpt_txt = (TextView) this.view.findViewById(R.id.rpt_txt);
        this.rand_txt = (TextView) this.view.findViewById(R.id.rand_txt);
        this.signal_txt = (TextView) this.view.findViewById(R.id.signal_txt);
        this.band_value_txt = (TextView) this.view.findViewById(R.id.band_value_txt);
        this.st_txt = (TextView) this.view.findViewById(R.id.st_txt);
        this.preset_txt = (TextView) this.view.findViewById(R.id.preset_txt);
    }
}
