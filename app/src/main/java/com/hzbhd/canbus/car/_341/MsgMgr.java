package com.hzbhd.canbus.car._341;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;




public final class MsgMgr extends AbstractMsgMgr {
    private int[] lastArrayForFrontAirCondition;
    private int[] lastArrayForGeneralInfo;
    private int[] lastArrayForRearAirCondition;
    private Integer stagedData1;
    private Integer stagedData7;
    public String test;

    public MsgMgr() {
        int[] iArr = new int[9];
        for (int i = 0; i < 9; i++) {
            iArr[i] = 0;
        }
        this.lastArrayForFrontAirCondition = iArr;
        int[] iArr2 = new int[9];
        for (int i2 = 0; i2 < 9; i2++) {
            iArr2[i2] = 0;
        }
        this.lastArrayForRearAirCondition = iArr2;
        int[] iArr3 = new int[4];
        for (int i3 = 0; i3 < 4; i3++) {
            iArr3[i3] = 0;
        }
        this.lastArrayForGeneralInfo = iArr3;
    }

    public final Integer getStagedData7() {
        return this.stagedData7;
    }

    public final void setStagedData7(Integer num) {
        this.stagedData7 = num;
    }

    public final Integer getStagedData1() {
        return this.stagedData1;
    }

    public final void setStagedData1(Integer num) {
        this.stagedData1 = num;
    }

    public final String getTest() {
        String str = this.test;
        if (str != null) {
            return str;
        }

        return null;
    }

