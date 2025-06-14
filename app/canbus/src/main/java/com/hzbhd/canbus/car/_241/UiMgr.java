package com.hzbhd.canbus.car._241;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener() { // from class: com.hzbhd.canbus.car._241.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnSettingPageStatusListener
        public void onStatusChange(int i) {
            if (i == 0) {
                UiMgr.this.sendData(new byte[]{22, -112, 37});
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._241.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0 && i2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i3});
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
