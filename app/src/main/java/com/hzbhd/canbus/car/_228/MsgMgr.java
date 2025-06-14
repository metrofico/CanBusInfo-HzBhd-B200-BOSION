package com.hzbhd.canbus.car._228;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    String ResultTemp;
    int differentId;
    private int eachId;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private byte mFreqHi;
    private byte mFreqLo;
    int[] mFrontRadarData;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    int[] mRearRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr uiMgr;

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
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setBackLightInfo0x14();
            return;
        }
        if (i == 41) {
            int i2 = this.eachId;
            if (i2 == 3 || i2 == 5 || i2 == 7) {
                return;
            }
            setTrackData0x29();
            return;
        }
        if (i == 48) {
            VersionInfo0x30();
            return;
        }
        if (i == 51) {
            if (this.eachId != 7) {
                return;
            }
            setPanoramicInfo0x33();
            return;
        }
        if (i != 52) {
            switch (i) {
                case 32:
                    setWheelKeyInfo0x20();
                    break;
                case 33:
                    int i3 = this.eachId;
                    if (i3 == 3 || i3 == 5 || i3 == 4) {
                        setAirInfo0x21();
                        break;
                    }
                case 34:
                    int i4 = this.eachId;
                    if (i4 == 6 || i4 == 7) {
                        setRearRadarInfo0x22();
                        break;
                    }
                case 35:
                    if (this.eachId == 6) {
                        setFrontRadarInfo0x22();
                        break;
                    }
                    break;
                case 36:
                    setDoorInfo0x24();
                    break;
            }
            return;
        }
        if (this.eachId != 5) {
            return;
        }
        setCarTypeInfo0x34();
    }

    private void setBackLightInfo0x14() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_228_driveInfo_1"), resolveBackLightState(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_228_driveInfo_0"), resolveBackLight(this.mCanBusInfoInt[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setWheelKeyInfo0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 32) {
            buttonKey(57);
            return;
        }
        if (i != 33) {
            switch (i) {
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
                    int i2 = this.eachId;
                    if (i2 == 1 || i2 == 6) {
                        buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                    }
                    int i3 = this.eachId;
                    if (i3 == 2 || i3 == 7 || i3 == 4) {
                        buttonKey(14);
                        break;
                    }
                    break;
                case 6:
                    buttonKey(15);
                    break;
                case 7:
                    buttonKey(2);
                    break;
                case 8:
                    buttonKey(7);
                    break;
                case 9:
                    buttonKey(8);
                    break;
                case 10:
                    buttonKey(1);
                    break;
                default:
                    switch (i) {
                        case 22:
                            buttonKey(3);
                            break;
                        case 23:
                            buttonKey(52);
                            break;
                        case 24:
                            buttonKey(50);
                            break;
                        case 25:
                            buttonKey(128);
                            break;
                    }
            }
            return;
        }
        buttonKey(2);
    }

    private void setAirInfo0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = iArr[4];
        int i4 = iArr[5];
        int i5 = iArr[6];
        int i6 = iArr[7];
        int i7 = iArr[8];
        GeneralAirData.power = DataHandleUtils.getBoolBit7(i);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(i);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(i);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(i);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(i);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(i);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(i);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit0(i);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(i2);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(i2);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(i2);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(i2);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(i2);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(i2);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(i2, 0, 4);
        GeneralAirData.front_left_temperature = resolveTempOfLeft(i3);
        GeneralAirData.front_right_temperature = resolveAirTemperature(i4);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(i5);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(i5);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(i5);
        GeneralAirData.eco = DataHandleUtils.getBoolBit4(i5);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(i5);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(i5);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i6, 4, 3);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i6, 0, 3);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(i7, 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void setDoorInfo0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setTrackData0x29() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void VersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setRearRadarInfo0x22() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x22() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setPanoramicInfo0x33() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_228_setting_0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_228_setting_0", "_228_initial_perspective"), Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarTypeInfo0x34() {
        new ArrayList().add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_228_car_type"), resolveCar(this.mCanBusInfoInt[2])));
    }

    private String resolveBackLight(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._332_setting_state2);
        }
        if (i == 227) {
            return this.mContext.getResources().getString(R.string._228_driveInfo_3);
        }
        if (i == 22) {
            return this.mContext.getResources().getString(R.string._228_driveInfo_2);
        }
        return i + "";
    }

    private String resolveBackLightState(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._332_setting_state2);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._332_setting_state1);
        }
        return null;
    }

    private String resolveCar(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._228_15_X35);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._228_16_X35);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._228_17_X35);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._228_18_X35);
        }
        return null;
    }

    private String resolveTempOfLeft(int i) {
        if (this.eachId == 4 && i > 9) {
            i = 9;
        }
        return i + this.mContext.getResources().getString(R.string.level);
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            this.ResultTemp = "LOW";
        } else if (i == 31) {
            this.ResultTemp = "HIGH";
        } else {
            this.ResultTemp = ((i / 2.0f) + 15.5d) + getTempUnitC(this.mContext);
        }
        return this.ResultTemp;
    }

    private String resolveOutDoorTem(int i) {
        return ((i / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (this.mContext == null) {
            return;
        }
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, allBandTypeData, this.mFreqLo, this.mFreqHi, (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
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
                return (byte) 16;
        }
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.mFreqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.mFreqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.mFreqHi = (byte) (i >> 8);
            this.mFreqLo = (byte) (i & 255);
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
