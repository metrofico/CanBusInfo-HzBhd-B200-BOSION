package com.hzbhd.canbus.car._296;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte bandType;
    private byte freqHi;
    private byte freqLo;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private int outDoorTemp;

    private boolean getIsUseFunit() {
        return Integer.parseInt(SharePreUtil.getStringValue(this.mContext, Constant.SHARE_PRE_IS_USE_F_UNIT, "0")) == 1;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 3) {
            setCarSetData0x03();
        }
        if (i == 6) {
            setSwc();
            return;
        }
        if (i == 8) {
            setCarDoor(context);
            return;
        }
        if (i == 12) {
            setCarSetData0x0c();
            return;
        }
        if (i == 113) {
            updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
            return;
        }
        switch (i) {
            case 98:
                setAir();
                break;
            case 99:
                setRadar();
                break;
            case 100:
                setTrack();
                break;
        }
    }

    private void setSwc() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(7);
                break;
            case 2:
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(2);
                break;
            case 4:
                realKeyClick(3);
                break;
            case 5:
                realKeyClick(46);
                break;
            case 6:
                realKeyClick(45);
                break;
            case 7:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 8:
                realKeyClick(14);
                break;
        }
    }

    private void setCarSetData0x0c() {
        String resString;
        String resString2;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        String resString3 = "";
        if (intFromByteWithBit == 0) {
            resString = getResString(R.string.acc) + getResString(R.string.close);
        } else if (intFromByteWithBit == 1) {
            resString = getResString(R.string.acc);
        } else {
            resString = intFromByteWithBit != 2 ? "" : getResString(R.string.acc) + getResString(R.string.open);
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
        if (intFromByteWithBit2 == 0) {
            resString2 = getResString(R.string.close);
        } else {
            resString2 = intFromByteWithBit2 != 1 ? "" : getResString(R.string.open);
        }
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
        if (intFromByteWithBit3 == 0) {
            resString3 = getResString(R.string.close);
            exitAuxIn2();
        } else if (intFromByteWithBit3 == 1) {
            resString3 = getResString(R.string.open);
            enterAuxIn2();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, resString));
        arrayList.add(new DriverUpdateEntity(0, 2, resString2));
        arrayList.add(new DriverUpdateEntity(0, 3, resString3));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSetData0x03() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 8) | iArr[3];
        int i2 = iArr[7] | (iArr[6] << 8);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 4, sb.append((iArr2[8] * 256 * 256) + (iArr2[9] * 256) + iArr2[10]).append("km").toString()));
        arrayList.add(new DriverUpdateEntity(0, 5, returnData(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(0, 6, returnData(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(0, 7, returnData(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(0, 8, returnData(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(0, 9, returnData(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(0, 10, returnData(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(0, 11, returnData(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(1, 0, i + " rpm"));
        arrayList.add(new DriverUpdateEntity(1, 1, new DecimalFormat("0.0").format(i2 * 0.1d) + " km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    private void setAir() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveLeftRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftRightTemp(this.mCanBusInfoInt[6]);
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        updateAirActivity(this.mContext, 1001);
    }

    private String resolveOutDoorTem() {
        String str;
        byte[] bArr = this.mCanBusInfoByte;
        double d = bArr[9] | (bArr[8] << 8);
        if (d < 0.0d) {
            str = (d * 0.5d) + "";
        } else {
            str = "" + (d * 0.5d);
        }
        return str + "℃";
    }

    private double tempCToTempF(double d) {
        LogUtil.showLog("tempCToTempF:" + d);
        try {
            return Double.valueOf(new DecimalFormat("0.0").format((d * 1.8d) + 32.0d)).doubleValue();
        } catch (Exception e) {
            LogUtil.showLog("Exception:" + e);
            return 0.0d;
        }
    }

    private boolean isOutDoorTempChange() {
        return SharePreUtil.getIntValue(this.mContext, "_296_out_door_temp", 0) != this.outDoorTemp;
    }

    private void setRadar() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[3], iArr[4], iArr[5], iArr[6]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr2[7], iArr2[8], iArr2[9], iArr2[10]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setCarDoor(Context context) {
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
    }

    private void setTrack() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 4608, 16);
        updateParkUi(null, this.mContext);
    }

    private String resolveLeftRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 127) {
            return "HI";
        }
        if (i < 30 || i > 64) {
            return "--";
        }
        if (this.mCanBusInfoInt[10] == 0) {
            return (i * 0.5f) + "℃";
        }
        return ((float) ((i * 0.5d * 1.8d) + 32.0d)) + "F";
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

    private String getResString(int i) {
        return this.mContext.getResources().getString(i);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private String returnData(boolean z) {
        return getResString(z ? R.string.open : R.string.close);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        setVwRadioInfo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte) i});
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1});
    }

    private void setVwRadioInfo(String str, String str2) throws NumberFormatException {
        if (str.equals("AM2") || str.equals("MW")) {
            this.bandType = (byte) 18;
        } else if (str.equals("AM1") || str.equals("LW")) {
            int i = Integer.parseInt(str2);
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
            this.bandType = (byte) 17;
        } else if (str.equals("FM1")) {
            this.bandType = (byte) 1;
        } else if (str.equals("FM2")) {
            this.bandType = (byte) 2;
        } else if (str.equals("FM3") || str.equals("OIRT")) {
            this.bandType = (byte) 3;
        }
        getFreqByteHiLo(str, str2);
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) DataHandleUtils.rangeNumber(i, 40)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DISC, 33, false, false);
            sndDiscMidea(this.mContext, new byte[]{22, -61, (byte) i4, (byte) i5, 0, 0, (byte) ((i / 60) % 60), (byte) (i % 60)});
        } else {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DISC, 16, false, false);
            sndDiscMidea(this.mContext, new byte[]{22, -61, 1, (byte) i3, 0, 0, (byte) ((i / 60) % 60), (byte) (i % 60)});
        }
    }

    private void sndDiscMidea(Context context, byte[] bArr) {
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BACKCAMERA || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.MPEG || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                try {
                    CanbusMsgSender.sendMsg(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 9, 17});
        } else {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17});
        }
        sndMusicMidea(this.mContext, new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    private void sndMusicMidea(Context context, byte[] bArr) {
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.MUSIC || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                try {
                    CanbusMsgSender.sendMsg(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                    Settings.System.putString(context.getContentResolver(), "currentReport_disc", Base64.encodeToString(bArr, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 9, 32});
        } else {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 32});
        }
        sndVideoMidea(this.mContext, new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, 0, 0});
    }

    private void sndVideoMidea(Context context, byte[] bArr) {
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.VIDEO || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                try {
                    CanbusMsgSender.sendMsg(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                    Settings.System.putString(context.getContentResolver(), "currentReport_disc", Base64.encodeToString(bArr, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
