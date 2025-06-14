package com.hzbhd.canbus.canCustom.canBase;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.hzbhd.ui.life.EmptyBinding;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public class CanBinding extends EmptyBinding {
    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public void bindAction() {
    }

    @Override // com.hzbhd.ui.life.EmptyBinding
    public int getLayoutId() {
        return -1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CanBinding(Context context) {
        super(context);
    }
}
