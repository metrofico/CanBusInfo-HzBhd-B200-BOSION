package com.hzbhd.canbus.car._57;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    boolean currentPresetStore;
    private boolean mBackStatus;
    private int mBtOn;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mData;
    private int[] mDriveDatax16Now;
    private int mFrontCameraStatusNow;
    private boolean mFrontStatus;
    private String mFuelUnit;
    private int mId;
    private String mInvalid = " - -";
    private boolean mIsDoorFirst = true;
    private boolean mIsMute;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private String mMileageUnit;
    private int mPanoramicBtnNow;
    private int mPanoramicStatusNow;
    private int[] mRadarDataNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private TimerTask mTimeTask;
    private Timer mTimer;
    private byte[] mTrackDataNow;
    private UiMgr mUiMgr;
    private String mUsbSonTimeNow;
    private String mUsbSongAlbumNow;
    private String mUsbSongArtistNow;
    private String mUsbSongTitleNow;
    private byte[] mVersionInfoNow;
    private int mWheelKeyNow;
    private byte[] mediaDataNow;
    private int vol;

    private int getDriveData(int i, int i2) {
        return (i * 256) + i2;
    }

    private int getDriveData(int i, int i2, int i3) {
        return (i * 256 * 256) + (i2 * 256) + i3;
    }

    private int getFuelRange(int i) {
        switch (i) {
            case 1:
                return 10;
            case 2:
                return 12;
            case 3:
                return 20;
            case 4:
                return 30;
            case 5:
                return 40;
            case 6:
                return 50;
            case 7:
                return 70;
            case 8:
                return 80;
            case 9:
                return 90;
            case 10:
                return 100;
            default:
                return 60;
        }
    }

    private String getFuelUnit(int i) {
        return i == 0 ? " MPG" : i == 1 ? " km/L" : i == 2 ? " L/100km" : "";
    }

    private String getMileageUnit(int i) {
        return i == 0 ? " km" : i == 1 ? " mile" : "";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mId = getCurrentCanDifferentId();
        CanbusMsgSender.sendMsg(new byte[]{22, 45, 2, (byte) getCurrentEachCanId()});
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setKeyTrack0x11();
            setDriveData0x11();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 18) {
            setDoorData0x12();
            return;
        }
        if (i == 22) {
            setDriveData0x16();
            return;
        }
        if (i == 23) {
            setDriveData0x17();
            return;
        }
        if (i == 65) {
            setRadarData0x41();
        } else if (i == 232) {
            setPanoramic0xE8();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private boolean isWheelKeyDataChange() {
        int i = this.mWheelKeyNow;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return i2 == 1 || i2 == 2 || i2 == 8 || i2 == 9;
        }
        this.mWheelKeyNow = i2;
        return true;
    }

    private void setKeyTrack0x11() {
        if (isWheelKeyDataChange()) {
            int i = this.mCanBusInfoInt[4];
            if (i == 0) {
                wheelKeyLongClick(0);
            } else if (i == 1) {
                wheelKeyLongClick(7);
            } else if (i == 2) {
                wheelKeyLongClick(8);
            } else if (i == 4) {
                wheelKeyLongClick(HotKeyConstant.K_SPEECH);
            } else if (i == 5) {
                wheelKeyLongClick(14);
            } else if (i == 6) {
                wheelKeyLongClick(15);
            } else if (i == 8) {
                wheelKeyLongClick(45);
            } else if (i == 9) {
                wheelKeyLongClick(46);
            } else if (i == 11) {
                wheelKeyLongClick(2);
            }
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(bArr[9], bArr[8], 0, 5200, 16);
            updateParkUi(null, this.mContext);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x11() throws Resources.NotFoundException {
        String string;
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[7];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_1);
        } else if (i == 100) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_2);
        } else {
            string = this.mCanBusInfoInt[7] + "";
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDoorData0x12() {
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private boolean isDriveData0x16Change() {
        if (Arrays.equals(this.mDriveDatax16Now, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveDatax16Now = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDriveData0x16() {
        if (isDriveData0x16Change()) {
            int fuelRange = getFuelRange(this.mCanBusInfoInt[15]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(1, 0, new DecimalFormat("0.0").format((fuelRange / 21.0f) * mCanBusInfoInt[2]) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 2))));
            int[] iArr = this.mCanBusInfoInt;
            int driveData = getDriveData(iArr[3], iArr[4]);
            String str = (driveData / 10.0f) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
            if (driveData == 65535) {
                str = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 1, str));
            int[] iArr2 = this.mCanBusInfoInt;
            int driveData2 = getDriveData(iArr2[5], iArr2[6]);
            String str2 = (driveData2 / 10.0f) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
            if (driveData2 == 65535) {
                str2 = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 2, str2));
            int[] iArr3 = this.mCanBusInfoInt;
            int driveData3 = getDriveData(iArr3[9], iArr3[10], iArr3[11]);
            String mileageUnit = getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 1));
            this.mMileageUnit = mileageUnit;
            String str3 = (driveData3 / 10.0f) + mileageUnit;
            if (driveData3 == 65535) {
                str3 = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 3, str3));
            int[] iArr4 = this.mCanBusInfoInt;
            int driveData4 = getDriveData(iArr4[7], iArr4[8]);
            String fuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 2));
            this.mFuelUnit = fuelUnit;
            String str4 = (driveData4 / 10.0f) + fuelUnit;
            if (driveData4 == 65535) {
                str4 = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 4, str4));
            int[] iArr5 = this.mCanBusInfoInt;
            int driveData5 = getDriveData(iArr5[12], iArr5[13]);
            arrayList.add(new DriverUpdateEntity(1, 5, driveData5 != 65535 ? driveData5 + getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1)) : "--"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setDriveData0x17() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        float driveData = getDriveData(iArr[2], iArr[3], iArr[4]);
        if (driveData == 1.6777215E7f) {
            str = this.mInvalid;
        } else {
            str = (driveData / 10.0f) + this.mMileageUnit;
        }
        arrayList.add(new DriverUpdateEntity(2, 1, str));
        int[] iArr2 = this.mCanBusInfoInt;
        float driveData2 = getDriveData(iArr2[5], iArr2[6]);
        if (driveData2 == 65535.0f) {
            str2 = this.mInvalid;
        } else {
            str2 = (driveData2 / 10.0f) + this.mFuelUnit;
        }
        arrayList.add(new DriverUpdateEntity(2, 2, str2));
        int[] iArr3 = this.mCanBusInfoInt;
        float driveData3 = getDriveData(iArr3[7], iArr3[8], iArr3[9]);
        if (driveData3 == 1.6777215E7f) {
            str3 = this.mInvalid;
        } else {
            str3 = (driveData3 / 10.0f) + this.mMileageUnit;
        }
        arrayList.add(new DriverUpdateEntity(2, 4, str3));
        int[] iArr4 = this.mCanBusInfoInt;
        float driveData4 = getDriveData(iArr4[10], iArr4[11]);
        if (driveData4 == 65535.0f) {
            str4 = this.mInvalid;
        } else {
            str4 = (driveData4 / 10.0f) + this.mFuelUnit;
        }
        arrayList.add(new DriverUpdateEntity(2, 5, str4));
        int[] iArr5 = this.mCanBusInfoInt;
        float driveData5 = getDriveData(iArr5[12], iArr5[13], iArr5[14]);
        if (driveData5 == 1.6777215E7f) {
            str5 = this.mInvalid;
        } else {
            str5 = (driveData5 / 10.0f) + this.mMileageUnit;
        }
        arrayList.add(new DriverUpdateEntity(2, 7, str5));
        int[] iArr6 = this.mCanBusInfoInt;
        float driveData6 = getDriveData(iArr6[15], iArr6[16]);
        if (driveData6 == 65535.0f) {
            str6 = this.mInvalid;
        } else {
            str6 = (driveData6 / 10.0f) + this.mFuelUnit;
        }
        arrayList.add(new DriverUpdateEntity(2, 8, str6));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            this.mUiMgr.setShowRadar(this.mCanBusInfoInt[12] == 1);
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr2[6], 255, 255, iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setPanoramic0xE8() {
        if (!CommUtil.isMiscReverse() && isFrontCameraStatusChange()) {
            switchFCamera(this.mContext, this.mCanBusInfoInt[4] == 1);
        }
        if (isPanoramicBtnChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 3));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private boolean isPanoramicBtnChange() {
        int i = this.mPanoramicBtnNow;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.mPanoramicBtnNow = i2;
        return true;
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte) i5, (byte) i6, (byte) i7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        reportID3Info("", "", "", true, "ascii");
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i == 256) {
            i = 0;
        }
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = new DecimalFormat("00").format(i) + " " + str2 + "     ";
            } else {
                str4 = new DecimalFormat("00").format(i) + " 0" + str2 + "     ";
            }
        } else if (str2.length() == 5) {
            str4 = new DecimalFormat("00").format(i) + "  " + str2.substring(0, str2.length() - 1) + "    ";
        } else {
            str4 = new DecimalFormat("00").format(i) + " " + str2.substring(0, str2.length() - 1) + "    ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, allBandTypeData}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        Log.d("_55_aux", "  .");
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("AUX".getBytes(), 0, bArr, 0, "AUX".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        Log.d("_55_atv", "  .");
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, "A2DP        ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = new DecimalFormat("00").format(b4);
        String str8 = new DecimalFormat("00").format(b5);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format((b7 * 256) + i), new Object[0]) + " " + str7 + str8 + "    ").getBytes()));
        if (!str8.equals(this.mUsbSonTimeNow)) {
            this.mUsbSonTimeNow = str8;
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -104, 0, 0, 0, 0, b4, b5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        reportID3Info(str4, str5, str6, false, "unicode");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        reportID3Info("", "", "", true, "ascii");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = new DecimalFormat("00").format(b4);
        String str6 = new DecimalFormat("00").format(b5);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + " " + str5 + str6 + "    ").getBytes()));
        if (str6.equals(this.mUsbSonTimeNow)) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, b4, b5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.mUsbSonTimeNow = str6;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 1}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 2}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        super.cameraInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
        super.cameraDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 3}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 4}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z && i == 0) {
            byte[] bArr2 = new byte[30];
            Arrays.fill(bArr2, (byte) 0);
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 0}, bArr2), new byte[]{0}));
        }
        this.mBtOn = z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.vol = i;
        this.mIsMute = z;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        int i13 = (i2 << 3) | (i3 << 4) | (i6 << 7) | (this.mBtOn << 6) | ((this.mIsMute ? 1 : 0) << 5) | (i11 << 2) | (i12 << 1);
        this.mData = i13;
        CanbusMsgSender.sendMsg(new byte[]{22, -26, (byte) i13, 0, 0, 0, (byte) this.vol, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        String str4 = b == 16 ? "LOADING " : b == 17 ? "EJECTING" : "";
        if (i7 == 48) {
            str4 = "PAUSE   ";
        } else if (i7 == 65) {
            str4 = "STOP    ";
        } else if (i7 == 241) {
            str4 = "ERROR   ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 6}, DataHandleUtils.byteMerger(str4.getBytes(), new byte[]{0, 0, 0, 0})));
        DecimalFormat decimalFormat = new DecimalFormat("00");
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger((new DecimalFormat("000").format(i4) + " " + decimalFormat.format(i / 60) + decimalFormat.format(i & 60)).getBytes(), new byte[]{0, 0, 0, 0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, ("    " + new DecimalFormat("00").format((i / 60) % 60) + new DecimalFormat("00").format(i % 60) + "    ").getBytes()));
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    private void wheelKeyShortClick(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void wheelKeyLongClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private boolean isDoorDataChange() {
        if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
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

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isPanoramicStatusChange() {
        int i = this.mCanBusInfoInt[6];
        if (this.mPanoramicStatusNow == i) {
            return false;
        }
        this.mPanoramicStatusNow = i;
        return true;
    }

    private boolean isFrontCameraStatusChange() {
        int i = this.mCanBusInfoInt[4];
        if (this.mFrontCameraStatusNow == i) {
            return false;
        }
        this.mFrontCameraStatusNow = i;
        return true;
    }

    private boolean isTrackDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[8], bArr[9]};
        if (Arrays.equals(this.mTrackDataNow, bArr2)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(bArr2, 2);
        return true;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isSupportDriveData0x17() {
        int i = this.mId;
        return (i == 0 || i == 17 || i == 18) ? false : true;
    }

    private boolean isSupportPanoramic() {
        int i = this.mId;
        return i == 18 || i == 12 || i == 13 || i == 14 || i == 15 || i == 6;
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [com.hzbhd.canbus.car._57.MsgMgr$1] */
    private void reportID3Info(final String str, final String str2, final String str3, final boolean z, final String str4) {
        new Thread() { // from class: com.hzbhd.canbus.car._57.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    if (z) {
                        sleep(1000L);
                    }
                    if (str.equals(MsgMgr.this.mUsbSongTitleNow) && str2.equals(MsgMgr.this.mUsbSongAlbumNow) && str3.equals(MsgMgr.this.mUsbSongArtistNow)) {
                        return;
                    }
                    MsgMgr.this.mUsbSongTitleNow = str;
                    MsgMgr.this.mUsbSongAlbumNow = str2;
                    MsgMgr.this.mUsbSongArtistNow = str3;
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -30, str, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -29, str2, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -28, str3, str4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str, String str2) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, b}, DataHandleUtils.exceptBOMHead(str.getBytes(str2))), 34));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startRequest0x32() {
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        if (this.mTimeTask == null) {
            TimerTask timerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._57.MsgMgr.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 50});
                }
            };
            this.mTimeTask = timerTask;
            this.mTimer.schedule(timerTask, 0L, 1000L);
        }
    }
}
