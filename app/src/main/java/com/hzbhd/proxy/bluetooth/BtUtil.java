package com.hzbhd.proxy.bluetooth;

import com.hzbhd.proxy.bluetooth.bean.BtCall;
import com.hzbhd.proxy.bluetooth.bean.Device;

import java.util.ArrayList;
import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


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
