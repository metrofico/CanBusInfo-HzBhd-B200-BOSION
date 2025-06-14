package com.hzbhd.canbus.car_cus._467.smartPanel.window;

import android.content.Context;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._467.air.view.CheckItemView;
import com.hzbhd.canbus.comm.ScreenConfig;
import com.hzbhd.canbus.util.ContextUtil;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class CarSelectWindow {
    public boolean addTag;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager mWindowManager;
    private String mode;
    private CheckItemView mode1;
    private CheckItemView mode2;
    private CheckItemView mode3;
    private Button ok;
    private ConstraintLayout round_view;
    private View view;

    private static class Holder {
        private static final CarSelectWindow INSTANCE = new CarSelectWindow(ContextUtil.getInstance().getContext());

        private Holder() {
        }
    }

    public static CarSelectWindow getInstance() {
        return Holder.INSTANCE;
    }

    private CarSelectWindow(Context context) {
        this.addTag = false;
        this.mode = "200T";
        initWindow(context);
    }

    private void initWindow(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2003);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.flags = 1024;
        intiView(context);
        String stringValue = SharePreUtil.getStringValue(context, "ID439.CAR.SELECT", "200T");
        if (stringValue.equals("200T")) {
            selectMode(1);
        } else if (stringValue.equals("160T")) {
            selectMode(2);
        } else if (stringValue.equals("60T")) {
            selectMode(3);
        }
    }

    private void intiView(final Context context) {
        if (ScreenConfig.screenWidth == 1024 && ScreenConfig.screenHeight == 600) {
            this.view = LayoutInflater.from(context).inflate(R.layout._467_car_select1024x600, (ViewGroup) null);
        } else {
            this.view = LayoutInflater.from(context).inflate(R.layout._467_car_select1280x720, (ViewGroup) null);
        }
        Button button = (Button) this.view.findViewById(R.id.ok);
        this.ok = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.smartPanel.window.CarSelectWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharePreUtil.setStringValue(context, "ID439.CAR.SELECT", CarSelectWindow.this.mode);
                CarSelectWindow.this.hide();
                Toast.makeText(context, "SUCCESS", 0).show();
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
        CheckItemView checkItemView = (CheckItemView) this.view.findViewById(R.id.mode1);
        this.mode1 = checkItemView;
        checkItemView.setTitle(context.getString(R.string._439_car_1));
        this.mode1.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.smartPanel.window.CarSelectWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarSelectWindow.this.selectMode(1);
            }
        });
        CheckItemView checkItemView2 = (CheckItemView) this.view.findViewById(R.id.mode2);
        this.mode2 = checkItemView2;
        checkItemView2.setTitle(context.getString(R.string._439_car_2));
        this.mode2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.smartPanel.window.CarSelectWindow.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarSelectWindow.this.selectMode(2);
            }
        });
        CheckItemView checkItemView3 = (CheckItemView) this.view.findViewById(R.id.mode3);
        this.mode3 = checkItemView3;
        checkItemView3.setTitle(context.getString(R.string._439_car_3));
        this.mode3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.smartPanel.window.CarSelectWindow.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarSelectWindow.this.selectMode(3);
            }
        });
        ConstraintLayout constraintLayout = (ConstraintLayout) this.view.findViewById(R.id.round_view);
        this.round_view = constraintLayout;
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._467.smartPanel.window.CarSelectWindow.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CarSelectWindow.this.hide();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectMode(int i) {
        if (i == 1) {
            this.mode = "200T";
            this.mode1.check(true);
            this.mode2.check(false);
            this.mode3.check(false);
            return;
        }
        if (i == 2) {
            this.mode = "160T";
            this.mode1.check(false);
            this.mode2.check(true);
            this.mode3.check(false);
            return;
        }
        if (i != 3) {
            return;
        }
        this.mode = "60T";
        this.mode1.check(false);
        this.mode2.check(false);
        this.mode3.check(true);
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
