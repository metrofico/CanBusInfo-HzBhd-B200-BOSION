package com.hzbhd.canbus.car._0;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzbhd.canbus.R;

public class CheckItemView2 extends LinearLayout {
    private ImageView check_bos;
    private TextView item_name;
    private boolean selectTag;
    private View view;

    public CheckItemView2(Context context) {
        this(context, null);
    }

    public CheckItemView2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckItemView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectTag = false;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._439_check_item_view2, (ViewGroup) this, true);
        this.view = viewInflate;
        this.item_name = (TextView) viewInflate.findViewById(R.id.item_name);
        this.check_bos = (ImageView) this.view.findViewById(R.id.check_bos);
    }

    public void setTitle(String str) {
        this.item_name.setText(str);
    }

    public void check(boolean z) {
        if (z) {
            this.selectTag = true;
            this.check_bos.setBackgroundResource(R.drawable._439_new_select);
        } else {
            this.selectTag = false;
            this.check_bos.setBackgroundResource(R.drawable._439_new_unselect);
        }
    }

    public boolean isCheck() {
        return this.selectTag;
    }

    public void setTextColor(int i) {
        this.item_name.setTextColor(i);
    }
}