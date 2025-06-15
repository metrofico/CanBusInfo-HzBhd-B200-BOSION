package com.hzbhd.canbus.car._308;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlinx.coroutines.scheduling.WorkQueueKt;


public class MsgMgr extends AbstractMsgMgr {
    private boolean isFM;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private byte carInfoData0 = 0;
    private byte carInfoData1 = 0;
    private boolean carInfoFirst = false;
    private boolean isNight = false;

    private int getSurroundVolFRprogress(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            return i;
        }
        switch (i) {
            case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                return -5;
            case 252:
                return -4;
            case 253:
                return -3;
            case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                return -2;
            case 255:
                return -1;
            default:
                return 0;
        }
    }

    private int getSurroundVolProgress(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            return i + 5;
        }
        switch (i) {
            case 252:
                return 1;
            case 253:
                return 2;
            case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                return 3;
            case 255:
                return 4;
            default:
                return 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 4});
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 5});
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 19});
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 51});
        CanbusMsgSender.sendMsg(new byte[]{22, -15, 113});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        CommUtil.printHexString("scyscyscy308：", bArr);
        int i = this.mCanBusInfoInt[1];
        if (i == 1) {
            keyEvent0x01();
            return;
        }
        if (i == 2) {
            panelEvent0x02();
            return;
        }
        if (i == 4) {
            carInfo0x04();
            return;
        }
        if (i == 5) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            airInfo0x05();
            return;
        }
        if (i == 9) {
            trackInfo0x09();
            return;
        }
        if (i == 11) {
            carMessageInfo0x0B();
            return;
        }
        if (i == 48) {
            carSetting0x30();
            return;
        }
        if (i == 51) {
            carMessageInfo0x33();
            return;
        }
        if (i == 113) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        } else if (i == 18) {
            dvdData0x12();
        } else {
            if (i != 19) {
                return;
            }
            amplifierInfo0x13();
        }
    }

    private void dvdData0x12() {
        Context context;
        int i;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            context = this.mContext;
            i = R.string._308_value_13;
        } else {
            context = this.mContext;
            i = R.string._308_value_14;
        }
        GeneralOriginalCarDeviceData.cdStatus = context.getString(i);
        GeneralOriginalCarDeviceData.runningState = getDVDStatus();
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[4];
        int i3 = iArr[5];
        int i4 = iArr[6];
        String str = "";
        String str2 = (i2 == 0 || i2 == 255) ? "" : this.mCanBusInfoInt[4] + "";
        String str3 = (i3 < 0 || i3 > 59) ? "0" : i3 + "";
        if (i4 >= 0 && i4 <= 59) {
            str = i4 + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._308_title_19) + str2));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._308_title_20) + str3 + ":" + str));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private String getDVDStatus() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        if (intFromByteWithBit == 0) {
            return this.mContext.getString(R.string._308_value_15);
        }
        if (intFromByteWithBit != 1) {
            return intFromByteWithBit != 2 ? "" : this.mContext.getString(R.string._308_value_16);
        }
        return this.mContext.getString(R.string._308_value_17);
    }

    private void carSetting0x30() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
        if (intFromByteWithBit >= 0 && intFromByteWithBit <= 3) {
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit));
        }
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void amplifierInfo0x13() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.frontRear = getSurroundVolFRprogress(this.mCanBusInfoInt[6]);
        GeneralAmplifierData.leftRight = getSurroundVolFRprogress(this.mCanBusInfoInt[5]);
        GeneralAmplifierData.bandBass = getSurroundVolProgress(this.mCanBusInfoInt[3]);
        GeneralAmplifierData.bandTreble = getSurroundVolProgress(this.mCanBusInfoInt[4]);
        GeneralAmplifierData.customBass = getSurroundVolProgress(this.mCanBusInfoInt[7]);
        GeneralAmplifierData.custom2Bass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 3);
        GeneralAmplifierData.bose_center_b = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        updateAmplifierActivity(null);
        ArrayList arrayList = new ArrayList();
        if (GeneralAmplifierData.custom2Bass >= 0 && GeneralAmplifierData.custom2Bass <= 5) {
            arrayList.add(new SettingUpdateEntity(1, 0, GeneralAmplifierData.custom2Bass + "").setProgress(GeneralAmplifierData.custom2Bass));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getSurroundVolStr(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            return i + "";
        }
        switch (i) {
            case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                return "-5";
            case 252:
                return "-4";
            case 253:
                return "-3";
            case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                return "-2";
            case 255:
                return "-1";
            default:
                return "0";
        }
    }

    private void carMessageInfo0x33() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, sb.append((iArr[2] * 256) + iArr[3]).append("KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void carMessageInfo0x0B() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 5, sb.append((iArr[2] * 256) + iArr[3]).append("Km/h").toString()));
        arrayList.add(new DriverUpdateEntity(0, 6, (this.mCanBusInfoInt[4] * 100) + " r/min"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo((iArr2[2] * 256) + iArr2[3]);
    }

    private void trackInfo0x09() {
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[2], (byte) 0, WorkQueueKt.MASK, 255, 16);
        updateParkUi(null, this.mContext);
    }

    private void airInfo0x05() {
        GeneralAirData.ac = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_window = true;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        updateAirActivity(this.mContext, 1001);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return (i < 1 || i > 29) ? "" : (((i - 1) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
    }

    private void carInfo0x04() {
        byte b = this.carInfoData0;
        byte[] bArr = this.mCanBusInfoByte;
        if (b != bArr[2] || this.carInfoData1 != bArr[3]) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isShowMasterDriverSeatBelt = true;
            GeneralDoorData.isSeatMasterDriverBeltTie = !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            if (this.carInfoFirst) {
                updateDoorView(this.mContext);
            } else {
                this.carInfoFirst = true;
            }
            byte[] bArr2 = this.mCanBusInfoByte;
            this.carInfoData0 = bArr2[2];
            this.carInfoData1 = bArr2[3];
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getSwitchStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(0, 1, getSwitchStatus(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(0, 2, getSwitchStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(0, 3, getSwitchStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]))));
        arrayList.add(new DriverUpdateEntity(0, 4, getLightStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getLightStatus(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._308_value_2);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._308_value_3);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._308_value_4);
        }
        return this.mContext.getString(R.string._308_value_1);
    }

    private String getSwitchStatus(boolean z) {
        if (z) {
            return this.mContext.getString(R.string.english_on);
        }
        return this.mContext.getString(R.string.english_off);
    }

    private void panelEvent0x02() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        switch (i) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(33);
                break;
            case 2:
                realKeyClick(34);
                break;
            case 3:
                realKeyClick(35);
                break;
            case 4:
                realKeyClick(36);
                break;
            case 5:
                realKeyClick(37);
                break;
            case 6:
                realKeyClick(38);
                break;
            case 7:
                if (this.isFM) {
                    realKeyClick(77);
                    break;
                } else {
                    realKeyClick(76);
                    break;
                }
            case 8:
                realKeyClick(130);
                break;
            case 9:
                realKeyClick(141);
                break;
            case 10:
                realKeyClick(30);
                break;
            case 11:
                realKeyClick(90);
                break;
            case 12:
                realKeyClick(91);
                break;
            case 13:
                UiMgr.sendAirCmd(4);
                break;
            case 14:
                realKeyClick(HotKeyConstant.K_SLEEP);
                break;
            case 15:
                realKeyClick(53);
                break;
            case 16:
            case 17:
                realKeyClick(31);
                break;
            case 18:
                realKeyClick(52);
                break;
            default:
                switch (i) {
                    case 32:
                        realKeyClick(49);
                        break;
                    case 33:
                        realKeyClick(45);
                        break;
                    case 34:
                        realKeyClick(46);
                        break;
                    case 35:
                        realKeyClick(47);
                        break;
                    case 36:
                        realKeyClick(48);
                        break;
                    case 37:
                    case 38:
                        startMainActivity(this.mContext);
                        break;
                    case 39:
                        realKeyClick(58);
                        break;
                    case 40:
                        if (this.isNight) {
                            setBacklightLevel(5);
                        } else {
                            setBacklightLevel(1);
                        }
                        this.isNight = !this.isNight;
                        break;
                    case 41:
                        if (MediaShareData.Screen.INSTANCE.getScreenBacklight() < 5) {
                            setBacklightLevel(MediaShareData.Screen.INSTANCE.getScreenBacklight() + 1);
                            break;
                        }
                        break;
                    case 42:
                        if (MediaShareData.Screen.INSTANCE.getScreenBacklight() > 1) {
                            setBacklightLevel(MediaShareData.Screen.INSTANCE.getScreenBacklight() - 1);
                            break;
                        }
                        break;
                    case 43:
                    case 44:
                        realKeyClick(this.mContext, 50);
                        break;
                    case 45:
                        realKeyClick3_2(this.mContext, 48, i, iArr[3]);
                        break;
                    case 46:
                        realKeyClick3_2(this.mContext, 47, i, iArr[3]);
                        break;
                    default:
                        switch (i) {
                            case 48:
                                realKeyClick3_1(this.mContext, 7, i, iArr[3]);
                                break;
                            case 49:
                                realKeyClick3_1(this.mContext, 8, i, iArr[3]);
                                break;
                            case 50:
                                realKeyClick3_2(this.mContext, 48, i, iArr[3]);
                                break;
                            case 51:
                                realKeyClick3_2(this.mContext, 47, i, iArr[3]);
                                break;
                        }
                }
        }
    }

    private void startActivityNow(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -113, 1, (byte) i, 0});
    }

    private void keyEvent0x01() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(7);
                break;
            case 2:
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(46);
                break;
            case 4:
                realKeyClick(45);
                break;
            case 5:
                realKeyClick(2);
                break;
            case 6:
                realKeyClick(14);
                break;
            case 7:
                realKeyClick(15);
                break;
        }
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.contains("FM")) {
            this.isFM = true;
        } else {
            this.isFM = false;
        }
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[6];
        if (i < 0 || i > 127) {
            return (i < 129 || i > 255) ? "" : (-(256 - i)) + getTempUnitC(this.mContext);
        }
        return i + getTempUnitC(this.mContext);
    }
}
