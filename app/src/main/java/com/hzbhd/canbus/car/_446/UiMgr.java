package com.hzbhd.canbus.car._446;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
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
    private byte[] canBusInfoByte = new byte[13];
    Context mContext;
    private MsgMgr mMsgMgr;

    public UiMgr(Context context) {
        this.mContext = context;
    }

    public void registerCanBusAirControlListener() {
        Log.d("fxHouShare", "Register Air Control");
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._446.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m874xfdba6256(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_446-UiMgr, reason: not valid java name */
    /* synthetic */ void m874xfdba6256(String str) {
        Log.d("fxHouShare", "CanBusGotAirControl=" + str);
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
        if (iArr[0] == 129 && iArr[1] == 129 && iArr[2] == 129 && iArr[3] == 129) {
            hand("SystemUi_OK");
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            try {
                this.canBusInfoByte[i] = (byte) iArr[i];
            } catch (Exception e) {
                Log.d("Exception", "error：" + e.toString());
            }
        }
        CanbusMsgSender.sendMsg(SplicingByte(this.canBusInfoByte, new byte[]{1}));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public void hand(String str) {
        if (str.equals("SystemUi_OK")) {
            launchgerHand = true;
            getMsgMgr(this.mContext).ShareCanInfo(new int[]{85, 85, 128, 128, 128, 128, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        if (str.equals("CanBus_OK")) {
            canHand = true;
        }
        if (canHand && launchgerHand) {
            CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
        }
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
