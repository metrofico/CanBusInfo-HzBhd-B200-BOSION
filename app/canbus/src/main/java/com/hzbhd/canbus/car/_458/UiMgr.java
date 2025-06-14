package com.hzbhd.canbus.car._458;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    RadarView radarView;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.radarView == null) {
            this.radarView = new RadarView(context);
        }
        this.radarView.refreshRadar();
        return this.radarView;
    }

    public void refreshRadar() {
        RadarView radarView = this.radarView;
        if (radarView != null) {
            radarView.refreshRadar();
        }
    }

    public UiMgr(Context context) {
    }
}
