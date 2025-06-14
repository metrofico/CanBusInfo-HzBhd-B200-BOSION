package com.hzbhd.canbus.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;

/* loaded from: classes.dex */
public class UPDProgressActivity extends Activity {
    private static final String CAN_TYPE_ID = "can_type_id";
    private static final String IS_CANBUS_SWC_UPD = "is_canbus_swc_upd";
    private boolean mIsCanSwcUpd;
    private TextView mProgressInfoTv;
    private SeekBar mSeekBar;
    private TextView mUpgradeTitleTv;
    private Handler mHandler = new Handler() { // from class: com.hzbhd.canbus.activity.UPDProgressActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                UPDProgressActivity.this.progressSeek(message.arg1);
            } else if (i == 2) {
                System.exit(0);
            } else if (i == 3) {
                UPDProgressActivity.this.mUpgradeTitleTv.setText(message.obj + " " + UPDProgressActivity.this.getResources().getString(R.string.info_upgrade_warn));
            }
            super.handleMessage(message);
        }
    };
    BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.activity.UPDProgressActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.hzbhd.intent.action.Mcusmodule.upgrade") || intent.getAction().equals("com.hzbhd.intent.action.upgrade")) {
                if (intent.getStringExtra("canbus_swc_upd_name") != null) {
                    UPDProgressActivity.this.mHandler.sendMessage(UPDProgressActivity.this.mHandler.obtainMessage(3, 0, 0, intent.getStringExtra("canbus_swc_upd_name")));
                }
                if (intent.getIntExtra("result", 0) == 2) {
                    Toast.makeText(UPDProgressActivity.this.getApplicationContext(), "upgrade success", Toast.LENGTH_LONG).show();
                } else if (intent.getIntExtra("result", 0) == -1) {
                    Toast.makeText(UPDProgressActivity.this.getApplicationContext(), "upgrade error!", Toast.LENGTH_LONG).show();
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.upgrade_falure);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.upgrade_falure);
                } else if (intent.getIntExtra("result", 0) == -2) {
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.no_pnl_file);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.no_pnl_file);
                } else if (intent.getIntExtra("result", 0) == -3) {
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.no_mcu_file);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.no_mcu_file);
                } else if (intent.getIntExtra("result", 0) == -4) {
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.no_app_file);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.no_app_file);
                } else if (intent.getIntExtra("result", 0) == -5) {
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.no_ts_file);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.no_ts_file);
                } else if (intent.getIntExtra("result", 0) == -6) {
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.no_config_file);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.no_config_file);
                } else if (intent.getIntExtra("result", 0) == -100) {
                    UPDProgressActivity.this.mUpgradeTitleTv.setText(R.string.password_error);
                    UPDProgressActivity.this.showUpgradeDialog(R.string.password_error);
                } else if (intent.getIntExtra("result", 0) == -102) {
                    UPDProgressActivity.this.mHandler.sendMessage(UPDProgressActivity.this.mHandler.obtainMessage(1, 100, 0));
                    return;
                }
                int intExtra = intent.getIntExtra(NotificationCompat.CATEGORY_PROGRESS, 0);
                if (intExtra > 0) {
                    UPDProgressActivity.this.mHandler.sendMessage(UPDProgressActivity.this.mHandler.obtainMessage(1, intExtra, 0));
                }
            }
        }
    };

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return true;
    }

    public static void openActivity(Context context, int i, boolean z) {
        Intent intent = new Intent(context, (Class<?>) UPDProgressActivity.class);
        intent.putExtra(CAN_TYPE_ID, i);
        intent.putExtra(IS_CANBUS_SWC_UPD, z);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_upd_progress);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mSeekBar = (SeekBar) findViewById(R.id.seekbar_def);
        this.mProgressInfoTv = (TextView) findViewById(R.id.lbl_progress_info);
        this.mUpgradeTitleTv = (TextView) findViewById(R.id.lbl_upgrade_title);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private void initViews() {
        int intExtra = getIntent().getIntExtra(CAN_TYPE_ID, 0);
        this.mIsCanSwcUpd = getIntent().getBooleanExtra(IS_CANBUS_SWC_UPD, false);
        IntentFilter intentFilter = new IntentFilter();
        if (this.mIsCanSwcUpd) {
            intentFilter.addAction("com.hzbhd.intent.action.upgrade");
        } else {
            intentFilter.addAction("com.hzbhd.intent.action.Mcusmodule.upgrade");
        }
        registerReceiver(this.mReceiver, intentFilter);
        progressSeek(0);
        String fileName = Util.getFileName(intExtra);
        Intent intent = new Intent();
        intent.setAction("com.hzbhd.intent.action.upgrade.service");
        if (this.mIsCanSwcUpd) {
            intent.putExtra("module", "CANBUS_SWC_UPD");
        } else {
            this.mUpgradeTitleTv.setText(fileName + " " + getResources().getString(R.string.info_upgrade_warn));
            intent.putExtra("module", "MCU_CANBUS_UPD");
            intent.putExtra("ASSET_FILE_NAME", fileName);
        }
        sendBroadcast(intent);
    }

    public void progressSeek(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 100) {
            i = 100;
        }
        this.mSeekBar.setProgress(i);
        this.mProgressInfoTv.setText(getResources().getString(R.string.upgrade) + " " + i + "%");
        if (i == 100) {
            finish();
            if (this.mIsCanSwcUpd) {
                SendKeyManager.getInstance().resetMpu(HotKeyConstant.RESET_MODE.NORMAL, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpgradeDialog(int i) {
        findViewById(R.id.main_layout).setVisibility(View.INVISIBLE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getString(R.string.upgrade_failed));
        builder.setMessage(i);
        builder.setCancelable(false);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.hzbhd.canbus.activity.UPDProgressActivity.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                UPDProgressActivity.this.finish();
            }
        });
        builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() { // from class: com.hzbhd.canbus.activity.UPDProgressActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                UPDProgressActivity.this.finish();
            }
        });
        builder.show();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }
}
