package com.hzbhd.canbus.car_cus._283.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import java.util.List;

/* loaded from: classes2.dex */
public class SettingTireResetView extends RelativeLayout implements View.OnClickListener {
    AlertDialog dialog;
    protected int id;
    protected List<SettingDialogBean> lists;
    public Context mContext;
    private View mView;
    Button ok_reset;
    private RelativeLayout relativeLayout;
    Thread tireResetThread;
    View view;

    public SettingTireResetView(Context context) {
        this(context, null);
    }

    public SettingTireResetView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingTireResetView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._283_setting_tire_reset_view, (ViewGroup) this, true);
        this.mView = viewInflate;
        RelativeLayout relativeLayout = (RelativeLayout) viewInflate.findViewById(R.id.relativeLayout);
        this.relativeLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.relativeLayout) {
            return;
        }
        showDialog(this.mContext);
    }

    public void showDialog(Context context) {
        if (this.view == null) {
            this.view = LayoutInflater.from(context).inflate(R.layout._283_tire_reset_view, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(context).setView(this.view).create();
        }
        if (this.ok_reset == null) {
            this.ok_reset = (Button) this.view.findViewById(R.id.ok_reset);
        }
        this.ok_reset.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.SettingTireResetView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingTireResetView.this.dialog.dismiss();
                resetTire();
            }

            private void resetTire() {
                SettingTireResetView.this.tireResetThread = SettingTireResetView.this.new TireResetThread();
                SettingTireResetView.this.tireResetThread.start();
            }
        });
        this.dialog.setCancelable(true);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.show();
    }

    public class TireResetThread extends Thread {
        public TireResetThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, 75, 1, 1});
            }
            SettingTireResetView.this.tireResetThread.interrupt();
        }
    }
}
