package com.hzbhd.canbus.car._316;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;


public class UiMgr extends AbstractUiMgr {
    private int mDifferent;

    public UiMgr(Context context) {
        if (getCurrentCarId() == 0) {
            removeFrontAirButtonByName(context, AirBtnAction.ECO);
            removeFrontAirButtonByName(context, "auto");
            removeFrontAirButtonByName(context, "dual");
        }
    }
}
