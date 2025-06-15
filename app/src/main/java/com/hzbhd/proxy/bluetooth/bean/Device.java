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

        public final String toJsonString(Device device) {
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
