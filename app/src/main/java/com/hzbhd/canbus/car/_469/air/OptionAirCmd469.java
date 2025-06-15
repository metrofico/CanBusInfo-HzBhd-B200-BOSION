package com.hzbhd.canbus.car._469.air;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.util.DataHandleUtils;


public class OptionAirCmd469 {
    public byte[] airCmd;
    public int compRunSt;
    public float defrstStartTempSet;
    public float defrstStopTempSet;
    public int defrstTimeCtrl;
    public int defrstTimeSet;
    public int longRangeCtrl;
    public float setTemp;
    public int sysRunCtrl;
    public float tempCtrlBckPoorSet;

    private static class Holder {
        private static final OptionAirCmd469 INSTANCE = new OptionAirCmd469();

        private Holder() {
        }
    }

    private OptionAirCmd469() {
        this.airCmd = new byte[]{22, 24, -1, -88, -5, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        this.setTemp = 0.0f;
        this.compRunSt = 0;
        this.sysRunCtrl = 0;
        this.defrstTimeCtrl = 0;
        this.defrstStopTempSet = 0.0f;
        this.defrstStartTempSet = 0.0f;
        this.defrstTimeSet = 0;
        this.tempCtrlBckPoorSet = 2.0f;
        this.longRangeCtrl = 0;
    }

    public static OptionAirCmd469 getInstance() {
        return Holder.INSTANCE;
    }

    public synchronized void controlOptionCmd() {
        optionCmdAndSend();
    }

    private void optionCmdAndSend() {
        int i = (int) ((this.setTemp + 30.0f) * 2.0f);
        int intFromByteWithBit = DataHandleUtils.setIntFromByteWithBit(DataHandleUtils.setIntFromByteWithBit(0, this.compRunSt, 0, 2), this.sysRunCtrl, 6, 2);
        int i2 = this.defrstTimeCtrl;
        int i3 = i2 != 0 ? (i2 - 15) / 15 : 0;
        int i4 = (int) ((this.defrstStopTempSet + 30.0f) * 2.0f);
        int i5 = (int) ((this.defrstStartTempSet + 30.0f) * 2.0f);
        int i6 = this.defrstTimeSet - 3;
        int i7 = (int) this.tempCtrlBckPoorSet;
        int i8 = this.longRangeCtrl;
        byte[] bArr = this.airCmd;
        bArr[5] = (byte) i;
        bArr[6] = (byte) intFromByteWithBit;
        bArr[7] = (byte) i3;
        bArr[8] = (byte) i4;
        bArr[9] = (byte) i5;
        bArr[10] = (byte) i6;
        bArr[11] = (byte) i7;
        bArr[12] = (byte) i8;
        CanbusMsgSender.sendMsg(bArr);
    }
}
