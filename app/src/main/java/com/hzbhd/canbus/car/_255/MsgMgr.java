package com.hzbhd.canbus.car._255;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;


public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private boolean isid12;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mIsHfpConnected;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        boolean z = true;
        if (getCurrentCanDifferentId() != 1 && getCurrentCanDifferentId() != 2) {
            z = false;
        }
        this.isid12 = z;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            realKeyControl();
            return;
        }
        if (i == 48) {
            setTrackInfo();
            return;
        }
        if (i == 82) {
            setSettingData0x52();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 38:
                    setRearRadarInfo();
                    break;
                case 39:
                    setFrontRadarInfo();
                    break;
                case 40:
                    if (!isDoorMsgRepeat(bArr)) {
                        setDoorData0x28();
                        break;
                    }
                    break;
            }
            return;
        }
        setVersionInfo();
    }

    private void realKeyClick(int i) {
        realKeyClick1(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    private void realKeyControl() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        switch (i) {
            case 9:
                realKeyClick(14);
                break;
            case 10:
                realKeyClick(15);
                break;
            case 11:
                realKeyClick(45);
                break;
            case 12:
                realKeyClick(46);
                break;
            case 13:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 14:
                realKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                break;
        }
    }

    private void setSettingData0x52() {
        int i = this.mCanBusInfoInt[3] - 1;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoByte[3]);
        updateDoorView(this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (this.isid12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        byte b;
        super.radioInfoChange(i, str, str2, str3, i2);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 2092:
                if (str.equals("AM")) {
                    c = 0;
                    break;
                }
                break;
            case 2247:
                if (str.equals("FM")) {
                    c = 1;
                    break;
                }
                break;
            case 64901:
                if (str.equals("AM1")) {
                    c = 2;
                    break;
                }
                break;
            case 64902:
                if (str.equals("AM2")) {
                    c = 3;
                    break;
                }
                break;
            case 64903:
                if (str.equals("AM3")) {
                    c = 4;
                    break;
                }
                break;
            case 69706:
                if (str.equals("FM1")) {
                    c = 5;
                    break;
                }
                break;
            case 69707:
                if (str.equals("FM2")) {
                    c = 6;
                    break;
                }
                break;
            case 69708:
                if (str.equals("FM3")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                b = 16;
                break;
            case 1:
                b = 0;
                break;
            case 2:
                b = 17;
                break;
            case 3:
                b = 18;
                break;
            case 4:
                b = 19;
                break;
            case 5:
            default:
                b = 1;
                break;
            case 6:
                b = 2;
                break;
            case 7:
                b = 3;
                break;
        }
        getFreqByteHiLo(str, str2);
        byte[] bArr = {22, -64, 1, b, this.freqLo, this.freqHi, (byte) i};
        if (this.isid12) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), bArr);
        }
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, i != 1 ? i != 2 ? i != 4 ? (byte) 0 : (byte) 4 : (byte) 3 : (byte) 1, 18, 2, 18}, bArr);
        if (this.isid12) {
            CanbusMsgSender.sendMsg(bArrByteMerger);
        }
        if (this.mIsHfpConnected != z) {
            this.mIsHfpConnected = z;
            byte[] bArrByteMerger2 = DataHandleUtils.byteMerger(new byte[]{22, -64, 5, z ? (byte) -127 : ByteCompanionObject.MIN_VALUE, 18, 2, 18}, bArr);
            if (this.isid12) {
                CanbusMsgSender.sendMsg(bArrByteMerger2);
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) throws UnsupportedEncodingException {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        byte[] bytes = new byte[0];
        try {
            bytes = str4.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte) bytes.length}, bytes);
        if (this.isid12) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), bArrByteMerger);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = "VEDIO " + ((b6 * 256) + i) + "/" + i2 + " " + str2 + ":" + str3 + ":" + str4;
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte) str5.length()}, DataHandleUtils.stringGetBytes(str5, "UTF-8"));
        if (this.isid12) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), bArrByteMerger);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        if (this.isid12) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 18, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        if (this.isid12) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7});
        }
    }

    private void setRearRadarInfo() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 9232, 16);
        updateParkUi(null, this.mContext);
    }
}
