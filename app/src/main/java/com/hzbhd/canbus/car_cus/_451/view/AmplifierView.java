package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class AmplifierView extends LinearLayout {
    private AmplifierItemSwitchView asl_item;
    private AmplifierItemSevenView bal_item;
    private AmplifierItemView bas_item;
    private AmplifierItemSevenView fad_item;
    private AmplifierItemView mid_item;
    private AmplifierItemView tre_item;
    private View view;

    public AmplifierView(Context context) {
        this(context, null);
    }

    public AmplifierView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AmplifierView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._451_view_amplifier, (ViewGroup) this, true);
        this.view = viewInflate;
        this.bas_item = (AmplifierItemView) viewInflate.findViewById(R.id.bas_item);
        this.mid_item = (AmplifierItemView) this.view.findViewById(R.id.mid_item);
        this.tre_item = (AmplifierItemView) this.view.findViewById(R.id.tre_item);
        this.fad_item = (AmplifierItemSevenView) this.view.findViewById(R.id.fad_item);
        this.bal_item = (AmplifierItemSevenView) this.view.findViewById(R.id.bal_item);
        this.asl_item = (AmplifierItemSwitchView) this.view.findViewById(R.id.asl_item);
        this.bas_item.setTitle("BAS");
        this.mid_item.setTitle("MID");
        this.tre_item.setTitle("TRE");
        this.fad_item.setTitle("FAD");
        this.fad_item.setUnit("F", "R");
        this.bal_item.setTitle("BAL");
        this.bal_item.setUnit("L", "R");
        this.asl_item.setTitle("ASL");
        this.bas_item.setData(-5);
        this.mid_item.setData(-2);
        this.tre_item.setData(-3);
        this.fad_item.setData(-5);
        this.bal_item.setData(7);
        this.asl_item.setData(false);
    }

    public void setData(int i, int i2, int i3, int i4, int i5, boolean z) {
        this.bas_item.setData(i);
        this.mid_item.setData(i2);
        this.tre_item.setData(i3);
        this.fad_item.setData(i4);
        this.bal_item.setData(i5);
        this.asl_item.setData(z);
    }
}
