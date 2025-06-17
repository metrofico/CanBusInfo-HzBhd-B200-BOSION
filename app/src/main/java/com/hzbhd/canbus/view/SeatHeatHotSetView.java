package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;

/* loaded from: classes2.dex */
public class SeatHeatHotSetView extends RelativeLayout {
    private ImageView mIvClickItem;
    private OnAirSeatHeatColdMinPlusClickListener mOnMinAddClickListener;
    private TextView mTvDisplayItem;

    public SeatHeatHotSetView(Context context) {
        super(context);
    }

    public SeatHeatHotSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_set_seat_heat_cold, this);
        this.mIvClickItem = (ImageView) viewInflate.findViewById(R.id.iv_click_item);
        this.mTvDisplayItem = (TextView) viewInflate.findViewById(R.id.tv_display_item);
        this.mIvClickItem.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.SeatHeatHotSetView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SeatHeatHotSetView.this.mOnMinAddClickListener != null) {
                    SeatHeatHotSetView.this.mOnMinAddClickListener.onClickPlus();
                }
            }
        });
    }

    public SeatHeatHotSetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnUpDownClickListener(OnAirSeatHeatColdMinPlusClickListener onAirSeatHeatColdMinPlusClickListener) {
        this.mOnMinAddClickListener = onAirSeatHeatColdMinPlusClickListener;
    }

    public void setValue(String str) {
        this.mTvDisplayItem.setText(str);
    }

    public void setControllable(boolean z) {
        this.mIvClickItem.setEnabled(z);
    }
}
