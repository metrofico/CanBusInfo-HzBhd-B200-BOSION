package com.hzbhd.canbus.car._459.mp5_state;

import com.hzbhd.canbus.CanbusMsgSender;

/* loaded from: classes2.dex */
public class Mp5StateCmdOption {
    public int data0bit0_bit3;
    public int data0bit4_bit5;
    public int data0bit6_bit7;
    public int data1bit0_bit1;
    public int data1bit2_bit3;
    public int data1bit4_bit5;
    public int data1bit6_bit7;
    public int data2bit0_bit1;
    public int data2bit2_bit3;
    public int data2bit4_bit5;
    public int data2bit6_bit7;
    public int data3bit0_bit1;
    public int data3bit2_bit3;
    public int data3bit4_bit5;
    public int data3bit6_bit7;
    public int data4bit0_bit1;
    public int data4bit2_bit3;
    public int data4bit4_bit5;
    public int data4bit6_bit7;
    public int data5bit0_bit1;
    public int data5bit2_bit3;
    public int data5bit4_bit5;
    public int data5bit6_bit7;
    public int data6;
    public int data7;

    private static class Holder {
        private static final Mp5StateCmdOption INSTANCE = new Mp5StateCmdOption();

        private Holder() {
        }
    }

    private Mp5StateCmdOption() {
        this.data0bit0_bit3 = 0;
        this.data0bit4_bit5 = 3;
        this.data0bit6_bit7 = 3;
        this.data1bit0_bit1 = 3;
        this.data1bit2_bit3 = 3;
        this.data1bit4_bit5 = 3;
        this.data1bit6_bit7 = 3;
        this.data2bit0_bit1 = 3;
        this.data2bit2_bit3 = 3;
        this.data2bit4_bit5 = 2;
        this.data2bit6_bit7 = 2;
        this.data3bit0_bit1 = 0;
        this.data3bit2_bit3 = 0;
        this.data3bit4_bit5 = 0;
        this.data3bit6_bit7 = 3;
        this.data4bit0_bit1 = 3;
        this.data4bit2_bit3 = 3;
        this.data4bit4_bit5 = 3;
        this.data4bit6_bit7 = 3;
        this.data5bit0_bit1 = 3;
        this.data5bit2_bit3 = 3;
        this.data5bit4_bit5 = 0;
        this.data5bit6_bit7 = 0;
        this.data6 = 255;
        this.data7 = 255;
    }

    public static Mp5StateCmdOption getInstance() {
        return Holder.INSTANCE;
    }

    public void send() {
        CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 113, 40, (byte) (((this.data0bit6_bit7 & 255) << 6) | ((this.data0bit4_bit5 & 255) << 4) | (this.data0bit0_bit3 & 255)), (byte) (((this.data1bit6_bit7 & 255) << 6) | ((this.data1bit4_bit5 & 255) << 4) | ((this.data1bit2_bit3 & 255) << 2) | (this.data1bit0_bit1 & 255)), (byte) (((this.data2bit6_bit7 & 255) << 6) | ((this.data2bit4_bit5 & 255) << 4) | ((this.data2bit2_bit3 & 255) << 2) | (this.data2bit0_bit1 & 255)), (byte) (((this.data3bit6_bit7 & 255) << 6) | ((this.data3bit4_bit5 & 255) << 4) | ((this.data3bit2_bit3 & 255) << 2) | (this.data3bit0_bit1 & 255)), (byte) (((this.data4bit6_bit7 & 255) << 6) | ((this.data4bit4_bit5 & 255) << 4) | ((this.data4bit2_bit3 & 255) << 2) | (this.data4bit0_bit1 & 255)), (byte) (((this.data5bit6_bit7 & 255) << 6) | ((this.data5bit4_bit5 & 255) << 4) | ((this.data5bit2_bit3 & 255) << 2) | (this.data5bit0_bit1 & 255)), (byte) this.data6, (byte) this.data7});
    }
}
