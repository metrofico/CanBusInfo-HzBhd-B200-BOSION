package com.hzbhd.canbus.car._299;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._299.MessageSender;
import com.hzbhd.canbus.car_cus._299.RadarView;
import com.hzbhd.canbus.car_cus._299.air.AirPopupView;
import com.hzbhd.canbus.car_cus._299.instrument.InstrumentActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import kotlin.jvm.internal.ByteCompanionObject;


public class MsgMgr extends AbstractMsgMgr {
    private static final String SHARE_299_SMARTPOWER_DATA3 = "_299_smart_data3";
    private static final String SHARE_299_SMARTPOWER_DATA4 = "_299_smart_data4";
    private static final String SHARE_299_SMARTPOWER_DATA5 = "_299_smart_data5";
    private static final String SHARE_299_SMARTPOWER_DATA6 = "_299_smart_data6";
    private static final String SHARE_299_SMARTPOWER_MODE = "_299_smart_mode";
    private int data3;
    private int data4;
    private int data5;
    private int data6;
    private int data7;
    private int[] drivingMode = {R.string._283_driving_pattern_8, R.string._283_driving_pattern_9, R.string._283_driving_pattern_1, R.string._283_driving_pattern_3, R.string._283_driving_pattern_10};
    private AirPopupView mAirPopupView;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private CusPanoramicView mPanoramicView;
    private RadarView mRadarView;
    private int mode;
    private int mode_int;
    private int version;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(final Context context) {
        super.initCommand(context);
        MessageSender.sendMsg(new byte[]{22, -127, 1});
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._299.MsgMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.lambda$initCommand$0(context);
            }
        }).start();
    }

    static /* synthetic */ void lambda$initCommand$0(Context context) {
        try {
            GeneralDzSmartData.mode = SharePreUtil.getIntValue(context, SHARE_299_SMARTPOWER_MODE, 1);
            GeneralDzSmartData.data3 = SharePreUtil.getIntValue(context, SHARE_299_SMARTPOWER_DATA3, 0);
            GeneralDzSmartData.data4 = SharePreUtil.getIntValue(context, SHARE_299_SMARTPOWER_DATA4, 0);
            GeneralDzSmartData.data5 = SharePreUtil.getIntValue(context, SHARE_299_SMARTPOWER_DATA5, 0);
            GeneralDzSmartData.data6 = SharePreUtil.getIntValue(context, SHARE_299_SMARTPOWER_DATA6, 0);
            MessageSender.sendDzMsg(GeneralDzSmartData.mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        } catch (Exception e) {
            MessageSender.sendDzMsg(1, 0, 0, 0, 0);
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        if (byteArrayToIntArray[0] == 85) {
            canBusInfo0x55(bArr);
        }
    }

    private void updateRadar() {
        updateRadarView(this.mContext);
        getMyPanoramicView().refreshRadarUi();
    }

    private void canBusInfo0x55(byte[] bArr) {
        int i = this.mCanBusInfoInt[1];
        if (i == 1) {
            setKey0x01();
            return;
        }
        if (i == 2) {
            setKey0x02();
            return;
        }
        if (i == 4) {
            setRearRadarInfo0x05();
            return;
        }
        if (i == 5) {
            setFrontRadarInfo0x04();
            return;
        }
        if (i == 33) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            setAirInfo0x21();
        } else if (i == 34) {
            if (isDoorMsgRepeat(bArr)) {
                return;
            }
            setDoorData0x22();
        } else if (i == 36) {
            setTrackInfo0x24();
        } else if (i == 38) {
            setSpeedInfo0x26();
        } else {
            if (i != 70) {
                return;
            }
            setCarInfo0x46();
        }
    }

    private void setCarInfo0x46() {
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        updateInstrumentActivity();
    }

    private void setKey0x01() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick2(this.mContext, 0);
            return;
        }
        if (i == 80) {
            realKeyLongClick2(this.mContext, 14);
            return;
        }
        if (i != 81) {
            switch (i) {
                case 18:
                    realKeyLongClick2(this.mContext, 48);
                    break;
                case 19:
                    realKeyLongClick2(this.mContext, 47);
                    break;
                case 20:
                    realKeyLongClick2(this.mContext, 7);
                    break;
                case 21:
                    realKeyLongClick2(this.mContext, 8);
                    break;
                case 22:
                    realKeyLongClick2(this.mContext, 3);
                    break;
                case 23:
                    realKeyLongClick2(this.mContext, HotKeyConstant.K_SPEECH);
                    break;
            }
            return;
        }
        realKeyLongClick2(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
    }

    private void setKey0x02() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        switch (i) {
            case 11:
                realKeyClick(50);
                return;
            case 12:
                break;
            case 13:
                realKeyClick(45);
                return;
            case 14:
                realKeyClick(46);
                return;
            default:
                switch (i) {
                    case 16:
                        realKeyClick(76);
                        break;
                    case 17:
                        realKeyClick(128);
                        break;
                    case 18:
                        break;
                    case 19:
                        realKeyClick(21);
                        break;
                    case 20:
                        realKeyClick(20);
                        break;
                    default:
                        switch (i) {
                            case 26:
                                realKeyClick(7);
                                break;
                            case 27:
                                realKeyClick(8);
                                break;
                            case 28:
                                realKeyClick(59);
                                break;
                            case 29:
                                realKeyClick(151);
                                break;
                            case 30:
                                realKeyClick(14);
                                break;
                            case 31:
                                realKeyClick(HotKeyConstant.K_DISP);
                                break;
                            case 32:
                                realKeyClick(58);
                                break;
                            case 33:
                                realKeyClick(47);
                                break;
                            case 34:
                                realKeyClick(48);
                                break;
                            case 35:
                                realKeyClick(1);
                                break;
                            case 36:
                                realKeyClick(31);
                                break;
                            default:
                                switch (i) {
                                    case 48:
                                        realKeyClick(32);
                                        break;
                                    case 49:
                                        realKeyClick(33);
                                        break;
                                    case 50:
                                        realKeyClick(34);
                                        break;
                                    case 51:
                                        realKeyClick(35);
                                        break;
                                    case 52:
                                        realKeyClick(36);
                                        break;
                                    case 53:
                                        realKeyClick(37);
                                        break;
                                    case 54:
                                        realKeyClick(38);
                                        break;
                                    case 55:
                                        realKeyClick(39);
                                        break;
                                    case 56:
                                        realKeyClick(40);
                                        break;
                                    case 57:
                                        realKeyClick(41);
                                        break;
                                    default:
                                        switch (i) {
                                            case 60:
                                                realKeyClick(15);
                                                break;
                                            case 61:
                                                realKeyClick(14);
                                                break;
                                            case 62:
                                                realKeyClick(43);
                                                break;
                                        }
                                }
                        }
                }
                return;
        }
        realKeyClick(49);
    }

    private void setFrontRadarInfo0x04() {
        GeneralDzData.radar_front_l = this.mCanBusInfoInt[2];
        GeneralDzData.radar_front_ml = this.mCanBusInfoInt[3];
        GeneralDzData.radar_front_mr = this.mCanBusInfoInt[4];
        GeneralDzData.radar_front_r = this.mCanBusInfoInt[5];
        updateRadar();
    }

    private void setRearRadarInfo0x05() {
        GeneralDzData.radar_rear_l = this.mCanBusInfoInt[2];
        GeneralDzData.radar_rear_ml = this.mCanBusInfoInt[3];
        GeneralDzData.radar_rear_mr = this.mCanBusInfoInt[4];
        GeneralDzData.radar_rear_r = this.mCanBusInfoInt[5];
        updateRadar();
    }

    private void setAirInfo0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.rest = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
            updateAirActivity(this.mContext, 1001);
        }
        updateAirPopupView(this.mContext);
    }

    private void setDoorData0x22() {
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void setTrackInfo0x24() {
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(this.mCanBusInfoByte[2], (byte) 0, 0, 50, 8);
        updateParkUi(null, this.mContext);
    }

    private void setSpeedInfo0x26() {
        int[] iArr = this.mCanBusInfoInt;
        String strValueOf = String.valueOf((iArr[2] * 256) + iArr[3]);
        int[] iArr2 = this.mCanBusInfoInt;
        String strValueOf2 = String.valueOf((iArr2[4] * 256) + iArr2[5]);
        String str = ((this.mCanBusInfoInt[6] / 2) - 40) + getTempUnitC(this.mContext);
        String str2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1) + "";
        String str3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1) + "";
        String str4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1) + "";
        String str5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4) + "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, strValueOf));
        arrayList.add(new DriverUpdateEntity(0, 1, strValueOf2));
        arrayList.add(new DriverUpdateEntity(0, 2, str));
        arrayList.add(new DriverUpdateEntity(0, 3, str2));
        arrayList.add(new DriverUpdateEntity(0, 4, str3));
        arrayList.add(new DriverUpdateEntity(0, 5, str4));
        arrayList.add(new DriverUpdateEntity(0, 6, str5));
        updateGeneralDriveData(arrayList);
        updateInstrumentActivity();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArr = {22, 112, 3};
        byte[] bArrExceptBOMHead = new byte[0];
        try {
            bArrExceptBOMHead = DataHandleUtils.exceptBOMHead(str4.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr, bArrExceptBOMHead), 67));
        byte[] bArr2 = {22, 113, 3};
        byte[] bArrExceptBOMHead2 = new byte[0];
        try {
            bArrExceptBOMHead2 = DataHandleUtils.exceptBOMHead(str6.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr2, bArrExceptBOMHead2), 67));
        byte[] bArr3 = {22, 114, 3};
        byte[] bArrExceptBOMHead3 = new byte[0];
        try {
            bArrExceptBOMHead3 = DataHandleUtils.exceptBOMHead(str5.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr3, bArrExceptBOMHead3), 67));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        sendSourceMsg1(str);
        sendSourceMsg2(str2);
        sendSourceMsg3(str3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendSourceMsg1("OFF");
        sendSourceMsg2(" ");
        sendSourceMsg3(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendSourceMsg1(getBandUnit(str));
        sendSourceMsg2(getBandUnit(str2));
        sendSourceMsg3(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendSourceMsg1("AUX");
        sendSourceMsg2(" ");
        sendSourceMsg3(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendSourceMsg1("DTV");
        sendSourceMsg2(" ");
        sendSourceMsg3(" ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        sendSourceMsg1(str);
        sendSourceMsg2(str3);
        sendSourceMsg3(str2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, 112, 3}, (((int) b4) + ":" + ((int) b5) + "   ").getBytes()), 67));
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, 113, 3}, ((((b6 & 255) * 256) + (i & 255)) + "/" + (i2 & 255)).getBytes()), 67));
        sendSourceMsg3(" ");
    }

    private String getBandUnit(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return "KHz";
            case "FM1":
            case "FM2":
            case "FM3":
                return "MHz";
            default:
                return "";
        }
    }

    private void sendSourceMsg1(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, 112, 3}, str.getBytes()), 67));
    }

    private void sendSourceMsg2(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, 113, 3}, str.getBytes()), 67));
    }

    private void sendSourceMsg3(String str) {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, 114, 3}, str.getBytes()), 67));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        DataHandleUtils.setIntByteWithBit(0, 7, z2);
        DataHandleUtils.setIntByteWithBit(0, 6, !z);
        CanbusMsgSender.sendMsg(new byte[]{22, ByteCompanionObject.MAX_VALUE, (byte) i5, (byte) i6, (byte) 0, (byte) i2, (byte) i3, (byte) i4});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, 122, 3}, 67));
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, 123, 3}, 67));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) throws UnsupportedEncodingException {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        byte[] bArr2 = {22, 122, 3};
        byte[] bytes = new byte[0];
        try {
            bytes = new String(bArr).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(bArr2, bytes), 67));
        byte[] bArr3 = {22, 123, 3};
        byte[] bytes2 = new byte[0];
        try {
            bytes2 = ((i / 60) + ":" + (i % 60)).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(bArr3, bytes2), 67));
    }

    private int getModeInt(int i) {
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[2];
        if (i == 3 || i == 4) {
            i2 = iArr[3];
        }
        return DataHandleUtils.getIntFromByteWithBit(i2, (i == 3 || i == 0) ? 4 : 0, 4);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 255 == i ? "HI" : (i < 30 || i > 64) ? "" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void updateSystemUIDrivingPattern(int i) {
        if (i < 0 || i > 4) {
            return;
        }
        Intent intent = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
        intent.putExtra("_283_car_mode", String.valueOf(i));
        this.mContext.sendBroadcast(intent);
    }

    private void updateInstrumentActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof InstrumentActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    protected void updateAirPopupView(Context context) {
        if (this.mAirPopupView == null) {
            this.mAirPopupView = new AirPopupView(context);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._299.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public final void callback() {
                this.f$0.m359lambda$updateAirPopupView$1$comhzbhdcanbuscar_299MsgMgr();
            }
        });
    }

    /* renamed from: lambda$updateAirPopupView$1$com-hzbhd-canbus-car-_299-MsgMgr, reason: not valid java name */
    /* synthetic */ void m359lambda$updateAirPopupView$1$comhzbhdcanbuscar_299MsgMgr() {
        this.mAirPopupView.refreshUi();
    }

    protected void updateRadarView(Context context) {
        if (this.mRadarView == null) {
            this.mRadarView = new RadarView(context);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._299.MsgMgr$$ExternalSyntheticLambda1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public final void callback() throws Settings.SettingNotFoundException {
                this.f$0.m360lambda$updateRadarView$2$comhzbhdcanbuscar_299MsgMgr();
            }
        });
    }

    /* renamed from: lambda$updateRadarView$2$com-hzbhd-canbus-car-_299-MsgMgr, reason: not valid java name */
    /* synthetic */ void m360lambda$updateRadarView$2$comhzbhdcanbuscar_299MsgMgr() throws Settings.SettingNotFoundException {
        this.mRadarView.refreshUiOut();
    }

    private CusPanoramicView getMyPanoramicView() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (CusPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        return this.mPanoramicView;
    }
}
