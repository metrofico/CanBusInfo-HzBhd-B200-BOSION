package com.hzbhd.canbus.park.parkui;

import android.content.res.Configuration;
import android.os.Handler;

/* loaded from: classes2.dex */
public interface BackCameraUiServiceBase {
    void changeVideoType();

    Handler getmHandler();

    void onConfigurationChanged(Configuration configuration);

    void onCreate();

    void onDestroy();

    void setAnalogColorUiChange(int i, int i2, int i3);
}
