package com.hzbhd.canbus.car._371;

import android.content.Context;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    DecimalFormat df_2Integer = new DecimalFormat("00");
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mRearRadarData;
    byte[] mTrackData;
    private UiMgr mUiMgr;

    private String getAscllContent() {
        return "Ascll RESULT";
    }

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
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            set0x20WheelKeyInfo();
            return;
        }
        if (i == 41) {
            set0x29EspInfo();
            return;
        }
        if (i != 48) {
            switch (i) {
                case 34:
                    set0x22RearRadar();
                    break;
                case 35:
                    set0x23FrontRadar();
                    break;
                case 36:
                    set0x24BaicInfo();
                    break;
                default:
                    switch (i) {
                        case 119:
                            set0x77Info();
                            break;
                        case 120:
                            set0x78WheelKeyInfo();
                            break;
                        case 121:
                            set0x79VolInfo();
                            break;
                        case 122:
                            set0x7ATimeInfo();
                            break;
                        case 123:
                            set0x7BInfo();
                            break;
                        case 124:
                            set0x7CInfo();
                            break;
                    }
            }
            return;
        }
        set0x30VersionInfo();
    }

    private void set0x7CInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads5", "_371_ads5_7"), get0xC7Data0State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads5", "_371_ads5_6"), get0xC7Data0State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads5", "_371_ads5_5"), get0xC7Data0State(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads6", "_371_ads6_0"), get0xC7Data1State(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads7", "_371_ads77"), getUsbState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads7", "_371_ads73_0"), getUsbPlayState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads8", "_371_ads87"), getBtState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads8", "_371_ads85"), getBtSignalState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads8", "_371_ads82"), getBtSignalState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getBtSignalState(int i) {
        return i == 0 ? "NO INFO" : (i - 1) + "Lever";
    }

    private String getBtState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._371_ads87_1);
        }
        return this.mContext.getString(R.string._371_ads87_0);
    }

    private String getUsbPlayState(int i) {
        switch (i) {
            case 0:
                return this.mContext.getString(R.string._371_ads73_1);
            case 1:
                return this.mContext.getString(R.string._371_ads73_2);
            case 2:
                return this.mContext.getString(R.string._371_ads73_3);
            case 3:
                return this.mContext.getString(R.string._371_ads73_4);
            case 4:
                return this.mContext.getString(R.string._371_ads73_5);
            case 5:
                return this.mContext.getString(R.string._371_ads73_6);
            case 6:
                return this.mContext.getString(R.string._371_ads73_7);
            default:
                return "NO INFO";
        }
    }

    private Object getUsbState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._371_ads77_1);
        }
        return this.mContext.getString(R.string._371_ads77_0);
    }

    private String get0xC7Data1State(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._371_ads6_0_0);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._371_ads6_0_1);
        }
        if (i != 2) {
            return i != 3 ? "NO INFO" : this.mContext.getString(R.string._371_ads6_0_3);
        }
        return this.mContext.getString(R.string._371_ads6_0_2);
    }

    private String get0xC7Data0State(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._371_ads5_on);
        }
        return this.mContext.getString(R.string._371_ads5_off);
    }

    private void set0x77Info() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4"), getContent()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            return;
        }
        if (i == 1) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4_0"), getContent()));
            updateGeneralDriveData(arrayList2);
            updateDriveDataActivity(null);
            return;
        }
        if (i == 2) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4_1"), getContent()));
            updateGeneralDriveData(arrayList3);
            updateDriveDataActivity(null);
            return;
        }
        if (i != 3) {
            return;
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_371_ads4_title"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_371_ads4_2"), getContent()));
        updateGeneralDriveData(arrayList4);
        updateDriveDataActivity(null);
    }

    private void set0x7BInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads", "_371_ads3"), Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x7ATimeInfo() {
        String str;
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
                str = "Afternoon " + this.df_2Integer.format(this.mCanBusInfoInt[3]) + ":" + this.df_2Integer.format(this.mCanBusInfoInt[4]);
            } else {
                str = "Morning " + this.df_2Integer.format(this.mCanBusInfoInt[3]) + ":" + this.df_2Integer.format(this.mCanBusInfoInt[4]);
            }
        } else {
            str = "" + this.df_2Integer.format(this.mCanBusInfoInt[3]) + ":" + this.df_2Integer.format(this.mCanBusInfoInt[4]);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads", "_371_ads2"), str).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x79VolInfo() {
        String str = this.mCanBusInfoInt[2] == 0 ? "No information" : this.mCanBusInfoInt[3] + "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_371_ads"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_371_ads", "_371_ads1"), str).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x78WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 29) {
            buttonKey(46);
            return;
        }
        if (i != 30) {
            switch (i) {
                case 17:
                    buttonKey(76);
                    break;
                case 18:
                    buttonKey(77);
                    break;
                case 19:
                    buttonKey(61);
                    break;
                case 20:
                    buttonKey(59);
                    break;
                case 21:
                    buttonKey(151);
                    break;
                case 22:
                    buttonKey(17);
                    break;
                default:
                    switch (i) {
                        case 24:
                            buttonKey(50);
                            break;
                        case 25:
                            buttonKey(49);
                            break;
                        case 26:
                            buttonKey(4);
                            break;
                    }
            }
            return;
        }
        buttonKey(45);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x29EspInfo() {
        if (isTrackInfoChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x24BaicInfo() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 0);
        int[] iArr2 = this.mCanBusInfoInt;
        iArr2[3] = blockBit(iArr2[3], 2);
        int[] iArr3 = this.mCanBusInfoInt;
        iArr3[3] = blockBit(iArr3[3], 3);
        int[] iArr4 = this.mCanBusInfoInt;
        iArr4[3] = blockBit(iArr4[3], 4);
        int[] iArr5 = this.mCanBusInfoInt;
        iArr5[3] = blockBit(iArr5[3], 5);
        int[] iArr6 = this.mCanBusInfoInt;
        iArr6[3] = blockBit(iArr6[3], 6);
        int[] iArr7 = this.mCanBusInfoInt;
        iArr7[3] = blockBit(iArr7[3], 7);
        if (isBasicInfoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralDoorData.isShowHandBrake = true;
            GeneralDoorData.isHandBrakeUp = true ^ DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x23FrontRadar() {
        if (isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22RearRadar() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 3) {
            buttonKey(46);
            return;
        }
        if (i == 4) {
            buttonKey(45);
            return;
        }
        if (i == 16) {
            buttonKey(HotKeyConstant.K_DISP);
            return;
        }
        if (i != 23) {
            switch (i) {
                case 7:
                    buttonKey(2);
                    break;
                case 8:
                    buttonKey(HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    buttonKey(14);
                    break;
                case 10:
                    buttonKey(15);
                    break;
            }
            return;
        }
        buttonKey(151);
    }

    private String getContent() {
        if (this.mCanBusInfoInt[3] == 0) {
            MyLog.temporaryTracking("解ASCLL");
            String ascllContent = getAscllContent();
            MyLog.temporaryTracking("ASCLL结果：" + ascllContent);
            return ascllContent;
        }
        return getUnicodeContent();
    }

    private String getUnicodeContent() {
        ArrayList arrayList = new ArrayList();
        String str = "";
        for (int i = 5; i < this.mCanBusInfoInt.length; i++) {
            if (i % 2 != 0) {
                str = "u" + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i]));
            } else {
                String str2 = str + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i]));
                MyLog.temporaryTracking("第" + i + "次：" + str2);
                arrayList.add(str2);
                str = "";
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((char) Integer.valueOf(((String) it.next()).substring(1), 16).intValue());
        }
        return stringBuffer.toString();
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
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

    public void panoramicSwitch(boolean z) {
        forceReverse(this.mContext, z);
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }
}
