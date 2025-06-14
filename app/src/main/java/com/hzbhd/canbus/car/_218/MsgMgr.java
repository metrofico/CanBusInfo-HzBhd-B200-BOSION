package com.hzbhd.canbus.car._218;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private int mButtonNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private int[] mDriveData0x32Now;
    private int[] mDriveData0x34Now;
    private int mEachId;
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private UiMgr mUiMgr;
    private int[] mVersionInfoNow;
    private int[] mWarningDataNow;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 49) {
            setAirData0x31();
            return;
        }
        if (i == 50) {
            setVehicleInfo0x32();
            return;
        }
        if (i == 52) {
            setVehicleInfo0x34();
            return;
        }
        if (i != 240) {
            switch (i) {
                case 114:
                    setButtonData0x72();
                    break;
                case 115:
                    setDoorData0x73();
                    break;
                case 116:
                    setWarningData0x74();
                    break;
            }
            return;
        }
        setVersionInfo0xF0();
    }

    private void setButtonData0x72() {
        if (isButtonDataChange()) {
            switch (this.mCanBusInfoInt[4]) {
                case 0:
                    wheelKeyClick(0);
                    break;
                case 1:
                    wheelKeyClick(130);
                    break;
                case 2:
                    wheelKeyClick(76);
                    break;
                case 3:
                    wheelKeyClick(141);
                    break;
                case 4:
                    wheelKeyClick(3);
                    break;
                case 5:
                    wheelKeyClick(52);
                    break;
                case 6:
                    wheelKeyClick(31);
                    break;
                case 7:
                    startMainActivity(this.mContext);
                    playBeep();
                    break;
                case 8:
                    wheelKeyClick(45);
                    break;
                case 9:
                    wheelKeyClick(46);
                    break;
                case 10:
                    wheelKeyClick(47);
                    break;
                case 11:
                    wheelKeyClick(48);
                    break;
                case 12:
                    wheelKeyClick(49);
                    break;
                case 13:
                    wheelKeyClick(21);
                    break;
                case 14:
                    wheelKeyClick(20);
                    break;
                case 15:
                    wheelKeyClick(HotKeyConstant.K_SLEEP);
                    break;
                case 16:
                    wheelKeyClick(94);
                    break;
                case 17:
                    wheelKeyClick(59);
                    break;
                case 18:
                    wheelKeyClick(33);
                    break;
                case 19:
                    wheelKeyClick(34);
                    break;
                case 20:
                    wheelKeyClick(35);
                    break;
                case 21:
                    wheelKeyClick(36);
                    break;
                case 22:
                    wheelKeyClick(37);
                    break;
                case 23:
                    wheelKeyClick(38);
                    break;
                case 24:
                    wheelKeyClick(39);
                    break;
                case 25:
                    wheelKeyClick(40);
                    break;
                case 26:
                    wheelKeyClick(41);
                    break;
                case 27:
                    wheelKeyClick(32);
                    break;
                case 28:
                    wheelKeyClick(143);
                    break;
                case 29:
                    wheelKeyClick(6);
                    break;
                case 30:
                    wheelKeyClick(44);
                    break;
                case 31:
                    wheelKeyClick(94);
                    break;
                case 32:
                    wheelKeyClick(HotKeyConstant.K_THIRD_KUWO);
                    break;
                case 33:
                    wheelKeyClick(11);
                    break;
                case 34:
                    wheelKeyClick(7);
                    break;
                case 35:
                    wheelKeyClick(8);
                    break;
            }
        }
    }

    private void setDoorData0x73() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            this.mLeftFrontRec = boolBit7;
            GeneralDoorData.isLeftFrontDoorOpen = boolBit7;
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            this.mRightFrontRec = boolBit6;
            GeneralDoorData.isRightFrontDoorOpen = boolBit6;
            boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            this.mLeftRearRec = boolBit5;
            GeneralDoorData.isLeftRearDoorOpen = boolBit5;
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            this.mRightRearRec = boolBit4;
            GeneralDoorData.isRightRearDoorOpen = boolBit4;
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
        }
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setWarningData0x74() {
        if (isWarningDataChange()) {
            ArrayList arrayList = new ArrayList();
            for (int i = 1; i <= 6; i++) {
                for (int i2 = 0; i2 <= 7; i2++) {
                    if ((this.mCanBusInfoInt[i + 2] & (1 << i2)) != 0 && !"null".equals(CommUtil.getStrByResId(this.mContext, "_218_warning_" + i + "_" + i2))) {
                        arrayList.add(new WarningEntity(CommUtil.getStrByResId(this.mContext, "_218_warning_" + i + "_" + i2)));
                    }
                }
            }
            GeneralWarningDataData.dataList = arrayList;
            updateWarningActivity(null);
            if (GeneralWarningDataData.dataList.size() > 0) {
                openWarningActivity();
            }
        }
    }

    private void openWarningActivity() {
        Intent intent = new Intent(this.mContext, (Class<?>) WarningActivity.class);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setVehicleInfo0x34() {
        if (isDriveData0x34Change()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 0, sb.append(getDriveData(new int[]{iArr[8], iArr[7], iArr[6]})).append(" km").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setVehicleInfo0x32() {
        if (isDriveData0x32Change()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 1, sb.append(getDriveData(new int[]{iArr[5], iArr[4]})).append(" rpm").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 2, sb2.append(getDriveData(new int[]{iArr2[7], iArr2[6]})).append(" km/h").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int[] iArr3 = this.mCanBusInfoInt;
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]));
        }
    }

    private void setAirData0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.max_heat = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = this.mCanBusInfoInt[6] == 3;
        GeneralAirData.front_right_blow_foot = this.mCanBusInfoInt[6] == 3;
        GeneralAirData.front_left_blow_head = this.mCanBusInfoInt[6] == 6;
        GeneralAirData.front_right_blow_head = this.mCanBusInfoInt[6] == 6;
        GeneralAirData.front_left_blow_window = this.mCanBusInfoInt[6] == 11;
        GeneralAirData.front_right_blow_window = this.mCanBusInfoInt[6] == 11;
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
        updateAirActivity(this.mContext, 1001);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        byte[] bArr = new byte[6];
        bArr[0] = 22;
        int i = 1;
        bArr[1] = -94;
        bArr[2] = (byte) getUiMgr(this.mContext).getData0();
        if (!FutureUtil.instance.isDiscExist() && z) {
            i = 0;
        }
        bArr[3] = (byte) i;
        bArr[4] = 0;
        bArr[5] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    private void wheelKeyClick(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isButtonDataChange() {
        int i = this.mButtonNow;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return false;
        }
        this.mButtonNow = i2;
        return true;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mVersionInfoNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDriveData0x34Change() {
        if (Arrays.equals(this.mDriveData0x34Now, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveData0x34Now = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDriveData0x32Change() {
        if (Arrays.equals(this.mDriveData0x32Now, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveData0x32Now = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isWarningDataChange() {
        if (Arrays.equals(this.mWarningDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mWarningDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String getDriveData(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return "";
        }
        int iPow = iArr[0];
        for (int i = 1; i < iArr.length; i++) {
            iPow = (int) (iPow + (iArr[i] * Math.pow(256.0d, i)));
        }
        int i2 = 1;
        for (int i3 = 0; i3 < (iArr.length * 8) - 1; i3++) {
            i2 = (i2 << 1) + 1;
        }
        return iPow == i2 ? "" : Integer.toString(iPow);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateSetting(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i, 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i, 3, 1))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i, 2, 1))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i, 1, 1))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i, 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
