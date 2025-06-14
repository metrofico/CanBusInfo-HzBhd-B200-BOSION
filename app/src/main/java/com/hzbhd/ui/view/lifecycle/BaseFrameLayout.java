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

/* compiled from: BaseFrameLayout.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0006\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020$H\u0016J\b\u0010&\u001a\u00020\u000fH&J\b\u0010'\u001a\u00020(H\u0014J\b\u0010)\u001a\u00020*H\u0016J\u0006\u0010+\u001a\u00020$J\n\u0010,\u001a\u0004\u0018\u00010\"H&J\b\u0010-\u001a\u00020$H\u0014J\b\u0010.\u001a\u00020$H\u0014J\b\u0010/\u001a\u00020$H\u0014J\u0010\u00100\u001a\u00020$2\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020$H\u0016J\u0018\u00104\u001a\u00020$2\u0006\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020\nH\u0014J\u0010\u00107\u001a\u00020$2\u0006\u00106\u001a\u00020\nH\u0014R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/hzbhd/ui/view/lifecycle/BaseFrameLayout;", "Landroid/widget/FrameLayout;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "isAdd", "", "()Z", "setAdd", "(Z)V", "isInit", "setInit", "isNeedAdd", "setNeedAdd", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "mVisibility", "getMVisibility", "()I", "setMVisibility", "(I)V", "mWindowVisibility", "getMWindowVisibility", "setMWindowVisibility", "viewChild", "Landroid/view/View;", "addBaseChild", "", "addOrRemoveView", "autoInitChild", "generateDefaultLayoutParams", "Landroid/widget/FrameLayout$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "initChild", "initView", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onViewAdd", "onVisibilityChanged", "changedView", "visibility", "onWindowVisibilityChanged", "commonview-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
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
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mWindowVisibility = 8;
        this.mVisibility = 8;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
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
        BaseUtil.INSTANCE.runBack(new Function0<Unit>() { // from class: com.hzbhd.ui.view.lifecycle.BaseFrameLayout.initChild.1
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

    public final void addBaseChild() {
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.lifecycle.BaseFrameLayout.addBaseChild.1
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
        BaseUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.view.lifecycle.BaseFrameLayout.addOrRemoveView.1
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
                    LogUtil.d("addOrRemoveView: " + BaseFrameLayout.this.getClass().getSimpleName() + "  " + BaseFrameLayout.this.isAttachedToWindow() + " , " + BaseFrameLayout.this.getWindowVisibility() + " , " + BaseFrameLayout.this.getVisibility() + " , " + BaseFrameLayout.this.getIsAdd() + " , " + BaseFrameLayout.this.getIsNeedAdd() + " , " + BaseFrameLayout.this.getMWindowVisibility() + ", " + BaseFrameLayout.this.getMVisibility());
                }
                if (BaseFrameLayout.this.isAttachedToWindow() && BaseFrameLayout.this.getWindowVisibility() == 0 && BaseFrameLayout.this.getVisibility() == 0 && BaseFrameLayout.this.getMWindowVisibility() == 0 && BaseFrameLayout.this.getMVisibility() == 0) {
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
        Intrinsics.checkNotNullParameter(state, "state");
        this.mLifecycleRegistry.markState(state);
    }
}
