package com.hzbhd.canbus.car._375;

import android.app.AlertDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SetTimeView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    TextView content;
    CountDownTimer countDownTimer;
    AlertDialog dialog;
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mEnergyInfo;
    int[] mFrontRadarData;
    int[] mLeftRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mRightData;
    int[] mTireInfo;
    int[] mTireInfo2;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    Button park_1;
    Button park_2;
    Button park_3;
    Button park_4;
    View view;
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    private int FrontRightAlert = 0;
    private int FrontLeftAlert = 0;
    private int RearRightAlert = 0;
    private int RearLeftAlert = 0;
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    int parkState = 1;
    private String myF_Distance = "";
    private String myR_Distance = "";
    int startHour = -1;
    int startMin = -1;
    int endHour = -1;
    int endMin = -1;
    int energyTag = 0;

    private int getIntFromByteWithBit(int i, int i2, int i3) {
        return (i >> i2) & ((1 << i3) - 1);
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getState(int i) {
        if (i == 10) {
            return 1;
        }
        if (i != 16) {
            return i != 32 ? 0 : 3;
        }
        return 2;
    }

    private int getState2(int i) {
        return i == 0 ? 1 : 0;
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
        getUiMgr(context).makeConnection();
        getUiMgr(this.mContext).getCanBoxVersion();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).sendTimeInfo(i6, i5, z ? 1 : 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        str.hashCode();
        switch (str) {
            case "skylight.open":
                MessageSender.sendVoiceMsg(2);
                break;
            case "ac.temperature.max":
                MessageSender.sendVoiceMsg(9);
                break;
            case "ac.open":
                MessageSender.sendVoiceMsg(4);
                break;
            case "rear.right.window.open":
                MessageSender.sendVoiceMsg(13);
                break;
            case "air.in.out.cycle.off":
                MessageSender.sendVoiceMsg(6);
                break;
            case "ac.windlevel.max":
                MessageSender.sendVoiceMsg(8);
                break;
            case "blow_positive":
                MessageSender.sendVoiceMsg(7);
                break;
            case "skylight.close":
                MessageSender.sendVoiceMsg(1);
                break;
            case "front.left.window.open":
                MessageSender.sendVoiceMsg(10);
                break;
            case "ac.close":
                MessageSender.sendVoiceMsg(3);
                break;
            case "front.right.window.open":
                MessageSender.sendVoiceMsg(11);
                break;
            case "air.in.out.cycle.on":
                MessageSender.sendVoiceMsg(5);
                break;
            case "rear.left.window.open":
                MessageSender.sendVoiceMsg(12);
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int msb;
        int lsb;
        int msb2;
        int lsb2;
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        int i4 = 0;
        if (!str.equals("FM")) {
            if (str.equals("FM1")) {
                i4 = 1;
            } else if (str.equals("FM2")) {
                i4 = 2;
            } else if (str.equals("FM3")) {
                i4 = 3;
            } else if (str.equals("AM")) {
                i4 = 16;
            } else if (str.equals("AM1")) {
                i4 = 17;
            } else if (str.equals("AM2")) {
                i4 = 18;
            } else if (str.equals("AM3")) {
                i4 = 19;
            }
        }
        if (str.equals("FM") || str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            msb = getMsb((int) (Double.parseDouble(str2) * 100.0d));
            lsb = getLsb((int) (Double.parseDouble(str2) * 100.0d));
        } else {
            msb = getMsb((int) Double.parseDouble(str2));
            lsb = getLsb((int) Double.parseDouble(str2));
        }
        getUiMgr(this.mContext).send0xC0RadioInfo(i4, lsb, msb, i);
        if (str.equals("FM") || str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            msb2 = getMsb((int) (Double.parseDouble(str2) * 100.0d));
            lsb2 = getLsb((int) (Double.parseDouble(str2) * 100.0d));
            i3 = 1;
        } else {
            msb2 = getMsb((int) Double.parseDouble(str2));
            lsb2 = getLsb((int) Double.parseDouble(str2));
            i3 = 3;
        }
        getUiMgr(this.mContext).send0x75LCD(1, i3, msb2, lsb2, i, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0xC0Str(7, "Aux Playing!");
        getUiMgr(this.mContext).send0x75LCD(5, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            getUiMgr(this.mContext).send0xC0Str(9, str4);
        } else {
            getUiMgr(this.mContext).send0xC0Str(8, str4);
        }
        getUiMgr(this.mContext).send0x75LCD(4, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            getUiMgr(this.mContext).send0xC0Str(9, "CD Video Playing!");
        } else {
            getUiMgr(this.mContext).send0xC0Str(8, "USB Video Playing!");
        }
        getUiMgr(this.mContext).send0x75LCD(3, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).send0xC0Str(11, "Bluetooth Music Playing!");
        getUiMgr(this.mContext).send0x75LCD(6, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            getUiMgr(this.mContext).send0xC0Str(11, "Media OFF");
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC0PhoneInfo(1, 0, 0, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC0PhoneInfo(3, 0, 0, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC0PhoneInfo(2, 0, 0, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC0PhoneInfo(0, 0, 0, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 41) {
            set0x29EspInfo();
            return;
        }
        if (i == 255) {
            set0xFFVersionInfo();
            return;
        }
        if (i == 82) {
            set0x52TireReset();
            return;
        }
        if (i != 83) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    set0x21AirInfo();
                    break;
                case 34:
                    set0x22RearRadarInfo();
                    break;
                case 35:
                    set0X23FrontRadarInfo();
                    break;
                case 36:
                    set0x24LeftRadarInfo();
                    break;
                case 37:
                    set0x25RightRadarInfo();
                    break;
                case 38:
                    set0x26RearRadarInfo();
                    break;
                case 39:
                    set0X27FrontRadarInfo();
                    break;
                default:
                    switch (i) {
                        case 48:
                            set0x30RightCamera();
                            break;
                        case 49:
                            set0x31PanoraSwitch();
                            break;
                        case 50:
                            set0x32PanoiraInfo();
                            break;
                        case 51:
                            set0x33ParkInfo();
                            break;
                        default:
                            switch (i) {
                                case 56:
                                    set0X38TireInfo();
                                    break;
                                case 57:
                                    set0x39TireAlert();
                                    break;
                                case 58:
                                    set0x3ADoorInfo();
                                    break;
                                case 59:
                                    set0X3BBackLightInfi();
                                    break;
                            }
                    }
            }
            return;
        }
        set0x53EnergyInfo();
    }

    private void set0xFFVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x53EnergyInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting1"), Integer.valueOf(getState2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1)))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting2"), Integer.valueOf(getState2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 1)))));
        int i = this.mCanBusInfoInt[11];
        if (i == 60) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting3"), 0));
        } else if (i == 100) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting3"), 1));
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting4"), Integer.valueOf(getState(this.mCanBusInfoInt[12]))));
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_reservation_charging");
        int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_reservation_charging", "_375_reservation_setting_xuhang");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, sb.append(((iArr[13] * 256) + iArr[14]) * 0.125d).append("km").toString()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        iArr2[10] = 0;
        iArr2[11] = 0;
        iArr2[12] = 0;
        iArr2[13] = 0;
        iArr2[14] = 0;
        if (isNotEnergyChange()) {
            return;
        }
        int[] iArr3 = this.mCanBusInfoInt;
        int i2 = iArr3[5] | (iArr3[2] << 24) | (iArr3[3] << 16) | (iArr3[4] << 8);
        int intFromByteWithBit = getIntFromByteWithBit(i2, 24, 7) + SystemConstant.THREAD_SLEEP_TIME_2000;
        int intFromByteWithBit2 = getIntFromByteWithBit(i2, 20, 4);
        int intFromByteWithBit3 = getIntFromByteWithBit(i2, 12, 5);
        int intFromByteWithBit4 = getIntFromByteWithBit(i2, 7, 5);
        int intFromByteWithBit5 = getIntFromByteWithBit(i2, 1, 6);
        int[] iArr4 = this.mCanBusInfoInt;
        int i3 = iArr4[6] << 24;
        int i4 = iArr4[7];
        int i5 = i3 | (i4 << 16) | (i4 << 8) | iArr4[9];
        new SetAlertView().showDialog(getActivity(), this.mContext.getString(R.string._375_reservation_charging_start) + ":" + intFromByteWithBit + "/" + intFromByteWithBit2 + "/" + intFromByteWithBit3 + " " + intFromByteWithBit4 + ":" + intFromByteWithBit5 + "___" + this.mContext.getString(R.string._375_reservation_charging_end) + ":" + (getIntFromByteWithBit(i5, 25, 7) + SystemConstant.THREAD_SLEEP_TIME_2000) + "/" + getIntFromByteWithBit(i5, 21, 4) + "/" + getIntFromByteWithBit(i5, 13, 5) + " " + getIntFromByteWithBit(i5, 8, 5) + ":" + getIntFromByteWithBit(i5, 2, 6));
    }

    private void set0x52TireReset() {
        if (isNotCarSetting2()) {
            return;
        }
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_375_car_setting");
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 128) {
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting2"), Integer.valueOf(getState())));
        } else {
            switch (i) {
                case 1:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting3"), Integer.valueOf(getState())));
                    break;
                case 2:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting4"), Integer.valueOf(getState())));
                    break;
                case 3:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting5"), Integer.valueOf(getState())));
                    break;
                case 4:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting6"), Integer.valueOf(getState())));
                    break;
                case 5:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting7"), Integer.valueOf(getState())));
                    break;
                case 6:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting8"), Integer.valueOf(getState())));
                    break;
                case 7:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting9"), Integer.valueOf(getState())));
                    break;
                case 8:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting10"), Integer.valueOf(getState())));
                    break;
                case 9:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting11"), Integer.valueOf(getState())));
                    break;
                case 10:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting12"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 11:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting13"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 12:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting14"), Integer.valueOf(getState())));
                    break;
                case 13:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting15"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 14:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting16"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 15:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting17"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 16:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting18"), Integer.valueOf(getState())));
                    break;
                case 17:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting180"), Integer.valueOf(getState())));
                    break;
                case 18:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting19"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 19:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting20"), Integer.valueOf(getState())));
                    break;
                case 20:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting21"), Integer.valueOf(getState())));
                    break;
                case 21:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting22"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 22:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting23"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 23:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting24"), Integer.valueOf(getState())));
                    break;
                case 24:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting25"), Integer.valueOf(getState())));
                    break;
                case 25:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting26"), Integer.valueOf(getState())));
                    break;
                case 26:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting27"), Integer.valueOf(getState())));
                    break;
                case 27:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting28"), Integer.valueOf(getState())));
                    break;
                case 28:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting29"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 29:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting30"), Integer.valueOf(getState())));
                    break;
                case 30:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting31"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 31:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting32"), Integer.valueOf(getState())));
                    break;
                case 32:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting33"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 33:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting34"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 34:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting35"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 35:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting36"), Integer.valueOf(getState())));
                    break;
                case 36:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting37"), Integer.valueOf(getState())));
                    break;
                case 37:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting370"), Integer.valueOf(getState())));
                    break;
                case 38:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting371"), Integer.valueOf(getState())));
                    break;
                case 39:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting372"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 40:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting373"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 41:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting374"), Integer.valueOf(this.mCanBusInfoInt[3] - 10)).setProgress(this.mCanBusInfoInt[3]));
                    break;
                case 42:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting375"), Integer.valueOf(getState())));
                    break;
                case 43:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting38"), Integer.valueOf(getState())));
                    break;
                case 44:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting39"), Integer.valueOf(getState())));
                    break;
                case 45:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting40"), Integer.valueOf(getState())));
                    break;
                case 46:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting41"), Integer.valueOf(getState())));
                    break;
                case 47:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting42"), Integer.valueOf(getState())));
                    break;
                case 48:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting43"), Integer.valueOf(getState())));
                    break;
                case 49:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting44"), Integer.valueOf(getState())));
                    break;
                case 50:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting45"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 51:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting46"), Integer.valueOf(getState())));
                    break;
                case 52:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting47"), Integer.valueOf(getState())));
                    break;
                case 53:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting48"), Integer.valueOf(getState())));
                    break;
                case 54:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting49"), Integer.valueOf(getState())));
                    break;
                case 55:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting50"), Integer.valueOf(getState())));
                    break;
                case 56:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting51"), Integer.valueOf(getState())));
                    break;
                case 57:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting53"), Integer.valueOf(getState())));
                    break;
                case 58:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting54"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 59:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting55"), Integer.valueOf(getState())));
                    break;
                case 60:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting56"), Integer.valueOf(getState())));
                    break;
                case 61:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting57"), Integer.valueOf(getState())));
                    break;
                case 62:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting58"), Integer.valueOf(getState())));
                    break;
                case 63:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting59"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 64:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting60"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 65:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting61"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 66:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting62"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 67:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting63"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 68:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting64"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 69:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting65"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 70:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting66"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 71:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting67"), Integer.valueOf(getState())));
                    break;
                case 72:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting68"), Integer.valueOf(getState())));
                    break;
                case 73:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting69"), Integer.valueOf(getState())));
                    break;
                case 74:
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getRight("_375_car_setting70"), Integer.valueOf(getState())));
                    break;
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private int getState() {
        return this.mCanBusInfoInt[3] == 1 ? 1 : 0;
    }

    private int getRight(String str) {
        return getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_375_car_setting", str);
    }

    private void set0X3BBackLightInfi() {
        int i = this.mCanBusInfoInt[2] / 4;
        if (i == 1) {
            setBacklightLevel(1);
            return;
        }
        if (i == 2) {
            setBacklightLevel(2);
            return;
        }
        if (i == 3) {
            setBacklightLevel(3);
        } else if (i == 4) {
            setBacklightLevel(4);
        } else {
            if (i != 5) {
                return;
            }
            setBacklightLevel(5);
        }
    }

    private void set0x3ADoorInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_375_car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_375_tire_door_info");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[3], iArr[4])).append("Km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        iArr2[3] = 0;
        iArr2[4] = 0;
        if (isNotBasicInfoChange()) {
            return;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            GeneralDoorData.skyWindowOpenLevel = 2;
        } else {
            GeneralDoorData.skyWindowOpenLevel = 0;
        }
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void set0x39TireAlert() {
        List<TireUpdateEntity> list = this.tyreInfoList;
        if (list != null) {
            list.clear();
        }
        GeneralTireData.isHaveSpareTire = false;
        this.arr0[2] = "";
        this.arr1[2] = "";
        this.arr2[2] = "";
        this.arr3[2] = "";
        setItem3Info();
        if (this.mCanBusInfoInt[2] != 0) {
            this.FrontRightAlert = 1;
            this.tyreInfoList.add(new TireUpdateEntity(0, this.FrontRightAlert, this.arr0));
        } else {
            this.FrontRightAlert = 0;
            this.tyreInfoList.add(new TireUpdateEntity(0, this.FrontRightAlert, this.arr0));
            this.arr0[2] = this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert_null);
        }
        if (this.mCanBusInfoInt[3] != 0) {
            this.FrontLeftAlert = 1;
            this.tyreInfoList.add(new TireUpdateEntity(1, this.FrontLeftAlert, this.arr1));
        } else {
            this.FrontLeftAlert = 0;
            this.tyreInfoList.add(new TireUpdateEntity(1, this.FrontLeftAlert, this.arr0));
            this.arr1[2] = this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert_null);
        }
        if (this.mCanBusInfoInt[4] != 0) {
            this.RearRightAlert = 1;
            this.tyreInfoList.add(new TireUpdateEntity(2, this.RearRightAlert, this.arr2));
        } else {
            this.RearRightAlert = 0;
            this.tyreInfoList.add(new TireUpdateEntity(2, this.RearRightAlert, this.arr0));
            this.arr2[2] = this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert_null);
        }
        if (this.mCanBusInfoInt[5] != 0) {
            this.RearLeftAlert = 1;
            this.tyreInfoList.add(new TireUpdateEntity(3, this.RearLeftAlert, this.arr3));
        } else {
            this.RearLeftAlert = 0;
            this.tyreInfoList.add(new TireUpdateEntity(3, this.RearLeftAlert, this.arr0));
            this.arr3[2] = this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert_null);
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void setItem3Info() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert7);
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert6);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert5);
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert4);
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert3);
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert2);
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert1);
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            this.arr0[2] = this.arr0[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert0);
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert7);
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert6);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert5);
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert4);
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert3);
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert2);
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert1);
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            this.arr1[2] = this.arr1[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert0);
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert7);
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert6);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert5);
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert4);
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert3);
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert2);
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert1);
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
            this.arr2[2] = this.arr2[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert0);
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert7);
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert6);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert5);
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert4);
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert3);
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert2);
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert1);
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5])) {
            this.arr3[2] = this.arr3[2] + " || " + this.mContext.getString(R.string._375_tire_alert) + this.mContext.getString(R.string._375_tire_alert0);
        }
    }

    private void set0X38TireInfo() {
        if (isNotTireInfoChange()) {
            return;
        }
        GeneralTireData.isHaveSpareTire = false;
        List<TireUpdateEntity> list = this.tyreInfoList;
        if (list != null) {
            list.clear();
        }
        this.arr0[0] = this.mContext.getString(R.string._375_tire_tem) + (this.mCanBusInfoInt[2] - 40) + getTempUnitC(this.mContext);
        this.arr0[1] = this.mContext.getString(R.string._375_tire_data) + this.df_1Decimal.format((this.mCanBusInfoInt[6] * 0.014d) - 0.1d) + "bar";
        this.arr1[0] = this.mContext.getString(R.string._375_tire_tem) + (this.mCanBusInfoInt[3] - 40) + getTempUnitC(this.mContext);
        this.arr1[1] = this.mContext.getString(R.string._375_tire_data) + this.df_1Decimal.format((this.mCanBusInfoInt[7] * 0.014d) - 0.1d) + "bar";
        this.arr2[0] = this.mContext.getString(R.string._375_tire_tem) + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
        this.arr2[1] = this.mContext.getString(R.string._375_tire_data) + this.df_1Decimal.format((this.mCanBusInfoInt[8] * 0.014d) - 0.1d) + "bar";
        this.arr3[0] = this.mContext.getString(R.string._375_tire_tem) + (this.mCanBusInfoInt[5] - 40) + getTempUnitC(this.mContext);
        this.arr3[1] = this.mContext.getString(R.string._375_tire_data) + this.df_1Decimal.format((this.mCanBusInfoInt[9] * 0.014d) - 0.1d) + "bar";
        this.tyreInfoList.add(new TireUpdateEntity(0, this.FrontRightAlert, this.arr0));
        this.tyreInfoList.add(new TireUpdateEntity(1, this.FrontLeftAlert, this.arr1));
        this.tyreInfoList.add(new TireUpdateEntity(2, this.RearRightAlert, this.arr2));
        this.tyreInfoList.add(new TireUpdateEntity(3, this.RearLeftAlert, this.arr3));
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x33ParkInfo() {
        Context context = this.mContext;
        if (SharePreUtil.getIntValue(context, getUiMgr(context).KEY_AUTO_PARKING_VIEW, 0) == 1) {
            return;
        }
        if (this.view == null) {
            this.view = LayoutInflater.from(getActivity()).inflate(R.layout._375_auto_park, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(getActivity()).setView(this.view).create();
        }
        if (this.content == null) {
            this.content = (TextView) this.view.findViewById(R.id.parking_content);
        }
        if (this.park_1 == null) {
            this.park_1 = (Button) this.view.findViewById(R.id.park_1);
        }
        if (this.park_2 == null) {
            this.park_2 = (Button) this.view.findViewById(R.id.park_2);
        }
        if (this.park_3 == null) {
            this.park_3 = (Button) this.view.findViewById(R.id.park_3);
        }
        if (this.park_4 == null) {
            this.park_4 = (Button) this.view.findViewById(R.id.park_4);
        }
        parkClick(this.park_1, this.park_2, this.park_3, this.park_4);
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            this.park_1.setTextColor(this.mContext.getResources().getColor(R.color.__m6__orange));
            this.park_2.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_3.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_4.setTextColor(this.mContext.getResources().getColor(R.color.white));
        } else if (i == 2) {
            this.park_1.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_2.setTextColor(this.mContext.getResources().getColor(R.color.__m6__orange));
            this.park_3.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_4.setTextColor(this.mContext.getResources().getColor(R.color.white));
        } else if (i == 3) {
            this.park_1.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_2.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_3.setTextColor(this.mContext.getResources().getColor(R.color.__m6__orange));
            this.park_4.setTextColor(this.mContext.getResources().getColor(R.color.white));
        } else if (i == 4) {
            this.park_1.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_2.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_3.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_4.setTextColor(this.mContext.getResources().getColor(R.color.__m6__orange));
        } else {
            this.park_1.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_2.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_3.setTextColor(this.mContext.getResources().getColor(R.color.white));
            this.park_4.setTextColor(this.mContext.getResources().getColor(R.color.white));
        }
        this.content.setText(getParkingStr(this.mCanBusInfoInt[3]));
        this.dialog.setCancelable(true);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        this.dialog.show();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(5000L, 1000L) { // from class: com.hzbhd.canbus.car._375.MsgMgr.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.dialog.dismiss();
            }
        };
        this.countDownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    private void parkClick(Button button, Button button2, Button button3, Button button4) {
        button.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._375.MsgMgr.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MsgMgr.this.parkState == 2) {
                    MsgMgr.this.parkState = 1;
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.getUiMgr(msgMgr.mContext).sendAutoParkingModel(1);
                } else {
                    MsgMgr.this.parkState = 2;
                    MsgMgr msgMgr2 = MsgMgr.this;
                    msgMgr2.getUiMgr(msgMgr2.mContext).sendAutoParkingModel(2);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._375.MsgMgr.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MsgMgr.this.parkState == 3) {
                    MsgMgr.this.parkState = 1;
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.getUiMgr(msgMgr.mContext).sendAutoParkingModel(1);
                } else {
                    MsgMgr.this.parkState = 3;
                    MsgMgr msgMgr2 = MsgMgr.this;
                    msgMgr2.getUiMgr(msgMgr2.mContext).sendAutoParkingModel(3);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._375.MsgMgr.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MsgMgr.this.parkState == 5) {
                    MsgMgr.this.parkState = 1;
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.getUiMgr(msgMgr.mContext).sendAutoParkingModel(1);
                } else {
                    MsgMgr.this.parkState = 5;
                    MsgMgr msgMgr2 = MsgMgr.this;
                    msgMgr2.getUiMgr(msgMgr2.mContext).sendAutoParkingModel(5);
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._375.MsgMgr.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MsgMgr.this.parkState == 4) {
                    MsgMgr.this.parkState = 1;
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.getUiMgr(msgMgr.mContext).sendAutoParkingModel(1);
                } else {
                    MsgMgr.this.parkState = 4;
                    MsgMgr msgMgr2 = MsgMgr.this;
                    msgMgr2.getUiMgr(msgMgr2.mContext).sendAutoParkingModel(4);
                }
            }
        });
    }

    private void set0x32PanoiraInfo() {
        if (isPanoramicInfoChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[2] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[2] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[2] == 3));
            arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[2] == 4));
            arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[2] == 5));
            arrayList.add(new PanoramicBtnUpdateEntity(5, this.mCanBusInfoInt[2] == 6));
            arrayList.add(new PanoramicBtnUpdateEntity(6, this.mCanBusInfoInt[2] == 7));
            arrayList.add(new PanoramicBtnUpdateEntity(7, this.mCanBusInfoInt[2] == 8));
            arrayList.add(new PanoramicBtnUpdateEntity(8, this.mCanBusInfoInt[2] == 9));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x31PanoraSwitch() {
        forceReverse(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
    }

    private void set0x30RightCamera() {
        switchFCamera(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
    }

    private void set0x29EspInfo() {
        if (isTrackInfoChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 5500, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0X27FrontRadarInfo() {
        this.myF_Distance = "Front L:" + this.mCanBusInfoInt[2] + "cm LM:" + this.mCanBusInfoInt[3] + "cm RM:" + this.mCanBusInfoInt[4] + "cm R:" + this.mCanBusInfoInt[5] + "cm";
        GeneralParkData.isShowLeftTopOneDistanceUi = true;
        GeneralParkData.strOnlyOneDistance = this.myF_Distance + "\n" + this.myR_Distance;
        updateParkUi(null, this.mContext);
    }

    private void set0x26RearRadarInfo() {
        this.myR_Distance = "Rear  L:" + this.mCanBusInfoInt[2] + "cm LM:" + this.mCanBusInfoInt[3] + "cm RM:" + this.mCanBusInfoInt[4] + "cm R:" + this.mCanBusInfoInt[5] + "cm";
        GeneralParkData.isShowLeftTopOneDistanceUi = true;
        GeneralParkData.strOnlyOneDistance = this.myF_Distance + "\n" + this.myR_Distance;
        updateParkUi(null, this.mContext);
    }

    private void set0x25RightRadarInfo() {
        if (isNotRightRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRightRadarLocationData(10, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x24LeftRadarInfo() {
        if (isNotLeftRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setLeftRadarLocationData(10, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0X23FrontRadarInfo() {
        if (isNotFrontRadarDataChange()) {
            return;
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(10, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x22RearRadarInfo() {
        if (isNotRearRadarDataChange()) {
            return;
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(10, getRadarData(this.mCanBusInfoInt[2]), getRadarData(this.mCanBusInfoInt[3]), getRadarData(this.mCanBusInfoInt[4]), getRadarData(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x21AirInfo() {
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.rear = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[3];
        if (this.mCanBusInfoInt[3] == 0) {
            GeneralAirData.power = false;
        } else {
            GeneralAirData.power = true;
        }
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
        }
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 0) {
            GeneralAirData.front_left_temperature = "LO";
            GeneralAirData.front_right_temperature = "LO";
        } else if (i2 == 30) {
            GeneralAirData.front_left_temperature = "HI";
            GeneralAirData.front_right_temperature = "HI";
        } else {
            GeneralAirData.front_left_temperature = (((this.mCanBusInfoInt[5] - 1) * 0.5d) + 18.0d) + getTempUnitC(this.mContext);
            GeneralAirData.front_right_temperature = (((this.mCanBusInfoInt[5] - 1) * 0.5d) + 18.0d) + getTempUnitC(this.mContext);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i != 136) {
            switch (i) {
                case 0:
                    buttonKey(0);
                    break;
                case 1:
                    buttonKey(7);
                    break;
                case 2:
                    buttonKey(8);
                    break;
                case 3:
                    buttonKey(45);
                    break;
                case 4:
                    buttonKey(46);
                    break;
                case 5:
                    buttonKey(3);
                    break;
                case 6:
                    buttonKey(2);
                    break;
                case 7:
                    buttonKey(HotKeyConstant.K_1_PICKUP);
                    break;
                case 8:
                    buttonKey(HotKeyConstant.K_2_HANGUP);
                    break;
                case 9:
                    buttonKey(76);
                    break;
                case 10:
                    buttonKey(HotKeyConstant.K_SLEEP);
                    break;
                default:
                    switch (i) {
                        case 17:
                            buttonKey(7);
                            break;
                        case 18:
                            buttonKey(8);
                            break;
                        case 19:
                            buttonKey(52);
                            break;
                        case 20:
                            buttonKey(59);
                            break;
                        case 21:
                            buttonKey(2);
                            break;
                        case 22:
                            buttonKey(62);
                            break;
                        case 23:
                            buttonKey(43);
                            break;
                        case 24:
                            buttonKey(63);
                            break;
                        case 25:
                            buttonKey(HotKeyConstant.K_ACTION_MEDIA);
                            break;
                        case 26:
                            buttonKey(33);
                            break;
                        case 27:
                            buttonKey(34);
                            break;
                        case 28:
                            buttonKey(35);
                            break;
                        case 29:
                            buttonKey(36);
                            break;
                        case 30:
                            buttonKey(37);
                            break;
                        case 31:
                            buttonKey(38);
                            break;
                        default:
                            switch (i) {
                                case 33:
                                    knobKey(7);
                                    break;
                                case 34:
                                    knobKey(8);
                                    break;
                                case 35:
                                    buttonKey(49);
                                    break;
                                case 36:
                                    knobKey(45);
                                    break;
                                case 37:
                                    knobKey(46);
                                    break;
                                case 38:
                                    buttonKey(47);
                                    break;
                                case 39:
                                    buttonKey(48);
                                    break;
                                case 40:
                                    buttonKey(58);
                                    break;
                                case 41:
                                    buttonKey(128);
                                    break;
                                case 42:
                                    buttonKey(HotKeyConstant.K_DISP);
                                    break;
                                case 43:
                                    buttonKey(30);
                                    break;
                                case 44:
                                    buttonKey(39);
                                    break;
                                case 45:
                                    buttonKey(14);
                                    break;
                                case 46:
                                    buttonKey(40);
                                    break;
                                case 47:
                                    buttonKey(41);
                                    break;
                                case 48:
                                    buttonKey(151);
                                    break;
                                case 49:
                                    buttonKey(49);
                                    break;
                                case 50:
                                    buttonKey(HotKeyConstant.K_6_SHUFFLE);
                                    break;
                                case 51:
                                    buttonKey(HotKeyConstant.K_AIR_POWER);
                                    break;
                            }
                    }
            }
        }
        buttonKey(HotKeyConstant.K_SPEECH);
    }

    public void myForceReverse(boolean z) {
        forceReverse(this.mContext, z);
    }

    private String getParkingStr(int i) {
        return CommUtil.getStrByResId(this.mContext, "_375_auto_park" + i);
    }

    private int getRadarData(int i) {
        if (i == 0) {
            MyLog.temporaryTracking("走0");
            return 0;
        }
        if (i == 1) {
            MyLog.temporaryTracking("走10");
            return 10;
        }
        if (i == 2) {
            MyLog.temporaryTracking("走5");
            return 5;
        }
        if (i == 3) {
            MyLog.temporaryTracking("走3");
            return 1;
        }
        MyLog.temporaryTracking("走默认");
        return 0;
    }

    public void setStartTime() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._375.MsgMgr.6
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new SetTimeView().showDialog(MsgMgr.this.getActivity(), MsgMgr.this.mContext.getString(R.string._375_reservation_charging_start), false, false, false, true, true, new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._375.MsgMgr.6.1
                    @Override // com.hzbhd.canbus.util.SetTimeView.TimeResultListener
                    public void result(int i, int i2, int i3, int i4, int i5) {
                        MsgMgr.this.startHour = i4;
                        MsgMgr.this.startMin = i5;
                        MsgMgr.this.updateSettingsStyle0(MsgMgr.this.getUiMgr(MsgMgr.this.mContext).getSettingLeftIndexes(MsgMgr.this.mContext, "_375_reservation_charging"), MsgMgr.this.getUiMgr(MsgMgr.this.mContext).getSettingRightIndex(MsgMgr.this.mContext, "_375_reservation_charging", "_375_reservation_charging_start"), MsgMgr.this.df_2Integer.format(i4) + ":" + MsgMgr.this.df_2Integer.format(i5));
                        if (MsgMgr.this.endHour == -1 || MsgMgr.this.endMin == -1) {
                            Toast.makeText(MsgMgr.this.getActivity(), MsgMgr.this.mContext.getString(R.string._375_reservation_charging_2), 0).show();
                        } else {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).send0x84CarInfo(1, MsgMgr.this.startHour, MsgMgr.this.startMin, MsgMgr.this.endHour, MsgMgr.this.endMin);
                        }
                    }
                });
            }
        });
    }

    public void setEndTime() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._375.MsgMgr.7
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new SetTimeView().showDialog(MsgMgr.this.getActivity(), MsgMgr.this.mContext.getString(R.string._375_reservation_charging_end), false, false, false, true, true, new SetTimeView.TimeResultListener() { // from class: com.hzbhd.canbus.car._375.MsgMgr.7.1
                    @Override // com.hzbhd.canbus.util.SetTimeView.TimeResultListener
                    public void result(int i, int i2, int i3, int i4, int i5) {
                        MsgMgr.this.endHour = i4;
                        MsgMgr.this.endMin = i5;
                        MsgMgr.this.updateSettingsStyle0(MsgMgr.this.getUiMgr(MsgMgr.this.mContext).getSettingLeftIndexes(MsgMgr.this.mContext, "_375_reservation_charging"), MsgMgr.this.getUiMgr(MsgMgr.this.mContext).getSettingRightIndex(MsgMgr.this.mContext, "_375_reservation_charging", "_375_reservation_charging_end"), MsgMgr.this.df_2Integer.format(i4) + ":" + MsgMgr.this.df_2Integer.format(i5));
                        if (MsgMgr.this.startHour == -1 || MsgMgr.this.startMin == -1) {
                            Toast.makeText(MsgMgr.this.getActivity(), MsgMgr.this.mContext.getString(R.string._375_reservation_charging_1), 0).show();
                        } else {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).send0x84CarInfo(1, MsgMgr.this.startHour, MsgMgr.this.startMin, MsgMgr.this.endHour, MsgMgr.this.endMin);
                        }
                    }
                });
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
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick3(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettingsStyle0(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, str).setValueStr(true));
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

    private void adjustBrightness() {
        int brightness = FutureUtil.instance.getBrightness();
        if (brightness == 5) {
            FutureUtil.instance.setBrightness(0);
        } else {
            FutureUtil.instance.setBrightness(brightness + 1);
        }
    }

    public void panoramicSwitch(boolean z) {
        forceReverse(this.mContext, z);
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

    private boolean isNotLeftRadarDataChange() {
        if (Arrays.equals(this.mLeftRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mLeftRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isNotRightRadarDataChange() {
        if (Arrays.equals(this.mRightData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRightData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isNotFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isNotRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isNotBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isNotTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isNotCarSetting2() {
        if (Arrays.equals(this.mTireInfo2, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo2 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isNotEnergyChange() {
        if (this.energyTag == 0) {
            this.energyTag = 1;
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 0 && iArr[3] == 0 && iArr[4] == 15 && iArr[5] == 254) {
            return true;
        }
        if ((iArr[6] == 0 && iArr[7] == 0 && iArr[8] == 31 && iArr[9] == 252) || Arrays.equals(this.mEnergyInfo, iArr)) {
            return true;
        }
        int[] iArr2 = this.mCanBusInfoInt;
        this.mEnergyInfo = Arrays.copyOf(iArr2, iArr2.length);
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

    public void toast(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._375.MsgMgr.8
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.getActivity(), str, 0);
            }
        });
    }
}
