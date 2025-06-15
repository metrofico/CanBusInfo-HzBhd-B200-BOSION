package com.hzbhd.canbus.park.parkui;

import android.app.Service;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.adapter.util.ScreenLogic;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.park.external360cam.External360CamCmds;
import com.hzbhd.canbus.park.panoramic.ParkPanoramic;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.config.use.CanBusDefault;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;

import java.util.List;

/* loaded from: classes2.dex */
public class BackCameraUiServiceVm implements BackCameraUiServiceBase {
    private static final String TAG = "fang";
    private int mExternal360CamType = 0;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.park.parkui.BackCameraUiServiceVm.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            BackCameraUiServiceVm.this.refreshUi(message.getData());
        }
    };
    private boolean mIsShowRadarLayout;
    private ParkPageUiSet mParkPageUiSet;
    private ParkPanoramic mParkPanoramic;
    private Service mService;

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void changeVideoType() {
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void onDestroy() {
    }

    public BackCameraUiServiceVm(Service service) {
        this.mService = service;
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void onCreate() {
        Vm.getVm().getAction().getMain().init();
        ScreenLogic.setOrientation(this.mService.getApplicationContext().getResources().getConfiguration().orientation);
        this.mService.setTheme(R.style.Theme_AppCompat_NoActionBar);
        this.mExternal360CamType = FutureUtil.instance.is360External();
        HzbhdLog.d(TAG, "BackCameraUiService onCreate" + Vm.getVm().getReverseListener().isReversing());
        if (Vm.getVm().getReverseListener().isReversing()) {
            addViewToWindow();
        }
        Vm.getVm().getReverseListener().setActionBefortViewInit(new Runnable() {
            @Override
            public void run() {
                addViewToWindow();
            }
        });
    }


    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.showLog(TAG, "onConfigurationChanged");
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
                Vm.getVm().getAction().getRadar().setRadarVisible(0);
                if (SharePreUtil.getBoolValue(this.mService, BackCameraUiService.SHARE_IS_SHOW_RADAR, true)) {
                    Vm.getVm().getAction().getRadar().setRadarScale(true);
                } else {
                    Vm.getVm().getAction().getRadar().setRadarScale(false);
                }
            } else {
                Vm.getVm().getAction().getRadar().setRadarVisible(8);
            }
        }
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("refreshUi: " + this.mParkPageUiSet.isIsShowRadar());
        }
        if (this.mParkPageUiSet.isIsShowRadar()) {
            Vm.getVm().getAction().getRadar().setShowDistanceNotShowLocationUi(GeneralParkData.isShowDistanceNotShowLocationUi);
            Vm.getVm().getAction().getRadar().setShowLeftTopOneDistanceUi(GeneralParkData.isShowLeftTopOneDistanceUi);
        }
        List<PanoramicBtnUpdateEntity> list = GeneralParkData.dataList;
        if (list != null) {
            for (PanoramicBtnUpdateEntity panoramicBtnUpdateEntity : list) {
                if (this.mParkPageUiSet.getPanoramicBtnList() != null) {
                    this.mParkPageUiSet.getPanoramicBtnList().get(panoramicBtnUpdateEntity.getIndex()).setSelect(panoramicBtnUpdateEntity.isSelect());
                }
            }
            Vm.getVm().getAction().getReverse().updatePanoramic();
        }
        Vm.getVm().getAction().getReverse().setCustomPanoramicVisible(this.mParkPageUiSet.isShowCusPanoramicView());
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public Handler getmHandler() {
        return this.mHandler;
    }

    private void addViewToWindow() {
        if (com.hzbhd.util.LogUtil.log5()) {
            com.hzbhd.util.LogUtil.d("addViewToWindow: ");
        }
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
                Vm.getVm().getAction().getRadar().setRadarVisible(0);
                if (SharePreUtil.getBoolValue(this.mService, BackCameraUiService.SHARE_IS_SHOW_RADAR, true) || this.mService.getResources().getConfiguration().orientation == 1) {
                    Vm.getVm().getAction().getRadar().setRadarScale(true);
                } else {
                    Vm.getVm().getAction().getRadar().setRadarScale(false);
                }
                Vm.getVm().getAction().getRadar().setSmallRadar(this.mParkPageUiSet.isHaveLeftRightRadar());
            } else {
                Vm.getVm().getAction().getRadar().setRadarVisible(8);
            }
            LogUtil.showLog(TAG, "IsShowPanoramic:" + this.mParkPageUiSet.isShowPanoramic());
            if (this.mParkPageUiSet.isShowPanoramic()) {
                Vm.getVm().getAction().getReverse().setPanoramicVisible(0);
            } else {
                Vm.getVm().getAction().getReverse().setPanoramicVisible(8);
            }
            LogUtil.showLog(TAG, "isShowCusPanoramic:" + this.mParkPageUiSet.isShowCusPanoramicView());
            if (this.mParkPageUiSet.isShowCusPanoramicView()) {
                Vm.getVm().getAction().getReverse().setCustomPanoramicVisible(true);
            } else {
                Vm.getVm().getAction().getReverse().setCustomPanoramicVisible(false);
            }
        } else {
            LogUtil.showLog(TAG, "mParkPageUiSet==null");
            if (this.mService.getResources().getConfiguration().orientation == 2) {
                Vm.getVm().getAction().getRadar().setRadarVisible(8);
            }
        }
        if (ParkPanoramic.isEnableParkPanoramic()) {
            LogUtil.showLog(TAG, "isEnableParkPanoramic");
            if (this.mParkPanoramic == null) {
                this.mParkPanoramic = new ParkPanoramic();
            }
            this.mParkPanoramic.constructParkPanoramic();
        }
        Vm.getVm().getReverseMainView().setOnTouchListener(new AnonymousClass2());
        if (CanIdSpecialConfig.hideRadarLayoutCanID(CanbusConfig.INSTANCE.getCanType())) {
            Vm.getVm().getAction().getRadar().setRadarVisible(8);
        }
    }

    /* renamed from: com.hzbhd.canbus.park.parkui.BackCameraUiServiceVm$2, reason: invalid class name */
    class AnonymousClass2 implements View.OnTouchListener {
        private float downX = 0.0f;
        private float downY = 0.0f;
        private final Runnable runnable = new Runnable() { // from class: com.hzbhd.canbus.park.parkui.BackCameraUiServiceVm$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                if (CanBusDefault.INSTANCE.getLongClick()) {
                    LogUtil.showLog(BackCameraUiServiceVm.TAG, "onLongClick");
                    SendKeyManager.getInstance().setKeyEvent(HotKeyConstant.K_REVERSE_SETUP, 0, 0);
                }
            }
        };


        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            try {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (BackCameraUiServiceVm.this.mParkPageUiSet != null) {
                    Log.i("BackCameraUiService", "onTouch: touchListener: " + BackCameraUiServiceVm.this.mParkPageUiSet.getOnPanoramicItemTouchListener());
                    if (BackCameraUiServiceVm.this.mParkPageUiSet.getOnPanoramicItemTouchListener() != null) {
                        BackCameraUiServiceVm.this.mParkPageUiSet.getOnPanoramicItemTouchListener().onTouchItem(motionEvent);
                    }
                }
                if (motionEvent.getAction() == 1) {
                    External360CamCmds.getInstance().getCmds().sndCoord(x, y);
                }
                if (motionEvent.getAction() == 0) {
                    this.downX = motionEvent.getX();
                    this.downY = motionEvent.getY();
                    BackCameraUiServiceVm.this.mHandler.postDelayed(this.runnable, 500L);
                } else {
                    if (motionEvent.getAction() == 2 && Math.sqrt(Math.pow(motionEvent.getX() - this.downX, 2.0d) + Math.pow(motionEvent.getY() - this.downY, 2.0d)) < 20.0d) {
                        return true;
                    }
                    BackCameraUiServiceVm.this.mHandler.removeCallbacks(this.runnable);
                }
            } catch (Exception e) {
                LogUtil.showLog(BackCameraUiServiceVm.TAG, e.toString());
            }
            return true;
        }
    }

    @Override // com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase
    public void setAnalogColorUiChange(int i, int i2, int i3) {
        Log.d(TAG, "setAnalogColorUiChange ");
    }
}
