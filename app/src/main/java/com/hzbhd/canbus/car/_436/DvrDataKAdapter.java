package com.hzbhd.canbus.car._436;

import android.os.RemoteException;
import com.hzbhd.proxy.callback.IDvrDataCallBack;
import com.hzbhd.proxy.dvr.DvrManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class DvrDataKAdapter {
    public static final DvrDataKAdapter INSTANCE = new DvrDataKAdapter();

    /* compiled from: DvrDataKAdapter.kt */
    
    public interface OnDvrDataReadListener {
        void onDataRead(byte[] bytes);
    }

    private DvrDataKAdapter() {
    }

    public final void registerOnDataReadCallback(final OnDvrDataReadListener listener) throws RemoteException {

        DvrManager.INSTANCE.registerOnDataReadCallback(new IDvrDataCallBack.Stub() { // from class: com.hzbhd.canbus.car._436.DvrDataKAdapter.registerOnDataReadCallback.1
            @Override // com.hzbhd.proxy.callback.IDvrDataCallBack
            public void onDvrDataRead(byte[] p0) {
                if (p0 != null) {
                    listener.onDataRead(p0);
                }
            }
        });
    }
}
