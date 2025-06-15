package com.hzbhd.canbus.car._207;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private static int volKnobValue;
    private static int volKnobValueRadio;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private byte[] mRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    int mTime1Hour;
    int mTime1Minute;
    int mTime2Hour;
    int mTime2Minute;
    private byte[] mTrackData;
    private int mVehicleSpeed;
    private final String TAG = "_207_MsgMgr";
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

    private void TimeInfo0xC2() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 21) {
            set0x15CarInfo();
            return;
        }
        if (i == 194) {
            TimeInfo0xC2();
            return;
        }
        if (i == 204) {
            set0xCCParkingVentilation();
            return;
        }
        if (i == 240) {
            set0xF0VersionDate();
            return;
        }
        if (i == 33) {
            Panelkeys0x21();
            return;
        }
        if (i == 34) {
            Panelknob0x22();
        } else if (i == 114) {
            set0x72CarBaseData(context);
        } else {
            if (i != 115) {
                return;
            }
            set0x73AirData(context);
        }
    }

    private void Panelknob0x22() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            volKnobValue = 0;
            volKnobValueRadio = 0;
            return;
        }
        if (i == 1) {
            int i2 = volKnobValue - this.mCanBusInfoByte[3];
            if (i2 < 0) {
                PanelKnobClick(7, Math.abs(i2));
            } else if (i2 > 0) {
                PanelKnobClick(8, Math.abs(i2));
            }
            volKnobValue = this.mCanBusInfoByte[3];
            return;
        }
        if (i != 2) {
            return;
        }
        int i3 = volKnobValueRadio - this.mCanBusInfoByte[3];
        if (i3 < 0) {
            PanelKnobClick(48, Math.abs(i3));
        } else if (i3 > 0) {
            PanelKnobClick(47, Math.abs(i3));
        }
        volKnobValueRadio = this.mCanBusInfoByte[3];
    }

    private void PanelKnobClick(int i, int i2) {
        realKeyClick3(this.mContext, i, i2, 1);
    }

    private void Panelkeys0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelBtnKeyClick(0);
            return;
        }
        if (i == 1) {
            panelBtnKeyClick(1);
            return;
        }
        if (i == 6) {
            panelBtnKeyClick(50);
            return;
        }
        if (i == 9) {
            panelBtnKeyClick(3);
            return;
        }
        if (i == 32) {
            panelBtnKeyClick(128);
            return;
        }
        if (i == 36) {
            panelBtnKeyClick(59);
            return;
        }
        if (i == 47) {
            panelBtnKeyClick(151);
            return;
        }
        if (i == 51) {
            panelBtnKeyClick(62);
        } else if (i == 57) {
            panelBtnKeyClick(1);
        } else {
            if (i != 76) {
                return;
            }
            panelBtnKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
        }
    }

    private void panelBtnKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void set0x72CarBaseData(Context context) {
        int i = this.mVehicleSpeed;
        int i2 = this.mCanBusInfoInt[3];
        if (i != i2) {
            this.mVehicleSpeed = i2;
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        }
        int i3 = this.mKeyStatus;
        int i4 = this.mCanBusInfoInt[4];
        if (i3 != i4) {
            this.mKeyStatus = i4;
        }
        if (i4 == 0) {
            realKeyLongClick2(context, 0);
        } else if (i4 == 1) {
            realKeyLongClick2(context, 7);
        } else if (i4 == 2) {
            realKeyLongClick2(context, 8);
        } else if (i4 == 3) {
            realKeyLongClick2(context, 3);
        } else if (i4 == 5) {
            realKeyLongClick2(context, 14);
        } else if (i4 == 6) {
            realKeyLongClick2(context, 15);
        } else if (i4 != 24) {
            switch (i4) {
                case 8:
                    realKeyLongClick2(context, 45);
                    break;
                case 9:
                    realKeyLongClick2(context, 46);
                    break;
                case 10:
                    realKeyLongClick2(context, 2);
                    break;
            }
        } else {
            realKeyLongClick2(context, HotKeyConstant.K_AIR_IN_OUT_CYCLE);
        }
        if (isTrackDataChange()) {
            byte b = this.mCanBusInfoByte[6];
            if (b != 0) {
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(b, (byte) 0, 0, 140, 16);
            }
            byte b2 = this.mCanBusInfoByte[7];
            if (b2 != 0) {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(b2, (byte) 0, 0, 140, 16);
            }
            Log.i("_207_MsgMgr", "set0x72CarBaseData: GeneralParkData.trackAngle  " + GeneralParkData.trackAngle);
            updateParkUi(null, context);
        }
        if (isRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(255, iArr[8], iArr[9], iArr[10], iArr[11]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(255, iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x73AirData(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, ResolveHeatLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(2, 1, ResolveHeatLevel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updateOutDoorTemp(context, resolveOutdoorTemperature(this.mCanBusInfoInt[8]));
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private String ResolveHeatLevel(int i) {
        return i + CommUtil.getStrByResId(this.mContext, "level");
    }

    private void set0x15CarInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 8) | iArr[3];
        int i2 = (iArr[4] << 8) | iArr[5];
        int i3 = iArr[11] | (iArr[10] << 8);
        float f = i * 0.1f;
        String str = f <= 400.0f ? new DecimalFormat("0.0").format(f) + " 1L/100KM" : "invalid";
        float f2 = i3 * 0.1f;
        String str2 = f2 <= 123.0f ? new DecimalFormat("0.0").format(f2) + " KM/H" : "invalid";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, str));
        arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.rangeNumber(i2, 4000) + " KM"));
        arrayList.add(new DriverUpdateEntity(0, 3, str2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0xCCParkingVentilation() {
        int[] iArr = this.mCanBusInfoInt;
        this.mTime1Hour = iArr[4];
        this.mTime1Minute = iArr[5];
        this.mTime2Hour = iArr[6];
        this.mTime2Minute = iArr[7];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(ResolveData(this.mCanBusInfoInt[2]))));
        arrayList.add(new SettingUpdateEntity(0, 1, ResolveDaTa(Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(0, 2, ResolveDaTa(Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mTime1Hour)).setProgress(this.mTime1Hour).setEnable(!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mTime1Minute)).setProgress(this.mTime1Minute).setEnable(!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mTime2Hour)).setProgress(this.mTime2Hour).setEnable(!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(this.mTime2Minute)).setProgress(this.mTime2Minute).setEnable(!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private int ResolveData(int i) {
        return DataHandleUtils.getBoolBit1(i) & DataHandleUtils.getBoolBit0(i) ? 1 : 0;
    }

    private String ResolveDaTa(Boolean bool) {
        if (bool.booleanValue()) {
            return CommUtil.getStrByResId(this.mContext, "_207_setting_8");
        }
        return CommUtil.getStrByResId(this.mContext, "_207_setting_9");
    }

    private void set0xF0VersionDate() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i8, (byte) i6, 0, 0, 0, (byte) i2, (byte) i3, (byte) i4, 0});
    }

    private String resolveOutdoorTemperature(int i) {
        return ((i * 0.5d) - 40.0d) + getTempUnitC(this.mContext);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isTrackDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[6], bArr[7]};
        if (Arrays.equals(this.mTrackData, bArr2)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(bArr2, 2);
        return true;
    }

    private boolean isRadarDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 8, bArr.length);
        Log.i("_207_MsgMgr", "isRadarDataChange: " + Arrays.toString(bArrCopyOfRange));
        if (Arrays.equals(this.mRadarData, bArrCopyOfRange)) {
            return false;
        }
        this.mRadarData = Arrays.copyOf(bArrCopyOfRange, bArrCopyOfRange.length);
        return true;
    }
}
