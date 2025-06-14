package com.hzbhd.ui.binding;

import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.ui.binding.BaseViewModel;
import kotlin.Metadata;

/* compiled from: BaseBinding.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\rH&¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0010R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/ui/binding/BaseBinding;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "", "()V", "root", "Landroid/view/View;", "getRoot", "()Landroid/view/View;", "addObserver", "", "vm", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Lcom/hzbhd/ui/binding/BaseViewModel;Landroidx/lifecycle/LifecycleOwner;)V", "bindAction", "(Lcom/hzbhd/ui/binding/BaseViewModel;)V", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public abstract class BaseBinding<T extends BaseViewModel> {
    public abstract void addObserver(T vm, LifecycleOwner lifecycleOwner);

    public abstract void bindAction(T vm);

    public abstract View getRoot();
}
