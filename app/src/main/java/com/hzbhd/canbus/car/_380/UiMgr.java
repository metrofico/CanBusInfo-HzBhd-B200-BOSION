package com.hzbhd.canbus.car._380;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;


public class UiMgr extends AbstractUiMgr {
    Context mContext;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        final PanelKeyPageUiSet panelKeyPageUiSet = getPanelKeyPageUiSet(context);
        panelKeyPageUiSet.setOnPanelKeyPositionListener(new OnPanelKeyPositionListener() { // from class: com.hzbhd.canbus.car._380.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener
            public void click(int i) {
                String str = panelKeyPageUiSet.getList().get(i);
                str.hashCode();
                switch (str) {
                    case "_380_item_1":
                        UiMgr.this.sendMessage(1);
                        break;
                    case "_380_item_2":
                        UiMgr.this.sendMessage(2);
                        break;
                    case "_380_item_3":
                        UiMgr.this.sendMessage(3);
                        break;
                    case "_380_item_4":
                        UiMgr.this.sendMessage(4);
                        break;
                    case "_380_item_5":
                        UiMgr.this.sendMessage(5);
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 110, (byte) i, 1});
    }

    public void sendMediaInfo0x91(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -111, 0, 0}, bArr));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
