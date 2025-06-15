package com.hzbhd.canbus.car._389;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;


public class UiMgr extends AbstractUiMgr {
    private MsgMgr msgMgr;
    SettingPageUiSet settingPageUiSet;

    public UiMgr(final Context context) {
        SettingPageUiSet settingUiSet = getSettingUiSet(context);
        this.settingPageUiSet = settingUiSet;
        settingUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._389.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                String titleSrn = UiMgr.this.settingPageUiSet.getList().get(i).getItemList().get(i2).getTitleSrn();
                titleSrn.hashCode();
                if (titleSrn.equals("language_setup")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte) i3});
                    UiMgr.this.getMsgMgr(context).updateSettings(i, i2, i3);
                }
            }
        });
    }

    public void sendMediaInfo0x91(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -110, (byte) i}, bArr));
    }

    public void sendMediaInfo0x93(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -109, (byte) i}, bArr));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
