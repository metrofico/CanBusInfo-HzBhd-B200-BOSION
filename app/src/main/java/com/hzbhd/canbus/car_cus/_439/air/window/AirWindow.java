package com.hzbhd.canbus.car_cus._439.air.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._439.air.view.AirView;
import com.hzbhd.ui.util.BaseUtil;

/* loaded from: classes2.dex */
public class AirWindow {
    public boolean addTag = false;
    private AirView air;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager mWindowManager;
    private View view;

    private static class Holder {
        private static final AirWindow airWindow = new AirWindow(BaseUtil.INSTANCE.getContext());

        private Holder() {
        }
    }

    public static AirWindow getInstance() {
        return Holder.airWindow;
    }

    public AirWindow(Context context) {
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
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._439_view_air_info, (ViewGroup) null);
        this.view = viewInflate;
        AirView airView = (AirView) viewInflate.findViewById(R.id.air);
        this.air = airView;
        airView.getAction(new ActionCallback() { // from class: com.hzbhd.canbus.car_cus._439.air.window.AirWindow.1
            @Override // com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback
            public void toDo(Object obj) {
                if (obj.equals("EXIT")) {
                    AirWindow.this.hide();
                }
            }
        });
    }

    public void setAutoExit(boolean z) {
        this.air.setAutoExit(z);
    }

    public boolean hide() {
        if (!this.addTag) {
            return false;
        }
        this.mWindowManager.removeView(this.view);
        this.addTag = false;
        return true;
    }

    public boolean show() {
        if (this.addTag) {
            return false;
        }
        this.mWindowManager.addView(this.view, this.layoutParams);
        this.addTag = true;
        this.air.initSetting();
        return true;
    }
}
