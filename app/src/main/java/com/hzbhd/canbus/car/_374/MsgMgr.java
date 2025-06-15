package com.hzbhd.canbus.car._374;

import android.content.Context;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
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
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getRadarData(int i) {
        if (i == 1) {
            return 10;
        }
        if (i == 2) {
            return 7;
        }
        if (i != 3) {
            return i != 4 ? 0 : 1;
        }
        return 3;
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
        if (i == 16) {
            set0x10TimeInfo();
            return;
        }
        if (i == 37) {
            set0x25AirInfo();
            return;
        }
        if (i == 39) {
            set0x27AuxInfo();
            return;
        }
        if (i != 48) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    set0x21WhellKeyInfo();
                    break;
                case 34:
                    set0X22RearRadarINfo();
                    break;
                case 35:
                    set0x23FromtRadarInfo();
                    break;
            }
            return;
        }
        set0x30VersionINfo();
    }

    private void set0x30VersionINfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x27AuxInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_374_car_aux"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_374_car_aux", "_374_car_aux"), getAuxSourceState(this.mCanBusInfoInt[2])).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private Object getAuxSourceState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._374_car_aux0);
        }
        return this.mContext.getString(R.string._374_car_aux1);
    }

    private void set0x25AirInfo() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        if (!GeneralAirData.front_left_blow_window && !GeneralAirData.front_left_blow_head && !GeneralAirData.front_left_blow_foot) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 5);
        if (intFromByteWithBit > 18) {
            GeneralAirData.front_auto_wind_speed = true;
        } else {
            GeneralAirData.front_auto_wind_speed = false;
            GeneralAirData.front_wind_level = intFromByteWithBit;
        }
        int i = this.mCanBusInfoInt[4];
        if (i == 254) {
            GeneralAirData.front_left_temperature = "HI";
        } else if (i == 0) {
            GeneralAirData.front_left_temperature = "LO";
        } else if (i > 28) {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[4] + getTempUnitF(this.mContext);
        } else {
            GeneralAirData.front_left_temperature = (((this.mCanBusInfoInt[4] - 1) * 0.5d) + 16.0d) + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 254) {
            GeneralAirData.front_right_temperature = "HI";
        } else if (i2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
        } else if (i2 > 28) {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[5] + getTempUnitF(this.mContext);
        } else {
            GeneralAirData.front_right_temperature = (((this.mCanBusInfoInt[5] - 1) * 0.5d) + 16.0d) + getTempUnitC(this.mContext);
        }
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x23FromtRadarInfo() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(10, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0X22RearRadarINfo() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(10, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x21WhellKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i != 128) {
            switch (i) {
                case 0:
                    buttonKey(0);
                    break;
                case 1:
                    buttonKey(33);
                    break;
                case 2:
                    buttonKey(34);
                    break;
                case 3:
                    buttonKey(35);
                    break;
                case 4:
                    buttonKey(36);
                    break;
                case 5:
                    buttonKey(37);
                    break;
                case 6:
                    buttonKey(38);
                    break;
                case 7:
                    buttonKey(39);
                    break;
                case 8:
                    buttonKey(40);
                    break;
                case 9:
                    buttonKey(41);
                    break;
                case 10:
                    buttonKey(42);
                    break;
                case 11:
                    buttonKey(12);
                    break;
                case 12:
                    buttonKey(67);
                    break;
                default:
                    switch (i) {
                        case 16:
                            buttonKey(HotKeyConstant.K_ACTION_RADIO);
                            break;
                        case 17:
                            buttonKey(2);
                            break;
                        case 18:
                            buttonKey(14);
                            break;
                        case 19:
                            buttonKey(94);
                            break;
                        case 20:
                            buttonKey(47);
                            break;
                        case 21:
                            buttonKey(48);
                            break;
                        case 22:
                            buttonKey(151);
                            break;
                        case 23:
                            buttonKey(50);
                            break;
                        case 24:
                            buttonKey(1);
                            break;
                        case 25:
                            buttonKey(30);
                            break;
                        case 26:
                            buttonKey(128);
                            break;
                        case 27:
                            buttonKey(6);
                            break;
                        case 28:
                            knobKey(8);
                            break;
                        case 29:
                            knobKey(7);
                            break;
                        case 30:
                            knobKey(46);
                            break;
                        case 31:
                            knobKey(45);
                            break;
                        case 32:
                            forceReverse(this.mContext, false);
                            break;
                        case 33:
                            buttonKey(45);
                            break;
                        case 34:
                            buttonKey(46);
                            break;
                        case 35:
                            buttonKey(49);
                            break;
                        case 36:
                            buttonKey(151);
                            break;
                    }
            }
        }
        buttonKey(141);
    }

    private void set0x20WheelKeyInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(20);
                break;
            case 4:
                buttonKey(21);
                break;
            case 5:
                buttonKey(14);
                break;
            case 6:
                buttonKey(3);
                break;
            case 7:
                buttonKey(2);
                break;
            case 8:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                buttonKey(14);
                break;
            case 10:
                buttonKey(15);
                break;
            case 11:
                buttonKey(49);
                break;
            case 12:
                buttonKey(50);
                break;
            case 13:
                knobKey(7);
                break;
            case 14:
                knobKey(8);
                break;
        }
    }

    private void set0x10TimeInfo() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] * 256) + iArr[3];
        if (i == 4095) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_374_car_time"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_374_car_time", "_374_car_time"), "invalid").setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_374_car_time"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_374_car_time", "_374_car_time"), i + "/" + this.mCanBusInfoInt[4] + "/" + this.mCanBusInfoInt[5] + "  " + this.mCanBusInfoInt[6] + ":" + this.mCanBusInfoInt[7]).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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
        realKeyClick3(this.mContext, i, 0, this.mCanBusInfoInt[3]);
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

    private void adjustBrightness() {
        int brightness = FutureUtil.instance.getBrightness();
        if (brightness == 5) {
            FutureUtil.instance.setBrightness(0);
        } else {
            FutureUtil.instance.setBrightness(brightness + 1);
        }
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
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
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
}
