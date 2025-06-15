package com.hzbhd.canbus.car._459.mp5_state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;


public class WifiStateReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws JSONException {
        int wifiState = ((WifiManager) context.getSystemService("wifi")).getWifiState();
        if (wifiState == 0) {
            Log.d("WIFI_STATE", "WIFI禁用中");
            return;
        }
        if (wifiState == 1) {
            Log.d("WIFI_STATE", "WIFI状态已禁用");
            sendData("MP5_WIFlSts", 0);
        } else {
            if (wifiState == 2) {
                Log.d("WIFI_STATE", "WIFI启动中");
                return;
            }
            if (wifiState == 3) {
                Log.d("WIFI_STATE", "WIFI已启动");
            } else {
                if (wifiState != 4) {
                    return;
                }
                Log.d("WIFI_STATE", "未知状态");
                sendData("MP5_WIFlSts", 2);
            }
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
