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

/* compiled from: BaseRelativeLayout.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0004J\b\u0010\u001b\u001a\u00020\u000fH&J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020\u0019J\n\u0010!\u001a\u0004\u0018\u00010\u0017H&J\b\u0010\"\u001a\u00020\u0019H\u0014J\b\u0010#\u001a\u00020\u0019H\u0014J\b\u0010$\u001a\u00020\u0019H\u0014J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016J\u0018\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\nH\u0014J\u0010\u0010,\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\nH\u0014R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/hzbhd/ui/view/lifecycle/BaseRelativeLayout;", "Landroid/widget/RelativeLayout;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isAdd", "", "isInit", "isNeedAdd", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "mVisibility", "mWindowVisibility", "viewChild", "Landroid/view/View;", "addBaseChild", "", "addOrRemoveView", "autoInitChild", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "initChild", "initView", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onViewAdd", "onVisibilityChanged", "changedView", "visibility", "onWindowVisibilityChanged", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
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
        BaseUtil.INSTANCE.runBack(new Function0<Unit>() { // from class: com.hzbhd.ui.view.lifecycle.BaseRelativeLayout.initChild.1
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
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.lifecycle.BaseRelativeLayout.addBaseChild.1
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
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.lifecycle.BaseRelativeLayout.addOrRemoveView.1
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
                if (LogUtil.log3()) {
                    LogUtil.d("addOrRemoveView: " + BaseRelativeLayout.this.getClass().getSimpleName() + "  " + BaseRelativeLayout.this.isAttachedToWindow() + " , " + BaseRelativeLayout.this.getWindowVisibility() + " , " + BaseRelativeLayout.this.getVisibility() + " , " + BaseRelativeLayout.this.isAdd + " , " + BaseRelativeLayout.this.isNeedAdd + " , " + BaseRelativeLayout.this.mWindowVisibility + ", " + BaseRelativeLayout.this.mVisibility);
                }
                if (!BaseRelativeLayout.this.isAttachedToWindow() || BaseRelativeLayout.this.getWindowVisibility() != 0 || BaseRelativeLayout.this.getVisibility() != 0 || BaseRelativeLayout.this.mWindowVisibility != 0 || BaseRelativeLayout.this.mVisibility != 0) {
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
