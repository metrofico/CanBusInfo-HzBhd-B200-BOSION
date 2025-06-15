package com.hzbhd.ui.binding;

import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.LifecycleOwner;

import com.hzbhd.ui.util.BaseUtil;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class ViewModelHelper {
    public static final ViewModelHelper INSTANCE = new ViewModelHelper();

    private ViewModelHelper() {
    }

    @Deprecated(message = "改成了在Repository实现")
    public <T extends BaseViewModel> View initView(LayoutInflater inflater, int layout, T baseViewModel, LifecycleOwner lifecycleOwner, BaseActionHelper<T> baseActionHelper, Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Intrinsics.checkNotNullParameter(baseViewModel, "baseViewModel");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(baseActionHelper, "baseActionHelper");
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        BaseUtil.logTime("init binding start: layout = " + layout + " class - " + modelClass.getName());
        BaseBinding<T> bindIngFromLayout = baseActionHelper.getBindIngFromLayout(inflater, layout, null, false, modelClass);
        bindIngFromLayout.addObserver(baseViewModel, lifecycleOwner);
        baseActionHelper.bindingAddAction(bindIngFromLayout, baseViewModel);
        bindIngFromLayout.bindAction(baseViewModel);
        BaseUtil.logTime("init binding " + bindIngFromLayout.getClass().getSimpleName() + " finish " + bindIngFromLayout.getRoot());
        return bindIngFromLayout.getRoot();
    }
}
