package com.hzbhd.ui.life;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyLifeCycleOwner.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/hzbhd/ui/life/MyLifeCycleOwner;", "Landroidx/lifecycle/LifecycleOwner;", "()V", "mLifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "onLifeCycleChange", "", "state", "Landroidx/lifecycle/Lifecycle$State;", "lifeview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MyLifeCycleOwner implements LifecycleOwner {
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final void onLifeCycleChange(Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.mLifecycleRegistry.setCurrentState(state);
    }
}
