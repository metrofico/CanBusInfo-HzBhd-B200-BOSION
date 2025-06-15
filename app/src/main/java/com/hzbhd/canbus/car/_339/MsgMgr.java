package com.hzbhd.canbus.car._339;

import android.content.Context;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    public byte[] mCanBusInfoByte;
    public int[] mCanBusInfoInt;

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
        if (i == 2) {
            panelKeyInfo(context);
            return;
        }
        if (i == 3) {
            airConditionInfo(context);
            return;
        }
        if (i == 7) {
            radarSwitchInfo();
            return;
        }
        if (i == 9) {
            reversingVideoInfo(context);
            return;
        }
        if (i == 13) {
            warningSoundState(context);
            return;
        }
        if (i == 40) {
            generalInfo(context);
            return;
        }
        if (i == 48) {
            steeringWheelAngleInfo(context);
        } else if (i == 33) {
            realKeyInfo(context);
        } else {
            if (i != 34) {
                return;
            }
            radarDataInfo(context);
        }
    }

    private void realKeyInfo(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick2(context, 0);
        }
        if (i == 1) {
            realKeyLongClick2(context, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick2(context, 8);
            return;
        }
        if (i == 5) {
            realKeyLongClick2(context, 3);
            return;
        }
        if (i == 7) {
            realKeyLongClick2(context, 2);
            return;
        }
        switch (i) {
            case 9:
                realKeyLongClick2(context, 14);
                break;
            case 10:
                realKeyLongClick2(context, 15);
                break;
            case 11:
                realKeyLongClick2(context, 45);
                break;
            case 12:
                realKeyLongClick2(context, 46);
                break;
        }
    }

    private void panelKeyInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(context, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(context, HotKeyConstant.K_SLEEP, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 21, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 20, iArr[3]);
            return;
        }
        if (i == 5) {
            realKeyLongClick1(context, 4, iArr[3]);
            return;
        }
        if (i == 7) {
            clickBand();
            return;
        }
        if (i != 20) {
            switch (i) {
                case 9:
                    realKeyLongClick1(context, 3, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(context, 33, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(context, 34, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(context, 35, iArr[3]);
                    break;
                case 13:
                    realKeyLongClick1(context, 36, iArr[3]);
                    break;
                case 14:
                    realKeyLongClick1(context, 37, iArr[3]);
                    break;
                case 15:
                    realKeyLongClick1(context, 38, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 22:
                            realKeyLongClick1(context, 49, iArr[3]);
                            break;
                        case 23:
                            volumeKnob(context, 7, iArr[3]);
                            break;
                        case 24:
                            volumeKnob(context, 8, iArr[3]);
                            break;
                        default:
                            switch (i) {
                                case 32:
                                    realKeyLongClick1(context, 128, iArr[3]);
                                    break;
                                case 33:
                                    realKeyLongClick1(context, 2, iArr[3]);
                                    break;
                                case 34:
                                    realKeyLongClick1(context, 30, iArr[3]);
                                    break;
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(context, 31, iArr[3]);
    }

    private void airConditionInfo(Context context) {
        boolean z;
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7)) {
            case 1:
                GeneralAirData.front_auto_wind_model = true;
                GeneralAirData.front_defog = false;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                break;
            case 2:
            case 7:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                break;
            case 3:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = false;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = false;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                break;
            case 5:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = false;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                break;
            case 6:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                break;
            case 8:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                break;
            case 9:
                GeneralAirData.front_auto_wind_model = false;
                GeneralAirData.front_defog = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                break;
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            int i = this.mCanBusInfoInt[4];
            if (i == 128) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i == 157) {
                GeneralAirData.front_left_temperature = "HI";
            } else if (i != 255) {
                GeneralAirData.front_left_temperature = ((intFromByteWithBit / 2.0d) + 18.0d) + "°C";
            }
            int i2 = this.mCanBusInfoInt[5];
            if (i2 == 128) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i2 == 157) {
                GeneralAirData.front_right_temperature = "HI";
            } else if (i2 != 255) {
                GeneralAirData.front_right_temperature = ((intFromByteWithBit2 / 2.0d) + 18.0d) + "°C";
            }
        } else {
            int i3 = this.mCanBusInfoInt[4];
            if (i3 == 128) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i3 == 157) {
                GeneralAirData.front_left_temperature = "HI";
            } else if (i3 != 255) {
                GeneralAirData.front_left_temperature = "Level " + intFromByteWithBit;
            }
            int i4 = this.mCanBusInfoInt[5];
            if (i4 == 128) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i4 == 157) {
                GeneralAirData.front_right_temperature = "HI";
            } else if (i4 != 255) {
                GeneralAirData.front_right_temperature = "Level " + intFromByteWithBit2;
            }
        }
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        if (intFromByteWithBit3 == 0) {
            GeneralAirData.left_set_seat_heat = false;
            GeneralAirData.front_left_seat_heat_level = 0;
            z = true;
        } else {
            z = true;
            GeneralAirData.left_set_seat_heat = true;
            GeneralAirData.front_left_seat_heat_level = intFromByteWithBit3;
        }
        if (intFromByteWithBit4 == 0) {
            GeneralAirData.right_set_seat_heat = false;
            GeneralAirData.front_right_seat_heat_level = 0;
        } else {
            GeneralAirData.right_set_seat_heat = z;
            GeneralAirData.front_right_seat_heat_level = intFromByteWithBit4;
        }
        int i5 = this.mCanBusInfoInt[7];
        if (i5 == 255) {
            updateOutDoorTemp(context, "--°C");
        } else if (DataHandleUtils.getBoolBit7(i5)) {
            updateOutDoorTemp(context, "-" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) + "°C");
        } else {
            updateOutDoorTemp(context, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) + "°C");
        }
        updateAirActivity(context, 1001);
    }

    private void radarSwitchInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void reversingVideoInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void warningSoundState(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.mCanBusInfoInt[2] == 0 ? 0 : 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void radarDataInfo(Context context) {
        int i;
        int i2 = 1;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        int i3 = iArr[2];
        if (i3 == 1) {
            i = 3;
        } else if (i3 == 2) {
            i = 2;
        } else if (i3 == 3) {
            i = 1;
        } else {
            if (i3 != 255) {
                throw new IllegalStateException("Unexpected value: " + this.mCanBusInfoInt[2]);
            }
            i = 0;
        }
        int i4 = iArr[3];
        if (i4 == 1) {
            i2 = 3;
        } else if (i4 == 2) {
            i2 = 2;
        } else if (i4 != 3) {
            if (i4 != 255) {
                throw new IllegalStateException("Unexpected value: " + this.mCanBusInfoInt[3]);
            }
            i2 = 0;
        }
        RadarInfoUtil.setRearRadarLocationData(3, i, 0, 0, i2);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void generalInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(context);
    }

    private void steeringWheelAngleInfo(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 550, 16);
        updateParkUi(null, context);
    }

    private void volumeKnob(Context context, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            realKeyClick4(context, i);
        }
    }

    private void clickBand() {
        String currentBand = getCurrentBand();
        currentBand.hashCode();
        switch (currentBand) {
            case "AM1":
                changeBandAm2();
                break;
            case "AM2":
                changeBandFm1();
                break;
            case "FM1":
                changeBandFm2();
                break;
            case "FM2":
                changeBandFm3();
                break;
            case "FM3":
                changeBandAm1();
                break;
        }
    }
}
