package com.hzbhd.canbus.car._195;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    static final String CAN_195_SAVE_RADAR_DISP = "__195_SAVE_RADAR_DISP";
    static int cam3603dMode = 0;
    static int cam3603dModeLast = 0;
    static int cam360Guideline = 0;
    static boolean isCam360LowSpeedOpen = false;
    private static int lastPm25Lev = 0;
    private static int lastSwcKey = 0;
    private static int lastSwcSt = 0;
    private static int mDifferentId = 0;
    private static boolean mIsBackLast = false;
    private static boolean mIsBelt = false;
    private static boolean mIsFLDoorLast = false;
    private static boolean mIsFRDoorLast = false;
    private static boolean mIsFrontLast = false;
    private static boolean mIsHandBreak = false;
    private static boolean mIsRLDoorLast = false;
    private static boolean mIsRRDoorLast = false;
    static boolean mLast360st = false;
    private static int nowPm25Lev;
    private AirPageUiSet mAirPageUiSet;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private ParkPageUiSet mParkPageUiSet;

    private void setSwcKey() {
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
            case 6:
            case 7:
            case 8:
            case 9:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 5});
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 4});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 2});
                break;
            case 12:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 3});
                break;
            default:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 81, 0});
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        mDifferentId = getCurrentCanDifferentId();
        setCarIdCmd();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 36) {
            setDoorData0x24();
        }
        if (i == 39) {
            setAirData0x27();
            return;
        }
        if (i == 41) {
            setTrack();
            return;
        }
        if (i == 80) {
            setPanoramic_0x50();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        switch (i) {
            case 32:
                setSwcKey();
                break;
            case 33:
                if (!isAirMsgRepeat(bArr)) {
                    setAirData0x23();
                    break;
                }
                break;
            case 34:
                setRearRadar0x22();
                break;
            default:
                switch (i) {
                    case 65:
                        recI5_0x41();
                        recEi5_2019_0x41();
                        recI6_2019_0x41();
                        recRx3_2018_other_0x41();
                        break;
                    case 66:
                        recI6_2016_2017_0x42();
                        break;
                    case 67:
                        setPm25();
                        break;
                }
        }
    }

    private void setPm25() {
        if (getCurrentCanDifferentId() != 2 && getCurrentCanDifferentId() != 9 && getCurrentCanDifferentId() != 8) {
            getAirPageUiSet().getFrontArea().setShowCenterWheel(false);
            GeneralAirData.center_wheel = "";
            return;
        }
        getAirPageUiSet().getFrontArea().setShowCenterWheel(true);
        int i = this.mCanBusInfoInt[2];
        if (i >= 0 && i <= 37) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(R.string.pm_excellent);
            nowPm25Lev = 0;
        } else if (i >= 38 && i <= 75) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(R.string.pm_good);
            nowPm25Lev = 1;
        } else if (i >= 76 && i <= 125) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(R.string.pm_mild_pollution);
            nowPm25Lev = 2;
        } else if (i >= 126 && i <= 250) {
            GeneralAirData.center_wheel = "PM2.5:" + this.mContext.getResources().getString(R.string.pm_moderately_polluted);
            nowPm25Lev = 3;
        } else if (i >= 251 && i <= 255) {
            GeneralAirData.center_wheel = this.mContext.getResources().getString(R.string.pm_heavy_pollution);
            nowPm25Lev = 4;
        }
        if (nowPm25Lev != lastPm25Lev) {
            updateAirActivity(this.mContext, 1001);
        }
        lastPm25Lev = nowPm25Lev;
    }

    private void setTrack() {
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

    void initRadarDisp(Context context) {
        int intValue = SharePreUtil.getIntValue(context, CAN_195_SAVE_RADAR_DISP, 0);
        if (getCurrentCanDifferentId() != 10) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(intValue)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    boolean isHaveCam360() {
        return mDifferentId == 10;
    }

    private ParkPageUiSet getmParkPageUiSet() {
        if (this.mParkPageUiSet == null) {
            this.mParkPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getParkPageUiSet(this.mContext);
        }
        return this.mParkPageUiSet;
    }

    private AirPageUiSet getAirPageUiSet() {
        if (this.mAirPageUiSet == null) {
            this.mAirPageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getAirUiSet(this.mContext);
        }
        return this.mAirPageUiSet;
    }

    private void setPanoramic_0x50() {
        if (isHaveCam360()) {
            forceReverse(this.mContext, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
            ArrayList arrayList = new ArrayList();
            ParkPageUiSet parkPageUiSet = getmParkPageUiSet();
            cam360Guideline = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
            cam3603dMode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
            isCam360LowSpeedOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            int i = cam360Guideline;
            if (i == 0) {
                parkPageUiSet.getPanoramicBtnList().get(1).setTitleSrn("_194_car_line_off");
            } else if (i == 1) {
                parkPageUiSet.getPanoramicBtnList().get(1).setTitleSrn("_194_car_line_static");
            } else if (i == 2) {
                parkPageUiSet.getPanoramicBtnList().get(1).setTitleSrn("_194_car_line_dynamic");
            } else if (i == 3) {
                parkPageUiSet.getPanoramicBtnList().get(1).setTitleSrn("_194_car_line_static_dynamic");
            }
            int i2 = cam3603dMode;
            if (i2 == 0) {
                parkPageUiSet.getPanoramicBtnList().get(2).setTitleSrn("_194_2d");
                if (cam3603dModeLast != cam3603dMode) {
                    parkPageUiSet.getPanoramicBtnList().remove(7);
                    parkPageUiSet.getPanoramicBtnList().remove(7);
                    parkPageUiSet.getPanoramicBtnList().remove(7);
                    parkPageUiSet.getPanoramicBtnList().remove(7);
                }
            } else if (i2 == 1) {
                parkPageUiSet.getPanoramicBtnList().get(2).setTitleSrn("_194_3d");
                if (cam3603dModeLast != cam3603dMode) {
                    parkPageUiSet.getPanoramicBtnList().remove(7);
                    parkPageUiSet.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_front_left", ""));
                    parkPageUiSet.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_front_right", ""));
                    parkPageUiSet.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_rear_left", ""));
                    parkPageUiSet.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_rear_right", ""));
                }
            }
            int i3 = cam3603dModeLast;
            int i4 = cam3603dMode;
            if (i3 != i4 && i4 == 1) {
                parkPageUiSet.getPanoramicBtnList().add(new ParkPageUiSet.Bean(0, "_194_exit", ""));
            }
            parkPageUiSet.setPanoramicBtnList(parkPageUiSet.getPanoramicBtnList());
            if (parkPageUiSet.getPanoramicBtnList() != null) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
                arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[3] == 0));
                arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[3] == 4));
                arrayList.add(new PanoramicBtnUpdateEntity(5, this.mCanBusInfoInt[3] == 2));
                arrayList.add(new PanoramicBtnUpdateEntity(6, this.mCanBusInfoInt[3] == 6));
                if (cam3603dMode == 1) {
                    arrayList.add(new PanoramicBtnUpdateEntity(7, this.mCanBusInfoInt[3] == 1));
                    arrayList.add(new PanoramicBtnUpdateEntity(8, this.mCanBusInfoInt[3] == 7));
                    arrayList.add(new PanoramicBtnUpdateEntity(9, this.mCanBusInfoInt[3] == 3));
                    arrayList.add(new PanoramicBtnUpdateEntity(10, this.mCanBusInfoInt[3] == 5));
                }
            }
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
            mLast360st = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            cam3603dModeLast = cam3603dMode;
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private boolean isNoDoor() {
        return getCurrentCanDifferentId() == 2 || getCurrentCanDifferentId() == 10;
    }

    private void setDoorData0x24() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = true ^ DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen || mIsHandBreak != GeneralDoorData.isHandBrakeUp || mIsBelt != GeneralDoorData.isSeatBeltTie) {
            if (isNoDoor()) {
                GeneralDoorData.isRightFrontDoorOpen = false;
                GeneralDoorData.isLeftFrontDoorOpen = false;
                GeneralDoorData.isRightRearDoorOpen = false;
                GeneralDoorData.isLeftRearDoorOpen = false;
                GeneralDoorData.isBackOpen = false;
                GeneralDoorData.isFrontOpen = false;
            }
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
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
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
        String str2 = "";
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = "-" + intFromByteWithBit;
        } else {
            str = intFromByteWithBit + "";
        }
        if (getCurrentCanDifferentId() != 4 && getCurrentCanDifferentId() != 3 && getCurrentCanDifferentId() != 5 && getCurrentCanDifferentId() != 7 && getCurrentCanDifferentId() != 9) {
            str2 = str;
        }
        return str2 + getTempUnitC(this.mContext);
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

    private void recI6_2016_2017_0x42() throws Resources.NotFoundException {
        String string;
        String string2;
        if (getCurrentCanDifferentId() == 6 || getCurrentCanDifferentId() == 7 || getCurrentCanDifferentId() == 3 || getCurrentCanDifferentId() == 4 || getCurrentCanDifferentId() == 5) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 0) {
                string = this.mContext.getResources().getString(R.string._194_end);
            } else {
                string = this.mContext.getResources().getString(R.string._194_start);
            }
            arrayList.add(new SettingUpdateEntity(1, 0, string).setValueStr(true));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
                string2 = this.mContext.getResources().getString(R.string._194_end);
            } else {
                string2 = this.mContext.getResources().getString(R.string._194_start);
            }
            arrayList.add(new SettingUpdateEntity(2, 0, string2).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void recEi5_2019_0x41() throws Resources.NotFoundException {
        String string;
        if (getCurrentCanDifferentId() == 11 || getCurrentCanDifferentId() == 12) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 0) {
                string = this.mContext.getResources().getString(R.string._194_end);
            } else {
                string = this.mContext.getResources().getString(R.string._194_start);
            }
            arrayList.add(new SettingUpdateEntity(3, 0, string).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
            int i = this.mCanBusInfoInt[7];
            if (i >= 1) {
                i--;
            }
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(i)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void recRx3_2018_other_0x41() throws Resources.NotFoundException {
        String string;
        if (getCurrentCanDifferentId() == 0 || getCurrentCanDifferentId() == 1 || getCurrentCanDifferentId() == 2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
            arrayList.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0 ? this.mContext.getResources().getString(R.string._194_end) : this.mContext.getResources().getString(R.string._194_start)).setValueStr(true));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1) == 0) {
                string = this.mContext.getResources().getString(R.string._194_end);
            } else {
                string = this.mContext.getResources().getString(R.string._194_start);
            }
            arrayList.add(new SettingUpdateEntity(3, 0, string).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void recI5_0x41() {
        if (getCurrentCanDifferentId() == 10) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 2))));
            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
            arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2))));
            arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1))));
            arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
            arrayList.add(new SettingUpdateEntity(3, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
            arrayList.add(new SettingUpdateEntity(3, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2))));
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))));
            arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 2))));
            arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2))));
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(this.mCanBusInfoInt[11])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void recI6_2019_0x41() {
        Resources resources;
        int i;
        if (getCurrentCanDifferentId() == 8 || getCurrentCanDifferentId() == 9) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1) == 0) {
                resources = this.mContext.getResources();
                i = R.string._194_end;
            } else {
                resources = this.mContext.getResources();
                i = R.string._194_start;
            }
            arrayList.add(new SettingUpdateEntity(1, 0, resources.getString(i)).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }
}
