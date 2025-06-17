package com.hzbhd.canbus.car._107;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;


public class MsgMgr extends AbstractMsgMgr {
    private String callState;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private SparseArray<KeyMap> mKeyMapArray;
    private UiMgr mUiMgr;
    private final int VEHICLE_TYPE_DEFAULT = 0;
    private final int VEHICLE_TYPE_LADA_VESTA = 1;
    private final int VEHICLE_TYPE_RENAULT_FLUENCE = 2;
    private boolean isCalling = false;
    private boolean isMute = false;
    private int VolTag = 0;
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int nowCarDoor = 0;

    private interface IKeyMapInterface {
        int getKey1();

        int getKey10();

        int getKey11();

        int getKey2();

        int getKey3();

        int getKey4();

        int getKey5();

        int getKey6();

        int getKey7();

        int getKey8();

        int getKey9();
    }

    private String getFuelUnit(int i) {
        return i == 0 ? " L/100KM" : i == 1 ? " KM/L" : i == 2 ? " MPG（US）" : i == 3 ? " MGP（UK）" : "--";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mDifferent = getCurrentCanDifferentId();
        initKeyMap();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setCarSetData0x14();
            return;
        }
        if (i == 39) {
            setAirData0x27();
            return;
        }
        if (i == 41) {
            setTrackInfo();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 51) {
            set0x33CarInfo();
            return;
        }
        if (i == 56) {
            setCarRangeInfo();
            return;
        }
        if (i == 36) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setDoorData0x24();
            return;
        }
        if (i == 37) {
            setParkingState();
            return;
        }
        if (i == 148) {
            set0x94PanoramaInfo();
            return;
        }
        if (i != 149) {
            switch (i) {
                case 32:
                    selectWheelKey();
                    break;
                case 33:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x21();
                        break;
                    }
                    break;
                case 34:
                    setRadarInfo();
                    break;
            }
            return;
        }
        set0x95Info();
    }

    private boolean getCallState() {
        String strRegisterShareStringListener = ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_BT_CALL_STATE, new IShareStringListener() { // from class: com.hzbhd.canbus.car._107.MsgMgr.1
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public void onString(String str) {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.isCalling = msgMgr.setCallState(str);
            }
        });
        this.callState = strRegisterShareStringListener;
        if (strRegisterShareStringListener != null) {
            this.isCalling = setCallState(strRegisterShareStringListener);
        }
        return this.isCalling;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setCallState(String str) {
        try {
            return ((Boolean) new JSONObject(str).get("isCall")).booleanValue();
        } catch (JSONException e) {
            MyLog.temporaryTracking(e.toString());
            e.printStackTrace();
            return false;
        }
    }

    private void selectWheelKey() {
        switch (getCurrentEachCanId()) {
            case 1:
                setSwcMode1();
                break;
            case 2:
                setSwcMode2();
                break;
            case 3:
                setSwcMode3();
                break;
            case 4:
                setSwcMode4();
                break;
            case 5:
                setSwcMode5();
                break;
            case 6:
                setSwcMode6();
                break;
            case 7:
                setSwcMode7();
                break;
            default:
                set0x20WheelKeyData(this.mContext);
                break;
        }
    }

    private void setSwcMode7() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8);
            return;
        }
        if (i == 3) {
            if (iArr[3] == 0) {
                return;
            }
            realKeyClick4(this.mContext, 46);
            return;
        }
        if (i == 4) {
            if (iArr[3] == 0) {
                return;
            }
            realKeyClick4(this.mContext, 45);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 62);
            return;
        }
        switch (i) {
            case 6:
                if (getCallState()) {
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
                    break;
                } else {
                    realKeyLongClick1(this.mContext, 3);
                    break;
                }
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                if (!getCallState()) {
                    realKeyLongClick1(this.mContext, 14);
                    break;
                }
                break;
        }
    }

    private void setSwcMode6() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 3) {
            if (iArr[3] == 0) {
                return;
            }
            realKeyClick4(this.mContext, 45);
            return;
        }
        if (i == 4) {
            if (iArr[3] == 0) {
                return;
            }
            realKeyClick4(this.mContext, 46);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 49);
            return;
        }
        switch (i) {
            case 6:
                if (getCallState()) {
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
                    break;
                } else {
                    realKeyLongClick1(this.mContext, 3);
                    break;
                }
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 14);
                break;
        }
    }

    private void setSwcMode5() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 15);
            return;
        }
        switch (i) {
            case 6:
                realKeyLongClick1(this.mContext, 14);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 3);
                break;
        }
    }

    private void setSwcMode4() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 49);
            return;
        }
        switch (i) {
            case 6:
                realKeyLongClick1(this.mContext, 3);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
                break;
        }
    }

    private void setSwcMode3() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 49);
            return;
        }
        switch (i) {
            case 6:
                realKeyLongClick1(this.mContext, 3);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
                break;
        }
    }

    private void setSwcMode2() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 49);
            return;
        }
        switch (i) {
            case 6:
                realKeyLongClick1(this.mContext, 3);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 62);
                break;
        }
    }

    private void setSwcMode1() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0);
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46);
            return;
        }
        if (i == 22) {
            realKeyLongClick1(this.mContext, 50);
            return;
        }
        switch (i) {
            case 6:
                realKeyLongClick1(this.mContext, 3);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(this.mContext, 2);
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
    }

    private void set0x95Info() {
        if (this.VolTag == 0) {
            this.VolTag = 1;
            realKeyClick(this.mContext, 7);
        }
        if (this.mCanBusInfoInt[2] != 1 || this.isMute) {
            return;
        }
        realKeyClick(this.mContext, 3);
    }

    private void set0x94PanoramaInfo() {
        if (this.mCanBusInfoInt[2] == 1) {
            forceReverse(this.mContext, true);
            ArrayList arrayList = new ArrayList();
            int[] iArr = this.mCanBusInfoInt;
            if (iArr[3] == 0) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                arrayList.add(new PanoramicBtnUpdateEntity(3, false));
            } else {
                int i = iArr[4];
                if (i == 1) {
                    arrayList.add(new PanoramicBtnUpdateEntity(0, true));
                    arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                } else if (i == 2) {
                    arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(1, true));
                    arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                } else if (i == 3) {
                    arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(2, true));
                    arrayList.add(new PanoramicBtnUpdateEntity(3, false));
                } else if (i == 4) {
                    arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(1, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(2, false));
                    arrayList.add(new PanoramicBtnUpdateEntity(3, true));
                }
            }
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
            return;
        }
        forceReverse(this.mContext, false);
    }

    private void set0x33CarInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_107_car_language"), Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarRangeInfo() {
        String fuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
        String str = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "MILE" : "KM";
        String str2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "Gal" : "L";
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 4, sb.append(getDriveData(iArr[3], iArr[4])).append(fuelUnit).toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 5, sb2.append(getDriveData(iArr2[5], iArr2[6], iArr2[7])).append(str).toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 6, sb3.append(getDriveData(iArr3[8], iArr3[9])).append(str2).toString()));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, sb4.append(getDriveData(iArr4[10], iArr4[11])).append("KM/H").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        DecimalFormat decimalFormat = this.df_2Integer;
        int[] iArr5 = this.mCanBusInfoInt;
        updateSpeedInfo(Integer.parseInt(decimalFormat.format(((iArr5[10] * 256) + iArr5[11]) / 10.0f)));
    }

    private String getDriveData(int i, int i2) {
        int i3 = (i * 256) + i2;
        return i3 == 65535 ? "--" : String.format("%.1f", Float.valueOf(i3 * 0.1f));
    }

    private String getDriveData(int i, int i2, int i3) {
        int i4 = (i * 65536) + (i2 * 256) + i3;
        return i4 == 16777215 ? "--" : String.format("%.1f", Float.valueOf(i4 * 0.1f));
    }

    private void setParkingState() {
        Resources resources;
        int i;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            resources = this.mContext.getResources();
            i = R.string.english_on;
        } else {
            resources = this.mContext.getResources();
            i = R.string.english_off;
        }
        arrayList.add(new DriverUpdateEntity(0, 3, resources.getString(i)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? 1 : 0)));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    private void setRadarInfo() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5888, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirData0x27() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[2];
        if (1 > i || i > 254) {
            return i == 0 ? "--" : "";
        }
        return (this.mCanBusInfoInt[2] * 0.5f) + "" + getTempUnitC(this.mContext);
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        int i = this.nowCarDoor;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.nowCarDoor = i2;
            updateDoorView(this.mContext);
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        String str = intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? "" : "D" : "R" : "P";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, str));
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_1);
        } else if (i == 255) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_2);
        } else {
            string = this.mCanBusInfoInt[2] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 2, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x20WheelKeyData(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey3());
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey4());
            return;
        }
        if (i == 3) {
            realKeyClick4(context, getKeyMap(this.mDifferent).getKey10());
            return;
        }
        if (i == 4) {
            realKeyClick4(context, getKeyMap(this.mDifferent).getKey11());
            return;
        }
        if (i == 19) {
            realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey7());
            return;
        }
        if (i == 20) {
            realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey8());
            return;
        }
        if (i != 22) {
            switch (i) {
                case 6:
                    realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey6());
                    break;
                case 7:
                    realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey1());
                    break;
                case 8:
                    realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey9());
                    break;
                case 9:
                    realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey2());
                    break;
            }
            return;
        }
        realKeyLongClick1(context, getKeyMap(this.mDifferent).getKey5());
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private KeyMap getKeyMap(int i) {
        KeyMap keyMap = this.mKeyMapArray.get(i);
        return keyMap == null ? this.mKeyMapArray.get(0) : keyMap;
    }

    private void initKeyMap() {
        SparseArray<KeyMap> sparseArray = new SparseArray<>();
        this.mKeyMapArray = sparseArray;
        sparseArray.put(0, new KeyMap());
        this.mKeyMapArray.put(1, new KeyMap() { // from class: com.hzbhd.canbus.car._107.MsgMgr.2
            @Override // com.hzbhd.canbus.car._107.MsgMgr.KeyMap, com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
            public int getKey2() {
                return 3;
            }

            @Override // com.hzbhd.canbus.car._107.MsgMgr.KeyMap, com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
            public int getKey5() {
                return HotKeyConstant.K_DOWN_HANGUP;
            }

            @Override // com.hzbhd.canbus.car._107.MsgMgr.KeyMap, com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
            public int getKey6() {
                return HotKeyConstant.K_UP_PICKUP;
            }
        });
        this.mKeyMapArray.put(2, new KeyMap() { // from class: com.hzbhd.canbus.car._107.MsgMgr.3
            @Override // com.hzbhd.canbus.car._107.MsgMgr.KeyMap, com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
            public int getKey6() {
                return HotKeyConstant.K_MUTE_PHONE_ON_OUT;
            }
        });
    }

    private class KeyMap implements IKeyMapInterface {
        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey1() {
            return 2;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey10() {
            return 45;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey11() {
            return 46;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey2() {
            return HotKeyConstant.K_PHONE_ON_OFF;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey3() {
            return 7;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey4() {
            return 8;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey5() {
            return 49;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey6() {
            return 3;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey7() {
            return 20;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey8() {
            return 21;
        }

        @Override // com.hzbhd.canbus.car._107.MsgMgr.IKeyMapInterface
        public int getKey9() {
            return HotKeyConstant.K_SPEECH;
        }

        private KeyMap() {
        }
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
