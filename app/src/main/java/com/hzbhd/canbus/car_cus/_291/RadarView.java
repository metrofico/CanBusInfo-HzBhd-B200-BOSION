package com.hzbhd.canbus.car_cus._291;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class RadarView extends RelativeLayout {
    public static final String SHARE_SYS_REVERSE = "sys.Reverse";
    private static final int TIME = 3000;
    private ImageView front_radar_left;
    private int[] front_radar_leftImages;
    private ImageView front_radar_left_mid;
    private int[] front_radar_left_midImages;
    private ImageView front_radar_right;
    private int[] front_radar_rightImages;
    private ImageView front_radar_right_mid;
    private int[] front_radar_right_midImages;
    private boolean isShowing;
    private Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    int mReverseStatus;
    private Runnable mRunnable;
    private View mView;
    private WindowManager mWindowManager;
    private RelativeLayout radarLayout;
    private ImageView rear_radar_left;
    private int[] rear_radar_leftImages;
    private ImageView rear_radar_left_mid;
    private int[] rear_radar_left_midImages;
    private ImageView rear_radar_right;
    private int[] rear_radar_rightImages;
    private ImageView rear_radar_right_mid;
    private int[] rear_radar_right_midImages;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isShowing = false;
        this.front_radar_leftImages = new int[]{R.drawable.__283__dz_radar1_left_2_1, R.drawable.__283__dz_radar1_left_2_2, R.drawable.__283__dz_radar1_left_2_3, R.drawable.__283__dz_radar1_left_2_4, R.drawable.__283__dz_radar1_left_2_5, R.drawable.__283__dz_radar1_left_2_6};
        this.front_radar_left_midImages = new int[]{R.drawable.__283__dz_radar1_left_1_1, R.drawable.__283__dz_radar1_left_1_2, R.drawable.__283__dz_radar1_left_1_3, R.drawable.__283__dz_radar1_left_1_4, R.drawable.__283__dz_radar1_left_1_5, R.drawable.__283__dz_radar1_left_1_6};
        this.rear_radar_leftImages = new int[]{R.drawable.__283__dz_radar1_left_7_1, R.drawable.__283__dz_radar1_left_7_2, R.drawable.__283__dz_radar1_left_7_3, R.drawable.__283__dz_radar1_left_7_4, R.drawable.__283__dz_radar1_left_7_5, R.drawable.__283__dz_radar1_left_7_6};
        this.rear_radar_left_midImages = new int[]{R.drawable.__283__dz_radar1_left_6_1, R.drawable.__283__dz_radar1_left_6_2, R.drawable.__283__dz_radar1_left_6_3, R.drawable.__283__dz_radar1_left_6_4, R.drawable.__283__dz_radar1_left_6_5, R.drawable.__283__dz_radar1_left_6_6};
        this.front_radar_right_midImages = new int[]{R.drawable.__283__dz_radar1_right_1_1, R.drawable.__283__dz_radar1_right_1_2, R.drawable.__283__dz_radar1_right_1_3, R.drawable.__283__dz_radar1_right_1_4, R.drawable.__283__dz_radar1_right_1_5, R.drawable.__283__dz_radar1_right_1_6};
        this.front_radar_rightImages = new int[]{R.drawable.__283__dz_radar1_right_2_1, R.drawable.__283__dz_radar1_right_2_2, R.drawable.__283__dz_radar1_right_2_3, R.drawable.__283__dz_radar1_right_2_4, R.drawable.__283__dz_radar1_right_2_5, R.drawable.__283__dz_radar1_right_2_6};
        this.rear_radar_rightImages = new int[]{R.drawable.__283__dz_radar1_right_7_1, R.drawable.__283__dz_radar1_right_7_2, R.drawable.__283__dz_radar1_right_7_3, R.drawable.__283__dz_radar1_right_7_4, R.drawable.__283__dz_radar1_right_7_5, R.drawable.__283__dz_radar1_right_7_6};
        this.rear_radar_right_midImages = new int[]{R.drawable.__283__dz_radar1_right_6_1, R.drawable.__283__dz_radar1_right_6_2, R.drawable.__283__dz_radar1_right_6_3, R.drawable.__283__dz_radar1_right_6_4, R.drawable.__283__dz_radar1_right_6_5, R.drawable.__283__dz_radar1_right_6_6};
        this.mReverseStatus = 0;
        this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.RadarView.2
            @Override // java.lang.Runnable
            public void run() {
                RadarView.this.dismissView();
            }
        };
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout._291_radar_view, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.front_radar_left_mid = (ImageView) this.mView.findViewById(R.id.front_radar_left_mid);
        this.front_radar_left = (ImageView) this.mView.findViewById(R.id.front_radar_left);
        this.rear_radar_left = (ImageView) this.mView.findViewById(R.id.rear_radar_left);
        this.rear_radar_left_mid = (ImageView) this.mView.findViewById(R.id.rear_radar_left_mid);
        this.front_radar_right_mid = (ImageView) this.mView.findViewById(R.id.front_radar_right_mid);
        this.front_radar_right = (ImageView) this.mView.findViewById(R.id.front_radar_right);
        this.rear_radar_right = (ImageView) this.mView.findViewById(R.id.rear_radar_right);
        this.rear_radar_right_mid = (ImageView) this.mView.findViewById(R.id.rear_radar_right_mid);
        this.radarLayout = (RelativeLayout) this.mView.findViewById(R.id.radarLayout);
    }

    public void refreshUi() {
        HashMap<Constant.RadarLocation, Integer> map = GeneralParkData.radar_distance_data;
        this.front_radar_left_mid.setImageDrawable(getRadarImageResource(this.front_radar_left_midImages, map.get(Constant.RadarLocation.FRONT_MID_LEFT).intValue()));
        this.front_radar_left.setImageDrawable(getRadarImageResource(this.front_radar_leftImages, map.get(Constant.RadarLocation.FRONT_LEFT).intValue()));
        this.front_radar_right_mid.setImageDrawable(getRadarImageResource(this.front_radar_right_midImages, map.get(Constant.RadarLocation.FRONT_MID_RIGHT).intValue()));
        this.front_radar_right.setImageDrawable(getRadarImageResource(this.front_radar_rightImages, map.get(Constant.RadarLocation.FRONT_RIGHT).intValue()));
        this.rear_radar_left_mid.setImageDrawable(getRadarImageResource(this.rear_radar_left_midImages, map.get(Constant.RadarLocation.REAR_MID_LEFT).intValue()));
        this.rear_radar_left.setImageDrawable(getRadarImageResource(this.rear_radar_leftImages, map.get(Constant.RadarLocation.REAR_LEFT).intValue()));
        this.rear_radar_right_mid.setImageDrawable(getRadarImageResource(this.rear_radar_right_midImages, map.get(Constant.RadarLocation.REAR_MID_RIGHT).intValue()));
        this.rear_radar_right.setImageDrawable(getRadarImageResource(this.rear_radar_rightImages, map.get(Constant.RadarLocation.REAR_RIGHT).intValue()));
        if (getReverseState()) {
            return;
        }
        addViewToWindow();
    }

    public boolean getReverseState() {
        int iRegisterShareIntListener = ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", new IShareIntListener() { // from class: com.hzbhd.canbus.car_cus._291.RadarView.1
            @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
            public void onInt(int i) {
                if (RadarView.this.mReverseStatus == i) {
                    return;
                }
                RadarView.this.mReverseStatus = i;
            }
        });
        this.mReverseStatus = iRegisterShareIntListener;
        return iRegisterShareIntListener == 1;
    }

    private Drawable getRadarImageResource(int[] iArr, int i) {
        if (i == 255) {
            return null;
        }
        int length = i <= 127 ? i > 105 ? 6 : i > 84 ? 5 : i > 63 ? 4 : i > 42 ? 3 : i > 21 ? 2 : 1 : 12;
        if (length > 6) {
            return null;
        }
        if (length > iArr.length) {
            length = iArr.length;
        }
        return this.mContext.getDrawable(iArr[length - 1]);
    }

    private void addViewToWindow() {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.gravity = 80;
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -2;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = 8;
        }
        if (!isRadarShowCondition() || isDistance()) {
            dismissView();
        } else {
            if (this.isShowing) {
                return;
            }
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
    }

    private boolean isDistance() {
        return this.front_radar_left_mid.getDrawable() == null && this.front_radar_left.getDrawable() == null && this.front_radar_right_mid.getDrawable() == null && this.front_radar_right.getDrawable() == null && this.rear_radar_left_mid.getDrawable() == null && this.rear_radar_left.getDrawable() == null && this.rear_radar_right_mid.getDrawable() == null && this.rear_radar_right.getDrawable() == null;
    }

    private boolean isRadarShowCondition() {
        return (GeneralDzData.handBrake || GeneralDzData.gears || GeneralDzData.speed > 20) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        View view;
        try {
            WindowManager windowManager = this.mWindowManager;
            if (windowManager == null || (view = this.mView) == null) {
                return;
            }
            windowManager.removeView(view);
            this.isShowing = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showRadar() {
        this.radarLayout.setVisibility(0);
    }

    public void goneRadar() {
        this.radarLayout.setVisibility(8);
    }
}
