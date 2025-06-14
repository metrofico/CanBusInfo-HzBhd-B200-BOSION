package com.hzbhd.canbus.util;

import androidx.core.app.NotificationCompat;
import com.hzbhd.adapter.BtAdapter;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BtApi.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\nJ\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\rJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\nJ\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\rJ\u0006\u0010 \u001a\u00020\u0004J\u000e\u0010!\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\r¨\u0006#"}, d2 = {"Lcom/hzbhd/canbus/util/BtApi;", "", "()V", "a2dpNext", "", "a2dpPause", "a2dpPlay", "a2dpPrev", "answer", "autoConn", "", NotificationCompat.CATEGORY_CALL, "num", "", "connA2dp", "address", "connHfp", "disConnA2dp", "disConnHfp", "getHfpDeviceName", "handUp", "muteMic", "mute", "pair", OriginalBtnAction.SCAN, "sendEQKey", "sendKey", "key", "setMicOut", "out", "setName", LcdInfoShare.MediaInfo.name, "syncPhoneBook", "unPair", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class BtApi {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<BtApi> instance$delegate = LazyKt.lazy(new Function0<BtApi>() { // from class: com.hzbhd.canbus.util.BtApi$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BtApi invoke() {
            return new BtApi();
        }
    });

    /* compiled from: BtApi.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/hzbhd/canbus/util/BtApi$Companion;", "", "()V", "instance", "Lcom/hzbhd/canbus/util/BtApi;", "getInstance", "()Lcom/hzbhd/canbus/util/BtApi;", "instance$delegate", "Lkotlin/Lazy;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BtApi getInstance() {
            return (BtApi) BtApi.instance$delegate.getValue();
        }
    }

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
