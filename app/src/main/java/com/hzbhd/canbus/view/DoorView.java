package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.DispUtility;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.LogUtil;

/* loaded from: classes2.dex */
public class DoorView {
    private ImageView mBackIv;
    private TextView mBatteryWarningTv;
    private Context mContext;
    private TextView mEspTv;
    private RelativeLayout mFloatView;
    private ImageView mFrontIv;
    private TextView mFuelWarningTv;
    private TextView mHandBrakeTv;
    private boolean mIsDataChange;
    private TextView mIstopTv;
    private WindowManager.LayoutParams mLayoutParams;
    private ImageView mLeftFrontIv;
    private ImageView mLeftRearIv;
    private TextView mLittleLightTv;
    private ImageView mRightFrontIv;
    private ImageView mRightRearIv;
    private RelativeLayout mRlCarBody;
    private TextView mSeatBeltCoPilotTv;
    private TextView mSeatBeltMasterTv;
    private TextView mSeatBeltRearLeftTv;
    private TextView mSeatBeltRearMidTv;
    private TextView mSeatBeltRearRightTv;
    private TextView mSeatBeltSubTv;
    private TextView mSeatBeltTv;
    private ImageView mSkyWindowIv;
    private TextView mWashingFluidWarning;
    private TextView mWaterTempTv;
    private WindowManager mWindowManager;
    private boolean isShowing = false;
    private final DoorItem<Boolean> mFrontLeftDoor = new DoorItem<>(false);
    private final DoorItem<Boolean> mFrontRightDoor = new DoorItem<>(false);
    private final DoorItem<Boolean> mRearLeftDoor = new DoorItem<>(false);
    private final DoorItem<Boolean> mRearRightDoor = new DoorItem<>(false);
    private final DoorItem<Boolean> mHood = new DoorItem<>(false);
    private final DoorItem<Boolean> mTrunk = new DoorItem<>(false);
    private final DoorItem<Integer> mSkyWindowOpenLevel = new DoorItem<>(0);
    private final DoorItem<Boolean> mIsHandBrakeUp = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSeatBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSubSeatBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSeatMasterDriverBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSeatCoPilotBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSeatRLBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSeatRMBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsSeatRRBeltTie = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsEspOn = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsIsTopOn = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsLittleLightOn = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsWaterTempWarning = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsBatteryWarning = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsFuelWarning = new DoorItem<>(false);
    private final DoorItem<Boolean> mIsWashingFluidWarning = new DoorItem<>(false);
    CanSettingProxy mProxy = (CanSettingProxy) Dependency.get(CanSettingProxy.class);
    private Runnable mRunnable = new Runnable() { // from class: com.hzbhd.canbus.view.DoorView.2
        @Override // java.lang.Runnable
        public void run() {
            DoorView.this.dismissView();
        }
    };

    public DoorView(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        initView();
    }

