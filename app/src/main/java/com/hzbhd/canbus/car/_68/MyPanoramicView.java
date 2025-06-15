package com.hzbhd.canbus.car._68;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;


public class MyPanoramicView extends RelativeLayout {
    int mRearCarStatus;
    private ImageView mRearCarWarnLeft1;
    private ImageView mRearCarWarnLeft2;
    private ImageView mRearCarWarnRight1;
    private ImageView mRearCarWarnRight2;

    public MyPanoramicView(Context context) {
        super(context);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_68_view, this);
        this.mRearCarWarnLeft1 = (ImageView) viewInflate.findViewById(R.id.imageView11);
        this.mRearCarWarnLeft2 = (ImageView) viewInflate.findViewById(R.id.imageView9);
        this.mRearCarWarnRight1 = (ImageView) viewInflate.findViewById(R.id.imageView10);
        this.mRearCarWarnRight2 = (ImageView) viewInflate.findViewById(R.id.imageView12);
    }

    void updatePanoramicView() {
        this.mRearCarWarnLeft1.setVisibility(4);
        this.mRearCarWarnLeft2.setVisibility(4);
        this.mRearCarWarnRight1.setVisibility(4);
        this.mRearCarWarnRight2.setVisibility(4);
        int i = this.mRearCarStatus;
        if (i == 1) {
            this.mRearCarWarnLeft1.setVisibility(0);
            this.mRearCarWarnLeft2.setVisibility(0);
        } else if (i == 2) {
            this.mRearCarWarnRight1.setVisibility(0);
            this.mRearCarWarnRight2.setVisibility(0);
        } else if (i == 3) {
            this.mRearCarWarnLeft1.setVisibility(0);
            this.mRearCarWarnLeft2.setVisibility(0);
            this.mRearCarWarnRight1.setVisibility(0);
            this.mRearCarWarnRight2.setVisibility(0);
        }
    }
}
