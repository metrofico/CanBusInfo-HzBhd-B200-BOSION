package com.hzbhd.canbus.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class AirSmallView {
    private AirPageUiSet mAirPageUiSet;
    private final Context mContext;
    private RelativeLayout mFloatView;
    private WindowManager.LayoutParams mLayoutParams;
    private ImageView[] mLeftBlow;
    private ImageView mLeftBlowAuto;
    private ImageView mLeftBlowFoot;
    private ImageView mLeftBlowHead;
    private ImageView mLeftBlowWindow;
    private ImageView mLeftHeat;
    private TextView mLeftTemperature;
    private ImageView[] mRightBlow;
    private ImageView mRightBlowAuto;
    private ImageView mRightBlowFoot;
    private ImageView mRightBlowHead;
    private ImageView mRightBlowWindow;
    private ImageView mRightHeat;
    private TextView mRightTemperature;
    private int mSwitchStatus;
    private LinearLayout mTopStatusLayout;
    private List<AirSmallStatusItemView> mTopStatusList;
    private SetWindSpeedView mWindSpeedWsv;
    private final WindowManager mWindowManager;
    private boolean isShowing = false;
    private boolean isInit = false;
    private final Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.view.AirSmallView.3
        @Override // java.lang.Runnable
        public void run() {
            AirSmallView.this.dismissView();
        }
    };

    public AirSmallView(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        findView();
        initView(context);
    }

    private void findView() {
        this.mAirPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getAirUiSet(this.mContext);
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.mContext).inflate(R.layout.layout_air_small_view, null);
        this.mFloatView = relativeLayout;
        this.mTopStatusLayout = relativeLayout.findViewById(R.id.ll_top_status_line);
        this.mWindSpeedWsv = this.mFloatView.findViewById(R.id.wind_speed);
        this.mLeftTemperature = this.mFloatView.findViewById(R.id.tv_left_temperature);
        this.mRightTemperature = this.mFloatView.findViewById(R.id.tv_right_temperature);
        this.mLeftBlowAuto = this.mFloatView.findViewById(R.id.iv_left_blow_auto);
        this.mLeftBlowWindow = this.mFloatView.findViewById(R.id.iv_left_blow_window);
        this.mLeftBlowHead = this.mFloatView.findViewById(R.id.iv_left_blow_head);
        this.mLeftBlowFoot = this.mFloatView.findViewById(R.id.iv_left_blow_foot);
        this.mRightBlowAuto = this.mFloatView.findViewById(R.id.iv_right_blow_auto);
        this.mRightBlowWindow = this.mFloatView.findViewById(R.id.iv_right_blow_window);
        this.mRightBlowHead = this.mFloatView.findViewById(R.id.iv_right_blow_head);
        this.mRightBlowFoot = this.mFloatView.findViewById(R.id.iv_right_blow_foot);
        this.mLeftHeat = this.mFloatView.findViewById(R.id.iv_left_heat);
    }

    private void initView(final Context context) {
        if (this.mAirPageUiSet == null) {
            return;
        }
        this.mFloatView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirSmallView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AirSmallView.this.mFloatView.removeCallbacks(AirSmallView.this.mRunnable);
                AirSmallView.this.mFloatView.post(AirSmallView.this.mRunnable);
            }
        });
        this.mFloatView.findViewById(R.id.air_small_view).setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirSmallView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(context, AirActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                AirSmallView.this.mFloatView.removeCallbacks(AirSmallView.this.mRunnable);
                AirSmallView.this.mFloatView.post(AirSmallView.this.mRunnable);
            }
        });
        this.mWindSpeedWsv.initViews(this.mContext, this.mAirPageUiSet.getFrontArea().isCanSetWindSpeed(), DataHandleUtils.rangeNumber(this.mAirPageUiSet.getFrontArea().getWindMaxLevel(), 0, 8), null);
        this.mWindSpeedWsv.findViewById(R.id.air_set_speed_view).setBackground(null);
        this.mWindSpeedWsv.findViewById(R.id.ib_down).setVisibility(View.INVISIBLE);
        this.mWindSpeedWsv.findViewById(R.id.ib_up).setVisibility(View.INVISIBLE);
        setVisible(this.mLeftHeat, this.mAirPageUiSet.getFrontArea().isShowSeatHeat());
        this.mTopStatusList = new ArrayList();
        String[] smallWindowStatusArray = this.mAirPageUiSet.getFrontArea().getSmallWindowStatusArray();
        if (smallWindowStatusArray.length > 0) {
            for (int i = 0; i < smallWindowStatusArray.length && i < 4; i++) {
                AirSmallStatusItemView item = getItem(this.mContext, smallWindowStatusArray[i]);
                if (item == null) {
                    Log.i("AirSmallView", "initView: The action[" + smallWindowStatusArray[i] + "] is undefined in smallWindow");
                } else {
                    this.mTopStatusLayout.addView(item, new LinearLayout.LayoutParams(0, -1, 1.0f));
                    this.mTopStatusList.add(item);
                }
            }
        }
        boolean switchAcTemperature = Dependency.get(CanSettingProxy.class).getSwitchAcTemperature();
        this.mSwitchStatus = switchAcTemperature ? 1 : 0;
        if (switchAcTemperature) {
            switchLeftRight();
        }
        this.isInit = true;
    }

    public void switchLeftRight() {
        Log.i("AirSmallView", "switchLeftRight: " + this.mSwitchStatus);
        TextView textView = this.mLeftTemperature;
        this.mLeftTemperature = this.mRightTemperature;
        this.mRightTemperature = textView;
    }

    public void refreshUi() {
        if (this.isInit) {
            if (Dependency.get(CanSettingProxy.class).getSwitchAcTemperature()) {
                this.mSwitchStatus = Dependency.get(CanSettingProxy.class).getSwitchAcTemperature() ? 1 : 0;
                switchLeftRight();
            }
            this.mWindSpeedWsv.setCurWindSpeed(GeneralAirData.front_wind_level);
            this.mWindSpeedWsv.setAuto(GeneralAirData.front_auto_wind_speed);
            this.mLeftTemperature.setText(CommUtil.temperatureUnitSwitch(GeneralAirData.front_left_temperature));
            this.mRightTemperature.setText(CommUtil.temperatureUnitSwitch(GeneralAirData.front_right_temperature));
            setVisible(this.mLeftBlowAuto, GeneralAirData.front_left_auto_wind);
            setVisible(this.mLeftBlowWindow, GeneralAirData.front_left_blow_window);
            setVisible(this.mLeftBlowHead, GeneralAirData.front_left_blow_head);
            setVisible(this.mLeftBlowFoot, GeneralAirData.front_left_blow_foot);
            setVisible(this.mRightBlowAuto, GeneralAirData.front_right_auto_wind);
            setVisible(this.mRightBlowWindow, GeneralAirData.front_right_blow_window);
            setVisible(this.mRightBlowHead, GeneralAirData.front_right_blow_head);
            setVisible(this.mRightBlowFoot, GeneralAirData.front_right_blow_foot);
            if (this.mSwitchStatus == 0) {
                this.mLeftHeat.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ic_air_s_seat_heat_" + DataHandleUtils.rangeNumber(GeneralAirData.front_left_seat_heat_level, 0, 3)));
            } else {
                this.mLeftHeat.setImageResource(CommUtil.getImgIdByResId(this.mContext, "ic_air_s_seat_heat_" + DataHandleUtils.rangeNumber(GeneralAirData.front_right_seat_heat_level, 0, 3)));
            }
            for (AirSmallStatusItemView airSmallStatusItemView : this.mTopStatusList) {
                airSmallStatusItemView.turn();
            }
            addViewToWindow();
        }
    }

    @SuppressLint("WrongConstant")
    private void addViewToWindow() {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.gravity = 80;
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -2;
        }
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mFloatView.removeCallbacks(this.mRunnable);
        this.mFloatView.postDelayed(this.mRunnable, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        RelativeLayout relativeLayout;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (relativeLayout = this.mFloatView) == null) {
            return;
        }
        windowManager.removeView(relativeLayout);
        this.isShowing = false;
    }

    private void setVisible(View view, boolean z) {
        if (view == null) {
            return;
        }
        if (z) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    private AirSmallStatusItemView getItem(Context context, String str) {
        AirSmallStatusItemView airSmallStatusItemView;
        switch (str) {
            case "front_defog":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_front_defog_max_n, R.drawable.ic_air_s_front_defog_max_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.9
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.front_defog || GeneralAirData.max_front;
                    }
                });
                break;
            case "rear_defog":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_rear_defog_n, R.drawable.ic_air_s_rear_defog_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.10
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.rear_defog;
                    }
                });
                break;
            case "ac":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_ac_n, R.drawable.ic_air_s_ac_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.5
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.ac;
                    }
                });
                break;
            case "aqs":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_aqs_n, R.drawable.ic_air_s_aqs_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.12
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.aqs;
                    }
                });
                break;
            case "auto":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_auto_n, R.drawable.ic_air_s_auto_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.6
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.auto || GeneralAirData.ac_auto;
                    }
                });
                break;
            case "dual":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_dual_n, R.drawable.ic_air_s_dual_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.7
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.dual;
                    }
                });
                break;
            case "sync":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_sync_n, R.drawable.ic_air_s_sync_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.11
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.sync;
                    }
                });
                break;
            case "power":
                airSmallStatusItemView = new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_power_n, R.drawable.ic_air_s_power_p, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.4
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.power;
                    }
                });
                break;
            case "in_out_cycle":
                return new AirSmallStatusItemView(context, str, R.drawable.ic_air_s_cycle_in, R.drawable.ic_air_s_cycle_out, new OnAirInfoChangeListener() { // from class: com.hzbhd.canbus.view.AirSmallView.8
                    @Override // com.hzbhd.canbus.view.OnAirInfoChangeListener
                    public boolean onAirInfoChange() {
                        return GeneralAirData.in_out_cycle;
                    }
                });
            default:
                return null;
        }
        return airSmallStatusItemView;
    }
}
