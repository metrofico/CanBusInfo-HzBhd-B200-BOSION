package com.hzbhd.ui.util;

import android.text.TextUtils;
import com.hzbhd.config.use.UI;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.ui.util.UiIdObserver;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UiIdObserver.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bJ\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000bR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/hzbhd/ui/util/UiIdObserver;", "", "()V", "oldId", "", "getOldId", "()Ljava/lang/String;", "setOldId", "(Ljava/lang/String;)V", "uiChangeListeners", "Ljava/util/ArrayList;", "Lcom/hzbhd/ui/util/UiIdObserver$UIChangeListener;", "Lkotlin/collections/ArrayList;", "addUIChangeListener", "", "index", "", "uiChangeListener", "notifyUIChange", "uiid", "removeUIChangeListener", "UIChangeListener", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class UiIdObserver {
    private final ArrayList<UIChangeListener> uiChangeListeners = new ArrayList<>();
    private String oldId = UI.INSTANCE.getUIId();

    /* compiled from: UiIdObserver.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/hzbhd/ui/util/UiIdObserver$UIChangeListener;", "", "onUIChange", "", "uiid", "", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface UIChangeListener {
        void onUIChange(String uiid);
    }

    public UiIdObserver() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_UI_ID, new IShareStringListener() { // from class: com.hzbhd.ui.util.UiIdObserver$$ExternalSyntheticLambda1
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                UiIdObserver.m1206_init_$lambda0(this.f$0, str);
            }
        });
    }

    public final String getOldId() {
        return this.oldId;
    }

    public final void setOldId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.oldId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1206_init_$lambda0(UiIdObserver this$0, String uiid) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(uiid, "uiid");
        this$0.notifyUIChange(uiid);
    }

    public static /* synthetic */ void addUIChangeListener$default(UiIdObserver uiIdObserver, int i, UIChangeListener uIChangeListener, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        uiIdObserver.addUIChangeListener(i, uIChangeListener);
    }

    public final void addUIChangeListener(int index, UIChangeListener uiChangeListener) {
        Intrinsics.checkNotNullParameter(uiChangeListener, "uiChangeListener");
        if (this.uiChangeListeners.contains(uiChangeListener)) {
            return;
        }
        if (index < 0) {
            this.uiChangeListeners.add(uiChangeListener);
        } else {
            this.uiChangeListeners.add(index, uiChangeListener);
        }
    }

    public final void removeUIChangeListener(UIChangeListener uiChangeListener) {
        Intrinsics.checkNotNullParameter(uiChangeListener, "uiChangeListener");
        if (this.uiChangeListeners.contains(uiChangeListener)) {
            this.uiChangeListeners.remove(uiChangeListener);
        }
    }

    private final void notifyUIChange(final String uiid) {
        if (LogUtil.log5()) {
            LogUtil.d("notifyUIChange: " + this.oldId + ' ' + uiid + "  " + UI.INSTANCE.getUIId() + "  " + this.uiChangeListeners.size());
        }
        if (Intrinsics.areEqual(this.oldId, uiid) || TextUtils.isEmpty(uiid)) {
            return;
        }
        this.oldId = uiid;
        this.uiChangeListeners.stream().forEach(new Consumer() { // from class: com.hzbhd.ui.util.UiIdObserver$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                UiIdObserver.m1207notifyUIChange$lambda1(uiid, (UiIdObserver.UIChangeListener) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: notifyUIChange$lambda-1, reason: not valid java name */
    public static final void m1207notifyUIChange$lambda1(String uiid, UIChangeListener uIChangeListener) {
        Intrinsics.checkNotNullParameter(uiid, "$uiid");
        uIChangeListener.onUIChange(uiid);
    }
}
