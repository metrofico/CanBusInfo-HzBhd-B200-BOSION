package com.hzbhd.ui.binding;

import android.view.LayoutInflater;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewModelHelper.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JS\u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\u00052\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0011H\u0017¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/hzbhd/ui/binding/ViewModelHelper;", "", "()V", "initView", "Landroid/view/View;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "inflater", "Landroid/view/LayoutInflater;", "layout", "", "baseViewModel", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "baseActionHelper", "Lcom/hzbhd/ui/binding/BaseActionHelper;", "modelClass", "Ljava/lang/Class;", "(Landroid/view/LayoutInflater;ILcom/hzbhd/ui/binding/BaseViewModel;Landroidx/lifecycle/LifecycleOwner;Lcom/hzbhd/ui/binding/BaseActionHelper;Ljava/lang/Class;)Landroid/view/View;", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
