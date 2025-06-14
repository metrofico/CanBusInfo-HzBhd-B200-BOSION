package com.hzbhd.canbus.car._299;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._299.RadarView;
import com.hzbhd.common.settings.constant.BodaSysContant;

/* loaded from: classes2.dex */
public class CusPanoramicView extends RelativeLayout {
    private RadarView mRadarView;

    public CusPanoramicView(Context context) {
        super(context);
        RadarView radarView = (RadarView) LayoutInflater.from(context).inflate(R.layout.layout_panoramice_299_view, this).findViewById(R.id.radarView);
        this.mRadarView = radarView;
        radarView.setBackgroundLayoutColor(0);
    }

    public void refreshRadarUi() {
        this.mRadarView.refreshUi();
    }

    public void setVisible(Context context) {
        try {
            if (Settings.System.getInt(context.getContentResolver(), BodaSysContant.Can.RadarDisp) == 1) {
                this.mRadarView.setVisibility(0);
            } else {
                this.mRadarView.setVisibility(8);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
}
