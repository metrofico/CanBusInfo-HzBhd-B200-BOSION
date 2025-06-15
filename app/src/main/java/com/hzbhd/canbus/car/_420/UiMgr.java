package com.hzbhd.canbus.car._420;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;


public class UiMgr extends AbstractUiMgr {
    public UiMgr(Context context) {
        getTireUiSet(context).setHaveSpareTire(false);
    }
}
