package com.hzbhd.canbus.car._334;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.NotificationManagerCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    TextView content;
    public int currentCanDifferentId;
    private OriginalDeviceData data;
    AlertDialog dialog;
    Button i_know;
    private int[] mAmplifierDataNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    Context mContext;
    private List<SongListEntity> mSongList;
    private int mVolume;
    UiMgr uiMgr;
    View view;
    private final DecimalFormat df = new DecimalFormat("0.0");
    final String fm = "FM";
    final String am = "AM";
    public String pageFlag = "null";
    private final String cd = "CD";
    private final String dvd = "DVD";
    private final String radio = "Radio";
    private final String[] tire0 = new String[1];
    private final String[] tire1 = new String[1];
    private final String[] tire2 = new String[1];
    private final String[] tire3 = new String[1];
    private final List<TireUpdateEntity> tireInfoList = new ArrayList();
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet = null;
    private String TAG = "lyn";
    int flag = 0;
    private int mPlayingIndex = -1;
    String nowRunningState = "";

    private void cdListInfo(Context context) {
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private void touchCoordinateInfo() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.currentCanDifferentId = getCurrentCanDifferentId();
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        GeneralTireData.isHaveSpareTire = false;
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        if (((List) Objects.requireNonNull(getUiMgr(context).cmd_carId_Map.get(Integer.valueOf(this.mCanBusInfoInt[1])))).contains(Integer.valueOf(this.currentCanDifferentId))) {
            int i = this.mCanBusInfoInt[1];
            if (i == 48) {
                mileageNextServiceDate(context);
                return;
            }
            if (i == 49) {
                maintenanceInfo(context);
                return;
            }
            if (i == 64) {
                hudStatus(context);
                return;
            }
            if (i == 65) {
                settingItemStatus(context);
                return;
            }
            if (i != 127) {
                switch (i) {
                    case 33:
                        realKeyInfo(context);
                        break;
                    case 34:
                        panelButtonInfo(context);
                        break;
                    case 35:
                        rearRadarInfo(context);
                        break;
                    case 36:
                        frontRadarInfo(context);
                        break;
                    case 37:
                        touchCoordinateInfo();
                        break;
                    case 38:
                        tirePressureInfo();
                        break;
                    case 39:
                        tirePressureUpdateTime();
                        break;
                    case 40:
                        generalInfo(context);
                        break;
                    case 41:
                        cornerInfo(context);
                        break;
                    default:
                        switch (i) {
                            case 80:
                                fuelAverageInfo(context);
                                break;
                            case 81:
                                fuelPerMinuteInfo(context);
                                break;
                            case 82:
                                i_stopInfo(context);
                                break;
                            default:
                                switch (i) {
                                    case 96:
                                        cd_dvd_Status(context);
                                        break;
                                    case 97:
                                        cd_dvd_MediaInfo(context);
                                        break;
                                    case 98:
                                        cdCharInfo(context);
                                        break;
                                    case 99:
                                        cdListInfo(context);
                                        break;
                                    default:
                                        switch (i) {
                                            case 112:
                                                amplifierSettingStatus(context);
                                                break;
                                            case 113:
                                                amplifierSoundsStatus(context);
                                                break;
                                            case 114:
                                                radioStatus(context);
                                                break;
                                            case 115:
                                                radioListInfo(context);
                                                break;
                                            case 116:
                                                securitySettingStatus(context);
                                                break;
                                        }
                                }
                        }
                }
                return;
            }
            versionInfo(context);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.mVolume = i;
    }

    private void maintenanceInfo(Context context) {
        if (this.flag == 0) {
            getUiMgr(context).initSettingPageIndex();
            this.flag++;
        }
        ArrayList arrayList = new ArrayList();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        int[] iArr = this.mCanBusInfoInt;
        int msbLsbResult = getMsbLsbResult(iArr[3], iArr[4]);
        int[] iArr2 = this.mCanBusInfoInt;
        int msbLsbResult2 = getMsbLsbResult(iArr2[6], iArr2[7]);
        int[] iArr3 = this.mCanBusInfoInt;
        int msbLsbResult3 = getMsbLsbResult(iArr3[9], iArr3[10]);
        int iIntValue = ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_maintenance_info"))).intValue();
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_scheduled_maintenance_switch"))).intValue(), Integer.valueOf(boolBit7 ? 1 : 0)));
        Log.i(this.TAG, iIntValue + " " + Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_scheduled_maintenance_switch")));
        if (boolBit7) {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_scheduled_maintenance_time"))).intValue()).setEnable(true).setValue(Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit - 1));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_scheduled_maintenance_distance"))).intValue()).setEnable(true).setValue(Integer.valueOf(msbLsbResult)).setProgress(msbLsbResult + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED));
        } else {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_scheduled_maintenance_time"))).intValue()).setEnable(false));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_scheduled_maintenance_distance"))).intValue()).setEnable(false));
        }
        boolean boolBit72 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_tire_rotation_switch"))).intValue(), Integer.valueOf(boolBit72 ? 1 : 0)));
        if (boolBit72) {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_tire_rotation_distance"))).intValue()).setEnable(true).setValue(Integer.valueOf(msbLsbResult2)).setProgress(msbLsbResult2 + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED));
        } else {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_tire_rotation_distance"))).intValue()).setEnable(false));
        }
        boolean boolBit73 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_oil_change_switch"))).intValue(), Integer.valueOf(boolBit73 ? 1 : 0)));
        if (boolBit73) {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_oil_change_distance"))).intValue()).setEnable(true).setValue(Integer.valueOf(msbLsbResult3)).setProgress(msbLsbResult3 + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED));
        } else {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_oil_change_distance"))).intValue()).setEnable(false));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void securitySettingStatus(Context context) {
        if (this.flag == 0) {
            getUiMgr(context).initSettingPageIndex();
            this.flag++;
        }
        int iIntValue = ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_security_setting_status_info"))).intValue();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_smart_city_brake"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_rear_vehicle_monitoring_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_smart_city_brake_distance"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_smart_city_brake_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_distance_recognition_display"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_distance_recognition_distance"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_sbs_display"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_sbs_distance"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_sbs_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_blind_spot_monitoring"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_warning_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system_time"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system_warning"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system_voice"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system_rumble"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_speed_limit_display"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_speed_limit_warning"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_warning_threshold"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system_intervene"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1) == 1) {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lane_departure_warning_system_voice"))).intValue(), 2));
        }
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_cruise_operation_tone"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_parking_sensor_display_guide"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_rear_parking_alarm_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 3) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_pedal_misuse_alarm"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1) == 1 ? 0 : 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_mazda_radar"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_rear_intersection_traffic_alert"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_bind_spot_monitoring"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_alarm_timing"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 3, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_alarm_type"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_auto_display_view"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_dynamic_route"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_front_camera_view"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 1))));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_security_alert_lane_departure_warning"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 6, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_security_alert_intersection_traffic_ahead"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_front_parking_sensor_alarm_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 5, 3) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_accident_avoidance_alarm_timing"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_active_safety_assistance"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 2) - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void radioListInfo(Context context) {
        if (this.mCanBusInfoInt[2] == 0) {
            StringBuilder sbAppend = new StringBuilder().append(this.nowRunningState).append("Update List：").append(this.mCanBusInfoInt[3]).append(" frequency：");
            int[] iArr = this.mCanBusInfoInt;
            GeneralOriginalCarDeviceData.runningState = sbAppend.append(getMsbLsbResult(iArr[4], iArr[5])).append("MHz").toString();
        } else {
            StringBuilder sbAppend2 = new StringBuilder().append(this.nowRunningState).append("Update List：").append(this.mCanBusInfoInt[3]).append(" frequency：");
            int[] iArr2 = this.mCanBusInfoInt;
            GeneralOriginalCarDeviceData.runningState = sbAppend2.append(getMsbLsbResult(iArr2[4], iArr2[5])).append("KHz").toString();
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void radioStatus(Context context) {
        setRadioPage(context);
        ArrayList arrayList = new ArrayList();
        String str = "AM";
        if (this.mCanBusInfoInt[2] == 0) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, "FM"));
            str = "FM";
        } else {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, "AM"));
        }
        switch (this.mCanBusInfoInt[3]) {
            case 0:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state0);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state0);
                break;
            case 1:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state1);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state1);
                break;
            case 2:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state2);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state2);
                break;
            case 3:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state3);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state3);
                break;
            case 4:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state4);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state4);
                break;
            case 5:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state5);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state5);
                break;
            case 6:
                this.nowRunningState = this.mContext.getString(R.string._334_radio_state6);
                GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._334_radio_state6);
                break;
        }
        if (str.equals("FM")) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb.append(((getMsbLsbResult(iArr[4], iArr[5]) - 1) * 0.05d) + 87.5d).append(" MHz").toString()));
        } else {
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append(((getMsbLsbResult(iArr2[4], iArr2[5]) - 1) * 9) + 522).append(" KHz").toString()));
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void amplifierSoundsStatus(Context context) {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state0"), context.getString(R.string._334_amp_state01)));
        } else {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state0"), context.getString(R.string._334_amp_state00)));
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state1"), context.getString(R.string._334_amp_state11)));
        } else {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state1"), context.getString(R.string._334_amp_state10)));
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6) + "LEVER"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state3"), getMediaState(this.mCanBusInfoInt[3])));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state4"), context.getString(R.string._334_amp_state41)));
        } else {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_amp_state"), getUiMgr(context).getDrivingItemIndexes(context, "_334_amp_state4"), context.getString(R.string._334_amp_state40)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getMediaState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._334_amp_state30);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._334_amp_state31);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._334_amp_state32);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._334_amp_state33);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._334_amp_state34);
        }
        if (i == 5) {
            return this.mContext.getString(R.string._334_amp_state35);
        }
        return this.mContext.getString(R.string._334_amp_state30);
    }

    private void amplifierSettingStatus(Context context) {
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[2];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[3];
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 8;
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[5] - 8;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[7];
        updateAmplifierActivity(null);
    }

    private void versionInfo(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    private void songListSetSelected() {
        int[] iArr = this.mCanBusInfoInt;
        this.mPlayingIndex = (iArr[3] * 256) + iArr[4];
        if (GeneralOriginalCarDeviceData.songList != null) {
            Iterator<SongListEntity> it = GeneralOriginalCarDeviceData.songList.iterator();
            while (it.hasNext()) {
                it.next().setSelected(false);
            }
            if (GeneralOriginalCarDeviceData.songList.size() > this.mPlayingIndex) {
                GeneralOriginalCarDeviceData.songList.get(this.mPlayingIndex).setSelected(true);
            }
        }
    }

    private void cdCharInfo(Context context) {
        setCdPage(context);
        ArrayList arrayList = new ArrayList();
        Log.i("lyn", "ListArray " + ((Object[]) Objects.requireNonNull(getUiMgr(context).cdPageList.toArray())).length);
        int length = ((Object[]) Objects.requireNonNull(getUiMgr(context).cdPageList.toArray())).length;
        int i = this.mCanBusInfoInt[2];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    if (length == 2) {
                        getUiMgr(context).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_music", "_334_song_title", null));
                        Log.i("lyn", "add success");
                    } else {
                        getUiMgr(context).cdPageList.remove(2);
                        getUiMgr(context).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_music", "_334_song_title", null));
                    }
                }
            } else if (length == 2) {
                getUiMgr(context).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_dvd", "_334_disc_name", null));
                Log.i("lyn", "add success");
            } else {
                getUiMgr(context).cdPageList.remove(2);
                getUiMgr(context).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_dvd", "_334_disc_name", null));
            }
        } else if (length == 2) {
            getUiMgr(context).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_artist", "_334_singer", null));
            Log.i("lyn", "add success");
        } else {
            getUiMgr(context).cdPageList.remove(2);
            getUiMgr(context).cdPageList.add(2, new OriginalCarDevicePageUiSet.Item("music_artist", "_334_singer", null));
        }
        int i2 = 0;
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            int i3 = 4;
            int length2 = this.mCanBusInfoByte.length - 4;
            byte[] bArr = new byte[length2];
            while (i2 < length2) {
                bArr[i2] = this.mCanBusInfoByte[i3];
                i2++;
                i3++;
            }
            try {
                String str = new String(bArr, "GBK");
                arrayList.add(new OriginalCarDeviceUpdateEntity(2, str));
                Log.i("lyn", str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
        } else {
            ((OriginalDeviceData) Objects.requireNonNull(getUiMgr(context).pageMap.get(0))).getItemList().remove(2);
        }
        updateOriginalDevicePage();
    }

    private void cd_dvd_MediaInfo(Context context) {
        setCdPage(context);
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            GeneralOriginalCarDeviceData.runningState = context.getString(R.string._334_stop_close);
        } else if (i == 1) {
            GeneralOriginalCarDeviceData.runningState = context.getString(R.string._334_pause);
        } else if (i == 2) {
            GeneralOriginalCarDeviceData.runningState = context.getString(R.string._334_play);
        } else if (i == 3) {
            GeneralOriginalCarDeviceData.runningState = context.getString(R.string._334_disk_reading);
        } else if (i == 4) {
            GeneralOriginalCarDeviceData.runningState = context.getString(R.string._334_disk_exit);
        } else if (i == 5) {
            GeneralOriginalCarDeviceData.runningState = context.getString(R.string._334_disk_insertion);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 1) {
            GeneralOriginalCarDeviceData.rpt = true;
        } else {
            GeneralOriginalCarDeviceData.rpt = false;
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1) == 1) {
            GeneralOriginalCarDeviceData.rdm = true;
        } else {
            GeneralOriginalCarDeviceData.rdm = false;
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1) == 1) {
            GeneralOriginalCarDeviceData.scan = true;
        } else {
            GeneralOriginalCarDeviceData.scan = false;
        }
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, String.valueOf(getMsbLsbResult(iArr[4], iArr[5]))));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, String.valueOf(getMsbLsbResult(iArr2[6], iArr2[7]))));
        String str = this.mCanBusInfoInt[11] + ":" + this.mCanBusInfoInt[12] + ":" + this.mCanBusInfoInt[13];
        String str2 = this.mCanBusInfoInt[8] + ":" + this.mCanBusInfoInt[9] + ":" + this.mCanBusInfoInt[10];
        GeneralOriginalCarDeviceData.startTime = str;
        GeneralOriginalCarDeviceData.endTime = str2;
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralOriginalCarDeviceData.progress = progress(iArr3[11], iArr3[12], iArr3[13], iArr3[8], iArr3[9], iArr3[10]);
        GeneralOriginalCarDeviceData.mList = arrayList;
        setCdPage(context);
        enterAuxIn2();
    }

    private void cd_dvd_Status(Context context) {
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1) {
            realKeyClick4(context, 50);
        } else {
            setCdPage(context);
            enterAuxIn2();
        }
    }

    private void updateOriginalDevicePage() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void i_stopInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 0, CommUtil.getStrByResId(context, !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "_334_i_stop_running" : "_334_i_stop_not_running")));
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 1, CommUtil.getStrByResId(context, !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? "_334_i_stop_unprepared" : "_334_i_stop_prepared")));
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 2, !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ? CommUtil.getStrByResId(context, "_334_i_stop_not_read") : CommUtil.getStrByResId(context, "_334_i_stop_read")));
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 3, !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) ? CommUtil.getStrByResId(context, "_334_i_stop_not_read") : CommUtil.getStrByResId(context, "_334_i_stop_read")));
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 4, !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? CommUtil.getStrByResId(context, "_334_i_stop_not_read") : CommUtil.getStrByResId(context, "_334_i_stop_read")));
        int i = this.mCanBusInfoInt[3];
        if (i == 0) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 5, CommUtil.getStrByResId(context, "_334_i_stop_nothing")));
        } else if (i == 1) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 5, CommUtil.getStrByResId(context, "_334_i_stop_available")));
        } else if (i == 2) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 5, CommUtil.getStrByResId(context, "_334_i_stop_more_pressure")));
        } else if (i == 3) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 5, CommUtil.getStrByResId(context, "_334_i_stop_shift_to_neutral")));
        } else if (i == 4) {
            arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 5, CommUtil.getStrByResId(context, "_334_i_stop_cant_activate")));
        }
        int drivingPageIndexes = getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, 6, timeFormat(getMsbLsbResult(iArr[4], iArr[5]), false)));
        int drivingPageIndexes2 = getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information");
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, 7, timeFormat(getMsbLsbResult(iArr2[6], iArr2[7]), false)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information"), 8, this.mCanBusInfoInt[8] + " %"));
        int drivingPageIndexes3 = getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information");
        StringBuilder sb = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, 9, sb.append(getMsbLsbResult(iArr3[9], iArr3[10]) * 0.1d).append(" km").toString()));
        int drivingPageIndexes4 = getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information");
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, 10, timeFormat(getMsbLsbResult(iArr4[11], iArr4[12]), true)));
        int drivingPageIndexes5 = getUiMgr(context).getDrivingPageIndexes(context, "_334_i_stop_information");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, 11, sb2.append(getMsbLsbResult(iArr5[13], iArr5[14]) * 0.1d).append(" L/100km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void fuelPerMinuteInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, 0, sb.append(decimalFormat.format(getMsbLsbResult(iArr[2], iArr[3]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes2 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb2 = new StringBuilder();
        DecimalFormat decimalFormat2 = this.df;
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, 1, sb2.append(decimalFormat2.format(getMsbLsbResult(iArr2[4], iArr2[5]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes3 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb3 = new StringBuilder();
        DecimalFormat decimalFormat3 = this.df;
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, 2, sb3.append(decimalFormat3.format(getMsbLsbResult(iArr3[6], iArr3[7]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes4 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb4 = new StringBuilder();
        DecimalFormat decimalFormat4 = this.df;
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, 3, sb4.append(decimalFormat4.format(getMsbLsbResult(iArr4[8], iArr4[9]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes5 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb5 = new StringBuilder();
        DecimalFormat decimalFormat5 = this.df;
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, 4, sb5.append(decimalFormat5.format(getMsbLsbResult(iArr5[10], iArr5[11]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes6 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb6 = new StringBuilder();
        DecimalFormat decimalFormat6 = this.df;
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes6, 5, sb6.append(decimalFormat6.format(getMsbLsbResult(iArr6[12], iArr6[13]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes7 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb7 = new StringBuilder();
        DecimalFormat decimalFormat7 = this.df;
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes7, 6, sb7.append(decimalFormat7.format(getMsbLsbResult(iArr7[14], iArr7[15]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes8 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb8 = new StringBuilder();
        DecimalFormat decimalFormat8 = this.df;
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes8, 7, sb8.append(decimalFormat8.format(getMsbLsbResult(iArr8[16], iArr8[17]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes9 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb9 = new StringBuilder();
        DecimalFormat decimalFormat9 = this.df;
        int[] iArr9 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes9, 8, sb9.append(decimalFormat9.format(getMsbLsbResult(iArr9[18], iArr9[19]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes10 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb10 = new StringBuilder();
        DecimalFormat decimalFormat10 = this.df;
        int[] iArr10 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes10, 9, sb10.append(decimalFormat10.format(getMsbLsbResult(iArr10[20], iArr10[21]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes11 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb11 = new StringBuilder();
        DecimalFormat decimalFormat11 = this.df;
        int[] iArr11 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes11, 10, sb11.append(decimalFormat11.format(getMsbLsbResult(iArr11[22], iArr11[23]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes12 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb12 = new StringBuilder();
        DecimalFormat decimalFormat12 = this.df;
        int[] iArr12 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes12, 11, sb12.append(decimalFormat12.format(getMsbLsbResult(iArr12[24], iArr12[25]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes13 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb13 = new StringBuilder();
        DecimalFormat decimalFormat13 = this.df;
        int[] iArr13 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes13, 12, sb13.append(decimalFormat13.format(getMsbLsbResult(iArr13[26], iArr13[27]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes14 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb14 = new StringBuilder();
        DecimalFormat decimalFormat14 = this.df;
        int[] iArr14 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes14, 13, sb14.append(decimalFormat14.format(getMsbLsbResult(iArr14[28], iArr14[29]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes15 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_9");
        StringBuilder sb15 = new StringBuilder();
        DecimalFormat decimalFormat15 = this.df;
        int[] iArr15 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes15, 14, sb15.append(decimalFormat15.format(getMsbLsbResult(iArr15[30], iArr15[31]) / 10)).append(" L/100km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void fuelAverageInfo(Context context) {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, 0, sb.append(decimalFormat.format(getMsbLsbResult(iArr[2], iArr[3]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes2 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb2 = new StringBuilder();
        DecimalFormat decimalFormat2 = this.df;
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, 1, sb2.append(decimalFormat2.format(getMsbLsbResult(iArr2[4], iArr2[5]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes3 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb3 = new StringBuilder();
        DecimalFormat decimalFormat3 = this.df;
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, 2, sb3.append(decimalFormat3.format(getMsbLsbResult(iArr3[6], iArr3[7]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes4 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb4 = new StringBuilder();
        DecimalFormat decimalFormat4 = this.df;
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, 3, sb4.append(decimalFormat4.format(getMsbLsbResult(iArr4[8], iArr4[9]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes5 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb5 = new StringBuilder();
        DecimalFormat decimalFormat5 = this.df;
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, 4, sb5.append(decimalFormat5.format(getMsbLsbResult(iArr5[10], iArr5[11]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes6 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb6 = new StringBuilder();
        DecimalFormat decimalFormat6 = this.df;
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes6, 5, sb6.append(decimalFormat6.format(getMsbLsbResult(iArr6[12], iArr6[13]) / 10)).append(" L/100km").toString()));
        int drivingPageIndexes7 = getUiMgr(context).getDrivingPageIndexes(context, "_334_fuel_info_1");
        StringBuilder sb7 = new StringBuilder();
        DecimalFormat decimalFormat7 = this.df;
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes7, 6, sb7.append(decimalFormat7.format(getMsbLsbResult(iArr7[16], iArr7[17]) / 10)).append(" L/100km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void settingItemStatus(Context context) {
        if (this.flag == 0) {
            getUiMgr(context).initSettingPageIndex();
            this.flag++;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_door_lock"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_auto_lock"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_door_lock"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_auto_re_lock"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_door_lock"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_unlock_mode"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_door_lock"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_door_lock"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lock_leave_car"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        if (this.currentCanDifferentId == 12) {
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_door_lock"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_tail_door"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) - 1)));
        }
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_turn"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_turn_signal"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_turn"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_turn_signal_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lighting"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_1_lights_off_automatically"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lighting"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_2_lights_off_automatically"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lighting"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lights_reminder"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lighting"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_adaptive_headlamp"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lighting"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_headlamp_off_timer"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_lighting"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_headlamp_on_auto"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 3))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_1"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_rain_sensor"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_1"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_language_state"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_1"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_blind_area_prompt_volume"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2))));
        int i = this.currentCanDifferentId;
        if (i == 10 || i == 12) {
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_1"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_rear_window_demister"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1))));
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_1"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_automatic_lock_time"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3))));
        }
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_2"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_synchronized_1"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
        int i2 = this.currentCanDifferentId;
        if (i2 == 10 || i2 == 12) {
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_2"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_rear_vision_mirror"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_2"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_notification_warning"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2))));
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_2"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_Instrument_custom_display"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2))));
        }
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_3"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_leaving_home_light"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_3"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_coming_home_light"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 3))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_3"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_distance_unit"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 3, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_3"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_temperature_unit"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 1))));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_3"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_high_beam_control"))).intValue(), Integer.valueOf(!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[9]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_other_3"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_day_light"))).intValue(), Integer.valueOf(1 ^ (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9]) ? 1 : 0))));
        if (this.currentCanDifferentId == 11) {
            arrayList.add(new SettingUpdateEntity(((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_environmental_lighting_title"))).intValue(), ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_environmental_lighting_item"))).intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 2))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void hudStatus(Context context) {
        if (this.flag == 0) {
            getUiMgr(context).initSettingPageIndex();
            this.flag++;
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = iArr[4];
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 7);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 4);
        int iIntValue = ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud"))).intValue();
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_height"))).intValue(), Integer.valueOf(i - 15)).setProgress(i));
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_brightness_control"))).intValue(), Integer.valueOf(boolBit7 ? 1 : 0)));
        if (boolBit7) {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_brightness"))).intValue(), Integer.valueOf(i2 - 20)).setEnable(true).setProgress(i2));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_calibration"))).intValue(), Integer.valueOf(i3 - 2)).setEnable(false).setProgress(i3));
        } else {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_brightness"))).intValue(), Integer.valueOf(i2 - 20)).setEnable(false).setProgress(i2));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_calibration"))).intValue(), Integer.valueOf(i3 - 2)).setEnable(true).setProgress(i3));
        }
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_rotate"))).intValue(), Integer.valueOf(intFromByteWithBit - 3)).setProgress(intFromByteWithBit));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_active_display"))).intValue(), Integer.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_hud_tilt"))).intValue(), Integer.valueOf(intFromByteWithBit2 - 5)).setProgress(intFromByteWithBit2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void cornerInfo(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 540, 16);
        updateParkUi(null, context);
    }

    private void generalInfo(Context context) {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isShowFuelWarning = true;
        GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            showDialog(getActivity(), getActivity().getString(R.string._334_door_ajar2), true);
        } else {
            showDialog(getActivity(), getActivity().getString(R.string._334_door_ajar2), false);
        }
        updateDoorView(context);
    }

    public void showDialog(Context context, String str, boolean z) {
        if (this.view == null) {
            this.view = LayoutInflater.from(context).inflate(R.layout._333_alert_dialog, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(context).setView(this.view).create();
        }
        if (this.content == null) {
            this.content = (TextView) this.view.findViewById(R.id.alert_content);
        }
        this.content.setText(str);
        if (this.i_know == null) {
            this.i_know = (Button) this.view.findViewById(R.id.i_know);
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._334.MsgMgr.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MsgMgr.this.dialog.dismiss();
            }
        });
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        if (z) {
            this.dialog.show();
        } else {
            this.dialog.dismiss();
        }
    }

    private void frontRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void rearRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private void mileageNextServiceDate(Context context) {
        if (this.flag == 0) {
            getUiMgr(context).initSettingPageIndex();
            this.flag++;
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        int i2 = iArr[4];
        int i3 = iArr[5];
        int msbLsbResult = getMsbLsbResult(iArr[6], iArr[7]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        int iIntValue = ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service"))).intValue();
        arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_manual_auto"))).intValue(), Integer.valueOf(boolBit0 ? 1 : 0)));
        if (boolBit0) {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_year"))).intValue()).setEnable(true).setValue(Integer.valueOf(i + 2018)).setProgress(i));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_month"))).intValue()).setEnable(true).setValue(Integer.valueOf(i2)).setProgress(i2 - 1));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_day"))).intValue()).setEnable(true).setValue(Integer.valueOf(i3)).setProgress(i3 - 1));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_mileage"))).intValue()).setEnable(true).setValue(Integer.valueOf(msbLsbResult)).setProgress(msbLsbResult - 400));
        } else {
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_year"))).intValue()).setEnable(false));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_month"))).intValue()).setEnable(false));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_day"))).intValue()).setEnable(false));
            arrayList.add(new SettingUpdateEntity(iIntValue, ((Integer) Objects.requireNonNull(getUiMgr(context).settingPageIndex.get("_334_next_service_mileage"))).intValue()).setEnable(false));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void tirePressureUpdateTime() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, String.valueOf(this.mCanBusInfoInt[2] + 2018)));
        arrayList.add(new DriverUpdateEntity(0, 1, String.valueOf(this.mCanBusInfoInt[3])));
        arrayList.add(new DriverUpdateEntity(0, 2, String.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new DriverUpdateEntity(0, 3, String.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new DriverUpdateEntity(0, 4, String.valueOf(this.mCanBusInfoInt[6])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void tirePressureInfo() {
        this.tire0[0] = (this.mCanBusInfoInt[2] * 2) + "KPa";
        this.tire1[0] = (this.mCanBusInfoInt[3] * 2) + "KPa";
        this.tire2[0] = (this.mCanBusInfoInt[4] * 2) + "KPa";
        this.tire3[0] = (this.mCanBusInfoInt[5] * 2) + "KPa";
        this.tireInfoList.add(new TireUpdateEntity(0, 0, this.tire0));
        this.tireInfoList.add(new TireUpdateEntity(1, 0, this.tire1));
        this.tireInfoList.add(new TireUpdateEntity(2, 0, this.tire2));
        this.tireInfoList.add(new TireUpdateEntity(3, 0, this.tire3));
        GeneralTireData.dataList = this.tireInfoList;
        updateTirePressureActivity(null);
    }

    private void panelButtonInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(context, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(context, 128, iArr[3]);
                break;
            case 2:
                if (this.mVolume > 0) {
                    volumeKnob(context, 2, iArr[3]);
                    break;
                }
                break;
            case 3:
                if (this.mVolume < 40) {
                    volumeKnob(context, 3, iArr[3]);
                    break;
                }
                break;
            case 4:
                int i = iArr[3];
                if (i == 1) {
                    realKeyLongClick1(context, 3, i);
                }
                int i2 = this.mCanBusInfoInt[3];
                if (i2 == 2) {
                    realKeyLongClick1(context, HotKeyConstant.K_SLEEP, i2);
                    break;
                }
                break;
            case 5:
                realKeyLongClick1(context, 52, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(context, 50, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(context, 79, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(context, 59, iArr[3]);
                break;
            case 9:
                realKeyClick3(context, 33, 0, iArr[3]);
                break;
            case 10:
                realKeyClick3(context, 34, 0, iArr[3]);
                break;
            case 11:
                realKeyClick3(context, 35, 0, iArr[3]);
                break;
            case 12:
                realKeyClick3(context, 36, 0, iArr[3]);
                break;
            case 13:
                realKeyClick3(context, 37, 0, iArr[3]);
                break;
            case 14:
                realKeyClick3(context, 38, 0, iArr[3]);
                break;
            case 15:
                realKeyClick3(context, 39, 0, iArr[3]);
                break;
            case 16:
                realKeyLongClick1(context, 21, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(context, 20, iArr[3]);
                break;
        }
    }

    private void realKeyInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 9) {
            realKeyLongClick1(context, 14, iArr[3]);
            return;
        }
        if (i == 10) {
            realKeyLongClick1(context, 15, iArr[3]);
            return;
        }
        if (i != 16) {
            switch (i) {
                case 0:
                    realKeyLongClick1(context, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(context, 7, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(context, 8, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(context, 21, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(context, 20, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(context, 3, iArr[3]);
                    break;
                case 6:
                    if (((List) Objects.requireNonNull(getUiMgr(context).cmd_carId_Map.get(2215942))).contains(Integer.valueOf(this.currentCanDifferentId))) {
                        realKeyLongClick1(context, 2, this.mCanBusInfoInt[3]);
                        break;
                    } else {
                        realKeyLongClick1(context, 39, this.mCanBusInfoInt[3]);
                        break;
                    }
            }
            return;
        }
        if (((List) Objects.requireNonNull(getUiMgr(context).cmd_carId_Map.get(2215952))).contains(Integer.valueOf(this.currentCanDifferentId))) {
            realKeyLongClick1(context, HotKeyConstant.K_SPEECH, this.mCanBusInfoInt[3]);
        }
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    public void setCdPage(Context context) {
        if (!this.pageFlag.equals("CD")) {
            if (this.mSongList == null) {
                this.mSongList = new ArrayList();
            }
            this.pageFlag = "CD";
            this.data = getUiMgr(context).pageMap.get(0);
            GeneralOriginalCarDeviceData.cdStatus = "CD";
            OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
            originalCarDevicePageUiSet.setItems(this.data.getItemList());
            originalCarDevicePageUiSet.setRowTopBtnAction(this.data.getTopBtn());
            originalCarDevicePageUiSet.setRowBottomBtnAction(this.data.getBottomBtn());
            originalCarDevicePageUiSet.setHavePlayTimeSeekBar(true);
            originalCarDevicePageUiSet.setHaveSongList(false);
        }
        updateOriginalDevicePage();
    }

    public void setRadioPage(Context context) {
        if (this.pageFlag.equals("Radio")) {
            return;
        }
        this.pageFlag = "Radio";
        GeneralOriginalCarDeviceData.cdStatus = "Radio";
        this.data = getUiMgr(context).pageMap.get(2);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
        originalCarDevicePageUiSet.setItems(this.data.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(this.data.getBottomBtn());
        originalCarDevicePageUiSet.setRowTopBtnAction(this.data.getTopBtn());
        originalCarDevicePageUiSet.setHavePlayTimeSeekBar(false);
        originalCarDevicePageUiSet.setHaveSongList(false);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
        enterAuxIn2();
    }

    private void volumeKnob(Context context, int i, int i2) {
        if (i == 2) {
            for (int i3 = 0; i3 < i2; i3++) {
                realKeyClick4(context, 8);
            }
        }
        if (i == 3) {
            for (int i4 = 0; i4 < i2; i4++) {
                realKeyClick4(context, 7);
            }
        }
    }

    private boolean isAmplifierDataChange(int[] iArr) {
        if (Arrays.equals(iArr, this.mAmplifierDataNow)) {
            return false;
        }
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private int progress(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = (i4 * 3600) + (i5 * 60) + i6;
        if (i7 != 0) {
            return ((((i * 3600) + (i2 * 60)) + i3) * 100) / i7;
        }
        return 0;
    }

    private String timeFormat(int i, boolean z) {
        if (z) {
            int i2 = i / 60;
            return i2 + "h" + (i - (i2 * 60)) + "m";
        }
        int i3 = i / 60;
        return i3 + "m" + (i - (i3 * 60)) + "s";
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
