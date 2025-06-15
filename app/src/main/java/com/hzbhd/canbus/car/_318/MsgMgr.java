package com.hzbhd.canbus.car._318;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private final String TAG = "_318_MsgMgr";
    private SparseArray<int[]> mCanbusDataArray;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private int mPm25AlarmCurrentLevel;
    private int mPm25AlarmSetLevel;
    private UiMgr mUiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mCanbusDataArray = new SparseArray<>();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = bArr;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            set0x20WheelKeyData(context);
            return;
        }
        if (i == 33) {
            set0x21AirData(context);
            return;
        }
        if (i == 39) {
            set0x27Pm25InfoData(context);
        } else if (i == 40) {
            set0x28Pm25SetupData(context);
        } else {
            if (i != 48) {
                return;
            }
            set0x30VersionData(context);
        }
    }

    private void set0x20WheelKeyData(Context context) {
        int i = this.mCanbusInfoInt[2];
        if (i == 20) {
            realKeyClick3_1(context, 7);
            return;
        }
        if (i != 21) {
            switch (i) {
                case 0:
                    realKeyLongClick1(context, 0);
                    break;
                case 1:
                    realKeyLongClick1(context, 7);
                    break;
                case 2:
                    realKeyLongClick1(context, 8);
                    break;
                case 3:
                    realKeyLongClick1(context, 46);
                    break;
                case 4:
                    realKeyLongClick1(context, 45);
                    break;
                case 5:
                    realKeyLongClick1(context, 14);
                    break;
                case 6:
                    realKeyLongClick1(context, 3);
                    break;
                case 7:
                    realKeyLongClick1(context, 2);
                    break;
                case 8:
                    realKeyLongClick1(context, HotKeyConstant.K_SPEECH);
                    break;
                default:
                    switch (i) {
                        case 10:
                            realKeyLongClick1(context, 33);
                            break;
                        case 11:
                            realKeyLongClick1(context, 34);
                            break;
                        case 12:
                            realKeyLongClick1(context, 35);
                            break;
                        case 13:
                            realKeyLongClick1(context, 36);
                            break;
                        case 14:
                            realKeyLongClick1(context, 37);
                            break;
                        case 15:
                            realKeyLongClick1(context, 38);
                            break;
                        case 16:
                            realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
                            break;
                        default:
                            switch (i) {
                                case 41:
                                    realKeyClick3_2(context, 47);
                                    break;
                                case 42:
                                    realKeyClick3_2(context, 48);
                                    break;
                                case 43:
                                    realKeyLongClick1(context, 47);
                                    break;
                                case 44:
                                    realKeyLongClick1(context, 48);
                                    break;
                                case 45:
                                    realKeyLongClick1(context, 45);
                                    break;
                                case 46:
                                    realKeyLongClick1(context, 46);
                                    break;
                                case 47:
                                    realKeyLongClick1(context, 49);
                                    break;
                                case 48:
                                    realKeyLongClick1(context, 59);
                                    break;
                                case 49:
                                    realKeyLongClick1(context, 129);
                                    break;
                                case 50:
                                    realKeyLongClick1(context, 52);
                                    break;
                                case 51:
                                    realKeyLongClick1(context, 141);
                                    break;
                                case 52:
                                    realKeyLongClick1(context, 58);
                                    break;
                                case 53:
                                    realKeyLongClick1(context, 50);
                                    break;
                                default:
                                    switch (i) {
                                        case 70:
                                            realKeyLongClick1(context, 27);
                                            break;
                                        case 71:
                                            realKeyLongClick1(context, 29);
                                            break;
                                        case 72:
                                            realKeyLongClick1(context, 74);
                                            break;
                                        case 73:
                                            realKeyLongClick1(context, 75);
                                            break;
                                        case 74:
                                            realKeyLongClick1(context, 128);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        realKeyClick3_1(context, 8);
    }

    private void set0x21AirData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = resolveTemperature(context, this.mCanbusInfoInt[5]);
            GeneralAirData.front_right_temperature = resolveTemperature(context, this.mCanbusInfoInt[6]);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 4, 3);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[7], 0, 3);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            updateAirActivity(context, 1001);
        }
    }

    private void set0x27Pm25InfoData(Context context) {
        String strByResId;
        if (isDataChange(this.mCanbusInfoInt)) {
            int[] iArr = this.mCanbusInfoInt;
            int i = iArr[2] | (iArr[3] << 8);
            String str = "";
            if (i >= 0 && i <= 35) {
                strByResId = CommUtil.getStrByResId(context, "pm_excellent");
            } else if (i >= 36 && i <= 75) {
                strByResId = CommUtil.getStrByResId(context, "pm_good");
            } else if (i >= 76 && i <= 115) {
                strByResId = CommUtil.getStrByResId(context, "mild");
            } else if (i >= 116 && i <= 150) {
                strByResId = CommUtil.getStrByResId(context, "moderate");
            } else if (i >= 151 && i <= 250) {
                strByResId = CommUtil.getStrByResId(context, "severe");
            } else {
                strByResId = (i < 251 || i > 999) ? "" : CommUtil.getStrByResId(context, "serious");
            }
            int i2 = this.mCanbusInfoInt[4];
            if (i2 == 0) {
                str = CommUtil.getStrByResId(context, "_103_car_setting_value_38") + CommUtil.getStrByResId(context, "_31_tire_date3");
            } else if (i2 == 1) {
                str = CommUtil.getStrByResId(context, "mild") + CommUtil.getStrByResId(context, "_31_tire_date3");
            } else if (i2 == 2) {
                str = CommUtil.getStrByResId(context, "moderate") + CommUtil.getStrByResId(context, "_31_tire_date3");
            } else if (i2 == 3) {
                str = CommUtil.getStrByResId(context, "severe") + CommUtil.getStrByResId(context, "_31_tire_date3");
            } else if (i2 == 4) {
                str = CommUtil.getStrByResId(context, "serious") + CommUtil.getStrByResId(context, "_31_tire_date3");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, i + " " + CommUtil.getStrByResId(context, "ug_m3")));
            arrayList.add(new DriverUpdateEntity(0, 1, strByResId));
            arrayList.add(new DriverUpdateEntity(0, 2, str));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int i3 = this.mPm25AlarmCurrentLevel;
            int i4 = this.mCanbusInfoInt[4];
            if (i3 != i4) {
                this.mPm25AlarmCurrentLevel = i4;
                if (i4 <= this.mPm25AlarmSetLevel || SystemUtil.isForeground(context, DriveDataActivity.class.getName())) {
                    return;
                }
                Intent intent = new Intent(context, (Class<?>) DriveDataActivity.class);
                intent.setFlags(268435456);
                context.startActivity(intent);
            }
        }
    }

    private void set0x28Pm25SetupData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 2))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            this.mPm25AlarmSetLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 2);
        }
    }

    private void set0x30VersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanbusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanbusInfoInt[3]);
    }

    private void realKeyClick3_1(Context context, int i) {
        int[] iArr = this.mCanbusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_2(Context context, int i) {
        int[] iArr = this.mCanbusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }

    private String resolveTemperature(Context context, int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (i < 1 || i > 30) ? "" : ((i + 31) / 2) + getTempUnitC(context);
    }
}
