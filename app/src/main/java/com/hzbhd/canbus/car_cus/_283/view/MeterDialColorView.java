package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MeterDialColorView extends RelativeLayout {
    private MeterColorAdapter colorAdapter;
    private int[] colors2;
    private RecyclerView mRecyclerView;

    public MeterDialColorView(Context context) {
        this(context, null);
    }

    public MeterDialColorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MeterDialColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.colors2 = new int[]{R.drawable._a6_meter_light_1, R.drawable._a6_meter_light_2, R.drawable._a6_meter_light_3, R.drawable._a6_meter_light_4, R.drawable._a6_meter_light_5, R.drawable._a6_meter_light_6, R.drawable._a6_meter_light_7, R.drawable._a6_meter_light_8, R.drawable._a6_meter_light_9, R.drawable._a6_meter_light_10, R.drawable._a6_meter_light_11, R.drawable._a6_meter_light_12, R.drawable._a6_meter_light_13, R.drawable._a6_meter_light_14, R.drawable._a6_meter_light_15, R.drawable._a6_meter_light_16, R.drawable._a6_meter_light_17, R.drawable._a6_meter_light_18, R.drawable._a6_meter_light_19, R.drawable._a6_meter_light_20, R.drawable._a6_meter_light_21, R.drawable._a6_meter_light_22, R.drawable._a6_meter_light_23, R.drawable._a6_meter_light_24, R.drawable._a6_meter_light_25, R.drawable._a6_meter_light_26, R.drawable._a6_meter_light_27, R.drawable._a6_meter_light_28, R.drawable._a6_meter_light_29, R.drawable._a6_meter_light_30};
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 6));
        MeterColorAdapter meterColorAdapter = new MeterColorAdapter(getContext(), this.colors2);
        this.colorAdapter = meterColorAdapter;
        this.mRecyclerView.setAdapter(meterColorAdapter);
        this.colorAdapter.setOnViewClickListener(new MeterColorAdapter.OnViewClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterDialColorView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter.OnViewClickListener
            public final void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) (i + 3 + 1)});
            }
        });
    }

    public void setSelectedItem(int i) {
        MeterColorAdapter meterColorAdapter = this.colorAdapter;
        if (meterColorAdapter != null) {
            meterColorAdapter.setSelectedItem(i);
        }
    }
}
