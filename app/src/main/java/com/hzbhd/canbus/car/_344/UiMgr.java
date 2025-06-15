package com.hzbhd.canbus.car._344;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    Context mContext;

    public UiMgr(Context context) {
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }
}
