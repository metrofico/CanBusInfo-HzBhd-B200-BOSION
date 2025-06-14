package com.hzbhd.canbus.car._29;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int SHARE_29_AMPLIFIER_HALF_MAX = 10;
    static final int SHARE_29_AMPLIFIER_START = 6;
    static final String SHARE_29_IS_REVERSING = "share_29_is_reversing";
    private int[] mAmplifierDataNow;
    private boolean mBackStatus;
    private AbstractMsgMgr.CallBackInterface mCallBackInterface;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private int mDifferent;
    private int mEachId;
    private boolean mFrontStatus;
    private int[] mKnobNow;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private int mOutDoorTempNow;
    private int[] mPanelKeyNow;
    private int[] mPanoramicNow;
    private int mPanoramicStatusNow;
    private int[] mRadarDataNow;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int mSeatStatusNow;
    private SeatStatusView mSeatStatusView;
    private int[] mSettingDataNow;
    private int[] mTrackDataNow;
    private int mTuneKnobNow;
    private UiMgr mUiMgr;
    private int[] mVehicleDataNow;
    private byte[] mVersionInfoNow;
    private int mVolumeKnobNow;
    private int[] mWheelKeyNow;
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    private boolean nowIsMute = false;

    private String getInOutCycleState(boolean z) {
        return z ? "ON" : "OFF";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.nowIsMute = z;
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mEachId = getCurrentEachCanId();
        sendCarDifferent();
        getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
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
            setWheelKey0x11();
            return;
        }
        if (i == 18) {
            setDoorData0x12();
            return;
        }
        if (i == 33) {
            setPanelKey0x21();
            return;
        }
        if (i == 34) {
            setPanelKnob0x22();
            return;
        }
        if (i == 38) {
            setVehicleType0x26();
            return;
        }
        if (i == 65) {
            setRadarData0x41();
            return;
        }
        if (i == 97) {
            setSettingData0x61();
            return;
        }
        if (i == 166) {
            setAmplifierData0xA6();
            return;
        }
        if (i == 233) {
            set0xPanoramic0xE9();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
        } else if (i == 49) {
            setAirData0x31();
        } else {
            if (i != 50) {
                return;
            }
            setVehicleData0x32();
        }
    }

    private void setWheelKey0x11() {
        CarState.setPARKState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        CarState.setREVState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        CarState.setILLState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        CarState.setACCState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        int i = this.mCanBusInfoInt[4];
        if (i == 32) {
            realKeyClick5(21);
        } else if (i != 33) {
            switch (i) {
                case 0:
                    realKeyClick5(0);
                    break;
                case 1:
                    realKeyClick5(7);
                    break;
                case 2:
                    realKeyClick5(8);
                    break;
                case 3:
                    realKeyClick5(3);
                    break;
                case 4:
                    realKeyClick5(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick5(14);
                    break;
                case 6:
                    realKeyClick5(15);
                    break;
                default:
                    switch (i) {
                        case 8:
                            realKeyClick5(45);
                            break;
                        case 9:
                            realKeyClick5(46);
                            break;
                        case 10:
                            realKeyClick5(2);
                            break;
                        case 11:
                            realKeyClick5(2);
                            break;
                        default:
                            switch (i) {
                                case 69:
                                    realKeyClick5(HotKeyConstant.K_CAN_CONFIG);
                                    break;
                                case 70:
                                    realKeyClick5(128);
                                    break;
                                case 71:
                                    realKeyClick5(14);
                                    break;
                            }
                    }
            }
        } else {
            realKeyClick5(20);
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setDoorData0x12() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        this.mLeftFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        this.mRightFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        this.mLeftRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        this.mRightRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setPanelKey0x21() {
        if (isPanelKeyChange()) {
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                panelKeyClick2(0);
                return;
            }
            if (i == 1) {
                panelKeyClick2(HotKeyConstant.K_SLEEP);
                return;
            }
            if (i == 2) {
                panelKeyClick2(21);
                return;
            }
            if (i == 3) {
                panelKeyClick2(20);
                return;
            }
            if (i == 6) {
                panelKeyClick2(50);
                return;
            }
            if (i == 22) {
                panelKeyClick2(49);
                return;
            }
            if (i == 36) {
                panelKeyClick2(59);
                return;
            }
            if (i == 40) {
                panelKeyClick2(14);
                return;
            }
            if (i == 43) {
                panelKeyClick2(52);
                return;
            }
            if (i == 47) {
                panelKeyClick2(52);
                return;
            }
            if (i == 91) {
                panelKeyClick2(45);
                return;
            }
            if (i != 92) {
                switch (i) {
                    case 53:
                        panelKeyClick2(HotKeyConstant.K_HOUR);
                        break;
                    case 54:
                        panelKeyClick2(58);
                        break;
                    case 55:
                        panelKeyClick2(21);
                        break;
                    case 56:
                        panelKeyClick2(20);
                        break;
                    case 57:
                        panelKeyClick2(128);
                        break;
                    default:
                        switch (i) {
                            case 71:
                                panelKeyClick2(76);
                                break;
                            case 72:
                                panelKeyClick2(77);
                                break;
                            case 73:
                                panelKeyClick2(139);
                                break;
                            case 74:
                                panelKeyClick2(HotKeyConstant.K_CAN_CONFIG);
                                break;
                            case 75:
                                panelKeyClick2(129);
                                break;
                        }
                }
                return;
            }
            panelKeyClick2(46);
        }
    }

    private void setPanelKnob0x22() {
        byte b;
        int i;
        if (isKnobChange()) {
            int i2 = this.mCanBusInfoInt[2];
            if (i2 != 1) {
                if (i2 != 2 || (i = (b = this.mCanBusInfoByte[3]) - this.mTuneKnobNow) == 0) {
                    return;
                }
                this.mTuneKnobNow = b;
                turnKnob3_2(i > 0 ? 48 : 47, Math.abs(i));
                return;
            }
            byte b2 = this.mCanBusInfoByte[3];
            int i3 = b2 - this.mVolumeKnobNow;
            if (i3 == 0) {
                return;
            }
            this.mVolumeKnobNow = b2;
            int volume = getVolume();
            if (i3 > 0 && volume <= 40) {
                turnKnob3_1(7, Math.abs(i3));
            }
            if (i3 >= 0 || volume < 0) {
                return;
            }
            turnKnob3_1(8, Math.abs(i3));
        }
    }

    private void setAirData0x31() {
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 13);
        if (isOutDoorTempChange()) {
            setOutDoorTem();
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "air_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "air_setting", "_29_air_in_out_auto_cycle"), getInOutCycleState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]))).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]) || this.mCanBusInfoInt[6] == 2;
        cleanAllBlow();
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 2) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 3) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_auto_wind = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_auto_wind = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_auto_wind = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_auto_wind = false;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setVehicleData0x32() {
        if (isVehicleDataChange()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 0, sb.append(resolveDriveData(iArr[4], iArr[5])).append(" rpm").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 1, sb2.append(resolveDriveData(iArr2[6], iArr2[7])).append(" km/h").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int[] iArr3 = this.mCanBusInfoInt;
            int i = (iArr3[4] * 256) + iArr3[5];
            if (i != 65535) {
                updateSpeedInfo(i);
            }
        }
        if (isSeatStatusChange()) {
            if (this.mSeatStatusView == null) {
                this.mSeatStatusView = new SeatStatusView(this.mContext);
            }
            this.mSeatStatusView.mSeatStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 5, 3);
            this.mSeatStatusView.mBackrestStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 3, 2);
            if (this.mCallBackInterface == null) {
                this.mCallBackInterface = new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._29.MsgMgr.1
                    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                    public void callback() {
                        MsgMgr.this.mSeatStatusView.refreshUi();
                    }
                };
            }
            runOnUi(this.mCallBackInterface);
        }
    }

    private void setAmplifierData0xA6() {
        if (isAmplifierDataChange()) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.leftRight = (this.mCanBusInfoByte[3] - 6) - 10;
            GeneralAmplifierData.frontRear = (this.mCanBusInfoByte[4] - 6) - 10;
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 6;
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 6;
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 6;
            saveAmplifierData(this.mContext, this.mCanId);
            int i = this.mCanBusInfoInt[8];
            if (i == 1) {
                if (!this.nowIsMute) {
                    realKeyClick4(this.mContext, 3);
                }
            } else if (i == 0 && this.nowIsMute) {
                realKeyClick4(this.mContext, 3);
            }
            updateAmplifierActivity(null);
        }
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            int i = this.mCanBusInfoInt[13];
            if (i == 0) {
                GeneralParkData.isShowDistanceNotShowLocationUi = false;
                RadarInfoUtil.mMinIsClose = true;
                int[] iArr = this.mCanBusInfoInt;
                RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
                int[] iArr2 = this.mCanBusInfoInt;
                RadarInfoUtil.setFrontRadarLocationData(3, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            } else if (i == 1) {
                GeneralParkData.isShowDistanceNotShowLocationUi = true;
                GeneralParkData.radar_distance_disable = new int[]{0};
                int[] iArr3 = this.mCanBusInfoInt;
                RadarInfoUtil.setRearRadarDistanceData(iArr3[2], iArr3[3], iArr3[4], iArr3[5]);
                int[] iArr4 = this.mCanBusInfoInt;
                RadarInfoUtil.setFrontRadarDistanceData(iArr4[6], iArr4[7], iArr4[8], iArr4[9]);
                GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            }
            updateParkUi(null, this.mContext);
        }
    }

    private void setVehicleType0x26() {
        if (this.mCanBusInfoInt[2] == 9) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_29_about"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_29_about", "_29_car_info"), getNowCarName(this.mCanBusInfoInt[3])).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0xPanoramic0xE9() {
        if (isPanoramicDataChange()) {
            SharePreUtil.setStringValue(this.mContext, "29_0xE9", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            if (isPanoramicStatusChange()) {
                forceReverse(this.mContext, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
            }
            updatePanoramicStatus(true);
            updatePanoramicStatus(false);
        }
    }

    private void setSettingData0x61() {
        if (isSettingDataChange()) {
            SharePreUtil.setStringValue(this.mContext, "29_0x61", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 6, 1))));
            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 5, 1))));
            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 4, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    void updateSettingData(int i) {
        LogUtil.showLog("updateSettingData:" + i);
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            String stringValue = SharePreUtil.getStringValue(this.mContext, "29_0xE9", null);
            if (!TextUtils.isEmpty(stringValue)) {
                byte[] bArrDecode = Base64.decode(stringValue, 0);
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 6, 2))));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 4, 2))));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[3], 5, 1))));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[3], 4, 1))));
            }
        } else if (i == 1) {
            String stringValue2 = SharePreUtil.getStringValue(this.mContext, "29_0x61", null);
            if (!TextUtils.isEmpty(stringValue2)) {
                arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(Base64.decode(stringValue2, 0)[2], 7, 1))));
            }
        } else if (i == 2) {
            String stringValue3 = SharePreUtil.getStringValue(this.mContext, "29_0x61", null);
            if (!TextUtils.isEmpty(stringValue3)) {
                byte[] bArrDecode2 = Base64.decode(stringValue3, 0);
                arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 7, 1))));
                arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 6, 1))));
            }
        } else if (i == 3) {
            String stringValue4 = SharePreUtil.getStringValue(this.mContext, "29_0x61", null);
            if (!TextUtils.isEmpty(stringValue4)) {
                byte[] bArrDecode3 = Base64.decode(stringValue4, 0);
                arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode3[3], 5, 1))));
                arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode3[3], 4, 1))));
            }
        } else if (i == 4) {
            String stringValue5 = SharePreUtil.getStringValue(this.mContext, "29_0xA6", null);
            if (!TextUtils.isEmpty(stringValue5)) {
                arrayList.add(new SettingUpdateEntity(4, 0, Byte.valueOf(Base64.decode(stringValue5, 0)[2])).setProgress(this.mCanBusInfoInt[2]));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        reportHiworldSrcInfo((byte) -46, (byte) 0);
    }

    private void reportHiworldSrcInfo(byte b, byte b2) {
        byte[] bArr = new byte[15];
        bArr[0] = 22;
        bArr[1] = b;
        bArr[2] = b2;
        for (int i = 3; i < 15; i++) {
            bArr[i] = 32;
        }
        CanbusMsgSender.sendMsg(bArr);
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -46, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = " " + str2 + "KHZ    ";
            } else {
                str4 = "  " + str2 + "KHZ    ";
            }
        } else if (str2.length() == 5) {
            str4 = " " + str2 + "MHZ   ";
        } else {
            str4 = str2 + "MHZ   ";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), byteMerger(new byte[]{22, -46, allBandTypeData}, str4.getBytes()));
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = new byte[15];
        bArr[0] = 22;
        bArr[1] = -46;
        bArr[2] = NfDef.AVRCP_EVENT_ID_UIDS_CHANGED;
        for (int i = 3; i < 15; i++) {
            bArr[i] = 32;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -46, 21, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -46, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        byte b2 = (byte) ((i / 60) % 60);
        byte b3 = (byte) (i % 60);
        byte b4 = 7;
        if (i6 != 1 && i6 != 2 && i6 != 3) {
            b4 = (i6 == 6 || i6 == 7) ? (byte) 6 : (byte) 0;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), byteMerger(new byte[]{22, -46, b4}, ("0" + new DecimalFormat("00").format(b2 & 255) + new DecimalFormat("00").format(b3 & 255) + new DecimalFormat("0000").format(i4) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), byteMerger(b == 9 ? new byte[]{22, -46, 14} : new byte[]{22, -46, 22}, ("0" + new DecimalFormat("00").format(b4 & 255) + new DecimalFormat("00").format(b5 & 255) + new DecimalFormat("0000").format(((b6 * 256) + i) % Priority.DEBUG_INT) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), byteMerger(b == 9 ? new byte[]{22, -46, 14} : new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, ("0" + new DecimalFormat("00").format(b4 & 255) + new DecimalFormat("00").format(b5 & 255) + new DecimalFormat("0000").format(((b7 * 256) + i) % Priority.DEBUG_INT) + "   ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = 0;
        bArr[3] = (byte) i8;
        bArr[4] = (byte) i6;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = (byte) (!z ? 0 : 1);
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = 0;
        bArr[11] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    private String getNowCarName(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._29_car_1);
            case 2:
                return this.mContext.getString(R.string._29_car_2);
            case 3:
                return this.mContext.getString(R.string._29_car_3);
            case 4:
                return this.mContext.getString(R.string._29_car_4);
            case 5:
                return this.mContext.getString(R.string._29_car_5);
            case 6:
                return this.mContext.getString(R.string._29_car_6);
            case 7:
                return this.mContext.getString(R.string._29_car_7);
            default:
                return this.mContext.getString(R.string._29_car_0);
        }
    }

    private void realKeyClick5(int i) {
        realKeyClick5(this.mContext, i, 0, this.mCanBusInfoInt[5]);
    }

    private void panelKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void turnKnob3_1(int i, int i2) {
        realKeyClick3_1(this.mContext, i, 0, i2);
    }

    private void turnKnob3_2(int i, int i2) {
        realKeyClick3_2(this.mContext, i, 0, i2);
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (i == length) {
            return Arrays.copyOf(bArr, length);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void setOutDoorTem() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        return ((this.mCanBusInfoInt[13] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveFrontAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i < 35 || i > 63) ? " " : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private void cleanAllBlow() {
        GeneralAirData.front_auto_wind_model = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isPanoramicStatusChange() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
        if (this.mPanoramicStatusNow == intFromByteWithBit) {
            return false;
        }
        this.mPanoramicStatusNow = intFromByteWithBit;
        return true;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRadarDataNotZero() {
        int i = 2;
        while (true) {
            int[] iArr = this.mCanBusInfoInt;
            if (i >= iArr.length) {
                return false;
            }
            if (iArr[i] != 0) {
                return true;
            }
            i++;
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isPanelKeyChange() {
        if (Arrays.equals(this.mPanelKeyNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanelKeyNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isKnobChange() {
        if (Arrays.equals(this.mKnobNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mKnobNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isVehicleDataChange() {
        if (Arrays.equals(this.mVehicleDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mVehicleDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isAmplifierDataChange() {
        if (Arrays.equals(this.mAmplifierDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicDataChange() {
        if (Arrays.equals(this.mPanoramicNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isSettingDataChange() {
        if (Arrays.equals(this.mSettingDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mSettingDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[8], iArr[9]};
        if (Arrays.equals(this.mTrackDataNow, iArr2)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(iArr2, 2);
        return true;
    }

    private boolean isOutDoorTempChange() {
        int i = this.mOutDoorTempNow;
        int i2 = this.mCanBusInfoInt[13];
        if (i == i2) {
            return false;
        }
        this.mOutDoorTempNow = i2;
        return true;
    }

    private boolean isSeatStatusChange() {
        int i = this.mSeatStatusNow;
        int i2 = this.mCanBusInfoInt[15];
        if (i == i2) {
            return false;
        }
        this.mSeatStatusNow = i2;
        return true;
    }

    private String resolveDriveData(int i, int i2) {
        int i3 = (i * 256) + i2;
        return i3 != 65535 ? Integer.toString(i3) : " ";
    }

    private boolean resolvePanoramicBtnStatus(int i) {
        return i + (SharePreUtil.getBoolValue(this.mContext, SHARE_29_IS_REVERSING, false) ? 6 : 1) == DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
    }

    private void updatePanoramicStatus(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new PanoramicBtnUpdateEntity(i, !z && resolvePanoramicBtnStatus(i)));
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void sendCarDifferent() {
        int i;
        switch (this.mDifferent) {
            case 0:
                i = 32;
                break;
            case 1:
                i = 33;
                break;
            case 2:
                i = 43;
                break;
            case 3:
                i = 34;
                break;
            case 4:
                i = 42;
                break;
            case 5:
                i = 45;
                break;
            case 6:
                i = 46;
                break;
            case 7:
                i = 35;
                break;
            case 8:
                i = 47;
                break;
            case 9:
                i = 36;
                break;
            case 10:
                i = 38;
                break;
            case 11:
                i = 37;
                break;
            case 12:
                i = 49;
                break;
            case 13:
                i = 39;
                break;
            case 14:
                i = 40;
                int i2 = this.mEachId;
                if (i2 != 0) {
                    i = i2;
                    break;
                }
                break;
            case 15:
                i = 44;
                break;
            case 16:
                i = 41;
                break;
            case 17:
                i = 48;
                break;
            case 18:
                i = 51;
                break;
            case 19:
                i = 52;
                break;
            case 20:
                i = 53;
                break;
            default:
                i = 0;
                break;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) i, 9});
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._29.MsgMgr$2] */
    private void initAmplifierData() {
        new Thread() { // from class: com.hzbhd.canbus.car._29.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 6 + 10)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 6 + 10)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 6)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 6)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 6)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private class SeatStatusView {
        private boolean isShowing;
        private int mBackrestStatus;
        private RelativeLayout mFloatView;
        private ImageView mIvBackrestBack;
        private ImageView mIvBackrestForward;
        private ImageView mIvBackrestSelelct;
        private ImageView mIvSeatBack;
        private ImageView mIvSeatDown;
        private ImageView mIvSeatForward;
        private ImageView mIvSeatSelect;
        private ImageView mIvSeatUp;
        private WindowManager.LayoutParams mLayoutParams;
        private Runnable mRunnable;
        private int mSeatStatus;
        private WindowManager mWindowManager;

        public SeatStatusView(Context context) {
            MsgMgr.this.mContext = context;
            this.mWindowManager = (WindowManager) MsgMgr.this.mContext.getSystemService("window");
            this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car._29.MsgMgr.SeatStatusView.1
                @Override // java.lang.Runnable
                public void run() {
                    SeatStatusView.this.dismissView();
                }
            };
            findView();
            initView();
        }

        private void findView() {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(MsgMgr.this.mContext).inflate(R.layout.layout_29_seat_status, (ViewGroup) null);
            this.mFloatView = relativeLayout;
            this.mIvSeatSelect = (ImageView) relativeLayout.findViewById(R.id.iv_seat_select);
            this.mIvBackrestSelelct = (ImageView) this.mFloatView.findViewById(R.id.iv_backrest_select);
            this.mIvSeatForward = (ImageView) this.mFloatView.findViewById(R.id.iv_seat_forward);
            this.mIvSeatBack = (ImageView) this.mFloatView.findViewById(R.id.iv_seat_back);
            this.mIvSeatUp = (ImageView) this.mFloatView.findViewById(R.id.iv_seat_up);
            this.mIvSeatDown = (ImageView) this.mFloatView.findViewById(R.id.iv_seat_down);
            this.mIvBackrestForward = (ImageView) this.mFloatView.findViewById(R.id.iv_backrest_forward);
            this.mIvBackrestBack = (ImageView) this.mFloatView.findViewById(R.id.iv_backrest_back);
        }

        private void initView() {
            this.mFloatView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._29.MsgMgr.SeatStatusView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SeatStatusView.this.mFloatView.removeCallbacks(SeatStatusView.this.mRunnable);
                    SeatStatusView.this.mFloatView.post(SeatStatusView.this.mRunnable);
                }
            });
        }

        private void addViewToWindow() {
            if (this.mLayoutParams == null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                this.mLayoutParams = layoutParams;
                layoutParams.type = 2002;
                this.mLayoutParams.gravity = 17;
                this.mLayoutParams.width = -2;
                this.mLayoutParams.height = -2;
            }
            if (!this.isShowing) {
                this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
                this.isShowing = true;
            }
            this.mFloatView.removeCallbacks(this.mRunnable);
            this.mFloatView.postDelayed(this.mRunnable, 5000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void refreshUi() {
            ImageView imageView = this.mIvSeatSelect;
            int i = this.mSeatStatus;
            setVisible(imageView, i == 1 || i == 2 || i == 3 || i == 4);
            setVisible(this.mIvSeatForward, this.mSeatStatus == 1);
            setVisible(this.mIvSeatBack, this.mSeatStatus == 2);
            setVisible(this.mIvSeatUp, this.mSeatStatus == 3);
            setVisible(this.mIvSeatDown, this.mSeatStatus == 4);
            ImageView imageView2 = this.mIvBackrestSelelct;
            int i2 = this.mBackrestStatus;
            setVisible(imageView2, i2 == 1 || i2 == 2);
            setVisible(this.mIvBackrestForward, this.mBackrestStatus == 1);
            setVisible(this.mIvBackrestBack, this.mBackrestStatus == 2);
            addViewToWindow();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dismissView() {
            RelativeLayout relativeLayout;
            WindowManager windowManager = this.mWindowManager;
            if (windowManager == null || (relativeLayout = this.mFloatView) == null) {
                return;
            }
            windowManager.removeView(relativeLayout);
            this.isShowing = false;
        }

        private void setVisible(View view, boolean z) {
            view.setVisibility(z ? 0 : 4);
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
