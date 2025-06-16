package com.hzbhd.canbus.car._481;

import android.content.Context;
import android.util.Log;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;


public class MsgMgr extends AbstractMsgMgr {
    Context mContext;
    private UiMgr mUiMgr;

    @Override
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.mContext = context;
        mUiMgr = getUiMgr(this.mContext);
        GeneralTireData.isHaveSpareTire = false;
    }

    @Override
    public void canbusInfoChange(Context context, byte[] msgReceived) {
        super.canbusInfoChange(context, msgReceived);
        byte type = msgReceived[5];

       /* switch (type) {
            case 0x20: {
                _0x20DoorInfo(msgReceived);
            }
            case 0x21: {

            }
            case 0x22: {

            }
        }*/


        Log.d("MsgMgrSWM", "CanbusInfoChange received");
    }

    public void _0x20DoorInfo(byte[] msgReceived) {
        if (msgReceived[1] == 0x35) {
            GeneralDoorData.isLeftFrontDoorOpen = true;
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
