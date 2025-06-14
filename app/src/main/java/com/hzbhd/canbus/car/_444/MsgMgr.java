package com.hzbhd.canbus.car._444;

import android.content.Context;
import android.content.IntentFilter;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._434.speech.STxData;
import com.hzbhd.canbus.car._444.speech.ISysRxListener;
import com.hzbhd.canbus.car._444.speech.STxData;
import com.hzbhd.canbus.car._444.speech.SysRxSpeechReceiver;
import com.hzbhd.canbus.car._444.speech.SysToSpeechReceiver;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static SysToSpeechReceiver.CarCtrl carCtrl;
    private static SysToSpeechReceiver sysToSpeechReceiver;
    private String airJsonStr;
    private int[] mCanBusInfoInt;
    int[] mLightData;
    private UiMgr mUiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(context).registerCanBusAirControlListener();
        registerSanYiVoiceReceiver(context);
        SysToSpeechReceiver sysToSpeechReceiver2 = new SysToSpeechReceiver(context);
        sysToSpeechReceiver = sysToSpeechReceiver2;
        carCtrl = sysToSpeechReceiver2.getCar();
        updateVersionInfo(context, "CANBOX-V1.0.2");
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "REQUEST_LAUNCHER_SWITCH_STATE");
    }

    private void registerSanYiVoiceReceiver(Context context) {
        SysRxSpeechReceiver sysRxSpeechReceiver = new SysRxSpeechReceiver(context, new VoiceControl());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.hzbhd.speech.to.sys");
        context.registerReceiver(sysRxSpeechReceiver, intentFilter);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws JSONException {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (getMsDataType(byteArrayToIntArray) == 419358460) {
            setTime0x18FEE6FC(this.mCanBusInfoInt);
            return;
        }
        ShareBasicInfo(getByteArrayToIntArray(bArr));
        if (isLightDataChange(getByteArrayToIntArray(bArr))) {
            sendLightInfoToSheech(getByteArrayToIntArray(bArr));
        }
    }

    private void setTime0x18FEE6FC(int[] iArr) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("format", "unknown");
            jSONObject.put("year", iArr[12] + 1985);
            jSONObject.put("month", iArr[10]);
            jSONObject.put("day", iArr[11]);
            jSONObject.put("hour", iArr[9]);
            jSONObject.put("minute", iArr[8]);
            jSONObject.put("second", iArr[7]);
            jSONObject.put("millisecond", 0);
            ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CANBUS_TIME, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void ShareBasicInfo(int[] iArr) {
        CanbusInfoChangeListener.getInstance().reportAllCanBusData(intArrayToJsonStr(iArr));
    }

    private String intArrayToJsonStr(int[] iArr) {
        this.airJsonStr = "{";
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + "}";
            } else {
                this.airJsonStr += "\"Data" + i + "\":" + iArr[i] + ",";
            }
        }
        return this.airJsonStr;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isLightDataChange(int[] iArr) {
        if (Arrays.equals(this.mLightData, iArr)) {
            return false;
        }
        this.mLightData = iArr;
        return true;
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    private void sendLightInfoToSheech(int[] iArr) {
        int msDataType = getMsDataType(iArr);
        if (msDataType == 419315745) {
            if (DataHandleUtils.getBoolBit2(iArr[7])) {
                carCtrl.sendAlarmLight(true);
                return;
            } else {
                carCtrl.sendAlarmLight(false);
                return;
            }
        }
        if (msDataType != 419316001) {
            return;
        }
        if (DataHandleUtils.getBoolBit4(iArr[7])) {
            carCtrl.sendHeadlight(true);
        } else {
            carCtrl.sendHeadlight(false);
        }
        if (DataHandleUtils.getBoolBit6(iArr[7])) {
            carCtrl.sendHighbeam(true);
        } else {
            carCtrl.sendHighbeam(false);
        }
    }

    private class VoiceControl implements ISysRxListener {
        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcAir(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcAuto(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcCold(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcDefrost(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcLoop(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcMode(STxData.AcCtrl.Mode.ModeEnum modeEnum) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcTempDec(int i) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcTempInc(int i) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcTempTo(int i) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcWarm(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcWind(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void AcWindTo(int i) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarClearancelamps(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarLock(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarReadinglamp(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarToplight(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarWiper(boolean z) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarWiperCtrl(STxData.CarCtrl.WiperCtrl.ModeEnum modeEnum) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarWiperMove(int i) {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void SystemSync() {
        }

        private VoiceControl() {
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarHeadlight(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -3, -1, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -4, -1, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarHighBeam(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -9, -1, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -13, -1, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarAlarmlight(boolean z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, ByteCompanionObject.MAX_VALUE, -1, -1, -1, -1, -1, -1, -1, 1});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, 63, -1, -1, -1, -1, -1, -1, -1, 1});
            }
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarWindowClose() {
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -5, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -33, -1, -1, -1, -1, -1, -1, 1});
        }

        @Override // com.hzbhd.canbus.car._444.speech.ISysRxListener
        public void CarWindowOpen(STxData.CarCtrl.WindowOpen.TypeEnum typeEnum) {
            if (typeEnum.equals(STxData.CarCtrl.WindowOpen.TypeEnum.leftfront)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -4, -1, -1, -1, -1, -1, -1, 1});
            }
            if (typeEnum.equals(STxData.CarCtrl.WindowOpen.TypeEnum.rightfront)) {
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
                CanbusMsgSender.sendMsg(new byte[]{22, 28, -1, 8, -17, -1, -49, -1, -1, -1, -1, -1, -1, 1});
            }
        }
    }
}
