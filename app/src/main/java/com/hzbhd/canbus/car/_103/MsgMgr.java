package com.hzbhd.canbus.car._103;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final String SHARE_103_AMPLIFIER_BALANCE = "share_103_amplifier_balance";
    private static final String SHARE_103_AMPLIFIER_BASS = "share_103_amplifier_bass";
    private static final String SHARE_103_AMPLIFIER_FADE = "share_103_amplifier_fade";
    private static final String SHARE_103_AMPLIFIER_MIDDLE = "share_103_amplifier_middle";
    private static final String SHARE_103_AMPLIFIER_TREBLE = "share_103_amplifier_treble";
    private static final String SHARE_103_AMPLIFIER_VOLUME = "share_103_amplifier_volume";
    static final int _103_AMPLIFIER_BAND_MAX = 2;
    static final int _103_AMPLIFIER_HALF_MAX = 11;
    static final int _103_AMPLIFIER_PUNCH_MAX = 5;
    private int[] mAmplifierDataNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private byte[] saveAirData;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_FRONT_OPEN = "is_front_open";

    private int againstInt(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i;
        }
        return 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 23, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        initAmplifierData(context);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.hzbhd.canbus.car._103.MsgMgr$1] */
    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_103_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_103_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_103_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_103_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_103_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_103_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        }
        new Thread() { // from class: com.hzbhd.canbus.car._103.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 11)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 11)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 2)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 2)});
                    sleep(50L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 2)});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            setDriveData0x16();
            return;
        }
        if (i == 23) {
            setSettingData0x17();
            return;
        }
        if (i == 41) {
            setTrackInfo0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 64) {
            setCarSetting0x40();
            return;
        }
        if (i != 203) {
            switch (i) {
                case 32:
                    setKeyControl0x20();
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
                    setFrontInfo0x23();
                    break;
                case 36:
                    if (!isDoorMsgRepeat(bArr)) {
                        setDoorData0x24();
                        break;
                    }
                    break;
            }
            return;
        }
        setCarTypeState0xCB();
    }

    private void setCarState0xd0() {
        int i = this.mCanBusInfoInt[3];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 26, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSetting0x40() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 3);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3);
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1);
        int intFromByteWithBit15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1);
        int intFromByteWithBit16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1);
        int intFromByteWithBit17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 2);
        int intFromByteWithBit18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1);
        int intFromByteWithBit19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        int intFromByteWithBit20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1);
        int intFromByteWithBit21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1);
        int intFromByteWithBit22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2);
        int intFromByteWithBit23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
        int intFromByteWithBit24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1);
        int intFromByteWithBit25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2);
        int intFromByteWithBit26 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(intFromByteWithBit2)));
        arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(intFromByteWithBit3)));
        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(intFromByteWithBit4)));
        arrayList.add(new SettingUpdateEntity(2, 5, Integer.valueOf(intFromByteWithBit5)));
        arrayList.add(new SettingUpdateEntity(2, 6, Integer.valueOf(intFromByteWithBit6)));
        arrayList.add(new SettingUpdateEntity(2, 7, Integer.valueOf(intFromByteWithBit7)));
        arrayList.add(new SettingUpdateEntity(2, 8, Integer.valueOf(intFromByteWithBit8)));
        arrayList.add(new SettingUpdateEntity(2, 9, Integer.valueOf(intFromByteWithBit9)));
        arrayList.add(new SettingUpdateEntity(2, 10, Integer.valueOf(intFromByteWithBit10)));
        arrayList.add(new SettingUpdateEntity(2, 11, Integer.valueOf(intFromByteWithBit11)));
        arrayList.add(new SettingUpdateEntity(2, 12, Integer.valueOf(intFromByteWithBit12)));
        arrayList.add(new SettingUpdateEntity(2, 13, Integer.valueOf(intFromByteWithBit13)));
        arrayList.add(new SettingUpdateEntity(2, 14, Integer.valueOf(intFromByteWithBit14)));
        arrayList.add(new SettingUpdateEntity(2, 15, Integer.valueOf(intFromByteWithBit15)));
        arrayList.add(new SettingUpdateEntity(2, 16, Integer.valueOf(intFromByteWithBit16)));
        arrayList.add(new SettingUpdateEntity(2, 17, Integer.valueOf(intFromByteWithBit17)));
        arrayList.add(new SettingUpdateEntity(2, 18, Integer.valueOf(intFromByteWithBit18)));
        arrayList.add(new SettingUpdateEntity(2, 19, Integer.valueOf(intFromByteWithBit19)));
        arrayList.add(new SettingUpdateEntity(2, 20, Integer.valueOf(intFromByteWithBit20)));
        arrayList.add(new SettingUpdateEntity(2, 21, Integer.valueOf(intFromByteWithBit21)));
        arrayList.add(new SettingUpdateEntity(2, 22, Integer.valueOf(intFromByteWithBit22)));
        arrayList.add(new SettingUpdateEntity(2, 23, Integer.valueOf(intFromByteWithBit23)));
        arrayList.add(new SettingUpdateEntity(2, 24, Integer.valueOf(intFromByteWithBit24)));
        arrayList.add(new SettingUpdateEntity(2, 25, Integer.valueOf(intFromByteWithBit25)));
        arrayList.add(new SettingUpdateEntity(2, 26, Integer.valueOf(intFromByteWithBit26)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarTypeState0xCB() {
        int i = this.mCanBusInfoInt[2];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTrackInfo0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4944, 16);
        updateParkUi(null, this.mContext);
    }

    private void setFrontInfo0x23() {
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.setFrontRadarLocationData(4, againstInt(this.mCanBusInfoInt[2]), againstInt(this.mCanBusInfoInt[3]), againstInt(this.mCanBusInfoInt[4]), againstInt(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRadarInfo0x22() {
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.setRearRadarLocationData(4, againstInt(this.mCanBusInfoInt[2]), againstInt(this.mCanBusInfoInt[3]), againstInt(this.mCanBusInfoInt[4]), againstInt(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setKeyControl0x20() {
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
                realKeyClick(46);
                break;
            case 4:
                realKeyClick(45);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 8:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick(14);
                break;
            case 10:
                realKeyClick(15);
                break;
        }
    }

    private void realKeyClick(int i) {
        realKeyClick2(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    private void setSettingData0x17() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        int i2 = iArr[10];
        int i3 = iArr[11];
        int i4 = iArr[12];
        int i5 = iArr[13];
        int i6 = iArr[14];
        int i7 = iArr[15];
        int i8 = iArr[8];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i2)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(i3)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(i4)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(i5)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(i6)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(i7)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(i)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(i8 - 5)).setProgress(i8 - 2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        GeneralAmplifierData.frontRear = 11 - this.mCanBusInfoInt[2];
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 11;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[9];
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 2;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[6] - 2;
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7] - 2;
        if (isAmplifierDataChange(new int[]{GeneralAmplifierData.leftRight, GeneralAmplifierData.frontRear, GeneralAmplifierData.bandBass, GeneralAmplifierData.bandMiddle, GeneralAmplifierData.bandTreble, GeneralAmplifierData.volume})) {
            updateAmplifierActivity(null);
        }
        SharePreUtil.setIntValue(this.mContext, SHARE_103_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_103_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_103_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_103_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
        SharePreUtil.setIntValue(this.mContext, SHARE_103_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_103_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
    }

    private void setDriveData0x16() {
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        String string = sb.append(((iArr[3] * 256) + iArr[2]) / 100).append("Km/h").toString();
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(((iArr2[3] * 256) + iArr2[2]) / 100);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    public byte[] getSaveAirData() {
        return this.saveAirData;
    }

    private void setAirData0x21() {
        this.saveAirData = this.mCanBusInfoByte;
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.auto_manual = !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        String string2;
        String string3;
        String string4;
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            if (isOnlyDoorMsgShow()) {
                SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
                SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
                SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
                SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
                SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
                SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
                updateDoorView(this.mContext);
            }
            ArrayList arrayList = new ArrayList();
            if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
                string = this.mContext.getResources().getString(R.string.reversing);
            } else {
                string = this.mContext.getResources().getString(R.string.non_reverse);
            }
            arrayList.add(new DriverUpdateEntity(0, 1, string));
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
                string2 = this.mContext.getResources().getString(R.string.gear_not_p);
            } else {
                string2 = this.mContext.getResources().getString(R.string.gear_p);
            }
            arrayList.add(new DriverUpdateEntity(0, 2, string2));
            if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
                string3 = this.mContext.getResources().getString(R.string.open);
            } else {
                string3 = this.mContext.getResources().getString(R.string.close);
            }
            arrayList.add(new DriverUpdateEntity(0, 3, string3));
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
                string4 = this.mContext.getResources().getString(R.string.open);
            } else {
                string4 = this.mContext.getResources().getString(R.string.close);
            }
            arrayList.add(new DriverUpdateEntity(0, 4, string4));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) == GeneralDoorData.isFrontOpen) ? false : true;
    }

    private String resolveLeftAndRightTemp(int i) {
        return i == 0 ? "LOW" : 255 == i ? "HIGH" : (i <= 0 || i >= 255) ? "" : (i * 0.5d) + getTempUnitC(this.mContext);
    }

    private boolean isAmplifierDataChange(int[] iArr) {
        if (Arrays.equals(iArr, this.mAmplifierDataNow)) {
            return false;
        }
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
