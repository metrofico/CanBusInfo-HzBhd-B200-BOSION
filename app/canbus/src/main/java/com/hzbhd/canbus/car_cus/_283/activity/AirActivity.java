package com.hzbhd.canbus.car_cus._283.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car._283.MsgMgr;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter;
import com.hzbhd.canbus.car_cus._283.bean.AirSelectBean;
import com.hzbhd.canbus.car_cus._283.view.AirTextView;
import com.hzbhd.canbus.car_cus._283.view.BtnView;
import com.hzbhd.canbus.car_cus._283.view.CenterControlView;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class AirActivity extends AbstractBaseActivity implements View.OnClickListener {
    public static final String TEMP_INTERVAL = "_283_temp_interval";
    public static final int WHAT_CHANGE_SYNC = 1;
    public static final int WHAT_FINISH_THIS = 0;
    private BtnView btn_cleanair;
    private BtnView btn_off_on;
    private BtnView btn_rear;
    private BtnView btn_setting;
    private BtnView btn_sync;
    private BtnView btn_wheel;
    private AirTextView center_airTextView;
    private ImageView front_down_wind;
    private ImageView front_left_seat;
    private AirTextView front_right_airTextView;
    private ImageView front_right_seat;
    private ImageView front_up_wind;
    private ImageView front_windows_wind;
    private ImageView iv_centerControl;
    private int left_front_hot;
    private int left_front_ventilate;
    private TextView left_view;
    private ImageView left_view_iv;
    private CenterControlView mCenterControlView;
    private AirTextView rear_right_airTextView;
    private ImageView rear_right_seat;
    private ImageView rear_wind;
    private int right_front_hot;
    private int right_front_ventilate;
    private int right_rear_hot;
    private int right_rear_ventilate;
    private ImageView right_view;
    private float tempInterval = 0.5f;
    private float maxF = 73.4f;
    private float maxC = 23.0f;
    private List<AirSelectBean> listLeft = new ArrayList();
    private List<AirSelectBean> listRight = new ArrayList();
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private int[] front_left_cold = {R.drawable.dz_seat_cold_fl_1, R.drawable.dz_seat_cold_fl_2, R.drawable.dz_seat_cold_fl_3};
    private int[] front_left_hot = {R.drawable.dz_seat_heat_fl_1, R.drawable.dz_seat_heat_fl_2, R.drawable.dz_seat_heat_fl_3};
    private int[] front_right_cold = {R.drawable.dz_seat_cold_fr_1, R.drawable.dz_seat_cold_fr_2, R.drawable.dz_seat_cold_fr_3};
    private int[] front_right_hot = {R.drawable.dz_seat_heat_fr_1, R.drawable.dz_seat_heat_fr_2, R.drawable.dz_seat_heat_fr_3};
    private int[] rear_right_cold = {R.drawable.dz_seat_cold_rear_1, R.drawable.dz_seat_cold_rear_2, R.drawable.dz_seat_cold_rear_3};
    private int[] rear_right_hot = {R.drawable.dz_seat_heat_rear_1, R.drawable.dz_seat_heat_rear_2, R.drawable.dz_seat_heat_rear_3};
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                AirActivity.this.finish();
            } else {
                if (i != 1) {
                    return;
                }
                AirActivity.this.sendMsg((byte) 17, !SharePreUtil.getBoolValue(AirActivity.this, MsgMgr.SHARE_283_AIR_SYNC, false));
                AirActivity.this.refreshUi(null);
            }
        }
    };

    private void setTimeOut() {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_activity_air);
        initView();
        CanbusMsgSender.sendMsg(new byte[]{22, 58, -16, 0});
        this.mHandler.sendEmptyMessage(1);
        this.mHandler.sendEmptyMessageDelayed(1, 1000L);
        MessageSender.getMsg(49);
        refreshUi(null);
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                AirActivity.lambda$onCreate$0();
            }
        }).start();
        setTimeOut();
    }

    static /* synthetic */ void lambda$onCreate$0() throws InterruptedException {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageSender.getMsg(49);
    }

    private void initView() {
        this.left_view_iv = (ImageView) findViewById(R.id.left_view_iv);
        this.iv_centerControl = (ImageView) findViewById(R.id.iv_centerControl);
        this.front_left_seat = (ImageView) findViewById(R.id.front_left_seat);
        this.front_right_seat = (ImageView) findViewById(R.id.front_right_seat);
        this.rear_right_seat = (ImageView) findViewById(R.id.rear_right_seat);
        this.front_windows_wind = (ImageView) findViewById(R.id.front_windows_wind);
        this.front_up_wind = (ImageView) findViewById(R.id.front_up_wind);
        this.front_down_wind = (ImageView) findViewById(R.id.front_down_wind);
        this.rear_wind = (ImageView) findViewById(R.id.rear_wind);
        this.front_right_airTextView = (AirTextView) findViewById(R.id.front_right_airTextView);
        this.center_airTextView = (AirTextView) findViewById(R.id.center_airTextView);
        this.rear_right_airTextView = (AirTextView) findViewById(R.id.rear_right_airTextView);
        this.left_view = (TextView) findViewById(R.id.left_view);
        this.right_view = (ImageView) findViewById(R.id.right_view);
        this.btn_off_on = (BtnView) findViewById(R.id.btn_off_on);
        this.btn_sync = (BtnView) findViewById(R.id.btn_sync);
        this.btn_rear = (BtnView) findViewById(R.id.btn_rear);
        this.btn_cleanair = (BtnView) findViewById(R.id.btn_cleanair);
        this.btn_wheel = (BtnView) findViewById(R.id.btn_wheel);
        this.btn_setting = (BtnView) findViewById(R.id.btn_setting);
        this.iv_centerControl.setOnClickListener(this);
        this.left_view_iv.setOnClickListener(this);
        this.left_view.setOnClickListener(this);
        this.right_view.setOnClickListener(this);
        this.btn_off_on.setOnClickListener(this);
        this.btn_sync.setOnClickListener(this);
        this.btn_rear.setOnClickListener(this);
        this.btn_cleanair.setOnClickListener(this);
        this.btn_wheel.setOnClickListener(this);
        this.btn_setting.setOnClickListener(this);
        this.listLeft.add(new AirSelectBean(getString(R.string._283_auto), null));
        this.listLeft.add(new AirSelectBean(getString(R.string._283_mac_ac), null));
        this.listLeft.add(new AirSelectBean(null, getDrawable(R.drawable._283_air_select_max)));
        this.listLeft.add(new AirSelectBean(getString(R.string._283_manu), null));
        this.listRight.add(new AirSelectBean(getString(R.string._283_wind_low), getDrawable(R.drawable._283_air_wind_low)));
        this.listRight.add(new AirSelectBean(getString(R.string._283_wind_mid), getDrawable(R.drawable._283_air_wind_mid)));
        this.listRight.add(new AirSelectBean(getString(R.string._283_wind_hight), getDrawable(R.drawable._283_air_wind_hight)));
        this.center_airTextView.setOnClickListener(new AirTextView.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity.2
            @Override // com.hzbhd.canbus.car_cus._283.view.AirTextView.OnClickListener
            public void add() {
                AirActivity.this.addTemp((byte) 20, GeneralDzData.air_front_left_temp);
            }

            @Override // com.hzbhd.canbus.car_cus._283.view.AirTextView.OnClickListener
            public void sub() {
                AirActivity.this.subTemp((byte) 20, GeneralDzData.air_front_left_temp);
            }
        });
        this.front_right_airTextView.setOnClickListener(new AirTextView.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity.3
            @Override // com.hzbhd.canbus.car_cus._283.view.AirTextView.OnClickListener
            public void add() {
                AirActivity.this.addTemp((byte) 21, GeneralDzData.air_front_right_temp);
            }

            @Override // com.hzbhd.canbus.car_cus._283.view.AirTextView.OnClickListener
            public void sub() {
                AirActivity.this.subTemp((byte) 21, GeneralDzData.air_front_right_temp);
            }
        });
        this.rear_right_airTextView.setOnClickListener(new AirTextView.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity.4
            @Override // com.hzbhd.canbus.car_cus._283.view.AirTextView.OnClickListener
            public void add() {
                AirActivity.this.addTemp((byte) 22, GeneralDzData.air_rear_temp);
            }

            @Override // com.hzbhd.canbus.car_cus._283.view.AirTextView.OnClickListener
            public void sub() {
                AirActivity.this.subTemp((byte) 22, GeneralDzData.air_rear_temp);
            }
        });
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (GeneralDzData.auto) {
            this.left_view.setText(this.listLeft.get(0).getText());
            setSelectLeft(0);
        }
        if (GeneralDzData.max_ac) {
            this.left_view.setText(this.listLeft.get(1).getText());
            setSelectLeft(1);
        }
        if (GeneralDzData.front_defog == 2) {
            this.left_view_iv.setImageDrawable(this.listLeft.get(2).getDrawable());
            setSelectLeft(2);
        }
        if (!GeneralDzData.auto && !GeneralDzData.max_ac && GeneralDzData.front_defog != 2) {
            this.left_view.setText(this.listLeft.get(3).getText());
            setSelectLeft(3);
        }
        if (GeneralDzData.auto_wind_power >= 0 && GeneralDzData.auto_wind_power <= 2) {
            this.right_view.setImageDrawable(this.listRight.get(GeneralDzData.auto_wind_power).getDrawable());
            setSelectRight(GeneralDzData.auto_wind_power);
        }
        if (GeneralDzData.air_power) {
            this.btn_off_on.setIcon(R.drawable.__283__dz_air_bottom_icon_on_p, R.drawable.__283__dz_air_bottom_icon_on_n);
            airPowerOpen();
        } else {
            this.btn_off_on.setIcon(R.drawable.__283__dz_air_bottom_icon_off_p, R.drawable.__283__dz_air_bottom_icon_off_n);
            airPowerClose();
        }
        this.btn_sync.setSelect(GeneralDzData.sync);
        this.btn_wheel.setSelect(GeneralDzData.steering_wheel_heating);
        if (GeneralDzData.rear_lock) {
            this.btn_rear.setIcon(R.drawable.__283__dz_air_bottom_icon_rear_p, R.drawable.__283__dz_air_bottom_icon_rear_n);
        } else {
            this.btn_rear.setIcon(R.drawable.__283__dz_air_bottom_icon_rear1_p, R.drawable.__283__dz_air_bottom_icon_rear1_n);
        }
        this.front_right_airTextView.setText(GeneralDzData.air_front_right_temp);
        this.center_airTextView.setText(GeneralDzData.air_front_left_temp);
        this.rear_right_airTextView.setText(GeneralDzData.air_rear_temp);
        setFrontWind(GeneralDzData.air_front_wind);
        setRearWind(GeneralDzData.air_rear_wind);
        if (this.left_front_ventilate != GeneralDzData.left_front_ventilate) {
            setSeat(GeneralDzData.left_front_ventilate, this.front_left_seat, this.front_left_cold);
        } else if (this.left_front_hot != GeneralDzData.left_front_hot) {
            setSeat(GeneralDzData.left_front_hot, this.front_left_seat, this.front_left_hot);
        }
        if (this.right_front_ventilate != GeneralDzData.right_front_ventilate) {
            setSeat(GeneralDzData.right_front_ventilate, this.front_right_seat, this.front_right_cold);
        } else if (this.right_front_hot != GeneralDzData.right_front_hot) {
            setSeat(GeneralDzData.right_front_hot, this.front_right_seat, this.front_right_hot);
        }
        setRearRightHot(GeneralDzData.right_rear_hot, this.rear_right_seat, this.rear_right_hot);
        this.right_front_ventilate = GeneralDzData.right_front_ventilate;
        this.left_front_ventilate = GeneralDzData.left_front_ventilate;
        this.right_front_hot = GeneralDzData.right_front_hot;
        this.left_front_hot = GeneralDzData.left_front_hot;
        CenterControlView centerControlView = this.mCenterControlView;
        if (centerControlView != null) {
            centerControlView.refreshUi();
        }
        setWindColor();
    }

    private void setWindColor() {
        float fFloatValue;
        float fFloatValue2;
        float fFloatValue3;
        try {
            boolean zContains = GeneralDzData.air_front_left_temp.contains(getString(R.string.str_temp_f_unit));
            float f = zContains ? this.maxF : this.maxC;
            if (zContains) {
                fFloatValue = Float.valueOf(GeneralDzData.air_front_left_temp.replace(getString(R.string.str_temp_f_unit), "")).floatValue();
                fFloatValue2 = Float.valueOf(GeneralDzData.air_front_right_temp.replace(getString(R.string.str_temp_f_unit), "")).floatValue();
                fFloatValue3 = Float.valueOf(GeneralDzData.air_rear_temp.replace(getString(R.string.str_temp_f_unit), "")).floatValue();
            } else {
                fFloatValue = Float.valueOf(GeneralDzData.air_front_left_temp.replace(getString(R.string.str_temp_c_unit), "")).floatValue();
                fFloatValue2 = Float.valueOf(GeneralDzData.air_front_right_temp.replace(getString(R.string.str_temp_c_unit), "")).floatValue();
                fFloatValue3 = Float.valueOf(GeneralDzData.air_rear_temp.replace(getString(R.string.str_temp_c_unit), "")).floatValue();
            }
            if (fFloatValue >= f && fFloatValue2 >= f) {
                this.front_windows_wind.setImageResource(R.drawable._283_dz_blow_glass);
                this.front_up_wind.setImageResource(R.drawable._283_dz_blow_head);
                this.front_down_wind.setImageResource(R.drawable._283_dz_blow_foot);
            } else if (fFloatValue >= f) {
                this.front_windows_wind.setImageResource(R.drawable._283_dz_blow_glass1);
                this.front_up_wind.setImageResource(R.drawable._283_dz_blow_head1);
                this.front_down_wind.setImageResource(R.drawable._283_dz_blow_foot1);
            } else if (fFloatValue2 >= f) {
                this.front_windows_wind.setImageResource(R.drawable._283_dz_blow_glass2);
                this.front_up_wind.setImageResource(R.drawable._283_dz_blow_head2);
                this.front_down_wind.setImageResource(R.drawable._283_dz_blow_foot2);
            } else {
                this.front_windows_wind.setImageResource(R.drawable.dz_blow_glass);
                this.front_up_wind.setImageResource(R.drawable.dz_blow_head);
                this.front_down_wind.setImageResource(R.drawable.dz_blow_foot);
            }
            if (fFloatValue3 >= f) {
                this.rear_wind.setImageResource(R.drawable._283_dz_blow_heat);
            } else {
                this.rear_wind.setImageResource(R.drawable.dz_blow_heat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        setTimeOut();
        switch (view.getId()) {
            case R.id.btn_cleanair /* 2131362014 */:
                startActivity(new Intent(this, (Class<?>) AirCleanActivity.class));
                break;
            case R.id.btn_off_on /* 2131362035 */:
                sendMsg((byte) 2, GeneralDzData.air_power);
                break;
            case R.id.btn_rear /* 2131362044 */:
                sendMsg((byte) 18, GeneralDzData.rear_lock);
                break;
            case R.id.btn_setting /* 2131362054 */:
                startActivity(new Intent(this, (Class<?>) AirSettingActivity.class));
                break;
            case R.id.btn_sync /* 2131362058 */:
                this.mHandler.removeMessages(1);
                sendMsg((byte) 17, GeneralDzData.sync);
                break;
            case R.id.btn_wheel /* 2131362065 */:
                sendMsg((byte) 35, GeneralDzData.steering_wheel_heating);
                break;
            case R.id.iv_centerControl /* 2131362554 */:
                this.mCenterControlView = DialogUtils.showCenterControl(this, this.iv_centerControl);
                break;
            case R.id.left_view /* 2131362749 */:
            case R.id.left_view_iv /* 2131362750 */:
                DialogUtils.showPopupwindows(this, this.left_view, this.listLeft, "", new AirSelectAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity.5
                    @Override // com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter.OnItemClickListener
                    public void click(int i) {
                        if (i == 0) {
                            MessageSender.sendMsg(new byte[]{22, 58, -127, 0});
                            return;
                        }
                        if (i == 1) {
                            MessageSender.sendMsg(new byte[]{22, 58, -127, 1});
                        } else if (i == 2) {
                            MessageSender.sendMsg(new byte[]{22, 58, -127, 2});
                        } else {
                            if (i != 3) {
                                return;
                            }
                            MessageSender.sendMsg(new byte[]{22, 58, -127, 3});
                        }
                    }
                });
                break;
            case R.id.right_view /* 2131363178 */:
                DialogUtils.showPopupwindows(this, this.right_view, this.listRight, getString(R.string._283_air_wind_choose), new AirSelectAdapter.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.AirActivity.6
                    @Override // com.hzbhd.canbus.car_cus._283.adapter.AirSelectAdapter.OnItemClickListener
                    public void click(int i) {
                        if (i == 0) {
                            MessageSender.sendMsg(new byte[]{22, 58, 1, 0});
                        } else if (i == 1) {
                            MessageSender.sendMsg(new byte[]{22, 58, 1, 1});
                        } else {
                            if (i != 2) {
                                return;
                            }
                            MessageSender.sendMsg(new byte[]{22, 58, 1, 2});
                        }
                    }
                });
                break;
        }
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

    private void setRearRightHot(int i, ImageView imageView, int[] iArr) {
        if (i == 0) {
            imageView.setImageDrawable(null);
        } else {
            if (i < 1 || i > 3) {
                return;
            }
            imageView.setImageDrawable(getDrawable(iArr[i - 1]));
        }
    }

    private void setSelectLeft(int i) {
        if (i == 2) {
            this.left_view_iv.setVisibility(0);
            this.left_view.setVisibility(4);
        } else {
            this.left_view_iv.setVisibility(4);
            this.left_view.setVisibility(0);
        }
        for (int i2 = 0; i2 < this.listLeft.size(); i2++) {
            if (i2 == i) {
                this.listLeft.get(i2).setSelect(true);
            } else {
                this.listLeft.get(i2).setSelect(false);
            }
        }
    }

    private void setSelectRight(int i) {
        for (int i2 = 0; i2 < this.listRight.size(); i2++) {
            if (i2 == i) {
                this.listRight.get(i2).setSelect(true);
            } else {
                this.listRight.get(i2).setSelect(false);
            }
        }
    }

    private void setRearWind(int i) {
        if (i == -1 || i == 0) {
            this.rear_wind.setVisibility(8);
            return;
        }
        if (i != 3 && i != 5 && i != 6) {
            switch (i) {
            }
            return;
        }
        this.rear_wind.setVisibility(0);
    }

    private void setFrontWind(int i) {
        if (i == -1) {
            this.front_windows_wind.setVisibility(8);
            this.front_up_wind.setVisibility(8);
            this.front_down_wind.setVisibility(8);
            GeneralDzData.fornt_left_blow_window = false;
            GeneralDzData.fornt_left_blow_head = false;
            GeneralDzData.fornt_left_blow_foot = false;
        }
        if (i == 0 || i == 1 || i == 2) {
            this.front_windows_wind.setVisibility(8);
            this.front_up_wind.setVisibility(8);
            this.front_down_wind.setVisibility(8);
            GeneralDzData.fornt_left_blow_window = false;
            GeneralDzData.fornt_left_blow_head = false;
            GeneralDzData.fornt_left_blow_foot = false;
            CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
            return;
        }
        if (i == 3) {
            this.front_windows_wind.setVisibility(8);
            this.front_up_wind.setVisibility(8);
            this.front_down_wind.setVisibility(0);
            GeneralDzData.fornt_left_blow_window = false;
            GeneralDzData.fornt_left_blow_head = false;
            GeneralDzData.fornt_left_blow_foot = true;
            return;
        }
        if (i == 5) {
            this.front_windows_wind.setVisibility(8);
            this.front_up_wind.setVisibility(0);
            this.front_down_wind.setVisibility(0);
            GeneralDzData.fornt_left_blow_window = false;
            GeneralDzData.fornt_left_blow_head = true;
            GeneralDzData.fornt_left_blow_foot = true;
            return;
        }
        if (i == 6) {
            this.front_windows_wind.setVisibility(8);
            this.front_up_wind.setVisibility(0);
            this.front_down_wind.setVisibility(8);
            GeneralDzData.fornt_left_blow_window = false;
            GeneralDzData.fornt_left_blow_head = true;
            GeneralDzData.fornt_left_blow_foot = false;
            return;
        }
        switch (i) {
            case 11:
                this.front_windows_wind.setVisibility(0);
                this.front_up_wind.setVisibility(8);
                this.front_down_wind.setVisibility(8);
                GeneralDzData.fornt_left_blow_window = true;
                GeneralDzData.fornt_left_blow_head = false;
                GeneralDzData.fornt_left_blow_foot = false;
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 42, 0});
                break;
            case 12:
                this.front_windows_wind.setVisibility(0);
                this.front_up_wind.setVisibility(8);
                this.front_down_wind.setVisibility(0);
                GeneralDzData.fornt_left_blow_window = true;
                GeneralDzData.fornt_left_blow_head = false;
                GeneralDzData.fornt_left_blow_foot = true;
                break;
            case 13:
                this.front_windows_wind.setVisibility(0);
                this.front_up_wind.setVisibility(0);
                this.front_down_wind.setVisibility(8);
                GeneralDzData.fornt_left_blow_window = true;
                GeneralDzData.fornt_left_blow_head = true;
                GeneralDzData.fornt_left_blow_foot = false;
                break;
            case 14:
                this.front_windows_wind.setVisibility(0);
                this.front_up_wind.setVisibility(0);
                this.front_down_wind.setVisibility(0);
                GeneralDzData.fornt_left_blow_window = true;
                GeneralDzData.fornt_left_blow_head = true;
                GeneralDzData.fornt_left_blow_foot = true;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTemp(byte b, String str) {
        setTimeOut();
        getTempInterval();
        try {
            if (str.equals("HI")) {
                return;
            }
            if (GeneralDzData.fahrenheit_celsius) {
                float fFloatValue = ((str.equals("LO") ? 16.0f : Float.valueOf(str.replace(getString(R.string.str_temp_c_unit), "")).floatValue()) + this.tempInterval) * 2.0f;
                if (fFloatValue >= 59.0f) {
                    MessageSender.sendMsg(new byte[]{22, 58, b, -1});
                    return;
                } else {
                    MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue});
                    return;
                }
            }
            float fFloatValue2 = ((str.equals("LO") ? 61.0f : Float.valueOf(str.replace(getString(R.string.str_temp_f_unit), "")).floatValue()) * 2.0f) + 2.0f;
            if (fFloatValue2 >= 170.0f) {
                MessageSender.sendMsg(new byte[]{22, 58, b, -1});
            } else {
                MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue2});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subTemp(byte b, String str) {
        setTimeOut();
        getTempInterval();
        try {
            if (str.equals("LO")) {
                return;
            }
            if (GeneralDzData.fahrenheit_celsius) {
                float fFloatValue = ((str.equals("HI") ? 29.5f : Float.valueOf(str.replace(getString(R.string.str_temp_c_unit), "")).floatValue()) - this.tempInterval) * 2.0f;
                if (fFloatValue <= 32.0f) {
                    MessageSender.sendMsg(new byte[]{22, 58, b, -2});
                    return;
                } else {
                    MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue});
                    return;
                }
            }
            float fFloatValue2 = ((str.equals("HI") ? 85.0f : Float.valueOf(str.replace(getString(R.string.str_temp_f_unit), "")).floatValue()) * 2.0f) - 2.0f;
            if (fFloatValue2 <= 122.0f) {
                MessageSender.sendMsg(new byte[]{22, 58, b, -2});
            } else {
                MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue2});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTempInterval() {
        try {
            this.tempInterval = Float.valueOf(SharePreUtil.getStringValue(this, TEMP_INTERVAL, "0.5")).floatValue();
        } catch (Exception e) {
            this.tempInterval = 0.5f;
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg(new byte[]{22, 58, b, (byte) (!z ? 1 : 0)});
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onDestroy() {
        ((MsgMgr) MsgMgrFactory.getCanMsgMgr(this)).clearCanbusAirInfoCopy();
        super.onDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, 58, -16});
        CanbusMsgSender.sendMsg(new byte[]{22, 58, -16, 0});
    }

    private void airPowerOpen() {
        this.iv_centerControl.setEnabled(true);
    }

    private void airPowerClose() {
        GeneralDzData.air_front_right_temp = "OFF";
        GeneralDzData.air_front_left_temp = "OFF";
        GeneralDzData.air_rear_temp = "OFF";
        GeneralDzData.sync = false;
        GeneralDzData.steering_wheel_heating = false;
        GeneralDzData.left_front_ventilate = 0;
        GeneralDzData.left_front_hot = 0;
        GeneralDzData.right_front_ventilate = 0;
        GeneralDzData.right_front_hot = 0;
        GeneralDzData.right_rear_hot = 0;
        GeneralDzData.air_front_wind = -1;
        GeneralDzData.air_rear_wind = -1;
        this.iv_centerControl.setEnabled(false);
        if (DialogUtils.getCenterControlPopupWindow() == null || !DialogUtils.getCenterControlPopupWindow().isShowing()) {
            return;
        }
        DialogUtils.getCenterControlPopupWindow().dismiss();
    }
}
