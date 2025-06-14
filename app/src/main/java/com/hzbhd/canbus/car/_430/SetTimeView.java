package com.hzbhd.canbus.car._430;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SetTimeView {
    Button cancel;
    Button hours_down;
    Button hours_up;
    CheckBox is24;
    Button minute_down;
    Button minute_up;
    Button ok;

    public interface ThisInterface {
        void hourDown();

        void hourUp();

        void minDown();

        void minUp();

        void timeFormat(boolean z);
    }

    public void showDialog(Context context, final ThisInterface thisInterface) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._430_time_set, (ViewGroup) null, true);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        if (this.hours_up == null) {
            this.hours_up = (Button) viewInflate.findViewById(R.id.hours_up);
        }
        if (this.hours_down == null) {
            this.hours_down = (Button) viewInflate.findViewById(R.id.hours_down);
        }
        if (this.minute_up == null) {
            this.minute_up = (Button) viewInflate.findViewById(R.id.minute_up);
        }
        if (this.minute_down == null) {
            this.minute_down = (Button) viewInflate.findViewById(R.id.minute_down);
        }
        if (this.ok == null) {
            this.ok = (Button) viewInflate.findViewById(R.id.ok);
        }
        if (this.cancel == null) {
            this.cancel = (Button) viewInflate.findViewById(R.id.cancel);
        }
        if (this.is24 == null) {
            this.is24 = (CheckBox) viewInflate.findViewById(R.id.is24);
        }
        this.ok.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                alertDialogCreate.dismiss();
            }
        });
        this.hours_up.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                thisInterface.hourUp();
            }
        });
        this.hours_down.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                thisInterface.hourDown();
            }
        });
        this.minute_up.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                thisInterface.minUp();
            }
        });
        this.minute_down.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                thisInterface.minDown();
            }
        });
        this.is24.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._430.SetTimeView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                thisInterface.timeFormat(SetTimeView.this.is24.isChecked());
            }
        });
        alertDialogCreate.setCancelable(false);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.show();
    }
}
