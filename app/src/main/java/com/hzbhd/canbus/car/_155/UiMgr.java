package com.hzbhd.canbus.car._155;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;



public class UiMgr extends AbstractUiMgr {
    public UiMgr(Context context) {
        final OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener() { // from class: com.hzbhd.canbus.car._155.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener
            public void onClickBottomBtnItem(int i) {
                String str = originalCarDevicePageUiSet.getRowBottomBtnAction()[i];
                str.hashCode();
                switch (str) {
                    case "left":
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, 4});
                        break;
                    case "play":
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, -127});
                        break;
                    case "stop":
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, Byte.MIN_VALUE});
                        break;
                    case "right":
                        CanbusMsgSender.sendMsg(new byte[]{22, -110, 3});
                        break;
                }
            }
        });
    }
}
