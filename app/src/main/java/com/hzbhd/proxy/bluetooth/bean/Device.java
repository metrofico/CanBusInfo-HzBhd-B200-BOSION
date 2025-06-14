package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Device.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0002$%BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0002\u0010\rJ\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0012\"\u0004\b\u0015\u0010\u0014R\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0012\"\u0004\b\u0019\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011¨\u0006&"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/Device;", "", "address", "", LcdInfoShare.MediaInfo.name, "isPaired", "", "isConn", "isHfpConn", "isPairIng", "isConnHfping", "isConnA2dping", "isA2dpConn", "(Ljava/lang/String;Ljava/lang/String;ZZZZZZZ)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "()Z", "setA2dpConn", "(Z)V", "setConn", "setConnA2dping", "setConnHfping", "setHfpConn", "setPairIng", "setPaired", "isVisible", "setVisible", "getName", "setName", "equals", "other", "hashCode", "", "toString", "Companion", "DEVICE_NAME", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class Device {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String address;
    private boolean isA2dpConn;
    private boolean isConn;
    private boolean isConnA2dping;
    private boolean isConnHfping;
    private boolean isHfpConn;
    private boolean isPairIng;
    private boolean isPaired;
    private boolean isVisible;
    private String name;

    /* compiled from: Device.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/Device$DEVICE_NAME;", "", "(Ljava/lang/String;I)V", "ADDRESS", "NAME", "PAIRED", "HFP_CONNED", "A2DP_CONNED", "CONNED", "CURR_HFP", "CONN_A2DP_ING", "CONN_HFP_ING", "PAIRING", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private enum DEVICE_NAME {
        ADDRESS,
        NAME,
        PAIRED,
        HFP_CONNED,
        A2DP_CONNED,
        CONNED,
        CURR_HFP,
        CONN_A2DP_ING,
        CONN_HFP_ING,
        PAIRING
    }

    public Device(String address, String name, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(name, "name");
        this.address = address;
        this.name = name;
        this.isPaired = z;
        this.isConn = z2;
        this.isHfpConn = z3;
        this.isPairIng = z4;
        this.isConnHfping = z5;
        this.isConnA2dping = z6;
        this.isA2dpConn = z7;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.address = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    /* renamed from: isPaired, reason: from getter */
    public final boolean getIsPaired() {
        return this.isPaired;
    }

    public final void setPaired(boolean z) {
        this.isPaired = z;
    }

    /* renamed from: isConn, reason: from getter */
    public final boolean getIsConn() {
        return this.isConn;
    }

    public final void setConn(boolean z) {
        this.isConn = z;
    }

    /* renamed from: isHfpConn, reason: from getter */
    public final boolean getIsHfpConn() {
        return this.isHfpConn;
    }

    public final void setHfpConn(boolean z) {
        this.isHfpConn = z;
    }

    /* renamed from: isPairIng, reason: from getter */
    public final boolean getIsPairIng() {
        return this.isPairIng;
    }

    public final void setPairIng(boolean z) {
        this.isPairIng = z;
    }

    /* renamed from: isConnHfping, reason: from getter */
    public final boolean getIsConnHfping() {
        return this.isConnHfping;
    }

    public final void setConnHfping(boolean z) {
        this.isConnHfping = z;
    }

    /* renamed from: isConnA2dping, reason: from getter */
    public final boolean getIsConnA2dping() {
        return this.isConnA2dping;
    }

    public final void setConnA2dping(boolean z) {
        this.isConnA2dping = z;
    }

    /* renamed from: isA2dpConn, reason: from getter */
    public final boolean getIsA2dpConn() {
        return this.isA2dpConn;
    }

    public final void setA2dpConn(boolean z) {
        this.isA2dpConn = z;
    }

    /* renamed from: isVisible, reason: from getter */
    public final boolean getIsVisible() {
        return this.isVisible;
    }

    public final void setVisible(boolean z) {
        this.isVisible = z;
    }

    public String toString() {
        return "Device{address='" + this.address + "', name='" + this.name + "', isPaired=" + this.isPaired + ", isHfpConn=" + this.isHfpConn + ", isA2dpConn=" + this.isA2dpConn + '}';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.hzbhd.proxy.bluetooth.bean.Device");
        Device device = (Device) other;
        return Intrinsics.areEqual(this.address, device.address) && Intrinsics.areEqual(this.name, device.name) && this.isPaired == device.isPaired && this.isConn == device.isConn && this.isHfpConn == device.isHfpConn && this.isPairIng == device.isPairIng && this.isConnHfping == device.isConnHfping && this.isConnA2dping == device.isConnA2dping && this.isA2dpConn == device.isA2dpConn && this.isVisible == device.isVisible;
    }

    public int hashCode() {
        return (((((((((((((((((this.address.hashCode() * 31) + this.name.hashCode()) * 31) + Boolean.hashCode(this.isPaired)) * 31) + Boolean.hashCode(this.isConn)) * 31) + Boolean.hashCode(this.isHfpConn)) * 31) + Boolean.hashCode(this.isPairIng)) * 31) + Boolean.hashCode(this.isConnHfping)) * 31) + Boolean.hashCode(this.isConnA2dping)) * 31) + Boolean.hashCode(this.isA2dpConn)) * 31) + Boolean.hashCode(this.isVisible);
    }

    /* compiled from: Device.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/Device$Companion;", "", "()V", "fromJson", "Lcom/hzbhd/proxy/bluetooth/bean/Device;", "deviceString", "", "toJsonString", SyncAction.KEYBOARD_DEVICE, "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Device fromJson(String deviceString) {
            try {
                if (TextUtils.isEmpty(deviceString)) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject(deviceString);
                String strOptString = jSONObject.optString("ADDRESS");
                String strOptString2 = jSONObject.optString("NAME");
                boolean zOptBoolean = jSONObject.optBoolean("PAIRED");
                boolean zOptBoolean2 = jSONObject.optBoolean("CONNED");
                boolean zOptBoolean3 = jSONObject.optBoolean("PAIRING");
                boolean zOptBoolean4 = jSONObject.optBoolean("CONN_HFP_ING");
                boolean zOptBoolean5 = jSONObject.optBoolean("CONN_A2DP_ING");
                boolean zOptBoolean6 = jSONObject.optBoolean("HFP_CONNED");
                boolean zOptBoolean7 = jSONObject.optBoolean("A2DP_CONNED");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(DEVICE_NAME.ADDRESS.name)");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(DEVICE_NAME.NAME.name)");
                return new Device(strOptString, strOptString2, zOptBoolean, zOptBoolean2, zOptBoolean6, zOptBoolean3, zOptBoolean4, zOptBoolean5, zOptBoolean7);
            } catch (Exception unused) {
                Log.d("Device", "fromJson: error:" + deviceString);
                return null;
            }
        }

        public final String toJsonString(Device device) throws JSONException {
            Intrinsics.checkNotNullParameter(device, "device");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ADDRESS", device.getAddress());
                jSONObject.put("NAME", device.getName());
                jSONObject.put("PAIRED", device.getIsPaired());
                jSONObject.put("CONNED", device.getIsConn());
                jSONObject.put("HFP_CONNED", device.getIsHfpConn());
                jSONObject.put("A2DP_CONNED", device.getIsA2dpConn());
                jSONObject.put("PAIRING", device.getIsPairIng());
                jSONObject.put("CONN_A2DP_ING", device.getIsConnA2dping());
                jSONObject.put("CONN_HFP_ING", device.getIsConnHfping());
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "jsonObject.toString()");
                return string;
            } catch (Exception unused) {
                Log.d("Device", "toJsonString: error");
                return "";
            }
        }
    }
}
