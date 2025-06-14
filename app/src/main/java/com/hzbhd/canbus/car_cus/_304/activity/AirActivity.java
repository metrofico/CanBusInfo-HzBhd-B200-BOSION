package com.hzbhd.canbus.car_cus._304.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.car_cus._304.view.AirTemperatureView;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;

/* loaded from: classes2.dex */
public class AirActivity extends AbstractBaseActivity implements View.OnClickListener, AirTemperatureView.OnTemperatureAdjustListener {
    public static final int GEAR_COUNT = 15;
    public static final int MAX_GEAR = 15;
    public static final float SINGLE_ANGLE = 10.0f;
    public static boolean mIsMsgStart;
    private AirPageUiSet mAirPageUiSet;
    private int mGear;
    private ImageButton mIbAc;
    private ImageButton mIbCylce;
    private ImageButton mIbPower;
    private ImageButton mIbPtc;
    private ImageView mIvBtnWindFoot;
    private ImageView mIvBtnWindHead;
    private ImageView mIvBtnWindHeadFoot;
    private ImageView mIvBtnWindWindowFoot;
    private ImageView mIvImgWindFoot;
    private ImageView mIvImgWindHead;
    private ImageView mIvImgWindIn;
    private ImageView mIvImgWindWindow;
    private OnAirBtnClickListener mOnAirBtnClickListener;
    private OnAirTemperatureUpDownClickListener mOnAirTemperatureUpDownClickListener;
    private AirTemperatureView mTemperatureView;
    private final String TAG = "_304_AirActivity";
    private final int MSG_FINISH_ACTIVITY = 0;
    private final int POSITION_BLOW_HEAD = 0;
    private final int POSITION_BLOW_HEAD_FOOT = 1;
    private final int POSITION_BLOW_FOOT = 2;
    private final int POSITION_BLOW_WINDOW_FOOT = 3;
    private final int POSITION_POWER = 4;
    private final int POSITION_AC = 5;
    private final int POSITION_PTC_HEATING = 6;
    private final int POSITION_CYCLE = 7;

