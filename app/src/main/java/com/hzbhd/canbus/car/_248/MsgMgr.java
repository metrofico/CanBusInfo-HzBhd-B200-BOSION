package com.hzbhd.canbus.car._248;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    private static final int RADAR_DATA_MAX = 255;
    private static final int RADAR_DATA_SEGMENT = 10;
    public static int chargeModel = 0;
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    public static int recycleModel;
    private static int volKnobValue;
    private static int volKnobValueRadio;
    private int mCameraStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private int mDifferent;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private int mEachId;
    private int[] mRadarData;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private int[] mTrackData;
    private UiMgr mUiMgr;
    String str;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIndexBy6Bit(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 <= 60; i3++) {
            if (i == i3) {
                i2 = i;
            }
        }
        return i2;
    }

    private int getIndexByData(int i) {
        return i == 1 ? 0 : 1;
    }

    private int resolveCycle(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        return i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        initSettingsItem(getUiMgr(context).getSettingUiSet(context));
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) this.mEachId, 14});
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
            set0x11WheelKey();
            setTrackData0x11();
            return;
        }
        if (i == 18) {
            if (isDoorMsgRepeatTrumpchi(bArr)) {
                return;
            }
            setDoorData0x12();
            return;
        }
        if (i == 33) {
            set0x21WheelKey();
            return;
        }
        if (i == 34) {
            set0x22WheelKey();
            return;
        }
        if (i == 37) {
            switchLinkAndSos();
            return;
        }
        if (i == 62) {
            setEnergyData();
            return;
        }
        if (i == 65) {
            setRadarData0x41();
            return;
        }
        if (i == 103) {
            setSettingData0x67();
            return;
        }
        if (i == 150) {
            setSettingData0x96();
            return;
        }
        if (i == 232) {
            setPanoramic0xe8();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i != 49) {
            if (i != 50) {
                return;
            }
            setVehicleInfo0x32();
        } else {
            if (isAirMsgRepeatTrumpchi(bArr)) {
                return;
            }
            setAirData0x31();
        }
    }

    private void set0x22WheelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            int i2 = volKnobValue - iArr[3];
            if (i2 < 0) {
                PanelKnobClick(7, Math.abs(i2));
            } else if (i2 > 0) {
                PanelKnobClick(8, Math.abs(i2));
            }
            volKnobValue = this.mCanBusInfoInt[3];
            return;
        }
        if (i != 2) {
            return;
        }
        int i3 = volKnobValueRadio - iArr[3];
        if (i3 < 0) {
            PanelKnobClick(46, Math.abs(i3));
        } else if (i3 > 0) {
            PanelKnobClick(45, Math.abs(i3));
        }
        volKnobValueRadio = this.mCanBusInfoInt[3];
    }

    private void PanelKnobClick(int i, int i2) {
        realKeyClick3(this.mContext, i, i2, 1);
    }

    private void set0x11WheelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        if (i != 103) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[5]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[5]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[5]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 3, iArr[5]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[5]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 14, iArr[5]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 15, iArr[5]);
                    break;
                default:
                    switch (i) {
                        case 8:
                            realKeyLongClick1(this.mContext, 45, iArr[5]);
                            break;
                        case 9:
                            realKeyLongClick1(this.mContext, 46, iArr[5]);
                            break;
                        case 10:
                            realKeyLongClick1(this.mContext, 2, iArr[5]);
                            break;
                    }
            }
        }
        realKeyLongClick1(this.mContext, 33, iArr[5]);
    }

    private void set0x21WheelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 1, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 21, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 20, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 62, iArr[3]);
            return;
        }
        if (i == 17) {
            realKeyLongClick1(this.mContext, 31, iArr[3]);
            return;
        }
        if (i == 37) {
            realKeyLongClick1(this.mContext, 128, iArr[3]);
            return;
        }
        if (i == 40) {
            realKeyLongClick1(this.mContext, 14, iArr[3]);
            return;
        }
        if (i == 45) {
            realKeyLongClick1(this.mContext, 151, iArr[3]);
            return;
        }
        if (i == 54) {
            realKeyLongClick1(this.mContext, 58, iArr[3]);
            return;
        }
        if (i == 63) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_VOICE_PICKUP, iArr[3]);
            return;
        }
        if (i == 21) {
            realKeyLongClick1(this.mContext, 75, iArr[3]);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 4, iArr[3]);
            return;
        }
        if (i == 56) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i != 57) {
            switch (i) {
                case 9:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 33, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 34, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 35, iArr[3]);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 36, iArr[3]);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 37, iArr[3]);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 38, iArr[3]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, 30, iArr[3]);
    }

    private void setEnergyData() {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[8] == 0) {
            this.str = CommUtil.getStrByResId(this.mContext, "energy_no_display");
        } else {
            this.str = CommUtil.getStrByResId(this.mContext, "energy_drive");
        }
        arrayList.add(checkEntity(helperSetValue("energy_flow", this.str)).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setPanoramic0xe8() {
        LogUtil.showLog("setPanoramic0xe8");
        int i = this.mCameraStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mCameraStatus = i2;
            forceReverse(this.mContext, i2 == 1);
        }
        ArrayList arrayList = new ArrayList();
        int i3 = this.mDifferent;
        if (i3 == 5) {
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[3] == 3));
            arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[3] == 4));
            arrayList.add(new PanoramicBtnUpdateEntity(5, this.mCanBusInfoInt[3] == 5));
            arrayList.add(new PanoramicBtnUpdateEntity(6, this.mCanBusInfoInt[3] == 7));
            arrayList.add(new PanoramicBtnUpdateEntity(7, this.mCanBusInfoInt[3] == 8));
        } else if (i3 == 1 || i3 == 4) {
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 3));
            arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[3] == 7));
            arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[3] == 8));
        } else {
            arrayList = null;
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private String ResolvePano(int i) {
        if (i == 1) {
            return CommUtil.getStrByResId(this.mContext, "_342_setting_1_5_1");
        }
        return CommUtil.getStrByResId(this.mContext, "_342_setting_1_5_0");
    }

    private void switchLinkAndSos() {
        if (this.mCanBusInfoInt[2] == 1) {
            GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string.link_display);
            sendDisplayMsgView(this.mContext);
            enterAuxIn2();
        }
        if (this.mCanBusInfoInt[2] == 2) {
            GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string.sos_display);
            sendDisplayMsgView(this.mContext);
            enterAuxIn2();
        }
        if (this.mCanBusInfoInt[2] == 0) {
            exitAuxIn2();
        }
    }

    private void setAirData0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1;
        GeneralAirData.ion = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_auto_cycle = resolveCycle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2));
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        if (i == 1) {
            GeneralAirData.front_auto_wind_model = true;
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
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
            }
        }
        int currentCanDifferentId = getCurrentCanDifferentId();
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        if (currentCanDifferentId == 3 || currentCanDifferentId == 4 || currentCanDifferentId == 0) {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        } else {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setSettingData0x96() {
        int i;
        int i2;
        int i3;
        int indexByData = getIndexByData(this.mCanBusInfoInt[2]);
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
        String strValueOf = String.valueOf(this.mCanBusInfoInt[5] * 10);
        int[] iArr = this.mCanBusInfoInt;
        int i4 = iArr[5];
        int i5 = iArr[6];
        String strValueOf2 = String.valueOf(iArr[7]);
        int[] iArr2 = this.mCanBusInfoInt;
        int i6 = iArr2[7];
        String strValueOf3 = String.valueOf(iArr2[8]);
        int[] iArr3 = this.mCanBusInfoInt;
        int i7 = iArr3[8];
        int i8 = iArr3[9];
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(iArr3[10]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]));
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]));
        int indexBy2Bit11 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10]));
        int indexBy2Bit12 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10]));
        int indexBy2Bit13 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10]));
        int indexBy2Bit14 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[10]));
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 2);
        int indexBy2Bit15 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]));
        int indexBy2Bit16 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]));
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
        int indexBy2Bit17 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[11]));
        int indexBy2Bit18 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[12]));
        ArrayList arrayList = new ArrayList();
        int i9 = indexByData == 1 ? 0 : 1;
        if (getCurrentCanDifferentId() == 0) {
            i = intFromByteWithBit3;
            i2 = intFromByteWithBit2;
            i3 = 0;
            arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(i9)));
        } else {
            i = intFromByteWithBit3;
            i2 = intFromByteWithBit2;
            i3 = 0;
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(i9)));
        }
        arrayList.add(new SettingUpdateEntity(i3, i3, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(i3, 1, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(i3, 2, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(i3, 3, Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(1, i3, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(indexBy2Bit6)));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(indexBy2Bit7)));
        arrayList.add(new SettingUpdateEntity(2, 0, strValueOf).setProgress(i4));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(i5)));
        arrayList.add(new SettingUpdateEntity(2, 2, strValueOf2).setProgress(i6));
        arrayList.add(new SettingUpdateEntity(2, 3, strValueOf3).setProgress(i7));
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(i8)));
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(indexBy2Bit8)));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(indexBy2Bit9)));
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(indexBy2Bit10)));
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(indexBy2Bit11)));
        arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(indexBy2Bit12)));
        arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(indexBy2Bit13)));
        arrayList.add(new SettingUpdateEntity(3, 6, Integer.valueOf(indexBy2Bit14)));
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(i2)));
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(indexBy2Bit15)));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(indexBy2Bit16)));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(i)));
        arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(indexBy2Bit17)));
        if (getCurrentCanDifferentId() == 0) {
            arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(indexBy2Bit18)));
        } else {
            arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(indexBy2Bit18)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingData0x67() {
        if (getCurrentCanDifferentId() == 0) {
            int[] iArr = this.mCanBusInfoInt;
            int i = iArr[2];
            chargeModel = i;
            int i2 = iArr[3];
            int i3 = iArr[4];
            int i4 = iArr[5];
            int i5 = iArr[6];
            int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(iArr[7]));
            recycleModel = indexBy2Bit;
            int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]));
            int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]));
            int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]));
            int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]));
            int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]));
            int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]));
            int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(i)));
            arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(i2)).setProgress(i2));
            arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(i3)).setProgress(i3));
            arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(i4)).setProgress(i4));
            arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(i5)).setProgress(i5));
            arrayList.add(new SettingUpdateEntity(5, 5, Integer.valueOf(indexBy2Bit)));
            arrayList.add(new SettingUpdateEntity(5, 6, Integer.valueOf(indexBy2Bit2)));
            arrayList.add(new SettingUpdateEntity(5, 7, Integer.valueOf(indexBy2Bit3)));
            arrayList.add(new SettingUpdateEntity(5, 8, Integer.valueOf(indexBy2Bit4)));
            arrayList.add(new SettingUpdateEntity(5, 9, Integer.valueOf(indexBy2Bit5)));
            arrayList.add(new SettingUpdateEntity(5, 10, Integer.valueOf(indexBy2Bit6)));
            arrayList.add(new SettingUpdateEntity(5, 11, Integer.valueOf(indexBy2Bit7)));
            arrayList.add(new SettingUpdateEntity(5, 12, Integer.valueOf(indexBy2Bit8)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setSettingData0x6D() {
        int i = this.mCanBusInfoInt[2];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 2, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x12() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setTrackData0x11() {
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 5500, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setVehicleInfo0x32() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, resolveDriveData(iArr[4], iArr[5], " r/min")));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, resolveDriveData(iArr2[6], iArr2[7], " km/h")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i8, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, 0});
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (36 > i || 63 < i) ? "--" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    protected boolean isAirMsgRepeatTrumpchi(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    protected boolean isDoorMsgRepeatTrumpchi(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean isTrackDataChange() {
        int[] iArr = this.mTrackData;
        int[] iArr2 = this.mCanBusInfoInt;
        Arrays.equals(iArr, new int[]{iArr2[8], iArr2[9]});
        int[] iArr3 = this.mCanBusInfoInt;
        this.mTrackData = Arrays.copyOf(iArr3, iArr3.length);
        return true;
    }

    private boolean isRadarDataChange() {
        Arrays.equals(this.mRadarData, this.mCanBusInfoInt);
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private int resolveRadarData(int i) {
        if (i == 255) {
            return 255;
        }
        return (i / 25) + 1;
    }

    private String resolveDriveData(int i, int i2, String str) {
        int i3 = (i * 256) + i2;
        return i3 != 65535 ? i3 + str : "---" + str;
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        this.mSettingItemHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                this.mSettingItemHashMap.put(itemListBean.getTitleSrn(), new SettingUpdateHelper(new SettingUpdateEntity(i, i2, "null_value"), itemListBean.getMin()));
            }
        }
    }

    private void initDriveItem(DriverDataPageUiSet driverDataPageUiSet) {
        this.mDriveItemHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = driverDataPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemHashMap.put(itemList.get(i2).getTitleSrn(), new DriveDataUpdateHelper(new DriverUpdateEntity(i, i2, "null_value")));
            }
        }
    }

    private SettingUpdateEntity checkEntity(SettingUpdateEntity settingUpdateEntity) {
        if (settingUpdateEntity.getLeftListIndex() == -1 || settingUpdateEntity.getRightListIndex() == -1) {
            return null;
        }
        return settingUpdateEntity;
    }

    private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity driverUpdateEntity) {
        if (driverUpdateEntity.getPage() == -1 || driverUpdateEntity.getIndex() == -1) {
            return null;
        }
        return driverUpdateEntity;
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj);
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj, boolean z) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value").setEnable(false)));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj).setEnable(z);
    }

    private DriverUpdateEntity helperSetDriveDataValue(String str, String str2) {
        if (!this.mDriveItemHashMap.containsKey(str)) {
            this.mDriveItemHashMap.put(str, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
        }
        return this.mDriveItemHashMap.get(str).setValue(str2);
    }

    private static class DriveDataUpdateHelper {
        private DriverUpdateEntity entity;

        public DriveDataUpdateHelper(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public void setEntity(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public DriverUpdateEntity getEntity() {
            return this.entity;
        }

        public DriverUpdateEntity setValue(String str) {
            this.entity.setValue(str);
            return this.entity;
        }
    }

    private static class SettingUpdateHelper {
        private SettingUpdateEntity entity;
        private int progressMin;

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity) {
            this(settingUpdateEntity, 0);
        }

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity, int i) {
            this.entity = settingUpdateEntity;
            this.progressMin = i;
        }

        public SettingUpdateEntity getEntity() {
            return this.entity;
        }

        public SettingUpdateEntity setValue(Object obj) {
            if (obj instanceof Integer) {
                Integer num = (Integer) obj;
                this.entity.setValue(Integer.valueOf(num.intValue() + this.progressMin));
                this.entity.setProgress(num.intValue());
            } else {
                this.entity.setValue(obj);
            }
            return this.entity;
        }

        public SettingUpdateEntity setEnable(boolean z) {
            this.entity.setEnable(z);
            return this.entity;
        }
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        SharePreUtil.setIntValue(context, "LeftPos", i);
        SharePreUtil.setIntValue(context, "RightPos", i2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
