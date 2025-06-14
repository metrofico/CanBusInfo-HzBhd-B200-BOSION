package com.hzbhd.canbus.canCustom.canBase;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.hzbhd.ui.life.EmptyBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CanBinding.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanBinding;", "Lcom/hzbhd/ui/life/EmptyBinding;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addObserver", "", "lifecycleObserver", "Landroidx/lifecycle/LifecycleOwner;", "bindAction", "getLayoutId", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public class CanBinding extends EmptyBinding {
    @Override // com.hzbhd.ui.life.EmptyBinding
    public void addObserver(LifecycleOwner lifecycleObserver) {
        Intrinsics.checkNotNullParameter(lifecycleObserver, "lifecycleObserver");
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
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
