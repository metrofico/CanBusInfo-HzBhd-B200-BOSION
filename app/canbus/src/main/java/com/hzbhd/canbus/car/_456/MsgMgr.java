package com.hzbhd.canbus.car._456;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.helpers.DateLayout;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mCarDoorData;
    Context mContext;
    int[] mDoorInfo;
    int[] mFrontRadarData;
    int[] mRearRadarData;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");
    public int state60 = 0;
    public int state61 = 0;
    public int state62 = 0;
    public int state63 = 0;
    public int state64 = 0;
    public int state65 = 0;
    public int state66 = 0;
    private String consumptionUnit = "l/100km";
    private String distanceUnit = "km";

    private String getGearState(int i) {
        switch (i) {
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "R";
            case 8:
                return "D";
            case 9:
                return "M";
            case 10:
                return "N";
            default:
                return "P";
        }
    }

    private String getLowHighState(int i) {
        return i == 0 ? " " : i == 1 ? "LOW" : "HIGH";
    }

    private int getRadarRange(int i) {
        return i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            setBasicInfo0x01(byteArrayToIntArray);
            return;
        }
        if (i == 2) {
            setSwc0x02(byteArrayToIntArray);
            return;
        }
        if (i == 4) {
            setRearRadar0x04(byteArrayToIntArray);
            return;
        }
        if (i == 5) {
            setRearRadar0x05(byteArrayToIntArray);
            return;
        }
        if (i == 6) {
            setDoorInfo0x06(byteArrayToIntArray);
            return;
        }
        if (i == 10) {
            setTrack0x0A(byteArrayToIntArray);
            return;
        }
        if (i == 53) {
            updateSpeedInfo(byteArrayToIntArray[3]);
            return;
        }
        if (i == 127) {
            updateVersionInfo(this.mContext, getVersionStr(bArr));
        } else if (i == 55) {
            setDrive0x37(byteArrayToIntArray);
        } else {
            if (i != 56) {
                return;
            }
            setDate0x38(byteArrayToIntArray);
        }
    }

    private void setDate0x38(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_4"), DataHandleUtils.getBoolBit2(iArr[3]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_5"), getLowHighState(DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_6"), getDriverMode(DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 4))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_7"), getAirMode(DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 4))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_8"), getGearState(iArr[7])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_9"), getTireTagState(iArr[8])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_10"), getTireTagState(iArr[9])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_11"), getTireTagState(iArr[10])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_12"), getTireTagState(iArr[11])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_13"), DataHandleUtils.getBoolBit7(iArr[12]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_14"), DataHandleUtils.getIntFromByteWithBit(iArr[12], 5, 2) + "LEVEL"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_15"), DataHandleUtils.getBoolBit4(iArr[12]) ? "ON" : "OFF"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_0"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[3]) ? 1 : 0)));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_1"), Integer.valueOf(iArr[2])).setProgress(iArr[2] - 16));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_2"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[3]) ? 1 : 0)));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 2))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_4"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[3]) ? 1 : 0)));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_5"), Integer.valueOf(iArr[5])));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_60"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[4]) ? 1 : 0)));
        this.state60 = DataHandleUtils.getBoolBit7(iArr[4]) ? 1 : 0;
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_61"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[4]) ? 1 : 0)));
        this.state61 = DataHandleUtils.getBoolBit6(iArr[4]) ? 1 : 0;
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_62"), Integer.valueOf(DataHandleUtils.getBoolBit5(iArr[4]) ? 1 : 0)));
        this.state62 = DataHandleUtils.getBoolBit5(iArr[4]) ? 1 : 0;
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_63"), Integer.valueOf(DataHandleUtils.getBoolBit4(iArr[4]) ? 1 : 0)));
        this.state63 = DataHandleUtils.getBoolBit4(iArr[4]) ? 1 : 0;
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_64"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[4]) ? 1 : 0)));
        this.state64 = DataHandleUtils.getBoolBit3(iArr[4]) ? 1 : 0;
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_65"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[4]) ? 1 : 0)));
        this.state65 = DataHandleUtils.getBoolBit2(iArr[4]) ? 1 : 0;
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_66"), Integer.valueOf(DataHandleUtils.getBoolBit1(iArr[4]) ? 1 : 0)));
        this.state66 = DataHandleUtils.getBoolBit1(iArr[4]) ? 1 : 0;
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    private String getTireTagState(int i) {
        if (i == 7) {
            return this.mContext.getString(R.string._456_drive_90);
        }
        if (i < 7) {
            return this.mContext.getString(R.string._456_drive_91);
        }
        if (i == 20) {
            return this.mContext.getString(R.string._456_drive_92);
        }
        if (i == 30) {
            return this.mContext.getString(R.string._456_drive_93);
        }
        if (i > 30) {
            return this.mContext.getString(R.string._456_drive_94);
        }
        return "" + i;
    }

    private String getAirMode(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._456_drive_71);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._456_drive_72);
        }
        return this.mContext.getString(R.string._456_drive_70);
    }

    private String getDriverMode(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._456_drive_61);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._456_drive_62);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._456_drive_63);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._456_drive_64);
        }
        return this.mContext.getString(R.string._456_drive_60);
    }

    private void setDrive0x37(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_1"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) / 10.0f) + this.consumptionUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_2"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) / 10.0f) + this.consumptionUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_456_drive_3"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) / 10.0f) + this.distanceUnit + "/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrack0x0A(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            if (DataHandleUtils.getBoolBit0(iArr[2])) {
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) iArr[3], (byte) iArr[4], 0, 480, 16);
                updateParkUi(null, this.mContext);
            } else {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[3], (byte) iArr[4], 0, 480, 16);
                updateParkUi(null, this.mContext);
            }
        }
    }

    private void setDoorInfo0x06(int[] iArr) {
        if (isDoorInfoChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setRearRadar0x05(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = false;
            RadarInfoUtil.setFrontRadarLocationData(10, getRadarRange(iArr[2]), getRadarRange(iArr[3]), getRadarRange(iArr[4]), getRadarRange(iArr[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadar0x04(int[] iArr) {
        if (isRearRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = false;
            RadarInfoUtil.setRearRadarLocationData(10, getRadarRange(iArr[2]), getRadarRange(iArr[3]), getRadarRange(iArr[4]), getRadarRange(iArr[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setSwc0x02(int[] iArr) {
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 68, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 15, iArr[3]);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 33, iArr[3]);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 34, iArr[3]);
                break;
            case 19:
                realKeyLongClick1(this.mContext, 35, iArr[3]);
                break;
            case 20:
                realKeyLongClick1(this.mContext, 36, iArr[3]);
                break;
            case 22:
                realKeyLongClick1(this.mContext, 128, iArr[3]);
                break;
            case 23:
                realKeyLongClick1(this.mContext, 59, iArr[3]);
                break;
            case 24:
                realKeyLongClick1(this.mContext, 1, iArr[3]);
                break;
            case 25:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 27:
                realKeyLongClick1(this.mContext, 52, iArr[3]);
                break;
            case 32:
                realKeyLongClick1(this.mContext, 37, iArr[3]);
                break;
            case 33:
                realKeyLongClick1(this.mContext, 38, iArr[3]);
                break;
            case 34:
                realKeyLongClick1(this.mContext, 39, iArr[3]);
                break;
        }
    }

    private void setBasicInfo0x01(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            setBacklightLevel((iArr[3] / 20) + 1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, "RADIO".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, "MUSIC".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, "VIDEO".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, "AUX".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, "BT MUSIC".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (DateLayout.NULL_DATE_FORMAT.equals(str)) {
            CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64}, "MEDIA OFF".getBytes()));
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDoorInfoChange(int[] iArr) {
        if (Arrays.equals(this.mDoorInfo, iArr)) {
            return false;
        }
        this.mDoorInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
