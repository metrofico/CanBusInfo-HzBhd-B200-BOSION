package com.hzbhd.canbus.car._271;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int _271_up_dn_btn_data = 0;
    private static int _271_voice_btn_data = 0;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static int outDoorTemp;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int[] mRadarDataNow;
    private int mDifferentId = getCurrentCanDifferentId();
    private final String _271_IS_OUT_DOOR_TEMP = "_271_is_out_door_temp";
    public boolean isShowRadar = true;

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

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
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            keyControl0x11();
            return;
        }
        if (i == 18) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData();
            return;
        }
        if (i == 33) {
            if (getCurrentCanDifferentId() == 1) {
                setPanelButton();
                return;
            }
            return;
        }
        if (i == 34) {
            if (getCurrentCanDifferentId() == 1) {
                setOriginalPanel();
                return;
            }
            return;
        }
        if (i == 49) {
            if (getCurrentCanDifferentId() != 1 || isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
            return;
        }
        if (i == 50) {
            carBodyInfo();
            return;
        }
        if (i == 65) {
            radarInfo();
            return;
        }
        if (i != 97) {
            if (i != 240) {
                return;
            }
            setVersionInfo();
        } else if (this.mDifferentId == 0) {
            setCar1SetData();
        } else {
            setCar2SetData();
        }
    }

    private void keyControl0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick1(0);
        } else if (i == 1) {
            realKeyClick1(7);
        } else if (i == 2) {
            realKeyClick1(8);
        } else if (i == 3) {
            realKeyClick1(184);
        } else if (i == 4) {
            realKeyClick1(HotKeyConstant.K_VOICE_PICKUP);
        } else if (i == 8) {
            realKeyClick1(45);
        } else if (i == 9) {
            realKeyClick1(46);
        } else if (i == 12) {
            realKeyClick1(2);
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        outDoorTemp = this.mCanBusInfoInt[13];
        if (isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "_271_is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "_271_is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private String resolveOutDoorTem() {
        return ((float) ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
    }

    private void carBodyInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[4] * 256) + iArr[5]).append("rpm").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    private void radarInfo() {
        this.isShowRadar = this.mCanBusInfoInt[12] == 1;
        if (isRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(6, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDoorData() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setPanelButton() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(0);
            return;
        }
        if (i == 6) {
            realKeyLongClick1(50);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(3);
            return;
        }
        if (i == 40) {
            realKeyLongClick1(HotKeyConstant.K_PHONE_ON_OFF);
            return;
        }
        if (i == 43) {
            realKeyLongClick1(52);
            return;
        }
        if (i == 55) {
            realKeyLongClick1(58);
            return;
        }
        if (i == 84) {
            realKeyLongClick1(2);
        } else if (i == 2) {
            realKeyLongClick1(21);
        } else {
            if (i != 3) {
                return;
            }
            realKeyLongClick1(20);
        }
    }

    private void setOriginalPanel() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (iArr[3] > _271_voice_btn_data) {
                realKeyClick4(7);
                _271_voice_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < _271_voice_btn_data) {
                realKeyClick4(8);
                _271_voice_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (iArr[3] > _271_up_dn_btn_data) {
            realKeyClick4(46);
            _271_up_dn_btn_data = this.mCanBusInfoInt[3];
        }
        if (this.mCanBusInfoInt[3] < _271_up_dn_btn_data) {
            realKeyClick4(45);
            _271_up_dn_btn_data = this.mCanBusInfoInt[3];
        }
    }

    private void realKeyLongClick1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCar1SetData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getItemData(this.mCanBusInfoInt[2], 4, 4))).setProgress(getItemData(this.mCanBusInfoInt[2], 4, 4)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(getItemData(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(7, this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(6, this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(getItemData(this.mCanBusInfoInt[3], 4, 2))));
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(getItemData(this.mCanBusInfoInt[4], 6, 2))));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(5, this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(4, this.mCanBusInfoInt[4])))));
        arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(getItemData(this.mCanBusInfoInt[4], 1, 3))));
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(getItemData(this.mCanBusInfoInt[5], 5, 3))).setProgress(getItemData(this.mCanBusInfoInt[5], 5, 3) - 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCar2SetData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getItemData(this.mCanBusInfoInt[2], 4, 4))).setProgress(getItemData(this.mCanBusInfoInt[2], 4, 4)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(getItemData(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(getItemData(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(6, this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(getItemData(this.mCanBusInfoInt[3], 4, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i5, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusAirInfoCopy)) {
            return true;
        }
        this.mCanBusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private int getItemData(int i, int i2, int i3) {
        return DataHandleUtils.getIntFromByteWithBit(i, i2, i3);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusDoorInfoCopy)) {
            return true;
        }
        this.mCanBusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }
}
