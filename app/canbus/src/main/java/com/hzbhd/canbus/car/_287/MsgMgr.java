package com.hzbhd.canbus.car._287;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x29Data;
    private int[] m0x30Data;
    private int[] m0x40Data;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private boolean mFrontStatus;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int mWheelKeyStatus;
    private int mWheelKeyWhat;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            setWheelKey0x20();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setMediaSwitch0x40();
            return;
        }
        if (i == 34) {
            setRearRadarData0x22();
            return;
        }
        if (i == 35) {
            setFrontRadarData0x23();
        } else if (i == 40) {
            setBaseInfo0x28();
        } else {
            if (i != 41) {
                return;
            }
            setTrackData0x29();
        }
    }

    private void setWheelKey0x20() {
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
        if (i == 3) {
            realKeyClick(46);
            return;
        }
        if (i == 4) {
            realKeyClick(45);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 135) {
            realKeyClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 9) {
            realKeyClick(14);
            return;
        }
        if (i == 10) {
            realKeyClick(HotKeyConstant.K_VOICE_HANGUP);
            return;
        }
        if (i == 21) {
            realKeyClick(50);
            return;
        }
        if (i == 22) {
            realKeyClick(49);
            return;
        }
        switch (i) {
            case 96:
                realKeyClick(45);
                break;
            case 97:
                realKeyClick(45);
                break;
            case 98:
                realKeyClick(48);
                break;
            case 99:
                realKeyClick(46);
                break;
            case 100:
                realKeyClick(46);
                break;
            case 101:
                realKeyClick(46);
                break;
            case 102:
                realKeyClick(47);
                break;
            case 103:
                realKeyClick(45);
                break;
            default:
                switch (i) {
                    case 112:
                        realKeyClick(49);
                        break;
                    case 113:
                        realKeyClick(50);
                        break;
                    case 114:
                        realKeyClick(128);
                        break;
                    case 115:
                        realKeyClick(52);
                        break;
                    case 116:
                        realKeyClick3_1(7);
                        break;
                    case 117:
                        realKeyClick3_1(8);
                        break;
                }
        }
    }

    private void setRearRadarData0x22() {
        if (is0x22DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (is0x23DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setBaseInfo0x28() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        this.mLeftFrontRec = boolBit7;
        GeneralDoorData.isRightFrontDoorOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        this.mRightFrontRec = boolBit6;
        GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        this.mLeftRearRec = boolBit5;
        GeneralDoorData.isRightRearDoorOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        this.mRightRearRec = boolBit4;
        GeneralDoorData.isLeftRearDoorOpen = boolBit4;
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setTrackData0x29() {
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 1712, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setMediaSwitch0x40() {
        try {
            switch (this.mCanBusInfoInt[2]) {
                case 0:
                    enterNoSource();
                    realKeyClick(52);
                    break;
                case 1:
                    changeBandAm1();
                    break;
                case 2:
                    changeBandFm1();
                    break;
                case 3:
                    changeBandFm2();
                    break;
                case 4:
                    realKeyClick(130);
                    break;
                case 5:
                    realKeyClick(59);
                    break;
                case 6:
                    realKeyClick(61);
                    break;
                case 7:
                    realKeyClick(140);
                    break;
                case 8:
                    realKeyClick(141);
                    break;
                case 9:
                    realKeyClick(129);
                    break;
                case 10:
                    realKeyClick(139);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setVersionInfo0x30() {
        if (is0x30DataChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, allBandTypeData, this.freqLo, this.freqHi, (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i7 == 240) {
            sendDiscEjectMsg(this.mContext);
            return;
        }
        if (i7 == 32) {
            int hour = getHour(i);
            int minute = getMinute(i);
            int second = getSecond(i);
            if (i6 == 1 || i6 == 5) {
                i3 = i4;
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, (byte) i3, (byte) i5, (byte) hour, (byte) minute, (byte) second});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z && i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1, 32});
        } else {
            if (z) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 32});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        if (z) {
            i5 = i8;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i6, (byte) i5, z ? (byte) 1 : (byte) 0});
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 17;
            case "AM2":
                return (byte) 18;
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

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.FreqInt = Integer.parseInt(str2);
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x29DataChange() {
        if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x29Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x30DataChange() {
        if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x30Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x40DataChange() {
        if (Arrays.equals(this.m0x40Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x40Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick3_1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        if (i == 0 && i2 == 0) {
            arrayList.add(new SettingUpdateEntity(0, 1, null).setEnable(i3 == 1));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        if (i != 2 && i != 49) {
            return false;
        }
        sendPanoramicDispCommand(context);
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int i) {
        if (!CommUtil.isMiscReverse() || (i != 2 && i != 49)) {
            return this.mDifferent == 1 && i == 2;
        }
        MyLog.temporaryTracking("切换视频");
        sendPanoramicDispCommand(context);
        return true;
    }

    private void sendPanoramicDispCommand(Context context) {
        if (SharePreUtil.getBoolValue(context, "share_287_is_support_panoramic", false)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
        }
    }
}
