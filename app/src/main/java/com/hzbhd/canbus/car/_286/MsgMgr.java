package com.hzbhd.canbus.car._286;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.view.MotionEventCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.TirePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MsgMgr extends AbstractMsgMgr {
    private static final int RADAR_PARAM_FORNT_MID_MAX = 120;
    private static final int RADAR_PARAM_MID_RANGE = 10;
    private static final int RADAR_PARAM_REAR_MID_MAX = 165;
    private static final int RADAR_PARAM_SIDE_MAX = 60;
    private static final int RADAR_PARAM_SIDE_RANGE = 5;
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x27Data;
    private int[] m0x29Data;
    private int[] m0x30Data;
    private int[] m0x3AData;
    private int[] m0x40Data;
    private int[] m0x66Data;
    private int[] m0x68Data;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat;
    private int mDiiferentId;
    private int mEachId;
    private boolean mFrontStatus;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private ID3[] mMusicId3s;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private HashMap<String, Integer> mSettingItemIndeHashMap;
    private SparseArray<String> mSettingItemSpareArray;
    private int mSettingLeftIndex;
    private int mSettingRightIndex;
    private String[] mTireInfoArray;
    private TirePageUiSet mTirePageUiSet;
    private List<TireResolve> mTireResolveList;
    private List<TireUpdateEntity> mTireUpdateEntityList;
    private String[] mTireWarningArray;
    private UiMgr mUiMgr;

    private int resolveFrontMidRadarData(int i) {
        return ((int) ((i / 120.0f) * 10.0f)) + 1;
    }

    private int resolveRearMidRadarData(int i) {
        return ((int) ((i / 165.0f) * 10.0f)) + 1;
    }

    private int resolveSideRadarData(int i) {
        return ((int) ((i / 60.0f) * 5.0f)) + 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.mDiiferentId = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        initSettingItemIndexSparseArray();
        initSettingsItem(getUiMgr().getSettingUiSet(context));
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.mMinIsClose = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralTireData.isHaveSpareTire = false;
        initID3();
        initTireResolveTool();
        this.mDecimalFormat = new DecimalFormat("0.0");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            set0x01WheelKey(context);
            return;
        }
        if (i == 22) {
            setVehicleSpeed0x16();
            return;
        }
        if (i == 39) {
            setOutDoorTemperatureData0x27();
            return;
        }
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setVehicleData0x40();
            return;
        }
        if (i == 102) {
            setTireInfo0x66();
            return;
        }
        if (i != 104) {
            switch (i) {
                case 32:
                    setWheelKey0x20();
                    break;
                case 33:
                    setAirData0x21();
                    break;
                case 34:
                    setRearRadarData0x22();
                    break;
                case 35:
                    setFrontRadarData0x23();
                    break;
                case 36:
                    setDoorDatat0x24();
                    break;
            }
            return;
        }
        setTireWarning0x68();
    }

    private void set0x01WheelKey(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(context, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(context, 2, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(context, 46, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(context, 45, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(context, 7, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(context, 8, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(context, 3, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(context, 14, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(context, 15, iArr[3]);
                break;
        }
    }

    private void setVehicleSpeed0x16() {
        int[] iArr = this.mCanBusInfoInt;
        int i = ((iArr[3] << 8) | iArr[2]) / 16;
        String str = DataHandleUtils.getBoolBit0(iArr[4]) ? "mph" : "km/h";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, i + " " + str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
            updateSpeedInfo(Integer.parseInt(this.df_2Integer.format(i * 1.6d)));
        } else {
            updateSpeedInfo(Integer.parseInt(this.df_2Integer.format(i)));
        }
    }

    private void setWheelKey0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick1(0);
                break;
            case 1:
                realKeyClick1(7);
                break;
            case 2:
                realKeyClick1(8);
                break;
            case 3:
                realKeyClick1(20);
                break;
            case 4:
                realKeyClick1(21);
                break;
            case 5:
                realKeyClick1(14);
                break;
            case 6:
                realKeyClick1(3);
                break;
            case 7:
                realKeyClick1(2);
                break;
            case 8:
                realKeyClick1(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick1(15);
                break;
        }
    }

    private void setAirData0x21() {
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) DataHandleUtils.setIntByteWithBit(bArr[3], 4, false);
        if (isAirMsgRepeat(this.mCanBusInfoByte)) {
            return;
        }
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        boolean z = true;
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        if (!DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            z = false;
        }
        GeneralAirData.auto = z;
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadarData0x22() {
        if (is0x22DataChange()) {
            RadarInfoUtil.setRearRadarLocationDataType2(5, resolveSideRadarData(this.mCanBusInfoInt[2]), 10, resolveRearMidRadarData(this.mCanBusInfoInt[3]), 10, resolveRearMidRadarData(this.mCanBusInfoInt[4]), 5, resolveSideRadarData(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (is0x23DataChange()) {
            RadarInfoUtil.setFrontRadarLocationDataType2(5, resolveSideRadarData(this.mCanBusInfoInt[2]), 10, resolveFrontMidRadarData(this.mCanBusInfoInt[3]), 10, resolveFrontMidRadarData(this.mCanBusInfoInt[4]), 5, resolveSideRadarData(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setDoorDatat0x24() {
        if (is0x3ADataChange()) {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            this.mRightFrontRec = boolBit7;
            GeneralDoorData.isRightFrontDoorOpen = boolBit7;
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            this.mLeftFrontRec = boolBit6;
            GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
            boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            this.mRightRearRec = boolBit5;
            GeneralDoorData.isRightRearDoorOpen = boolBit5;
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mLeftRearRec = boolBit4;
            GeneralDoorData.isLeftRearDoorOpen = boolBit4;
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            if (isDoorDataChange()) {
                updateDoorView(this.mContext);
            }
        }
    }

    private void setOutDoorTemperatureData0x27() {
        if (is0x27DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            short s = (short) ((iArr[4] << 8) | iArr[3]);
            if (s < -580 || s > 1710) {
                return;
            }
            short s2 = (short) (s / 10);
            if (DataHandleUtils.getBoolBit0(iArr[2])) {
                updateOutDoorTemp(this.mContext, ((int) s2) + getTempUnitF(this.mContext));
            } else {
                updateOutDoorTemp(this.mContext, ((int) s2) + getTempUnitC(this.mContext));
            }
        }
    }

    private void setTrackData0x29() {
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 19918, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setVersionInfo0x30() {
        if (is0x30DataChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setVehicleData0x40() {
        int i;
        Log.i("ljq", "setVehicleData0x40: " + Arrays.toString(this.mCanBusInfoInt));
        if (is0x40DataChange()) {
            byte b = this.mCanBusInfoByte[2];
            if (b == 1) {
                StringBuilder sb = new StringBuilder();
                int[] iArr = this.mCanBusInfoInt;
                updateDriverData(0, 1, sb.append(iArr[4] | (iArr[3] << 8)).append(" ").append(CommUtil.getStrByResId(this.mContext, "unit_hour")).toString());
            } else if (b == 2) {
                StringBuilder sb2 = new StringBuilder();
                int[] iArr2 = this.mCanBusInfoInt;
                updateDriverData(0, 2, sb2.append(iArr2[4] | (iArr2[3] << 8)).append(" km").toString());
            } else if (b == 3) {
                int[] iArr3 = this.mCanBusInfoInt;
                updateDriverData(0, 3, ((iArr3[4] | (iArr3[3] << 8)) / 10.0f) + " l/100km");
            } else if (b == 4) {
                StringBuilder sb3 = new StringBuilder();
                int[] iArr4 = this.mCanBusInfoInt;
                updateDriverData(0, 4, sb3.append(iArr4[4] | (iArr4[3] << 8)).append(" km/h").toString());
            }
            getLeftAndRight(getItem(this.mCanBusInfoByte[2]));
            int i2 = this.mSettingLeftIndex;
            if (i2 == -1 || (i = this.mSettingRightIndex) == -1) {
                return;
            }
            switch (this.mCanBusInfoByte[2]) {
                case 5:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    updateSettingData(i2, i, this.mCanBusInfoInt[3]);
                    break;
                case 6:
                    int i3 = this.mCanBusInfoInt[3];
                    updateSettingData(i2, i, i3 * 10, i3 - 1);
                    break;
                case 7:
                case 8:
                    int i4 = this.mCanBusInfoInt[3];
                    updateSettingData(i2, i, i4 * 10, i4);
                    break;
            }
        }
    }

    private void setTireInfo0x66() {
        if (is0x66DataChange()) {
            TireResolve tireResolve = this.mTireResolveList.get(this.mCanBusInfoInt[7]);
            for (int i = 0; i < this.mTireUpdateEntityList.size(); i++) {
                ArrayList arrayList = new ArrayList();
                this.mTireInfoArray[i] = this.mDecimalFormat.format(this.mCanBusInfoInt[i + 3] * tireResolve.calculate) + tireResolve.unit;
                arrayList.add(this.mTireInfoArray[i]);
                arrayList.add(this.mTireWarningArray[i]);
                this.mTireUpdateEntityList.get(i).setList(arrayList);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void setTireWarning0x68() {
        int i;
        if (is0x68DataChange()) {
            for (int i2 = 0; i2 < this.mTireUpdateEntityList.size(); i2++) {
                int i3 = (this.mCanBusInfoInt[2] & (1 << i2)) == 0 ? 0 : 1;
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.mTireInfoArray[i2]);
                String[] strArr = this.mTireWarningArray;
                strArr[i2] = " ";
                if (i3 != 0 && (i = this.mCanBusInfoInt[3]) >= 2 && i <= 4) {
                    strArr[i2] = CommUtil.getStrByResId(this.mContext, "_286_tire_warning_" + this.mCanBusInfoInt[3]);
                }
                arrayList.add(this.mTireWarningArray[i2]);
                this.mTireUpdateEntityList.get(i2).setList(arrayList);
                this.mTireUpdateEntityList.get(i2).setTireStatus(i3);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
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
        int bandData = getBandData(str);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, (byte) bandData, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        int[] time = getTime(i);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, (byte) i3, (byte) i5, (byte) time[0], (byte) time[1], (byte) time[2]});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 34, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(1, 6, z2), 5, z), 4, true)});
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -54, 1, 1}, bArr), 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(2, 6, z2), 5, z), 4, true)});
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -54, 1, 1}, bArr), 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        int[] time = getTime(i);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0, (byte) time[1], (byte) time[2], 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(4, 6, z2), 5, z), 4, true)});
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -54, 1, 1}, bArr), 15, 32));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, (byte) (b == 9 ? 9 : 8), 18, (byte) i, b7, (byte) (i2 & 255), (byte) (i2 >> 8), 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, (byte) (b == 9 ? 9 : 8), 18, (byte) i, b6, (byte) (i2 & 255), (byte) (i2 >> 8), 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -64, 10, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 32, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) (DataHandleUtils.rangeNumber(i, 0, 30) | (z ? 128 : 0))});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) i2, (byte) i3, (byte) i4, (byte) (i5 | (z ? 0 : 128)), (byte) i6, (byte) i7, (byte) (i9 - 1)});
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (i < 1 || i > 28) {
            return "";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return (i + 59) + getTempUnitF(this.mContext);
        }
        return ((i + 31) / 2.0f) + getTempUnitC(this.mContext);
    }

    private void updateDriverData(int i, int i2, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(i, i2, str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void updateSettingData(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void updateSettingData(int i, int i2, int i3, int i4) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)).setProgress(i4));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean is0x29DataChange() {
        if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x29Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x3ADataChange() {
        if (Arrays.equals(this.m0x3AData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x3AData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x30DataChange() {
        if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x30Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x27DataChange() {
        if (Arrays.equals(this.m0x27Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x27Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x40DataChange() {
        if (Arrays.equals(this.m0x40Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x40Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x66DataChange() {
        if (Arrays.equals(this.m0x66Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x66Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x68DataChange() {
        if (Arrays.equals(this.m0x68Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x68Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private int getBandData(String str) {
        if ("FM1".equals(str)) {
            return 1;
        }
        if ("FM2".equals(str)) {
            return 2;
        }
        if ("FM3".equals(str)) {
            return 3;
        }
        if ("AM1".equals(str)) {
            return 17;
        }
        return "AM2".equals(str) ? 18 : 0;
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private class ID3 {
        private int line;
        private String info = "";
        private String record = "";

        private ID3(int i) {
            this.line = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            this.record = this.info;
            return true;
        }
    }

    private void initID3() {
        this.mMusicId3s = new ID3[0];
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._286.MsgMgr$1] */
    private void reportID3Info(final int i, final ID3[] id3Arr, final boolean z) {
        new Thread() { // from class: com.hzbhd.canbus.car._286.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    for (ID3 id3 : id3Arr) {
                        if (id3.isId3Change()) {
                            if (z) {
                                sleep(900L);
                            }
                            for (ID3 id32 : id3Arr) {
                                sleep(100L);
                                MsgMgr.this.reportID3InfoFinal(i, id32.line, id32.info);
                            }
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(int i, int i2, String str) throws UnsupportedEncodingException {
        byte[] bArrExceptBOMHead = DataHandleUtils.exceptBOMHead(str.getBytes("utf-8"));
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) i, -1, 18, (byte) i2, (byte) bArrExceptBOMHead.length}, bArrExceptBOMHead));
    }

    private UiMgr getUiMgr() {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgr;
    }

    private TirePageUiSet getTirePageUiSet() {
        if (this.mTirePageUiSet == null) {
            this.mTirePageUiSet = getUiMgr().getTireUiSet(this.mContext);
        }
        return this.mTirePageUiSet;
    }

    private String getItem(int i) {
        return this.mSettingItemSpareArray.get(i, "null_value");
    }

    private void initSettingItemIndexSparseArray() {
        SparseArray<String> sparseArray = new SparseArray<>();
        this.mSettingItemSpareArray = sparseArray;
        sparseArray.append(5, "_286_settings_item_5");
        this.mSettingItemSpareArray.append(6, "_286_settings_item_6");
        this.mSettingItemSpareArray.append(7, "_286_settings_item_7");
        this.mSettingItemSpareArray.append(8, "_286_settings_item_8");
        this.mSettingItemSpareArray.append(9, "_286_settings_item_9");
        this.mSettingItemSpareArray.append(10, "_286_settings_item_a");
        this.mSettingItemSpareArray.append(11, "_286_settings_item_b");
        this.mSettingItemSpareArray.append(12, "_286_settings_item_c");
        this.mSettingItemSpareArray.append(13, "_286_settings_item_d");
        this.mSettingItemSpareArray.append(14, "_286_settings_item_e");
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        Log.i("ljq", "initSettingsItem: ");
        this.mSettingItemIndeHashMap = new HashMap<>();
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingPageUiSet.getList().get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), Integer.valueOf(((i << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | i2));
            }
        }
    }

    private void getLeftAndRight(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            int iIntValue = this.mSettingItemIndeHashMap.get(str).intValue();
            this.mSettingLeftIndex = iIntValue >> 8;
            this.mSettingRightIndex = iIntValue & 255;
            Log.i("ljq", "getLeftAndRigth: left:" + this.mSettingLeftIndex + ", right:" + this.mSettingRightIndex);
            return;
        }
        this.mSettingLeftIndex = -1;
        this.mSettingRightIndex = -1;
    }

    private void initTireResolveTool() {
        this.mTireUpdateEntityList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            this.mTireUpdateEntityList.add(new TireUpdateEntity(i, 0, new String[0]));
        }
        ArrayList arrayList = new ArrayList();
        this.mTireResolveList = arrayList;
        arrayList.add(new TireResolve("bar", 0.1f));
        this.mTireResolveList.add(new TireResolve("psi", 0.5f));
        this.mTireResolveList.add(new TireResolve("kPa", 10.0f));
        this.mTireInfoArray = new String[4];
        this.mTireWarningArray = new String[4];
    }

    private class TireResolve {
        float calculate;
        String unit;

        public TireResolve(String str, float f) {
            this.unit = str;
            this.calculate = f;
        }
    }
}
