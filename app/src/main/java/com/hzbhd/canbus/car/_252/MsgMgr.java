package com.hzbhd.canbus.car._252;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            set0x21WheelKeyInfo();
            return;
        }
        if (i == 35) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            setAirData0x23();
        } else {
            if (i == 37) {
                setRadar();
                return;
            }
            if (i == 40) {
                if (isDoorMsgRepeat(bArr)) {
                    return;
                }
                setDoorData0x28();
            } else if (i == 54) {
                setAirData0x36();
            } else {
                if (i != 127) {
                    return;
                }
                setVersionInfo();
            }
        }
    }

    private void set0x21WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 5) {
            realKeyLongClick1(this.mContext, 184, iArr[3]);
            return;
        }
        if (i == 6) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i == 7) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i == 11) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 12) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i == 50) {
            realKeyLongClick1(this.mContext, 128, iArr[3]);
            return;
        }
        switch (i) {
            case 129:
                realKeyLongClick1(this.mContext, 52, iArr[3]);
                break;
            case 130:
                realKeyLongClick1(this.mContext, 50, iArr[3]);
                break;
            case 131:
                realKeyLongClick1(this.mContext, 59, iArr[3]);
                break;
            case 132:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_DISP, iArr[3]);
                break;
            case 133:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            case HotKeyConstant.K_SLEEP /* 134 */:
                realKeyLongClick1(this.mContext, 76, iArr[3]);
                break;
            case 135:
                realKeyLongClick1(this.mContext, 1, iArr[3]);
                break;
            case 136:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 137:
                realKeyLongClick1(this.mContext, 77, iArr[3]);
                break;
            case 138:
                realKeyLongClick1(this.mContext, 61, iArr[3]);
                break;
            case 139:
                realKeyLongClick1(this.mContext, 68, iArr[3]);
                break;
            case 140:
                realKeyLongClick1(this.mContext, 58, iArr[3]);
                break;
            case 141:
                realKeyLongClick1(this.mContext, 4, iArr[3]);
                break;
            case 142:
                forceReverse(this.mContext, true);
                break;
            case 143:
                realKeyLongClick1(this.mContext, 75, iArr[3]);
                break;
            case 144:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 145:
                realKeyLongClick1(this.mContext, 151, iArr[3]);
                break;
            case 146:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 147:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 148:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            case 149:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 150:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
            case 151:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 152:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_DISP, iArr[3]);
                break;
            case 153:
                realKeyLongClick1(this.mContext, 17, iArr[3]);
                break;
            default:
                switch (i) {
                    case 241:
                        realKeyLongClick1(this.mContext, 8, iArr[3]);
                        break;
                    case 242:
                        realKeyLongClick1(this.mContext, 7, iArr[3]);
                        break;
                    case 243:
                        realKeyLongClick1(this.mContext, 21, iArr[3]);
                        break;
                    case NfDef.STATE_3WAY_CALL_M_ACT_S_HOLD /* 244 */:
                        realKeyLongClick1(this.mContext, 20, iArr[3]);
                        break;
                }
        }
    }

    private void setRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        int i = this.mCanBusInfoInt[3];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirData0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            if (intFromByteWithBit > 40) {
                intFromByteWithBit = 40;
            }
            str = "-" + intFromByteWithBit;
        } else {
            if (intFromByteWithBit > 120) {
                intFromByteWithBit = 120;
            }
            str = intFromByteWithBit + "";
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTemp(int i) {
        return 17 == i ? "LO" : 32 == i ? "HI" : (17 > i || 31 < i) ? "" : i + getTempUnitC(this.mContext);
    }
}
