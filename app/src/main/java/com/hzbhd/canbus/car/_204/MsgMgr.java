package com.hzbhd.canbus.car._204;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.util.ArrayList;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int volKnobValue;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mEachId;
    private boolean mFrontStatus;
    private int mFrontViewStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int mPanoramicStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr mUiMgr;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mEachId = getCurrentEachCanId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), 17});
        initAmplifierData(context);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), getmUiMgr(context).getAmplifierPageUiSet(context));
        }
        final byte[][] bArr = {new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume}, new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 10)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 10)}, new byte[]{22, -83, 4, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -83, 5, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -83, 6, (byte) GeneralAmplifierData.bandTreble}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._204.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    CanbusMsgSender.sendMsg(bArr2[i]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private UiMgr getmUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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
            set0x12CarDetailedData(context);
            return;
        }
        if (i == 33) {
            set0x24WheelKeyData();
            return;
        }
        if (i == 34) {
            set0x22KnobData();
            return;
        }
        if (i == 50) {
            set0X32CarBodyData();
            return;
        }
        if (i == 98) {
            set0x62CarSetupDate();
            return;
        }
        if (i == 166) {
            set0xA6AmplifierData();
            return;
        }
        if (i == 232) {
            set0xE8VehicleScreenStatusData();
            return;
        }
        if (i == 240) {
            set0xF0Version();
        } else if (i == 65) {
            set0x41FrontRearRadar();
        } else {
            if (i != 66) {
                return;
            }
            set0x42LeftRightRadar();
        }
    }

    private void set0x11CarBaseData(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[4];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 0) {
            realKeyClick(0);
        } else if (i2 == 1) {
            realKeyClick(7);
        } else if (i2 == 2) {
            realKeyClick(8);
        } else if (i2 == 5) {
            realKeyClick(14);
        } else if (i2 != 103) {
            switch (i2) {
                case 8:
                    realKeyClick(45);
                    break;
                case 9:
                    realKeyClick(46);
                    break;
                case 10:
                    realKeyClick(2);
                    break;
            }
        } else {
            realKeyClick(220);
        }
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        }
        updateParkUi(null, context);
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void set0x12CarDetailedData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
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

    private void set0x24WheelKeyData() {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 6) {
            realKeyClick2(this.mContext, 50);
        }
        if (i2 == 9) {
            realKeyClick2(this.mContext, 3);
            return;
        }
        if (i2 == 37) {
            realKeyClick2(this.mContext, 128);
            return;
        }
        switch (i2) {
            case 64:
                startMainActivity(this.mContext);
                break;
            case 65:
                realKeyClick2(this.mContext, 76);
                break;
            case 66:
                realKeyClick2(this.mContext, 4);
                break;
        }
    }

    private void realKeyClick2(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void set0x22KnobData() {
        int i = this.mKeyStatus;
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 != 1) {
            return;
        }
        int i3 = volKnobValue - iArr[3];
        if (i3 < 0) {
            realKeyClick03(7, Math.abs(i3));
        } else if (i3 > 0) {
            realKeyClick03(8, Math.abs(i3));
        }
        volKnobValue = this.mCanBusInfoInt[3];
    }

    private void realKeyClick03(int i, int i2) {
        realKeyClick3_1(this.mContext, i, 0, i2);
    }

    private void set0X32CarBodyData() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[4] << 8) | iArr[5];
        int i2 = iArr[7] | (iArr[6] << 8);
        String str = "无效值";
        String str2 = i == 65535 ? "无效值" : i + " RPM";
        if (i2 != 65535) {
            str = i2 + " KM/H";
            updateSpeedInfo(i2);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, str2));
        arrayList.add(new DriverUpdateEntity(0, 1, str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x41FrontRearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.mDisableData2 = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationDataType2(2, iArr[6], 3, iArr[7], 3, iArr[8], 4, iArr[9]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationDataType2(2, iArr2[2], 4, iArr2[3], 4, iArr2[4], 2, iArr2[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x42LeftRightRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.mDisableData2 = 255;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRightRadarLocationData(2, iArr[2], 0, 0, iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(2, iArr2[6], 0, 0, iArr2[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0xA6AmplifierData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 10;
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 10;
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
            saveAmplifierData(this.mContext, getCanId());
            updateAmplifierActivity(new Bundle());
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0xF0Version() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0xE8VehicleScreenStatusData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            int i = this.mFrontViewStatus;
            int i2 = this.mCanBusInfoInt[4];
            if (i != i2) {
                this.mFrontViewStatus = i2;
                if (!CommUtil.isMiscReverse()) {
                    switchFCamera(this.mContext, this.mFrontViewStatus == 1);
                }
            }
            int i3 = this.mPanoramicStatus;
            int i4 = this.mCanBusInfoInt[5];
            if (i3 != i4) {
                this.mPanoramicStatus = i4;
                switchFCamera(this.mContext, i4 == 1);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[7] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[8] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[6] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[4] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[5] == 1));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x62CarSetupDate() {
        if (isDataChange(this.mCanBusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(2, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(2, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(2, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(2, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(2, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(3, 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(3, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(3, 2, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(3, 3, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1) == 1 ? "valid" : "invalid"));
            arrayList.add(new SettingUpdateEntity(3, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1) != 1 ? "invalid" : "valid"));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i8, (byte) i6, 0, 0, 0, 0, 0, 0, 0});
    }

    void updateSettings(int i, int i2, Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, obj));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
