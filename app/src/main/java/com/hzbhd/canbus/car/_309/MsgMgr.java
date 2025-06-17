package com.hzbhd.canbus.car._309;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.PanelKeyActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    private byte carInfoData0;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 49});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 42});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        CommUtil.printHexString("scyscyscy309：", bArr);
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            keyEvent0x11();
            return;
        }
        if (i == 28) {
            mediaInfo0x1c();
            return;
        }
        if (i == 32) {
            keyEvent0x20();
            return;
        }
        if (i == 36) {
            carInfo0x24();
            return;
        }
        if (i == 42) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            airInfo0x2A();
        } else if (i == 48) {
            updateVersionInfo(context, getVersionStr(bArr));
        } else {
            if (i != 49) {
                return;
            }
            amplifierInfo0x31();
        }
    }

    private void amplifierInfo0x31() {
        Context context;
        int i;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        GeneralAmplifierData.frontRear = -(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 7);
        GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 7;
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
        updateAmplifierActivity(null);
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[7])) {
            context = this.mContext;
            i = R.string._309_value_4;
        } else {
            context = this.mContext;
            i = R.string._309_value_3;
        }
        arrayList.add(new DriverUpdateEntity(0, 3, context.getString(i)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void airInfo0x2A() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void carInfo0x24() {
        Context context;
        int i;
        Context context2;
        int i2;
        Context context3;
        int i3;
        if (this.carInfoData0 != this.mCanBusInfoByte[2]) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
            this.carInfoData0 = this.mCanBusInfoByte[2];
        }
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoByte[3])) {
            context = this.mContext;
            i = R.string.reversing;
        } else {
            context = this.mContext;
            i = R.string.non_reverse;
        }
        arrayList.add(new DriverUpdateEntity(0, 0, context.getString(i)));
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoByte[3])) {
            context2 = this.mContext;
            i2 = R.string._309_value_1;
        } else {
            context2 = this.mContext;
            i2 = R.string._309_value_2;
        }
        arrayList.add(new DriverUpdateEntity(0, 1, context2.getString(i2)));
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoByte[3])) {
            context3 = this.mContext;
            i3 = R.string.english_on;
        } else {
            context3 = this.mContext;
            i3 = R.string.english_off;
        }
        arrayList.add(new DriverUpdateEntity(0, 2, context3.getString(i3)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void mediaInfo0x1c() {
        String str;
        ArrayList arrayList = new ArrayList();
        GeneralOriginalCarDeviceData.cdStatus = getCDStatus();
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            GeneralOriginalCarDeviceData.runningState = "";
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, ""));
        } else if (i == 1) {
            int i2 = iArr[4];
            String str2 = "MHz";
            if (i2 == 17) {
                str2 = "kHz";
                str = "AM";
            } else {
                str = i2 == 2 ? "FM2" : "FM";
            }
            GeneralOriginalCarDeviceData.runningState = str;
            int i3 = this.mCanBusInfoInt[7];
            String strValueOf = String.valueOf(((mCanBusInfoInt[5] * 256) + mCanBusInfoInt[6]) / 10.0f);
            if (str.contains("AM")) {
                strValueOf = strValueOf.split("\\.")[0];
            }
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._309_title_1) + strValueOf + str2));
            if (i3 >= 1 && i3 <= 6) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._309_title_2) + i3));
            } else {
                arrayList.add(new OriginalCarDeviceUpdateEntity(1, ""));
            }
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, ""));
        } else if (i == 2) {
            GeneralOriginalCarDeviceData.runningState = "DVD " + (iArr[4] > 0 ? this.mCanBusInfoInt[4] + "" : "");
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._309_title_6) + this.mCanBusInfoInt[5]));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._309_title_3) + (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]) ? this.mContext.getString(R.string.english_on) : this.mContext.getString(R.string.english_off))));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.mContext.getString(R.string._309_title_4) + (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]) ? this.mContext.getString(R.string.english_on) : this.mContext.getString(R.string.english_off))));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.mContext.getString(R.string._309_title_5) + this.mCanBusInfoInt[8] + ":" + this.mCanBusInfoInt[9]));
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private String getCDStatus() {
        int i = this.mCanBusInfoInt[2];
        return i == 1 ? "TUNER" : i == 2 ? "DISC(CD DVD)" : "OFF";
    }

    private void keyEvent0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick(45);
            return;
        }
        if (i == 4) {
            realKeyClick(46);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 135) {
            realKeyClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 9) {
            realKeyClick(14);
            return;
        }
        if (i == 10) {
            realKeyClick(15);
        } else if (i == 21) {
            realKeyClick(50);
        } else {
            if (i != 22) {
                return;
            }
            realKeyClick(49);
        }
    }

    private void keyEvent0x11() {
        if (SystemUtil.isForeground(this.mContext, PanelKeyActivity.class.getName())) {
            byte[] bArr = this.mCanBusInfoByte;
            CanbusMsgSender.sendMsg(new byte[]{22, 116, bArr[2], bArr[3]});
        }
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        switch (i) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(HotKeyConstant.K_SLEEP);
                break;
            case 2:
                realKeyClick(7);
                break;
            case 3:
                realKeyClick(8);
                break;
            case 4:
                realKeyClick(77);
                break;
            case 5:
                realKeyClick(76);
                break;
            case 6:
                realKeyClick(2);
                break;
            case 7:
                realKeyClick(130);
                break;
            case 8:
                realKeyClick(46);
                break;
            case 9:
                realKeyClick(45);
                break;
            default:
                switch (i) {
                    case 16:
                        realKeyClick(3);
                        break;
                    case 17:
                        realKeyClick3_2(this.mContext, 48, i, iArr[3]);
                        break;
                    case 18:
                        realKeyClick3_2(this.mContext, 47, i, iArr[3]);
                        break;
                    case 19:
                        if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                            realKeyClick(33);
                            break;
                        } else {
                            realKeyClick(45);
                            break;
                        }
                    case 20:
                        if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                            realKeyClick(34);
                            break;
                        } else {
                            realKeyClick(91);
                            break;
                        }
                    case 21:
                        if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                            realKeyClick(35);
                            break;
                        } else {
                            realKeyClick(29);
                            break;
                        }
                    case 22:
                        if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                            realKeyClick(36);
                            break;
                        } else {
                            realKeyClick(46);
                            break;
                        }
                    case 23:
                        if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                            realKeyClick(37);
                            break;
                        } else {
                            realKeyClick(90);
                            break;
                        }
                    case 24:
                        if (SourceConstantsDef.SOURCE_ID.FM.name().equals(FutureUtil.instance.getCurrentValidSource().name())) {
                            realKeyClick(38);
                            break;
                        } else {
                            realKeyClick(27);
                            break;
                        }
                    case 25:
                        break;
                    default:
                        switch (i) {
                        }
                }
                byte[] bArr2 = this.mCanBusInfoByte;
                CanbusMsgSender.sendMsg(new byte[]{22, 116, bArr2[2], bArr2[3]});
                break;
        }
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }
}
