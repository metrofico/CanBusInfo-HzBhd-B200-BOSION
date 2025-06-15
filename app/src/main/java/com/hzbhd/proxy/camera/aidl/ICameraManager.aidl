package com.hzbhd.proxy.camera.aidl;

import android.view.Surface;
import com.hzbhd.proxy.camera.aidl.ICameraCallback;

interface ICameraManager
{
    void startPreview(int flag,in Surface surface);
    void stopPreview(int flag);
    void setFlagAttr(int flag,int attr,int value);
    int getFlagAttr(int flag,int attr);
    void addCallBack(int flag,ICameraCallback callback);
    void removeCallBack(int flag,ICameraCallback callback);
    void startRecord(int flag,in Surface surface);
    void stopRecord(int flag);
    void setCameraInfo(int flag,int info,String value);
    String getCameraInfo(int flag,int info);
    void startPreviewWithCallBack(int flag,in Surface surface,ICameraCallback callback);
}