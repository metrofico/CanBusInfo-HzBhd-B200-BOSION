package com.hzbhd.proxy.camera.aidl;

interface ICameraCallback
{
    void onSignalChange(int signal);
    void onPreviewState(int state);
    void onAttrChange(int attrType,int value);
    void onInfoChange(int infoType,String value);
}