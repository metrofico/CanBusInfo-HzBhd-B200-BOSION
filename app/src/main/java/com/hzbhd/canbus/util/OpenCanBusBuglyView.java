package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.CrashReportUtils;

import kotlinx.coroutines.DebugKt;

/* loaded from: classes2.dex */
public class OpenCanBusBuglyView {
    Button open;

    public void showDialog(final Context context) {
        final View viewInflate = LayoutInflater.from(context).inflate(R.layout._open_canbus_bugly_dialog, (ViewGroup) null, true);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        if (this.open == null) {
            this.open = (Button) viewInflate.findViewById(R.id.open);
        }
        this.open.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.OpenCanBusBuglyView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (((EditText) viewInflate.findViewById(R.id.pass_word)).getText().toString().trim().equals(DebugKt.DEBUG_PROPERTY_VALUE_ON)) {
                    CrashReportUtils.openCanBusBugly(context, true);
                    Toast.makeText(context, R.string._open_bugly_success, Toast.LENGTH_LONG).show();
                    alertDialogCreate.dismiss();
                } else if (((EditText) viewInflate.findViewById(R.id.pass_word)).getText().toString().trim().equals("off")) {
                    CrashReportUtils.openCanBusBugly(context, false);
                    Toast.makeText(context, R.string._open_bugly_off_success, Toast.LENGTH_LONG).show();
                    alertDialogCreate.dismiss();
                } else {
                    Toast.makeText(context, R.string._open_bugly_password, Toast.LENGTH_SHORT).show();
                    ((EditText) viewInflate.findViewById(R.id.pass_word)).setText("");
                }
            }
        });
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.getWindow().setType(2003);
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
