package com.hzbhd.canbus.car._133;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._133.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
        public void click(int i) {
            if (i == 0) {
                UiMgr.this.SendMsg(1);
            } else {
                if (i != 1) {
                    return;
                }
                UiMgr.this.SendMsg(2);
            }
        }
    };

    public UiMgr(Context context) {
        getPanelKeyPageUiSet(context).setOnPanelKeyPositionListener(this.mOnPanelKeyPositionListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) i});
        CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
    }
}
