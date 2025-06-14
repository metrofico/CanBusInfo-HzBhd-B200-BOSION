package com.hzbhd.canbus.car._437;

import android.content.Context;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
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
        getUiMgr(this.mContext).makeConnect();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 3) {
            set0x03CarInfo();
            return;
        }
        if (i == 18) {
            set0x12AuxState();
            return;
        }
        if (i == 41) {
            set0x29EspInfo();
            return;
        }
        if (i != 48) {
            switch (i) {
                case 32:
                    set0x20SWC();
                    break;
                case 33:
                    set0x21AirInfo();
                    break;
                case 34:
                    set0x22RearRadar();
                    break;
                case 35:
                    set0x23FrontRadar();
                    break;
                case 36:
                    set0x24BasicInfo();
                    break;
            }
            return;
        }
        set0x30VersionInfo();
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x03CarInfo() {
        if (isBasicInfoChange()) {
            updateOutDoorTemp(this.mContext, getOutTemperature());
            DecimalFormat decimalFormat = this.df_2Integer;
            int[] iArr = this.mCanBusInfoInt;
            updateSpeedInfo(Integer.parseInt(decimalFormat.format(getMsbLsbResult(iArr[6], iArr[7]) * 0.1d)));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_1"), getOutTemperature()));
            int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0");
            int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_2");
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr2[4], iArr2[5])).append("KM").toString()));
            int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0");
            int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_3");
            StringBuilder sb2 = new StringBuilder();
            DecimalFormat decimalFormat2 = this.df_2Decimal;
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(decimalFormat2.format(getMsbLsbResult(iArr3[6], iArr3[7]) * 0.1d)).append("KM/H").toString()));
            int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_437_function_0");
            int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_437_function_4");
            StringBuilder sb3 = new StringBuilder();
            DecimalFormat decimalFormat3 = this.df_2Decimal;
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(decimalFormat3.format(getMsbLsbResult(iArr4[8], iArr4[9]) * 0.1d)).append("L").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x12AuxState() {
        if (this.mCanBusInfoInt[2] == 1) {
            CommUtil.requestAudioChannel(SourceConstantsDef.SOURCE_ID.ORIGAUX, SourceConstantsDef.DISP_ID.disp_main, null);
            getUiMgr(this.mContext).issueAux(true);
        } else {
            CommUtil.releaseAudioChannel(SourceConstantsDef.SOURCE_ID.ORIGAUX, SourceConstantsDef.DISP_ID.disp_main);
            getUiMgr(this.mContext).issueAux(false);
        }
    }

    private void set0x29EspInfo() {
        if (isTrackInfoChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5400, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x24BasicInfo() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = 0;
        if (DataHandleUtils.getBoolBit0(iArr[2]) && !isDoorDataNoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x23FrontRadar() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22RearRadar() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x21AirInfo() {
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        int i = this.mCanBusInfoInt[5];
        if (i == 0) {
            GeneralAirData.front_left_temperature = "LOW";
        } else if (i == 31) {
            GeneralAirData.front_left_temperature = "HI";
        } else {
            GeneralAirData.front_left_temperature = ((this.mCanBusInfoInt[5] * 0.5d) + 15.5d) + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[6];
        if (i2 == 0) {
            GeneralAirData.front_right_temperature = "LOW";
        } else if (i2 == 31) {
            GeneralAirData.front_right_temperature = "HI";
        } else {
            GeneralAirData.front_right_temperature = ((this.mCanBusInfoInt[6] * 0.5d) + 15.5d) + getTempUnitC(this.mContext);
        }
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x20SWC() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        if (i == 2) {
            return;
        }
        int i2 = iArr[2];
        if (i2 != 70) {
            switch (i2) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, i);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, i);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, i);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 46, i);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 45, i);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, i);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, i);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, i);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, i);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 128, i);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 33, i);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 34, i);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 35, i);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 36, i);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 37, i);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 38, i);
                    break;
                case 16:
                    realKeyLongClick1(this.mContext, 39, i);
                    break;
                case 17:
                    realKeyLongClick1(this.mContext, 40, i);
                    break;
                default:
                    switch (i2) {
                        case 41:
                            if (i != 0) {
                                realKeyClick4(this.mContext, 47);
                                break;
                            }
                            break;
                        case 42:
                            if (i != 0) {
                                realKeyClick4(this.mContext, 48);
                                break;
                            }
                            break;
                        case 43:
                            realKeyLongClick1(this.mContext, 21, i);
                            break;
                        case 44:
                            realKeyLongClick1(this.mContext, 20, i);
                            break;
                        case 45:
                            realKeyLongClick1(this.mContext, 45, i);
                            break;
                        case 46:
                            realKeyLongClick1(this.mContext, 46, i);
                            break;
                        case 47:
                            realKeyLongClick1(this.mContext, 49, i);
                            break;
                        case 48:
                            realKeyLongClick1(this.mContext, 59, i);
                            break;
                        case 49:
                            realKeyLongClick1(this.mContext, 62, i);
                            break;
                        case 50:
                            realKeyLongClick1(this.mContext, 52, i);
                            break;
                        case 51:
                            realKeyLongClick1(this.mContext, 14, i);
                            break;
                        case 52:
                            realKeyLongClick1(this.mContext, 128, i);
                            break;
                        case 53:
                            realKeyLongClick1(this.mContext, 50, i);
                            break;
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 58, i);
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

    private String getOutTemperature() {
        int[] iArr = this.mCanBusInfoInt;
        if (getMsbLsbResult(iArr[2], iArr[3]) > 200) {
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            return sb.append((getMsbLsbResult(iArr2[2], iArr2[3]) * 0.5d) - 32768.0d).append(getTempUnitC(this.mContext)).toString();
        }
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        return sb2.append(getMsbLsbResult(iArr3[2], iArr3[3]) * 0.5d).append(getTempUnitC(this.mContext)).toString();
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

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isDoorDataNoChange() {
        if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mDoorData = this.mCanBusInfoInt;
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
