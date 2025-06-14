package com.hzbhd.canbus.car._352;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    List<PanoramicBtnUpdateEntity> list;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    private CountDownTimer panoramaTimer;

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
        getUiMgr(this.mContext).makeConnetion();
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
        initOriginalCarDevice();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        getUiMgr(this.mContext).send0xC0(1, 1);
        send0xC2(i, str, str2);
        getUiMgr(this.mContext).send0xC3(16, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).send0xC0(2, 34);
        getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        getUiMgr(this.mContext).send0xC0(3, 34);
        getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        getUiMgr(this.mContext).send0xC0(3, 34);
        getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).send0xC0(5, 64);
        getUiMgr(this.mContext).send0xC3(64, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        getUiMgr(this.mContext).send0xC0(6, 34);
        getUiMgr(this.mContext).send0xC3(34, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0xC0(7, 48);
        getUiMgr(this.mContext).send0xC3(48, 0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).send0xC0(8, 19);
        getUiMgr(this.mContext).send0xC3(19, i2, i, 0, 0, b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        getUiMgr(this.mContext).send0xC0(8, 19);
        getUiMgr(this.mContext).send0xC3(19, i2, i, 0, 0, b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        getUiMgr(this.mContext).send0xC4(i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).send0xC8(0, !z ? 1 : 0, i5, i6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14backLightInfo();
            return;
        }
        if (i == 22) {
            set0x16SpeedInfo();
            return;
        }
        if (i == 41) {
            set0x29EPSInfo();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo();
            return;
        }
        if (i == 210) {
            set0xD2CompassInfo();
            return;
        }
        if (i == 112) {
            set0x70RightCameraInfo();
            return;
        }
        if (i != 113) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    set0x21MediaInfo();
                    break;
                case 34:
                    set0x22RearRadar();
                    break;
                case 35:
                    set0x23FrontRadar();
                    break;
                case 36:
                    set0x24BasicInfo();
                    break;
            }
            return;
        }
        set0x71SpeedInfo();
    }

    private void set0xD2CompassInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_352_setting_zhi_nan"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan1"), getCompassState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_352_setting_zhi_nan"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 1));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_352_setting_zhi_nan"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_352_setting_zhi_nan", "_352_setting_zhi_nan3"), Integer.valueOf((this.mCanBusInfoInt[3] * HotKeyConstant.K_AIR_WIND_INC) / 256)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private Object getCompassState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._352_setting_zhi_nan1_0);
        }
        return this.mContext.getString(R.string._352_setting_zhi_nan1_1);
    }

    private void set0x71SpeedInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_352_car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_352_car_tire_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[3], iArr[2])).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x70RightCameraInfo() {
        if (isPanoramicInfoChange()) {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            if (this.list == null) {
                this.list = new ArrayList();
            }
            this.list.add(new PanoramicBtnUpdateEntity(0, boolBit7));
            this.list.add(new PanoramicBtnUpdateEntity(1, boolBit6));
            GeneralParkData.dataList = this.list;
            updateParkUi(null, this.mContext);
            if (boolBit6) {
                forceReverse(this.mContext, true);
                CountDownTimer countDownTimer = this.panoramaTimer;
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    this.panoramaTimer = null;
                    return;
                }
                return;
            }
            forceReverse(this.mContext, false);
        }
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x29EPSInfo() {
        if (isTrackInfoChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x24BasicInfo() {
        if (isBasicInfoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x23FrontRadar() {
        if (isFrontRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22RearRadar() {
        if (isRearRadarDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x21MediaInfo() {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(33);
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.cdStatus = get0x21Media(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        GeneralOriginalCarDeviceData.runningState = getUsbStateInfo(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
        GeneralOriginalCarDeviceData.mList = setInfo(this.mCanBusInfoInt);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x16SpeedInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_352_car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_352_car_speed");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[3], iArr[2])).append("km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void set0x14backLightInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i <= 50) {
            setBacklightLevel(1);
            return;
        }
        if (i > 50 && i <= 100) {
            setBacklightLevel(2);
            return;
        }
        if (i > 100 && i <= 150) {
            setBacklightLevel(3);
            return;
        }
        if (i > 150 && i <= 200) {
            setBacklightLevel(4);
        } else {
            if (i <= 200 || i > 255) {
                return;
            }
            setBacklightLevel(5);
        }
    }

    private void set0x20WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
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
        if (i == 3) {
            buttonKey(46);
            return;
        }
        if (i == 4) {
            buttonKey(45);
            return;
        }
        if (i == 23) {
            buttonKey(151);
            return;
        }
        if (i != 24) {
            switch (i) {
                case 7:
                    buttonKey(2);
                    break;
                case 8:
                    buttonKey(HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    buttonKey(14);
                    break;
                case 10:
                    buttonKey(15);
                    break;
                case 11:
                    buttonKey(HotKeyConstant.K_SPEECH);
                    break;
                case 12:
                    buttonKey(14);
                    break;
                case 13:
                    buttonKey(15);
                    break;
            }
            return;
        }
        buttonKey(HotKeyConstant.K_DISP);
    }

    private void send0xC2(int i, String str, String str2) {
        int i2;
        int lsb;
        int msb;
        if (str.equals("FM") || str.equals("FM3")) {
            i2 = 0;
        } else if (str.equals("FM1")) {
            i2 = 1;
        } else {
            i2 = str.equals("FM2") ? 2 : 16;
        }
        if (i2 == 16) {
            lsb = getLsb(Integer.parseInt(str2));
            msb = getMsb(Integer.parseInt(str2));
        } else {
            lsb = getLsb(Integer.parseInt(str2) * 100);
            msb = getMsb(Integer.parseInt(str2) * 100);
        }
        getUiMgr(this.mContext).send0xC2(i2, lsb, msb, i);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._352.MsgMgr$1] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._352.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_1", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_2", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_3", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_4", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_352_info_0x21_5", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(33, new OriginalDeviceData(arrayList));
    }

    private static class OriginalDeviceData {
        private final String[] bottomBtns;
        private final List<OriginalCarDevicePageUiSet.Item> list;

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
            this(list, null);
        }

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
            this.list = list;
            this.bottomBtns = strArr;
        }

        public List<OriginalCarDevicePageUiSet.Item> getItemList() {
            return this.list;
        }

        public String[] getBottomBtns() {
            return this.bottomBtns;
        }
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private List<OriginalCarDeviceUpdateEntity> setInfo(int[] iArr) {
        String str = iArr[4] + ":" + iArr[5];
        String str2 = getMsbLsbResult(iArr[6], iArr[7]) + "";
        String str3 = getMsbLsbResult(iArr[8], iArr[9]) + "";
        String str4 = iArr[10] + "";
        String str5 = iArr[11] + "%";
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, str2));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str3));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str4));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str5));
        }
        return arrayList;
    }

    private String get0x21Media(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._352_usb_state2);
        }
        if (i != 2) {
            return this.mContext.getString(R.string._352_usb_state1);
        }
        return this.mContext.getString(R.string._352_usb_state3);
    }

    private String getUsbStateInfo(int i) {
        switch (i) {
            case 1:
                return ":" + this.mContext.getString(R.string._352_usb_state5);
            case 2:
                return ":" + this.mContext.getString(R.string._352_usb_state6);
            case 3:
                return ":" + this.mContext.getString(R.string._352_usb_state7);
            case 4:
                return ":" + this.mContext.getString(R.string._352_usb_state8);
            case 5:
                return ":" + this.mContext.getString(R.string._352_usb_state9);
            case 6:
                return ":" + this.mContext.getString(R.string._352_usb_state10);
            default:
                return ":" + this.mContext.getString(R.string._352_usb_state4);
        }
    }

    private UiMgr getUiMgr(Context context) {
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
        int brightness = getBrightness();
        if (brightness == 5) {
            setBacklightLevel(0);
        } else {
            setBacklightLevel(brightness + 1);
        }
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public void panoramicSwitch(boolean z) {
        forceReverse(this.mContext, z);
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

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }
}
