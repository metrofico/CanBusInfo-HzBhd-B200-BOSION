package com.hzbhd.canbus.car._304;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._304.view.MyRadarView;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

/* loaded from: classes2.dex */
public class CusPanoramicView extends RelativeLayout {
    private final String TAG;
    private Button mBtnExit;
    private MyRadarView mRadarView;

    public CusPanoramicView(Context context) {
        super(context);
        this.TAG = "_304_CusPanoramicView";
        findViews(context);
    }

    private void findViews(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_304_view, this);
        this.mRadarView = (MyRadarView) viewInflate.findViewById(R.id.radar_view);
        this.mBtnExit = (Button) viewInflate.findViewById(R.id.btn_exit_avm);
    }

    public void initView(final ParkPageUiSet parkPageUiSet) {
        this.mRadarView.initViews(parkPageUiSet.getOnPanoramicItemClickListener());
        this.mBtnExit.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._304.CusPanoramicView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                parkPageUiSet.getOnPanoramicItemClickListener().onClickItem(6);
            }
        });
    }

    public void refreshRadar() {
        this.mRadarView.refreshRadar();
    }
}
