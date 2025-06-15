package com.hzbhd.canbus.car._431;

import android.content.Context;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPInfo;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    SystemButton systemButton;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int nowValue = -1;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private void setCarType0x26() {
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
        getUiMgr(this.mContext).carType(this.eachId);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).sendTimeInfo(i5, i6, z ? 1 : 0, i - 2000, i3, i4);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        int i3 = 1;
        if (str.equals("FM1")) {
            str4 = str2 + "MHz";
        } else if (str.equals("FM2")) {
            i3 = 2;
            str4 = str2 + "MHz";
        } else if (str.equals("FM3")) {
            i3 = 3;
            str4 = str2 + "MHz";
        } else if (str.equals("AM1")) {
            i3 = 4;
            str4 = str2 + "KHz";
        } else if (str.equals("AM2")) {
            i3 = 5;
            str4 = str2 + "KHz";
        } else {
            str4 = "nothing";
        }
        int length = 12 - str4.length();
        for (int i4 = 0; i4 < length; i4++) {
            str4 = str4 + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(i3, str4.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = ((int) b3) + str2 + str3;
        int length = 12 - str7.length();
        for (int i4 = 0; i4 < length; i4++) {
            str7 = str7 + " ";
        }
        if (b == 9) {
            getUiMgr(this.mContext).sendMediaInfo0x91(25, str7.getBytes());
        } else {
            getUiMgr(this.mContext).sendMediaInfo0x91(13, str7.getBytes());
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = ((int) b3) + str3 + str4;
        int length = 12 - str5.length();
        for (int i5 = 0; i5 < length; i5++) {
            str5 = str5 + " ";
        }
        if (b == 9) {
            getUiMgr(this.mContext).sendMediaInfo0x91(25, str5.getBytes());
        } else {
            getUiMgr(this.mContext).sendMediaInfo0x91(13, str5.getBytes());
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        String str = "AUX Playing";
        for (int i = 0; i < 1; i++) {
            str = str + " ";
        }
        getUiMgr(this.mContext).sendMediaInfo0x91(12, str.getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setSwc0x11();
            setTrack0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 33) {
            setPanelButton0x21();
            return;
        }
        if (i == 34) {
            setKnob0x22();
            return;
        }
        if (i == 38) {
            setCarType0x26();
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
            return;
        }
        if (i == 65) {
            setRadarInfo0x41();
            return;
        }
        if (i == 97) {
            setCarSetting0x61();
        } else if (i == 232) {
            setCarCamera0xE8();
        } else {
            if (i != 240) {
                return;
            }
            setVersion0xF0();
        }
    }

    private void setVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCarCamera0xE8() {
        int i;
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        forceReverse(context, iArr[4] == 1 || iArr[5] == 1 || (i = iArr[6]) == 1 || i == 1 || iArr[7] == 1 || iArr[8] == 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[7] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[8] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[6] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[4] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[5] == 1));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setCarSetting0x61() {
        getUiMgr(this.mContext);
        UiMgr.now_model1_R = this.mCanBusInfoInt[3];
        getUiMgr(this.mContext);
        UiMgr.now_model1_G = this.mCanBusInfoInt[4];
        getUiMgr(this.mContext);
        UiMgr.now_model1_B = this.mCanBusInfoInt[5];
        getUiMgr(this.mContext);
        UiMgr.now_model2_R = this.mCanBusInfoInt[7];
        getUiMgr(this.mContext);
        UiMgr.now_model2_G = this.mCanBusInfoInt[8];
        getUiMgr(this.mContext);
        UiMgr.now_model2_B = this.mCanBusInfoInt[9];
        getUiMgr(this.mContext);
        UiMgr.now_model3_R = this.mCanBusInfoInt[11];
        getUiMgr(this.mContext);
        UiMgr.now_model3_G = this.mCanBusInfoInt[12];
        getUiMgr(this.mContext);
        UiMgr.now_model3_B = this.mCanBusInfoInt[13];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_r"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_g"), Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_b"), Integer.valueOf(this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting0", "_431_setting_l"), Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_r"), Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_g"), Integer.valueOf(this.mCanBusInfoInt[8])).setProgress(this.mCanBusInfoInt[8]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_b"), Integer.valueOf(this.mCanBusInfoInt[9])).setProgress(this.mCanBusInfoInt[9]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting1", "_431_setting_l"), Integer.valueOf(this.mCanBusInfoInt[10])).setProgress(this.mCanBusInfoInt[10]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_r"), Integer.valueOf(this.mCanBusInfoInt[11])).setProgress(this.mCanBusInfoInt[11]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_g"), Integer.valueOf(this.mCanBusInfoInt[12])).setProgress(this.mCanBusInfoInt[12]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_b"), Integer.valueOf(this.mCanBusInfoInt[13])).setProgress(this.mCanBusInfoInt[13]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_431_setting2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_431_setting2", "_431_setting_l"), Integer.valueOf(this.mCanBusInfoInt[14])).setProgress(this.mCanBusInfoInt[14]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRadarInfo0x41() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 255) {
            iArr[2] = 0;
        }
        if (iArr[3] == 255) {
            iArr[3] = 0;
        }
        if (iArr[4] == 255) {
            iArr[4] = 0;
        }
        if (iArr[5] == 255) {
            iArr[5] = 0;
        }
        if (iArr[6] == 255) {
            iArr[6] = 0;
        }
        if (iArr[7] == 255) {
            iArr[7] = 0;
        }
        if (iArr[8] == 255) {
            iArr[8] = 0;
        }
        if (iArr[9] == 255) {
            iArr[9] = 0;
        }
        if (iArr[13] == 0) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, iArr2[2], iArr2[3], iArr2[4], iArr2[5]);
            int[] iArr3 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(3, iArr3[6], iArr3[7], iArr3[8], iArr3[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr4 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr4[2], iArr4[3], iArr4[4], iArr4[5]);
        int[] iArr5 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr5[6], iArr5[7], iArr5[8], iArr5[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setAirInfo0x31() {
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_foot = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[8], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[9], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        updateAirActivity(this.mContext, 1004);
    }

    private void setKnob0x22() {
        int i = this.nowValue;
        if (i == -1) {
            this.nowValue = this.mCanBusInfoInt[3];
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (i < iArr[3]) {
                realKeyClick4(this.mContext, 7);
            } else {
                realKeyClick4(this.mContext, 8);
            }
        } else if (i < iArr[3]) {
            realKeyClick4(this.mContext, 46);
        } else {
            realKeyClick4(this.mContext, 45);
        }
        this.nowValue = this.mCanBusInfoInt[3];
    }

    private void setPanelButton0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey2(0);
            return;
        }
        if (i == 1) {
            buttonKey2(1);
            return;
        }
        if (i == 2) {
            buttonKey2(21);
            return;
        }
        if (i == 3) {
            buttonKey2(20);
            return;
        }
        if (i == 21) {
            buttonKey2(75);
            return;
        }
        if (i == 22) {
            buttonKey2(49);
            return;
        }
        if (i == 36) {
            buttonKey2(59);
            return;
        }
        if (i == 40) {
            buttonKey2(HotKeyConstant.K_PHONE_ON_OFF);
            return;
        }
        if (i == 51) {
            buttonKey2(62);
            return;
        }
        if (i == 57) {
            buttonKey2(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 67) {
            buttonKey2(30);
            return;
        }
        if (i == 62) {
            buttonKey2(128);
            return;
        }
        if (i != 63) {
            switch (i) {
                case 9:
                    buttonKey2(3);
                    break;
                case 10:
                    buttonKey2(33);
                    break;
                case 11:
                    buttonKey2(34);
                    break;
                case 12:
                    buttonKey2(35);
                    break;
                case 13:
                    buttonKey2(36);
                    break;
                case 14:
                    buttonKey2(37);
                    break;
                case 15:
                    buttonKey2(38);
                    break;
            }
            return;
        }
        buttonKey2(HotKeyConstant.K_SPEECH);
    }

    private void setDoorInfo0x12() {
        if (isBasicInfoChange()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
            updateDoorView(this.mContext);
        }
    }

    private void setTrack0x11() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setSwc0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 4) {
            buttonKey(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            buttonKey(14);
            return;
        }
        if (i == 6) {
            buttonKey(15);
            return;
        }
        if (i == 48) {
            buttonKey(3);
            return;
        }
        switch (i) {
            case 8:
                buttonKey(45);
                break;
            case 9:
                buttonKey(46);
                break;
            case 10:
                buttonKey(151);
                break;
            default:
                switch (i) {
                    case 12:
                        buttonKey(2);
                        break;
                    case 13:
                        buttonKey(47);
                        break;
                    case 14:
                        buttonKey(48);
                        break;
                }
        }
    }

    public void showButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._431.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "360", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._431.MsgMgr.1.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramic0xFD(4, 1);
                        }
                    });
                }
                MsgMgr.this.systemButton.show();
            }
        });
    }

    public void hideButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._431.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "360", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._431.MsgMgr.2.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramic0xFD(4, 1);
                        }
                    });
                }
                MsgMgr.this.systemButton.hide();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    public void buttonKey2(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
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

    private String getTemperature(int i, int i2, int i3) {
        return i == i2 ? "LO" : i == i3 ? "HI" : this.df_2Decimal.format(i * 0.5d) + getTempUnitC(this.mContext);
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

    private boolean isNotAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
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

    private boolean isNotPanoramicInfoChange() {
        if (Arrays.equals(this.mPInfo, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPInfo = Arrays.copyOf(iArr, iArr.length);
        return false;
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

    public void forceReverse(boolean z) {
        forceReverse(this.mContext, z);
    }
}
