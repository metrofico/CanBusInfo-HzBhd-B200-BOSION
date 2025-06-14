package com.hzbhd.canbus.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hzbhd.R;
import com.hzbhd.canbus.service.TestCanBusService;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SetErrorLogView;
import com.hzbhd.canbus.util.SetErrorProgressView;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes.dex */
public class TestCanBusActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String BROADCAST_ACTION_DISC = "com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast";
    public static final String BROADCAST_ACTION_SUCCESS = "com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast_SUCCESS";
    private static final String CAN_BUS_SWITCH_KEY = "TEST_SERVICE_SWITCH_KEY";
    private static Button start_button;
    private final ServiceConnection conn = new ServiceConnection() { // from class: com.hzbhd.canbus.activity.TestCanBusActivity.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TestCanBusActivity.this.isBind = true;
            TestCanBusActivity.this.service = ((TestCanBusService.MyBinder) iBinder).getService();
            TestCanBusActivity.this.service.getTag();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            TestCanBusActivity.this.isBind = false;
        }
    };
    Intent intent;
    boolean isBind;
    private LocatiopnBroadcast locatiopnBroadcast;
    TestCanBusService service;
    SetErrorLogView setErrorLogView;
    SetErrorProgressView setErrorProgressView;

    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_test_can_bus);
        Button button = findViewById(R.id.start_button);
        start_button = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.activity.TestCanBusActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TestCanBusActivity.this.onClick(view);
            }
        });
        if (SharePreUtil.getBoolValue(this, CAN_BUS_SWITCH_KEY, false)) {
            bindService();
        }
    }

    @Override
    // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && i == 4 && SharePreUtil.getBoolValue(this, CAN_BUS_SWITCH_KEY, false)) {
            Toast.makeText(this, "No se permiten regresar en el estado de detección", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.start_button) {
            return;
        }
        startService();
        SharePreUtil.setBoolValue(this, CAN_BUS_SWITCH_KEY, true);
        showProgressView(true, false);
    }

    public void showProgressView(boolean z, boolean z2) {
        if (z) {
            start_button.setVisibility(View.GONE);
        } else {
            start_button.setVisibility(View.VISIBLE);
        }
        if (this.setErrorProgressView == null) {
            this.setErrorProgressView = new SetErrorProgressView();
        }
        this.setErrorProgressView.showDialog(this, z, z2, new SetErrorProgressView.ThisInterface() { // from class: com.hzbhd.canbus.activity.TestCanBusActivity.1
            @Override // com.hzbhd.canbus.util.SetErrorProgressView.ThisInterface
            public boolean onPause() {
                if (TestCanBusActivity.this.service == null) {
                    Toast.makeText(TestCanBusActivity.this, "Operation too fast！", Toast.LENGTH_SHORT).show();
                    TestCanBusActivity.this.bindService();
                    return false;
                }
                TestCanBusActivity.this.service.pauseTest();
                return true;
            }

            @Override // com.hzbhd.canbus.util.SetErrorProgressView.ThisInterface
            public boolean onContinue() {
                if (TestCanBusActivity.this.service == null) {
                    Toast.makeText(TestCanBusActivity.this, "Operation too fast！", Toast.LENGTH_SHORT).show();
                    TestCanBusActivity.this.bindService();
                    return false;
                }
                TestCanBusActivity.this.service.continueTest();
                return true;
            }

            @Override // com.hzbhd.canbus.util.SetErrorProgressView.ThisInterface
            public boolean onSuccessButtonClick() {
                TestCanBusActivity.this.showProgressView(false, false);
                SharePreUtil.setBoolValue(TestCanBusActivity.this, TestCanBusActivity.CAN_BUS_SWITCH_KEY, false);
                TestCanBusActivity.this.stopService();
                try {
                    if (TestCanBusActivity.this.locatiopnBroadcast != null) {
                        TestCanBusActivity testCanBusActivity = TestCanBusActivity.this;
                        testCanBusActivity.unregisterReceiver(testCanBusActivity.locatiopnBroadcast);
                    }
                    if (TestCanBusActivity.this.service != null) {
                        TestCanBusActivity.this.service.stopTest();
                    }
                    TestCanBusActivity.this.finish();
                    return true;
                } catch (Exception e) {
                    MyLog.temporaryTracking(e.toString());
                    return true;
                }
            }

            @Override // com.hzbhd.canbus.util.SetErrorProgressView.ThisInterface
            public boolean onCancel() {
                if (TestCanBusActivity.this.service == null) {
                    Toast.makeText(TestCanBusActivity.this, "Operation too fast！", Toast.LENGTH_SHORT).show();
                    TestCanBusActivity.this.bindService();
                    return false;
                }
                TestCanBusActivity.this.showProgressView(false, false);
                SharePreUtil.setBoolValue(TestCanBusActivity.this, TestCanBusActivity.CAN_BUS_SWITCH_KEY, false);
                TestCanBusActivity.this.stopService();
                try {
                    if (TestCanBusActivity.this.locatiopnBroadcast != null) {
                        TestCanBusActivity testCanBusActivity = TestCanBusActivity.this;
                        testCanBusActivity.unregisterReceiver(testCanBusActivity.locatiopnBroadcast);
                    }
                    TestCanBusActivity.this.finish();
                } catch (Exception e) {
                    MyLog.temporaryTracking(e.toString());
                }
                TestCanBusActivity.this.service.stopTest();
                return true;
            }
        });
    }

    public void showErrorLogView(String str) {
        if (this.setErrorLogView == null) {
            this.setErrorLogView = new SetErrorLogView();
        }
        this.setErrorLogView.showDialog(this, str, new SetErrorLogView.ThisInterface() { // from class: com.hzbhd.canbus.activity.TestCanBusActivity.2
            @Override // com.hzbhd.canbus.util.SetErrorLogView.ThisInterface
            public boolean ignoreCallBack() {
                if (TestCanBusActivity.this.service == null) {
                    Toast.makeText(TestCanBusActivity.this, "Operation too fast！", Toast.LENGTH_SHORT).show();
                    TestCanBusActivity.this.bindService();
                    return false;
                }
                TestCanBusActivity.this.service.continueTest();
                TestCanBusActivity.this.showProgressView(true, false);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService() {
        MyLog.temporaryTracking("绑定服务");
        if (this.intent == null) {
            this.intent = new Intent(this, TestCanBusService.class);
        }
        bindService(this.intent, this.conn, 1);
    }

    private void unBindService() {
        MyLog.temporaryTracking("解除绑定服务");
        try {
            unbindService(this.conn);
        } catch (Exception unused) {
            MyLog.temporaryTracking("ServiceConnection没有绑定任何Service");
        }
    }

    private void startService() {
        MyLog.temporaryTracking("启动服务");
        if (this.intent == null) {
            this.intent = new Intent(this, TestCanBusService.class);
        }
        startService(this.intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopService() {
        MyLog.temporaryTracking("停止服务");
        Intent intent = this.intent;
        if (intent != null) {
            stopService(intent);
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.locatiopnBroadcast = new LocatiopnBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION_DISC);
        intentFilter.addAction(BROADCAST_ACTION_SUCCESS);
        registerReceiver(this.locatiopnBroadcast, intentFilter);
    }

    public class LocatiopnBroadcast extends BroadcastReceiver {
        public LocatiopnBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            MyLog.temporaryTracking("有收到广播");
            if (intent.getAction().equals(TestCanBusActivity.BROADCAST_ACTION_DISC)) {
                MyLog.temporaryTracking("收到了ERROR广播");
                if (SharePreUtil.getBoolValue(TestCanBusActivity.this, TestCanBusActivity.CAN_BUS_SWITCH_KEY, false)) {
                    TestCanBusActivity.this.runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MyLog.temporaryTracking("错误信息：" + intent.getStringExtra("ERROR_DATA"));
                            TestCanBusActivity.this.showProgressView(false, false);
                            TestCanBusActivity.this.showErrorLogView(intent.getStringExtra("ERROR_DATA"));
                        }
                    });
                }
            }
            if (intent.getAction().equals(TestCanBusActivity.BROADCAST_ACTION_SUCCESS)) {
                MyLog.temporaryTracking("收到了SUCCESS广播");
                if (SharePreUtil.getBoolValue(TestCanBusActivity.this, TestCanBusActivity.CAN_BUS_SWITCH_KEY, false)) {
                    TestCanBusActivity.this.runOnUiThread(new Runnable() { // from class: com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast.2
                        @Override // java.lang.Runnable
                        public void run() {
                            TestCanBusActivity.this.showProgressView(true, true);
                        }
                    });
                }
            }
        }
    }
}
