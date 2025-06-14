package com.hzbhd.canbus.car._265;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int BackVideo = 1;
    private int FreqInt;
    private byte bandType;
    private int eachId;
    private byte freqHi;
    private byte freqLo;
    int[] mAirData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private int[] mTrackDataNow;
    private UiMgr uiMgr;

    private static boolean noNeedRepBtStatus(boolean z, int i) {
        return !z || i == 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        int i = this.eachId;
        if (i == 13 || i == 14 || i == 15) {
            sendCarType();
            Log.d("Lai", this.eachId + "");
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        int i;
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i2 = byteArrayToIntArray[1];
        if (i2 == 6) {
            int i3 = this.eachId;
            if (i3 == 1 || i3 == 2 || i3 == 10) {
                setRearAirInfo();
                return;
            }
            return;
        }
        if (i2 == 7) {
            setCarSpeed();
            return;
        }
        if (i2 == 8) {
            setEngineSpeed();
            return;
        }
        if (i2 == 20) {
            int i4 = this.eachId;
            if (i4 == 12 || i4 == 13 || i4 == 14 || i4 == 15) {
                return;
            }
            setCarSetData0x14();
            return;
        }
        if (i2 != 48) {
            if (i2 == 57) {
                if (this.eachId == 12) {
                    setCarSetting0x39();
                }
            } else {
                if (i2 != 64) {
                    if (i2 != 65) {
                        switch (i2) {
                            case 32:
                                realKeyControl();
                                return;
                            case 33:
                                if (isAirMsgReturn(bArr)) {
                                    return;
                                }
                                int i5 = this.eachId;
                                if (i5 == 1 || i5 == 2 || i5 == 10 || i5 == 11 || i5 == 12 || i5 == 13 || i5 == 14 || i5 == 15 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14) {
                                    setAirData0x21();
                                    return;
                                }
                                return;
                            case 34:
                                int i6 = this.eachId;
                                if (i6 == 1 || i6 == 2 || i6 == 10 || i6 == 11 || i6 == 12 || i6 == 13 || i6 == 14 || i6 == 15 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14) {
                                    setRearRadarInfo();
                                    return;
                                }
                                return;
                            case 35:
                                int i7 = this.eachId;
                                if (i7 == 1 || i7 == 2 || i7 == 10 || i7 == 11 || i7 == 12 || i7 == 13 || i7 == 14 || i7 == 15 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14) {
                                    setFrontRadarInfo();
                                    return;
                                }
                                return;
                            case 36:
                                int i8 = this.eachId;
                                if (i8 == 13 || i8 == 14 || i8 == 15) {
                                    return;
                                }
                                setDriveData0x24();
                                return;
                            case 37:
                                if (this.eachId == 12) {
                                    return;
                                }
                                setParkingState();
                                return;
                            case 38:
                                setTrackInfo();
                                return;
                            case 39:
                                int i9 = this.eachId;
                                if (i9 == 2 || i9 == 12 || i9 == 13 || i9 == 14 || i9 == 15 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14) {
                                    setIDriverInfo();
                                    return;
                                }
                                return;
                            case 40:
                                int i10 = this.eachId;
                                if (i10 == 10 || i10 == 12 || i10 == 13 || i10 == 14 || i10 == 15 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14) {
                                    OriginalCarTime0x28();
                                    return;
                                }
                                return;
                            case 41:
                                if (this.eachId == 11) {
                                    setMousebuttoninfo0x30();
                                    break;
                                }
                                break;
                            default:
                                return;
                        }
                    }
                }
                if (!isDoorMsgReturn(bArr) || (i = this.eachId) == 1 || i == 12) {
                    return;
                }
                setDoorData0x41();
                return;
            }
            int i11 = this.eachId;
            if (i11 == 13 || i11 == 14 || i11 == 15 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14) {
                setAUXInfo();
            }
            if (!isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData0x41();
            return;
        }
        setVersionInfo();
    }

    private void OriginalCarTime0x28() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_Time");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, resolveTime(iArr[2], iArr[3], iArr[4], iArr[5], iArr[6], iArr[7])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAUXInfo() {
        Resources resources;
        int i;
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_setting_car_info22");
        if (this.mCanBusInfoInt[2] == 0) {
            resources = this.mContext.getResources();
            i = R.string._246_setting_car_info23;
        } else {
            resources = this.mContext.getResources();
            i = R.string._246_setting_car_info24;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, resources.getString(i)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int i2 = this.mCanBusInfoInt[2];
        if (i2 == 0) {
            enterAuxIn2();
        } else if (i2 == 1) {
            exitAuxIn2();
        }
    }

    private void setMousebuttoninfo0x30() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1 && i == 1) {
            switch (iArr[3]) {
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
                    realKeyClick(20);
                    break;
                case 4:
                    realKeyClick(21);
                    break;
                case 5:
                    realKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                    break;
                case 7:
                    realKeyClick(128);
                    break;
                case 8:
                    realKeyClick(76);
                    break;
                case 9:
                    realKeyClick(1);
                    break;
                case 10:
                    realKeyClick(3);
                    break;
                case 11:
                    if (this.BackVideo == 1) {
                        forceReverse(this.mContext, true);
                        this.BackVideo = 2;
                        break;
                    } else {
                        forceReverse(this.mContext, false);
                        this.BackVideo = 1;
                        break;
                    }
                case 13:
                    Intent intent = new Intent();
                    intent.setClass(this.mContext, SettingActivity.class);
                    this.mContext.startActivity(intent);
                    break;
                case 14:
                    realKeyClick(57);
                    break;
                case 16:
                    realKeyClick(45);
                    break;
                case 17:
                    realKeyClick(46);
                    break;
                case 18:
                    realKeyClick(47);
                    break;
                case 19:
                    realKeyClick(48);
                    break;
                case 20:
                    realKeyClick(49);
                    break;
                case 21:
                    realKeyClick(50);
                    break;
                case 22:
                    realKeyClick(52);
                    break;
            }
        }
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2[2] == 2) {
            if (this.mCanBusInfoByte[3] > 0) {
                DataHandleUtils.knob(this.mContext, 7, iArr2[3]);
            }
            if (this.mCanBusInfoByte[3] < 0) {
                DataHandleUtils.knob(this.mContext, 8, 256 - this.mCanBusInfoInt[3]);
            }
        }
    }

    private void setCarSetting0x39() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i != 1) {
            if (i == 2) {
                int i2 = iArr[3];
                if (i2 == 1) {
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_332_setting_9"), Integer.valueOf(this.mCanBusInfoInt[4])));
                } else if (i2 == 2) {
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_332_setting_6"), Integer.valueOf(this.mCanBusInfoInt[4])));
                } else if (i2 == 3) {
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_332_setting_5"), Integer.valueOf(this.mCanBusInfoInt[4])));
                }
            } else if (i == 3) {
                int i3 = iArr[3];
                if (i3 == 1) {
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_337_setting_42"), Integer.valueOf(this.mCanBusInfoInt[4])));
                } else if (i3 == 2) {
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_189_car_setting_A"), Integer.valueOf(this.mCanBusInfoInt[4])));
                }
            }
        } else if (iArr[3] == 1) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_setting_car_info32"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "language_setup"), Integer.valueOf(this.mCanBusInfoInt[4])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRearRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        if (isTrackDataSame()) {
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte) iArr[2], (byte) iArr[3], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyControl() {
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
                realKeyClick(45);
                break;
            case 4:
                realKeyClick(46);
                break;
            case 5:
                realKeyClick(14);
                break;
            case 6:
                realKeyClick(15);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 8:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick(50);
                break;
            case 10:
                realKeyClick(3);
                break;
            case 11:
                realKeyClick(204);
                break;
            case 12:
                realKeyClick(45);
                break;
            case 13:
                realKeyClick(46);
                break;
            case 14:
                realKeyClick(47);
                break;
            case 15:
                realKeyClick(48);
                break;
            case 16:
                realKeyClick(52);
                break;
            case 17:
                realKeyClick(49);
                break;
            case 18:
                realKeyClick(39);
                break;
        }
    }

    private void setIDriverInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i != 32) {
            if (i != 33) {
                switch (i) {
                    case 0:
                        realKeyClick(0);
                        return;
                    case 1:
                        realKeyClick(21);
                        return;
                    case 2:
                        realKeyClick(20);
                        return;
                    case 3:
                        realKeyClick(45);
                        return;
                    case 4:
                        realKeyClick(46);
                        return;
                    case 5:
                        realKeyClick(47);
                        return;
                    case 6:
                        realKeyClick(48);
                        return;
                    case 7:
                        realKeyClick(49);
                        return;
                    case 8:
                        realKeyClick(151);
                        return;
                    case 9:
                        realKeyClick(50);
                        return;
                    case 10:
                        realKeyClick(1);
                        return;
                    case 11:
                        realKeyClick(43);
                        return;
                    default:
                        switch (i) {
                        }
                }
            }
            DataHandleUtils.knob(this.mContext, 8, iArr[3]);
            return;
        }
        DataHandleUtils.knob(this.mContext, 7, iArr[3]);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x21() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2);
        if (intFromByteWithBit == 1) {
            GeneralAirData.center_wheel = "DIFFUSE";
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.center_wheel = "MEDIUM";
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.center_wheel = "FOCUS";
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearAirInfo() {
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.rear_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        updateAirActivity(this.mContext, 1002);
    }

    private void setDoorData0x41() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            i = iArr.length > 3 ? iArr[3] : 0;
            GeneralDoorData.isShowHandBrake = true;
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSubShowSeatBelt = true;
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(i);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(i);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(i);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(i);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(i);
            GeneralDoorData.isSeatBeltTie = !DataHandleUtils.getBoolBit7(i);
            GeneralDoorData.isSubSeatBeltTie = !DataHandleUtils.getBoolBit6(i);
            GeneralDoorData.isHandBrakeUp = !DataHandleUtils.getBoolBit5(i);
            updateDoorView(this.mContext);
            return;
        }
        if (i == 2) {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
            int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
            int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append((iArr2[5] * 65536) + (iArr2[6] * 256) + iArr2[7]).append(" Km").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            return;
        }
        if (i == 128) {
            arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set1", "_246_setting_car_info28"), Integer.valueOf(this.mCanBusInfoInt[4])));
            updateGeneralSettingData(arrayList2);
            updateSettingActivity(null);
        } else {
            if (i != 129) {
                return;
            }
            int i2 = iArr[5];
            int i3 = (i2 != 1 && i2 == 2) ? 1 : 0;
            int i4 = iArr[6];
            if (i4 != 6 && i4 == 7) {
                i = 1;
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_265_oreginal_camera_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_265_oreginal_camera_setting", "_265_oreginal_camera_setting1"), Integer.valueOf(i3)));
            arrayList3.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_265_oreginal_camera_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_265_oreginal_camera_setting", "_265_oreginal_camera_setting2"), Integer.valueOf(i)));
            updateGeneralSettingData(arrayList3);
            updateSettingActivity(null);
        }
    }

    private void setDriveData0x24() {
        Resources resources;
        int i;
        Resources resources2;
        int i2;
        Resources resources3;
        int i3;
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "light_info");
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            resources = this.mContext.getResources();
            i = R.string.open;
        } else {
            resources = this.mContext.getResources();
            i = R.string.close;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, resources.getString(i)));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "gear_position");
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            resources2 = this.mContext.getResources();
            i2 = R.string.gear_p;
        } else {
            resources2 = this.mContext.getResources();
            i2 = R.string.gear_not_p;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, resources2.getString(i2)));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "reverse_state");
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            resources3 = this.mContext.getResources();
            i3 = R.string.reversing;
        } else {
            resources3 = this.mContext.getResources();
            i3 = R.string.non_reverse;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, resources3.getString(i3)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setParkingState() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set1", "_246_paring_info2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_41_park_assist_system_status"), resolveParkingSystem(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
    }

    private String resolveParkingSystem(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._332_setting_state1);
        }
        return this.mContext.getResources().getString(R.string._332_setting_state2);
    }

    private void setCarSpeed() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append((iArr[2] * 256) + iArr[3]).append("KM/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo((iArr2[2] * 256) + iArr2[3]);
    }

    private void setEngineSpeed() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append((iArr[2] * 256) + iArr[3]).append("RPM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i >= 0 && i < 51) {
            setBacklightLevel(5);
        } else if (i >= 51 && i < 102) {
            setBacklightLevel(4);
        } else if (i >= 102 && i < 153) {
            setBacklightLevel(3);
        } else if (i >= 153 && i < 204) {
            setBacklightLevel(2);
        } else if (i >= 204 && i <= 255) {
            setBacklightLevel(1);
        }
        int i2 = this.mCanBusInfoInt[2];
        if (i2 == 0) {
            string = this.mContext.getResources().getString(R.string.minimum);
        } else if (i2 == 255) {
            string = this.mContext.getResources().getString(R.string.max);
        } else {
            string = this.mCanBusInfoInt[2] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_333_setting_carState"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "keyboard_backlight_adjustment"), string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    public void sourceSwitchDestroy() {
        if (isSendSourceState()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 34});
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            return;
        }
        int i = this.eachId;
        if (i == 2 || i == 12 || i == 16) {
            CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -60, 0}, 36));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (isSendSourceState()) {
            setVwRadioInfo(str, str2);
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1});
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte) i});
            return;
        }
        int i3 = this.eachId;
        if (i3 == 2 || i3 == 12 || i3 == 16) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -60, getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5)}, str2.getBytes()), 36));
        }
    }

    private void setVwRadioInfo(String str, String str2) throws NumberFormatException {
        if (str.equals("AM2") || str.equals("MW")) {
            this.bandType = (byte) 18;
        } else if (str.equals("AM1") || str.equals("LW")) {
            int i = Integer.parseInt(str2);
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
            this.bandType = (byte) 17;
        } else if (str.equals("FM1")) {
            this.bandType = (byte) 1;
        } else if (str.equals("FM2")) {
            this.bandType = (byte) 2;
        } else if (str.equals("FM3") || str.equals("OIRT")) {
            this.bandType = (byte) 3;
        }
        getFreqByteHiLo(str, str2);
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (isSendSourceState()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -64, 2, 33});
            int minute = getMinute(i);
            int second = getSecond(i);
            if (i6 == 1 || i6 == 5) {
                i3 = i4;
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), new byte[]{22, -61, (byte) i3, (byte) i5, 0, 0, (byte) minute, (byte) second});
            return;
        }
        int i8 = this.eachId;
        if (i8 == 2 || i8 == 12 || i8 == 16) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), Util.makeBytesFixedLength(new byte[]{22, -60, 6}, 36));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        if (isSendSourceState()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), new byte[]{22, -64, 3, 34});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        if (isSendSourceState()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), new byte[]{22, -64, 7, 48});
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            return;
        }
        int i = this.eachId;
        if (i == 2 || i == 12 || i == 16) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 8}, 36));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        if (isSendSourceState()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), new byte[]{22, -64, 3, 34});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        if (isSendSourceState()) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -64, 11, 64});
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            return;
        }
        int i = this.eachId;
        if (i == 2 || i == 12 || i == 16) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 11}, 36));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (isSendSourceState()) {
            if (b == 9) {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -64, 9, 17});
            } else {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -64, 8, 17});
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
            return;
        }
        int i4 = this.eachId;
        if (i4 == 2 || i4 == 12 || i4 == 16) {
            byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -60, 9}, str6.getBytes());
            byte[] bArrByteMerger2 = DataHandleUtils.byteMerger(new byte[]{32, 45, 32}, str4.getBytes());
            byte[] bArrByteMerger3 = DataHandleUtils.byteMerger(bArrByteMerger, bArrByteMerger2);
            byte[] bArrByteMerger4 = DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -60, 10}, str6.getBytes()), bArrByteMerger2);
            if (b == 9) {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.limitDataLength(bArrByteMerger4, 36));
            } else {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.limitDataLength(bArrByteMerger3, 36));
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (isSendSourceState()) {
            if (b == 9) {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, 9, 17});
            } else {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -64, 16, 17});
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, b4, b5});
            return;
        }
        int i5 = this.eachId;
        if (i5 == 2 || i5 == 12 || i5 == 16) {
            if (b == 9) {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 10, (byte) ((i2 >> 8) & 255), (byte) i, b6}, 36));
            } else {
                sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(new byte[]{22, -60, 9, (byte) ((i2 >> 8) & 255), (byte) i, b6}, 36));
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        int i11;
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i12 = this.eachId;
        if (i12 == 13 || i12 == 14 || i12 == 15) {
            sendCarType();
        }
        if (getCurrentCanDifferentId() == 0 || getCurrentCanDifferentId() == 1 || (i11 = this.eachId) == 10 || i11 == 12) {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, (byte) (1 ^ (z ? 1 : 0)), (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        if (telephoneStatus()) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 1, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        if (telephoneStatus()) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 2, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        if (telephoneStatus()) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 3, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        if (telephoneStatus()) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58, 0, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (!telephoneStatus() || noNeedRepBtStatus(z, i)) {
            return;
        }
        Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, false);
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    private String resolveOutDoorTem() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (int) (((iArr[3] * 256) + iArr[4]) * 0.1d);
        if (i >= 127) {
            return "-" + (i - 6553) + getTempUnitC(this.mContext);
        }
        return i + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 254 == i ? "HI" : (i < 32 || i > 56) ? "" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean telephoneStatus() {
        int i = this.eachId;
        return i == 5 || i == 6 || i == 7 || i == 10 || i == 12 || i == 16 || i == 17;
    }

    private boolean isSendSourceState() {
        int i;
        return getCurrentCanDifferentId() == 2 || getCurrentCanDifferentId() == 3 || getCurrentCanDifferentId() == 4 || getCurrentCanDifferentId() == 5 || getCurrentCanDifferentId() == 6 || (i = this.eachId) == 10 || i == 13 || i == 14 || i == 15 || i == 17 || getCurrentCanDifferentId() == 12 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 14;
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

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isTrackDataSame() {
        if (Arrays.equals(this.mTrackDataNow, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTrackDataNow = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    private String resolveTime(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int[] iArr = {i, i2, i3, i4, i5, i6};
        String[] strArr = {"", "", "", "", "", ""};
        if (DataHandleUtils.getBoolBit7(i4) && (i7 = iArr[3]) > 23) {
            iArr[3] = i7 - 128;
        }
        for (int i8 = 0; i8 < 6; i8++) {
            if (iArr[i8] < 10) {
                strArr[i8] = "0" + iArr[i8];
            } else {
                strArr[i8] = iArr[i8] + "";
            }
        }
        return "20" + strArr[0] + "-" + strArr[1] + "-" + strArr[2] + "-" + strArr[3] + "-" + strArr[4] + "-" + strArr[5];
    }

    private void sendCarType() {
        if (this.eachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
        }
        if (this.eachId == 14) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
        }
        if (this.eachId == 15) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
