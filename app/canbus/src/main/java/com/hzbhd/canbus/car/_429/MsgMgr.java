package com.hzbhd.canbus.car._429;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    private int nowBackLight = 100;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.equals("FM1")) {
            getUiMgr(this.mContext).sendMediaInfo0xD2(1);
            return;
        }
        if (str.equals("FM2")) {
            getUiMgr(this.mContext).sendMediaInfo0xD2(2);
            return;
        }
        if (str.equals("FM3")) {
            getUiMgr(this.mContext).sendMediaInfo0xD2(3);
        } else if (str.equals("AM1")) {
            getUiMgr(this.mContext).sendMediaInfo0xD2(4);
        } else if (str.equals("AM2")) {
            getUiMgr(this.mContext).sendMediaInfo0xD2(5);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        getUiMgr(this.mContext).sendMediaInfo0xD2(8);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        getUiMgr(this.mContext).sendMediaInfo0xD2(8);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        getUiMgr(this.mContext).sendMediaInfo0xD2(10);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).sendMediaInfo0xD2(11);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).sendMediaInfo0xD2(12);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).sendMediaInfo0xD2(13);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        getUiMgr(this.mContext).sendMediaInfo0xD2(13);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i != 114) {
            if (i == 115) {
                setAir0x73();
                return;
            } else {
                if (i != 240) {
                    return;
                }
                setVersion0xF0();
                return;
            }
        }
        setSpeed0x72();
        setSwc0x72();
        setBacklight0x72();
        setTrack0x72();
        setRadar0x72();
        updateSpeedInfo(this.mCanBusInfoInt[3]);
    }

    private void setVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAir0x73() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8])) {
            updateOutDoorTemp(this.mContext, this.df_2Decimal.format(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7) - 40));
        }
        this.mCanBusInfoInt[8] = 0;
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 1, 255);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 1, 255);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        updateAirActivity(this.mContext, 1004);
    }

    private void setRadar0x72() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[8], iArr[9], iArr[10], iArr[11]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrack0x72() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
            updateParkUi(null, this.mContext);
        } else {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
            updateParkUi(null, this.mContext);
        }
        updateParkUi(null, this.mContext);
    }

    private void setBacklight0x72() {
        int i = this.mCanBusInfoInt[5];
        if (i == this.nowBackLight) {
            return;
        }
        this.nowBackLight = i;
        setBacklightLevel((i / 20) + 1);
    }

    private void setSwc0x72() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick4(this.mContext, 0);
            return;
        }
        if (i == 1) {
            realKeyClick4(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyClick4(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyClick4(this.mContext, 3);
            return;
        }
        if (i == 5) {
            realKeyClick4(this.mContext, 14);
            return;
        }
        if (i == 6) {
            realKeyClick4(this.mContext, 15);
            return;
        }
        if (i == 10) {
            realKeyClick4(this.mContext, 2);
        } else if (i == 13) {
            realKeyClick4(this.mContext, 45);
        } else {
            if (i != 14) {
                return;
            }
            realKeyClick4(this.mContext, 46);
        }
    }

    private void setSpeed0x72() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_427_drive"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_427_drive1"), this.mCanBusInfoInt[3] + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isNotAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private String getTemperature(int i, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (DataHandleUtils.getBoolBit7(i)) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 0, 7) + 0.5d) + getTempUnitC(this.mContext);
        }
        return i + getTempUnitC(this.mContext);
    }
}
