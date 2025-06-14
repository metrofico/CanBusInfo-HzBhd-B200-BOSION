package com.hzbhd.canbus.car_cus._439.smartPanel.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.ContextUtil;

/* loaded from: classes2.dex */
public class PanelWindow {
    public boolean addTag;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager mWindowManager;
    private View view;

    private static class Holder {
        private static final PanelWindow airWindow = new PanelWindow(ContextUtil.getInstance().getContext());

        private Holder() {
        }
    }

    public static PanelWindow getInstance() {
        return Holder.airWindow;
    }

    private PanelWindow(Context context) {
        this.addTag = false;
        initWindow(context);
    }

    private void initWindow(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2010);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.flags = 1024;
        this.layoutParams.systemUiVisibility = 5380;
        this.view = LayoutInflater.from(context).inflate(R.layout._439_panel_window, (ViewGroup) null);
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
