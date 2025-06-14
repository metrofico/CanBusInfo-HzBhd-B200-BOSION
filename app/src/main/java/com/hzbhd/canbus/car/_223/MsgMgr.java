package com.hzbhd.canbus.car._223;

import android.content.Context;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private String mAirUnit;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDiscExsit;
    private boolean mFrontStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private final String TAG = "_223_MsgMgr";
    private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int INVAILE_VALUE = -1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 34, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 36, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 6});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 36) {
            set0x24BaseData(context);
            return;
        }
        if (i == 41) {
            set0x29VehicleData();
            return;
        }
        if (i != 48) {
            switch (i) {
                case 32:
                    set0x20WheelKeyData(context);
                    break;
                case 33:
                    set0x21AirData(context);
                    break;
                case 34:
                    set0x22RearRadar(context);
                    break;
            }
            return;
        }
        set0x30VersionData();
    }

    private void set0x20WheelKeyData(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(context, 45);
            return;
        }
        if (i == 7) {
            realKeyLongClick1(context, 2);
            return;
        }
        if (i == 63) {
            realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 86) {
            realKeyLongClick1(context, 94);
            return;
        }
        if (i == 87) {
            if (iArr[3] == 1) {
                startMainActivity(context);
                return;
            }
            return;
        }
        if (i == 240) {
            realKeyClick2(context, 7);
            return;
        }
        if (i != 241) {
            switch (i) {
                case 32:
                    realKeyLongClick1(context, 32);
                    break;
                case 33:
                    realKeyLongClick1(context, 33);
                    break;
                case 34:
                    realKeyLongClick1(context, 34);
                    break;
                case 35:
                    realKeyLongClick1(context, 35);
                    break;
                case 36:
                    realKeyLongClick1(context, 36);
                    break;
                case 37:
                    realKeyLongClick1(context, 37);
                    break;
                case 38:
                    realKeyLongClick1(context, 38);
                    break;
                case 39:
                    realKeyLongClick1(context, 39);
                    break;
                case 40:
                    realKeyLongClick1(context, 40);
                    break;
                case 41:
                    realKeyLongClick1(context, 41);
                    break;
                default:
                    switch (i) {
                        case 52:
                            realKeyLongClick1(context, 129);
                            break;
                        case 53:
                            realKeyLongClick1(context, 130);
                            break;
                        case 54:
                            realKeyLongClick1(context, 141);
                            break;
                        case 55:
                            realKeyLongClick1(context, 151);
                            break;
                        case 56:
                            realKeyLongClick1(context, 4);
                            break;
                        case 57:
                            realKeyLongClick1(context, 14);
                            break;
                        default:
                            switch (i) {
                                case 72:
                                    realKeyLongClick1(context, 49);
                                    break;
                                case 73:
                                    realKeyLongClick1(context, 47);
                                    break;
                                case 74:
                                    realKeyLongClick1(context, 48);
                                    break;
                                case 75:
                                    realKeyLongClick1(context, 45);
                                    break;
                                case 76:
                                    realKeyLongClick1(context, 46);
                                    break;
                                default:
                                    switch (i) {
                                        case 82:
                                            realKeyLongClick1(context, HotKeyConstant.K_UP_PICKUP);
                                            break;
                                        case 83:
                                            realKeyLongClick1(context, HotKeyConstant.K_DOWN_HANGUP);
                                            break;
                                        case 84:
                                            realKeyLongClick1(context, 31);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        realKeyClick2(context, 8);
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void set0x21AirData(Context context) {
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (GeneralAirData.fahrenheit_celsius) {
            this.mAirUnit = getTempUnitF(context);
        } else {
            this.mAirUnit = getTempUnitC(context);
        }
        updateOutDoorTemp(context, resolveOutdoorTemperature(this.mCanBusInfoByte[7]));
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        bArr[7] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        updateAirActivity(context, 1001);
    }

    private String resolveOutdoorTemperature(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((i * 9) / 5.0f) + 32.0f) + this.mAirUnit;
        }
        return i + this.mAirUnit;
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LOW";
        }
        if (i == 127) {
            return "HIGH";
        }
        if (i < 31 || i > 59) {
            return "";
        }
        float f = i / 2.0f;
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((f * 9.0f) / 5.0f) + 32.0f) + this.mAirUnit;
        }
        return f + this.mAirUnit;
    }

    private void set0x22RearRadar(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x24BaseData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private void set0x30VersionData() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x29VehicleData() {
        if (this.mCanBusInfoInt[2] == 2) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 0, sb.append(getData(iArr[6], iArr[7])).append(" rpm").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        if (this.mDiscExsit != i4) {
            this.mDiscExsit = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) ((1 - i4) + 6)});
        }
    }
}
