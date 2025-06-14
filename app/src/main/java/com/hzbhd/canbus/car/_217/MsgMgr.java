package com.hzbhd.canbus.car._217;

import android.content.Context;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int data0 = 0;
    int data1 = 0;
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private UiMgr mUiMgr;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        if (byteArrayToIntArray[1] != 114) {
            return;
        }
        set0x72CarInfo();
    }

    private void set0x72CarInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i != this.data0) {
            this.data0 = i;
            setDriveInfo();
        }
        int i2 = this.mCanBusInfoInt[4];
        if (i2 != this.data1) {
            this.data1 = i2;
            setWheelKeyInfo();
        }
    }

    private void setWheelKeyInfo() {
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
            case 5:
                buttonKey(14);
                break;
            case 6:
                buttonKey(15);
                break;
            case 8:
                buttonKey(45);
                break;
            case 9:
                buttonKey(46);
                break;
            case 10:
                buttonKey(2);
                break;
        }
    }

    private void setDriveInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit4"), getKeySatate(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit3"), getParkSatate(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit2"), getREVSatate(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit1"), getILLSatate(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_202_car_state"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_202_car_D0_bit0"), getACCSatate(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getKeySatate(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._202_state1);
        }
        return this.mContext.getString(R.string._202_state0);
    }

    private String getParkSatate(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._217_PARK_state1);
        }
        return this.mContext.getString(R.string._217_PARK_state0);
    }

    private String getREVSatate(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._217_REV_state1);
        }
        return this.mContext.getString(R.string._217_REV_state0);
    }

    private String getILLSatate(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._217_ILL_state1);
        }
        return this.mContext.getString(R.string._217_ILL_state0);
    }

    private String getACCSatate(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._217_ACC_state1);
        }
        return this.mContext.getString(R.string._217_ACC_state0);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick2(this.mContext, i);
    }
}
