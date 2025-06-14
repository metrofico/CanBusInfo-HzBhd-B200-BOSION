package com.hzbhd.canbus.car._400;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int mDifferentID;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        Log.d("lai", "canbusInfoChange: 123");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mDifferentID = getCurrentCanDifferentId();
        Log.d("lai", "canbusInfoChange: 1234");
        int i = this.mCanBusInfoInt[1];
        if (i == 114) {
            setRadarInfo0x72();
        } else if (i == 115) {
            setAirInfo0x73();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setAirInfo0x73() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
            updateOutDoorTemp(this.mContext, resolveOutTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7)));
        }
    }

    private String resolveOutTemp(int i) {
        return (i - 40) + getTempUnitC(this.mContext);
    }

    private void setRadarInfo0x72() {
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[8], iArr[9], iArr[10], iArr[11]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 0}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, getAllBandTypeData(str)}, 15));
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 4;
            case "AM2":
                return (byte) 5;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) (b == 9 ? 14 : 22)}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        super.cameraInfoChange();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 0 || i6 == 15) {
            return;
        }
        int i8 = 6;
        if (i6 == 1) {
            i8 = 7;
        } else if (i6 == 5) {
            i8 = 24;
        } else if (i6 != 6) {
            i8 = 0;
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, (byte) i8}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -46, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 15));
    }
}
