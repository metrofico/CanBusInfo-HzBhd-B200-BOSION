package com.hzbhd.canbus.car._459.settings;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.mcu.McuConstants;
import com.hzbhd.setting.proxy.SettingsManager;
import com.hzbhd.ui.util.BaseUtil;
import com.hzbhd.util.LogUtil;
import kotlin.jvm.internal.ByteCompanionObject;


public class OptionSettingsCmd459 {
    public String TAG_AWS;
    public String TAG_BL;
    public String TAG_CW;
    public String TAG_DCM;
    public String TAG_ER;
    public String TAG_FL;
    public String TAG_FLWSL;
    public String TAG_LD;
    public String TAG_SA;
    public String TAG_SPS;
    public String TAG_VDM;
    public boolean awsState;
    private int blState;
    public boolean cwState;
    private boolean dcmState;
    private int ecoCmd;
    private boolean erState;
    private boolean flState;
    private boolean flwslState;
    boolean isShoeAWS;
    boolean isShowEBS;
    boolean isShowFCW;
    boolean isShowFHAL;
    boolean isShowHW;
    boolean isShowLDW;
    boolean isShowTSR;
    public boolean ldState;
    public boolean saState;
    private byte[] settingsCmd;
    public boolean spsState;
    public String vdmState;

    private static class Holder {
        private static final OptionSettingsCmd459 INSTANCE = new OptionSettingsCmd459();

        private Holder() {
        }
    }

