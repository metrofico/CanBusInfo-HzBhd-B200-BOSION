package com.hzbhd.canbus.car._447;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    public ID447RadarView id447RadarView;
    private Context mContext;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public View getCusPanoramicView(Context context) {
        if (this.id447RadarView == null) {
            this.id447RadarView = new ID447RadarView(context);
        }
        return this.id447RadarView;
    }

    public UiMgr(Context context) {
        this.mContext = context;
        getParkPageUiSet(context).setShowCusPanoramicView(true);
    }
}
