package com.hzbhd.proxy.keydispatcher;

import android.os.Bundle;

import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService;
import com.hzbhd.proxy.keydispatcher.interfaces.IHotKeyListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ReceiveKeyManager {
    private static ReceiveKeyManager mReceiveKeyManager;
    private HashMap<Integer, IHotKeyListener> mHotKeyListeners = new HashMap<>();
    private HashMap<Integer, IKeyDispatcherCallback> mIKeyDispatcherCallbacks = new HashMap<>();
    private HashSet<Integer> mThirdApps = new HashSet<>();

    public static synchronized ReceiveKeyManager getInstance() {
        synchronized (ReceiveKeyManager.class) {
            if (mReceiveKeyManager == null) {
                mReceiveKeyManager = new ReceiveKeyManager();
            }
        }
        return mReceiveKeyManager;
    }

    public boolean registerHotKeyListener(int i, IHotKeyListener iHotKeyListener) {
        this.mHotKeyListeners.put(Integer.valueOf(i), iHotKeyListener);
        return registerKeyDispatcherCallback(i);
    }

    public boolean unregisterHotKeyListener(int i) {
        if (!this.mHotKeyListeners.containsKey(Integer.valueOf(i))) {
            return true;
        }
        this.mHotKeyListeners.remove(Integer.valueOf(i));
        return unregisterKeyDispatcherCallback(i);
    }

    public boolean registerThirdApp(int i) {
        IKeyDispatcherService iKeyDispatcherService = KeyDispatcherManager.getIKeyDispatcherService();
        if (iKeyDispatcherService == null) {
            return false;
        }
        try {
            this.mThirdApps.add(Integer.valueOf(i));
            return iKeyDispatcherService.registerThirdApp(i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterThirdApp(int i) {
        IKeyDispatcherService iKeyDispatcherService = KeyDispatcherManager.getIKeyDispatcherService();
        if (iKeyDispatcherService == null) {
            return false;
        }
        try {
            this.mThirdApps.remove(Integer.valueOf(i));
            return iKeyDispatcherService.unregisterThirdApp(i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean registerKeyDispatcherCallback(int i) {
        IKeyDispatcherService iKeyDispatcherService = KeyDispatcherManager.getIKeyDispatcherService();
        if (iKeyDispatcherService == null) {
            return false;
        }
        if (this.mIKeyDispatcherCallbacks.containsKey(Integer.valueOf(i))) {
            return true;
        }
        this.mIKeyDispatcherCallbacks.put(Integer.valueOf(i), new MyIKeyDispatcherCallback(i));
        try {
            return iKeyDispatcherService.registerKeyDispatcherCallback(i, this.mIKeyDispatcherCallbacks.get(Integer.valueOf(i)));
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean unregisterKeyDispatcherCallback(int i) {
        IKeyDispatcherService iKeyDispatcherService = KeyDispatcherManager.getIKeyDispatcherService();
        if (iKeyDispatcherService == null) {
            return false;
        }
        if (!this.mIKeyDispatcherCallbacks.containsKey(Integer.valueOf(i))) {
            return true;
        }
        try {
            return iKeyDispatcherService.unregisterKeyDispatcherCallback(i, this.mIKeyDispatcherCallbacks.get(Integer.valueOf(i)));
        } catch (Exception e) {
            e.printStackTrace();
            this.mIKeyDispatcherCallbacks.remove(Integer.valueOf(i));
            return true;
        }
    }

    public void resetIKeyDispatcherCallback() {
        IKeyDispatcherService iKeyDispatcherService;
        if (this.mIKeyDispatcherCallbacks.isEmpty() || (iKeyDispatcherService = KeyDispatcherManager.getIKeyDispatcherService()) == null) {
            return;
        }
        Iterator<Integer> it = this.mIKeyDispatcherCallbacks.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            try {
                iKeyDispatcherService.registerKeyDispatcherCallback(iIntValue, this.mIKeyDispatcherCallbacks.get(Integer.valueOf(iIntValue)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Iterator<Integer> it2 = this.mThirdApps.iterator();
        while (it2.hasNext()) {
            try {
                iKeyDispatcherService.registerThirdApp(it2.next().intValue());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private class MyIKeyDispatcherCallback extends IKeyDispatcherCallback.Stub {
        private int mAppId;

        private MyIKeyDispatcherCallback(int i) {
            this.mAppId = i;
        }

        @Override // com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherCallback
        public boolean onKeyEvent(int i, int i2, int i3, Bundle bundle) {
            return ((IHotKeyListener) ReceiveKeyManager.this.mHotKeyListeners.get(Integer.valueOf(this.mAppId))).onKeyEvent(i, i2, i3, bundle);
        }
    }
}
