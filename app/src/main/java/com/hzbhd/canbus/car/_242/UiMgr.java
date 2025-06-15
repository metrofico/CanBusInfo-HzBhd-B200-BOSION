package com.hzbhd.canbus.car._242;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        int currentCarId = getCurrentCarId();
        if (currentCarId == 0) {
            getParkPageUiSet(context).setShowRadar(false);
        } else {
            if (currentCarId != 1) {
                return;
            }
            getParkPageUiSet(context).setShowRadar(true);
            removeFrontAirButton(context, 3, 0, 2);
            removeFrontAirButton(context, 3, 1, 1);
        }
    }

    public UiMgr(Context context) {
        this.mContext = context;
    }
}
