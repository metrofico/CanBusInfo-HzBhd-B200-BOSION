package com.hzbhd.canbus.car._459.air;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;
import nfore.android.bt.res.NfDef;


public class OptionAirCmd459 {
    public boolean ac;
    public boolean acMax;
    public byte[] airCmd;
    public boolean auto;
    public boolean cycle;
    public boolean frontDefog;
    private boolean isEnable;
    public boolean power;
    public boolean ptc;
    public double temp;
    public int windLevel;
    public int windMode;

    private static class Holder {
        private static final OptionAirCmd459 INSTANCE = new OptionAirCmd459();

        private Holder() {
        }
    }

    private OptionAirCmd459() {
        this.airCmd = new byte[]{22, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, -11, 112, 40, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        this.auto = false;
        this.frontDefog = false;
        this.ac = false;
        this.power = false;
        this.acMax = false;
        this.ptc = false;
        this.cycle = false;
        this.temp = 18.0d;
        this.windMode = 0;
        this.windLevel = 0;
        this.isEnable = false;
    }

    public static OptionAirCmd459 getInstance() {
        return Holder.INSTANCE;
    }

    public void enableCycle(boolean z) {
        this.isEnable = z;
        controlOptionCmd();
    }

    public synchronized void controlOptionCmd() {
        if (this.isEnable) {
            optionCmdAndSend();
        }
    }

    private void optionCmdAndSend() {
        int i = ((this.auto ? 1 : 0) & 255) | (((this.power ? 1 : 2) & 255) << 2);
        int i2 = (int) (this.temp * 2.0d);
        int msbLsbResult_4bit = DataHandleUtils.getMsbLsbResult_4bit(this.windLevel, this.windMode);
        int i3 = this.ac ? 1 : 2;
        int i4 = this.ptc ? 1 : 2;
        int i5 = i3 & 255;
        int i6 = i5 | ((i4 & 255) << 2) | (((this.cycle ? 1 : 2) & 255) << 4) | (((this.frontDefog ? 1 : 2) & 255) << 6);
        int i7 = this.acMax ? 1 : 2;
        byte[] bArr = this.airCmd;
        bArr[5] = (byte) i;
        bArr[6] = (byte) i2;
        bArr[7] = (byte) msbLsbResult_4bit;
        bArr[8] = (byte) i6;
        bArr[9] = (byte) i7;
        CanbusMsgSender.sendMsg(bArr);
    }
}
