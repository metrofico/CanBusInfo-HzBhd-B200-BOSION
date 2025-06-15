package com.hzbhd.ui.life;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public abstract class EmptyBinding {
    private final View root;

    public abstract void addObserver(LifecycleOwner lifecycleObserver);

    public abstract void bindAction();

    public abstract int getLayoutId();

    public EmptyBinding(Context context) {

        this.root = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null, false);
    }

    public final View getRoot() {
        return this.root;
    }
}
