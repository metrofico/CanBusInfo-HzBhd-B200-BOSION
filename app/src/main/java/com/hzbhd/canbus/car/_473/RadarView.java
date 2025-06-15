package com.hzbhd.canbus.car._473;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class RadarView extends RelativeLayout {
    FrameLayout left_rear;
    ImageView left_rear_point;
    FrameLayout right_rear;
    ImageView right_rear_point;

    public RadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RadarView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout._473_md_radar_view, this);
        this.left_rear_point = (ImageView) findViewById(R.id.left_rear_point);
        this.right_rear_point = (ImageView) findViewById(R.id.left_rear_point);
        this.left_rear = (FrameLayout) findViewById(R.id.left_rear);
        this.right_rear = (FrameLayout) findViewById(R.id.right_rear);
    }

    public void refreshRadar() {
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car._473.RadarView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m892lambda$refreshRadar$0$comhzbhdcanbuscar_473RadarView();
            }
        });
    }

    /* renamed from: lambda$refreshRadar$0$com-hzbhd-canbus-car-_473-RadarView, reason: not valid java name */
    /* synthetic */ Unit m892lambda$refreshRadar$0$comhzbhdcanbuscar_473RadarView() {
        if (LogUtil.log5()) {
            LogUtil.d("refreshRadar(): --------------" + this.left_rear.getMeasuredWidth() + "----" + this.left_rear_point.getMeasuredWidth());
        }
        this.left_rear.setPadding((MdRadarData.reverse_right_rear_horizontal * (this.left_rear.getMeasuredWidth() - this.left_rear_point.getMeasuredWidth())) / 255, 0, 0, (MdRadarData.reverse_left_rear_vertical * (this.left_rear.getMeasuredHeight() - this.left_rear_point.getMeasuredHeight())) / 255);
        this.right_rear.setPadding(0, 0, (MdRadarData.reverse_right_rear_horizontal * (this.right_rear.getMeasuredWidth() - this.right_rear_point.getMeasuredWidth())) / 255, (MdRadarData.reverse_right_rear_vertical * (this.right_rear.getMeasuredHeight() - this.right_rear_point.getMeasuredHeight())) / 255);
        return null;
    }
}
