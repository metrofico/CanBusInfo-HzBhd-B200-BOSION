package com.hzbhd.canbus.car._185;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Priority;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int volKnobValue;
    private int eachId;
    int[] mAirData;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    int[] mRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private boolean mSeatBeltStatus;
    private boolean mSubSeatBeltStatus;
    String resulttemp;
    private UiMgr uiMgr;
    private final int INVAILE_VALUE = -1;
    DecimalFormat format1 = new DecimalFormat("###0.00");
    DecimalFormat format2 = new DecimalFormat("000.0");
    DecimalFormat format3 = new DecimalFormat("00");
    DecimalFormat format4 = new DecimalFormat("000");

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        initCarMode();
        getUiMgr(context).initSettings();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initCarMode();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        initCarMode();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        initCarMode();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).sendMediaInfo0x91(133, "".getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -116, 3, (byte) i});
    }

    private void initCarMode() {
        switch (getCurrentEachCanId()) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 18, 53});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 11, 53});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 14, 53});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 19, 53});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 20, 53});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 21, 53});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 22, 53});
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, 36, 24, 53});
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            CarInfo0x11();
            return;
        }
        if (i == 18) {
            BodyDetailsInfo0x12(byteArrayToIntArray);
            return;
        }
        if (i == 33) {
            WheelKeyInfo0x21();
            return;
        }
        if (i == 34) {
            WheelSpinKey0x22();
            return;
        }
        if (i == 49) {
            AirInfo0x31(byteArrayToIntArray);
            return;
        }
        if (i == 50) {
            setSpeedInfo(byteArrayToIntArray);
            return;
        }
        if (i == 65) {
            RadarInfo0x41();
            return;
        }
        if (i == 135) {
            CentralControlInfo0x87();
        } else if (i == 232) {
            OriginalCarStatusInformation0xE8();
        } else {
            if (i != 240) {
                return;
            }
            VersionInfo0xF0();
        }
    }

    private void setSpeedInfo(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_185_speed1"), DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) + ""));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_185_speed2"), DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) + ""));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void CarInfo0x11() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(3);
                break;
            case 4:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                buttonKey(14);
                break;
            case 6:
                buttonKey(15);
                break;
            case 8:
                buttonKey(21);
                break;
            case 9:
                buttonKey(20);
                break;
            case 12:
                buttonKey(2);
                break;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, Priority.DEBUG_INT, 16);
        updateParkUi(null, this.mContext);
    }

    private void BodyDetailsInfo0x12(int[] iArr) {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(iArr[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit1(iArr[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(iArr[4]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void AirInfo0x31(int[] iArr) {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem(iArr));
        iArr[13] = 0;
        if (isAirDataNoChange(iArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(iArr[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(iArr[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(iArr[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(iArr[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr[4]);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_auto_wind_model = false;
        int i = iArr[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 1) {
            GeneralAirData.front_auto_wind_model = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
        } else {
            switch (i) {
                case 12:
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = false;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
            }
        }
        switch (iArr[7]) {
            case 0:
                GeneralAirData.front_wind_level = 0;
                break;
            case 1:
                GeneralAirData.front_wind_level = 1;
                break;
            case 2:
                GeneralAirData.front_wind_level = 2;
                break;
            case 3:
                GeneralAirData.front_wind_level = 3;
                break;
            case 4:
                GeneralAirData.front_wind_level = 4;
                break;
            case 5:
                GeneralAirData.front_wind_level = 5;
                break;
            case 6:
                GeneralAirData.front_wind_level = 6;
                break;
            case 7:
                GeneralAirData.front_wind_level = 7;
                break;
        }
        GeneralAirData.front_left_temperature = resolveAirTemperature(iArr[8]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(iArr[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private void RadarInfo0x41() {
        if (isRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 255) {
            i = 0;
        }
        int i2 = iArr[3];
        if (i2 == 255) {
            i2 = 0;
        }
        int i3 = iArr[4];
        if (i3 == 255) {
            i3 = 0;
        }
        int i4 = iArr[5];
        if (i4 == 255) {
            i4 = 0;
        }
        RadarInfoUtil.setRearRadarLocationData(4, i, i2, i3, i4);
        int[] iArr2 = this.mCanBusInfoInt;
        int i5 = iArr2[6];
        if (i5 == 255) {
            i5 = 0;
        }
        int i6 = iArr2[7];
        if (i6 == 255) {
            i6 = 0;
        }
        int i7 = iArr2[8];
        if (i7 == 255) {
            i7 = 0;
        }
        int i8 = iArr2[9];
        RadarInfoUtil.setFrontRadarLocationData(4, i5, i6, i7, i8 != 255 ? i8 : 0);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void WheelKeyInfo0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey1(0);
            return;
        }
        if (i == 1) {
            buttonKey1(1);
            return;
        }
        if (i == 37) {
            buttonKey1(128);
            return;
        }
        if (i == 43) {
            buttonKey1(52);
            return;
        }
        if (i == 55) {
            buttonKey1(58);
            return;
        }
        if (i == 67) {
            buttonKey1(33);
        } else if (i == 69) {
            buttonKey1(7);
        } else {
            if (i != 70) {
                return;
            }
            buttonKey1(8);
        }
    }

    private void WheelSpinKey0x22() {
        if (this.mCanBusInfoInt[2] != 1) {
            return;
        }
        int i = volKnobValue - this.mCanBusInfoByte[3];
        if (i < 0) {
            sendKnob_1(7, Math.abs(i));
        } else if (i > 0) {
            sendKnob_1(8, Math.abs(i));
        }
        volKnobValue = this.mCanBusInfoByte[3];
    }

    private void CentralControlInfo0x87() throws Resources.NotFoundException {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String string9;
        String string10;
        String string11;
        String string12;
        String string13;
        String string14;
        String string15;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_1");
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            string = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, string));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_2");
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            string2 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string2 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, string2));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_2_0");
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            string3 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string3 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, string3));
        int drivingPageIndexes4 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes4 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_2_1");
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            string4 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string4 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, string4));
        int drivingPageIndexes5 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes5 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_3_0");
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            string5 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string5 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, drivingItemIndexes5, string5));
        int drivingPageIndexes6 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes6 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_3_1");
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            string6 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string6 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes6, drivingItemIndexes6, string6));
        int drivingPageIndexes7 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes7 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_3_2");
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            string7 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string7 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes7, drivingItemIndexes7, string7));
        int drivingPageIndexes8 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes8 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_4_0");
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            string8 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string8 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes8, drivingItemIndexes8, string8));
        int drivingPageIndexes9 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes9 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_4_1");
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            string9 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string9 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes9, drivingItemIndexes9, string9));
        int drivingPageIndexes10 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes10 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_4");
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            string10 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string10 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes10, drivingItemIndexes10, string10));
        int drivingPageIndexes11 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes11 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_3");
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            string11 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string11 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes11, drivingItemIndexes11, string11));
        int drivingPageIndexes12 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes12 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_8");
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            string12 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string12 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes12, drivingItemIndexes12, string12));
        int drivingPageIndexes13 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes13 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_6");
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            string13 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string13 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes13, drivingItemIndexes13, string13));
        int drivingPageIndexes14 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes14 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_7");
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            string14 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string14 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes14, drivingItemIndexes14, string14));
        int drivingPageIndexes15 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes15 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_1193_setting_1_0");
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            string15 = this.mContext.getResources().getString(R.string._185_state1);
        } else {
            string15 = this.mContext.getResources().getString(R.string._185_state2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes15, drivingItemIndexes15, string15));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_185_setting_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_A"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_6_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_6_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1))));
        updateGeneralSettingData(arrayList2);
        updateGeneralDriveData(arrayList);
        updateSettingActivity(null);
        updateDriveDataActivity(null);
    }

    private void OriginalCarStatusInformation0xE8() {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        forceReverse(context, iArr[4] == 1 || iArr[5] == 1 || iArr[6] == 1 || iArr[7] == 1 || iArr[8] == 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[7] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[8] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[6] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[4] == 1));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Right_camera_status"), Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_panorama_camera_status"), Integer.valueOf(this.mCanBusInfoInt[5])));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Left_camera_status"), Integer.valueOf(this.mCanBusInfoInt[6])));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Front_camera_status"), Integer.valueOf(this.mCanBusInfoInt[7])));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_185_Original_vehicle_screen_status"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_185_Original_vehicle_screen_status", "_185_Rear_camera_status"), Integer.valueOf(this.mCanBusInfoInt[8])));
        updateGeneralSettingData(arrayList2);
        updateActivity(null);
    }

    private void VersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    public void buttonKey1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private String resolveAirTemperature(int i) {
        if (i == 254) {
            this.resulttemp = "Low_Temp";
        } else if (i == 255) {
            this.resulttemp = "High_Temp";
        } else {
            this.resulttemp = (i / 2.0f) + getTempUnitC(this.mContext);
        }
        return this.resulttemp;
    }

    private String resolveOutDoorTem(int[] iArr) {
        return ((iArr[13] / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp && this.mSeatBeltStatus == GeneralDoorData.isSeatBeltTie && this.mSubSeatBeltStatus == GeneralDoorData.isSubSeatBeltTie) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        this.mSubSeatBeltStatus = GeneralDoorData.isSubSeatBeltTie;
        this.mSeatBeltStatus = GeneralDoorData.isSeatBeltTie;
        return true;
    }

    private int getData(int... iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i += iArr[i2] << (i2 * 8);
        }
        if (i == Math.pow(2.0d, iArr.length * 8) - 1.0d) {
            return -1;
        }
        return i;
    }

    private void sendKnob_1(int i, int i2) {
        realKeyClick3_1(this.mContext, i, 0, i2);
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i5, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) (i - 2000), (byte) i3, (byte) i4, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        byte[] bytes = str4.getBytes(StandardCharsets.UTF_8);
        byte[] bytes2 = str5.getBytes(StandardCharsets.UTF_8);
        final byte[] bArrMakeBytesFixedLength = DataHandleUtils.makeBytesFixedLength(bytes, 32);
        final byte[] bArrMakeBytesFixedLength2 = DataHandleUtils.makeBytesFixedLength(bytes2, 32);
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._185.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                MsgMgr.lambda$musicInfoChange$0(bArrMakeBytesFixedLength, bArrMakeBytesFixedLength2);
            }
        }).start();
        String str7 = ((int) b3) + str2 + str3;
        int length = 12 - str7.length();
        for (int i4 = 0; i4 < length; i4++) {
            str7 = str7 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(13, str7.getBytes());
    }

    static /* synthetic */ void lambda$musicInfoChange$0(byte[] bArr, byte[] bArr2) throws InterruptedException {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -110}, bArr));
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -109}, bArr2));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        String str5 = "AM";
        int i3 = 1;
        if (str.equals("FM1")) {
            str5 = "FM";
        } else {
            if (str.equals("FM2")) {
                i3 = 2;
            } else if (str.equals("FM3")) {
                i3 = 3;
            } else if (str.equals("AM1")) {
                i3 = 4;
            } else if (str.equals("AM2")) {
                i3 = 5;
            }
            str5 = "FM";
        }
        String str6 = this.format3.format(i);
        if (str5.equals("FM")) {
            str4 = this.format2.format(Double.parseDouble(str2));
        } else {
            str4 = this.format4.format(Integer.parseInt(str2));
        }
        char[] charArray = (str6 + " " + str4).toCharArray();
        byte[] bArr = new byte[12];
        for (int i4 = 0; i4 < 12; i4++) {
            bArr[i4] = 32;
        }
        for (int i5 = 0; i5 < charArray.length; i5++) {
            bArr[i5] = (byte) charArray[i5];
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(i3, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        String str5 = ((int) b3) + str3 + str4;
        int length = 12 - str5.length();
        for (int i5 = 0; i5 < length; i5++) {
            str5 = str5 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(13, str5.getBytes());
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    private boolean isAirDataNoChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return true;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }
}
