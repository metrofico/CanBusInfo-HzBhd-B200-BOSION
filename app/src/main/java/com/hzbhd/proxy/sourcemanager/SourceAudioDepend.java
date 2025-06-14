package com.hzbhd.proxy.sourcemanager;

import android.os.RemoteException;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceService;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class SourceAudioDepend {
    private static SourceAudioDepend mAudioDepend;
    private HashMap<Integer, IAudioDependListener> mDispAudioListeners = new HashMap<>();
    private HashMap<Integer, IAudioCallback> mDispAudioCallbacks = new HashMap<>();

    public static synchronized SourceAudioDepend getInstance() {
        synchronized (SourceAudioDepend.class) {
            if (mAudioDepend == null) {
                mAudioDepend = new SourceAudioDepend();
            }
        }
        return mAudioDepend;
        return mAudioDepend;
    }

    public int registerAudioListener(SourceConstantsDef.DISP_ID disp_id, IAudioDependListener iAudioDependListener) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return -1;
        }
        int iOrdinal = disp_id.ordinal();
        this.mDispAudioListeners.put(Integer.valueOf(iOrdinal), iAudioDependListener);
        try {
            return iSourceService.registerAudioCallback(iOrdinal, getIAudioCallback(iOrdinal));
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    public boolean unregisterAudioListener(int i, IAudioDependListener iAudioDependListener) {
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        if (iSourceService == null) {
            return false;
        }
        if (this.mDispAudioListeners.containsKey(Integer.valueOf(i)) && this.mDispAudioListeners.get(Integer.valueOf(i)) == iAudioDependListener) {
            this.mDispAudioListeners.remove(Integer.valueOf(i));
            try {
                iSourceService.unregisterAudioCallback(i, getIAudioCallback(i));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            this.mDispAudioCallbacks.remove(Integer.valueOf(i));
        }
        return true;
    }

    public void resetAudioCallback() {
        if (this.mDispAudioCallbacks.isEmpty()) {
            return;
        }
        ISourceService iSourceService = SourceServiceManager.getISourceService();
        Iterator<Integer> it = this.mDispAudioCallbacks.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            try {
                iSourceService.registerAudioCallback(iIntValue, this.mDispAudioCallbacks.get(Integer.valueOf(iIntValue)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private IAudioCallback getIAudioCallback(int i) {
        if (!this.mDispAudioCallbacks.containsKey(Integer.valueOf(i))) {
            this.mDispAudioCallbacks.put(Integer.valueOf(i), new MyIAudioCallback(i));
        }
        return this.mDispAudioCallbacks.get(Integer.valueOf(i));
    }

    private class MyIAudioCallback extends IAudioCallback.Stub {
        private int mDispId;

        public MyIAudioCallback(int i) {
            this.mDispId = i;
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback
        public void requestAudio(int i, int i2, boolean z) throws RemoteException {
            if (SourceAudioDepend.this.mDispAudioListeners.containsKey(Integer.valueOf(this.mDispId))) {
                ((IAudioDependListener) SourceAudioDepend.this.mDispAudioListeners.get(Integer.valueOf(this.mDispId))).requestAudio(i, i2, z);
            }
        }

        @Override // com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback
        public void setNavi(int i, int i2) throws RemoteException {
            if (SourceAudioDepend.this.mDispAudioListeners.containsKey(Integer.valueOf(this.mDispId))) {
                ((IAudioDependListener) SourceAudioDepend.this.mDispAudioListeners.get(Integer.valueOf(this.mDispId))).setNavi(i, i2);
            }
        }
    }
}
