package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.activity.WmTireInfoActivity;

/* loaded from: classes2.dex */
public class Page4View extends LinearLayout {
    private Context context;
    private SelectionView selection1;
    private SelectionView selection2;
    private View view;

    public Page4View(Context context) {
        this(context, null);
    }

    public Page4View(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Page4View(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_view_page4, (ViewGroup) this, true);
        this.view = viewInflate;
        this.selection1 = (SelectionView) viewInflate.findViewById(R.id.tire_info);
        this.selection2 = (SelectionView) this.view.findViewById(R.id.return_setting);
        this.selection1.setValue("GONE");
        this.selection2.setValue("GONE");
        initAction();
    }

    private void initAction() {
        this.selection1.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page4View.1
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) {
                Page4View.this.context.startActivity(new Intent(Page4View.this.context, (Class<?>) WmTireInfoActivity.class));
            }
        });
        this.selection2.getAction(new ActionDo() { // from class: com.hzbhd.canbus.car_cus._446.view.Page4View.2
            @Override // com.hzbhd.canbus.car_cus._446.Interface.ActionDo
            public void toDo(Object obj) {
            }
        });
    }
}
