package com.hzbhd.ui.life;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.lifecycle.Lifecycle;

import com.hzbhd.ui.base.BhdFrameLayout;
import com.hzbhd.ui.base.BhdViewUtil;
import com.hzbhd.util.LogUtil;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseFrameLayout extends BhdFrameLayout {
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
    public BaseFrameLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
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
                if (!BaseFrameLayout.this.autoRemoveChild() && BaseFrameLayout.this.emptyBinding != null) {
                    BaseFrameLayout.this.addBaseChild();
                    return;
                }
                BaseFrameLayout baseFrameLayout = BaseFrameLayout.this;
                baseFrameLayout.emptyBinding = baseFrameLayout.getBinding();
                EmptyBinding emptyBinding = BaseFrameLayout.this.emptyBinding;
                if (emptyBinding != null) {
                    MyLifeCycleOwner myLifeCycleOwner = BaseFrameLayout.this.getMyLifeCycleOwner();
                    Intrinsics.checkNotNull(myLifeCycleOwner);
                    emptyBinding.addObserver(myLifeCycleOwner);
                }
                EmptyBinding emptyBinding2 = BaseFrameLayout.this.emptyBinding;
                if (emptyBinding2 != null) {
                    emptyBinding2.bindAction();
                }
                BaseFrameLayout.this.addBaseChild();
            }
        });

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
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        this.mVisibility = visibility;
        addOrRemoveView();
    }

    @Override // com.hzbhd.ui.base.BhdFrameLayout, android.view.ViewGroup, android.view.View
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
                if (BaseFrameLayout.this.isNeedAdd) {
                    BaseFrameLayout.this.removeAllViews();
                    EmptyBinding emptyBinding = BaseFrameLayout.this.emptyBinding;
                    if (emptyBinding == null || (root = emptyBinding.getRoot()) == null) {
                        return;
                    }
                    BaseFrameLayout baseFrameLayout = BaseFrameLayout.this;
                    baseFrameLayout.addView(root);
                    baseFrameLayout.onViewAdd();
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
                    LogUtil.d("addOrRemoveView: " + BaseFrameLayout.this.getClass().getSimpleName() + "  " + BaseFrameLayout.this.isAttachedToWindow() + " , " + BaseFrameLayout.this.getWindowVisibility() + " , " + BaseFrameLayout.this.getVisibility() + " , " + BaseFrameLayout.this.isAdd + " , " + BaseFrameLayout.this.isNeedAdd + " , " + BaseFrameLayout.this.mWindowVisibility + ", " + BaseFrameLayout.this.mVisibility);
                }
                Lifecycle.State currentState = null;
                if (!BaseFrameLayout.this.isAttachedToWindow() || BaseFrameLayout.this.getWindowVisibility() != 0 || BaseFrameLayout.this.getVisibility() != 0 || BaseFrameLayout.this.mWindowVisibility != 0 || BaseFrameLayout.this.mVisibility != 0) {
                    if (BaseFrameLayout.this.isAdd) {
                        BaseFrameLayout.this.isAdd = false;
                        BaseFrameLayout.this.isNeedAdd = false;
                        BaseFrameLayout.this.removeAllViews();
                        if (BaseFrameLayout.this.autoInitChild() && BaseFrameLayout.this.autoRemoveChild()) {
                            MyLifeCycleOwner myLifeCycleOwner = BaseFrameLayout.this.getMyLifeCycleOwner();
                            if (myLifeCycleOwner != null) {
                                myLifeCycleOwner.onLifeCycleChange(Lifecycle.State.DESTROYED);
                            }
                            BaseFrameLayout baseFrameLayout = BaseFrameLayout.this;
                            MyLifeCycleOwner myLifeCycleOwner2 = baseFrameLayout.getMyLifeCycleOwner();
                            Lifecycle.State currentState2 = (myLifeCycleOwner2 == null || (lifecycle2 = myLifeCycleOwner2.getLifecycle()) == null) ? null : lifecycle2.getCurrentState();
                            if (currentState2 == null) {
                                currentState2 = Lifecycle.State.CREATED;
                            }
                            Intrinsics.checkNotNullExpressionValue(currentState2, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                            baseFrameLayout.onLifeCycleChange(currentState2);
                            BaseFrameLayout.this.emptyBinding = null;
                            BaseFrameLayout.this.setMyLifeCycleOwner(null);
                            return;
                        }
                        MyLifeCycleOwner myLifeCycleOwner3 = BaseFrameLayout.this.getMyLifeCycleOwner();
                        if (myLifeCycleOwner3 != null) {
                            myLifeCycleOwner3.onLifeCycleChange(Lifecycle.State.CREATED);
                        }
                        BaseFrameLayout baseFrameLayout2 = BaseFrameLayout.this;
                        MyLifeCycleOwner myLifeCycleOwner4 = baseFrameLayout2.getMyLifeCycleOwner();
                        if (myLifeCycleOwner4 != null && (lifecycle = myLifeCycleOwner4.getLifecycle()) != null) {
                            currentState = lifecycle.getCurrentState();
                        }
                        if (currentState == null) {
                            currentState = Lifecycle.State.CREATED;
                        }
                        Intrinsics.checkNotNullExpressionValue(currentState, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                        baseFrameLayout2.onLifeCycleChange(currentState);
                        return;
                    }
                    return;
                }
                if (BaseFrameLayout.this.isAdd) {
                    return;
                }
                BaseFrameLayout.this.isAdd = true;
                BaseFrameLayout.this.isNeedAdd = true;
                if (BaseFrameLayout.this.getMyLifeCycleOwner() == null) {
                    BaseFrameLayout.this.setMyLifeCycleOwner(new MyLifeCycleOwner());
                    if (BaseFrameLayout.this.autoInitChild()) {
                        BaseFrameLayout.this.initChild();
                    }
                }
                if (!BaseFrameLayout.this.autoInitChild()) {
                    BaseFrameLayout.this.addBaseChild();
                }
                MyLifeCycleOwner myLifeCycleOwner5 = BaseFrameLayout.this.getMyLifeCycleOwner();
                if (myLifeCycleOwner5 != null) {
                    myLifeCycleOwner5.onLifeCycleChange(Lifecycle.State.RESUMED);
                }
                BaseFrameLayout baseFrameLayout3 = BaseFrameLayout.this;
                MyLifeCycleOwner myLifeCycleOwner6 = baseFrameLayout3.getMyLifeCycleOwner();
                if (myLifeCycleOwner6 != null && (lifecycle3 = myLifeCycleOwner6.getLifecycle()) != null) {
                    currentState = lifecycle3.getCurrentState();
                }
                if (currentState == null) {
                    currentState = Lifecycle.State.CREATED;
                }
                Intrinsics.checkNotNullExpressionValue(currentState, "myLifeCycleOwner?.lifecy…?:Lifecycle.State.CREATED");
                baseFrameLayout3.onLifeCycleChange(currentState);
            }
        });

    }
}
