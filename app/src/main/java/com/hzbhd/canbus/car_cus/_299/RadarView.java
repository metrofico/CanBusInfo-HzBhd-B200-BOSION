package com.hzbhd.canbus.car_cus._299;

import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.common.settings.constant.BodaSysContant;

/* loaded from: classes2.dex */
public class RadarView extends RelativeLayout {
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
    private Runnable mRunnable;
    private View mView;
    private WindowManager mWindowManager;
    private ImageView rear_radar_left;
    private int[] rear_radar_leftImages;
    private ImageView rear_radar_left_mid;
    private int[] rear_radar_left_midImages;
    private ImageView rear_radar_right;
    private int[] rear_radar_rightImages;
    private ImageView rear_radar_right_mid;
    private int[] rear_radar_right_midImages;
    private RelativeLayout relativeLayout;

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isShowing = false;
        this.front_radar_leftImages = new int[]{R.drawable.dz_baom_leida_img_shang1_01, R.drawable.dz_baom_leida_img_shang1_02, R.drawable.dz_baom_leida_img_shang1_03, R.drawable.dz_baom_leida_img_shang1_04, R.drawable.dz_baom_leida_img_shang1_05, R.drawable.dz_baom_leida_img_shang1_06};
        this.front_radar_left_midImages = new int[]{R.drawable.dz_baom_leida_img_shang2_01, R.drawable.dz_baom_leida_img_shang2_02, R.drawable.dz_baom_leida_img_shang2_03, R.drawable.dz_baom_leida_img_shang2_04, R.drawable.dz_baom_leida_img_shang2_05, R.drawable.dz_baom_leida_img_shang2_06};
        this.front_radar_right_midImages = new int[]{R.drawable.dz_baom_leida_img_shang3_01, R.drawable.dz_baom_leida_img_shang3_02, R.drawable.dz_baom_leida_img_shang3_03, R.drawable.dz_baom_leida_img_shang3_04, R.drawable.dz_baom_leida_img_shang3_05, R.drawable.dz_baom_leida_img_shang3_06};
        this.front_radar_rightImages = new int[]{R.drawable.dz_baom_leida_img_shang4_01, R.drawable.dz_baom_leida_img_shang4_02, R.drawable.dz_baom_leida_img_shang4_03, R.drawable.dz_baom_leida_img_shang4_04, R.drawable.dz_baom_leida_img_shang4_05, R.drawable.dz_baom_leida_img_shang4_06};
        this.rear_radar_leftImages = new int[]{R.drawable.dz_baom_leida_img_xia1_01, R.drawable.dz_baom_leida_img_xia1_02, R.drawable.dz_baom_leida_img_xia1_03, R.drawable.dz_baom_leida_img_xia1_04, R.drawable.dz_baom_leida_img_xia1_05, R.drawable.dz_baom_leida_img_xia1_06};
        this.rear_radar_left_midImages = new int[]{R.drawable.dz_baom_leida_img_xia2_01, R.drawable.dz_baom_leida_img_xia2_02, R.drawable.dz_baom_leida_img_xia2_03, R.drawable.dz_baom_leida_img_xia2_04, R.drawable.dz_baom_leida_img_xia2_05, R.drawable.dz_baom_leida_img_xia2_06};
        this.rear_radar_right_midImages = new int[]{R.drawable.dz_baom_leida_img_xia3_01, R.drawable.dz_baom_leida_img_xia3_02, R.drawable.dz_baom_leida_img_xia3_03, R.drawable.dz_baom_leida_img_xia3_04, R.drawable.dz_baom_leida_img_xia3_05, R.drawable.dz_baom_leida_img_xia3_06};
        this.rear_radar_rightImages = new int[]{R.drawable.dz_baom_leida_img_xia4_01, R.drawable.dz_baom_leida_img_xia4_02, R.drawable.dz_baom_leida_img_xia4_03, R.drawable.dz_baom_leida_img_xia4_04, R.drawable.dz_baom_leida_img_xia4_05, R.drawable.dz_baom_leida_img_xia4_06};
        this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._299.RadarView.1
            @Override // java.lang.Runnable
            public void run() {
                RadarView.this.dismissView();
            }
        };
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout._299_radar_view, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.front_radar_left = (ImageView) this.mView.findViewById(R.id.front_radar_left);
        this.front_radar_left_mid = (ImageView) this.mView.findViewById(R.id.front_radar_left_mid);
        this.front_radar_right_mid = (ImageView) this.mView.findViewById(R.id.front_radar_right_mid);
        this.front_radar_right = (ImageView) this.mView.findViewById(R.id.front_radar_right);
        this.rear_radar_left = (ImageView) this.mView.findViewById(R.id.rear_radar_left);
        this.rear_radar_left_mid = (ImageView) this.mView.findViewById(R.id.rear_radar_left_mid);
        this.rear_radar_right_mid = (ImageView) this.mView.findViewById(R.id.rear_radar_right_mid);
        this.rear_radar_right = (ImageView) this.mView.findViewById(R.id.rear_radar_right);
        this.relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.relativeLayout);
    }

    public void refreshUi() {
        this.front_radar_left.setImageResource(getRadarRes(GeneralDzData.radar_front_l, this.front_radar_leftImages));
        this.front_radar_left_mid.setImageResource(getRadarRes(GeneralDzData.radar_front_ml, this.front_radar_left_midImages));
        this.front_radar_right_mid.setImageResource(getRadarRes(GeneralDzData.radar_front_mr, this.front_radar_right_midImages));
        this.front_radar_right.setImageResource(getRadarRes(GeneralDzData.radar_front_r, this.front_radar_rightImages));
        this.rear_radar_left.setImageResource(getRadarRes(GeneralDzData.radar_rear_l, this.rear_radar_leftImages));
        this.rear_radar_left_mid.setImageResource(getRadarRes(GeneralDzData.radar_rear_ml, this.rear_radar_left_midImages));
        this.rear_radar_right_mid.setImageResource(getRadarRes(GeneralDzData.radar_rear_mr, this.rear_radar_right_midImages));
        this.rear_radar_right.setImageResource(getRadarRes(GeneralDzData.radar_rear_r, this.rear_radar_rightImages));
    }

    public void refreshUiOut() throws Settings.SettingNotFoundException {
        addViewToWindow();
        refreshUi();
    }

    private int getRadarRes(int i, int[] iArr) {
        if (i <= 0 || i > 7) {
            return 0;
        }
        if (i == 7) {
            return iArr[iArr.length - 1];
        }
        return iArr[i - 1];
    }

    private void addViewToWindow() throws Settings.SettingNotFoundException {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -2;
            this.mLayoutParams.format = 1;
        }
        int i = 0;
        try {
            i = Settings.System.getInt(this.mContext.getContentResolver(), BodaSysContant.Can.RadarDisp);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (!this.isShowing && i == 1) {
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mView.removeCallbacks(this.mRunnable);
        this.mView.postDelayed(this.mRunnable, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        View view;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (view = this.mView) == null) {
            return;
        }
        windowManager.removeView(view);
        this.isShowing = false;
    }

    public void setBackgroundLayoutColor(int i) {
        this.relativeLayout.setBackgroundColor(i);
    }
}
