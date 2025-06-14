package com.hzbhd.canbus.car._326;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
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
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x22Data;
    private int[] m0x23Data;
    private byte[] mAirData;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mEachId;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private byte[] mTrackData;
    private UiMgr mUiMgr;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap = new HashMap<>();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initSettingsItem(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 48) {
            set0x30VersionData();
            return;
        }
        if (i != 107) {
            switch (i) {
                case 32:
                    set0x20WheelKey(context);
                    break;
                case 33:
                    set0x21AirData();
                    break;
                case 34:
                    set0x22RearRadarInfo(context);
                    break;
                case 35:
                    set0x23FrontRadarInfo(context);
                    break;
                case 36:
                    set0x24BaseInfo(context);
                    break;
                case 37:
                    set0x25ParkAssistant();
                    break;
                case 38:
                    set0x26TrackDate(context);
                    break;
            }
            return;
        }
        set0x68SeatHeat();
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 63) {
            realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
            return;
        }
        if (i2 == 90) {
            realKeyLongClick1(context, 50);
            return;
        }
        if (i2 == 82) {
            realKeyLongClick1(context, 14);
            return;
        }
        if (i2 != 83) {
            switch (i2) {
                case 0:
                    realKeyLongClick1(context, 0);
                    break;
                case 1:
                    realKeyLongClick1(context, 7);
                    break;
                case 2:
                    realKeyLongClick1(context, 8);
                    break;
                case 3:
                    realKeyLongClick1(context, 20);
                    break;
                case 4:
                    realKeyLongClick1(context, 21);
                    break;
                case 5:
                    realKeyLongClick1(context, 68);
                    break;
                case 6:
                    realKeyLongClick1(context, 3);
                    break;
                case 7:
                    realKeyLongClick1(context, 2);
                    break;
                default:
                    switch (i2) {
                        case 14:
                            realKeyLongClick1(context, 45);
                            break;
                        case 15:
                            realKeyLongClick1(context, 46);
                            break;
                        case 16:
                            realKeyLongClick1(context, 47);
                            break;
                        case 17:
                            realKeyLongClick1(context, 48);
                            break;
                        case 18:
                            realKeyLongClick1(context, 49);
                            break;
                        default:
                            switch (i2) {
                                case 32:
                                    realKeyLongClick1(context, 32);
                                    break;
                                case 33:
                                    realKeyLongClick1(context, 33);
                                    break;
                                case 34:
                                    realKeyLongClick1(context, 34);
                                    break;
                                case 35:
                                    realKeyLongClick1(context, 35);
                                    break;
                                case 36:
                                    realKeyLongClick1(context, 36);
                                    break;
                                case 37:
                                    realKeyLongClick1(context, 37);
                                    break;
                                case 38:
                                    realKeyLongClick1(context, 38);
                                    break;
                                case 39:
                                    realKeyLongClick1(context, 39);
                                    break;
                                case 40:
                                    realKeyLongClick1(context, 40);
                                    break;
                                case 41:
                                    realKeyLongClick1(context, 41);
                                    break;
                                default:
                                    switch (i2) {
                                        case 52:
                                            realKeyLongClick1(context, 129);
                                            break;
                                        case 53:
                                            realKeyLongClick1(context, 59);
                                            break;
                                        case 54:
                                            realKeyLongClick1(context, 141);
                                            break;
                                        case 55:
                                            realKeyLongClick1(context, 52);
                                            break;
                                        case 56:
                                            realKeyLongClick1(context, 4);
                                            break;
                                        case 57:
                                            realKeyLongClick1(context, 68);
                                            break;
                                        default:
                                            switch (i2) {
                                                case 72:
                                                    realKeyLongClick1(context, 49);
                                                    break;
                                                case 73:
                                                    realKeyLongClick1(context, 47);
                                                    break;
                                                case 74:
                                                    realKeyLongClick1(context, 48);
                                                    break;
                                                case 75:
                                                    realKeyLongClick1(context, 45);
                                                    break;
                                                case 76:
                                                    realKeyLongClick1(context, 46);
                                                    break;
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(context, 15);
    }

    private void set0x21AirData() {
        if (isAirDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            bArr[3] = (byte) (bArr[3] & 239);
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = resolverAirTemperature(this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = resolverAirTemperature(this.mCanBusInfoInt[5]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x22RearRadarInfo(Context context) {
        if (is0x22DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x23FrontRadarInfo(Context context) {
        if (is0x23DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x24BaseInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorChange()) {
            updateDoorView(context);
        }
    }

    private void set0x25ParkAssistant() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void set0x26TrackDate(Context context) {
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
            updateParkUi(null, context);
        }
    }

    private void set0x68SeatHeat() {
        Log.i("cbc", "set0x68SeatHeat: ");
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2), 4);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2), 4);
            Log.i("cbc", "value : " + GeneralAirData.front_left_seat_heat_level);
            Log.i("cbc", "key : " + GeneralAirData.front_right_seat_heat_level);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x30VersionData() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void initSettingsItem(Context context) {
        this.mSettingItemIndeHashMap = new HashMap<>();
        SparseArray sparseArray = new SparseArray();
        List<SettingPageUiSet.ListBean> list = getUiMgr(context).getSettingUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                String titleSrn = itemList.get(i2).getTitleSrn();
                sparseArray.append((i << 8) | i2, titleSrn);
                this.mSettingItemIndeHashMap.put(titleSrn, new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        this.mSettingItemIndeHashMap.put(str, new SettingUpdateEntity(-1, -1, null));
        return getSettingUpdateEntity(str);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isDoorChange() {
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
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
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

    private String resolverAirTemperature(int i) {
        return i == 0 ? "LO" : i == 127 ? "HI" : (31 > i || i > 59) ? "" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mAirData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }
}
