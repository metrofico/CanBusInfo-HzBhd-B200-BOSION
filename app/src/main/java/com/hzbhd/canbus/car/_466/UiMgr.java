package com.hzbhd.canbus.car._466;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private View mCusPanoramicView;

    public UiMgr(Context context) {
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.mCusPanoramicView == null) {
            this.mCusPanoramicView = new MyCameraBackView(context);
        }
        return this.mCusPanoramicView;
    }
}
