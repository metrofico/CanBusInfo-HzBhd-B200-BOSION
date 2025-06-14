package com.hzbhd.canbus.car._454;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int differentId;
    private int eachId;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    int[] mCarDiveData;
    int[] mCarDoorData;
    private Context mContext;
    int[] mRadarData;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private String[] arr0 = new String[1];
    private String[] arr1 = new String[1];
    private String[] arr2 = new String[1];
    private String[] arr3 = new String[1];
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private String getSwitchState(boolean z) {
        return z ? "ON" : "OFF";
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
        GeneralTireData.isHaveSpareTire = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 3) {
            set0x03VersionInfo(bArr);
            return;
        }
        if (i != 56) {
            switch (i) {
                case 32:
                    set0x20Swc(byteArrayToIntArray);
                    break;
                case 33:
                    set0x21DriveInfo(byteArrayToIntArray);
                    break;
                case 34:
                    set0x22Radar(byteArrayToIntArray);
                    break;
                case 35:
                    set0x23Esp(byteArrayToIntArray);
                    break;
                case 36:
                    set0x24Door(byteArrayToIntArray);
                    break;
                default:
                    switch (i) {
                        case 52:
                            set0x34DriveInfo(byteArrayToIntArray);
                            break;
                        case 53:
                            setDrive0x35(byteArrayToIntArray);
                            break;
                        case 54:
                            setTire0x36(byteArrayToIntArray);
                            break;
                    }
            }
            return;
        }
        set0x38Settings(byteArrayToIntArray);
    }

    private void setDrive0x35(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive2"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive2_0"), DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) + ""));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive2"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive2_1"), (DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive2"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive2_2"), (DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) / 10.0f) + ""));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTire0x36(int[] iArr) {
        if (DataHandleUtils.getBoolBit7(iArr[2])) {
            this.arr0[0] = this.mContext.getString(R.string._1001_tire_1);
            this.tyreInfoList.add(new TireUpdateEntity(0, 1, this.arr0));
        } else {
            this.arr0[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
        }
        if (DataHandleUtils.getBoolBit6(iArr[2])) {
            this.arr1[0] = this.mContext.getString(R.string._1001_tire_1);
            this.tyreInfoList.add(new TireUpdateEntity(1, 1, this.arr1));
        } else {
            this.arr1[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        }
        if (DataHandleUtils.getBoolBit5(iArr[2])) {
            this.arr2[0] = this.mContext.getString(R.string._1001_tire_1);
            this.tyreInfoList.add(new TireUpdateEntity(2, 1, this.arr2));
        } else {
            this.arr2[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        }
        if (DataHandleUtils.getBoolBit4(iArr[2])) {
            this.arr3[0] = this.mContext.getString(R.string._1001_tire_1);
            this.tyreInfoList.add(new TireUpdateEntity(3, 1, this.arr3));
        } else {
            this.arr3[0] = this.mContext.getString(R.string._1001_tire_2);
            this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        }
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private void set0x38Settings(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 5, 6))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting10"), Integer.valueOf(iArr[4])).setProgress(iArr[4]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting11"), Integer.valueOf(DataHandleUtils.getMsbLsbResult(iArr[5], iArr[6]))).setProgress(DataHandleUtils.getMsbLsbResult(iArr[5], iArr[6]) - 6));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_454_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_454_setting", "_454_setting12"), Integer.valueOf(iArr[7])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x34DriveInfo(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        String str = "--";
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer0"), (iArr[2] == 255 || iArr[3] == 255) ? "--" : iArr[2] + ":" + iArr[3]));
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer1");
        if (iArr[4] != 255 && iArr[5] != 255 && iArr[6] != 255) {
            str = DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) + ":" + iArr[6];
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, str));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer2"), DataHandleUtils.getMsbLsbResult(iArr[7], iArr[8]) + "km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer3"), (DataHandleUtils.getMsbLsbResult(iArr[9], iArr[10]) / 10.0f) + ""));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_computer"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_computer4"), (DataHandleUtils.getMsbLsbResult(iArr[11], iArr[12]) / 10.0f) + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x24Door(int[] iArr) {
        if (isBasicInfoChange(iArr) && DataHandleUtils.getBoolBit0(iArr[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x23Esp(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            int i = iArr[2];
            if (i >= 0 && i <= 32) {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getLsb(i), (byte) getMsb(iArr[2]), 0, 32, 8);
                updateParkUi(null, this.mContext);
            } else {
                if (i < 224 || i > 255) {
                    return;
                }
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) getLsb(255 - i), (byte) getMsb(255 - iArr[2]), 0, 32, 8);
                updateParkUi(null, this.mContext);
            }
        }
    }

    private void set0x22Radar(int[] iArr) {
        if (isRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            int i = iArr[2];
            int i2 = iArr[3];
            RadarInfoUtil.setRearRadarLocationData(5, i, i2, i2, iArr[4]);
            int i3 = iArr[5];
            int i4 = iArr[6];
            RadarInfoUtil.setFrontRadarLocationData(5, i3, i4, i4, iArr[7]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x21DriveInfo(int[] iArr) {
        if (isDriveInfoChange(iArr)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_speed"), iArr[2] + "km/h"));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_0il_quantity"), iArr[3] + ""));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_light_level"), iArr[4] + ""));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Airbag_yellow"), getSwitchState(DataHandleUtils.getBoolBit0(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Airbag_red"), getSwitchState(DataHandleUtils.getBoolBit1(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Tire_pressure_yellow"), getSwitchState(DataHandleUtils.getBoolBit2(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Tire_pressure_red"), getSwitchState(DataHandleUtils.getBoolBit3(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Front_fog_lamp"), getSwitchState(DataHandleUtils.getBoolBit4(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Rear_fog_lamp"), getSwitchState(DataHandleUtils.getBoolBit5(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Left_turn"), getSwitchState(DataHandleUtils.getBoolBit6(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Right_turn"), getSwitchState(DataHandleUtils.getBoolBit7(iArr[5]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_safety_belt"), getSwitchState(DataHandleUtils.getBoolBit0(iArr[6]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_ESP"), getSwitchState(DataHandleUtils.getBoolBit1(iArr[6]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_cruise_control"), getSwitchState(DataHandleUtils.getBoolBit2(iArr[6]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_engine_green"), getSwitchState(DataHandleUtils.getBoolBit3(iArr[6]))));
            arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_454_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_engine_yellow"), getSwitchState(DataHandleUtils.getBoolBit4(iArr[6]))));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x20Swc(int[] iArr) {
        switch (iArr[2]) {
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
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(this.mContext, 3, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 8:
                realKeyClick4(this.mContext, 48);
                break;
            case 9:
                realKeyClick4(this.mContext, 47);
                break;
            case 10:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 11:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 47, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 48, iArr[3]);
                break;
            case 15:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
            case 16:
                realKeyLongClick1(this.mContext, 52, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(this.mContext, 151, iArr[3]);
                break;
        }
    }

    private void set0x03VersionInfo(byte[] bArr) {
        updateVersionInfo(this.mContext, getVersionStr(bArr));
    }

    private boolean isRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRadarData, iArr)) {
            return false;
        }
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isDriveInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDiveData, iArr)) {
            return false;
        }
        this.mCarDiveData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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
}
