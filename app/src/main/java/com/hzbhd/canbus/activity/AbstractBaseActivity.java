package com.hzbhd.canbus.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.hzbhd.canbus.comm.MyApplication;
import com.hzbhd.canbus.interfaces.MgrRefreshUiInterface;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;

public abstract class AbstractBaseActivity extends Activity implements MgrRefreshUiInterface {
    private boolean isActivityFront = false;

    public UiMgrInterface getUiMgrInterface(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        if (canUiMgr == null) {
            throw new NullPointerException("1. Verifica si hay una carpeta para este modelo de coche en el directorio \"car\"; 2. Intenta usar try...catch... en el constructor de UiMgr para depurar; 3. Asegúrate de que el archivo JSON corresponda con el archivo Bean.");
        }
        if (!MyApplication.IS_SET) {
            canUiMgr.updateUiByDifferentCar(context);
            MyApplication.IS_SET = true;
        }
        return canUiMgr;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((MyApplication) getApplication()).addActivity(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        MsgMgrInterfaceUtil canMsgMgrUtil = MsgMgrFactory.getCanMsgMgrUtil(this);
        if (canMsgMgrUtil != null) {
            canMsgMgrUtil.setMgrRefreshUiInterface(this);
        }
        this.isActivityFront = true;
    }

    @Override // android.app.Activity
    protected void onPause() {
        this.isActivityFront = false;
        super.onPause();
    }

    public boolean isShouldRefreshUi() {
        return this.isActivityFront;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ((MyApplication) getApplication()).removeActivity(this);
    }
}
