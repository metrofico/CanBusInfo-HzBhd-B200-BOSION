package com.hzbhd.proxy.sourcemanager;

import android.os.Bundle;
import android.os.RemoteException;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceService;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class SourceManager {
    public static final int ERR_ACT_FAIL = -2;
    public static final int ERR_NO_SERVICE = -1;
    public static final int ERR_SOURCE_NOT_DEFINED = -3;
    private static SourceManager mSourceManager;
    private HashMap<Integer, ISourceListener> mDispSourceListeners = new HashMap<>();
    private HashMap<Integer, MyISourceCallback> mDispSourceCallbacks = new HashMap<>();
    private HashMap<Integer, ISourceBluetoothListener> mSourceBluetoothListeners = new HashMap<>();
    private HashMap<Integer, ISourceBluetoothCallback> mSourceBluetoothCallbacks = new HashMap<>();

    public static synchronized SourceManager getInstance() {
        synchronized (SourceManager.class) {
            if (mSourceManager == null) {
                mSourceManager = new SourceManager();
            }
        }
        return mSourceManager;
        return mSourceManager;
    }

    public boolean registerSourceListener(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id, ISourceListener iSourceListener) {
        boolean zRegisterSourceCallback;
        int dispSource = SourceUtils.getDispSource(source_id.ordinal(), disp_id.ordinal());
        this.mDispSourceListeners.put(Integer.valueOf(dispSource), iSourceListener);
        if (!this.mDispSourceCallbacks.containsKey(Integer.valueOf(dispSource))) {
            this.mDispSourceCallbacks.put(Integer.valueOf(dispSource), new MyISourceCallback(dispSource));
        }
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return false;
        }
        try {
            zRegisterSourceCallback = iSourceService.registerSourceCallback(dispSource, this.mDispSourceCallbacks.get(Integer.valueOf(dispSource)));
        } catch (Exception e) {
            e.printStackTrace();
            zRegisterSourceCallback = false;
        }
        if (zRegisterSourceCallback) {
            return true;
        }
        removeISourceCallback(dispSource);
        return false;
    }

    public boolean unregisterSourceListener(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id) {
        int dispSource = SourceUtils.getDispSource(source_id.ordinal(), disp_id.ordinal());
        boolean zUnregisterSourceCallback = false;
        if (!this.mDispSourceListeners.containsKey(Integer.valueOf(dispSource))) {
            return false;
        }
        this.mDispSourceListeners.remove(Integer.valueOf(dispSource));
        try {
            zUnregisterSourceCallback = SourceServiceManager.getISourceService().unregisterSourceCallback(dispSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        removeISourceCallback(dispSource);
        return zUnregisterSourceCallback;
    }

    public int requestSource(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("start", bundle);
        return requestAudioChannel(source_id, disp_id, bundle2);
    }

    public int requestAudioChannel(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id) {
        return requestAudioChannel(source_id, disp_id, null);
    }

    public int requestAudioChannel(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("pkg_name", str);
        bundle.putString("cls_name", str);
        return requestAudioChannel(source_id, disp_id, bundle);
    }

    public int requestAudioChannel(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id, Bundle bundle) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return -1;
        }
        try {
            return iSourceService.requestAudioChannel(source_id.ordinal(), disp_id.ordinal(), bundle);
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    public int releaseAudioChannel(SourceConstantsDef.SOURCE_ID source_id, SourceConstantsDef.DISP_ID disp_id) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return -1;
        }
        try {
            return iSourceService.releaseAudioChannel(source_id.ordinal(), disp_id.ordinal());
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    public void notifyAppAudio(SourceConstantsDef.SOURCE_ID source_id, String str, int i, boolean z) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return;
        }
        try {
            iSourceService.notifyAppAudio(source_id.getValue(), str, i, z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SourceConstantsDef.SOURCE_ID getCurrentAudioSource(SourceConstantsDef.DISP_ID disp_id) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return SourceConstantsDef.SOURCE_ID.NULL;
        }
        int currentAudioSource = 0;
        try {
            currentAudioSource = iSourceService.getCurrentAudioSource(disp_id.ordinal());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return SourceConstantsDef.SOURCE_ID.values()[currentAudioSource];
    }

    private void removeISourceCallback(int i) {
        if (this.mDispSourceCallbacks.containsKey(Integer.valueOf(i))) {
            this.mDispSourceCallbacks.remove(Integer.valueOf(i));
        }
    }

    private class MyISourceCallback extends ISourceCallback.Stub {
        private int mDispSource;

        public MyISourceCallback(int i) {
            this.mDispSource = i;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback
        public void onAction(int i, Bundle bundle) throws RemoteException {
            if (SourceManager.this.mDispSourceListeners.containsKey(Integer.valueOf(this.mDispSource))) {
                ((ISourceListener) SourceManager.this.mDispSourceListeners.get(Integer.valueOf(this.mDispSource))).onAction(SourceConstantsDef.SOURCE_ACTION.values()[i], bundle);
            }
        }
    }

    public void resetSourceCallback() {
        if (this.mDispSourceCallbacks.isEmpty()) {
            return;
        }
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        Iterator<Integer> it = this.mDispSourceCallbacks.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            try {
                iSourceService.registerSourceCallback(iIntValue, this.mDispSourceCallbacks.get(Integer.valueOf(iIntValue)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean requestBluetooth(SourceConstantsDef.SOURCE_ID source_id, ISourceBluetoothListener iSourceBluetoothListener) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return false;
        }
        int value = source_id.getValue();
        this.mSourceBluetoothListeners.put(Integer.valueOf(value), iSourceBluetoothListener);
        if (this.mSourceBluetoothCallbacks.containsKey(Integer.valueOf(value))) {
            return true;
        }
        this.mSourceBluetoothCallbacks.put(Integer.valueOf(value), new MyISourceBluetoothCallback(value));
        try {
            return iSourceService.requestBluetooth(value, this.mSourceBluetoothCallbacks.get(Integer.valueOf(value)));
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean requestBluetooth(SourceConstantsDef.SOURCE_ID source_id) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return false;
        }
        try {
            return iSourceService.requestBluetooth(source_id.getValue(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean releaseBluetooth(SourceConstantsDef.SOURCE_ID source_id) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return false;
        }
        int value = source_id.getValue();
        if (!this.mSourceBluetoothListeners.containsKey(Integer.valueOf(value))) {
            return true;
        }
        this.mSourceBluetoothListeners.remove(Integer.valueOf(value));
        this.mSourceBluetoothCallbacks.remove(Integer.valueOf(value));
        try {
            iSourceService.releaseBluetooth(value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    private class MyISourceBluetoothCallback extends ISourceBluetoothCallback.Stub {
        private int mSourceId;

        public MyISourceBluetoothCallback(int i) {
            this.mSourceId = i;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback
        public void onState(int i) throws RemoteException {
            if (SourceManager.this.mSourceBluetoothListeners.containsKey(Integer.valueOf(this.mSourceId))) {
                ((ISourceBluetoothListener) SourceManager.this.mSourceBluetoothListeners.get(Integer.valueOf(this.mSourceId))).onState(i);
            }
        }
    }

    public void resetSourceBluetoothCallbacks() {
        if (this.mSourceBluetoothCallbacks.isEmpty()) {
            return;
        }
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        Iterator<Integer> it = this.mSourceBluetoothCallbacks.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            try {
                iSourceService.requestBluetooth(iIntValue, this.mSourceBluetoothCallbacks.get(Integer.valueOf(iIntValue)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
