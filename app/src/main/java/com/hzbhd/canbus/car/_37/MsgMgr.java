package com.hzbhd.canbus.car._37;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TimerTask;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String _37_IS_BACK_CAMERA = "_37_is_back_camera";
    static final String _37_IS_PANORAMIC = "_37_is_panoramic";
    private int FreqInt;
    private byte bandType;
    private byte freqHi;
    private byte freqLo;
    private int isOnlyRearTempDate;
    private int isOnlyRearUpdate;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int tireStatus1;
    private int tireStatus2;
    private int tireStatus3;
    private int tireStatus4;
    private int tireStatus5;
    private final String _37_ISLEFT_FRONT_DOOR_OPEN = "_37_isleft_front_door_open";
    private final String _37_ISRIGHT_FRONT_DOOR_OPEN = "_37_isright_front_door_open";
    private final String _37_ISRIGHT_REAR_DOOR_OPEN = "_37_isright_rear_door_open";
    private final String _37_ISLEFT_REAR_DOOR_OPEN = "_37_isleft_rear_door_open";
    private final String _37_ISBACK_OPEN = "_37_isback_open";
    private final String _37_AIR_REAR = "_37_air_rear";
    private final String _37_AIR_REAR_TEMP = "_37_air_rear_temp";
    private int mVolume = 28;

    private static boolean noNeedRepBtStatus(boolean z, int i) {
        return !z || i == 0;
    }

    private void setAirData0x27() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -11, 97, 0});
    }

    private void initAmpCommand() {
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._37.MsgMgr.1
            final Iterator<byte[]> iterator;

            {
                this.iterator = Arrays.stream(new byte[][]{new byte[]{22, -60, (byte) MsgMgr.this.mVolume}, new byte[]{22, -57, 0, (byte) (10 - GeneralAmplifierData.frontRear), (byte) (GeneralAmplifierData.leftRight + 10), (byte) (GeneralAmplifierData.bandTreble + 10), (byte) (GeneralAmplifierData.bandMiddle + 10), (byte) (GeneralAmplifierData.bandBass + 10)}}).iterator();
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setCarSetData0x14();
            return;
        }
        if (i == 22) {
            setCarSpeed();
            return;
        }
        if (i == 39) {
            setAirData0x27();
            return;
        }
        if (i == 41) {
            setAmplifierData();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 64) {
            setTypeDataInfo();
            return;
        }
        if (i == 80) {
            setPanoramicStatus();
            setPanoramicSetting();
            return;
        }
        if (i == 149) {
            setEra();
            return;
        }
        switch (i) {
            case 32:
                set0x20WhellKeyInfo();
                break;
            case 33:
                if (!isAirMsgRepeat(bArr)) {
                    setAirData0x21(bArr);
                    break;
                }
                break;
            case 34:
                setRearRadar();
                break;
            case 35:
                setFrontRadar();
                break;
            case 36:
                if (!isDoorMsgRepeat(bArr)) {
                    setDoorData0x24();
                    break;
                }
                break;
        }
    }

    private void set0x20WhellKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 18) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
            return;
        }
        if (i == 129) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i != 130) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 136, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 57, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_HOUR, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_HOUR, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 50, iArr[3]);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 1, iArr[3]);
                    break;
                case 16:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 48:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_DISP, iArr[3]);
                            break;
                        case 49:
                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                            break;
                        case 50:
                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                            break;
                        case 51:
                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                            break;
                        case 52:
                            realKeyLongClick1(this.mContext, 58, iArr[3]);
                            break;
                        case 53:
                            realKeyLongClick1(this.mContext, 49, iArr[3]);
                            break;
                        case 54:
                            realKeyLongClick1(this.mContext, 62, iArr[3]);
                            break;
                        case 55:
                            realKeyLongClick1(this.mContext, 2, iArr[3]);
                            break;
                        case 56:
                            realKeyLongClick1(this.mContext, 14, iArr[3]);
                            break;
                        case 57:
                            realKeyLongClick1(this.mContext, 47, iArr[3]);
                            break;
                        case 58:
                            realKeyLongClick1(this.mContext, 48, iArr[3]);
                            break;
                        case 59:
                            realKeyLongClick1(this.mContext, 1, iArr[3]);
                            break;
                        case 60:
                            realKeyLongClick1(this.mContext, 7, iArr[3]);
                            break;
                        case 61:
                            realKeyLongClick1(this.mContext, 8, iArr[3]);
                            break;
                        case 62:
                            realKeyLongClick1(this.mContext, 56, iArr[3]);
                            break;
                        case 63:
                            realKeyLongClick1(this.mContext, 56, iArr[3]);
                            break;
                        case 64:
                            realKeyLongClick1(this.mContext, 1, iArr[3]);
                            break;
                        case 65:
                            realKeyLongClick1(this.mContext, 45, iArr[3]);
                            break;
                        case 66:
                            realKeyLongClick1(this.mContext, 48, iArr[3]);
                            break;
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 8, iArr[3]);
    }

    private void setRearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setEra() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string._31_drive_data3);
        } else if (i == 1) {
            string = this.mContext.getResources().getString(R.string._31_drive_data4);
        } else {
            string = this.mContext.getResources().getString(R.string._31_drive_data5);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 5, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setPanoramicStatus() {
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        SharePreUtil.setBoolValue(this.mContext, _37_IS_BACK_CAMERA, intFromByteWithBit == 1);
        SharePreUtil.setBoolValue(this.mContext, _37_IS_PANORAMIC, intFromByteWithBit == 2);
        forceReverse(this.mContext, intFromByteWithBit != 0);
        if (SharePreUtil.getBoolValue(this.mContext, _37_IS_BACK_CAMERA, false) || CommUtil.isMiscReverse()) {
            int i = 1;
            while (i <= 4) {
                arrayList.add(new PanoramicBtnUpdateEntity(i - 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == i));
                i++;
            }
        } else if (SharePreUtil.getBoolValue(this.mContext, _37_IS_PANORAMIC, false)) {
            int i2 = 5;
            while (i2 <= 8) {
                arrayList.add(new PanoramicBtnUpdateEntity(i2 - 5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == i2));
                i2++;
            }
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setPanoramicSetting() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = {DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? 1 : 0, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) ? 1 : 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2)};
        for (int i = 0; i < 4; i++) {
            arrayList.add(new SettingUpdateEntity(0, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAirData0x21(byte[] bArr) {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        if (bArr.length > 8) {
            int i = this.mCanBusInfoInt[8];
            this.isOnlyRearTempDate = i;
            GeneralAirData.rear_temperature = resolveLeftAndRightAutoTemp(i);
        }
        Log.d("cwh", "mCanBusInfoByte.length  " + bArr.length);
        this.isOnlyRearUpdate = this.mCanBusInfoInt[7];
        if (isOnlyRearUpdate()) {
            SharePreUtil.setIntValue(this.mContext, "_37_air_rear", this.isOnlyRearUpdate);
            SharePreUtil.setIntValue(this.mContext, "_37_air_rear_temp", this.isOnlyRearTempDate);
            updateAirActivity(this.mContext, 1002);
        } else if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setAmplifierData() throws Resources.NotFoundException {
        String string;
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 1) {
            string = this.mContext.getResources().getString(R.string._31_drive_data1);
        } else {
            string = this.mContext.getResources().getString(R.string._31_drive_data2);
        }
        arrayList.add(new DriverUpdateEntity(0, 4, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setTypeDataInfo() {
        /*
            r13 = this;
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            int[] r0 = r13.mCanBusInfoInt
            r1 = 4
            r0 = r0[r1]
            r2 = 0
            r3 = 2
            int r0 = com.hzbhd.canbus.util.DataHandleUtils.getIntFromByteWithBit(r0, r2, r3)
            int[] r4 = r13.mCanBusInfoInt
            r4 = r4[r1]
            int r4 = com.hzbhd.canbus.util.DataHandleUtils.getIntFromByteWithBit(r4, r3, r3)
            int[] r5 = r13.mCanBusInfoInt
            r5 = r5[r1]
            boolean r5 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit4(r5)
            int[] r6 = r13.mCanBusInfoInt
            r6 = r6[r1]
            boolean r6 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit5(r6)
            int[] r8 = r13.mCanBusInfoInt
            r8 = r8[r1]
            boolean r8 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit6(r8)
            int[] r9 = r13.mCanBusInfoInt
            r10 = 3
            r9 = r9[r10]
            r11 = 5
            r12 = 1
            if (r9 == r12) goto L41
            if (r9 == r3) goto L47
            if (r9 == r10) goto L45
            if (r9 == r1) goto L43
            if (r9 == r11) goto L48
        L41:
            r1 = r2
            goto L48
        L43:
            r1 = r3
            goto L48
        L45:
            r1 = r10
            goto L48
        L47:
            r1 = r12
        L48:
            java.lang.String r2 = r13.getTisWarmMsg(r4)
            int[] r3 = r13.mCanBusInfoInt
            r3 = r3[r11]
            java.lang.String r3 = r13.getTirePressure(r3, r0)
            int[] r0 = r13.mCanBusInfoInt
            r4 = 6
            r0 = r0[r4]
            java.lang.String r4 = r13.getTireTemp(r0, r5)
            int[] r0 = r13.mCanBusInfoInt
            r5 = 7
            r0 = r0[r5]
            java.lang.String r5 = r13.getVoltage(r0, r8)
            java.lang.String r6 = r13.getSensor(r6)
            r0 = r13
            com.hzbhd.canbus.entity.TireUpdateEntity r0 = r0.getTireEntity(r1, r2, r3, r4, r5, r6)
            r7.add(r0)
            com.hzbhd.canbus.ui_datas.GeneralTireData.dataList = r7
            r0 = 0
            r13.updateTirePressureActivity(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._37.MsgMgr.setTypeDataInfo():void");
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        if (isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "_37_isleft_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_37_isright_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_37_isright_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_37_isleft_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_37_isback_open", GeneralDoorData.isBackOpen);
            updateDoorView(this.mContext);
        }
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? "Not P" : "P"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.minimum);
        } else if (i == 255) {
            string = this.mContext.getResources().getString(R.string.max);
        } else {
            string = this.mCanBusInfoInt[2] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSpeed() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, sb.append((iArr[2] + (iArr[3] * 256)) / 16).append("Km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo((iArr2[2] + (iArr2[3] * 256)) / 16);
    }

    void setLanguage_recNull(Context context) {
        int intValue = SharePreUtil.getIntValue(context, "_37_SAVE_LANGUAGE", 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(intValue)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 0, 0, 0, (byte) i5, (byte) i6, (byte) i7, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, z2), 6, z3)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.OFF, 255, false, true);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
        initAmpCommand();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        setVwRadioInfo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, this.bandType, this.freqLo, this.freqHi, (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.AUX, 255, false, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b != 9) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.USB_VIDEO, 255, true, true);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 33, (byte) i4, (byte) i5, (byte) i2, (byte) (i / 3600), (byte) ((i / 60) % 60), (byte) (i % 60)});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 16, 1, (byte) i3, (byte) i5, (byte) (i / 3600), (byte) ((i / 60) % 60), (byte) (i % 60)});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.ATV, 255, false, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.mVolume = (int) ((i / 40.0f) * 35.0f);
        sndSimpleVol((byte) 35, (byte) i, z);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (noNeedRepBtStatus(z, i)) {
            return;
        }
        Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.PHONE, 255, false, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.A2DP, 48, false, true);
    }

    private void sndSimpleVol(byte b, byte b2, boolean z) {
        if (b >= 40) {
            b = 40;
        }
        if (b2 >= b) {
            b2 = b;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) (((z ? 1 : 0) << 7) | (b2 & ByteCompanionObject.MAX_VALUE))});
    }

    private void setVwRadioInfo(String str, String str2) throws NumberFormatException {
        if (str.equals("AM2") || str.equals("MW")) {
            this.bandType = (byte) 18;
        } else if (str.equals("AM1") || str.equals("LW")) {
            int i = Integer.parseInt(str2);
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
            this.bandType = (byte) 17;
        } else if (str.equals("FM1")) {
            this.bandType = (byte) 1;
        } else if (str.equals("FM2")) {
            this.bandType = (byte) 2;
        } else if (str.equals("FM3") || str.equals("OIRT")) {
            this.bandType = (byte) 3;
        }
        getFreqByteHiLo(str, str2);
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "_37_isleft_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_37_isright_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_37_isright_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_37_isleft_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_37_isback_open", false) == GeneralDoorData.isBackOpen) ? false : true;
    }

    private boolean isOnlyRearUpdate() {
        return (SharePreUtil.getIntValue(this.mContext, "_37_air_rear", 0) == this.isOnlyRearUpdate && SharePreUtil.getIntValue(this.mContext, "_37_air_rear_temp", 0) == this.isOnlyRearTempDate) ? false : true;
    }

    private String getTirePressure(int i, int i2) {
        String str = ((float) (i * 2.35d)) + "kpa";
        if (i2 == 1) {
            String str2 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date2);
            this.tireStatus2 = 1;
            return str2;
        }
        if (i2 == 2) {
            String str3 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date3);
            this.tireStatus2 = 1;
            return str3;
        }
        String str4 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date1);
        this.tireStatus2 = 0;
        return str4;
    }

    private String getTireTemp(int i, int i2) {
        String str = (i - 50) + "℃";
        if (i2 == 1) {
            String str2 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date8);
            this.tireStatus3 = 1;
            return str2;
        }
        String str3 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date1);
        this.tireStatus3 = 0;
        return str3;
    }

    private String getVoltage(int i, int i2) {
        String str = (i / 10.0f) + "V";
        if (i2 == 1) {
            String str2 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date10);
            this.tireStatus4 = 1;
            return str2;
        }
        String str3 = str + " " + this.mContext.getResources().getString(R.string._31_tire_date1);
        this.tireStatus4 = 0;
        return str3;
    }

    private String getSensor(int i) throws Resources.NotFoundException {
        if (i == 1) {
            String string = this.mContext.getResources().getString(R.string._31_tire_date12);
            this.tireStatus5 = 1;
            return string;
        }
        String string2 = this.mContext.getResources().getString(R.string._31_tire_date11);
        this.tireStatus5 = 0;
        return string2;
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, String str3, String str4, String str5) {
        return new TireUpdateEntity(i, (this.tireStatus1 == 1 || this.tireStatus2 == 1 || this.tireStatus3 == 1 || this.tireStatus4 == 1 || this.tireStatus5 == 1) ? 1 : 0, new String[]{str, str2, str3, str4, str5});
    }

    private String getTisWarmMsg(int i) throws Resources.NotFoundException {
        if (i == 1) {
            String string = this.mContext.getResources().getString(R.string._31_tire_date5);
            this.tireStatus1 = 1;
            return string;
        }
        if (i == 2) {
            String string2 = this.mContext.getResources().getString(R.string._31_tire_date6);
            this.tireStatus1 = 1;
            return string2;
        }
        String string3 = this.mContext.getResources().getString(R.string._31_tire_date1);
        this.tireStatus1 = 0;
        return string3;
    }

    private String resolveOutDoorTem() {
        byte b = this.mCanBusInfoByte[2];
        if (b < 0 || b > 87) {
            return b < 0 ? ((int) this.mCanBusInfoByte[2]) + getTempUnitC(this.mContext) : "";
        }
        return ((int) this.mCanBusInfoByte[2]) + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 30 == i ? "HI" : (1 > i || i > 29) ? "" : ((i * 0.5f) + 17.0f) + getTempUnitC(this.mContext);
    }

    void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.frontRear = 10 - SharePreUtil.getIntValue(context, "_37_amplifier_fade", 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, "_37_amplifier_balance", 0) - 10;
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, "_37_amplifier_bass", 0) - 10;
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, "_37_amplifier_middle", 0) - 10;
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, "_37_amplifier_treble", 0) - 10;
        }
        updateAmplifierActivity(null);
    }
}
