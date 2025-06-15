package com.hzbhd.canbus.car._221;

import android.content.Context;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
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
import java.util.HashMap;


public class MsgMgr extends AbstractMsgMgr {
    static final int VEHICLE_17_H2S = 7;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferentId;
    private int mFrontCameraStatusNow;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private int mKeyStatus2;
    private int mKonb;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int mPanoramaBtnNow;
    private byte[] mRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap;
    private byte[] mTrackData;
    private int mVolume;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 17});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 18});
        this.mDifferentId = getCurrentCanDifferentId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            set0x11CarBaseData(context);
            return;
        }
        if (i == 18) {
            set0x12CarBaseData2(context);
            return;
        }
        if (i == 33) {
            set0x21PanelKey(context);
            return;
        }
        if (i == 34) {
            set0x22KnobKey(context);
            return;
        }
        if (i == 49) {
            set0x31AirData(context);
            return;
        }
        if (i == 50) {
            set0x32CarData(context);
            return;
        }
        if (i == 65) {
            set0x41RadarData(context);
        } else if (i == 232) {
            set0xE8PanoramicInfo(context);
        } else {
            if (i != 240) {
                return;
            }
            set0xf0Version();
        }
    }

    private void set0x11CarBaseData(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[4];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 0) {
            realKeyLongClick1(context, 0);
        } else if (i2 == 1) {
            realKeyLongClick1(context, 7);
        } else if (i2 == 2) {
            realKeyLongClick1(context, 8);
        } else if (i2 == 5) {
            realKeyLongClick1(context, 14);
        } else if (i2 == 6) {
            realKeyLongClick1(context, 15);
        } else {
            switch (i2) {
                case 12:
                    realKeyLongClick1(context, 2);
                    break;
                case 13:
                    realKeyLongClick1(context, 46);
                    break;
                case 14:
                    realKeyLongClick1(context, 45);
                    break;
            }
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 5500, 16);
            updateParkUi(null, context);
        }
    }

    private void set0x12CarBaseData2(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        if (isDoorChange()) {
            updateDoorView(context);
        }
    }

    private void set0x21PanelKey(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick2(context, 0);
        }
        if (i == 1) {
            realKeyClick2(context, HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 2) {
            realKeyClick2(context, 21);
            return;
        }
        if (i == 3) {
            realKeyClick2(context, 20);
            return;
        }
        if (i == 44) {
            realKeyClick2(context, 2);
            return;
        }
        if (i == 63) {
            realKeyClick2(context, 52);
            return;
        }
        if (i == 67) {
            realKeyClick2(context, 3);
            return;
        }
        switch (i) {
            case 71:
                realKeyClick2(context, 14);
                break;
            case 72:
                realKeyClick2(context, 15);
                break;
            case 73:
                realKeyClick2(context, 62);
                break;
            case 74:
                realKeyClick2(context, 58);
                break;
            case 75:
                realKeyClick2(context, 128);
                break;
        }
    }

    private void set0x22KnobKey(Context context) {
        if (this.mCanBusInfoInt[2] == 1) {
            int i = this.mKonb - this.mCanBusInfoByte[3];
            if (i < 0) {
                if (this.mVolume < 40) {
                    realKeyClick3_1(context, 7, 0, Math.abs(i));
                }
            } else {
                if (i <= 0) {
                    return;
                }
                if (this.mVolume > 0) {
                    realKeyClick3_1(context, 8, 0, Math.abs(i));
                }
            }
            this.mKonb = this.mCanBusInfoByte[3];
        }
    }

    private void set0x31AirData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            int i = this.mCanBusInfoInt[6];
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            if (i != 10) {
                switch (i) {
                    case 2:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 3:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                    case 4:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 5:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                    case 6:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        break;
                    case 7:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                }
            } else {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            }
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            GeneralAirData.front_left_temperature = resolveTemp(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = resolveTemp(this.mCanBusInfoInt[9]);
            updateOutDoorTemp(context, resolveOutdoorTemp(context, this.mCanBusInfoInt[13]));
            updateAirActivity(context, 1001);
        }
    }

    private void set0x41RadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationDataType2(3, iArr[2], 7, iArr[3], 7, iArr[4], 3, iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x32CarData(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        double d = iArr[8] * 0.1d;
        double d2 = iArr[11] - 40;
        double d3 = iArr[14] - 40;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, new DecimalFormat("0.0").format(d) + " V"));
        arrayList.add(new DriverUpdateEntity(0, 1, d2 + getTempUnitC(context)));
        arrayList.add(new DriverUpdateEntity(0, 2, d3 + getTempUnitC(context)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0xf0Version() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte) i8, (byte) i6, (byte) i7});
    }

    private void set0xE8PanoramicInfo(Context context) {
        if (!CommUtil.isPanoramic(context) && isFrontCameraStatusChange()) {
            switchFCamera(context, this.mCanBusInfoInt[2] == 1);
        }
        if (isPanoramaBtnChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 3));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        super.cameraInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
        super.cameraDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        this.mVolume = i;
    }

    private boolean isPanoramaBtnChange() {
        int i = this.mFrontCameraStatusNow;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.mPanoramaBtnNow = i2;
        return true;
    }

    private boolean isFrontCameraStatusChange() {
        int i = this.mCanBusInfoInt[2];
        if (this.mFrontCameraStatusNow == i) {
            return false;
        }
        this.mFrontCameraStatusNow = i;
        return true;
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        SettingUpdateEntity settingUpdateEntity = new SettingUpdateEntity(-1, -1, null);
        this.mSettingItemIndeHashMap.put(str, settingUpdateEntity);
        return settingUpdateEntity;
    }

    private String resolveOutdoorTemp(Context context, int i) {
        return i == 255 ? "--.-" : ((i * 0.5d) - 40.0d) + getTempUnitC(context);
    }

    private String resolveTemp(int i) {
        return i == 0 ? "LO" : i == 255 ? "HI" : (i <= 0 || i > 38) ? "" : i + getTempUnitC(this.mContext);
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
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[8], bArr[9]};
        if (Arrays.equals(this.mTrackData, bArr2)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(bArr2, 2);
        return true;
    }

    private void realKeyClick3(int i) {
        realKeyClick3_1(this.mContext, i, this.mCanBusInfoInt[2], Math.abs((int) this.mCanBusInfoByte[3]));
    }

    private void realKeyClick2(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[5]);
    }
}
