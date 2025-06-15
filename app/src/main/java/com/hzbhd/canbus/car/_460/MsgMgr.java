package com.hzbhd.canbus.car._460;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.canCustom.canBase.CanDocking;
import com.hzbhd.canbus.canCustom.canBase.CanVm;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;


public class MsgMgr extends AbstractMsgMgr {
    Context context;
    CanDocking docking;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.context = context;
        if (this.docking == null) {
            this.docking = CanVm.getVm().getCanDocking();
        }
        this.docking.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        if (this.docking == null) {
            this.docking = CanVm.getVm().getCanDocking();
        }
        this.docking.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.docking.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (getMsDataType(byteArrayToIntArray) == 401) {
            set0x191EspInfo(byteArrayToIntArray);
        }
        if (getMsDataType(byteArrayToIntArray) == 929) {
            set0x3a1Info(byteArrayToIntArray);
        }
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    private void set0x191EspInfo(int[] iArr) {
        int msbLsbResult = (int) (((DataHandleUtils.getMsbLsbResult(iArr[7], iArr[8]) * 0.1d) - 780.0d) / 30.0d);
        Log.d("fxHou新特转角", "mCanBusInfoInt[7]=" + iArr[7]);
        Log.d("fxHou新特转角", "mCanBusInfoInt[8]=" + iArr[8]);
        Log.d("fxHou新特转角", "result=" + msbLsbResult);
        GeneralParkData.trackAngle = msbLsbResult;
        updateParkUi(null, this.context);
    }

    private void set0x3a1Info(int[] iArr) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[10], 0, 3);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[10], 3, 3);
        Log.d("fxHou新特雷达", "mCanBusInfoInt[10]=" + iArr[10]);
        Log.d("fxHou新特雷达", "left=" + intFromByteWithBit);
        Log.d("fxHou新特雷达", "right=" + intFromByteWithBit2);
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(4, intFromByteWithBit, 0, 0, intFromByteWithBit2);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.context);
    }
}
