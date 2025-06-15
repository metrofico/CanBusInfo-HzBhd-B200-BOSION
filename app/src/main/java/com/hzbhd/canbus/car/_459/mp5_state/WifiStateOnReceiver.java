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
