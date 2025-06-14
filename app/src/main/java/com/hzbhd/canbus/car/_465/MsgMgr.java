package com.hzbhd.canbus.car._465;

import android.content.Context;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.proxy.keydispatcher.SendKeyManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int msDataType = getMsDataType(byteArrayToIntArray);
        if (msDataType == 384) {
            setDrive0x180(byteArrayToIntArray);
            return;
        }
        if (msDataType == 505) {
            setDrive0x1F9(byteArrayToIntArray);
            return;
        }
        if (msDataType == 636) {
            setDrive0x27C(byteArrayToIntArray);
            return;
        }
        if (msDataType == 640) {
            setDrive0x280(byteArrayToIntArray);
            return;
        }
        if (msDataType == 734) {
            setDrive0x2DE(byteArrayToIntArray);
            return;
        }
        if (msDataType == 1153) {
            setDrive0x481(byteArrayToIntArray);
            return;
        }
        if (msDataType == 1171) {
            setMute(byteArrayToIntArray);
        } else if (msDataType == 1361) {
            setDrive0x551(byteArrayToIntArray);
        } else {
            if (msDataType != 1608) {
                return;
            }
            setDrive0x648(byteArrayToIntArray);
        }
    }

    private void setMute(int[] iArr) {
        SendKeyManager.getInstance().setAppMute(1, DataHandleUtils.getBoolBit7(iArr[7]));
    }

    protected int getMsDataType(int[] iArr) {
        return (iArr[5] & 255) | ((iArr[2] & 255) << 24) | ((iArr[3] & 255) << 16) | ((iArr[4] & 255) << 8);
    }

    private void setDrive0x1F9(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data18"), (DataHandleUtils.getMsbLsbResult(iArr[8], iArr[9]) * 0.01f) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrive0x648(int[] iArr) {
        String string;
        String string2;
        String string3;
        ArrayList arrayList = new ArrayList();
        String string4 = "--";
        switch (DataHandleUtils.getIntFromByteWithBit(iArr[7], 1, 7)) {
            case 125:
                string = this.mContext.getString(R.string._465_drive_state_23);
                break;
            case 126:
                string = this.mContext.getString(R.string._465_drive_state_24);
                break;
            case WorkQueueKt.MASK /* 127 */:
                string = this.mContext.getString(R.string._465_drive_state_25);
                break;
            default:
                string = "--";
                break;
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data14"), string));
        switch (DataHandleUtils.getIntFromByteWithBit(iArr[8], 1, 7)) {
            case 125:
                string2 = this.mContext.getString(R.string._465_drive_state_23);
                break;
            case 126:
                string2 = this.mContext.getString(R.string._465_drive_state_24);
                break;
            case WorkQueueKt.MASK /* 127 */:
                string2 = this.mContext.getString(R.string._465_drive_state_25);
                break;
            default:
                string2 = "--";
                break;
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data15"), string2));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[9], 1, 7);
        if (intFromByteWithBit == 126) {
            string3 = this.mContext.getString(R.string._465_drive_state_26);
        } else {
            string3 = intFromByteWithBit != 127 ? "--" : this.mContext.getString(R.string._465_drive_state_27);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data16"), string3));
        switch (DataHandleUtils.getIntFromByteWithBit(iArr[10], 1, 7)) {
            case 125:
                string4 = this.mContext.getString(R.string._465_drive_state_28);
                break;
            case 126:
                string4 = this.mContext.getString(R.string._465_drive_state_29);
                break;
            case WorkQueueKt.MASK /* 127 */:
                string4 = this.mContext.getString(R.string._465_drive_state_30);
                break;
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data17"), string4));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrive0x27C(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data13"), (DataHandleUtils.getMsbLsbResult(iArr[11], iArr[12]) * 0.01f) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo((int) (DataHandleUtils.getMsbLsbResult(iArr[11], iArr[12]) * 0.01f));
    }

    private void setDrive0x280(int[] iArr) {
        updateOutDoorTemp(this.mContext, (iArr[9] - 40) + "℃");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_279_outdoor_temperature"), (iArr[9] - 40) + "℃"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrive0x2DE(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data9"), DataHandleUtils.getBoolBit1(iArr[8]) ? this.mContext.getString(R.string._465_drive_state_22) : this.mContext.getString(R.string._465_drive_state_21)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data10"), DataHandleUtils.getBoolBit0(iArr[9]) ? this.mContext.getString(R.string._465_drive_state_22) : this.mContext.getString(R.string._465_drive_state_21)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data11"), DataHandleUtils.getBoolBit2(iArr[9]) ? this.mContext.getString(R.string._465_drive_state_22) : this.mContext.getString(R.string._465_drive_state_21)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrive0x551(int[] iArr) {
        String string;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data4"), (iArr[8] - 40) + "℃"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data5"), (iArr[9] * 0.0625f) + "ml"));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 2);
        String string2 = "--";
        if (intFromByteWithBit == 0) {
            string = this.mContext.getString(R.string._465_drive_state_9);
        } else if (intFromByteWithBit == 1) {
            string = this.mContext.getString(R.string._465_drive_state_10);
        } else if (intFromByteWithBit == 2) {
            string = this.mContext.getString(R.string._465_drive_state_11);
        } else {
            string = intFromByteWithBit != 3 ? "--" : this.mContext.getString(R.string._465_drive_state_12);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data6"), string));
        switch (DataHandleUtils.getIntFromByteWithBit(iArr[11], 4, 3)) {
            case 0:
                string2 = this.mContext.getString(R.string._465_drive_state_13);
                break;
            case 1:
                string2 = this.mContext.getString(R.string._465_drive_state_14);
                break;
            case 2:
                string2 = this.mContext.getString(R.string._465_drive_state_15);
                break;
            case 3:
                string2 = this.mContext.getString(R.string._465_drive_state_16);
                break;
            case 4:
                string2 = this.mContext.getString(R.string._465_drive_state_17);
                break;
            case 5:
                string2 = this.mContext.getString(R.string._465_drive_state_18);
                break;
            case 6:
                string2 = this.mContext.getString(R.string._465_drive_state_19);
                break;
            case 7:
                string2 = this.mContext.getString(R.string._465_drive_state_20);
                break;
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data7"), string2));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data8"), DataHandleUtils.getBoolBit7(iArr[11]) ? "ON" : "OFF"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrive0x481(int[] iArr) {
        String string;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data2"), DataHandleUtils.getBoolBit0(iArr[7]) ? "ON" : "OFF"));
        switch (DataHandleUtils.getIntFromByteWithBit(iArr[8], 5, 3)) {
            case 0:
                string = this.mContext.getString(R.string._465_drive_state_1);
                break;
            case 1:
                string = this.mContext.getString(R.string._465_drive_state_2);
                break;
            case 2:
                string = this.mContext.getString(R.string._465_drive_state_3);
                break;
            case 3:
                string = this.mContext.getString(R.string._465_drive_state_4);
                break;
            case 4:
                string = this.mContext.getString(R.string._465_drive_state_5);
                break;
            case 5:
                string = this.mContext.getString(R.string._465_drive_state_6);
                break;
            case 6:
                string = this.mContext.getString(R.string._465_drive_state_7);
                break;
            case 7:
                string = this.mContext.getString(R.string._465_drive_state_8);
                break;
            default:
                string = "--";
                break;
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data3"), string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit5(iArr[7]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[7]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[8]);
        updateDoorView(this.mContext);
    }

    private void setDrive0x180(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_465_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_465_drive_data1"), String.valueOf(DataHandleUtils.getMsbLsbResult(iArr[7], iArr[8]) * 0.125d)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
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

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTireInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTireInfo, iArr)) {
            return false;
        }
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mPanoramicInfo, iArr)) {
            return false;
        }
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
