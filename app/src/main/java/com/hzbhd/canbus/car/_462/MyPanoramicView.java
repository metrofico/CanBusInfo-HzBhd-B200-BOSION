package com.hzbhd.canbus.car._462;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* loaded from: classes2.dex */
public class MyPanoramicView extends LinearLayout {
    public boolean manualLock;
    private Button returnBtn;
    private View view;

    public MyPanoramicView(Context context) {
        this(context, null);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyPanoramicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.manualLock = false;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__462__panoramic_view, (ViewGroup) this, true);
        this.view = viewInflate;
        Button button = (Button) viewInflate.findViewById(R.id.returnBtn);
        this.returnBtn = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._462.MyPanoramicView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyPanoramicView.this.closeManual();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.hzbhd.canbus.car._462.MyPanoramicView$2] */
    public void openManual() {
        SendKeyManager.getInstance().forceReverse(true);
        new CountDownTimer(500L, 100L) { // from class: com.hzbhd.canbus.car._462.MyPanoramicView.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._462.MyPanoramicView.2.1
                    @Override // kotlin.jvm.functions.Function0
                    public Unit invoke() {
                        MyPanoramicView.this.returnBtn.setVisibility(0);
                        return null;
                    }
                });
            }
        }.start();
        this.manualLock = true;
    }

    public void closeManual() {
        SendKeyManager.getInstance().forceReverse(false);
        this.returnBtn.setVisibility(8);
        this.manualLock = false;
    }

    public void openAuto() {
        if (this.manualLock) {
            BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._462.MyPanoramicView.3
                @Override // kotlin.jvm.functions.Function0
                public Unit invoke() {
                    MyPanoramicView.this.returnBtn.setVisibility(8);
                    return null;
                }
            });
        } else {
            SendKeyManager.getInstance().forceReverse(true);
        }
    }

    public void closeAuto() {
        if (this.manualLock) {
            BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.canbus.car._462.MyPanoramicView.4
                @Override // kotlin.jvm.functions.Function0
                public Unit invoke() {
                    MyPanoramicView.this.returnBtn.setVisibility(0);
                    return null;
                }
            });
        } else {
            SendKeyManager.getInstance().forceReverse(false);
        }
    }
}
