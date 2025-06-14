package com.hzbhd.canbus.car._288;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String DEVICE_WORK_MODE_AUX = "AUX";
    static final String DEVICE_WORK_MODE_CD = "CD";
    static final String DEVICE_WORK_MODE_MEDIA_OFF = "Media Off";
    static final String DEVICE_WORK_MODE_RADIO = "Radio";
    static final String DEVICE_WORK_MODE_REAR_DISC = "Rear CD";
    private static final int SEND_NORMAL_MESSAGE = 2;
    static final int SHARE_288_AMPLIFIER_BAND_OFFSET = 2;
    static final int SHARE_288_AMPLIFIER_FADE_OFFSET = 7;
    private static boolean isOriginalCdFirst = true;
    private static boolean isOriginalDvdFirst = true;
    private static boolean m0x62First = true;
    private static boolean m0x65First = true;
    int currentDisc;
    int currentPtore;
    private byte[] m0x61InfoCopy;
    private byte[] m0x62InfoCopy;
    private byte[] m0x65InfoCopy;
    private int[] m0xD1Data;
    private int[] mCanBusInfo;
    private byte[] mCanBusInfoByte;
    private int mCanId;
    private Context mContext;
    private int mCurrentClick;
    private int mCurrentStatus;
    private String mDeviceStatusNow;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Intent mIntent1;
    private Intent mIntent2;
    private int outDoorTemp;
    private boolean isOriginalCd = true;
    private String isCdSongList = "";
    private String _0x61runningStatus = " ";
    DecimalFormat mDecimalFormat = new DecimalFormat("00");
    private int mLastDataDvd = 0;
    private int mLastDataCd = 0;
    private List<OriginalCarDeviceUpdateEntity> mList1 = new ArrayList();
    private List<OriginalCarDeviceUpdateEntity> mList2 = new ArrayList();

    private String discInfo(boolean z, boolean z2) {
        return z ? z2 ? "DVD" : DEVICE_WORK_MODE_CD : " ";
    }

    private String discStatus(boolean z) {
        return z ? "Have Disk" : "No Disk";
    }

    private String getDeviceWorkModle(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? " " : DEVICE_WORK_MODE_AUX : DEVICE_WORK_MODE_CD : DEVICE_WORK_MODE_RADIO : DEVICE_WORK_MODE_MEDIA_OFF;
    }

    private String setBand(int i) {
        return i != 1 ? i != 2 ? i != 16 ? "" : "AM" : "FM2" : "FM1";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        initHandler();
        initAmplifierData(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) getCurrentEachCanId()});
        updateAmplifier();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfo = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(byteArrayToIntArray[3], byteArrayToIntArray[2]));
            return;
        }
        if (i == 32) {
            setSwc();
            return;
        }
        if (i == 40) {
            if (isAirMsgRepeat(this.mCanBusInfoByte)) {
                return;
            }
            setAir();
            return;
        }
        if (i == 101) {
            if (DataHandleUtils.getBoolBit0(byteArrayToIntArray[2])) {
                if (!DataHandleUtils.getBoolBit1(this.mCanBusInfo[2])) {
                    if (is0x65Repeat(bArr)) {
                        return;
                    }
                    setRearDiscInfo();
                    return;
                } else {
                    if (isOriginalDvdChange()) {
                        openAuxIn();
                    }
                    sendRearDvdBoardCast();
                    return;
                }
            }
            return;
        }
        if (i == 48) {
            updateVersionInfo(this.mContext, getVersionStr(bArr));
            return;
        }
        if (i == 49) {
            setAmplifier();
            return;
        }
        if (i == 97) {
            if (is0x61Repeat(bArr)) {
                return;
            }
            setDiscStatus();
        } else if (i == 98 && !is0x62Repeat(bArr)) {
            setDiscInfo();
        }
    }

    private void sendRearDvdBoardCast() {
        Intent intent = new Intent();
        this.mIntent1 = intent;
        intent.setAction("com.hzbhd.canbus.car._288.MsgMgr.rear_dvd");
        Bundle bundle = new Bundle();
        bundle.putString("_288_play_mode1", setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4)));
        bundle.putString("_288_work_status1", setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 4, 4)));
        bundle.putString("_288_run_status1", getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4)));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfo;
        StringBuilder sbAppend = sb.append(setByteHighLow(iArr[8], iArr[7])).append("/");
        int[] iArr2 = this.mCanBusInfo;
        bundle.putString("_288_track1", sbAppend.append(setByteHighLow(iArr2[6], iArr2[5])).toString());
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfo;
        StringBuilder sbAppend2 = sb2.append(resolveTime(iArr3[12], iArr3[13], iArr3[14])).append("/");
        int[] iArr4 = this.mCanBusInfo;
        bundle.putString("_288_play_time1", sbAppend2.append(resolveTime(iArr4[9], iArr4[10], iArr4[11])).toString());
        this.mIntent1.putExtras(bundle);
        this.mContext.sendBroadcast(this.mIntent1);
    }

    private void sendDvdBoardCast() {
        Intent intent = new Intent();
        this.mIntent2 = intent;
        intent.setAction("com.hzbhd.canbus.car._288.MsgMgr.dvd");
        Bundle bundle = new Bundle();
        bundle.putString("_288_current_disc", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4) + "");
        bundle.putString("_288_play_mode2", setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4)));
        bundle.putString("_288_work_status2", setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 4, 4)));
        bundle.putString("_288_run_status2", this._0x61runningStatus);
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfo;
        StringBuilder sbAppend = sb.append(setByteHighLow(iArr[9], iArr[8])).append("/");
        int[] iArr2 = this.mCanBusInfo;
        bundle.putString("_288_track2", sbAppend.append(setByteHighLow(iArr2[7], iArr2[6])).toString());
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfo;
        StringBuilder sbAppend2 = sb2.append(resolveTime(iArr3[13], iArr3[14], iArr3[15])).append("/");
        int[] iArr4 = this.mCanBusInfo;
        bundle.putString("_288_play_time2", sbAppend2.append(resolveTime(iArr4[10], iArr4[11], iArr4[12])).toString());
        this.mIntent2.putExtras(bundle);
        this.mContext.sendBroadcast(this.mIntent2);
    }

    private void setSwc() {
        int i = this.mCanBusInfo[2];
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
        if (i == 19) {
            realKeyClick(45);
            return;
        }
        if (i != 20) {
            switch (i) {
                case 7:
                    realKeyClick(2);
                    break;
                case 8:
                    realKeyClick(HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    realKeyClick(14);
                    break;
                case 10:
                    realKeyClick(15);
                    break;
            }
            return;
        }
        realKeyClick(46);
    }

    private void setAir() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfo[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfo[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfo[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfo[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfo[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfo[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfo[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfo[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfo[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfo[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfo[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfo[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 0, 4);
        GeneralAirData.front_left_temperature = setLeftRightTemp(this.mCanBusInfo[4]);
        GeneralAirData.front_right_temperature = setLeftRightTemp(this.mCanBusInfo[5]);
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfo[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfo[6]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfo[6]);
        this.outDoorTemp = this.mCanBusInfo[7];
        if (isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "_288_out_door_temp", this.outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem(this.outDoorTemp));
        } else if (getCurrentEachCanId() == 64) {
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfo[3])) {
                updateAirActivity(this.mContext, 1001);
            }
        } else if (isOnlyAirData1Bit4Change()) {
            SharePreUtil.setBoolValue(this.mContext, "_288_0x28_data1_bit4", DataHandleUtils.getBoolBit4(this.mCanBusInfo[3]));
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setAmplifier() {
        GeneralAmplifierData.frontRear = 7 - resolveAmpData(2, 4);
        GeneralAmplifierData.leftRight = resolveAmpData(2, 0) - 7;
        GeneralAmplifierData.bandBass = resolveAmpData(3, 4) - 2;
        GeneralAmplifierData.bandTreble = resolveAmpData(3, 0) - 2;
        GeneralAmplifierData.bandMiddle = resolveAmpData(4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfo[5];
        updateAmplifierActivity(null);
        saveAmplifierData(this.mContext, this.mCanId);
        SharePreUtil.setStringValue(this.mContext, "_288_0x31", Base64.encodeToString(this.mCanBusInfoByte, 0));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4))));
        arrayList.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[6], 0, 1) == 1 ? "_288_0x31_setting7" : "_288_0x31_setting8"));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[6], 4, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateAmplifier() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(SharePreUtil.getIntValue(this.mContext, "_288_amplifier_on_off", 0))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(SharePreUtil.getIntValue(this.mContext, "_288_amplifier_mute", 0))));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(SharePreUtil.getIntValue(this.mContext, "_288_amplifier_round", 0))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean is0xD1DataChange() {
        if (Arrays.equals(this.m0xD1Data, this.mCanBusInfo)) {
            return false;
        }
        int[] iArr = this.mCanBusInfo;
        this.m0xD1Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDiscStatus() {
        this.mCurrentClick = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4);
        this.mCurrentStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfo[5]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfo[5]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfo[5]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfo[5]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfo[5]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfo[5]);
        int i = this.mCurrentClick;
        this.isOriginalCd = (i == 1 && !boolBit0) || (i == 2 && !boolBit1) || ((i == 3 && !boolBit2) || ((i == 4 && !boolBit3) || ((i == 5 && !boolBit4) || (i == 6 && !boolBit5))));
        String strDiscStatus = discStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfo[2]));
        String strDiscStatus2 = discStatus(DataHandleUtils.getBoolBit1(this.mCanBusInfo[2]));
        String strDiscStatus3 = discStatus(DataHandleUtils.getBoolBit2(this.mCanBusInfo[2]));
        String strDiscStatus4 = discStatus(DataHandleUtils.getBoolBit3(this.mCanBusInfo[2]));
        String strDiscStatus5 = discStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfo[2]));
        String strDiscStatus6 = discStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfo[2]));
        String strDiscInfo = discInfo(DataHandleUtils.getBoolBit0(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit0(this.mCanBusInfo[5]));
        discInfo(DataHandleUtils.getBoolBit1(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit1(this.mCanBusInfo[5]));
        discInfo(DataHandleUtils.getBoolBit2(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit2(this.mCanBusInfo[5]));
        discInfo(DataHandleUtils.getBoolBit3(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit3(this.mCanBusInfo[5]));
        discInfo(DataHandleUtils.getBoolBit4(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit4(this.mCanBusInfo[5]));
        discInfo(DataHandleUtils.getBoolBit5(this.mCanBusInfo[2]), DataHandleUtils.getBoolBit5(this.mCanBusInfo[5]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SongListEntity("1. " + strDiscStatus));
        arrayList.add(new SongListEntity("2. " + strDiscStatus2));
        arrayList.add(new SongListEntity("3. " + strDiscStatus3));
        arrayList.add(new SongListEntity("4. " + strDiscStatus4));
        arrayList.add(new SongListEntity("5. " + strDiscStatus5));
        arrayList.add(new SongListEntity("6. " + strDiscStatus6));
        GeneralOriginalCarDeviceData.runningState = getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4));
        this._0x61runningStatus = GeneralOriginalCarDeviceData.runningState;
        GeneralOriginalCarDeviceData.isSongListChange = !this.isCdSongList.equals(strDiscStatus + "              " + strDiscInfo);
        this.isCdSongList.equals(strDiscStatus + "              " + strDiscInfo);
        GeneralOriginalCarDeviceData.songList = arrayList;
        updateOriginalCarDeviceActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 0, 4) + ""));
        this.mList1.clear();
        this.mList1.addAll(arrayList2);
        GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1);
        updateOriginalCarDeviceActivity(null);
        if (this.isOriginalCd || this.mCurrentClick == 0) {
            return;
        }
        Log.d("cwh", "dvd界面发广播");
        sendDvdBoardCast();
    }

    private void setDiscInfo() throws Resources.NotFoundException {
        GeneralOriginalCarDeviceData.cdStatus = getDeviceWorkModle(this.mCanBusInfo[2]);
        if (!GeneralOriginalCarDeviceData.cdStatus.equals(this.mDeviceStatusNow)) {
            this.mDeviceStatusNow = GeneralOriginalCarDeviceData.cdStatus;
            cleanDevice();
            cleanSongList();
            enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        }
        if (isDeviceStatusSame(DEVICE_WORK_MODE_CD)) {
            if (this.isOriginalCd || this.mCurrentClick == 0) {
                setOriginalCdPage();
                setCdInfo();
                Log.d("cwh", "0x62 cd");
            } else {
                if (isOriginalCdChange()) {
                    openAuxIn();
                }
                sendDvdBoardCast();
                Log.d("cwh", "0x62 dvd");
            }
        }
        if (isDeviceStatusSame(DEVICE_WORK_MODE_RADIO)) {
            setOriginalRadioPage();
            setRadioInfo();
        }
        if (DEVICE_WORK_MODE_RADIO.equals(GeneralOriginalCarDeviceData.cdStatus) || DEVICE_WORK_MODE_CD.equals(GeneralOriginalCarDeviceData.cdStatus)) {
            return;
        }
        setOriginalOtherPage();
    }

    private void setCdInfo() {
        ArrayList arrayList = new ArrayList();
        this.currentDisc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4);
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4) + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 4, 4))));
        int[] iArr = this.mCanBusInfo;
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, setByteHighLow(iArr[7], iArr[6])));
        int[] iArr2 = this.mCanBusInfo;
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, setByteHighLow(iArr2[9], iArr2[8])));
        int[] iArr3 = this.mCanBusInfo;
        GeneralOriginalCarDeviceData.endTime = resolveTime(iArr3[10], iArr3[11], iArr3[12]);
        int[] iArr4 = this.mCanBusInfo;
        GeneralOriginalCarDeviceData.startTime = resolveTime(iArr4[13], iArr4[14], iArr4[15]);
        int[] iArr5 = this.mCanBusInfo;
        int i = iArr5[10];
        int i2 = iArr5[11];
        int i3 = iArr5[12];
        if ((i * 3600) + (i2 * 60) + i3 != 0) {
            GeneralOriginalCarDeviceData.progress = ((((iArr5[13] * 3600) + (iArr5[14] * 60)) + iArr5[15]) * 100) / (((i * 3600) + (i2 * 60)) + i3);
        }
        this.mList2.clear();
        this.mList2.addAll(arrayList);
        GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
        updateOriginalCarDeviceActivity(null);
    }

    private void setRadioInfo() throws Resources.NotFoundException {
        String string;
        if (this.mCanBusInfo[3] == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, setBand(this.mCanBusInfo[4])));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, setState(DataHandleUtils.getBoolBit7(this.mCanBusInfo[5]))));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, setPreset(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4))));
            int[] iArr = this.mCanBusInfo;
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, setFreq(iArr[6], iArr[7])));
            this.currentPtore = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[5], 0, 4);
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfo[5])) {
                string = this.mContext.getResources().getString(R.string.ford_original_status2);
            } else {
                string = this.mContext.getResources().getString(R.string.ford_original_status1);
            }
            GeneralOriginalCarDeviceData.runningState = string;
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
            return;
        }
        setPresetData();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setPresetData() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfo[4];
        int i2 = 2;
        if (i != 0 && i != 1 && i != 2) {
            switch (i) {
                case 16:
                case 17:
                case 18:
                    while (i2 < 8) {
                        int[] iArr = this.mCanBusInfo;
                        int i3 = i2 * 2;
                        arrayList.add(new SongListEntity((i2 - 1) + ". " + ((iArr[i3 + 2] * 256) + iArr[i3 + 1]) + "KHz"));
                        i2++;
                    }
                    break;
            }
        } else {
            while (i2 < 8) {
                int[] iArr2 = this.mCanBusInfo;
                int i4 = i2 * 2;
                arrayList.add(new SongListEntity((i2 - 1) + ". " + (((iArr2[i4 + 2] * 256) + iArr2[i4 + 1]) / 100.0f) + "MHz"));
                i2++;
            }
        }
        GeneralOriginalCarDeviceData.songList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setRearDiscInfo() {
        turnToRearCdPage();
        GeneralOriginalCarDeviceData.cdStatus = DEVICE_WORK_MODE_REAR_DISC;
        GeneralOriginalCarDeviceData.runningState = getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 4, 4));
        ArrayList arrayList = new ArrayList();
        this.currentDisc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4);
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 0, 4))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[4], 4, 4))));
        int[] iArr = this.mCanBusInfo;
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, setByteHighLow(iArr[6], iArr[5])));
        int[] iArr2 = this.mCanBusInfo;
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, setByteHighLow(iArr2[8], iArr2[7])));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[3], 0, 4) + ""));
        int[] iArr3 = this.mCanBusInfo;
        GeneralOriginalCarDeviceData.endTime = resolveTime(iArr3[9], iArr3[10], iArr3[11]);
        int[] iArr4 = this.mCanBusInfo;
        GeneralOriginalCarDeviceData.startTime = resolveTime(iArr4[12], iArr4[13], iArr4[14]);
        int[] iArr5 = this.mCanBusInfo;
        int i = iArr5[9];
        int i2 = iArr5[10];
        int i3 = iArr5[11];
        if ((i * 3600) + (i2 * 60) + i3 != 0) {
            GeneralOriginalCarDeviceData.progress = ((((iArr5[12] * 3600) + (iArr5[13] * 60)) + iArr5[14]) * 100) / (((i * 3600) + (i2 * 60)) + i3);
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    void turnToRearCdPage() {
        cleanDevice();
        cleanSongList();
        enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux", OriginalBtnAction.RPT, OriginalBtnAction.RDM, OriginalBtnAction.SCAN}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", "down", "right", OriginalBtnAction.NEXT_DISC}, false, true);
    }

    void updateSettingData() {
        ArrayList arrayList = new ArrayList();
        String stringValue = SharePreUtil.getStringValue(this.mContext, "_288_0x31", null);
        if (!TextUtils.isEmpty(stringValue)) {
            byte[] bArrDecode = Base64.decode(stringValue, 0);
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[4], 0, 4))));
            arrayList.add(new SettingUpdateEntity(0, 1, DataHandleUtils.getIntFromByteWithBit(bArrDecode[6], 0, 1) == 1 ? "_288_0x31_setting7" : "_288_0x31_setting8"));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[6], 4, 2))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfo;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private String setLeftRightTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (i < 1 || i > 29) {
            return "--";
        }
        return ((i + 35) * 0.5f) + (DataHandleUtils.getBoolBit0(this.mCanBusInfo[6]) ? "°F" : "℃");
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "_288_out_door_temp", 0.0f) != ((float) this.outDoorTemp);
    }

    private boolean isOnlyAirData1Bit4Change() {
        return SharePreUtil.getBoolValue(this.mContext, "_288_0x28_data1_bit4", false) != DataHandleUtils.getBoolBit4(this.mCanBusInfo[3]);
    }

    private String resolveOutDoorTem(int i) {
        return ((float) ((i * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
    }

    private int resolveAmpData(int i, int i2) {
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfo[i], i2, 4);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private void initAmplifierData(Context context) {
        int intValue = 0;
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
            intValue = SharePreUtil.getIntValue(context, "share_288_language", 0);
        }
        TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask(intValue, timerUtil) { // from class: com.hzbhd.canbus.car._288.MsgMgr.1
            final Iterator<byte[]> iterator;
            final /* synthetic */ int val$finalLanguage;
            final /* synthetic */ TimerUtil val$util;

            {
                this.val$finalLanguage = intValue;
                this.val$util = timerUtil;
                this.iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1, 1}, new byte[]{22, -127, 1, 1}, new byte[]{22, -125, 36, (byte) intValue}, new byte[]{22, -124, 8, 1}, new byte[]{22, -124, 1, (byte) (7 - GeneralAmplifierData.frontRear)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}}).iterator();
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    this.val$util.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("MyApplication");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._288.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 2) {
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
    }

    private void sendNormalMessage(Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private boolean isDeviceStatusSame(String str) {
        return isCurrentStatus(str);
    }

    private void cleanDevice() {
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.am_st = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.scan = false;
        GeneralOriginalCarDeviceData.disc_scan = false;
        GeneralOriginalCarDeviceData.rpt = false;
        GeneralOriginalCarDeviceData.rpt_disc = false;
        GeneralOriginalCarDeviceData.rdm_off = false;
        GeneralOriginalCarDeviceData.rdm_disc = false;
        GeneralOriginalCarDeviceData.startTime = "     ";
        GeneralOriginalCarDeviceData.endTime = "     ";
        GeneralOriginalCarDeviceData.progress = 0;
        updateOriginalCarDeviceActivity(null);
    }

    private boolean isCurrentStatus(String str) {
        return str.equals(GeneralOriginalCarDeviceData.cdStatus);
    }

    private void cleanSongList() {
        if (GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
        }
    }

    private void setOriginalRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux"}, new String[]{"up", "left", OriginalBtnAction.RADIO_SCAN, "right", "down"}, true, false);
    }

    private void setOriginalCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux", OriginalBtnAction.RPT, OriginalBtnAction.RDM, OriginalBtnAction.SCAN}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", "down", "right", OriginalBtnAction.NEXT_DISC}, true, true);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, String[] strArr2, boolean z, boolean z2) {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        if (originalCarDevicePageUiSet == null) {
            return;
        }
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        originalCarDevicePageUiSet.setRowBottomBtnAction(strArr2);
        originalCarDevicePageUiSet.setHaveSongList(z);
        originalCarDevicePageUiSet.setHavePlayTimeSeekBar(z2);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setOriginalOtherPage() {
        GeneralOriginalCarDeviceData.mList = null;
        changeOriginalDevicePage(new ArrayList(), new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux"}, new String[0], false, false);
    }

    private String getChangerStatus(int i) {
        return CommUtil.getStrByResId(this.mContext, "_288_divice_status_" + i);
    }

    private String setPlayStatus(int i) {
        Resources resources;
        int i2;
        if (i == 1) {
            resources = this.mContext.getResources();
            i2 = R.string._288_divice_cd_mode2;
        } else {
            resources = this.mContext.getResources();
            i2 = R.string._288_divice_cd_mode1;
        }
        return resources.getString(i2);
    }

    private String setCycleStatus(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._288_divice_cd_mode3);
        }
        if (i != 1) {
            return i != 2 ? "" : this.mContext.getResources().getString(R.string._288_divice_cd_mode5);
        }
        return this.mContext.getResources().getString(R.string._288_divice_cd_mode4);
    }

    private String setByteHighLow(int i, int i2) {
        int i3 = (i * 256) + i2;
        return i3 == 255 ? "--" : i3 + "";
    }

    private String resolveTime(int i, int i2, int i3) {
        return (i == 255 || i2 == 255 || i3 == 255) ? "--" : this.mDecimalFormat.format(i) + ":" + this.mDecimalFormat.format(i2) + ":" + this.mDecimalFormat.format(i3);
    }

    private String setState(boolean z) {
        Resources resources;
        int i;
        if (z) {
            resources = this.mContext.getResources();
            i = R.string.stereo;
        } else {
            resources = this.mContext.getResources();
            i = R.string._288_divice_radio_mode;
        }
        return resources.getString(i);
    }

    private String setPreset(int i) {
        return i == 0 ? "" : "P" + i;
    }

    private String setFreq(int i, int i2) {
        if (this.mCanBusInfo[4] == 16) {
            return (i + (i2 * 256)) + "KHz";
        }
        return ((i + (i2 * 256)) / 100.0f) + "MHz";
    }

    private boolean is0x61Repeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.m0x61InfoCopy)) {
            return true;
        }
        this.m0x61InfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean is0x62Repeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.m0x62InfoCopy)) {
            return true;
        }
        this.m0x62InfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!m0x62First) {
            return false;
        }
        m0x62First = false;
        return true;
    }

    private boolean is0x65Repeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.m0x65InfoCopy)) {
            return true;
        }
        this.m0x65InfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!m0x65First) {
            return false;
        }
        m0x65First = false;
        return true;
    }

    private static <T> List<T> mergeLists(List<T>... listArr) {
        List<T> list;
        try {
            list = (List) listArr[0].getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (List<T> list2 : listArr) {
            list.addAll(list2);
        }
        return list;
    }

    private void openAuxIn() {
        ComponentName componentName = new ComponentName(HzbhdComponentName.MISC, "com.hzbhd.misc.auxin.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    private boolean isOriginalDvdChange() {
        int i = this.mCanBusInfo[3];
        if (i == this.mLastDataDvd) {
            return false;
        }
        this.mLastDataDvd = i;
        if (!isOriginalDvdFirst) {
            return true;
        }
        isOriginalDvdFirst = false;
        return false;
    }

    private boolean isOriginalCdChange() {
        int i = this.mCurrentStatus;
        if (i == this.mLastDataCd) {
            return false;
        }
        this.mLastDataCd = i;
        if (!isOriginalCdFirst) {
            return true;
        }
        isOriginalCdFirst = false;
        return false;
    }

    private String getStringRes(int i) {
        return this.mContext.getResources().getString(i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }
}
