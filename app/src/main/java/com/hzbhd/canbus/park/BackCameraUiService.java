package com.hzbhd.canbus.park;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.hzbhd.canbus.interfaces.AnalogColorSettingInterface;
import com.hzbhd.canbus.interfaces.VideoTypeUiChangeInterface;
import com.hzbhd.canbus.park.parkui.BackCameraUiServiceBase;
import com.hzbhd.canbus.park.parkui.BackCameraUiServiceVm;

public class BackCameraUiService extends Service implements AnalogColorSettingInterface, VideoTypeUiChangeInterface {
    public static final int MSG_CHECK_BACKCAMERA_STATE = 0;
    public static final int MSG_REFRESH_UI = 1;
    public static final String SHARE_IS_SHOW_RADAR = "is_show_radar";
    private BackCameraUiServiceBase backCameraUiServiceBase;

    private BackCameraUiServiceBase getBackCameraUiServiceBase() {
        if (this.backCameraUiServiceBase == null) {
            this.backCameraUiServiceBase = new BackCameraUiServiceVm(this);
        }
        return this.backCameraUiServiceBase;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        getBackCameraUiServiceBase().onCreate();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        getBackCameraUiServiceBase().onConfigurationChanged(configuration);
    }

    public Handler getHandler() {
        return getBackCameraUiServiceBase().getmHandler();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        getBackCameraUiServiceBase().onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override // com.hzbhd.canbus.interfaces.AnalogColorSettingInterface
    public void setAnalogColorUiChange(int i, int i2, int i3) {
        getBackCameraUiServiceBase().setAnalogColorUiChange(i, i2, i3);
    }

    @Override // com.hzbhd.canbus.interfaces.VideoTypeUiChangeInterface
    public void changeVideoType() {
        getBackCameraUiServiceBase().changeVideoType();
    }

    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public BackCameraUiService getService() {
            return BackCameraUiService.this;
        }
    }
}
