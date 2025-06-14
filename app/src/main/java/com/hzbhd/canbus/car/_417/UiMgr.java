package com.hzbhd.canbus.car._417;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    Context mContext;
    MsgMgr mMsgMgr;
    int eachId = getEachId();
    int differentId = getCurrentCarId();

    public UiMgr(Context context) {
        this.mContext = context;
        getSettingUiSet(this.mContext).setOnSettingItemSelectListener(new OnSettingItemSelectListener() { // from class: com.hzbhd.canbus.car._417.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemSelectListener
            public void onClickItem(int i, int i2, int i3) {
                if (i == 0 && i2 == 0) {
                    if (i3 == 1) {
                        UiMgr uiMgr = UiMgr.this;
                        uiMgr.getMsgMgr(uiMgr.mContext).showButton();
                    } else {
                        UiMgr uiMgr2 = UiMgr.this;
                        uiMgr2.getMsgMgr(uiMgr2.mContext).hideButton();
                    }
                }
            }
        });
        getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._417.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.sendPanoramicInfo0xFD(3);
                    return;
                }
                if (i == 1) {
                    UiMgr.this.sendPanoramicInfo0xFD(4);
                    return;
                }
                if (i == 2) {
                    UiMgr.this.sendPanoramicInfo0xFD(5);
                } else if (i == 3) {
                    UiMgr.this.sendPanoramicInfo0xFD(6);
                } else {
                    if (i != 4) {
                        return;
                    }
                    UiMgr.this.sendPanoramicInfo0xFD(2);
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public void sendPanoramicInfo0xFD(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, (byte) i});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
