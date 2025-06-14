package com.hzbhd.canbus.car._274;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private MsgMgr mMsgMgr;

    public UiMgr(final Context context) {
        this.mContext = context;
        getMsgMgr(context).updateSettings(1, 0, SharePreUtil.getIntValue(context, "share_274_left_camera_input", 0));
        getMsgMgr(context).updateSettings(1, 1, SharePreUtil.getIntValue(context, "share_274_right_camera_input", 0));
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._274.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0) {
                    if (i2 == 0) {
                        CanbusMsgSender.sendMsg(new byte[]{22, 110, 9, (byte) i3});
                    }
                } else if (i == 1) {
                    if (i2 == 0) {
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        UiMgr.this.getMsgMgr(context).setCameraInput("share_274_left_camera_input", i3);
                    } else if (i2 == 1) {
                        UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                        UiMgr.this.getMsgMgr(context).setCameraInput("share_274_right_camera_input", i3);
                    }
                }
            }
        });
        settingUiSet.setOnSettingConfirmDialogListener(new OnConfirmDialogListener() { // from class: com.hzbhd.canbus.car._274.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnConfirmDialogListener
            public void onConfirmClick(int i, int i2) {
                if (i == 0 && i2 == 1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 110, 10, 1});
                    Toast.makeText(UiMgr.this.mContext, R.string.have_update, 0).show();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
