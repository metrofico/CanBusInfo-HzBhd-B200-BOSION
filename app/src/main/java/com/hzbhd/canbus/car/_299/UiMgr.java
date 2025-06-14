package com.hzbhd.canbus.car._299;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private CusPanoramicView mMyPanoramicView;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new CusPanoramicView(context);
        }
        this.mMyPanoramicView.setVisible(context);
        return this.mMyPanoramicView;
    }

    public UiMgr(Context context) {
    }
}