    public final void setTest(String str) {

        this.test = str;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        super.canbusInfoChange(context, canbusInfo);
        if (canbusInfo == null || context == null) {
            return;
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        int i = byteArrayToIntArray[1];
        if (i == 29) {
            frontRadarInfo(context, byteArrayToIntArray);
            return;
        }
        if (i == 30) {
            rearRadarInfo(context, byteArrayToIntArray);
            return;
        }
        if (i == 32) {
            steeringWheelKeys(context, byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            rptCmdFilter(context, byteArrayToIntArray, this.lastArrayForGeneralInfo, new AnonymousClass1(this));
            return;
        }
        if (i == 41) {
            steeringWheelAngle(context, byteArrayToIntArray);
            return;
        }
        if (i == 53) {
            vehicleInfo(byteArrayToIntArray);
            return;
        }
        if (i == 85) {
            rptCmdFilter(context, byteArrayToIntArray, this.lastArrayForFrontAirCondition, new AnonymousClass2(this));
            return;
        }
        if (i != 86) {
            switch (i) {
                case 48:
                    versionInfo(context, canbusInfo);
                    break;
                case 49:
                    amplifierInfo(byteArrayToIntArray);
                    break;
                case 50:
                    systemInfo(context, byteArrayToIntArray);
                    break;
            }
            return;
        }
        rptCmdFilter(context, byteArrayToIntArray, this.lastArrayForRearAirCondition, new AnonymousClass3(this));
    }

    /* compiled from: MsgMgr.kt */
    
    /* renamed from: com.hzbhd.canbus.car._341.MsgMgr$canbusInfoChange$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<Context, int[], Unit> {
        AnonymousClass1(Object obj) {
            super(2, obj, MsgMgr.class, "generalInfo", "generalInfo(Landroid/content/Context;[I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Context context, int[] iArr) {
            invoke2(context, iArr);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Context p0, int[] p1) {


            ((MsgMgr) this.receiver).generalInfo(p0, p1);
        }
    }

    /* compiled from: MsgMgr.kt */
    
    /* renamed from: com.hzbhd.canbus.car._341.MsgMgr$canbusInfoChange$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<Context, int[], Unit> {
        AnonymousClass2(Object obj) {
            super(2, obj, MsgMgr.class, "frontAirConditionInfo", "frontAirConditionInfo(Landroid/content/Context;[I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Context context, int[] iArr) {
            invoke2(context, iArr);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Context p0, int[] p1) {


            ((MsgMgr) this.receiver).frontAirConditionInfo(p0, p1);
        }
    }

    /* compiled from: MsgMgr.kt */
    
    /* renamed from: com.hzbhd.canbus.car._341.MsgMgr$canbusInfoChange$3, reason: invalid class name */
    /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<Context, int[], Unit> {
        AnonymousClass3(Object obj) {
            super(2, obj, MsgMgr.class, "rearAirConditionInfo", "rearAirConditionInfo(Landroid/content/Context;[I)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Context context, int[] iArr) {
            invoke2(context, iArr);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Context p0, int[] p1) {


            ((MsgMgr) this.receiver).rearAirConditionInfo(p0, p1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {

        super.initCommand(context);
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isShowLittleLight = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009b  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, int r14) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 394
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._341.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        if (stoagePath != 9) {
            sendMediaInfo$default(this, 8, 255, null, 4, null);
            if (songTitle != null) {
                sendTextInfo(112, songTitle);
            } else {
                sendTextInfo$default(this, 112, null, 2, null);
            }
            if (songArtist != null) {
                sendTextInfo(113, songArtist);
            } else {
                sendTextInfo$default(this, 113, null, 2, null);
            }
            if (songAlbum != null) {
                sendTextInfo(114, songAlbum);
            } else {
                sendTextInfo$default(this, 114, null, 2, null);
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        sendMediaInfo$default(this, 11, 255, null, 4, null);
        if (title != null) {
            sendTextInfo(112, title);
        } else {
            sendTextInfo$default(this, 112, null, 2, null);
        }
        if (artist != null) {
            sendTextInfo(113, artist);
        } else {
            sendTextInfo$default(this, 113, null, 2, null);
        }
        if (album != null) {
            sendTextInfo(114, album);
        } else {
            sendTextInfo$default(this, 114, null, 2, null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaInfo$default(this, 7, 0, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        sendMediaInfo$default(this, 3, 0, null, 4, null);
    }

    private final void steeringWheelKeys(Context context, int[] mCanBusInfoInt) {
        int i = mCanBusInfoInt[2];
        int i2 = mCanBusInfoInt[3];
        if (i == 0) {
            realKeyLongClick1(context, 0, i2);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(context, 7, i2);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 8, i2);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 48, i2);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(context, 47, i2);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(context, 14, i2);
            return;
        }
        if (i == 10) {
            realKeyLongClick1(context, 15, i2);
            return;
        }
        if (i != 136) {
            switch (i) {
                case 19:
                    realKeyLongClick1(context, 45, i2);
                    break;
                case 20:
                    realKeyLongClick1(context, 46, i2);
                    break;
                case 21:
                    realKeyLongClick1(context, 50, i2);
                    break;
                case 22:
                    realKeyLongClick1(context, 49, i2);
                    break;
            }
            return;
        }
        realKeyLongClick1(context, 2, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void generalInfo(Context context, int[] mCanBusInfoInt) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(mCanBusInfoInt[2]);
        GeneralDoorData.isLittleLightOn = DataHandleUtils.getBoolBit2(mCanBusInfoInt[3]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(mCanBusInfoInt[3]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(mCanBusInfoInt[3]);
        updateDoorView(context);
    }

    private final void versionInfo(Context context, byte[] mCanBusInfoByte) {
        updateVersionInfo(context, getVersionStr(mCanBusInfoByte));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void frontAirConditionInfo(Context context, int[] mCanBusInfoInt) {
        if (isOnlyChanged(mCanBusInfoInt, 7)) {
            if (mCanBusInfoInt[7] == 255) {
                updateOutDoorTemp(context, "--");
                return;
            } else {
                updateOutDoorTemp(context, (mCanBusInfoInt[7] - 40) + " °C");
                return;
            }
        }
        int i = mCanBusInfoInt[2];
        String str = "HI";
        GeneralAirData.front_left_temperature = i == 16 ? "L0" : i == 80 ? "HI" : (mCanBusInfoInt[2] / 2.0d) + " °C";
        int i2 = mCanBusInfoInt[3];
        if (i2 == 16) {
            str = "L0";
        } else if (i2 != 80) {
            str = (mCanBusInfoInt[3] / 2.0d) + " °C";
        }
        GeneralAirData.front_right_temperature = str;
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[4], 0, 4);
        GeneralAirData.power = GeneralAirData.front_wind_level != 0;
        GeneralAirData.front_auto_wind_model = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_left_blow_window = false;
        switch (DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[4], 4, 4)) {
            case 0:
                GeneralAirData.front_auto_wind_model = true;
                break;
            case 1:
                GeneralAirData.front_left_blow_head = true;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_window = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                break;
        }
        GeneralAirData.rear_lock = !DataHandleUtils.getBoolBit2(mCanBusInfoInt[5]);
        GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit3(mCanBusInfoInt[5]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit0(mCanBusInfoInt[6]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit1(mCanBusInfoInt[6]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(mCanBusInfoInt[6]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit3(mCanBusInfoInt[6]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit4(mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(mCanBusInfoInt[6]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit7(mCanBusInfoInt[6]);
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[8], 4, 4);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_right_blow_foot = true;
        }
        updateAirActivity(context, 1001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rearAirConditionInfo(Context context, int[] mCanBusInfoInt) {
        String str;
        int i = mCanBusInfoInt[2];
        if (i != 16) {
            str = i != 80 ? (mCanBusInfoInt[2] / 2.0d) + " °C" : "HI";
        } else {
            str = "LO";
        }
        GeneralAirData.rear_temperature = str;
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[4], 0, 4);
        GeneralAirData.rear_auto_wind_model = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[4], 4, 4);
        if (intFromByteWithBit == 0) {
            GeneralAirData.rear_auto_wind_model = true;
        } else if (intFromByteWithBit == 1) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        }
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit0(mCanBusInfoInt[6]);
        updateAirActivity(context, 1002);
    }

    private final void steeringWheelAngle(Context context, int[] mCanBusInfoInt) {
        int iCulTrackAngle;
        if (DataHandleUtils.getBoolBit7(mCanBusInfoInt[3])) {
            iCulTrackAngle = culTrackAngle((short) (-(((mCanBusInfoInt[3] & 15) * 256) + mCanBusInfoInt[2])));
        } else {
            int i = mCanBusInfoInt[2];
            iCulTrackAngle = (i == 0 && mCanBusInfoInt[3] == 0) ? 0 : culTrackAngle((short) (4096 - (((mCanBusInfoInt[3] & 15) * 256) + i)));
        }
        GeneralParkData.trackAngle = iCulTrackAngle;
        updateParkUi(null, context);
    }

    private final void amplifierInfo(int[] mCanBusInfoInt) {
        GeneralAmplifierData.frontRear = MsgMgrKt.reverse(DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[2], 4, 4), 0, 14)) - 7;
        GeneralAmplifierData.leftRight = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[2], 0, 4), 0, 14) - 7;
        GeneralAmplifierData.bandBass = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[3], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[3], 0, 4), 2, 12) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[4], 4, 4), 2, 12) - 2;
        GeneralAmplifierData.volume = DataHandleUtils.rangeNumber(mCanBusInfoInt[5], 0, 63);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[4], 0, 4) == 1 ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(mCanBusInfoInt[6], 0, 4) == 1 ? 1 : 0)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updateAmplifierActivity(null);
    }

    private final void systemInfo(Context context, int[] mCanBusInfoInt) {
        String strByResId = CommUtil.getStrByResId(context, "_341_setting_0_0_0");

        String strByResId2 = CommUtil.getStrByResId(context, "_341_setting_0_0_1");

        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit6(mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getBoolBit7(mCanBusInfoInt[2]) ? 1 : 0)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new DriverUpdateEntity(1, 0, DataHandleUtils.getBoolBit0(mCanBusInfoInt[2]) ? strByResId2 : strByResId));
        arrayList2.add(new DriverUpdateEntity(1, 1, DataHandleUtils.getBoolBit1(mCanBusInfoInt[2]) ? strByResId2 : strByResId));
        arrayList2.add(new DriverUpdateEntity(1, 2, DataHandleUtils.getBoolBit2(mCanBusInfoInt[2]) ? strByResId2 : strByResId));
        if (DataHandleUtils.getBoolBit3(mCanBusInfoInt[2])) {
            strByResId = strByResId2;
        }
        arrayList2.add(new DriverUpdateEntity(1, 3, strByResId));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
    }

    private final void rearRadarInfo(Context context, int[] mCanBusInfoInt) {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(3, mCanBusInfoInt[2], mCanBusInfoInt[3], mCanBusInfoInt[4], mCanBusInfoInt[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private final void frontRadarInfo(Context context, int[] mCanBusInfoInt) {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(3, mCanBusInfoInt[2], mCanBusInfoInt[3], mCanBusInfoInt[4], mCanBusInfoInt[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private final void vehicleInfo(int[] mCanBusInfoInt) {
        updateSpeedInfo(mCanBusInfoInt[4]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, MsgMgrKt.getMsbLsbResult(mCanBusInfoInt[2], mCanBusInfoInt[3]) + " r/min"));
        arrayList.add(new DriverUpdateEntity(0, 1, mCanBusInfoInt[4] + " km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private final int culTrackAngle(short track) {
        return DataHandleUtils.rangeNumber((int) (track / 14.4f), -25, 25);
    }

    private final void rptCmdFilter(Context context, int[] current, int[] last, Function2<? super Context, ? super int[], Unit> func) {
        if (!MsgMgrKt.contentCompare(current, last)) {
            func.invoke(context, current);
        }
        if (current.length == last.length) {
            MsgMgrKt.transTo(current, last);
        }
    }

    private final boolean isOnlyChanged(int[] mCanBusInfoInt, int param) {
        if (!(param >= 0 && param < mCanBusInfoInt.length)) {
            return false;
        }
        for (int i = 0; i < param; i++) {
            if (mCanBusInfoInt[i] != this.lastArrayForFrontAirCondition[i]) {
                return false;
            }
        }
        int length = mCanBusInfoInt.length;
        for (int i2 = param + 1; i2 < length; i2++) {
            if (mCanBusInfoInt[i2] != this.lastArrayForFrontAirCondition[i2]) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ void sendMediaInfo$default(MsgMgr msgMgr, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            bArr = new byte[]{0, 0, 0, 0, 0, 0};
        }
        msgMgr.sendMediaInfo(i, i2, bArr);
    }

    private final void sendMediaInfo(int data0, int data1, byte[] data2_7) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) data0, (byte) data1}, data2_7));
    }

    static /* synthetic */ void sendTextInfo$default(MsgMgr msgMgr, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "Unknown";
        }
        msgMgr.sendTextInfo(i, str);
    }

    private final void sendTextInfo(int dataType, String text) {
        byte[] bytes;
        if (text.length() >= 15) {
            bytes = (StringsKt.substring(text, new IntRange(0, 15)) + "...").getBytes(Charsets.UTF_16LE);

        } else {
            bytes = text.getBytes(Charsets.UTF_16LE);

        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, (byte) dataType, 17}, bytes));
    }
}
