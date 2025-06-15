package com.hzbhd.canbus.car._313;

import android.content.Context;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private int[] mCanBusInfoInt;
    private byte[] mCanbusInfoByte;
    private Context mContext;
    int now0x24Data0 = 0;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mCanbusInfoByte = bArr;
        byte b = bArr[1];
        if (b == 32) {
            set0x20WheelKeyData(context);
            return;
        }
        if (b == 36) {
            set0x24DoorInfo();
        } else if (b == 53) {
            set0x35VehicleInfo(context);
        } else {
            if (b != 57) {
                return;
            }
            set0x39AmpInfo();
        }
    }

    private void set0x24DoorInfo() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            int i = this.now0x24Data0;
            int i2 = this.mCanBusInfoInt[2];
            if (i == i2) {
                return;
            }
            this.now0x24Data0 = i2;
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(i2);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x39AmpInfo() {
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAmplifierData.frontRear = (-this.mCanBusInfoInt[3]) + 10;
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[4] - 10;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        updateAmplifierActivity(null);
    }

    private void set0x20WheelKeyData(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = 2;
        int i2 = iArr[2];
        if (i2 == 21) {
            i = 50;
        } else if (i2 != 22) {
            switch (i2) {
                case 0:
                default:
                    i = 0;
                    break;
                case 1:
                    i = 7;
                    break;
                case 2:
                    i = 8;
                    break;
                case 3:
                    i = 14;
                    break;
                case 4:
                    i = 3;
                    break;
                case 5:
                    i = 46;
                    break;
                case 6:
                    i = 45;
                    break;
                case 7:
                    break;
                case 8:
                    i = HotKeyConstant.K_SPEECH;
                    break;
                case 9:
                case 10:
                    i = 15;
                    break;
            }
        } else {
            i = 49;
        }
        realKeyLongClick1(context, i, iArr[3]);
    }

    private void set0x26VehicleInfo(Context context) {
        String strByResId;
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 8) | iArr[3];
        int i2 = (iArr[4] << 8) | iArr[5];
        int i3 = iArr[6];
        if (i3 == 0) {
            strByResId = CommUtil.getStrByResId(context, "geely_e6_item_0");
        } else {
            strByResId = i3 == 1 ? CommUtil.getStrByResId(context, "_313_battery_too_low") : "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, i + " KM/H"));
        arrayList.add(new DriverUpdateEntity(0, 1, i2 + " RPM"));
        arrayList.add(new DriverUpdateEntity(0, 2, strByResId));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(i);
    }

    private void set0x35VehicleInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 8) | iArr[3];
        int i2 = iArr[4];
        int i3 = iArr[5];
        int i4 = iArr[6];
        if (i4 == 0) {
            CommUtil.getStrByResId(context, "geely_e6_item_0");
        } else if (i4 == 1) {
            CommUtil.getStrByResId(context, "_313_battery_too_low");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
        StringBuilder sb = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb.append(DataHandleUtils.getMsbLsbResult(iArr2[6], iArr2[7])).append(" RPM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(i);
    }
}
