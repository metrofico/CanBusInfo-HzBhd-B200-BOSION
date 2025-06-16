package com.hzbhd.canbus.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.view.AirFrontView;
import com.hzbhd.canbus.view.AirRearView;

public class AirActivity extends AbstractBaseActivity {
    public static final int AIR_FRONT_WHAT = 1001;
    public static final int AIR_FRONT_WHAT_NO_TURN = 1004;
    public static final int AIR_REAR_WHAT = 1002;
    public static final int AIR_REAR_WHAT_NO_TURN = 1003;
    public static final String BUNDLE_AIR_WHAT = "bundle_air_what";
    private static final int DISMISS_POPUP_VIEW = 100;
    public static boolean mIsAirActivityInit = false;
    public static boolean mIsClickOpen = false;
    public static boolean mIsMsgOpen = false;
    private static int mWhat;
    public static int page;
    private AirFrontView mAirFrontView;
    private AirRearView mAirRearView;
    private AirPageUiSet mSet;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constant.INTENT_BROADCAST_FINISH_AIR_ACTIVITY.equals(intent.getAction())) {
                AirActivity.this.finish();
            }
        }
    };
    private final MyHandler mHandler = new MyHandler(this);

    public static void msgOpenActivity(Context context, Bundle bundle) {
        mIsMsgOpen = true;
        mWhat = bundle.getInt(BUNDLE_AIR_WHAT);
        if (SystemUtil.isForeground(context, AirActivity.class.getName())) {
            return;
        }
        Intent intent = new Intent(context, AirActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void clickToOpenActivity(Context context) {
        mIsClickOpen = true;
        context.startActivity(new Intent(context, AirActivity.class));
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_air);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mAirFrontView = findViewById(R.id.air_front);
        this.mAirRearView = findViewById(R.id.air_rear);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private void initViews() {
        AirPageUiSet airUiSet = getUiMgrInterface(this).getAirUiSet(this);
        this.mSet = airUiSet;
        if (airUiSet == null) {
            return;
        }
        if (airUiSet.getOnAirInitListener() != null) {
            this.mSet.getOnAirInitListener().onInit();
        }
        this.mAirFrontView.initViews(this, this.mSet);
        this.mAirRearView.setVisibility(View.INVISIBLE);
        if (this.mSet.isHaveRearArea()) {
            this.mAirRearView.initViews(this, this.mSet);
        }
        registerReceiver(this.mReceiver, new IntentFilter(Constant.INTENT_BROADCAST_FINISH_AIR_ACTIVITY));
    }

    public void switchViewPager(int i) {
        if (i == 0) {
            this.mAirFrontView.setVisibility(View.VISIBLE);
            this.mAirRearView.setVisibility(View.INVISIBLE);
            page = i;
        } else {
            if (i != 1) {
                return;
            }
            this.mAirFrontView.setVisibility(View.INVISIBLE);
            this.mAirRearView.setVisibility(View.VISIBLE);
            page = i;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsAirActivityInit = true;
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_AIR_WHAT, mWhat);
        refreshUi(bundle);
    }

    @Override
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi() && bundle != null) {
            int i = bundle.getInt(BUNDLE_AIR_WHAT);
            mWhat = i;
            if (!mIsAirActivityInit) {
                LogUtil.showLog("activity not init");
                return;
            }
            switch (i) {
                case AIR_FRONT_WHAT:
                    if (this.mAirFrontView != null) {
                        switchViewPager(0);
                    }
                    break;
                case AIR_REAR_WHAT:
                    if (this.mAirRearView != null && this.mSet.isHaveRearArea()) {
                        switchViewPager(1);
                    }
                    break;
                case AIR_REAR_WHAT_NO_TURN:
                    if (this.mAirRearView != null) {
                        this.mAirRearView.refreshUi();
                    }
                    break;
                case AIR_FRONT_WHAT_NO_TURN:
                    if (this.mAirFrontView != null) {
                        this.mAirFrontView.refreshUi();
                    }
                    break;
            }
            if (this.mAirFrontView != null) {
                this.mAirFrontView.refreshUi();
            }
            if (this.mSet != null && this.mAirRearView != null && this.mSet.isHaveRearArea()) {
                this.mAirRearView.refreshUi();
            }
            if (this.mHandler != null && mIsMsgOpen && !mIsClickOpen) {
                this.mHandler.removeCallbacksAndMessages(null);
                this.mHandler.sendEmptyMessageDelayed(DISMISS_POPUP_VIEW, 5000L);
            }
        }
    }

    public boolean isNeedSwitchTemAndSeat() {
        return Dependency.get(CanSettingProxy.class).getSwitchAcTemperature();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIsClickOpen = false;
        mIsMsgOpen = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(DISMISS_POPUP_VIEW);
        unregisterReceiver(this.mReceiver);
    }

    private static class MyHandler extends Handler {
        private final AirActivity activity;

        public MyHandler(AirActivity airActivity) {
            this.activity = airActivity;
        }

        @Override
        public void handleMessage(Message message) {
            if (message.what == DISMISS_POPUP_VIEW) {
                try {
                    this.activity.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
