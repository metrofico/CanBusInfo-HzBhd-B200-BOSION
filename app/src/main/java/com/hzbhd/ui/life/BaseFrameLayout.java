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

/* compiled from: BaseFrameLayout.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\b\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020\u000eH&J\u0006\u0010\"\u001a\u00020\u001bJ\b\u0010#\u001a\u00020\u001bH\u0014J\b\u0010$\u001a\u00020\u001bH\u0014J\b\u0010%\u001a\u00020\u001bH\u0014J\u0010\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020\u001bH\u0016J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\tH\u0014J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\tH\u0014R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006/"}, d2 = {"Lcom/hzbhd/ui/life/BaseFrameLayout;", "Lcom/hzbhd/ui/base/BhdFrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "emptyBinding", "Lcom/hzbhd/ui/life/EmptyBinding;", "isAdd", "", "isNeedAdd", "mVisibility", "mWindowVisibility", "myLifeCycleOwner", "Lcom/hzbhd/ui/life/MyLifeCycleOwner;", "getMyLifeCycleOwner", "()Lcom/hzbhd/ui/life/MyLifeCycleOwner;", "setMyLifeCycleOwner", "(Lcom/hzbhd/ui/life/MyLifeCycleOwner;)V", "addBaseChild", "", "addOrRemoveView", "autoInitChild", "autoRemoveChild", "generateDefaultLayoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "getBinding", "initChild", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onViewAdd", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "onWindowVisibilityChanged", "lifeview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
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
        BhdViewUtil.INSTANCE.runBack(new Function0<Unit>() { // from class: com.hzbhd.ui.life.BaseFrameLayout.initChild.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
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
        BhdViewUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.life.BaseFrameLayout.addBaseChild.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
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
        BhdViewUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.life.BaseFrameLayout.addOrRemoveView.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Lifecycle lifecycle;
                Lifecycle lifecycle2;
                Lifecycle lifecycle3;
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + ((Object) BaseFrameLayout.this.getClass().getSimpleName()) + "  " + BaseFrameLayout.this.isAttachedToWindow() + " , " + BaseFrameLayout.this.getWindowVisibility() + " , " + BaseFrameLayout.this.getVisibility() + " , " + BaseFrameLayout.this.isAdd + " , " + BaseFrameLayout.this.isNeedAdd + " , " + BaseFrameLayout.this.mWindowVisibility + ", " + BaseFrameLayout.this.mVisibility);
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
