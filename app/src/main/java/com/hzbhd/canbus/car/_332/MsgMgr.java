package com.hzbhd.canbus.car._332;

import android.content.Context;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    private int[] m0x11AirData;
    private int[] m0x22RearRadarData;
    private int[] m0x23FrontRadarData;
    private int[] m0x28Data;
    int[] m0x38TireAlert;
    int[] m0x38TireInfo;
    private int[] m0x3BData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private byte[] mTrackData;
    private UiMgr mUiMgr;
    private String[] arr0 = new String[3];
    private String[] arr1 = new String[3];
    private String[] arr2 = new String[3];
    private String[] arr3 = new String[3];
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    DecimalFormat df1 = new DecimalFormat("###0.0");
    DecimalFormat df2 = new DecimalFormat("###0.00");
    boolean isFrontLeftAlert = false;
    boolean isFrontRightAlert = false;
    boolean isRearLeftAlert = false;
    boolean isRearRightAlert = false;
    private int frontDefogState = 0;
    private int RearDefogState = 0;
    private boolean data1Bite3 = true;

    private String getDrivingMode(int i) {
        return i != 1 ? i != 2 ? "S" : "L" : "E";
    }

    private String getGearInformation(int i) {
        return i != 1 ? i != 2 ? "R" : "D" : "N";
    }

    public static int getMyIntFromByteWithBit(int i, int i2, int i3) {
        return ((i & 65535) >> i2) & ((1 << i3) - 1);
    }

    private int getRadarDistance(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 4;
        }
        if (i != 3) {
            return i != 4 ? 0 : 10;
        }
        return 7;
    }

    private boolean getTireAlertState(int i) {
        return i != 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        getmUigMgr(context).makeConnection();
        GeneralTireData.isHaveSpareTire = false;
        getmUigMgr(this.mContext).sendTime();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        byte b = bArr[1];
        if (b == 2) {
            setWheelKey0x02();
            return;
        }
        if (b == 17) {
            setAirInfo0x11();
            return;
        }
        if (b == 40) {
            set0x28BaseInfo();
            return;
        }
        if (b == 48) {
            set0x30TrackData();
            return;
        }
        if (b == 80) {
            setTachographInfo0x50();
            return;
        }
        if (b != Byte.MAX_VALUE) {
            switch (b) {
                case 33:
                    setWheelKey0x21();
                    break;
                case 34:
                    setRearRadar0x22();
                    break;
                case 35:
                    setFrontRadar0x23();
                    break;
                default:
                    switch (b) {
                        case 56:
                            set0x38TireInfo();
                            break;
                        case 57:
                            setTireAlert0x39();
                            break;
                        case 58:
                            setSettingsInfo0x3A();
                            break;
                        case 59:
                            setPanoramicData0x3B();
                            break;
                        case 60:
                            setDriverData0x3C();
                            break;
                        case 61:
                            setChargeState0x3D();
                            break;
                        case 62:
                            setEnergyInfo0x3E();
                            break;
                    }
            }
            return;
        }
        setVersionInfo0x7F();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getmUigMgr(this.mContext).sendNowTime(getMyIntFromByteWithBit(i, 0, 8), Integer.parseInt(getMyIntFromByteWithBit(i, 0, 4) + "" + getMyIntFromByteWithBit(i, 8, 4)), i4, i5, i6);
        getmUigMgr(this.mContext).sendCarType();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        getmUigMgr(this.mContext).sendMediaSourcesNoSRC();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        getmUigMgr(this.mContext).sendMediaSourcesRadio(str);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getmUigMgr(this.mContext).sendMediaSourcesMusic(b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getmUigMgr(this.mContext).sendMediaSourcesAux();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getmUigMgr(this.mContext).sendMediaSourcesBluetooth();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void voiceControlInfo(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 802
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._332.MsgMgr.voiceControlInfo(java.lang.String):void");
    }

    private void setWheelKey0x02() {
        if (this.eachId != 6) {
        }
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(HotKeyConstant.K_SLEEP);
                getmUigMgr(this.mContext).sendMediaSourcesPower();
                break;
            case 2:
                knobKey(7);
                break;
            case 3:
                knobKey(8);
                break;
            case 4:
                buttonKey(58);
                break;
            case 5:
                buttonKey(2);
                break;
            case 6:
                buttonKey(3);
                break;
            case 7:
                buttonKey(75);
                break;
            case 8:
                buttonKey(21);
                break;
            case 9:
                buttonKey(20);
                break;
            case 10:
                buttonKey(2);
                break;
            case 11:
                buttonKey(HotKeyConstant.K_CAN_CONFIG);
                break;
        }
    }

    private void setVersionInfo0x7F() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setTachographInfo0x50() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 2) {
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_current_state"), getTachographState(this.mCanBusInfoInt[2])));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_1"), getTachographStateInfo(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_2"), getTachographStateInfo(2, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_3"), getTachographStateInfo(3, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            return;
        }
        if (i == 255 || i == 243) {
            return;
        }
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_current_state"), getTachographState(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_1"), this.mContext.getString(R.string._332_playback_setting_27)));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_2"), this.mContext.getString(R.string._332_playback_setting_27)));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_tachograph"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_status_information_3"), this.mContext.getString(R.string._332_playback_setting_27)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setEnergyInfo0x3E() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information");
        int drivingItemIndexes = getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Total_distance");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df1;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(((iArr[2] * 65536) + (iArr[3] * 256) + iArr[4]) * 0.1d)).append("km").toString()));
        int drivingPageIndexes2 = getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information");
        int drivingItemIndexes2 = getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Reference_remaining_mileage");
        StringBuilder sb2 = new StringBuilder();
        DecimalFormat decimalFormat2 = this.df1;
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(decimalFormat2.format((iArr2[5] * 256) + iArr2[6])).append("km").toString()));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Percentage_of_charge"), this.mCanBusInfoInt[7] + "%"));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_flow"), getEnergyFlow(this.mCanBusInfoInt[8])));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Average_energy_consumption"), this.df1.format(this.mCanBusInfoInt[9] * 0.1d) + "km/kmh"));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Instantaneous_energy_consumption"), this.df1.format(this.mCanBusInfoInt[10] * 0.1d) + "km/kmh"));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_distribution_1"), this.df1.format(this.mCanBusInfoInt[11] * 0.1d) + "km"));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_distribution_2"), this.df1.format(this.mCanBusInfoInt[12] * 0.1d) + "km"));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_Energy_information"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_Energy_distribution_3"), this.df2.format(this.mCanBusInfoInt[13] * 0.75d) + "km"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setChargeState0x3D() {
        ArrayList arrayList = new ArrayList();
        int leftIndexes = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_332_charge");
        if (leftIndexes != -1) {
            arrayList.add(new SettingUpdateEntity(leftIndexes, 1, this.df1.format(this.mCanBusInfoInt[2] * 0.1d) + "A").setValueStr(true));
            StringBuilder sb = new StringBuilder();
            DecimalFormat decimalFormat = this.df1;
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new SettingUpdateEntity(leftIndexes, 2, sb.append(decimalFormat.format(((iArr[3] * 256) + iArr[4]) * 0.1d)).append("V").toString()).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDriverData0x3C() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_driverData_1"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_driverData_2"), getDrivingMode(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_332_driverData_1"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_332_driverData_3"), getGearInformation(this.mCanBusInfoInt[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setPanoramicData0x3B() {
        if (isPanoramicDataChange()) {
            ArrayList arrayList = new ArrayList();
            if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
                panoramicSwitch(true);
                arrayList.add(new PanoramicBtnUpdateEntity(0, true));
            } else {
                panoramicSwitch(false);
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
            }
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            if (intFromByteWithBit == 3) {
                arrayList.add(new PanoramicBtnUpdateEntity(1, true));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            } else if (intFromByteWithBit == 5) {
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, true));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            } else if (intFromByteWithBit == 7) {
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, true));
                arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            } else if (intFromByteWithBit == 9) {
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                arrayList.add(new PanoramicBtnUpdateEntity(4, true));
            }
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
            if (intFromByteWithBit2 == 1) {
                arrayList.add(new PanoramicBtnUpdateEntity(5, true));
                arrayList.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList.add(new PanoramicBtnUpdateEntity(7, false));
            } else if (intFromByteWithBit2 == 2) {
                arrayList.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList.add(new PanoramicBtnUpdateEntity(6, true));
                arrayList.add(new PanoramicBtnUpdateEntity(7, false));
            } else if (intFromByteWithBit2 == 3) {
                arrayList.add(new PanoramicBtnUpdateEntity(5, false));
                arrayList.add(new PanoramicBtnUpdateEntity(6, false));
                arrayList.add(new PanoramicBtnUpdateEntity(7, true));
            }
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void setSettingsInfo0x3A() {
        int leftIndexes;
        if (this.eachId == 7 && (leftIndexes = getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_332_setting_1")) != -1) {
            ArrayList arrayList = new ArrayList();
            switch (this.mCanBusInfoInt[2]) {
                case 1:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 2:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 3:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 4:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 5:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 6:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 7:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 6, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 8:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 7, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 9:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 8, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 10:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 9, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 11:
                    arrayList.add(new SettingUpdateEntity(leftIndexes, 10, Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setTireAlert0x39() {
        if (is0x38TireAlertChange()) {
            this.arr0[2] = getTireAlertInfo(this.mCanBusInfoInt[2]);
            this.arr1[2] = getTireAlertInfo(this.mCanBusInfoInt[3]);
            this.arr2[2] = getTireAlertInfo(this.mCanBusInfoInt[4]);
            this.arr3[2] = getTireAlertInfo(this.mCanBusInfoInt[5]);
            this.isFrontLeftAlert = getTireAlertState(this.mCanBusInfoInt[2]);
            this.isFrontRightAlert = getTireAlertState(this.mCanBusInfoInt[3]);
            this.isRearLeftAlert = getTireAlertState(this.mCanBusInfoInt[4]);
            this.isRearRightAlert = getTireAlertState(this.mCanBusInfoInt[5]);
            if (this.isFrontLeftAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
            }
            if (this.isFrontRightAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
            }
            if (this.isRearLeftAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
            }
            if (this.isRearRightAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
            }
            GeneralTireData.dataList = this.tyreInfoList;
            updateTirePressureActivity(null);
        }
    }

    private void set0x38TireInfo() {
        if (is0x38TireInfoChange()) {
            this.arr0[0] = this.mContext.getString(R.string._332_Tire_temperature) + (this.mCanBusInfoInt[2] - 40) + getTempUnitC(this.mContext);
            this.arr0[1] = this.mContext.getString(R.string._332_Tire_pressure) + this.df1.format(this.mCanBusInfoInt[6] * 0.02745d) + "bar";
            this.arr1[0] = this.mContext.getString(R.string._332_Tire_temperature) + (this.mCanBusInfoInt[3] - 40) + getTempUnitC(this.mContext);
            this.arr1[1] = this.mContext.getString(R.string._332_Tire_pressure) + this.df1.format(this.mCanBusInfoInt[7] * 0.02745d) + "bar";
            this.arr2[0] = this.mContext.getString(R.string._332_Tire_temperature) + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
            this.arr2[1] = this.mContext.getString(R.string._332_Tire_pressure) + this.df1.format(this.mCanBusInfoInt[8] * 0.02745d) + "bar";
            this.arr3[0] = this.mContext.getString(R.string._332_Tire_temperature) + (this.mCanBusInfoInt[5] - 40) + getTempUnitC(this.mContext);
            this.arr3[1] = this.mContext.getString(R.string._332_Tire_pressure) + this.df1.format(this.mCanBusInfoInt[9] * 0.02745d) + "bar";
            if (this.isFrontLeftAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
            }
            if (this.isFrontRightAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
            }
            if (this.isRearLeftAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
            }
            if (this.isRearRightAlert) {
                this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
            } else {
                this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
            }
            GeneralTireData.dataList = this.tyreInfoList;
            updateTirePressureActivity(null);
        }
    }

    private void set0x30TrackData() {
        if (isTrackDataChange()) {
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 8), (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 0, 7), 0, 12000, 16);
            } else {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 8), (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 0, 7), 0, -12000, 16);
            }
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x28BaseInfo() {
        if (this.data1Bite3 != DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            this.data1Bite3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            updateDoorView(this.mContext);
        }
        this.mCanBusInfoInt[3] = 0;
        if (is0x28DataChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setFrontRadar0x23() {
        if (is0x23DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[2]), getRadarDistance(this.mCanBusInfoInt[3]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadar0x22() {
        if (is0x22DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[2]), getRadarDistance(this.mCanBusInfoInt[3]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setWheelKey0x21() {
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
        if (i == 6) {
            buttonKey(3);
            return;
        }
        if (i == 7) {
            if (this.eachId == 2) {
                buttonKey(HotKeyConstant.K_SPEECH);
                return;
            } else {
                buttonKey(2);
                return;
            }
        }
        if (i != 18) {
            switch (i) {
                case 9:
                    if (this.eachId == 2) {
                        buttonKey(HotKeyConstant.K_1_PICKUP);
                        return;
                    } else {
                        buttonKey(HotKeyConstant.K_VOICE_PICKUP);
                        return;
                    }
                case 10:
                    if (this.eachId == 2) {
                        buttonKey(HotKeyConstant.K_2_HANGUP);
                        return;
                    } else {
                        buttonKey(184);
                        return;
                    }
                case 11:
                    buttonKey(45);
                    return;
                case 12:
                    buttonKey(46);
                    return;
                default:
                    switch (i) {
                        case 129:
                            int i2 = this.eachId;
                            if (i2 == 4 || i2 == 6 || i2 == 8 || i2 == 9) {
                                buttonKey(151);
                                break;
                            } else {
                                buttonKey(129);
                                break;
                            }
                        case 130:
                            int i3 = this.eachId;
                            if (i3 == 4 || i3 == 6 || i3 == 8 || i3 == 9) {
                                buttonKey(50);
                                break;
                            } else {
                                buttonKey(47);
                                break;
                            }
                        case 131:
                            int i4 = this.eachId;
                            if (i4 == 4 || i4 == 6 || i4 == 8 || i4 == 9) {
                                buttonKey(2);
                                break;
                            } else {
                                buttonKey(48);
                                break;
                            }
                        case 132:
                            int i5 = this.eachId;
                            if (i5 == 4 || i5 == 6 || i5 == 8 || i5 == 9) {
                                buttonKey(129);
                                break;
                            } else {
                                buttonKey(50);
                                break;
                            }
                        case 133:
                            int i6 = this.eachId;
                            if (i6 == 4 || i6 == 6 || i6 == 8 || i6 == 9) {
                                buttonKey(21);
                                break;
                            } else {
                                buttonKey(68);
                                break;
                            }
                        case HotKeyConstant.K_SLEEP /* 134 */:
                            int i7 = this.eachId;
                            if (i7 == 4 || i7 == 6 || i7 == 8 || i7 == 9) {
                                buttonKey(128);
                                break;
                            }
                        case 135:
                            int i8 = this.eachId;
                            if (i8 == 4 || i8 == 6 || i8 == 8 || i8 == 9) {
                                buttonKey(3);
                                break;
                            } else {
                                buttonKey(2);
                                break;
                            }
                        case 136:
                            int i9 = this.eachId;
                            if (i9 == 4 || i9 == 6 || i9 == 8 || i9 == 9) {
                                buttonKey(HotKeyConstant.K_DISP);
                                break;
                            } else {
                                buttonKey(58);
                                break;
                            }
                        case 137:
                            int i10 = this.eachId;
                            if (i10 == 4 || i10 == 6 || i10 == 8 || i10 == 9) {
                                buttonKey(14);
                                break;
                            }
                        case 138:
                            int i11 = this.eachId;
                            if (i11 == 4 || i11 == 6 || i11 == 8 || i11 == 9) {
                                buttonKey(20);
                                break;
                            }
                        case 139:
                            if (this.eachId == 7) {
                                buttonKey(7);
                                break;
                            }
                            break;
                        case 140:
                            if (this.eachId == 7) {
                                buttonKey(8);
                                break;
                            }
                            break;
                        case 141:
                            if (this.eachId == 7) {
                                buttonKey(HotKeyConstant.K_CARPLAY_SIRI);
                                break;
                            }
                            break;
                        case 142:
                            if (this.eachId == 7) {
                                buttonKey(220);
                                break;
                            }
                            break;
                    }
                    return;
            }
        }
        buttonKey(HotKeyConstant.K_SPEECH);
        int i12 = this.eachId;
        if (i12 == 4 || i12 == 6 || i12 == 8 || i12 == 9) {
            buttonKey(HotKeyConstant.K_SLEEP);
            getmUigMgr(this.mContext).sendMediaSourcesPower();
        }
    }

    private void setAirInfo0x11() {
        updateOutDoorTemp(this.mContext, getOutdoorTemperature());
        this.mCanBusInfoInt[7] = 0;
        if (is0x11DataChange()) {
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !getAirAutoState();
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            switch (this.mCanBusInfoInt[3]) {
                case 1:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_right_blow_foot = false;
                    GeneralAirData.front_left_blow_window = false;
                    GeneralAirData.front_right_blow_window = false;
                    break;
                case 2:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = false;
                    GeneralAirData.front_right_blow_window = false;
                    break;
                case 3:
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = false;
                    GeneralAirData.front_right_blow_window = false;
                    break;
                case 4:
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 5:
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_right_blow_foot = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 6:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_right_blow_foot = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 7:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
            }
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
            if (this.mCanBusInfoInt[4] == 0) {
                GeneralAirData.power = false;
            } else {
                GeneralAirData.power = true;
            }
            GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[5]);
            GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[6]);
            updateAirActivity(this.mContext, 1001);
            MyLog.e("Air", "Coming...Air");
        }
    }

    private boolean is0x11DataChange() {
        if (Arrays.equals(this.m0x11AirData, this.mCanBusInfoInt)) {
            return false;
        }
        this.m0x11AirData = this.mCanBusInfoInt;
        return true;
    }

    private boolean getAirAutoState() {
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1) == 1;
    }

    private String getTemperature(int i) {
        return ((i * 0.5d) + 17.0d) + getTempUnitC(this.mContext);
    }

    private String getOutdoorTemperature() {
        return (this.mCanBusInfoInt[7] - 40) + getTempUnitC(this.mContext);
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22RearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22RearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23FrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23FrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x28DataChange() {
        if (Arrays.equals(this.m0x28Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x28Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private String getTireAlertInfo(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._332_alert_1);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._332_alert_2);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._332_alert_3);
        }
        return this.mContext.getString(R.string._332_alert_4);
    }

    private boolean is0x38TireInfoChange() {
        if (Arrays.equals(this.m0x38TireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x38TireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x38TireAlertChange() {
        if (Arrays.equals(this.m0x38TireAlert, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x38TireAlert = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private UiMgr getmUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isPanoramicDataChange() {
        if (Arrays.equals(this.m0x3BData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x3BData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    public void panoramicSwitch(boolean z) {
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        forceReverse(this.mContext, z);
        MyLog.e("运行时间CD", (Long.valueOf(System.currentTimeMillis()).longValue() - lValueOf.longValue()) + "");
    }

    private String getEnergyFlow(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._332_drive);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._332_energy_recovery);
        }
        return this.mContext.getString(R.string._332_Stateless);
    }

    private String getTachographState(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._332_playback_setting_16);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._332_playback_setting_17);
        }
        if (i == 243) {
            return this.mContext.getString(R.string._332_playback_setting_19);
        }
        if (i == 255) {
            return this.mContext.getString(R.string._332_playback_setting_18);
        }
        return this.mContext.getString(R.string._332_playback_setting_20);
    }

    private String getTachographStateInfo(int i, boolean z) {
        if (i == 1) {
            if (z) {
                return this.mContext.getString(R.string._332_playback_setting_21);
            }
            return this.mContext.getString(R.string._332_playback_setting_22);
        }
        if (i != 2) {
            if (z) {
                return this.mContext.getString(R.string._332_playback_setting_25);
            }
            return this.mContext.getString(R.string._332_playback_setting_26);
        }
        if (z) {
            return this.mContext.getString(R.string._332_playback_setting_23);
        }
        return this.mContext.getString(R.string._332_playback_setting_24);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    private int getMonth(int i, int i2) {
        return Integer.parseInt(DataHandleUtils.getIntFromByteWithBit(i2, 0, 4) + "" + DataHandleUtils.getIntFromByteWithBit(i, 8, 4));
    }
}
