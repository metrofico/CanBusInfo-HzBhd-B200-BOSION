package com.hzbhd.ui.view.lifecycle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseRelativeLayout extends RelativeLayout implements LifecycleOwner {
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
    public BaseRelativeLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    public final void initChild() {
        BaseUtil.INSTANCE.runBack(new Runnable() {
            @Override
            public void run() {
                BaseRelativeLayout baseRelativeLayout = BaseRelativeLayout.this;
                baseRelativeLayout.viewChild = baseRelativeLayout.initView();
                BaseRelativeLayout.this.addBaseChild();
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

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        addOrRemoveView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addBaseChild() {
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (BaseRelativeLayout.this.isNeedAdd) {
                    BaseUtil.logTime("viewVisible " + BaseRelativeLayout.this.getClass().getSimpleName() + " , " + BaseRelativeLayout.this.viewChild);
                    BaseRelativeLayout.this.removeAllViews();
                    View view = BaseRelativeLayout.this.viewChild;
                    if (view != null) {
                        BaseRelativeLayout baseRelativeLayout = BaseRelativeLayout.this;
                        baseRelativeLayout.addView(view);
                        baseRelativeLayout.onViewAdd();
                    }
                }
            }
        });
    }

    protected final void addOrRemoveView() {
        BaseUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + BaseRelativeLayout.this.getClass().getSimpleName() + "  " + BaseRelativeLayout.this.isAttachedToWindow() + " , " + BaseRelativeLayout.this.getWindowVisibility() + " , " + BaseRelativeLayout.this.getVisibility() + " , " + BaseRelativeLayout.this.isAdd + " , " + BaseRelativeLayout.this.isNeedAdd + " , " + BaseRelativeLayout.this.mWindowVisibility + ", " + BaseRelativeLayout.this.mVisibility);
                }
                if (!BaseRelativeLayout.this.isAttachedToWindow() || BaseRelativeLayout.this.getWindowVisibility() != View.VISIBLE || BaseRelativeLayout.this.getVisibility() != View.VISIBLE || BaseRelativeLayout.this.mWindowVisibility != 0 || BaseRelativeLayout.this.mVisibility != 0) {
                    if (BaseRelativeLayout.this.isAdd) {
                        BaseRelativeLayout.this.isAdd = false;
                        BaseRelativeLayout.this.isNeedAdd = false;
                        BaseRelativeLayout.this.onLifeCycleChange(Lifecycle.State.CREATED);
                        BaseUtil.logTime("viewHide " + BaseRelativeLayout.this.getClass().getSimpleName());
                        BaseRelativeLayout.this.removeAllViews();
                        return;
                    }
                    return;
                }
                if (BaseRelativeLayout.this.isAdd) {
                    return;
                }
                BaseRelativeLayout.this.isAdd = true;
                BaseRelativeLayout.this.isNeedAdd = true;
                BaseRelativeLayout.this.onLifeCycleChange(Lifecycle.State.RESUMED);
                if (BaseRelativeLayout.this.isInit || !BaseRelativeLayout.this.autoInitChild()) {
                    BaseRelativeLayout.this.addBaseChild();
                } else {
                    BaseRelativeLayout.this.isInit = true;
                    BaseRelativeLayout.this.initChild();
                }
            }
        });
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public void onLifeCycleChange(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.mLifecycleRegistry.setCurrentState(state);
    }
}
