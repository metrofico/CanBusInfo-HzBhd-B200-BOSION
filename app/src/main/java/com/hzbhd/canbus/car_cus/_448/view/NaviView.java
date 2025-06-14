package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import java.util.List;

/* loaded from: classes2.dex */
public class NaviView extends LinearLayout {
    private MyButton item1;
    private MyButton item2;
    private MyButton item3;
    private MyButton item4;
    private MyButton item5;
    private MyButton item6;
    private MyButton item7;
    private View view;

    public NaviView(Context context) {
        this(context, null);
    }

    public NaviView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NaviView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_navi_view, (ViewGroup) this, true);
        this.view = viewInflate;
        this.item1 = (MyButton) viewInflate.findViewById(R.id.item1_btn);
        this.item2 = (MyButton) this.view.findViewById(R.id.item2_btn);
        this.item3 = (MyButton) this.view.findViewById(R.id.item3_btn);
        this.item4 = (MyButton) this.view.findViewById(R.id.item4_btn);
        this.item5 = (MyButton) this.view.findViewById(R.id.item5_btn);
        this.item6 = (MyButton) this.view.findViewById(R.id.item6_btn);
        this.item7 = (MyButton) this.view.findViewById(R.id.item7_btn);
    }

    public void getAction(final ActionCallback actionCallback) {
        this.item1.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.1
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(1);
            }
        });
        this.item2.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.2
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(2);
            }
        });
        this.item3.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.3
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(3);
            }
        });
        this.item4.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.4
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(4);
            }
        });
        this.item5.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.5
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(5);
            }
        });
        this.item6.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.6
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(6);
            }
        });
        this.item7.getEventUpAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._448.view.NaviView.7
            @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
            public void toDo(Object obj) {
                actionCallback.toDo(7);
            }
        });
    }

    public void initItem(List<String> list) {
        if (list.size() == 1) {
            this.item1.setText(list.get(0).trim());
            this.item2.setVisibility(8);
            this.item3.setVisibility(8);
            this.item4.setVisibility(8);
            this.item5.setVisibility(8);
            this.item6.setVisibility(8);
            this.item7.setVisibility(8);
        }
        if (list.size() == 2) {
            this.item1.setText(list.get(0).trim());
            this.item2.setText(list.get(1).trim());
            this.item3.setVisibility(8);
            this.item4.setVisibility(8);
            this.item5.setVisibility(8);
            this.item6.setVisibility(8);
            this.item7.setVisibility(8);
        }
        if (list.size() == 3) {
            this.item1.setText(list.get(0).trim());
            this.item2.setText(list.get(1).trim());
            this.item3.setText(list.get(2).trim());
            this.item4.setVisibility(8);
            this.item5.setVisibility(8);
            this.item6.setVisibility(8);
            this.item7.setVisibility(8);
        }
        if (list.size() == 4) {
            this.item1.setText(list.get(0).trim());
            this.item2.setText(list.get(1).trim());
            this.item3.setText(list.get(2).trim());
            this.item4.setText(list.get(3).trim());
            this.item5.setVisibility(8);
            this.item6.setVisibility(8);
            this.item7.setVisibility(8);
        }
        if (list.size() == 5) {
            this.item1.setText(list.get(0).trim());
            this.item2.setText(list.get(1).trim());
            this.item3.setText(list.get(2).trim());
            this.item4.setText(list.get(3).trim());
            this.item5.setText(list.get(4).trim());
            this.item6.setVisibility(8);
            this.item7.setVisibility(8);
        }
        if (list.size() == 6) {
            this.item1.setText(list.get(0).trim());
            this.item2.setText(list.get(1).trim());
            this.item3.setText(list.get(2).trim());
            this.item4.setText(list.get(3).trim());
            this.item5.setText(list.get(4).trim());
            this.item6.setText(list.get(5).trim());
            this.item7.setVisibility(8);
        }
    }

    public void setItem2Gone(boolean z) {
        if (z) {
            this.item2.setVisibility(8);
        } else {
            this.item2.setVisibility(0);
        }
    }

    public void select(int i) {
        if (i == 1) {
            this.item1.setBackgroundResource(R.drawable.__448_eq1_left_p);
            this.item2.setBackgroundResource(R.drawable.__448_null);
            this.item3.setBackgroundResource(R.drawable.__448_null);
            this.item4.setBackgroundResource(R.drawable.__448_null);
            this.item5.setBackgroundResource(R.drawable.__448_null);
            this.item6.setBackgroundResource(R.drawable.__448_null);
            this.item7.setBackgroundResource(R.drawable.__448_null);
        }
        if (i == 2) {
            this.item1.setBackgroundResource(R.drawable.__448_null);
            this.item2.setBackgroundResource(R.drawable.__448_eq1_left_p);
            this.item3.setBackgroundResource(R.drawable.__448_null);
            this.item4.setBackgroundResource(R.drawable.__448_null);
            this.item5.setBackgroundResource(R.drawable.__448_null);
            this.item6.setBackgroundResource(R.drawable.__448_null);
            this.item7.setBackgroundResource(R.drawable.__448_null);
        }
        if (i == 3) {
            this.item1.setBackgroundResource(R.drawable.__448_null);
            this.item2.setBackgroundResource(R.drawable.__448_null);
            this.item3.setBackgroundResource(R.drawable.__448_eq1_left_p);
            this.item4.setBackgroundResource(R.drawable.__448_null);
            this.item5.setBackgroundResource(R.drawable.__448_null);
            this.item6.setBackgroundResource(R.drawable.__448_null);
            this.item7.setBackgroundResource(R.drawable.__448_null);
        }
        if (i == 4) {
            this.item1.setBackgroundResource(R.drawable.__448_null);
            this.item2.setBackgroundResource(R.drawable.__448_null);
            this.item3.setBackgroundResource(R.drawable.__448_null);
            this.item4.setBackgroundResource(R.drawable.__448_eq1_left_p);
            this.item5.setBackgroundResource(R.drawable.__448_null);
            this.item6.setBackgroundResource(R.drawable.__448_null);
            this.item7.setBackgroundResource(R.drawable.__448_null);
        }
        if (i == 5) {
            this.item1.setBackgroundResource(R.drawable.__448_null);
            this.item2.setBackgroundResource(R.drawable.__448_null);
            this.item3.setBackgroundResource(R.drawable.__448_null);
            this.item4.setBackgroundResource(R.drawable.__448_null);
            this.item5.setBackgroundResource(R.drawable.__448_eq1_left_p);
            this.item6.setBackgroundResource(R.drawable.__448_null);
            this.item7.setBackgroundResource(R.drawable.__448_null);
        }
        if (i == 6) {
            this.item1.setBackgroundResource(R.drawable.__448_null);
            this.item2.setBackgroundResource(R.drawable.__448_null);
            this.item3.setBackgroundResource(R.drawable.__448_null);
            this.item4.setBackgroundResource(R.drawable.__448_null);
            this.item5.setBackgroundResource(R.drawable.__448_null);
            this.item6.setBackgroundResource(R.drawable.__448_eq1_left_p);
            this.item7.setBackgroundResource(R.drawable.__448_null);
        }
        if (i == 7) {
            this.item1.setBackgroundResource(R.drawable.__448_null);
            this.item2.setBackgroundResource(R.drawable.__448_null);
            this.item3.setBackgroundResource(R.drawable.__448_null);
            this.item4.setBackgroundResource(R.drawable.__448_null);
            this.item5.setBackgroundResource(R.drawable.__448_null);
            this.item6.setBackgroundResource(R.drawable.__448_null);
            this.item7.setBackgroundResource(R.drawable.__448_eq1_left_p);
        }
    }
}
