package com.hzbhd.canbus.car._470;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class RadarView extends RelativeLayout {
    TextView pdc_ecufault;
    TextView pdc_led;
    TextView radar_mode;
    ImageView rear_center_error;
    ImageView rear_centre;
    ImageView rear_left;
    ImageView rear_left_error;
    ImageView rear_right;
    ImageView rear_right_error;

    public RadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RadarView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout._470_md_radar_view, this);
        this.radar_mode = (TextView) findViewById(R.id.radar_mode);
        this.pdc_led = (TextView) findViewById(R.id.pdc_led);
        this.pdc_ecufault = (TextView) findViewById(R.id.pdc_ecufault);
        this.rear_left = (ImageView) findViewById(R.id.rear_left);
        this.rear_centre = (ImageView) findViewById(R.id.rear_centre);
        this.rear_right = (ImageView) findViewById(R.id.rear_right);
        this.rear_left_error = (ImageView) findViewById(R.id.rear_left_error);
        this.rear_center_error = (ImageView) findViewById(R.id.rear_center_error);
        this.rear_right_error = (ImageView) findViewById(R.id.rear_right_error);
    }

    public void refreshRadar() {
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car._470.RadarView$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m890lambda$refreshRadar$0$comhzbhdcanbuscar_470RadarView();
            }
        });
    }

    /* renamed from: lambda$refreshRadar$0$com-hzbhd-canbus-car-_470-RadarView, reason: not valid java name */
    /* synthetic */ Unit m890lambda$refreshRadar$0$comhzbhdcanbuscar_470RadarView() {
        if (MdRadarData.sensorFaultRl) {
            this.rear_left_error.setVisibility(8);
        } else {
            this.rear_left_error.setVisibility(0);
        }
        if (MdRadarData.sensorFaultRml) {
            this.rear_center_error.setVisibility(8);
        } else {
            this.rear_center_error.setVisibility(0);
        }
        if (MdRadarData.sensorFaultRr) {
            this.rear_right_error.setVisibility(8);
        } else {
            this.rear_right_error.setVisibility(0);
        }
        switch (MdRadarData.distanceRl) {
            case 0:
                this.rear_left.setBackgroundResource(R.drawable.transparent);
                break;
            case 1:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_1);
                break;
            case 2:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_2);
                break;
            case 3:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_3);
                break;
            case 4:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_4);
                break;
            case 5:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_5);
                break;
            case 6:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_6);
                break;
            case 7:
                this.rear_left.setBackgroundResource(R.drawable.__470__rear_left_signal_7);
                break;
        }
        switch (MdRadarData.distanceRml) {
            case 0:
                this.rear_centre.setBackgroundResource(R.drawable.transparent);
                break;
            case 1:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_1);
                break;
            case 2:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_2);
                break;
            case 3:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_3);
                break;
            case 4:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_4);
                break;
            case 5:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_5);
                break;
            case 6:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_6);
                break;
            case 7:
                this.rear_centre.setBackgroundResource(R.drawable.__470__rear_centre_signal_7);
                break;
        }
        switch (MdRadarData.distanceRr) {
            case 0:
                this.rear_right.setBackgroundResource(R.drawable.transparent);
                break;
            case 1:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_1);
                break;
            case 2:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_2);
                break;
            case 3:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_3);
                break;
            case 4:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_4);
                break;
            case 5:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_5);
                break;
            case 6:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_6);
                break;
            case 7:
                this.rear_right.setBackgroundResource(R.drawable.__470__rear_right_signal_7);
                break;
        }
        switch (MdRadarData.reverse_radar_working_mode) {
            case 0:
                this.radar_mode.setText("off");
                break;
            case 1:
                this.radar_mode.setText("Standby");
                break;
            case 2:
                this.radar_mode.setText("Front Rear Active");
                break;
            case 3:
                this.radar_mode.setText("Front Active");
                break;
            case 4:
                this.radar_mode.setText("Rear Active");
                break;
            case 5:
                this.radar_mode.setText("System Failure");
                break;
            case 6:
                this.radar_mode.setText("Inhibited");
                break;
            case 7:
                this.radar_mode.setText("Invalid(Reserved)");
                break;
        }
        if (MdRadarData.pdc_led) {
            this.pdc_led.setText("开启");
        } else {
            this.pdc_led.setText("关闭");
        }
        if (MdRadarData.pdc_ecufault) {
            this.pdc_ecufault.setText("正常");
            return null;
        }
        this.pdc_ecufault.setText("故障");
        return null;
    }
}
