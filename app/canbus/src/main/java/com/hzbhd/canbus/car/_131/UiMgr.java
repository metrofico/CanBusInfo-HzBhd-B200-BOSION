package com.hzbhd.canbus.car._131;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private MsgMgr msgMgr;

    public UiMgr(final Context context) {
        this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._131.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    UiMgr.this.sendMsg(i2, i3);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendMsg(i2 + 5, i3);
                } else if (i2 == 0) {
                    UiMgr.this.sendMsg(10, i3);
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    UiMgr.this.sendMsg(11, i3);
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._131.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 2 && i2 == 2) {
                    UiMgr.this.sendMsg(12, 0);
                }
                if (UiMgr.this.msgMgr._0x33_data0 == 12) {
                    if (UiMgr.this.msgMgr._0x33_data1 == 0) {
                        Context context2 = context;
                        Toast.makeText(context2, context2.getText(R.string._131_result_success), 0);
                    } else {
                        Context context3 = context;
                        Toast.makeText(context3, context3.getText(R.string._131_result_fail), 0);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsg(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i, (byte) i2});
    }
}
