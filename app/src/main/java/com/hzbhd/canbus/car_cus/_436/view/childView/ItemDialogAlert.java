package com.hzbhd.canbus.car_cus._436.view.childView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;

/* loaded from: classes2.dex */
public class ItemDialogAlert {
    public void showDialog(Context context, String str, final MdOnClickListener mdOnClickListener) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._436_alert_item_dialog, (ViewGroup) null, true);
        TextView textView = (TextView) viewInflate.findViewById(R.id.alertContent);
        Button button = (Button) viewInflate.findViewById(R.id.ok);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.show();
        textView.setText(str);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.childView.ItemDialogAlert.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mdOnClickListener.clickPosition(0);
                alertDialogCreate.dismiss();
            }
        });
    }
}
