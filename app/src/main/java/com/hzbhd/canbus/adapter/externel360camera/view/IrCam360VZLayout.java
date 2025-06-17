package com.hzbhd.canbus.adapter.externel360camera.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.externel360camera.VZ360Constance;

/* loaded from: classes.dex */
public class IrCam360VZLayout extends LinearLayout implements View.OnClickListener {
    boolean showKeyBoard;
    View view;
    private int[] views;

    public IrCam360VZLayout(Context context) {
        this(context, null);
    }

    public IrCam360VZLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IrCam360VZLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public IrCam360VZLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

        this.views = new int[]{R.id.vz_irkey_button, R.id.vz_irkey_button_keybroad, R.id.id_selector_bottom_vz_1, R.id.id_selector_bottom_vz_2, R.id.id_selector_bottom_vz_3, R.id.id_selector_bottom_vz_4, R.id.id_selector_bottom_vz_5, R.id.id_selector_bottom_vz_6, R.id.id_selector_bottom_vz_7, R.id.id_selector_bottom_vz_8, R.id.id_selector_bottom_vz_9, R.id.id_selector_bottom_vz_0, R.id.id_selector_bottom_vz_add, R.id.id_selector_bottom_vz_minusr, R.id.id_selector_bottom_vz_left, R.id.id_selector_bottom_vz_right, R.id.id_selector_bottom_vz_up, R.id.id_selector_bottom_vz_down, R.id.id_selector_bottom_vz_ok, R.id.id_selector_bottom_vz_back, R.id.id_selector_bottom_vz_login, R.id.id_selector_bottom_vz_info, R.id.id_selector_bottom_vz_prev, R.id.id_selector_bottom_vz_playpause, R.id.id_selector_bottom_vz_next, R.id.id_selector_bottom_vz_stop, R.id.id_selector_bottom_vz_keybroad};
        this.showKeyBoard = false;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.vz_ir_key_rl, (ViewGroup) null);
        this.view = viewInflate;
        addView(viewInflate);
        int i3 = 0;
        while (true) {
            int[] iArr = this.views;
            if (i3 < iArr.length) {
                this.view.findViewById(iArr[i3]).setOnClickListener(this);
                i3++;
            } else {
                hideKeyBoard();
                show_vz_irkey_button();
                this.showKeyBoard = false;
                return;
            }
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
    }

    public static void addVIew(Context context, View view) {
        if (VZ360Constance.isVZ360Camera().booleanValue()) {
            IrCam360VZLayout irCam360VZLayout = new IrCam360VZLayout(context);
            irCam360VZLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            irCam360VZLayout.setGravity(80);
            ((RelativeLayout) view).addView(irCam360VZLayout);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        if (!isTouchPointInView(this.view.findViewById(R.id.vz_irkey_button_keybroad), rawX, rawY) && !isTouchPointInView(this.view.findViewById(R.id.bottom_control), rawX, rawY) && !isTouchPointInView(this.view.findViewById(R.id.left_control), rawX, rawY)) {
            if (is_visible_vz_irkey_button()) {
                hideKeyBoard();
                hide_vz_irkey_button();
            } else {
                showKeyBoard();
                show_vz_irkey_button();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void setVisual(View view, int i) {
        view.setVisibility(i);
    }

    public void showKeyBoard() {
        if (this.showKeyBoard) {
            setVisual(this.view.findViewById(R.id.vz_irkey_button_keybroad), 0);
        }
    }

    public void hideKeyBoard() {
        setVisual(this.view.findViewById(R.id.vz_irkey_button_keybroad), 8);
        this.showKeyBoard = false;
    }

    public void show_vz_irkey_button() {
        setVisual(this.view.findViewById(R.id.vz_irkey_button), 0);
    }

    public void hide_vz_irkey_button() {
        setVisual(this.view.findViewById(R.id.vz_irkey_button), 8);
    }

    public boolean is_visible_vz_irkey_button() {
        return this.view.findViewById(R.id.vz_irkey_button).getVisibility() == View.VISIBLE;
    }

    private boolean isTouchPointInView(View view, int i, int i2) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i3 = iArr[0];
        int i4 = iArr[1];
        return i2 >= i4 && i2 <= view.getMeasuredHeight() + i4 && i >= i3 && i <= view.getMeasuredWidth() + i3;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.vz_irkey_button || id == R.id.vz_irkey_button_keybroad) {
            return;
        }
        if (id == R.id.id_selector_bottom_vz_1) {
            VZ360Constance.sendMDs(VZ360Constance.comds_1);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_2) {
            VZ360Constance.sendMDs(VZ360Constance.comds_2);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_3) {
            VZ360Constance.sendMDs(VZ360Constance.comds_3);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_4) {
            VZ360Constance.sendMDs(VZ360Constance.comds_4);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_5) {
            VZ360Constance.sendMDs(VZ360Constance.comds_5);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_6) {
            VZ360Constance.sendMDs(VZ360Constance.comds_6);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_7) {
            VZ360Constance.sendMDs(VZ360Constance.comds_7);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_8) {
            VZ360Constance.sendMDs(VZ360Constance.comds_8);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_9) {
            VZ360Constance.sendMDs(VZ360Constance.comds_9);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_0) {
            VZ360Constance.sendMDs(VZ360Constance.comds_0);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_add) {
            VZ360Constance.sendMDs(VZ360Constance.comds_add);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_minusr) {
            VZ360Constance.sendMDs(VZ360Constance.comds_minus);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_left) {
            VZ360Constance.sendMDs(VZ360Constance.LEFT);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_right) {
            VZ360Constance.sendMDs(VZ360Constance.RIGHT);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_up) {
            VZ360Constance.sendMDs(VZ360Constance.UP);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_down) {
            VZ360Constance.sendMDs(VZ360Constance.DOWN);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_ok) {
            VZ360Constance.sendMDs(VZ360Constance.ENTER);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_back) {
            VZ360Constance.sendMDs(VZ360Constance.RETURN);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_login) {
            VZ360Constance.sendMDs(VZ360Constance.LOGIN);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_info) {
            VZ360Constance.sendMDs(VZ360Constance.INFO);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_prev) {
            VZ360Constance.sendMDs(VZ360Constance.PREV);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_playpause) {
            VZ360Constance.sendMDs(VZ360Constance.PLAY);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_next) {
            VZ360Constance.sendMDs(VZ360Constance.NEXT);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_stop) {
            VZ360Constance.sendMDs(VZ360Constance.STOP);
            return;
        }
        if (id == R.id.id_selector_bottom_vz_keybroad) {
            if (!this.showKeyBoard) {
                this.showKeyBoard = true;
                showKeyBoard();
            } else {
                this.showKeyBoard = false;
                hideKeyBoard();
            }
        }
    }
}
