package com.hzbhd.canbus.car._133;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (byteArrayToIntArray[1] != 1) {
            return;
        }
        setWheelKeyInfo();
    }

    private void setWheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
        } else if (i == 6) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
        } else {
            if (i != 7) {
                return;
            }
            realKeyLongClick1(this.mContext, 2, iArr[3]);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 5, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 7, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int i8 = i4 + 1;
        byte b2 = (byte) ((i8 >> 8) & 255);
        byte b3 = (byte) (i % 60);
        byte b4 = (byte) (i8 & 255);
        byte b5 = (byte) ((i / 60) % 60);
        boolean z3 = i6 == 1;
        if (b2 > 3) {
            b2 = 3;
        }
        byte b6 = (byte) ((b2 << 6) | b3);
        if (z3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, 3, b4, b6, b5});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 2, 4, b4, b6, b5});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int stereoStatus = FutureUtil.instance.getStereoStatus();
        byte allBandTypeData = (byte) ((i << 3) | getAllBandTypeData(str, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4));
        getFreqByteHiLo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) stereoStatus, allBandTypeData, this.freqHi, this.freqLo});
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 3, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 10, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) ((z ? 128 : 0) + (i & 0x7F)), 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b != 9 ? (byte) 8 : (byte) 9, 0, (byte) i, (byte) ((b7 << 6) | b5), b4});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(new byte[]{22, -124, b != 9 ? (byte) 8 : (byte) 9, 0, (byte) i, (byte) ((b6 << 6) | b5), b4});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{22, -124, 0, 0, 0, 0, 0});
        }
    }
}
