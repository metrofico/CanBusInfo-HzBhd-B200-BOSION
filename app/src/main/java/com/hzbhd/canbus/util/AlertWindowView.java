package com.hzbhd.canbus.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.hzbhd.canbus.R;

/* loaded from: classes2.dex */
public class AlertWindowView {
    public boolean addTag = false;
    private TextView contentTxt;
    private Button i_know;
    private WindowManager.LayoutParams layoutParams;
    private Context mContext;
    private WindowManager mWindowManager;
    private View view;

    @SuppressLint("WrongConstant")
    public void initWindow(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.type = 2010;
        this.layoutParams.flags = 16777512;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.width = -1;
        this.layoutParams.height = -1;
        initView(context);
    }

    private void initView(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._333_alert_dialog, (ViewGroup) null);
        this.view = viewInflate;
        this.contentTxt = (TextView) viewInflate.findViewById(R.id.alert_content);
        if (this.i_know == null) {
            this.i_know = (Button) this.view.findViewById(R.id.i_know);
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.util.AlertWindowView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertWindowView.this.hide();
            }
        });
    }

    public void show(Context context, String str) {
        WindowManager.LayoutParams layoutParams;
        View view;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null && (layoutParams = this.layoutParams) != null && (view = this.view) != null) {
            if (!this.addTag) {
                windowManager.addView(view, layoutParams);
                this.addTag = true;
            }
        } else {
            initWindow(context);
            if (!this.addTag) {
                this.mWindowManager.addView(this.view, this.layoutParams);
                this.addTag = true;
            }
        }
        this.contentTxt.setText(str);
    }

    public void hide() {
        View view;
        try {
            WindowManager windowManager = this.mWindowManager;
            if (windowManager == null || (view = this.view) == null || !this.addTag) {
                return;
            }
            windowManager.removeView(view);
            this.addTag = false;
        } catch (Exception unused) {
        }
    }
}
