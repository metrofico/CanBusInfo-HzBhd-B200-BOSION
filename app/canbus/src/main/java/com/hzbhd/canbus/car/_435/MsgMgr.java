package com.hzbhd.canbus.car._435;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mDoorData;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
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
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 38) {
            setEscInfo();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 65) {
            setDoorInfo();
            return;
        }
        if (i != 80) {
            switch (i) {
                case 32:
                    setSwcInfo();
                    break;
                case 33:
                    setAirInfo();
                    break;
                case 34:
                    setRearRadarInfo();
                    break;
                case 35:
                    setForntRadarInfo();
                    break;
            }
            return;
        }
        setPanelKey();
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorInfo() {
        if (this.mCanBusInfoInt[2] != 1 || isDoorDataNoChange()) {
            return;
        }
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void setEscInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setForntRadarInfo() {
        if (isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadarInfo() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setAirInfo() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[3] = blockBit(iArr[3], 4);
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            GeneralAirData.front_left_temperature = "LO";
        } else if (i == 31) {
            GeneralAirData.front_left_temperature = "HI";
        } else {
            GeneralAirData.front_left_temperature = ((this.mCanBusInfoInt[4] * 0.5d) + 17.5d) + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 0) {
            GeneralAirData.front_right_temperature = "LO";
        } else if (i2 == 31) {
            GeneralAirData.front_right_temperature = "HI";
        } else {
            GeneralAirData.front_right_temperature = ((this.mCanBusInfoInt[5] * 0.5d) + 17.5d) + getTempUnitC(this.mContext);
        }
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void setPanelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        if (i == 2) {
            return;
        }
        int i2 = iArr[2];
        if (i2 == 13) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, i);
            return;
        }
        if (i2 != 14) {
            switch (i2) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, i);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SLEEP, i);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 2, i);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 50, i);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 52, i);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 62, i);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 21, i);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 20, i);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 49, i);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 3, i);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 128, i);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 58, i);
                    break;
                default:
                    switch (i2) {
                        case 16:
                            realKeyLongClick1(this.mContext, 7, i);
                            break;
                        case 17:
                            realKeyLongClick1(this.mContext, 8, i);
                            break;
                        case 18:
                            realKeyLongClick1(this.mContext, 48, i);
                            break;
                        case 19:
                            realKeyLongClick1(this.mContext, 47, i);
                            break;
                        default:
                            switch (i2) {
                                case 33:
                                    realKeyLongClick1(this.mContext, 33, i);
                                    break;
                                case 34:
                                    realKeyLongClick1(this.mContext, 34, i);
                                    break;
                                case 35:
                                    realKeyLongClick1(this.mContext, 35, i);
                                    break;
                                case 36:
                                    realKeyLongClick1(this.mContext, 36, i);
                                    break;
                                case 37:
                                    realKeyLongClick1(this.mContext, 37, i);
                                    break;
                                case 38:
                                    realKeyLongClick1(this.mContext, 38, i);
                                    break;
                                case 39:
                                    realKeyLongClick1(this.mContext, 4, i);
                                    break;
                                case 40:
                                    realKeyLongClick1(this.mContext, 30, i);
                                    break;
                                case 41:
                                    realKeyLongClick1(this.mContext, 68, i);
                                    break;
                                case 42:
                                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, i);
                                    break;
                                case 43:
                                    realKeyLongClick1(this.mContext, 20, i);
                                    break;
                                case 44:
                                    realKeyLongClick1(this.mContext, 21, i);
                                    break;
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 15, i);
    }

    private void setSwcInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        if (i == 2) {
        }
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, i);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, i);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, i);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 46, i);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 45, i);
                break;
            case 5:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, i);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 3, i);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2, i);
                break;
            case 8:
                realKeyLongClick1(this.mContext, 14, i);
                break;
        }
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

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
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

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String tenToSixTeen(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    private int sixteenToTen(String str) {
        return Integer.parseInt(("0x" + str).replaceAll("^0[x|X]", ""), 16);
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isDoorDataNoChange() {
        if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mDoorData = this.mCanBusInfoInt;
        return false;
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

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }
}
