package com.hzbhd.canbus.car._234;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private boolean isMute = false;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    int[] mDoorData;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            setSwc();
            return;
        }
        if (i == 36) {
            setTrack();
            return;
        }
        if (i == 38) {
            setCarStatus();
            return;
        }
        if (i == 48) {
            setVersionInfo();
        } else if (i == 33) {
            setAir();
        } else {
            if (i != 34) {
                return;
            }
            setCarDoor();
        }
    }

    private void setSwc() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            return;
        }
        if (i == 0) {
            MyRealKeyClick4(0);
            return;
        }
        if (i == 80) {
            MyRealKeyClick4(14);
            return;
        }
        if (i != 81) {
            switch (i) {
                case 18:
                    MyRealKeyClick4(46);
                    break;
                case 19:
                    MyRealKeyClick4(45);
                    break;
                case 20:
                    MyRealKeyClick4(7);
                    break;
                case 21:
                    MyRealKeyClick4(8);
                    break;
                case 22:
                    MyRealKeyClick4(3);
                    break;
                case 23:
                    MyRealKeyClick4(HotKeyConstant.K_SPEECH);
                    break;
            }
            return;
        }
        MyRealKeyClick4(15);
    }

    private void MyRealKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setAir() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.rest = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftRightTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setCarDoor() {
        if (isNotDo0rDataChange()) {
            return;
        }
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private boolean isNotDo0rDataChange() {
        if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mDoorData = this.mCanBusInfoInt;
        return false;
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setTrack() {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[2], (byte) 0, 0, 50, 8);
        updateParkUi(null, this.mContext);
    }

    private void setCarStatus() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[2] * 256) + iArr[3]).append("km/h").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveLeftRightTemp(int i) {
        return i == 0 ? "LO" : i == 255 ? "HI" : (i < 30 || i > 64) ? "--" : (i * 0.5f) + "℃";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.isMute = z;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int i3;
        int msb;
        int lsb;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.equals("FM") || str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            i3 = 16;
            int i4 = (int) (Float.parseFloat(str2) * 100.0f);
            msb = DataHandleUtils.getMsb(i4);
            lsb = DataHandleUtils.getLsb(i4);
        } else if (str.equals("AM") || str.equals("AM1") || str.equals("AM2")) {
            i3 = 32;
            int i5 = Integer.parseInt(str2);
            msb = DataHandleUtils.getMsb(i5);
            lsb = DataHandleUtils.getLsb(i5);
        } else {
            i3 = 0;
            lsb = 0;
            msb = 0;
        }
        byte b = (byte) 255;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) 1, b, (byte) i3, (byte) msb, (byte) lsb, (byte) i, (byte) (this.isMute ? 8 : 0), b, b});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte b10 = (byte) 255;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) 4, b10, b10, (byte) DataHandleUtils.getMsb(i3), (byte) DataHandleUtils.getLsb(i3), b10, (byte) (this.isMute ? 8 : 0), (byte) ((Integer.parseInt(str) * 60) + Integer.parseInt(str2)), (byte) Integer.parseInt(str3)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte b9 = (byte) 255;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) 4, b9, b9, b6, (byte) i, b9, (byte) (this.isMute ? 8 : 0), (byte) ((Integer.parseInt(str2) * 60) + Integer.parseInt(str3)), (byte) Integer.parseInt(str4)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte b = (byte) 255;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) 7, b, b, b, b, b, (byte) (this.isMute ? 8 : 0), b, b});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        byte b = (byte) 255;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) 9, b, b, b, b, b, (byte) (this.isMute ? 12 : 4), b, b});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendPhoneNumber(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        sendPhoneNumber(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        sendPhoneNumber(bArr);
    }

    public void sendPhoneNumber(byte[] bArr) {
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -125, (byte) DataHandleUtils.getMsbLsbResult_4bit(1, bArr.length)}, bArr));
    }
}
