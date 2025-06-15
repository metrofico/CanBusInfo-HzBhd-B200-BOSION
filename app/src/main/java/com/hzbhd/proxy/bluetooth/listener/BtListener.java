package com.hzbhd.proxy.bluetooth.listener;

import androidx.core.app.NotificationCompat;

import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.BtStatus;
import com.hzbhd.proxy.bluetooth.bean.Device;

import java.util.ArrayList;

import kotlin.Metadata;


public interface BtListener {
    void onCloseA2dp();

    void onCloseHfp();

    void onCurrA2dpAddressChange(String address);

    void onCurrHfpAddressChange(String address);

    void onHfpConnChange(ArrayList<Device> hfpList);

    void onMicOutChange(boolean out);

    void onPairedChange(ArrayList<Device> pairList);

    void onVisibleDeviceChange(ArrayList<Device> deviceList);

    void updateBtStatus(BtConstants.BT_STATUS changeState, BtStatus status);

    void updateCall(ArrayList<BtCall> calls, BtCall changeCall);

    void updateCallDevice(BtConstants.CallDevice callDevice);

    void updateId3(String title, String artist, String album);

    void updateIsAutoAnswer(boolean autoAnswer);

    void updateIsAutoConn(boolean autoConn);

    void updateMicMute(boolean mute);

    void updateName(String name);

    void updateWechatCall(BtCall changeCall);
}
