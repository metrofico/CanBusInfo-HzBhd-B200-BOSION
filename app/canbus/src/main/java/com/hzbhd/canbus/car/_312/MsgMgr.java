package com.hzbhd.canbus.car._312;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final int MEDIA_ID3_INFO_LANGTH = 56;
    private int mAmplifierSwitch;
    private int mCanId;
    private SparseArray<int[]> mCanbusDataArray;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private Context mContext;
    private int mDifferent;
    private int mGlonassStatus;
    private Id3 mId3;
    private boolean mIs2019;
    private boolean mIsBackOpen;
    private boolean mIsFrontOpen;
    private boolean mIsFrontViewOpen;
    private boolean mIsLeftFrontOpen;
    private boolean mIsLeftRearOpen;
    private boolean mIsMute;
    private boolean mIsRightFrontOpen;
    private boolean mIsRightRearOpen;
    private int mPanoramicStatus;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap;
    private UiMgr mUiMgr;
    private final String TAG = "_999_MsgMgr";
    private final int DATA_TYPE = 1;
    private final int INVAILE_VALUE = -1;
    private final int VEHICLE_TYPE_2019 = 1;
    private int mAmplifierBalanceMid = 10;
    private int mAmplifierBandMin = 3;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        sendAmplifierInit(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        initSettingsItem(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        int currentCanDifferentId = getCurrentCanDifferentId();
        this.mDifferent = currentCanDifferentId;
        this.mIs2019 = DataHandleUtils.getIntFromByteWithBit(currentCanDifferentId, 4, 4) == 1;
        this.mCanbusDataArray = new SparseArray<>();
        initAmplifierParam(context);
        this.mId3 = new Id3(200, "UnicodeLittle", new Id3.Item(3), new Id3.Item(4), new Id3.Item(5));
    }

    private void initAmplifierParam(Context context) {
        this.mAmplifierBalanceMid = getUiMgr(context).getBalanceMid();
        this.mAmplifierBandMin = getUiMgr(context).getBandMin();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = bArr;
        int i = byteArrayToIntArray[1];
        if (i == 36) {
            set0x24BaseInfoData(context);
            return;
        }
        if (i == 37) {
            set0x25ParkAssistData();
            return;
        }
        if (i == 41) {
            set0x29TrackData(context);
            return;
        }
        if (i == 64) {
            set0x40VehicleSettingData();
            return;
        }
        if (i == 48) {
            set0x30VersionData(context);
            return;
        }
        if (i == 49) {
            set0x31AmplifierData(context);
            return;
        }
        if (i == 148) {
            setPanoramic0x94Data();
            return;
        }
        if (i != 149) {
            switch (i) {
                case 32:
                    set0x20WheelKeyData(context);
                    break;
                case 33:
                    set0x21AirData(context);
                    break;
                case 34:
                    set0x22RadarData(context);
                    break;
            }
            return;
        }
        setGlonass0x95Data(context);
    }

    private void set0x20WheelKeyData(Context context) {
        int i = 2;
        int i2 = this.mCanbusInfoInt[2];
        if (i2 == 1) {
            i = 7;
        } else if (i2 == 2) {
            i = 8;
        } else if (i2 == 3) {
            i = 46;
        } else if (i2 == 4) {
            i = 45;
        } else if (i2 == 21) {
            i = 50;
        } else if (i2 != 22) {
            switch (i2) {
                case 7:
                    break;
                case 8:
                    i = HotKeyConstant.K_SPEECH;
                    break;
                case 9:
                    if (!this.mIs2019) {
                        i = 14;
                        break;
                    } else {
                        i = HotKeyConstant.K_VOICE_PICKUP;
                        break;
                    }
                case 10:
                    i = 15;
                    break;
                default:
                    i = 0;
                    break;
            }
        } else {
            i = 49;
        }
        realKeyLongClick1(context, i);
    }

    private void set0x21AirData(Context context) {
        int[] iArr = this.mCanbusInfoInt;
        iArr[3] = iArr[3] & 239;
        if (isDataChange(iArr)) {
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[6]);
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
            if (DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2])) {
                GeneralAirData.auto_1_2 = 2;
            } else if (DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2])) {
                GeneralAirData.auto_1_2 = 1;
            } else {
                GeneralAirData.auto_1_2 = 0;
            }
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = getAirTemperature(context, this.mCanbusInfoInt[4]);
            GeneralAirData.front_right_temperature = getAirTemperature(context, this.mCanbusInfoInt[5]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[6]);
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            updateAirActivity(context, 1001);
        }
    }

    private void set0x22RadarData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            int[] iArr = this.mCanbusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x24BaseInfoData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
        if (isDoorStatusChange()) {
            updateDoorView(context);
        }
    }

    private void set0x25ParkAssistData() {
        if (isDataChange(this.mCanbusInfoInt)) {
            boolean z = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 2) != 0;
            if (this.mIsFrontViewOpen ^ z) {
                this.mIsFrontViewOpen = z;
                switchFCamera(this.mContext, z);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSettingUpdateEntity("_41_park_assist").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 1, 1))));
            arrayList.add(getSettingUpdateEntity("_312_rab").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x29TrackData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            byte[] bArr = this.mCanbusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4864, 16);
            updateParkUi(null, context);
        }
    }

    private void set0x30VersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanbusInfoByte));
    }

    private void set0x31AmplifierData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            GeneralAmplifierData.volume = this.mCanbusInfoInt[3];
            GeneralAmplifierData.frontRear = this.mCanbusInfoInt[4] - this.mAmplifierBalanceMid;
            GeneralAmplifierData.leftRight = this.mCanbusInfoInt[5] - this.mAmplifierBalanceMid;
            GeneralAmplifierData.bandBass = this.mCanbusInfoInt[6] - this.mAmplifierBandMin;
            GeneralAmplifierData.bandTreble = this.mCanbusInfoInt[7] - this.mAmplifierBandMin;
            GeneralAmplifierData.bandMiddle = this.mCanbusInfoInt[8] - this.mAmplifierBandMin;
            updateAmplifierActivity(null);
            saveAmplifierData(context, this.mCanId);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 1);
            if (this.mAmplifierSwitch != intFromByteWithBit) {
                this.mAmplifierSwitch = intFromByteWithBit;
                ArrayList arrayList = new ArrayList();
                arrayList.add(getSettingUpdateEntity("amplifier_switch").setValue(Integer.valueOf(intFromByteWithBit)));
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
            }
        }
    }

    private void set0x40VehicleSettingData() {
        if (isDataChange(this.mCanbusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSettingUpdateEntity("_312_interior_light").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 6, 2))));
            arrayList.add(getSettingUpdateEntity("_312_defogger").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 5, 1))));
            arrayList.add(getSettingUpdateEntity("_312_one_touch_lane_changer").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 1))));
            arrayList.add(getSettingUpdateEntity("_312_hazard_warning_flasher").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 3, 1))));
            arrayList.add(getSettingUpdateEntity("_312_security_re_locking").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 3))));
            arrayList.add(getSettingUpdateEntity("_312_auto_door_lock").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 6, 2))));
            arrayList.add(getSettingUpdateEntity("_312_auto_door_unlock").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 4, 2))));
            arrayList.add(getSettingUpdateEntity("_312_wheel_control_type").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setPanoramic0x94Data() {
        int i = this.mPanoramicStatus;
        int i2 = this.mCanbusInfoInt[2];
        if (i != i2) {
            this.mPanoramicStatus = i2;
            forceReverse(this.mContext, i2 == 1);
        }
    }

    private void setGlonass0x95Data(Context context) {
        int i = this.mGlonassStatus;
        int i2 = this.mCanbusInfoInt[2];
        if (i != i2) {
            this.mGlonassStatus = i2;
            if (i2 != 1 || this.mIsMute) {
                return;
            }
            realKeyClick(context, 3);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        this.mIsMute = z;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            byte b = (byte) 0;
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, b, b}, new byte[6]));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        sendAmplifierInit(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int i3 = str.contains("AM") ? 16 : 0;
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        byte b = (byte) 1;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, b, b}, new byte[]{(byte) i3, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i, 0, 0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            i3 = i4;
        }
        byte[] bArr = new byte[6];
        bArr[0] = (byte) ((i3 >> 8) & 255);
        bArr[1] = (byte) (i3 & 255);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) 2, (byte) 19}, bArr));
        this.mId3.sendId3Datas(str, str2, str3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    protected void sendDiscEjectMsg(Context context) {
        super.sendDiscEjectMsg(context);
        this.mId3.sendId3Datas(" ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z) {
            byte[] bArr2 = new byte[6];
            bArr2[0] = (byte) (i != 1 ? i != 2 ? i != 4 ? i != 5 ? 0 : 4 : 2 : 3 : 1);
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) 5, (byte) 64}, bArr2));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        byte[] bArrByteMerger = {22, -56, 16, 1};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, new String(bArr).getBytes("unicodeLittle"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 58));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        byte[] bArrByteMerger = {22, -56, 16, 1};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, new String(bArr).getBytes("unicodeLittle"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 58));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        byte[] bArrByteMerger = {22, -56, 16, 1};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, " ".getBytes("unicodeLittle"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 58));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) 7, (byte) 0}, new byte[6]));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) i;
        bArr[1] = b7;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) 8, (byte) 19}, bArr));
        this.mId3.sendId3Datas(str4, str5, str6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mId3.sendId3Datas(" ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) i;
        bArr[1] = b6;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) 8, (byte) 19}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        Log.i("_999_MsgMgr", "btMusicId3InfoChange: \ntitle <--> " + str + "\nartist <--> " + str2 + "\nalbum <--> " + str3);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) 11, (byte) 0}, new byte[6]));
        this.mId3.sendId3Datas(str, str3, str2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        Log.i("_999_MsgMgr", "btMusiceDestdroy: ");
        this.mId3.sendId3Datas(" ", " ", " ");
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanbusInfoInt[3]);
    }

    private String getAirTemperature(Context context, int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return i + getTempUnitF(context);
        }
        return (i * 0.5f) + getTempUnitC(context);
    }

    private boolean isDoorStatusChange() {
        if (!(this.mIsLeftFrontOpen ^ GeneralDoorData.isLeftFrontDoorOpen) && !(this.mIsRightFrontOpen ^ GeneralDoorData.isRightFrontDoorOpen) && !(this.mIsLeftRearOpen ^ GeneralDoorData.isLeftRearDoorOpen) && !(this.mIsRightRearOpen ^ GeneralDoorData.isRightRearDoorOpen) && !(this.mIsBackOpen ^ GeneralDoorData.isBackOpen) && !(this.mIsFrontOpen ^ GeneralDoorData.isFrontOpen)) {
            return false;
        }
        this.mIsLeftFrontOpen = GeneralDoorData.isLeftFrontDoorOpen;
        this.mIsRightFrontOpen = GeneralDoorData.isRightFrontDoorOpen;
        this.mIsLeftRearOpen = GeneralDoorData.isLeftRearDoorOpen;
        this.mIsRightRearOpen = GeneralDoorData.isRightRearDoorOpen;
        this.mIsBackOpen = GeneralDoorData.isBackOpen;
        this.mIsFrontOpen = GeneralDoorData.isFrontOpen;
        return true;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void sendAmplifierInit(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
            initAmplifierParam(context);
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._312.MsgMgr.1
            final Iterator<byte[]> iterator;

            {
                this.iterator = Arrays.stream(new byte[][]{new byte[]{22, -124, 1, 1}, new byte[]{22, -124, 2, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 3, (byte) (GeneralAmplifierData.frontRear + MsgMgr.this.mAmplifierBalanceMid)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.leftRight + MsgMgr.this.mAmplifierBalanceMid)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandBass + MsgMgr.this.mAmplifierBandMin)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandTreble + MsgMgr.this.mAmplifierBandMin)}, new byte[]{22, -124, 7, (byte) (GeneralAmplifierData.bandMiddle + MsgMgr.this.mAmplifierBandMin)}}).iterator();
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (CommUtil.equal(str, "AM2", "MW", "AM1", "LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (CommUtil.equal(str, "FM1", "FM2", "FM3", "OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    private void initSettingsItem(Context context) {
        Log.i("_999_MsgMgr", "initSettingsItem: ");
        this.mSettingItemIndeHashMap = new HashMap<>();
        SettingPageUiSet settingUiSet = getUiMgr(context).getSettingUiSet(context);
        for (int i = 0; i < settingUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingUiSet.getList().get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        SettingUpdateEntity settingUpdateEntity = new SettingUpdateEntity(-1, -1, null);
        this.mSettingItemIndeHashMap.put(str, settingUpdateEntity);
        return settingUpdateEntity;
    }

    private static class Id3 {
        private final Item album;
        private final Item artist;
        private final String charset;
        private final int dataType;
        private final Item[] items;
        private final TimerUtil timer = new TimerUtil();
        private final Item title;

        public Id3(int i, String str, Item item, Item item2, Item item3) {
            this.dataType = i;
            this.charset = str;
            this.title = item;
            this.album = item2;
            this.artist = item3;
            this.items = new Item[]{item, item2, item3};
        }

        public void sendId3Datas(String str, String str2, String str3) {
            this.title.setInfo(str);
            this.album.setInfo(str2);
            this.artist.setInfo(str3);
            if (this.title.isChange() || this.album.isChange() || this.artist.isChange()) {
                this.timer.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._312.MsgMgr.Id3.1
                    int i = 0;

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() throws UnsupportedEncodingException {
                        if (this.i >= Id3.this.items.length) {
                            Id3.this.timer.stopTimer();
                            return;
                        }
                        Id3 id3 = Id3.this;
                        id3.sendId3Data(id3.items[this.i].getInfo(), Id3.this.items[this.i].getCommand());
                        this.i++;
                    }
                }, 100L, 100L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void sendId3Data(String str, int i) throws UnsupportedEncodingException {
            byte[] bArr = {22, (byte) this.dataType, 16, (byte) i};
            byte[] bytes = new byte[0];
            try {
                bytes = str.getBytes(this.charset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr, DataHandleUtils.exceptBOMHead(bytes)), 58));
        }

        private static class Item {
            private final int command;
            private String info;
            private boolean isChange;

            public Item(int i) {
                this.command = i;
            }

            public void setInfo(String str) {
                if (str == null) {
                    return;
                }
                boolean z = !str.equals(this.info);
                this.isChange = z;
                if (z) {
                    this.info = str;
                }
            }

            public String getInfo() {
                return this.info;
            }

            public int getCommand() {
                return this.command;
            }

            public boolean isChange() {
                return this.isChange;
            }
        }
    }
}
