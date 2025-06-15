package com.hzbhd.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.hzbhd.R;
import com.hzbhd.util.LogUtil;


@SuppressLint("AppCompatCustomView")
public class BhdSeekBar extends SeekBar implements BhdViewUtil.ViewUtilListener {
    private int background_n;
    private int background_n1;
    private int focusId;
    private int progressdrawable_n;
    private int progressdrawable_n1;
    private int progressdrawable_p;
    private int progressdrawable_p1;
    private int thumb_n;
    private int thumb_n1;
    private int thumb_p;
    private int thumb_p1;

    @Override // com.hzbhd.ui.base.BhdViewUtil.ViewUtilListener
    public void onFocusChange(boolean isFocus) {
    }

    public final int getFocusId() {
        return this.focusId;
    }

    public final void setFocusId(int i) {
        this.focusId = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdSeekBar(Context context) {
        super(context);

        this.focusId = -1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.focusId = -1;
        if (attributeSet == null) {
            return;
        }
        init(context, attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BhdSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        this.focusId = -1;
        if (attributeSet == null) {
            return;
        }
        init(context, attributeSet);
    }

    private final void init(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.seek_bar_attr);
        this.thumb_n = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_thumb_n, 0);
        this.thumb_p = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_thumb_p, 0);
        this.progressdrawable_n = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_progressdrawable_n, 0);
        this.progressdrawable_p = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_progressdrawable_p, 0);
        this.thumb_n1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_thumb_n1, 0);
        this.thumb_p1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_thumb_p1, 0);
        this.progressdrawable_n1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_progressdrawable_n1, 0);
        this.progressdrawable_p1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_progressdrawable_p1, 0);
        this.background_n = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_background_n, 0);
        this.background_n1 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.seek_bar_attr_background_n1, 0);
        updateView();
        typedArrayObtainStyledAttributes.recycle();
    }

    private final void updateView() {
        if (BhdViewUtil.INSTANCE.getColorStyle() == 1) {
            if (this.thumb_n1 != 0) {
                setThumb(getContext().getDrawable(this.thumb_n1));
            }
            if (LogUtil.log5()) {

            }
            if (this.progressdrawable_n1 != 0) {
                setProgressDrawableTiled(getContext().getDrawable(this.progressdrawable_n1));
            }
            int i = this.background_n1;
            if (i != 0) {
                setBackgroundResource(i);
                return;
            }
            return;
        }
        if (this.thumb_n != 0) {
            setThumb(getContext().getDrawable(this.thumb_n));
        }
        if (this.progressdrawable_n != 0) {
            setProgressDrawableTiled(getContext().getDrawable(this.progressdrawable_n));
        }
        int i2 = this.background_n;
        if (i2 != 0) {
            setBackgroundResource(i2);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BhdViewUtil.INSTANCE.addListener(this, this.focusId);
    }

    @Override // android.widget.ProgressBar, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BhdViewUtil.INSTANCE.removeListener(this, this.focusId);
    }

    @Override // com.hzbhd.ui.base.BhdViewUtil.ViewUtilListener
    public void onColorChange() {
        updateView();
    }
}
