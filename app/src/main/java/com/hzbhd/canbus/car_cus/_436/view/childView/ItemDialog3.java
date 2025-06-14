package com.hzbhd.canbus.car_cus._436.view.childView;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._436.Interface.MdOnClickListener;

/* loaded from: classes2.dex */
public class ItemDialog3 {
    public void showDialog(Context context, String str, String str2, String str3, final MdOnClickListener mdOnClickListener) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._436_3_item_dialog, (ViewGroup) null, true);
        TextView textView = (TextView) viewInflate.findViewById(R.id.item_txt1);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.item_txt2);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.item_txt3);
        textView2.setText(str2);
        textView.setText(str);
        textView3.setText(str3);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.show();
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog3.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mdOnClickListener.clickPosition(0);
                alertDialogCreate.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog3.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mdOnClickListener.clickPosition(1);
                alertDialogCreate.dismiss();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._436.view.childView.ItemDialog3.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mdOnClickListener.clickPosition(2);
                alertDialogCreate.dismiss();
            }
        });
    }
}
