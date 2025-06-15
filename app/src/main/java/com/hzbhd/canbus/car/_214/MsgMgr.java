package com.hzbhd.canbus.car._214;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.config.constant.ClassName;
import com.hzbhd.config.constant.PackageName;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.service.ServiceConstants;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static boolean isLastLongClick = false;
    private static int lastPanelSt = 0;
    private static int lastPanelkey = 0;
    private static int lastSwcKey = 0;
    private static int lastSwcSt = 0;
    private static int longClickCount = 0;
    private static boolean mIsKonbClockwise = true;
    private static boolean mIsKonbSelClockwise = true;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private TimerHelper mRequestTimer;
    private UiMgr mUiMgr;
    private int mKonbCount = 0;
    private int mKonbSelCount = 0;
    private int mLastKonbCount = 0;
    private int mLastSelKonbCount = 0;
    private String mFuelUnit = "mpg";
    private int mData0_7 = 0;
    private int mData0_26 = 15;
    private int mData2_47 = 5;
    private int mData2_03 = 5;
    private boolean mEnable6_4 = false;
    private boolean mEnable6_3 = false;
    private boolean mEnable6_2 = false;
    private boolean mEnable6_1 = false;
    private int mStartTime = 0;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        switch (getCurrentEachCanId()) {
            case 0:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 11, 10});
                break;
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 9, 10});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 7, 10});
                break;
            case 3:
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 4, 10});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 5, 10});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 10, 10});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 6, 10});
                break;
            case 9:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 10});
                break;
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 8, 10});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 10});
                break;
            case 12:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 3, 10});
                break;
            case 13:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 10});
                break;
        }
        initAmplifierData(context);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final byte[][] bArr = {new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume}, new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 16)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 16)}, new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 10)}, new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 10)}};
        final TimerHelper timerHelper = new TimerHelper();
        timerHelper.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._214.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                } else {
                    timerHelper.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setTrack();
            setSwcKey();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 18) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData();
            return;
        }
        if (i == 20) {
            setDriveData();
            return;
        }
        if (i == 21) {
            setDriveData2();
            return;
        }
        if (i == 33) {
            set0x21PanelKeyData(context);
            return;
        }
        if (i == 34) {
            setPanelKnobVol();
            setPanelKnobSel();
            return;
        }
        if (i == 38) {
            setCarType();
            return;
        }
        if (i == 65) {
            setRadar();
            return;
        }
        if (i == 174) {
            setOriginalCarDeviceInfo();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData0x31();
            return;
        }
        if (i == 50) {
            set0x32BodyInfo();
            return;
        }
        if (i == 120) {
            settingInfo();
            return;
        }
        if (i == 121) {
            setHudData0x79();
        } else if (i == 165) {
            setOriginalCarDeviceInfo2();
        } else {
            if (i != 166) {
                return;
            }
            setAmplifierData();
        }
    }

    private void set0x32BodyInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, MainAction.DRIVE_DATA);
        int drivingItemIndexes = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_214_car_speed1");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[4], iArr[5])).append("RPM").toString()));
        int drivingPageIndexes2 = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, MainAction.DRIVE_DATA);
        int drivingItemIndexes2 = getUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_214_car_speed2");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[6], iArr2[7])).append("KM/H").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setOriginalCarDeviceInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr[7] * 256) + iArr[8]).append("").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append((iArr2[15] * 256) + iArr2[16]).append("").toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb3.append((iArr3[9] * 256) + iArr3[10]).append("").toString()));
        GeneralOriginalCarDeviceData.mList = arrayList;
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6])) {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.open);
        } else {
            GeneralOriginalCarDeviceData.cdStatus = this.mContext.getResources().getString(R.string.close);
        }
        GeneralOriginalCarDeviceData.cd = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralOriginalCarDeviceData.rpt_off = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) == 0;
        GeneralOriginalCarDeviceData.rpt_fold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) == 1;
        GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) == 2;
        GeneralOriginalCarDeviceData.rdm_off = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3) == 0;
        GeneralOriginalCarDeviceData.rdm_fold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3) == 1;
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3) == 2;
        Context context = this.mContext;
        GeneralOriginalCarDeviceData.runningState = context.getString(CommUtil.getStrIdByResId(context, "_123_divice_status_" + this.mCanBusInfoInt[5]));
        int[] iArr4 = this.mCanBusInfoInt;
        int i = (iArr4[13] * 256) + iArr4[14];
        int i2 = (iArr4[11] * 256) + iArr4[12];
        if (i == 0 && this.mStartTime != 0) {
            if (this.mRequestTimer == null) {
                this.mRequestTimer = new TimerHelper();
            }
            final byte[][] bArr = {new byte[]{22, -14, 10, 0}, new byte[]{22, -14, 10, 1}, new byte[]{22, -14, 10, 2}};
            this.mRequestTimer.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._214.MsgMgr.2
                int i = 0;

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    int i3 = this.i;
                    byte[][] bArr2 = bArr;
                    if (i3 >= bArr2.length) {
                        MsgMgr.this.mRequestTimer.stopTimer();
                    } else {
                        this.i = i3 + 1;
                        CanbusMsgSender.sendMsg(bArr2[i3]);
                    }
                }
            }, 0L, 150L);
        }
        this.mStartTime = i;
        if (i2 > 0 && i <= i2) {
            GeneralOriginalCarDeviceData.startTime = startEndTimeMethod(i);
            GeneralOriginalCarDeviceData.endTime = startEndTimeMethod(i2);
            GeneralOriginalCarDeviceData.progress = (i * 100) / i2;
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarDeviceInfo2() {
        String str;
        try {
            str = new String(DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 3, (byte) 0), "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            str = null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(this.mCanBusInfoInt[2] + 2, str));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setTrack() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 0, this.mCanBusInfoInt[3] + this.mContext.getResources().getString(R.string.unit_kmh)));
        int i = this.mCanBusInfoInt[7];
        if (i > 0 && 100 > i) {
            arrayList.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[7] + ""));
        } else if (i == 0) {
            arrayList.add(new DriverUpdateEntity(2, 1, "OFF"));
        } else if (i == 100) {
            arrayList.add(new DriverUpdateEntity(2, 1, "MAX"));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_left_blow_foot = true;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_left_blow_head = true;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6])) {
            GeneralAirData.front_left_blow_window = true;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])) {
            GeneralAirData.front_right_blow_foot = true;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7])) {
            GeneralAirData.front_right_blow_head = true;
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7])) {
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
        GeneralAirData.front_left_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRadar() {
        if (this.mCanBusInfoInt[13] == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(3, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        GeneralParkData.radar_distance_disable = new int[]{0, 255};
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr3 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr3[2], iArr3[3], iArr3[4], iArr3[5]);
        int[] iArr4 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr4[6], iArr4[7], iArr4[8], iArr4[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void settingInfo() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[10]));
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3);
        if (intFromByteWithBit > 5) {
            intFromByteWithBit = 5;
        }
        boolean boolBit72 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 2);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]));
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9]));
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[10]));
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[10]));
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 2);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2);
        boolean boolBit62 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[11]));
        boolean boolBit32 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]));
        boolean boolBit12 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12]));
        boolean boolBit22 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]));
        int indexBy2Bit11 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]));
        boolean boolBit02 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2);
        boolean boolBit73 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 3);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 5, 3);
        boolean boolBit63 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 3);
        boolean boolBit52 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        int indexBy2Bit12 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[13]));
        boolean boolBit53 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        int indexBy2Bit13 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13]));
        boolean boolBit43 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 1, 2);
        boolean boolBit33 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        int indexBy2Bit14 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14]));
        boolean boolBit23 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 5, 2);
        boolean boolBit13 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        int indexBy2Bit15 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[11]));
        boolean boolBit34 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]);
        int indexBy2Bit16 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]));
        int indexBy2Bit17 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13]));
        boolean boolBit24 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 3, 2);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 1, 2);
        int indexBy2Bit18 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[14]));
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 2);
        int intFromByteWithBit15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2);
        int indexBy2Bit19 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[15]));
        int indexBy2Bit20 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[15]));
        int intFromByteWithBit16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2);
        int indexBy2Bit21 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[16]));
        int indexBy2Bit22 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[16]));
        int indexBy2Bit23 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[16]));
        int indexBy2Bit24 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[16]));
        int indexBy2Bit25 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[16]));
        boolean boolBit14 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]);
        int indexBy2Bit26 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[16]));
        boolean boolBit03 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
        int intFromByteWithBit17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[16], 0, 2);
        boolean boolBit64 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
        int intFromByteWithBit18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 6, 2);
        boolean boolBit04 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        int indexBy2Bit27 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[17]));
        boolean boolBit74 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
        int intFromByteWithBit19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 3, 2);
        boolean boolBit65 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
        int indexBy2Bit28 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[17]));
        boolean boolBit54 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
        int intFromByteWithBit20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 0, 2);
        boolean boolBit44 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
        int intFromByteWithBit21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 5, 3);
        boolean boolBit75 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        int[] iArr = {indexBy2Bit, intFromByteWithBit, intFromByteWithBit2, intFromByteWithBit3, indexBy2Bit2, indexBy2Bit3, indexBy2Bit4, indexBy2Bit5, intFromByteWithBit4, intFromByteWithBit5, indexBy2Bit6, indexBy2Bit7, indexBy2Bit11, intFromByteWithBit6, intFromByteWithBit7, intFromByteWithBit8, intFromByteWithBit9, indexBy2Bit15, indexBy2Bit16, indexBy2Bit17, indexBy2Bit18, intFromByteWithBit13, intFromByteWithBit12, intFromByteWithBit14, intFromByteWithBit15, indexBy2Bit19, indexBy2Bit20, intFromByteWithBit16, indexBy2Bit21, indexBy2Bit22, indexBy2Bit23, indexBy2Bit24, indexBy2Bit25, indexBy2Bit26, intFromByteWithBit17, intFromByteWithBit18, indexBy2Bit27, intFromByteWithBit19, indexBy2Bit28, intFromByteWithBit20, intFromByteWithBit21, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 3, 2)};
        boolean[] zArr = {boolBit7, boolBit72, boolBit4, boolBit3, boolBit2, boolBit5, boolBit1, boolBit0, boolBit6, boolBit62, boolBit32, boolBit12, boolBit02, boolBit73, boolBit42, boolBit63, boolBit52, boolBit34, true, boolBit24, true, true, true, true, true, true, true, true, true, true, true, true, boolBit14, boolBit03, boolBit64, boolBit04, boolBit74, boolBit65, boolBit54, boolBit44, boolBit75, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])};
        int[] iArr2 = {indexBy2Bit12, indexBy2Bit13, intFromByteWithBit10, indexBy2Bit14, intFromByteWithBit11};
        boolean[] zArr2 = {boolBit53, boolBit43, boolBit33, boolBit23, boolBit13};
        int[] iArr3 = {indexBy2Bit8, indexBy2Bit9, indexBy2Bit10};
        boolean[] zArr3 = {true, boolBit22, true};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 42; i++) {
            arrayList.add(new SettingUpdateEntity(0, i, Integer.valueOf(iArr[i])).setEnable(zArr[i]));
        }
        for (int i2 = 0; i2 < 5; i2++) {
            arrayList.add(new SettingUpdateEntity(1, i2, Integer.valueOf(iArr2[i2])).setEnable(zArr2[i2]));
        }
        for (int i3 = 0; i3 < 3; i3++) {
            arrayList.add(new SettingUpdateEntity(2, i3, Integer.valueOf(iArr3[i3])).setEnable(zArr3[i3]));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        this.mEnable6_4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
        this.mEnable6_3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8]);
        this.mEnable6_2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]);
        this.mEnable6_1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]);
        setHudData();
    }

    private void setHudData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_0_7"), Integer.valueOf(this.mData0_7)).setEnable(this.mEnable6_4));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_0_62"), Integer.valueOf(this.mData0_26 - 15)).setProgress(this.mData0_26).setEnable(this.mEnable6_3));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_2_74"), Integer.valueOf(this.mData2_47 - 5)).setProgress(this.mData2_47).setEnable(this.mEnable6_2));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_213_79_title"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_213_79_title", "_213_79_2_30"), Integer.valueOf(this.mData2_03 - 5)).setProgress(this.mData2_03).setEnable(this.mEnable6_1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setHudData0x79() {
        this.mData0_7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        this.mData0_26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 5);
        this.mData2_47 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        this.mData2_03 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        setHudData();
    }

    private void setDriveData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            int i2 = i * 2;
            arrayList.add(new DriverUpdateEntity(0, i, sb.append(String.valueOf(((float) ((((iArr[i2 + 2] * 256) + iArr[i2 + 3]) * 0.1d) * 10.0d)) / 10.0f)).append(" ").append(this.mFuelUnit).toString()));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData2() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            int i2 = i * 2;
            arrayList.add(new DriverUpdateEntity(1, i, sb.append(String.valueOf(((float) ((((iArr[i2 + 2] * 256) + iArr[i2 + 3]) * 0.1d) * 10.0d)) / 10.0f)).append(" ").append(this.mFuelUnit).toString()));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarType() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 2, carType(this.mCanBusInfoInt[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAmplifierData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 16;
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 16;
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 10;
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 10;
            saveAmplifierData(this.mContext, this.mCanId);
            updateAmplifierActivity(new Bundle());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(this.mCanBusInfoInt[9])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String carType(int i) {
        Context context = this.mContext;
        return context.getString(CommUtil.getStrIdByResId(context, "_213_car" + i));
    }

    private void setDoorData() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
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

    private boolean isAirMsgReturn(byte[] bArr) {
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

    private String resolveFrontAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i > 6) {
            i = 0;
        }
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = new DecimalFormat("00").format(i) + " " + str2 + "  Khz";
            } else {
                str4 = new DecimalFormat("00").format(i) + "  " + str2 + "  Khz";
            }
        } else if (str2.length() == 5) {
            str4 = new DecimalFormat("00").format(i) + "  " + str2 + "Mhz";
        } else {
            str4 = new DecimalFormat("00").format(i) + " " + str2 + "Mhz";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), Util.byteMerger(new byte[]{22, -111, radioCurrentBand}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("AUX".getBytes(), 0, bArr, 0, "AUX".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), Util.byteMerger(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), Util.byteMerger(new byte[]{22, -111, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), Util.byteMerger(new byte[]{22, -111, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = String.format(new DecimalFormat("000").format(((b7 & 255) * 256) + (i & 255)), new Object[0]) + " " + new DecimalFormat("00").format(b4) + ":" + new DecimalFormat("00").format(b5) + "   ";
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -111;
        bArr[2] = b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), Util.byteMerger(bArr, str7.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + "         ";
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -111;
        bArr[2] = b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), Util.byteMerger(bArr, str5.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        String str4 = new DecimalFormat("000").format(i4) + " " + new DecimalFormat("00").format((byte) ((i / 60) % 60)) + new DecimalFormat("00").format((byte) (i % 60)) + "    ";
        byte b2 = 7;
        if (i6 != 1 && i6 != 2 && i6 != 3) {
            b2 = (i6 == 6 || i6 == 7) ? (byte) 6 : (byte) 0;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), Util.byteMerger(new byte[]{22, -111, b2}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private void panelBtnKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void setSwcKey() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                panelBtnKeyClick(0);
                break;
            case 1:
                panelBtnKeyClick(7);
                break;
            case 2:
                panelBtnKeyClick(8);
                break;
            case 3:
                panelBtnKeyClick(3);
                break;
            case 4:
                panelBtnKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                panelBtnKeyClick(14);
                break;
            case 6:
                panelBtnKeyClick(15);
                break;
            case 8:
                panelBtnKeyClick(45);
                break;
            case 9:
                panelBtnKeyClick(46);
                break;
            case 10:
                panelBtnKeyClick(2);
                break;
        }
    }

    private void set0x21PanelKeyData(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
        }
        if (i == 6) {
            realKeyLongClick1(context, 50);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(context, 3);
            return;
        }
        if (i == 32) {
            realKeyLongClick1(context, 128);
            return;
        }
        if (i == 43) {
            realKeyLongClick1(context, 52);
            return;
        }
        if (i == 45) {
            realKeyLongClick1(context, 59);
            return;
        }
        if (i == 84) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(PackageName.google_youtube, ClassName.google_youtube));
                intent.setFlags(268435456);
                context.startActivity(intent);
                return;
            } catch (Exception unused) {
                realKeyLongClick1(context, 79);
                return;
            }
        }
        if (i == 2) {
            realKeyLongClick1(context, 21);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 20);
            return;
        }
        switch (i) {
            case 22:
                sendAppIconSelect(context, 49, 1);
                break;
            case 23:
                realKeyLongClick1(context, 45);
                break;
            case 24:
                realKeyLongClick1(context, 46);
                break;
            case 25:
                realKeyLongClick1(context, 47);
                break;
            case 26:
                realKeyLongClick1(context, 48);
                break;
            case 27:
                realKeyLongClick1(context, 128);
                break;
        }
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick3_2(Context context, int i, int i2) {
        realKeyClick3_2(context, i, this.mCanBusInfoInt[2], i2);
    }

    private void realKeyClick4(int i) {
        realKeyClick(this.mContext, i);
    }

    private void konbKeyVol(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (mIsKonbClockwise) {
                realKeyClick4(7);
            } else {
                realKeyClick4(8);
            }
        }
    }

    private void konbKeySel(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (mIsKonbSelClockwise) {
                realKeyClick4(48);
            } else {
                realKeyClick4(47);
            }
        }
    }

    private void setPanelKnobVol() {
        if (this.mCanBusInfoInt[2] != 1) {
            return;
        }
        byte b = this.mCanBusInfoByte[3];
        int i = this.mLastKonbCount;
        mIsKonbClockwise = b - i >= 0;
        int iAbs = Math.abs(b - i);
        this.mKonbCount = iAbs;
        if (iAbs == 0) {
            return;
        }
        if (this.mCanBusInfoInt[2] == 1) {
            konbKeyVol(iAbs);
        }
        this.mLastKonbCount = this.mCanBusInfoByte[3];
    }

    private void setPanelKnobSel() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] != 2) {
            return;
        }
        int i = iArr[3];
        int i2 = this.mLastSelKonbCount;
        mIsKonbSelClockwise = i - i2 >= 0;
        int iAbs = Math.abs(i - i2);
        this.mKonbSelCount = iAbs;
        if (iAbs == 0) {
            return;
        }
        if (this.mCanBusInfoInt[2] == 2) {
            konbKeySel(iAbs);
        }
        this.mLastSelKonbCount = this.mCanBusInfoInt[3];
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void setFuelUnit(int i) {
        if (i == 0) {
            this.mFuelUnit = "mpg";
        } else if (i == 1) {
            this.mFuelUnit = "km/l";
        } else {
            if (i != 2) {
                return;
            }
            this.mFuelUnit = "l/100km";
        }
    }

    private class TimerHelper {
        private Timer mTimer;
        private TimerTask mTimerTask;

        private TimerHelper() {
        }

        public void startTimer(TimerTask timerTask, long j, long j2) {
            Log.i("TimerUtil", "startTimer: " + this);
            if (timerTask == null) {
                return;
            }
            this.mTimerTask = timerTask;
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            this.mTimer.schedule(this.mTimerTask, j, j2);
        }

        public void stopTimer() {
            Log.i("TimerUtil", "stopTimer: " + this);
            TimerTask timerTask = this.mTimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimerTask = null;
            }
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
            }
        }
    }

    private void sendAppIconSelect(Context context, int i, int i2) {
        String str;
        if (ConfigUtil.getDeviceId().contains("P0R")) {
            String str2 = Settings.System.getString(context.getContentResolver(), ServiceConstants.KEY_CURRENT_TOP_PACKAGENAME).split(":;:")[1];
            if (TextUtils.isEmpty(str2) || !str2.contains("com.android.launcher3")) {
                if (i == 49) {
                    realKeyClick(context, i);
                    return;
                } else {
                    realKeyClick3_2(context, i, i2);
                    return;
                }
            }
            if (i == 47) {
                str = "KEY_APP_SELECT_PREV";
            } else if (i == 48) {
                str = "KEY_APP_SELECT_NEXT";
            } else if (i != 49) {
                return;
            } else {
                str = "KEY_APP_SELECT_ENTER";
            }
            FutureUtil.instance.sendAppSelect(str);
        }
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
