package com.hzbhd.canbus.car._380;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr uiMgr;

    private boolean resolveRadar(int i) {
        return i != 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (byteArrayToIntArray[1] != 17) {
            return;
        }
        setWheelKey0x11();
        setRadarSwitch0x11();
        setBacklight0x11();
        setTrackDate0x11();
        updateSpeedInfo(this.mCanBusInfoInt[3]);
    }

    private void setRadarSwitch0x11() {
        getUiMgr(this.mContext).getParkPageUiSet(this.mContext).setShowRadar(resolveRadar(this.mCanBusInfoInt[6]));
    }

    private void setBacklight0x11() {
        setBacklightLevel((this.mCanBusInfoInt[7] / 20) + 1);
    }

    private void setTrackDate0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setWheelKey0x11() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                RealKeyClick(0);
                break;
            case 1:
                RealKeyClick(7);
                break;
            case 2:
                RealKeyClick(8);
                break;
            case 3:
                RealKeyClick(3);
                break;
            case 4:
                RealKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                RealKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 8:
                RealKeyClick(46);
                break;
            case 9:
                RealKeyClick(45);
                break;
            case 10:
                RealKeyClick(151);
                break;
            case 11:
                RealKeyClick(2);
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        int i3 = 1;
        if (str.equals("FM1")) {
            str4 = str2 + "MHz";
        } else if (str.equals("FM2")) {
            i3 = 2;
            str4 = str2 + "MHz";
        } else if (str.equals("FM3")) {
            i3 = 3;
            str4 = str2 + "MHz";
        } else if (str.equals("AM1")) {
            i3 = 4;
            str4 = str2 + "KHz";
        } else if (str.equals("AM2")) {
            i3 = 5;
            str4 = str2 + "KHz";
        } else {
            str4 = "nothing";
        }
        int length = 24 - str4.length();
        for (int i4 = 0; i4 < length; i4++) {
            str4 = str4 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(i3, str4.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = ((int) b3) + ":" + str2 + ":" + str3;
        int length = 24 - str7.length();
        for (int i4 = 0; i4 < length; i4++) {
            str7 = str7 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(22, str7.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = ((int) b3) + ":" + str3 + ":" + str4;
        int length = 24 - str5.length();
        for (int i5 = 0; i5 < length; i5++) {
            str5 = str5 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(22, str5.getBytes());
    }

    private void RealKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
