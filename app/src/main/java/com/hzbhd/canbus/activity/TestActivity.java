package com.hzbhd.canbus.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Config;
import com.hzbhd.canbus.util.LogUtil;

/* loaded from: classes.dex */
public class TestActivity extends AbstractBaseActivity implements View.OnClickListener {
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.hzbhd.canbus.activity.TestActivity.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.showLog(Config.LOG_TAG, "onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.showLog(Config.LOG_TAG, "onServiceDisconnected");
        }
    };

    private void findViews() {
    }

    private void initViews() {
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test);
        findViews();
        initViews();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_test) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(HzbhdComponentName.OnPauseOnResumeService);
        bindService(intent, this.mConnection, 1);
    }
}
