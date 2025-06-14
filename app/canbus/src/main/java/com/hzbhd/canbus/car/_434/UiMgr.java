package com.hzbhd.canbus.car._434;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._434.impl.MsAbstractUiMgr;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UiMgr extends MsAbstractUiMgr {
    private int[] canBusInfo = new int[14];
    private byte[] canBusInfoByte = new byte[14];

    public UiMgr(Context context) {
    }

    public void registerCanBusAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CANBUS_MS_AIR_CONTROL_JSON, new IShareStringListener() { // from class: com.hzbhd.canbus.car._434.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m854x2c0cbef5(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_434-UiMgr, reason: not valid java name */
    /* synthetic */ void m854x2c0cbef5(String str) {
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

    public void registerCanBusBasicControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CANBUS_MS_BISIC_CONTROL_JSON, new IShareStringListener() { // from class: com.hzbhd.canbus.car._434.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m855xb72e2892(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusBasicControlListener$1$com-hzbhd-canbus-car-_434-UiMgr, reason: not valid java name */
    /* synthetic */ void m855xb72e2892(String str) {
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
