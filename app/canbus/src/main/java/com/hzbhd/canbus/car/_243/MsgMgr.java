package com.hzbhd.canbus.car._243;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i != 2) {
            if (i == 3 && !isAirMsgRepeat(bArr)) {
                setAirData0x03();
                return;
            }
            return;
        }
        set0x02WheelKey();
    }

    private void set0x02WheelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 1, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 21, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 20, iArr[3]);
            return;
        }
        if (i != 17) {
            switch (i) {
                case 7:
                    realKeyLongClick1(this.mContext, 62, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 141, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 33, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 34, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 35, iArr[3]);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 36, iArr[3]);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 37, iArr[3]);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 38, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 22:
                            realKeyLongClick1(this.mContext, 49, iArr[3]);
                            break;
                        case 23:
                            realKeyClick4(this.mContext, 7);
                            break;
                        case 24:
                            realKeyClick4(this.mContext, 8);
                            break;
                        default:
                            switch (i) {
                                case 28:
                                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                                    break;
                                case 29:
                                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                                    break;
                                case 30:
                                    realKeyLongClick1(this.mContext, 27, iArr[3]);
                                    break;
                                case 31:
                                    realKeyLongClick1(this.mContext, 29, iArr[3]);
                                    break;
                                case 32:
                                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                                    break;
                                case 33:
                                    realKeyLongClick1(this.mContext, 58, iArr[3]);
                                    break;
                                case 34:
                                    realKeyLongClick1(this.mContext, 30, iArr[3]);
                                    break;
                                case 35:
                                    realKeyLongClick1(this.mContext, 75, iArr[3]);
                                    break;
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 31, iArr[3]);
    }

    private void setAirData0x03() {
        GeneralAirData.max_cool = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 8);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 8) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        updateAirActivity(this.mContext, 1001);
    }

    private String resolveLeftAndRightTemp(int i) {
        return (1 > i || 29 < i) ? "" : i + "";
    }
}
