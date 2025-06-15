package com.hzbhd.canbus.car._176;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private static final String DEVICE_STATUS_AUX = "AUX";
    private static final String DEVICE_STATUS_CD = "CD";
    private static final String DEVICE_STATUS_POWER_OFF = "Power Off";
    private static final String DEVICE_STATUS_POWER_ON = "Power On";
    private static final String DEVICE_STATUS_RADIO = "RADIO";
    private static final String DEVICE_STATUS_TV = "TV";
    static int ONE = 1;
    static int WIND_LEVEL_HIGH = 8;
    static int WIND_LEVEL_LOW = 1;
    private boolean isInComeBtPhone;
    private int[] m0x10DataNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwcInfoCopy;
    private Context mContext;
    private boolean mIsBackOpen;
    private boolean mIsBackOpenNow;
    private boolean mIsFrontLeftOpen;
    private boolean mIsFrontLeftOpenNow;
    private boolean mIsFrontOpen;
    private boolean mIsFrontOpenNow;
    private boolean mIsFrontRightOpen;
    private boolean mIsFrontRightOpenNow;
    private boolean mIsRearLeftOpen;
    private boolean mIsRearLeftOpenNow;
    private boolean mIsRearRightOpen;
    private boolean mIsRearRightOpenNow;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    int[] mPanoramicInfo;
    private UiMgr mUiMgr;
    private String mDeviceStatusNow = "";
    private int data0x90 = 0;
    private int data0x91 = 0;
    private int data0x92 = 0;
    private int data0x93 = 0;
    private int data0xA0 = 0;
    private int data0xA1 = 0;
    private int data0xA2 = 0;
    private int data0xA3 = 0;
    private int data0xA4 = 0;
    private String SHARE_176_DATA_0X90 = "data0x90";
    private String SHARE_176_DATA_0X91 = "data0x91";
    private String SHARE_176_DATA_0X92 = "data0x92";
    private String SHARE_176_DATA_0X93 = "data0x93";
    private String SHARE_176_DATA_0XA0 = "data0xa0";
    private String SHARE_176_DATA_0XA1 = "data0xa1";
    private String SHARE_176_DATA_0XA2 = "data0xa2";
    private String SHARE_176_DATA_0XA3 = "data0xa3";
    private String SHARE_176_DATA_0XA4 = "data0xa4";
    private String SHARE_176_LANGUAGE = "176_Language";
    private int mCallStatus = 1;

    private String getDeviceWorkModle(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : DEVICE_STATUS_TV : DEVICE_STATUS_AUX : DEVICE_STATUS_CD : DEVICE_STATUS_RADIO;
    }

    private String getPowerStatus(boolean z) {
        return z ? DEVICE_STATUS_POWER_ON : DEVICE_STATUS_POWER_OFF;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        int currentEachCanId = getCurrentEachCanId();
        if (currentEachCanId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 0});
        } else if (currentEachCanId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 1});
        } else if (currentEachCanId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 16, 2});
        }
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 113, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 114, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, -127, 0});
        initAmplifierData();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        if (getCurrentCanDifferentId() == 3) {
            SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        } else {
            SelectCanTypeUtil.disableApp(context, Constant.OriginalDeviceActivity);
        }
    }

    /* JADX WARN: Type inference failed for: r0v21, types: [com.hzbhd.canbus.car._176.MsgMgr$1] */
    private void initAmplifierData() {
        int intValue = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_LANGUAGE, 0);
        if (getCurrentCanDifferentId() == 11 || getCurrentCanDifferentId() == 12 || intValue <= 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, (byte) intValue});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED, 0});
        }
        this.data0x90 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X90, 0);
        this.data0x91 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X91, 0);
        this.data0x92 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X92, 0);
        this.data0x93 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0X93, 0);
        this.data0xA0 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA0, 0);
        this.data0xA1 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA1, 0);
        this.data0xA2 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA2, 0);
        this.data0xA3 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA3, 0);
        this.data0xA4 = SharePreUtil.getIntValue(this.mContext, this.SHARE_176_DATA_0XA4, 0);
        new Thread() { // from class: com.hzbhd.canbus.car._176.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -112, (byte) MsgMgr.this.data0x90});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -111, (byte) (MsgMgr.this.data0x91 + MsgMgr.ONE)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -110, (byte) (MsgMgr.this.data0x92 + MsgMgr.ONE)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -109, (byte) (MsgMgr.this.data0x93 + MsgMgr.ONE)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, (byte) MsgMgr.this.data0xA0});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, (byte) MsgMgr.this.data0xA1});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, (byte) MsgMgr.this.data0xA2});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte) MsgMgr.this.data0xA3});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, (byte) MsgMgr.this.data0xA4});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 10) {
            setVolumeInfo0x0A();
            return;
        }
        if (i == 16) {
            setOriginalCarWorkModle0x10();
            return;
        }
        if (i == 40) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setCarMessage0x28();
            return;
        }
        if (i == 48) {
            setTrackInfo();
            return;
        }
        if (i == 64) {
            set0x40ModeInfo();
            return;
        }
        if (i == 97) {
            setTireData0x61();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i == 145) {
            setSOS0x91();
            return;
        }
        if (i == 160) {
            setPanelButton0xA0();
            return;
        }
        if (i == 61) {
            set0x3DWheelKeyInfo();
            return;
        }
        if (i == 62) {
            set0x3EWheelInfo();
            return;
        }
        if (i == 129) {
            setCarData0x81();
            return;
        }
        if (i != 130) {
            switch (i) {
                case 32:
                    setAccused0x20();
                    break;
                case 33:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x21();
                        break;
                    }
                    break;
                case 34:
                    setRadarInfo0x22();
                    break;
                case 35:
                    setFrontRadarInfo0x23();
                    break;
                case 36:
                    setLeftRadarInfo0x24();
                    break;
                case 37:
                    setRightRadarInfo0x25();
                    break;
                default:
                    switch (i) {
                        case 113:
                            setCarSetting0x71();
                            break;
                        case 114:
                            setCarSetting0x72();
                            break;
                        case 115:
                            settingInfo0x73();
                            break;
                        case 116:
                            setPanel360Info();
                            break;
                    }
            }
            return;
        }
        setDriveInfo0x82();
    }

    private void set0x40ModeInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            GeneralDisplayMsgData.displayMsg = this.mContext.getString(R.string._176_add_function54);
            sendDisplayMsgView(this.mContext);
        } else if (i == 1) {
            GeneralDisplayMsgData.displayMsg = this.mContext.getString(R.string._176_add_function55);
            sendDisplayMsgView(this.mContext);
        } else if (i == 2) {
            GeneralDisplayMsgData.displayMsg = this.mContext.getString(R.string._176_add_function56);
            sendDisplayMsgView(this.mContext);
        }
    }

    private void setDriveInfo0x82() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
        int drivingItemIndexes = getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function50");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) / 10.0f).append("kWh/100Km").toString()));
        int drivingPageIndexes2 = getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
        int drivingItemIndexes2 = getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function51");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(DataHandleUtils.getMsbLsbResult(iArr2[4], iArr2[5]) / 10.0f).append("kWh").toString()));
        int drivingPageIndexes3 = getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
        int drivingItemIndexes3 = getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function52");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]) / 10.0f).append("kWh").toString()));
        int drivingPageIndexes4 = getUiMgr().getDrivingPageIndexes(this.mContext, "_176_car_setting_title_43");
        int drivingItemIndexes4 = getUiMgr().getDrivingItemIndexes(this.mContext, "_176_add_function53");
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb4.append(DataHandleUtils.getMsbLsbResult(iArr4[8], iArr4[9]) / 10.0f).append("kWh").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setPanel360Info() {
        if (isPanoramicInfoChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[2] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[2] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[2] == 3));
            arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[2] == 4));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x3EWheelInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2) == 1) {
                rearKey3(8);
                return;
            } else {
                rearKey3(7);
                return;
            }
        }
        if (i == 1) {
            realKeyClick4(33);
            return;
        }
        if (i == 2) {
            realKeyClick4(34);
            return;
        }
        if (i == 16) {
            realKeyClick4(35);
            return;
        }
        if (i == 20) {
            realKeyClick4(36);
            return;
        }
        if (i == 32) {
            realKeyClick4(37);
            return;
        }
        if (i == 36) {
            realKeyClick4(38);
            return;
        }
        if (i == 48) {
            realKeyClick4(42);
        } else if (i == 64) {
            realKeyClick4(44);
        } else {
            if (i != 68) {
                return;
            }
            realKeyClick4(144);
        }
    }

    private void rearKey3(int i) {
        realKeyClick3(this.mContext, i, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 6));
    }

    private void set0x3DWheelKeyInfo() {
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7)) {
            case 0:
                realKeyClick4(39);
                break;
            case 1:
                realKeyClick4(50);
                break;
            case 2:
                realKeyClick4(40);
                break;
            case 3:
                realKeyClick4(41);
                break;
            case 4:
                realKeyClick4(151);
                break;
            case 5:
                realKeyClick4(133);
                break;
            case 6:
                realKeyClick4(3);
                break;
            case 7:
                realKeyClick4(52);
                break;
            case 8:
                realKeyClick4(128);
                break;
        }
    }

    private void settingInfo0x73() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_0"), Integer.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_1"), Integer.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_2"), Integer.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_3"), Integer.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_4"), Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_5"), Integer.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_setting_0"), getUiMgr().getRightIndex(this.mContext, "_176_setting_0", "_176_setting_0_6"), Integer.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function33"), Integer.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function36"), Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function39"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function43"), Integer.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function44"), Integer.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getLeftIndexes(this.mContext, "_176_add_function"), getUiMgr().getRightIndex(this.mContext, "_176_add_function", "_176_add_function45"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2) - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAccused0x20() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i != 143) {
            switch (i) {
                case 6:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_MUTE_PHONE_ON_OUT, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 18:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                            break;
                        case 19:
                            realKeyLongClick1(this.mContext, 45, iArr[3]);
                            break;
                        case 20:
                            realKeyLongClick1(this.mContext, 46, iArr[3]);
                            break;
                        case 21:
                            realKeyLongClick1(this.mContext, 50, iArr[3]);
                            break;
                        case 22:
                            realKeyLongClick1(this.mContext, 49, iArr[3]);
                            break;
                    }
            }
            return;
        }
        realKeyClick4(14);
    }

    private void setPanelButton0xA0() {
        int i = this.mCanBusInfoInt[2];
        if (i == 5) {
            realKeyClick4(8);
            return;
        }
        if (i == 6) {
            realKeyClick4(7);
            return;
        }
        if (i == 64) {
            realKeyClick4(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 128) {
            realKeyClick4(52);
            return;
        }
        if (i != 130) {
            if (i != 131) {
                return;
            }
            realKeyClick4(152);
        } else {
            Intent intent = new Intent(this.mContext, (Class<?>) SettingActivity.class);
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        }
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setVolumeInfo0x0A() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        iArr[2] = blockBit(iArr[2], 7);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
        int i = intFromByteWithBit >> 4;
        int i2 = intFromByteWithBit & 15;
        String str = boolBit7 ? Integer.toString(i) + Integer.toString(i2) : "";
        if ((i > 9 || i2 > 9) && boolBit7) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(getOriginalCarDevicePageUiSet().getItems().size() - 1, str));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
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

    private void setOriginalCarWorkModle0x10() {
        if (is0x10DataChange()) {
            GeneralOriginalCarDeviceData.runningState = getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
            GeneralOriginalCarDeviceData.cdStatus = getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
            GeneralOriginalCarDeviceData.discStatus = getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3));
            updateOriginalCarDeviceActivity(null);
            if (DEVICE_STATUS_POWER_OFF.equals(GeneralOriginalCarDeviceData.cdStatus)) {
                cleanDevice();
            } else if (isDeviceStatusSame(DEVICE_STATUS_AUX)) {
                exitAuxIn2();
                setOriginalOtherPage();
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if ((SourceConstantsDef.SOURCE_ID.ATV.name().equals(str) || SourceConstantsDef.SOURCE_ID.AUX1.name().equals(str) || SourceConstantsDef.SOURCE_ID.BTAUDIO.name().equals(str) || SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str) || SourceConstantsDef.SOURCE_ID.MPEG.name().equals(str) || SourceConstantsDef.SOURCE_ID.DTV.name().equals(str) || SourceConstantsDef.SOURCE_ID.MUSIC.name().equals(str) || SourceConstantsDef.SOURCE_ID.FM.name().equals(str) || SourceConstantsDef.SOURCE_ID.VIDEO.name().equals(str) || SourceConstantsDef.SOURCE_ID.NAVIAUDIO.name().equals(str)) && getCurrentCanDifferentId() == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    private void setSOS0x91() {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 1}, new byte[]{31, 33, 36, 39, 32, 37, 35, 33, 35, 30, 34}));
    }

    private void setCarData0x81() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getData(2) + getOilUnit()));
        arrayList.add(new DriverUpdateEntity(0, 1, getData(4) + getSpeedUnit()));
        arrayList.add(new DriverUpdateEntity(0, 2, getDataDistance(6) + getDistanceUnit()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]));
    }

    private String getOilUnit() {
        int intFromByteWithBit;
        int i = this.mCanBusInfoInt[9];
        return (i == 255 || (intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 4, 3)) == 0) ? "L/100KM" : intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? intFromByteWithBit != 3 ? intFromByteWithBit != 4 ? intFromByteWithBit != 5 ? "" : "MPKWH" : "KWH/100KM" : "MPGUK" : "MPGUS" : "KM/L";
    }

    private String getSpeedUnit() {
        int intFromByteWithBit;
        int i = this.mCanBusInfoInt[9];
        return (i == 255 || (intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 2, 2)) == 0) ? "KM/H" : intFromByteWithBit != 1 ? "" : "MILES/H";
    }

    private String getDistanceUnit() {
        int intFromByteWithBit;
        int i = this.mCanBusInfoInt[9];
        return (i == 255 || (intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, 2, 2)) == 0) ? "KM" : intFromByteWithBit != 1 ? "" : "MILES";
    }

    private String getDataDistance(int i) {
        return i == 65535 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Float.valueOf((((DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[i + 2], 0, 1) * 256) * 256) + getIdFromCanbusInfo(i)) / 10.0f));
    }

    private String getData(int i) {
        return i == 65535 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Float.valueOf(getIdFromCanbusInfo(i) / 10.0f));
    }

    private int getIdFromCanbusInfo(int i) {
        int[] iArr = this.mCanBusInfoInt;
        return (iArr[i + 1] & 255) | ((iArr[i] & 255) << 8);
    }

    private void setCarSetting0x72() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        switch (i) {
            case 144:
                this.data0x90 = iArr[3];
                break;
            case 145:
                this.data0x91 = iArr[3];
                break;
            case 146:
                this.data0x92 = iArr[3];
                break;
            case 147:
                this.data0x93 = iArr[3];
                break;
            default:
                switch (i) {
                    case 160:
                        this.data0xA0 = iArr[3];
                        break;
                    case MpegConstantsDef.MPEG_INFO_DISCTYPE_CFM /* 161 */:
                        this.data0xA1 = iArr[3];
                        break;
                    case MpegConstantsDef.MPEG_INFO_AUDIO_CFM /* 162 */:
                        this.data0xA2 = iArr[3];
                        break;
                    case MpegConstantsDef.MPEG_INFO_SUBTITLE_CFM /* 163 */:
                        this.data0xA3 = iArr[3];
                        break;
                    case MpegConstantsDef.MPEG_INFO_ANGLE_CFM /* 164 */:
                        this.data0xA4 = iArr[3];
                        break;
                }
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(this.data0x90)));
        int i2 = this.data0x91;
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(i2 == 0 ? 0 : i2 - ONE)));
        int i3 = this.data0x92;
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(i3 == 0 ? 0 : i3 - ONE)));
        int i4 = this.data0x93;
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(i4 == 0 ? 0 : i4 - ONE)));
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(this.data0xA0)));
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(this.data0xA1)));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(this.data0xA2)).setProgress(this.data0xA2));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(this.data0xA3)).setProgress(this.data0xA3));
        arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(this.data0xA4)).setProgress(this.data0xA4));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X90, this.data0x90);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X91, this.data0x91);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X92, this.data0x92);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0X93, this.data0x93);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA0, this.data0xA0);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA1, this.data0xA1);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA2, this.data0xA2);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA3, this.data0xA3);
        SharePreUtil.setIntValue(this.mContext, this.SHARE_176_DATA_0XA4, this.data0xA4);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0387  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setCarSetting0x71() {
        /*
            Method dump skipped, instructions count: 1274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._176.MsgMgr.setCarSetting0x71():void");
    }

    private Object getColor(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 6) {
            return 1;
        }
        if (i == 7) {
            return 2;
        }
        return 3;
    }

    private void addLanguage(List<SettingUpdateEntity> list, int i) {
        int i2 = i >= 38 ? i - 4 : i >= 23 ? i - 3 : i >= 4 ? i - 2 : i;
        if (i == 4 || i == 5 || i == 23 || i == 38) {
            return;
        }
        list.add(new SettingUpdateEntity(0, 14, Integer.valueOf(i2)));
    }

    private void setTireData0x61() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getTireEntity(0, getTirePressure(this.mCanBusInfoInt[3])));
        arrayList.add(getTireEntity(1, getTirePressure(this.mCanBusInfoInt[4])));
        arrayList.add(getTireEntity(2, getTirePressure(this.mCanBusInfoInt[5])));
        arrayList.add(getTireEntity(3, getTirePressure(this.mCanBusInfoInt[6])));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
        String tisWarmMsg = getTisWarmMsg(this.mCanBusInfoInt[2]);
        if (TextUtils.isEmpty(tisWarmMsg)) {
            return;
        }
        GeneralDisplayMsgData.displayMsg = tisWarmMsg;
        sendDisplayMsgView(this.mContext);
    }

    private void setCarMessage0x28() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = boolBit7;
        this.mIsFrontLeftOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
        this.mIsFrontRightOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = boolBit5;
        this.mIsRearLeftOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = boolBit4;
        this.mIsRearRightOpen = boolBit4;
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = boolBit3;
        this.mIsBackOpen = boolBit3;
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = boolBit2;
        this.mIsFrontOpen = boolBit2;
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private boolean isDoorDataChange() {
        boolean z = this.mIsFrontLeftOpen;
        if (z == this.mIsFrontLeftOpenNow && this.mIsFrontRightOpen == this.mIsFrontRightOpenNow && this.mIsRearLeftOpen == this.mIsRearLeftOpenNow && this.mIsRearRightOpen == this.mIsRearRightOpenNow && this.mIsBackOpen == this.mIsBackOpenNow && this.mIsFrontOpen == this.mIsFrontOpenNow) {
            return false;
        }
        this.mIsFrontLeftOpenNow = z;
        this.mIsFrontRightOpenNow = this.mIsFrontRightOpen;
        this.mIsRearLeftOpenNow = this.mIsRearLeftOpen;
        this.mIsRearRightOpenNow = this.mIsRearRightOpen;
        this.mIsBackOpenNow = this.mIsBackOpen;
        this.mIsFrontOpenNow = this.mIsFrontOpen;
        return true;
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[3];
        if (i == 255) {
            return "--" + this.mContext.getString(R.string.str_temp_c_unit);
        }
        return i == 254 ? "" : (this.mCanBusInfoInt[3] - 40) + this.mContext.getString(R.string.str_temp_c_unit);
    }

    private void setRightRadarInfo0x25() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRightRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setLeftRadarInfo0x24() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x23() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRadarInfo0x22() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[3], bArr[2], 0, 5400, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x21() {
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.soft = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.fast = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.normal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[4];
        GeneralAirData.front_wind_level = i;
        GeneralAirData.front_auto_wind_speed = i == 15;
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.contains("FM")) {
            i3 = (int) (Float.parseFloat(str2) * 100.0f);
        } else {
            i3 = str.contains("AM") ? Integer.parseInt(str2) : 0;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, getAllBandTypeData(str), (byte) (i3 % 256), (byte) (i3 / 256), (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = i3 + 1;
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, (byte) (i2 / 256), (byte) (i2 % 256), (byte) (i4 / 256), (byte) (i4 % 256), b3, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, -1, -1, -1, -1, -1, -1, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 33, (byte) i4, (byte) i5, (byte) i2, (byte) (i / 3600), (byte) ((i / 60) % 60), (byte) (i % 60)});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 16, (byte) (i3 / 256), (byte) (i3 % 256), (byte) (i5 / 256), (byte) (i5 % 256), (byte) (i / 3600), (byte) ((i / 60) % 60), (byte) (i % 60)});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        byte b;
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        this.mCallStatus = i;
        if (i == 1) {
            this.isInComeBtPhone = true;
            b = 1;
        } else if (i == 2) {
            this.isInComeBtPhone = false;
            b = 3;
        } else if (i != 4) {
            b = 0;
        } else {
            b = this.isInComeBtPhone ? (byte) 2 : (byte) 4;
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, b, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        this.mCallStatus = 1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        this.mCallStatus = 2;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        int i = this.mCallStatus;
        if (i == 1) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 2, 1}, bArr));
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 4, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 1}, bArr));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:4:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte getAllBandTypeData(java.lang.String r7) {
        /*
            r6 = this;
            r7.hashCode()
            int r0 = r7.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = -1
            switch(r0) {
                case 2092: goto L5f;
                case 2247: goto L54;
                case 64901: goto L49;
                case 64902: goto L3e;
                case 64903: goto L33;
                case 69706: goto L28;
                case 69707: goto L1d;
                case 69708: goto L12;
                default: goto Lf;
            }
        Lf:
            r7 = r5
            goto L69
        L12:
            java.lang.String r0 = "FM3"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L1b
            goto Lf
        L1b:
            r7 = 7
            goto L69
        L1d:
            java.lang.String r0 = "FM2"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L26
            goto Lf
        L26:
            r7 = 6
            goto L69
        L28:
            java.lang.String r0 = "FM1"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L31
            goto Lf
        L31:
            r7 = 5
            goto L69
        L33:
            java.lang.String r0 = "AM3"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L3c
            goto Lf
        L3c:
            r7 = 4
            goto L69
        L3e:
            java.lang.String r0 = "AM2"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L47
            goto Lf
        L47:
            r7 = r1
            goto L69
        L49:
            java.lang.String r0 = "AM1"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L52
            goto Lf
        L52:
            r7 = r2
            goto L69
        L54:
            java.lang.String r0 = "FM"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L5d
            goto Lf
        L5d:
            r7 = r3
            goto L69
        L5f:
            java.lang.String r0 = "AM"
            boolean r7 = r7.equals(r0)
            if (r7 != 0) goto L68
            goto Lf
        L68:
            r7 = r4
        L69:
            switch(r7) {
                case 0: goto L7a;
                case 1: goto L79;
                case 2: goto L76;
                case 3: goto L73;
                case 4: goto L70;
                case 5: goto L6f;
                case 6: goto L6e;
                case 7: goto L6d;
                default: goto L6c;
            }
        L6c:
            return r5
        L6d:
            return r1
        L6e:
            return r2
        L6f:
            return r3
        L70:
            r7 = 19
            return r7
        L73:
            r7 = 18
            return r7
        L76:
            r7 = 17
            return r7
        L79:
            return r4
        L7a:
            r7 = 16
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._176.MsgMgr.getAllBandTypeData(java.lang.String):byte");
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "" : 254 == i ? "LO" : 255 == i ? "HI" : (i < 32 || i > 64) ? "" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private TireUpdateEntity getTireEntity(int i, String str) {
        return new TireUpdateEntity(i, 0, new String[]{str});
    }

    private String getTirePressure(int i) {
        return i == 255 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Float.valueOf(i * 0.03f)) + this.mContext.getString(R.string.pressure_unit);
    }

    private String getTisWarmMsg(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = R.string._176_tire_info_1;
                break;
            case 2:
                i2 = R.string._176_tire_info_2;
                break;
            case 3:
                i2 = R.string._176_tire_info_3;
                break;
            case 4:
                i2 = R.string._176_tire_info_4;
                break;
            case 5:
                i2 = R.string._176_tire_info_5;
                break;
            case 6:
                i2 = R.string._176_tire_info_6;
                break;
            default:
                i2 = 0;
                break;
        }
        return i2 == 0 ? "" : this.mContext.getString(i2);
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet() {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr().getOriginalCarDevicePageUiSet(this.mContext);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private UiMgr getUiMgr() {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgr;
    }

    private void showOriginalDevice() {
        Intent intent = new Intent();
        intent.setComponent(Constant.OriginalDeviceActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mContext.startActivity(intent);
    }

    private void setOriginalRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.RDS, OriginalBtnAction.SCANE, OriginalBtnAction.ST, OriginalBtnAction.AUTO_P}, false);
    }

    private void setOriginalCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_artist", "_186_play_modle", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_play_time", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FOLDER, OriginalBtnAction.WMA, OriginalBtnAction.MP3, OriginalBtnAction.SCANE}, true);
    }

    private void setOriginalOtherPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, null, false);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, boolean z) {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet();
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        originalCarDevicePageUiSet.setHaveSongList(z);
        updateOriginalDeviceWithInit();
        cleanDevice();
    }

    private void updateOriginalDeviceWithInit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private boolean isDeviceStatusSame(String str) {
        if (!str.equals(GeneralOriginalCarDeviceData.discStatus) || str.equals(this.mDeviceStatusNow)) {
            return false;
        }
        this.mDeviceStatusNow = str;
        return true;
    }

    private void cleanDevice() {
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.folder = false;
        GeneralOriginalCarDeviceData.wma = false;
        GeneralOriginalCarDeviceData.mp3 = false;
        GeneralOriginalCarDeviceData.scane = false;
        GeneralOriginalCarDeviceData.rds = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.auto_p = false;
        GeneralOriginalCarDeviceData.mList = null;
        Iterator<OriginalCarDevicePageUiSet.Item> it = getOriginalCarDevicePageUiSet().getItems().iterator();
        while (it.hasNext()) {
            it.next().setValue("");
        }
        updateOriginalCarDeviceActivity(null);
    }

    private boolean is0x10DataChange() {
        if (Arrays.equals(this.m0x10DataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x10DataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    public void toast(String str) {
        Toast.makeText(getActivity(), str, 0).show();
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
