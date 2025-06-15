package com.hzbhd.canbus.car._270;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
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
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        senMsg(17);
        senMsg(18);
        senMsg(49);
        senMsg(50);
        senMsg(65);
        senMsg(NfDef.STATE_3WAY_M_HOLD);
        senMsg(97);
    }

    private void senMsg(int i) {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte) i});
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
            setDoorData(context);
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
        } else {
            if (i == 50) {
                carBodyInfo();
                return;
            }
            if (i == 65) {
                radarInfo();
            } else if (i == 97) {
                setCarSetData();
            } else {
                if (i != 240) {
                    return;
                }
                setVersionInfo();
            }
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
            realKeyClick1(3);
        } else if (i == 4) {
            realKeyClick1(HotKeyConstant.K_SPEECH);
        } else if (i == 8) {
            realKeyClick1(45);
        } else if (i == 9) {
            realKeyClick1(46);
        } else if (i == 11) {
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
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
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
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1001);
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
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        Log.d("cwh", "radar");
        updateParkUi(null, this.mContext);
    }

    private void setDoorData(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 2, CommUtil.getStrByResId(context, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]) ? "open" : "close")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        byte[] bArr = this.mCanBusInfoByte;
        bArr[4] = (byte) (bArr[4] & 253);
        if (isDoorMsgReturn(bArr)) {
            return;
        }
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatBeltTie = true ^ DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCarSetData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(1, this.mCanBusInfoInt[2])))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(0, this.mCanBusInfoInt[2])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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
