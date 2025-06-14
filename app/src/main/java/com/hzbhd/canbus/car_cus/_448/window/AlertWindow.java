package com.hzbhd.canbus.car_cus._448.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.view.EverScrollTextView;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class AlertWindow {
    private boolean addTag = false;
    private EverScrollTextView everScrollTextView;
    private Button i_know;
    private WindowManager.LayoutParams layoutParams;
    private Context mContext;
    private WindowManager mWindowManager;
    private LinearLayout out_view_lin;
    private View view;

    public AlertWindow(Context context, String str, String str2, ActionCallback actionCallback) {
        initWindow(context, str, str2, actionCallback);
    }

    private void initWindow(Context context, String str, String str2, final ActionCallback actionCallback) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2024);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.flags = 1024;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_alert_window, (ViewGroup) null);
        this.view = viewInflate;
        EverScrollTextView everScrollTextView = (EverScrollTextView) viewInflate.findViewById(R.id.alert_content);
        this.everScrollTextView = everScrollTextView;
        everScrollTextView.setText(str);
        Button button = (Button) this.view.findViewById(R.id.i_know);
        this.i_know = button;
        button.setText(str2);
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._448.window.AlertWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                actionCallback.toDo(null);
                AlertWindow.this.hide();
            }
        });
        LinearLayout linearLayout = (LinearLayout) this.view.findViewById(R.id.out_view_lin);
        this.out_view_lin = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._448.window.AlertWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertWindow.this.hide();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hide() {
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car_cus._448.window.AlertWindow$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m1143lambda$hide$0$comhzbhdcanbuscar_cus_448windowAlertWindow();
            }
        });
    }

    /* renamed from: lambda$hide$0$com-hzbhd-canbus-car_cus-_448-window-AlertWindow, reason: not valid java name */
    /* synthetic */ Unit m1143lambda$hide$0$comhzbhdcanbuscar_cus_448windowAlertWindow() {
        if (!this.addTag) {
            return null;
        }
        this.mWindowManager.removeView(this.view);
        this.addTag = false;
        return null;
    }

    public void show() {
        BaseUtil.INSTANCE.runUi(new Function0() { // from class: com.hzbhd.canbus.car_cus._448.window.AlertWindow$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.m1144lambda$show$1$comhzbhdcanbuscar_cus_448windowAlertWindow();
            }
        });
    }

    /* renamed from: lambda$show$1$com-hzbhd-canbus-car_cus-_448-window-AlertWindow, reason: not valid java name */
    /* synthetic */ Unit m1144lambda$show$1$comhzbhdcanbuscar_cus_448windowAlertWindow() {
        if (this.addTag) {
            return null;
        }
        this.mWindowManager.addView(this.view, this.layoutParams);
        this.addTag = true;
        return null;
    }
}
