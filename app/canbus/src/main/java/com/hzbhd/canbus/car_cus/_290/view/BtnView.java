package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.LogUtil;

/* loaded from: classes2.dex */
public class BtnView extends ImageButton {
    private boolean isSelect;
    private int noSelectIcon;
    private int selectIcon;

    public BtnView(Context context) {
        super(context);
    }

    public BtnView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BtnView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ChengWeiComBtnViewStyle);
        this.selectIcon = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        this.noSelectIcon = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        initView();
    }

    private void initView() {
        setImageResource(this.noSelectIcon);
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
        LogUtil.showLog("this:" + this);
        LogUtil.showLog("this.isSelect:" + this.isSelect);
        LogUtil.showLog("selectIcon:" + this.selectIcon);
        LogUtil.showLog("noSelectIcon:" + this.noSelectIcon);
        if (this.isSelect) {
            setImageResource(this.selectIcon);
        } else {
            setImageResource(this.noSelectIcon);
        }
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            setImageResource(this.selectIcon);
        } else {
            if (action == 1) {
                performClick();
            } else if (action == 3) {
            }
            if (!this.isSelect) {
                setImageResource(this.noSelectIcon);
            }
        }
        return true;
    }

    public int getSelectIcon() {
        return this.selectIcon;
    }

    public void setSelectIcon(int i) {
        this.selectIcon = i;
    }

    public int getNoSelectIcon() {
        return this.noSelectIcon;
    }

    public void setNoSelectIcon(int i) {
        this.noSelectIcon = i;
    }
}
