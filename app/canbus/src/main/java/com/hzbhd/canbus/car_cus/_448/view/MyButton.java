package com.hzbhd.canbus.car_cus._448.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class MyButton extends LinearLayout {
    private ActionCallback actionCallback;
    private Button btn;
    private View view;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.__448_view_my_button, (ViewGroup) this, true);
        this.view = viewInflate;
        Button button = (Button) viewInflate.findViewById(R.id.btn);
        this.btn = button;
        button.setOnTouchListener(new View.OnTouchListener() { // from class: com.hzbhd.canbus.car_cus._448.view.MyButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    MyButton.this.btn.setBackgroundResource(R.drawable.__448_eq1_left_p);
                    return true;
                }
                if (motionEvent.getAction() == 4) {
                    MyButton.this.btn.setBackgroundResource(R.drawable.__448_null);
                    return true;
                }
                if (motionEvent.getAction() == 1) {
                    MyButton.this.btn.setBackgroundResource(R.drawable.__448_null);
                    if (MyButton.this.actionCallback != null) {
                        MyButton.this.actionCallback.toDo(null);
                    }
                }
                if (motionEvent.getAction() == 3) {
                    MyButton.this.btn.setBackgroundResource(R.drawable.__448_null);
                }
                return true;
            }
        });
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
