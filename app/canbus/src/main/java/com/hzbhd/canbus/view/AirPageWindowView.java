package com.hzbhd.canbus.view;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;

/* loaded from: classes2.dex */
public class AirPageWindowView extends AbstractBaseActivity {
    public static final int AIR_FRONT_WHAT = 1001;
    public static final int AIR_FRONT_WHAT_NO_TURN = 1004;
    public static final int AIR_REAR_WHAT = 1002;
    public static final int AIR_REAR_WHAT_NO_TURN = 1003;
    public static final String BUNDLE_AIR_WHAT = "bundle_air_what";
    private static AirPageWindowView airPageWindowView = null;
    private static CountDownTimer countDownTimer = null;
    private static IShareIntListener iShareIntListener = null;
    private static Context mContext = null;
    public static boolean mIsAirActivityInit = false;
    public static boolean mIsClickOpen = false;
    public static boolean mIsMsgOpen = false;
    private static int mWhat;
    public static int page;
    public boolean addTag = false;
    private ImageButton hide;
    private WindowManager.LayoutParams layoutParams;
    private AirFrontWindowView mAirFrontView;
    private AirRearWindowView mAirRearView;
    private AirPageUiSet mSet;
    private WindowManager mWindowManager;
    private View view;
    private LinearLayout window_lay;

    public static AirPageWindowView getInstance(Context context) {
        mContext = context;
        if (airPageWindowView == null) {
            airPageWindowView = new AirPageWindowView(context);
        }
        return airPageWindowView;
    }

    private AirPageWindowView(Context context) {
        initWindow(context);
    }

    public void initWindow(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.type = 2010;
        this.layoutParams.flags = 16777512;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.width = -1;
        this.layoutParams.height = -1;
        initView(context);
    }

    private void initView(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._air_page_window_view, (ViewGroup) null);
        this.view = viewInflate;
        findViews(viewInflate);
        initViews(this.view);
    }

    private void findViews(View view) {
        this.window_lay = (LinearLayout) view.findViewById(R.id.window_lay);
        this.mAirFrontView = (AirFrontWindowView) view.findViewById(R.id.air_front);
        this.mAirRearView = (AirRearWindowView) view.findViewById(R.id.air_rear);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.hide);
        this.hide = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.view.AirPageWindowView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AirPageWindowView.this.hide();
            }
        });
        this.window_lay.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.view.AirPageWindowView.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                AirPageWindowView.this.initCountDownTimer();
                return false;
            }
        });
    }

    private void initViews(View view) {
        AirPageUiSet airUiSet = getUiMgrInterface(mContext).getAirUiSet(mContext);
        this.mSet = airUiSet;
        if (airUiSet == null) {
            return;
        }
        if (airUiSet.getOnAirInitListener() != null) {
            this.mSet.getOnAirInitListener().onInit();
        }
        this.mAirFrontView.initViews(this, this.mSet);
        this.mAirRearView.setVisibility(4);
        if (this.mSet.isHaveRearArea()) {
            this.mAirRearView.initViews(this, this.mSet);
        }
    }

    public boolean isNeedSwitchTemAndSeat() {
        return ((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
    }

    public void switchViewPager(int i) {
        if (i == 0) {
            this.mAirFrontView.setVisibility(0);
            this.mAirRearView.setVisibility(4);
            page = i;
        } else {
            if (i != 1) {
                return;
            }
            this.mAirFrontView.setVisibility(4);
            this.mAirRearView.setVisibility(0);
            page = i;
        }
    }

    public void initCountDownTimer() {
        CountDownTimer countDownTimer2 = countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        CountDownTimer countDownTimer3 = new CountDownTimer(5000L, 1000L) { // from class: com.hzbhd.canbus.view.AirPageWindowView.3
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                AirPageWindowView.this.hide();
            }
        };
        countDownTimer = countDownTimer3;
        countDownTimer3.start();
    }

    public void show(Bundle bundle) {
        initCountDownTimer();
        if (!this.addTag) {
            this.mWindowManager.addView(this.view, this.layoutParams);
            this.addTag = true;
        }
        this.mAirFrontView.refreshUi();
        if (this.mSet.isHaveRearArea()) {
            this.mAirRearView.refreshUi();
        }
        if (iShareIntListener == null) {
            iShareIntListener = new ReversState();
        }
        ShareDataManager.getInstance().registerShareIntListener("sys.Reverse", iShareIntListener);
        selectPagaFrontRear(bundle);
    }

    public void hide() {
        View view;
        MyLog.temporaryTracking("hide");
        try {
            WindowManager windowManager = this.mWindowManager;
            if (windowManager != null && (view = this.view) != null && this.addTag) {
                windowManager.removeView(view);
                this.addTag = false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        CountDownTimer countDownTimer2 = countDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        mIsClickOpen = false;
        mIsMsgOpen = false;
    }

    private void selectPagaFrontRear(Bundle bundle) {
        int i = bundle.getInt("bundle_air_what");
        mWhat = i;
        switch (i) {
            case 1001:
                if (this.mAirFrontView != null) {
                    switchViewPager(0);
                    break;
                }
                break;
            case 1002:
                if (this.mAirRearView != null && this.mSet.isHaveRearArea()) {
                    switchViewPager(1);
                    break;
                }
                break;
            case 1003:
                AirRearWindowView airRearWindowView = this.mAirRearView;
                if (airRearWindowView != null) {
                    airRearWindowView.refreshUi();
                    break;
                }
                break;
            case 1004:
                AirFrontWindowView airFrontWindowView = this.mAirFrontView;
                if (airFrontWindowView != null) {
                    airFrontWindowView.refreshUi();
                    break;
                }
                break;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi() && bundle != null) {
            int i = bundle.getInt("bundle_air_what");
            mWhat = i;
            if (mIsAirActivityInit) {
                switch (i) {
                    case 1001:
                        if (this.mAirFrontView != null) {
                            switchViewPager(0);
                            break;
                        }
                        break;
                    case 1002:
                        if (this.mAirRearView != null && this.mSet.isHaveRearArea()) {
                            switchViewPager(1);
                            break;
                        }
                        break;
                    case 1003:
                        AirRearWindowView airRearWindowView = this.mAirRearView;
                        if (airRearWindowView != null) {
                            airRearWindowView.refreshUi();
                            break;
                        }
                        break;
                    case 1004:
                        AirFrontWindowView airFrontWindowView = this.mAirFrontView;
                        if (airFrontWindowView != null) {
                            airFrontWindowView.refreshUi();
                            break;
                        }
                        break;
                }
                AirFrontWindowView airFrontWindowView2 = this.mAirFrontView;
                if (airFrontWindowView2 != null) {
                    airFrontWindowView2.refreshUi();
                }
                AirPageUiSet airPageUiSet = this.mSet;
                if (airPageUiSet == null || this.mAirRearView == null || !airPageUiSet.isHaveRearArea()) {
                    return;
                }
                this.mAirRearView.refreshUi();
            }
        }
    }

    private class ReversState implements IShareIntListener {
        private ReversState() {
        }

        @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
        public void onInt(int i) {
            AirPageWindowView.this.hide();
        }
    }
}
