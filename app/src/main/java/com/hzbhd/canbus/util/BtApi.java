package com.hzbhd.canbus.util;

import com.hzbhd.adapter.BtAdapter;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;

import kotlin.jvm.internal.Intrinsics;


public final class BtApi {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final BtApi INSTANCE = new BtApi();

    public final void scan(boolean scan) {
        BtAdapter.INSTANCE.scan(scan);
    }

    public final void pair(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        BtAdapter.INSTANCE.pair(address, true);
    }

    public final void unPair(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        BtAdapter.INSTANCE.pair(address, false);
    }

    public final void connHfp(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        BtAdapter.INSTANCE.connectHfp(address, true);
    }

    public final void disConnHfp(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        BtAdapter.INSTANCE.connectHfp(address, false);
    }

    public final void connA2dp(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        BtAdapter.INSTANCE.connectA2dp(address, true);
    }

    public final void disConnA2dp(String address) {
        Intrinsics.checkNotNullParameter(address, "address");
        BtAdapter.INSTANCE.connectA2dp(address, false);
    }

    public final void autoConn(boolean autoConn) {
        BtAdapter.INSTANCE.setAutoConn(autoConn);
    }

    public final void call(String num) {
        Intrinsics.checkNotNullParameter(num, "num");
        BtAdapter.INSTANCE.call(num);
    }

    public final void a2dpPlay() {
        BtAdapter.INSTANCE.a2dpPlay();
    }

    public final void a2dpPause() {
        BtAdapter.INSTANCE.a2dpPause();
    }

    public final void a2dpPrev() {
        BtAdapter.INSTANCE.a2dpPrev();
    }

    public final void a2dpNext() {
        BtAdapter.INSTANCE.a2dpNext();
    }

    public final void sendEQKey() {
        SendKeyManager.getInstance().setKeyEvent(4, HotKeyConstant.KeyState.CLICK, false);
    }

    public final void setMicOut(boolean out) {
        BtAdapter.INSTANCE.setMicOut(out);
    }

    public final void syncPhoneBook() {
        BtAdapter.INSTANCE.syncPhoneBook();
    }

    public final void answer() {
        BtAdapter.INSTANCE.answer();
    }

    public final void handUp() {
        BtAdapter.INSTANCE.hangup();
    }

    public final void muteMic(boolean mute) {
        BtAdapter.INSTANCE.muteMic(mute);
    }

    public final void sendKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        BtAdapter.INSTANCE.sendKey(key);
    }

    public final void setName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        BtAdapter.INSTANCE.setBtName(name);
    }

    public final String getHfpDeviceName() {
        return BtAdapter.INSTANCE.getHfpDeviceName();
    }
}
