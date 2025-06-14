package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SetErrorLogView {
    AlertDialog dialog;
    Button ignore;
    TextView log_view;
    View view;

    public interface ThisInterface {
        boolean ignoreCallBack();
    }

    public void showDialog(Context context, String str, final ThisInterface thisInterface) {
        if (this.view == null) {
            this.view = LayoutInflater.from(context).inflate(R.layout._error_log_dialog, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(context).setView(this.view).create();
        }
        if (this.log_view == null) {
            this.log_view = (TextView) this.view.findViewById(R.id.log_view);
        }
        this.log_view.setText(str);
        if (this.ignore == null) {
            this.ignore = (Button) this.view.findViewById(R.id.ignore);
        }
        this.ignore.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetErrorLogView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (thisInterface.ignoreCallBack()) {
                    SetErrorLogView.this.dialog.dismiss();
                }
            }
        });
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        this.dialog.show();
    }
}
