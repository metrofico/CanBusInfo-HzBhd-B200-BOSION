package com.hzbhd.canbus.car._252;

import android.content.Context;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;



public class UiMgr extends AbstractUiMgr {
    public UiMgr(Context context) {
        getAirUiSet(context).setOnAirPageStatusListener(new OnAirPageStatusListener() { // from class: com.hzbhd.canbus.car._252.UiMgr.1
            @Override // com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener
            public void onStatusChange(int i) {
                if (i == 1) {
                    UiMgr.this.sendData(new byte[]{22, -112, 35});
                    UiMgr.this.sendData(new byte[]{22, -112, 54});
                    UiMgr.this.sendData(new byte[]{22, -112, Byte.MAX_VALUE});
                }
            }
        });
    }
}
