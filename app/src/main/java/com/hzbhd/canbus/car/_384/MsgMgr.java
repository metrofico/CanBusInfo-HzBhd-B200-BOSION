package com.hzbhd.canbus.car._384;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    String disc_1_select = "";
    String disc_2_select = "";
    String disc_3_select = "";
    String disc_4_select = "";
    String disc_5_select = "";
    String disc_6_select = "";
    String disc_66_select = "";
    String disc_1_have = "";
    String disc_2_have = "";
    String disc_3_have = "";
    String disc_4_have = "";
    String disc_5_have = "";
    String disc_6_have = "";
    String disc_1_type = "";
    String disc_2_type = "";
    String disc_3_type = "";
    String disc_4_type = "";
    String disc_5_type = "";
    String disc_6_type = "";
    int nowKnowValue = -1;
    int cdc0x86Tag = -1;
    int media0x84Tag = -1;
    int media0x83Tag = -1;
    int doorInfo = 0;

    private boolean getCycleState(int i) {
        return i == 2;
    }

    private String getDeviceState(int i) {
        switch (i) {
            case 0:
                return "Radio";
            case 1:
                return "CD";
            case 2:
                return "AUX/NAVI";
            case 3:
                return "USB";
            case 4:
                return "TEL";
            case 5:
                return "IPOD";
            case 6:
                return "TV";
            default:
                return "NO MEDIA";
        }
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
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (str.equals("FM1")) {
            getUiMgr(this.mContext).sendMediaInfo0xF3(2);
            return;
        }
        if (str.equals("FM2")) {
            getUiMgr(this.mContext).sendMediaInfo0xF3(3);
            return;
        }
        if (str.equals("FM3")) {
            getUiMgr(this.mContext).sendMediaInfo0xF3(3);
        } else if (str.equals("AM1") || str.equals("AM2")) {
            getUiMgr(this.mContext).sendMediaInfo0xF3(1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).sendMediaInfo0xF3(4);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 19) {
            setCarInfo();
            return;
        }
        if (i == 26) {
            set0x1ADoor();
            set0x1ASpeed();
            set0x1ATrack();
            int[] iArr = this.mCanBusInfoInt;
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[5], iArr[6]));
            return;
        }
        if (i == 49) {
            updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
            return;
        }
        if (i == 134) {
            set0x86CDCInfo();
            return;
        }
        if (i == 240) {
            set0xF5VersionInfo();
            return;
        }
        if (i == 33) {
            set0x21PanelButton();
            return;
        }
        if (i != 34) {
            switch (i) {
                case 129:
                    set0x81Speed();
                    set0x81SWC();
                    set0x81Track();
                    break;
                case 130:
                    set0x82HVACInfo();
                    break;
                case 131:
                    set0x83MediaType();
                    break;
                case 132:
                    set0x84Tuner();
                    break;
            }
            return;
        }
        set0x22PanelKnob();
    }

    private void setCarInfo() {
        String str = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[13]) ? "mile" : "km";
        int i = this.mCanBusInfoInt[12];
        String str2 = i != 0 ? i != 1 ? "L/100KM" : "KM/L" : "MPG";
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item1");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(((iArr[2] << 8) + iArr[3]) / 10.0f).append(str2).toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item2");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append((iArr2[4] << 8) + iArr2[5]).append(str).toString()));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item3");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(((iArr3[6] << 8) + iArr3[7]) / 10.0f).append(str2).toString()));
        int drivingPageIndexes4 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
        int drivingItemIndexes4 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item4");
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, resolveRuntime((iArr4[8] << 8) + iArr4[9])));
        int drivingPageIndexes5 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
        int drivingItemIndexes5 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item5");
        StringBuilder sb4 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, drivingItemIndexes5, sb4.append((iArr5[10] << 8) + iArr5[11]).append("KM/h").toString()));
        int drivingPageIndexes6 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info");
        int drivingItemIndexes6 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_item6");
        StringBuilder sb5 = new StringBuilder();
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes6, drivingItemIndexes6, sb5.append(((iArr6[16] << 8) + iArr6[17]) / 10.0f).append(str).toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveRuntime(int i) {
        return (i / 60) + ":" + new DecimalFormat("00").format(i % 60);
    }

    private void set0x22PanelKnob() {
        if (this.nowKnowValue == -1) {
            this.nowKnowValue = this.mCanBusInfoInt[3];
        }
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            if (iArr[3] < this.nowKnowValue) {
                realKeyClick4(this.mContext, 8);
            } else {
                realKeyClick4(this.mContext, 7);
            }
        } else if (i == 2) {
            if (iArr[3] < this.nowKnowValue) {
                realKeyClick4(this.mContext, 45);
            } else {
                realKeyClick4(this.mContext, 46);
            }
        }
        this.nowKnowValue = this.mCanBusInfoInt[3];
    }

    private void set0xF5VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x86CDCInfo() {
        if (this.cdc0x86Tag == -1) {
            startDrivingDataActivity(this.mContext, 0);
            this.cdc0x86Tag = 1;
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 1) {
            this.disc_1_select = "☑select";
            this.disc_2_select = "□select";
            this.disc_3_select = "□select";
            this.disc_4_select = "□select";
            this.disc_5_select = "□select";
            this.disc_6_select = "□select";
            this.disc_66_select = "□select";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 2) {
            this.disc_1_select = "□select";
            this.disc_2_select = "☑select";
            this.disc_3_select = "□select";
            this.disc_4_select = "□select";
            this.disc_5_select = "□select";
            this.disc_6_select = "□select";
            this.disc_66_select = "□select";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 3) {
            this.disc_1_select = "□select";
            this.disc_2_select = "□select";
            this.disc_3_select = "☑select";
            this.disc_4_select = "□select";
            this.disc_5_select = "□select";
            this.disc_6_select = "□select";
            this.disc_66_select = "□select";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 4) {
            this.disc_1_select = "□select";
            this.disc_2_select = "□select";
            this.disc_3_select = "□select";
            this.disc_4_select = "☑select";
            this.disc_5_select = "□select";
            this.disc_6_select = "□select";
            this.disc_66_select = "□select";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 5) {
            this.disc_1_select = "□select";
            this.disc_2_select = "□select";
            this.disc_3_select = "□select";
            this.disc_4_select = "□select";
            this.disc_5_select = "☑select";
            this.disc_6_select = "□select";
            this.disc_66_select = "□select";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 6) {
            this.disc_1_select = "□select";
            this.disc_2_select = "□select";
            this.disc_3_select = "□select";
            this.disc_4_select = "□select";
            this.disc_5_select = "□select";
            this.disc_6_select = "☑select";
            this.disc_66_select = "□select";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) == 15) {
            this.disc_1_select = "□select";
            this.disc_2_select = "□select";
            this.disc_3_select = "□select";
            this.disc_4_select = "□select";
            this.disc_5_select = "□select";
            this.disc_6_select = "□select";
            this.disc_66_select = "☑select";
        } else {
            this.disc_1_select = "□select";
            this.disc_2_select = "□select";
            this.disc_3_select = "□select";
            this.disc_4_select = "□select";
            this.disc_5_select = "□select";
            this.disc_6_select = "□select";
            this.disc_66_select = "□select";
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            this.disc_6_have = "☑Have Disc";
        } else {
            this.disc_6_have = "□Have Disc";
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            this.disc_5_have = "☑Have Disc";
        } else {
            this.disc_5_have = "□Have Disc";
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            this.disc_4_have = "☑Have Disc";
        } else {
            this.disc_4_have = "□Have Disc";
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            this.disc_3_have = "☑Have Disc";
        } else {
            this.disc_3_have = "□Have Disc";
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            this.disc_2_have = "☑Have Disc";
        } else {
            this.disc_2_have = "□Have Disc";
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            this.disc_1_have = "☑Have Disc";
        } else {
            this.disc_1_have = "□Have Disc";
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4])) {
            this.disc_6_type = "☑Rom Disc";
        } else {
            this.disc_6_type = "☑CD Disc";
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4])) {
            this.disc_5_type = "☑Rom Disc";
        } else {
            this.disc_5_type = "☑CD Disc";
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4])) {
            this.disc_4_type = "☑Rom Disc";
        } else {
            this.disc_4_type = "☑CD Disc";
        }
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4])) {
            this.disc_3_type = "☑Rom Disc";
        } else {
            this.disc_3_type = "☑CD Disc";
        }
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4])) {
            this.disc_2_type = "☑Rom Disc";
        } else {
            this.disc_2_type = "☑CD Disc";
        }
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])) {
            this.disc_1_type = "☑Rom Disc";
        } else {
            this.disc_1_type = "☑CD Disc";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC1"), this.disc_1_select + "          " + this.disc_1_have + "          " + this.disc_1_type));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC2"), this.disc_2_select + "          " + this.disc_2_have + "          " + this.disc_2_type));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC3"), this.disc_3_select + "          " + this.disc_3_have + "          " + this.disc_3_type));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC4"), this.disc_4_select + "          " + this.disc_4_have + "          " + this.disc_4_type));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC5"), this.disc_5_select + "          " + this.disc_5_have + "          " + this.disc_5_type));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC6"), this.disc_6_select + "          " + this.disc_6_have + "          " + this.disc_6_type));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC66"), this.disc_66_select));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC7"), this.mCanBusInfoInt[5] + ""));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC8"), this.df_2Integer.format(this.mCanBusInfoInt[6]) + ":" + this.df_2Integer.format(this.mCanBusInfoInt[7])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC9"), getPlayingModel()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_CDC"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_CDC10"), getChangerState()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getChangerState() {
        switch (this.mCanBusInfoInt[9]) {
            case 0:
                return "Reading TOC";
            case 1:
                return "PAUSE";
            case 2:
                return "Play";
            case 3:
                return "Fast";
            case 4:
                return "User Search";
            case 5:
                return "Intemal search";
            case 6:
                return "Stop";
            case 7:
                return "Rom read";
            case 8:
                return "Rom search";
            case 9:
                return "Retrieving";
            case 10:
                return "Disc changing(user)";
            case 11:
                return "Disc changing(Intemal)";
            case 12:
                return "Eject";
            default:
                return "Invalid";
        }
    }

    private String getPlayingModel() {
        String str = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]) ? "【Scan ON】" : "";
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8])) {
            str = str + "【Disc Scan ON】";
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8])) {
            str = str + "【Repeat ON】";
        }
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8])) {
            str = str + "【Disc Repeat ON】";
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[8])) {
            str = str + "【Random ON】";
        }
        return DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]) ? str + "【Disc Random ON】" : str;
    }

    private void set0x84Tuner() {
        if (this.media0x84Tag == -1) {
            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
            this.media0x84Tag = 1;
        }
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(132);
        GeneralOriginalCarDeviceData.cdStatus = getRadiaState();
        GeneralOriginalCarDeviceData.runningState = getRadioRuningState();
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = get0x84List();
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x83MediaType() {
        if (this.media0x83Tag == -1) {
            startOtherActivity(this.mContext, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
            this.media0x83Tag = 1;
        }
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(131);
        GeneralOriginalCarDeviceData.cdStatus = getDeviceState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
        GeneralOriginalCarDeviceData.runningState = getRunningState();
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = get0x83List();
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x82HVACInfo() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !getCycleState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.swing = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[4]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4);
        if (intFromByteWithBit == 0) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
        } else if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        updateAirActivity(this.mContext, 1001);
    }

    private void set0x21PanelButton() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 1, iArr[3]);
            return;
        }
        if (i == 32) {
            realKeyLongClick1(this.mContext, 128, iArr[3]);
            return;
        }
        if (i == 36) {
            realKeyLongClick1(this.mContext, 53, iArr[3]);
            return;
        }
        if (i == 57) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_SLEEP, iArr[3]);
            return;
        }
        if (i == 66) {
            realKeyLongClick1(this.mContext, 4, iArr[3]);
        } else if (i == 47) {
            realKeyLongClick1(this.mContext, 151, iArr[3]);
        } else {
            if (i != 48) {
                return;
            }
            realKeyLongClick1(this.mContext, 68, iArr[3]);
        }
    }

    private void set0x1ATrack() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void set0x1ASpeed() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_driver_data");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_driver_data2");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.df_2Decimal;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getMsbLsbResult(iArr[5], iArr[6]) * 0.1d)).append("KM/H").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_driver_data");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_driver_data3");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[11], iArr2[12])).append("RPM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x1ADoor() {
        int i = this.mCanBusInfoInt[3];
        if (i == this.doorInfo) {
            return;
        }
        this.doorInfo = i;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void set0x81SWC() {
        int i = this.mCanBusInfoInt[6];
        if (i == 0) {
            realKeyClick4(this.mContext, 0);
            return;
        }
        if (i == 1) {
            realKeyClick4(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyClick4(this.mContext, 8);
            return;
        }
        if (i == 4) {
            realKeyClick4(this.mContext, HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 5) {
            realKeyClick4(this.mContext, 14);
            return;
        }
        if (i == 6) {
            realKeyClick4(this.mContext, 15);
            return;
        }
        if (i == 8) {
            realKeyClick4(this.mContext, 45);
            return;
        }
        if (i == 9) {
            realKeyClick4(this.mContext, 46);
        } else if (i == 12) {
            realKeyClick4(this.mContext, 2);
        } else {
            if (i != 32) {
                return;
            }
            realKeyClick4(this.mContext, 58);
        }
    }

    private void set0x81Speed() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_384_driver_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_384_driver_data1"), this.mCanBusInfoInt[4] + "KM/H"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x81Track() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7])) {
            GeneralParkData.trackAngle = (-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7)) / 5;
        } else {
            GeneralParkData.trackAngle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 7) / 5;
        }
        updateParkUi(null, this.mContext);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._384.MsgMgr$1] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._384.MsgMgr.1
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
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data1", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data2", null));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data3", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data4", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data5", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_384_Original_data6", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(0, new OriginalDeviceData(arrayList, new String[0]));
        this.mOriginalDeviceDataArray.put(131, new OriginalDeviceData(arrayList2, new String[0]));
        this.mOriginalDeviceDataArray.put(132, new OriginalDeviceData(arrayList3, new String[0]));
    }

    private List<OriginalCarDeviceUpdateEntity> get0x83List() {
        String str = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? "ON" : "OFF";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7) + " Lever"));
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> get0x84List() {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            string = sb.append(getMsbLsbResult(iArr[4], iArr[3])).append("KHz").toString();
        } else if (i == 0 || i == 2 || i == 3) {
            StringBuilder sb2 = new StringBuilder();
            DecimalFormat decimalFormat = this.df_2Decimal;
            int[] iArr2 = this.mCanBusInfoInt;
            string = sb2.append(decimalFormat.format(getMsbLsbResult(iArr2[4], iArr2[3]) * 0.01d)).append("MHz").toString();
        } else {
            string = "NO SUPPORT";
        }
        String str = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]) ? "ON" : "OFF";
        String str2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) ? "ON" : "OFF";
        String str3 = this.mCanBusInfoInt[5] == 0 ? "NO INFO" : this.mCanBusInfoInt[5] + "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, string));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, str));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, str2));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, str3));
        return arrayList;
    }

    private String getRunningState() {
        return (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "Power ON" : "Power OFF") + (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? "(Show Menu)" : "(No Menu)");
    }

    private String getRadiaState() {
        int i = this.mCanBusInfoInt[2];
        return i == 0 ? "FM" : i == 1 ? "AM" : i == 2 ? "FM1" : i == 3 ? "FM2" : i == 4 ? "MW" : i == 5 ? "LW" : "NO STATE";
    }

    private String getRadioRuningState() {
        switch (this.mCanBusInfoInt[7]) {
            case 1:
                return "SEEK+";
            case 2:
                return "SEEK-";
            case 3:
                return "AUTO SEEK";
            case 4:
                return "Tune+";
            case 5:
                return "Tune-";
            case 6:
                return "SCAN";
            default:
                return "Invalid";
        }
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
        return i == 1 ? "LO" : i == 255 ? "HI" : i == 0 ? "No Display" : (i * 0.5d) + getTempUnitC(this.mContext);
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

    private boolean isNotDoorInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return false;
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
