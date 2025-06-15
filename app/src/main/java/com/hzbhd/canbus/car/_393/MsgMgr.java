package com.hzbhd.canbus.car._393;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.net.SyslogAppender;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    String haveDisc1 = "○Have";
    String haveDisc2 = "○Have";
    String haveDisc3 = "○Have";
    String haveDisc4 = "○Have";
    String haveDisc5 = "○Have";
    String haveDisc6 = "○Have";
    String Disc1Type = "□CD Disc";
    String Disc2Type = "□CD Disc";
    String Disc3Type = "□CD Disc";
    String Disc4Type = "□CD Disc";
    String Disc5Type = "□CD Disc";
    String Disc6Type = "□CD Disc";
    String cdcTextInfo = "Text information is empty";
    int cdcTag = -1;
    int[] saveCdcIntArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String textContent = "Text information is empty";
    int radioInfoTag = -1;
    int[] save0x84Data = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private String getCDCState(int i) {
        if (i == 15) {
            return "Single CD";
        }
        switch (i) {
            case 1:
                return "Disc 1";
            case 2:
                return "Disc 2";
            case 3:
                return "Disc 3";
            case 4:
                return "Disc 4";
            case 5:
                return "Disc 5";
            case 6:
                return "Disc 6";
            default:
                return "NO MEDIA";
        }
    }

    private String getCDType(boolean z) {
        return z ? "☑Rom Disc" : "☑CD Disc";
    }

    private String getChangerState(int i) {
        if (i == 0) {
            return "reading disc";
        }
        if (i == 2) {
            return "Play";
        }
        if (i == 6) {
            return "Stop";
        }
        if (i == 12) {
            return "Eject";
        }
        if (i == 255) {
            return "Invalid";
        }
        switch (i) {
            case 17:
                return "Loading disc ";
            case 18:
                return "Insert disc";
            case 19:
                return "wait";
            case 20:
                return "Busy";
            case 21:
                return "Select disc to load";
            case 22:
                return "Select disc to eject";
            case 23:
                return "Disc Error";
            default:
                return "NO STATE";
        }
    }

    private String getFloderState(int i) {
        return i != 1 ? i != 2 ? "OFF" : "RDM FLODER" : "RPT FLODER";
    }

    private String getHaveState(boolean z) {
        return z ? "●Have" : "○Have";
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private String getRadioState(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 6 ? i != 7 ? "NO MEDIA" : "AMAP" : "FMAP" : "FM2" : "FM1" : "AM" : "FM";
    }

    private String getRandomState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "OFF" : "All Disc Random" : "Disc Random" : "Random";
    }

    private String getRepeatState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "OFF" : "All Disc Repeat" : "Disc Repeat" : "Track Repeat";
    }

    private String getScanState(int i) {
        return i != 1 ? i != 2 ? "OFF" : "Disc Scan" : "Scan";
    }

    private String getSwitchState(boolean z) {
        return z ? "ON" : "OFF";
    }

    private Object gteMediaType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? " " : "AUX" : "CD" : "Radio";
    }

    private Object gteShowType(int i) {
        switch (i) {
            case 1:
                return "Volume";
            case 2:
                return "Balance";
            case 3:
                return "Fade";
            case 4:
                return "Bass";
            case 5:
                return "Treble";
            case 6:
                return "Beep";
            default:
                return " ";
        }
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
        getUiMgr(context).send0x24CarSelect(10, 4);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setSWC0x11();
            return;
        }
        if (i == 38) {
            set0x26CarInfo();
            return;
        }
        if (i == 49) {
            setAir0x21();
            return;
        }
        if (i == 132) {
            setRadio0x84(byteArrayToIntArray);
            return;
        }
        if (i == 134) {
            set0x86CDCInfo(byteArrayToIntArray);
            return;
        }
        if (i != 240) {
            switch (i) {
                case MpegConstantsDef.MPEG_PASSWORD_CFM /* 166 */:
                    setAmp0xA6();
                    break;
                case 167:
                    setRadioTextInfo0xA7();
                    break;
                case SyslogAppender.LOG_LOCAL5 /* 168 */:
                    set0xA8TextInfo();
                    break;
                case 169:
                    set0xA9MediaInfo();
                    break;
            }
            return;
        }
        set0xF0VersionInfo();
    }

    private void set0xF0VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x26CarInfo() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 4 && iArr[3] == 10) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_car"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_car", "_393_car1"), 1));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0xA9MediaInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_media"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_media", "_393_media1"), getSwitchState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_media"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_media", "_393_media2"), gteMediaType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3))).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAmp0xA6() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 5;
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 5;
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
        updateAmplifierActivity(null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_apm"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_apm", "_393_apm1"), getSwitchState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_393_apm"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_393_apm", "_393_apm2"), gteShowType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 3))).setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 3) != 0).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAir0x21() {
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = false;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setSWC0x11() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[5]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[5]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[5]);
            return;
        }
        if (i == 8) {
            realKeyLongClick1(this.mContext, 45, iArr[5]);
        } else if (i == 9) {
            realKeyLongClick1(this.mContext, 46, iArr[5]);
        } else {
            if (i != 11) {
                return;
            }
            realKeyLongClick1(this.mContext, 2, iArr[5]);
        }
    }

    private void set0xA8TextInfo() {
        if (this.cdcTag == -1) {
            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
            this.cdcTag = 1;
        }
        String str = "%";
        for (int i = 2; i < this.mCanBusInfoInt.length; i++) {
            if (i != 2) {
                str = str + "%";
            }
            str = str + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i]));
        }
        this.cdcTextInfo = getUTF8Result(str);
        set0x86CDCInfo(this.saveCdcIntArray);
    }

    private void set0x86CDCInfo(int[] iArr) {
        if (this.cdcTag == -1) {
            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
            this.cdcTag = 1;
        }
        this.saveCdcIntArray = iArr;
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(HotKeyConstant.K_SLEEP);
        GeneralOriginalCarDeviceData.cdStatus = getCDCState(DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 4));
        GeneralOriginalCarDeviceData.runningState = getChangerState(iArr[9]);
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = get0x86List(iArr);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setRadioTextInfo0xA7() {
        if (this.radioInfoTag == -1) {
            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
            this.radioInfoTag = 1;
        }
        String str = "%";
        for (int i = 2; i < this.mCanBusInfoInt.length; i++) {
            if (i != 2) {
                str = str + "%";
            }
            str = str + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i]));
        }
        this.textContent = getUTF8Result(str);
        setRadio0x84(this.save0x84Data);
    }

    private void setRadio0x84(int[] iArr) {
        if (this.radioInfoTag == -1) {
            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
            this.radioInfoTag = 1;
        }
        for (int i = 0; i < iArr.length; i++) {
            this.save0x84Data[i] = iArr[i];
        }
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(132);
        GeneralOriginalCarDeviceData.cdStatus = getRadioState(iArr[2]);
        GeneralOriginalCarDeviceData.runningState = getRunningState();
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = get0x84List(iArr);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._393.MsgMgr$1] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._393.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    private static class OriginalDeviceData {
        private final String[] bottomBtns;
        private final List<OriginalCarDevicePageUiSet.Item> list;

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
            this(list, null);
        }

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
            this.list = list;
            this.bottomBtns = strArr;
        }

        public List<OriginalCarDevicePageUiSet.Item> getItemList() {
            return this.list;
        }

        public String[] getBottomBtns() {
            return this.bottomBtns;
        }
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio1", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio2", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio3", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio4", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio5", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_radio6", null));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc1", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc2", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc3", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc4", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc5", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc6", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc7", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc8", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc9", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc10", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc11", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc12", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_393_disc13", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(0, new OriginalDeviceData(arrayList, new String[0]));
        this.mOriginalDeviceDataArray.put(132, new OriginalDeviceData(arrayList2, new String[0]));
        this.mOriginalDeviceDataArray.put(HotKeyConstant.K_SLEEP, new OriginalDeviceData(arrayList3, new String[0]));
    }

    private List<OriginalCarDeviceUpdateEntity> get0x86List(int[] iArr) {
        this.haveDisc1 = getHaveState(DataHandleUtils.getBoolBit0(iArr[3]));
        this.haveDisc2 = getHaveState(DataHandleUtils.getBoolBit1(iArr[3]));
        this.haveDisc3 = getHaveState(DataHandleUtils.getBoolBit2(iArr[3]));
        this.haveDisc4 = getHaveState(DataHandleUtils.getBoolBit3(iArr[3]));
        this.haveDisc5 = getHaveState(DataHandleUtils.getBoolBit4(iArr[3]));
        this.haveDisc6 = getHaveState(DataHandleUtils.getBoolBit5(iArr[3]));
        this.Disc6Type = getCDType(DataHandleUtils.getBoolBit5(iArr[4]));
        this.Disc5Type = getCDType(DataHandleUtils.getBoolBit4(iArr[4]));
        this.Disc4Type = getCDType(DataHandleUtils.getBoolBit3(iArr[4]));
        this.Disc3Type = getCDType(DataHandleUtils.getBoolBit2(iArr[4]));
        this.Disc2Type = getCDType(DataHandleUtils.getBoolBit1(iArr[4]));
        this.Disc1Type = getCDType(DataHandleUtils.getBoolBit0(iArr[4]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.haveDisc1 + "    " + this.Disc1Type));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.haveDisc2 + "    " + this.Disc2Type));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.haveDisc3 + "    " + this.Disc3Type));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.haveDisc4 + "    " + this.Disc4Type));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.haveDisc5 + "    " + this.Disc5Type));
        arrayList.add(new OriginalCarDeviceUpdateEntity(5, this.haveDisc6 + "    " + this.Disc6Type));
        if (iArr[5] == 255) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, "Invalid"));
        } else {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, iArr[5] + ""));
        }
        if (iArr[6] == 255 || iArr[7] == 255) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(7, "Invalid"));
        } else {
            arrayList.add(new OriginalCarDeviceUpdateEntity(7, iArr[6] + ":" + iArr[7]));
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(8, getScanState(DataHandleUtils.getIntFromByteWithBit(iArr[8], 6, 2))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(9, getRepeatState(DataHandleUtils.getIntFromByteWithBit(iArr[8], 4, 2))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(10, getRandomState(DataHandleUtils.getIntFromByteWithBit(iArr[8], 2, 2))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(11, getFloderState(DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 2))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(12, getCdcText(DataHandleUtils.getBoolBit7(iArr[3]))));
        return arrayList;
    }

    private String getCdcText(boolean z) {
        return z ? this.cdcTextInfo : "OFF";
    }

    private List<OriginalCarDeviceUpdateEntity> get0x84List(int[] iArr) {
        String string;
        if (iArr[5] == 0) {
            string = this.mContext.getString(R.string._393_radio_content1);
        } else {
            string = iArr[5] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, string));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, getSwitchState(DataHandleUtils.getBoolBit7(iArr[7]))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, getSwitchState(DataHandleUtils.getBoolBit6(iArr[7]))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, getSwitchState(DataHandleUtils.getBoolBit5(iArr[7]))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, getSwitchState(DataHandleUtils.getBoolBit4(iArr[7]))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(5, getTextState(DataHandleUtils.getBoolBit3(iArr[7]))));
        return arrayList;
    }

    private String getTextState(boolean z) {
        return z ? this.textContent : "OFF";
    }

    private String getRunningState() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1 || i == 7) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            return sb.append(getMsbLsbResult(iArr[4], iArr[3])).append("KHz").toString();
        }
        StringBuilder sb2 = new StringBuilder();
        DecimalFormat decimalFormat = this.df_2Decimal;
        int[] iArr2 = this.mCanBusInfoInt;
        return sb2.append(decimalFormat.format(getMsbLsbResult(iArr2[4], iArr2[3]) * 0.01d)).append("MHz").toString();
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
        return i == 254 ? "LO" : i == 255 ? "HI" : this.df_2Decimal.format(i * 0.5d) + getTempUnitC(this.mContext);
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
