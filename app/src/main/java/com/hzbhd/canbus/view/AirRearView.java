package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;

/* loaded from: classes2.dex */
public class AirRearView extends RelativeLayout implements View.OnClickListener {
    private AirActivity mActivity;
    private ImageView mBlowFootLeftIv;
    private ImageView mBlowFootRightIv;
    private ImageView mBlowHeadLeftIv;
    private ImageView mBlowHeadRightIv;
    private ImageView mBlowWindowsLeftIv;
    private ImageView mBlowWindowsRightIv;
    private LineBtnView mBottomLbv;
    private LineBtnView mBottomLeftLbv;
    private LineBtnView mBottomRightLbv;
    private SeatHeatHotSetView mColdLeftShhsv;
    private SeatHeatHotSetView mColdRightShhsv;
    private final Context mContext;
    private SeatHeatHotSetView mHeatLeftShhsv;
    private SeatHeatHotSetView mHeatRightShhsv;
    private ImageView mLeftBlowAuto;
    private ImageView mLeftSeatIv;
    private String[][] mLineBtnAction;
    private OnAirPageStatusListener mOnAirPageStatusListener;
    private OnAirSeatClickListener mOnAirSeatClickListener;
    private ImageView mRightBlowAuto;
    private ImageView mRightSeatIv;
    private ImageView mSeatBackLeftTv;
    private ImageView mSeatBackRightTv;
    private ImageView mSeatBottomLeftIv;
    private ImageView mSeatBottomRightIv;
    private String[] mSeatColdValueRes;
    private String[] mSeatHeatValueRes;
    private LinearLayout mSwitchFrontRearLl;
    private TempSetView mTempSetViewCenter;
    private TempSetView mTempSetViewLeft;
    private TempSetView mTempSetViewRight;
    private LineBtnView mTopLbv;
    private final View mView;
    private SetWindSpeedView mWindSpeedWsv;
    private RearArea set;

