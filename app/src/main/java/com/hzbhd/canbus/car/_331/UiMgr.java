package com.hzbhd.canbus.car._331;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class UiMgr extends AbstractUiMgr {
    private CusPanoramicView mMyPanoramicView;

    public UiMgr(Context context) {
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {

        if (this.mMyPanoramicView == null) {
            this.mMyPanoramicView = new CusPanoramicView(context, null, 0, 6, null);
        }
        CusPanoramicView cusPanoramicView = this.mMyPanoramicView;

        return cusPanoramicView;
    }
}
