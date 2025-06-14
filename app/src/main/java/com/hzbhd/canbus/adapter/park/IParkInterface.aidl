package com.hzbhd.canbus.adapter.park;
import com.hzbhd.canbus.adapter.park.IParkCallBackInterface;

interface IParkInterface {
    void registerCallBack(IParkCallBackInterface acallbackInterface);
    void unRegisterCallBack();
    void sendPanoramicParkOn(boolean isOn);
    void sendPanoramicParkKey(int key);
    void sendPanoramicParkWH(int w, int h);
    void sendPanoramicParkPos(int press, int x, int y);
}
