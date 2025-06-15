// ISourceService.aidl
package com.hzbhd.proxy.sourcemanager.aidl;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceCallback;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceBluetoothCallback;
import com.hzbhd.proxy.sourcemanager.aidl.IAudioCallback;

// Declare any non-default types here with import statements

interface ISourceService {
    // Source Audio
    boolean registerSourceCallback(int dispSource, ISourceCallback callback);
    boolean unregisterSourceCallback(int dispSource);
    int requestAudioChannel(int sourceId, int dispId, in Bundle bundle);
    int releaseAudioChannel(int sourceId, int dispId);
    void notifyAppAudio(int sourceId, String pkgName, int streamType, boolean isPlay);
    int getCurrentAudioSource(int dispId);

    // Source Bluetooth
    boolean requestBluetooth(int sourceId, ISourceBluetoothCallback callback);
    boolean releaseBluetooth(int sourceId);

    // Audio depend
    int registerAudioCallback(int dispId, IAudioCallback callback);
    int unregisterAudioCallback(int dispId, IAudioCallback callback);
}