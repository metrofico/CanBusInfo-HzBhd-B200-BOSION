package com.hzbhd.canbus.car._340;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static int[] index;
    int[] mAirData;
    public byte[] mCanBusInfoByte;
    public int[] mCanBusInfoInt;
    private boolean flagIn = false;
    private boolean flagOut = false;
    private final int currentCanDifferentId = getCurrentCanDifferentId();
    private int lastParam = 0;
    public int nowLeftValue = 40;
    public int nowRightValue = 40;
    public final Map<String, Integer> settingPageIndex = new HashMap();
    private UiMgr mUiMgr = null;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        GeneralDoorData.isShowHandBrake = true;
        initSettingPageIndex(context);
        index = new int[]{((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_restore_setting"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_automatic_folding"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_tire_pressure"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_scenarios_rain_snow_mode"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_scenarios_smoking_mode"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_scenarios_cool_mode"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_scenarios_warm_mode"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_remote_control_window_lowering"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_remote_control_active_entry"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_turn_off_the_engine_and_unlock_the_door_automatically"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_locking_tips"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_automatic_trip_lock"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_lane_change_flashing_light"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_indoor_light_delay"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_accompany_me_home"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_accompany_me_home_delay"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_looking_for_my_car"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_wiper_maintenance_function"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_adaptive_cruise_speed_setting"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_anti_collision_warning"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_anti_collision_warning_sensitivity"))).intValue(), ((Integer) Objects.requireNonNull(this.settingPageIndex.get("_340_automatic_emergency_braking_system"))).intValue()};
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            int i2 = this.currentCanDifferentId;
            if (i2 == 8 || i2 == 9) {
                airInfo(context);
                return;
            }
            return;
        }
        if (i == 33) {
            steeringWheelKeyInfo(context);
            return;
        }
        if (i == 48) {
            espInfo(context);
            return;
        }
        if (i == 82) {
            if (this.currentCanDifferentId == 8) {
                vehicleSettingInfo();
            }
        } else {
            if (i != 127) {
                switch (i) {
                    case 38:
                        rearRadarInfo(context);
                        break;
                    case 39:
                        frontRadarInfo(context);
                        break;
                    case 40:
                        generalInfo(context);
                        break;
                }
                return;
            }
            versionInfo(context);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int i3;
        int lsb;
        int msb;
        super.radioInfoChange(i, str, str2, str3, i2);
        Log.i("lyn", str);
        str.hashCode();
        switch (str) {
            case "AM":
                i3 = 16;
                int i4 = Integer.parseInt(str2);
                lsb = getLsb(i4);
                msb = getMsb(i4);
                break;
            case "FM":
                int i5 = (int) (Double.parseDouble(str2) * 100.0d);
                lsb = getLsb(i5);
                msb = getMsb(i5);
                i3 = 0;
                break;
            case "AM1":
                i3 = 17;
                int i6 = Integer.parseInt(str2);
                lsb = getLsb(i6);
                msb = getMsb(i6);
                break;
            case "AM2":
                i3 = 18;
                int i7 = Integer.parseInt(str2);
                lsb = getLsb(i7);
                msb = getMsb(i7);
                break;
            case "AM3":
                i3 = 19;
                int i8 = Integer.parseInt(str2);
                lsb = getLsb(i8);
                msb = getMsb(i8);
                break;
            case "FM1":
                int i9 = (int) (Double.parseDouble(str2) * 100.0d);
                lsb = getLsb(i9);
                msb = getMsb(i9);
                i3 = 1;
                break;
            case "FM2":
                int i10 = (int) (Double.parseDouble(str2) * 100.0d);
                lsb = getLsb(i10);
                msb = getMsb(i10);
                i3 = 2;
                break;
            case "FM3":
                int i11 = (int) (Double.parseDouble(str2) * 100.0d);
                lsb = getLsb(i11);
                msb = getMsb(i11);
                i3 = 3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) i3, (byte) lsb, (byte) msb, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i11 = this.currentCanDifferentId;
        if (i11 == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 1});
        } else if (i11 == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 2});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        if (this.lastParam != z) {
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, -127});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, ByteCompanionObject.MIN_VALUE});
            }
            this.lastParam = z ? 1 : 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        sendPhoneData(1, bArr);
        this.flagIn = true;
        this.flagOut = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        sendPhoneData(3, bArr);
        this.flagIn = false;
        this.flagOut = true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        if (this.flagIn) {
            sendPhoneData(2, bArr);
        } else if (this.flagOut) {
            sendPhoneData(4, bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        sendPhoneData(0, bArr);
        this.flagIn = false;
        this.flagOut = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        byte[] bytes = str.getBytes();
        byte[] bytes2 = str2.getBytes();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 11, 18, 1, (byte) bytes.length}, bytes));
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 11, 18, 2, (byte) bytes2.length}, bytes2));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        if (b != 9) {
            byte[] bytes = str4.getBytes();
            byte[] bytes2 = str6.getBytes();
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 1, (byte) bytes.length}, bytes));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 8, 18, 2, (byte) bytes2.length}, bytes2));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, 7});
    }

    private void airInfo(Context context) {
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 0;
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = false;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = false;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = false;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = true;
        }
        if (this.mCanBusInfoInt[4] == 0) {
            GeneralAirData.front_wind_level = this.mCanBusInfoByte[4];
            GeneralAirData.power = false;
        } else {
            GeneralAirData.front_wind_level = this.mCanBusInfoByte[4];
        }
        int i2 = this.mCanBusInfoInt[5];
        if (i2 == 254) {
            GeneralAirData.front_left_temperature = "Low";
            this.nowLeftValue = com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
        } else if (i2 == 255) {
            GeneralAirData.front_left_temperature = "High";
            this.nowLeftValue = 255;
        } else if (i2 != 0) {
            this.nowLeftValue = i2;
            GeneralAirData.front_left_temperature = (this.mCanBusInfoInt[5] / 2.0d) + "°C";
            String str = GeneralAirData.front_left_temperature;
        } else {
            this.nowLeftValue = 20;
            GeneralAirData.front_left_temperature = "--";
        }
        int i3 = this.mCanBusInfoInt[6];
        if (i3 == 254) {
            GeneralAirData.front_right_temperature = "Low";
            this.nowRightValue = com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
        } else if (i3 == 255) {
            GeneralAirData.front_right_temperature = "High";
            this.nowRightValue = 255;
        } else if (i3 != 0) {
            this.nowRightValue = i3;
            GeneralAirData.front_right_temperature = (this.mCanBusInfoInt[6] / 2.0d) + "°C";
            String str2 = GeneralAirData.front_right_temperature;
        } else {
            this.nowRightValue = 20;
            GeneralAirData.front_right_temperature = "--";
        }
        updateAirActivity(context, 1001);
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void steeringWheelKeyInfo(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._340.MsgMgr.steeringWheelKeyInfo(android.content.Context):void");
    }

    private void rearRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void frontRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void generalInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        updateDoorView(context);
    }

    private void espInfo(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 8600, 16);
        updateParkUi(null, context);
    }

    private void vehicleSettingInfo() {
        ArrayList arrayList = new ArrayList();
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                arrayList.add(new SettingUpdateEntity(0, index[1], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 3:
                arrayList.add(new SettingUpdateEntity(0, index[3], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 4:
                arrayList.add(new SettingUpdateEntity(0, index[4], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 5:
                arrayList.add(new SettingUpdateEntity(0, index[5], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 6:
                arrayList.add(new SettingUpdateEntity(0, index[6], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 7:
                arrayList.add(new SettingUpdateEntity(0, index[7], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 8:
                arrayList.add(new SettingUpdateEntity(0, index[8], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 9:
                arrayList.add(new SettingUpdateEntity(0, index[9], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 10:
                arrayList.add(new SettingUpdateEntity(0, index[10], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 11:
                arrayList.add(new SettingUpdateEntity(0, index[11], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 12:
                arrayList.add(new SettingUpdateEntity(0, index[12], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 13:
                arrayList.add(new SettingUpdateEntity(0, index[13], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 14:
                arrayList.add(new SettingUpdateEntity(0, index[14], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 15:
                arrayList.add(new SettingUpdateEntity(0, index[15], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 16:
                arrayList.add(new SettingUpdateEntity(0, index[16], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 17:
                arrayList.add(new SettingUpdateEntity(0, index[17], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 18:
                arrayList.add(new SettingUpdateEntity(0, index[18], Integer.valueOf(((this.mCanBusInfoInt[3] - 1) * 5) + 30)).setProgress(this.mCanBusInfoInt[3] - 1));
                break;
            case 19:
                arrayList.add(new SettingUpdateEntity(0, index[19], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 20:
                arrayList.add(new SettingUpdateEntity(0, index[20], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 21:
                arrayList.add(new SettingUpdateEntity(0, index[21], Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void versionInfo(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    public Map<String, Integer> initSettingPageIndex(Context context) {
        List<SettingPageUiSet.ListBean> list = getUigMgr(context).getSettingUiSet(context).getList();
        Iterator<SettingPageUiSet.ListBean> it = list.iterator();
        for (int i = 0; i < list.size(); i++) {
            SettingPageUiSet.ListBean next = it.next();
            this.settingPageIndex.put(next.getTitleSrn(), Integer.valueOf(i));
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = next.getItemList();
            Iterator<SettingPageUiSet.ListBean.ItemListBean> it2 = itemList.iterator();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.settingPageIndex.put(it2.next().getTitleSrn(), Integer.valueOf(i2));
            }
        }
        return this.settingPageIndex;
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void sendPhoneData(int i, byte[] bArr) {
        String string = Arrays.toString(bArr);
        Log.i("lyn", string + "");
        Log.i("lyn", string.getBytes(StandardCharsets.UTF_8) + "");
        CanbusMsgSender.sendMsg(byteMerger(new byte[]{22, -64, 5, (byte) i, 18, 2, (byte) bArr.length}, bArr));
    }

    public byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private void knob(Context context, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            realKeyClick4(context, i);
        }
    }
}
