package com.hzbhd.ui.life;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.hzbhd.ui.base.BhdFrameLayout;
import com.hzbhd.ui.base.BhdViewUtil;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public class BaseLifeFrameLayout extends BhdFrameLayout implements LifecycleOwner {
    private final LifecycleRegistry mLifecycleRegistry;
    private int mVisibility;
    private int mWindowVisibility;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeFrameLayout(Context context) {
        super(context);

        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    public LifecycleRegistry getMLifecycleRegistry() {
        return this.mLifecycleRegistry;
    }

    protected final int getMWindowVisibility() {
        return this.mWindowVisibility;
    }

    protected final void setMWindowVisibility(int i) {
        this.mWindowVisibility = i;
    }

    protected final int getMVisibility() {
        return this.mVisibility;
    }

    protected final void setMVisibility(int i) {
        this.mVisibility = i;
    }

    @Override // com.hzbhd.ui.base.BhdFrameLayout, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOrRemoveView();
    }

    @Override // com.hzbhd.ui.base.BhdFrameLayout, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOrRemoveView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.mWindowVisibility = visibility;
        addOrRemoveView();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {

        super.onVisibilityChanged(changedView, visibility);
        this.mVisibility = visibility;
        addOrRemoveView();
    }

    @Override // com.hzbhd.ui.base.BhdFrameLayout, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        addOrRemoveView();
    }

    public void addOrRemoveView() {
        BhdViewUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + BaseLifeFrameLayout.this.getClass().getSimpleName() + "  " + BaseLifeFrameLayout.this.isAttachedToWindow() + " , " + BaseLifeFrameLayout.this.getWindowVisibility() + " , " + BaseLifeFrameLayout.this.getVisibility() + " , " + BaseLifeFrameLayout.this.getMVisibility() + ", " + BaseLifeFrameLayout.this.getMWindowVisibility());
                }
                if (BaseLifeFrameLayout.this.isAttachedToWindow() && BaseLifeFrameLayout.this.getWindowVisibility() == View.VISIBLE
                        && BaseLifeFrameLayout.this.getVisibility() == View.VISIBLE && BaseLifeFrameLayout.this.getMVisibility() == 0 && BaseLifeFrameLayout.this.getMWindowVisibility() == 0) {
                    BaseLifeFrameLayout.this.onLifeCycleChange(Lifecycle.State.RESUMED);
                } else {
                    BaseLifeFrameLayout.this.onLifeCycleChange(Lifecycle.State.CREATED);
                }
            }
        });

    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return getMLifecycleRegistry();
    }

    public void onLifeCycleChange(Lifecycle.State state) {

        if (getMLifecycleRegistry().getCurrentState() != state && LogUtil.log5()) {
            LogUtil.d("onLifeCycleChange():" + getClass().getSimpleName() + " : " + state);
        }
        getMLifecycleRegistry().setCurrentState(state);
    }
}
