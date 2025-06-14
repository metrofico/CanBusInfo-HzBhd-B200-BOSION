package com.hzbhd.canbus.car_cus._291;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.PA6Utils;
import com.hzbhd.canbus.car_cus._283.view.BtnView;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.SystemUtil;

/* loaded from: classes2.dex */
public class AirSeatView {
    private BtnView btn_left_front_hot_1;
    private BtnView btn_left_front_hot_2;
    private BtnView btn_left_front_hot_3;
    private BtnView btn_right_front_hot_1;
    private BtnView btn_right_front_hot_2;
    private BtnView btn_right_front_hot_3;
    private Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private WindowManager mWindowManager;
    private TextView tv_left_front_hot;
    private TextView tv_right_front_hot;
    private boolean isShowing = false;
    private Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.AirSeatView.1
        @Override // java.lang.Runnable
        public void run() {
            AirSeatView.this.dismissView();
        }
    };

    public AirSeatView(Context context) {
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._291_air_seat_view, (ViewGroup) null);
        this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        initView();
    }

    private void initView() {
        this.tv_left_front_hot = (TextView) this.mView.findViewById(R.id.tv_left_front_hot);
        this.tv_right_front_hot = (TextView) this.mView.findViewById(R.id.tv_right_front_hot);
        this.btn_left_front_hot_1 = (BtnView) this.mView.findViewById(R.id.btn_left_front_hot_1);
        this.btn_left_front_hot_2 = (BtnView) this.mView.findViewById(R.id.btn_left_front_hot_2);
        this.btn_left_front_hot_3 = (BtnView) this.mView.findViewById(R.id.btn_left_front_hot_3);
        this.btn_right_front_hot_1 = (BtnView) this.mView.findViewById(R.id.btn_right_front_hot_1);
        this.btn_right_front_hot_2 = (BtnView) this.mView.findViewById(R.id.btn_right_front_hot_2);
        this.btn_right_front_hot_3 = (BtnView) this.mView.findViewById(R.id.btn_right_front_hot_3);
    }

    public void refreshUi() {
        setGrade(GeneralAirData.front_left_seat_heat_level, this.tv_left_front_hot, this.btn_left_front_hot_1, this.btn_left_front_hot_2, this.btn_left_front_hot_3);
        setGrade(GeneralAirData.front_right_seat_heat_level, this.tv_right_front_hot, this.btn_right_front_hot_1, this.btn_right_front_hot_2, this.btn_right_front_hot_3);
        if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName()) || !PA6Utils.getAirSeatShow()) {
            return;
        }
        addViewToWindow();
    }

    private void setGrade(int i, TextView textView, BtnView btnView, BtnView btnView2, BtnView btnView3) {
        if (i == 0) {
            textView.setText(R.string._283_OFF);
            btnView.setSelect(false);
            btnView2.setSelect(false);
            btnView3.setSelect(false);
            return;
        }
        if (i == 1) {
            textView.setText(R.string._283_ON);
            btnView.setSelect(true);
            btnView2.setSelect(false);
            btnView3.setSelect(false);
            return;
        }
        if (i == 2) {
            textView.setText(R.string._283_ON);
            btnView.setSelect(true);
            btnView2.setSelect(true);
            btnView3.setSelect(false);
            return;
        }
        if (i != 3) {
            return;
        }
        textView.setText(R.string._283_ON);
        btnView.setSelect(true);
        btnView2.setSelect(true);
        btnView3.setSelect(true);
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
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mView.removeCallbacks(this.mRunnable);
        this.mView.postDelayed(this.mRunnable, 5000L);
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
}
