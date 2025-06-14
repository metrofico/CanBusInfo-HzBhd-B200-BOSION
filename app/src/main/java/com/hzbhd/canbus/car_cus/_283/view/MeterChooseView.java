package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MeterChooseView extends ConstraintLayout implements View.OnClickListener {
    private static final int idCounts = 10;
    private List<TextView> lists;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void itemClick(View view, int i);
    }

    public MeterChooseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lists = new ArrayList();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i = 0;
        while (i < 10) {
            int i2 = i + 1;
            this.lists.add((TextView) findViewById(getContext().getResources().getIdentifier("meterChooseTv" + i2, "id", getContext().getPackageName())));
            this.lists.get(i).setOnClickListener(this);
            i = i2;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.itemClick(view, this.lists.indexOf(view));
        }
    }

    public void setSelectItem(int i) {
        for (int i2 = 0; i2 < this.lists.size(); i2++) {
            this.lists.get(i2).setSelected(false);
        }
        this.lists.get(i).setSelected(true);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
