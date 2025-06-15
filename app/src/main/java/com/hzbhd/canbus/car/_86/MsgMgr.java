package com.hzbhd.canbus.car._86;

import android.content.Context;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int[] mRearRadarData;
    private UiMgr mUiMgr;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(this.mContext).makeConnection();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int i3;
        float f;
        super.radioInfoChange(i, str, str2, str3, i2);
        getUiMgr(this.mContext).sendSourceInfo0xC0(1, 1);
        if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            i3 = 0;
            f = Float.parseFloat(str2) * 100.0f;
        } else {
            i3 = 16;
            f = Float.parseFloat(str2);
        }
        int i4 = (int) f;
        getUiMgr(this.mContext).sendRadioInfo0xC2(i3, getLsb(i4), getMsb(i4), i);
        getUiMgr(this.mContext).sendMediaInfo0xC3(1, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).sendSourceInfo0xC0(2, 48);
        getUiMgr(this.mContext).sendMediaInfo0xC3(48, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).sendSourceInfo0xC0(7, 48);
        getUiMgr(this.mContext).sendMediaInfo0xC3(48, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        getUiMgr(this.mContext).sendSourceInfo0xC0(0, 48);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        getUiMgr(this.mContext).sendVolInfo0xC4(i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14BachLightInfo();
            return;
        }
        if (i == 37) {
            set0x25ParkSytem();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo();
            return;
        }
        if (i != 64) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    set0x21AirInfo();
                    break;
                case 34:
                    set0x22RearRadarInfo();
                    break;
                case 35:
                    set0x23FrontRadarInfo();
                    break;
            }
            return;
        }
        set0x40MediaTypeInfo();
    }

    private void set0x21AirInfo() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        int i = this.mCanBusInfoInt[4];
        if (i == 31) {
            GeneralAirData.front_left_temperature = "LO";
        } else if (i == 57) {
            GeneralAirData.front_left_temperature = "HI";
        } else {
            GeneralAirData.front_left_temperature = (this.mCanBusInfoInt[4] * 0.5d) + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 31) {
            GeneralAirData.front_right_temperature = "LO";
        } else if (i2 == 57) {
            GeneralAirData.front_right_temperature = "HI";
        } else {
            GeneralAirData.front_right_temperature = (this.mCanBusInfoInt[5] * 0.5d) + getTempUnitC(this.mContext);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x25ParkSytem() {
        String str = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? "ON" : "OFF";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_paring_info"), str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x23FrontRadarInfo() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[2]), getRadarDistance(this.mCanBusInfoInt[3]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22RearRadarInfo() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[2]), getRadarDistance(this.mCanBusInfoInt[3]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x40MediaTypeInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            startOtherActivity(this.mContext, HzbhdComponentName.RadioActivity);
        } else if (i == 1) {
            startOtherActivity(this.mContext, HzbhdComponentName.MediaMusic);
        } else {
            if (i != 2) {
                return;
            }
            startOtherActivity(this.mContext, HzbhdComponentName.AuxMainActivity);
        }
    }

    private void set0x14BachLightInfo() {
        setBacklightLevel((this.mCanBusInfoInt[2] / 25) + 1);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 3) {
            buttonKey(45);
            return;
        }
        if (i == 4) {
            buttonKey(46);
            return;
        }
        if (i == 5) {
            buttonKey(14);
            return;
        }
        if (i == 7) {
            buttonKey(2);
        } else if (i == 10) {
            buttonKey(15);
        } else {
            if (i != 11) {
                return;
            }
            buttonKey(3);
        }
    }

    private int getRadarDistance(int i) {
        if (i == 0) {
            return 0;
        }
        return (i / 3) + 1;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
