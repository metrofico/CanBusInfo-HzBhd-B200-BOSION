package com.hzbhd.ui.binding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;

import com.hzbhd.ui.binding.BaseViewModel;
import com.hzbhd.ui.util.BaseUtil;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public abstract class BaseRepository<T extends BaseViewModel> {
    private boolean isInit;
    public T vm;

    public BaseBinding<T> getBindIngFromLayout(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToRoot) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return null;
    }

    public abstract void onInit();

    public final T getVm() {
        T t = this.vm;
        if (t != null) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException("vm");
        return null;
    }

    public final void setVm(T t) {
        Intrinsics.checkNotNullParameter(t, "<set-?>");
        this.vm = t;
    }

    /* renamed from: isInit, reason: from getter */
    public final boolean getIsInit() {
        return this.isInit;
    }

    public final void setInit(boolean z) {
        this.isInit = z;
    }

    public static /* synthetic */ void initVm$default(BaseRepository baseRepository, ViewModelStoreOwner viewModelStoreOwner, Class cls, int i, Object obj) throws IllegalAccessException, InstantiationException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initVm");
        }
        if ((i & 1) != 0) {
            viewModelStoreOwner = null;
        }
        baseRepository.initVm(viewModelStoreOwner, cls);
    }

    public void initVm(ViewModelStoreOwner viewModelStoreOwner, Class<T> modelClass) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        T tNewInstance = modelClass.newInstance();
        Intrinsics.checkNotNullExpressionValue(tNewInstance, "modelClass.newInstance()");
        setVm(tNewInstance);
        onInit();
    }

    public void resetVm(Class<T> modelClass) throws IllegalAccessException, InstantiationException {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        T tNewInstance = modelClass.newInstance();
        Intrinsics.checkNotNullExpressionValue(tNewInstance, "modelClass.newInstance()");
        setVm(tNewInstance);
    }

    public View initView(LayoutInflater inflater, int layout, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        BaseUtil.logTime("init binding start: layout = " + layout + " class - " + getVm().getClass().getName());
        BaseBinding<T> bindIngFromLayout = getBindIngFromLayout(inflater, layout, null, false);
        Intrinsics.checkNotNull(bindIngFromLayout);
        bindIngFromLayout.addObserver(getVm(), lifecycleOwner);
        bindIngFromLayout.bindAction(getVm());
        BaseUtil.logTime("init binding " + bindIngFromLayout.getClass().getSimpleName() + " finish " + bindIngFromLayout.getRoot());
        return bindIngFromLayout.getRoot();
    }
}
