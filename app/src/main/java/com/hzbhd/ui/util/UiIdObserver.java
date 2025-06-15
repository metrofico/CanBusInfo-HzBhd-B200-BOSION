package com.hzbhd.ui.util;

import android.text.TextUtils;

import com.hzbhd.config.use.UI;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.util.LogUtil;

import java.util.ArrayList;
import java.util.function.Consumer;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

public final class UiIdObserver {
    private final ArrayList<UIChangeListener> uiChangeListeners = new ArrayList<>();
    private String oldId = UI.INSTANCE.getUIId();

    public interface UIChangeListener {
        void onUIChange(String uiid);
    }

    public UiIdObserver() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_UI_ID, new IShareStringListener() { // from class: com.hzbhd.ui.util.UiIdObserver$$ExternalSyntheticLambda1
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                notifyUIChange(str);
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


    public static void addUIChangeListener$default(UiIdObserver uiIdObserver, int i, UIChangeListener uIChangeListener, int i2, Object obj) {
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
                UiIdObserver.m1207notifyUIChange$lambda1(uiid, (UIChangeListener) obj);
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
