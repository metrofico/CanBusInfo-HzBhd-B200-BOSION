package com.hzbhd.proxy.bluetooth.bean;

import android.text.TextUtils;
import android.util.Log;

import com.hzbhd.constant.bluetooth.BtConstants;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

import org.json.JSONException;
import org.json.JSONObject;


public final class BtStatus {
    private static final String ADDRESS = "address";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String address = "";
    private boolean isA2dpConn;
    private boolean isA2dpPlaying;
    private boolean isCalling;
    private boolean isConning;
    private boolean isEnable;
    private boolean isHfpConn;
    private boolean isInA2dpSource;
    private boolean isInComing;
    private boolean isInHfpSource;
    private boolean isOutGoing;
    private boolean isPairing;
    private boolean isPbSyncFinish;
    private boolean isPbSyncing;
    private boolean isScanning;

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.address = str;
    }

    /* renamed from: isEnable, reason: from getter */
    public final boolean getIsEnable() {
        return this.isEnable;
    }

    public final void setEnable(boolean z) {
        this.isEnable = z;
    }

    /* renamed from: isHfpConn, reason: from getter */
    public final boolean getIsHfpConn() {
        return this.isHfpConn;
    }

    public final void setHfpConn(boolean z) {
        this.isHfpConn = z;
    }

    /* renamed from: isA2dpConn, reason: from getter */
    public final boolean getIsA2dpConn() {
        return this.isA2dpConn;
    }

    public final void setA2dpConn(boolean z) {
        this.isA2dpConn = z;
    }

    /* renamed from: isScanning, reason: from getter */
    public final boolean getIsScanning() {
        return this.isScanning;
    }

    public final void setScanning(boolean z) {
        this.isScanning = z;
    }

    /* renamed from: isPairing, reason: from getter */
    public final boolean getIsPairing() {
        return this.isPairing;
    }

    public final void setPairing(boolean z) {
        this.isPairing = z;
    }

    /* renamed from: isConning, reason: from getter */
    public final boolean getIsConning() {
        return this.isConning;
    }

    public final void setConning(boolean z) {
        this.isConning = z;
    }

    /* renamed from: isInHfpSource, reason: from getter */
    public final boolean getIsInHfpSource() {
        return this.isInHfpSource;
    }

    public final void setInHfpSource(boolean z) {
        this.isInHfpSource = z;
    }

    /* renamed from: isInA2dpSource, reason: from getter */
    public final boolean getIsInA2dpSource() {
        return this.isInA2dpSource;
    }

    public final void setInA2dpSource(boolean z) {
        this.isInA2dpSource = z;
    }

    /* renamed from: isA2dpPlaying, reason: from getter */
    public final boolean getIsA2dpPlaying() {
        return this.isA2dpPlaying;
    }

    public final void setA2dpPlaying(boolean z) {
        this.isA2dpPlaying = z;
    }

    /* renamed from: isCalling, reason: from getter */
    public final boolean getIsCalling() {
        return this.isCalling;
    }

    public final void setCalling(boolean z) {
        this.isCalling = z;
    }

    /* renamed from: isInComing, reason: from getter */
    public final boolean getIsInComing() {
        return this.isInComing;
    }

    public final void setInComing(boolean z) {
        this.isInComing = z;
    }

    /* renamed from: isOutGoing, reason: from getter */
    public final boolean getIsOutGoing() {
        return this.isOutGoing;
    }

    public final void setOutGoing(boolean z) {
        this.isOutGoing = z;
    }

    /* renamed from: isPbSyncing, reason: from getter */
    public final boolean getIsPbSyncing() {
        return this.isPbSyncing;
    }

    public final void setPbSyncing(boolean z) {
        this.isPbSyncing = z;
    }

    /* renamed from: isPbSyncFinish, reason: from getter */
    public final boolean getIsPbSyncFinish() {
        return this.isPbSyncFinish;
    }

    public final void setPbSyncFinish(boolean z) {
        this.isPbSyncFinish = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BtStatus{address=").append(this.address).append("isHfpConn=").append(this.isHfpConn).append(", isA2dpConn=").append(this.isA2dpConn).append(", isScanning=").append(this.isScanning).append(", isPairing=").append(this.isPairing).append(", isConning=").append(this.isConning).append(", isInHfpSource=").append(this.isInHfpSource).append(", isInA2dpSource=").append(this.isInA2dpSource).append(", isA2dpPlaying=").append(this.isA2dpPlaying).append(", isCalling=").append(this.isCalling).append(", isInComing=").append(this.isInComing).append(", isOutGoing=");
        sb.append(this.isOutGoing).append(", isPbSyncing=").append(this.isPbSyncing).append(", isPbSyncFinish=").append(this.isPbSyncFinish).append('}');
        return sb.toString();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BtStatus fromJson(String statusString) {
            try {
                if (TextUtils.isEmpty(statusString)) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject(statusString);
                BtStatus btStatus = new BtStatus();
                String strOptString = jSONObject.optString(BtStatus.ADDRESS);
                Intrinsics.checkNotNullExpressionValue(strOptString, "jsonObject.optString(ADDRESS)");
                btStatus.setAddress(strOptString);
                btStatus.setEnable(jSONObject.optBoolean(BtConstants.BT_STATUS.ENABLE.name()));
                btStatus.setHfpConn(jSONObject.optBoolean(BtConstants.BT_STATUS.HFP_CONN.name()));
                btStatus.setA2dpConn(jSONObject.optBoolean(BtConstants.BT_STATUS.A2DP_CONN.name()));
                btStatus.setScanning(jSONObject.optBoolean(BtConstants.BT_STATUS.SCANNING.name()));
                btStatus.setPairing(jSONObject.optBoolean(BtConstants.BT_STATUS.PAIRING.name()));
                btStatus.setConning(jSONObject.optBoolean(BtConstants.BT_STATUS.CONNING.name()));
                btStatus.setInHfpSource(jSONObject.optBoolean(BtConstants.BT_STATUS.IN_HFP.name()));
                btStatus.setInA2dpSource(jSONObject.optBoolean(BtConstants.BT_STATUS.IN_A2DP.name()));
                btStatus.setA2dpPlaying(jSONObject.optBoolean(BtConstants.BT_STATUS.A2DP_PLAYING.name()));
                btStatus.setCalling(jSONObject.optBoolean(BtConstants.BT_STATUS.CALLING.name()));
                btStatus.setInComing(jSONObject.optBoolean(BtConstants.BT_STATUS.INCOMING.name()));
                btStatus.setOutGoing(jSONObject.optBoolean(BtConstants.BT_STATUS.OUTGOING.name()));
                btStatus.setPbSyncing(jSONObject.optBoolean(BtConstants.BT_STATUS.SYNC_PB_ING.name()));
                btStatus.setPbSyncFinish(jSONObject.optBoolean(BtConstants.BT_STATUS.SYNC_PB_FINISH.name()));
                return btStatus;
            } catch (Exception unused) {
                Log.d("Device", "fromJson: error:" + statusString);
                return null;
            }
        }

        public final String toJsonString(BtStatus btStatus) throws JSONException {
            Intrinsics.checkNotNullParameter(btStatus, "btStatus");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(BtStatus.ADDRESS, btStatus.getAddress());
                jSONObject.put(BtConstants.BT_STATUS.ENABLE.name(), btStatus.getIsEnable());
                jSONObject.put(BtConstants.BT_STATUS.HFP_CONN.name(), btStatus.getIsHfpConn());
                jSONObject.put(BtConstants.BT_STATUS.A2DP_CONN.name(), btStatus.getIsA2dpConn());
                jSONObject.put(BtConstants.BT_STATUS.SCANNING.name(), btStatus.getIsScanning());
                jSONObject.put(BtConstants.BT_STATUS.PAIRING.name(), btStatus.getIsPairing());
                jSONObject.put(BtConstants.BT_STATUS.CONNING.name(), btStatus.getIsConning());
                jSONObject.put(BtConstants.BT_STATUS.IN_HFP.name(), btStatus.getIsInHfpSource());
                jSONObject.put(BtConstants.BT_STATUS.IN_A2DP.name(), btStatus.getIsInA2dpSource());
                jSONObject.put(BtConstants.BT_STATUS.A2DP_PLAYING.name(), btStatus.getIsA2dpPlaying());
                jSONObject.put(BtConstants.BT_STATUS.CALLING.name(), btStatus.getIsCalling());
                jSONObject.put(BtConstants.BT_STATUS.INCOMING.name(), btStatus.getIsInComing());
                jSONObject.put(BtConstants.BT_STATUS.OUTGOING.name(), btStatus.getIsOutGoing());
                jSONObject.put(BtConstants.BT_STATUS.SYNC_PB_ING.name(), btStatus.getIsPbSyncing());
                jSONObject.put(BtConstants.BT_STATUS.SYNC_PB_FINISH.name(), btStatus.getIsPbSyncFinish());
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
