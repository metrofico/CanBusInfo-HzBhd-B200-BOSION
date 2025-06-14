package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_set.AirBtnAction;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;

import java.util.Locale;

import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class AirFrontView extends RelativeLayout implements View.OnClickListener {
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
    private Context mContext;
    private SeatHeatHotSetView mHeatLeftShhsv;
    private SeatHeatHotSetView mHeatRightShhsv;
    private String mLanguage;
    private ImageView mLeftBlowAuto;
    private ImageView mLeftSeatIv;
    private SetWindSpeedView mLeftWindSpeedWsv;
    private String[][] mLineBtnAction;
    private LinearLayout mLlSeatCold;
    private LinearLayout mLlSeatHeat;
    private Locale mLocale;
    private OnAirPageStatusListener mOnAirPageStatusListener;
    private OnAirSeatClickListener mOnAirSeatClickListener;
    private TextView mPmInValueTv;
    private LinearLayout mPmLayoutLl;
    private TextView mPmOutValueTv;
    private ImageView mRightBlowAuto;
    private ImageView mRightSeatIv;
    private SetWindSpeedView mRightWindSpeedWsv;
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
    private View mView;
    private FrontArea set;

    public AirFrontView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout.layout_air_front, this);
        findViews();
    }

    private void findViews() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        this.mLocale = locale;
        this.mLanguage = locale.getLanguage();
        this.mPmLayoutLl = (LinearLayout) this.mView.findViewById(R.id.ll_pm_layout);
        this.mPmInValueTv = (TextView) this.mView.findViewById(R.id.tv_pm_in_value);
        this.mPmOutValueTv = (TextView) this.mView.findViewById(R.id.tv_pm_out_value);
        this.mLeftWindSpeedWsv = (SetWindSpeedView) this.mView.findViewById(R.id.swsv_left_wind_speed);
        this.mRightWindSpeedWsv = (SetWindSpeedView) this.mView.findViewById(R.id.swsv_right_wind_speed);
        this.mTempSetViewLeft = (TempSetView) this.mView.findViewById(R.id.tsv_left);
        this.mTempSetViewCenter = (TempSetView) this.mView.findViewById(R.id.tsv_center);
        this.mTempSetViewRight = (TempSetView) this.mView.findViewById(R.id.tsv_right);
        this.mLlSeatHeat = (LinearLayout) this.mView.findViewById(R.id.ll_seat_heat);
        this.mLlSeatCold = (LinearLayout) this.mView.findViewById(R.id.ll_seat_cold);
        this.mHeatLeftShhsv = (SeatHeatHotSetView) this.mView.findViewById(R.id.sv_heat_left);
        this.mHeatRightShhsv = (SeatHeatHotSetView) this.mView.findViewById(R.id.sv_heat_right);
        this.mColdLeftShhsv = (SeatHeatHotSetView) this.mView.findViewById(R.id.sv_cold_left);
        this.mColdRightShhsv = (SeatHeatHotSetView) this.mView.findViewById(R.id.sv_cold_right);
        this.mTopLbv = (LineBtnView) this.mView.findViewById(R.id.ll_top_0);
        this.mBottomLbv = (LineBtnView) this.mView.findViewById(R.id.lbv_bottom_0);
        this.mBottomLeftLbv = (LineBtnView) this.mView.findViewById(R.id.lbv_bottom_1_left);
        this.mBottomRightLbv = (LineBtnView) this.mView.findViewById(R.id.lbv_bottom_1_right);
        this.mLeftSeatIv = (ImageView) this.mView.findViewById(R.id.iv_left_seat);
        this.mBlowWindowsLeftIv = (ImageView) this.mView.findViewById(R.id.iv_left_blow_window);
        this.mBlowHeadLeftIv = (ImageView) this.mView.findViewById(R.id.iv_left_blow_head);
        this.mBlowFootLeftIv = (ImageView) this.mView.findViewById(R.id.iv_left_blow_foot);
        this.mLeftBlowAuto = (ImageView) this.mView.findViewById(R.id.iv_left_blow_auto);
        this.mSeatBottomLeftIv = (ImageView) this.mView.findViewById(R.id.iv_left_bottom);
        this.mSeatBackLeftTv = (ImageView) this.mView.findViewById(R.id.iv_left_back);
        this.mRightSeatIv = (ImageView) this.mView.findViewById(R.id.iv_right_seat);
        this.mBlowWindowsRightIv = (ImageView) this.mView.findViewById(R.id.iv_right_blow_window);
        this.mBlowHeadRightIv = (ImageView) this.mView.findViewById(R.id.iv_right_blow_head);
        this.mBlowFootRightIv = (ImageView) this.mView.findViewById(R.id.iv_right_blow_foot);
        this.mRightBlowAuto = (ImageView) this.mView.findViewById(R.id.iv_right_blow_auto);
        this.mSeatBottomRightIv = (ImageView) this.mView.findViewById(R.id.iv_right_bottom);
        this.mSeatBackRightTv = (ImageView) this.mView.findViewById(R.id.iv_right_back);
        this.mSwitchFrontRearLl = (LinearLayout) this.mView.findViewById(R.id.ll_sw_front_area);
    }

    protected void showOrHide(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    protected String getStringByName(String str) {
        Context context = this.mContext;
        return context.getString(CommUtil.getStrIdByResId(context, str));
    }

    public void initViews(AirActivity airActivity, AirPageUiSet airPageUiSet) {
        this.mActivity = airActivity;
        if (airActivity.isNeedSwitchTemAndSeat()) {
            switchTempSetView();
            switchSeatHeatHotSetView();
            switchSeatHeatHotSetView2();
            switchSeatView();
            switchBlowWindowsIv();
            switchBlowHeadIv();
            switchBlowFootIv();
            switchSeatBottomIv();
            switchSeatBackIv();
            switchBlowAutoIv();
            switchWindLevelSetView(airPageUiSet);
        }
        if (airPageUiSet == null) {
            return;
        }
        LogUtil.showLog("holdSet.isHaveRearArea():" + airPageUiSet.isHaveRearArea());
        if (airPageUiSet.isHaveRearArea()) {
            this.mSwitchFrontRearLl.setVisibility(0);
            this.mSwitchFrontRearLl.setOnClickListener(this);
        } else {
            this.mSwitchFrontRearLl.setVisibility(8);
        }
        FrontArea frontArea = airPageUiSet.getFrontArea();
        this.set = frontArea;
        if (frontArea == null) {
            return;
        }
        if (frontArea.isShowPmValue()) {
            this.mPmLayoutLl.setVisibility(0);
        }
        this.mLineBtnAction = this.set.getLineBtnAction();
        OnAirBtnClickListener[] onAirBtnClickListeners = this.set.getOnAirBtnClickListeners();
        if (onAirBtnClickListeners == null) {
            onAirBtnClickListeners = new OnAirBtnClickListener[]{null, null, null, null};
        }
        boolean zIsAllBtnCanClick = this.set.isAllBtnCanClick();
        String[] disableBtnArray = this.set.getDisableBtnArray();
        String[][] strArr = this.mLineBtnAction;
        if (strArr != null) {
            if (strArr.length > 0) {
                this.mTopLbv.initButton(this.mContext, strArr[0], zIsAllBtnCanClick, disableBtnArray, onAirBtnClickListeners[0]);
            }
            String[][] strArr2 = this.mLineBtnAction;
            if (strArr2.length > 1) {
                this.mBottomLeftLbv.initButton(this.mContext, strArr2[1], zIsAllBtnCanClick, disableBtnArray, onAirBtnClickListeners[1]);
            }
            String[][] strArr3 = this.mLineBtnAction;
            if (strArr3.length > 2) {
                this.mBottomRightLbv.initButton(this.mContext, strArr3[2], zIsAllBtnCanClick, disableBtnArray, onAirBtnClickListeners[2]);
            }
            String[][] strArr4 = this.mLineBtnAction;
            if (strArr4.length > 3) {
                this.mBottomLbv.initButton(this.mContext, strArr4[3], zIsAllBtnCanClick, disableBtnArray, onAirBtnClickListeners[3]);
            }
        }
        if (this.set.isShowSeatHeat()) {
            this.mHeatLeftShhsv.setVisibility(0);
            this.mHeatLeftShhsv.setControllable(this.set.isCanSetSeatHeat());
            this.mHeatRightShhsv.setVisibility(0);
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
        } else if (airActivity.getResources().getConfiguration().orientation == 1) {
            this.mLlSeatHeat.setVisibility(8);
        }
        if (this.set.isShowSeatCold()) {
            this.mColdLeftShhsv.setVisibility(0);
            this.mColdRightShhsv.setVisibility(0);
            this.mColdLeftShhsv.setControllable(this.set.isCanSetSeatCold());
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
        } else if (airActivity.getResources().getConfiguration().orientation == 1) {
            this.mLlSeatCold.setVisibility(8);
        }
        if (this.set.isCanSetLeftTemp()) {
            OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners = this.set.getTempSetViewOnUpDownClickListeners();
            OnAirTempTouchListener[] tempTouchListeners = this.set.getTempTouchListeners();
            if (tempSetViewOnUpDownClickListeners != null) {
                this.mTempSetViewLeft.setOnUpDownClickListener(tempSetViewOnUpDownClickListeners[0]);
            }
            if (tempTouchListeners != null) {
                this.mTempSetViewLeft.setOnUpDownTouchListener(tempTouchListeners[0]);
            }
        }
        if (this.set.isCanSetCenterTemp()) {
            OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners2 = this.set.getTempSetViewOnUpDownClickListeners();
            OnAirTempTouchListener[] tempTouchListeners2 = this.set.getTempTouchListeners();
            if (tempSetViewOnUpDownClickListeners2 != null) {
                this.mTempSetViewCenter.setOnUpDownClickListener(tempSetViewOnUpDownClickListeners2[1]);
            }
            if (tempTouchListeners2 != null) {
                this.mTempSetViewCenter.setOnUpDownTouchListener(tempTouchListeners2[1]);
            }
        }
        if (this.set.isCanSetRightTemp()) {
            OnAirTemperatureUpDownClickListener[] tempSetViewOnUpDownClickListeners3 = this.set.getTempSetViewOnUpDownClickListeners();
            OnAirTempTouchListener[] tempTouchListeners3 = this.set.getTempTouchListeners();
            if (tempSetViewOnUpDownClickListeners3 != null) {
                this.mTempSetViewRight.setOnUpDownClickListener(tempSetViewOnUpDownClickListeners3[2]);
            }
            if (tempTouchListeners3 != null) {
                this.mTempSetViewRight.setOnUpDownTouchListener(tempTouchListeners3[2]);
            }
        }
        this.mTempSetViewLeft.setControllable(this.set.isCanSetLeftTemp());
        this.mTempSetViewCenter.setControllable(this.set.isCanSetCenterTemp());
        this.mTempSetViewRight.setControllable(this.set.isCanSetRightTemp());
        showOrHide(this.mTempSetViewLeft, this.set.isShowLeftWheel());
        showOrHide(this.mTempSetViewCenter, this.set.isShowCenterWheel());
        showOrHide(this.mTempSetViewRight, this.set.isShowRightWheel());
        this.mLeftWindSpeedWsv.initViews(this.mContext, this.set.isCanSetWindSpeed(), this.set.getWindMaxLevel(), this.set.getSetWindSpeedViewOnClickListener());
        if (this.set.getIsHaveLeftRightWindSpeed()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
                this.mLeftWindSpeedWsv.setVisibility(0);
            } else {
                this.mRightWindSpeedWsv.setVisibility(0);
            }
            this.mRightWindSpeedWsv.initViews(this.mContext, this.set.isCanSetWindSpeed(), this.set.getWindMaxLevel(), this.set.getSetRightWindSpeedViewOnClickListener());
        }
        OnAirSeatClickListener onAirSeatClickListener = this.set.getOnAirSeatClickListener();
        this.mOnAirSeatClickListener = onAirSeatClickListener;
        if (onAirSeatClickListener != null) {
            this.mLeftSeatIv.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirFrontView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AirFrontView.this.mOnAirSeatClickListener.onLeftSeatClick();
                }
            });
            this.mRightSeatIv.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirFrontView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AirFrontView.this.mOnAirSeatClickListener.onRightSeatClick();
                }
            });
        }
        refreshUi();
        if (AirActivity.mIsClickOpen) {
            OnAirPageStatusListener onAirPageStatusListener = this.set.getOnAirPageStatusListener();
            this.mOnAirPageStatusListener = onAirPageStatusListener;
            if (onAirPageStatusListener != null) {
                onAirPageStatusListener.onStatusChange(1);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void refreshUi() {
        if (this.mView == null) {
            LogUtil.showLog("front fragment not init");
            return;
        }
        if (this.mLineBtnAction != null) {
            for (int i = 0; i < this.mLineBtnAction.length; i++) {
                int i2 = 0;
                while (true) {
                    String[] strArr = this.mLineBtnAction[i];
                    if (i2 < strArr.length) {
                        String str = strArr[i2];
                        str.hashCode();
                        char c = 65535;
                        switch (str.hashCode()) {
                            case -1878365090:
                                if (str.equals(AirBtnAction.RIGHT_SET_SEAT_COLD)) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case -1878226070:
                                if (str.equals(AirBtnAction.RIGHT_SET_SEAT_HEAT)) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case -1786872896:
                                if (str.equals(AirBtnAction.STEERING_WHEEL_HEATING)) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case -1761278488:
                                if (str.equals(AirBtnAction.POLLRN_REMOVAL)) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case -1640478633:
                                if (str.equals(AirBtnAction.SMALL_WIND_LIGHT)) {
                                    c = 4;
                                    break;
                                }
                                break;
                            case -1604835367:
                                if (str.equals(AirBtnAction.WINDSHIELD_DEICING)) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case -1470045433:
                                if (str.equals("front_defog")) {
                                    c = 6;
                                    break;
                                }
                                break;
                            case -1428679594:
                                if (str.equals(AirBtnAction.AUTO_MANUAL)) {
                                    c = 7;
                                    break;
                                }
                                break;
                            case -1423573049:
                                if (str.equals(AirBtnAction.AC_MAX)) {
                                    c = '\b';
                                    break;
                                }
                                break;
                            case -1406322270:
                                if (str.equals(AirBtnAction.AUTO_2)) {
                                    c = '\t';
                                    break;
                                }
                                break;
                            case -1274277292:
                                if (str.equals(AirBtnAction.CLEAN_AIR)) {
                                    c = '\n';
                                    break;
                                }
                                break;
                            case -1181429844:
                                if (str.equals(AirBtnAction.AC_AUTO)) {
                                    c = 11;
                                    break;
                                }
                                break;
                            case -1181328134:
                                if (str.equals(AirBtnAction.AC_ECON)) {
                                    c = '\f';
                                    break;
                                }
                                break;
                            case -1081415738:
                                if (str.equals(AirBtnAction.MANUAL)) {
                                    c = '\r';
                                    break;
                                }
                                break;
                            case -1073935955:
                                if (str.equals(AirBtnAction.THREE_ZONE)) {
                                    c = 14;
                                    break;
                                }
                                break;
                            case -1039745817:
                                if (str.equals(AirBtnAction.NORMAL)) {
                                    c = 15;
                                    break;
                                }
                                break;
                            case -825767279:
                                if (str.equals(AirBtnAction.AUTO_WIND_LV)) {
                                    c = 16;
                                    break;
                                }
                                break;
                            case -712865050:
                                if (str.equals(AirBtnAction.REAR_LOCK)) {
                                    c = 17;
                                    break;
                                }
                                break;
                            case -631663038:
                                if (str.equals("rear_defog")) {
                                    c = 18;
                                    break;
                                }
                                break;
                            case -597744666:
                                if (str.equals("blow_positive")) {
                                    c = 19;
                                    break;
                                }
                                break;
                            case -479653111:
                                if (str.equals(AirBtnAction.LEFT_SET_SEAT_COLD)) {
                                    c = 20;
                                    break;
                                }
                                break;
                            case -479514091:
                                if (str.equals(AirBtnAction.LEFT_SET_SEAT_HEAT)) {
                                    c = 21;
                                    break;
                                }
                                break;
                            case -424438238:
                                if (str.equals(AirBtnAction.BLOW_NEGATIVE)) {
                                    c = 22;
                                    break;
                                }
                                break;
                            case -386400856:
                                if (str.equals(AirBtnAction.SEAT_STEERING_WHEEL_SYNCHRONIZATION)) {
                                    c = 23;
                                    break;
                                }
                                break;
                            case -246396018:
                                if (str.equals(AirBtnAction.MAX_FRONT)) {
                                    c = 24;
                                    break;
                                }
                                break;
                            case -148776258:
                                if (str.equals(AirBtnAction.BIG_WIND_LIGHT)) {
                                    c = 25;
                                    break;
                                }
                                break;
                            case -92674103:
                                if (str.equals(AirBtnAction.FRONT_WINDOW_HEAT)) {
                                    c = 26;
                                    break;
                                }
                                break;
                            case -54617514:
                                if (str.equals(AirBtnAction.AUTO_CYCLE)) {
                                    c = 27;
                                    break;
                                }
                                break;
                            case -54286835:
                                if (str.equals(AirBtnAction.AUTO_DEFOG)) {
                                    c = 28;
                                    break;
                                }
                                break;
                            case 3106:
                                if (str.equals("ac")) {
                                    c = 29;
                                    break;
                                }
                                break;
                            case 96694:
                                if (str.equals(AirBtnAction.AMB)) {
                                    c = 30;
                                    break;
                                }
                                break;
                            case 96835:
                                if (str.equals(AirBtnAction.AQS)) {
                                    c = 31;
                                    break;
                                }
                                break;
                            case 100241:
                                if (str.equals(AirBtnAction.ECO)) {
                                    c = ' ';
                                    break;
                                }
                                break;
                            case 104456:
                                if (str.equals(AirBtnAction.ION)) {
                                    c = '!';
                                    break;
                                }
                                break;
                            case 1782194:
                                if (str.equals(AirBtnAction.LEFT_SEAT_HEAT)) {
                                    c = Typography.quote;
                                    break;
                                }
                                break;
                            case 3005871:
                                if (str.equals("auto")) {
                                    c = '#';
                                    break;
                                }
                                break;
                            case 3094652:
                                if (str.equals("dual")) {
                                    c = Typography.dollar;
                                    break;
                                }
                                break;
                            case 3107581:
                                if (str.equals(AirBtnAction.ECON)) {
                                    c = '%';
                                    break;
                                }
                                break;
                            case 3135580:
                                if (str.equals(AirBtnAction.FAST)) {
                                    c = Typography.amp;
                                    break;
                                }
                                break;
                            case 3357411:
                                if (str.equals(AirBtnAction.MONO)) {
                                    c = '\'';
                                    break;
                                }
                                break;
                            case 3496356:
                                if (str.equals(AirBtnAction.REAR)) {
                                    c = '(';
                                    break;
                                }
                                break;
                            case 3496916:
                                if (str.equals(AirBtnAction.REST)) {
                                    c = ')';
                                    break;
                                }
                                break;
                            case 3535914:
                                if (str.equals(AirBtnAction.SOFT)) {
                                    c = '*';
                                    break;
                                }
                                break;
                            case 3545755:
                                if (str.equals("sync")) {
                                    c = '+';
                                    break;
                                }
                                break;
                            case 88944080:
                                if (str.equals(AirBtnAction.SYNC_TEMPERATURE)) {
                                    c = ',';
                                    break;
                                }
                                break;
                            case 99489345:
                                if (str.equals(AirBtnAction.IN_OUT_AUTO_CYCLE)) {
                                    c = '-';
                                    break;
                                }
                                break;
                            case 106858757:
                                if (str.equals("power")) {
                                    c = '.';
                                    break;
                                }
                                break;
                            case 109854462:
                                if (str.equals(AirBtnAction.SWING)) {
                                    c = '/';
                                    break;
                                }
                                break;
                            case 349917670:
                                if (str.equals(AirBtnAction.CYCLE_IN_OUT_CLOSE)) {
                                    c = '0';
                                    break;
                                }
                                break;
                            case 395882750:
                                if (str.equals(AirBtnAction.NEGATIVE_ION)) {
                                    c = '1';
                                    break;
                                }
                                break;
                            case 407601476:
                                if (str.equals(AirBtnAction.MAX_COOL)) {
                                    c = '2';
                                    break;
                                }
                                break;
                            case 407740395:
                                if (str.equals(AirBtnAction.MAX_HEAT)) {
                                    c = '3';
                                    break;
                                }
                                break;
                            case 756225563:
                                if (str.equals("in_out_cycle")) {
                                    c = '4';
                                    break;
                                }
                                break;
                            case 860813349:
                                if (str.equals(AirBtnAction.CLIMATE)) {
                                    c = '5';
                                    break;
                                }
                                break;
                            case 1006620906:
                                if (str.equals(AirBtnAction.AUTO_WIND_MODE)) {
                                    c = '6';
                                    break;
                                }
                                break;
                            case 1139377839:
                                if (str.equals(AirBtnAction.AUTO_WIND_LIGHT)) {
                                    c = '7';
                                    break;
                                }
                                break;
                            case 1171871774:
                                if (str.equals(AirBtnAction.AUTO_WIND_STRONG)) {
                                    c = '8';
                                    break;
                                }
                                break;
                            case 1252031879:
                                if (str.equals(AirBtnAction.RIGHT_SEAT_HEAT)) {
                                    c = '9';
                                    break;
                                }
                                break;
                            case 1438998804:
                                if (str.equals(AirBtnAction.AUTO_1_2)) {
                                    c = ':';
                                    break;
                                }
                                break;
                            case 1574763845:
                                if (str.equals(AirBtnAction.HYBRID_AC)) {
                                    c = ';';
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                getBtnItemView(i, i2).turn(GeneralAirData.right_set_seat_cold);
                                break;
                            case 1:
                                getBtnItemView(i, i2).turn(GeneralAirData.right_set_seat_heat);
                                break;
                            case 2:
                                getBtnItemView(i, i2).turn(GeneralAirData.steering_wheel_heating);
                                break;
                            case 3:
                                getBtnItemView(i, i2).turn(GeneralAirData.pollrn_removal);
                                break;
                            case 4:
                                getBtnItemView(i, i2).turn(GeneralAirData.small_wind_light);
                                break;
                            case 5:
                                getBtnItemView(i, i2).turn(GeneralAirData.windshield_deicing);
                                break;
                            case 6:
                                getBtnItemView(i, i2).turn(GeneralAirData.front_defog);
                                break;
                            case 7:
                                getBtnItemView(i, i2).turn(true);
                                if (GeneralAirData.auto_manual) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_auto_n);
                                    break;
                                } else {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_man_n);
                                    break;
                                }
                            case '\b':
                                getBtnItemView(i, i2).turn(GeneralAirData.ac_max);
                                break;
                            case '\t':
                                getBtnItemView(i, i2).turn(GeneralAirData.auto_2);
                                break;
                            case '\n':
                                getBtnItemView(i, i2).turn(GeneralAirData.clean_air);
                                break;
                            case 11:
                                getBtnItemView(i, i2).turn(GeneralAirData.ac_auto);
                                break;
                            case '\f':
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
                            case '\r':
                                getBtnItemView(i, i2).turn(GeneralAirData.manual);
                                break;
                            case 14:
                                getBtnItemView(i, i2).turn(GeneralAirData.threeZone);
                                break;
                            case 15:
                                getBtnItemView(i, i2).turn(GeneralAirData.normal);
                                break;
                            case 16:
                                getBtnItemView(i, i2).turn(true);
                                if (this.mLanguage.contains("zh")) {
                                    if (GeneralAirData.auto_wind_lv == 0) {
                                        getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_ruo_n);
                                        break;
                                    } else if (GeneralAirData.auto_wind_lv == 1) {
                                        getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_zhong_n);
                                        break;
                                    } else {
                                        getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_qiang_n);
                                        break;
                                    }
                                } else if (GeneralAirData.auto_wind_lv == 0) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_winl_n);
                                    break;
                                } else if (GeneralAirData.auto_wind_lv == 1) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_windme_n);
                                    break;
                                } else {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_windst_n);
                                    break;
                                }
                            case 17:
                                getBtnItemView(i, i2).turn(true);
                                if (GeneralAirData.rear_lock) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_rearl_n);
                                    break;
                                } else {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_rearunl_n);
                                    break;
                                }
                            case 18:
                                getBtnItemView(i, i2).turn(GeneralAirData.rear_defog);
                                break;
                            case 19:
                                getBtnItemView(i, i2).turn(GeneralAirData.blow_positive);
                                break;
                            case 20:
                                getBtnItemView(i, i2).turn(GeneralAirData.left_set_seat_cold);
                                break;
                            case 21:
                                getBtnItemView(i, i2).turn(GeneralAirData.left_set_seat_heat);
                                break;
                            case 22:
                                getBtnItemView(i, i2).turn(GeneralAirData.blow_negative);
                                break;
                            case 23:
                                getBtnItemView(i, i2).turn(GeneralAirData.seat_steering_wheel_synchronization);
                                break;
                            case 24:
                                getBtnItemView(i, i2).turn(GeneralAirData.max_front);
                                break;
                            case 25:
                                getBtnItemView(i, i2).turn(GeneralAirData.big_wind_light);
                                break;
                            case 26:
                                getBtnItemView(i, i2).turn(GeneralAirData.front_window_heat);
                                break;
                            case 27:
                                getBtnItemView(i, i2).turn(GeneralAirData.auto_cycle);
                                break;
                            case 28:
                                getBtnItemView(i, i2).turn(GeneralAirData.auto_defog);
                                break;
                            case 29:
                                getBtnItemView(i, i2).turn(GeneralAirData.ac);
                                break;
                            case 30:
                                getBtnItemView(i, i2).turn(GeneralAirData.amb);
                                break;
                            case 31:
                                getBtnItemView(i, i2).turn(GeneralAirData.aqs);
                                break;
                            case ' ':
                                getBtnItemView(i, i2).turn(GeneralAirData.eco);
                                break;
                            case '!':
                                getBtnItemView(i, i2).turn(GeneralAirData.ion);
                                break;
                            case '\"':
                                getBtnItemView(i, i2).turn(GeneralAirData.is_left_seat_heat);
                                break;
                            case '#':
                                getBtnItemView(i, i2).turn(GeneralAirData.auto);
                                break;
                            case '$':
                                getBtnItemView(i, i2).turn(GeneralAirData.dual);
                                break;
                            case '%':
                                getBtnItemView(i, i2).turn(GeneralAirData.econ);
                                break;
                            case '&':
                                getBtnItemView(i, i2).turn(GeneralAirData.fast);
                                break;
                            case '\'':
                                getBtnItemView(i, i2).turn(GeneralAirData.mono);
                                break;
                            case '(':
                                getBtnItemView(i, i2).turn(GeneralAirData.rear);
                                break;
                            case ')':
                                getBtnItemView(i, i2).turn(GeneralAirData.rest);
                                break;
                            case '*':
                                getBtnItemView(i, i2).turn(GeneralAirData.soft);
                                break;
                            case '+':
                                getBtnItemView(i, i2).turn(GeneralAirData.sync);
                                break;
                            case ',':
                                getBtnItemView(i, i2).turn(GeneralAirData.sync_temperature);
                                break;
                            case '-':
                                getBtnItemView(i, i2).turn(GeneralAirData.in_out_auto_cycle != 0);
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
                            case '.':
                                getBtnItemView(i, i2).turn(GeneralAirData.power);
                                break;
                            case '/':
                                getBtnItemView(i, i2).turn(GeneralAirData.swing);
                                break;
                            case '0':
                                getBtnItemView(i, i2).turn(true);
                                if (GeneralAirData.cycle_in_out_close == 0) {
                                    getBtnItemView(i, i2).turn(false);
                                    break;
                                } else if (GeneralAirData.cycle_in_out_close == 1) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_caro_n);
                                    break;
                                } else {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_carin_n);
                                    break;
                                }
                            case '1':
                                getBtnItemView(i, i2).turn(GeneralAirData.negative_ion);
                                break;
                            case '2':
                                getBtnItemView(i, i2).turn(GeneralAirData.max_cool);
                                break;
                            case '3':
                                getBtnItemView(i, i2).turn(GeneralAirData.max_heat);
                                break;
                            case '4':
                                getBtnItemView(i, i2).turn(!GeneralAirData.in_out_cycle);
                                if (GeneralAirData.in_out_cycle) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_caro_n);
                                    break;
                                } else {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_carin_n);
                                    break;
                                }
                            case '5':
                                getBtnItemView(i, i2).turn(GeneralAirData.climate);
                                break;
                            case '6':
                                getBtnItemView(i, i2).turn(GeneralAirData.front_auto_wind_model);
                                break;
                            case '7':
                                getBtnItemView(i, i2).turn(GeneralAirData.auto_wind_light);
                                break;
                            case '8':
                                getBtnItemView(i, i2).turn(GeneralAirData.auto_wind_strong);
                                break;
                            case '9':
                                getBtnItemView(i, i2).turn(GeneralAirData.is_right_seat_heat);
                                break;
                            case ':':
                                getBtnItemView(i, i2).turn(GeneralAirData.auto_1_2 != 0);
                                if (GeneralAirData.auto_1_2 == 0 || GeneralAirData.auto_1_2 == 1) {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_auto_n);
                                    break;
                                } else {
                                    getBtnItemView(i, i2).setImageResource(R.drawable.ic_air_f_auto2_n);
                                    break;
                                }
                            case ';':
                                getBtnItemView(i, i2).turn(GeneralAirData.hybrid_ac);
                                break;
                        }
                        i2++;
                    }
                }
            }
        }
        if (this.set.isShowPmValue()) {
            this.mPmInValueTv.setText(GeneralAirData.pm_value_level_in_car);
            this.mPmOutValueTv.setText(GeneralAirData.pm_value_level_out_car);
        }
        this.mLeftWindSpeedWsv.setCurWindSpeed(GeneralAirData.front_wind_level);
        this.mLeftWindSpeedWsv.setAuto(GeneralAirData.front_auto_wind_speed);
        if (this.set.getIsHaveLeftRightWindSpeed()) {
            this.mRightWindSpeedWsv.setCurWindSpeed(GeneralAirData.front_right_wind_level);
            this.mRightWindSpeedWsv.setAuto(GeneralAirData.front_right_auto_wind_speed);
        }
        this.mTempSetViewLeft.setValue(GeneralAirData.front_left_temperature);
        this.mTempSetViewRight.setValue(GeneralAirData.front_right_temperature);
        this.mTempSetViewCenter.setValue(GeneralAirData.center_wheel);
        if (this.set.isShowSeatHeat()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
                setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.front_left_seat_heat_level > 0, R.drawable.img_air_m_hot2);
                setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.front_right_seat_heat_level > 0, R.drawable.img_air_m_hot1);
            } else {
                setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.front_left_seat_heat_level > 0, R.drawable.img_air_m_hot1);
                setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.front_right_seat_heat_level > 0, R.drawable.img_air_m_hot2);
            }
            setIvShowImgOrNot(this.mSeatBottomLeftIv, GeneralAirData.front_left_seat_heat_level > 0, R.drawable.img_air_m_hot);
            setIvShowImgOrNot(this.mSeatBottomRightIv, GeneralAirData.front_right_seat_heat_level > 0, R.drawable.img_air_m_hot);
            this.mHeatLeftShhsv.setValue(getStringByName(this.mSeatHeatValueRes[GeneralAirData.front_left_seat_heat_level]));
            this.mHeatRightShhsv.setValue(getStringByName(this.mSeatHeatValueRes[GeneralAirData.front_right_seat_heat_level]));
        }
        if (this.set.isShowSeatCold()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
                if (GeneralAirData.front_left_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.front_left_seat_cold_level > 0, R.drawable.img_air_m_co2);
                }
                if (GeneralAirData.front_right_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.front_right_seat_cold_level > 0, R.drawable.img_air_m_co1);
                }
            } else {
                if (GeneralAirData.front_left_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackLeftTv, GeneralAirData.front_left_seat_cold_level > 0, R.drawable.img_air_m_co1);
                }
                if (GeneralAirData.front_right_seat_heat_level <= 0) {
                    setIvShowImgOrNot(this.mSeatBackRightTv, GeneralAirData.front_right_seat_cold_level > 0, R.drawable.img_air_m_co2);
                }
            }
            if (GeneralAirData.front_left_seat_heat_level <= 0) {
                setIvShowImgOrNot(this.mSeatBottomLeftIv, GeneralAirData.front_left_seat_cold_level > 0, R.drawable.img_air_m_co);
            }
            if (GeneralAirData.front_right_seat_heat_level <= 0) {
                setIvShowImgOrNot(this.mSeatBottomRightIv, GeneralAirData.front_right_seat_cold_level > 0, R.drawable.img_air_m_co);
            }
            this.mColdLeftShhsv.setValue(getStringByName(this.mSeatColdValueRes[GeneralAirData.front_left_seat_cold_level]));
            this.mColdRightShhsv.setValue(getStringByName(this.mSeatColdValueRes[GeneralAirData.front_right_seat_cold_level]));
        }
        if (this.mActivity.isNeedSwitchTemAndSeat()) {
            setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.front_left_blow_head, R.drawable.img_air_mr_co1);
            setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.front_right_blow_head, R.drawable.img_air_ml_co1);
        } else {
            setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.front_left_blow_head, R.drawable.img_air_ml_co1);
            setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.front_right_blow_head, R.drawable.img_air_mr_co1);
        }
        setIvShowImgOrNot(this.mBlowWindowsLeftIv, GeneralAirData.front_left_blow_window, R.drawable.img_air_mf_wind);
        setIvShowImgOrNot(this.mBlowFootLeftIv, GeneralAirData.front_left_blow_foot, R.drawable.img_air_ml_co2);
        setIvShowImgOrNot(this.mBlowWindowsRightIv, GeneralAirData.front_right_blow_window, R.drawable.img_air_mf_wind);
        setIvShowImgOrNot(this.mBlowFootRightIv, GeneralAirData.front_right_blow_foot, R.drawable.img_air_ml_co2);
        setIvShowImgOrNot(this.mLeftBlowAuto, GeneralAirData.front_left_auto_wind, R.drawable.img_air_auto_wind);
        setIvShowImgOrNot(this.mRightBlowAuto, GeneralAirData.front_right_auto_wind, R.drawable.img_air_auto_wind);
    }

    private void setIvShowImgOrNot(ImageView imageView, boolean z, int i) {
        if (z) {
            imageView.setVisibility(0);
            imageView.setImageResource(i);
        } else {
            imageView.setVisibility(4);
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
        this.mActivity.switchViewPager(1);
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

    private void switchSeatView() {
        ImageView imageView = this.mLeftSeatIv;
        this.mLeftSeatIv = this.mRightSeatIv;
        this.mRightSeatIv = imageView;
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

    private void switchWindLevelSetView(AirPageUiSet airPageUiSet) {
        if (airPageUiSet.getFrontArea().getIsHaveLeftRightWindSpeed()) {
            SetWindSpeedView setWindSpeedView = this.mLeftWindSpeedWsv;
            this.mLeftWindSpeedWsv = this.mRightWindSpeedWsv;
            this.mRightWindSpeedWsv = setWindSpeedView;
        }
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
