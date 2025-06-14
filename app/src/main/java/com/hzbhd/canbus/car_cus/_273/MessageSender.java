package com.hzbhd.canbus.car_cus._273;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._273.MsgMgr;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.ui.util.BaseUtil;

/* loaded from: classes2.dex */
public class MessageSender {
    private static int[] mCommonSwitchs;

    public static Context getContext() {
        return BaseUtil.INSTANCE.getContext();
    }

    public static void setCommonSwitchs(int[] iArr) {
        mCommonSwitchs = iArr;
    }

    public static int[] getmCommonSwitchs() {
        return mCommonSwitchs;
    }

    public static void saveCommonSwitch(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i : iArr) {
            sb.append(i);
            sb.append(",");
        }
        LogUtil.showLog(sb.toString());
        SharePreUtil.setStringValue(getContext(), MsgMgr.SAVE_COMMON_SWITCH, sb.toString());
    }

    public static String getCommonSwitch() {
        return SharePreUtil.getStringValue(getContext(), MsgMgr.SAVE_COMMON_SWITCH, "");
    }

    private void saveCanBusInfo(int[] iArr, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 : iArr) {
            sb.append(i2);
            sb.append(",");
        }
        LogUtil.showLog(sb.toString());
        if (i == 1) {
            SharePreUtil.setStringValue(getContext(), MsgMgr.SAVE_AIRINFO_1, sb.toString());
        } else if (i == 2) {
            SharePreUtil.setStringValue(getContext(), MsgMgr.SAVE_AIRINFO_2, sb.toString());
        }
    }

    public static void showCommonSwitch(int i, int i2, boolean z) {
        byte[] bArr = new byte[15];
        bArr[0] = 22;
        bArr[1] = 1;
        bArr[2] = 24;
        bArr[3] = -2;
        bArr[4] = -87;
        bArr[5] = 23;
        bArr[6] = 0;
        int[] iArr = mCommonSwitchs;
        bArr[7] = (byte) (i == 0 ? change(iArr[0], i2, z, i) : iArr[0]);
        int[] iArr2 = mCommonSwitchs;
        bArr[8] = (byte) (i == 1 ? change(iArr2[1], i2, z, i) : iArr2[1]);
        int[] iArr3 = mCommonSwitchs;
        bArr[9] = (byte) (i == 2 ? change(iArr3[2], i2, z, i) : iArr3[2]);
        int[] iArr4 = mCommonSwitchs;
        bArr[10] = (byte) (i == 3 ? change(iArr4[3], i2, z, i) : iArr4[3]);
        int[] iArr5 = mCommonSwitchs;
        bArr[11] = (byte) (i == 4 ? change(iArr5[4], i2, z, i) : iArr5[4]);
        int[] iArr6 = mCommonSwitchs;
        bArr[12] = (byte) (i == 5 ? change(iArr6[5], i2, z, i) : iArr6[5]);
        int[] iArr7 = mCommonSwitchs;
        bArr[13] = (byte) (i == 6 ? change(iArr7[6], i2, z, i) : iArr7[6]);
        bArr[14] = (byte) (i == 7 ? change(mCommonSwitchs[7], i2, z, i) : mCommonSwitchs[7]);
        CanbusMsgSender.sendMsg(bArr);
    }

    public static void showAirSender(int i, int i2, boolean z) {
        byte[] bArr = new byte[15];
        bArr[0] = 22;
        bArr[1] = 1;
        bArr[2] = 24;
        bArr[3] = -1;
        bArr[4] = -61;
        bArr[5] = 25;
        bArr[6] = 0;
        bArr[7] = (byte) (i == 0 ? changeAir(0, i2, z) : 0);
        bArr[8] = (byte) (i == 1 ? changeAir(0, i2, z) : 0);
        bArr[9] = (byte) (i == 2 ? changeAir(0, i2, z) : 0);
        bArr[10] = (byte) (i == 3 ? changeAir(0, i2, z) : 0);
        bArr[11] = (byte) (i == 4 ? changeAir(0, i2, z) : 0);
        bArr[12] = (byte) (i == 5 ? changeAir(0, i2, z) : 0);
        bArr[13] = (byte) (i == 6 ? changeAir(0, i2, z) : 0);
        bArr[14] = (byte) (i == 7 ? changeAir(0, i2, z) : 0);
        CanbusMsgSender.sendMsg(bArr);
    }

    private static int changeAir(int i, int i2, boolean z) {
        return DataHandleUtils.setIntByteWithBit(i, i2, z);
    }

    private static int change(int i, int i2, boolean z, int i3) {
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(i, i2, z);
        changeCommonSwitchIndex(i3, intByteWithBit);
        return intByteWithBit;
    }

    private static void changeCommonSwitchIndex(int i, int i2) {
        mCommonSwitchs[i] = i2;
    }
}
