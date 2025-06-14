package com.hzbhd.canbus.car._459.mp5_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UDiskReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws JSONException {
        if ("android.intent.action.MEDIA_MOUNTED".equals(intent.getAction())) {
            sendData("MP5_USBSts", 1);
        } else if (!"android.intent.action.MEDIA_REMOVED".equals(intent.getAction()) && "android.intent.action.MEDIA_UNMOUNTED".equals(intent.getAction())) {
            sendData("MP5_USBSts", 0);
        }
    }

    public void sendData(String str, int i) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("TAG", "MP5_Sts");
            jSONObject.put("KEY", str);
            jSONObject.put("VALUE", i);
            ShareDataManager.getInstance().reportString(ShareConstants.SHARE_REQUEST_ALL_DATA, jSONObject.toString());
        } catch (Exception unused) {
        }
    }
}
