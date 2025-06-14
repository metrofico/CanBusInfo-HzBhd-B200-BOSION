package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter;
import com.hzbhd.canbus.car_cus._283.view.MyRecyclerView;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MeterColorView extends RelativeLayout {
    private int[] colors1;
    private int[] colors1_byte;
    private int[] colors2;
    private MyRecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private View view1;
    private View view2;

    public MeterColorView(Context context) {
        this(context, null);
    }

    public MeterColorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MeterColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.colors1_byte = new int[]{ViewCompat.MEASURED_SIZE_MASK, 15400960, 18687, 7400960, 2162632, 52449, 16762368, 13789470, 11591910, 16668912, 8142782, 16753828, 14423100, 16748800, 13529087, 14929751, 65533, 11534144};
        this.colors1 = new int[]{R.drawable._a6_meter_pointer_1, R.drawable._a6_meter_pointer_2, R.drawable._a6_meter_pointer_3, R.drawable._a6_meter_pointer_4, R.drawable._a6_meter_pointer_5, R.drawable._a6_meter_pointer_6, R.drawable._a6_meter_pointer_7, R.drawable._a6_meter_pointer_8, R.drawable._a6_meter_pointer_9, R.drawable._a6_meter_pointer_10, R.drawable._a6_meter_pointer_11, R.drawable._a6_meter_pointer_12, R.drawable._a6_meter_pointer_13, R.drawable._a6_meter_pointer_14, R.drawable._a6_meter_pointer_15, R.drawable._a6_meter_pointer_16, R.drawable._a6_meter_pointer_17, R.drawable._a6_meter_pointer_18};
        this.colors2 = new int[]{R.drawable._a6_meter_light_1, R.drawable._a6_meter_light_2, R.drawable._a6_meter_light_3, R.drawable._a6_meter_light_4, R.drawable._a6_meter_light_5, R.drawable._a6_meter_light_6, R.drawable._a6_meter_light_7, R.drawable._a6_meter_light_8, R.drawable._a6_meter_light_9, R.drawable._a6_meter_light_10, R.drawable._a6_meter_light_11, R.drawable._a6_meter_light_12, R.drawable._a6_meter_light_13, R.drawable._a6_meter_light_14, R.drawable._a6_meter_light_15, R.drawable._a6_meter_light_16, R.drawable._a6_meter_light_17, R.drawable._a6_meter_light_18, R.drawable._a6_meter_light_19, R.drawable._a6_meter_light_20, R.drawable._a6_meter_light_21, R.drawable._a6_meter_light_22, R.drawable._a6_meter_light_23, R.drawable._a6_meter_light_24, R.drawable._a6_meter_light_25, R.drawable._a6_meter_light_26, R.drawable._a6_meter_light_27, R.drawable._a6_meter_light_28, R.drawable._a6_meter_light_29, R.drawable._a6_meter_light_30};
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRecyclerView1 = (MyRecyclerView) findViewById(R.id.recyclerView1);
        this.mRecyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        this.view1 = findViewById(R.id.view1);
        this.view2 = findViewById(R.id.view2);
        this.mRecyclerView1.setLayoutManager(new GridLayoutManager(getContext(), 6));
        MeterColorAdapter meterColorAdapter = new MeterColorAdapter(getContext(), this.colors1);
        this.mRecyclerView1.setAdapter(meterColorAdapter);
        meterColorAdapter.setOnViewClickListener(new MeterColorAdapter.OnViewClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterColorView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter.OnViewClickListener
            public final void onClick(View view, int i) {
                this.f$0.m1095xf3b2d4ef(view, i);
            }
        });
        this.mRecyclerView1.setOnScrollChangeListener(new MyRecyclerView.OnScrollChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterColorView.1
            @Override // com.hzbhd.canbus.car_cus._283.view.MyRecyclerView.OnScrollChangeListener
            public void scrollUp() {
                MeterColorView.this.mRecyclerView1.smoothScrollToPosition(0);
                MeterColorView.this.view1.setBackgroundResource(R.drawable._a6_meter_dot_b);
                MeterColorView.this.view2.setBackgroundResource(R.drawable._a6_meter_dot_g);
            }

            @Override // com.hzbhd.canbus.car_cus._283.view.MyRecyclerView.OnScrollChangeListener
            public void scrollDown() {
                MeterColorView.this.mRecyclerView1.smoothScrollToPosition(MeterColorView.this.colors1.length - 1);
                MeterColorView.this.view1.setBackgroundResource(R.drawable._a6_meter_dot_g);
                MeterColorView.this.view2.setBackgroundResource(R.drawable._a6_meter_dot_b);
            }
        });
        this.mRecyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 6));
        MeterColorAdapter meterColorAdapter2 = new MeterColorAdapter(getContext(), this.colors2);
        this.mRecyclerView2.setAdapter(meterColorAdapter2);
        meterColorAdapter2.setOnViewClickListener(new MeterColorAdapter.OnViewClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterColorView$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter.OnViewClickListener
            public final void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, 109, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) (i + 3 + 1)});
            }
        });
    }

    /* renamed from: lambda$onFinishInflate$0$com-hzbhd-canbus-car_cus-_283-view-MeterColorView, reason: not valid java name */
    /* synthetic */ void m1095xf3b2d4ef(View view, int i) {
        int i2 = this.colors1_byte[i];
        MessageSender.sendMsg(new byte[]{25, 0, 0, 0, 0, 33, 0, 3, (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)});
    }
}
