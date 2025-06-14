package com.hzbhd.canbus.car._134;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener;
import com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import nfore.android.bt.res.NfDef;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.3
        @Override // com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener
        public void onClickTopBtnItem(int i) {
            switch (i) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 8});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 6});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 7});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 11});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 9});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 10});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 2});
                    break;
            }
        }
    };
    private OnOriginalCarDevicePageStatusListener mOnOriginalCarDevicePageStatusListener = new OnOriginalCarDevicePageStatusListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.4
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDevicePageStatusListener
        public void onStatusChange(int i) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 16});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 18});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 19});
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 2});
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, 3});
        }
    };
    private OnOriginalCarDeviceBackPressedListener mOnOriginalCarDeviceBackPressedListener = new OnOriginalCarDeviceBackPressedListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.5
        @Override // com.hzbhd.canbus.interfaces.OnOriginalCarDeviceBackPressedListener
        public void onBackPressed() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
        }
    };
    private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.6
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                if (i2 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 15, (byte) i3});
                    return;
                } else if (i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 17, (byte) i3});
                    return;
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 18, (byte) i3});
                    return;
                }
            }
            switch (i2) {
                case 0:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 0, (byte) i3});
                    break;
                case 1:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, (byte) i3});
                    break;
                case 2:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) i3});
                    break;
                case 3:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, (byte) i3});
                    break;
                case 4:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 4, (byte) i3});
                    break;
                case 5:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, (byte) i3});
                    break;
                case 6:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 6, (byte) i3});
                    break;
                case 7:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, (byte) i3});
                    break;
                case 8:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 8, (byte) i3});
                    break;
                case 9:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) i3});
                    break;
                case 10:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, (byte) i3});
                    break;
                case 11:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 20, (byte) i3});
                    break;
                case 12:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 22, (byte) i3});
                    break;
                case 13:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 21, (byte) i3});
                    break;
                case 14:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 11, (byte) i3});
                    break;
                case 15:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, (byte) i3});
                    break;
                case 16:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) i3});
                    break;
                case 17:
                    CanbusMsgSender.sendMsg(new byte[]{22, -124, 48, (byte) i3});
                    break;
            }
        }
    };
    private OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.7
        @Override // com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener
        public void onClickItem(int i, int i2, int i3) {
            if (i != 1) {
                return;
            }
            if (i2 == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 14, (byte) i3});
            } else if (i2 == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 16, (byte) i3});
            } else {
                if (i2 != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 19, (byte) i3});
            }
        }
    };

    public UiMgr(final Context context) {
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
        settingUiSet.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 2) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 23, 0});
                        Context context2 = context;
                        Toast.makeText(context2, context2.getText(R.string.reset_completed), 0).show();
                    } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -124, 24, 0});
                        Context context3 = context;
                        Toast.makeText(context3, context3.getText(R.string.reset_completed), 0).show();
                    }
                }
            }
        });
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalCarDevicePageStatusListener(this.mOnOriginalCarDevicePageStatusListener);
        originalCarDevicePageUiSet.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
        originalCarDevicePageUiSet.setOnOriginalCarDeviceBackPressedListener(this.mOnOriginalCarDeviceBackPressedListener);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._134.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                if (i == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                    return;
                }
                if (i == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 5});
                    return;
                }
                if (i == 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 0});
                    return;
                }
                if (i == 3) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 1});
                } else if (i == 4) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, 4});
                } else {
                    if (i != 5) {
                        return;
                    }
                    CanbusMsgSender.sendMsg(new byte[]{22, -126, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                }
            }
        });
    }
}
