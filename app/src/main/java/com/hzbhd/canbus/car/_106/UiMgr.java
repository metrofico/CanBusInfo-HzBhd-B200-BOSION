package com.hzbhd.canbus.car._106;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.DataHandleUtils;

/* loaded from: classes.dex */
public class UiMgr extends AbstractUiMgr {
    MsgMgr mMsgMgr;

    public UiMgr(Context context) {
    }

    @Override // com.hzbhd.canbus.ui_mgr.AbstractUiMgr, com.hzbhd.canbus.interfaces.UiMgrInterface
    public void updateUiByDifferentCar(Context context) {
        super.updateUiByDifferentCar(context);
    }

    public void makeConnection() {
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    public void send0x86VolControl(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(i, 6, 1), DataHandleUtils.getIntFromByteWithBit(i, 5, 1), DataHandleUtils.getIntFromByteWithBit(i, 4, 1), DataHandleUtils.getIntFromByteWithBit(i, 3, 1), DataHandleUtils.getIntFromByteWithBit(i, 2, 1), DataHandleUtils.getIntFromByteWithBit(i, 1, 1), DataHandleUtils.getIntFromByteWithBit(i, 0, 1))});
    }

    public void send0xC0MediaInfo(int i, int i2, int i3, int i4, int i5, int i6) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    public void send0xC1Info(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }
}
