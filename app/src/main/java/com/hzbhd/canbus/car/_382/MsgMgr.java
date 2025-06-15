package com.hzbhd.canbus.car._382;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


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
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i11 = z3 ? 0 : 128;
        MyLog.temporaryTracking("bYearTotal:" + i);
        getUiMgr(this.mContext).send0xCBTimeInfo(i11, i5, i6, 128, 1, z ? 1 : 0, (i - 2000) + HotKeyConstant.K_SOS, i3, i4, 2);
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
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), SplicingByte(new byte[]{22, -31, radioCurrentBand}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = String.format(new DecimalFormat("000").format(((b7 & 255) * 256) + (i & 255)), new Object[0]) + " " + new DecimalFormat("00").format(b4) + ":" + new DecimalFormat("00").format(b5) + "   ";
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -31;
        bArr[2] = b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), SplicingByte(bArr, str7.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + "         ";
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -31;
        bArr[2] = b == 9 ? (byte) 14 : NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), SplicingByte(bArr, str5.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), SplicingByte(new byte[]{22, -31, 6}, (String.format(new DecimalFormat("000").format(i3), new Object[0]) + "         ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("AUX".getBytes(), 0, bArr, 0, "AUX".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), SplicingByte(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), SplicingByte(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("BtMusic".getBytes(), 0, bArr, 0, "BtMusic".getBytes().length);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), SplicingByte(new byte[]{22, -31, 11}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        switch (byteArrayToIntArray[1]) {
            case 17:
                set0x11SpeedInfo();
                set0x11SWC();
                set0x11LightingInfo();
                set0x11TrackInfo();
                set0x11DoorInfo();
                updateSpeedInfo(this.mCanBusInfoInt[3]);
                break;
            case 25:
                set0x19Info();
                break;
            case 49:
                set0x31AirInfo();
                break;
            case 65:
                set0x41RadarInfo();
                break;
            case 70:
                set0x46TyresInfo();
                break;
            case 72:
                set0x48SystemInfo();
                break;
            case 100:
                set0x64WindowInfo();
                break;
            case 104:
                set0x68LightInfo();
                break;
            case 113:
                set0x71ParkingInfo();
                break;
            case 193:
                set0xC1UnitInfo();
                break;
            case 195:
                set0xC3Info();
                break;
        }
    }

    private void set0x19Info() {
        ArrayList arrayList = new ArrayList();
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
        int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor9");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, sb.append((iArr[2] << 16) + (iArr[3] << 8) + iArr[4]).append("km").toString()).setValueStr(true));
        int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
        int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor10");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, sb2.append((iArr2[10] << 8) + iArr2[11]).append("RPM").toString()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0xC3Info() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor1"), Integer.valueOf(this.mCanBusInfoInt[2])));
        if (this.mCanBusInfoInt[4] == 1) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor7"), "Mile").setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor7"), "Km").setValueStr(true));
        }
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
        int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor2");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, Integer.valueOf(((iArr[5] * 256) + iArr[6]) * 100)).setValueStr(true));
        int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor");
        int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor3");
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, Integer.valueOf((iArr2[7] * 256) + iArr2[8])).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor4"), Integer.valueOf(this.mCanBusInfoInt[9] * 100)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor5"), Integer.valueOf(this.mCanBusInfoInt[10])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_athor"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_athor", "_382_athor6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0xC1UnitInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_unit_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_unit_setting", "_382_unit_setting6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x71ParkingInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking2"), Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking3"), Integer.valueOf(this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking4"), Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_parking"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_parking", "_382_parking5"), Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x64WindowInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_window"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_window", "_382_window7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x68LightInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2) - 1)).setEnable(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))).setEnable(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light6"), Integer.valueOf(this.mCanBusInfoInt[6])).setEnable(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[6]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_light"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_light", "_382_car_light7"), Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x41RadarInfo() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x48SystemInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_auxiliary"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_auxiliary", "_382_car_auxiliary1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x46TyresInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_tyres"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_tyres", "_382_car_tyres1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_382_car_tyres"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_382_car_tyres", "_382_car_tyres2"), this.mCanBusInfoInt[4] + "").setEnable(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[4]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x31AirInfo() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_auto_wind = false;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_auto_wind = false;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_auto_wind = false;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_auto_wind = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_auto_wind = false;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = false;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_left_auto_wind = true;
                    GeneralAirData.front_right_blow_window = false;
                    GeneralAirData.front_right_blow_foot = false;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_right_auto_wind = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_right_auto_wind = false;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = false;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_auto_wind = false;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_auto_wind = false;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_auto_wind = false;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        int i2 = this.mCanBusInfoInt[8];
        if (i2 == 254) {
            GeneralAirData.front_left_temperature = "Low";
        } else if (i2 == 255) {
            GeneralAirData.front_left_temperature = "Hi";
        } else {
            GeneralAirData.front_left_temperature = (this.mCanBusInfoInt[8] * 0.5d) + getTempUnitC(this.mContext);
        }
        int i3 = this.mCanBusInfoInt[9];
        if (i3 == 254) {
            GeneralAirData.front_right_temperature = "Low";
        } else if (i3 == 255) {
            GeneralAirData.front_right_temperature = "Hi";
        } else {
            GeneralAirData.front_right_temperature = (this.mCanBusInfoInt[9] * 0.5d) + getTempUnitC(this.mContext);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x11DoorInfo() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11]);
        updateDoorView(this.mContext);
    }

    private void set0x11TrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 480, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x11LightingInfo() {
        setBacklightLevel((this.mCanBusInfoInt[7] / 20) + 1);
    }

    private void set0x11SWC() {
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

    private void set0x11SpeedInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_382_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_382_car_speed"), this.mCanBusInfoInt[3] + "KM/H"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
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

    public void Toast(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._382.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.mContext, str, 0).show();
            }
        });
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
