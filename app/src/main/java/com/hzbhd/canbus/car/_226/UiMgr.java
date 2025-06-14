package com.hzbhd.canbus.car._226;

import android.content.Context;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AirBtnAction;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() == 1) {
            removeFrontAirButtonByName(context, "power");
            removeFrontAirButtonByName(context, "front_defog");
            removeFrontAirButtonByName(context, "dual");
            removeFrontAirButtonByName(context, "auto");
            removeFrontAirButtonByName(context, AirBtnAction.ECO);
        }
    }

    public UiMgr(Context context) {
        AirPageUiSet airUiSet = getAirUiSet(context);
        if (getCurrentCarId() == 1) {
            airUiSet.getFrontArea().setShowLeftWheel(false);
            airUiSet.getFrontArea().setShowRightWheel(false);
        }
    }
}
