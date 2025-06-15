package com.hzbhd.canbus.car._351;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;


public class MsgMgr extends AbstractMsgMgr {
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (byteArrayToIntArray[1] != 32) {
            return;
        }
        setOx20WheelKeyInfo();
    }

    private void setOx20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 3) {
            buttonKey(46);
            return;
        }
        if (i == 4) {
            buttonKey(45);
        } else if (i == 5) {
            buttonKey(14);
        } else {
            if (i != 7) {
                return;
            }
            buttonKey(2);
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }
}
