package com.hzbhd.canbus.car_cus._446.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._446.Interface.ActionDo;
import com.hzbhd.canbus.car_cus._446.util.SelectItemUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class SelectionView extends LinearLayout {
    private LinearLayout all_action_lin;
    private TextView big_title;
    private Context context;
    private List<String> itemList;
    private TextView now_mode;
    private TextView small_title;
    private View view;

    public SelectionView(Context context) {
        this(context, null);
    }

    public SelectionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SelectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__446_view_selection, (ViewGroup) this, true);
        this.view = viewInflate;
        this.now_mode = (TextView) viewInflate.findViewById(R.id.now_mode);
        this.all_action_lin = (LinearLayout) this.view.findViewById(R.id.all_action_lin);
        this.big_title = (TextView) this.view.findViewById(R.id.big_title);
        this.small_title = (TextView) this.view.findViewById(R.id.small_title);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MD_446_title_attr);
        this.big_title.setText(typedArrayObtainStyledAttributes.getString(0));
        if (typedArrayObtainStyledAttributes.getString(1).equals("GONE")) {
            this.small_title.setVisibility(8);
        } else {
            this.small_title.setText(typedArrayObtainStyledAttributes.getString(1));
        }
    }

    public void setValue(String str) {
        if (str.equals("GONE")) {
            this.now_mode.setVisibility(8);
        } else {
            this.now_mode.setText(str);
        }
    }

    public void getAction(final ActionDo actionDo) {
        this.all_action_lin.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._446.view.SelectionView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SelectionView.this.itemList != null) {
                    new SelectItemUtil(SelectionView.this.context).show(SelectionView.this.itemList, actionDo);
                } else {
                    actionDo.toDo("ON_TITLE_CLICK");
                }
            }
        });
    }

    public void initItem(List<String> list) {
        this.itemList = list;
    }
}
