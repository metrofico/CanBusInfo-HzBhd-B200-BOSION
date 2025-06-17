package com.hzbhd.canbus.car._306;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import androidx.core.view.MotionEventCompat;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.util.ArrayList;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    private static final String TAG = "_306_MsgMgr";
    public static int hour;
    public static int language;
    private static String mLanguage;
    public static int minute;
    private final String PATH_VOICE_306 = "voice/_306";
    private byte carInfoData0 = 0;
    private boolean carInfoFirst = false;
    private LanguageReceiver languageReceiver;
    private SparseArray<String> mAutoParkVoiceArray;
    private AutoParkVoiceManger mAutoParkVoiceManger;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private MyPanoramicView mPanoramicView;

    private int getLeftAndRightRadar(int i) {
        if (i == 1) {
            return 2;
        }
        return i == 2 ? 4 : 0;
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
        this.mContext.unregisterReceiver(this.languageReceiver);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 36, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 37, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 40, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 50, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 51, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 53, 0});
        this.languageReceiver = new LanguageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        context.registerReceiver(this.languageReceiver, intentFilter);
        initAutoParkVoice();
        setLanguage(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws IllegalStateException, IllegalArgumentException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 40) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            airInfo0x28();
            return;
        }
        if (i == 41) {
            trackInfo0x29();
            return;
        }
        if (i != 48) {
            switch (i) {
                case 32:
                    keyEvent0x20();
                    break;
                case 33:
                    panelEvent0x21();
                    break;
                case 34:
                    rearRadar0x22();
                    break;
                case 35:
                    frontRadar0x23();
                    break;
                case 36:
                    carInfo0x24();
                    break;
                case 37:
                    tpmsInfo0x25();
                    break;
                default:
                    switch (i) {
                        case 51:
                            carSettingInfo0x33();
                            break;
                        case 52:
                            carSettingInfo0x34();
                            break;
                        case 53:
                            set360camera0x35();
                            break;
                        case 54:
                            voice0x36();
                            break;
                        case 55:
                            set0x37AutoParkVoice(context);
                            break;
                    }
            }
            return;
        }
        setVersionInfo();
    }

    private void voice0x36() {
        if (this.mCanBusInfoInt[2] == 1) {
            enterAuxIn2();
        } else {
            exitAuxIn2();
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -124, 0});
    }

    private void carSettingInfo0x33() {
        Context context;
        int i;
        int[] iArr = this.mCanBusInfoInt;
        float f = (float) (((iArr[2] * 256) + iArr[3]) * 0.1d);
        int i2 = (iArr[4] * 256) + iArr[5];
        int i3 = (iArr[6] * 256) + iArr[7];
        int i4 = iArr[8];
        short s = (short) (((iArr[9] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (iArr[10] & 255));
        int i5 = (iArr[11] * 256) + iArr[12];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, f + "L/100km"));
        arrayList.add(new DriverUpdateEntity(0, 1, i2 + "KM"));
        arrayList.add(new DriverUpdateEntity(0, 2, i3 + "KM"));
        arrayList.add(new DriverUpdateEntity(0, 3, i4 + "%"));
        arrayList.add(new DriverUpdateEntity(0, 4, i5 + "KM"));
        arrayList.add(new DriverUpdateEntity(0, 5, ((float) (iArr[13] * 0.1d)) + "V"));
        arrayList.add(new DriverUpdateEntity(0, 6, ((int) s) + "°"));
        if (this.mCanBusInfoInt[14] == 1) {
            context = this.mContext;
            i = R.string._306_value_31;
        } else {
            context = this.mContext;
            i = R.string._306_value_32;
        }
        arrayList.add(new DriverUpdateEntity(0, 7, context.getString(i)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        List<TireUpdateEntity> list = GeneralTireData.dataList;
        if (list != null) {
            int i6 = this.mCanBusInfoInt[14];
            list.get(0).setTireStatus(i6);
            list.get(1).setTireStatus(i6);
            list.get(2).setTireStatus(i6);
            list.get(3).setTireStatus(i6);
            GeneralTireData.dataList = list;
            updateTirePressureActivity(null);
        }
    }

    private void set360camera0x35() {
        getMyPanoramicView().refreshUi(this.mCanBusInfoInt[2]);
    }

    private void carSettingInfo0x34() {
        ArrayList arrayList = new ArrayList();
        if (!isDX7()) {
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        } else {
            arrayList.add(new SettingUpdateEntity(0, isDX7() ? 0 : 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
            arrayList.add(new SettingUpdateEntity(0, isDX7() ? 1 : 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(0, isDX7() ? 2 : 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(0, isDX7() ? 3 : 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))));
            arrayList.add(new SettingUpdateEntity(0, isDX7() ? 4 : 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
            int i = this.mCanBusInfoInt[3];
            if (i >= 1 && i <= 3) {
                arrayList.add(new SettingUpdateEntity(0, isDX7() ? 5 : 8, Integer.valueOf(i - 1)));
            }
            int i2 = this.mCanBusInfoInt[4];
            if (i2 >= 0 && i2 <= 1) {
                arrayList.add(new SettingUpdateEntity(0, isDX7() ? 6 : 9, Integer.valueOf(i2)));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void trackInfo0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, -540, 16);
        updateParkUi(null, this.mContext);
    }

    private void airInfo0x28() {
        GeneralAirData.dual = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[3];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void tpmsInfo0x25() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new TireUpdateEntity(0, 0, new String[]{sb.append(iArr[3] + (iArr[2] * 256)).append("KPA").toString()}));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new TireUpdateEntity(1, 0, new String[]{sb2.append(iArr2[5] + (iArr2[4] * 256)).append("KPA").toString()}));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new TireUpdateEntity(2, 0, new String[]{sb3.append(iArr3[7] + (iArr3[6] * 256)).append("KPA").toString()}));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new TireUpdateEntity(3, 0, new String[]{sb4.append(iArr4[9] + (iArr4[8] * 256)).append("KPA").toString()}));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private void carInfo0x24() {
        if (this.carInfoData0 != this.mCanBusInfoByte[2]) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            if (this.carInfoFirst) {
                updateDoorView(this.mContext);
            } else {
                this.carInfoFirst = true;
            }
            this.carInfoData0 = this.mCanBusInfoByte[2];
        }
    }

    private void rearRadar0x22() {
        int leftAndRightRadar = getLeftAndRightRadar(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, leftAndRightRadar, iArr[3], iArr[4], getLeftAndRightRadar(iArr[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void frontRadar0x23() {
        int leftAndRightRadar = getLeftAndRightRadar(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, leftAndRightRadar, iArr[3], iArr[4], getLeftAndRightRadar(iArr[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void panelEvent0x21() {
        int i = this.mCanBusInfoInt[2];
        switch (i) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(52);
                break;
            case 2:
            case 4:
                realKeyClick(128);
                break;
            case 3:
                realKeyClick(59);
                break;
            case 5:
                realKeyClick(50);
                break;
            case 6:
                startActivity(Constant.CanBusMainActivity);
                break;
            case 7:
                realKeyClick(49);
                break;
            case 8:
                realKeyClick(47);
                break;
            case 9:
                realKeyClick(48);
                break;
            default:
                switch (i) {
                    case 19:
                        realKeyClick(45);
                        break;
                    case 20:
                        realKeyClick(46);
                        break;
                    case 21:
                        realKeyClick(HotKeyConstant.K_SLEEP);
                        break;
                    case 22:
                        realKeyClick(8);
                        break;
                    case 23:
                        realKeyClick(7);
                        break;
                    case 24:
                        realKeyClick(HotKeyConstant.K_SPEECH);
                        break;
                }
        }
    }

    private void keyEvent0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(7);
                break;
            case 2:
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(45);
                break;
            case 4:
                realKeyClick(46);
                break;
            case 6:
                realKeyClick(3);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 8:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick(68);
                break;
            case 10:
                realKeyClick(15);
                break;
        }
    }

    private void set0x37AutoParkVoice(Context context) {
        if (TextUtils.isEmpty(mLanguage) || TextUtils.isEmpty(this.mAutoParkVoiceArray.get(this.mCanBusInfoInt[2]))) {
            return;
        }
        String str = "voice/_306" + mLanguage + this.mAutoParkVoiceArray.get(this.mCanBusInfoInt[2]);
        Log.i(TAG, "set0x37AutoParkVoice: fileName \"" + str + "\"");
        try {
            this.mAutoParkVoiceManger.play(context, str);
        } catch (Throwable w) {
            w.printStackTrace();
        }
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 31 == i ? "HI" : (((i - 1) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
    }

    private void startActivity(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mContext.startActivity(intent);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        hour = i5;
        minute = i6;
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) i5, (byte) i6, (byte) LanguageReceiver.getLanguage(this.mContext)});
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private MyPanoramicView getMyPanoramicView() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        return this.mPanoramicView;
    }

    private void initAutoParkVoice() {
        this.mAutoParkVoiceManger = new AutoParkVoiceManger();
        SparseArray<String> sparseArray = new SparseArray<>();
        this.mAutoParkVoiceArray = sparseArray;
        sparseArray.put(10, "_0x0a.mp3");
        this.mAutoParkVoiceArray.put(11, "_0x0b.mp3");
        this.mAutoParkVoiceArray.put(12, "_0x0c.mp3");
        this.mAutoParkVoiceArray.put(13, "_0x0d.mp3");
        this.mAutoParkVoiceArray.put(14, "_0x0e.mp3");
        this.mAutoParkVoiceArray.put(15, "_0x0f.mp3");
        this.mAutoParkVoiceArray.put(1, "_0x01_0x02.mp3");
        this.mAutoParkVoiceArray.put(2, "_0x01_0x02.mp3");
        this.mAutoParkVoiceArray.put(3, "_0x03_0x04.mp3");
        this.mAutoParkVoiceArray.put(4, "_0x03_0x04.mp3");
        this.mAutoParkVoiceArray.put(5, "_0x05_0x06.mp3");
        this.mAutoParkVoiceArray.put(6, "_0x05_0x06.mp3");
        this.mAutoParkVoiceArray.put(7, "_0x07_0x08.mp3");
        this.mAutoParkVoiceArray.put(8, "_0x07_0x08.mp3");
        this.mAutoParkVoiceArray.put(9, "_0x09.mp3");
        this.mAutoParkVoiceArray.put(16, "_0x10.mp3");
        this.mAutoParkVoiceArray.put(17, "_0x11.mp3");
        this.mAutoParkVoiceArray.put(18, "_0x12.mp3");
        this.mAutoParkVoiceArray.put(19, "_0x13.mp3");
        this.mAutoParkVoiceArray.put(20, "_0x14.mp3");
        this.mAutoParkVoiceArray.put(21, "_0x15.mp3");
        this.mAutoParkVoiceArray.put(22, "_0x16.mp3");
        this.mAutoParkVoiceArray.put(23, "_0x17.mp3");
    }

    static void setLanguage(Context context) {
        String country = context.getResources().getConfiguration().getLocales().get(0).getCountry();
        Log.i(TAG, "setLanguage: country " + country);
        if (country.endsWith("CN")) {
            mLanguage = "_zh";
        } else {
            mLanguage = "_en";
        }
        Log.i(TAG, "setLanguage: mLanguage " + mLanguage);
    }

    private boolean isDX7() {
        return getUiMgr().isDX7();
    }

    private UiMgr getUiMgr() {
        return (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
    }
}
