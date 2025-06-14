package com.hzbhd.canbus.car_cus._467.air.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.comm.ScreenConfig;

/* loaded from: classes2.dex */
public class CheckItemView extends LinearLayout {
    private ImageView check_bos;
    private TextView item_name;
    private boolean selectTag;
    private View view;

    public CheckItemView(Context context) {
        this(context, null);
    }

    public CheckItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectTag = false;
        if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
            this.view = LayoutInflater.from(context).inflate(R.layout._467_check_item_view1024x600, (ViewGroup) this, true);
        } else {
            this.view = LayoutInflater.from(context).inflate(R.layout._467_check_item_view1280x720, (ViewGroup) this, true);
        }
        this.item_name = (TextView) this.view.findViewById(R.id.item_name);
        this.check_bos = (ImageView) this.view.findViewById(R.id.check_bos);
    }

    public void setTitle(String str) {
        this.item_name.setText(str);
    }

    public void check(boolean z) {
        if (z) {
            this.selectTag = true;
            this.check_bos.setBackgroundResource(R.drawable._439_select);
        } else {
            this.selectTag = false;
            this.check_bos.setBackgroundResource(R.drawable._439_unselect);
        }
    }

    public boolean isCheck() {
        return this.selectTag;
    }

    public void setTextColor(int i) {
        this.item_name.setTextColor(i);
    }
}