    public AirRearView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout.layout_air_rear, this);
        findViews();
    }

    protected void showOrHide(View view, boolean z) {
        if (z) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    protected String getStringByName(String str) {
        Context context = this.mContext;
        return context.getString(CommUtil.getStrIdByResId(context, str));
    }

    private void findViews() {
        this.mWindSpeedWsv = this.mView.findViewById(R.id.swsv_left_wind_speed);
        this.mTempSetViewLeft = this.mView.findViewById(R.id.tsv_left);
        this.mTempSetViewCenter = this.mView.findViewById(R.id.tsv_center);
        this.mTempSetViewRight = this.mView.findViewById(R.id.tsv_right);
        this.mHeatLeftShhsv = this.mView.findViewById(R.id.sv_heat_left);
        this.mHeatRightShhsv = this.mView.findViewById(R.id.sv_heat_right);
        this.mColdLeftShhsv = this.mView.findViewById(R.id.sv_cold_left);
        this.mColdRightShhsv = this.mView.findViewById(R.id.sv_cold_right);
        this.mTopLbv = this.mView.findViewById(R.id.ll_top_0);
        this.mBottomLbv = this.mView.findViewById(R.id.lbv_bottom_0);
        this.mBottomLeftLbv = this.mView.findViewById(R.id.lbv_bottom_1_left);
        this.mBottomRightLbv = this.mView.findViewById(R.id.lbv_bottom_1_right);
        this.mBlowWindowsLeftIv = this.mView.findViewById(R.id.iv_left_blow_window);
        this.mBlowHeadLeftIv = this.mView.findViewById(R.id.iv_left_blow_head);
        this.mBlowFootLeftIv = this.mView.findViewById(R.id.iv_left_blow_foot);
        this.mSeatBottomLeftIv = this.mView.findViewById(R.id.iv_left_bottom);
        this.mSeatBackLeftTv = this.mView.findViewById(R.id.iv_left_back);
        this.mLeftBlowAuto = this.mView.findViewById(R.id.iv_left_blow_auto);
        this.mBlowWindowsRightIv = this.mView.findViewById(R.id.iv_right_blow_window);
        this.mBlowHeadRightIv = this.mView.findViewById(R.id.iv_right_blow_head);
        this.mBlowFootRightIv = this.mView.findViewById(R.id.iv_right_blow_foot);
        this.mSeatBottomRightIv = this.mView.findViewById(R.id.iv_right_bottom);
        this.mSeatBackRightTv = this.mView.findViewById(R.id.iv_right_back);
        this.mRightBlowAuto = this.mView.findViewById(R.id.iv_right_blow_auto);
        this.mSwitchFrontRearLl = this.mView.findViewById(R.id.ll_sw_front_area);
        this.mLeftSeatIv = this.mView.findViewById(R.id.iv_left_seat);
        this.mRightSeatIv = this.mView.findViewById(R.id.iv_right_seat);
    }

    public void initViews(AirActivity airActivity, AirPageUiSet airPageUiSet) {
        OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners;
        this.mActivity = airActivity;
        if (airActivity.isNeedSwitchTemAndSeat()) {
            switchTempSetView();
            switchSeatHeatHotSetView();
            switchSeatHeatHotSetView2();
            switchBlowWindowsIv();
            switchBlowHeadIv();
            switchBlowFootIv();
            switchSeatBottomIv();
            switchSeatBackIv();
            switchSeatView();
            switchBlowAutoIv();
        }
        this.set = airPageUiSet.getRearArea();
        this.mSwitchFrontRearLl.setOnClickListener(this);
        this.mLineBtnAction = this.set.getLineBtnAction();
        OnAirBtnClickListener[] onAirBtnClickListeners = this.set.getOnAirBtnClickListeners();
        if (onAirBtnClickListeners == null) {
            onAirBtnClickListeners = new OnAirBtnClickListener[]{null, null, null, null};
        }
        boolean zIsAllBtnCanClick = this.set.isAllBtnCanClick();
        String[] disableBtnS = this.set.getDisableBtnS();
        String[][] strArr = this.mLineBtnAction;
        if (strArr != null) {
            if (strArr.length > 0) {
                this.mTopLbv.initButton(this.mContext, strArr[0], zIsAllBtnCanClick, disableBtnS, onAirBtnClickListeners[0]);
            }
            String[][] strArr2 = this.mLineBtnAction;
            if (strArr2.length > 1) {
                this.mBottomLeftLbv.initButton(this.mContext, strArr2[1], zIsAllBtnCanClick, disableBtnS, onAirBtnClickListeners[1]);
            }
            String[][] strArr3 = this.mLineBtnAction;
            if (strArr3.length > 2) {
                this.mBottomRightLbv.initButton(this.mContext, strArr3[2], zIsAllBtnCanClick, disableBtnS, onAirBtnClickListeners[2]);
            }
            String[][] strArr4 = this.mLineBtnAction;
            if (strArr4.length > 3) {
                this.mBottomLbv.initButton(this.mContext, strArr4[3], zIsAllBtnCanClick, disableBtnS, onAirBtnClickListeners[3]);
            }
        }
        if (this.set.isShowSeatHeat()) {
            this.mHeatLeftShhsv.setVisibility(View.VISIBLE);
            this.mHeatLeftShhsv.setControllable(this.set.isCanSetSeatHeat());
            this.mHeatRightShhsv.setVisibility(View.VISIBLE);
            this.mHeatRightShhsv.setControllable(this.set.isCanSetSeatHeat());
            String stringByName = getStringByName(this.set.getSeatHeatSrnArray()[0]);
            this.mHeatLeftShhsv.setValue(stringByName);
            this.mHeatRightShhsv.setValue(stringByName);
            this.mSeatHeatValueRes = this.set.getSeatHeatSrnArray();
            OnAirSeatHeatColdMinPlusClickListener[] seatHeatColdClickListeners = this.set.getSeatHeatColdClickListeners();
            if (seatHeatColdClickListeners != null) {
                this.mHeatLeftShhsv.setOnUpDownClickListener(seatHeatColdClickListeners[0]);
                this.mHeatRightShhsv.setOnUpDownClickListener(seatHeatColdClickListeners[1]);
            }
        }
        if (this.set.isShowSeatCold()) {
            this.mColdLeftShhsv.setVisibility(View.VISIBLE);
            this.mColdLeftShhsv.setControllable(this.set.isCanSetSeatCold());
            this.mColdRightShhsv.setVisibility(View.VISIBLE);
            this.mColdRightShhsv.setControllable(this.set.isCanSetSeatCold());
            String stringByName2 = getStringByName(this.set.getSeatColdSrnArray()[0]);
            this.mColdLeftShhsv.setValue(stringByName2);
            this.mColdRightShhsv.setValue(stringByName2);
            this.mSeatColdValueRes = this.set.getSeatColdSrnArray();
            OnAirSeatHeatColdMinPlusClickListener[] seatHeatColdClickListeners2 = this.set.getSeatHeatColdClickListeners();
            if (seatHeatColdClickListeners2 != null) {
                this.mColdLeftShhsv.setOnUpDownClickListener(seatHeatColdClickListeners2[2]);
                this.mColdRightShhsv.setOnUpDownClickListener(seatHeatColdClickListeners2[3]);
            }
        }
        if (this.set.isCanSetTemp() && (tempSetViewOnUpDownClickListeners = this.set.getTempSetViewOnUpDownClickListeners()) != null) {
            this.mTempSetViewLeft.setOnUpDownClickListener(tempSetViewOnUpDownClickListeners[0]);
            this.mTempSetViewCenter.setOnUpDownClickListener(tempSetViewOnUpDownClickListeners[1]);
            this.mTempSetViewRight.setOnUpDownClickListener(tempSetViewOnUpDownClickListeners[2]);
        }
        this.mTempSetViewLeft.setControllable(this.set.isCanSetTemp());
        this.mTempSetViewCenter.setControllable(this.set.isCanSetTemp());
        this.mTempSetViewRight.setControllable(this.set.isCanSetTemp());
        showOrHide(this.mTempSetViewLeft, this.set.isShowLeftWheel());
        showOrHide(this.mTempSetViewCenter, this.set.isShowCenterWheel());
        showOrHide(this.mTempSetViewRight, this.set.isShowRightWheel());
        this.mWindSpeedWsv.initViews(this.mContext, this.set.isCanSetWindSpeed(), this.set.getWindMaxLevel(), this.set.getSetWindSpeedViewOnClickListener());
        OnAirSeatClickListener onAirSeatClickListener = this.set.getOnAirSeatClickListener();
        this.mOnAirSeatClickListener = onAirSeatClickListener;
        if (onAirSeatClickListener != null) {
            this.mLeftSeatIv.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirRearView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AirRearView.this.mOnAirSeatClickListener.onLeftSeatClick();
                }
            });
            this.mRightSeatIv.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirRearView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AirRearView.this.mOnAirSeatClickListener.onRightSeatClick();
                }
            });
        }
        refreshUi();
        if (AirActivity.mIsClickOpen) {
            OnAirPageStatusListener onAirPageStatusListener = this.set.getOnAirPageStatusListener();
            this.mOnAirPageStatusListener = onAirPageStatusListener;
            if (onAirPageStatusListener != null) {
                onAirPageStatusListener.onStatusChange(5);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void refreshUi() {
        if (this.mView == null) {
            LogUtil.showLog("rear fragment not init");
            return;
        }
        if (this.mLineBtnAction != null) {
            for (int i = 0; i < this.mLineBtnAction.length; i++) {
                String[] strArr = this.mLineBtnAction[i];
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    String str = strArr[i2];

                    // Se maneja el caso de los botones con un enfoque basado en una comparación directa
                    if (str.equals(AirBtnAction.RIGHT_SET_SEAT_COLD)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.right_set_seat_cold);
                    } else if (str.equals(AirBtnAction.RIGHT_SET_SEAT_HEAT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.right_set_seat_heat);
                    } else if (str.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.steering_wheel_heating);
                    } else if (str.equals(AirBtnAction.SMALL_WIND_LIGHT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.small_wind_light);
                    } else if (str.equals(AirBtnAction.AUTO_MANUAL)) {
                        getBtnItemView(i, i2).turn(true);
                        if (GeneralAirData.auto_manual) {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_auto_n);
                        } else {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_man_n);
                        }
                    } else if (str.equals(AirBtnAction.AUTO_2)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.auto_2);
                    } else if (str.equals(AirBtnAction.CLEAN_AIR)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.clean_air);
                    } else if (str.equals(AirBtnAction.AC_AUTO)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.ac_auto);
                    } else if (str.equals(AirBtnAction.REAR_AUTO)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear_auto);
                    } else if (str.equals(AirBtnAction.REAR_DUAL)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear_dual);
                    } else if (str.equals(AirBtnAction.REAR_LOCK)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear_lock);
                        if (GeneralAirData.rear_lock) {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_rearl_n);
                        } else {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_rearunl_n);
                        }
                    } else if (str.equals(AirBtnAction.REAR_SYNC)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear_sync);
                    } else if (str.equals(AirBtnAction.REAR_POWER)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear_power);
                    } else if (str.equals("blow_positive")) {
                        getBtnItemView(i, i2).turn(GeneralAirData.blow_positive);
                    } else if (str.equals(AirBtnAction.LEFT_SET_SEAT_COLD)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.left_set_seat_cold);
                    } else if (str.equals(AirBtnAction.LEFT_SET_SEAT_HEAT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.left_set_seat_heat);
                    } else if (str.equals(AirBtnAction.BLOW_NEGATIVE)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.blow_negative);
                    } else if (str.equals(AirBtnAction.SEAT_STEERING_WHEEL_SYNCHRONIZATION)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.seat_steering_wheel_synchronization);
                    } else if (str.equals(AirBtnAction.MAX_FRONT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.max_front);
                    } else if (str.equals(AirBtnAction.BIG_WIND_LIGHT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.big_wind_light);
                    } else if (str.equals(AirBtnAction.FRONT_WINDOW_HEAT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.front_window_heat);
                    } else if (str.equals(AirBtnAction.AUTO_CYCLE)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.auto_cycle);
                    } else if (str.equals(AirBtnAction.AUTO_DEFOG)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.auto_defog);
                    } else if (str.equals("ac")) {
                        getBtnItemView(i, i2).turn(GeneralAirData.ac);
                    } else if (str.equals(AirBtnAction.AMB)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.amb);
                    } else if (str.equals(AirBtnAction.AQS)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.aqs);
                    } else if (str.equals(AirBtnAction.ECO)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.eco);
                    } else if (str.equals(AirBtnAction.ION)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.ion);
                    } else if (str.equals("auto")) {
                        getBtnItemView(i, i2).turn(GeneralAirData.auto);
                    } else if (str.equals("dual")) {
                        getBtnItemView(i, i2).turn(GeneralAirData.dual);
                    } else if (str.equals(AirBtnAction.ECON)) {
                        getBtnItemView(i, i2).turn(true);
                        getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_ac_n);
                        if (GeneralAirData.ac_econ == 0) {
                            getBtnItemView(i, i2).turn(false);
                            break;
                        } else if (GeneralAirData.ac_econ == 1) {
                            getBtnItemView(i, i2).turn(true);
                            break;
                        } else {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_econ_n);
                            break;
                        }
                    } else if (str.equals(AirBtnAction.MONO)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.mono);
                    } else if (str.equals(AirBtnAction.REAR)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear);
                    } else if (str.equals("sync")) {
                        getBtnItemView(i, i2).turn(GeneralAirData.sync);
                    } else if (str.equals(AirBtnAction.SYNC_TEMPERATURE)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.sync_temperature);
                    } else if (str.equals(AirBtnAction.IN_OUT_AUTO_CYCLE)) {
                        if (GeneralAirData.in_out_auto_cycle == 0) {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_caro_n);
                            break;
                        } else if (GeneralAirData.in_out_auto_cycle == 1) {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_carin_n);
                            break;
                        } else {
                            getBtnItemView(i, i2).setImageResource(R.drawable.auto_in_out_cycle);
                            break;
                        }
                    } else if (str.equals(AirBtnAction.MAX_COOL)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.max_cool);
                    } else if (str.equals(AirBtnAction.MAX_HEAT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.max_heat);
                    } else if (str.equals("in_out_cycle")) {
                        if (GeneralAirData.in_out_cycle) {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_caro_n);
                            break;
                        } else {
                            getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_carin_n);
                            break;
                        }
                    } else if (str.equals(AirBtnAction.CLIMATE)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.climate);
                    } else if (str.equals(AirBtnAction.AUTO_WIND_MODE)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.rear_auto_wind_model);
                    } else if (str.equals(AirBtnAction.AUTO_WIND_LIGHT)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.auto_wind_light);
                    } else if (str.equals(AirBtnAction.AUTO_WIND_STRONG)) {
                        getBtnItemView(i, i2).turn(GeneralAirData.auto_wind_strong);
                    }
                }
            }
        }
        this.mWindSpeedWsv.setAuto(GeneralAirData.rear_auto_wind_speed);
        this.mWindSpeedWsv.setCurWindSpeed(GeneralAirData.rear_wind_level);
        this.mTempSetViewLeft.setValue(GeneralAirData.rear_left_temperature);
        this.mTempSetViewRight.setValue(GeneralAirData.rear_right_temperature);
        this.mTempSetViewCenter.setValue(GeneralAirData.rear_temperature);
        if (this.set.isShowSeatHeat()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
                setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.rear_left_seat_heat_level > 0, R.drawable.img_air_m_hot2);
                setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.rear_right_seat_heat_level > 0, R.drawable.img_air_m_hot1);
            } else {
                setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.rear_left_seat_heat_level > 0, R.drawable.img_air_m_hot1);
                setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.rear_right_seat_heat_level > 0, R.drawable.img_air_m_hot2);
            }
            setIvShowImgOrNot(this.mSeatBottomLeftIv, GeneralAirData.rear_left_seat_heat_level > 0, R.drawable.img_air_m_hot);
            setIvShowImgOrNot(this.mSeatBottomRightIv, GeneralAirData.rear_right_seat_heat_level > 0, R.drawable.img_air_m_hot);
            this.mHeatLeftShhsv.setValue(getStringByName(this.mSeatHeatValueRes[GeneralAirData.rear_left_seat_heat_level]));
            this.mHeatRightShhsv.setValue(getStringByName(this.mSeatHeatValueRes[GeneralAirData.rear_right_seat_heat_level]));
        }
        if (this.set.isShowSeatCold()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
                if (GeneralAirData.rear_left_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.rear_left_seat_cold_level > 0, R.drawable.img_air_m_co2);
                }
                if (GeneralAirData.rear_right_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.rear_right_seat_cold_level > 0, R.drawable.img_air_m_co1);
                }
            } else {
                if (GeneralAirData.rear_left_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.rear_left_seat_cold_level > 0, R.drawable.img_air_m_co1);
                }
                if (GeneralAirData.rear_right_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.rear_right_seat_cold_level > 0, R.drawable.img_air_m_co2);
                }
            }
            if (GeneralAirData.rear_left_seat_heat_level <= 0) {
                setIvShowImgOrNot(this.mSeatBottomLeftIv, GeneralAirData.rear_left_seat_cold_level > 0, R.drawable.img_air_m_co);
            }
            if (GeneralAirData.rear_right_seat_heat_level <= 0) {
                setIvShowImgOrNot(this.mSeatBottomRightIv, GeneralAirData.rear_right_seat_cold_level > 0, R.drawable.img_air_m_co);
            }
            this.mColdLeftShhsv.setValue(getStringByName(this.mSeatColdValueRes[GeneralAirData.rear_left_seat_cold_level]));
            this.mColdRightShhsv.setValue(getStringByName(this.mSeatColdValueRes[GeneralAirData.rear_right_seat_cold_level]));
        }
        if (this.mActivity.isNeedSwitchTemAndSeat()) {
            setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.rear_left_blow_head, R.drawable.img_air_mr_co1);
            setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.rear_right_blow_head, R.drawable.img_air_ml_co1);
        } else {
            setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.rear_left_blow_head, R.drawable.img_air_ml_co1);
            setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.rear_right_blow_head, R.drawable.img_air_mr_co1);
        }
        setIvShowImgOrNot(this.mBlowWindowsLeftIv, GeneralAirData.rear_left_blow_window, R.drawable.img_air_mf_wind);
        setIvShowImgOrNot(this.mBlowFootLeftIv, GeneralAirData.rear_left_blow_foot, R.drawable.img_air_ml_co2);
        setIvShowImgOrNot(this.mBlowWindowsRightIv, GeneralAirData.rear_right_blow_window, R.drawable.img_air_mf_wind);
        setIvShowImgOrNot(this.mBlowFootRightIv, GeneralAirData.rear_right_blow_foot, R.drawable.img_air_ml_co2);
        setIvShowImgOrNot(this.mLeftBlowAuto, GeneralAirData.rear_left_auto_wind, R.drawable.img_air_auto_wind);
        setIvShowImgOrNot(this.mRightBlowAuto, GeneralAirData.rear_right_auto_wind, R.drawable.img_air_auto_wind);
    }

    private void setIvShowImgOrNot(ImageView imageView, boolean z, int i) {
        if (z) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(i);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    private BtnItemView getBtnItemView(int i, int i2) {
        if (i == 0) {
            return this.mTopLbv.getBtnItemView(i2);
        }
        if (i == 1) {
            return this.mBottomLeftLbv.getBtnItemView(i2);
        }
        if (i == 2) {
            return this.mBottomRightLbv.getBtnItemView(i2);
        }
        if (i != 3) {
            return null;
        }
        return this.mBottomLbv.getBtnItemView(i2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AirActivity airActivity;
        if (view.getId() != R.id.ll_sw_front_area || (airActivity = this.mActivity) == null || airActivity.isFinishing() || this.mActivity.isDestroyed()) {
            return;
        }
        this.mActivity.switchViewPager(0);
    }

    private void switchSeatBackIv() {
        ImageView imageView = this.mSeatBackLeftTv;
        this.mSeatBackLeftTv = this.mSeatBackRightTv;
        this.mSeatBackRightTv = imageView;
        Matrix matrix = new Matrix();
        matrix.setRotate(90.0f);
        this.mSeatBackLeftTv.setImageMatrix(matrix);
        this.mSeatBackRightTv.setImageMatrix(matrix);
    }

    private void switchSeatView() {
        ImageView imageView = this.mLeftSeatIv;
        this.mLeftSeatIv = this.mRightSeatIv;
        this.mRightSeatIv = imageView;
    }

    private void switchSeatBottomIv() {
        ImageView imageView = this.mSeatBottomLeftIv;
        this.mSeatBottomLeftIv = this.mSeatBottomRightIv;
        this.mSeatBottomRightIv = imageView;
    }

    private void switchBlowFootIv() {
        ImageView imageView = this.mBlowFootLeftIv;
        this.mBlowFootLeftIv = this.mBlowFootRightIv;
        this.mBlowFootRightIv = imageView;
    }

    private void switchBlowHeadIv() {
        ImageView imageView = this.mBlowHeadLeftIv;
        this.mBlowHeadLeftIv = this.mBlowHeadRightIv;
        this.mBlowHeadRightIv = imageView;
    }

    private void switchBlowWindowsIv() {
        ImageView imageView = this.mBlowWindowsLeftIv;
        this.mBlowWindowsLeftIv = this.mBlowWindowsRightIv;
        this.mBlowWindowsRightIv = imageView;
    }

    private void switchTempSetView() {
        TempSetView tempSetView = this.mTempSetViewLeft;
        this.mTempSetViewLeft = this.mTempSetViewRight;
        this.mTempSetViewRight = tempSetView;
    }

    private void switchSeatHeatHotSetView() {
        SeatHeatHotSetView seatHeatHotSetView = this.mHeatLeftShhsv;
        this.mHeatLeftShhsv = this.mHeatRightShhsv;
        this.mHeatRightShhsv = seatHeatHotSetView;
    }

    private void switchSeatHeatHotSetView2() {
        SeatHeatHotSetView seatHeatHotSetView = this.mColdLeftShhsv;
        this.mColdLeftShhsv = this.mColdRightShhsv;
        this.mColdRightShhsv = seatHeatHotSetView;
    }

    private void switchBlowAutoIv() {
        ImageView imageView = this.mLeftBlowAuto;
        this.mLeftBlowAuto = this.mRightBlowAuto;
        this.mRightBlowAuto = imageView;
    }
}
