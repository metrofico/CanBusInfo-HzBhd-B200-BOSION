package com.hzbhd.canbus.car._406;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;
    private byte lastKnobValue;

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }

        return null;
    }

    public final void setFrame(int[] iArr) {

        this.frame = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        InitUtilsKt.setMContext(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), 19});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
            return;
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 33) {
            set0x21Data();
            return;
        }
        if (i != 34) {
            if (i == 49) {
                set0x31Data();
                return;
            } else {
                if (i != 240) {
                    return;
                }
                set0xF0Data(canbusInfo);
                return;
            }
        }
        byte b = canbusInfo[3];
        byte b2 = this.lastKnobValue;
        int iAbs = Math.abs(b - b2);
        int i2 = getFrame()[2];
        if (i2 != 1) {
            if (i2 == 2) {
                if (b > b2) {
                    DataHandleUtils.knob(context, 46, iAbs);
                } else if (b < b2) {
                    DataHandleUtils.knob(context, 45, iAbs);
                }
            }
        } else if (b > b2) {
            DataHandleUtils.knob(context, 7, iAbs);
        } else if (b < b2) {
            DataHandleUtils.knob(context, 8, iAbs);
        }
        this.lastKnobValue = canbusInfo[3];
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x21Data() {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._406.MsgMgr.set0x21Data():void");
    }

    private final void set0x31Data() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        updateOutDoorTemp(InitUtilsKt.getMContext(), (getFrame()[13] - 40) + " °C");
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = com.hzbhd.canbus.car._369.MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[4])) == 1;
        updateDoorView(InitUtilsKt.getMContext());
    }

    private final void set0x11Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = getFrame()[4];
        int i2 = 3;
        if (i == 1) {
            i2 = 7;
        } else if (i == 2) {
            i2 = 8;
        } else if (i != 3) {
            switch (i) {
                case 12:
                    i2 = 2;
                    break;
                case 13:
                    i2 = HotKeyConstant.K_UP_PICKUP;
                    break;
                case 14:
                    i2 = HotKeyConstant.K_DOWN_HANGUP;
                    break;
                default:
                    i2 = 0;
                    break;
            }
        }
        realKeyLongClick1(mContext, i2, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
