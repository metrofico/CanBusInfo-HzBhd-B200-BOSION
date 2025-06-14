package com.hzbhd.ui.binding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.hzbhd.ui.binding.BaseViewModel;
import kotlin.Deprecated;
import kotlin.Metadata;

/* compiled from: BaseActionHelper.kt */
@Deprecated(message = "新项目不再使用这个类，旧项目后续有空可以优化")
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J#\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\b\u001a\u00028\u0000H'¢\u0006\u0002\u0010\tJ>\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H'¨\u0006\u0015"}, d2 = {"Lcom/hzbhd/ui/binding/BaseActionHelper;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "", "bindingAddAction", "", "binding", "Lcom/hzbhd/ui/binding/BaseBinding;", "vm", "(Lcom/hzbhd/ui/binding/BaseBinding;Lcom/hzbhd/ui/binding/BaseViewModel;)V", "getBindIngFromLayout", "inflater", "Landroid/view/LayoutInflater;", "layoutId", "", "parent", "Landroid/view/ViewGroup;", "attachToRoot", "", "modelClass", "Ljava/lang/Class;", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface BaseActionHelper<T extends BaseViewModel> {
    @Deprecated(message = "改成了在binding自己加action")
    void bindingAddAction(BaseBinding<T> binding, T vm);

    @Deprecated(message = "改成了在Repository实现")
    BaseBinding<T> getBindIngFromLayout(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToRoot, Class<T> modelClass);
}
