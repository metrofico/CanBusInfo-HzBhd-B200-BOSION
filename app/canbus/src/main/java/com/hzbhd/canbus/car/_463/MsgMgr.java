package com.hzbhd.canbus.car._463;

import android.content.Context;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCarDoorData;
    Context mContext;
    int[] mRearRadarData;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatInteger2 = new DecimalFormat("00");
    int volValue = -1;
    int selectValue = -1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 3) {
            set0x03Radar(byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            setDoor0x24(byteArrayToIntArray);
            return;
        }
        if (i == 38) {
            setEsp0x26(byteArrayToIntArray);
            return;
        }
        if (i == 65) {
            setSpeed0x41(byteArrayToIntArray);
            return;
        }
        if (i == 85) {
            setAir0x55(byteArrayToIntArray);
            return;
        }
        if (i == 87) {
            setVersion0x57(byteArrayToIntArray);
        } else if (i == 33) {
            setSwc0x21(byteArrayToIntArray);
        } else {
            if (i != 34) {
                return;
            }
            setSwc0x22(byteArrayToIntArray);
        }
    }

    private void setSwc0x22(int[] iArr) {
        int i = iArr[2];
        if (i == 1) {
            int i2 = this.volValue;
            if (i2 != -1) {
                if (iArr[3] > i2) {
                    realKeyClick4(this.mContext, 7);
                } else {
                    realKeyClick4(this.mContext, 8);
                }
            }
            this.volValue = iArr[3];
            return;
        }
        if (i != 2) {
            return;
        }
        int i3 = this.selectValue;
        if (i3 != -1) {
            if (iArr[3] > i3) {
                realKeyClick4(this.mContext, 48);
            } else {
                realKeyClick4(this.mContext, 47);
            }
        }
        this.selectValue = iArr[3];
    }

    private void setSwc0x21(int[] iArr) {
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 1, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i == 6) {
            realKeyLongClick1(this.mContext, 50, iArr[3]);
            return;
        }
        if (i == 9) {
            realKeyLongClick1(this.mContext, 3, iArr[3]);
            return;
        }
        if (i == 32) {
            realKeyLongClick1(this.mContext, 128, iArr[3]);
            return;
        }
        if (i == 51) {
            realKeyLongClick1(this.mContext, 62, iArr[3]);
            return;
        }
        if (i == 57) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SET_BACKLIGHT, iArr[3]);
            return;
        }
        if (i == 99) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
            return;
        }
        if (i == 43) {
            realKeyLongClick1(this.mContext, 52, iArr[3]);
            return;
        }
        if (i == 44) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
        } else if (i == 47) {
            realKeyLongClick1(this.mContext, 151, iArr[3]);
        } else {
            if (i != 48) {
                return;
            }
            realKeyLongClick1(this.mContext, 68, iArr[3]);
        }
    }

    private void setVersion0x57(int[] iArr) {
        int i = iArr[3];
        String str = i == 3 ? "Accord7" : i == 4 ? "AccordB" : "";
        updateVersionInfo(this.mContext, "Honda_" + str + "_" + String.valueOf(iArr[4] + SystemConstant.THREAD_SLEEP_TIME_2000) + this.formatInteger2.format(iArr[5]) + this.formatInteger2.format(iArr[6]) + "_" + ("V" + iArr[7]));
    }

    private void setSpeed0x41(int[] iArr) {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]));
    }

    private void set0x03Radar(int[] iArr) {
        if (isRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            RadarInfoUtil.setRearRadarLocationData(4, iArr[6], iArr[7], iArr[8], iArr[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setAir0x55(int[] iArr) {
        if (iArr[9] == 255) {
            updateOutDoorTemp(this.mContext, "");
        } else {
            updateOutDoorTemp(this.mContext, (iArr[9] - 40) + "℃");
        }
        iArr[9] = 0;
        if (isAirDataChange(iArr)) {
            if (iArr[2] < 10) {
                GeneralAirData.front_left_temperature = "";
            } else {
                GeneralAirData.front_left_temperature = (iArr[2] / 2.0f) + "℃";
            }
            if (iArr[3] < 10) {
                GeneralAirData.front_right_temperature = "";
            } else {
                GeneralAirData.front_right_temperature = (iArr[3] / 2.0f) + "℃";
            }
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4);
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_auto_wind_model = false;
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[4], 4, 4);
            if (intFromByteWithBit == 0) {
                GeneralAirData.front_auto_wind_model = true;
            } else if (intFromByteWithBit == 1) {
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_head = true;
            } else if (intFromByteWithBit == 2) {
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_foot = true;
            } else if (intFromByteWithBit == 3) {
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_foot = true;
            } else if (intFromByteWithBit == 4) {
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_window = true;
            } else if (intFromByteWithBit == 6) {
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_window = true;
            }
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(iArr[5], 2, 2);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(iArr[5], 5, 2);
            GeneralAirData.auto = DataHandleUtils.getBoolBit0(iArr[6]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit1(iArr[6]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[6]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(iArr[6]);
            if (DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 1) {
                GeneralAirData.in_out_cycle = true;
            } else if (DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2) == 2) {
                GeneralAirData.in_out_cycle = false;
            }
            GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit2(iArr[7]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit3(iArr[7]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setDoor0x24(int[] iArr) {
        iArr[3] = 0;
        int iBlockBit = DataHandleUtils.blockBit(iArr[2], 6);
        iArr[2] = iBlockBit;
        iArr[2] = DataHandleUtils.blockBit(iBlockBit, 7);
        if (isBasicInfoChange(iArr)) {
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setEsp0x26(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            if (DataHandleUtils.getBoolBit3(iArr[3])) {
                GeneralParkData.trackAngle = ((iArr[2] & 255) | ((DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 3) & 255) << 8)) / 15;
                updateParkUi(null, this.mContext);
            } else {
                GeneralParkData.trackAngle = (-((iArr[2] & 255) | ((DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 3) & 255) << 8))) / 15;
                updateParkUi(null, this.mContext);
            }
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
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
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
