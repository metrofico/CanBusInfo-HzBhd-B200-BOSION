package com.hzbhd.canbus.car._403;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;




public final class MsgMgr extends AbstractMsgMgr {
    private Integer dateFormat;
    public int[] frame;
    private boolean isReverse;

    private final void set0x26Data() {
    }

    private static final int set0x41Data$restrictValue(int i) {
        if (i == 255) {
            return 0;
        }
        return i;
    }

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }

        return null;
    }

    public final void setFrame(int[] iArr) {

        this.frame = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        UiMgrInterface canUiMgr2 = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr2, null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 38) {
            set0x26Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 232) {
            set0xE8Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 50) {
            set0x32Data();
            return;
        }
        switch (i) {
            case 102:
                set0x66Data();
                break;
            case 103:
                set0x67Data();
                break;
            case 104:
                set0x68Data();
                break;
        }
    }

    private final void sendMediaSource(int d0) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(new byte[]{(byte) d0}, 13, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendMediaSource(0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaSource(12);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        sendMediaSource(10);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        int i;
        if (currBand != null) {
            switch (currBand.hashCode()) {
                case 64901:
                    if (currBand.equals("AM1")) {
                        i = 4;
                        break;
                    } else {
                        return;
                    }
                case 64902:
                    if (currBand.equals("AM2")) {
                        i = 5;
                        break;
                    } else {
                        return;
                    }
                case 69706:
                    if (currBand.equals("FM1")) {
                        i = 1;
                        break;
                    } else {
                        return;
                    }
                case 69707:
                    if (currBand.equals("FM2")) {
                        i = 2;
                        break;
                    } else {
                        return;
                    }
                case 69708:
                    if (currBand.equals("FM3")) {
                        i = 3;
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            sendMediaSource(i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        sendMediaSource(stoagePath == 9 ? 14 : 13);

        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, songTitle, Charsets.UTF_16BE, 32, 10);

        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(146, songArtist, Charsets.UTF_16BE, 32, 10);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0xE8Data() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2);
        if (intFromByteWithBit == 1) {
            new PanoramicBtnUpdateEntity(5, true);
            new PanoramicBtnUpdateEntity(6, false);
        } else if (intFromByteWithBit == 2) {
            new PanoramicBtnUpdateEntity(5, true);
            new PanoramicBtnUpdateEntity(6, true);
        } else {
            new PanoramicBtnUpdateEntity(5, false);
            new PanoramicBtnUpdateEntity(6, false);
        }
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    public final Integer getDateFormat() {
        return this.dateFormat;
    }

    public final void setDateFormat(Integer num) {
        this.dateFormat = num;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = 0;
        if (isFormat24H) {
            bHours = bHours24H;
        }
        bArr[3] = (byte) bHours;
        bArr[4] = (byte) bMins;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = isFormat24H ? (byte) 1 : (byte) 0;
        bArr[8] = (byte) bYear2Dig;
        bArr[9] = (byte) bMonth;
        bArr[10] = (byte) bDay;
        Integer num = this.dateFormat;
        if (num != null) {
            bArr[11] = (byte) num.intValue();
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    private final void set0x68Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("403_0x68_1");
        if (itemListBean != null) {
            itemListBean.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit5(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit4(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit3(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit2(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("403_0x68_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(itemListBean5.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 2)));
        }
        updateSettingActivity(null);
    }

    private final void set0x67Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("403_0x67_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 5, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 7, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 5, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 4, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_9");
        if (itemListBean9 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 2, 2);
            itemListBean9.setProgress(intFromByteWithBit);
            itemListBean9.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 0, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 2, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_13");
        if (itemListBean13 != null) {
            itemListBean13.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 1, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("403_0x67_14");
        if (itemListBean14 != null) {
            itemListBean14.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 0, 1)));
        }
        updateSettingActivity(null);
    }

    private final void set0x66Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("403_0x66_1");
        if (itemListBean != null) {
            itemListBean.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit7(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_2");
        if (itemListBean2 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 3);
            itemListBean2.setProgress(intFromByteWithBit);
            itemListBean2.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit2(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit1(getFrame()[3])));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("403_0x66_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Boolean.valueOf(DataHandleUtils.getBoolBit0(getFrame()[3])));
        }
        updateSettingActivity(null);
    }

    private final void set0x32Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]));
        updateDriveDataActivity(null);
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(3, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        RadarInfoUtil.setFrontRadarLocationData(3, set0x41Data$restrictValue(getFrame()[6]), set0x41Data$restrictValue(getFrame()[7]), set0x41Data$restrictValue(getFrame()[8]), set0x41Data$restrictValue(getFrame()[9]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i2 = getFrame()[8];
        String str2 = "High";
        if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        int i3 = getFrame()[9];
        if (i3 == 254) {
            str2 = "Low";
        } else if (i3 != 255) {
            str2 = (i3 * 0.5d) + " °C";
        }
        GeneralAirData.front_right_temperature = str2;
        updateOutDoorTemp(InitUtilsKt.getMContext(), getFrame()[13] + " °C");
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(getFrame()[4]);
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(getFrame()[4]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    /* renamed from: isReverse, reason: from getter */
    public final boolean getIsReverse() {
        return this.isReverse;
    }

    public final void setReverse(boolean z) {
        this.isReverse = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void set0x11Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = getFrame()[4];
        int i2 = 3;
        if (i != 101) {
            switch (i) {
                case 0:
                    break;
                case 1:
                    i2 = 7;
                    break;
                case 2:
                    i2 = 8;
                    break;
                case 3:
                case 4:
                    break;
                case 5:
                    i2 = 14;
                    break;
                case 6:
                    i2 = 15;
                    break;
                default:
                    switch (i) {
                        case 8:
                            i2 = 21;
                            break;
                        case 9:
                            i2 = 20;
                            break;
                        case 10:
                            i2 = 52;
                            break;
                        case 11:
                            i2 = 2;
                            break;
                        default:
                            switch (i) {
                                case 13:
                                    i2 = 45;
                                    break;
                                case 14:
                                    i2 = 46;
                                    break;
                                case 15:
                                    i2 = 49;
                                    break;
                                case 16:
                                    i2 = 50;
                                    break;
                            }
                    }
            }
            realKeyLongClick1(mContext, i2, getFrame()[5]);
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
            updateParkUi(null, InitUtilsKt.getMContext());
        }
        forceReverse(InitUtilsKt.getMContext(), !this.isReverse);
        this.isReverse = !this.isReverse;
        i2 = 0;
        realKeyLongClick1(mContext, i2, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
