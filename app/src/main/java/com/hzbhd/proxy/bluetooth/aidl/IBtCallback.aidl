package com.hzbhd.proxy.bluetooth.aidl;

interface IBtCallback
{
    void onCloseA2dp();
        void onCloseHfp();
        void updateBtStatus(int changeState,String status);

        void updateIsAutoConn(boolean autoConn);
        void onMicOutChange(boolean micOut);

        void onFoundDeviceChange(in List<String> StringList);
        void onHfpConnChange(in List<String> hfpList);
        void onCurrHfpAddressChange(String address);
        void onCurrA2dpAddressChange(String address);
        void onPairedChange(in List<String> pairList);

        void updateName(String name);
        void updateCallName(String name);//无效了
        void onCallNumChange(String num);//无效了

        void updateId3(String title,String artist,String album);

        void updateCallTime(long time);//无效了

        void updateMicMute(boolean mute);

        void updateCallDevice(int callDevice);

        void callingMessage(String name,String address);//无效了

    void updateCall(in List<String> calls,String changeCall);
    void updateWechatCall(String changeCall);
    void updateIsAutoAnswer(boolean autoAnswer);
}