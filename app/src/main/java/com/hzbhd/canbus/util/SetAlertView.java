package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class SetAlertView {
    TextView content;
    CountDownTimer countDownTimer;
    Button i_know;

    /* JADX WARN: Type inference failed for: r10v4, types: [com.hzbhd.canbus.util.SetAlertView$2] */
    public void showDialog(Context context, String str) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._333_alert_dialog, (ViewGroup) null, true);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        if (this.content == null) {
            this.content = (TextView) viewInflate.findViewById(R.id.alert_content);
        }
        this.content.setText(str);
        if (this.i_know == null) {
            this.i_know = (Button) viewInflate.findViewById(R.id.i_know);
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetAlertView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.show();
        if (getScreenWidth(context) > getScreenHeight(context)) {
            alertDialogCreate.getWindow().setLayout(getScreenHeight(context) / 2, getScreenHeight(context) / 2);
        } else {
            alertDialogCreate.getWindow().setLayout(getScreenWidth(context) / 2, getScreenWidth(context) / 2);
        }
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.countDownTimer = new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.util.SetAlertView.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                AlertDialog alertDialog = alertDialogCreate;
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
            }
        }.start();
    }

    private static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    private static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
