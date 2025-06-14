package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class KeyButton extends LinearLayout {
    private ActionCallback actionCallback;
    private Button btn;
    private View view;

    public KeyButton(Context context) {
        this(context, null);
    }

    public KeyButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_key_button, (ViewGroup) this, true);
        this.view = viewInflate;
        Button button = (Button) viewInflate.findViewById(R.id.btn);
        this.btn = button;
        button.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._448.view.KeyButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    KeyButton.this.btn.setBackgroundResource(R.drawable.__448___ph8_dvr_last_bg_p);
                    return true;
                }
                if (motionEvent.getAction() == 4) {
                    KeyButton.this.btn.setBackgroundResource(R.drawable.__448___ph8_dvr_last_bg_n);
                    return true;
                }
                if (motionEvent.getAction() == 1) {
                    KeyButton.this.btn.setBackgroundResource(R.drawable.__448___ph8_dvr_last_bg_n);
                    if (KeyButton.this.actionCallback != null) {
                        KeyButton.this.actionCallback.toDo(null);
                    }
                }
                if (motionEvent.getAction() == 3) {
                    KeyButton.this.btn.setBackgroundResource(R.drawable.__448___ph8_dvr_last_bg_n);
                }
                return true;
            }
        });
    }

    public void setTextValue(String str) {
        this.btn.setText(str);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        this.btn.setBackgroundResource(i);
    }

    public void setText(String str) {
        this.btn.setText(str);
    }

    public void getEventUpAction(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }
}
