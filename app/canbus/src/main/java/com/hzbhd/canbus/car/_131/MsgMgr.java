package com.hzbhd.canbus.car._131;

import android.content.Context;
import android.content.res.Resources;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    int _0x33_data0;
    int _0x33_data1;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private final String _131_LEFT_FRONT_DOOR_OPEN = "_131_left_front_door_open";
    private final String _131_RIGHT_FRONT_DOOR_OPEN = "_131_right_front_door_open";
    private final String _131_RIGHT_REAR_DOOR_OPEN = "_131_right_rear_door_open";
    private final String _131_LEFT_REAR_DOOR_OPEN = "_131_left_rear_door_open";
    private final String _131_BACK_OPEN = "_131_back_open";
    private final String _131_FRONT_OPEN = "_131_front_open";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 32) {
            set0x20WheelKeyInfo();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 50) {
            settingInfo();
            return;
        }
        if (i != 51) {
            switch (i) {
                case 34:
                    setRearRadarInfo();
                    break;
                case 35:
                    setFrontRadarInfo();
                    break;
                case 36:
                    if (!isDoorMsgRepeat(bArr)) {
                        setDoorData0x24();
                        break;
                    }
                    break;
                case 37:
                    parkingStatus();
                    break;
            }
            return;
        }
        settingTips();
    }

    private void set0x20WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 16) {
            realKeyLongClick1(this.mContext, 47, iArr[3]);
            return;
        }
        if (i != 17) {
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
                    realKeyLongClick1(this.mContext, 20, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 21, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 8:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                            break;
                        case 9:
                            realKeyLongClick1(this.mContext, 14, iArr[3]);
                            break;
                        case 10:
                            realKeyLongClick1(this.mContext, 15, iArr[3]);
                            break;
                        default:
                            switch (i) {
                                case 19:
                                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                                    break;
                                case 20:
                                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                                    break;
                                case 21:
                                    realKeyLongClick1(this.mContext, 50, iArr[3]);
                                    break;
                                case 22:
                                    realKeyLongClick1(this.mContext, 49, iArr[3]);
                                    break;
                                default:
                                    switch (i) {
                                        case 129:
                                            realKeyLongClick1(this.mContext, 58, iArr[3]);
                                            break;
                                        case 130:
                                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                                            break;
                                        case 131:
                                            realKeyLongClick1(this.mContext, 4, iArr[3]);
                                            break;
                                        case 132:
                                            realKeyLongClick1(this.mContext, 7, iArr[3]);
                                            break;
                                        case 133:
                                            realKeyLongClick1(this.mContext, 8, iArr[3]);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 48, iArr[3]);
    }

    private void setRearRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void settingInfo() {
        int[] iArr = {DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]) ? 1 : 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? 1 : 0};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new SettingUpdateEntity(0, i, Integer.valueOf(iArr[i])));
        }
        int[] iArr2 = {DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]) ? 1 : 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3)};
        for (int i2 = 0; i2 < 5; i2++) {
            arrayList.add(new SettingUpdateEntity(1, i2, Integer.valueOf(iArr2[i2])));
        }
        int[] iArr3 = {DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]) ? 1 : 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3)};
        for (int i3 = 0; i3 < 2; i3++) {
            arrayList.add(new SettingUpdateEntity(2, i3, Integer.valueOf(iArr3[i3])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void settingTips() {
        int[] iArr = this.mCanBusInfoInt;
        this._0x33_data0 = iArr[2];
        this._0x33_data1 = iArr[3];
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) && isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "_131_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_131_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_131_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_131_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "_131_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "_131_front_open", GeneralDoorData.isFrontOpen);
            updateDoorView(this.mContext);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? "Not P" : "P"));
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "_131_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_131_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_131_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_131_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "_131_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "_131_front_open", false) == GeneralDoorData.isFrontOpen) ? false : true;
    }

    private void parkingStatus() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? "open" : "close"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }
}
