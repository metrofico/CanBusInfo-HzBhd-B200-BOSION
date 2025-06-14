package com.hzbhd.canbus.car._275;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    private Context mContext;
    private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener() { // from class: com.hzbhd.canbus.car._275.UiMgr.1
        @Override // com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener
        public void onClickItem(int i) {
            if (i == 0) {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 1});
                return;
            }
            if (i == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 3});
                return;
            }
            if (i == 2) {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 4});
                return;
            }
            if (i == 3) {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 5});
            } else if (i == 4) {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 6});
            } else {
                if (i != 5) {
                    return;
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 3, 7});
            }
        }
    };

    public UiMgr(Context context) {
        getParkPageUiSet(context).setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
    }
}
