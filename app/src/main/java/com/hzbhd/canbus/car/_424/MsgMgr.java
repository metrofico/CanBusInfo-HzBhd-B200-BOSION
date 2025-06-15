package com.hzbhd.canbus.car._424;

import android.content.Context;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static int outDoorTemp;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int mDifferentID;
    DecimalFormat decimalFormat = new DecimalFormat("0.0000");
    private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
    List<TireUpdateEntity> MTire0 = new ArrayList();
    List<TireUpdateEntity> MTire1 = new ArrayList();
    List<TireUpdateEntity> MTire2 = new ArrayList();
    List<TireUpdateEntity> MTire3 = new ArrayList();
    private int TirePressure1000 = 0;
    private int TirePressure0100 = 0;
    private int TirePressure0010 = 0;
    private int TirePressure0001 = 0;
    private int TireTempture1000 = 0;
    private int TireTempture0100 = 0;
    private int TireTempture0010 = 0;
    private int TireTempture0001 = 0;
    Boolean PressureCoefficient1 = false;
    Boolean errorAlarm1 = false;
    Boolean PressureAlarm1 = false;
    Boolean QuickLeakAlarm1 = false;
    Boolean TemperatureAlarm1 = false;
    Boolean PressureCoefficient2 = false;
    Boolean errorAlarm2 = false;
    Boolean PressureAlarm2 = false;
    Boolean QuickLeakAlarm2 = false;
    Boolean TemperatureAlarm2 = false;
    Boolean PressureCoefficient3 = false;
    Boolean errorAlarm3 = false;
    Boolean PressureAlarm3 = false;
    Boolean QuickLeakAlarm3 = false;
    Boolean TemperatureAlarm3 = false;
    Boolean PressureCoefficient4 = false;
    Boolean errorAlarm4 = false;
    Boolean PressureAlarm4 = false;
    Boolean QuickLeakAlarm4 = false;
    Boolean TemperatureAlarm4 = false;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mDifferentID = getCurrentCanDifferentId();
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            setWheelKey0x11();
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
            return;
        }
        if (i == 65) {
            setRadarInfo0x41();
        } else if (i == 72) {
            setTireInfo0x48();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setTireInfo0x48() {
        GeneralTireData.isHaveSpareTire = false;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        if (i == 1) {
            this.TirePressure1000 = iArr[4];
            this.TireTempture1000 = iArr[5];
            this.PressureCoefficient1 = Boolean.valueOf(DataHandleUtils.getBoolBit4(iArr[6]));
            this.errorAlarm1 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]));
            this.PressureAlarm1 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]));
            this.QuickLeakAlarm1 = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]));
            this.TemperatureAlarm1 = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]));
        } else if (i == 2) {
            this.TirePressure0100 = iArr[4];
            this.TireTempture0100 = iArr[5];
            this.PressureCoefficient2 = Boolean.valueOf(DataHandleUtils.getBoolBit4(iArr[6]));
            this.errorAlarm2 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]));
            this.PressureAlarm2 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]));
            this.QuickLeakAlarm2 = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]));
            this.TemperatureAlarm2 = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]));
        } else if (i == 3) {
            this.TirePressure0010 = iArr[4];
            this.TireTempture0010 = iArr[5];
            this.PressureCoefficient3 = Boolean.valueOf(DataHandleUtils.getBoolBit4(iArr[6]));
            this.errorAlarm3 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]));
            this.PressureAlarm3 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]));
            this.QuickLeakAlarm3 = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]));
            this.TemperatureAlarm3 = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]));
        } else if (i == 4) {
            this.TirePressure0001 = iArr[4];
            this.TireTempture0001 = iArr[5];
            this.PressureCoefficient4 = Boolean.valueOf(DataHandleUtils.getBoolBit4(iArr[6]));
            this.errorAlarm4 = Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]));
            this.PressureAlarm4 = Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]));
            this.QuickLeakAlarm4 = Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]));
            this.TemperatureAlarm4 = Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]));
        }
        this.MTire0.add(getTireEntity(0, this.TirePressure1000, this.TireTempture1000, this.PressureCoefficient1.booleanValue(), this.errorAlarm1.booleanValue(), this.PressureAlarm1.booleanValue(), this.QuickLeakAlarm1.booleanValue(), this.TemperatureAlarm1.booleanValue()));
        this.MTire1.add(getTireEntity(1, this.TirePressure0100, this.TireTempture0100, this.PressureCoefficient2.booleanValue(), this.errorAlarm2.booleanValue(), this.PressureAlarm2.booleanValue(), this.QuickLeakAlarm2.booleanValue(), this.TemperatureAlarm2.booleanValue()));
        this.MTire2.add(getTireEntity(2, this.TirePressure0010, this.TireTempture0010, this.PressureCoefficient3.booleanValue(), this.errorAlarm3.booleanValue(), this.PressureAlarm3.booleanValue(), this.QuickLeakAlarm3.booleanValue(), this.TemperatureAlarm3.booleanValue()));
        this.MTire3.add(getTireEntity(3, this.TirePressure0001, this.TireTempture0001, this.PressureCoefficient4.booleanValue(), this.errorAlarm4.booleanValue(), this.PressureAlarm4.booleanValue(), this.QuickLeakAlarm4.booleanValue(), this.TemperatureAlarm4.booleanValue()));
        GeneralTireData.dataList = mergeList(this.MTire0, this.MTire1, this.MTire2, this.MTire3);
        updateTirePressureActivity(null);
    }

    private TireUpdateEntity getTireEntity(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        double d;
        String str;
        String strByResId;
        int i4;
        int i5;
        String strByResId2;
        int i6;
        String strByResId3;
        int i7;
        String strByResId4;
        if (z) {
            d = 3.137d;
            str = "3.137";
        } else {
            d = 1.3725d;
            str = "1.3725";
        }
        if (z2) {
            strByResId = CommUtil.getStrByResId(this.mContext, "_424_Warning_2");
            i4 = 1;
        } else {
            strByResId = CommUtil.getStrByResId(this.mContext, "_424_Warning_1");
            i4 = 0;
        }
        if (z3) {
            strByResId2 = CommUtil.getStrByResId(this.mContext, "_424_Warning_4");
            i5 = 1;
        } else {
            i5 = i4;
            strByResId2 = CommUtil.getStrByResId(this.mContext, "_424_Warning_3");
        }
        if (z4) {
            strByResId3 = CommUtil.getStrByResId(this.mContext, "_424_Warning_6");
            i6 = 1;
        } else {
            i6 = i5;
            strByResId3 = CommUtil.getStrByResId(this.mContext, "_424_Warning_5");
        }
        if (z5) {
            strByResId4 = CommUtil.getStrByResId(this.mContext, "_424_Warning_8");
            i7 = 1;
        } else {
            i7 = i6;
            strByResId4 = CommUtil.getStrByResId(this.mContext, "_424_Warning_7");
        }
        return new TireUpdateEntity(i, i7, new String[]{this.decimalFormat.format(i2 * d) + "kpa", (i3 - 50) + getTempUnitC(this.mContext), str, strByResId, strByResId2, strByResId3, strByResId4});
    }

    protected static <T> List<T> mergeList(List<T>... listArr) {
        List<T> list;
        try {
            list = (List) listArr[0].getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (List<T> list2 : listArr) {
            list.addAll(list2);
        }
        return list;
    }

    private void setRadarInfo0x41() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(6, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(6, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            realKeyClick0x11(0);
            return;
        }
        if (i == 1) {
            realKeyClick0x11(7);
            return;
        }
        if (i == 2) {
            realKeyClick0x11(8);
            return;
        }
        if (i == 3) {
            realKeyClick0x11(3);
            return;
        }
        if (i == 5) {
            realKeyClick0x11(14);
            return;
        }
        if (i == 6) {
            realKeyClick0x11(15);
            return;
        }
        if (i == 8) {
            realKeyClick0x11(45);
        } else if (i == 9) {
            realKeyClick0x11(46);
        } else {
            if (i != 12) {
                return;
            }
            realKeyClick0x11(2);
        }
    }

    private void realKeyClick0x11(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setAirInfo0x31() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            int i = this.mCanBusInfoInt[6];
            if (i == 0) {
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 12) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            } else if (i == 5) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 6) {
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
            }
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = ResolveTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.center_wheel = ((this.mCanBusInfoInt[12] * 0.5d) - 40.0d) + getTempUnitC(this.mContext);
            outDoorTemp = this.mCanBusInfoInt[13];
            if (isOnlyOutDoorDataChange()) {
                SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", outDoorTemp);
                updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
            } else {
                updateAirActivity(this.mContext, 1001);
            }
        }
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private String ResolveTemp(int i) {
        String str = (i * 0.5d) + getTempUnitC(this.mContext);
        if (i == 254) {
            str = "LOW_TEMP";
        }
        return i == 255 ? "HIGH_TEMP" : str;
    }
}
