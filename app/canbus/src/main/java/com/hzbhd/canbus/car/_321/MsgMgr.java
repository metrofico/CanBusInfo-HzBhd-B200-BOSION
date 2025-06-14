package com.hzbhd.canbus.car._321;

import android.content.Context;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.text.DecimalFormat;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x22Data;
    private int[] m0x23Data;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mKeyStatus;
    private byte[] mRadarData;
    private byte[] mTrackData;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        switch (byteArrayToIntArray[1]) {
            case 32:
                set0x20WheelKey(context);
                break;
            case 33:
                set0x21AirData(context);
                break;
            case 34:
                set0x22RearRadarData(context);
                break;
            case 35:
                set0x23FrontRadarData(context);
                break;
        }
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        switch (i2) {
            case 0:
                realKeyLongClick1(context, 0);
                break;
            case 1:
                realKeyLongClick1(context, 7);
                break;
            case 2:
                realKeyLongClick1(context, 8);
                break;
            case 3:
                realKeyLongClick1(context, 45);
                break;
            case 4:
                realKeyLongClick1(context, 46);
                break;
            case 5:
                realKeyLongClick1(context, 14);
                break;
            case 6:
                realKeyLongClick1(context, 3);
                break;
            case 7:
                realKeyLongClick1(context, 2);
                break;
        }
    }

    private void set0x21AirData(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_wind_strong = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(context, 1001);
    }

    private void set0x22RearRadarData(Context context) {
        if (is0x22DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x23FrontRadarData(Context context) {
        if (is0x23DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String resolveAirTemperature(int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (i < 1 || i > 17) ? "" : this.mDecimalFormat0p0.format((i + 35) / 2.0f) + getTempUnitC(this.mContext);
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }
}
