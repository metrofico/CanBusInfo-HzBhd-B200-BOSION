package com.hzbhd.canbus.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.hzbhd.canbus.R;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public final class SwcResetDialog extends Dialog {
    private final Button btnCancel;
    private final Button btnConfirm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwcResetDialog(Context context, final View.OnClickListener onConfirm) {
        super(context, R.style.style_swc_dialog);
        setContentView(R.layout.layout_swc_reset_dialog);
        View viewFindViewById = findViewById(R.id.btn_confirm);
        Button button = (Button) viewFindViewById;
        button.setOnClickListener(v -> {
            SwcResetDialog.this.dismiss();
            onConfirm.onClick(v);
        });
        this.btnConfirm = button;
        View viewFindViewById2 = findViewById(R.id.btn_cancel);
        Button button2 = (Button) viewFindViewById2;
        // android.view.View.OnClickListener
        button2.setOnClickListener(view -> this.dismiss());
        this.btnCancel = button2;
    }

}
