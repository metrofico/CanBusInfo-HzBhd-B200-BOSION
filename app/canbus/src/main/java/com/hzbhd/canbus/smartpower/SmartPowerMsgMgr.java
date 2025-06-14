package com.hzbhd.canbus.smartpower;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.commontools.utils.ConfigUtil;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class SmartPowerMsgMgr {
    private static SmartPowerMsgMgr instance;
    private int data3;
    private int data4;
    private int data5;
    private int data6;
    private int data7;
    private int[] drivingMode = {R.string.driving_pattern_8, R.string.driving_pattern_9, R.string.driving_pattern_1, R.string.driving_pattern_3, R.string.driving_pattern_10};
    private int mode;
    private int mode_int;
    private int version;

    public interface OnRefreshUiListener {
        void onRefreshUi();
    }

    public static SmartPowerMsgMgr getInstance() {
        if (!ConfigUtil.getDeviceId().contains("DZ")) {
            return null;
        }
        if (instance == null) {
            instance = new SmartPowerMsgMgr();
        }
        return instance;
    }

    public void canbusInfoChange(Context context, byte[] bArr, OnRefreshUiListener onRefreshUiListener) {
        if (context != null && bArr[0] == 87) {
            int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
            if (byteArrayToIntArray.length >= 7) {
                GeneralDzSmartData.mode = byteArrayToIntArray[1];
                GeneralDzSmartData.mode_int = getModeInt(byteArrayToIntArray);
                GeneralDzSmartData.data3 = byteArrayToIntArray[2];
                GeneralDzSmartData.data4 = byteArrayToIntArray[3];
                GeneralDzSmartData.data5 = byteArrayToIntArray[4];
                GeneralDzSmartData.data6 = byteArrayToIntArray[5];
                GeneralDzSmartData.data7 = byteArrayToIntArray[6];
                GeneralDzSmartData.version = byteArrayToIntArray[7];
                GeneralDzSmartData.isHasData = true;
                if (this.mode == GeneralDzSmartData.mode && this.mode_int == GeneralDzSmartData.mode_int && this.data3 == GeneralDzSmartData.data3 && this.data4 == GeneralDzSmartData.data4 && this.data5 == GeneralDzSmartData.data5 && this.data6 == GeneralDzSmartData.data6 && this.data7 == GeneralDzSmartData.data7 && this.version == GeneralDzSmartData.version) {
                    return;
                }
                this.mode = GeneralDzSmartData.mode;
                this.mode_int = GeneralDzSmartData.mode_int;
                this.data3 = GeneralDzSmartData.data3;
                this.data4 = GeneralDzSmartData.data4;
                this.data5 = GeneralDzSmartData.data5;
                this.data6 = GeneralDzSmartData.data6;
                this.data7 = GeneralDzSmartData.data7;
                this.version = GeneralDzSmartData.version;
                GeneralDzSmartData.hardware_model = DataHandleUtils.getIntFromByteWithBit(this.data6, 6, 2);
                GeneralDzSmartData.power_model = ((this.data6 & 63) << 8) | this.data5;
                GeneralDzSmartData.data7_0 = DataHandleUtils.getBoolBit0(this.data7);
                GeneralDzSmartData.data7_1 = DataHandleUtils.getBoolBit1(this.data7);
                GeneralDzSmartData.data7_2 = DataHandleUtils.getBoolBit2(this.data7);
                GeneralDzSmartData.data7_3 = DataHandleUtils.getBoolBit3(this.data7);
                GeneralDzSmartData.data7_4 = DataHandleUtils.getBoolBit4(this.data7);
                updateSystemUIDrivingPattern(context, GeneralDzSmartData.mode);
                if (onRefreshUiListener != null) {
                    onRefreshUiListener.onRefreshUi();
                }
            }
        }
    }

    private void updateSystemUIDrivingPattern(Context context, int i) {
        if (i < 0 || i > 4) {
            return;
        }
        Intent intent = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
        intent.putExtra("_283_car_mode", context.getString(this.drivingMode[i]));
        context.sendBroadcast(intent);
    }

    private int getModeInt(int[] iArr) {
        if (iArr.length > 4) {
            int i = iArr[1];
            int i2 = iArr[2];
            if (i == 3 || i == 4) {
                i2 = iArr[3];
            }
            return DataHandleUtils.getIntFromByteWithBit(i2, (i == 3 || i == 0) ? 4 : 0, 4) - 1;
        }
        return GeneralDzSmartData.mode;
    }

    private int[] getByteArrayToIntArray(byte[] bArr) {
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
                iArr[i] = b;
            } else {
                iArr[i] = b & 255;
            }
        }
        return iArr;
    }
}
