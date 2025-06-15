package com.hzbhd.canbus.car._189;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.net.SyslogAppender;


public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private int eachId;
    private byte freqHi;
    private byte freqLo;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private String mSongAlbum;
    private String mSongArtist;
    private String mSongTitle;

    private String getCloseOpenStr(int i) {
        return i == 0 ? "close" : i == 1 ? "open" : "set_default";
    }

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIndexBy3Bit(int i) {
        if (i == 11) {
            return 0;
        }
        if (i != 27) {
            return i != 267 ? 0 : 2;
        }
        return 1;
    }

    private int resultRadar(int i) {
        switch (i) {
            case 1:
            case 2:
                return 4;
            case 3:
            case 4:
                return 3;
            case 5:
            case 6:
                return 2;
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    protected int getPmValue(int i) {
        if (i >= 0 && i <= 49) {
            return 1;
        }
        if (50 <= i && i <= 99) {
            return 2;
        }
        if (100 <= i && i <= 149) {
            return 3;
        }
        if (150 <= i && i <= 199) {
            return 4;
        }
        if (200 > i || i > 299) {
            return (300 > i || i > 999) ? 0 : 6;
        }
        return 5;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        GeneralTireData.isHaveSpareTire = false;
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 33});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -18, 112, (byte) getCurrentEachCanId()});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        int currentEachCanId = getCurrentEachCanId();
        this.eachId = currentEachCanId;
        int i = this.mCanBusInfoInt[1];
        if (i == 20) {
            setBacklight0x14();
            return;
        }
        if (i == 32) {
            setSteeringWheelControls();
            return;
        }
        if (i == 48) {
            setTrack0x30();
            return;
        }
        if (i == 96) {
            setChargingState0x60();
            return;
        }
        if (i == 125) {
            set0x7D();
            return;
        }
        if (i == 127) {
            setVersionInfo();
            return;
        }
        if (i == 63) {
            setturnlightInfo0x3f();
            return;
        }
        if (i == 64) {
            if (currentEachCanId == 8 || currentEachCanId == 11 || currentEachCanId == 23) {
                setPanoramic0x40();
                return;
            }
            return;
        }
        if (i == 82) {
            if (getCurrentCanDifferentId() == 8 || getCurrentCanDifferentId() == 19) {
                setTireData0x52();
                return;
            }
            return;
        }
        if (i != 83) {
            switch (i) {
                case 34:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x22();
                        break;
                    }
                    break;
                case 35:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x23();
                        break;
                    }
                    break;
                case 36:
                    if (!isDoorMsgRepeat(bArr)) {
                        setDoorData0x24();
                        break;
                    }
                    break;
                case 37:
                    setRearRadarInfo0x25();
                    break;
                case 38:
                    if (currentEachCanId == 8 || currentEachCanId == 21) {
                        setFrontRadarInfo0x26();
                        break;
                    }
                    break;
                case 39:
                    if (currentEachCanId == 8) {
                        setSettingData0x27();
                        break;
                    }
                    break;
                case 40:
                    if (getCurrentCanDifferentId() == 7) {
                        setOutDoorTem0x28();
                        break;
                    }
                    break;
                default:
                    switch (i) {
                        case 78:
                            setSettingData0x4E();
                            break;
                        case 79:
                            if (currentEachCanId == 16) {
                                setSettingData0x4F();
                                break;
                            }
                            break;
                        case 80:
                            if (currentEachCanId == 5 || currentEachCanId == 7 || currentEachCanId == 8 || currentEachCanId == 10 || currentEachCanId == 11 || currentEachCanId == 12 || currentEachCanId == 13 || currentEachCanId == 14 || currentEachCanId == 17 || currentEachCanId == 18 || currentEachCanId == 19 || currentEachCanId == 22) {
                                setSettingData0x50();
                                break;
                            }
                    }
                    break;
            }
            return;
        }
        setMaintenance0x53();
    }

    private void set0x7D() {
        if (this.mCanBusInfoInt[2] != 8) {
            return;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[4], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setturnlightInfo0x3f() {
        switchFCamera(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        updateParkUi(null, this.mContext);
    }

    private void setBacklight0x14() {
        int i = this.mCanBusInfoInt[2];
        if (i >= 0 && i < 51) {
            setBacklightLevel(1);
            return;
        }
        if (i >= 51 && i < 102) {
            setBacklightLevel(2);
            return;
        }
        if (i >= 102 && i < 153) {
            setBacklightLevel(3);
            return;
        }
        if (i >= 153 && i < 204) {
            setBacklightLevel(4);
        } else {
            if (i < 204 || i >= 255) {
                return;
            }
            setBacklightLevel(5);
        }
    }

    private void setMaintenance0x53() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sbAppend = new StringBuilder().append(this.mContext.getString(R.string._189_maintenance_tip_1));
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sbAppend.append((iArr[2] * 256) + iArr[3]).append(this.mContext.getString(R.string._189_maintenance_tip_2)).toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setChargingState0x60() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, getMessage(this.mCanBusInfoInt[2])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getMessage(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._189_charging_state_1);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._189_charging_state_2);
        }
        return this.mContext.getString(R.string.set_default);
    }

    private void setRearRadarInfo0x25() {
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.setRearRadarLocationData(7, resultRadar(this.mCanBusInfoInt[2]), resultRadar(this.mCanBusInfoInt[3]), resultRadar(this.mCanBusInfoInt[4]), resultRadar(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x26() {
        RadarInfoUtil.mMinIsClose = false;
        RadarInfoUtil.setFrontRadarLocationData(7, resultRadar(this.mCanBusInfoInt[2]), resultRadar(this.mCanBusInfoInt[3]), resultRadar(this.mCanBusInfoInt[4]), resultRadar(this.mCanBusInfoInt[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setTrack0x30() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 5445, 16);
        MyLog.temporaryTracking("ZJ " + GeneralParkData.trackAngle);
        updateParkUi(null, this.mContext);
    }

    private void setTireData0x52() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(0, getTisWarmMsg(iArr[2], 1, DataHandleUtils.getBoolBit7(iArr[12])), getTirePressure(this.mCanBusInfoInt[4]), getTemperature(this.mCanBusInfoInt[8])));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(1, getTisWarmMsg(iArr2[2], 0, DataHandleUtils.getBoolBit6(iArr2[12])), getTirePressure(this.mCanBusInfoInt[5]), getTemperature(this.mCanBusInfoInt[9])));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(2, getTisWarmMsg(iArr3[3], 1, DataHandleUtils.getBoolBit5(iArr3[12])), getTirePressure(this.mCanBusInfoInt[6]), getTemperature(this.mCanBusInfoInt[10])));
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(getTireEntity(3, getTisWarmMsg(iArr4[3], 0, DataHandleUtils.getBoolBit4(iArr4[12])), getTirePressure(this.mCanBusInfoInt[7]), getTemperature(this.mCanBusInfoInt[11])));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private void setSteeringWheelControls() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
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
            realKeyClick(20);
            return;
        }
        if (i == 4) {
            realKeyClick(21);
            return;
        }
        switch (i) {
            case 6:
                realKeyClick(3);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 8:
                realKeyClick(68);
                break;
            case 9:
                realKeyClick(14);
                break;
            case 10:
                realKeyClick(15);
                break;
            case 11:
                realKeyClick(45);
                break;
            case 12:
                realKeyClick(46);
                break;
            case 13:
                realKeyClick(52);
                break;
            case 14:
                realKeyClick(49);
                break;
            case 15:
                realKeyClick(200);
                break;
            case 16:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            default:
                switch (i) {
                    case 174:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string.steering_wheel_control_switch_to_host);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 175:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string.steering_wheel_control_switches_to_meter);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case SyslogAppender.LOG_LOCAL6 /* 176 */:
                        int currentCanDifferentId = getCurrentCanDifferentId();
                        if (currentCanDifferentId == 4 || currentCanDifferentId == 6 || currentCanDifferentId == 7 || currentCanDifferentId == 9 || currentCanDifferentId == 10 || currentCanDifferentId == 11 || currentCanDifferentId == 12 || currentCanDifferentId == 13 || currentCanDifferentId == 15 || currentCanDifferentId == 16 || currentCanDifferentId == 17 || currentCanDifferentId == 18 || currentCanDifferentId == 19 || currentCanDifferentId == 21 || currentCanDifferentId == 22 || currentCanDifferentId == 23 || currentCanDifferentId == 24 || currentCanDifferentId == 25) {
                            Intent intent = new Intent(this.mContext, (Class<?>) SettingActivity.class);
                            intent.addFlags(268435456);
                            intent.setAction(Constant.SETTING_OPEN_TARGET_PAGE);
                            intent.putExtra(Constant.LEFT_INDEX, 0);
                            intent.putExtra(Constant.RIGHT_INDEX, 13);
                            this.mContext.startActivity(intent);
                            break;
                        }
                        break;
                }
        }
    }

    private void setSettingData0x27() {
        int i = this.mCanBusInfoInt[2];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 13, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setOutDoorTem0x28() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setSettingData0x4F() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]));
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]));
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]));
        int i = this.mCanBusInfoInt[4];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit7)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(intFromByteWithBit2)));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit5)));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(intFromByteWithBit3)));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(intFromByteWithBit)));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit2)));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit3)));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(indexBy2Bit4)));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(indexBy2Bit6)));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(indexBy2Bit8)));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(indexBy2Bit9)));
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(indexBy2Bit10)));
        if (i >= 40 && i <= 80) {
            arrayList.add(new SettingUpdateEntity(0, 13, Integer.valueOf(i)).setProgress(i - 20));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingData0x4E() {
        ArrayList arrayList = new ArrayList();
        int i = this.eachId;
        if (i == 20) {
            if (this.mCanBusInfoInt[2] == 0) {
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
            }
            if (this.mCanBusInfoInt[2] == 1) {
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
            }
            if (this.mCanBusInfoInt[2] == 3) {
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
            }
            if (this.mCanBusInfoInt[2] == 8) {
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
            }
            if (this.mCanBusInfoInt[2] == 33) {
                arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
            }
            if (this.mCanBusInfoInt[2] == 40) {
                arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
            }
        } else {
            switch (i) {
                case 23:
                    if (this.mCanBusInfoInt[2] == 8) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 19) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 20) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 40) {
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 41) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                        break;
                    }
                    break;
                case 24:
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 28) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 30) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 36) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    int[] iArr = this.mCanBusInfoInt;
                    if (iArr[2] == 39) {
                        int i2 = iArr[3];
                        if (i2 == 255) {
                            i2 = 3;
                        }
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(i2)));
                    }
                    if (this.mCanBusInfoInt[2] == 42) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
                case 25:
                    if (this.mCanBusInfoInt[2] == 8) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 9) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 10) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 28) {
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 31) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 32) {
                        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 41) {
                        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
                case 26:
                    if (this.mCanBusInfoInt[2] == 8) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 10) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 20) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 28) {
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 33) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    int[] iArr2 = this.mCanBusInfoInt;
                    if (iArr2[2] == 39) {
                        int i3 = iArr2[3];
                        if (i3 == 255) {
                            i3 = 3;
                        }
                        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(i3)));
                    }
                    if (this.mCanBusInfoInt[2] == 43) {
                        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
                case 27:
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 27) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
                case 28:
                    if (this.mCanBusInfoInt[2] == 8) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 28) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    int[] iArr3 = this.mCanBusInfoInt;
                    if (iArr3[2] == 39) {
                        int i4 = iArr3[3];
                        if (i4 == 255) {
                            i4 = 3;
                        }
                        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(i4)));
                    }
                    if (this.mCanBusInfoInt[2] == 43) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 44) {
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 45) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
                case 29:
                    if (this.mCanBusInfoInt[2] == 10) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 20) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 30) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 33) {
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 46) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
                case 30:
                    if (this.mCanBusInfoInt[2] == 8) {
                        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 17) {
                        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 39) {
                        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 43) {
                        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 47) {
                        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(this.mCanBusInfoInt[3])));
                    }
                    if (this.mCanBusInfoInt[2] == 48) {
                        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(this.mCanBusInfoInt[3])));
                        break;
                    }
                    break;
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setSettingData0x50() {
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
        int indexBy2Bit3 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        int indexBy2Bit4 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        int indexBy2Bit5 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        int indexBy2Bit6 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit7 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        int indexBy2Bit8 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]));
        int indexBy2Bit9 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]));
        int indexBy2Bit10 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]));
        int indexBy2Bit11 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]));
        int indexBy2Bit12 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]));
        int indexBy2Bit13 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]));
        int indexBy2Bit14 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]));
        int indexBy2Bit15 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]));
        int indexBy2Bit16 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]));
        int indexBy2Bit17 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]));
        int indexBy2Bit18 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]));
        int indexBy2Bit19 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]));
        int indexBy2Bit20 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]));
        int indexBy2Bit21 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]));
        int indexBy2Bit22 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]));
        int indexBy2Bit23 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]));
        int indexBy2Bit24 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]));
        int indexBy2Bit25 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]));
        int indexBy2Bit26 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]));
        int indexBy2Bit27 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]));
        int indexBy2Bit28 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]));
        int indexBy2Bit29 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]));
        int indexBy2Bit30 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
        int indexBy2Bit31 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]));
        int indexBy2Bit32 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]));
        int indexBy2Bit33 = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]));
        int indexBy2Bit34 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]));
        int indexBy2Bit35 = getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]));
        int indexBy2Bit36 = getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]));
        int indexBy2Bit37 = getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8]));
        int indexBy2Bit38 = getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]));
        int indexBy2Bit39 = getIndexBy2Bit(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]));
        int indexBy2Bit40 = getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]));
        ArrayList arrayList = new ArrayList();
        switch (this.eachId) {
            case 5:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit2)));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit3)));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit4)));
                arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit5)));
                break;
            case 7:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit6)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit7)));
                break;
            case 8:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit8)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit9)));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit10)));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit11)));
                arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit12)));
                arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit13)));
                arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit27)));
                arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit28)));
                arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(indexBy2Bit29)));
                arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(indexBy2Bit30)));
                arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(intFromByteWithBit)));
                arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(indexBy2Bit31)));
                arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(indexBy2Bit32)));
                break;
            case 10:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit21)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit22)));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit23)));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit24)));
                arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit25)));
                arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit26)));
                break;
            case 11:
                arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit27)));
                arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit28)));
                arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(indexBy2Bit29)));
                arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(indexBy2Bit30)));
                arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(intFromByteWithBit)));
                arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(indexBy2Bit31)));
                arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(indexBy2Bit32)));
                break;
            case 12:
            case 13:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit14)));
                break;
            case 14:
            case 17:
            case 18:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit33)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit34)));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit35)));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit36)));
                arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(indexBy2Bit37)));
                arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(indexBy2Bit38)));
                arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(indexBy2Bit39)));
                arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(indexBy2Bit40)));
                break;
            case 19:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit14)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit18)));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit19)));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit20)));
                break;
            case 22:
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit14)));
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit15)));
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit16)));
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy2Bit17)));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy2Bit18)));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(indexBy2Bit19)));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(indexBy2Bit20)));
                break;
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
    }

    public void mForceReverse(boolean z) {
        forceReverse(this.mContext, z);
    }

    private void setPanoramic0x40() {
        forceReverse(this.mContext, this.mCanBusInfoInt[2] == 1);
        DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[4], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
        ArrayList arrayList = new ArrayList();
        if (getCurrentCanDifferentId() == 7) {
            arrayList.add(new SettingUpdateEntity(1, 0, 0));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(i == 3 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(i == 4 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(i == 1 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(i == 2 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(intFromByteWithBit3)));
            arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(intFromByteWithBit4)));
            arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(intFromByteWithBit5)));
            arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(intFromByteWithBit6)));
            arrayList.add(new SettingUpdateEntity(1, 9, Integer.valueOf(intFromByteWithBit7)));
            arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(intFromByteWithBit8)));
        } else if (getCurrentCanDifferentId() == 10) {
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(i == 3 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(i == 4 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(i == 1 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(i == 2 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(i == 5 ? 1 : 0)));
            arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(intFromByteWithBit)));
            arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(intFromByteWithBit2)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x22() {
        GeneralAirData.eco = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        int i = this.mCanBusInfoInt[3];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[7]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralAirData.center_wheel = resolveCenterWheelValue(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 2));
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2);
        int[] iArr = this.mCanBusInfoInt;
        switch (getPmValue((iArr[9] * 256) + iArr[10])) {
            case 1:
                GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(R.string.pm_excellent);
                break;
            case 2:
                GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(R.string.pm_good);
                break;
            case 3:
                GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(R.string.pm_mild_pollution);
                break;
            case 4:
                GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(R.string.pm_moderately_polluted);
                break;
            case 5:
                GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(R.string.pm_heavy_pollution);
                break;
            case 6:
                GeneralAirData.pm_value_level_in_car = this.mContext.getResources().getString(R.string.pm_severe_pollution);
                break;
        }
        int[] iArr2 = this.mCanBusInfoInt;
        switch (getPmValue((iArr2[11] * 256) + iArr2[12])) {
            case 1:
                GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(R.string.pm_excellent);
                break;
            case 2:
                GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(R.string.pm_good);
                break;
            case 3:
                GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(R.string.pm_mild_pollution);
                break;
            case 4:
                GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(R.string.pm_moderately_polluted);
                break;
            case 5:
                GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(R.string.pm_heavy_pollution);
                break;
            case 6:
                GeneralAirData.pm_value_level_out_car = this.mContext.getResources().getString(R.string.pm_severe_pollution);
                break;
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setDoorData0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private boolean isCanPlay() {
        return getCurrentCanDifferentId() == 7 || getCurrentCanDifferentId() == 9 || getCurrentCanDifferentId() == 10 || getCurrentCanDifferentId() == 15 || getCurrentCanDifferentId() == 22 || getCurrentCanDifferentId() == 23 || getCurrentCanDifferentId() == 25;
    }

    private boolean isPhoneState() {
        return getCurrentCanDifferentId() == 7 || getCurrentCanDifferentId() == 15 || getCurrentCanDifferentId() == 22 || getCurrentCanDifferentId() == 23 || getCurrentCanDifferentId() == 25;
    }

    private boolean isSend() {
        return getCurrentCanDifferentId() == 15 || getCurrentCanDifferentId() == 2 || getCurrentCanDifferentId() == 23 || getCurrentCanDifferentId() == 25;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        super.atvDestdroy();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        if (isPhoneState()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1});
            sendPhone(bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        if (isPhoneState()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 2});
            sendPhone(bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        if (isPhoneState()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 4});
            sendPhone(bArr);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        if (isPhoneState()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 5});
            sendPhone(new byte[0]);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (!isPhoneState() || z) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 6});
        sendPhone(bArr);
    }

    private void sendPhone(byte[] bArr) {
        if (isSend()) {
            try {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -54, 3, 17}, DataHandleUtils.exceptBOMHead(new String(bArr).getBytes("UnicodeLittle"))));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 19});
            if (sendMusicFirst(str4, str5, str6)) {
                sendMusic(str4, str5, str6);
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
            sendMusic("", "", "");
        }
    }

    private boolean sendMusicFirst(String str, String str2, String str3) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(this.mSongTitle) || TextUtils.isEmpty(this.mSongAlbum) || TextUtils.isEmpty(this.mSongArtist) || !this.mSongTitle.equals(str) || !this.mSongAlbum.equals(str2) || !this.mSongArtist.equals(str3);
    }

    private void sendMusic(String str, String str2, String str3) {
        if (isSend()) {
            this.mSongTitle = str;
            this.mSongAlbum = str2;
            this.mSongArtist = str3;
            byte[] bArr = {22, 113, 17};
            byte[] bArr2 = {22, 114, 17};
            try {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 17}, DataHandleUtils.exceptBOMHead(str.getBytes("UnicodeLittle"))));
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, DataHandleUtils.exceptBOMHead(str2.getBytes("UnicodeLittle"))));
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, DataHandleUtils.exceptBOMHead(str3.getBytes("UnicodeLittle"))));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (isCanPlay()) {
            byte allBandTypeData = getAllBandTypeData(str, (byte) 0, (byte) 0, (byte) 0, (byte) 16, (byte) 16);
            getFreqByteHiLo(str, str2);
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, allBandTypeData, this.freqLo, this.freqHi});
        }
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 0});
            String str5 = i + "/" + i2;
            String str6 = str2 + ":" + str3 + ":" + str4;
            if (sendMusicFirst(str5, str6, " ")) {
                sendMusic(str5, str6, " ");
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        if (isCanPlay()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
            sendMusic("", "", "");
        }
    }

    private String getTemperature(int i) {
        return (i - 50) + this.mContext.getString(R.string.str_temp_c_unit);
    }

    private TireUpdateEntity getTireEntity(int i, String[] strArr, String str, String str2) {
        String strByResId = CommUtil.getStrByResId(this.mContext, "_189_status_1");
        int i2 = 0;
        for (String str3 : strArr) {
            if (!str3.equals(strByResId)) {
                Log.d("lai", "getTireEntity: ");
                i2 = 1;
            }
        }
        return new TireUpdateEntity(i, i2, new String[]{str, str2, strArr[0], strArr[1], strArr[2], strArr[3]});
    }

    private String getTirePressure(int i) {
        return String.valueOf(Math.floor(i * 1.37d)) + this.mContext.getString(R.string.pressure_unit);
    }

    private String[] getTisWarmMsg(int i, int i2, boolean z) {
        String[] strArr = {"", "", "", ""};
        if (i2 == 1) {
            if (DataHandleUtils.getIntFromByteWithBit(i, 6, 2) == 0) {
                strArr[0] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
            } else if (DataHandleUtils.getIntFromByteWithBit(i, 6, 2) == 1) {
                strArr[0] = CommUtil.getStrByResId(this.mContext, "_189_status_2");
            } else {
                strArr[0] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
            }
            if (DataHandleUtils.getIntFromByteWithBit(i, 5, 1) == 1) {
                strArr[1] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
            } else {
                strArr[1] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
            }
            if (DataHandleUtils.getIntFromByteWithBit(i, 4, 1) == 1) {
                strArr[2] = CommUtil.getStrByResId(this.mContext, "_189_status_4");
            } else {
                strArr[2] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
            }
        } else {
            if (DataHandleUtils.getIntFromByteWithBit(i, 2, 2) == 0) {
                strArr[0] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
            } else if (DataHandleUtils.getIntFromByteWithBit(i, 6, 2) == 1) {
                strArr[0] = CommUtil.getStrByResId(this.mContext, "_189_status_2");
            } else {
                strArr[0] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
            }
            if (DataHandleUtils.getIntFromByteWithBit(i, 1, 1) == 1) {
                strArr[1] = CommUtil.getStrByResId(this.mContext, "_189_status_3");
            } else {
                strArr[1] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
            }
            if (DataHandleUtils.getIntFromByteWithBit(i, 0, 1) == 1) {
                strArr[2] = CommUtil.getStrByResId(this.mContext, "_189_status_4");
            } else {
                strArr[2] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
            }
        }
        if (z) {
            strArr[3] = CommUtil.getStrByResId(this.mContext, "_189_Fast_leak");
        } else {
            strArr[3] = CommUtil.getStrByResId(this.mContext, "_189_status_1");
        }
        return strArr;
    }

    private String resolveOutDoorTem() {
        String str;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (intFromByteWithBit >= 127) {
            intFromByteWithBit = 127;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            str = "-" + intFromByteWithBit;
        } else {
            str = intFromByteWithBit + "";
        }
        return str + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTempGe(int i) {
        return (1 > i || i > 17) ? "" : i + "格";
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (127 == i) {
            return "HI";
        }
        if (1 <= i && i <= 30) {
            return ((i * 0.5d) + 17.0d) + getTempUnitC(this.mContext);
        }
        if (32 > i || i > 34) {
            return "";
        }
        if (i == 32) {
            return 16 + getTempUnitC(this.mContext);
        }
        if (i == 33) {
            return 16.5d + getTempUnitC(this.mContext);
        }
        return 17 + getTempUnitC(this.mContext);
    }

    private String resolveCenterWheelValue(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.ion) + this.mContext.getResources().getString(R.string.close);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string.ion) + " Lv1";
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string.ion) + " Lv2";
        }
        return this.mContext.getResources().getString(R.string.ion) + " Lv3";
    }

    private void realKeyClick(int i) {
        realKeyClick1(this.mContext, i, this.mCanBusInfoInt[2], this.mCanBusInfoByte[3]);
    }

    private void getSettingItem() {
        ((UiMgr) UiMgrFactory.getCanUiMgr(this.mContext)).updateUiByDifferentCar(this.mContext);
    }
}
