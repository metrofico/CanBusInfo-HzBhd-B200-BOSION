package com.hzbhd.canbus.car._208;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SetTimeView;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    int data;
    private Context mContext;
    private MsgMgr msgMgr;

    public UiMgr(final Context context) {
        this.mContext = context;
        getParkPageUiSet(context).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._208.UiMgr.1
            @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
            public void onClickItem(int i) {
                if (i == 0) {
                    UiMgr.this.SendParkPageMessage(9);
                } else if (i == 1) {
                    UiMgr.this.SendParkPageMessage(10);
                } else {
                    if (i != 2) {
                        return;
                    }
                    UiMgr.this.SendParkPageMessage(11);
                }
            }
        });
        getSettingUiSet(context).setOnSettingItemClickListener(new OnSettingItemClickListener() { // from class: com.hzbhd.canbus.car._208.UiMgr.2
            @Override // com.hzbhd.canbus.interfaces.OnSettingItemClickListener
            public void onClickItem(int i, int i2) {
                if (i == 2 && i2 == 0) {
                    new SetTimeView().showDialog(UiMgr.this.getMsgMgr(context).getCurrentActivity(), CommUtil.getStrByResId(context, "_283_setting_title_9"), true, true, true, true, true, new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._208.UiMgr.2.1
                        @Override // com.hzbhd.canbus.util.SetTimeView.TimeResultListener
                        public void result(int i3, int i4, int i5, int i6, int i7) {
                            CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i6, (byte) i7, 0, 0, 1, (byte) i3, (byte) i4, (byte) i5, 2});
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void SendParkPageMessage(int i) {
        switch (i) {
            case 9:
                if (getMsgMgr(this.mContext).a) {
                    this.data = 0;
                } else {
                    this.data = 1;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte) i, (byte) this.data});
                break;
            case 10:
                if (getMsgMgr(this.mContext).b) {
                    this.data = 0;
                } else {
                    this.data = 1;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte) i, (byte) this.data});
                break;
            case 11:
                if (getMsgMgr(this.mContext).c) {
                    this.data = 0;
                } else {
                    this.data = 1;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte) i, (byte) this.data});
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }
}
