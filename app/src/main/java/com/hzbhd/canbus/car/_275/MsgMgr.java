package com.hzbhd.canbus.car._275;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static boolean isWarnFirst = true;
    private static int outDoorTemp;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusWarnInfoCopy;
    private Context mContext;
    int nowState0;
    int nowState1;
    int nowState2;
    int nowState3;
    int nowState4;
    private final String _275_IS_OUT_DOOR_TEMP = "_275_is_out_door_temp";
    private List<TireUpdateEntity> mTire0 = new ArrayList();
    private List<TireUpdateEntity> mTire1 = new ArrayList();
    private List<TireUpdateEntity> mTire2 = new ArrayList();
    private List<TireUpdateEntity> mTire3 = new ArrayList();
    private List<TireUpdateEntity> mTire4 = new ArrayList();
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    private String[] arr4 = new String[10];
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();

    private int getTireStatus(int i) {
        return (i == 0 || i == 1) ? 0 : 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, (byte) getCurrentEachCanId()});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        try {
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
            if (i == 49) {
                if (isAirMsgReturn(bArr)) {
                    return;
                }
                setAirData();
                return;
            }
            if (i == 55) {
                tireTest0x37();
                return;
            }
            if (i != 114) {
                if (i != 240) {
                    return;
                }
                setVersionInfo();
            } else {
                if (isWarningMsgReturn(bArr)) {
                    return;
                }
                Intent intent = new Intent(this.mContext, (Class<?>) WarningActivity.class);
                intent.addFlags(268435456);
                if (this.mCanBusInfoInt[4] != 0) {
                    this.mContext.startActivity(intent);
                } else {
                    Log.d("cwh", "0");
                    if (SystemUtil.isForeground(this.mContext, WarningActivity.class.getName())) {
                        Log.d("cwh", "1");
                        finishActivity();
                    }
                }
                warning0x72();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("cwh", "e = " + e);
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
        } else if (i == 5) {
            realKeyClick1(14);
        } else if (i == 6) {
            realKeyClick1(15);
        } else if (i == 8) {
            realKeyClick1(21);
        } else if (i == 9) {
            realKeyClick1(20);
        } else if (i == 12) {
            realKeyClick1(2);
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[4], iArr[5]);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
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
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        GeneralAirData.auto = false;
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 3) {
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
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        outDoorTemp = this.mCanBusInfoInt[13];
        if (isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "_275_is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void warning0x72() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = {R.string._275_warning_0, R.string._275_warning_1, R.string._275_warning_2, R.string._275_warning_3, R.string._275_warning_4, R.string._275_warning_5, R.string._275_warning_6, R.string._275_warning_7};
        String[] strArr = new String[8];
        for (int i = 0; i < 8; i++) {
            strArr[i] = this.mContext.getResources().getString(iArr[i]);
            if (DataHandleUtils.getBoolBit(i, this.mCanBusInfoInt[4])) {
                arrayList.add(new WarningEntity(strArr[i]));
            }
        }
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
    }

    private void setDoorData() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getDoorSwapFront() || ((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
            GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        } else {
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
            GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        }
        updateDoorView(this.mContext);
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "_275_is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void tireTest0x37() {
        operationTireInfo();
        this.tyreInfoList.add(new TireUpdateEntity(0, this.nowState0, this.arr0));
        this.tyreInfoList.add(new TireUpdateEntity(1, this.nowState1, this.arr1));
        this.tyreInfoList.add(new TireUpdateEntity(2, this.nowState2, this.arr2));
        this.tyreInfoList.add(new TireUpdateEntity(3, this.nowState3, this.arr3));
        this.tyreInfoList.add(new TireUpdateEntity(4, this.nowState4, this.arr4));
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void operationTireInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            int i2 = iArr[3];
            if (i2 == 0) {
                String[] strArr = this.arr0;
                StringBuilder sb = new StringBuilder();
                int[] iArr2 = this.mCanBusInfoInt;
                strArr[2] = sb.append((iArr2[4] * 256) + iArr2[5]).append("Kpa").toString();
                return;
            }
            if (i2 == 1) {
                String[] strArr2 = this.arr0;
                StringBuilder sb2 = new StringBuilder();
                int[] iArr3 = this.mCanBusInfoInt;
                strArr2[1] = sb2.append(((iArr3[4] * 256) + iArr3[5]) - 40).append(getTempUnitC(this.mContext)).toString();
                return;
            }
            if (i2 == 2) {
                this.arr0[0] = tireWarningTest(iArr[5]);
                this.nowState0 = getTireStatus(this.mCanBusInfoInt[5]);
                return;
            }
            return;
        }
        if (i == 1) {
            int i3 = iArr[3];
            if (i3 == 0) {
                String[] strArr3 = this.arr1;
                StringBuilder sb3 = new StringBuilder();
                int[] iArr4 = this.mCanBusInfoInt;
                strArr3[2] = sb3.append((iArr4[4] * 256) + iArr4[5]).append("Kpa").toString();
                return;
            }
            if (i3 == 1) {
                String[] strArr4 = this.arr1;
                StringBuilder sb4 = new StringBuilder();
                int[] iArr5 = this.mCanBusInfoInt;
                strArr4[1] = sb4.append(((iArr5[4] * 256) + iArr5[5]) - 40).append(getTempUnitC(this.mContext)).toString();
                return;
            }
            if (i3 == 2) {
                this.arr1[0] = tireWarningTest(iArr[5]);
                this.nowState1 = getTireStatus(this.mCanBusInfoInt[5]);
                return;
            }
            return;
        }
        if (i == 2) {
            int i4 = iArr[3];
            if (i4 == 0) {
                String[] strArr5 = this.arr2;
                StringBuilder sb5 = new StringBuilder();
                int[] iArr6 = this.mCanBusInfoInt;
                strArr5[2] = sb5.append((iArr6[4] * 256) + iArr6[5]).append("Kpa").toString();
                return;
            }
            if (i4 == 1) {
                String[] strArr6 = this.arr2;
                StringBuilder sb6 = new StringBuilder();
                int[] iArr7 = this.mCanBusInfoInt;
                strArr6[1] = sb6.append(((iArr7[4] * 256) + iArr7[5]) - 40).append(getTempUnitC(this.mContext)).toString();
                return;
            }
            if (i4 == 2) {
                this.arr2[0] = tireWarningTest(iArr[5]);
                this.nowState2 = getTireStatus(this.mCanBusInfoInt[5]);
                return;
            }
            return;
        }
        if (i == 3) {
            int i5 = iArr[3];
            if (i5 == 0) {
                String[] strArr7 = this.arr3;
                StringBuilder sb7 = new StringBuilder();
                int[] iArr8 = this.mCanBusInfoInt;
                strArr7[2] = sb7.append((iArr8[4] * 256) + iArr8[5]).append("Kpa").toString();
                return;
            }
            if (i5 == 1) {
                String[] strArr8 = this.arr3;
                StringBuilder sb8 = new StringBuilder();
                int[] iArr9 = this.mCanBusInfoInt;
                strArr8[1] = sb8.append(((iArr9[4] * 256) + iArr9[5]) - 40).append(getTempUnitC(this.mContext)).toString();
                return;
            }
            if (i5 == 2) {
                this.arr3[0] = tireWarningTest(iArr[5]);
                this.nowState3 = getTireStatus(this.mCanBusInfoInt[5]);
                return;
            }
            return;
        }
        if (i == 4) {
            int i6 = iArr[3];
            if (i6 == 0) {
                String[] strArr9 = this.arr4;
                StringBuilder sb9 = new StringBuilder();
                int[] iArr10 = this.mCanBusInfoInt;
                strArr9[2] = sb9.append((iArr10[4] * 256) + iArr10[5]).append("Kpa").toString();
                return;
            }
            if (i6 == 1) {
                String[] strArr10 = this.arr4;
                StringBuilder sb10 = new StringBuilder();
                int[] iArr11 = this.mCanBusInfoInt;
                strArr10[1] = sb10.append(((iArr11[4] * 256) + iArr11[5]) - 40).append(getTempUnitC(this.mContext)).toString();
                return;
            }
            if (i6 == 2) {
                this.arr4[0] = tireWarningTest(iArr[5]);
                this.nowState4 = getTireStatus(this.mCanBusInfoInt[5]);
            }
        }
    }

    private String tireWarningTest(int i) {
        switch (i) {
            case 1:
                return this.mContext.getResources().getString(R.string._275_tire_1);
            case 2:
                return this.mContext.getResources().getString(R.string._275_tire_2);
            case 3:
                return this.mContext.getResources().getString(R.string._275_tire_3);
            case 4:
                return this.mContext.getResources().getString(R.string._275_tire_4);
            case 5:
                return this.mContext.getResources().getString(R.string._275_tire_5);
            case 6:
                return this.mContext.getResources().getString(R.string._275_tire_6);
            case 7:
                return this.mContext.getResources().getString(R.string._275_tire_7);
            case 8:
                return this.mContext.getResources().getString(R.string._275_tire_8);
            default:
                return this.mContext.getResources().getString(R.string.set_default);
        }
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private String resolveOutDoorTem() {
        return ((float) ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
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

    private boolean isWarningMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusWarnInfoCopy)) {
            return true;
        }
        this.mCanBusWarnInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isWarnFirst) {
            return false;
        }
        isWarnFirst = false;
        return true;
    }

    private static <T> List<T> mergeList(List<T>... listArr) {
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
}