    private OptionSettingsCmd459() {
        this.isShoeAWS = false;
        this.isShowEBS = false;
        this.isShowLDW = false;
        this.isShowFCW = false;
        this.isShowTSR = false;
        this.isShowHW = false;
        this.isShowFHAL = false;
        this.blState = 160;
        this.dcmState = false;
        this.flState = true;
        this.flwslState = true;
        this.spsState = false;
        this.erState = true;
        this.saState = true;
        this.ldState = true;
        this.awsState = false;
        this.cwState = true;
        this.vdmState = "MID";
        this.ecoCmd = 3;
        this.TAG_BL = "can_settings_switch_bl";
        this.TAG_DCM = "can_settings_switch_dcm";
        this.TAG_FL = "can_settings_switch_fl";
        this.TAG_FLWSL = "can_settings_switch_flwsl";
        this.TAG_SPS = "can_settings_switch_sps";
        this.TAG_ER = "can_settings_switch_er";
        this.TAG_SA = "can_settings_switch_sa";
        this.TAG_LD = "can_settings_switch_ld";
        this.TAG_CW = "can_settings_switch_cw";
        this.TAG_AWS = "can_settings_switch_aws";
        this.TAG_VDM = "can_settings_switch_vdm";
        this.settingsCmd = new byte[]{22, ByteCompanionObject.MAX_VALUE, ByteCompanionObject.MAX_VALUE, ByteCompanionObject.MAX_VALUE, ByteCompanionObject.MAX_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    }

    public static OptionSettingsCmd459 getInstance() {
        return Holder.INSTANCE;
    }

    public void intSaver(String str, int i) {
        SharePreUtil.setIntValue(BaseUtil.INSTANCE.getContext(), str, i);
    }

    public void booleanSaver(String str, Boolean bool) {
        SharePreUtil.setBoolValue(BaseUtil.INSTANCE.getContext(), str, bool.booleanValue());
    }

    public void stringSaver(String str, String str2) {
        SharePreUtil.setStringValue(BaseUtil.INSTANCE.getContext(), str, str2);
    }

    protected int[] getByteArrayToIntArray(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
                iArr[i] = b;
            } else {
                iArr[i] = b & 255;
            }
        }
        return iArr;
    }

    public void initState() {
        byte[] bytes = SettingsManager.getInstance().getBytes(SourceConstantsDef.MODULE_ID.MCU, BodaSysContant.TypeDef.SETTING, McuConstants.SETTING_GET.getMcuDtcMsg.ordinal(), 2);
        int[] byteArrayToIntArray = bytes != null ? getByteArrayToIntArray(bytes) : null;
        this.isShoeAWS = !DataHandleUtils.getBoolBit4(byteArrayToIntArray[7]);
        this.isShowEBS = !DataHandleUtils.getBoolBit5(byteArrayToIntArray[7]);
        this.isShowLDW = !DataHandleUtils.getBoolBit0(byteArrayToIntArray[8]);
        this.isShowFCW = !DataHandleUtils.getBoolBit2(byteArrayToIntArray[8]);
        this.isShowTSR = !DataHandleUtils.getBoolBit0(byteArrayToIntArray[9]);
        this.isShowHW = !DataHandleUtils.getBoolBit5(byteArrayToIntArray[11]);
        this.isShowFHAL = !DataHandleUtils.getBoolBit6(byteArrayToIntArray[11]);
        if (LogUtil.log5()) {
            LogUtil.d("initState(): --" + this.isShoeAWS + "-" + this.isShowEBS + "-" + this.isShowLDW + "-" + this.isShowFCW + "-" + this.isShowTSR + "-" + this.isShowHW + "-" + this.isShowFHAL);
        }
        this.blState = SharePreUtil.getIntValue(BaseUtil.INSTANCE.getContext(), this.TAG_BL, 160);
        this.dcmState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_DCM, false);
        this.flState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_FL, true);
        this.flwslState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_FLWSL, true);
        this.awsState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_AWS, false);
        this.saState = SharePreUtil.getBoolValue(BaseUtil.INSTANCE.getContext(), this.TAG_SA, true);
        this.vdmState = SharePreUtil.getStringValue(BaseUtil.INSTANCE.getContext(), this.TAG_VDM, "MID");
        renewInitializationState();
        optionCmd();
    }

    public void renewInitializationState() {
        if (!this.isShowFHAL) {
            this.flState = false;
            this.flwslState = false;
        }
        if (!this.isShowEBS) {
            this.erState = false;
        }
        if (!this.isShowTSR) {
            this.saState = false;
        }
        if (!this.isShoeAWS) {
            this.awsState = false;
        }
        if (!this.isShowLDW) {
            this.ldState = false;
        }
        if (!this.isShowFCW) {
            this.cwState = false;
        }
        if (this.isShowHW) {
            return;
        }
        this.vdmState = "MID";
    }

    public void setBL(int i) {
        this.blState = i;
        intSaver(this.TAG_BL, i);
        optionCmd();
    }

    public int getBL() {
        return this.blState;
    }

    public void setDCM(boolean z) {
        this.dcmState = z;
        booleanSaver(this.TAG_DCM, Boolean.valueOf(z));
        optionCmd();
    }

    public boolean getDCM() {
        return this.dcmState;
    }

    public void setFL(boolean z) {
        if (this.isShowFHAL) {
            this.flState = z;
        } else {
            this.flState = false;
        }
        booleanSaver(this.TAG_FL, Boolean.valueOf(this.flState));
        optionCmd();
    }

    public void setFLWSL(boolean z) {
        if (this.isShowFHAL) {
            this.flwslState = z;
        } else {
            this.flwslState = false;
        }
        booleanSaver(this.TAG_FLWSL, Boolean.valueOf(this.flwslState));
        optionCmd();
    }

    public boolean getFL() {
        if (this.isShowFHAL) {
            return this.flState;
        }
        return false;
    }

    public boolean getFLWSL() {
        if (this.isShowFHAL) {
            return this.flwslState;
        }
        return false;
    }

    public void setSPS(boolean z) {
        this.spsState = z;
        optionCmd();
    }

    public boolean getSPS() {
        return this.spsState;
    }

    public void setER(boolean z) {
        if (this.isShowEBS) {
            this.erState = z;
        } else {
            this.erState = false;
        }
        optionCmd();
    }

    public boolean getER() {
        if (this.isShowEBS) {
            return this.erState;
        }
        return false;
    }

    public void setSA(boolean z) {
        if (this.isShowTSR) {
            this.saState = z;
        } else {
            this.saState = false;
        }
        optionCmd();
    }

    public boolean getSA() {
        if (this.isShowTSR) {
            return this.saState;
        }
        return false;
    }

    public void setAWS(boolean z) {
        if (LogUtil.log5()) {
            LogUtil.d("setAutomaticWiperSystem(): ----" + z);
        }
        if (this.isShoeAWS) {
            this.awsState = z;
        } else {
            this.awsState = false;
        }
        optionCmd();
    }

    public boolean getAWS() {
        if (this.isShoeAWS) {
            return this.awsState;
        }
        return false;
    }

    public void setLD(Boolean bool) {
        if (this.isShowLDW) {
            this.ldState = bool.booleanValue();
        } else {
            this.ldState = false;
        }
        optionCmd();
    }

    public boolean getLD() {
        if (this.isShowLDW) {
            return this.ldState;
        }
        return false;
    }

    public void setCW(Boolean bool) {
        if (this.isShowFCW) {
            this.cwState = bool.booleanValue();
        } else {
            this.cwState = false;
        }
        optionCmd();
    }

    public boolean getCW() {
        if (this.isShowFCW) {
            return this.cwState;
        }
        return false;
    }

    public void setVDM(String str) {
        if (this.isShowHW) {
            this.vdmState = str;
        } else {
            this.vdmState = "MID";
        }
        stringSaver(this.TAG_VDM, this.vdmState);
        optionCmd();
    }

    public String getVDM() {
        return this.isShowHW ? this.vdmState : "MID";
    }

    public void setEcoRequestCmd(int i) {
        this.ecoCmd = i;
        optionCmd();
        this.ecoCmd = 3;
    }

    public void optionCmd() {
        int i = this.blState;
        boolean z = this.dcmState;
        boolean z2 = this.flState;
        boolean z3 = this.spsState;
        int i2 = !this.erState ? 1 : 0;
        boolean z4 = this.saState;
        boolean z5 = this.ldState;
        boolean z6 = this.cwState;
        boolean z7 = this.flwslState;
        if (LogUtil.log5()) {
            LogUtil.d("optionCmd(): -----------" + (z3 ? 1 : 0));
        }
        int i3 = z ? 1 : 0;
        int i4 = z2 ? 1 : 0;
        int i5 = z3 ? 1 : 0;
        int i6 = z4 ? 1 : 0;
        int decimalFrom8Bit = DataHandleUtils.getDecimalFrom8Bit(z7 ? 1 : 0, z6 ? 1 : 0, z5 ? 1 : 0, i6, i2, i5, i4, i3);
        int i7 = 0;
        if (!this.vdmState.equals("FAR")) {
            if (this.vdmState.equals("MID")) {
                i7 = 1;
            } else if (this.vdmState.equals("NEAR")) {
                i7 = 2;
            }
        }
        int i8 = this.ecoCmd;
        boolean z8 = this.awsState;
        if (LogUtil.log5()) {
            LogUtil.d("setAutomaticWiperSystem()bit: ----" + (z ? 1 : 0) + "--" + (z2 ? 1 : 0) + "--" + (z3 ? 1 : 0) + "--" + i2 + "--" + (z4 ? 1 : 0) + "--" + (z5 ? 1 : 0) + "--" + (z6 ? 1 : 0) + "--" + (z7 ? 1 : 0) + "--" + i7 + "---" + (z8 ? 1 : 0));
        }
        byte[] bArr = this.settingsCmd;
        bArr[5] = (byte) i;
        bArr[6] = (byte) decimalFrom8Bit;
        bArr[7] = (byte) i7;
        bArr[8] = (byte) i8;
        bArr[9] = z8 ? (byte) 1 : (byte) 0;
        sender();
    }

    private void sender() {
        CanbusMsgSender.sendMsg(this.settingsCmd);
    }
}
