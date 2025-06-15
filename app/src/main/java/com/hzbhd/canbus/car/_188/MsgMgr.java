package com.hzbhd.canbus.car._188;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private int[] lastArrayAirCondition = {0, 0, 0, 0, 0, 0, 0};
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferentId;
    private MyPanoramicView mPanoramicView;
    private int mWheelKeyStatus;
    private int mWheelKeyWhat;
    private int textNumberFive;
    private int textNumberFour;
    private int textNumberOne;
    private int textNumberThree;
    private int textNumberTwo;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mDifferentId = getCurrentCanDifferentId();
        RadarInfoUtil.mMinIsClose = false;
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        int i = this.mDifferentId;
        if (i == 1 || i == 2 || i == 4) {
            updateBubble(context, SharePreUtil.getIntValue(context, "share_188_is_support_panoramic", 0) == 1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setMediaSwitchCommand0x40();
            return;
        }
        if (i == 80) {
            setPhoneCommand0x50();
            return;
        }
        if (i == 96) {
            setPanelInfo0x60();
            return;
        }
        if (i == 106) {
            setCarSpeed0x6A();
            return;
        }
        if (i == 148) {
            setParkAssistance0x94();
            return;
        }
        if (i != 149) {
            switch (i) {
                case 32:
                    realKeyControl0x20();
                    break;
                case 33:
                    rptCmdFilter(context, byteArrayToIntArray, this.lastArrayAirCondition);
                    break;
                case 34:
                    setRearRadar0x22();
                    break;
                case 35:
                    setFrontRadar0x23();
                    break;
            }
            return;
        }
        setDrivingAids0x95();
    }

    private void setCarSpeed0x6A() {
        int[] iArr = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]));
    }

    private void airConditionInfo(Context context) {
        if (isOnlyChanged(this.mCanBusInfoInt, 6)) {
            return;
        }
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_left_blow_window = false;
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3)) {
            case 0:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                break;
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                break;
            case 3:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                break;
            case 4:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                break;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            GeneralAirData.cycle_in_out_close = 1;
        } else if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            GeneralAirData.cycle_in_out_close = 2;
        } else {
            GeneralAirData.cycle_in_out_close = 0;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[4];
        if (i >= 1 && i <= 15) {
            GeneralAirData.front_left_temperature = "LO";
        } else if (i >= 49 && i <= 63) {
            GeneralAirData.front_left_temperature = "HI";
        } else if (i == 0) {
            GeneralAirData.front_left_temperature = " ";
        } else if (i >= 16 && i <= 48) {
            GeneralAirData.front_left_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 6) + "°C";
        }
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[5];
        if (i2 >= 1 && i2 <= 15) {
            GeneralAirData.front_right_temperature = "LO";
        } else if (i2 >= 49 && i2 <= 63) {
            GeneralAirData.front_right_temperature = "HI";
        } else {
            int i3 = iArr[4];
            if (i3 == 0) {
                GeneralAirData.front_left_temperature = " ";
            } else if (i3 >= 16 && i3 <= 48) {
                GeneralAirData.front_right_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 6) + "°C";
            }
        }
        updateAirActivity(context, 1001);
    }

    private void realKeyControl0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            wheelKeyClick(0);
            return;
        }
        if (i == 1) {
            wheelKeyClick(7);
            return;
        }
        if (i == 2) {
            wheelKeyClick(8);
            return;
        }
        if (i == 3) {
            wheelKeyClick(46);
            return;
        }
        if (i == 4) {
            wheelKeyClick(45);
            return;
        }
        if (i == 9) {
            wheelKeyClick(14);
            return;
        }
        if (i == 10) {
            wheelKeyClick(15);
            return;
        }
        if (i == 21) {
            wheelKeyClick(50);
            return;
        }
        if (i == 22) {
            wheelKeyClick(49);
            return;
        }
        if (i == 80) {
            wheelKeyClick(53);
            return;
        }
        if (i != 81) {
            switch (i) {
                case 7:
                    wheelKeyClick(2);
                    break;
                case 64:
                    wheelKeyClick(141);
                    break;
                case 66:
                    wheelKeyClick(30);
                    break;
                case 68:
                    wheelKeyClick(HotKeyConstant.K_DISP);
                    break;
                case 74:
                    wheelKeyClick(HotKeyConstant.K_BRIGHTNESS_INCREASE);
                    break;
                case 78:
                    wheelKeyClick(222);
                    break;
                case 96:
                    wheelKeyClick(45);
                    break;
                case 98:
                    wheelKeyClick(48);
                    break;
                case 100:
                    wheelKeyClick(46);
                    break;
                case 102:
                    wheelKeyClick(47);
                    break;
                case 135:
                    wheelKeyClick(HotKeyConstant.K_SLEEP);
                    break;
                default:
                    switch (i) {
                        case 32:
                            wheelKeyClick(HotKeyConstant.K_HOUR);
                            break;
                        case 33:
                            wheelKeyClick(HotKeyConstant.K_HOUR);
                            break;
                        case 34:
                            wheelKeyClick(128);
                            break;
                        case 35:
                            wheelKeyClick(128);
                            break;
                        case 36:
                            wheelKeyClick(76);
                            break;
                        case 37:
                            wheelKeyClick(130);
                            break;
                        case 38:
                            wheelKeyClick(77);
                            break;
                        case 39:
                            wheelKeyClick(33);
                            break;
                        case 40:
                            wheelKeyClick(34);
                            break;
                        case 41:
                            wheelKeyClick(35);
                            break;
                        case 42:
                            wheelKeyClick(36);
                            break;
                        case 43:
                            wheelKeyClick(37);
                            break;
                        case 44:
                            wheelKeyClick(38);
                            break;
                        case 45:
                            sendKnob_1(7);
                            break;
                        case 46:
                            sendKnob_1(8);
                            break;
                        case 47:
                            sendKnob_2(47);
                            break;
                        case 48:
                            sendKnob_2(48);
                            break;
                        case 49:
                            wheelKeyClick(45);
                            break;
                        case 50:
                            wheelKeyClick(46);
                            break;
                        case 51:
                            wheelKeyClick(17);
                            break;
                        default:
                            switch (i) {
                                case 70:
                                    wheelKeyClick(56);
                                    break;
                                case 71:
                                    wheelKeyClick(151);
                                    break;
                                case 72:
                                    wheelKeyClick(56);
                                    break;
                                default:
                                    switch (i) {
                                        case 83:
                                            wheelKeyClick(143);
                                            break;
                                        case 84:
                                            wheelKeyClick(141);
                                            break;
                                        case 85:
                                            wheelKeyClick(27);
                                            break;
                                        default:
                                            switch (i) {
                                                case 112:
                                                    wheelKeyClick(49);
                                                    break;
                                                case 113:
                                                    wheelKeyClick(50);
                                                    break;
                                                case 114:
                                                    wheelKeyClick(128);
                                                    break;
                                                case 115:
                                                    wheelKeyClick(52);
                                                    break;
                                                case 116:
                                                    sendKnob_1(7);
                                                    break;
                                                case 117:
                                                    sendKnob_1(8);
                                                    break;
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        wheelKeyClick(31);
    }

    private void setRearRadar0x22() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar0x23() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[3], bArr[2], 0, 5448, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setMediaSwitchCommand0x40() {
        try {
            switch (this.mCanBusInfoInt[2]) {
                case 0:
                    enterNoSource();
                    realKeyClick(52);
                    break;
                case 1:
                    changeBandAm1();
                    break;
                case 2:
                    changeBandFm1();
                    break;
                case 3:
                    changeBandFm2();
                    break;
                case 4:
                    realKeyClick(130);
                    break;
                case 5:
                    realKeyClick(59);
                    break;
                case 6:
                    realKeyClick(61);
                    break;
                case 7:
                    realKeyClick(140);
                    break;
                case 8:
                    realKeyClick(141);
                    break;
                case 9:
                    realKeyClick(129);
                    break;
                case 10:
                    realKeyClick(139);
                    break;
                case 11:
                    realKeyClick(52);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPhoneCommand0x50() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            realKeyClick(14);
        } else if (i == 2) {
            realKeyClick(15);
        } else {
            if (i != 3) {
                return;
            }
            realKeyClick(15);
        }
    }

    private void setPanelInfo0x60() {
        if (this.mCanBusInfoInt[2] != 49) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
        GeneralSettingData.dataList = arrayList;
        updateSettingActivity(null);
    }

    private void setParkAssistance0x94() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        this.mPanoramicView.mPageNumber = this.mCanBusInfoInt[2];
        this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mTvTipsStatus = this.mCanBusInfoInt[4];
        this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[5];
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._188.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mPanoramicView.updatePanoramicView(MsgMgr.this.mContext);
            }
        });
    }

    private void setDrivingAids0x95() {
        if (this.mDifferentId == 3) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "open" : "close"));
            arrayList.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? "open" : "close"));
            arrayList.add(new SettingUpdateEntity(0, 2, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ? "open" : "close"));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, allBandTypeData, this.freqLo, this.freqHi, (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i7 == 240) {
            sendDiscEjectMsg(this.mContext);
            return;
        }
        if (i7 == 32) {
            int hour = getHour(i);
            int minute = getMinute(i);
            int second = getSecond(i);
            if (i6 == 1 || i6 == 5) {
                i3 = i4;
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, (byte) i3, (byte) i5, (byte) hour, (byte) minute, (byte) second});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, -1, 1, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z && i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1, 32});
        } else {
            if (z) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 32});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i6, (byte) i8, z ? (byte) 1 : (byte) 0});
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void wheelKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void sendKnob_1(int i) {
        realKeyClick3_1(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }

    private void sendKnob_2(int i) {
        realKeyClick3_2(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick(int i) {
        realKeyClick(this.mContext, i);
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 17;
            case "AM2":
                return (byte) 18;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.FreqInt = Integer.parseInt(str2);
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    public void updateSettingActivity(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettingActivity(int i, int i2, Object obj, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, obj).setEnable(z));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    protected void updateBubble(Context context, boolean z) {
        GeneralBubbleData.isShowBubble = z;
        updateBubble(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        if (i != 2 && i != 49) {
            return false;
        }
        sendPanoramicDispCommand(context);
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int i) {
        if (!CommUtil.isMiscReverse()) {
            return false;
        }
        if (i != 2 && i != 49) {
            return false;
        }
        MyLog.temporaryTracking("切换视频");
        sendPanoramicDispCommand(context);
        return true;
    }

    private void sendPanoramicDispCommand(Context context) {
        if (SharePreUtil.getIntValue(context, "share_188_is_support_panoramic", 0) == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int i, int i2, int i3, Bundle bundle) {
        if (i == 182) {
            sendPanoramicDispCommand(this.mContext);
        }
    }

    private boolean isOnlyChanged(int[] iArr, int i) {
        if (i >= iArr.length || i < 0) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != this.lastArrayAirCondition[i2]) {
                return false;
            }
        }
        for (int i3 = i + 1; i3 < iArr.length; i3++) {
            if (iArr[i3] != this.lastArrayAirCondition[i3]) {
                return false;
            }
        }
        return true;
    }

    private void rptCmdFilter(Context context, int[] iArr, int[] iArr2) {
        if (!contentCompare(iArr, iArr2)) {
            airConditionInfo(context);
        }
        if (iArr.length == iArr2.length) {
            transTo(iArr, iArr2);
        }
    }

    public boolean contentCompare(int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    public int[] transTo(int[] iArr, int[] iArr2) {
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        return iArr2;
    }
}
