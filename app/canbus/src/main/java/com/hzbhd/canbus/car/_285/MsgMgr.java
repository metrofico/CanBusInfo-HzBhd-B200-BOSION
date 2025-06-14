package com.hzbhd.canbus.car._285;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private int[] mTrackDataNow;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setAirData0x11();
            return;
        }
        if (i == 40) {
            setDoorData0x28();
            return;
        }
        if (i == 48) {
            setTrackInfo();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 33:
                    setSwc0x21();
                    break;
                case 34:
                    setRearRadarInfo();
                    break;
                case 35:
                    setFrontRadarInfo();
                    break;
            }
            return;
        }
        setVersionInfo();
    }

    private void setRearRadarInfo() {
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo() {
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        if (isTrackDataSame()) {
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte) iArr[3], (byte) iArr[2], 0, 12000, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x11() {
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 0;
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
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
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
        if (isOnlyOutDoorTempChange(this.mCanBusInfoInt[7])) {
            SharePreUtil.setIntValue(this.mContext, "_285_outdoor_temp", this.mCanBusInfoInt[7]);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[7];
        return i == 255 ? "--℃" : i == 254 ? "  " : (i - 40) + "℃";
    }

    private boolean isOnlyOutDoorTempChange(int i) {
        return SharePreUtil.getIntValue(this.mContext, "_285_outdoor_temp", 0) != i;
    }

    private void setSwc0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        switch (i) {
            case 9:
                realKeyClick(HotKeyConstant.K_VOICE_PICKUP);
                break;
            case 10:
                realKeyClick(15);
                break;
            case 11:
                realKeyClick(45);
                break;
            case 12:
                realKeyClick(46);
                break;
        }
    }

    private void setDoorData0x28() {
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 30 == i ? "HI" : (i <= 0 || i >= 30) ? "" : ((i + 34) * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isTrackDataSame() {
        if (Arrays.equals(this.mTrackDataNow, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTrackDataNow = Arrays.copyOf(iArr, iArr.length);
        return false;
    }
}
