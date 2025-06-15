package com.hzbhd.ui.life;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.hzbhd.ui.base.BhdRelativeLayout;
import com.hzbhd.ui.base.BhdViewUtil;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public class BaseLifeRelativeLayout extends BhdRelativeLayout implements LifecycleOwner {
    private final LifecycleRegistry mLifecycleRegistry;
    private int mVisibility;
    private int mWindowVisibility;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeRelativeLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLifeRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
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

    @Override // com.hzbhd.ui.base.BhdRelativeLayout, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOrRemoveView();
    }

    @Override // com.hzbhd.ui.base.BhdRelativeLayout, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOrRemoveView();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new RelativeLayout.LayoutParams(-1, -1);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.mWindowVisibility = visibility;
        addOrRemoveView();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        this.mVisibility = visibility;
        addOrRemoveView();
    }

    @Override // com.hzbhd.ui.base.BhdRelativeLayout, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        addOrRemoveView();
    }

    public void addOrRemoveView() {
        BhdViewUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + BaseLifeRelativeLayout.this.getClass().getSimpleName() + "  " + BaseLifeRelativeLayout.this.isAttachedToWindow() + " , " + BaseLifeRelativeLayout.this.getWindowVisibility() + " , " + BaseLifeRelativeLayout.this.getVisibility() + " , " + BaseLifeRelativeLayout.this.getMVisibility() + ", " + BaseLifeRelativeLayout.this.getMWindowVisibility());
                }
                if (BaseLifeRelativeLayout.this.isAttachedToWindow() && BaseLifeRelativeLayout.this.getWindowVisibility() == View.VISIBLE
                        && BaseLifeRelativeLayout.this.getVisibility() == View.VISIBLE && BaseLifeRelativeLayout.this.getMVisibility() == 0
                        && BaseLifeRelativeLayout.this.getMWindowVisibility() == 0) {
                    BaseLifeRelativeLayout.this.onLifeCycleChange(Lifecycle.State.RESUMED);
                } else {
                    BaseLifeRelativeLayout.this.onLifeCycleChange(Lifecycle.State.CREATED);
                }
            }
        });

    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return getMLifecycleRegistry();
    }

    public void onLifeCycleChange(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        getMLifecycleRegistry().setCurrentState(state);
    }
}
