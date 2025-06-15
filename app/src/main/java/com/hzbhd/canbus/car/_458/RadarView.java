package com.hzbhd.canbus.car._458;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;


public class RadarView extends RelativeLayout {
    ImageView rear_left_line;
    ImageView rear_mid_line;
    ImageView rear_right_line;

    public RadarView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout._441_md_radar_view, this);
        this.rear_left_line = (ImageView) findViewById(R.id.rear_left_line);
        this.rear_mid_line = (ImageView) findViewById(R.id.rear_mid_line);
        this.rear_right_line = (ImageView) findViewById(R.id.rear_right_line);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void refreshRadar() {
        if (MdRadarData.left_rear == 0) {
            this.rear_left_line.setBackgroundResource(R.drawable._441_radar_null1);
        } else if (MdRadarData.left_rear == 1) {
            this.rear_left_line.setBackgroundResource(R.drawable._441_radar_zuoxia3);
        } else if (MdRadarData.left_rear == 2) {
            this.rear_left_line.setBackgroundResource(R.drawable._441_radar_zuoxia2);
        } else if (MdRadarData.left_rear == 3) {
            this.rear_left_line.setBackgroundResource(R.drawable._441_radar_zuoxia1);
        }
        if (MdRadarData.mid_rear == 0) {
            this.rear_mid_line.setBackgroundResource(R.drawable._441_radar_null2);
        } else if (MdRadarData.mid_rear == 1) {
            this.rear_mid_line.setBackgroundResource(R.drawable._441_radar_xiazhong3);
        } else if (MdRadarData.mid_rear == 2) {
            this.rear_mid_line.setBackgroundResource(R.drawable._441_radar_xiazhong2);
        } else if (MdRadarData.mid_rear == 3) {
            this.rear_mid_line.setBackgroundResource(R.drawable._441_radar_xiazhong1);
        }
        if (MdRadarData.right_rear == 0) {
            this.rear_right_line.setBackgroundResource(R.drawable._441_radar_null1);
            return;
        }
        if (MdRadarData.right_rear == 1) {
            this.rear_right_line.setBackgroundResource(R.drawable._441_radar_youxia3);
        } else if (MdRadarData.right_rear == 2) {
            this.rear_right_line.setBackgroundResource(R.drawable._441_radar_youxia2);
        } else if (MdRadarData.right_rear == 3) {
            this.rear_right_line.setBackgroundResource(R.drawable._441_radar_youxia1);
        }
    }
}
