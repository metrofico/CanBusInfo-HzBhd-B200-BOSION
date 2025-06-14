package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._304.MsgMgr;
import com.hzbhd.canbus.car_cus._304.util.Util;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RealKeyUtil;

/* loaded from: classes2.dex */
public class ParkBottomView extends RelativeLayout implements View.OnClickListener {
    private final String TAG;
    private ImageButton mIbHome;
    private ImageButton mIbPhone;
    private ImageButton mIbWindDecrease;
    private ImageButton mIbWindIncrease;
    private ImageView mIvWindSpeed;
    private OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListener;
    private View mView;

    public ParkBottomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "_304_MeidaBottomViewljqdebug";
        this.mView = LayoutInflater.from(context).inflate(R.layout._304_view_meida_bottom, this);
        findViews();
        initViews(context);
        refreshWindSpeed();
    }

    private void findViews() {
        this.mIvWindSpeed = (ImageView) this.mView.findViewById(R.id.iv_wind_speed);
        this.mIbWindIncrease = (ImageButton) this.mView.findViewById(R.id.ib_wind_increase);
        this.mIbWindDecrease = (ImageButton) this.mView.findViewById(R.id.ib_wind_decrease);
        this.mIbPhone = (ImageButton) this.mView.findViewById(R.id.ib_phone);
        this.mIbHome = (ImageButton) this.mView.findViewById(R.id.ib_home);
    }

    public void initViews(Context context) {
        this.mOnAirWindSpeedUpDownClickListener = UiMgrFactory.getCanUiMgr(context).getAirUiSet(context).getFrontArea().getSetWindSpeedViewOnClickListener();
        this.mIbWindIncrease.setOnClickListener(this);
        this.mIbWindDecrease.setOnClickListener(this);
        this.mIbPhone.setOnClickListener(this);
        this.mIbHome.setOnClickListener(this);
        this.mView.findViewById(R.id.rl_media_adjust).setVisibility(4);
        ((MsgMgr) MsgMgrFactory.getCanMsgMgr(context)).registOnWindLevelChangeListener(new MsgMgr.OnWindLevelChangeListener() { // from class: com.hzbhd.canbus.car_cus._304.view.ParkBottomView$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car._304.MsgMgr.OnWindLevelChangeListener
            public final void onChange() {
                this.f$0.m1136xf6b8073d();
            }
        });
    }

    /* renamed from: lambda$initViews$0$com-hzbhd-canbus-car_cus-_304-view-ParkBottomView, reason: not valid java name */
    /* synthetic */ void m1136xf6b8073d() {
        this.mIvWindSpeed.setImageLevel(DataHandleUtils.rangeNumber(GeneralAirData.front_wind_level, 0, 7));
    }

    public void refreshWindSpeed() {
        this.mIvWindSpeed.setImageLevel(GeneralAirData.front_wind_level);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_home /* 2131362399 */:
                Util.sendAvmCommand(0);
                sendHome();
                break;
            case R.id.ib_phone /* 2131362411 */:
                Util.sendAvmCommand(0);
                RealKeyUtil.realKeyClick(getContext(), 14);
                break;
            case R.id.ib_wind_decrease /* 2131362427 */:
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = this.mOnAirWindSpeedUpDownClickListener;
                if (onAirWindSpeedUpDownClickListener != null) {
                    onAirWindSpeedUpDownClickListener.onClickLeft();
                    break;
                }
                break;
            case R.id.ib_wind_increase /* 2131362428 */:
                OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener2 = this.mOnAirWindSpeedUpDownClickListener;
                if (onAirWindSpeedUpDownClickListener2 != null) {
                    onAirWindSpeedUpDownClickListener2.onClickRight();
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car_cus._304.view.ParkBottomView$1] */
    private void sendHome() {
        new Thread() { // from class: com.hzbhd.canbus.car_cus._304.view.ParkBottomView.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                while (!CommUtil.isMiscReverse()) {
                }
                RealKeyUtil.realKeyClick(ParkBottomView.this.getContext(), 52);
            }
        }.start();
    }
}
