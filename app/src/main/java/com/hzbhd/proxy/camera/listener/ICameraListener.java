package com.hzbhd.proxy.camera.listener;

import kotlin.Metadata;


public interface ICameraListener {
    void onAttrChange(int attrType, int value);

    void onInfoChange(int attrType, String value);

    void onPreviewState(int state);

    void onServiceConn();

    void onSignalChange(int signal);
}
