package com.hzbhd.proxy.bluetooth.aidl;

import com.hzbhd.proxy.bluetooth.aidl.IBtCallback;

interface IBtManager
{
    void addBtCallBack(IBtCallback callabck);
        void removeBtCallBack(IBtCallback callabck);

        void sendAction(String action);
        void sendDeviceAction(String action,String address);

        void call(String num);
        void sendKey(String key);
        void setName(String name);

        String getState();

        boolean isAutoConn();
        String getName();
        List<String> getPairedHfp();
        List<String> getConHfp();
        String getLocalAddress();
        String getCurrHfpAddress();
        String getCurrA2dpAddress();
        String getMusicTitle();
        String getMusicAlbum();
        String getMusicArtist();
        String getCallNum();////无效了

        boolean isMicOut();
        boolean isMicMute();
        int getCallDevice();

        boolean getPairMode();
        void setPinCode(String pinCode);
        String getPinCode();

    boolean enableWechatFilter();
    boolean isWechatFilter();
    void setWechatFilter(boolean filter);

    List<String> getCall();

    void answer(String call);
    void handup(String call);
    void sendCallKey(String call,String key);

    String getWechatCall();
    boolean isAutoAnswer();
}