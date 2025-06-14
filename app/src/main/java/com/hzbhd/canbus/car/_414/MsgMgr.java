package com.hzbhd.canbus.car._414;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static int nowModel;
    private int alertInfo2;
    private int alertInfo3;
    TextView content;
    AlertDialog dialog;
    int differentId;
    int[] door0x72;
    int eachId;
    Button i_know;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mDoorData;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    SystemButton systemButton;
    View view;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");

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
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(context).sendCarType();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            setSwc0x21();
            return;
        }
        if (i == 65) {
            setRadar0x41();
            return;
        }
        if (i == 97) {
            setCArSetting0x61();
            return;
        }
        if (i == 114) {
            setAlertInfo0x72();
            setDoorInfo0x72();
            return;
        }
        if (i == 232) {
            setPanoromic0xE8();
            return;
        }
        if (i == 240) {
            setVersion0xF0();
            return;
        }
        if (i == 49) {
            set0x31AirInfo();
            return;
        }
        if (i != 50) {
            switch (i) {
                case 17:
                    setCarInfo0x11();
                    break;
                case 18:
                    set0x12DoorInfo();
                    break;
                case 19:
                    set0x13Info();
                    break;
            }
            return;
        }
        set0x32CarInfo();
    }

    private void setVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setPanoromic0xE8() {
        if (nowModel == 0 && this.mCanBusInfoInt[5] == 1) {
            forceReverse(this.mContext, true);
        }
        if (nowModel == 5 && this.mCanBusInfoInt[5] == 0) {
            forceReverse(this.mContext, false);
        }
        int i = nowModel;
        if (i == 5) {
            nowModel = 0;
        } else {
            nowModel = i + 1;
        }
    }

    private void setCArSetting0x61() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 1)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        if (!DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            Toast.makeText(getActivity(), this.mContext.getString(R.string._414_setting70), 0).show();
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_414_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_414_setting", "_414_setting11"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorInfo0x72() {
        if (isNotDoor0x72InfoChange()) {
            return;
        }
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setAlertInfo0x72() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == this.alertInfo2 && iArr[3] == this.alertInfo3) {
            return;
        }
        int i2 = iArr[3];
        this.alertInfo3 = i2;
        this.alertInfo2 = i;
        if (i == 0 && i2 == 0) {
            return;
        }
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
        this.i_know.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._414.MsgMgr.1
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
        int i;
        String str = "";
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = "   1." + this.mContext.getString(R.string._414_alert0);
            i = 1;
        } else {
            i = 0;
        }
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert1);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert2);
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert3);
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert4);
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert5);
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert6);
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert7);
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            i++;
            str = str + "   " + i + "." + this.mContext.getString(R.string._414_alert8);
        }
        if (!DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            return str;
        }
        return str + "   " + (i + 1) + "." + this.mContext.getString(R.string._414_alert9);
    }

    private void set0x32CarInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data4");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[4], iArr[5])).append("RPM").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data5");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[6], iArr2[7])).append("KM/H").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    private void setRadar0x41() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x31AirInfo() {
        updateOutDoorTemp(this.mContext, this.df_2Decimal.format((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        this.mCanBusInfoInt[13] = 0;
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_foot = false;
        int i = this.mCanBusInfoInt[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[8], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[8], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        updateAirActivity(this.mContext, 1004);
    }

    private void set0x13Info() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data0");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df_2Decimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format((((iArr[5] & 255) << 8) | ((iArr[4] & 255) << 16) | (this.mCanBusInfoByte[6] & 255)) * 0.1d)).append("KM").toString()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data1"), this.mCanBusInfoInt[7] + "%"));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_414_drive_data");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_414_drive_data2");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[8], iArr2[9])).append("KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSwc0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
        } else {
            if (i != 1) {
                return;
            }
            realKeyLongClick1(this.mContext, 1, iArr[3]);
        }
    }

    private void set0x12DoorInfo() {
        if (isNotDoor0x12DataChange()) {
            return;
        }
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void setCarInfo0x11() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[5]);
        } else if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[5]);
        } else if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[5]);
        } else if (i == 3) {
            realKeyLongClick1(this.mContext, 3, iArr[5]);
        } else if (i == 4) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[5]);
        } else {
            switch (i) {
                case 8:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_UP_PICKUP, iArr[5]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_DOWN_HANGUP, iArr[5]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 2, iArr[5]);
                    break;
            }
        }
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr2[9], (byte) iArr2[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getTemperature(int i, int i2, int i3) {
        return i == i2 ? "LO" : i == i3 ? "HI" : this.df_2Decimal.format(i * 0.5d) + getTempUnitC(this.mContext);
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

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String tenToSixTeen(int i) {
        return String.format("%02x", Integer.valueOf(i));
    }

    private int sixteenToTen(String str) {
        return Integer.parseInt(("0x" + str).replaceAll("^0[x|X]", ""), 16);
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isNotAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isNotDoor0x12DataChange() {
        if (Arrays.equals(this.mDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mDoorData = this.mCanBusInfoInt;
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

    private boolean isNotDoor0x72InfoChange() {
        if (Arrays.equals(this.door0x72, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.door0x72 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }

    public void showButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._414.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._414.MsgMgr.2.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicInfo0xFD();
                        }
                    });
                }
                MsgMgr.this.systemButton.show();
            }
        });
    }

    public void hideButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._414.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._414.MsgMgr.3.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicInfo0xFD();
                        }
                    });
                }
                MsgMgr.this.systemButton.hide();
            }
        });
    }
}
