package com.hzbhd.canbus.msg_mgr;

import android.content.Context;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;

/* loaded from: classes2.dex */
public class MsgMgrFactory {
    private static final String TAG = "MsgMgrFactory";
    public static MsgMgrInterfaceUtil mMsgMgrInterface;

    public static synchronized MsgMgrInterfaceUtil getCanMsgMgrUtil(Context context) {
        if (mMsgMgrInterface == null) {
            mMsgMgrInterface = new MsgMgrInterfaceUtil(context);
        }
        return mMsgMgrInterface;
    }

    public static synchronized MsgMgrInterface getCanMsgMgr(Context context) {
        return getCanMsgMgrUtil(context).getMMsgMgrInterface();
    }

    public static void setThisNull() {
        mMsgMgrInterface = null;
    }
}
