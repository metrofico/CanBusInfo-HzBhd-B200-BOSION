package com.hzbhd.ui.binding;

import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.hzbhd.ui.binding.BaseViewModel;

import kotlin.Metadata;


public abstract class BaseBinding<T extends BaseViewModel> {
    public abstract void addObserver(T vm, LifecycleOwner lifecycleOwner);

    public abstract void bindAction(T vm);

    public abstract View getRoot();
}
