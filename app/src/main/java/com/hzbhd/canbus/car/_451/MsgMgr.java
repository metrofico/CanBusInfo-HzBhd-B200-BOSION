package com.hzbhd.canbus.car._451;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.car_cus._451.data.EquipData;
import com.hzbhd.canbus.car_cus._451.observer.ObserverBuilding451;
import com.hzbhd.canbus.car_cus._451.util.CycleRequestRadar;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.media.aux2.action.ActionTag;
import com.hzbhd.canbus.media.aux2.manager.Aux2Manager;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.sourcemanager.ISourceListener;
import com.hzbhd.proxy.sourcemanager.SourceManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

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
    int[] mInfo1;
    int[] mInfo2;
    int[] mInfo3;
    int[] mInfo4;
    int[] mInfo5;
    int[] mInfo6;
    int[] mInfo7;
    int[] mPanoramicInfo;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getRadarRange(int i) {
        if (i > 10) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        return i;
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
        registerAux2();
    }

    private void registerAux2() {
        SourceManager.getInstance().registerSourceListener(SourceConstantsDef.SOURCE_ID.AUX2, SourceConstantsDef.DISP_ID.disp_main, new ISourceListener() { // from class: com.hzbhd.canbus.car._451.MsgMgr.1
            @Override // com.hzbhd.proxy.sourcemanager.ISourceListener
            public void onAction(SourceConstantsDef.SOURCE_ACTION source_action, Bundle bundle) {
                if (source_action.equals(SourceConstantsDef.SOURCE_ACTION.PLAY)) {
                    MsgMgr.this.requestToOriginalMode(true);
                }
                if (source_action.equals(SourceConstantsDef.SOURCE_ACTION.PAUSE)) {
                    MsgMgr.this.requestToOriginalMode(false);
                }
                if (source_action.equals(SourceConstantsDef.SOURCE_ACTION.STOP)) {
                    MsgMgr.this.requestToOriginalMode(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestToOriginalMode(boolean z) {
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -111, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void reverseStateChange(boolean z) {
        super.reverseStateChange(z);
        if (z) {
            CycleRequestRadar.getInstance().start(10, new ActionCallback() { // from class: com.hzbhd.canbus.car._451.MsgMgr.2
                @Override // com.hzbhd.canbus.car_cus._451.Interface.ActionCallback
                public void toDo(Object obj) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -112, 97});
                    CycleRequestRadar.getInstance().reset(500);
                }
            });
        } else {
            CycleRequestRadar.getInstance().stop();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 18) {
            setDriveInfo0x12(byteArrayToIntArray);
            setDoorInfo0x12(this.mCanBusInfoInt);
            return;
        }
        if (i == 22) {
            set0x16(byteArrayToIntArray);
            return;
        }
        if (i == 34) {
            set0x22(byteArrayToIntArray);
            return;
        }
        if (i == 36) {
            setAir0x24(byteArrayToIntArray);
            return;
        }
        if (i == 48) {
            updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
            return;
        }
        if (i == 71) {
            set0x47();
            return;
        }
        if (i == 82) {
            set0x52(byteArrayToIntArray);
        } else if (i == 97) {
            set0x61();
        } else {
            if (i != 98) {
                return;
            }
            set0x62();
        }
    }

    private void set0x52(int[] iArr) {
        setBasicInfo(iArr);
        switch (DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 4)) {
            case 0:
                EquipData.mode = EquipData.MODE.AMPL;
                break;
            case 1:
                EquipData.mode = EquipData.MODE.AMPL;
                setRadioInfo(iArr);
                break;
            case 2:
                EquipData.mode = EquipData.MODE.CD;
                setCdInfo(iArr);
                break;
            case 3:
                EquipData.mode = EquipData.MODE.AUX;
                break;
            case 4:
                EquipData.mode = EquipData.MODE.BT;
                setBtInfo(iArr);
                break;
            case 5:
                EquipData.mode = EquipData.MODE.USB;
                setUsbInfo(iArr);
                break;
            case 6:
                EquipData.mode = EquipData.MODE.PHONE;
                break;
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4);
        if (intFromByteWithBit == 0) {
            EquipData.isShowVolume = false;
            return;
        }
        if (intFromByteWithBit == 1) {
            EquipData.isShowVolume = true;
            EquipData.volume = iArr[10];
            EquipData.isMute = false;
            updateEquip();
            return;
        }
        if (intFromByteWithBit == 2) {
            EquipData.isShowVolume = true;
            EquipData.isMute = true;
            updateEquip();
        } else if (intFromByteWithBit == 3) {
            EquipData.mode = EquipData.MODE.AMPL;
            EquipData.isShowVolume = false;
            setAmpl(iArr);
        } else {
            if (intFromByteWithBit != 15) {
                return;
            }
            EquipData.isShowVolume = false;
            setTextInfo(iArr);
        }
    }

    private void setUsbInfo(int[] iArr) {
        if (isFun1NoChange(iArr)) {
            return;
        }
        EquipData.usbFileNumber = this.mContext.getString(R.string._451_folder_sn) + "：" + iArr[11];
        EquipData.usbSongNumber = this.mContext.getString(R.string._451_document_no) + "：" + iArr[13];
        EquipData.usbTimeStr = this.mContext.getString(R.string._451_time) + "：" + iArr[14] + ":" + iArr[15];
        updateEquip();
    }

    private void setBtInfo(int[] iArr) {
        if (isFun2NoChange(iArr)) {
            return;
        }
        EquipData.btSongNumber = iArr[12] == 255 ? "" : this.mContext.getString(R.string._451_song) + "：" + iArr[12];
        EquipData.btTimeStr = this.mContext.getString(R.string._451_time) + "：" + iArr[14] + ":" + iArr[15];
        updateEquip();
    }

    private void setCdInfo(int[] iArr) {
        if (isFun3NoChange(iArr)) {
            return;
        }
        EquipData.cdNumber = this.mContext.getString(R.string._451_disc_number) + "：" + iArr[10];
        EquipData.cdSongNumber = this.mContext.getString(R.string._451_song) + "：" + iArr[12];
        EquipData.cdTimeStr = this.mContext.getString(R.string._451_time) + "：" + iArr[14] + ":" + iArr[15];
        updateEquip();
    }

    private void setRadioInfo(int[] iArr) {
        if (isFun4NoChange(iArr)) {
            return;
        }
        int i = iArr[10];
        if (i == 0) {
            EquipData.presetStr = "";
        } else {
            EquipData.presetStr = String.valueOf(i);
        }
        if (EquipData.band.equals("FM1") || EquipData.band.equals("FM2")) {
            EquipData.frequency = this.df_2Decimal.format(getMsbLsbResult(iArr[11], iArr[12]) / 100.0f) + "MHz";
        } else if (EquipData.band.equals("AM")) {
            EquipData.frequency = getMsbLsbResult(iArr[11], iArr[12]) + "KHz";
        }
        EquipData.isShowSt = DataHandleUtils.getBoolBit7(iArr[13]);
        updateEquip();
    }

    private void setTextInfo(int[] iArr) {
        if (isFun5NoChange(iArr)) {
            return;
        }
        byte[] bArr = new byte[22];
        for (int i = 11; i <= 33; i++) {
            bArr[i - 11] = (byte) iArr[i];
        }
        EquipData.txtInfo = getVersionStr(bArr);
        updateEquip();
    }

    private void setAmpl(int[] iArr) {
        if (isFun6NoChange(iArr)) {
            return;
        }
        int i = iArr[11];
        if (i == 16) {
            EquipData.bas = 0;
        } else if (i >= 11 && i <= 15) {
            EquipData.bas = i - 16;
        } else if (i < 11) {
            EquipData.bas = -5;
        } else if (i >= 17 && i <= 21) {
            EquipData.bas = i - 16;
        } else if (i > 21) {
            EquipData.bas = 5;
        }
        int i2 = iArr[12];
        if (i2 == 16) {
            EquipData.mid = 0;
        } else if (i2 >= 11 && i2 <= 15) {
            EquipData.mid = i2 - 16;
        } else if (i2 < 11) {
            EquipData.mid = -5;
        } else if (i2 >= 17 && i2 <= 21) {
            EquipData.mid = i2 - 16;
        } else if (i2 > 21) {
            EquipData.mid = 5;
        }
        int i3 = iArr[13];
        if (i3 == 16) {
            EquipData.tre = 0;
        } else if (i3 >= 11 && i3 <= 15) {
            EquipData.tre = i3 - 16;
        } else if (i3 < 11) {
            EquipData.tre = -5;
        } else if (i3 >= 17 && i3 <= 21) {
            EquipData.tre = i3 - 16;
        } else if (i3 > 21) {
            EquipData.tre = 5;
        }
        int i4 = iArr[14];
        if (i4 == 16) {
            EquipData.fad = 0;
        } else if (i4 >= 9 && i4 <= 15) {
            EquipData.fad = i4 - 16;
        } else if (i4 < 9) {
            EquipData.fad = -7;
        } else if (i4 >= 17 && i4 <= 23) {
            EquipData.fad = i4 - 16;
        } else if (i4 > 23) {
            EquipData.fad = -7;
        }
        int i5 = iArr[15];
        if (i5 == 16) {
            EquipData.bal = 0;
        } else if (i5 >= 9 && i5 <= 15) {
            EquipData.bal = i5 - 16;
        } else if (i5 < 9) {
            EquipData.bal = -7;
        } else if (i5 >= 17 && i5 <= 23) {
            EquipData.bal = i5 - 16;
        } else if (i5 > 23) {
            EquipData.bal = -7;
        }
        EquipData.asl = iArr[16] == 1;
        updateEquip();
    }

    private void setBasicInfo(int[] iArr) {
        if (isFun7NoChange(iArr)) {
            return;
        }
        int i = iArr[3];
        if (i == 1) {
            EquipData.band = "FM1";
        } else if (i == 2) {
            EquipData.band = "FM2";
        } else if (i == 16) {
            EquipData.band = "AM";
        }
        int i2 = iArr[4];
        if (i2 == 0 || i2 == 1) {
            EquipData.isShowAslIcon = false;
        }
        EquipData.disc_in = DataHandleUtils.getIntFromByteWithBit(iArr[5], 6, 2);
        EquipData.disc1 = DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 2);
        EquipData.disc2 = DataHandleUtils.getIntFromByteWithBit(iArr[5], 2, 2);
        EquipData.disc3 = DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 2);
        EquipData.disc4 = DataHandleUtils.getIntFromByteWithBit(iArr[6], 6, 2);
        EquipData.disc5 = DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 2);
        EquipData.disc6 = DataHandleUtils.getIntFromByteWithBit(iArr[6], 2, 2);
        EquipData.isShowBtIcon = DataHandleUtils.getBoolBit7(iArr[7]);
        EquipData.isShowPhoneIcon = DataHandleUtils.getBoolBit6(iArr[7]);
        EquipData.signal = DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 4);
        EquipData.scan = DataHandleUtils.getIntFromByteWithBit(iArr[8], 6, 2);
        EquipData.rpt = DataHandleUtils.getIntFromByteWithBit(iArr[8], 4, 2);
        EquipData.rand = DataHandleUtils.getIntFromByteWithBit(iArr[8], 2, 2);
        updateEquip();
    }

    public void updateEquip() {
        ObserverBuilding451.getInstance().dataChange();
    }

    private void set0x62() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            Aux2Manager.getInstance().openAux2(this.mContext, 1280, 720);
        } else if (i == 0) {
            Aux2Manager.getInstance().exitAux2(ActionTag.TAG.EXIT, "null");
        }
    }

    private void set0x61() {
        if (is0x61NoChange(this.mCanBusInfoInt)) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(10, getRadarRange(this.mCanBusInfoInt[4]), getRadarRange(this.mCanBusInfoInt[5]), getRadarRange(this.mCanBusInfoInt[6]), getRadarRange(this.mCanBusInfoInt[7]));
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(10, getRadarRange(this.mCanBusInfoInt[8]), getRadarRange(this.mCanBusInfoInt[9]), getRadarRange(this.mCanBusInfoInt[10]), getRadarRange(this.mCanBusInfoInt[11]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12])) {
            int[] iArr = this.mCanBusInfoInt;
            iArr[12] = DataHandleUtils.blockBit(iArr[12], 7);
            int[] iArr2 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = -((DataHandleUtils.getMsbLsbResult(iArr2[12], iArr2[13]) / 31) + 1);
        } else {
            int[] iArr3 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = (DataHandleUtils.getMsbLsbResult(iArr3[12], iArr3[13]) / 31) + 1;
        }
        updateParkUi(null, this.mContext);
    }

    private void set0x47() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_451_resolving_power"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_451_resolving_power", "_451_resolving_power_1"), Integer.valueOf(this.mCanBusInfoInt[2] + 1)).setProgress(this.mCanBusInfoInt[2]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAir0x24(int[] iArr) {
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.sync = DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 2) == 3;
            if (DataHandleUtils.getBoolBit3(iArr[2])) {
                GeneralAirData.front_left_temperature = iArr[3] + getTempUnitF(this.mContext);
            } else {
                GeneralAirData.front_left_temperature = (iArr[3] * 0.5d) + getTempUnitC(this.mContext);
            }
            if (DataHandleUtils.getBoolBit3(iArr[2])) {
                GeneralAirData.front_right_temperature = iArr[4] + getTempUnitF(this.mContext);
            } else {
                GeneralAirData.front_right_temperature = (iArr[4] * 0.5d) + getTempUnitC(this.mContext);
            }
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(iArr[5]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit5(iArr[5]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(iArr[5]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 4);
            GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit7(iArr[7]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[7]);
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr[8], 4, 2);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 2);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(iArr[9], 4, 2);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 2);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(iArr[10]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit5(iArr[10]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(iArr[10]);
            GeneralAirData.front_right_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[11], 0, 4);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x22(int[] iArr) {
        int i = iArr[2];
        if (i == 9) {
            realKeyLongClick1(this.mContext, 2, iArr[3]);
            return;
        }
        if (i == 129) {
            realKeyClick4(this.mContext, 48);
            return;
        }
        if (i == 130) {
            realKeyClick4(this.mContext, 47);
            return;
        }
        if (i == 145) {
            knobKey(7);
            return;
        }
        if (i != 146) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 1, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 16:
                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                            break;
                        case 17:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                            break;
                        case 18:
                            realKeyLongClick1(this.mContext, 14, iArr[3]);
                            break;
                        case 19:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_RADIO, iArr[3]);
                            break;
                        case 20:
                            realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_MEDIA, iArr[3]);
                            break;
                        case 21:
                            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusMainActivity);
                            break;
                        case 22:
                            realKeyLongClick1(this.mContext, 4, iArr[3]);
                            break;
                        case 23:
                            realKeyLongClick1(this.mContext, 58, iArr[3]);
                            break;
                        case 24:
                            startOtherActivity(this.mContext, HzbhdComponentName.CanBusDriveDataActivity);
                            break;
                        case 25:
                            realKeyLongClick1(this.mContext, 15, iArr[3]);
                            break;
                        default:
                            switch (i) {
                                case 32:
                                    realKeyLongClick1(this.mContext, 151, iArr[3]);
                                    break;
                                case 33:
                                    realKeyLongClick1(this.mContext, 21, iArr[3]);
                                    break;
                                case 34:
                                    realKeyLongClick1(this.mContext, 20, iArr[3]);
                                    break;
                                case 35:
                                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                                    break;
                                case 36:
                                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                                    break;
                                case 37:
                                    realKeyLongClick1(this.mContext, 49, iArr[3]);
                                    break;
                                case 38:
                                    realKeyLongClick1(this.mContext, 50, iArr[3]);
                                    break;
                                case 39:
                                    realKeyLongClick1(this.mContext, 50, iArr[3]);
                                    break;
                            }
                    }
            }
            return;
        }
        knobKey(8);
    }

    private void setDoorInfo0x12(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[6]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[6]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[6]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[6]);
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit6(iArr[8]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x16(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_current_vehicle_speed"), String.valueOf(getMsbLsbResult(iArr[2], iArr[3]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_current_speed"), String.valueOf(getMsbLsbResult(iArr[4], iArr[5]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveInfo0x12(int[] iArr) {
        String string;
        ArrayList arrayList = new ArrayList();
        int i = iArr[2];
        if (i == 1) {
            string = this.mContext.getString(R.string._451_acc_status_on);
        } else if (i == 2) {
            string = this.mContext.getString(R.string._451_operation_mode);
        } else if (i == 3) {
            string = this.mContext.getString(R.string._451_ignition_mode);
        } else {
            string = this.mContext.getString(R.string._451_acc_status_off);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_acc_status"), string));
        int i2 = iArr[3];
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_gear_status"), i2 == 1 ? "R" : i2 == 2 ? "N" : i2 == 3 ? "D" : "P"));
        int i3 = iArr[4];
        String str = "----";
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_central_door_lock"), i3 == 0 ? "unLock" : i3 == 1 ? "lock" : "----"));
        int i4 = iArr[5];
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_hand_brake_status"), i4 == 0 ? "OFF" : i4 == 1 ? "ON" : "----"));
        int i5 = iArr[7];
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_current_oil_quantity"), (i5 <= 0 || i5 >= 127) ? "----" : String.valueOf(i5)));
        int i6 = iArr[10];
        if (i6 == 0) {
            str = "OFF";
        } else if (i6 == 1) {
            str = "ON";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_451_body_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_451_small_lamp_status"), str));
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

    public void updateSettingsUi(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = iArr;
        return true;
    }

    private boolean is0x61NoChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return true;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
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

    private boolean isFun1NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo1, iArr)) {
            return true;
        }
        this.mInfo1 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFun2NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo2, iArr)) {
            return true;
        }
        this.mInfo2 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFun3NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo3, iArr)) {
            return true;
        }
        this.mInfo3 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFun4NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo4, iArr)) {
            return true;
        }
        this.mInfo4 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFun5NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo5, iArr)) {
            return true;
        }
        this.mInfo5 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFun6NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo6, iArr)) {
            return true;
        }
        this.mInfo6 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isFun7NoChange(int[] iArr) {
        if (Arrays.equals(this.mInfo7, iArr)) {
            return true;
        }
        this.mInfo7 = Arrays.copyOf(iArr, iArr.length);
        return false;
    }
}
