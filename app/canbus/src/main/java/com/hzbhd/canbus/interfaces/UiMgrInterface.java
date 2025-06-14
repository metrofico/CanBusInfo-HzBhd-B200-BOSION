package com.hzbhd.canbus.interfaces;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.BubbleUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.ui_set.MainPageUiSet;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.PageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.ui_set.WarningPageUiSet;

/* loaded from: classes2.dex */
public interface UiMgrInterface {
    AirPageUiSet getAirUiSet(Context context);

    AmplifierPageUiSet getAmplifierPageUiSet(Context context);

    BubbleUiSet getBubbleUiSet(Context context);

    View getCusPanoramicView(Context context);

    DriverDataPageUiSet getDriverDataPageUiSet(Context context);

    HybirdPageUiSet getHybirdPageUiSet(Context context);

    MainPageUiSet getMainUiSet(Context context);

    OnStartPageUiSet getOnStartPageUiSet(Context context);

    OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context);

    PageUiSet getPageUiSet(Context context);

    PanelKeyPageUiSet getPanelKeyPageUiSet(Context context);

    ParkPageUiSet getParkPageUiSet(Context context);

    SettingPageUiSet getSettingUiSet(Context context);

    SyncPageUiSet getSyncPageUiSet(Context context);

    TirePageUiSet getTireUiSet(Context context);

    WarningPageUiSet getWarningPageUiSet(Context context);

    void updateUiByDifferentCar(Context context);
}
