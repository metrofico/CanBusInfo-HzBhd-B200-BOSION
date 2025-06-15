package com.hzbhd.canbus.car._194;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private static int mDifferentId = 0;
    static int mFindCarLight = 0;
    static int mFindCarTime = 0;
    static int mHomeWithMeLight = 0;
    static int mHomeWithMeTime = 0;
    private static boolean mIsBackLast = false;
    private static boolean mIsBelt = false;
    private static boolean mIsFLDoorLast = false;
    private static boolean mIsFRDoorLast = false;
    private static boolean mIsFrontLast = false;
    private static boolean mIsHandBreak = false;
    private static boolean mIsRLDoorLast = false;
    private static boolean mIsRRDoorLast = false;
    private int eachID;
    int[] m0x23FrontRadarData;
    int[] m0x54Info;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    int[] mTireInfo;
    private UiMgr mUiMgr;
    int lastData0 = 0;
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr3 = new String[10];
    private String[] arr2 = new String[10];
    int nowPm25 = 0;
    private byte[] mMediaInfo = new byte[2];

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private void set0x39CarInfo() {
    }

    private void set0x42CarInfo() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        mDifferentId = getCurrentCanDifferentId();
        this.eachID = getCurrentEachCanId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        setCarIdCmd();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bArr = {22, -90};
        int i11 = z ? 0 : 128;
        if (z) {
            i5 = i8;
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) (i5 | i11), (byte) i6}));
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
            set0x11RearAirInfo();
            return;
        }
        if (i == 39) {
            setAirData0x27();
            return;
        }
        if (i == 41) {
            set0x29Track();
            return;
        }
        if (i == 57) {
            set0x39CarInfo();
            return;
        }
        if (i == 80) {
            setPanoramic_0x50();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 32:
                    set0x20SwcKey();
                    break;
                case 33:
                    setAirData0x23();
                    break;
                case 34:
                    setRearRadar0x22();
                    break;
                case 35:
                    set0x23FrontRedarInfo();
                    break;
                case 36:
                    setDoorData0x24();
                    break;
                default:
                    switch (i) {
                        case 64:
                            set0x40CarInfo();
                            break;
                        case 65:
                            set0x41CarInfo();
                            break;
                        case 66:
                            set0x42CarInfo();
                            break;
                        case 67:
                            set0x43Pm25();
                            break;
                        case 68:
                            setWarnInfo0x44();
                            break;
                        default:
                            switch (i) {
                                case 82:
                                    set0x52TireInfo();
                                    break;
                                case 83:
                                    set0x53DriveInfo();
                                    break;
                                case 84:
                                    set0x54EnergyLevel();
                                    break;
                                default:
                                    switch (i) {
                                        case 96:
                                            set0x60EnergyInfo();
                                            break;
                                        case 97:
                                            set0x61DianJiInfo();
                                            break;
                                        case 98:
                                            set0x62ElectricInfo();
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        setVersionInfo();
    }

    private void set0x62ElectricInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_electric_info");
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_electric_info1");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[2], iArr[3])).append("V").toString()));
        int[] iArr2 = this.mCanBusInfoInt;
        if (getMsbLsbResult(iArr2[4], iArr2[5]) > 60000) {
            int drivingPageIndexes2 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_electric_info");
            int drivingItemIndexes2 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_electric_info2");
            StringBuilder sb2 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr3[4], iArr3[5]) - 65536).append("A").toString()));
        } else {
            int drivingPageIndexes3 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_electric_info");
            int drivingItemIndexes3 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_electric_info2");
            StringBuilder sb3 = new StringBuilder();
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(getMsbLsbResult(iArr4[4], iArr4[5])).append("A").toString()));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x61DianJiInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo7");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[2], iArr[3])).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x54EnergyLevel() {
        if (is0x54InfoChange()) {
            return;
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._194.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.showEnergyDialog(msgMgr.getActivity(), MsgMgr.this.mCanBusInfoInt[2] + 1);
            }
        });
    }

    private void set0x60EnergyInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo"), getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo1"), getHybridSystemInfo(this.mCanBusInfoInt[2])));
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo2");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[3], iArr[4])).append("km").toString()));
        int drivingPageIndexes2 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
        int drivingItemIndexes2 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo3");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[5], iArr2[6])).append("km").toString()));
        int drivingPageIndexes3 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
        int drivingItemIndexes3 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo4");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(getMsbLsbResult(iArr3[7], iArr3[8])).append("km").toString()));
        int drivingPageIndexes4 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
        int drivingItemIndexes4 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo5");
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb4.append(getMsbLsbResult(iArr4[9], iArr4[10]) * 0.1d).append("kWh/100km").toString()));
        int drivingPageIndexes5 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_194_EnergyInfo");
        int drivingItemIndexes5 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_194_EnergyInfo6");
        StringBuilder sb5 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, drivingItemIndexes5, sb5.append(getMsbLsbResult(iArr5[11], iArr5[12]) * 0.1d).append("L/100km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x53DriveInfo() {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 4) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model1"), Integer.valueOf(this.lastData0)));
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model1"), Integer.valueOf(this.mCanBusInfoInt[2])));
            this.lastData0 = this.mCanBusInfoInt[2];
        }
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model5"), (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext)).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model6"), (this.mCanBusInfoInt[5] / 10) + "Bar").setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model7"), (this.mCanBusInfoInt[6] * 0.04d) + "Kpa").setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_drive_model1"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_drive_model1", "_194_drive_model8"), (this.mCanBusInfoInt[7] - 40) + getTempUnitC(this.mContext)).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x52TireInfo() {
        int i;
        if (isTireInfoChange()) {
            return;
        }
        GeneralTireData.isHaveSpareTire = false;
        ArrayList arrayList = new ArrayList();
        int i2 = this.mCanBusInfoInt[12];
        if (i2 == 0) {
            this.arr0[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[4] * 10) + "Kpa";
            this.arr1[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[5] * 10) + "Kpa";
            this.arr3[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[6] * 10) + "Kpa";
            this.arr2[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[7] * 10) + "Kpa";
        } else if (i2 == 1) {
            this.arr0[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[4] * 0.1d) + "Bar";
            this.arr1[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[5] * 0.1d) + "Bar";
            this.arr3[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[6] * 0.1d) + "Bar";
            this.arr2[0] = this.mContext.getString(R.string._194_tire1) + (this.mCanBusInfoInt[7] * 0.1d) + "Bar";
        } else if (i2 == 2) {
            this.arr0[0] = this.mContext.getString(R.string._194_tire1) + this.mCanBusInfoInt[4] + "Psi";
            this.arr1[0] = this.mContext.getString(R.string._194_tire1) + this.mCanBusInfoInt[5] + "Psi";
            this.arr3[0] = this.mContext.getString(R.string._194_tire1) + this.mCanBusInfoInt[6] + "Psi";
            this.arr2[0] = this.mContext.getString(R.string._194_tire1) + this.mCanBusInfoInt[7] + "Psi";
        }
        this.arr0[1] = this.mContext.getString(R.string._194_tire2) + (this.mCanBusInfoInt[8] - 60) + getTempUnitC(this.mContext);
        this.arr1[1] = this.mContext.getString(R.string._194_tire2) + (this.mCanBusInfoInt[9] - 60) + getTempUnitC(this.mContext);
        this.arr3[1] = this.mContext.getString(R.string._194_tire2) + (this.mCanBusInfoInt[10] - 60) + getTempUnitC(this.mContext);
        this.arr2[1] = this.mContext.getString(R.string._194_tire2) + (this.mCanBusInfoInt[11] - 60) + getTempUnitC(this.mContext);
        if (this.mCanBusInfoInt[4] == 255) {
            this.arr0[0] = this.mContext.getString(R.string._194_tire1) + "Null Info";
        }
        if (this.mCanBusInfoInt[5] == 255) {
            this.arr1[0] = this.mContext.getString(R.string._194_tire1) + "Null Info";
        }
        if (this.mCanBusInfoInt[6] == 255) {
            this.arr3[0] = this.mContext.getString(R.string._194_tire1) + "Null Info";
        }
        if (this.mCanBusInfoInt[7] == 255) {
            this.arr2[0] = this.mContext.getString(R.string._194_tire1) + "Null Info";
        }
        if (this.mCanBusInfoInt[8] == 255) {
            this.arr0[1] = this.mContext.getString(R.string._194_tire2) + "Null Info";
        }
        if (this.mCanBusInfoInt[9] == 255) {
            this.arr1[1] = this.mContext.getString(R.string._194_tire2) + "Null Info";
        }
        if (this.mCanBusInfoInt[10] == 255) {
            this.arr3[1] = this.mContext.getString(R.string._194_tire2) + "Null Info";
        }
        if (this.mCanBusInfoInt[11] == 255) {
            i = 1;
            this.arr2[1] = this.mContext.getString(R.string._194_tire2) + "Null Info";
        } else {
            i = 1;
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == i) {
            arrayList.add(new TireUpdateEntity(0, i, this.arr0));
        } else {
            arrayList.add(new TireUpdateEntity(0, 0, this.arr0));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == i) {
            arrayList.add(new TireUpdateEntity(i, i, this.arr1));
        } else {
            arrayList.add(new TireUpdateEntity(i, 0, this.arr1));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) == i) {
            arrayList.add(new TireUpdateEntity(2, i, this.arr3));
        } else {
            arrayList.add(new TireUpdateEntity(2, 0, this.arr3));
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == i) {
            arrayList.add(new TireUpdateEntity(3, i, this.arr2));
        } else {
            arrayList.add(new TireUpdateEntity(3, 0, this.arr2));
        }
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private void set0x41CarInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_i_went_home_with"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_searching_cars_indication"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_steering_wheel_feels"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_unlock_mode"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_smart_unlock_the_car_near"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_convenient_and_comfortable"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_convenient_and_comfortable", "_194_a_foldable_outside_mirror_automatically"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_airconditioning_settings"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_airconditioning_settings", "_194_rear_window_demisting_demisting_linkage"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_airconditioning_settings"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_airconditioning_settings", "_194_automatic_mode_wind"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_airconditioning_settings"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_airconditioning_settings", "_194_partition_temperature_settings"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset"), this.mContext.getString(R.string._194_add_function14)).setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_tire_pressure_reset"), this.mContext.getString(R.string._194_add_function15)).setValueStr(true));
        }
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_vehicle_stability_control"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_ecodriving"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_driving_maintenance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_driving_maintenance", "_194_add_action1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_reset"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_reset", "_194_reset"), this.mContext.getString(R.string._194_add_function14)).setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_reset"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_reset", "_194_reset"), this.mContext.getString(R.string._194_add_function15)).setValueStr(true));
        }
        if (this.mCanBusInfoInt.length == 6) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            return;
        }
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_instrument_brightness"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_instrument_brightness", "_194_meter_brightness_level"), Integer.valueOf(this.mCanBusInfoInt[7] - 1)));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_pedestrian_warning_beep"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_speed_auxiliary"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_assist_mode"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_lane_departure_warning"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_alarm_sensitivity"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_alarm_sound"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_auxiliary_systems"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_assist__assist_mode"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_forward_collision_aid__alert_sensitivity"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 5, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 3, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_advanced_driver_assistance"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_advanced_driver_assistance", "_194_add_function11"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_window"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_window", "_194_skylights_state"), Integer.valueOf(this.mCanBusInfoInt[11])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), Integer.valueOf(this.mCanBusInfoInt[13] - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x40CarInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_driving_luosuo"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_unlock"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_unlock_mode"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lock_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lock_control", "_194_smart_unlock_the_car_near"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with__reversing_lights"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with__beam_lights"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with__rear_fog"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_i_came_home_with_a_duration"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indication__reversing_lights"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indication__near_light"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indication__rear_fog"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_194_lighting_control"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_194_lighting_control", "_194_searching_cars_indicate_the_duration"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x23FrontRedarInfo() {
        if (is0x23DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[6] + this.mContext.getResources().getString(R.string.unit_cm);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x11RearAirInfo() {
        int i = mDifferentId;
        if (i == 13 || i == 17) {
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            int i2 = this.mCanBusInfoInt[3];
            if (i2 == 255) {
                GeneralAirData.rear_temperature = "HI";
            } else if (i2 == 0) {
                GeneralAirData.rear_temperature = "LO";
            } else {
                GeneralAirData.rear_temperature = (this.mCanBusInfoInt[3] + 15) + getTempUnitC(this.mContext);
            }
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
            if (intFromByteWithBit == 0) {
                GeneralAirData.rear_left_blow_head = true;
                GeneralAirData.rear_right_blow_head = true;
                GeneralAirData.rear_left_blow_foot = false;
                GeneralAirData.rear_right_blow_foot = false;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_left_auto_wind = false;
                GeneralAirData.rear_right_auto_wind = false;
            } else if (intFromByteWithBit == 1) {
                GeneralAirData.rear_left_blow_head = true;
                GeneralAirData.rear_right_blow_head = true;
                GeneralAirData.rear_left_blow_foot = true;
                GeneralAirData.rear_right_blow_foot = true;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_left_auto_wind = false;
                GeneralAirData.rear_right_auto_wind = false;
            } else if (intFromByteWithBit == 2) {
                GeneralAirData.rear_left_blow_head = false;
                GeneralAirData.rear_right_blow_head = false;
                GeneralAirData.rear_left_blow_foot = true;
                GeneralAirData.rear_right_blow_foot = true;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_left_auto_wind = false;
                GeneralAirData.rear_right_auto_wind = false;
            } else if (intFromByteWithBit == 15) {
                GeneralAirData.rear_left_blow_head = false;
                GeneralAirData.rear_right_blow_head = false;
                GeneralAirData.rear_left_blow_foot = false;
                GeneralAirData.rear_right_blow_foot = false;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_left_auto_wind = true;
                GeneralAirData.rear_right_auto_wind = true;
            }
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
            updateAirActivity(this.mContext, 1002);
        }
    }

    private void set0x43Pm25() {
        this.nowPm25 = this.mCanBusInfoInt[2];
    }

    private void set0x29Track() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] * 256) + iArr[3];
        if (i > 24870 && i <= 32768) {
            GeneralParkData.trackAngle = ((32768 - i) * 40) / 7898;
        } else {
            if (i <= 32768 || i >= 40531) {
                if (i == 0) {
                    GeneralParkData.trackAngle = 0;
                    updateParkUi(null, this.mContext);
                    return;
                }
                return;
            }
            GeneralParkData.trackAngle = ((-(i - 32768)) * 40) / 7763;
        }
        updateParkUi(null, this.mContext);
    }

    private void setPanoramic_0x50() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            detectParkPanoramic(true);
        } else {
            detectParkPanoramic(false);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "panorama_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "panorama_setting", "_194_360panoramic_10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "panorama_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "panorama_setting", "_194_360panoramic_11"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "panorama_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "panorama_setting", "_194_360panoramic_12"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        ArrayList arrayList2 = new ArrayList();
        switch (this.mCanBusInfoInt[3]) {
            case 0:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 1:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 2:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 3:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 4:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 5:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 6:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
            case 7:
                arrayList2.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(4, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList2.add(new PanoramicBtnUpdateEntity(7, true));
                arrayList2.add(new PanoramicBtnUpdateEntity(8, false));
                break;
        }
        GeneralParkData.dataList = arrayList2;
        updateParkUi(null, this.mContext);
    }

    private void setWarnInfo0x44() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 14; i++) {
            int i2 = i + 2;
            if (this.mCanBusInfoInt[i2] != 0) {
                arrayList.add(new WarningEntity(getWarningMsg(this.mCanBusInfoInt[i2])));
            }
        }
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
    }

    private String getWarningMsg(int i) {
        return i == 0 ? "" : CommUtil.getStrByResId(this.mContext, "_194_warning_msg_" + i);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x24() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen || mIsHandBreak != GeneralDoorData.isHandBrakeUp || mIsBelt != GeneralDoorData.isSeatBeltTie) {
            updateDoorView(this.mContext);
        }
        mIsFLDoorLast = GeneralDoorData.isLeftFrontDoorOpen;
        mIsFRDoorLast = GeneralDoorData.isRightFrontDoorOpen;
        mIsRLDoorLast = GeneralDoorData.isLeftRearDoorOpen;
        mIsRRDoorLast = GeneralDoorData.isRightRearDoorOpen;
        mIsFrontLast = GeneralDoorData.isFrontOpen;
        mIsBackLast = GeneralDoorData.isBackOpen;
        mIsHandBreak = GeneralDoorData.isHandBrakeUp;
        mIsBelt = GeneralDoorData.isSeatBeltTie;
    }

    private void setAirData0x23() {
        if (isAirMsgRepeat(this.mCanBusInfoByte)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        if (intFromByteWithBit == 0) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (intFromByteWithBit == 15) {
            GeneralAirData.front_auto_wind_model = true;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_auto_wind_speed = GeneralAirData.front_wind_level == 15;
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        GeneralAirData.econ = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 2);
        try {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
        } catch (Exception e) {
            Log.e("[CAR_194]", "<AIR> erro=" + e);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirData0x27() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
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
        if (15 == i) {
            return "HI";
        }
        if (16 == i) {
            return 16 + getTempUnitC(this.mContext);
        }
        return (1 > i || 14 < i) ? "--" : (i + 16) + getTempUnitC(this.mContext);
    }

    private void setRearRadar0x22() {
        GeneralParkData.isShowLeftTopOneDistanceUi = true;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.strOnlyOneDistance = this.mCanBusInfoInt[6] + this.mContext.getResources().getString(R.string.unit_cm);
        updateParkUi(null, this.mContext);
    }

    private void keyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void volKnobTurn(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void otherKnobTurn(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }

    private void detectParkPanoramic(Boolean bool) {
        forceReverse(this.mContext, bool.booleanValue());
    }

    private void set0x20SwcKey() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            keyClick(0);
            return;
        }
        if (i == 1) {
            keyClick(7);
            return;
        }
        if (i == 2) {
            keyClick(8);
            return;
        }
        if (i == 3) {
            keyClick(47);
            return;
        }
        if (i == 4) {
            keyClick(48);
            return;
        }
        if (i != 50) {
            switch (i) {
                case 6:
                    keyClick(14);
                    break;
                case 7:
                    keyClick(2);
                    break;
                case 8:
                    keyClick(HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    keyClick(14);
                    break;
                default:
                    switch (i) {
                        case 16:
                            keyClick(HotKeyConstant.K_SPEECH);
                            break;
                        case 17:
                            keyClick(52);
                            break;
                        case 18:
                            keyClick(50);
                            break;
                        case 19:
                            keyClick(52);
                            break;
                        case 20:
                            updateAirActivity(this.mContext, 1001);
                            break;
                        case 21:
                            keyClick(HotKeyConstant.K_CAN_CONFIG);
                            break;
                        case 22:
                            keyClick(3);
                            break;
                        case 23:
                            if (SharePreUtil.getBoolValue(this.mContext, Constant.SHARE_IS_PANORAMIC, false)) {
                                detectParkPanoramic(false);
                                break;
                            } else {
                                detectParkPanoramic(true);
                                break;
                            }
                        default:
                            switch (i) {
                                case 31:
                                    keyClick(58);
                                    break;
                                case 32:
                                    otherKnobTurn(47);
                                    break;
                                case 33:
                                    otherKnobTurn(48);
                                    break;
                                default:
                                    switch (i) {
                                        case 128:
                                            keyClick(HotKeyConstant.K_SLEEP);
                                            break;
                                        case 129:
                                            volKnobTurn(7);
                                            break;
                                        case 130:
                                            volKnobTurn(8);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        keyClick(128);
    }

    private void setCarIdCmd() {
        switch (getCurrentCanDifferentId()) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 1});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 3});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 6});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 1});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 0});
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 3});
                break;
            case 9:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 2});
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 4});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
                break;
            case 12:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 4});
                break;
            case 13:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 7});
                break;
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 5});
                break;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 6});
                break;
            case 16:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 8});
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 9});
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 6});
                break;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 7});
                break;
            case 20:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 0});
                break;
            case 21:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 3});
                break;
            case 22:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 2});
                break;
        }
        if (this.eachID != 20) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 80, 10});
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23FrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23FrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private String getHybridSystemInfo(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._194_EnergyInfo1_1);
            case 2:
                return this.mContext.getString(R.string._194_EnergyInfo1_2);
            case 3:
                return this.mContext.getString(R.string._194_EnergyInfo1_3);
            case 4:
                return this.mContext.getString(R.string._194_EnergyInfo1_4);
            case 5:
                return this.mContext.getString(R.string._194_EnergyInfo1_5);
            case 6:
                return this.mContext.getString(R.string._194_EnergyInfo1_6);
            case 7:
                return this.mContext.getString(R.string._194_EnergyInfo1_7);
            default:
                return this.mContext.getString(R.string._194_EnergyInfo1_0);
        }
    }

    private boolean is0x54InfoChange() {
        if (Arrays.equals(this.m0x54Info, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x54Info = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    public void showPmInfo() {
        showPm25Dialog(getActivity(), this.nowPm25);
    }

    private void showPm25Dialog(Context context, int i) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._194_pm_info, (ViewGroup) null, true);
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        Button button = (Button) viewInflate.findViewById(R.id.b1);
        Button button2 = (Button) viewInflate.findViewById(R.id.b2);
        Button button3 = (Button) viewInflate.findViewById(R.id.b3);
        Button button4 = (Button) viewInflate.findViewById(R.id.b4);
        Button button5 = (Button) viewInflate.findViewById(R.id.b5);
        if (i < 37) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.INVISIBLE);
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);
            button5.setVisibility(View.INVISIBLE);
        } else if (i > 37 && i <= 75) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.INVISIBLE);
            button4.setVisibility(View.INVISIBLE);
            button5.setVisibility(View.INVISIBLE);
        } else if (i > 75 && i <= 125) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.INVISIBLE);
            button5.setVisibility(View.INVISIBLE);
        } else if (i > 125 && i <= 250) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);
            button5.setVisibility(View.INVISIBLE);
        } else if (i > 250 && i < 255) {
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            button4.setVisibility(View.VISIBLE);
            button5.setVisibility(View.VISIBLE);
        }
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r9v7, types: [com.hzbhd.canbus.car._194.MsgMgr$2] */
    public void showEnergyDialog(Context context, int i) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout._194_energy_level, (ViewGroup) null, true);
        final AlertDialog alertDialogCreate = new AlertDialog.Builder(context).setView(viewInflate).create();
        ((TextView) viewInflate.findViewById(R.id.level)).setText(i + "LEVEL");
        alertDialogCreate.getWindow().setType(2003);
        alertDialogCreate.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialogCreate.show();
        new CountDownTimer(5000L, 1000L) { // from class: com.hzbhd.canbus.car._194.MsgMgr.2
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                alertDialogCreate.dismiss();
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -80, 4});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -80, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -80, 2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -80, 3});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(new byte[]{22, -79, Byte.parseByte(new DecimalFormat("00").format((i / 60) % 60)), Byte.parseByte(new DecimalFormat("00").format(i % 60))});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        str.hashCode();
        switch (str) {
            case "AM1":
                i3 = 16;
                break;
            case "AM2":
                i3 = 18;
                break;
            case "FM1":
                i3 = 1;
                break;
            case "FM2":
                i3 = 2;
                break;
            case "FM3":
                i3 = 3;
                break;
            default:
                i3 = 0;
                break;
        }
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        byte[] bArr = this.mMediaInfo;
        bArr[0] = (byte) freqByteHiLo[0];
        bArr[1] = (byte) freqByteHiLo[1];
        bArr[2] = (byte) i;
        CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -64, 1, 1, (byte) i3}, bArr));
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, -1, 1});
        sendId3Cmds(str4, 0, 0, str6);
        sendMusicProgress(j2, b4, b5);
    }

    private void sendMusicProgress(long j, byte b, byte b2) {
        int i = (int) (j / 1000);
        CanbusMsgSender.sendMsg(new byte[]{22, -63, 8, (byte) ((i % 3600) / 60), (byte) (i % 60), b, b2});
    }

    private void sendId3Cmds(String str, int i, int i2, String str2) {
        try {
            CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -64, 8, 1, 1}, Util.exceptBOMHead(str.getBytes("Unicode")), (byte) 0, (byte) 0, Util.exceptBOMHead(str2.getBytes("Unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2, byte b, byte b2, byte[] bArr3) {
        return Util.byteMerger(Util.byteMerger(Util.byteMerger(Util.byteMerger(bArr, bArr2), new byte[]{b}), new byte[]{b2}), bArr3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 0});
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
