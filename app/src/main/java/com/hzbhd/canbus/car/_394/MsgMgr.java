package com.hzbhd.canbus.car._394;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SystemButton;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.Priority;


public class MsgMgr extends AbstractMsgMgr {
    private static int volKnobValue;
    DecimalFormat format1 = new DecimalFormat("###0.00");
    DecimalFormat format2 = new DecimalFormat("000.0");
    DecimalFormat format3 = new DecimalFormat("00");
    DecimalFormat format4 = new DecimalFormat("000");
    int[] mAirData;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    int[] mRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private boolean mSeatBeltStatus;
    private boolean mSubSeatBeltStatus;
    String resulttemp;
    SystemButton systemButton;
    SystemButton systemDvrButton;
    private UiMgr uiMgr;

    private void CarTypeInfo0x26() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        if (getCurrentCanDifferentId() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 23, 53});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i5, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, (byte) (i - 1999), (byte) i3, (byte) i4, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) throws UnsupportedEncodingException {
        byte[] bytes;
        int i4;
        byte[] bytes2 = " SUB  MEDIA ".getBytes(StandardCharsets.UTF_8);
        if (b == 9) {
            i4 = 25;
            bytes = " SD  MEDIA  ".getBytes(StandardCharsets.UTF_8);
        } else {
            bytes = bytes2;
            i4 = 13;
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, (byte) i4}, bytes));
        byte[] bArrByteMerger = new byte[0];
        try {
            bArrByteMerger = str4.getBytes("unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bArrByteMerger.length < 32) {
            byte[] bArr = new byte[32 - bArrByteMerger.length];
            for (int i5 = 0; i5 < 32 - bArrByteMerger.length; i5++) {
                bArr[i5] = 0;
            }
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, bArr);
        }
        byte[] bArr2 = new byte[32];
        for (int i6 = 0; i6 < 32; i6++) {
            bArr2[i6] = bArrByteMerger[i6];
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -110}, bArr2));
        byte[] bArrByteMerger2 = new byte[0];
        try {
            bArrByteMerger2 = str5.getBytes("unicode");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (bArrByteMerger2.length < 32) {
            byte[] bArr3 = new byte[32 - bArrByteMerger2.length];
            for (int i7 = 0; i7 < 32 - bArrByteMerger2.length; i7++) {
                bArr3[i7] = 0;
            }
            bArrByteMerger2 = DataHandleUtils.byteMerger(bArrByteMerger2, bArr3);
        }
        byte[] bArr4 = new byte[32];
        for (int i8 = 0; i8 < 32; i8++) {
            bArr4[i8] = bArrByteMerger2[i8];
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -109}, bArr4));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        String str5 = "AM";
        int i3 = 1;
        if (str.equals("FM1")) {
            str5 = "FM";
        } else {
            if (str.equals("FM2")) {
                i3 = 2;
            } else if (str.equals("FM3")) {
                i3 = 3;
            } else if (str.equals("AM1")) {
                i3 = 4;
            } else if (str.equals("AM2")) {
                i3 = 5;
            }
            str5 = "FM";
        }
        String str6 = this.format3.format(i);
        if (str5.equals("FM")) {
            str4 = this.format2.format(Double.parseDouble(str2));
        } else {
            str4 = this.format4.format(Integer.parseInt(str2));
        }
        char[] charArray = (str6 + " " + str4).toCharArray();
        byte[] bArr = new byte[12];
        for (int i4 = 0; i4 < 12; i4++) {
            bArr[i4] = 32;
        }
        for (int i5 = 0; i5 < charArray.length; i5++) {
            bArr[i5] = (byte) charArray[i5];
        }
        sendMediaInfo0x91(i3, bArr);
    }

    public void sendMediaInfo0x91(int i, byte[] bArr) {
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -111, (byte) i}, bArr));
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        byte[] bytes;
        int i5;
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bytes2 = " SUB  MEDIA ".getBytes(StandardCharsets.UTF_8);
        if (b == 9) {
            i5 = 25;
            bytes = " SD  MEDIA  ".getBytes(StandardCharsets.UTF_8);
        } else {
            bytes = bytes2;
            i5 = 13;
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, (byte) i5}, bytes));
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) throws UnsupportedEncodingException {
        super.btMusicId3InfoChange(str, str2, str3);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, -123}, " BT  MEDIA  ".getBytes(StandardCharsets.UTF_8)));
        byte[] bArrByteMerger = new byte[0];
        try {
            bArrByteMerger = str.getBytes("unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bArrByteMerger.length < 32) {
            byte[] bArr = new byte[32 - bArrByteMerger.length];
            for (int i = 0; i < 32 - bArrByteMerger.length; i++) {
                bArr[i] = 0;
            }
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, bArr);
        }
        byte[] bArr2 = new byte[32];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr2[i2] = bArrByteMerger[i2];
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -110}, bArr2));
        byte[] bArrByteMerger2 = new byte[0];
        try {
            bArrByteMerger2 = str3.getBytes("unicode");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (bArrByteMerger2.length < 32) {
            byte[] bArr3 = new byte[32 - bArrByteMerger2.length];
            for (int i3 = 0; i3 < 32 - bArrByteMerger2.length; i3++) {
                bArr3[i3] = 0;
            }
            bArrByteMerger2 = DataHandleUtils.byteMerger(bArrByteMerger2, bArr3);
        }
        byte[] bArr4 = new byte[32];
        for (int i4 = 0; i4 < 32; i4++) {
            bArr4[i4] = bArrByteMerger2[i4];
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -109}, bArr4));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, " AUX  MEDIA ".getBytes(StandardCharsets.UTF_8)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            CarInfo0x11();
            return;
        }
        if (i == 18) {
            BodyDetailsInfo0x12();
            return;
        }
        if (i == 33) {
            WheelKeyInfo0x21();
            return;
        }
        if (i == 34) {
            WheelSpinKey0x22();
            return;
        }
        if (i == 38) {
            CarTypeInfo0x26();
            return;
        }
        if (i == 49) {
            AirInfo0x31(byteArrayToIntArray);
            return;
        }
        if (i == 65) {
            RadarInfo0x41();
            return;
        }
        if (i == 135) {
            CentralControlInfo0x87();
        } else if (i == 232) {
            OriginalCarStatusInformation0xE8();
        } else {
            if (i != 240) {
                return;
            }
            VersionInfo0xF0();
        }
    }

    private void CarInfo0x11() {
        switch (this.mCanBusInfoInt[4]) {
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
                buttonKey(3);
                break;
            case 4:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                buttonKey(14);
                break;
            case 6:
                buttonKey(15);
                break;
            case 8:
                buttonKey(21);
                break;
            case 9:
                buttonKey(20);
                break;
            case 12:
                buttonKey(2);
                break;
        }
        GeneralDoorData.isShowRev = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowAcc = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, Priority.DEBUG_INT, 16);
        updateParkUi(null, this.mContext);
    }

    private void BodyDetailsInfo0x12() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void AirInfo0x31(int[] iArr) {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        iArr[13] = 0;
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(iArr[3]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[3]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(iArr[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr[4]);
            int i = iArr[6];
            if (i == 0) {
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 1) {
                GeneralAirData.front_auto_wind_model = true;
            } else if (i == 3) {
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 5) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
            } else if (i == 6) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_right_blow_window = false;
            } else {
                switch (i) {
                    case 12:
                        GeneralAirData.front_left_blow_head = false;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_head = false;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 13:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = false;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = false;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 14:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_right_blow_foot = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                }
            }
            switch (iArr[7]) {
                case 0:
                    GeneralAirData.front_wind_level = 0;
                    break;
                case 1:
                    GeneralAirData.front_wind_level = 1;
                    break;
                case 2:
                    GeneralAirData.front_wind_level = 2;
                    break;
                case 3:
                    GeneralAirData.front_wind_level = 3;
                    break;
                case 4:
                    GeneralAirData.front_wind_level = 4;
                    break;
                case 5:
                    GeneralAirData.front_wind_level = 5;
                    break;
                case 6:
                    GeneralAirData.front_wind_level = 6;
                    break;
                case 7:
                    GeneralAirData.front_wind_level = 7;
                    break;
            }
            GeneralAirData.front_left_temperature = resolveAirTemperature(iArr[8]);
            GeneralAirData.front_right_temperature = resolveAirTemperature(iArr[9]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void RadarInfo0x41() {
        if (isRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(3, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void WheelKeyInfo0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey1(0);
            return;
        }
        if (i == 1) {
            buttonKey1(1);
            return;
        }
        if (i == 37) {
            buttonKey1(128);
            return;
        }
        if (i == 43) {
            buttonKey1(52);
            return;
        }
        if (i == 55) {
            buttonKey1(58);
            return;
        }
        if (i == 67) {
            buttonKey1(39);
        } else if (i == 69) {
            buttonKey1(7);
        } else {
            if (i != 70) {
                return;
            }
            buttonKey1(8);
        }
    }

    private void WheelSpinKey0x22() {
        if (this.mCanBusInfoInt[2] != 1) {
            return;
        }
        int i = volKnobValue - this.mCanBusInfoByte[3];
        if (i < 0) {
            sendKnob_1(7, Math.abs(i));
        } else if (i > 0) {
            sendKnob_1(8, Math.abs(i));
        }
        volKnobValue = this.mCanBusInfoByte[3];
    }

    private void CentralControlInfo0x87() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_1"), Integer.valueOf(NewUtil.getBoolBit7(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_185_setting_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_2"), Integer.valueOf(NewUtil.getBoolBit4(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_0"), Integer.valueOf(NewUtil.getBoolBit3(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_1"), Integer.valueOf(NewUtil.getBoolBit2(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_0"), Integer.valueOf(NewUtil.getBoolBit1(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_1"), Integer.valueOf(NewUtil.getBoolBit0(this.mCanBusInfoInt[4]))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_4"), Integer.valueOf(NewUtil.getBoolBit3(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_3"), Integer.valueOf(NewUtil.getBoolBit2(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_8"), Integer.valueOf(NewUtil.getBoolBit1(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_6"), Integer.valueOf(NewUtil.getBoolBit0(this.mCanBusInfoInt[6]))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_7"), Integer.valueOf(NewUtil.getBoolBit7(this.mCanBusInfoInt[7]))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 2))).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_9"), Integer.valueOf(NewUtil.getBoolBit4(this.mCanBusInfoInt[7]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_A"), Integer.valueOf(NewUtil.getBoolBit3(this.mCanBusInfoInt[7]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_bu_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        str.hashCode();
        if (str.equals("skylight.open")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 25, 1});
        } else if (str.equals("skylight.close")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -116, 25, 0});
        }
    }

    private void OriginalCarStatusInformation0xE8() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, false));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[7] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[8] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(3, this.mCanBusInfoInt[6] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(4, this.mCanBusInfoInt[4] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(5, this.mCanBusInfoInt[5] == 1));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        forceReverse(context, iArr[4] == 1 || iArr[5] == 1 || iArr[6] == 1 || iArr[7] == 1 || iArr[8] == 1);
    }

    private void VersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    public void buttonKey1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private String resolveAirTemperature(int i) {
        if (i == 254) {
            this.resulttemp = "Low_Temp";
        } else if (i == 255) {
            this.resulttemp = "High_Temp";
        } else {
            this.resulttemp = (i / 2.0f) + getTempUnitC(this.mContext);
        }
        return this.resulttemp;
    }

    private String resolveOutDoorTem() {
        return ((this.mCanBusInfoInt[13] / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp && this.mSeatBeltStatus == GeneralDoorData.isSeatBeltTie && this.mSubSeatBeltStatus == GeneralDoorData.isSubSeatBeltTie) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        this.mSubSeatBeltStatus = GeneralDoorData.isSubSeatBeltTie;
        this.mSeatBeltStatus = GeneralDoorData.isSeatBeltTie;
        return true;
    }

    private void sendKnob_1(int i, int i2) {
        realKeyClick3_1(this.mContext, i, 0, i2);
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    public void showP360Button() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._394.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemButton == null) {
                    MsgMgr.this.systemButton = new SystemButton(MsgMgr.this.getActivity(), "360", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._394.MsgMgr.1.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 1});
                        }
                    });
                }
                MsgMgr.this.systemButton.show();
            }
        });
    }

    public void hideP360Button() {
        if (this.systemButton == null) {
            this.systemButton = new SystemButton(getActivity(), "360", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._394.MsgMgr.2
                @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                public void clickEvent() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 1});
                }
            });
        }
        this.systemButton.hide();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 3});
    }

    public void showDvrButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._394.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemDvrButton == null) {
                    MsgMgr.this.systemDvrButton = new SystemButton(MsgMgr.this.getActivity(), "DVR", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._394.MsgMgr.3.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 8});
                            MsgMgr.this.realKeyClick4(MsgMgr.this.mContext, 141);
                        }
                    });
                }
                MsgMgr.this.systemDvrButton.show();
            }
        });
    }

    public void hideDvrButton() {
        if (this.systemDvrButton == null) {
            this.systemDvrButton = new SystemButton(getActivity(), "DVR", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._394.MsgMgr.4
                @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                public void clickEvent() {
                    CanbusMsgSender.sendMsg(new byte[]{22, -3, 4, 8});
                    MsgMgr msgMgr = MsgMgr.this;
                    msgMgr.realKeyClick4(msgMgr.mContext, 141);
                }
            });
        }
        this.systemDvrButton.hide();
    }

    public void toast(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._394.MsgMgr.5
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.getActivity(), str, 0).show();
            }
        });
    }
}
