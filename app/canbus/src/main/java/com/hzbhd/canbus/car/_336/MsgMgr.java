package com.hzbhd.canbus.car._336;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int EspInfoData6;
    int EspInfoData7;
    TextView content;
    AlertDialog dialog;
    int differentId;
    int driveInfoData0;
    int eachId;
    Button i_know;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    View view;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    int nowData6Bit4567 = 0;
    boolean isFrontCAmera = false;

    private int assignRadarProgress(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 5;
        }
        return i == 3 ? 10 : 0;
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private void set0x26CarModelInfo() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i5, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) i2, (byte) i3, (byte) i4, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            set0x11CarBasicInfo();
            return;
        }
        if (i == 18) {
            set0x12CarDetailInfo();
            return;
        }
        if (i == 38) {
            set0x26CarModelInfo();
            return;
        }
        if (i == 49) {
            set0x31AirInfo();
            return;
        }
        if (i == 65) {
            set0x41RadarInfo();
        } else if (i == 97) {
            setOx61CarSettingsInfo();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0VersionInfo();
        }
    }

    private void setOx61CarSettingsInfo() {
        setDriveInfo();
        setSettingInfo();
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4) != 0) {
            if (this.nowData6Bit4567 != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4)) {
                this.nowData6Bit4567 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
                setAlert();
                return;
            }
            return;
        }
        this.nowData6Bit4567 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void setAlert() {
        if (this.view == null) {
            this.view = LayoutInflater.from(this.mContext).inflate(R.layout._333_alert_dialog, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(this.mContext).setView(this.view).create();
        }
        if (this.content == null) {
            this.content = (TextView) this.view.findViewById(R.id.alert_content);
        }
        this.content.setText(getAlertContent());
        if (this.i_know == null) {
            this.i_know = (Button) this.view.findViewById(R.id.i_know);
        }
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._336.MsgMgr.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MsgMgr.this.dialog.dismiss();
            }
        });
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        this.dialog.show();
    }

    private String getAlertContent() {
        String str = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]) ? "" + this.mContext.getString(R.string._336_car_settings0) : "";
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
            str = str + this.mContext.getString(R.string._336_car_settings1);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
            str = str + this.mContext.getString(R.string._336_car_settings2);
        }
        return DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]) ? str + this.mContext.getString(R.string._336_car_settings3) : str;
    }

    private void setSettingInfo() {
        ArrayList arrayList = new ArrayList();
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_336_car_settings");
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, 11, getData5Bit1State()).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getData5Bit1State() {
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7])) {
            return this.mContext.getString(R.string._336_state15);
        }
        return this.mContext.getString(R.string._336_state16);
    }

    private void setDriveInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air1"), getAir1State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air2"), getAir2State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air3"), getAir3State(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 2))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air4"), getAir4State(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_air"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_air5"), getAir5State(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice1"), getVoiceState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice2"), getVoiceState(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice3"), getVoiceState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_voice"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_voice4"), getVoiceState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control1"), getRemoteControlState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control2"), getRemoteControlState(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control3"), getRemoteControlState(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_remote_control"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_remote_control4"), getRemoteControlState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_travel"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_travel1"), getTravel1State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_travel"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_travel2"), getTravel2State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_travel"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_travel3"), getTravel3State(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 5, 1))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x31AirInfo() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[6];
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i == 3) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i == 11) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i == 12) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 13) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i == 14) {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else {
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_auto_wind = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        }
        if (this.mCanBusInfoInt[7] == 19) {
            GeneralAirData.front_auto_wind_speed = true;
        } else {
            GeneralAirData.front_auto_wind_speed = false;
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        }
        int i2 = this.mCanBusInfoInt[8];
        if (i2 == 254) {
            GeneralAirData.front_left_temperature = "LOW";
        } else if (i2 == 255) {
            GeneralAirData.front_left_temperature = "HI";
        } else {
            GeneralAirData.front_left_temperature = this.df_2Decimal.format(this.mCanBusInfoInt[8] * 0.5d) + getTempUnitC(this.mContext);
        }
        int i3 = this.mCanBusInfoInt[9];
        if (i3 == 254) {
            GeneralAirData.front_right_temperature = "LOW";
        } else if (i3 == 255) {
            GeneralAirData.front_right_temperature = "HI";
        } else {
            GeneralAirData.front_right_temperature = this.df_2Decimal.format(this.mCanBusInfoInt[9] * 0.5d) + getTempUnitC(this.mContext);
        }
        updateAirActivity(this.mContext, 1002);
    }

    private void set0xF0VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x41RadarInfo() {
        if (isTrackInfoChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, assignRadarProgress(this.mCanBusInfoInt[2]), assignRadarProgress(this.mCanBusInfoInt[3]), assignRadarProgress(this.mCanBusInfoInt[4]), assignRadarProgress(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_14"), getRadarDisplayState(this.mCanBusInfoInt[12])));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_17"), getRadarDisplayModel(this.mCanBusInfoInt[13])));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x12CarDetailInfo() {
        if (isBasicInfoChange()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x11CarBasicInfo() {
        if (driveInfoChange()) {
            set0x11DriveInfo();
        }
        set0x11WhellKeyInfo();
        if (isEspInfoChange()) {
            set0x11EspInfo();
        }
    }

    private void set0x11EspInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x11WhellKeyInfo() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 4) {
            buttonKey(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            buttonKey(14);
            return;
        }
        if (i == 6) {
            buttonKey(15);
            return;
        }
        if (i != 57) {
            switch (i) {
                case 8:
                    buttonKey(45);
                    break;
                case 9:
                    buttonKey(46);
                    break;
                case 10:
                    buttonKey(2);
                    break;
            }
            return;
        }
        frontCameraFunction();
    }

    private void frontCameraFunction() {
        if (this.mCanBusInfoInt[5] == 0) {
            return;
        }
        if (!this.isFrontCAmera) {
            switchFCamera(this.mContext, true);
            this.isFrontCAmera = true;
        } else {
            switchFCamera(this.mContext, false);
            this.isFrontCAmera = false;
        }
    }

    private void set0x11DriveInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_2"), getParkState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_3"), getRevState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_4"), getILLState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_336_car_1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_336_car_5"), getAccState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getTravel3State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 1) {
            return string + "/" + this.mContext.getString(R.string._336_state19);
        }
        return string + "/" + this.mContext.getString(R.string._336_state20);
    }

    private String getTravel2State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 1) {
            return string + "/" + this.mContext.getString(R.string._336_state17);
        }
        return string + "/" + this.mContext.getString(R.string._336_state18);
    }

    private String getTravel1State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 1) {
            return string + "/" + this.mContext.getString(R.string._336_state15);
        }
        return string + "/" + this.mContext.getString(R.string._336_state16);
    }

    private String getRemoteControlState(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            return string + "/" + this.mContext.getString(R.string._336_state3);
        }
        return string + "/" + this.mContext.getString(R.string._336_state4);
    }

    private String getVoiceState(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            return string + "/" + this.mContext.getString(R.string._336_state5);
        }
        return string + "/" + this.mContext.getString(R.string._336_state6);
    }

    private String getAir5State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            return string + "/" + this.mContext.getString(R.string._336_state3);
        }
        return string + "/" + this.mContext.getString(R.string._336_state4);
    }

    private String getAir4State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            return string + "/" + this.mContext.getString(R.string._336_state3);
        }
        return string + "/" + this.mContext.getString(R.string._336_state4);
    }

    private String getAir3State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            return string + "/" + this.mContext.getString(R.string._336_state3);
        }
        if (i == 1) {
            return string + "/" + this.mContext.getString(R.string._336_state7);
        }
        if (i == 2) {
            return string + "/" + this.mContext.getString(R.string._336_state8);
        }
        return i == 3 ? string + "/" + this.mContext.getString(R.string._336_state9) : string;
    }

    private String getAir2State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            return string + "/" + this.mContext.getString(R.string._336_state13);
        }
        return string + "/" + this.mContext.getString(R.string._336_state14);
    }

    private String getAir1State(boolean z, int i) {
        String string;
        if (z) {
            string = this.mContext.getString(R.string._336_state1);
        } else {
            string = this.mContext.getString(R.string._336_state2);
        }
        if (i == 0) {
            string = string + "/" + this.mContext.getString(R.string._336_state10);
        }
        if (i == 1) {
            return string + "/" + this.mContext.getString(R.string._336_state11);
        }
        return i == 2 ? string + "/" + this.mContext.getString(R.string._336_state12) : string;
    }

    private String getRadarDisplayModel(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._336_car_19);
        }
        return this.mContext.getString(R.string._336_car_18);
    }

    private String getRadarDisplayState(int i) {
        if (i == 1) {
            panoramicSwitch(true);
            return this.mContext.getString(R.string._336_car_15);
        }
        panoramicSwitch(false);
        return this.mContext.getString(R.string._336_car_16);
    }

    private String getAccState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._336_car_12);
        }
        return this.mContext.getString(R.string._336_car_13);
    }

    private String getILLState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._336_car_10);
        }
        return this.mContext.getString(R.string._336_car_11);
    }

    private String getRevState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._336_car_8);
        }
        return this.mContext.getString(R.string._336_car_9);
    }

    private String getParkState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._336_car_6);
        }
        return this.mContext.getString(R.string._336_car_7);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
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

    private void adjustBrightness() {
        int screenBacklight = MediaShareData.Screen.INSTANCE.getScreenBacklight();
        if (screenBacklight == 5) {
            setBacklightLevel(1);
        } else {
            setBacklightLevel(screenBacklight + 1);
        }
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        int length = bArr.length + bArr2.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        MyLog.e("长度", length + "");
        for (int i = 0; i < length; i++) {
            MyLog.e("内容" + i, ((int) bArr3[i]) + "");
        }
        return bArr3;
    }

    public void panoramicSwitch(boolean z) {
        forceReverse(this.mContext, z);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean driveInfoChange() {
        int i = this.driveInfoData0;
        int i2 = this.mCanBusInfoInt[2];
        if (i == i2) {
            return false;
        }
        this.driveInfoData0 = i2;
        return true;
    }

    private boolean isEspInfoChange() {
        int i = this.EspInfoData6;
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[8];
        if (i == i2 && this.EspInfoData7 == iArr[9]) {
            return false;
        }
        this.EspInfoData6 = i2;
        this.EspInfoData7 = iArr[9];
        return true;
    }
}
