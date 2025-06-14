package com.hzbhd.canbus.ui_mgr;

import android.content.Context;
import com.hzbhd.canbus.interfaces.UiMgrInterface;

/* loaded from: classes2.dex */
public class UiMgrFactory {
    private static UiMgrInterfaceUtil mUiMgrInterface;

    public static synchronized UiMgrInterfaceUtil getCanUiMgrUtil(Context context) {
        if (mUiMgrInterface == null) {
            mUiMgrInterface = new UiMgrInterfaceUtil(context);
        }
        return mUiMgrInterface;
    }

    public static synchronized UiMgrInterface getCanUiMgr(Context context) {
        return getCanUiMgrUtil(context).getMUiMgrInterface();
    }

    public static void setThisNull() {
        mUiMgrInterface = null;
    }
}
