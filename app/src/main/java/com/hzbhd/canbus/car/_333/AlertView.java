package com.hzbhd.canbus.car._333;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.R;


public class AlertView {
    TextView content;
    Button i_know;

    public void showDialog(Context context, String str) {
        if (context == null) {
            return;
        }
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._333_alert_dialog, (ViewGroup) null, true);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        if (this.content == null) {
            this.content = (TextView) viewInflate.findViewById(R.id.alert_content);
        }
        this.content.setText(str);
        if (this.i_know == null) {
            this.i_know = (Button) viewInflate.findViewById(R.id.i_know);
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._333.AlertView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.show();
        if (getScreenWidth(context) > getScreenHeight(context)) {
            alertDialogCreate.getWindow().setLayout(getScreenHeight(context) / 2, getScreenHeight(context) / 2);
        } else {
            alertDialogCreate.getWindow().setLayout(getScreenWidth(context) / 2, getScreenWidth(context) / 2);
        }
    }

    private static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    private static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
