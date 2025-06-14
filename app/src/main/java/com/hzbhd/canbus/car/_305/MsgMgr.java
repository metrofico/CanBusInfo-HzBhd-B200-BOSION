package com.hzbhd.canbus.car._305;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private boolean isBackOpen;
    private boolean isFrontOpen;
    private boolean isLeftFrontDoorOpen;
    private boolean isLeftRearDoorOpen;
    private boolean isRightFrontDoorOpen;
    private boolean isRightRearDoorOpen;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mRearFrontRadar;
    private boolean mShowFrontRadar;
    private int mCallStatus = 1;
    private String mSongTitle = "";
    private String mSongArtist = "";

    private boolean isRadarShow(int i) {
        return i > 0 && i <= 4;
    }

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
        if (i == 33) {
            setKeyEvent0x21();
            return;
        }
        if (i == 48) {
            setTrack0x30();
            return;
        }
        if (i == 82) {
            if (getCurrentCanDifferentId() == 1) {
                setCarSettingInfo0x52();
            }
        } else {
            if (i != 127) {
                switch (i) {
                    case 38:
                        setRearRadarData0x26(context);
                        break;
                    case 39:
                        setFrontRadarData0x27(context);
                        break;
                    case 40:
                        setCarInfo0x28();
                        break;
                }
                return;
            }
            setVersionInfo0x7f();
        }
    }

    private void setCarSettingInfo0x52() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarInfo0x28() {
        Context context;
        int i;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isOnlyDoorMsgShow()) {
            this.isRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
            this.isLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
            this.isRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
            this.isLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
            this.isBackOpen = GeneralDoorData.isBackOpen;
            this.isFrontOpen = GeneralDoorData.isFrontOpen;
            updateDoorView(this.mContext);
        }
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            context = this.mContext;
            i = R.string.pull_up;
        } else {
            context = this.mContext;
            i = R.string.put_down;
        }
        arrayList.add(new DriverUpdateEntity(0, 0, context.getString(i)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrack0x30() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 9232, 16);
        updateParkUi(null, this.mContext);
    }

    private void setRearRadarData0x26(Context context) {
        boolean z = true;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
        if (!isRadarShow(this.mCanBusInfoInt[2]) && !isRadarShow(this.mCanBusInfoInt[3]) && !isRadarShow(this.mCanBusInfoInt[4]) && !isRadarShow(this.mCanBusInfoInt[5])) {
            z = false;
        }
        this.mRearFrontRadar = z;
        showRadar(context, this.mShowFrontRadar, z);
    }

    private void setFrontRadarData0x27(Context context) {
        boolean z = true;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
        if (!isRadarShow(this.mCanBusInfoInt[2]) && !isRadarShow(this.mCanBusInfoInt[3]) && !isRadarShow(this.mCanBusInfoInt[4]) && !isRadarShow(this.mCanBusInfoInt[5])) {
            z = false;
        }
        this.mShowFrontRadar = z;
        showRadar(context, z, this.mRearFrontRadar);
    }

    private void setKeyEvent0x21() {
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
        }
    }

    private void setVersionInfo0x7f() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private boolean isOnlyDoorMsgShow() {
        return (this.isRightFrontDoorOpen == GeneralDoorData.isRightFrontDoorOpen && this.isLeftFrontDoorOpen == GeneralDoorData.isLeftFrontDoorOpen && this.isRightRearDoorOpen == GeneralDoorData.isRightRearDoorOpen && this.isLeftRearDoorOpen == GeneralDoorData.isLeftRearDoorOpen && this.isBackOpen == GeneralDoorData.isBackOpen && this.isFrontOpen == GeneralDoorData.isFrontOpen) ? false : true;
    }

    private void realKeyClick(int i) {
        realKeyClick1(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        if (isOther()) {
            this.mCallStatus = 2;
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 18, 2, (byte) bArr.length}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        if (isOther()) {
            this.mCallStatus = 1;
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 18, 2, (byte) bArr.length}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        this.mCallStatus = 5;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 18, 2, (byte) bArr.length}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (isOther()) {
            int i4 = this.mCallStatus;
            if (i4 == 1) {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 2, 18, 2, (byte) bArr.length}, bArr));
            } else if (i4 == 2) {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 18, 2, (byte) bArr.length}, bArr));
            } else if (i4 == 5) {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 18, 2, (byte) bArr.length}, bArr));
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        if (isOther()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (isOther()) {
            if (this.mSongTitle.equals(str4) && this.mSongArtist.equals(str6)) {
                return;
            }
            byte[] bytes = str4.getBytes();
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte) bytes.length}, bytes));
            byte[] bytes2 = str6.getBytes();
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 2, (byte) bytes2.length}, bytes2));
            this.mSongTitle = str4;
            this.mSongArtist = str6;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (isOther()) {
            int frepInt = getFrepInt(str, str2);
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, getBandUnit(str), (byte) (frepInt % 256), (byte) (frepInt / 256), (byte) i});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
        this.mSongTitle = "";
        this.mSongArtist = "";
    }

    private int getFrepInt(String str, String str2) throws NumberFormatException {
        double d;
        if (str.contains("FM")) {
            d = Double.parseDouble(str2) * 100.0d;
        } else {
            d = Double.parseDouble(str2);
        }
        return (int) d;
    }

    private byte getBandUnit(String str) {
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

    private boolean isOther() {
        return getCurrentCanDifferentId() != 0;
    }

    private void showRadar(Context context, boolean z, boolean z2) {
        forceReverse(context, z || z2);
    }
}