    public static int getGear(float f) {
        return 15 - ((int) ((f - 20.0f) / 10.0f));
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._304_activity_air);
        findViews();
        initViews();
    }

    @Override // android.app.Activity
    protected void onStop() {
        mIsMsgStart = false;
        super.onStop();
    }

    private void findViews() {
        this.mIbPower = (ImageButton) findViewById(R.id.ib_power);
        this.mIbAc = (ImageButton) findViewById(R.id.ib_ac);
        this.mIbPtc = (ImageButton) findViewById(R.id.ib_ptc);
        this.mIbCylce = (ImageButton) findViewById(R.id.ib_cycle);
        this.mIvImgWindIn = (ImageView) findViewById(R.id.iv_img_wind_in);
        this.mIvImgWindWindow = (ImageView) findViewById(R.id.iv_img_wind_window);
        this.mIvImgWindHead = (ImageView) findViewById(R.id.iv_img_wind_head);
        this.mIvImgWindFoot = (ImageView) findViewById(R.id.iv_img_wind_foot);
        this.mIvBtnWindHead = (ImageView) findViewById(R.id.iv_btn_wind_head);
        this.mIvBtnWindHeadFoot = (ImageView) findViewById(R.id.iv_btn_wind_head_foot);
        this.mIvBtnWindFoot = (ImageView) findViewById(R.id.iv_btn_wind_foot);
        this.mIvBtnWindWindowFoot = (ImageView) findViewById(R.id.iv_btn_wind_window_foot);
        this.mTemperatureView = (AirTemperatureView) findViewById(R.id.temeperature_view);
    }

    private void initViews() {
        AirPageUiSet airUiSet = UiMgrFactory.getCanUiMgr(this).getAirUiSet(this);
        this.mAirPageUiSet = airUiSet;
        this.mOnAirBtnClickListener = airUiSet.getFrontArea().getOnAirBtnClickListeners()[0];
        this.mOnAirTemperatureUpDownClickListener = this.mAirPageUiSet.getFrontArea().getTempSetViewOnUpDownClickListeners()[0];
        this.mTemperatureView.initViews(this);
        refreshUi(null);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        this.mTemperatureView.refresh(MyGeneralData.mTemperature, 180.0f - (((MyGeneralData.mTemperatureGear - 1) * 10.0f) + 20.0f));
        setInvisible(this.mIvImgWindIn, !GeneralAirData.in_out_cycle);
        setInvisible(this.mIvImgWindWindow, compare(Integer.valueOf(MyGeneralData.mBlowMode), 3, 4));
        setInvisible(this.mIvImgWindHead, compare(Integer.valueOf(MyGeneralData.mBlowMode), 0, 1));
        setInvisible(this.mIvImgWindFoot, compare(Integer.valueOf(MyGeneralData.mBlowMode), 1, 2, 3));
        this.mIvBtnWindHead.setSelected(MyGeneralData.mBlowMode == 0);
        this.mIvBtnWindHeadFoot.setSelected(MyGeneralData.mBlowMode == 1);
        this.mIvBtnWindFoot.setSelected(MyGeneralData.mBlowMode == 2);
        this.mIvBtnWindWindowFoot.setSelected(MyGeneralData.mBlowMode == 3);
        this.mIbPower.setSelected(MyGeneralData.etc_power);
        this.mIbAc.setSelected(GeneralAirData.ac);
        this.mIbPtc.setSelected(MyGeneralData.ptc);
        if (GeneralAirData.in_out_cycle) {
            this.mIbCylce.setImageResource(R.drawable._304_selector_air_cycle_in);
        } else {
            this.mIbCylce.setImageResource(R.drawable._304_selector_air_cycle_out);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click_area_foot /* 2131362140 */:
                this.mOnAirBtnClickListener.onClickItem(2);
                setWindBtnsPosition(2);
                MyGeneralData.mBlowMode = 2;
                setIvImageWind();
                break;
            case R.id.click_area_head /* 2131362141 */:
                this.mOnAirBtnClickListener.onClickItem(0);
                setWindBtnsPosition(0);
                MyGeneralData.mBlowMode = 0;
                setIvImageWind();
                break;
            case R.id.click_area_head_foot /* 2131362142 */:
                this.mOnAirBtnClickListener.onClickItem(1);
                setWindBtnsPosition(1);
                MyGeneralData.mBlowMode = 1;
                setIvImageWind();
                break;
            case R.id.click_area_window_foot /* 2131362143 */:
                this.mOnAirBtnClickListener.onClickItem(3);
                setWindBtnsPosition(3);
                MyGeneralData.mBlowMode = 3;
                setIvImageWind();
                break;
            case R.id.ib_ac /* 2131362382 */:
                this.mOnAirBtnClickListener.onClickItem(5);
                break;
            case R.id.ib_cycle /* 2131362385 */:
                this.mOnAirBtnClickListener.onClickItem(7);
                GeneralAirData.in_out_cycle = !GeneralAirData.in_out_cycle;
                setIvImgWindIn();
                break;
            case R.id.ib_power /* 2131362412 */:
                this.mOnAirBtnClickListener.onClickItem(4);
                break;
            case R.id.ib_ptc /* 2131362413 */:
                this.mOnAirBtnClickListener.onClickItem(6);
                break;
        }
    }

    @Override // com.hzbhd.canbus.car_cus._304.view.AirTemperatureView.OnTemperatureAdjustListener
    public void onAdjust(float f) {
        if (this.mOnAirTemperatureUpDownClickListener == null) {
            return;
        }
        Log.i("_304_AirActivity", "onAdjust: angle - > " + f);
        int gear = getGear(f);
        if (this.mGear != gear) {
            Log.i("_304_AirActivity", "onAdjust: gear - > " + gear);
            this.mGear = gear;
            CanbusMsgSender.sendMsg(new byte[]{22, -78, 0, 0, (byte) (gear << 3), 0});
        }
    }

    private void setInvisible(View view, boolean z) {
        if (z) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    private boolean compare(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj2.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private void setWindBtnsPosition(int i) {
        if (i == 0) {
            this.mIvBtnWindHead.setSelected(true);
            this.mIvBtnWindHeadFoot.setSelected(false);
            this.mIvBtnWindFoot.setSelected(false);
            this.mIvBtnWindWindowFoot.setSelected(false);
            return;
        }
        if (i == 1) {
            this.mIvBtnWindHead.setSelected(false);
            this.mIvBtnWindHeadFoot.setSelected(true);
            this.mIvBtnWindFoot.setSelected(false);
            this.mIvBtnWindWindowFoot.setSelected(false);
            return;
        }
        if (i == 2) {
            this.mIvBtnWindHead.setSelected(false);
            this.mIvBtnWindHeadFoot.setSelected(false);
            this.mIvBtnWindFoot.setSelected(true);
            this.mIvBtnWindWindowFoot.setSelected(false);
            return;
        }
        if (i != 3) {
            return;
        }
        this.mIvBtnWindHead.setSelected(false);
        this.mIvBtnWindHeadFoot.setSelected(false);
        this.mIvBtnWindFoot.setSelected(false);
        this.mIvBtnWindWindowFoot.setSelected(true);
    }

    private void setIvImageWind() {
        setInvisible(this.mIvImgWindWindow, compare(Integer.valueOf(MyGeneralData.mBlowMode), 3, 4));
        setInvisible(this.mIvImgWindHead, compare(Integer.valueOf(MyGeneralData.mBlowMode), 0, 1));
        setInvisible(this.mIvImgWindFoot, compare(Integer.valueOf(MyGeneralData.mBlowMode), 1, 2, 3));
    }

    private void setIvImgWindIn() {
        if (GeneralAirData.in_out_cycle) {
            this.mIbCylce.setImageResource(R.drawable._304_selector_air_cycle_in);
        } else {
            this.mIbCylce.setImageResource(R.drawable._304_selector_air_cycle_out);
        }
        setInvisible(this.mIvImgWindIn, !GeneralAirData.in_out_cycle);
    }
}
