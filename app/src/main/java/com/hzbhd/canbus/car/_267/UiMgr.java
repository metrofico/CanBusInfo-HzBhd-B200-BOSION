package com.hzbhd.canbus.car._267;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;

    public UiMgr(Context context) {
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._267.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -17, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemTouchListener(new OnSettingItemTouchListener() { // from class: com.hzbhd.canbus.car._267.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemTouchListener
            public void onTouchItem(int i, int i2, View view, MotionEvent motionEvent) {
                if (i == 1) {
                    if (i2 == 0) {
                        if (motionEvent.getAction() == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -111, 1, 1});
                        }
                        if (motionEvent.getAction() == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -111, 1, 0});
                            return;
                        }
                        return;
                    }
                    if (i2 == 1) {
                        if (motionEvent.getAction() == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -111, 2, 1});
                        }
                        if (motionEvent.getAction() == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -111, 2, 0});
                            return;
                        }
                        return;
                    }
                    if (i2 == 2) {
                        if (motionEvent.getAction() == 0) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -111, 3, 1});
                        }
                        if (motionEvent.getAction() == 1) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -111, 3, 0});
                            return;
                        }
                        return;
                    }
                    if (i2 != 3) {
                        return;
                    }
                    if (motionEvent.getAction() == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -111, 4, 1});
                    }
                    if (motionEvent.getAction() == 1) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -111, 4, 0});
                    }
                }
            }
        });
    }
}
