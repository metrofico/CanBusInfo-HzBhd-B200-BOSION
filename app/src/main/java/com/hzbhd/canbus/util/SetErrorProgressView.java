package com.hzbhd.canbus.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hzbhd.R;

/* loaded from: classes2.dex */
public class SetErrorProgressView {
    private static AlertDialog dialog;
    private static View view;
    Button cancel_btn;
    Button continue_btn;
    ProgressBar mProgressBar;
    Button ok_success;
    Button pause;
    LinearLayout running_view;
    LinearLayout success_view;

    public interface ThisInterface {
        boolean onCancel();

        boolean onContinue();

        boolean onPause();

        boolean onSuccessButtonClick();
    }

    public void showDialog(Context context, boolean z, boolean z2, final ThisInterface thisInterface) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout._error_progress_dialog, null, true);
        }
        if (dialog == null) {
            dialog = new AlertDialog.Builder(context).setView(view).create();
        }
        if (this.pause == null) {
            this.pause = view.findViewById(R.id.pause);
        }
        if (this.continue_btn == null) {
            this.continue_btn = view.findViewById(R.id.continue_btn);
        }
        if (this.cancel_btn == null) {
            this.cancel_btn = view.findViewById(R.id.cancel_btn);
        }
        if (this.mProgressBar == null) {
            this.mProgressBar = view.findViewById(R.id.mProgressBar);
        }
        if (this.success_view == null) {
            this.success_view = view.findViewById(R.id.success_view);
        }
        if (this.running_view == null) {
            this.running_view = view.findViewById(R.id.running_view);
        }
        if (this.ok_success == null) {
            this.ok_success = view.findViewById(R.id.ok_success);
        }
        this.pause.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetErrorProgressView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (thisInterface.onPause()) {
                    SetErrorProgressView.this.pause.setVisibility(View.GONE);
                    SetErrorProgressView.this.continue_btn.setVisibility(View.VISIBLE);
                    SetErrorProgressView.this.mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        this.continue_btn.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetErrorProgressView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (thisInterface.onContinue()) {
                    SetErrorProgressView.this.pause.setVisibility(View.VISIBLE);
                    SetErrorProgressView.this.continue_btn.setVisibility(View.GONE);
                    SetErrorProgressView.this.mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        this.cancel_btn.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetErrorProgressView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetErrorProgressView.this.pause.setVisibility(View.VISIBLE);
                SetErrorProgressView.this.continue_btn.setVisibility(View.GONE);
                SetErrorProgressView.this.mProgressBar.setVisibility(View.VISIBLE);
                thisInterface.onCancel();
            }
        });
        this.ok_success.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.SetErrorProgressView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetErrorProgressView.this.success_view.setVisibility(View.GONE);
                thisInterface.onSuccessButtonClick();
            }
        });
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setType(2003);
        if (z) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
        if (z2) {
            this.success_view.setVisibility(View.VISIBLE);
            this.running_view.setVisibility(View.GONE);
        } else {
            this.success_view.setVisibility(View.GONE);
            this.running_view.setVisibility(View.VISIBLE);
        }
    }
}
