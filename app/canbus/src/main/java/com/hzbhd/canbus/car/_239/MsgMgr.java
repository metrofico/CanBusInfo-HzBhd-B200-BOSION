package com.hzbhd.canbus.car._239;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -126, ByteCompanionObject.MAX_VALUE});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 40) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setDoorData0x28();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i == 48) {
            setTrack0x30();
            return;
        }
        if (i != 49) {
            switch (i) {
                case 33:
                    realKeyClick0x21();
                    break;
                case 34:
                    realKeyClick0x22();
                    break;
                case 35:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x23();
                        break;
                    }
                    break;
                case 36:
                    setRadar0x24();
                    break;
            }
            return;
        }
        setSettingData0x31();
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 23) {
            realKeyClick1(52);
            return;
        }
        if (i != 24) {
            switch (i) {
                case 0:
                    realKeyClick1(0);
                    break;
                case 1:
                    realKeyClick1(7);
                    break;
                case 2:
                    realKeyClick1(8);
                    break;
                case 3:
                    if (getCurrentCanDifferentId() == 2) {
                        realKeyClick1(HotKeyConstant.K_NEXT_HANGUP);
                        break;
                    } else {
                        realKeyClick1(20);
                        break;
                    }
                case 4:
                    if (getCurrentCanDifferentId() == 2) {
                        realKeyClick1(206);
                        break;
                    } else {
                        realKeyClick1(21);
                        break;
                    }
                case 5:
                    realKeyClick1(14);
                    break;
                case 6:
                    realKeyClick1(3);
                    break;
                case 7:
                    realKeyClick1(2);
                    break;
                case 8:
                    realKeyClick1(HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    realKeyClick1(14);
                    break;
                case 10:
                    realKeyClick1(15);
                    break;
                case 11:
                    realKeyClick1(45);
                    break;
                case 12:
                    realKeyClick1(46);
                    break;
                case 13:
                    realKeyClick1(47);
                    break;
                case 14:
                    realKeyClick1(48);
                    break;
                case 15:
                    realKeyClick1(49);
                    break;
            }
            return;
        }
        realKeyClick1(152);
    }

    private void realKeyClick0x22() {
        int i = this.mCanBusInfoInt[2];
        switch (i) {
            case 0:
                realKeyClick1(0);
                break;
            case 1:
                realKeyClick1(HotKeyConstant.K_SLEEP);
                break;
            case 2:
                realKeyClick1(21);
                break;
            case 3:
                realKeyClick1(20);
                break;
            case 4:
                realKeyClick1(58);
                break;
            case 5:
                realKeyClick1(4);
                break;
            case 6:
                realKeyClick1(50);
                break;
            case 7:
                realKeyClick1(129);
                break;
            case 8:
                requestMpeg();
                break;
            case 9:
                realKeyClick1(3);
                break;
            case 10:
                realKeyClick1(33);
                break;
            case 11:
                realKeyClick1(34);
                break;
            case 12:
                realKeyClick1(35);
                break;
            case 13:
                realKeyClick1(36);
                break;
            case 14:
                realKeyClick1(37);
                break;
            case 15:
                realKeyClick1(38);
                break;
            case 16:
                realKeyClick1(39);
                break;
            case 17:
                realKeyClick1(40);
                break;
            case 18:
                realKeyClick1(41);
                break;
            case 19:
                realKeyClick1(32);
                break;
            case 20:
                realKeyClick1(31);
                break;
            case 21:
                realKeyClick1(31);
                break;
            case 22:
                startMainActivity(this.mContext);
                break;
            case 23:
                realKeyClick1(HotKeyConstant.K_HOUR);
                break;
            case 24:
                realKeyClick1(66);
                break;
            case 25:
                realKeyClick1(75);
                break;
            default:
                switch (i) {
                    case 32:
                        realKeyClick1(49);
                        break;
                    case 33:
                        realKeyClick3_1(7);
                        break;
                    case 34:
                        realKeyClick3_1(8);
                        break;
                    case 35:
                        realKeyClick3(45);
                        break;
                    case 36:
                        realKeyClick3(46);
                        break;
                    case 37:
                        realKeyClick1(17);
                        break;
                    case 38:
                        realKeyClick1(45);
                        break;
                    case 39:
                        realKeyClick1(46);
                        break;
                    case 40:
                        realKeyClick1(141);
                        break;
                    case 41:
                        realKeyClick3_2(48);
                        break;
                    default:
                        switch (i) {
                            case 48:
                                realKeyClick3_2(47);
                                break;
                            case 49:
                                realKeyClick1(2);
                                break;
                            case 50:
                                realKeyClick1(14);
                                break;
                            case 51:
                                realKeyClick1(15);
                                break;
                            case 52:
                                realKeyClick1(75);
                                break;
                            case 53:
                                realKeyClick1(75);
                                break;
                            case 54:
                                realKeyClick1(52);
                                break;
                            case 55:
                                realKeyClick1(146);
                                break;
                        }
                }
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, CommUtil.subArrayToString(this.mCanBusInfoByte, 0, 16));
    }

    private void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = (byte) DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4), 0, 7);
        if (getCurrentCanDifferentId() == 3) {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp2(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp2(this.mCanBusInfoInt[4]);
        } else {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4], DataHandleUtils.getBoolBit0(this.mCanBusInfoByte[6]));
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5], DataHandleUtils.getBoolBit0(this.mCanBusInfoByte[6]));
        }
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRadar0x24() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDoorData0x28() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setTrack0x30() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(bArr[2], bArr[3], 0, 6144, 16);
        updateParkUi(null, this.mContext);
    }

    private void setSettingData0x31() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveLeftAndRightTemp2(int i) {
        return String.valueOf(DataHandleUtils.rangeNumber(i, 18, 32) - 17);
    }

    private String resolveLeftAndRightTemp(int i, boolean z) {
        if (i == 0) {
            return "LO";
        }
        if (31 == i) {
            return "HI";
        }
        if (255 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        if (1 <= i && 28 >= i) {
            if (z) {
                return (i + 59) + "°F";
            }
            return ((i * 0.5d) + 15.5d) + getTempUnitC(this.mContext);
        }
        if (32 > i || 36 < i) {
            return "";
        }
        if (z) {
            return (i + 59) + "°F";
        }
        return ((i * 0.5d) + 14.0d) + getTempUnitC(this.mContext);
    }
}
