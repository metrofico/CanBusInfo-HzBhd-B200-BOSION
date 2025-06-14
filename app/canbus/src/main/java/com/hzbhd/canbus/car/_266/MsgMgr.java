package com.hzbhd.canbus.car._266;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
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
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static int swc_data3;
    private static int up_dn_btn_data;
    private static int voice_btn_data;
    private final String SWC_DATA_3 = "swc_data_3";
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusBtnPanelInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwcInfoCopy;
    private Context mContext;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        sendMsgWhenInit();
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 33});
    }

    private void sendMsgWhenInit() {
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 120});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 18});
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 33});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -102});
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
            if (isSwcMsgReturn(bArr)) {
                return;
            }
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
            setPanelButton();
            return;
        }
        if (i == 34) {
            if (isBtnPanelMsgReturn(bArr)) {
                return;
            }
            setOriginalPanel();
            return;
        }
        if (i == 38) {
            setCarType();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
        } else if (i == 65) {
            setRadarData0x41();
        } else if (i == 120) {
            setCarSetData();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo();
        }
    }

    private void setRadarData0x41() {
        RadarInfoUtil.setRearRadarLocationData(4, return10(this.mCanBusInfoInt[2]), return10(this.mCanBusInfoInt[3]), return10(this.mCanBusInfoInt[4]), return10(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private int return10(int i) {
        if (i == 255) {
            return 255;
        }
        return new int[]{4, 3, 2, 1}[i - 1];
    }

    private void setOriginalPanel() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (iArr[3] > voice_btn_data) {
                realKeyClick4(this.mContext, 7);
                voice_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < voice_btn_data) {
                realKeyClick4(this.mContext, 8);
                voice_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (iArr[3] > up_dn_btn_data) {
            realKeyClick4(this.mContext, 46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
        if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            realKeyClick4(this.mContext, 45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
    }

    private void setCarType() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 8, this.mCanBusInfoInt[3] == 2 ? "_266_car_type" : "invalid"));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void keyControl0x11() {
        swc_data3 = this.mCanBusInfoInt[5];
        if (isData3Change()) {
            SharePreUtil.setIntValue(this.mContext, "swc_data_3", swc_data3);
            int i = this.mCanBusInfoInt[4];
            if (i == 0) {
                realKeyClick1(0);
            } else if (i != 1) {
                if (i == 2) {
                    realKeyClick1(8);
                } else if (i == 3) {
                    realKeyClick1(3);
                } else if (i == 5) {
                    realKeyClick1(14);
                } else if (i == 6) {
                    realKeyClick1(15);
                } else if (i == 8) {
                    realKeyClick1(45);
                } else if (i == 9) {
                    realKeyClick1(46);
                } else if (i == 12) {
                    realKeyClick1(2);
                }
            }
            realKeyClick1(7);
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private boolean isData3Change() {
        return SharePreUtil.getIntValue(this.mContext, "swc_data_3", 0) != swc_data3;
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[4], iArr[5]);
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
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
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        if (getCurrentCanDifferentId() != 2) {
            updateAirActivity(this.mContext, 1004);
        }
    }

    private void setDoorData() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setPanelButton() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SLEEP, iArr[3]);
            return;
        }
        if (i == 36) {
            realKeyLongClick1(this.mContext, 59, iArr[3]);
            return;
        }
        if (i == 43) {
            realKeyLongClick1(this.mContext, 52, iArr[3]);
        } else if (i == 51) {
            realKeyLongClick1(this.mContext, 129, iArr[3]);
        } else {
            if (i != 76) {
                return;
            }
            realKeyLongClick1(this.mContext, 14, iArr[3]);
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCarSetData() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            arrayList.add(new SettingUpdateEntity(0, i, Integer.valueOf(getIntByBoolean(DataHandleUtils.getBoolBit(i2, this.mCanBusInfoInt[9])))).setEnable(DataHandleUtils.getBoolBit(i2, this.mCanBusInfoInt[4])));
            i = i2;
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void languageSet(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(i)));
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

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isBtnPanelMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusBtnPanelInfoCopy)) {
            return true;
        }
        this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
