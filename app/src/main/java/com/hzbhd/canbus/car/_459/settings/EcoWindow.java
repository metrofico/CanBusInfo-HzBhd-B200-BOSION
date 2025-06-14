package com.hzbhd.canbus.car._459.settings;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.ui_set.SyncAction;

/* loaded from: classes2.dex */
public class EcoWindow {
    private ActionCallback actionCallback;
    private boolean addTag;
    private TextView alert;
    private Button cancel;
    private Context context;
    private CountDownTimer countDownTimer;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager mWindowManager;
    private Button ok;
    private TextView time;
    private TextView title;
    private View view;

    public interface ActionCallback {
        void actionDo(String str);
    }

    private static class Holder {
        private static final EcoWindow INSTANCE = new EcoWindow();

        private Holder() {
        }
    }

    private EcoWindow() {
        this.addTag = false;
    }

    public static EcoWindow getInstance() {
        return Holder.INSTANCE;
    }

    public void initWindow(Context context, String str) {
        this.context = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2024);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 80;
        this.layoutParams.format = 1;
        this.layoutParams.x = 0;
        this.layoutParams.y = 0;
        this.layoutParams.flags = 16777512;
        if (str.equals("c9")) {
            this.view = LayoutInflater.from(context).inflate(R.layout._459_eco_window, (ViewGroup) null);
        } else {
            this.view = LayoutInflater.from(context).inflate(R.layout._459_eco_window_black, (ViewGroup) null);
        }
        Button button = (Button) this.view.findViewById(R.id.cancel);
        this.cancel = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._459.settings.EcoWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EcoWindow.this.hide();
                if (EcoWindow.this.actionCallback != null) {
                    EcoWindow.this.actionCallback.actionDo("cancel");
                }
            }
        });
        Button button2 = (Button) this.view.findViewById(R.id.ok);
        this.ok = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._459.settings.EcoWindow.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EcoWindow.this.hide();
                if (EcoWindow.this.actionCallback != null) {
                    EcoWindow.this.actionCallback.actionDo(SyncAction.KEYBOARD_OK);
                }
            }
        });
        this.time = (TextView) this.view.findViewById(R.id.time);
        this.title = (TextView) this.view.findViewById(R.id.title);
        this.alert = (TextView) this.view.findViewById(R.id.alert);
    }

    public void show(final Context context, String str, final ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
        initWindow(context, str);
        this.title.setText(context.getString(R.string._459_eco_0));
        this.alert.setText(context.getString(R.string._459_eco_1));
        this.cancel.setText(context.getString(R.string._459_eco_4));
        this.ok.setText(context.getString(R.string._459_eco_3));
        if (this.addTag) {
            return;
        }
        this.addTag = true;
        actionCallback.actionDo("countdown");
        this.mWindowManager.addView(this.view, this.layoutParams);
        CountDownTimer countDownTimer = new CountDownTimer(10000L, 1000L) { // from class: com.hzbhd.canbus.car._459.settings.EcoWindow.3
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                String string = context.getString(R.string._459_eco_2);
                if (string.equals("ZH")) {
                    EcoWindow.this.time.setText(((j / 1000) + 1) + "秒后自动取消");
                } else if (string.equals("EN")) {
                    EcoWindow.this.time.setText("Automatically cancel after " + ((j / 1000) + 1) + " seconds");
                } else if (string.equals("RU")) {
                    EcoWindow.this.time.setText("Автоматическая отмена через " + ((j / 1000) + 1) + " секунд.");
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                actionCallback.actionDo("cancel");
                EcoWindow.this.hide();
            }
        };
        this.countDownTimer = countDownTimer;
        countDownTimer.start();
    }

    public void hide() {
        if (this.addTag) {
            CountDownTimer countDownTimer = this.countDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.mWindowManager.removeView(this.view);
            this.addTag = false;
        }
    }
}
