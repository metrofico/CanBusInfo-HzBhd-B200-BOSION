package com.hzbhd.canbus.car._42;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private AirActivity mActivity;
    private Context mContext;

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
        if (getCurrentCarId() != 0) {
            return;
        }
        removeMainPrjBtn(this.mContext, 0, 1);
    }

    public UiMgr(Context context) {
        this.mContext = context;
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
            public void onStatusChange(int i) {
            }
        });
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
            public void onClickTopBtnItem(int i) {
            }
        });
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.3
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 3});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 1});
                } else if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -59, 4});
                }
            }
        });
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.4
            @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
            public void onBackPressed() {
                CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
            }
        });
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.5
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -55, 2, (byte) i3});
                }
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.6
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
            }
        });
        settingUiSet.setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._42.UiMgr.7
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == 0 && i2 == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -55, 1, 1});
                }
            }
        });
    }
}
