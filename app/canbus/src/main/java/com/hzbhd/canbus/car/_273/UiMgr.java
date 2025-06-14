package com.hzbhd.canbus.car._273;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.car._18.MyPanoramicView;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private View mMyPanoramiceView;
    private ParkPageUiSet parkPageUiSet;

    public UiMgr(Context context) {
        if (this.mMyPanoramiceView == null) {
            this.mMyPanoramiceView = new MyPanoramicView(context);
        }
        this.parkPageUiSet = getParkPageUiSet(context);
    }
}
