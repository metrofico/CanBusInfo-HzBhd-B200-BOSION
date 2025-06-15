package com.hzbhd.canbus.car._444;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import org.json.JSONException;
import org.json.JSONObject;


public class UiMgr extends AbstractUiMgr {
    private static boolean canHand = false;
    private static boolean launchgerHand = false;
    private int[] canBusInfo = new int[14];
    private byte[] canBusInfoByte = new byte[14];
    private HwCarSettings hwCarSettings;

    public UiMgr(Context context) {
    }

    public void registerCanBusAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._444.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m871x93edebd4(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_444-UiMgr, reason: not valid java name */
    /* synthetic */ void m871x93edebd4(String str) {
        if (str == null || str.equals("[]")) {
            return;
        }
        if (this.hwCarSettings == null) {
            this.hwCarSettings = new HwCarSettings();
        }
        this.hwCarSettings.setData(str);
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
        if (iArr[0] == 129) {
            hand("Launcher_OK");
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            this.canBusInfoByte[i] = (byte) iArr[i];
        }
        CanbusMsgSender.sendMsg(this.canBusInfoByte);
    }

    public void hand(String str) {
        if (str.equals("Launcher_OK")) {
            launchgerHand = true;
        }
        if (str.equals("CanBus_OK")) {
            canHand = true;
        }
        if (canHand && launchgerHand) {
            CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
        }
    }
}
