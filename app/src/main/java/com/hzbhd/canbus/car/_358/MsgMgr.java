package com.hzbhd.canbus.car._358;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import org.apache.log4j.Priority;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int mDifferentId;
    private int eachId;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr uiMgr;

    private int getSurroundVolFRprogress(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return i;
            default:
                switch (i) {
                    case 247:
                        return -9;
                    case 248:
                        return -8;
                    case com.hzbhd.canbus.car._464.MsgMgr.REAR_DISC_MODE /* 249 */:
                        return -7;
                    case 250:
                        return -6;
                    case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                        return -5;
                    case 252:
                        return -4;
                    case 253:
                        return -3;
                    case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                        return -2;
                    case 255:
                        return -1;
                    default:
                        return 0;
                }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 23) {
            setAmplifier0x17();
            return;
        }
        if (i == 32) {
            setWheelKey0x20();
            return;
        }
        if (i == 36) {
            setDoorInfo0x24();
            setDoorInfo0x2402();
        } else if (i == 41) {
            setTrackData0x29();
        } else {
            if (i != 48) {
                return;
            }
            VersionInfo0x30();
        }
    }

    private void setWheelKey0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(20);
                break;
            case 4:
                buttonKey(21);
                break;
            case 5:
                buttonKey(3);
                break;
            case 7:
                buttonKey(2);
                break;
            case 8:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                buttonKey(HotKeyConstant.K_1_PICKUP);
                break;
        }
    }

    private void setDoorInfo0x2402() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_333_setting_carState_10"), ResolveRev(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "light_info"), ResolveLight(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDoorInfo0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
        updateParkUi(null, this.mContext);
    }

    private void setTrackData0x29() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setAmplifier0x17() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_amplifier_switch"), Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
        GeneralAmplifierData.frontRear = getSurroundVolFRprogress(this.mCanBusInfoInt[4]);
        GeneralAmplifierData.leftRight = getSurroundVolFRprogress(this.mCanBusInfoInt[5]);
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[8];
        updateAmplifierActivity(null);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void VersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private String ResolveRev(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._333_setting_carState_10_1);
        }
        return this.mContext.getResources().getString(R.string._333_setting_carState_10_0);
    }

    private String ResolveLight(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._220_status2);
        }
        return this.mContext.getResources().getString(R.string._220_status1);
    }

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        return true;
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i > 6) {
            i = 0;
        }
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 2, (byte) 3, (byte) 17, (byte) 18);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, radioCurrentBand, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i});
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        if (i6 == 6 || i6 == 7) {
            if (i6 == 1 || i6 == 5) {
                i3 = i4;
            }
            byte b2 = (byte) (i5 & 255);
            byte b3 = (byte) ((i5 >> 8) & 255);
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 16, 0, b3, b2, (byte) (i3 & 255), b3, 0, 0, (byte) ((i >> 8) & 255), (byte) (i & 255)});
        }
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
