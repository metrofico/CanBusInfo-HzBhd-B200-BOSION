package com.hzbhd.canbus.car._479;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCarDoorData;
    private Context mContext;
    int[] mDoorInfo;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");
    String haveDisc1 = "";
    String haveDisc2 = "";
    String haveDisc3 = "";
    String haveDisc4 = "";
    String haveDisc5 = "";
    String haveDisc6 = "";

    private String getCDCState(int i) {
        switch (i) {
            case 1:
                return "Disc 1";
            case 2:
                return "Disc 2";
            case 3:
                return "Disc 3";
            case 4:
                return "Disc 4";
            case 5:
                return "Disc 5";
            case 6:
                return "Disc 6";
            default:
                return "NO MEDIA";
        }
    }

    private String getHaveState(boolean z) {
        return z ? "有" : "无";
    }

    private String getRandomState(int i) {
        return i != 1 ? i != 2 ? "Random OFF" : "Disc Random" : "All Disc Random";
    }

    private String getRptState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "Rpt" : "Disc Rpt" : "Track Rpt" : "All Disc Rpt";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 55});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 53});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 39});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 57});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            set0x20WheelKey(byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            set0x24BasicInfo(byteArrayToIntArray);
            return;
        }
        if (i == 39) {
            set0x27AirInfo(byteArrayToIntArray);
            return;
        }
        if (i == 41) {
            set0x29Track(byteArrayToIntArray);
            return;
        }
        if (i == 48) {
            updateVersionInfo(this.mContext, getVersionStr(bArr));
            return;
        }
        if (i != 57) {
            switch (i) {
                case 53:
                    set0x35DashboardInfo2(byteArrayToIntArray);
                    break;
                case 54:
                    set0x36TireInfo(byteArrayToIntArray);
                    break;
                case 55:
                    set0x37FuelInfo(byteArrayToIntArray);
                    break;
            }
            return;
        }
        set0x39CDCInfo(byteArrayToIntArray);
    }

    private void set0x39CDCInfo(int[] iArr) {
        GeneralOriginalCarDeviceData.cdStatus = getCDCState(DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4));
        GeneralOriginalCarDeviceData.runningState = getChangerState(this.mContext, DataHandleUtils.getIntFromByteWithBit(iArr[4], 4, 4));
        GeneralOriginalCarDeviceData.rpt_off = DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4) == 0;
        GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4) == 1;
        Log.d(OriginalBtnAction.RPT, GeneralOriginalCarDeviceData.rpt + "");
        GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4) == 2;
        GeneralOriginalCarDeviceData.rpt_disc = DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 4) == 3;
        GeneralOriginalCarDeviceData.rdm_off = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4) == 0;
        GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4) == 1;
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4) == 2;
        this.haveDisc1 = getHaveState(DataHandleUtils.getBoolBit0(iArr[2]));
        this.haveDisc2 = getHaveState(DataHandleUtils.getBoolBit1(iArr[2]));
        this.haveDisc3 = getHaveState(DataHandleUtils.getBoolBit2(iArr[2]));
        this.haveDisc4 = getHaveState(DataHandleUtils.getBoolBit3(iArr[2]));
        this.haveDisc5 = getHaveState(DataHandleUtils.getBoolBit4(iArr[2]));
        this.haveDisc6 = getHaveState(DataHandleUtils.getBoolBit5(iArr[2]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.haveDisc1 + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.haveDisc2 + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.haveDisc3 + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.haveDisc4 + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.haveDisc5 + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(5, this.haveDisc6 + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(6, iArr[5] + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(7, iArr[6] + ":" + iArr[7]));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private String getChangerState(Context context, int i) {
        if (i == 0) {
            return context.getString(R.string.ford_original_status1);
        }
        if (i == 1) {
            return context.getString(R.string.device_run_status_4);
        }
        if (i == 2) {
            return context.getString(R.string.device_run_status_5);
        }
        if (i == 3) {
            return context.getString(R.string._432_CD_state8);
        }
        if (i != 4) {
            return i != 5 ? "NO STATE" : context.getString(R.string._337_cd_state_3);
        }
        return context.getString(R.string._432_CD_state9);
    }

    private void set0x36TireInfo(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TireUpdateEntity(0, DataHandleUtils.getIntFromByteWithBit(iArr[6], 6, 1), new String[]{((DataHandleUtils.getIntFromByteWithBit(iArr[6], 2, 1) << 8) + iArr[3]) + "kPa"}));
        arrayList.add(new TireUpdateEntity(1, DataHandleUtils.getIntFromByteWithBit(iArr[6], 7, 1), new String[]{((DataHandleUtils.getIntFromByteWithBit(iArr[6], 3, 1) << 8) + iArr[2]) + "kPa"}));
        arrayList.add(new TireUpdateEntity(2, DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 1), new String[]{((DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 1) << 8) + iArr[5]) + "kPa"}));
        arrayList.add(new TireUpdateEntity(3, DataHandleUtils.getIntFromByteWithBit(iArr[6], 5, 1), new String[]{((DataHandleUtils.getIntFromByteWithBit(iArr[6], 1, 1) << 8) + iArr[4]) + "kPa"}));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private void set0x35DashboardInfo2(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data2"), iArr[3] + this.mContext.getString(R.string.unit_kmh)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data3"), ((iArr[4] * 256) + iArr[5]) + (DataHandleUtils.getBoolBit6(iArr[12]) ? "mil" : "km")));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data4"), ((iArr[6] * 256) + iArr[7]) + "rpm"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_279_dashboard_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_dashboard_data5"), ((iArr[9] << 16) + (iArr[10] << 8) + iArr[11]) + (DataHandleUtils.getBoolBit6(iArr[12]) ? "mil" : "km")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(iArr[3]);
        updateOutDoorTemp(this.mContext, (iArr[8] - 40) + (DataHandleUtils.getBoolBit7(iArr[12]) ? getTempUnitF(this.mContext) : getTempUnitC(this.mContext)));
        Log.d("室外温度", (iArr[8] - 40) + (DataHandleUtils.getBoolBit7(iArr[12]) ? getTempUnitF(this.mContext) : getTempUnitC(this.mContext)));
    }

    private void set0x37FuelInfo(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "vm_golf7_vehicle_setup_mfd_avg_consumption"), this.formatDecimal1.format(DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) * 0.1d) + this.mContext.getString(R.string.vm_golf7_vehicle_setup_units_consumption_l100km)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "instaneous_fuel_consumption"), this.formatDecimal1.format(DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) * 0.1d) + this.mContext.getString(R.string.vm_golf7_vehicle_setup_units_consumption_l100km)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "driven_distance"), this.formatDecimal1.format(((iArr[6] << 16) + (iArr[7] << 8) + iArr[8]) * 0.1d) + this.mContext.getString(R.string.vm_golf7_vehicle_setup_units_distance_km)));
        updateSpeedInfo(iArr[3]);
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "fuel_consumption_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "average_speed"), this.formatDecimal1.format(DataHandleUtils.getMsbLsbResult(iArr[9], iArr[10]) * 0.1d) + this.mContext.getString(R.string.unit_km_h)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x29Track(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[2], (byte) iArr[3], 0, 1200, 16);
            Log.d("trackAngle", GeneralParkData.trackAngle + "");
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x27AirInfo(int[] iArr) {
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mContext, iArr[4]);
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mContext, iArr[5]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(iArr[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(iArr[6]);
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.front_wind_level = iArr[8];
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x24BasicInfo(int[] iArr) {
        Context context;
        int i;
        Context context2;
        int i2;
        Context context3;
        int i3;
        if (DataHandleUtils.getBoolBit0(iArr[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            if (isDoorInfoChange(iArr)) {
                updateDoorView(this.mContext);
            }
        }
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_308_title_1");
        if (DataHandleUtils.getBoolBit2(iArr[3])) {
            context = this.mContext;
            i = R.string._194_open;
        } else {
            context = this.mContext;
            i = R.string._194_turn_off;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, context.getString(i)));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "gear_p");
        if (DataHandleUtils.getBoolBit1(iArr[3])) {
            context2 = this.mContext;
            i2 = R.string.gear_not_p;
        } else {
            context2 = this.mContext;
            i2 = R.string.gear_p;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, context2.getString(i2)));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "reverse_state");
        if (DataHandleUtils.getBoolBit0(iArr[3])) {
            context3 = this.mContext;
            i3 = R.string.reversing;
        } else {
            context3 = this.mContext;
            i3 = R.string.non_reverse;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, context3.getString(i3)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x20WheelKey(int[] iArr) {
        switch (iArr[2]) {
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
                realKeyLongClick1(this.mContext, 20, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 21, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 15, iArr[3]);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 128, iArr[3]);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 12:
                startDrivingDataActivity(this.mContext, 0);
                break;
            case 13:
                startDrivingDataActivity(this.mContext, 1);
                break;
            case 14:
                realKeyLongClick1(this.mContext, 21, iArr[3]);
                break;
            case 15:
                realKeyLongClick1(this.mContext, 23, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 20, iArr[3]);
                break;
            case 18:
                realKeyLongClick1(this.mContext, 22, iArr[3]);
                break;
            case 19:
                realKeyLongClick1(this.mContext, 27, iArr[3]);
                break;
            case 20:
                startDrivingDataActivity(this.mContext, 2);
                break;
            case 21:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 22:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 23:
                realKeyLongClick1(this.mContext, 1, iArr[3]);
                break;
            case 24:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 25:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            case 26:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
            case 27:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 28:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 29:
                realKeyLongClick1(this.mContext, 76, iArr[3]);
                break;
            case 30:
                realKeyLongClick1(this.mContext, 77, iArr[3]);
                break;
            case 31:
                realKeyLongClick1(this.mContext, 130, iArr[3]);
                break;
            case 33:
                realKeyLongClick1(this.mContext, 62, iArr[3]);
                break;
            case 34:
                realKeyLongClick1(this.mContext, 130, iArr[3]);
                break;
            case 35:
                realKeyLongClick1(this.mContext, 141, iArr[3]);
                break;
            case 36:
                realKeyLongClick1(this.mContext, 65, iArr[3]);
                break;
            case 37:
                realKeyLongClick1(this.mContext, 65, iArr[3]);
                break;
            case 38:
                realKeyLongClick1(this.mContext, 65, iArr[3]);
                break;
            case 39:
                realKeyLongClick1(this.mContext, 65, iArr[3]);
                break;
            case 40:
                realKeyLongClick1(this.mContext, 65, iArr[3]);
                break;
            case 41:
                realKeyLongClick1(this.mContext, 65, iArr[3]);
                break;
            case 42:
                realKeyLongClick1(this.mContext, 30, iArr[3]);
                break;
            case 43:
                realKeyLongClick1(this.mContext, 50, iArr[3]);
                break;
            case 44:
                realKeyLongClick1(this.mContext, 151, iArr[3]);
                break;
            case 46:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_DISP, iArr[3]);
                break;
            case 47:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_CAN_CONFIG, iArr[3]);
                break;
            case 48:
                realKeyLongClick1(this.mContext, 32, iArr[3]);
                break;
            case 49:
                realKeyLongClick1(this.mContext, 33, iArr[3]);
                break;
            case 50:
                realKeyLongClick1(this.mContext, 34, iArr[3]);
                break;
            case 51:
                realKeyLongClick1(this.mContext, 35, iArr[3]);
                break;
            case 52:
                realKeyLongClick1(this.mContext, 36, iArr[3]);
                break;
            case 53:
                realKeyLongClick1(this.mContext, 37, iArr[3]);
                break;
            case 54:
                realKeyLongClick1(this.mContext, 38, iArr[3]);
                break;
            case 55:
                realKeyLongClick1(this.mContext, 39, iArr[3]);
                break;
            case 56:
                realKeyLongClick1(this.mContext, 40, iArr[3]);
                break;
            case 57:
                realKeyLongClick1(this.mContext, 41, iArr[3]);
                break;
            case 58:
                realKeyLongClick1(this.mContext, 63, iArr[3]);
                break;
            case 59:
                realKeyLongClick1(this.mContext, 64, iArr[3]);
                break;
            case 60:
                realKeyLongClick1(this.mContext, 150, iArr[3]);
                break;
            case 61:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 62:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 63:
                realKeyLongClick1(this.mContext, 4, iArr[3]);
                break;
            case 64:
                realKeyLongClick1(this.mContext, 31, iArr[3]);
                break;
            case 65:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_HOUR, iArr[3]);
                break;
            case 66:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_HOUR, iArr[3]);
                break;
            case 67:
                realKeyLongClick1(this.mContext, 58, iArr[3]);
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._479.MsgMgr$1] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._479.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    private static class OriginalDeviceData {
        private final String[] bottomBtns;
        private final List<OriginalCarDevicePageUiSet.Item> list;

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
            this(list, null);
        }

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
            this.list = list;
            this.bottomBtns = strArr;
        }

        public List<OriginalCarDevicePageUiSet.Item> getItemList() {
            return this.list;
        }

        public String[] getBottomBtns() {
            return this.bottomBtns;
        }
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc1", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc2", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc3", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc4", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc5", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc6", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_479_disc_track", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc8", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(HotKeyConstant.K_SLEEP, new OriginalDeviceData(arrayList, new String[0]));
    }

    private String resolveLeftAndRightTemp(Context context, int i) {
        if (i == 0) {
            return "LO";
        }
        if (32 == i) {
            return "HI";
        }
        if (1 > i || 29 < i) {
            return "";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return ((i / 2.0f) + 17.5d) + getTempUnitF(context);
        }
        return ((i / 2.0f) + 17.5d) + getTempUnitC(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.CDC.equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDoorInfoChange(int[] iArr) {
        if (Arrays.equals(this.mDoorInfo, iArr)) {
            return false;
        }
        this.mDoorInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
