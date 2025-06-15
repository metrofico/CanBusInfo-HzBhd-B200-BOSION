package com.hzbhd.canbus.car._244;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._244.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -112, 64});
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._244.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 0, (byte) i3});
                    return;
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 1, (byte) i3});
                    return;
                }
            }
            if (i != 1) {
                return;
            }
            if (i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 2, (byte) i3});
            } else {
                if (i2 != 1) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, Byte.MIN_VALUE, 3, (byte) i3});
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
    }
}
