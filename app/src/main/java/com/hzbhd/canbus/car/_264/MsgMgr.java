package com.hzbhd.canbus.car._264;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setCarSetData0x14();
            return;
        }
        if (i == 32) {
            realKeyControl();
            return;
        }
        if (i == 36) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setDoorData0x24();
        } else if (i == 38) {
            setTrackInfo();
        } else {
            if (i != 255) {
                return;
            }
            setVersionInfo();
        }
    }

    private void setTrackInfo() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1((byte) iArr[3], (byte) iArr[2], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyControl() {
        if (isAirMsgRepeat(this.mCanBusInfoByte)) {
        }
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(7);
                break;
            case 2:
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(128);
                break;
            case 4:
                realKeyClick(2);
                break;
            case 5:
                realKeyClick(14);
                break;
            case 6:
                realKeyClick(3);
                break;
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        String string2;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            updateDoorView(this.mContext);
        }
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string.non_reverse);
        } else {
            string = this.mContext.getResources().getString(R.string.reversing);
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            string2 = this.mContext.getResources().getString(R.string.pull_up);
        } else {
            string2 = this.mContext.getResources().getString(R.string.put_down);
        }
        arrayList.add(new DriverUpdateEntity(0, 2, string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen) ? false : true;
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 1) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
