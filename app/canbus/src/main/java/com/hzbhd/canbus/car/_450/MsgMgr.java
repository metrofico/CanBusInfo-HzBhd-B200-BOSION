package com.hzbhd.canbus.car._450;

import android.content.Context;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    private UiMgr mUiMgr;
    private String[] arr0 = new String[1];
    private String[] arr1 = new String[1];
    private String[] arr2 = new String[1];
    private String[] arr3 = new String[1];
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int optionRadarRange(int i) {
        if (i >= 0 && i <= 51) {
            return 1;
        }
        if (i < 52 || i > 150) {
            return (i <= 150 || i > 252) ? 0 : 10;
        }
        return 6;
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
        GeneralTireData.isHaveSpareTire = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14(byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            set0X24(byteArrayToIntArray);
            return;
        }
        if (i == 40) {
            set0x28(byteArrayToIntArray);
            return;
        }
        if (i == 48) {
            set0x30(this.mCanBusInfoByte);
            return;
        }
        if (i == 70) {
            set0x46(byteArrayToIntArray);
            return;
        }
        if (i == 33) {
            set0x21(byteArrayToIntArray);
            return;
        }
        if (i == 34) {
            set0x22(byteArrayToIntArray);
            return;
        }
        if (i == 64) {
            set0x40(byteArrayToIntArray);
            return;
        }
        if (i != 65) {
            switch (i) {
                case 16:
                    set0x10(byteArrayToIntArray);
                    break;
                case 17:
                    set0x11(byteArrayToIntArray);
                    break;
                case 18:
                    set0x12(byteArrayToIntArray);
                    break;
                default:
                    switch (i) {
                        case 72:
                            set0x48(byteArrayToIntArray);
                            break;
                        case 73:
                            setTire0x49(byteArrayToIntArray);
                            break;
                        case 74:
                            set0x4A(byteArrayToIntArray);
                            break;
                        case 75:
                            set0x4B(byteArrayToIntArray);
                            break;
                    }
            }
            return;
        }
        setDrive0x41(byteArrayToIntArray);
    }

    private void set0x12(int[] iArr) {
        Context context;
        int i;
        Context context2;
        int i2;
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_62");
        if (DataHandleUtils.getBoolBit7(iArr[2])) {
            context = this.mContext;
            i = R.string._450_bmw_car_62_1;
        } else {
            context = this.mContext;
            i = R.string._450_bmw_car_62_0;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, context.getString(i)));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_63");
        if (DataHandleUtils.getBoolBit6(iArr[2])) {
            context2 = this.mContext;
            i2 = R.string._450_bmw_car_63_1;
        } else {
            context2 = this.mContext;
            i2 = R.string._450_bmw_car_63_0;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, context2.getString(i2)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_64"), DataHandleUtils.getBoolBit7(iArr[3]) ? this.mContext.getString(R.string._450_bmw_car_64_1) : this.mContext.getString(R.string._450_bmw_car_64_0)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_65"), DataHandleUtils.getBoolBit6(iArr[3]) ? this.mContext.getString(R.string._450_bmw_car_64_1) : this.mContext.getString(R.string._450_bmw_car_64_0)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_66"), iArr[4] + "L"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        if (DataHandleUtils.getBoolBit7(iArr[5])) {
            updateOutDoorTemp(this.mContext, "-" + (DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 7) * 0.5d) + getTempUnitC(this.mContext));
        } else {
            updateOutDoorTemp(this.mContext, (DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 7) * 0.5d) + getTempUnitC(this.mContext));
        }
    }

    private void set0x4B(int[] iArr) {
        String string;
        String string2;
        String string3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_50"), iArr[2] + ""));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_51"), iArr[3] + ""));
        int i = iArr[4];
        String str = "--";
        if (i == 1) {
            string = this.mContext.getString(R.string._450_bmw_car_52_1);
        } else if (i == 2) {
            string = this.mContext.getString(R.string._450_bmw_car_52_2);
        } else if (i == 3) {
            string = this.mContext.getString(R.string._450_bmw_car_52_3);
        } else if (i == 4) {
            string = this.mContext.getString(R.string._450_bmw_car_52_4);
        } else if (i == 5) {
            string = this.mContext.getString(R.string._450_bmw_car_52_5);
        } else if (i == 130) {
            string = this.mContext.getString(R.string._450_bmw_car_52_82);
        } else if (i == 134) {
            string = this.mContext.getString(R.string._450_bmw_car_52_86);
        } else if (i == 137) {
            string = this.mContext.getString(R.string._450_bmw_car_52_89);
        } else {
            string = i != 149 ? "--" : this.mContext.getString(R.string._450_bmw_car_52_95);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_52"), string));
        int i2 = iArr[5];
        if (i2 == 0) {
            string2 = this.mContext.getString(R.string._450_bmw_car_53_0);
        } else if (i2 == 1) {
            string2 = this.mContext.getString(R.string._450_bmw_car_53_1);
        } else {
            string2 = i2 != 2 ? "--" : this.mContext.getString(R.string._450_bmw_car_53_2);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_53"), string2));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_54_0"), DataHandleUtils.getBoolBit7(iArr[6]) ? this.mContext.getString(R.string._450_bmw_car_54_01) : this.mContext.getString(R.string._450_bmw_car_54_00)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_54_1"), DataHandleUtils.getBoolBit6(iArr[6]) ? this.mContext.getString(R.string._450_bmw_car_54_01) : this.mContext.getString(R.string._450_bmw_car_54_00)));
        if (DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 2) == 1) {
            str = "km";
        } else if (DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 2) == 2) {
            str = "mi";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_54_2"), str));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_55"), (iArr[7] + SystemConstant.THREAD_SLEEP_TIME_2000) + "-" + iArr[8]));
        if (DataHandleUtils.getBoolBit7(iArr[9])) {
            string3 = this.mContext.getString(R.string._450_bmw_car_59);
        } else {
            string3 = this.mContext.getString(R.string._450_bmw_car_58);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_57"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_56"), string3 + ((iArr[11] & 255) | ((DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 4) & 255) << 16) | ((iArr[10] & 255) << 8))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x4A(int[] iArr) {
        Context context;
        int i;
        String string;
        String str;
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_42");
        if (DataHandleUtils.getBoolBit7(iArr[2])) {
            context = this.mContext;
            i = R.string._450_bmw_car_44;
        } else {
            context = this.mContext;
            i = R.string._450_bmw_car_43;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, context.getString(i)));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 3);
        if (intFromByteWithBit == 0) {
            string = this.mContext.getString(R.string._450_bmw_car_46);
        } else if (intFromByteWithBit == 1) {
            string = this.mContext.getString(R.string._450_bmw_car_47);
        } else {
            string = intFromByteWithBit == 4 ? this.mContext.getString(R.string._450_bmw_car_48) : "";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_45"), string));
        switch (iArr[3]) {
            case 0:
                str = "0%";
                break;
            case 1:
                str = "16%";
                break;
            case 2:
                str = "32%";
                break;
            case 3:
                str = "48%";
                break;
            case 4:
                str = "64%";
                break;
            case 5:
                str = "80%";
                break;
            case 6:
                str = "100%";
                break;
            case 7:
                str = "100% +";
                break;
            default:
                str = "--";
                break;
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_49"), str));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_49"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_48_888"), DataHandleUtils.getBoolBit0(iArr[2]) ? "Yes" : "NO"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTire0x49(int[] iArr) {
        int i = iArr[6];
        String str = i == 1 ? "BAR" : i == 2 ? "KPA" : i == 3 ? "PSI" : "";
        this.arr0[0] = this.mContext.getString(R.string._450_bmw_car_41) + (DataHandleUtils.getMsbLsbResult(iArr[8], iArr[7]) * 0.1f) + str;
        this.arr1[0] = this.mContext.getString(R.string._450_bmw_car_41) + (DataHandleUtils.getMsbLsbResult(iArr[10], iArr[9]) * 0.1f) + str;
        this.arr2[0] = this.mContext.getString(R.string._450_bmw_car_41) + (DataHandleUtils.getMsbLsbResult(iArr[12], iArr[11]) * 0.1f) + str;
        this.arr3[0] = this.mContext.getString(R.string._450_bmw_car_41) + (DataHandleUtils.getMsbLsbResult(iArr[14], iArr[13]) * 0.1f) + str;
        if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 6, 2) == 2) {
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
        }
        if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 2) == 2) {
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        }
        if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 2, 2) == 2) {
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        }
        if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2) == 2) {
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
        } else {
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x48(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_40"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_37"), DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) + "KM"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_40"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_38"), (DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) * 0.1f) + "L/100KM "));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_40"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_39"), DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) + "KM/H"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x46(int[] iArr) {
        UiMgr.data0bit7 = DataHandleUtils.getIntFromByteWithBit(iArr[2], 7, 1);
        UiMgr.data0bit6 = DataHandleUtils.getIntFromByteWithBit(iArr[2], 6, 1);
        UiMgr.data0bit5 = DataHandleUtils.getIntFromByteWithBit(iArr[2], 5, 1);
        UiMgr.data0bit4 = DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 1);
        UiMgr.data0bit3 = DataHandleUtils.getIntFromByteWithBit(iArr[2], 3, 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_11"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_12"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_13"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_14"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_10_left1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_10_left1", "_450_bmw_car_15"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_19"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_20"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_21"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[4], 4, 4) - 1)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_22"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[4], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_23"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_24"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_25"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_26"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_27"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_28"), Integer.valueOf(iArr[6])).setProgress(iArr[6]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_29"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[7], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_30"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_31"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[7], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_32"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_33"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[7], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_34"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[7], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_35"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[8], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_450_bmw_car_18_left2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_450_bmw_car_18_left2", "_450_bmw_car_36"), Integer.valueOf(DataHandleUtils.getMsbLsbResult(DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 4), iArr[9]))).setProgress(DataHandleUtils.getMsbLsbResult(DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 4), iArr[9])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDrive0x41(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4) != 1) {
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_3"), "--"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_4"), DataHandleUtils.getBoolBit7(iArr[5]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_5"), DataHandleUtils.getBoolBit6(iArr[5]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_6"), DataHandleUtils.getBoolBit3(iArr[5]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_7"), DataHandleUtils.getBoolBit3(iArr[6]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_8"), DataHandleUtils.getBoolBit2(iArr[6]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_9"), DataHandleUtils.getBoolBit1(iArr[6]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_10"), DataHandleUtils.getBoolBit0(iArr[6]) ? "ON" : "OFF"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x40(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_1"), (((iArr[2] & 255) << 16) | ((iArr[3] & 255) << 8) | (iArr[4] & 255)) + "km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_2"), ((iArr[6] & 255) | ((iArr[5] & 255) << 8)) + "km"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x30(byte[] bArr) {
        updateVersionInfo(this.mContext, getVersionStr(bArr));
    }

    private void set0x28(int[] iArr) {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(10, optionRadarRange(iArr[3]), optionRadarRange(iArr[4]), optionRadarRange(iArr[5]), optionRadarRange(iArr[6]));
        RadarInfoUtil.setRearRadarLocationData(10, optionRadarRange(iArr[7]), optionRadarRange(iArr[8]), optionRadarRange(iArr[9]), optionRadarRange(iArr[10]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0X24(int[] iArr) {
        if (DataHandleUtils.getBoolBit7(iArr[2])) {
            GeneralParkData.trackAngle = DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 7) / 2;
        } else {
            GeneralParkData.trackAngle = (-iArr[2]) / 2;
        }
        updateTrack();
    }

    private void set0x22(int[] iArr) {
        int i = iArr[2];
        if (i == 129) {
            realKeyClick4(this.mContext, 47);
            return;
        }
        if (i != 130) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 59, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 62, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 128, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 151, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 50, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 58, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 16:
                            realKeyLongClick1(this.mContext, 49, iArr[3]);
                            break;
                        case 17:
                            realKeyLongClick1(this.mContext, 45, iArr[3]);
                            break;
                        case 18:
                            realKeyLongClick1(this.mContext, 46, iArr[3]);
                            break;
                        case 19:
                            realKeyLongClick1(this.mContext, 47, iArr[3]);
                            break;
                        case 20:
                            realKeyLongClick1(this.mContext, 48, iArr[3]);
                            break;
                    }
            }
            return;
        }
        realKeyClick4(this.mContext, 48);
    }

    private void set0x21(int[] iArr) {
        int i = iArr[2];
        if (i == 16) {
            realKeyLongClick1(this.mContext, 49, iArr[3]);
            return;
        }
        if (i == 129) {
            realKeyClick4(this.mContext, 47);
            return;
        }
        if (i != 130) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 49, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
            }
            return;
        }
        realKeyClick4(this.mContext, 48);
    }

    private void set0x14(int[] iArr) {
        setBacklightLevel((iArr[2] / 51) + 1);
    }

    private void set0x11(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_61"), DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) + "rpm"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_450_bmw_car_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_450_bmw_car_60"), DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(getMsbLsbResult(iArr[4], iArr[5]));
    }

    private void set0x10(int[] iArr) {
        iArr[2] = 0;
        iArr[4] = blockBit(iArr[4], 4);
        if (isBasicInfoChange()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[3]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[3]);
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSubShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(iArr[4]);
            GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit6(iArr[4]);
            updateDoorView(this.mContext);
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
