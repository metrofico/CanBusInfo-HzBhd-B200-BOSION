package com.hzbhd.canbus.car._24;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    private static final String DEVICE_STATUS_AUX = "AUX";
    private static final String DEVICE_STATUS_CD = "CD";
    private static final String DEVICE_STATUS_POWER_OFF = "Power Off";
    private static final String DEVICE_STATUS_POWER_ON = "Power On";
    private static final String DEVICE_STATUS_RADIO = "RADIO";
    private static final String DEVICE_STATUS_TV = "TV";
    private int[] m0x10DataNow;
    private int[] mAmplifierData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private String mCdMessage;
    private Context mContext;
    private int mCurrentDisc;
    private String mDeviceStatusNow = "";
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private String mRadioMessage;
    private String mRunningState;
    private boolean mShowCdMessage;
    private boolean mShowRadioMessage;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private UiMgr mUiMgr;

    private String getAmpType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "Beep" : "Balance" : "Fade" : "Treble" : "Bass";
    }

    private String getCdWorkModle(int i) {
        switch (i) {
            case 0:
                return "Play";
            case 1:
                return "Reading disc";
            case 2:
                return "Loading disc";
            case 3:
                return "Insert disc";
            case 4:
                return "Busy";
            case 5:
                return "Ejecting disc";
            case 6:
                return "Select disc to load";
            case 7:
                return "Select disc to eject";
            case 8:
                return "Disc error";
            default:
                return "";
        }
    }

    private String getDeviceWorkModle(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : DEVICE_STATUS_TV : DEVICE_STATUS_AUX : DEVICE_STATUS_CD : DEVICE_STATUS_RADIO;
    }

    private String getPowerStatus(boolean z) {
        return z ? DEVICE_STATUS_POWER_ON : DEVICE_STATUS_POWER_OFF;
    }

    private String getRadioBand(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "FMAP" : "FM2" : "FM1" : "AMAP" : "AM";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        try {
            int i = byteArrayToIntArray[1];
            if (i == 16) {
                setOriginalCarWorkModle0x10();
            } else if (i != 17) {
                switch (i) {
                    case 2:
                        setAirData0x02();
                        break;
                    case 3:
                        setRadioStatus0x03();
                        break;
                    case 4:
                        setRadioInfo0x04();
                        break;
                    case 5:
                        setRadioMessage0x05();
                        break;
                    case 6:
                        setCdStatus0x06();
                        break;
                    case 7:
                        setCdInfo0x07();
                        break;
                    case 8:
                        setCdMessage0x08();
                        break;
                    case 9:
                        setAmplifierInfo0x09();
                        break;
                    case 10:
                        setVolumeInfo0x0A();
                        break;
                }
            } else {
                setWheelKey0x11();
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setAirData0x02() {
        if (isAirMsgRepeat(this.mCanBusInfoByte)) {
            return;
        }
        GeneralAirData.front_left_temperature = resolveTemperature(this.mCanBusInfoInt[2]);
        GeneralAirData.front_right_temperature = resolveTemperature(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 3);
        cleanAllBlow();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.rear = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5])) {
            GeneralAirData.in_out_cycle = false;
        } else if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5])) {
            GeneralAirData.in_out_cycle = true;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRadioStatus0x03() {
        if (isDeviceStatusConform(DEVICE_STATUS_RADIO)) {
            GeneralOriginalCarDeviceData.rds = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.auto_p = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mShowRadioMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            if (this.mShowRadioMessage) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.mRadioMessage));
            } else {
                arrayList.add(new OriginalCarDeviceUpdateEntity(3, ""));
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setRadioInfo0x04() {
        if (isDeviceStatusConform(DEVICE_STATUS_RADIO)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, getRadioBand(this.mCanBusInfoInt[2])));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "P" + this.mCanBusInfoInt[3]));
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, getRadioFreq(iArr[2], iArr[4], iArr[5])));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setRadioMessage0x05() {
        if (isDeviceStatusConform(DEVICE_STATUS_RADIO)) {
            ArrayList arrayList = new ArrayList();
            byte[] bArr = this.mCanBusInfoByte;
            this.mRadioMessage = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.mRadioMessage));
            GeneralOriginalCarDeviceData.mList = arrayList;
            if (this.mShowRadioMessage) {
                updateOriginalCarDeviceActivity(null);
            }
        }
    }

    private void setCdStatus0x06() {
        if (isDeviceStatusConform(DEVICE_STATUS_CD)) {
            GeneralOriginalCarDeviceData.folder = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.wma = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.mp3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mShowCdMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, getPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3))));
            if (this.mShowCdMessage) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.mCdMessage));
            } else {
                arrayList.add(new OriginalCarDeviceUpdateEntity(4, ""));
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new SongListEntity("Disc 1").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 1).setSelected(this.mCurrentDisc == 1));
            arrayList2.add(new SongListEntity("Disc 2").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) == 1).setSelected(this.mCurrentDisc == 2));
            arrayList2.add(new SongListEntity("Disc 3").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) == 1).setSelected(this.mCurrentDisc == 3));
            arrayList2.add(new SongListEntity("Disc 4").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1).setSelected(this.mCurrentDisc == 4));
            arrayList2.add(new SongListEntity("Disc 5").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) == 1).setSelected(this.mCurrentDisc == 5));
            arrayList2.add(new SongListEntity("Disc 6").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2) == 1).setSelected(this.mCurrentDisc == 6));
            GeneralOriginalCarDeviceData.songList = arrayList2;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCdInfo0x07() {
        if (isDeviceStatusConform(DEVICE_STATUS_CD)) {
            this.mCurrentDisc = this.mCanBusInfoInt[2];
            if (GeneralOriginalCarDeviceData.songList != null) {
                for (SongListEntity songListEntity : GeneralOriginalCarDeviceData.songList) {
                    songListEntity.setSelected(songListEntity.getTitle().substring(songListEntity.getTitle().length() - 1).equals(Integer.toString(this.mCurrentDisc)));
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "Disc " + this.mCurrentDisc));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, getBcdWithByte(this.mCanBusInfoInt[3])));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, getBcdWithByte(this.mCanBusInfoInt[4]) + ":" + getBcdWithByte(this.mCanBusInfoInt[5])));
            GeneralOriginalCarDeviceData.mList = arrayList;
            String cdWorkModle = getCdWorkModle(this.mCanBusInfoInt[6]);
            this.mRunningState = cdWorkModle;
            GeneralOriginalCarDeviceData.runningState = cdWorkModle;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCdMessage0x08() {
        if (isDeviceStatusConform(DEVICE_STATUS_CD)) {
            ArrayList arrayList = new ArrayList();
            byte[] bArr = this.mCanBusInfoByte;
            this.mCdMessage = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.mCdMessage));
            GeneralOriginalCarDeviceData.mList = arrayList;
            if (this.mShowCdMessage) {
                updateOriginalCarDeviceActivity(null);
            }
        }
    }

    private void setAmplifierInfo0x09() {
        if (isAmpDataChange()) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
            String str = getAmpType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3)) + "  " + getAmpValue(intFromByteWithBit, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 5));
            if (intFromByteWithBit != 0) {
                GeneralOriginalCarDeviceData.runningState = str;
                updateOriginalCarDeviceActivity(null);
                stopTimer();
                this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._24.MsgMgr.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        GeneralOriginalCarDeviceData.runningState = MsgMgr.this.mRunningState;
                        MsgMgr.this.updateOriginalCarDeviceActivity(null);
                        MsgMgr.this.stopTimer();
                    }
                };
                startTimer();
            }
        }
    }

    private void setVolumeInfo0x0A() {
        String bcdWithByte = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? getBcdWithByte(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6)) : "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(getOriginalCarDevicePageUiSet().getItems().size() - 1, bcdWithByte));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarWorkModle0x10() {
        GeneralOriginalCarDeviceData.cdStatus = getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        GeneralOriginalCarDeviceData.discStatus = getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3));
        updateOriginalCarDeviceActivity(null);
        if (DEVICE_STATUS_POWER_OFF.equals(GeneralOriginalCarDeviceData.cdStatus)) {
            cleanDevice();
            updateOriginalCarDeviceActivity(null);
            return;
        }
        if (isDeviceStatusSame(DEVICE_STATUS_RADIO)) {
            showOriginalDevice();
            setOriginalRadioPage();
        } else if (isDeviceStatusSame(DEVICE_STATUS_CD)) {
            showOriginalDevice();
            setOriginalCdPage();
        } else if (isDeviceStatusSame(DEVICE_STATUS_AUX)) {
            setOriginalAuxPage();
        }
    }

    private void setWheelKey0x11() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            realKeyClick4(45);
        } else {
            if (i != 2) {
                return;
            }
            realKeyClick4(46);
        }
    }

    private void realKeyClick4(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private String resolveTemperature(int i) {
        return (i < 1 || i > 29) ? "" : ((i + 35) / 2.0f) + getTempUnitC(this.mContext);
    }

    private String getPlayStatus(int i) {
        switch (i) {
            case 0:
                return this.mContext.getResources().getString(R.string.all_disc_rpt);
            case 1:
                return this.mContext.getResources().getString(R.string.one_disc_rpt);
            case 2:
                return this.mContext.getResources().getString(R.string.one_track_rpt);
            case 3:
                return this.mContext.getResources().getString(R.string.one_disc_rdm);
            case 4:
                return this.mContext.getResources().getString(R.string.all_disc_rdm);
            case 5:
                return this.mContext.getResources().getString(R.string.rdm_folder);
            case 6:
                return this.mContext.getResources().getString(R.string.rpt_folder);
            case 7:
                return this.mContext.getResources().getString(R.string.air_set_3_2);
            default:
                return "";
        }
    }

    private String getRadioFreq(int i, int i2, int i3) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i2, 7, 1);
        int i4 = ((i2 & (255 >> intFromByteWithBit)) * 256) + i3;
        if (i == 1 || i == 2) {
            return ((((DataHandleUtils.rangeNumber(i4, 1, 120) - 1) * (intFromByteWithBit + 9)) + 531) - intFromByteWithBit) + "KHz";
        }
        if (i != 3 && i != 4 && i != 5) {
            return "";
        }
        double dRangeNumber = ((DataHandleUtils.rangeNumber(i4, 1, 409) - 1) * 0.05f) + 87.5d;
        new BigDecimal(dRangeNumber).setScale(2, 4).floatValue();
        return new DecimalFormat("0.00").format(dRangeNumber) + "MHz";
    }

    private boolean isDeviceStatusSame(String str) {
        if (!str.equals(GeneralOriginalCarDeviceData.discStatus) || str.equals(this.mDeviceStatusNow)) {
            return false;
        }
        this.mDeviceStatusNow = str;
        return true;
    }

    private void cleanDevice() {
        this.mRunningState = "";
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.folder = false;
        GeneralOriginalCarDeviceData.wma = false;
        GeneralOriginalCarDeviceData.mp3 = false;
        GeneralOriginalCarDeviceData.scane = false;
        GeneralOriginalCarDeviceData.rds = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.auto_p = false;
        GeneralOriginalCarDeviceData.mList = null;
        Iterator<OriginalCarDevicePageUiSet.Item> it = getOriginalCarDevicePageUiSet().getItems().iterator();
        while (it.hasNext()) {
            it.next().setValue("");
        }
    }

    private void setOriginalRadioPage() {
        Log.i("ljq", "setOriginalRadioPage: ");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.RDS, OriginalBtnAction.SCANE, OriginalBtnAction.ST, OriginalBtnAction.AUTO_P}, false);
    }

    private void setOriginalCdPage() {
        Log.i("ljq", "setOriginalCdPage: ");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_artist", "_186_play_modle", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_play_time", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FOLDER, OriginalBtnAction.WMA, OriginalBtnAction.MP3, OriginalBtnAction.SCANE}, true);
    }

    private void setOriginalAuxPage() {
        Log.i("ljq", "setOriginalAuxPage: ");
        exitAuxIn2();
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._24.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                CommUtil.showToast(MsgMgr.this.mContext, CommUtil.getStrByResId(MsgMgr.this.mContext, "_272_toast_text18"));
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, null, false);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, boolean z) {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet();
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        originalCarDevicePageUiSet.setHaveSongList(z);
        cleanDevice();
        updateOriginalDeviceWithInit();
    }

    private void updateOriginalDeviceWithInit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void showOriginalDevice() {
        Intent intent = new Intent();
        intent.setComponent(Constant.OriginalDeviceActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mContext.startActivity(intent);
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet() {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr().getOriginalCarDevicePageUiSet(this.mContext);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private UiMgr getUiMgr() {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgr;
    }

    private boolean isDeviceStatusConform(String str) {
        if (DEVICE_STATUS_POWER_OFF.equals(GeneralOriginalCarDeviceData.cdStatus)) {
            return false;
        }
        return str.equals(GeneralOriginalCarDeviceData.discStatus);
    }

    private void startTimer() {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getAmpValue(int r7, int r8) {
        /*
            r6 = this;
            java.lang.String r0 = ""
            r1 = 1
            r2 = 5
            if (r7 != r2) goto L14
            if (r8 <= r1) goto L9
            r8 = r1
        L9:
            if (r8 != 0) goto Le
            java.lang.String r7 = "OFF"
            return r7
        Le:
            if (r8 != r1) goto L13
            java.lang.String r7 = "ON"
            return r7
        L13:
            return r0
        L14:
            r2 = 2
            if (r8 >= r2) goto L18
            r8 = r2
        L18:
            int r8 = r8 + (-7)
            java.lang.String r3 = "R"
            r4 = 4
            r5 = 3
            if (r8 >= 0) goto L31
            if (r7 == r1) goto L2e
            if (r7 == r2) goto L2e
            if (r7 == r5) goto L2c
            if (r7 == r4) goto L29
            goto L41
        L29:
            java.lang.String r0 = "L"
            goto L41
        L2c:
            r0 = r3
            goto L41
        L2e:
            java.lang.String r0 = "-"
            goto L41
        L31:
            if (r8 <= 0) goto L57
            if (r7 == r1) goto L3f
            if (r7 == r2) goto L3f
            if (r7 == r5) goto L3c
            if (r7 == r4) goto L2c
            goto L41
        L3c:
            java.lang.String r0 = "F"
            goto L41
        L3f:
            java.lang.String r0 = "+"
        L41:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r7 = r7.append(r0)
            int r8 = java.lang.Math.abs(r8)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            return r7
        L57:
            java.lang.String r7 = "0"
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._24.MsgMgr.getAmpValue(int, int):java.lang.String");
    }

    private String getBcdWithByte(int i) {
        return Integer.toString(i >> 4) + Integer.toString(i & 15);
    }

    private boolean is0x10DataChange() {
        if (Arrays.equals(this.m0x10DataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x10DataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isAmpDataChange() {
        if (Arrays.equals(this.mAmplifierData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mAmplifierData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
