package com.hzbhd.canbus.park.parkui;

import android.R;
import android.app.Service;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.externel360camera.VZ360Constance;
import com.hzbhd.canbus.adapter.externel360camera.view.IrCam360VZLayout;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.adapter.util.ScreenLogic;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.AnalogColorSettingInterface;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.VideoTypeUiChangeInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.park.external360cam.External360CamCmds;
import com.hzbhd.canbus.park.external360cam.IrCam360MaylasiaView;
import com.hzbhd.canbus.park.panoramic.PanoramicView;
import com.hzbhd.canbus.park.panoramic.ParkPanoramic;
import com.hzbhd.canbus.park.parkui.BackCameraUiServiceOld;
import com.hzbhd.canbus.park.radar.RadarView;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.config.use.CanBusDefault;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import java.util.List;

/* loaded from: classes2.dex */
public class BackCameraUiServiceOld implements AnalogColorSettingInterface, VideoTypeUiChangeInterface, BackCameraUiServiceBase {
    private static final String TAG = "fang";
    private TextView mCameraTisTv;
    private RelativeLayout mCusPanoramicContainerView;
    private IrCam360MaylasiaView mIrCam360MaylasiaView;
    private boolean mIsShowRadarLayout;
    private PanoramicView mPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private ParkPanoramic mParkPanoramic;
    private RadarView mRadarView;
    private Service mService;
    private View mTouchListenerView;
    private View mView;
    private WindowManager.LayoutParams mViewParams;
    private WindowManager mWindowManager;
    private RelativeLayout radar_all_page;
    private boolean mHasAddViewToWindow = false;
    private int mExternal360CamType = 0;
    private final IShareIntListener mReverseStatusListener = new IShareIntListener() { // from class: com.hzbhd.canbus.park.parkui.BackCameraUiServiceOld.1
        @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
        public void onInt(int i) {
            Log.d(BackCameraUiServiceOld.TAG, "REVERSE <onChanged> " + i);
            BackCameraUiServiceOld.this.setReverseState(i);
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.park.parkui.BackCameraUiServiceOld.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                BackCameraUiServiceOld.this.refreshUi(message.getData());
                return;
            }
            boolean z = message.arg1 == 1;
            Log.d(BackCameraUiServiceOld.TAG, "MSG_CHECK_BACKCAMERA_STATE " + z);
            if (!z || BackCameraUiServiceOld.this.mHasAddViewToWindow) {
                if (z || !BackCameraUiServiceOld.this.mHasAddViewToWindow) {
                    return;
                }
                BackCameraUiServiceOld.this.removeViewFromWindow();
                BackCameraUiServiceOld.this.mHasAddViewToWindow = false;
                return;
            }
            BackCameraUiServiceOld.this.addViewToWindow(false);
            BackCameraUiServiceOld.this.mHasAddViewToWindow = true;
        }
    };

    @Override // com.hzbhd.canbus.interfaces.VideoTypeUiChangeInterface
    public void changeVideoType() {
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void onDestroy() {
    }

    public BackCameraUiServiceOld(Service service) {
        this.mService = service;
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void onCreate() {
        ScreenLogic.setOrientation(this.mService.getApplicationContext().getResources().getConfiguration().orientation);
        this.mService.setTheme(R.style.Theme.Holo.Light.NoActionBar);
        this.mView = LayoutInflater.from(this.mService).inflate(com.hzbhd.canbus.R.layout.layout_park, (ViewGroup) null);
        this.mExternal360CamType = FutureUtil.instance.is360External();
        HzbhdLog.d(TAG, "BackCameraUiService onCreate");
        try {
            setReverseState(ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MISC_REVERSE, new IShareIntListener() { // from class: com.hzbhd.canbus.park.parkui.BackCameraUiServiceOld$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    this.f$0.m1150x85645fee(i);
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: lambda$onCreate$0$com-hzbhd-canbus-park-parkui-BackCameraUiServiceOld, reason: not valid java name */
    /* synthetic */ void m1150x85645fee(int i) {
        Log.d(TAG, "REVERSE <onChanged> " + i);
        setReverseState(i);
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.showLog(TAG, "onConfigurationChanged");
        try {
            this.mWindowManager.removeView(this.mView);
        } catch (Exception unused) {
        }
        this.mView = LayoutInflater.from(this.mService).inflate(com.hzbhd.canbus.R.layout.layout_park, (ViewGroup) null);
        if (ScreenLogic.isScreenOreitaionChanged(configuration) && this.mHasAddViewToWindow) {
            LogUtil.showLog(TAG, "onConfigurationChanged 22");
            addViewToWindow(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUi(Bundle bundle) {
        LogUtil.showLog(TAG, "BackCameraUiService refreshUi");
        if (this.mParkPageUiSet == null) {
            LogUtil.showLog(TAG, "mParkPageUiSet==null");
            return;
        }
        LogUtil.showLog(TAG, "BackCameraUiService mParkPageUiSet.isIsShowRadar():" + this.mParkPageUiSet.isIsShowRadar());
        if (this.mIsShowRadarLayout != this.mParkPageUiSet.isIsShowRadar()) {
            this.mIsShowRadarLayout = this.mParkPageUiSet.isIsShowRadar();
            if (this.mParkPageUiSet.isIsShowRadar()) {
                this.mRadarView.setVisibility(0);
                if (SharePreUtil.getBoolValue(this.mService, BackCameraUiService.SHARE_IS_SHOW_RADAR, true)) {
                    this.mRadarView.showRadarView();
                } else {
                    this.mRadarView.hideRadarView();
                }
            } else {
                this.mRadarView.setVisibility(8);
            }
        }
        if (this.mParkPageUiSet.isIsShowRadar()) {
            if (GeneralParkData.isShowDistanceNotShowLocationUi) {
                this.mRadarView.refreshDistance(GeneralParkData.radar_distance_data);
            } else {
                this.mRadarView.refreshLocation(GeneralParkData.radar_location_data);
            }
            if (GeneralParkData.isShowLeftTopOneDistanceUi) {
                this.mRadarView.setOneRadarDitance(GeneralParkData.strOnlyOneDistance);
            } else {
                this.mRadarView.hideOneRadarDistance();
            }
        }
        List<PanoramicBtnUpdateEntity> list = GeneralParkData.dataList;
        if (list != null) {
            for (PanoramicBtnUpdateEntity panoramicBtnUpdateEntity : list) {
                if (this.mParkPageUiSet.getPanoramicBtnList() != null) {
                    this.mParkPageUiSet.getPanoramicBtnList().get(panoramicBtnUpdateEntity.getIndex()).setSelect(panoramicBtnUpdateEntity.isSelect());
                }
            }
            this.mPanoramicView.getAdapter().notifyDataSetChanged();
        }
        if (this.mParkPageUiSet.isShowCusPanoramicView()) {
            this.mCusPanoramicContainerView.setVisibility(0);
            View cusPanoramicView = UiMgrFactory.getCanUiMgr(this.mService).getCusPanoramicView(this.mService);
            if (cusPanoramicView == null || this.mCusPanoramicContainerView.getChildCount() != 0) {
                return;
            }
            this.mCusPanoramicContainerView.addView(cusPanoramicView);
            return;
        }
        this.mCusPanoramicContainerView.setVisibility(8);
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public Handler getmHandler() {
        return this.mHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReverseState(int i) {
        Message message = new Message();
        message.what = 0;
        message.arg1 = i;
        this.mHandler.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addViewToWindow(boolean z) {
        initWindowParam();
        HzbhdLog.d(TAG, "addViewToWindow");
        this.mRadarView = (RadarView) this.mView.findViewById(com.hzbhd.canbus.R.id.view_radar);
        this.mPanoramicView = (PanoramicView) this.mView.findViewById(com.hzbhd.canbus.R.id.view_panoramic);
        this.mCusPanoramicContainerView = (RelativeLayout) this.mView.findViewById(com.hzbhd.canbus.R.id.view_cus_panoramic);
        this.mTouchListenerView = this.mView.findViewById(com.hzbhd.canbus.R.id.cameraView);
        this.mIrCam360MaylasiaView = (IrCam360MaylasiaView) this.mView.findViewById(com.hzbhd.canbus.R.id.view_external_360_cam_my);
        this.mCameraTisTv = (TextView) this.mView.findViewById(com.hzbhd.canbus.R.id.tv_camera_tis);
        this.mRadarView.refreshText();
        if (FutureUtil.instance.isShowBackCameraTips()) {
            this.mCameraTisTv.setVisibility(0);
        }
        SystemProperties.set("BackCameraUI", "true");
        if (UiMgrFactory.getCanUiMgr(this.mService) != null) {
            this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mService).getParkPageUiSet(this.mService);
        }
        ParkPageUiSet parkPageUiSet = this.mParkPageUiSet;
        if (parkPageUiSet != null) {
            OnBackCameraStatusListener onBackCameraStatusListener = parkPageUiSet.getOnBackCameraStatusListener();
            if (onBackCameraStatusListener != null) {
                onBackCameraStatusListener.addViewToWindows();
            }
            boolean radarDispCheck = ((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getRadarDispCheck();
            LogUtil.showLog(TAG, "IsShowRadar:" + this.mParkPageUiSet.isIsShowRadar() + ", showRadarFromSetting:" + radarDispCheck);
            if ((radarDispCheck && this.mParkPageUiSet.isIsShowRadar()) || this.mService.getResources().getConfiguration().orientation == 1) {
                this.mRadarView.setVisibility(0);
                if (SharePreUtil.getBoolValue(this.mService, BackCameraUiService.SHARE_IS_SHOW_RADAR, true) || this.mService.getResources().getConfiguration().orientation == 1) {
                    this.mRadarView.showRadarView();
                } else {
                    this.mRadarView.hideRadarView();
                }
                if (this.mParkPageUiSet.isHaveLeftRightRadar()) {
                    this.mRadarView.setSmallRadarViewWidth();
                }
            } else {
                this.mRadarView.setVisibility(8);
            }
            LogUtil.showLog(TAG, "IsShowPanoramic:" + this.mParkPageUiSet.isShowPanoramic());
            if (this.mParkPageUiSet.isShowPanoramic()) {
                this.mPanoramicView.setBtnList(this.mParkPageUiSet.getPanoramicBtnList(), this.mParkPageUiSet.getOnPanoramicItemClickListener());
                this.mPanoramicView.setVisibility(0);
            } else {
                this.mPanoramicView.setVisibility(8);
            }
            LogUtil.showLog(TAG, "isShowCusPanoramic:" + this.mParkPageUiSet.isShowCusPanoramicView());
            if (this.mParkPageUiSet.isShowCusPanoramicView()) {
                this.mCusPanoramicContainerView.setVisibility(0);
                View cusPanoramicView = UiMgrFactory.getCanUiMgr(this.mService).getCusPanoramicView(this.mService);
                if (cusPanoramicView != null && this.mCusPanoramicContainerView.getChildCount() == 0) {
                    if (cusPanoramicView.getParent() != null) {
                        ((ViewGroup) cusPanoramicView.getParent()).removeAllViews();
                    }
                    this.mCusPanoramicContainerView.addView(cusPanoramicView);
                }
            } else {
                this.mCusPanoramicContainerView.setVisibility(8);
            }
        } else {
            LogUtil.showLog(TAG, "mParkPageUiSet==null");
            if (this.mService.getResources().getConfiguration().orientation == 2) {
                this.mRadarView.setVisibility(8);
            }
        }
        if (ParkPanoramic.isEnableParkPanoramic()) {
            LogUtil.showLog(TAG, "isEnableParkPanoramic");
            if (this.mParkPanoramic == null) {
                this.mParkPanoramic = new ParkPanoramic();
            }
            this.mParkPanoramic.constructParkPanoramic();
        }
        if (this.mExternal360CamType == 2) {
            this.mIrCam360MaylasiaView.setVisibility(0);
        }
        intIrCam360VZView();
        this.mTouchListenerView.setOnTouchListener(new AnonymousClass3());
        this.radar_all_page = (RelativeLayout) this.mView.findViewById(com.hzbhd.canbus.R.id.radar_all_page);
        if (CanIdSpecialConfig.hideRadarLayoutCanID(CanbusConfig.INSTANCE.getCanType())) {
            this.radar_all_page.setVisibility(8);
        }
        this.mWindowManager.addView(this.mView, this.mViewParams);
    }

    /* renamed from: com.hzbhd.canbus.park.parkui.BackCameraUiServiceOld$3, reason: invalid class name */
    class AnonymousClass3 implements View.OnTouchListener {
        private float downX = 0.0f;
        private float downY = 0.0f;
        private final Runnable runnable = new Runnable() { // from class: com.hzbhd.canbus.park.parkui.BackCameraUiServiceOld$3$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BackCameraUiServiceOld.AnonymousClass3.lambda$$0();
            }
        };

        AnonymousClass3() {
        }

        static /* synthetic */ void lambda$$0() {
            if (CanBusDefault.INSTANCE.getLongClick()) {
                LogUtil.showLog(BackCameraUiServiceOld.TAG, "onLongClick");
                SendKeyManager.getInstance().setKeyEvent(HotKeyConstant.K_REVERSE_SETUP, 0, 0);
            }
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            try {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (BackCameraUiServiceOld.this.mParkPageUiSet != null) {
                    Log.i("BackCameraUiService", "onTouch: touchListener: " + BackCameraUiServiceOld.this.mParkPageUiSet.getOnPanoramicItemTouchListener());
                    if (BackCameraUiServiceOld.this.mParkPageUiSet.getOnPanoramicItemTouchListener() != null) {
                        BackCameraUiServiceOld.this.mParkPageUiSet.getOnPanoramicItemTouchListener().onTouchItem(motionEvent);
                    }
                }
                if (motionEvent.getAction() == 1) {
                    External360CamCmds.getInstance().getCmds().sndCoord(x, y);
                }
                if (motionEvent.getAction() == 0) {
                    this.downX = motionEvent.getX();
                    this.downY = motionEvent.getY();
                    BackCameraUiServiceOld.this.mHandler.postDelayed(this.runnable, 500L);
                } else {
                    if (motionEvent.getAction() == 2 && Math.sqrt(Math.pow(motionEvent.getX() - this.downX, 2.0d) + Math.pow(motionEvent.getY() - this.downY, 2.0d)) < 20.0d) {
                        return true;
                    }
                    BackCameraUiServiceOld.this.mHandler.removeCallbacks(this.runnable);
                }
            } catch (Exception e) {
                LogUtil.showLog(BackCameraUiServiceOld.TAG, e.toString());
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeViewFromWindow() {
        HzbhdLog.d(TAG, "removeViewFromWindow");
        try {
            this.mWindowManager.removeView(this.mView);
        } catch (Exception e) {
            LogUtil.showLog(TAG, "e:" + e.toString());
        }
    }

    private void initWindowParam() {
        LogUtil.showLog(TAG, "initWindowParam");
        this.mWindowManager = (WindowManager) this.mService.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mViewParams = layoutParams;
        layoutParams.type = 2024;
        this.mViewParams.format = 1;
        this.mViewParams.flags = 16777512;
        this.mViewParams.x = 0;
        this.mViewParams.y = 0;
        this.mViewParams.width = -1;
        this.mViewParams.height = -1;
        this.mViewParams.format = 1;
        this.mViewParams.setTitle("ReverseWindow");
    }

    @Override // com.hzbhd.canbus.interfaces.AnalogColorSettingInterface
    public void setAnalogColorUiChange(int i, int i2, int i3) {
        Log.d(TAG, "setAnalogColorUiChange ");
    }

    private void intIrCam360VZView() {
        if (VZ360Constance.isVZ360Camera().booleanValue()) {
            IrCam360VZLayout.addVIew(this.mService.getBaseContext(), this.mView);
        }
    }
}
