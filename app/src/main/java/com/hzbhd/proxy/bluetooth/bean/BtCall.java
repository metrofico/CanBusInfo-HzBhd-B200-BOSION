package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.hzbhd.constant.bluetooth.BtConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BtCall.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 (2\u00020\u0001:\u0002'(B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001d¨\u0006)"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "", "()V", "callDeviceAddress", "", "getCallDeviceAddress", "()Ljava/lang/String;", "setCallDeviceAddress", "(Ljava/lang/String;)V", "callDeviceName", "getCallDeviceName", "setCallDeviceName", "callName", "getCallName", "setCallName", "callNum", "getCallNum", "setCallNum", "callState", "Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;", "getCallState", "()Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;", "setCallState", "(Lcom/hzbhd/constant/bluetooth/BtConstants$CallState;)V", "callingTime", "", "getCallingTime", "()J", "setCallingTime", "(J)V", "isCurrCallDevice", "", "()Z", "setCurrCallDevice", "(Z)V", "ringTime", "getRingTime", "setRingTime", "toString", "CALL_NAME", "Companion", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class BtCall {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long callingTime;
    private boolean isCurrCallDevice;
    private long ringTime;
    private BtConstants.CallState callState = BtConstants.CallState.NORMAL;
    private String callNum = "";
    private String callName = "";
    private String callDeviceAddress = "";
    private String callDeviceName = "";

    /* compiled from: BtCall.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtCall$CALL_NAME;", "", "(Ljava/lang/String;I)V", "CALL_STATE", "CALL_NUM", "CALL_NAME", "RING_TIME", "CALLING_TIME", "CALL_DEVICE_ADDRRESS", "CALL_DEVICE_NAME", "IS_CURR_CALL_DEVICE", "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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
        Intrinsics.checkNotNullParameter(callState, "<set-?>");
        this.callState = callState;
    }

    public final String getCallNum() {
        return this.callNum;
    }

    public final void setCallNum(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.callNum = str;
    }

    public final String getCallName() {
        return this.callName;
    }

    public final void setCallName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
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
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.callDeviceAddress = str;
    }

    public final String getCallDeviceName() {
        return this.callDeviceName;
    }

    public final void setCallDeviceName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
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

    /* compiled from: BtCall.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/hzbhd/proxy/bluetooth/bean/BtCall$Companion;", "", "()V", "fromJson", "Lcom/hzbhd/proxy/bluetooth/bean/BtCall;", "callString", "", "toJsonString", NotificationCompat.CATEGORY_CALL, "bt-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BtCall fromJson(String callString) {
            try {
                if (TextUtils.isEmpty(callString)) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject(callString);
                BtCall btCall = new BtCall();
                String strOptString = jSONObject.optString("CALL_STATE");
                Intrinsics.checkNotNullExpressionValue(strOptString, "jsonObject.optString(CALL_NAME.CALL_STATE.name)");
                btCall.setCallState(BtConstants.CallState.valueOf(strOptString));
                String strOptString2 = jSONObject.optString("CALL_NUM");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "jsonObject.optString(CALL_NAME.CALL_NUM.name)");
                btCall.setCallNum(strOptString2);
                String strOptString3 = jSONObject.optString("CALL_NAME");
                Intrinsics.checkNotNullExpressionValue(strOptString3, "jsonObject.optString(CALL_NAME.CALL_NAME.name)");
                btCall.setCallName(strOptString3);
                btCall.setRingTime(jSONObject.optLong("RING_TIME"));
                btCall.setCallingTime(jSONObject.optLong("CALLING_TIME"));
                String strOptString4 = jSONObject.optString("CALL_DEVICE_ADDRRESS");
                Intrinsics.checkNotNullExpressionValue(strOptString4, "jsonObject.optString(CAL…ALL_DEVICE_ADDRRESS.name)");
                btCall.setCallDeviceAddress(strOptString4);
                String strOptString5 = jSONObject.optString("CALL_DEVICE_NAME");
                Intrinsics.checkNotNullExpressionValue(strOptString5, "jsonObject.optString(CAL…ME.CALL_DEVICE_NAME.name)");
                btCall.setCallDeviceName(strOptString5);
                btCall.setCurrCallDevice(jSONObject.optBoolean("IS_CURR_CALL_DEVICE"));
                return btCall;
            } catch (Exception unused) {
                Log.d("Call", "fromJson: error:" + callString);
                return null;
            }
        }

        public final String toJsonString(BtCall call) throws JSONException {
            Intrinsics.checkNotNullParameter(call, "call");
            try {
                JSONObject jSONObject = new JSONObject();
                BtConstants.CallState callState = call.getCallState();
                Intrinsics.checkNotNull(callState);
                jSONObject.put("CALL_STATE", callState.name());
                jSONObject.put("CALL_NUM", call.getCallNum());
                jSONObject.put("CALL_NAME", call.getCallName());
                jSONObject.put("RING_TIME", call.getRingTime());
                jSONObject.put("CALLING_TIME", call.getCallingTime());
                jSONObject.put("CALL_DEVICE_ADDRRESS", call.getCallDeviceAddress());
                jSONObject.put("CALL_DEVICE_NAME", call.getCallDeviceName());
                jSONObject.put("IS_CURR_CALL_DEVICE", call.getIsCurrCallDevice());
                String string = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(string, "jsonObject.toString()");
                return string;
            } catch (Exception unused) {
                Log.d("Call", "toJsonString: error");
                return "";
            }
        }
    }
}
