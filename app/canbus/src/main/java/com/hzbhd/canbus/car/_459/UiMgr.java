package com.hzbhd.canbus.car._459;

import android.content.Context;
import com.hzbhd.canbus.car._459.mp5_state.Mp5StateCmdOption;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.util.LogUtil;
import nfore.android.bt.res.NfDef;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class UiMgr extends AbstractUiMgr {
    public byte[] airCmd = {22, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, -11, 112, 40, 0, -2, -32, 0, 0, 0, 0, 0, 1};
    private Context mContext;
    private MsgMgr mMsgMgr;

    public UiMgr(Context context) {
        this.mContext = context;
    }

    public void registerCanBusAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._459.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m877x44e40f8(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_459-UiMgr, reason: not valid java name */
    /* synthetic */ void m877x44e40f8(String str) {
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getString("TAG").equals("MP5_Sts")) {
                checkMp5State(jSONObject.getString("KEY"), Integer.parseInt(jSONObject.getString("VALUE")));
            } else {
                checkData(jSONObject.getString("TAG"), jSONObject.getString("VALUE"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkMp5State(String str, int i) {
        str.hashCode();
        switch (str) {
            case "MP5_VoiceCtrlSts":
                Mp5StateCmdOption.getInstance().data2bit6_bit7 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_RAMSts":
                Mp5StateCmdOption.getInstance().data6 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_ROMSts":
                Mp5StateCmdOption.getInstance().data7 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_Runsts":
                Mp5StateCmdOption.getInstance().data0bit6_bit7 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_USBSts":
                Mp5StateCmdOption.getInstance().data3bit4_bit5 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_DisplaySts":
                Mp5StateCmdOption.getInstance().data0bit0_bit3 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_VideoStsFront":
                Mp5StateCmdOption.getInstance().data1bit2_bit3 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_VideoStsRight":
                Mp5StateCmdOption.getInstance().data2bit0_bit1 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_PhoneConetSts":
                Mp5StateCmdOption.getInstance().data4bit4_bit5 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_VideoStsBack":
                Mp5StateCmdOption.getInstance().data1bit4_bit5 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_VideoStsLeft":
                Mp5StateCmdOption.getInstance().data1bit6_bit7 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_ExitReason":
                Mp5StateCmdOption.getInstance().data0bit4_bit5 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_WIFlSts":
                Mp5StateCmdOption.getInstance().data3bit0_bit1 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_StoreSts":
                Mp5StateCmdOption.getInstance().data1bit0_bit1 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_VidoSts":
                Mp5StateCmdOption.getInstance().data4bit6_bit7 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_BluetoothSts":
                Mp5StateCmdOption.getInstance().data3bit2_bit3 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_NavgtSts":
                Mp5StateCmdOption.getInstance().data4bit2_bit3 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_RunOper":
                Mp5StateCmdOption.getInstance().data2bit2_bit3 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_MediaSts":
                Mp5StateCmdOption.getInstance().data5bit0_bit1 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_RadioAntennaSts":
                Mp5StateCmdOption.getInstance().data4bit0_bit1 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_DispWorkSts":
                Mp5StateCmdOption.getInstance().data3bit6_bit7 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_GPSAntennaSts":
                Mp5StateCmdOption.getInstance().data2bit4_bit5 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
            case "MP5_RadioSts":
                Mp5StateCmdOption.getInstance().data5bit2_bit3 = i;
                Mp5StateCmdOption.getInstance().send();
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkData(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 1709
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._459.UiMgr.checkData(java.lang.String, java.lang.String):void");
    }

    private MsgMgr getMsgMgr(Context context) {
        if (LogUtil.log5()) {
            LogUtil.d("getMsgMgrzoubuzou(): --------");
        }
        if (this.mMsgMgr == null) {
            this.mMsgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.mMsgMgr;
    }
}
