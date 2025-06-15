package com.hzbhd.canbus.car._0;

import android.content.Context;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.hzbhd.R;
import com.hzbhd.canbus.car._0.CheckItemView2;
import com.hzbhd.ui.util.BaseUtil;

public class PanelSwitchWindow {
    public boolean addTag = false;
    private Button cancel;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager mWindowManager;
    private CheckItemView2 off_view;
    private CheckItemView2 on_view;
    private Button reset;
    private ConstraintLayout round_view;
    private View view;

    public PanelSwitchWindow(Context context) {
        initWindow(context);
    }

    private void initWindow(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003);
        this.layoutParams = layoutParams;
        layoutParams.gravity = Gravity.BOTTOM;
        this.layoutParams.format = 1;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.flags = android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
        intiView(context);
    }

    private void intiView(final Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._439_panel_switch_window, (ViewGroup) null);
        this.view = viewInflate;
        this.round_view = (ConstraintLayout) viewInflate.findViewById(R.id.round_view);
        this.on_view = (CheckItemView2) this.view.findViewById(R.id.on_view);
        this.off_view = (CheckItemView2) this.view.findViewById(R.id.off_view);
        this.on_view.setTitle(context.getString(R.string._439_panel_app3));
        this.off_view.setTitle(context.getString(R.string._439_panel_app4));
        if (Settings.System.getInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_439", 1) == 1) {
            this.on_view.check(true);
            this.off_view.check(false);
        } else {
            this.on_view.check(false);
            this.off_view.check(true);
        }
        this.on_view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.PanelSwitchWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PanelSwitchWindow.this.on_view.check(true);
                PanelSwitchWindow.this.off_view.check(false);
            }
        });
        this.off_view.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.PanelSwitchWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PanelSwitchWindow.this.on_view.check(false);
                PanelSwitchWindow.this.off_view.check(true);
            }
        });
        this.reset = (Button) this.view.findViewById(R.id.reset);
        Button button = (Button) this.view.findViewById(R.id.cancel);
        this.cancel = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.PanelSwitchWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PanelSwitchWindow.this.hide();
            }
        });
        this.reset.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._439.smartPanel.window.PanelSwitchWindow.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PanelSwitchWindow.this.on_view.isCheck()) {
                    Settings.System.putInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_439", 1);
                } else {
                    Settings.System.putInt(BaseUtil.INSTANCE.getContext().getContentResolver(), "CAN_BUS_SMART_PANEL_PAGE_SWITCH_439", 0);
                }
                Context context2 = context;
                Toast.makeText(context2, context2.getString(R.string._439_panel_app5), Toast.LENGTH_SHORT).show();
                System.exit(0);
                PanelSwitchWindow.this.hide();
            }
        });
    }

    public void hide() {
        if (this.addTag) {
            this.mWindowManager.removeView(this.view);
            this.addTag = false;
        }
    }

    public void show() {
        if (this.addTag) {
            return;
        }
        this.mWindowManager.addView(this.view, this.layoutParams);
        this.addTag = true;
    }
}