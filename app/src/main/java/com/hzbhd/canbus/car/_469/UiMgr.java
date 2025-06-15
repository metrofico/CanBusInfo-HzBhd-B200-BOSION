package com.hzbhd.canbus.car._469;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._469.air.OptionAirCmd469;
import com.hzbhd.canbus.car._469.impl.MsAbstractUiMgr;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.util.LogUtil;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;


public class UiMgr extends MsAbstractUiMgr {
    private SharedPreferences.Editor editor;
    private byte[] lastCmd;
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private int[] canBusInfo = new int[14];
    private int[] calibrationInfoInt = new int[2];
    private byte[] canBusInfoByte = new byte[14];
    private byte[] calibrationInfoByte = new byte[2];

    public UiMgr(Context context) {
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("_s9_prefs", 0);
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public void registerCanBusAirControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CANBUS_MS_AIR_CONTROL_JSON, new IShareStringListener() { // from class: com.hzbhd.canbus.car._469.UiMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m883x6c2f6dd7(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusAirControlListener$0$com-hzbhd-canbus-car-_469-UiMgr, reason: not valid java name */
    /* synthetic */ void m883x6c2f6dd7(String str) {
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

    public void setCmd(byte[] bArr) {
        String strEncodeToString = Base64.encodeToString(bArr, 0);
        if (LogUtil.log5()) {
            LogUtil.d("<set_last_cmd>: " + Arrays.toString(Base64.decode(strEncodeToString, 0)));
        }
        this.editor.putString("last_cmd", strEncodeToString);
        this.editor.apply();
    }

    public void registerCanBusBasicControlListener() {
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CANBUS_MS_BISIC_CONTROL_JSON, new IShareStringListener() { // from class: com.hzbhd.canbus.car._469.UiMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m884xf750d774(str);
            }
        });
        ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_REQUEST_ALL_DATA, new IShareStringListener() { // from class: com.hzbhd.canbus.car._469.UiMgr$$ExternalSyntheticLambda2
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                this.f$0.m885x75289375(str);
            }
        });
    }

    /* renamed from: lambda$registerCanBusBasicControlListener$1$com-hzbhd-canbus-car-_469-UiMgr, reason: not valid java name */
    /* synthetic */ void m884xf750d774(String str) {
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

    /* renamed from: lambda$registerCanBusBasicControlListener$2$com-hzbhd-canbus-car-_469-UiMgr, reason: not valid java name */
    /* synthetic */ void m885x75289375(String str) {
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            checkData(jSONObject.getString("TAG"), jSONObject.getDouble("VALUE"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkData(String str, double d) {
        if (LogUtil.log5()) {
            LogUtil.d("<UiMgr>: event: " + str + " value:" + d);
        }
        str.hashCode();
        switch (str) {
            case "systemOperationStatusControl":
                int i = (int) d;
                OptionAirCmd469.getInstance().sysRunCtrl = i;
                OptionAirCmd469.getInstance().controlOptionCmd();
                this.editor.putInt("sysRunCtrl", i);
                this.editor.apply();
                break;
            case "settingTemperature":
                float f = (float) d;
                OptionAirCmd469.getInstance().setTemp = f;
                this.editor.putFloat("settingTemp", f);
                this.editor.apply();
                OptionAirCmd469.getInstance().controlOptionCmd();
                break;
            case "timeSettingForColdMachineDefrostingProcess":
                int i2 = (int) d;
                OptionAirCmd469.getInstance().defrstTimeSet = i2;
                this.editor.putInt("value4", i2);
                this.editor.apply();
                OptionAirCmd469.getInstance().controlOptionCmd();
                break;
            case "bootCommand":
                OptionAirCmd469.getInstance().longRangeCtrl = (int) d;
                OptionAirCmd469.getInstance().controlOptionCmd();
                break;
            case "coldMachineDefrostingTerminationTemperatureSetting":
                float f2 = (float) d;
                OptionAirCmd469.getInstance().defrstStopTempSet = f2;
                this.editor.putFloat("value2", f2);
                this.editor.apply();
                OptionAirCmd469.getInstance().controlOptionCmd();
                break;
            case "defrostingIntervalControlForColdMachines":
                int i3 = (int) d;
                OptionAirCmd469.getInstance().defrstTimeCtrl = i3;
                OptionAirCmd469.getInstance().controlOptionCmd();
                this.editor.putInt("value1", i3);
                this.editor.apply();
                break;
            case "compressorOperationStatus":
                int i4 = (int) d;
                OptionAirCmd469.getInstance().compRunSt = i4;
                OptionAirCmd469.getInstance().controlOptionCmd();
                this.editor.putInt("compRunSt", i4);
                this.editor.apply();
                break;
            case "startingTemperatureSettingForColdMachineDefrosting":
                float f3 = (float) d;
                OptionAirCmd469.getInstance().defrstStartTempSet = f3;
                this.editor.putFloat("value3", f3);
                this.editor.apply();
                OptionAirCmd469.getInstance().controlOptionCmd();
                break;
            case "temperatureControlReturnDifferenceSetting":
                float f4 = (float) d;
                OptionAirCmd469.getInstance().tempCtrlBckPoorSet = f4;
                this.editor.putFloat("value5", f4);
                this.editor.apply();
                OptionAirCmd469.getInstance().controlOptionCmd();
                break;
        }
        setCmd(OptionAirCmd469.getInstance().airCmd);
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
