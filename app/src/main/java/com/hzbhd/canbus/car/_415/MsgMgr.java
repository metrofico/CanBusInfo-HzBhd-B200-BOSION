package com.hzbhd.canbus.car._415;

import android.content.Context;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
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
    int[] mDoorData;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int nowValue = -1;

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
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).send0xCBTimeInfo(i5, i6, z ? 1 : 0);
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
        int length = 12 - str4.length();
        for (int i4 = 0; i4 < length; i4++) {
            str4 = str4 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(i3, str4.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = ((int) b3) + str2 + str3;
        int length = 12 - str7.length();
        for (int i4 = 0; i4 < length; i4++) {
            str7 = str7 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(22, str7.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = ((int) b3) + str3 + str4;
        int length = 12 - str5.length();
        for (int i5 = 0; i5 < length; i5++) {
            str5 = str5 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(22, str5.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        String str = "Aux";
        for (int i = 0; i < 9; i++) {
            str = str + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(12, str.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        String str = "BT music";
        for (int i = 0; i < 4; i++) {
            str = str + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(21, str.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setSwc0x11();
            setTrack0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 33) {
            setPanelKeyInfo0x21();
            return;
        }
        if (i == 34) {
            setPanelKnob0x22();
            return;
        }
        if (i == 49) {
            setAir0x31();
            return;
        }
        if (i == 52) {
            set0x34Info();
            return;
        }
        if (i == 65) {
            set0x41RadarInfo();
            return;
        }
        if (i == 240) {
            setVersion0xF0();
        } else if (i == 102) {
            setDoorSetting0x66();
        } else {
            if (i != 103) {
                return;
            }
            setLight0x67();
        }
    }

    private void setVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setLight0x67() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting2", "_415_setting3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting2", "_415_setting4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorSetting0x66() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting", "_415_setting0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_415_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_415_setting", "_415_setting1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x41RadarInfo() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x34Info() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data0");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df_2Decimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format((((iArr[7] & 255) << 8) | ((iArr[6] & 255) << 16) | (this.mCanBusInfoByte[8] & 255)) * 0.1d)).append("KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAir0x31() {
        updateOutDoorTemp(this.mContext, this.df_2Decimal.format((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        this.mCanBusInfoInt[13] = 0;
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_foot = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[8], 239, 255);
        updateAirActivity(this.mContext, 1004);
    }

    private void setPanelKnob0x22() {
        int i = this.nowValue;
        if (i == -1) {
            this.nowValue = this.mCanBusInfoInt[3];
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (iArr[3] < i) {
                realKeyClick4(this.mContext, 8);
            } else {
                realKeyClick4(this.mContext, 7);
            }
        }
        this.nowValue = this.mCanBusInfoInt[3];
    }

    private void setPanelKeyInfo0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            knobKey(0);
            return;
        }
        if (i == 1) {
            knobKey(1);
            return;
        }
        if (i == 2) {
            knobKey(21);
            return;
        }
        if (i == 3) {
            knobKey(20);
            return;
        }
        if (i == 36) {
            knobKey(2);
            return;
        }
        if (i == 37) {
            knobKey(27);
            return;
        }
        if (i == 66) {
            knobKey(4);
            return;
        }
        if (i == 67) {
            knobKey(30);
            return;
        }
        if (i == 69) {
            knobKey(7);
            return;
        }
        if (i == 70) {
            knobKey(8);
            return;
        }
        if (i == 77) {
            knobKey(45);
            return;
        }
        if (i != 78) {
            switch (i) {
                case 6:
                    knobKey(50);
                    break;
                case 9:
                    knobKey(3);
                    break;
                case 16:
                    knobKey(17);
                    break;
                case 19:
                    knobKey(39);
                    break;
                case 22:
                    knobKey(40);
                    break;
                case 32:
                    knobKey(128);
                    break;
                case 40:
                    knobKey(14);
                    break;
                case 42:
                    knobKey(49);
                    break;
                case 47:
                    knobKey(151);
                    break;
                case 51:
                    knobKey(62);
                    break;
                case 55:
                    knobKey(58);
                    break;
                case 57:
                    knobKey(HotKeyConstant.K_SLEEP);
                    break;
                case 75:
                    knobKey(62);
                    break;
                case 94:
                    knobKey(75);
                    break;
                default:
                    switch (i) {
                        case 60:
                            knobKey(29);
                            break;
                        case 61:
                            knobKey(139);
                            break;
                        case 62:
                            knobKey(68);
                            break;
                    }
            }
            return;
        }
        knobKey(46);
    }

    private void setDoorInfo0x12() {
        if (isNotDoor0x12DataChange()) {
            return;
        }
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setTrack0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setSwc0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 4) {
            buttonKey(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
        } else if (i == 8) {
            buttonKey(45);
        } else {
            if (i != 9) {
                return;
            }
            buttonKey(46);
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    public void knobKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
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

    private String getTemperature(int i, int i2, int i3) {
        return i == i2 ? "LO" : i == i3 ? "HI" : this.df_2Decimal.format(i * 0.5d) + getTempUnitC(this.mContext);
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

    private boolean isNotAirDataChange() {
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

    private boolean isNotDoor0x12DataChange() {
        if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mDoorData = this.mCanBusInfoInt;
        return false;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }
}
