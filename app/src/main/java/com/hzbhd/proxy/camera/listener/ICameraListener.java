package com.hzbhd.proxy.camera.listener;

import kotlin.Metadata;

/* compiled from: ICameraListener.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u001a\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/hzbhd/proxy/camera/listener/ICameraListener;", "", "onAttrChange", "", "attrType", "", "value", "onInfoChange", "", "onPreviewState", "state", "onServiceConn", "onSignalChange", "signal", "camera-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public interface ICameraListener {
    void onAttrChange(int attrType, int value);

    void onInfoChange(int attrType, String value);

    void onPreviewState(int state);

    void onServiceConn();

    void onSignalChange(int signal);
}
