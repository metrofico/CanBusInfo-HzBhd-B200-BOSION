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

/* compiled from: BaseRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J2\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\"\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016J\b\u0010$\u001a\u00020\u001fH&J\u0016\u0010%\u001a\u00020\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u00028\u0000X\u0086.¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006&"}, d2 = {"Lcom/hzbhd/ui/binding/BaseRepository;", "T", "Lcom/hzbhd/ui/binding/BaseViewModel;", "", "()V", "isInit", "", "()Z", "setInit", "(Z)V", "vm", "getVm", "()Lcom/hzbhd/ui/binding/BaseViewModel;", "setVm", "(Lcom/hzbhd/ui/binding/BaseViewModel;)V", "Lcom/hzbhd/ui/binding/BaseViewModel;", "getBindIngFromLayout", "Lcom/hzbhd/ui/binding/BaseBinding;", "inflater", "Landroid/view/LayoutInflater;", "layoutId", "", "parent", "Landroid/view/ViewGroup;", "attachToRoot", "initView", "Landroid/view/View;", "layout", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "initVm", "", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "modelClass", "Ljava/lang/Class;", "onInit", "resetVm", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
