package com.hzbhd.canbus.car._259;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private int mCurrentCarId;
    private int mPreKeyState;
    private UiMgr mUiMgr;
    private byte[] mediaDataNow;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_FRONT_OPEN = "is_front_open";

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int resultRadar0(int i) {
        if (i == 255) {
            return 0;
        }
        return i;
    }

    private int resultRadar2(int i) {
        if (i != 1) {
            return i != 2 ? 0 : 4;
        }
        return 1;
    }

    private int resultRadar4(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 0 : 7;
        }
        return 5;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mCurrentCarId = getCurrentCanDifferentId();
        Log.d("cwh", "mCurrentCarId   " + this.mCurrentCarId);
        this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        if (this.mCurrentCarId != 1) {
            Log.d("cwh", "0");
            this.mUiMgr.setShowRadar(false);
        } else {
            Log.d("cwh", "1");
            this.mUiMgr.setShowRadar(true);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            realKeyControl();
            return;
        }
        if (i == 18) {
            setCarSetData0x12();
            return;
        }
        if (i == 26) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData0x1a();
            int[] iArr = this.mCanBusInfoInt;
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[5], iArr[6]));
            return;
        }
        if (i == 65) {
            if (this.mCurrentCarId == 1) {
                setRadar0x41();
                return;
            }
            return;
        }
        if (i == 97) {
            setCarSetData0x61();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData0x31();
        } else if (i == 50 && this.mCurrentCarId == 0) {
            setCarBodyInfo();
        }
    }

    private void setRadar0x41() {
        if (this.mCanBusInfoInt[12] == 1) {
            RadarInfoUtil.mMinIsClose = true;
            GeneralParkData.isShowDistanceNotShowLocationUi = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13]);
            if (GeneralParkData.isShowDistanceNotShowLocationUi) {
                int[] iArr = this.mCanBusInfoInt;
                RadarInfoUtil.setRearRadarDistanceData(iArr[2], iArr[3], iArr[4], iArr[5]);
                int[] iArr2 = this.mCanBusInfoInt;
                RadarInfoUtil.setFrontRadarDistanceData(iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
                GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            } else {
                RadarInfoUtil.setRearRadarLocationData(7, resultRadar4(this.mCanBusInfoInt[2]), resultRadar0(this.mCanBusInfoInt[3]), resultRadar0(this.mCanBusInfoInt[4]), resultRadar4(this.mCanBusInfoInt[5]));
                RadarInfoUtil.setFrontRadarLocationData(4, resultRadar2(this.mCanBusInfoInt[6]), resultRadar0(this.mCanBusInfoInt[7]), resultRadar0(this.mCanBusInfoInt[8]), resultRadar2(this.mCanBusInfoInt[9]));
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            }
            updateParkUi(null, this.mContext);
        }
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realKeyControl() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        if (i == 0) {
            realKeyClick(0);
            return;
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
            realKeyClick(3);
            return;
        }
        if (i == 4) {
            if (this.mCurrentCarId == 0) {
                realKeyClick(HotKeyConstant.K_VOICE_PICKUP);
                return;
            } else {
                realKeyClick(HotKeyConstant.K_SPEECH);
                return;
            }
        }
        if (i == 5) {
            if (this.mCurrentCarId == 1) {
                realKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                return;
            } else {
                realKeyClick(14);
                return;
            }
        }
        if (i == 8) {
            realKeyClick(45);
            return;
        }
        if (i == 9) {
            realKeyClick(46);
            return;
        }
        if (i != 12) {
            return;
        }
        if (this.mCurrentCarId == 1 && this.mPreKeyState == iArr[5]) {
            LogUtil.showLog("send speech");
            realKeyClick(HotKeyConstant.K_SPEECH);
        } else {
            this.mPreKeyState = iArr[5];
            realKeyClick(2);
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ion = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            GeneralAirData.in_out_auto_cycle = 1;
        } else {
            GeneralAirData.in_out_auto_cycle = 0;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            GeneralAirData.in_out_auto_cycle = 2;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
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
        if (this.mCurrentCarId == 0) {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        } else {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setCarBodyInfo() {
        GeneralAirData.pm_value_level_in_car = this.mCanBusInfoInt[8] + "";
        GeneralAirData.pm_value_level_out_car = this.mCanBusInfoInt[9] + "";
        updateAirActivity(this.mContext, 1001);
    }

    private void setCarSetData0x12() {
        ArrayList arrayList = new ArrayList();
        if (this.mCurrentCarId == 0) {
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])))));
        } else {
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])))));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSetData0x61() {
        ArrayList arrayList = new ArrayList();
        if (this.mCurrentCarId == 1) {
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])))));
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])))));
        } else {
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x1a() throws Resources.NotFoundException {
        String string;
        Resources resources;
        int i;
        GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowRev = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowSteeringWheeAngle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowGearPosition = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowSpeed = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowLeftRightLight = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowAcc = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        if (GeneralDoorData.isShowCarDoor && isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
            updateDoorView(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[7];
        if (i2 != 0) {
            string = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? "" : "D" : "R" : "N" : "P";
        } else {
            string = this.mContext.getResources().getString(R.string.invalid);
        }
        ArrayList arrayList = new ArrayList();
        if (GeneralDoorData.isShowRev) {
            arrayList.add(new DriverUpdateEntity(0, 0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? getCloseOpenStr(1) : getCloseOpenStr(0)));
        }
        if (GeneralDoorData.isShowAcc) {
            arrayList.add(new DriverUpdateEntity(0, 1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ? getCloseOpenStr(1) : getCloseOpenStr(0)));
        }
        if (GeneralDoorData.isShowLeftRightLight) {
            arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]) ? getCloseOpenStr(1) : getCloseOpenStr(0)));
            arrayList.add(new DriverUpdateEntity(0, 3, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]) ? getCloseOpenStr(1) : getCloseOpenStr(0)));
            arrayList.add(new DriverUpdateEntity(0, 4, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]) ? getCloseOpenStr(1) : getCloseOpenStr(0)));
        }
        if (GeneralDoorData.isShowSpeed) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 5, sb.append((iArr[6] + (iArr[5] * 256)) / 10.0f).append(" km/h").toString()));
        }
        if (GeneralDoorData.isShowGearPosition) {
            arrayList.add(new DriverUpdateEntity(0, 6, string));
        }
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, sb2.append(iArr2[12] + (iArr2[11] * 256)).append(" rps").toString()));
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10])) {
            resources = this.mContext.getResources();
            i = R.string.key_360_on;
        } else {
            resources = this.mContext.getResources();
            i = R.string.key_360_no;
        }
        arrayList.add(new DriverUpdateEntity(0, 8, resources.getString(i)));
        forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        if (GeneralDoorData.isShowSteeringWheeAngle) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        }
        updateParkUi(null, this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (this.mCurrentCarId == 1) {
            byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
            if (isBandFm(str)) {
                if (str2.length() == 5) {
                    str4 = "0" + i + " 0" + str2.substring(0, 4) + "    ";
                } else if (str2.length() == 4) {
                    str4 = "0" + i + "  " + str2.substring(0, 4) + "    ";
                } else {
                    str4 = "0" + i + " " + str2.substring(0, 5) + "    ";
                }
            } else if (str2.length() == 3) {
                str4 = "0" + i + " " + str2 + "      ";
            } else {
                str4 = "0" + i + " " + str2 + "     ";
            }
            byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -111, allBandTypeData}, str4.getBytes());
            CanbusMsgSender.sendMsg(bArrByteMerger);
            sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), bArrByteMerger);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (this.mCurrentCarId == 1) {
            byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -111, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (new DecimalFormat("000").format((b7 * 256) + i) + " 00:" + new DecimalFormat("00").format(b5 & 255) + "   ").getBytes());
            CanbusMsgSender.sendMsg(bArrByteMerger);
            sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), bArrByteMerger);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
        }
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, -123}, "0000256 6666".getBytes()));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(DataHandleUtils.setReportHiworldSrcInfoData((byte) -111, (byte) 8));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        super.atvDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int i, int i2, String str) {
        super.btPhoneCallLogInfoChange(i, i2, str);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (this.mCurrentCarId == 1) {
            String str4 = new DecimalFormat("000").format(i4) + " " + new DecimalFormat("00").format((i / 60) % 60) + ":" + new DecimalFormat("00").format(i % 60) + "   ";
            byte b2 = 7;
            if (i6 != 1 && i6 != 2 && i6 != 3) {
                b2 = (i6 == 6 || i6 == 7) ? (byte) 6 : (byte) 0;
            }
            byte[] bArr = {22, -111, b2};
            CanbusMsgSender.sendMsg(bArr);
            sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), DataHandleUtils.byteMerger(bArr, str4.getBytes()));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.toString(), new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(DataHandleUtils.setReportHiworldSrcInfoData((byte) -111, (byte) 8));
        }
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -111, (byte) 8));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (this.mCurrentCarId == 1) {
            String str5 = new DecimalFormat("000").format((b6 * 256) + i) + " 00:" + new DecimalFormat("00").format(b5 & 255) + "   ";
            byte[] bArr = {22, -111, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED};
            CanbusMsgSender.sendMsg(bArr);
            sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.byteMerger(bArr, str5.getBytes()));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        if (this.mCurrentCarId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) == GeneralDoorData.isFrontOpen) ? false : true;
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    private String getCloseOpenStr(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.close);
        }
        return i == 1 ? this.mContext.getResources().getString(R.string.open) : "set_default";
    }

    private void sendMediaMsg1(Context context, String str, byte[] bArr) {
        Log.i("AbstractMsgMgr", "sendMediaMsg: context: " + context + ", media: " + str);
        if (context == null) {
            return;
        }
        String string = FutureUtil.instance.getCurrentValidSource().toString();
        Log.i("AbstractMsgMgr", "sendMediaMsg: frontSeat:" + string);
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if ((str.equals(string) || SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name().equals(str)) && !Arrays.equals(bArr, this.mediaDataNow)) {
                CanbusMsgSender.sendMsg(bArr);
                this.mediaDataNow = Arrays.copyOf(bArr, bArr.length);
                if (SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str)) {
                    return;
                }
                Settings.System.putString(context.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(bArr, 0));
                if (SourceConstantsDef.SOURCE_ID.MPEG.toString().equals(string)) {
                    return;
                }
                Settings.System.putString(context.getContentResolver(), "reportForDiscEject", Base64.encodeToString(bArr, 0));
            }
        }
    }
}
