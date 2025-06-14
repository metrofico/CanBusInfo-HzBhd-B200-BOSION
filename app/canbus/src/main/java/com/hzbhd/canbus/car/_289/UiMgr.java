package com.hzbhd.canbus.car._289;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        this.mContext = context;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        getParkPageUiSet(context);
    }
}
