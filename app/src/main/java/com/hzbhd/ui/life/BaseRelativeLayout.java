package com.hzbhd.ui.life;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.lifecycle.Lifecycle;

import com.hzbhd.ui.base.BhdRelativeLayout;
import com.hzbhd.ui.base.BhdViewUtil;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseRelativeLayout extends BhdRelativeLayout {
    private EmptyBinding emptyBinding;
    private boolean isAdd;
    private boolean isNeedAdd;
    private int mVisibility;
    private int mWindowVisibility;
    private MyLifeCycleOwner myLifeCycleOwner;

    public boolean autoInitChild() {
        return true;
    }

    public boolean autoRemoveChild() {
        return true;
    }

    public abstract EmptyBinding getBinding();

    public void onLifeCycleChange(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
    }

    public void onViewAdd() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    public final MyLifeCycleOwner getMyLifeCycleOwner() {
        return this.myLifeCycleOwner;
    }

    public final void setMyLifeCycleOwner(MyLifeCycleOwner myLifeCycleOwner) {
        this.myLifeCycleOwner = myLifeCycleOwner;
    }

    public final void initChild() {
        BhdViewUtil.INSTANCE.runBack(new Runnable() {
            @Override
            public void run() {
                if (!BaseRelativeLayout.this.autoRemoveChild() && BaseRelativeLayout.this.emptyBinding != null) {
                    BaseRelativeLayout.this.addBaseChild();
                    return;
                }
                BaseRelativeLayout baseRelativeLayout = BaseRelativeLayout.this;
                baseRelativeLayout.emptyBinding = baseRelativeLayout.getBinding();
                EmptyBinding emptyBinding = BaseRelativeLayout.this.emptyBinding;
                if (emptyBinding != null) {
                    MyLifeCycleOwner myLifeCycleOwner = BaseRelativeLayout.this.getMyLifeCycleOwner();
                    Intrinsics.checkNotNull(myLifeCycleOwner);
                    emptyBinding.addObserver(myLifeCycleOwner);
                }
                EmptyBinding emptyBinding2 = BaseRelativeLayout.this.emptyBinding;
                if (emptyBinding2 != null) {
                    emptyBinding2.bindAction();
                }
                BaseRelativeLayout.this.addBaseChild();
            }
        });

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.RelativeLayout, android.view.ViewGroup
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void addBaseChild() {
        BhdViewUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                View root;
                if (BaseRelativeLayout.this.isNeedAdd) {
                    BaseRelativeLayout.this.removeAllViews();
                    EmptyBinding emptyBinding = BaseRelativeLayout.this.emptyBinding;
                    if (emptyBinding == null || (root = emptyBinding.getRoot()) == null) {
                        return;
                    }
                    BaseRelativeLayout baseRelativeLayout = BaseRelativeLayout.this;
                    baseRelativeLayout.addView(root);
                    baseRelativeLayout.onViewAdd();
                }
            }
        });

    }

    private final void addOrRemoveView() {
        BhdViewUtil.INSTANCE.runUi(new Runnable() {
            @Override
            public void run() {
                Lifecycle lifecycle;
                Lifecycle lifecycle2;
                Lifecycle lifecycle3;
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + BaseRelativeLayout.this.getClass().getSimpleName() + "  " + BaseRelativeLayout.this.isAttachedToWindow() + " , " + BaseRelativeLayout.this.getWindowVisibility() + " , " + BaseRelativeLayout.this.getVisibility() + " , " + BaseRelativeLayout.this.isAdd + " , " + BaseRelativeLayout.this.isNeedAdd + " , " + BaseRelativeLayout.this.mWindowVisibility + ", " + BaseRelativeLayout.this.mVisibility);
                }
                Lifecycle.State currentState = null;
                if (!BaseRelativeLayout.this.isAttachedToWindow() || BaseRelativeLayout.this.getWindowVisibility() != View.VISIBLE
                        || BaseRelativeLayout.this.getVisibility() != View.VISIBLE || BaseRelativeLayout.this.mWindowVisibility != 0
                        || BaseRelativeLayout.this.mVisibility != 0) {
                    if (BaseRelativeLayout.this.isAdd) {
                        BaseRelativeLayout.this.isAdd = false;
                        BaseRelativeLayout.this.isNeedAdd = false;
                        BaseRelativeLayout.this.removeAllViews();
                        if (BaseRelativeLayout.this.autoInitChild() && BaseRelativeLayout.this.autoRemoveChild()) {
                            MyLifeCycleOwner myLifeCycleOwner = BaseRelativeLayout.this.getMyLifeCycleOwner();
                            if (myLifeCycleOwner != null) {
                                myLifeCycleOwner.onLifeCycleChange(Lifecycle.State.DESTROYED);
                            }
                            BaseRelativeLayout baseRelativeLayout = BaseRelativeLayout.this;
                            MyLifeCycleOwner myLifeCycleOwner2 = baseRelativeLayout.getMyLifeCycleOwner();
                            Lifecycle.State currentState2 = (myLifeCycleOwner2 == null || (lifecycle2 = myLifeCycleOwner2.getLifecycle()) == null) ? null : lifecycle2.getCurrentState();
                            if (currentState2 == null) {
                                currentState2 = Lifecycle.State.CREATED;
                            }
                            Intrinsics.checkNotNullExpressionValue(currentState2, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                            baseRelativeLayout.onLifeCycleChange(currentState2);
                            BaseRelativeLayout.this.emptyBinding = null;
                            BaseRelativeLayout.this.setMyLifeCycleOwner(null);
                            return;
                        }
                        MyLifeCycleOwner myLifeCycleOwner3 = BaseRelativeLayout.this.getMyLifeCycleOwner();
                        if (myLifeCycleOwner3 != null) {
                            myLifeCycleOwner3.onLifeCycleChange(Lifecycle.State.CREATED);
                        }
                        BaseRelativeLayout baseRelativeLayout2 = BaseRelativeLayout.this;
                        MyLifeCycleOwner myLifeCycleOwner4 = baseRelativeLayout2.getMyLifeCycleOwner();
                        if (myLifeCycleOwner4 != null && (lifecycle = myLifeCycleOwner4.getLifecycle()) != null) {
                            currentState = lifecycle.getCurrentState();
                        }
                        if (currentState == null) {
                            currentState = Lifecycle.State.CREATED;
                        }
                        Intrinsics.checkNotNullExpressionValue(currentState, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                        baseRelativeLayout2.onLifeCycleChange(currentState);
                        return;
                    }
                    return;
                }
                if (BaseRelativeLayout.this.isAdd) {
                    return;
                }
                BaseRelativeLayout.this.isAdd = true;
                BaseRelativeLayout.this.isNeedAdd = true;
                if (BaseRelativeLayout.this.getMyLifeCycleOwner() == null) {
                    BaseRelativeLayout.this.setMyLifeCycleOwner(new MyLifeCycleOwner());
                    if (BaseRelativeLayout.this.autoInitChild()) {
                        BaseRelativeLayout.this.initChild();
                    }
                }
                if (!BaseRelativeLayout.this.autoInitChild()) {
                    BaseRelativeLayout.this.addBaseChild();
                }
                MyLifeCycleOwner myLifeCycleOwner5 = BaseRelativeLayout.this.getMyLifeCycleOwner();
                if (myLifeCycleOwner5 != null) {
                    myLifeCycleOwner5.onLifeCycleChange(Lifecycle.State.RESUMED);
                }
                BaseRelativeLayout baseRelativeLayout3 = BaseRelativeLayout.this;
                MyLifeCycleOwner myLifeCycleOwner6 = baseRelativeLayout3.getMyLifeCycleOwner();
                if (myLifeCycleOwner6 != null && (lifecycle3 = myLifeCycleOwner6.getLifecycle()) != null) {
                    currentState = lifecycle3.getCurrentState();
                }
                if (currentState == null) {
                    currentState = Lifecycle.State.CREATED;
                }
                Intrinsics.checkNotNullExpressionValue(currentState, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                baseRelativeLayout3.onLifeCycleChange(currentState);
            }
        });

    }
}
