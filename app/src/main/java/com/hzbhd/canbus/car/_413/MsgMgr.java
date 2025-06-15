package com.hzbhd.canbus.car._413;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemButton;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


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
    SystemButton systemDvrButton;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");

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
        CanbusMsgSender.sendMsg(new byte[]{22, 36, 38, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 49) {
            set0x31AirInfo();
        } else if (i == 50) {
            set0x32CarInfo();
        } else {
            if (i != 240) {
                return;
            }
            set0xF0Version();
        }
    }

    private void set0xF0Version() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v30, types: [int] */
    /* JADX WARN: Type inference failed for: r0v85 */
    /* JADX WARN: Type inference failed for: r0v86 */
    private void set0x31AirInfo() {
        updateOutDoorTemp(this.mContext, this.df_2Decimal.format((this.mCanBusInfoInt[13] * 0.5d) - 40.0d));
        this.mCanBusInfoInt[13] = 0;
        if (isNotAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        ?? boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            boolBit4 = 2;
        }
        GeneralAirData.in_out_auto_cycle = boolBit4;
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_left_auto_wind = false;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_auto_wind = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_window = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else {
            switch (intFromByteWithBit) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    break;
            }
        }
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_right_auto_wind = false;
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
        if (intFromByteWithBit2 == 1) {
            GeneralAirData.front_right_auto_wind = true;
        } else if (intFromByteWithBit2 == 2) {
            GeneralAirData.front_right_blow_window = true;
        } else if (intFromByteWithBit2 == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 6) {
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (intFromByteWithBit2) {
                case 11:
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[8], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[9], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_foot = false;
        int i = this.mCanBusInfoInt[10];
        if (i == 1) {
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i == 2) {
            GeneralAirData.rear_right_blow_head = true;
        } else if (i == 3) {
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_head = true;
        }
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_foot = false;
        int i2 = this.mCanBusInfoInt[10];
        if (i2 == 1) {
            GeneralAirData.rear_left_blow_foot = true;
        } else if (i2 == 2) {
            GeneralAirData.rear_left_blow_head = true;
        } else if (i2 == 3) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_head = true;
        }
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        GeneralAirData.rear_temperature = getTemperature(this.mCanBusInfoInt[12], com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, 255);
        updateAirActivity(this.mContext, 1004);
    }

    private void set0x32CarInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_412_drive");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_412_drive1");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[4], iArr[5])).append("RPM").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_412_drive");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_412_drive2");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(getMsbLsbResult(iArr2[6], iArr2[7])).append("KM/H").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr3[6], iArr3[7]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
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
        return i == i2 ? "LO" : i == i3 ? "HI" : this.df_1Decimal.format(i * 0.5d) + getTempUnitC(this.mContext);
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

    public void showDvrButton() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._413.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.systemDvrButton == null) {
                    MsgMgr.this.systemDvrButton = new SystemButton(MsgMgr.this.getActivity(), "P", new SystemButton.PanoramaListener() { // from class: com.hzbhd.canbus.car._413.MsgMgr.1.1
                        @Override // com.hzbhd.canbus.util.SystemButton.PanoramaListener
                        public void clickEvent() {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPanoramicCmd(1);
                        }
                    });
                }
                MsgMgr.this.systemDvrButton.show();
            }
        });
    }

    public void hideDvrButton() {
        SystemButton systemButton = this.systemDvrButton;
        if (systemButton == null) {
            return;
        }
        systemButton.hide();
    }
}
