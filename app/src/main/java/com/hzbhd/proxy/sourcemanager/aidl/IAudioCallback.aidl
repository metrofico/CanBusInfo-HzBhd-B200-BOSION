
package com.hzbhd.proxy.sourcemanager.aidl;

// Declare any non-default types here with import statements

interface IAudioCallback {
    void requestAudio(int sourceId, int streamType, boolean naviMute);
    void setNavi(int sourceId, int streamType);
}