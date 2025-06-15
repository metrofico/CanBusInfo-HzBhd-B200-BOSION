package com.hzbhd.canbus.car._390;

import android.content.Context;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private int nowState;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
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
        if (i == 129) {
            set0x81BasicInfo();
            return;
        }
        if (i != 240) {
            switch (i) {
                case 131:
                    set0x83Idrive();
                    break;
                case 132:
                    set0x84MediaState();
                    break;
                case 133:
                    set0x85TimeInfo();
                    break;
            }
            return;
        }
        set0xF0VersionInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            getUiMgr(this.mContext).send0xE1Media(1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).send0xE1Media(6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0xE1Media(12);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        getUiMgr(this.mContext).send0xE1Media(0);
    }

    private void set0xF0VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x85TimeInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_390_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_390_drive2"), getTimeStr()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getTimeStr() {
        int[] iArr = this.mCanBusInfoInt;
        String str = iArr[9] == 0 ? "(12H)" : "(24H)";
        int i = iArr[8];
        if (i == 0) {
            StringBuilder sbAppend = new StringBuilder().append(this.mCanBusInfoInt[5]).append(".").append(this.mCanBusInfoInt[4]).append(".");
            int[] iArr2 = this.mCanBusInfoInt;
            return sbAppend.append((iArr2[2] * 256) + iArr2[3]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(str).toString();
        }
        if (i == 1) {
            StringBuilder sbAppend2 = new StringBuilder().append(this.mCanBusInfoInt[4]).append("/").append(this.mCanBusInfoInt[5]).append("/");
            int[] iArr3 = this.mCanBusInfoInt;
            return sbAppend2.append((iArr3[2] * 256) + iArr3[3]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(str).toString();
        }
        if (i == 2) {
            StringBuilder sb = new StringBuilder();
            int[] iArr4 = this.mCanBusInfoInt;
            return sb.append((iArr4[2] * 256) + iArr4[3]).append(".").append(this.mCanBusInfoInt[4]).append(".").append(this.mCanBusInfoInt[5]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(str).toString();
        }
        StringBuilder sb2 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        return sb2.append((iArr5[2] * 256) + iArr5[3]).append("/").append(this.mCanBusInfoInt[4]).append("/").append(this.mCanBusInfoInt[5]).append("     ").append(this.mCanBusInfoInt[6]).append(":").append(this.mCanBusInfoInt[7]).append(str).toString();
    }

    private void set0x84MediaState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_390_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_390_drive1"), getMediaType()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getMediaType() {
        int i = this.mCanBusInfoInt[2];
        return i != 0 ? i != 1 ? i != 6 ? i != 10 ? i != 12 ? i != 13 ? CanTypeMsg.OTHER : "USB" : "AUX" : "PHONE" : "CD" : "FM" : "OFF";
    }

    private void set0x83Idrive() {
        set0x83WheelKeyInfo();
        set0x83Knob();
    }

    private void set0x83Knob() {
        int i = this.nowState;
        int i2 = this.mCanBusInfoInt[3];
        if (i < i2) {
            realKeyClick4(this.mContext, 7);
        } else if (i > i2) {
            realKeyClick4(this.mContext, 8);
        }
        this.nowState = this.mCanBusInfoInt[3];
    }

    private void set0x83WheelKeyInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick4(this.mContext, 0);
                break;
            case 1:
                realKeyClick4(this.mContext, 53);
                break;
            case 2:
                realKeyClick4(this.mContext, 151);
                break;
            case 3:
                realKeyClick4(this.mContext, 14);
                break;
            case 4:
                realKeyClick4(this.mContext, 50);
                break;
            case 5:
                realKeyClick4(this.mContext, 58);
                break;
            case 6:
                realKeyClick4(this.mContext, 47);
                break;
            case 7:
                realKeyClick4(this.mContext, 48);
                break;
            case 8:
                realKeyClick4(this.mContext, 49);
                break;
        }
    }

    private void set0x81BasicInfo() {
        set0x81WheelKeyInfo();
        set0x81TrackInfo();
        set0x81RadarInfo();
    }

    private void set0x81RadarInfo() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[8], iArr[9], iArr[10], iArr[11]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x81TrackInfo() {
        int i = this.mCanBusInfoInt[6];
        if (i != 0 && i != 255) {
            GeneralParkData.trackAngle = (-i) / 5;
        } else {
            GeneralParkData.trackAngle = i / 5;
        }
    }

    private void set0x81WheelKeyInfo() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick4(this.mContext, 0);
        }
        if (i == 1) {
            realKeyClick4(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyClick4(this.mContext, 8);
            return;
        }
        if (i == 4) {
            realKeyClick4(this.mContext, HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            realKeyClick4(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
            return;
        }
        switch (i) {
            case 8:
                realKeyClick4(this.mContext, 45);
                break;
            case 9:
                realKeyClick4(this.mContext, 46);
                break;
            case 10:
                realKeyClick4(this.mContext, 2);
                break;
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String tenToSixTeen(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    private int sixteenToTen(String str) {
        return Integer.parseInt(("0x" + str).replaceAll("^0[x|X]", ""), 16);
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }
}
