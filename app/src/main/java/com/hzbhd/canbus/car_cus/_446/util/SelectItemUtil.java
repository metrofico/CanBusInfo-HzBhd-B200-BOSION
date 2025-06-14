package com.hzbhd.canbus.car_cus._446.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import java.util.List;

/* loaded from: classes2.dex */
public class SelectItemUtil {
    private boolean addTag = false;
    private TextView item1;
    private TextView item2;
    private TextView item3;
    private TextView item4;
    private TextView item5;
    private WindowManager.LayoutParams layoutParams;
    private Context mContext;
    private WindowManager mWindowManager;
    private LinearLayout out_view;
    private View view;
    private View view2;
    private View view3;
    private View view4;
    private View view5;

    public SelectItemUtil(Context context) {
        initWindow(context);
    }

    private void initWindow(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.type = 2038;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.width = -1;
        this.layoutParams.height = -1;
        initView();
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.__446_view_select_item, (ViewGroup) null);
        this.view = viewInflate;
        this.item1 = (TextView) viewInflate.findViewById(R.id.item1);
        this.item2 = (TextView) this.view.findViewById(R.id.item2);
        this.item3 = (TextView) this.view.findViewById(R.id.item3);
        this.item4 = (TextView) this.view.findViewById(R.id.item4);
        this.item5 = (TextView) this.view.findViewById(R.id.item5);
        this.view2 = this.view.findViewById(R.id.view2);
        this.view3 = this.view.findViewById(R.id.view3);
        this.view4 = this.view.findViewById(R.id.view4);
        this.view5 = this.view.findViewById(R.id.view5);
        LinearLayout linearLayout = (LinearLayout) this.view.findViewById(R.id.out_view);
        this.out_view = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.util.SelectItemUtil.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SelectItemUtil.this.hide();
            }
        });
    }

    public void show(List<String> list, final ActionDo actionDo) {
        if (list.size() == 3) {
            this.item4.setVisibility(8);
            this.item5.setVisibility(8);
            this.view4.setVisibility(8);
            this.view5.setVisibility(8);
            this.item1.setText(list.get(0));
            this.item2.setText(list.get(1));
            this.item3.setText(list.get(2));
        } else if (list.size() == 2) {
            this.item3.setVisibility(8);
            this.item4.setVisibility(8);
            this.item5.setVisibility(8);
            this.view3.setVisibility(8);
            this.view4.setVisibility(8);
            this.view5.setVisibility(8);
            this.item1.setText(list.get(0));
            this.item2.setText(list.get(1));
        } else if (list.size() == 5) {
            this.item1.setText(list.get(0));
            this.item2.setText(list.get(1));
            this.item3.setText(list.get(2));
            this.item4.setText(list.get(3));
            this.item5.setText(list.get(4));
        }
        if (!this.addTag) {
            this.mWindowManager.addView(this.view, this.layoutParams);
            this.addTag = true;
        }
        this.item1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.util.SelectItemUtil.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionDo.toDo(0);
                SelectItemUtil.this.hide();
            }
        });
        this.item2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.util.SelectItemUtil.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionDo.toDo(1);
                SelectItemUtil.this.hide();
            }
        });
        this.item3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.util.SelectItemUtil.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionDo.toDo(2);
                SelectItemUtil.this.hide();
            }
        });
        this.item4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.util.SelectItemUtil.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionDo.toDo(3);
                SelectItemUtil.this.hide();
            }
        });
        this.item5.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.util.SelectItemUtil.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionDo.toDo(4);
                SelectItemUtil.this.hide();
            }
        });
    }

    public void hide() {
        if (this.addTag) {
            this.mWindowManager.removeView(this.view);
            this.addTag = false;
        }
    }
}