    private void initView() {
        DispUtility.disabledDisplayDpiChange(this.mContext.getResources());
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.mContext).inflate(R.layout.layout_door_view, (ViewGroup) null);
        this.mFloatView = relativeLayout;
        this.mLeftFrontIv = (ImageView) relativeLayout.findViewById(R.id.iv_left_front);
        this.mRightFrontIv = (ImageView) this.mFloatView.findViewById(R.id.iv_right_front);
        this.mLeftRearIv = (ImageView) this.mFloatView.findViewById(R.id.iv_left_rear);
        this.mRightRearIv = (ImageView) this.mFloatView.findViewById(R.id.iv_right_rear);
        this.mBackIv = (ImageView) this.mFloatView.findViewById(R.id.iv_back);
        this.mSkyWindowIv = (ImageView) this.mFloatView.findViewById(R.id.iv_sky_window);
        this.mFrontIv = (ImageView) this.mFloatView.findViewById(R.id.iv_head);
        this.mHandBrakeTv = (TextView) this.mFloatView.findViewById(R.id.tv_handbrake);
        this.mSeatBeltTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt);
        this.mSeatBeltSubTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt_sub);
        this.mEspTv = (TextView) this.mFloatView.findViewById(R.id.tv_esp);
        this.mIstopTv = (TextView) this.mFloatView.findViewById(R.id.tv_istop);
        this.mLittleLightTv = (TextView) this.mFloatView.findViewById(R.id.tv_little_light);
        this.mWaterTempTv = (TextView) this.mFloatView.findViewById(R.id.tv_water_temp_warning);
        this.mBatteryWarningTv = (TextView) this.mFloatView.findViewById(R.id.tv_battery_warning);
        this.mFuelWarningTv = (TextView) this.mFloatView.findViewById(R.id.tv_fuel_warning);
        this.mWashingFluidWarning = (TextView) this.mFloatView.findViewById(R.id.tv_washing_fluid_warning);
        this.mSeatBeltMasterTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt_master);
        this.mSeatBeltCoPilotTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt_co_Pilot);
        this.mSeatBeltRearLeftTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt_r_l);
        this.mSeatBeltRearMidTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt_r_m);
        this.mSeatBeltRearRightTv = (TextView) this.mFloatView.findViewById(R.id.tv_seat_belt_r_r);
        this.mRlCarBody = (RelativeLayout) this.mFloatView.findViewById(R.id.rl_door);
        if (!GeneralDoorData.isShowCarBody) {
            this.mRlCarBody.setVisibility(8);
        }
        this.mFloatView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.DoorView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DoorView.this.mFloatView.removeCallbacks(DoorView.this.mRunnable);
                DoorView.this.mFloatView.post(DoorView.this.mRunnable);
            }
        });
        LogUtil.showLog("door", "11 door_right_front_margin_end:" + this.mContext.getResources().getDimension(R.dimen.door_right_front_margin_end));
    }

    private boolean isDataChange() {
        if (this.mProxy.getDoorSwapFront()) {
            this.mFrontLeftDoor.setValue(Boolean.valueOf(GeneralDoorData.isRightFrontDoorOpen));
            this.mFrontRightDoor.setValue(Boolean.valueOf(GeneralDoorData.isLeftFrontDoorOpen));
        } else {
            this.mFrontLeftDoor.setValue(Boolean.valueOf(GeneralDoorData.isLeftFrontDoorOpen));
            this.mFrontRightDoor.setValue(Boolean.valueOf(GeneralDoorData.isRightFrontDoorOpen));
        }
        if (this.mProxy.getDoorSwapRear()) {
            this.mRearLeftDoor.setValue(Boolean.valueOf(GeneralDoorData.isRightRearDoorOpen));
            this.mRearRightDoor.setValue(Boolean.valueOf(GeneralDoorData.isLeftRearDoorOpen));
        } else {
            this.mRearLeftDoor.setValue(Boolean.valueOf(GeneralDoorData.isLeftRearDoorOpen));
            this.mRearRightDoor.setValue(Boolean.valueOf(GeneralDoorData.isRightRearDoorOpen));
        }
        Log.i("DoorView", "isDataChange: mHood");
        if (this.mProxy.getShowHood()) {
            this.mHood.setValue(Boolean.valueOf(GeneralDoorData.isFrontOpen));
        } else {
            this.mHood.setValue(false);
        }
        Log.i("DoorView", "isDataChange: mTrunk");
        if (this.mProxy.getShowTrunk()) {
            this.mTrunk.setValue(Boolean.valueOf(GeneralDoorData.isBackOpen));
        } else {
            this.mTrunk.setValue(false);
        }
        this.mSkyWindowOpenLevel.setValue(Integer.valueOf(GeneralDoorData.skyWindowOpenLevel));
        if (GeneralDoorData.isShowHandBrake) {
            this.mIsHandBrakeUp.setValue(Boolean.valueOf(GeneralDoorData.isHandBrakeUp));
        }
        if (GeneralDoorData.isShowSeatBelt) {
            this.mIsSeatBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSeatBeltTie));
        }
        if (GeneralDoorData.isSubShowSeatBelt) {
            this.mIsSubSeatBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSubSeatBeltTie));
        }
        if (GeneralDoorData.isShowMasterDriverSeatBelt) {
            this.mIsSeatMasterDriverBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSeatMasterDriverBeltTie));
        }
        if (GeneralDoorData.isShowCoPilotSeatBelt) {
            this.mIsSeatCoPilotBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSeatCoPilotBeltTie));
        }
        if (GeneralDoorData.isShowRLSeatBelt) {
            this.mIsSeatRLBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSeatRLBeltTie));
        }
        if (GeneralDoorData.isShowRMSeatBelt) {
            this.mIsSeatRMBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSeatRMBeltTie));
        }
        if (GeneralDoorData.isShowRRSeatBelt) {
            this.mIsSeatRRBeltTie.setValue(Boolean.valueOf(GeneralDoorData.isSeatRRBeltTie));
        }
        if (GeneralDoorData.isShowEsp) {
            this.mIsEspOn.setValue(Boolean.valueOf(GeneralDoorData.isEspOn));
        }
        if (GeneralDoorData.isShowIstop) {
            this.mIsIsTopOn.setValue(Boolean.valueOf(GeneralDoorData.isIstopOn));
        }
        if (GeneralDoorData.isShowLittleLight) {
            this.mIsLittleLightOn.setValue(Boolean.valueOf(GeneralDoorData.isLittleLightOn));
        }
        if (GeneralDoorData.isShowWaterTemp) {
            this.mIsWaterTempWarning.setValue(Boolean.valueOf(GeneralDoorData.isWaterTempWarning));
        }
        if (GeneralDoorData.isShowBatteryWarning) {
            this.mIsBatteryWarning.setValue(Boolean.valueOf(GeneralDoorData.isBatteryWarning));
        }
        if (GeneralDoorData.isShowFuelWarning) {
            this.mIsFuelWarning.setValue(Boolean.valueOf(GeneralDoorData.isFuelWarning));
        }
        if (GeneralDoorData.isShowWashingFluidWarning) {
            this.mIsWashingFluidWarning.setValue(Boolean.valueOf(GeneralDoorData.isWashingFluidWarning));
        }
        return this.mIsDataChange;
    }

    public void refreshUi() {
        if (this.mProxy.getShowDoorInfo() && isDataChange()) {
            this.mIsDataChange = false;
            setImageViewStatus(this.mLeftFrontIv, this.mFrontLeftDoor.getValue(), R.drawable.car_door_front_left_on, R.drawable.car_door_front_left_off);
            setImageViewStatus(this.mRightFrontIv, this.mFrontRightDoor.getValue(), R.drawable.car_door_front_right_on, R.drawable.car_door_front_right_off);
            setImageViewStatus(this.mLeftRearIv, this.mRearLeftDoor.getValue(), R.drawable.car_door_rear_left_on, R.drawable.car_door_rear_left_off);
            setImageViewStatus(this.mRightRearIv, this.mRearRightDoor.getValue(), R.drawable.car_door_rear_right_on, R.drawable.car_door_rear_right_off);
            if (this.mHood.getValue().booleanValue()) {
                this.mFrontIv.setVisibility(0);
            } else {
                this.mFrontIv.setVisibility(4);
            }
            if (this.mTrunk.getValue().booleanValue()) {
                this.mBackIv.setVisibility(0);
            } else {
                this.mBackIv.setVisibility(4);
            }
            int iIntValue = this.mSkyWindowOpenLevel.getValue().intValue();
            if (iIntValue == 0) {
                this.mSkyWindowIv.setImageResource(R.drawable.car_door_skylight_off);
            } else if (iIntValue == 1) {
                this.mSkyWindowIv.setImageResource(R.drawable.car_door_skylight_half_on);
            } else if (iIntValue == 2) {
                this.mSkyWindowIv.setImageResource(R.drawable.car_door_skylight_whole_on);
            }
            if (GeneralDoorData.isShowHandBrake) {
                this.mHandBrakeTv.setVisibility(0);
                if (this.mIsHandBrakeUp.getValue().booleanValue()) {
                    this.mHandBrakeTv.setText(R.string.hand_brake_up);
                    this.mHandBrakeTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.handbreak_on, 0, 0, 0);
                } else {
                    this.mHandBrakeTv.setText(R.string.hand_brake_down);
                    this.mHandBrakeTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.handbreak_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowSeatBelt) {
                this.mSeatBeltTv.setVisibility(0);
                if (this.mIsSeatBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltTv.setText(GeneralDoorData.isSubShowSeatBelt ? R.string.master_driver_belt_has_been_attached : R.string.seat_belt_has_been_attached);
                    this.mSeatBeltTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltTv.setText(GeneralDoorData.isSubShowSeatBelt ? R.string.master_driver_belt_not_tied : R.string.seat_belt_not_tied);
                    this.mSeatBeltTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isSubShowSeatBelt) {
                this.mSeatBeltSubTv.setVisibility(0);
                if (this.mIsSubSeatBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltSubTv.setText(R.string.co_pilot_belt_has_been_attached);
                    this.mSeatBeltSubTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltSubTv.setText(R.string.co_pilot_belt_not_tied);
                    this.mSeatBeltSubTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowMasterDriverSeatBelt) {
                this.mSeatBeltMasterTv.setVisibility(0);
                this.mSeatBeltMasterTv.setText(R.string.master_driver);
                if (this.mIsSeatMasterDriverBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltMasterTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltMasterTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowCoPilotSeatBelt) {
                this.mSeatBeltCoPilotTv.setVisibility(0);
                this.mSeatBeltCoPilotTv.setText(R.string.co_pilot);
                if (this.mIsSeatCoPilotBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltCoPilotTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltCoPilotTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowRLSeatBelt) {
                this.mSeatBeltRearLeftTv.setVisibility(0);
                this.mSeatBeltRearLeftTv.setText(R.string.back_left);
                if (this.mIsSeatRLBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltRearLeftTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltRearLeftTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowRMSeatBelt) {
                this.mSeatBeltRearMidTv.setVisibility(0);
                this.mSeatBeltRearMidTv.setText(R.string.back_mid);
                if (this.mIsSeatRMBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltRearMidTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltRearMidTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowRRSeatBelt) {
                this.mSeatBeltRearRightTv.setVisibility(0);
                this.mSeatBeltRearRightTv.setText(R.string.back_right);
                if (this.mIsSeatRRBeltTie.getValue().booleanValue()) {
                    this.mSeatBeltRearRightTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_on, 0, 0, 0);
                } else {
                    this.mSeatBeltRearRightTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.belt_off, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowEsp) {
                this.mEspTv.setVisibility(0);
                if (this.mIsEspOn.getValue().booleanValue()) {
                    this.mEspTv.setText(R.string.esp_off);
                    this.mEspTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.esp_p, 0, 0, 0);
                } else {
                    this.mEspTv.setVisibility(8);
                }
            }
            if (GeneralDoorData.isShowIstop) {
                this.mIstopTv.setVisibility(0);
                if (this.mIsIsTopOn.getValue().booleanValue()) {
                    this.mIstopTv.setText(R.string.istop_on);
                    this.mIstopTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.istop_p, 0, 0, 0);
                } else {
                    this.mIstopTv.setText(R.string.istop_off);
                    this.mIstopTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.istop_n, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowLittleLight) {
                this.mLittleLightTv.setVisibility(0);
                if (this.mIsLittleLightOn.getValue().booleanValue()) {
                    this.mLittleLightTv.setText(R.string.little_light_on);
                    this.mLittleLightTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.small_light_p, 0, 0, 0);
                } else {
                    this.mLittleLightTv.setText(R.string.little_light_off);
                    this.mLittleLightTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.small_light_n, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowWaterTemp) {
                this.mWaterTempTv.setVisibility(0);
                if (this.mIsWaterTempWarning.getValue().booleanValue()) {
                    this.mWaterTempTv.setText(R.string.water_temp_warning_);
                    this.mWaterTempTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.water_warning_p, 0, 0, 0);
                } else {
                    this.mWaterTempTv.setText(R.string.water_temp);
                    this.mWaterTempTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.water_warning_n, 0, 0, 0);
                }
            }
            if (GeneralDoorData.isShowBatteryWarning) {
                if (this.mIsBatteryWarning.getValue().booleanValue()) {
                    this.mBatteryWarningTv.setVisibility(0);
                    this.mBatteryWarningTv.setText(R.string.battery_warning);
                    this.mBatteryWarningTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.battery_on, 0, 0, 0);
                } else {
                    this.mBatteryWarningTv.setVisibility(8);
                }
            }
            if (GeneralDoorData.isShowFuelWarning) {
                if (this.mIsFuelWarning.getValue().booleanValue()) {
                    this.mFuelWarningTv.setVisibility(0);
                    this.mFuelWarningTv.setText(R.string.fuel_warning);
                    this.mFuelWarningTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fuel_on, 0, 0, 0);
                } else {
                    this.mFuelWarningTv.setVisibility(8);
                }
            }
            if (GeneralDoorData.isShowWashingFluidWarning) {
                if (this.mIsWashingFluidWarning.getValue().booleanValue()) {
                    this.mWashingFluidWarning.setVisibility(0);
                    this.mWashingFluidWarning.setText(R.string.washing_fluid_warning);
                    this.mWashingFluidWarning.setCompoundDrawablesWithIntrinsicBounds(R.drawable.water_on, 0, 0, 0);
                } else {
                    this.mWashingFluidWarning.setVisibility(8);
                }
            }
            addViewToWindow();
        }
    }

    private void addViewToWindow() {
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
        }
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
        }
        if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getDoorCountDownTimerState()) {
            return;
        }
        this.mFloatView.removeCallbacks(this.mRunnable);
        this.mFloatView.postDelayed(this.mRunnable, 5000L);
    }

    private void setImageViewStatus(ImageView imageView, Boolean bool, int i, int i2) {
        if (!bool.booleanValue()) {
            i = i2;
        }
        imageView.setImageResource(i);
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

    private class DoorItem<T> {
        private T value;

        public DoorItem(T t) {
            this.value = t;
        }

        void setValue(T t) {
            if (this.value != t) {
                this.value = t;
                DoorView.this.mIsDataChange = true;
            }
        }

        T getValue() {
            return this.value;
        }
    }
}
