package com.hzbhd.canbus.car._156;

import android.content.Context;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import java.util.Arrays;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        LogUtil.showLog("canbusInfoChange:" + this.mCanBusInfoInt[1]);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[1];
        if (i == 32) {
            setSwc();
            return;
        }
        if (i == 160) {
            resolveOutDoorTem(iArr);
        } else if (i == 208 && !isDoorMsgReturn(bArr)) {
            setDoorData0x24();
        }
    }

    private void setSwc() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick2(0);
                break;
            case 1:
                realKeyClick2(7);
                break;
            case 2:
                realKeyClick2(8);
                break;
            case 3:
                realKeyClick2(46);
                break;
            case 4:
                realKeyClick2(45);
                break;
            case 5:
                realKeyClick2(14);
                break;
            case 6:
                realKeyClick2(3);
                break;
            case 7:
                if (getCurrentCanDifferentId() == 1) {
                    realKeyClick2(59);
                    break;
                } else {
                    realKeyClick2(2);
                    break;
                }
            case 8:
                realKeyClick2(20);
                break;
            case 9:
                realKeyClick2(21);
                break;
            case 10:
                realKeyClick2(49);
                break;
            case 11:
                if (getCurrentCanDifferentId() == 1) {
                    realKeyClick2(129);
                    break;
                } else {
                    realKeyClick2(128);
                    break;
                }
        }
    }

    private void realKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void resolveOutDoorTem(int[] iArr) {
        int i;
        if (iArr[2] == 1) {
            i = (iArr[3] & 255) * (-1);
        } else {
            i = iArr[3] & 255;
        }
        if (i > 59 || i < -40) {
            updateOutDoorTemp(this.mContext, " ");
        } else {
            updateOutDoorTemp(this.mContext, String.valueOf(i) + getTempUnitC(this.mContext));
        }
    }

    private void setDoorData0x24() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }
}
