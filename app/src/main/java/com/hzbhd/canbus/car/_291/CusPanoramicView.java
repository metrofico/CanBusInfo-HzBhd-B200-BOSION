package com.hzbhd.canbus.car._291;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._291.RadarView;
import com.hzbhd.canbus.ui_datas.GeneralParkData;

/* loaded from: classes2.dex */
public class CusPanoramicView extends RelativeLayout {
    private ImageView baselineImage;
    private int[] leftImageTracks;
    private Context mContext;
    private RadarView mRadarView;
    private int[] righttImageTracks;
    private ImageView trackImage;
    private int tracks0;

    public CusPanoramicView(Context context) {
        super(context);
        this.leftImageTracks = new int[]{R.drawable.dz_reverse_track_1_l, R.drawable.dz_reverse_track_2_l, R.drawable.dz_reverse_track_3_l, R.drawable.dz_reverse_track_4_l, R.drawable.dz_reverse_track_5_l, R.drawable.dz_reverse_track_6_l, R.drawable.dz_reverse_track_7_l, R.drawable.dz_reverse_track_8_l, R.drawable.dz_reverse_track_9_l, R.drawable.dz_reverse_track_10_l, R.drawable.dz_reverse_track_11_l, R.drawable.dz_reverse_track_12_l, R.drawable.dz_reverse_track_13_l, R.drawable.dz_reverse_track_14_l, R.drawable.dz_reverse_track_15_l, R.drawable.dz_reverse_track_16_l, R.drawable.dz_reverse_track_17_l, R.drawable.dz_reverse_track_18_l, R.drawable.dz_reverse_track_19_l, R.drawable.dz_reverse_track_20_l, R.drawable.dz_reverse_track_21_l, R.drawable.dz_reverse_track_22_l, R.drawable.dz_reverse_track_23_l, R.drawable.dz_reverse_track_24_l, R.drawable.dz_reverse_track_25_l, R.drawable.dz_reverse_track_26_l};
        this.righttImageTracks = new int[]{R.drawable.dz_reverse_track_1_r, R.drawable.dz_reverse_track_2_r, R.drawable.dz_reverse_track_3_r, R.drawable.dz_reverse_track_4_r, R.drawable.dz_reverse_track_5_r, R.drawable.dz_reverse_track_6_r, R.drawable.dz_reverse_track_7_r, R.drawable.dz_reverse_track_8_r, R.drawable.dz_reverse_track_9_r, R.drawable.dz_reverse_track_10_r, R.drawable.dz_reverse_track_11_r, R.drawable.dz_reverse_track_12_r, R.drawable.dz_reverse_track_13_r, R.drawable.dz_reverse_track_14_r, R.drawable.dz_reverse_track_15_r, R.drawable.dz_reverse_track_16_r, R.drawable.dz_reverse_track_17_r, R.drawable.dz_reverse_track_18_r, R.drawable.dz_reverse_track_19_r, R.drawable.dz_reverse_track_20_r, R.drawable.dz_reverse_track_21_r, R.drawable.dz_reverse_track_22_r, R.drawable.dz_reverse_track_23_r, R.drawable.dz_reverse_track_24_r, R.drawable.dz_reverse_track_25_r, R.drawable.dz_reverse_track_26_r};
        this.tracks0 = R.drawable.dz_reverse_track_0;
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_panoramice_291_view, this);
        this.mRadarView = (RadarView) viewInflate.findViewById(R.id.radarView);
        this.trackImage = (ImageView) viewInflate.findViewById(R.id.trackImage);
        this.baselineImage = (ImageView) viewInflate.findViewById(R.id.baselineImage);
    }

    public CusPanoramicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.leftImageTracks = new int[]{R.drawable.dz_reverse_track_1_l, R.drawable.dz_reverse_track_2_l, R.drawable.dz_reverse_track_3_l, R.drawable.dz_reverse_track_4_l, R.drawable.dz_reverse_track_5_l, R.drawable.dz_reverse_track_6_l, R.drawable.dz_reverse_track_7_l, R.drawable.dz_reverse_track_8_l, R.drawable.dz_reverse_track_9_l, R.drawable.dz_reverse_track_10_l, R.drawable.dz_reverse_track_11_l, R.drawable.dz_reverse_track_12_l, R.drawable.dz_reverse_track_13_l, R.drawable.dz_reverse_track_14_l, R.drawable.dz_reverse_track_15_l, R.drawable.dz_reverse_track_16_l, R.drawable.dz_reverse_track_17_l, R.drawable.dz_reverse_track_18_l, R.drawable.dz_reverse_track_19_l, R.drawable.dz_reverse_track_20_l, R.drawable.dz_reverse_track_21_l, R.drawable.dz_reverse_track_22_l, R.drawable.dz_reverse_track_23_l, R.drawable.dz_reverse_track_24_l, R.drawable.dz_reverse_track_25_l, R.drawable.dz_reverse_track_26_l};
        this.righttImageTracks = new int[]{R.drawable.dz_reverse_track_1_r, R.drawable.dz_reverse_track_2_r, R.drawable.dz_reverse_track_3_r, R.drawable.dz_reverse_track_4_r, R.drawable.dz_reverse_track_5_r, R.drawable.dz_reverse_track_6_r, R.drawable.dz_reverse_track_7_r, R.drawable.dz_reverse_track_8_r, R.drawable.dz_reverse_track_9_r, R.drawable.dz_reverse_track_10_r, R.drawable.dz_reverse_track_11_r, R.drawable.dz_reverse_track_12_r, R.drawable.dz_reverse_track_13_r, R.drawable.dz_reverse_track_14_r, R.drawable.dz_reverse_track_15_r, R.drawable.dz_reverse_track_16_r, R.drawable.dz_reverse_track_17_r, R.drawable.dz_reverse_track_18_r, R.drawable.dz_reverse_track_19_r, R.drawable.dz_reverse_track_20_r, R.drawable.dz_reverse_track_21_r, R.drawable.dz_reverse_track_22_r, R.drawable.dz_reverse_track_23_r, R.drawable.dz_reverse_track_24_r, R.drawable.dz_reverse_track_25_r, R.drawable.dz_reverse_track_26_r};
        this.tracks0 = R.drawable.dz_reverse_track_0;
    }

    public CusPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.leftImageTracks = new int[]{R.drawable.dz_reverse_track_1_l, R.drawable.dz_reverse_track_2_l, R.drawable.dz_reverse_track_3_l, R.drawable.dz_reverse_track_4_l, R.drawable.dz_reverse_track_5_l, R.drawable.dz_reverse_track_6_l, R.drawable.dz_reverse_track_7_l, R.drawable.dz_reverse_track_8_l, R.drawable.dz_reverse_track_9_l, R.drawable.dz_reverse_track_10_l, R.drawable.dz_reverse_track_11_l, R.drawable.dz_reverse_track_12_l, R.drawable.dz_reverse_track_13_l, R.drawable.dz_reverse_track_14_l, R.drawable.dz_reverse_track_15_l, R.drawable.dz_reverse_track_16_l, R.drawable.dz_reverse_track_17_l, R.drawable.dz_reverse_track_18_l, R.drawable.dz_reverse_track_19_l, R.drawable.dz_reverse_track_20_l, R.drawable.dz_reverse_track_21_l, R.drawable.dz_reverse_track_22_l, R.drawable.dz_reverse_track_23_l, R.drawable.dz_reverse_track_24_l, R.drawable.dz_reverse_track_25_l, R.drawable.dz_reverse_track_26_l};
        this.righttImageTracks = new int[]{R.drawable.dz_reverse_track_1_r, R.drawable.dz_reverse_track_2_r, R.drawable.dz_reverse_track_3_r, R.drawable.dz_reverse_track_4_r, R.drawable.dz_reverse_track_5_r, R.drawable.dz_reverse_track_6_r, R.drawable.dz_reverse_track_7_r, R.drawable.dz_reverse_track_8_r, R.drawable.dz_reverse_track_9_r, R.drawable.dz_reverse_track_10_r, R.drawable.dz_reverse_track_11_r, R.drawable.dz_reverse_track_12_r, R.drawable.dz_reverse_track_13_r, R.drawable.dz_reverse_track_14_r, R.drawable.dz_reverse_track_15_r, R.drawable.dz_reverse_track_16_r, R.drawable.dz_reverse_track_17_r, R.drawable.dz_reverse_track_18_r, R.drawable.dz_reverse_track_19_r, R.drawable.dz_reverse_track_20_r, R.drawable.dz_reverse_track_21_r, R.drawable.dz_reverse_track_22_r, R.drawable.dz_reverse_track_23_r, R.drawable.dz_reverse_track_24_r, R.drawable.dz_reverse_track_25_r, R.drawable.dz_reverse_track_26_r};
        this.tracks0 = R.drawable.dz_reverse_track_0;
    }

    public void refreshUiLine() {
        this.mRadarView.showRadar();
        this.baselineImage.setVisibility(0);
        this.trackImage.setVisibility(0);
    }

    public void refreRadarUi() {
        this.mRadarView.refreshUi();
    }

    public void updateParkUi() {
        this.trackImage.setImageResource(getImageResource(GeneralParkData.trackAngle));
    }

    private int getImageResource(int i) {
        if (i > 0) {
            return getResDrawable(i, this.righttImageTracks);
        }
        if (i < 0) {
            return getResDrawable(-i, this.leftImageTracks);
        }
        return this.tracks0;
    }

    private int getResDrawable(int i, int[] iArr) {
        switch (i) {
            case 1:
                return iArr[1];
            case 2:
                return iArr[2];
            case 3:
                return iArr[3];
            case 4:
                return iArr[4];
            case 5:
                return iArr[5];
            case 6:
                return iArr[6];
            case 7:
                return iArr[7];
            case 8:
                return iArr[8];
            case 9:
                return iArr[9];
            case 10:
                return iArr[10];
            case 11:
            case 12:
                return iArr[11];
            case 13:
            case 14:
                return iArr[12];
            case 15:
            case 16:
                return iArr[13];
            case 17:
            case 18:
                return iArr[14];
            case 19:
            case 20:
                return iArr[15];
            case 21:
            case 22:
                return iArr[16];
            case 23:
            case 24:
                return iArr[17];
            case 25:
            case 26:
                return iArr[18];
            case 27:
            case 28:
                return iArr[19];
            case 29:
            case 30:
                return iArr[20];
            case 31:
            case 32:
                return iArr[21];
            case 33:
            case 34:
                return iArr[22];
            case 35:
            case 36:
                return iArr[23];
            case 37:
            case 38:
                return iArr[24];
            case 39:
            case 40:
                return iArr[25];
            default:
                return this.tracks0;
        }
    }
}
