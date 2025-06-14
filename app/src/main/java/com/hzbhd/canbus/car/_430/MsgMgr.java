package com.hzbhd.canbus.car._430;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._430.SetTimeView;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int nowValue = -1;
    int nowLight = 4;

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
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 240) {
            setVersion0xF0();
            return;
        }
        if (i == 33) {
            setPanelButton0x21();
            return;
        }
        if (i == 34) {
            setKnob0x22();
            return;
        }
        if (i != 114) {
            if (i != 115) {
                return;
            }
            setAirInfo0x73();
        } else {
            setSwc0x72();
            setTrack0x72();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        }
    }

    private void setVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirInfo0x73() {
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_econ = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 1, 255);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 1, 255);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        updateAirActivity(this.mContext, 1004);
    }

    private void setKnob0x22() {
        int i = this.nowValue;
        if (i == -1) {
            this.nowValue = this.mCanBusInfoInt[3];
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (i < iArr[3]) {
                realKeyClick4(this.mContext, 7);
            } else {
                realKeyClick4(this.mContext, 8);
            }
        } else if (i < iArr[3]) {
            realKeyClick4(this.mContext, 46);
        } else {
            realKeyClick4(this.mContext, 45);
        }
        this.nowValue = this.mCanBusInfoInt[3];
    }

    private void setPanelButton0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(1);
            return;
        }
        if (i == 2) {
            buttonKey(45);
            return;
        }
        if (i == 3) {
            buttonKey(46);
            return;
        }
        if (i == 6) {
            buttonKey(50);
            return;
        }
        if (i == 9) {
            buttonKey(3);
            return;
        }
        if (i == 16) {
            buttonKey(49);
            return;
        }
        if (i == 32) {
            buttonKey(128);
            return;
        }
        if (i == 36) {
            buttonKey(59);
            return;
        }
        if (i == 57) {
            buttonKey(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 59) {
            buttonKey(2);
            return;
        }
        if (i == 66) {
            buttonKey(4);
            return;
        }
        if (i == 75) {
            buttonKey(62);
            return;
        }
        if (i == 95) {
            buttonKey(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 42) {
            buttonKey(49);
            return;
        }
        if (i == 43) {
            buttonKey(52);
            return;
        }
        switch (i) {
            case 47:
                buttonKey(151);
                break;
            case 48:
                buttonKey(68);
                break;
            case 49:
                if (iArr[3] != 0) {
                    setBackLight();
                    break;
                }
                break;
            default:
                switch (i) {
                    case 51:
                        buttonKey(62);
                        break;
                    case 52:
                        buttonKey(14);
                        break;
                    case 53:
                        buttonKey(15);
                        break;
                }
        }
    }

    private void setBackLight() {
        int i = this.nowLight;
        if (i == 3) {
            this.nowLight = 4;
        } else if (i == 4) {
            this.nowLight = 5;
        } else if (i == 5) {
            this.nowLight = 3;
        }
        setBacklightLevel(this.nowLight);
    }

    private void setDoorInfo0x12() {
        if (isBasicInfoChange()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
            updateDoorView(this.mContext);
        }
    }

    private void setTrack0x72() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
            updateParkUi(null, this.mContext);
        } else {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[7], (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), 0, 7800, 16);
            updateParkUi(null, this.mContext);
        }
        updateParkUi(null, this.mContext);
    }

    private void setSwc0x72() {
        int i = this.mCanBusInfoInt[4];
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
        if (i == 3) {
            realKeyClick4(this.mContext, 3);
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
        if (i == 10) {
            realKeyClick4(this.mContext, 2);
        } else if (i == 13) {
            realKeyClick4(this.mContext, 45);
        } else {
            if (i != 14) {
                return;
            }
            realKeyClick4(this.mContext, 46);
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

    private String getTemperature(int i, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (DataHandleUtils.getBoolBit7(i)) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 0, 7) + 0.5d) + getTempUnitC(this.mContext);
        }
        return i + getTempUnitC(this.mContext);
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

    public void showDialog() {
        new SetTimeView().showDialog(getActivity(), new SetTimeView.ThisInterface() { // from class: com.hzbhd.canbus.car._430.MsgMgr.1
            @Override // com.hzbhd.canbus.car._430.SetTimeView.ThisInterface
            public void hourUp() {
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 6, 1});
            }

            @Override // com.hzbhd.canbus.car._430.SetTimeView.ThisInterface
            public void hourDown() {
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 6, 2});
            }

            @Override // com.hzbhd.canbus.car._430.SetTimeView.ThisInterface
            public void minUp() {
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 7, 1});
            }

            @Override // com.hzbhd.canbus.car._430.SetTimeView.ThisInterface
            public void minDown() {
                CanbusMsgSender.sendMsg(new byte[]{22, 110, 7, 2});
            }

            @Override // com.hzbhd.canbus.car._430.SetTimeView.ThisInterface
            public void timeFormat(boolean z) {
                byte[] bArr = new byte[4];
                bArr[0] = 22;
                bArr[1] = 110;
                bArr[2] = 8;
                bArr[3] = (byte) (z ? 1 : 2);
                CanbusMsgSender.sendMsg(bArr);
            }
        });
    }
}
