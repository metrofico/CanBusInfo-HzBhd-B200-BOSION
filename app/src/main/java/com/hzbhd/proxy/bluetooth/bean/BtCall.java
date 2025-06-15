package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.hzbhd.constant.bluetooth.BtConstants;
import com.hzbhd.proxy.bluetooth.manager.BtManager;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import org.json.JSONException;
import org.json.JSONObject;


public final class BtCall {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final BtCall INSTANCE = new BtCall();
    private long callingTime;
    private boolean isCurrCallDevice;
    private long ringTime;
    private BtConstants.CallState callState = BtConstants.CallState.NORMAL;
    private String callNum = "";
    private String callName = "";
    private String callDeviceAddress = "";
    private String callDeviceName = "";

    public enum CALL_NAME {
        CALL_STATE,
        CALL_NUM,
        CALL_NAME,
        RING_TIME,
        CALLING_TIME,
        CALL_DEVICE_ADDRRESS,
        CALL_DEVICE_NAME,
        IS_CURR_CALL_DEVICE
    }

    public final BtConstants.CallState getCallState() {
        return this.callState;
    }

    public final void setCallState(BtConstants.CallState callState) {

        this.callState = callState;
    }

    public final String getCallNum() {
        return this.callNum;
    }

    public final void setCallNum(String str) {

        this.callNum = str;
    }

    public final String getCallName() {
        return this.callName;
    }

    public final void setCallName(String str) {

        this.callName = str;
    }

    public final long getRingTime() {
        return this.ringTime;
    }

    public final void setRingTime(long j) {
        this.ringTime = j;
    }

    public final long getCallingTime() {
        return this.callingTime;
    }

    public final void setCallingTime(long j) {
        this.callingTime = j;
    }

    public final String getCallDeviceAddress() {
        return this.callDeviceAddress;
    }

    public final void setCallDeviceAddress(String str) {

        this.callDeviceAddress = str;
    }

    public final String getCallDeviceName() {
        return this.callDeviceName;
    }

    public final void setCallDeviceName(String str) {

        this.callDeviceName = str;
    }

    /* renamed from: isCurrCallDevice, reason: from getter */
    public final boolean getIsCurrCallDevice() {
        return this.isCurrCallDevice;
    }

    public final void setCurrCallDevice(boolean z) {
        this.isCurrCallDevice = z;
    }

    public String toString() {
        return "BtCall{mCallState=" + this.callState + ", callNum='" + this.callNum + "', callName='" + this.callName + "', ringTime=" + this.ringTime + ", callingTime=" + this.callingTime + ", callDeviceAddress='" + this.callDeviceAddress + "', callDeviceName='" + this.callDeviceName + "', isCurrCallDevice=" + this.isCurrCallDevice + '}';
    }

    public final BtCall fromJson(String callString) {
        try {
            if (TextUtils.isEmpty(callString)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(callString);
            BtCall btCall = new BtCall();
            String strOptString = jSONObject.optString("CALL_STATE");

            btCall.setCallState(BtConstants.CallState.valueOf(strOptString));
            String strOptString2 = jSONObject.optString("CALL_NUM");

            btCall.setCallNum(strOptString2);
            String strOptString3 = jSONObject.optString("CALL_NAME");

            btCall.setCallName(strOptString3);
            btCall.setRingTime(jSONObject.optLong("RING_TIME"));
            btCall.setCallingTime(jSONObject.optLong("CALLING_TIME"));
            String strOptString4 = jSONObject.optString("CALL_DEVICE_ADDRRESS");

            btCall.setCallDeviceAddress(strOptString4);
            String strOptString5 = jSONObject.optString("CALL_DEVICE_NAME");

            btCall.setCallDeviceName(strOptString5);
            btCall.setCurrCallDevice(jSONObject.optBoolean("IS_CURR_CALL_DEVICE"));
            return btCall;
        } catch (Exception unused) {
            Log.d("Call", "fromJson: error:" + callString);
            return null;
        }
    }

    public final String toJsonString(BtCall call) {

        try {
            JSONObject jSONObject = new JSONObject();
            BtConstants.CallState callState = call.getCallState();

            jSONObject.put("CALL_STATE", callState.name());
            jSONObject.put("CALL_NUM", call.getCallNum());
            jSONObject.put("CALL_NAME", call.getCallName());
            jSONObject.put("RING_TIME", call.getRingTime());
            jSONObject.put("CALLING_TIME", call.getCallingTime());
            jSONObject.put("CALL_DEVICE_ADDRRESS", call.getCallDeviceAddress());
            jSONObject.put("CALL_DEVICE_NAME", call.getCallDeviceName());
            jSONObject.put("IS_CURR_CALL_DEVICE", call.getIsCurrCallDevice());
            String string = jSONObject.toString();

            return string;
        } catch (Exception unused) {
            Log.d("Call", "toJsonString: error");
            return "";
        }
    }
}
