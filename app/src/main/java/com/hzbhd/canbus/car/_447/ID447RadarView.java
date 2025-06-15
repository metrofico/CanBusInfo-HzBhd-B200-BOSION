package com.hzbhd.canbus.car._447;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class ID447RadarView extends RelativeLayout {
    private ImageView rear_left;
    private ImageView rear_left_mid;
    private ImageView rear_right;
    private ImageView rear_right_mid;
    private View view;

    public ID447RadarView(Context context) {
        this(context, null);
    }

    public ID447RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ID447RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__id_447_radar_view, (ViewGroup) this, true);
        this.view = viewInflate;
        this.rear_left = (ImageView) viewInflate.findViewById(R.id.rear_left);
        this.rear_left_mid = (ImageView) this.view.findViewById(R.id.rear_left_mid);
        this.rear_right_mid = (ImageView) this.view.findViewById(R.id.rear_right_mid);
        this.rear_right = (ImageView) this.view.findViewById(R.id.rear_right);
    }

    public void updateRadar() {
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._447.ID447RadarView.1
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                if (ID447Data.rearLeftRange == 0) {
                    ID447RadarView.this.rear_left.setBackgroundResource(R.drawable._flb_447_null);
                } else if (ID447Data.rearLeftRange == 1) {
                    ID447RadarView.this.rear_left.setBackgroundResource(R.drawable._flb_447_hz1);
                } else if (ID447Data.rearLeftRange == 2) {
                    ID447RadarView.this.rear_left.setBackgroundResource(R.drawable._flb_447_hz2);
                } else if (ID447Data.rearLeftRange == 3) {
                    ID447RadarView.this.rear_left.setBackgroundResource(R.drawable._flb_447_hz3);
                } else if (ID447Data.rearLeftRange == 4) {
                    ID447RadarView.this.rear_left.setBackgroundResource(R.drawable._flb_447_hz4);
                }
                if (ID447Data.rearLeftMidRange == 0) {
                    ID447RadarView.this.rear_left_mid.setBackgroundResource(R.drawable._flb_447_null);
                } else if (ID447Data.rearLeftMidRange == 1) {
                    ID447RadarView.this.rear_left_mid.setBackgroundResource(R.drawable._flb_447_hzz1);
                } else if (ID447Data.rearLeftMidRange == 2) {
                    ID447RadarView.this.rear_left_mid.setBackgroundResource(R.drawable._flb_447_hzz2);
                } else if (ID447Data.rearLeftMidRange == 3) {
                    ID447RadarView.this.rear_left_mid.setBackgroundResource(R.drawable._flb_447_hzz3);
                } else if (ID447Data.rearLeftMidRange == 4) {
                    ID447RadarView.this.rear_left_mid.setBackgroundResource(R.drawable._flb_447_hzz4);
                }
                if (ID447Data.rearRightMidRange == 0) {
                    ID447RadarView.this.rear_right_mid.setBackgroundResource(R.drawable._flb_447_null);
                } else if (ID447Data.rearRightMidRange == 1) {
                    ID447RadarView.this.rear_right_mid.setBackgroundResource(R.drawable._flb_447_hyz1);
                } else if (ID447Data.rearRightMidRange == 2) {
                    ID447RadarView.this.rear_right_mid.setBackgroundResource(R.drawable._flb_447_hyz2);
                } else if (ID447Data.rearRightMidRange == 3) {
                    ID447RadarView.this.rear_right_mid.setBackgroundResource(R.drawable._flb_447_hyz3);
                } else if (ID447Data.rearRightMidRange == 4) {
                    ID447RadarView.this.rear_right_mid.setBackgroundResource(R.drawable._flb_447_hyz4);
                }
                if (ID447Data.rearRightRange == 0) {
                    ID447RadarView.this.rear_right.setBackgroundResource(R.drawable._flb_447_null);
                    return null;
                }
                if (ID447Data.rearRightRange == 1) {
                    ID447RadarView.this.rear_right.setBackgroundResource(R.drawable._flb_447_hy1);
                    return null;
                }
                if (ID447Data.rearRightRange == 2) {
                    ID447RadarView.this.rear_right.setBackgroundResource(R.drawable._flb_447_hy2);
                    return null;
                }
                if (ID447Data.rearRightRange == 3) {
                    ID447RadarView.this.rear_right.setBackgroundResource(R.drawable._flb_447_hy3);
                    return null;
                }
                if (ID447Data.rearRightRange != 4) {
                    return null;
                }
                ID447RadarView.this.rear_right.setBackgroundResource(R.drawable._flb_447_hy4);
                return null;
            }
        });
    }
}
