package com.hzbhd.canbus.car._440;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import org.json.JSONException;
import org.json.JSONObject;


public class UiMgr extends AbstractUiMgr {
    private int[] canBusInfo = new int[14];
    private byte[] canBusInfoByte = new byte[14];

    public UiMgr(Context context) {
    }

    public void registerCanBusAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._440.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m864xc054fed0(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_440-UiMgr, reason: not valid java name */
    /* synthetic */ void m864xc054fed0(String str) {
        if (str == null || str.equals("[]")) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            for (int i = 0; i < jSONObject.length(); i++) {
                this.canBusInfo[i] = ((Integer) jSONObject.get("Data" + i)).intValue();
            }
            sendAirControlCmd(this.canBusInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void sendAirControlCmd(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            this.canBusInfoByte[i] = (byte) iArr[i];
        }
        CanbusMsgSender.sendMsg(this.canBusInfoByte);
    }
}
