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

/* compiled from: BaseLifeRelativeLayout.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001bH\u0014J\b\u0010!\u001a\u00020\u001bH\u0014J\b\u0010\"\u001a\u00020\u001bH\u0014J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\nH\u0014J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\nH\u0014R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006+"}, d2 = {"Lcom/hzbhd/ui/life/BaseLifeRelativeLayout;", "Lcom/hzbhd/ui/base/BhdRelativeLayout;", "Landroidx/lifecycle/LifecycleOwner;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getMLifecycleRegistry", "()Landroidx/lifecycle/LifecycleRegistry;", "mVisibility", "getMVisibility", "()I", "setMVisibility", "(I)V", "mWindowVisibility", "getMWindowVisibility", "setMWindowVisibility", "addOrRemoveView", "", "generateDefaultLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "onAttachedToWindow", "onDetachedFromWindow", "onFinishInflate", "onLifeCycleChange", "state", "Landroidx/lifecycle/Lifecycle$State;", "onVisibilityChanged", "changedView", "Landroid/view/View;", "visibility", "onWindowVisibilityChanged", "lifeview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
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
        BhdViewUtil.INSTANCE.runUi(new Function0<Unit>() { // from class: com.hzbhd.ui.life.BaseLifeRelativeLayout.addOrRemoveView.1
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
                    LogUtil.d("addOrRemoveView: " + ((Object) BaseLifeRelativeLayout.this.getClass().getSimpleName()) + "  " + BaseLifeRelativeLayout.this.isAttachedToWindow() + " , " + BaseLifeRelativeLayout.this.getWindowVisibility() + " , " + BaseLifeRelativeLayout.this.getVisibility() + " , " + BaseLifeRelativeLayout.this.getMVisibility() + ", " + BaseLifeRelativeLayout.this.getMWindowVisibility());
                }
                if (BaseLifeRelativeLayout.this.isAttachedToWindow() && BaseLifeRelativeLayout.this.getWindowVisibility() == 0 && BaseLifeRelativeLayout.this.getVisibility() == 0 && BaseLifeRelativeLayout.this.getMVisibility() == 0 && BaseLifeRelativeLayout.this.getMWindowVisibility() == 0) {
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
