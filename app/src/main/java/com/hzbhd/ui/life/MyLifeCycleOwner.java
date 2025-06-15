package com.hzbhd.ui.life;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class MyLifeCycleOwner implements LifecycleOwner {
    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final void onLifeCycleChange(Lifecycle.State state) {

        this.mLifecycleRegistry.setCurrentState(state);
    }
}
