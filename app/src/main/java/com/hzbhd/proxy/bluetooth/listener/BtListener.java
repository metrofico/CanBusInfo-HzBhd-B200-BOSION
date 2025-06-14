package com.hzbhd.proxy.bluetooth.listener;

import androidx.core.app.NotificationCompat;
import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.BtStatus;
import com.hzbhd.proxy.bluetooth.bean.Device;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: BtListener.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\t\u001a\u00020\u00032\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J \u0010\u0011\u001a\u00020\u00032\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rH&J \u0010\u0013\u001a\u00020\u00032\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rH&J\u0018\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H&J*\u0010\u001a\u001a\u00020\u00032\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u000bj\b\u0012\u0004\u0012\u00020\u001c`\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H&J&\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010\u00072\b\u0010#\u001a\u0004\u0018\u00010\u00072\b\u0010$\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0010H&J\u0010\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0010H&J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0010H&J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0007H&J\u0012\u0010-\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH&¨\u0006."}, d2 = {"Lcom/hzbhd/proxy/bluetooth/listener/BtListener;", "", "onCloseA2dp", "", "onCloseHfp", "onCurrA2dpAddressChange", "address", "", "onCurrHfpAddressChange", "onHfpConnChange", "hfpList", "Ljava/util/ArrayList;", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "Lkotlin/collections/ArrayList;", "onMicOutChange", "out", "", "onPairedChange", "pairList", "onVisibleDeviceChange", "deviceList", "updateBtStatus", "changeState", "Lcom/hzbhd/constant/bluetooth/BtConstants$BT_STATUS;", NotificationCompat.CATEGORY_STATUS, "Lcom/hzbhd/proxy/bluetooth/bean/BtStatus;", "updateCall", "calls", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "changeCall", "updateCallDevice", "callDevice", "Lcom/hzbhd/constant/bluetooth/BtConstants$CallDevice;", "updateId3", LcdInfoShare.MediaInfo.title, LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "updateIsAutoAnswer", "autoAnswer", "updateIsAutoConn", "autoConn", "updateMicMute", "mute", "updateName", LcdInfoShare.MediaInfo.name, "updateWechatCall", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
