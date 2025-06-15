package com.hzbhd.ui.view.lifecycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseFrameLayout extends FrameLayout implements LifecycleOwner {
    private boolean isAdd;
    private boolean isInit;
    private boolean isNeedAdd;
    private final LifecycleRegistry mLifecycleRegistry;
    private int mVisibility;
    private int mWindowVisibility;
    private View viewChild;

    public abstract boolean autoInitChild();

    public abstract View initView();

    public void onViewAdd() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context) {
        super(context);

        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);

        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);

        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* renamed from: isAdd, reason: from getter */
    public final boolean getIsAdd() {
        return this.isAdd;
    }

    public final void setAdd(boolean z) {
        this.isAdd = z;
    }

    /* renamed from: isNeedAdd, reason: from getter */
    public final boolean getIsNeedAdd() {
        return this.isNeedAdd;
    }

    public final void setNeedAdd(boolean z) {
        this.isNeedAdd = z;
    }

    public final int getMWindowVisibility() {
        return this.mWindowVisibility;
    }

    public final void setMWindowVisibility(int i) {
        this.mWindowVisibility = i;
    }

    public final int getMVisibility() {
        return this.mVisibility;
    }

    public final void setMVisibility(int i) {
        this.mVisibility = i;
    }

    /* renamed from: isInit, reason: from getter */
    public final boolean getIsInit() {
        return this.isInit;
    }

    public final void setInit(boolean z) {
        this.isInit = z;
    }

    public final void initChild() {
        BaseUtil.INSTANCE.runBack(new Runnable() {
            @Override
            public void run() {
                BaseFrameLayout baseFrameLayout = BaseFrameLayout.this;
                baseFrameLayout.viewChild = baseFrameLayout.initView();
                BaseFrameLayout.this.addBaseChild();
            }
        });

    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOrRemoveView();
    }

    @Override // android.view.ViewGroup, android.view.View
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

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        addOrRemoveView();
    }

    public final void addBaseChild() {
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (BaseFrameLayout.this.getIsNeedAdd()) {
                    BaseUtil.logTime("viewVisible " + BaseFrameLayout.this.getClass().getSimpleName() + " , " + BaseFrameLayout.this.viewChild);
                    BaseFrameLayout.this.removeAllViews();
                    View view = BaseFrameLayout.this.viewChild;
                    if (view != null) {
                        BaseFrameLayout baseFrameLayout = BaseFrameLayout.this;
                        baseFrameLayout.addView(view);
                        baseFrameLayout.onViewAdd();
                    }
                }
            }
        });

    }

    public void addOrRemoveView() {
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + BaseFrameLayout.this.getClass().getSimpleName() + "  " + BaseFrameLayout.this.isAttachedToWindow() + " , " + BaseFrameLayout.this.getWindowVisibility() + " , " + BaseFrameLayout.this.getVisibility() + " , " + BaseFrameLayout.this.getIsAdd() + " , " + BaseFrameLayout.this.getIsNeedAdd() + " , " + BaseFrameLayout.this.getMWindowVisibility() + ", " + BaseFrameLayout.this.getMVisibility());
                }
                if (BaseFrameLayout.this.isAttachedToWindow() && BaseFrameLayout.this.getWindowVisibility() == 0 && BaseFrameLayout.this.getVisibility() == View.VISIBLE && BaseFrameLayout.this.getMWindowVisibility() == 0 && BaseFrameLayout.this.getMVisibility() == 0) {
                    if (BaseFrameLayout.this.getIsAdd()) {
                        return;
                    }
                    BaseFrameLayout.this.setAdd(true);
                    BaseFrameLayout.this.setNeedAdd(true);
                    BaseFrameLayout.this.onLifeCycleChange(Lifecycle.State.RESUMED);
                    if (!BaseFrameLayout.this.getIsInit() && BaseFrameLayout.this.autoInitChild()) {
                        BaseFrameLayout.this.setInit(true);
                        BaseFrameLayout.this.initChild();
                        return;
                    } else {
                        BaseFrameLayout.this.addBaseChild();
                        return;
                    }
                }
                if (BaseFrameLayout.this.getIsAdd()) {
                    BaseFrameLayout.this.setAdd(false);
                    BaseFrameLayout.this.setNeedAdd(false);
                    BaseFrameLayout.this.onLifeCycleChange(Lifecycle.State.CREATED);
                    BaseUtil.logTime("viewHide " + BaseFrameLayout.this.getClass().getSimpleName());
                    BaseFrameLayout.this.removeAllViews();
                }
            }
        });

    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public void onLifeCycleChange(Lifecycle.State state) {

        this.mLifecycleRegistry.markState(state);
    }
}
