package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.utils._283_SharedPreferencesUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class RadarView extends RelativeLayout implements View.OnClickListener {
    public static final String SHARE_SYS_REVERSE = "sys.Reverse";
    private static final int TIME = 3000;
    private RelativeLayout LeftRelative;
    private BtnView btn_margin;
    private BtnView btn_overlook;
    private BtnView btn_radar_bg;
    private BtnView btn_standard;
    private BtnView btn_wide;
    private ImageView front_radar_left;
    private int[] front_radar_leftImages;
    private ImageView front_radar_left_mid;
    private int[] front_radar_left_midImages;
    private ImageView front_radar_right;
    private int[] front_radar_rightImages;
    private ImageView front_radar_right_mid;
    private int[] front_radar_right_midImages;
    private ImageView hideCarRadar;
    private boolean isBackCar;
    private boolean isHideLeftRadar;
    private boolean isShowing;
    private boolean isTransparency;
    private ImageView iv_radar_stop;
    private ImageView iv_radar_voice;
    private Runnable justShowRadarRunnable;
    private ImageView leftClose;
    private RelativeLayout leftRadarView;
    private ImageView left_radar_front;
    private int[] left_radar_frontImages;
    private ImageView left_radar_mid_front;
    private int[] left_radar_mid_frontImages;
    private ImageView left_radar_mid_rear;
    private int[] left_radar_mid_rearImages;
    private ImageView left_radar_rear;
    private int[] left_radar_rearImages;
    private LinearLayout linearBottomRadar;
    private Context mContext;
    private SharedPreferences.Editor mEditor;
    private Handler mHandler;
    private WindowManager.LayoutParams mLayoutParams;
    private RadarRegulationView mRadarRegulationView;
    int mReverseStatus;
    private Runnable mRunnable;
    private SharedPreferences mSharedPreferences;
    private View mView;
    private WindowManager mWindowManager;
    private ImageView rear_radar_left;
    private int[] rear_radar_leftImages;
    private ImageView rear_radar_left_auxiliary;
    private int[] rear_radar_left_auxiliaryImages;
    private ImageView rear_radar_left_mid;
    private int[] rear_radar_left_midImages;
    private ImageView rear_radar_right;
    private int[] rear_radar_rightImages;
    private ImageView rear_radar_right_auxiliary;
    private int[] rear_radar_right_auxiliaryImages;
    private ImageView rear_radar_right_mid;
    private int[] rear_radar_right_midImages;
    private RelativeLayout relativeLayout;
    private BtnView rightClose;
    private LinearLayout rightLinear;
    private ImageView right_radar_front;
    private int[] right_radar_frontImages;
    private ImageView right_radar_mid_front;
    private int[] right_radar_mid_frontImages;
    private ImageView right_radar_mid_rear;
    private int[] right_radar_mid_rearImages;
    private ImageView right_radar_rear;
    private int[] right_radar_rearImages;
    private ImageView showCarRadar;
    private ImageView showRightBar;

    public RadarView(Context context) {
        this(context, null);
        this.isBackCar = false;
        this.rightLinear.setVisibility(8);
        this.showRightBar.setVisibility(8);
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.isBackCar = true;
    }

    public RadarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isShowing = false;
        this.isTransparency = false;
        this.isBackCar = false;
        this.isHideLeftRadar = false;
        this.front_radar_left_midImages = new int[]{R.drawable.___283___dz_radar_left_1_1, R.drawable.___283___dz_radar_left_1_2, R.drawable.___283___dz_radar_left_1_3, R.drawable.___283___dz_radar_left_1_4, R.drawable.___283___dz_radar_left_1_5, R.drawable.___283___dz_radar_left_1_6, R.drawable.___283___dz_radar_left_1_7, R.drawable.___283___dz_radar_left_1_8};
        this.front_radar_leftImages = new int[]{R.drawable.___283___dz_radar_left_2_1, R.drawable.___283___dz_radar_left_2_2, R.drawable.___283___dz_radar_left_2_3, R.drawable.___283___dz_radar_left_2_4, R.drawable.___283___dz_radar_left_2_5, R.drawable.___283___dz_radar_left_2_6};
        this.left_radar_frontImages = new int[]{R.drawable.___283___dz_radar_left_3_1, R.drawable.___283___dz_radar_left_3_2, R.drawable.___283___dz_radar_left_3_3, R.drawable.___283___dz_radar_left_3_4, R.drawable.___283___dz_radar_left_3_5, R.drawable.___283___dz_radar_left_3_6};
        this.left_radar_mid_frontImages = new int[]{R.drawable.___283___dz_radar_left_4_1, R.drawable.___283___dz_radar_left_4_2, R.drawable.___283___dz_radar_left_4_3, R.drawable.___283___dz_radar_left_4_4, R.drawable.___283___dz_radar_left_4_5, R.drawable.___283___dz_radar_left_4_6};
        this.left_radar_mid_rearImages = new int[]{R.drawable.___283___dz_radar_left_5_1, R.drawable.___283___dz_radar_left_5_2, R.drawable.___283___dz_radar_left_5_3, R.drawable.___283___dz_radar_left_5_4, R.drawable.___283___dz_radar_left_5_5, R.drawable.___283___dz_radar_left_5_6};
        this.left_radar_rearImages = new int[]{R.drawable.___283___dz_radar_left_6_1, R.drawable.___283___dz_radar_left_6_2, R.drawable.___283___dz_radar_left_6_3, R.drawable.___283___dz_radar_left_6_4, R.drawable.___283___dz_radar_left_6_5, R.drawable.___283___dz_radar_left_6_6};
        this.rear_radar_leftImages = new int[]{R.drawable.___283___dz_radar_left_7_1, R.drawable.___283___dz_radar_left_7_2, R.drawable.___283___dz_radar_left_7_3, R.drawable.___283___dz_radar_left_7_4, R.drawable.___283___dz_radar_left_7_5, R.drawable.___283___dz_radar_left_7_6};
        this.rear_radar_left_midImages = new int[]{R.drawable.___283___dz_radar_left_8_1, R.drawable.___283___dz_radar_left_8_2, R.drawable.___283___dz_radar_left_8_3, R.drawable.___283___dz_radar_left_8_4, R.drawable.___283___dz_radar_left_8_5, R.drawable.___283___dz_radar_left_8_6, R.drawable.___283___dz_radar_left_8_7, R.drawable.___283___dz_radar_left_8_8, R.drawable.___283___dz_radar_left_8_9, R.drawable.___283___dz_radar_left_8_10, R.drawable.___283___dz_radar_left_8_11};
        this.rear_radar_left_auxiliaryImages = new int[]{R.drawable.dz_radar_left_auxiliary_gray, R.drawable.dz_radar_left_auxiliary_orange, R.drawable.dz_radar_left_auxiliary_red};
        this.front_radar_right_midImages = new int[]{R.drawable.___283___dz_radar_right_1_1, R.drawable.___283___dz_radar_right_1_2, R.drawable.___283___dz_radar_right_1_3, R.drawable.___283___dz_radar_right_1_4, R.drawable.___283___dz_radar_right_1_5, R.drawable.___283___dz_radar_right_1_6, R.drawable.___283___dz_radar_right_1_7, R.drawable.___283___dz_radar_right_1_8};
        this.front_radar_rightImages = new int[]{R.drawable.___283___dz_radar_right_2_1, R.drawable.___283___dz_radar_right_2_2, R.drawable.___283___dz_radar_right_2_3, R.drawable.___283___dz_radar_right_2_4, R.drawable.___283___dz_radar_right_2_5, R.drawable.___283___dz_radar_right_2_6};
        this.right_radar_frontImages = new int[]{R.drawable.___283___dz_radar_right_3_1, R.drawable.___283___dz_radar_right_3_2, R.drawable.___283___dz_radar_right_3_3, R.drawable.___283___dz_radar_right_3_4, R.drawable.___283___dz_radar_right_3_5, R.drawable.___283___dz_radar_right_3_6};
        this.right_radar_mid_frontImages = new int[]{R.drawable.___283___dz_radar_right_4_1, R.drawable.___283___dz_radar_right_4_2, R.drawable.___283___dz_radar_right_4_3, R.drawable.___283___dz_radar_right_4_4, R.drawable.___283___dz_radar_right_4_5, R.drawable.___283___dz_radar_right_4_6};
        this.right_radar_mid_rearImages = new int[]{R.drawable.___283___dz_radar_right_5_1, R.drawable.___283___dz_radar_right_5_2, R.drawable.___283___dz_radar_right_5_3, R.drawable.___283___dz_radar_right_5_4, R.drawable.___283___dz_radar_right_5_5, R.drawable.___283___dz_radar_right_5_6};
        this.right_radar_rearImages = new int[]{R.drawable.___283___dz_radar_right_6_1, R.drawable.___283___dz_radar_right_6_2, R.drawable.___283___dz_radar_right_6_3, R.drawable.___283___dz_radar_right_6_4, R.drawable.___283___dz_radar_right_6_5, R.drawable.___283___dz_radar_right_6_6};
        this.rear_radar_rightImages = new int[]{R.drawable.___283___dz_radar_right_7_1, R.drawable.___283___dz_radar_right_7_2, R.drawable.___283___dz_radar_right_7_3, R.drawable.___283___dz_radar_right_7_4, R.drawable.___283___dz_radar_right_7_5, R.drawable.___283___dz_radar_right_7_6};
        this.rear_radar_right_midImages = new int[]{R.drawable.___283___dz_radar_right_8_1, R.drawable.___283___dz_radar_right_8_2, R.drawable.___283___dz_radar_right_8_3, R.drawable.___283___dz_radar_right_8_4, R.drawable.___283___dz_radar_right_8_5, R.drawable.___283___dz_radar_right_8_6, R.drawable.___283___dz_radar_right_8_7, R.drawable.___283___dz_radar_right_8_8, R.drawable.___283___dz_radar_right_8_9, R.drawable.___283___dz_radar_right_8_10, R.drawable.___283___dz_radar_right_8_11};
        this.rear_radar_right_auxiliaryImages = new int[]{R.drawable.dz_radar_right_auxiliary_gray, R.drawable.dz_radar_right_auxiliary_orange, R.drawable.dz_radar_right_auxiliary_red};
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mReverseStatus = 0;
        this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarView.2
            @Override // java.lang.Runnable
            public void run() {
                RadarView.this.dismissView();
            }
        };
        this.justShowRadarRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarView.3
            @Override // java.lang.Runnable
            public void run() {
                RadarView.this.justShowRadar();
            }
        };
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout._283_radar_view, (ViewGroup) this, true);
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(_283_SharedPreferencesUtils.MAIN_NAME, 0);
        this.mSharedPreferences = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
        initView();
    }

    private void initView() {
        this.showRightBar = (ImageView) this.mView.findViewById(R.id.showRightBar);
        this.hideCarRadar = (ImageView) this.mView.findViewById(R.id.hideCarRadar);
        this.showCarRadar = (ImageView) this.mView.findViewById(R.id.showCarRadar);
        this.front_radar_left_mid = (ImageView) this.mView.findViewById(R.id.front_radar_left_mid);
        this.front_radar_left = (ImageView) this.mView.findViewById(R.id.front_radar_left);
        this.left_radar_front = (ImageView) this.mView.findViewById(R.id.left_radar_front);
        this.left_radar_mid_front = (ImageView) this.mView.findViewById(R.id.left_radar_mid_front);
        this.left_radar_mid_rear = (ImageView) this.mView.findViewById(R.id.left_radar_mid_rear);
        this.left_radar_rear = (ImageView) this.mView.findViewById(R.id.left_radar_rear);
        this.rear_radar_left = (ImageView) this.mView.findViewById(R.id.rear_radar_left);
        this.rear_radar_left_auxiliary = (ImageView) this.mView.findViewById(R.id.rear_radar_left_auxiliary);
        this.rear_radar_left_mid = (ImageView) this.mView.findViewById(R.id.rear_radar_left_mid);
        this.front_radar_right_mid = (ImageView) this.mView.findViewById(R.id.front_radar_right_mid);
        this.front_radar_right = (ImageView) this.mView.findViewById(R.id.front_radar_right);
        this.right_radar_front = (ImageView) this.mView.findViewById(R.id.right_radar_front);
        this.right_radar_mid_front = (ImageView) this.mView.findViewById(R.id.right_radar_mid_front);
        this.right_radar_mid_rear = (ImageView) this.mView.findViewById(R.id.right_radar_mid_rear);
        this.right_radar_rear = (ImageView) this.mView.findViewById(R.id.right_radar_rear);
        this.rear_radar_right = (ImageView) this.mView.findViewById(R.id.rear_radar_right);
        this.rear_radar_right_auxiliary = (ImageView) this.mView.findViewById(R.id.rear_radar_right_auxiliary);
        this.rear_radar_right_mid = (ImageView) this.mView.findViewById(R.id.rear_radar_right_mid);
        this.iv_radar_stop = (ImageView) this.mView.findViewById(R.id.iv_radar_stop);
        this.iv_radar_voice = (ImageView) this.mView.findViewById(R.id.iv_radar_voice);
        this.relativeLayout = (RelativeLayout) this.mView.findViewById(R.id.relativeLayout);
        this.linearBottomRadar = (LinearLayout) this.mView.findViewById(R.id.linearBottomRadar);
        this.LeftRelative = (RelativeLayout) this.mView.findViewById(R.id.LeftRelative);
        this.leftRadarView = (RelativeLayout) this.mView.findViewById(R.id.leftRadarView);
        this.rightLinear = (LinearLayout) this.mView.findViewById(R.id.rightLinear);
        this.leftClose = (ImageView) this.mView.findViewById(R.id.leftClose);
        this.rightClose = (BtnView) this.mView.findViewById(R.id.rightClose);
        this.btn_radar_bg = (BtnView) this.mView.findViewById(R.id.btn_radar_bg);
        this.btn_overlook = (BtnView) this.mView.findViewById(R.id.btn_overlook);
        this.btn_margin = (BtnView) this.mView.findViewById(R.id.btn_margin);
        this.btn_standard = (BtnView) this.mView.findViewById(R.id.btn_standard);
        this.btn_wide = (BtnView) this.mView.findViewById(R.id.btn_wide);
        this.mRadarRegulationView = (RadarRegulationView) this.mView.findViewById(R.id.radarRegulationView);
        this.btn_radar_bg.setOnClickListener(this);
        this.btn_overlook.setOnClickListener(this);
        this.btn_margin.setOnClickListener(this);
        this.btn_standard.setOnClickListener(this);
        this.btn_wide.setOnClickListener(this);
        this.leftClose.setOnClickListener(this);
        this.rightClose.setOnClickListener(this);
        this.iv_radar_stop.setOnClickListener(this);
        this.iv_radar_voice.setOnClickListener(this);
        this.showCarRadar.setOnClickListener(this);
        this.hideCarRadar.setOnClickListener(this);
        this.showRightBar.setOnClickListener(this);
        showAllView();
        boolean z = this.mSharedPreferences.getBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_LEFT, true);
        this.LeftRelative.setVisibility(z ? 0 : 8);
        this.leftRadarView.setVisibility(z ? 0 : 8);
        this.showCarRadar.setVisibility(z ? 8 : 0);
        boolean z2 = this.mSharedPreferences.getBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_RIGHT, true);
        this.rightLinear.setVisibility(z2 ? 0 : 8);
        this.showRightBar.setVisibility(z2 ? 8 : 0);
    }

    public void refreshUi() {
        updateUi();
        if (getReverseState()) {
            return;
        }
        addViewToWindow();
    }

    public boolean getReverseState() {
        int iRegisterShareIntListener = ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", new IShareIntListener() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarView.1
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

    public void updateUi() {
        this.front_radar_left_mid.setImageDrawable(getRadarImageResource(this.front_radar_left_midImages, GeneralDzData.radar_front_ml));
        this.front_radar_left.setImageDrawable(getRadarImageResource(this.front_radar_leftImages, GeneralDzData.radar_front_l));
        this.front_radar_right_mid.setImageDrawable(getRadarImageResource(this.front_radar_right_midImages, GeneralDzData.radar_front_mr));
        this.front_radar_right.setImageDrawable(getRadarImageResource(this.front_radar_rightImages, GeneralDzData.radar_front_r));
        this.rear_radar_left_mid.setImageDrawable(getRadarImageResource(this.rear_radar_left_midImages, GeneralDzData.radar_rear_ml));
        this.rear_radar_left.setImageDrawable(getRadarImageResource(this.rear_radar_leftImages, GeneralDzData.radar_rear_l));
        this.rear_radar_right_mid.setImageDrawable(getRadarImageResource(this.rear_radar_right_midImages, GeneralDzData.radar_rear_mr));
        this.rear_radar_right.setImageDrawable(getRadarImageResource(this.rear_radar_rightImages, GeneralDzData.radar_rear_r));
        this.right_radar_front.setImageDrawable(getRadarImageResource(this.right_radar_frontImages, GeneralDzData.radar_right_f));
        this.right_radar_mid_front.setImageDrawable(getRadarImageResource(this.right_radar_mid_frontImages, GeneralDzData.radar_right_mf));
        this.right_radar_mid_rear.setImageDrawable(getRadarImageResource(this.right_radar_mid_rearImages, GeneralDzData.radar_right_mr));
        this.right_radar_rear.setImageDrawable(getRadarImageResource(this.right_radar_rearImages, GeneralDzData.radar_right_r));
        this.left_radar_front.setImageDrawable(getRadarImageResource(this.left_radar_frontImages, GeneralDzData.radar_left_f));
        this.left_radar_mid_front.setImageDrawable(getRadarImageResource(this.left_radar_mid_frontImages, GeneralDzData.radar_left_mf));
        this.left_radar_mid_rear.setImageDrawable(getRadarImageResource(this.left_radar_mid_rearImages, GeneralDzData.radar_left_mr));
        this.left_radar_rear.setImageDrawable(getRadarImageResource(this.left_radar_rearImages, GeneralDzData.radar_left_r));
        this.btn_overlook.setSelect(GeneralDzData.camera_overlook);
        this.btn_standard.setSelect(GeneralDzData.camera_standard);
        this.btn_wide.setSelect(GeneralDzData.camera_wide);
        this.btn_margin.setSelect(GeneralDzData.camera_margin);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1100lambda$updateUi$0$comhzbhdcanbuscar_cus_283viewRadarView();
            }
        });
        MyLog.temporaryTracking("雷达数据初始化完成");
    }

    /* renamed from: lambda$updateUi$0$com-hzbhd-canbus-car_cus-_283-view-RadarView, reason: not valid java name */
    /* synthetic */ void m1100lambda$updateUi$0$comhzbhdcanbuscar_cus_283viewRadarView() {
        boolean z = false;
        if (GeneralDzData.handBrake) {
            this.iv_radar_stop.setEnabled(false);
            this.iv_radar_stop.setSelected(false);
            this.iv_radar_voice.setEnabled(false);
            this.iv_radar_voice.setSelected(false);
            return;
        }
        this.iv_radar_stop.setEnabled(true);
        ImageView imageView = this.iv_radar_stop;
        if (GeneralDzData.parking_function && GeneralDzData.parking_switch) {
            z = true;
        }
        imageView.setSelected(z);
        this.iv_radar_voice.setEnabled(true);
        this.iv_radar_voice.setSelected(!GeneralDzData.parking_radar_voice);
    }

    public void goneRadar() {
        this.LeftRelative.setVisibility(8);
        this.leftRadarView.setVisibility(8);
        this.showCarRadar.setVisibility(8);
    }

    public void showRadar() {
        boolean z = this.mSharedPreferences.getBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_LEFT, true);
        this.LeftRelative.setVisibility(z ? 0 : 8);
        this.leftRadarView.setVisibility(z ? 0 : 8);
        this.showCarRadar.setVisibility(z ? 8 : 0);
    }

    private Drawable getRadarImageResource(int[] iArr, int i) {
        int length = (i < 0 || i > 15) ? (i < 16 || i > 30) ? (i < 31 || i > 45) ? (i < 46 || i > 60) ? (i < 61 || i > 75) ? (i < 76 || i > 90) ? (i < 91 || i > 105) ? (i < 106 || i > 120) ? (i < 121 || i > 135) ? (i < 136 || i > 150) ? (i < 151 || i > 165) ? 12 : 11 : 10 : 9 : 8 : 7 : 6 : 5 : 4 : 3 : 2 : 1;
        if (length > 11) {
            return null;
        }
        if (length > iArr.length) {
            length = iArr.length;
        }
        return this.mContext.getDrawable(iArr[length - 1]);
    }

    private void addViewToWindow() {
        Context context;
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2038;
            this.mLayoutParams.gravity = 80;
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -2;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = 8;
        }
        if (!isRadarShowCondition() || isRadarDistance()) {
            dismissView();
        } else {
            if (this.isShowing || (context = this.mContext) == null || !SharePreUtil.getBoolValue(context, _283_SharedPreferencesUtils.KEY_DIALOG_RADAR_SWITCH, false)) {
                return;
            }
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
    }

    private boolean isRadarShowCondition() {
        return (GeneralDzData.handBrake || GeneralDzData.gears || GeneralDzData.speed > 20) ? false : true;
    }

    private boolean isRadarDistance() {
        return GeneralDzData.radar_left_r >= 165 && GeneralDzData.radar_left_mf >= 165 && GeneralDzData.radar_left_mr >= 165 && GeneralDzData.radar_left_f >= 165 && GeneralDzData.radar_right_f >= 165 && GeneralDzData.radar_right_mf >= 165 && GeneralDzData.radar_right_mr >= 165 && GeneralDzData.radar_right_r >= 165 && GeneralDzData.radar_rear_ml >= 165 && GeneralDzData.radar_rear_l >= 165 && GeneralDzData.radar_rear_mr >= 165 && GeneralDzData.radar_rear_r >= 165 && GeneralDzData.radar_front_ml >= 165 && GeneralDzData.radar_front_l >= 165 && GeneralDzData.radar_front_mr >= 165 && GeneralDzData.radar_front_r >= 165;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        View view;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (view = this.mView) == null || !this.isShowing) {
            return;
        }
        try {
            this.isShowing = false;
            windowManager.removeView(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRadarBackground(Drawable drawable) {
        this.relativeLayout.setBackground(drawable);
    }

    public void setRadarBackgroundColor(int i) {
        this.relativeLayout.setBackgroundColor(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void justShowRadar() {
        this.LeftRelative.setVisibility(8);
        this.rightLinear.setVisibility(8);
    }

    private void showAllView() {
        if (!this.isHideLeftRadar) {
            this.LeftRelative.setVisibility(0);
        }
        if (this.isBackCar) {
            this.rightLinear.setVisibility(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_margin /* 2131362031 */:
                MessageSender.sendMsg(new byte[]{22, -14, 9, -1});
                break;
            case R.id.btn_overlook /* 2131362037 */:
                MessageSender.sendMsg(new byte[]{22, -14, 3, -1});
                break;
            case R.id.btn_radar_bg /* 2131362043 */:
                if (this.isTransparency) {
                    this.mRadarRegulationView.setVisibility(8);
                } else {
                    this.mRadarRegulationView.setVisibility(0);
                }
                this.isTransparency = !this.isTransparency;
                break;
            case R.id.btn_standard /* 2131362055 */:
                MessageSender.sendMsg(new byte[]{22, -14, 2, -1});
                break;
            case R.id.btn_wide /* 2131362066 */:
                MessageSender.sendMsg(new byte[]{22, -14, 1, -1});
                break;
            case R.id.hideCarRadar /* 2131362369 */:
                this.isHideLeftRadar = true;
                this.LeftRelative.setVisibility(8);
                this.showCarRadar.setVisibility(0);
                this.leftRadarView.setVisibility(8);
                this.mEditor.putBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_LEFT, false);
                this.mEditor.apply();
                break;
            case R.id.iv_radar_stop /* 2131362609 */:
                if (GeneralDzData.parking_switch) {
                    sendRadarStop(true);
                    break;
                } else {
                    sendRadarStop(false);
                    break;
                }
            case R.id.iv_radar_voice /* 2131362610 */:
                MessageSender.sendMsg((byte) 74, (byte) 8, !GeneralDzData.parking_radar_voice);
                break;
            case R.id.leftClose /* 2131362720 */:
                this.LeftRelative.setVisibility(8);
                break;
            case R.id.rightClose /* 2131363148 */:
                this.rightLinear.setVisibility(8);
                this.showRightBar.setVisibility(0);
                this.mEditor.putBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_RIGHT, false);
                this.mEditor.apply();
                break;
            case R.id.showCarRadar /* 2131363327 */:
                this.isHideLeftRadar = false;
                this.showCarRadar.setVisibility(8);
                this.leftRadarView.setVisibility(0);
                this.LeftRelative.setVisibility(0);
                this.mEditor.putBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_LEFT, true);
                this.mEditor.apply();
                break;
            case R.id.showRightBar /* 2131363330 */:
                this.rightLinear.setVisibility(0);
                this.showRightBar.setVisibility(8);
                this.mEditor.putBoolean(_283_SharedPreferencesUtils.SAVE_RADAR_RIGHT, true);
                this.mEditor.apply();
                break;
        }
    }

    private void sendRadarStop(final boolean z) {
        if (GeneralDzData.parking_function) {
            MessageSender.sendMsg((byte) 74, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, z);
        } else {
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.RadarView$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    RadarView.lambda$sendRadarStop$1(z);
                }
            }).start();
        }
    }

    static /* synthetic */ void lambda$sendRadarStop$1(boolean z) throws InterruptedException {
        MessageSender.sendMsg((byte) 74, (byte) 11, z);
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageSender.sendMsg((byte) 74, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, z);
    }
}
