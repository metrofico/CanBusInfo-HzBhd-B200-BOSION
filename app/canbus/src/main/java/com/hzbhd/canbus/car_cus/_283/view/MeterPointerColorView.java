package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter;

/* loaded from: classes2.dex */
public class MeterPointerColorView extends RelativeLayout {
    private MeterColorAdapter colorAdapter1;
    private int[] colors1;
    private int[] colors1_byte;
    private RecyclerView mRecyclerView1;

    public MeterPointerColorView(Context context) {
        this(context, null);
    }

    public MeterPointerColorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MeterPointerColorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.colors1_byte = new int[]{ViewCompat.MEASURED_SIZE_MASK, 15400960, 18687, 7400960, 2162632, 52449, 16762368, 13789470, 11591910, 16668912, 8142782, 16753828, 14423100, 16748800, 13529087, 14929751, 65533, 11534144};
        this.colors1 = new int[]{R.drawable._a6_meter_pointer_1, R.drawable._a6_meter_pointer_2, R.drawable._a6_meter_pointer_3, R.drawable._a6_meter_pointer_4, R.drawable._a6_meter_pointer_5, R.drawable._a6_meter_pointer_6, R.drawable._a6_meter_pointer_7, R.drawable._a6_meter_pointer_8, R.drawable._a6_meter_pointer_9, R.drawable._a6_meter_pointer_10, R.drawable._a6_meter_pointer_11, R.drawable._a6_meter_pointer_12, R.drawable._a6_meter_pointer_13, R.drawable._a6_meter_pointer_14, R.drawable._a6_meter_pointer_15, R.drawable._a6_meter_pointer_16, R.drawable._a6_meter_pointer_17, R.drawable._a6_meter_pointer_18};
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        this.mRecyclerView1 = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 6));
        MeterColorAdapter meterColorAdapter = new MeterColorAdapter(getContext(), this.colors1);
        this.colorAdapter1 = meterColorAdapter;
        this.mRecyclerView1.setAdapter(meterColorAdapter);
        this.colorAdapter1.setOnViewClickListener(new MeterColorAdapter.OnViewClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.MeterPointerColorView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.adapter.MeterColorAdapter.OnViewClickListener
            public final void onClick(View view, int i) {
                this.f$0.m1096xb8f41cc4(view, i);
            }
        });
    }

    /* renamed from: lambda$onFinishInflate$0$com-hzbhd-canbus-car_cus-_283-view-MeterPointerColorView, reason: not valid java name */
    /* synthetic */ void m1096xb8f41cc4(View view, int i) {
        int i2 = this.colors1_byte[i];
        MessageSender.sendMsg(new byte[]{25, 0, 0, 0, 0, 33, 0, 3, (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)});
    }

    public void setSelectedItem() {
        int i = 0;
        while (true) {
            int[] iArr = this.colors1_byte;
            if (i >= iArr.length) {
                return;
            }
            int i2 = iArr[i];
            int i3 = (i2 >> 16) & 255;
            int i4 = (i2 >> 8) & 255;
            int i5 = i2 & 255;
            if (GeneralDzData.colorR == i3 && GeneralDzData.colorG == i4 && GeneralDzData.colorB == i5) {
                this.colorAdapter1.setSelectedItem(i);
                return;
            }
            i++;
        }
    }
}
