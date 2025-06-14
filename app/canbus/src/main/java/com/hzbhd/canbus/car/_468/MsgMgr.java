package com.hzbhd.canbus.car._468;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        if (getCurrentEachCanId() == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 0});
        } else if (getCurrentEachCanId() == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
        }
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
    public void onAccOff() {
        super.onAccOff();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 29) {
            set0x1DRadarInfo(byteArrayToIntArray);
            return;
        }
        if (i == 30) {
            set0x1ERadarInfo(byteArrayToIntArray);
            return;
        }
        if (i == 32) {
            set0x20Swc(byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            set0x24Door(byteArrayToIntArray);
            return;
        }
        if (i == 48) {
            set0x30VersionInfo(bArr);
            return;
        }
        if (i == 40) {
            set0x28Air(byteArrayToIntArray);
            return;
        }
        if (i == 41) {
            set0x29Esp(bArr);
        } else if (i == 53) {
            set0x35DriveData(byteArrayToIntArray);
        } else {
            if (i != 54) {
                return;
            }
            set0x36PanelKey(byteArrayToIntArray);
        }
    }

    private void set0x36PanelKey(int[] iArr) {
        switch (iArr[2]) {
            case 1:
                realKeyClick(this.mContext, 33);
                break;
            case 2:
                realKeyClick(this.mContext, 34);
                break;
            case 3:
                realKeyClick(this.mContext, 35);
                break;
            case 4:
                realKeyClick(this.mContext, 36);
                break;
            case 5:
                realKeyClick(this.mContext, 37);
                break;
            case 6:
                realKeyClick(this.mContext, 38);
                break;
            case 7:
                realKeyClick(this.mContext, 21);
                break;
            case 8:
                realKeyClick(this.mContext, 20);
                break;
        }
    }

    private void set0x35DriveData(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13"), DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) + "rpm"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14"), DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) + "km/h"));
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16"), (((iArr[9] & 255) << 8) | ((iArr[8] & 255) << 16) | (iArr[10] & 255)) + "km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Left_turn"), DataHandleUtils.getBoolBit0(iArr[11]) ? "ON" : "OFF"));
        turnLeftLamp(DataHandleUtils.getBoolBit0(iArr[11]));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Right_turn"), DataHandleUtils.getBoolBit1(iArr[11]) ? "ON" : "OFF"));
        turnRightLamp(DataHandleUtils.getBoolBit1(iArr[11]));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x30VersionInfo(byte[] bArr) {
        updateVersionInfo(this.mContext, getVersionStr(bArr));
    }

    private void set0x29Esp(byte[] bArr) {
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 4608, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x28Air(int[] iArr) {
        if (isAirDataChange(iArr)) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4);
            int i = iArr[4];
            if (i == 0) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i == 34) {
                GeneralAirData.front_left_temperature = 17 + getTempUnitC(this.mContext);
            } else if (i == 255) {
                GeneralAirData.front_left_temperature = "";
            } else if (i == 31) {
                GeneralAirData.front_left_temperature = "HI";
            } else if (i == 32) {
                GeneralAirData.front_left_temperature = 16 + getTempUnitC(this.mContext);
            } else {
                GeneralAirData.front_left_temperature = ((iArr[4] / 2.0f) + 17.5d) + getTempUnitC(this.mContext);
            }
            int i2 = iArr[5];
            if (i2 == 0) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i2 == 34) {
                GeneralAirData.front_right_temperature = 17 + getTempUnitC(this.mContext);
            } else if (i2 == 255) {
                GeneralAirData.front_right_temperature = "";
            } else if (i2 == 31) {
                GeneralAirData.front_right_temperature = "HI";
            } else if (i2 == 32) {
                GeneralAirData.front_right_temperature = 16 + getTempUnitC(this.mContext);
            } else {
                GeneralAirData.front_right_temperature = ((iArr[5] / 2.0f) + 17.5d) + getTempUnitC(this.mContext);
            }
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x24Door(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x20Swc(int[] iArr) {
        int i = iArr[2];
        if (i == 0) {
            buttonKey(0, iArr);
            return;
        }
        if (i == 1) {
            buttonKey(7, iArr);
            return;
        }
        if (i == 2) {
            buttonKey(8, iArr);
            return;
        }
        if (i == 19) {
            buttonKey(45, iArr);
            return;
        }
        if (i == 20) {
            buttonKey(46, iArr);
            return;
        }
        if (i != 135) {
            switch (i) {
                case 7:
                    buttonKey(2, iArr);
                    break;
                case 8:
                    buttonKey(HotKeyConstant.K_SPEECH, iArr);
                    break;
                case 9:
                    buttonKey(14, iArr);
                    break;
                case 10:
                    buttonKey(15, iArr);
                    break;
            }
            return;
        }
        buttonKey(3, iArr);
    }

    public void buttonKey(int i, int[] iArr) {
        realKeyLongClick1(this.mContext, i, iArr[3]);
    }

    private void set0x1ERadarInfo(int[] iArr) {
        if (isRearRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x1DRadarInfo(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, (byte) DataHandleUtils.getMsb(i3), (byte) DataHandleUtils.getLsb(i3)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, b6, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i == 0) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) DataHandleUtils.getMsbLsbResult_4bit(0, i), (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f)), (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2))), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            } else {
                if ("AM1".equals(str) || "AM2".equals(str)) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) DataHandleUtils.getMsbLsbResult_4bit(8, i), (byte) DataHandleUtils.getMsb(Integer.parseInt(str2)), (byte) DataHandleUtils.getLsb(Integer.parseInt(str2)), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                    return;
                }
                return;
            }
        }
        if (i == 1) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                int msbLsbResult_4bit = DataHandleUtils.getMsbLsbResult_4bit(0, i);
                byte msb = (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f));
                byte lsb = (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2)));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit, msb, lsb, msb, lsb, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            }
            if ("AM1".equals(str) || "AM2".equals(str)) {
                int msbLsbResult_4bit2 = DataHandleUtils.getMsbLsbResult_4bit(8, i);
                byte msb2 = (byte) DataHandleUtils.getMsb(Integer.parseInt(str2));
                byte lsb2 = (byte) DataHandleUtils.getLsb(Integer.parseInt(str2));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit2, msb2, lsb2, msb2, lsb2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            }
            return;
        }
        if (i == 2) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                int msbLsbResult_4bit3 = DataHandleUtils.getMsbLsbResult_4bit(0, i);
                byte msb3 = (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f));
                byte lsb3 = (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2)));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit3, msb3, lsb3, 0, 0, msb3, lsb3, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            }
            if ("AM1".equals(str) || "AM2".equals(str)) {
                int msbLsbResult_4bit4 = DataHandleUtils.getMsbLsbResult_4bit(8, i);
                byte msb4 = (byte) DataHandleUtils.getMsb(Integer.parseInt(str2));
                byte lsb4 = (byte) DataHandleUtils.getLsb(Integer.parseInt(str2));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit4, msb4, lsb4, 0, 0, msb4, lsb4, 0, 0, 0, 0, 0, 0, 0, 0});
                return;
            }
            return;
        }
        if (i == 3) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                int msbLsbResult_4bit5 = DataHandleUtils.getMsbLsbResult_4bit(0, i);
                byte msb5 = (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f));
                byte lsb5 = (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2)));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit5, msb5, lsb5, 0, 0, 0, 0, msb5, lsb5, 0, 0, 0, 0, 0, 0});
                return;
            }
            if ("AM1".equals(str) || "AM2".equals(str)) {
                int msbLsbResult_4bit6 = DataHandleUtils.getMsbLsbResult_4bit(8, i);
                byte msb6 = (byte) DataHandleUtils.getMsb(Integer.parseInt(str2));
                byte lsb6 = (byte) DataHandleUtils.getLsb(Integer.parseInt(str2));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit6, msb6, lsb6, 0, 0, 0, 0, msb6, lsb6, 0, 0, 0, 0, 0, 0});
                return;
            }
            return;
        }
        if (i == 4) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                int msbLsbResult_4bit7 = DataHandleUtils.getMsbLsbResult_4bit(0, i);
                byte msb7 = (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f));
                byte lsb7 = (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2)));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit7, msb7, lsb7, 0, 0, 0, 0, 0, 0, msb7, lsb7, 0, 0, 0, 0});
                return;
            }
            if ("AM1".equals(str) || "AM2".equals(str)) {
                int msbLsbResult_4bit8 = DataHandleUtils.getMsbLsbResult_4bit(8, i);
                byte msb8 = (byte) DataHandleUtils.getMsb(Integer.parseInt(str2));
                byte lsb8 = (byte) DataHandleUtils.getLsb(Integer.parseInt(str2));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit8, msb8, lsb8, 0, 0, 0, 0, 0, 0, msb8, lsb8, 0, 0, 0, 0});
                return;
            }
            return;
        }
        if (i == 5) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                int msbLsbResult_4bit9 = DataHandleUtils.getMsbLsbResult_4bit(0, i);
                byte msb9 = (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f));
                byte lsb9 = (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2)));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit9, msb9, lsb9, 0, 0, 0, 0, 0, 0, 0, 0, msb9, lsb9, 0, 0});
                return;
            }
            if ("AM1".equals(str) || "AM2".equals(str)) {
                int msbLsbResult_4bit10 = DataHandleUtils.getMsbLsbResult_4bit(8, i);
                byte msb10 = (byte) DataHandleUtils.getMsb(Integer.parseInt(str2));
                byte lsb10 = (byte) DataHandleUtils.getLsb(Integer.parseInt(str2));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit10, msb10, lsb10, 0, 0, 0, 0, 0, 0, 0, 0, msb10, lsb10, 0, 0});
                return;
            }
            return;
        }
        if (i == 6) {
            if ("FM1".equals(str) || "FM2".equals(str) || "FM3".equals(str)) {
                int msbLsbResult_4bit11 = DataHandleUtils.getMsbLsbResult_4bit(0, i);
                byte msb11 = (byte) DataHandleUtils.getMsb((int) (Float.parseFloat(str2) * 10.0f));
                byte lsb11 = (byte) DataHandleUtils.getLsb((int) (10.0f * Float.parseFloat(str2)));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit11, msb11, lsb11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, msb11, lsb11});
                return;
            }
            if ("AM1".equals(str) || "AM2".equals(str)) {
                int msbLsbResult_4bit12 = DataHandleUtils.getMsbLsbResult_4bit(8, i);
                byte msb12 = (byte) DataHandleUtils.getMsb(Integer.parseInt(str2));
                byte lsb12 = (byte) DataHandleUtils.getLsb(Integer.parseInt(str2));
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) msbLsbResult_4bit12, msb12, lsb12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, msb12, lsb12});
            }
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

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
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

    private boolean isTireInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTireInfo, iArr)) {
            return false;
        }
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mPanoramicInfo, iArr)) {
            return false;
        }
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
