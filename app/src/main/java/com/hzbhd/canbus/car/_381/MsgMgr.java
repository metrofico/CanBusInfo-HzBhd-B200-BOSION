package com.hzbhd.canbus.car._381;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    int firstEnable = 0;

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
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i > 6) {
            i = 0;
        }
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = new DecimalFormat("00").format(i) + " " + str2 + "  Khz";
            } else {
                str4 = new DecimalFormat("00").format(i) + "  " + str2 + "  Khz";
            }
        } else if (str2.length() == 5) {
            str4 = new DecimalFormat("00").format(i) + "  " + str2 + "Mhz";
        } else {
            str4 = new DecimalFormat("00").format(i) + " " + str2 + "Mhz";
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), SplicingByte(new byte[]{22, -46, radioCurrentBand}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = String.format(new DecimalFormat("000").format(((b7 & 255) * 256) + (i & 255)), new Object[0]) + " " + new DecimalFormat("00").format(b4) + ":" + new DecimalFormat("00").format(b5) + "   ";
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -46;
        bArr[2] = b == 9 ? (byte) 14 : (byte) 13;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), SplicingByte(bArr, str7.getBytes()));
        sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, "MusicPlaying");
        sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, "Device:USB");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + "         ";
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -46;
        bArr[2] = b == 9 ? (byte) 14 : (byte) 13;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), SplicingByte(bArr, str5.getBytes()));
        sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, "VideoPlaying");
        sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, "Device:USB");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), SplicingByte(new byte[]{22, -46, 6}, (String.format(new DecimalFormat("000").format(i3), new Object[0]) + "         ").getBytes()));
        sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, "Disc Playing");
        sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, "Device:CD");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -46, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, " ");
            sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, " ");
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("AUX".getBytes(), 0, bArr, 0, "AUX".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), SplicingByte(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr));
        sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, "Aux Playing");
        sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, "Device:Aux");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), SplicingByte(new byte[]{22, -46, 8}, bArr));
        sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, "Atv Playing");
        sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, "Device:TV");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("BtMusic".getBytes(), 0, bArr, 0, "BtMusic".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), SplicingByte(new byte[]{22, -46, 11}, bArr));
        sendAsciiInfo(13, HotKeyConstant.K_ANDROIDAUTO, "IPOD Playing");
        sendAsciiInfo(13, HotKeyConstant.K_CARPLAY, "Device:IPOD");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 114) {
            SMC_0x72();
            Lighting_0x72();
            Track_0x72();
            Radar_0x72();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 115) {
            Air_0x73();
        } else {
            if (i != 240) {
                return;
            }
            Version_0xF0();
        }
    }

    private void Lighting_0x72() {
        int i = this.mCanBusInfoInt[5];
        if (i != 0 || this.firstEnable == 0) {
            this.firstEnable = 1;
            setBacklightLevel((i / 20) + 1);
        }
    }

    private void Version_0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void Air_0x73() {
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
        updateAirActivity(this.mContext, 1001);
    }

    private void Radar_0x72() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[8], iArr[9], iArr[10], iArr[11]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[12], iArr2[13], iArr2[14], iArr2[15]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void Track_0x72() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        if (i != 0) {
            GeneralParkData.trackAngle = (-i) / 10;
        } else {
            GeneralParkData.trackAngle = iArr[7] / 10;
        }
        updateParkUi(null, this.mContext);
    }

    private void SMC_0x72() {
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyClick4(this.mContext, 0);
                break;
            case 1:
                realKeyClick4(this.mContext, 7);
                break;
            case 2:
                realKeyClick4(this.mContext, 8);
                break;
            case 3:
                realKeyClick4(this.mContext, 3);
                break;
            case 5:
                realKeyClick4(this.mContext, 14);
                break;
            case 6:
                realKeyClick4(this.mContext, 15);
                break;
            case 8:
                realKeyClick4(this.mContext, 45);
                break;
            case 9:
                realKeyClick4(this.mContext, 46);
                break;
            case 10:
                realKeyClick4(this.mContext, 2);
                break;
        }
    }

    private void sendAsciiInfo(int i, int i2, String str) {
        byte[] bytes = (" " + str + "             ").getBytes(StandardCharsets.UTF_8);
        byte[] bArr = {22, (byte) i2};
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = bytes[i3];
        }
        CanbusMsgSender.sendMsg(SplicingByte(bArr, bArr2));
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

    private String getTemperature(int i) {
        return i == 0 ? "LOW" : i == 0 ? "HI" : i + getTempUnitC(this.mContext);
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
        updateParkUi(null, this.mContext);
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

    private boolean isNotAirDataChange() {
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
