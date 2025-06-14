package com.hzbhd.canbus.car._447;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.car._447.RadarUartDevice;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.commontools.utils.FgeString;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr implements RadarUartDevice.IUartDataReport {
    private Context mContext;
    int[] mFrontRadarData;
    int[] mRearRadarData;
    private UiMgr mUiMgr;

    private int getShowLevel(byte b) {
        int i = b & 255;
        if (i <= 30) {
            return 1;
        }
        if (i <= 60) {
            return 2;
        }
        if (i <= 100) {
            return 3;
        }
        return i <= 150 ? 4 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        this.mContext = context;
        super.initCommand(context);
        RadarUartDevice.getRadarUartDevice(this).start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
    }

    private void setFrontRadar0x23(byte[] bArr, Context context) {
        if (isFrontRadarDataChange(getByteArrayToIntArray(bArr))) {
            ID447Data.frontLeftRange = bArr[3];
            ID447Data.frontLeftMidRange = bArr[4];
            ID447Data.frontRightMidRange = bArr[5];
            ID447Data.frontRightRange = bArr[6];
            updateId447Radar(context);
        }
    }

    private void setRearRadar0x55(byte[] bArr, Context context) {
        if (isRearRadarDataChange(getByteArrayToIntArray(bArr))) {
            ID447Data.rearLeftRange = getShowLevel(bArr[1]);
            ID447Data.rearLeftMidRange = getShowLevel(bArr[2]);
            ID447Data.rearRightMidRange = getShowLevel(bArr[3]);
            ID447Data.rearRightRange = getShowLevel(bArr[4]);
            updateId447Radar(context);
        }
    }

    private void updateId447Radar(Context context) {
        if (getUiMgr(context).id447RadarView != null) {
            getUiMgr(context).id447RadarView.updateRadar();
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    @Override // com.hzbhd.canbus.car._447.RadarUartDevice.IUartDataReport
    public void onRead(byte[] bArr) {
        Log.d("onRead:", "uartdata:  " + FgeString.bytes2HexString(bArr, bArr.length));
        setRearRadar0x55(bArr, this.mContext);
    }
}
