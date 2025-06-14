package com.hzbhd.canbus.car_cus._291;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._291.view.BtnView;
import com.hzbhd.canbus.car_cus._291.view.WindSpeedView;
import com.hzbhd.canbus.ui_datas.GeneralAirData;

/* loaded from: classes2.dex */
public class AirActivity extends AbstractBaseActivity {
    public static final String UPDATE_LEFT_WIND_POWER = "update_left_wind_power";
    public static final String UPDATE_RIGHT_WIND_POWER = "update_right_wind_power";
    public static final String UPDATE_WIND_POWER = "update_wind_power";
    public static final int WHAT_FINISH_THIS = 0;
    private BtnView btn_ac;
    private BtnView btn_auto;
    private BtnView btn_auto2;
    private BtnView btn_cycle;
    private BtnView btn_dual;
    private BtnView btn_max;
    private BtnView btn_max_ac;
    private BtnView btn_off_on;
    private BtnView btn_rear;
    private TextView front_left_airTextView;
    private ImageView front_left_seat;
    private TextView front_right_airTextView;
    private ImageView front_right_seat;
    private ImageView iv_left_foot;
    private ImageView iv_left_head;
    private ImageView iv_left_windows;
    private ImageView iv_right_foot;
    private ImageView iv_right_head;
    private ImageView iv_right_windows;
    private WindSpeedView wsv_left_Wind;
    private WindSpeedView wsv_right_Wind;
    private int[] front_left_hot = {R.drawable.dz_seat_heat_fl_1, R.drawable.dz_seat_heat_fl_2, R.drawable.dz_seat_heat_fl_3};
    private int[] front_right_hot = {R.drawable.dz_seat_heat_fr_1, R.drawable.dz_seat_heat_fr_2, R.drawable.dz_seat_heat_fr_3};
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._291.AirActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            AirActivity.this.finish();
        }
    };

    private int getIvVisible(boolean z) {
        return z ? 0 : 8;
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._291_activity_air);
        initView();
        setTimeOut();
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._291.AirActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1125lambda$onCreate$0$comhzbhdcanbuscar_cus_291AirActivity();
            }
        }, 500L);
    }

    /* renamed from: lambda$onCreate$0$com-hzbhd-canbus-car_cus-_291-AirActivity, reason: not valid java name */
    /* synthetic */ void m1125lambda$onCreate$0$comhzbhdcanbuscar_cus_291AirActivity() {
        refreshUi(null);
    }

    private void setTimeOut() {
        String stringExtra = getIntent().getStringExtra("type");
        if (TextUtils.isEmpty(stringExtra) || !stringExtra.equals("SETUP")) {
            return;
        }
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 5000L);
    }

    private void initView() {
        this.front_left_seat = (ImageView) findViewById(R.id.front_left_seat);
        this.front_right_seat = (ImageView) findViewById(R.id.front_right_seat);
        this.iv_left_foot = (ImageView) findViewById(R.id.iv_left_foot);
        this.iv_left_head = (ImageView) findViewById(R.id.iv_left_head);
        this.iv_left_windows = (ImageView) findViewById(R.id.iv_left_windows);
        this.iv_right_foot = (ImageView) findViewById(R.id.iv_right_foot);
        this.iv_right_head = (ImageView) findViewById(R.id.iv_right_head);
        this.iv_right_windows = (ImageView) findViewById(R.id.iv_right_windows);
        this.front_right_airTextView = (TextView) findViewById(R.id.front_right_airTextView);
        this.front_left_airTextView = (TextView) findViewById(R.id.front_left_airTextView);
        this.btn_off_on = (BtnView) findViewById(R.id.btn_off_on);
        this.btn_auto = (BtnView) findViewById(R.id.btn_auto);
        this.btn_max_ac = (BtnView) findViewById(R.id.btn_max_ac);
        this.btn_max = (BtnView) findViewById(R.id.btn_max);
        this.btn_rear = (BtnView) findViewById(R.id.btn_rear);
        this.btn_auto2 = (BtnView) findViewById(R.id.btn_auto2);
        this.btn_dual = (BtnView) findViewById(R.id.btn_dual);
        this.btn_ac = (BtnView) findViewById(R.id.btn_ac);
        this.btn_cycle = (BtnView) findViewById(R.id.btn_cycle);
        this.wsv_left_Wind = (WindSpeedView) findViewById(R.id.wsv_left_Wind);
        this.wsv_right_Wind = (WindSpeedView) findViewById(R.id.wsv_right_Wind);
        this.btn_off_on.setEnabled(false);
        this.btn_auto.setEnabled(false);
        this.btn_max_ac.setEnabled(false);
        this.btn_max.setEnabled(false);
        this.btn_rear.setEnabled(false);
        this.btn_auto2.setEnabled(false);
        this.btn_dual.setEnabled(false);
        this.btn_ac.setEnabled(false);
        this.btn_cycle.setEnabled(false);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        String string;
        setTimeOut();
        this.btn_auto.setSelect(GeneralAirData.auto);
        this.btn_max_ac.setSelect(GeneralAirData.ac_max);
        this.btn_max.setSelect(GeneralAirData.ac_max);
        this.btn_rear.setSelect(GeneralAirData.rear_defog);
        this.btn_auto2.setSelect(GeneralAirData.auto_2);
        this.btn_dual.setSelect(GeneralAirData.dual);
        this.btn_ac.setSelect(GeneralAirData.ac);
        if (GeneralAirData.in_out_auto_cycle == 1) {
            this.btn_cycle.setIcon(R.drawable.dz_air_inner_loop_p, R.drawable.dz_air_inner_loop_p);
        } else if (GeneralAirData.in_out_auto_cycle == 3) {
            this.btn_cycle.setIcon(R.drawable.dz_air_auto_loop_p, R.drawable.dz_air_auto_loop_p);
        } else {
            this.btn_cycle.setIcon(R.drawable.dz_air_outer_loop_p, R.drawable.dz_air_outer_loop_p);
        }
        if (GeneralAirData.power) {
            this.btn_off_on.setIcon(R.drawable._291_dz_air_bottom_icon_on_p, R.drawable._291_dz_air_bottom_icon_on_n);
        } else {
            this.btn_off_on.setIcon(R.drawable._291_dz_air_bottom_icon_off_p, R.drawable._291_dz_air_bottom_icon_off_n);
        }
        this.front_right_airTextView.setText(GeneralAirData.front_right_temperature);
        this.front_left_airTextView.setText(GeneralAirData.front_left_temperature);
        this.iv_left_foot.setVisibility(getIvVisible(GeneralAirData.front_left_blow_foot));
        this.iv_left_head.setVisibility(getIvVisible(GeneralAirData.front_left_blow_head));
        this.iv_left_windows.setVisibility(getIvVisible(GeneralAirData.front_left_blow_window));
        this.iv_right_foot.setVisibility(getIvVisible(GeneralAirData.front_right_blow_foot));
        this.iv_right_head.setVisibility(getIvVisible(GeneralAirData.front_right_blow_head));
        this.iv_right_windows.setVisibility(getIvVisible(GeneralAirData.front_right_blow_window));
        if (bundle != null && (string = bundle.getString(UPDATE_WIND_POWER)) != null) {
            string.hashCode();
            if (string.equals(UPDATE_LEFT_WIND_POWER)) {
                if (GeneralAirData.center_wheel.equals("Left")) {
                    this.wsv_left_Wind.setWindPower(GeneralAirData.front_wind_level);
                }
            } else if (string.equals(UPDATE_RIGHT_WIND_POWER) && GeneralAirData.center_wheel.equals("Right")) {
                this.wsv_right_Wind.setWindPower(GeneralAirData.front_right_wind_level);
            }
        }
        this.wsv_left_Wind.setWindPower(GeneralAirData.front_wind_level);
        this.wsv_right_Wind.setWindPower(GeneralAirData.front_right_wind_level);
        setSeat(GeneralAirData.front_left_seat_heat_level, this.front_left_seat, this.front_left_hot);
        setSeat(GeneralAirData.front_right_seat_heat_level, this.front_right_seat, this.front_right_hot);
    }

    private void setSeat(int i, ImageView imageView, int[] iArr) {
        if (i == 0) {
            imageView.setImageDrawable(null);
        } else {
            if (i < 1 || i > 3) {
                return;
            }
            imageView.setImageDrawable(getDrawable(iArr[i - 1]));
        }
    }
}
