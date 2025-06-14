package com.hzbhd.ui.life;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmptyBinding.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u0010H&R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/ui/life/EmptyBinding;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "root", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getRoot", "()Landroid/view/View;", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "lifeview_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class EmptyBinding {
    private final View root;

    public abstract void addObserver(LifecycleOwner lifecycleObserver);

    public abstract void bindAction();

    public abstract int getLayoutId();

    public EmptyBinding(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.root = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) null, false);
    }

    public final View getRoot() {
        return this.root;
    }
}
