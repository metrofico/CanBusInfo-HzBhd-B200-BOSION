package com.hzbhd.canbus.car._443;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._443.impl.MsAbstractUiMgr;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import org.json.JSONException;
import org.json.JSONObject;


public class UiMgr extends MsAbstractUiMgr {
    private int[] canBusInfo = new int[14];
    private int[] calibrationInfoInt = new int[2];
    private byte[] canBusInfoByte = new byte[14];
    private byte[] calibrationInfoByte = new byte[2];

    public UiMgr(Context context) {
    }

    public void registerCanBusAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CANBUS_MS_AIR_CONTROL_JSON, new IShareStringListener() { // from class: com.hzbhd.canbus.car._443.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m867x5f07b093(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_443-UiMgr, reason: not valid java name */
    /* synthetic */ void m867x5f07b093(String str) {
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
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CANBUS_MS_BISIC_CONTROL_JSON, new IShareStringListener() { // from class: com.hzbhd.canbus.car._443.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m868xea291a30(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusBasicControlListener$1$com-hzbhd-canbus-car-_443-UiMgr, reason: not valid java name */
    /* synthetic */ void m868xea291a30(String str) {
        if (str == null || str.equals("[]")) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = 0;
            if (((Integer) jSONObject.get("Data0")).intValue() == 34) {
                while (i < jSONObject.length()) {
                    this.calibrationInfoInt[i] = ((Integer) jSONObject.get("Data" + i)).intValue();
                    i++;
                }
                sendCalibrationControlCmd(this.calibrationInfoInt);
                return;
            }
            while (i < jSONObject.length()) {
                this.canBusInfo[i] = ((Integer) jSONObject.get("Data" + i)).intValue();
                i++;
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

    private void sendCalibrationControlCmd(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            this.calibrationInfoByte[i] = (byte) iArr[i];
        }
        CanbusMsgSender.sendMsg(this.calibrationInfoByte);
    }
}
