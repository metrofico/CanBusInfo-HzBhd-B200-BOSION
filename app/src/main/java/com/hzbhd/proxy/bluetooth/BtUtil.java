package com.hzbhd.proxy.bluetooth;

import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.Device;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BtUtil.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bJ$\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bJ$\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0004j\b\u0012\u0004\u0012\u00020\t`\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\bJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\b¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/BtUtil;", "", "()V", "callsToString", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "calls", "", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "devicesToString", "devices", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "stringToCall", "callStrings", "stringToDevices", "stringDevices", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class BtUtil {
    public static final BtUtil INSTANCE = new BtUtil();

    private BtUtil() {
    }

    public final ArrayList<Device> stringToDevices(List<String> stringDevices) {
        Intrinsics.checkNotNullParameter(stringDevices, "stringDevices");
        ArrayList<Device> arrayList = new ArrayList<>();
        int size = stringDevices.size();
        for (int i = 0; i < size; i++) {
            Device deviceFromJson = Device.INSTANCE.fromJson(stringDevices.get(i));
            Intrinsics.checkNotNull(deviceFromJson);
            arrayList.add(deviceFromJson);
        }
        return arrayList;
    }

    public final ArrayList<String> devicesToString(List<Device> devices) {
        Intrinsics.checkNotNullParameter(devices, "devices");
        ArrayList<String> arrayList = new ArrayList<>();
        int size = devices.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Device.INSTANCE.toJsonString(devices.get(i)));
        }
        return arrayList;
    }

    public final ArrayList<BtCall> stringToCall(List<String> callStrings) {
        Intrinsics.checkNotNullParameter(callStrings, "callStrings");
        ArrayList<BtCall> arrayList = new ArrayList<>();
        int size = callStrings.size();
        for (int i = 0; i < size; i++) {
            BtCall btCallFromJson = BtCall.INSTANCE.fromJson(callStrings.get(i));
            Intrinsics.checkNotNull(btCallFromJson);
            arrayList.add(btCallFromJson);
        }
        return arrayList;
    }

    public final ArrayList<String> callsToString(List<BtCall> calls) {
        Intrinsics.checkNotNullParameter(calls, "calls");
        ArrayList<String> arrayList = new ArrayList<>();
        int size = calls.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(BtCall.INSTANCE.toJsonString(calls.get(i)));
        }
        return arrayList;
    }
}
