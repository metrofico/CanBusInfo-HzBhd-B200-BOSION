package com.hzbhd.canbus.car._250;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    protected static final String CAN_250_SAVE_RADAR_DISP = "__250_SAVE_RADAR_DISP";
    public static int m0x27SettingData = 0;
    private static int mDifferentId = 0;
    protected static boolean mIs360Full = false;
    private static boolean mIsBackLast = false;
    private static boolean mIsBelt = false;
    private static boolean mIsFLDoorLast = false;
    private static boolean mIsFRDoorLast = false;
    private static boolean mIsFrontLast = false;
    private static boolean mIsLeftEable = true;
    private static boolean mIsRLDoorLast = false;
    private static boolean mIsRRDoorLast = false;
    private static boolean mIsRightEable = true;
    private static boolean mIsSubBelt = false;
    protected static int mLast360st;
    private static int mSkyLineSt;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private UiMgr mUiMgr;

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private String getStringByIndex(boolean z) {
        return z ? "english_on" : "english_off";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        initSettingsItem(getUiMgr(context).getSettingUiSet(context));
        initDriveItem(getUiMgr(context).getDriverDataPageUiSet(context));
    }

    /* JADX WARN: Type inference failed for: r4v4, types: [com.hzbhd.canbus.car._250.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        mDifferentId = getCurrentCanDifferentId();
        int currentCanDifferentId = getCurrentCanDifferentId();
        if (currentCanDifferentId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
        } else if (currentCanDifferentId == 3) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
        } else if (currentCanDifferentId == 4) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
        } else if (currentCanDifferentId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 8});
        } else if (currentCanDifferentId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 9});
        }
        new Thread() { // from class: com.hzbhd.canbus.car._250.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        sleep(1500L);
                        CanbusMsgSender.sendMsg(new byte[]{22, -112, 125, 10});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 125, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            keyControl0x20();
            return;
        }
        if (i == 33) {
            keyControl0x21();
            return;
        }
        if (i == 48) {
            setTrack();
            return;
        }
        if (i == 125) {
            setCarInfo_0x7d();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i == 54) {
            setAirData0x36();
            return;
        }
        if (i == 55) {
            setPanoramic_0x37();
            return;
        }
        if (i == 64) {
            SetEnergyinformation0x40();
            return;
        }
        if (i != 65) {
            switch (i) {
                case 35:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x23();
                        break;
                    }
                    break;
                case 36:
                    setRearRadar0x24();
                    break;
                case 37:
                    setRearRadar0x25();
                    break;
                case 38:
                    setFrontRadar0x26();
                    break;
                case 39:
                    setSetting0x27();
                    break;
                case 40:
                    setDoorData0x28();
                    break;
                case 41:
                    original_car_set_0x29();
                    profiles_0x29();
                    and_ambient_lighting_brightness_meter_0x29();
                    power_tailgate_0x29();
                    vehicle_settings_0x29();
                    personalized_instrument_settings_0x29();
                    SettingData8_0x29();
                    SettingData9_0x29();
                    break;
            }
            return;
        }
        SetEnergysavingdriving();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) (i - 2000), (byte) i3, (byte) i4, (byte) i8, (byte) i6, (byte) i7});
    }

    private void setSetting0x27() {
        m0x27SettingData = this.mCanBusInfoInt[2];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "str_250_0_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "str_250_0_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTrack() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5600, 16);
        LogUtil.showLog("trackAngle:" + GeneralParkData.trackAngle);
        updateParkUi(null, this.mContext);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void keyControl0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 8) {
            realKeyClick(50);
            return;
        }
        switch (i) {
            case 11:
                realKeyClick(206);
                break;
            case 12:
                realKeyClick(HotKeyConstant.K_NEXT_HANGUP);
                break;
            case 13:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
        }
    }

    private void keyControl0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 9) {
            if (getCurrentCanDifferentId() == 3 || getCurrentCanDifferentId() == 4) {
                realKeyClick(152);
                return;
            } else {
                realKeyClick(14);
                return;
            }
        }
        if (i == 10) {
            if (getCurrentCanDifferentId() == 3 || getCurrentCanDifferentId() == 4) {
                realKeyClick(14);
                return;
            } else {
                realKeyClick(15);
                return;
            }
        }
        if (i == 47) {
            realKeyClick(4);
            return;
        }
        if (i != 48) {
            switch (i) {
                case 32:
                    realKeyClick(30);
                    break;
                case 33:
                    realKeyClick(129);
                    break;
                case 34:
                    realKeyClick(75);
                    break;
                case 35:
                    realKeyClick(21);
                    break;
                case 36:
                    realKeyClick(20);
                    break;
                default:
                    switch (i) {
                        case 43:
                            realKeyClick(7);
                            break;
                        case 44:
                            realKeyClick(8);
                            break;
                        case 45:
                            realKeyClick(HotKeyConstant.K_SLEEP);
                            break;
                        default:
                            switch (i) {
                                case 50:
                                    realKeyClick(68);
                                    break;
                                case 51:
                                    realKeyClick(59);
                                    break;
                                case 52:
                                    realKeyClick(50);
                                    break;
                                case 53:
                                    realKeyClick(52);
                                    break;
                                case 54:
                                    realKeyClick(39);
                                    break;
                                case 55:
                                    realKeyClick(128);
                                    break;
                                case 56:
                                    realKeyClick(40);
                                    break;
                                case 57:
                                    realKeyClick(152);
                                    break;
                            }
                    }
            }
            return;
        }
        realKeyClick(58);
    }

    private String getBytesString(byte[] bArr) {
        int length = bArr.length - 3;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i + 3];
        }
        return new String(bArr2);
    }

    void initRadarDisp(Context context) {
        int intValue = SharePreUtil.getIntValue(context, CAN_250_SAVE_RADAR_DISP, 0);
        ArrayList arrayList = new ArrayList();
        int i = mDifferentId;
        if (i == 4) {
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(intValue)));
        } else if (i == 5 || i == 7) {
            arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(intValue)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    boolean isHaveCam360() {
        int i = mDifferentId;
        return i == 4 || i == 5 || i == 7;
    }

    private void setPanoramic_0x37() {
        if (isHaveCam360()) {
            boolean z = this.mCanBusInfoInt[2] != 0;
            forceReverse(this.mContext, z);
            if (z) {
                ArrayList arrayList = new ArrayList();
                int i = mDifferentId;
                if (i == 5) {
                    int i2 = this.mCanBusInfoInt[2];
                    mIs360Full = i2 == 5 || i2 == 6 || i2 == 7 || i2 == 8;
                    arrayList.add(new PanoramicBtnUpdateEntity(0, mIs360Full));
                    int i3 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(1, i3 == 1 || i3 == 5));
                    int i4 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(2, i4 == 4 || i4 == 8));
                    int i5 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(3, i5 == 2 || i5 == 6));
                    int i6 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(4, i6 == 3 || i6 == 7));
                } else if (i == 7) {
                    int i7 = this.mCanBusInfoInt[2];
                    mIs360Full = i7 == 5 || i7 == 6 || i7 == 7 || i7 == 8;
                    arrayList.add(new PanoramicBtnUpdateEntity(0, mIs360Full));
                    int i8 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(1, i8 == 1 || i8 == 5));
                    int i9 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(2, i9 == 4 || i9 == 8));
                    int i10 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(3, i10 == 2 || i10 == 6));
                    int i11 = this.mCanBusInfoInt[2];
                    arrayList.add(new PanoramicBtnUpdateEntity(4, i11 == 3 || i11 == 7));
                    arrayList.add(new PanoramicBtnUpdateEntity(6, this.mCanBusInfoInt[2] == 17));
                    arrayList.add(new PanoramicBtnUpdateEntity(7, this.mCanBusInfoInt[2] == 18));
                    arrayList.add(new PanoramicBtnUpdateEntity(8, this.mCanBusInfoInt[2] == 19));
                    arrayList.add(new PanoramicBtnUpdateEntity(9, this.mCanBusInfoInt[2] == 20));
                    arrayList.add(new PanoramicBtnUpdateEntity(10, this.mCanBusInfoInt[2] == 21));
                    arrayList.add(new PanoramicBtnUpdateEntity(11, this.mCanBusInfoInt[2] == 22));
                    arrayList.add(new PanoramicBtnUpdateEntity(12, this.mCanBusInfoInt[2] == 23));
                    arrayList.add(new PanoramicBtnUpdateEntity(13, this.mCanBusInfoInt[2] == 24));
                } else {
                    arrayList = null;
                }
                GeneralParkData.dataList = arrayList;
                updateParkUi(null, this.mContext);
                mLast360st = this.mCanBusInfoInt[2];
            }
        }
    }

    void updateDashBoardSetEable_0x29() {
        ArrayList arrayList = new ArrayList();
        mIsLeftEable = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) != 0;
        mIsRightEable = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) != 0;
        arrayList.add(new SettingUpdateEntity(2, 0, null).setEnable(mIsLeftEable));
        arrayList.add(new SettingUpdateEntity(2, 1, null).setEnable(mIsRightEable));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarInfo_0x7d() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            String bytesString = getBytesString(this.mCanBusInfoByte);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(2, 0, bytesString));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
        if (i == 3) {
            ArrayList arrayList2 = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 0, sb.append(((iArr2[4] * 256) + iArr2[3]) / 100).append(" KM/H").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 1, sb2.append(((iArr3[6] * 256) + iArr3[5]) / 100).append(" KM/H").toString()));
            updateGeneralDriveData(arrayList2);
            updateDriveDataActivity(null);
            return;
        }
        if (i == 4) {
            ArrayList arrayList3 = new ArrayList();
            StringBuilder sb3 = new StringBuilder();
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(0, 0, sb3.append((iArr4[5] * 256 * 256) + (iArr4[4] * 256) + iArr4[3]).append(" KM").toString()));
            StringBuilder sb4 = new StringBuilder();
            int[] iArr5 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(0, 1, sb4.append(((iArr5[7] * 256) + iArr5[6]) / 10).append(" KM").toString()));
            StringBuilder sb5 = new StringBuilder();
            int[] iArr6 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(0, 2, sb5.append(((((iArr6[10] * 256) * 256) + (iArr6[9] * 256)) + iArr6[8]) / 10).append(" KM").toString()));
            StringBuilder sb6 = new StringBuilder();
            int[] iArr7 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(0, 3, sb6.append(((((iArr7[13] * 256) * 256) + (iArr7[12] * 256)) + iArr7[11]) / 10).append(" KM").toString()));
            updateGeneralDriveData(arrayList3);
            updateDriveDataActivity(null);
            return;
        }
        if (i == 6) {
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSubShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            if (mIsBelt != GeneralDoorData.isSeatBeltTie || mIsSubBelt != GeneralDoorData.isSubSeatBeltTie) {
                updateDoorView(this.mContext);
            }
            mIsBelt = GeneralDoorData.isSeatBeltTie;
            mIsSubBelt = GeneralDoorData.isSubSeatBeltTie;
            return;
        }
        if (i == 7) {
            ArrayList arrayList4 = new ArrayList();
            StringBuilder sb7 = new StringBuilder();
            int[] iArr8 = this.mCanBusInfoInt;
            arrayList4.add(new DriverUpdateEntity(0, 4, sb7.append(((iArr8[4] * 256) + iArr8[3]) / 10).append(" L/100KM").toString()));
            StringBuilder sb8 = new StringBuilder();
            int[] iArr9 = this.mCanBusInfoInt;
            arrayList4.add(new DriverUpdateEntity(0, 5, sb8.append(((iArr9[6] * 256) + iArr9[5]) / 10).append("  L/100KM").toString()));
            updateGeneralDriveData(arrayList4);
            updateDriveDataActivity(null);
            return;
        }
        switch (i) {
            case 9:
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(new DriverUpdateEntity(2, 1, (this.mCanBusInfoInt[3] - 40) + " ℃"));
                arrayList5.add(new DriverUpdateEntity(2, 2, (this.mCanBusInfoInt[4] - 40) + " ℃"));
                arrayList5.add(new DriverUpdateEntity(2, 3, (this.mCanBusInfoInt[5] - 40) + " ℃"));
                updateGeneralDriveData(arrayList5);
                updateDriveDataActivity(null);
                break;
            case 10:
                ArrayList arrayList6 = new ArrayList();
                StringBuilder sb9 = new StringBuilder();
                int[] iArr10 = this.mCanBusInfoInt;
                arrayList6.add(new DriverUpdateEntity(2, 4, sb9.append(((iArr10[4] * 256) + iArr10[3]) / 4).append(" rpm").toString()));
                updateGeneralDriveData(arrayList6);
                updateDriveDataActivity(null);
                break;
            case 11:
                ArrayList arrayList7 = new ArrayList();
                StringBuilder sb10 = new StringBuilder();
                int[] iArr11 = this.mCanBusInfoInt;
                arrayList7.add(new DriverUpdateEntity(2, 5, sb10.append(((iArr11[4] * 256) + iArr11[3]) / 1000).append(" V").toString()));
                updateGeneralDriveData(arrayList7);
                updateDriveDataActivity(null);
                break;
            case 12:
                ArrayList arrayList8 = new ArrayList();
                StringBuilder sb11 = new StringBuilder();
                int[] iArr12 = this.mCanBusInfoInt;
                arrayList8.add(new DriverUpdateEntity(3, 0, sb11.append((iArr12[4] * 256) + iArr12[3]).append("").toString()));
                StringBuilder sb12 = new StringBuilder();
                int[] iArr13 = this.mCanBusInfoInt;
                arrayList8.add(new DriverUpdateEntity(3, 1, sb12.append((iArr13[6] * 256) + iArr13[5]).append("").toString()));
                StringBuilder sb13 = new StringBuilder();
                int[] iArr14 = this.mCanBusInfoInt;
                arrayList8.add(new DriverUpdateEntity(3, 2, sb13.append((iArr14[8] * 256) + iArr14[7]).append("").toString()));
                StringBuilder sb14 = new StringBuilder();
                int[] iArr15 = this.mCanBusInfoInt;
                arrayList8.add(new DriverUpdateEntity(3, 3, sb14.append((iArr15[10] * 256) + iArr15[9]).append("").toString()));
                StringBuilder sb15 = new StringBuilder();
                int[] iArr16 = this.mCanBusInfoInt;
                arrayList8.add(new DriverUpdateEntity(3, 4, sb15.append((iArr16[12] * 256) + iArr16[11]).append("").toString()));
                updateGeneralDriveData(arrayList8);
                updateDriveDataActivity(null);
                break;
            case 13:
                int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[3], 6, 2);
                if (intFromByteWithBit == 1) {
                    GeneralDoorData.skyWindowOpenLevel = 2;
                } else if (intFromByteWithBit != 2 && intFromByteWithBit == 3) {
                    GeneralDoorData.skyWindowOpenLevel = 1;
                } else {
                    GeneralDoorData.skyWindowOpenLevel = 0;
                }
                if (mSkyLineSt != GeneralDoorData.skyWindowOpenLevel) {
                    updateDoorView(this.mContext);
                }
                mSkyLineSt = GeneralDoorData.skyWindowOpenLevel;
                break;
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen) {
            updateDoorView(this.mContext);
        }
        mIsFLDoorLast = GeneralDoorData.isLeftFrontDoorOpen;
        mIsFRDoorLast = GeneralDoorData.isRightFrontDoorOpen;
        mIsRLDoorLast = GeneralDoorData.isLeftRearDoorOpen;
        mIsRRDoorLast = GeneralDoorData.isRightRearDoorOpen;
        mIsFrontLast = GeneralDoorData.isFrontOpen;
        mIsBackLast = GeneralDoorData.isBackOpen;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 6, this.mCanBusInfoInt[3] + " %"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.center_wheel = this.mCanBusInfoInt[6] == 255 ? "PM2.5: --" : "PM2.5: " + this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        int i = this.mCanBusInfoInt[3];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirData0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = "-" + intFromByteWithBit;
        } else {
            str = intFromByteWithBit + "";
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTemp(int i) {
        return 64 == i ? "LO" : 96 == i ? "HI" : (65 > i || 95 < i) ? "" : (((i - 64) * 0.5f) + 16.0f) + getTempUnitC(this.mContext);
    }

    private void setRearRadar0x24() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        RadarInfoUtil.setRearRadarLocationDataType2(4, i, 5, i2, 5, i2, 4, iArr[4]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        updateParkUi(null, this.mContext);
    }

    private void setRearRadar0x25() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationDataType2(4, iArr[2], 5, iArr[3], 5, iArr[4], 4, iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar0x26() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationDataType2(4, iArr[2], 5, iArr[3], 5, iArr[4], 4, iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        updateParkUi(null, this.mContext);
    }

    private void original_car_set_0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void profiles_0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
        arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void personalized_instrument_settings_0x29() {
        int intFromByteWithBit;
        int intFromByteWithBit2;
        ArrayList arrayList = new ArrayList();
        if (mIsLeftEable) {
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) >= 1) {
                intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) - 1;
            } else {
                intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
            }
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(intFromByteWithBit2)));
        }
        if (mIsRightEable) {
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) >= 1) {
                intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) - 1;
            } else {
                intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
            }
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(intFromByteWithBit)));
        }
        if (mIsRightEable || mIsLeftEable) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void and_ambient_lighting_brightness_meter_0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) >= 1 ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) - 1 : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3))));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4))));
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) >= 1 ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) - 1 : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void power_tailgate_0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
        arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 3) >= 1 ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 3) - 1 : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 3))));
        arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void vehicle_settings_0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2))));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
        arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
        arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void SettingData8_0x29() {
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 3);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 3);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2);
        arrayList.add(checkEntity(helperSetValue("_250_theme_change", Integer.valueOf(intFromByteWithBit))));
        arrayList.add(checkEntity(helperSetValue("_250_Language", Integer.valueOf(intFromByteWithBit2))));
        arrayList.add(checkEntity(helperSetValue("_250_Combination_meter_style_settings", Integer.valueOf(intFromByteWithBit3 - 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void SettingData9_0x29() {
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1);
        boolean z = intFromByteWithBit == 1;
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 1);
        arrayList.add(checkEntity(helperSetValue("_250_Lane_Keeping_Assist_System", Integer.valueOf(intFromByteWithBit))));
        arrayList.add(checkEntity(helperSetValue("_250_Lane_Keeping_Assist_System_Assist_Mode", Integer.valueOf(intFromByteWithBit2), z)));
        arrayList.add(checkEntity(helperSetValue("_250_Traffic_sign_recognition", Integer.valueOf(intFromByteWithBit3))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void SetEnergysavingdriving() {
        ArrayList arrayList = new ArrayList();
        String str = "numb_" + this.mCanBusInfoInt[2];
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue(str, resolveEnergysaving(iArr[2], iArr[3], iArr[4], iArr[5], iArr[6], iArr[7], iArr[8], iArr[9], iArr[10], iArr[11], iArr[12]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveEnergysaving(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        if (i == 0) {
            return "20" + Zea0(i2) + "." + Zea0(i3) + "." + Zea0(i4) + "\b\b\b" + Zea0(i5) + ":" + Zea0(i6) + "\n" + CommUtil.getStrByResId(this.mContext, "_250_Energy_saving_level") + ":\b" + i7 + "\n" + CommUtil.getStrByResId(this.mContext, "_250_mileage") + ":\b" + Zea00(getMsbLsbResult(i8, i9) / 10.0f) + "km\n" + CommUtil.getStrByResId(this.mContext, "_250_consumption") + ":\b" + Zea00(getMsbLsbResult(i10, i11) / 10.0f) + "kwh/100km";
        }
        return "20" + Zea0(i2) + "." + Zea0(i3) + "." + Zea0(i4) + "\b\b\b" + Zea0(i5) + ":" + Zea0(i6) + "\n" + CommUtil.getStrByResId(this.mContext, "_250_Energy_saving_level") + ":\b" + i7 + "\b\b\b" + CommUtil.getStrByResId(this.mContext, "_250_mileage") + ":\b" + Zea00(getMsbLsbResult(i8, i9) / 10.0f) + "km\b\b\b" + CommUtil.getStrByResId(this.mContext, "_250_consumption") + ":\b" + Zea00(getMsbLsbResult(i10, i11) / 10.0f) + "kwh/100km";
    }

    private String Zea0(int i) {
        return new DecimalFormat("00").format(i);
    }

    private String Zea00(float f) {
        return new DecimalFormat("0.0").format(f);
    }

    private void SetEnergyinformation0x40() {
        ArrayList arrayList = new ArrayList();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_Motor_Power", resplveMotoPower(this.mCanBusInfoInt[2]))));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_Other_system_power", this.mCanBusInfoInt[3] + " kw")));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_Average_energy_consumption", sb.append(decimalFormat.format(getMsbLsbResult(iArr[4], iArr[5]) / 10.0f)).append("kwh/100km").toString())));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_Remaining_journey", sb2.append(getMsbLsbResult(iArr2[6], iArr2[7])).append("km").toString())));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_battery_power", this.mCanBusInfoInt[8] + "%")));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_Last_charge_value", this.mCanBusInfoInt[9] + "%")));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("_250_Driving_after_the_most_recent_charge_mileage", sb3.append(decimalFormat.format(getMsbLsbResult(iArr3[10], iArr3[11]) / 10.0f)).append("km").toString())));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resplveMotoPower(int i) {
        return (i - 80) + "kw";
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        this.mSettingItemHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                this.mSettingItemHashMap.put(itemListBean.getTitleSrn(), new SettingUpdateHelper(new SettingUpdateEntity(i, i2, "null_value"), itemListBean.getMin()));
            }
        }
    }

    private void initDriveItem(DriverDataPageUiSet driverDataPageUiSet) {
        this.mDriveItemHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = driverDataPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemHashMap.put(itemList.get(i2).getTitleSrn(), new DriveDataUpdateHelper(new DriverUpdateEntity(i, i2, "null_value")));
            }
        }
    }

    private SettingUpdateEntity checkEntity(SettingUpdateEntity settingUpdateEntity) {
        if (settingUpdateEntity.getLeftListIndex() == -1 || settingUpdateEntity.getRightListIndex() == -1) {
            return null;
        }
        return settingUpdateEntity;
    }

    private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity driverUpdateEntity) {
        if (driverUpdateEntity.getPage() == -1 || driverUpdateEntity.getIndex() == -1) {
            return null;
        }
        return driverUpdateEntity;
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj);
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj, boolean z) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value").setEnable(false)));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj).setEnable(z);
    }

    private DriverUpdateEntity helperSetDriveDataValue(String str, String str2) {
        if (!this.mDriveItemHashMap.containsKey(str)) {
            this.mDriveItemHashMap.put(str, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
        }
        return this.mDriveItemHashMap.get(str).setValue(str2);
    }

    private static class DriveDataUpdateHelper {
        private DriverUpdateEntity entity;

        public DriveDataUpdateHelper(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public void setEntity(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public DriverUpdateEntity getEntity() {
            return this.entity;
        }

        public DriverUpdateEntity setValue(String str) {
            this.entity.setValue(str);
            return this.entity;
        }
    }

    private static class SettingUpdateHelper {
        private SettingUpdateEntity entity;
        private int progressMin;

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity) {
            this(settingUpdateEntity, 0);
        }

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity, int i) {
            this.entity = settingUpdateEntity;
            this.progressMin = i;
        }

        public SettingUpdateEntity getEntity() {
            return this.entity;
        }

        public SettingUpdateEntity setValue(Object obj) {
            if (obj instanceof Integer) {
                Integer num = (Integer) obj;
                this.entity.setValue(Integer.valueOf(num.intValue() + this.progressMin));
                this.entity.setProgress(num.intValue());
            } else {
                this.entity.setValue(obj);
            }
            return this.entity;
        }

        public SettingUpdateEntity setEnable(boolean z) {
            this.entity.setEnable(z);
            return this.entity;
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
