package com.hzbhd.canbus.car._121;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private final String TAG = "_1121_MsgMgr";
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        new TimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._121.MsgMgr.1
            int time = 0;
            int track = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr.this.discInfoChange((byte) 0, this.time, 0, 0, this.track, 0, 0, true, true, 0, "", "", "");
                this.time += 3600;
                this.track++;
            }
        }, 0L, 1000L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            set0x20CarBase(context);
        } else {
            if (i != 240) {
                return;
            }
            set0xf0VersionInfo();
        }
    }

    private void set0x20CarBase(Context context) {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyClick2(this.mContext, 0);
                break;
            case 1:
                realKeyClick2(this.mContext, 7);
                break;
            case 2:
                realKeyClick2(this.mContext, 8);
                break;
            case 3:
                realKeyClick2(this.mContext, 3);
                break;
            case 5:
                realKeyClick2(this.mContext, 14);
                break;
            case 6:
                realKeyClick2(this.mContext, 15);
                break;
            case 8:
                realKeyClick2(this.mContext, 45);
                break;
            case 9:
                realKeyClick2(this.mContext, 46);
                break;
            case 10:
                realKeyClick2(this.mContext, 2);
                break;
        }
    }

    private void realKeyClick2(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[5]);
    }

    private void set0xf0VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            for (int length = str2.length(); length < 5; length++) {
                str2 = " " + str2;
            }
            str4 = str2 + "KHZ";
        } else {
            for (int length2 = str2.length(); length2 < 6; length2++) {
                str2 = " " + str2;
            }
            str4 = str2 + "MHZ";
        }
        if (i >= 1 && i <= 6) {
            str4 = str4 + i;
        }
        for (int length3 = str4.length(); length3 < 12; length3++) {
            str4 = str4 + " ";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, allBandTypeData}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 0 || i6 == 15) {
            return;
        }
        byte bRangeNumber = (byte) DataHandleUtils.rangeNumber(i / 3600, 9);
        byte b2 = (byte) ((i / 60) % 60);
        byte b3 = (byte) (i % 60);
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        String str4 = String.format("%1d%02d%02d%04d   ", Byte.valueOf(bRangeNumber), Byte.valueOf(b2), Byte.valueOf(b3), Integer.valueOf(i3));
        int i8 = 6;
        if (i6 == 1) {
            i8 = 7;
        } else if (i6 == 5) {
            i8 = 24;
        } else if (i6 != 6) {
            i8 = 0;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) i8}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, String.format("     %04d   ", Integer.valueOf((b7 << 8) | i)).getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 22)}, String.format("     %04d   ", Integer.valueOf((b6 << 8) | i)).getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
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
}
