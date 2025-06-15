package com.hzbhd.canbus.car._315;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;


public class MsgMgr extends AbstractMsgMgr {
    public static String UPDATE_SETTING_ACTION = "update_setting_action";
    private BroadcastReceiver mBroadcastReceiver;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private final String TAG = "_315_MsgMgr";
    private byte[] m0x41Data = new byte[4];
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private List<TireUpdateEntity> mTireUpdateEntityList = new ArrayList();
    private List<List<String>> mTireInfoList = new ArrayList();
    private List<List<String>> mTireWarningList = new ArrayList();

    private boolean getIsUseFunit() {
        return SharePreUtil.getIntValue(this.mContext, Constant.SHARE_PRE_IS_USE_F_UNIT, 0) == 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        for (int i = 0; i < 4; i++) {
            this.mTireUpdateEntityList.add(new TireUpdateEntity(i, 0, new String[0]));
            ArrayList arrayList = new ArrayList();
            arrayList.add(CommUtil.getStrByResId(context, "_284_tire_temperature_default"));
            arrayList.add(CommUtil.getStrByResId(context, "_284_tire_pressure_default"));
            this.mTireInfoList.add(arrayList);
            this.mTireWarningList.add(new ArrayList());
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._315.MsgMgr.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MsgMgr.this.setSettingData();
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, new IntentFilter(UPDATE_SETTING_ACTION));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 40) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            set0x28BaseData();
            return;
        }
        if (i == 41) {
            set0x29TrackData(context);
            return;
        }
        if (i == 54) {
            set0x36AirData();
            return;
        }
        if (i == 65) {
            setSystemInfoData();
            return;
        }
        if (i == 127) {
            set0x7fVersionData();
            return;
        }
        if (i == 56) {
            set0x38TirePressureData();
            return;
        }
        if (i != 57) {
            switch (i) {
                case 32:
                    set0x20WheelKeyData(context);
                    break;
                case 33:
                    set0x21InterfaceKeyData(context);
                    break;
                case 34:
                    set0x22RearRadarData(context);
                    break;
                case 35:
                    set0x23FrontRadarData(context);
                    break;
                case 36:
                    set0x24AirData();
                    break;
            }
            return;
        }
        set0x39TirePressureWarning();
    }

    private void set0x20WheelKeyData(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = 2;
        switch (iArr[2]) {
            case 1:
                i = 7;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                i = 46;
                break;
            case 4:
                i = 45;
                break;
            case 5:
            case 8:
            default:
                i = 0;
                break;
            case 6:
                i = 3;
                break;
            case 7:
                break;
            case 9:
                i = 14;
                break;
            case 10:
                i = HotKeyConstant.K_VOICE_HANGUP;
                break;
        }
        realKeyLongClick1(context, i, iArr[3]);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:45:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set0x21InterfaceKeyData(android.content.Context r6) {
        /*
            r5 = this;
            int[] r0 = r5.mCanBusInfoInt
            r1 = 2
            r2 = r0[r1]
            r3 = 3
            r4 = 1
            switch(r2) {
                case 1: goto L6c;
                case 2: goto L69;
                case 3: goto L70;
                case 4: goto L5d;
                case 5: goto L5b;
                case 6: goto L58;
                case 7: goto L56;
                case 8: goto L5b;
                case 9: goto L4a;
                case 10: goto L3e;
                case 11: goto L32;
                case 12: goto L26;
                case 13: goto L1a;
                case 14: goto Lc;
                default: goto La;
            }
        La:
            goto L6f
        Lc:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L14
            r1 = 129(0x81, float:1.81E-43)
            goto L70
        L14:
            if (r2 != r4) goto L6f
            r1 = 38
            goto L70
        L1a:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L21
            r1 = 128(0x80, float:1.8E-43)
            goto L70
        L21:
            if (r2 != r4) goto L6f
            r1 = 37
            goto L70
        L26:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L2d
            r1 = 14
            goto L70
        L2d:
            if (r2 != r4) goto L6f
            r1 = 36
            goto L70
        L32:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L39
            r1 = 58
            goto L70
        L39:
            if (r2 != r4) goto L6f
            r1 = 35
            goto L70
        L3e:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L45
            r1 = 50
            goto L70
        L45:
            if (r2 != r4) goto L6f
            r1 = 34
            goto L70
        L4a:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L51
            r1 = 52
            goto L70
        L51:
            if (r2 != r4) goto L6f
            r1 = 33
            goto L70
        L56:
            r1 = 7
            goto L70
        L58:
            r1 = 8
            goto L70
        L5b:
            r1 = r3
            goto L70
        L5d:
            int r2 = r5.mDifferent
            if (r2 != r1) goto L64
            r1 = 59
            goto L70
        L64:
            if (r2 != r4) goto L6f
            r1 = 185(0xb9, float:2.59E-43)
            goto L70
        L69:
            r1 = 46
            goto L70
        L6c:
            r1 = 45
            goto L70
        L6f:
            r1 = 0
        L70:
            r0 = r0[r3]
            r5.realKeyLongClick1(r6, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._315.MsgMgr.set0x21InterfaceKeyData(android.content.Context):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int i) {
        if (i != 3) {
            return false;
        }
        realKeyClick(context, HotKeyConstant.K_SLEEP);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSettingData() {
        boolean isUseFunit = getIsUseFunit();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(isUseFunit ? 1 : 0)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x22RearRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], this.mCanBusInfoByte[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x23FrontRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], this.mCanBusInfoByte[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x24AirData() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[2] = iArr[2] & 239;
        if (isDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            LogUtil.showLog("power:" + GeneralAirData.power);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            LogUtil.showLog("in_out_cycle:" + GeneralAirData.in_out_cycle);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7);
            GeneralAirData.front_wind_level = intFromByteWithBit;
            LogUtil.showLog("windSpeed:" + intFromByteWithBit);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7);
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            if (intFromByteWithBit2 == 1) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else if (intFromByteWithBit2 == 2) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit2 == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (intFromByteWithBit2 == 4) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 7));
            } else {
                GeneralAirData.front_left_temperature = resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
            }
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
                GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7));
            } else {
                GeneralAirData.front_right_temperature = resolveTempLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 7));
            }
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x28BaseData() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void set0x29TrackData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) iArr[3], (byte) iArr[2], 0, 5570, 16);
            Log.i("cbc", "set0x29 !!!!!!!!!!!!!!!!!!!! " + GeneralParkData.trackAngle);
            updateParkUi(null, context);
        }
    }

    private void set0x36AirData() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void set0x38TirePressureData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            Iterator<TireUpdateEntity> it = this.mTireUpdateEntityList.iterator();
            while (it.hasNext()) {
                int whichTire = it.next().getWhichTire();
                List<String> list = this.mTireInfoList.get(whichTire);
                list.clear();
                int i = this.mCanBusInfoInt[whichTire + 2];
                String strByResId = CommUtil.getStrByResId(this.mContext, "_284_tire_temperature_default");
                if (i != 255) {
                    strByResId = (i - 40) + getTempUnitC(this.mContext);
                }
                list.add(strByResId);
                int i2 = this.mCanBusInfoInt[whichTire + 6];
                String strByResId2 = CommUtil.getStrByResId(this.mContext, "_284_tire_pressure_default");
                if (i2 != 255) {
                    strByResId2 = new DecimalFormat("0.0").format(i2 * 1.373f) + "KPa";
                }
                list.add(strByResId2);
                list.addAll(this.mTireWarningList.get(whichTire));
                this.mTireUpdateEntityList.get(whichTire).setList(list);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void set0x39TirePressureWarning() {
        if (isDataChange(this.mCanBusInfoInt)) {
            for (TireUpdateEntity tireUpdateEntity : this.mTireUpdateEntityList) {
                int whichTire = tireUpdateEntity.getWhichTire();
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.mTireInfoList.get(whichTire));
                List<String> list = this.mTireWarningList.get(whichTire);
                list.clear();
                int intFromByteWithBit = 0;
                tireUpdateEntity.setTireStatus(0);
                if (whichTire == 0) {
                    intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
                } else if (whichTire == 1) {
                    intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 3);
                } else if (whichTire == 2) {
                    intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 3);
                } else if (whichTire == 3) {
                    intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 3);
                }
                for (int i = 2; i >= 0; i--) {
                    if (((intFromByteWithBit >> i) & 1) == 1) {
                        list.add(CommUtil.getStrByResId(this.mContext, "_315_tire_alarm_" + i));
                        tireUpdateEntity.setTireStatus(1);
                    }
                }
                if (list.size() == 0) {
                    list.add(CommUtil.getStrByResId(this.mContext, "_315_tire_normal"));
                }
                while (3 > list.size()) {
                    list.add("");
                }
                arrayList.addAll(list);
                tireUpdateEntity.setList(arrayList);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void setSystemInfoData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
            byte[] bArr = this.mCanBusInfoByte;
            byte b = bArr[4];
            byte b2 = bArr[5];
            byte b3 = bArr[6];
            byte b4 = bArr[7];
            byte b5 = bArr[8];
            byte b6 = bArr[9];
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit - 1));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(b)));
            arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(b2)).setProgress(b2));
            arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(b3)).setProgress(b3));
            arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(b4)));
            arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(b5)));
            arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(b6)).setProgress(b6));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    byte[] get0x41Data() {
        return this.m0x41Data;
    }

    private void set0x7fVersionData() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private String resolveOutDoorTem() {
        String str;
        double intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (intFromByteWithBit >= 127.0d) {
            intFromByteWithBit = 127.0d;
        }
        if (getIsUseFunit()) {
            intFromByteWithBit = tempCToTempF(intFromByteWithBit);
        }
        if (!DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = intFromByteWithBit + "";
        } else {
            str = "-" + intFromByteWithBit;
        }
        if (getIsUseFunit()) {
            return str + getTempUnitF(this.mContext);
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveTempLevel(int i) {
        return 1 == i ? "LO" : 15 == i ? "HI" : (2 > i || i > 14) ? "" : i + "";
    }

    private String resolveLeftAndRightTemp(int i) {
        if (36 == i) {
            return "LO";
        }
        if (64 == i) {
            return "HI";
        }
        if (255 == i) {
            return this.mContext.getString(R.string.no_display);
        }
        if (37 > i || i > 63) {
            return "";
        }
        if (getIsUseFunit()) {
            return tempCToTempF(((i - 1) * 0.5d) + 0.5d) + getTempUnitF(this.mContext);
        }
        return (((i - 1) * 0.5d) + 0.5d) + getTempUnitC(this.mContext);
    }

    private double tempCToTempF(double d) {
        LogUtil.showLog("tempCToTempF:" + d);
        try {
            return Double.valueOf(new DecimalFormat("0.0").format((d * 1.8d) + 32.0d)).doubleValue();
        } catch (Exception e) {
            LogUtil.showLog("Exception:" + e);
            return 0.0d;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -55, (byte) i6, (byte) i5, z ? (byte) 1 : (byte) 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) DataHandleUtils.rangeNumber(i, 30)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -58, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        boolean zContains = str.contains("AM");
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -58, zContains ? (byte) 1 : (byte) 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte) i, 0, (byte) freqByteHiLo[0], (byte) freqByteHiLo[1], 0});
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = b == 9 ? 3 : 2;
        int iRangeNumber = DataHandleUtils.rangeNumber((b7 << 8) | i, 255);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -58, (byte) i4, (byte) (iRangeNumber >> 8), (byte) (iRangeNumber & 255), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        int i5 = b == 9 ? 3 : 2;
        int iRangeNumber = DataHandleUtils.rangeNumber((b6 << 8) | i, 255);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -58, (byte) i5, (byte) (iRangeNumber >> 8), (byte) (iRangeNumber & 255), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -58, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -58, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    void updateSettingItem(int i, int i2, Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, obj));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
