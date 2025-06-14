package com.hzbhd.canbus.car._23;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_23_AMPLIFIER_ASL = "share_23_amplifier_asl";
    static final int SHARE_23_AMPLIFIER_BAND_OFFSET = 5;
    static final String SHARE_23_AMPLIFIER_SURROUND = "share_23_amplifier_surround";
    static final String SHARE_23_IS_SUPPORT_PANORAMIC = "share_23_is_support_panoramic";
    static final String SHARE_23_LANGUAGE = "share_23_language";
    private static int mKnobNow;
    private int m0x72WheelKeyData;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private int[] mAmplifierDataNow;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private int mDifferent;
    private int[] mDriveDataNow;
    private int mEachId;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private int mMediaStatusNow;
    private int mOutDoorTempDataNow;
    private int mPanoramicStatusNow;
    private MyPanoramicView mPanoramicView;
    private int[] mRadarDataNow;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private TimerHelper mTimerHelper;
    private byte[] mTrackDataNow;
    private String mUsbSongArtistNow;
    private String mUsbSongTitleNow;
    private byte[] mVersionInfoNow;
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    private boolean isFinish = false;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        int i = this.mEachId;
        if (i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 0, 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) i, 4});
        }
        initAmplifier(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        boolean z = true;
        try {
            int i = byteArrayToIntArray[1];
            if (i == 18) {
                setDoorData0x12();
            } else if (i == 49) {
                setAirData0x31();
            } else if (i == 65) {
                setRadarData0x41();
            } else if (i == 114) {
                setKeyTrack0x72(context);
            } else if (i == 116) {
                setKeyKonb0x74();
            } else if (i == 166) {
                setAmplifierData0xA6();
            } else if (i == 224) {
                setMediaStatus0xE0();
            } else if (i == 232) {
                setParkAssistance0xE8();
            } else if (i == 240) {
                setVersionInfo0xF0();
            } else if (i == 242) {
                if (byteArrayToIntArray[2] != 1) {
                    z = false;
                }
                forceReverse(context, z);
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setParkAssistance0xE8() {
        MyPanoramicView myPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        this.mPanoramicView = myPanoramicView;
        myPanoramicView.mTvTipsStatus = this.mCanBusInfoInt[2];
        this.mPanoramicView.mPageNumber = this.mCanBusInfoInt[6];
        this.mPanoramicView.mBtnStartStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbPaStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
        this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[8];
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._23.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mPanoramicView.updatePanoramicView(MsgMgr.this.mContext);
            }
        });
    }

    private void setKeyTrack0x72(Context context) {
        int i;
        char c;
        if (isDriveDataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " km/h"));
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 1, sb.append((iArr[10] * 256) + iArr[11]).append(" rpm").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        }
        int i2 = this.m0x72WheelKeyData;
        int i3 = this.mCanBusInfoInt[4];
        if (i2 != i3) {
            this.m0x72WheelKeyData = i3;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 == 2) {
                        realKeyLongClick2(context, 8);
                    } else if (i3 == 4) {
                        realKeyLongClick2(context, HotKeyConstant.K_SPEECH);
                    } else if (i3 == 5) {
                        int i4 = this.mDifferent;
                        if (i4 == 3 || i4 == 11 || i4 == 12 || i4 == 13 || i4 == 16) {
                            realKeyLongClick2(context, HotKeyConstant.K_VOICE_PICKUP);
                        } else if (i4 == 5 || i4 == 14 || i4 == 15) {
                            realKeyLongClick2(context, HotKeyConstant.K_SPEECH);
                        } else {
                            realKeyLongClick2(context, 14);
                        }
                    } else if (i3 == 6) {
                        int i5 = this.mDifferent;
                        if (i5 == 1 || i5 == 2 || i5 == 5 || i5 == 11 || i5 == 12 || i5 == 14) {
                            realKeyLongClick2(context, HotKeyConstant.K_PHONE_ON_OFF);
                        } else {
                            realKeyLongClick2(context, 15);
                        }
                    } else if (i3 == 10) {
                        myRealKeyLongClick2(context, 2);
                    } else {
                        switch (i3) {
                            case 13:
                                int i6 = this.mDifferent;
                                if (i6 == 5 || i6 == 11 || i6 == 12 || i6 == 13 || i6 == 15 || i6 == 16) {
                                    realKeyLongClick2(context, 46);
                                    break;
                                } else {
                                    realKeyLongClick2(context, 45);
                                    break;
                                }
                            case 14:
                                int i7 = this.mDifferent;
                                if (i7 == 5 || i7 == 11 || i7 == 12 || i7 == 13 || i7 == 15 || i7 == 16) {
                                    realKeyLongClick2(context, 45);
                                    break;
                                } else {
                                    realKeyLongClick2(context, 46);
                                    break;
                                }
                            case 15:
                                realKeyLongClick2(context, 49);
                                break;
                            case 16:
                                realKeyLongClick2(context, 50);
                                break;
                        }
                    }
                    c = 7;
                } else {
                    c = 7;
                    realKeyLongClick2(context, 7);
                }
                i = 0;
            } else {
                i = 0;
                c = 7;
                realKeyLongClick2(context, 0);
            }
        } else {
            i = 0;
            c = 7;
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[c], bArr[6], i, 500, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setMediaStatus0xE0() {
        if (isMediaStatusChange()) {
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                enterNoSource();
                realKeyClick4(52);
            }
            switch (i) {
                case 32:
                    changeBandAm1();
                    break;
                case 33:
                    changeBandFm1();
                    break;
                case 34:
                    changeBandFm2();
                    break;
                case 35:
                    realKeyClick4(130);
                    break;
                case 36:
                    realKeyClick4(59);
                    break;
                case 37:
                    realKeyClick4(140);
                    break;
                case 38:
                    realKeyClick4(141);
                    break;
                case 39:
                    realKeyClick4(61);
                    break;
            }
        }
    }

    private void setAmplifierData0xA6() {
        if (isAmplifierDataChange()) {
            GeneralAmplifierData.volume = this.mCanBusInfoByte[2];
            GeneralAmplifierData.leftRight = this.mCanBusInfoByte[3];
            GeneralAmplifierData.frontRear = this.mCanBusInfoByte[4];
            GeneralAmplifierData.bandBass = this.mCanBusInfoByte[5] + 5;
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoByte[6] + 5;
            GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[7] + 5;
            saveAmplifierData(this.mContext, this.mCanId);
            updateAmplifierActivity(null);
            SharePreUtil.setStringValue(this.mContext, "23_0xA6", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 3);
            arrayList.add(new SettingUpdateEntity(0, 2, intFromByteWithBit + "").setValueStr(true));
            arrayList.add(new SettingUpdateEntity(0, 3, intFromByteWithBit + "").setValueStr(true));
            arrayList.add(new SettingUpdateEntity(0, 4, ((int) this.mCanBusInfoByte[9]) + "").setValueStr(true));
            arrayList.add(new SettingUpdateEntity(0, 5, ((int) this.mCanBusInfoByte[9]) + "").setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationDataType2(3, iArr[2], 4, iArr[3], 4, iArr[4], 3, iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationDataType2(3, iArr2[6], 4, iArr2[7], 4, iArr2[8], 3, iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setKeyKonb0x74() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick4(0);
                break;
            case 1:
                realKeyClick4(45);
                break;
            case 2:
                realKeyClick4(46);
                break;
            case 3:
                realKeyClick4(47);
                break;
            case 4:
                realKeyClick4(48);
                break;
            case 5:
                realKeyClick4(4);
                break;
            case 6:
                realKeyClick4(59);
                break;
            case 7:
                realKeyClick4(141);
                break;
            case 8:
                realKeyClick4(140);
                break;
            case 9:
                realKeyClick4(49);
                break;
            case 10:
                realKeyClick4(50);
                break;
            case 11:
                realKeyClick4(52);
                break;
            case 12:
                realKeyClick4(128);
                break;
        }
        resolveKnob();
    }

    private void setDoorData0x12() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        this.mLeftFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        this.mRightFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        this.mLeftRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        this.mRightRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setAirData0x31() {
        int airWhat = getAirWhat();
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 13);
        setOutDoorTem();
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            GeneralAirData.in_out_auto_cycle = 2;
        } else {
            GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]) ? 1 : 0;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        cleanAllBlow();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_auto_wind = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_window = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else {
            switch (intFromByteWithBit) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    break;
            }
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
        if (intFromByteWithBit2 == 1) {
            GeneralAirData.front_right_auto_wind = true;
        } else if (intFromByteWithBit2 == 2) {
            GeneralAirData.front_right_blow_window = true;
        } else if (intFromByteWithBit2 == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 6) {
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (intFromByteWithBit2) {
                case 11:
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveFrontAirTemp(this.mCanBusInfoInt[9]);
        int i = this.mCanBusInfoInt[10];
        if (i == 1) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        } else if (i == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
        }
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        GeneralAirData.rear_temperature = resolveRearAirTemp(this.mCanBusInfoInt[12]);
        updateAirActivity(this.mContext, airWhat);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        byte[] bArr = new byte[15];
        bArr[0] = 22;
        bArr[1] = -46;
        bArr[2] = 0;
        for (int i = 3; i < 15; i++) {
            bArr[i] = 32;
        }
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, "ATV         ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 3) {
                str4 = "0" + i + " " + str2 + "      ";
            } else {
                str4 = "0" + i + " " + str2 + "     ";
            }
        } else if (str2.length() != 5 || str2.charAt(0) == '1') {
            str4 = "0" + i + " " + str2 + "   ";
        } else {
            str4 = "0" + i + "  " + str2 + "   ";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, allBandTypeData}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "AUX         ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, "            ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, "DTV         ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        String str4;
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        String str5 = new DecimalFormat("00").format((byte) ((i / 60) % 60));
        String str6 = new DecimalFormat("00").format((byte) (i % 60));
        byte b2 = 7;
        if (i6 == 6 || i6 == 7) {
            str4 = new DecimalFormat("000").format(i3) + " " + str5 + str6 + "    ";
        } else {
            str4 = new DecimalFormat("000").format(i4) + " " + str5 + str6 + "    ";
        }
        if (i6 != 1 && i6 != 2 && i6 != 3) {
            b2 = (i6 == 6 || i6 == 7) ? (byte) 6 : (byte) 0;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, b2}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (new DecimalFormat("000").format((b6 * 256) + i) + " " + new DecimalFormat("00").format(b4 & 255) + new DecimalFormat("00").format(b5 & 255) + "    ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -46, b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (new DecimalFormat("000").format((b7 * 256) + i) + " " + new DecimalFormat("00").format(b4 & 255) + new DecimalFormat("00").format(b5 & 255) + "    ").getBytes()));
        reportID3Info((byte) -44, (byte) -45, str4, str6, false, "Unicode");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        reportID3Info((byte) -44, (byte) -45, "", "", true, "Unicode");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bArr = {22, -53};
        byte[] bArr2 = new byte[10];
        bArr2[0] = 0;
        bArr2[1] = 0;
        bArr2[2] = 0;
        bArr2[3] = (byte) i8;
        bArr2[4] = (byte) i6;
        bArr2[5] = (byte) (z ? 1 : 0);
        bArr2[6] = (byte) (!z2 ? 0 : 1);
        bArr2[7] = 0;
        bArr2[8] = 0;
        bArr2[9] = 0;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, bArr2));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        if (i != 2) {
            return false;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
        return true;
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void myRealKeyLongClick2(Context context, int i) {
        if (i == 2 && Settings.System.getInt(context.getContentResolver(), "gpioLevel", 1) == 0) {
            int i2 = this.mDifferent;
            if (i2 == 13 || i2 == 16) {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 2, 0});
                return;
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
                return;
            }
        }
        realKeyLongClick2(context, i);
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (i == length) {
            return Arrays.copyOf(bArr, length);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void setOutDoorTem() {
        if (isOutDoorTempChange()) {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private String resolveOutDoorTem() {
        return ((this.mCanBusInfoInt[13] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveFrontAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private String resolveRearAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i < 34 || i > 64) ? " " : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_left_auto_wind = false;
        GeneralAirData.rear_right_auto_wind = false;
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = 0;
        int[] iArr2 = {i & 239, i2 & WorkQueueKt.MASK, iArr[4], iArr[5], iArr[6], iArr[7], iArr[8], iArr[9]};
        int[] iArr3 = {i & 16, i2 & 128, iArr[10], iArr[11], iArr[12]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i3 = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr3)) {
            i3 = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 8);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 5);
        return i3;
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 4;
            case "AM2":
                return (byte) 5;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 1;
        }
    }

    private String getBandUnit(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return "KHz";
            case "FM1":
            case "FM2":
            case "FM3":
                return "MHz";
            default:
                return "";
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

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    /* JADX WARN: Type inference failed for: r8v0, types: [com.hzbhd.canbus.car._23.MsgMgr$2] */
    private void reportID3Info(final byte b, final byte b2, final String str, final String str2, final boolean z, final String str3) {
        new Thread() { // from class: com.hzbhd.canbus.car._23.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    if (z) {
                        sleep(1000L);
                    }
                    if (str.equals(MsgMgr.this.mUsbSongTitleNow) && str2.equals(MsgMgr.this.mUsbSongArtistNow)) {
                        return;
                    }
                    MsgMgr.this.mUsbSongTitleNow = str;
                    MsgMgr.this.mUsbSongArtistNow = str2;
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal(b, str, str3, 34);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal(b2, str2, str3, 18);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str, String str2, int i) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, b}, DataHandleUtils.exceptBOMHead(str.getBytes(str2))), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus) {
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

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isPanoramicStatusChange() {
        int i = this.mCanBusInfoInt[2];
        if (this.mPanoramicStatusNow == i) {
            return false;
        }
        this.mPanoramicStatusNow = i;
        return true;
    }

    private boolean isTrackDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[6], bArr[7]};
        if (Arrays.equals(this.mTrackDataNow, bArr2)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(bArr2, 2);
        return true;
    }

    private boolean isMediaStatusChange() {
        int i = this.mCanBusInfoInt[2];
        if (this.mMediaStatusNow == i) {
            return false;
        }
        this.mMediaStatusNow = i;
        return true;
    }

    private boolean isAmplifierDataChange() {
        if (Arrays.equals(this.mAmplifierDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDriveDataChange() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[2], iArr[3], iArr[10], iArr[11]};
        if (Arrays.equals(this.mDriveDataNow, iArr2)) {
            return false;
        }
        this.mDriveDataNow = Arrays.copyOf(iArr2, 4);
        return true;
    }

    private boolean isOutDoorTempChange() {
        int i = this.mOutDoorTempDataNow;
        int i2 = this.mCanBusInfoInt[13];
        if (i == i2) {
            return false;
        }
        this.mOutDoorTempDataNow = i2;
        return true;
    }

    private void resolveKnob() {
        int i;
        int volumeValue = FutureUtil.instance.getVolumeValue();
        int i2 = this.mCanBusInfoInt[3];
        int i3 = i2 - mKnobNow;
        if (i3 == 0) {
            return;
        }
        mKnobNow = i2;
        Log.i("Ljq", "resolveKnob: mKnobNow: " + mKnobNow);
        if (i3 > 0) {
            i = 7;
            if (volumeValue >= 40) {
                return;
            }
        } else {
            i = 8;
            if (volumeValue <= 0) {
                return;
            }
        }
        for (int i4 = 0; i4 < Math.abs(i3); i4++) {
            realKeyClick4(i);
        }
    }

    private void initAmplifier(Context context) {
        getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
    }

    /* renamed from: com.hzbhd.canbus.car._23.MsgMgr$3, reason: invalid class name */
    class AnonymousClass3 extends Thread {
        final /* synthetic */ int val$balance;
        final /* synthetic */ int val$bass;
        final /* synthetic */ int val$fade;
        final /* synthetic */ int val$middle;
        final /* synthetic */ int val$treble;
        final /* synthetic */ int val$volume;

        AnonymousClass3(int i, int i2, int i3, int i4, int i5, int i6) {
            this.val$volume = i;
            this.val$balance = i2;
            this.val$fade = i3;
            this.val$bass = i4;
            this.val$middle = i5;
            this.val$treble = i6;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            try {
                sleep(100L);
                MsgMgr.this.sendAmplifierVol(this.val$volume);
                while (!MsgMgr.this.isFinish) {
                    sleep(50L);
                }
                MsgMgr.this.sendAmplifierData(2, this.val$balance);
                while (!MsgMgr.this.isFinish) {
                    sleep(50L);
                }
                MsgMgr.this.sendAmplifierData(3, this.val$fade);
                while (!MsgMgr.this.isFinish) {
                    sleep(50L);
                }
                MsgMgr.this.sendAmplifierData(4, this.val$bass - 5);
                while (!MsgMgr.this.isFinish) {
                    sleep(50L);
                }
                MsgMgr.this.sendAmplifierData(5, this.val$middle - 5);
                while (!MsgMgr.this.isFinish) {
                    sleep(50L);
                }
                MsgMgr.this.sendAmplifierData(6, this.val$treble - 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierVol(int i) {
        this.isFinish = false;
        getTimerHelper().startTimer(new TimerTask(i) { // from class: com.hzbhd.canbus.car._23.MsgMgr.4
            final /* synthetic */ int val$data;
            int vol;

            {
                this.val$data = i;
                this.vol = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i2 = this.vol - 5;
                this.vol = i2;
                if (i2 > 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, 5});
                    return;
                }
                MsgMgr.this.getTimerHelper().stopTimer();
                int i3 = this.vol + 5;
                this.vol = i3;
                CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) i3});
                MsgMgr.this.isFinish = true;
            }
        }, 0L, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAmplifierData(int i, int i2) {
        if (i2 != 0) {
            this.isFinish = false;
            getTimerHelper().startTimer(new TimerTask(i2, i) { // from class: com.hzbhd.canbus.car._23.MsgMgr.5
                int i = 0;
                int step;
                final /* synthetic */ int val$cmd;
                final /* synthetic */ int val$data;

                {
                    this.val$data = i2;
                    this.val$cmd = i;
                    this.step = i2 / Math.abs(i2);
                }

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    int i3 = this.i;
                    this.i = i3 + 1;
                    if (i3 < Math.abs(this.val$data)) {
                        CanbusMsgSender.sendMsg(new byte[]{22, -83, (byte) this.val$cmd, (byte) this.step});
                    } else {
                        MsgMgr.this.isFinish = true;
                        MsgMgr.this.getTimerHelper().stopTimer();
                    }
                }
            }, 0L, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerHelper getTimerHelper() {
        if (this.mTimerHelper == null) {
            this.mTimerHelper = new TimerHelper();
        }
        return this.mTimerHelper;
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        if (i == 2 && i2 == 0) {
            arrayList.add(new SettingUpdateEntity(2, 1, null).setEnable(i3 == 1));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private class TimerHelper {
        private Timer mTimer;
        private TimerTask mTimerTask;

        private TimerHelper() {
        }

        public void startTimer(TimerTask timerTask, long j, long j2) {
            Log.i("TimerUtil", "startTimer: " + this);
            if (timerTask == null) {
                return;
            }
            this.mTimerTask = timerTask;
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            this.mTimer.schedule(this.mTimerTask, j, j2);
        }

        public void stopTimer() {
            Log.i("TimerUtil", "stopTimer: " + this);
            TimerTask timerTask = this.mTimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimerTask = null;
            }
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int i, int i2, int i3, Bundle bundle) {
        if (i == 182) {
            CanbusMsgSender.sendMsg(new byte[]{22, -3, 1, 0});
        }
    }
}
