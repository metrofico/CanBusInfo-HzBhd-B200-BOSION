package com.hzbhd.canbus.car_cus._299.air;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.SystemUtil;

/* loaded from: classes2.dex */
public class AirPopupView {
    private static final int WHAT_HIDE_VIEW = 1;
    private static final int WHAT_SHOW_VIEW = 0;
    private ImageView iv_ac;
    private ImageView iv_wind;
    private Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private WindowManager mWindowManager;
    private TextView tv_left_temp;
    private TextView tv_right_temp;
    private TextView tv_wind;
    private boolean isShowing = false;
    private Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._299.air.AirPopupView$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.dismissView();
        }
    };

    public AirPopupView(Context context) {
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._299_air_popup_view, (ViewGroup) null);
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        initView();
    }

    private void initView() {
        this.tv_left_temp = (TextView) this.mView.findViewById(R.id.tv_left_temp);
        this.tv_right_temp = (TextView) this.mView.findViewById(R.id.tv_right_temp);
        this.tv_wind = (TextView) this.mView.findViewById(R.id.tv_wind);
        this.iv_ac = (ImageView) this.mView.findViewById(R.id.iv_ac);
        this.iv_wind = (ImageView) this.mView.findViewById(R.id.iv_wind);
    }

    public void refreshUi() {
        this.tv_left_temp.setText(GeneralAirData.front_left_temperature);
        this.tv_right_temp.setText(GeneralAirData.front_right_temperature);
        this.tv_wind.setText(String.valueOf(GeneralAirData.front_wind_level));
        this.iv_ac.setImageResource(GeneralAirData.ac ? R.drawable._c6_btm_acon : R.drawable._c6_btm_acoff);
        this.iv_wind.setImageResource(getWindRes());
        if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
            return;
        }
        addViewToWindow();
    }

    private int getWindRes() {
        boolean z = GeneralAirData.front_left_blow_window;
        boolean z2 = GeneralAirData.front_left_blow_head;
        boolean z3 = GeneralAirData.front_left_blow_foot;
        return (z && z2 && z3) ? R.drawable._c6_buttom_bar_front_demist_front_down_wind : (z && z2) ? R.drawable._c6_buttom_bar_front_demist_front_wind : (z && z3) ? R.drawable._c6_buttom_bar_front_demist_down_wind : (z2 && z3) ? R.drawable._c6_buttom_bar_front_down_wind : z ? R.drawable._c6_buttom_bar_front_demist : z2 ? R.drawable._c6_buttom_bar_front_wind : z3 ? R.drawable._c6_buttom_bar_down_wind : R.drawable._c6_buttom_bar_wind_auto;
    }

    private void addViewToWindow() {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.gravity = 80;
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = (int) this.mContext.getResources().getDimension(R.dimen.dp60);
            this.mLayoutParams.format = 1;
            this.mLayoutParams.flags = 8;
        }
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mView.removeCallbacks(this.mRunnable);
        this.mView.postDelayed(this.mRunnable, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        WindowManager windowManager;
        View view;
        if (!this.isShowing || (windowManager = this.mWindowManager) == null || (view = this.mView) == null) {
            return;
        }
        windowManager.removeView(view);
        this.isShowing = false;
    }
}
