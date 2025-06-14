package com.hzbhd.canbus.car._258;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int data1 = 0;
    private static boolean isAirFirst = true;
    private final String SWC_DATA_1 = "swc_data_1";
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwcInfoCopy;
    private byte[] mCanbusAirInfoCopy;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            if (isSwcMsgReturn(bArr)) {
                return;
            }
            realKeyControl();
        } else if (i != 49) {
            if (i != 240) {
                return;
            }
            setVersionInfo();
        } else {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            setAirData0x31();
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
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
        updateAirActivity(this.mContext, 1001);
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realKeyControl() {
        int i = this.mCanBusInfoInt[4];
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
        if (i == 5) {
            realKeyClick(14);
            return;
        }
        if (i == 6) {
            realKeyClick(HotKeyConstant.K_VOICE_HANGUP);
        } else if (i == 8) {
            realKeyClick(45);
        } else {
            if (i != 9) {
                return;
            }
            realKeyClick(46);
        }
    }

    private boolean isData1Change() {
        return SharePreUtil.getIntValue(this.mContext, "swc_data_1", 0) != data1;
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    protected boolean isAirMsgRepeat(byte[] bArr) {
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

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
