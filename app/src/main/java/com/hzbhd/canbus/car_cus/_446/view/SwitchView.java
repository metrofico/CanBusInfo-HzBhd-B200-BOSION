package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;

/* loaded from: classes2.dex */
public class SwitchView extends LinearLayout {
    private LinearLayout all_action_lin;
    private TextView big_title;
    private TextView small_title;
    private ImageView switch_img;
    private View view;

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_view_switch, (ViewGroup) this, true);
        this.view = viewInflate;
        this.big_title = (TextView) viewInflate.findViewById(R.id.big_title);
        this.small_title = (TextView) this.view.findViewById(R.id.small_title);
        this.switch_img = (ImageView) this.view.findViewById(R.id.switch_img);
        this.all_action_lin = (LinearLayout) this.view.findViewById(R.id.all_action_lin);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MD_446_title_attr);
        this.big_title.setText(typedArrayObtainStyledAttributes.getString(0));
        if (typedArrayObtainStyledAttributes.getString(1).equals("GONE")) {
            this.small_title.setVisibility(8);
        } else {
            this.small_title.setText(typedArrayObtainStyledAttributes.getString(1));
        }
        select(false);
    }

    public void getAction(final ActionDo actionDo) {
        this.all_action_lin.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.view.SwitchView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionDo.toDo(null);
            }
        });
    }

    public void select(boolean z) {
        if (z) {
            this.switch_img.setBackgroundResource(R.drawable.__446_shezhi1_ic_kai);
        } else {
            this.switch_img.setBackgroundResource(R.drawable.__446_shezhi1_ic_guan);
        }
    }
}
