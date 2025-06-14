package com.hzbhd.canbus.car._459.mp5_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: WifiStateOnReceiver.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/WifiStateOnReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "sendData", "key", "", "value", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class WifiStateOnReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws JSONException {
        String action = intent != null ? intent.getAction() : null;
        if (LogUtil.log5()) {
            LogUtil.d("onReceive(): -----------");
        }
        if (Intrinsics.areEqual("android.net.wifi.STATE_CHANGE", action)) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if ((networkInfo != null ? networkInfo.getState() : null) == NetworkInfo.State.CONNECTED) {
                sendData("MP5_WIFlSts", 1);
            } else {
                sendData("MP5_WIFlSts", 0);
            }
        }
    }

    public final void sendData(String key, int value) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("TAG", "MP5_Sts");
            jSONObject.put("KEY", key);
            jSONObject.put("VALUE", value);
            ShareDataManager.getInstance().reportString(ShareConstants.SHARE_REQUEST_ALL_DATA, jSONObject.toString());
        } catch (Exception unused) {
        }
    }
}
