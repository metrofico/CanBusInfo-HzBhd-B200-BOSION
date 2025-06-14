package com.hzbhd.canbus.car._236;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] m0x22RearRadarData;
    int[] m0x23FrontRadarData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    int[] mTireInfo;
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIndexBy3Bit(int i) {
        if (i != 0) {
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 2;
            }
        }
        return 0;
    }

    private int getRadarData(int i) {
        if (i == 0) {
            return 0;
        }
        return (-i) + 7;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            setWheelKekInfo();
            return;
        }
        if (i == 54) {
            int i2 = this.mDifferent;
            if (i2 == 1 || i2 == 2) {
                return;
            }
            setAirData0x36();
            return;
        }
        if (i == 81) {
            setSettingData0x51();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 35:
                    int i3 = this.mDifferent;
                    if (i3 != 1 && i3 != 2 && !isAirMsgRepeat(bArr)) {
                        setAirData0x23();
                        break;
                    }
                    break;
                case 36:
                    if (!isDoorMsgRepeat(bArr)) {
                        setDoorData0x24();
                        break;
                    }
                    break;
                case 37:
                    set0x25TireInfo();
                    break;
                case 38:
                    set0x26FrontRadarInfo();
                    break;
                case 39:
                    set0x27RearRadarInfo();
                    break;
            }
            return;
        }
        setVersionInfo();
    }

    private void setWheelKekInfo() {
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, i);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, i);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, i);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 20, i);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 21, i);
            return;
        }
        if (i == 16) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, i);
            return;
        }
        if (i == 128) {
            realKeyLongClick1(this.mContext, 1, i);
            return;
        }
        if (i == 18) {
            realKeyLongClick1(this.mContext, 184, i);
            return;
        }
        if (i != 19) {
            switch (i) {
                case 6:
                    realKeyLongClick1(this.mContext, 3, i);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, i);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 14, i);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 14, i);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 15, i);
                    break;
                default:
                    switch (i) {
                        case 32:
                            realKeyLongClick1(this.mContext, 52, i);
                            break;
                        case 33:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_CARPLAY_SIRI, i);
                            break;
                        case 34:
                            realKeyLongClick1(this.mContext, 59, i);
                            break;
                        case 35:
                            realKeyLongClick1(this.mContext, 2, i);
                            break;
                        case 36:
                            realKeyLongClick1(this.mContext, 139, i);
                            break;
                        case 37:
                            realKeyLongClick1(this.mContext, 141, i);
                            break;
                        case 38:
                            realKeyLongClick1(this.mContext, 62, i);
                            break;
                        case 39:
                            realKeyLongClick1(this.mContext, 50, i);
                            break;
                        case 40:
                            realKeyLongClick1(this.mContext, 151, i);
                            break;
                        case 41:
                            realKeyLongClick1(this.mContext, 58, i);
                            break;
                        case 42:
                            realKeyLongClick1(this.mContext, 49, i);
                            break;
                        case 43:
                            realKeyLongClick1(this.mContext, 47, i);
                            break;
                        case 44:
                            realKeyLongClick1(this.mContext, 48, i);
                            break;
                        case 45:
                            realKeyLongClick1(this.mContext, 53, i);
                            break;
                        case 46:
                            realKeyLongClick1(this.mContext, 45, i);
                            break;
                        case 47:
                            realKeyLongClick1(this.mContext, 46, i);
                            break;
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 2, i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        this.mDifferent = getCurrentCanDifferentId();
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
        int i = this.mDifferent;
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 0});
        } else if (i == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 2});
        } else if (i == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 1});
        } else if (i == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 3});
        } else if (i != 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, ByteCompanionObject.MIN_VALUE});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 82, 4});
        }
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) i2, (byte) i3, (byte) i4, (byte) ((i5 & WorkQueueKt.MASK) | (((!z ? 1 : 0) << 7) & 128)), (byte) i6, (byte) i7});
    }

    private void set0x27RearRadarInfo() {
        if (is0x27DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(6, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x26FrontRadarInfo() {
        if (is0x26DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(6, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x25TireInfo() {
        if (isTireInfoChange()) {
            return;
        }
        if (this.mCanBusInfoInt[2] == 255) {
            this.arr0[0] = this.mContext.getString(R.string._236_tire_warming);
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
            this.arr0[0] = (this.mCanBusInfoInt[2] * 2.75d) + "kPa";
        }
        if (this.mCanBusInfoInt[3] == 255) {
            this.arr1[0] = this.mContext.getString(R.string._236_tire_warming);
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
        } else {
            this.arr1[0] = (this.mCanBusInfoInt[3] * 2.75d) + "kPa";
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        }
        if (this.mCanBusInfoInt[5] == 255) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
            this.arr2[0] = this.mContext.getString(R.string._236_tire_warming);
        } else {
            this.arr2[0] = (this.mCanBusInfoInt[5] * 2.75d) + "kPa";
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        }
        if (this.mCanBusInfoInt[4] == 255) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
            this.arr3[0] = this.mContext.getString(R.string._236_tire_warming);
        } else {
            this.arr3[0] = (this.mCanBusInfoInt[4] * 2.75d) + "kPa";
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
        if (intFromByteWithBit == 9) {
            GeneralAirData.front_wind_level = 0;
            GeneralAirData.front_auto_wind_speed = true;
        } else {
            GeneralAirData.front_wind_level = intFromByteWithBit;
            GeneralAirData.front_auto_wind_speed = false;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        switch (intFromByteWithBit2) {
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 6:
                GeneralAirData.front_auto_wind_model = true;
                break;
        }
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setSettingData0x51() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2));
        int indexBy3Bit2 = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[5], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy3Bit)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy3Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit6)));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(indexBy2Bit8)));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(indexBy2Bit7)));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(i)).setProgress(i - 30));
        Boolean bool = false;
        if (intFromByteWithBit == 1) {
            bool = true;
        }
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(intFromByteWithBit2)).setEnable(bool.booleanValue()));
        arrayList.add(new SettingUpdateEntity(0, 14, Integer.valueOf(intFromByteWithBit3)).setEnable(bool.booleanValue()));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private String resolveOutDoorTem() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (intFromByteWithBit >= 127) {
            intFromByteWithBit = 127;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = "-" + intFromByteWithBit;
        } else {
            str = intFromByteWithBit + "";
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (18 == i) {
            return "HI";
        }
        if (255 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        return (3 > i || 17 < i) ? "" : (i + 15) + getTempUnitC(this.mContext);
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean is0x27DataChange() {
        if (Arrays.equals(this.m0x22RearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22RearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x26DataChange() {
        if (Arrays.equals(this.m0x23FrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23FrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
