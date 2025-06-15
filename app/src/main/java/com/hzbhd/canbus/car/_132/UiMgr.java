package com.hzbhd.canbus.car._132;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr msgMgr;
    private int currentTopBtn1 = 0;
    private int currentTopBtn2 = 0;
    private int currentButtomBtn = 0;
    private OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._132.UiMgr.2
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            if (i == 0) {
                if (UiMgr.this.currentTopBtn1 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 7});
                    UiMgr.this.currentTopBtn1 = 1;
                    return;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 8});
                    UiMgr.this.currentTopBtn1 = 0;
                    return;
                }
            }
            if (i != 1) {
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 0});
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, -16});
                    return;
                }
            }
            if (UiMgr.this.currentTopBtn2 == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 9});
                UiMgr.this.currentTopBtn2 = 1;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -57, 10});
                UiMgr.this.currentTopBtn2 = 0;
            }
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._132.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i == 0) {
                switch (i2) {
                    case 0:
                        UiMgr.this.settingMsg(1, i3);
                        break;
                    case 1:
                        UiMgr.this.settingMsg(2, i3);
                        break;
                    case 2:
                        UiMgr.this.settingMsg(3, i3 + 1);
                        break;
                    case 3:
                        UiMgr.this.settingMsg(4, i3 + 1);
                        break;
                    case 4:
                        UiMgr.this.settingMsg(5, i3);
                        break;
                    case 5:
                        UiMgr.this.settingMsg(17, i3);
                        break;
                    case 6:
                        UiMgr.this.settingMsg(18, i3 + 1);
                        break;
                    case 7:
                        UiMgr.this.settingMsg(33, i3 + 1);
                        break;
                    case 8:
                        UiMgr.this.settingMsg(34, i3 + 1);
                        break;
                    case 9:
                        UiMgr.this.settingMsg(35, i3);
                        break;
                }
            }
        }
    };

    public UiMgr(Context context) {
        this.mContext = context;
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        ParkPageUiSet parkPageUiSet = getParkPageUiSet(this.mContext);
        if (getCurrentCarId() == 0) {
            removeDrivigDateItem(this.mContext, 0, 0, 2);
            removeMainPrjBtn(this.mContext, 0, 2);
            parkPageUiSet.setShowTrack(false);
        } else {
            parkPageUiSet.setShowTrack(true);
        }
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._132.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 4});
                    return;
                }
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 3});
                } else {
                    if (UiMgr.this.currentButtomBtn == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                        UiMgr.this.currentButtomBtn = 1;
                        originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", OriginalBtnAction.PLAY, "right"});
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                        UiMgr.this.msgMgr.updateOriginalCarDevice(bundle);
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -57, 2});
                    UiMgr.this.currentButtomBtn = 0;
                    originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", OriginalBtnAction.PAUSE, "right"});
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
                    UiMgr.this.msgMgr.updateOriginalCarDevice(bundle2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, (byte) i2, 0});
    }
}
