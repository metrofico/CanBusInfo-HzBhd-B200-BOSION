package com.hzbhd.canbus.car._225;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    public static String INTENT_KEY_POSITION = "intent_key_position";
    public static String SHARE_AIR_SET = "share_air_set";
    public static String UPDATE_SETTING_ACTION = "update_setting_action";
    private BroadcastReceiver mBroadcastReceiver;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mIsFirstTime;
    private Resources resource;

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._225.MsgMgr.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MsgMgr.this.setAirSet(intent.getIntExtra(MsgMgr.INTENT_KEY_POSITION, 0));
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, new IntentFilter(UPDATE_SETTING_ACTION));
        setAirSet(SharePreUtil.getIntValue(context, SHARE_AIR_SET, 0));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        this.resource = context.getResources();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[1];
        if (i == 1) {
            byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 2);
            setOutDoorTem();
            if (isAirMsgRepeat(bArrBytesExpectOneByte)) {
                return;
            }
            if (this.mIsFirstTime) {
                this.mIsFirstTime = false;
                return;
            } else {
                setAirInfo0x01();
                return;
            }
        }
        if (i == 2) {
            setAirDiagnosisInfo0x02();
            return;
        }
        if (i == 11) {
            updateSpeedInfo(iArr[2]);
            return;
        }
        if (i == 36) {
            if (isDoorMsgRepeat(this.mCanBusInfoByte)) {
                return;
            }
            setVehicleDoorInfo0x24();
        } else if (i == 48) {
            setVersionInfo0x30();
        } else {
            if (i != 101) {
                return;
            }
            setVehicleRunInfo0x65();
        }
    }

    private void setOutDoorTem() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setAirInfo0x01() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        if (intFromByteWithBit == 1) {
            GeneralAirData.ac = false;
            GeneralAirData.ac_auto = false;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.ac = true;
            GeneralAirData.ac_auto = false;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.ac_auto = true;
        }
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        if (!GeneralAirData.front_auto_wind_speed) {
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3);
        }
        GeneralAirData.power = GeneralAirData.front_wind_level > 0;
        cleanAllBlow();
        if (!GeneralAirData.front_auto_wind_model) {
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 3);
            if (intFromByteWithBit2 == 1) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else if (intFromByteWithBit2 == 2) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit2 == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit2 == 4) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit2 == 5) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            }
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4));
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirDiagnosisInfo0x02() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new DriverUpdateEntity(0, 1, getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new DriverUpdateEntity(0, 2, getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
        arrayList.add(new DriverUpdateEntity(0, 3, getAcDiagnosticInfoItem12(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
        arrayList.add(new DriverUpdateEntity(0, 4, getAcDiagnosticInfoItem345(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        arrayList.add(new DriverUpdateEntity(0, 5, getAcDiagnosticInfoItem345(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
        arrayList.add(new DriverUpdateEntity(0, 6, getAcDiagnosticInfoItem345(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2))));
        arrayList.add(new DriverUpdateEntity(0, 7, getAcDiagnosticInfoItem6(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
        arrayList.add(new DriverUpdateEntity(1, 0, resolveDeiagnosticInfo(this.mCanBusInfoInt[4], 0, 100, 101, "%")));
        arrayList.add(new DriverUpdateEntity(1, 1, resolveDeiagnosticInfo(this.mCanBusInfoInt[5], 0, 100, 101, "%")));
        arrayList.add(new DriverUpdateEntity(1, 2, resolveDeiagnosticInfo(this.mCanBusInfoInt[6], 0, 109, 110, " Kcal/m².h")));
        arrayList.add(new DriverUpdateEntity(1, 3, resolveDeiagnosticInfo(this.mCanBusInfoInt[7], 0, 125, 255, "℃")));
        arrayList.add(new DriverUpdateEntity(1, 4, resolveDeiagnosticInfo(this.mCanBusInfoInt[8], 0, 125, 255, "℃")));
        arrayList.add(new DriverUpdateEntity(1, 5, resolveDeiagnosticInfo(this.mCanBusInfoInt[9], 0, 130, 255, "℃")));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVehicleDoorInfo0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    public void setAirSet(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVehicleRunInfo0x65() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 0, ((iArr[2] * 256) + iArr[3]) + " r/min"));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 1, ((iArr2[4] * 256) + iArr2[5]) + " km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private String resolveOutDoorTem() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            int i = 64 - intFromByteWithBit;
            if (i <= 0 || i >= 41) {
                return i == 41 ? "Er" : this.mContext.getString(R.string.no_display);
            }
            return "-" + i + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[2];
        if (i2 >= 0 && i2 <= 85) {
            return "" + i2 + getTempUnitC(this.mContext);
        }
        return this.mContext.getString(R.string.no_display);
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private String resolveLeftAndRightTemp(int i) {
        return 1 == i ? "LO" : 15 == i ? "HI" : (i <= 1 || i >= 15) ? "" : (i + 17) + getTempUnitC(this.mContext);
    }

    private String getAcDiagnosticInfoItem12(int i) {
        if (i != 1) {
            return i != 2 ? "" : this.resource.getString(R.string.ac_diagnostic_info_item_2);
        }
        return this.resource.getString(R.string.ac_diagnostic_info_item_1);
    }

    private String getAcDiagnosticInfoItem345(int i) {
        if (i == 1) {
            return this.resource.getString(R.string.ac_diagnostic_info_item_3);
        }
        if (i != 2) {
            return i != 3 ? "" : this.resource.getString(R.string.ac_diagnostic_info_item_5);
        }
        return this.resource.getString(R.string.ac_diagnostic_info_item_4);
    }

    private String getAcDiagnosticInfoItem6(int i) {
        return i == 1 ? this.resource.getString(R.string.ac_diagnostic_info_item_6) : "";
    }

    private String resolveDeiagnosticInfo(int i, int i2, int i3, int i4, String str) {
        return i == i4 ? "Er" : (i < i2 || i > i3) ? "" : i + str;
    }
}
