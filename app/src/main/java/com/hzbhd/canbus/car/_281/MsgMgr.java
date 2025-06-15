package com.hzbhd.canbus.car._281;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.ui.util.BaseUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;




public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;
    private Byte lastKnobValue;
    private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    public UiMgr uiMgr;
    private Integer x1AD1;
    private Integer x1AD6;
    private Integer x1AD7;
    private Integer x1AD8;
    private Integer x21;
    private Integer x81D4;
    private Integer x81D5;

    public MsgMgr() {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getOriginalCarDevicePageUiSet(BaseUtil.INSTANCE.getContext());

        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
    }

    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }

        return null;
    }

    public final void setContext(Context context) {

        this.context = context;
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

    public final UiMgr getUiMgr() {
        UiMgr uiMgr = this.uiMgr;
        if (uiMgr != null) {
            return uiMgr;
        }

        return null;
    }

    public final void setUiMgr(UiMgr uiMgr) {

        this.uiMgr = uiMgr;
    }

    static /* synthetic */ void send0xF3Data$default(MsgMgr msgMgr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 5;
        }
        msgMgr.send0xF3Data(i);
    }

    private final void send0xF3Data(int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -13, 1, (byte) d1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        send0xF3Data$default(this, 0, 1, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        send0xF3Data$default(this, 0, 1, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        send0xF3Data$default(this, 0, 1, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        send0xF3Data$default(this, 0, 1, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        setUiMgr((UiMgr) canUiMgr);
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, getUiMgr(), null, 4, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (context == null || canbusInfo == null) {
            return;
        }
        setContext(context);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 19) {
            set0x13Data();
            return;
        }
        if (i == 26) {
            door(getFrame()[3]);
            anotherWheelAngle(getFrame()[8], getFrame()[9]);
            panorama();
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 134) {
            set0x86Data();
            return;
        }
        if (i == 33) {
            panelBtn();
            return;
        }
        if (i != 34) {
            switch (i) {
                case 129:
                    button(getFrame()[6]);
                    wheelAngle(getFrame()[7]);
                    break;
                case 130:
                    air();
                    break;
                case 131:
                    set0x83Data();
                    break;
                case 132:
                    set0x84Data();
                    break;
            }
            return;
        }
        panelKnob(canbusInfo);
    }

    public final OriginalCarDevicePageUiSet getMOriginalCarDevicePageUiSet() {
        return this.mOriginalCarDevicePageUiSet;
    }

    private final void set0x86Data() {
        String str;
        int i = getFrame()[8];
        GeneralOriginalCarDeviceData.cdStatus = i != 4 ? i != 8 ? i != 16 ? i != 32 ? i != 64 ? i != 128 ? "More" : "Scan" : "Disc Scan" : "Repeat" : "Disc Repeat" : "Random" : "Disc Random";
        switch (getFrame()[9]) {
            case 0:
                str = "Reading TOC";
                break;
            case 1:
                str = "Pause";
                break;
            case 2:
                str = "Play";
                break;
            case 3:
                str = "Fast";
                break;
            case 4:
                str = "User search";
                break;
            case 5:
                str = "Internal search";
                break;
            case 6:
                str = "Stop";
                break;
            case 7:
                str = "Rom read";
                break;
            case 8:
                str = "Rom search";
                break;
            case 9:
                str = "Retrieving";
                break;
            case 10:
                str = "Disc changing (User)";
                break;
            case 11:
                str = "Disc changing (Internal)";
                break;
            case 12:
                str = "Eject";
                break;
            default:
                str = "INVALID";
                break;
        }
        GeneralOriginalCarDeviceData.runningState = str;
        List<OriginalCarDevicePageUiSet.Item> items = this.mOriginalCarDevicePageUiSet.getItems();
        items.clear();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 4);
        boolean z = false;
        if (1 <= intFromByteWithBit && intFromByteWithBit < 7) {
            z = true;
        }
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_disc", z ? "Disk " + intFromByteWithBit : "Single"));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_track", String.valueOf(getFrame()[5])));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_current_play_time", getFrame()[6] + " : " + getFrame()[7]));
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private final void set0x84Data() {
        String str;
        int i = getFrame()[2];
        String str2 = "INVALID";
        GeneralOriginalCarDeviceData.cdStatus = i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "INVALID" : "LW" : "MW" : "FM2" : "FM1" : "AM" : "FM";
        switch (getFrame()[7]) {
            case 1:
                str2 = "SEEK +";
                break;
            case 2:
                str2 = "SEEK -";
                break;
            case 3:
                str2 = "Auto Seek";
                break;
            case 4:
                str2 = "Tune +";
                break;
            case 5:
                str2 = "Tune -";
                break;
            case 6:
                str2 = "SCAN";
                break;
        }
        GeneralOriginalCarDeviceData.runningState = str2;
        List<OriginalCarDevicePageUiSet.Item> items = this.mOriginalCarDevicePageUiSet.getItems();
        items.clear();
        StringBuilder sbAppend = new StringBuilder().append(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[3])).append(' ');
        String cdStatus = GeneralOriginalCarDeviceData.cdStatus;

        if (StringsKt.contains$default((CharSequence) cdStatus, (CharSequence) "FM", false, 2, (Object) null)) {
            str = "MHz";
        } else {
            String cdStatus2 = GeneralOriginalCarDeviceData.cdStatus;

            str = StringsKt.contains$default((CharSequence) cdStatus2, (CharSequence) "AM", false, 2, (Object) null) ? "KHz" : "";
        }
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_frequency", sbAppend.append(str).toString()));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "S281_0x84_1", String.valueOf(getFrame()[5])));
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private final void set0x83Data() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 4);
        if (intFromByteWithBit == 0 || intFromByteWithBit == 1) {
            enterAuxIn2();
        }
    }

    private final void set0x31Data() {
        updateOutDoorTemp(getContext(), ((getFrame()[13] * 0.5d) - 40) + " °C");
    }

    private final void set0x13Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_6");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[2], getFrame()[3]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_7");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_8");
        if (item3 != null) {
            item3.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]) / 10.0d));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_9");
        if (item4 != null) {
            item4.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[9]) + " Min");
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_10");
        if (item5 != null) {
            item5.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[11]) + " Km/H");
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_11");
        String str = "--";
        if (item6 != null) {
            int i = getFrame()[12];
            item6.setValue(i != 0 ? i != 1 ? i != 2 ? "--" : "L/100KM" : "KM/L" : "MPG");
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S18_CarBody_12");
        if (item7 != null) {
            int i2 = getFrame()[13];
            if (i2 == 0) {
                str = "KM";
            } else if (i2 == 1) {
                str = "MILE";
            }
            item7.setValue(str);
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("_2_setting_10_6");
        if (item8 != null) {
            item8.setValue(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(DataHandleUtils.getMsbLsbResult(getFrame()[16], getFrame()[17]) / 10.0d, 10) + " Km");
        }
        updateDriveDataActivity(null);
    }

    private final void panorama() {
        Integer num = this.x1AD8;
        int i = getFrame()[10];
        if (num == null || num.intValue() != i) {
            if (getFrame()[10] == 1) {
                forceReverse(getContext(), true);
            } else {
                forceReverse(getContext(), false);
            }
        }
        this.x1AD8 = Integer.valueOf(getFrame()[10]);
    }

    private final void panelBtn() {
        int i = getFrame()[2];
        Integer num = this.x21;
        if (num == null || num.intValue() != i) {
            if (i == 1) {
                realKeyClick4(getContext(), HotKeyConstant.K_SLEEP);
            } else if (i == 32) {
                realKeyClick4(getContext(), 128);
            } else if (i == 36) {
                realKeyClick4(getContext(), 2);
            } else if (i == 57) {
                realKeyClick4(getContext(), HotKeyConstant.K_DISP);
            } else if (i == 66) {
                realKeyClick4(getContext(), 4);
            } else if (i == 47) {
                realKeyClick4(getContext(), 151);
            } else if (i == 48) {
                realKeyClick4(getContext(), 68);
            }
        }
        this.x21 = Integer.valueOf(i);
    }

    private final void panelKnob(byte[] canbusInfo) {
        byte b = canbusInfo[3];
        Byte b2 = this.lastKnobValue;
        byte bByteValue = b2 != null ? b2.byteValue() : b;
        int iAbs = Math.abs(b - bByteValue);
        int i = getFrame()[2];
        if (i != 1) {
            if (i == 2) {
                if (b > bByteValue) {
                    DataHandleUtils.knob(getContext(), 46, iAbs);
                } else if (b < bByteValue) {
                    DataHandleUtils.knob(getContext(), 45, iAbs);
                }
            }
        } else if (b > bByteValue) {
            DataHandleUtils.knob(getContext(), 7, iAbs);
        } else if (b < bByteValue) {
            DataHandleUtils.knob(getContext(), 8, iAbs);
        }
        this.lastKnobValue = Byte.valueOf(canbusInfo[3]);
    }

    private final void air() {
        if (DataHandleUtils.getBoolBit7(getFrame()[2])) {
            popAirActivity();
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.cycle_in_out_close = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrame()[2]);
        GeneralAirData.swing = DataHandleUtils.getBoolBit1(getFrame()[2]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit0(getFrame()[2]);
        GeneralAirData.front_left_temperature = air$temp(getFrame()[3]);
        GeneralAirData.front_right_temperature = air$temp(getFrame()[4]);
        MsgMgrKt.windDirectionInit();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 4, 4);
        if (intFromByteWithBit == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 0, 4);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[6]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit3(getFrame()[6]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit2(getFrame()[6]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(getFrame()[6]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit0(getFrame()[6]);
        updateAirActivity(getContext(), 1001);
    }

    private static final String air$temp(int i) {
        return i != 0 ? i != 1 ? i != 255 ? (i / 2.0d) + " °C" : "HI" : "LO" : "  ";
    }

    public final void button(int d4) {
        Integer num = this.x81D4;
        if (num != null && d4 == num.intValue()) {
            return;
        }
        if (d4 == 0) {
            realKeyClick4(getContext(), 0);
        } else if (d4 == 1) {
            realKeyClick4(getContext(), 7);
        } else if (d4 == 2) {
            realKeyClick4(getContext(), 8);
        } else if (d4 == 4) {
            realKeyClick4(getContext(), HotKeyConstant.K_SPEECH);
        } else if (d4 == 5) {
            realKeyClick4(getContext(), 14);
        } else if (d4 == 6) {
            realKeyClick4(getContext(), 15);
        } else if (d4 == 8) {
            realKeyClick4(getContext(), 21);
        } else if (d4 == 9) {
            realKeyClick4(getContext(), 20);
        } else if (d4 == 12) {
            realKeyClick4(getContext(), 2);
        } else if (d4 == 32) {
            popAirActivity();
        }
        this.x81D4 = Integer.valueOf(d4);
    }

    public final void wheelAngle(int d5) {
        Integer num = this.x81D5;
        if (num == null || d5 != num.intValue()) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1((byte) d5, (byte) 0, 0, WorkQueueKt.MASK, 8);
            updateParkUi(null, getContext());
        }
        this.x81D5 = Integer.valueOf(d5);
    }

    public final void door(int d1) {
        Integer num = this.x1AD1;
        if (num == null || d1 != num.intValue()) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(d1);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(d1);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(d1);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(d1);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(d1);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(d1);
            updateDoorView(getContext());
        }
        this.x1AD1 = Integer.valueOf(d1);
    }

    private final void anotherWheelAngle(int d6, int d7) {
        Integer num;
        Integer num2 = this.x1AD6;
        if (num2 == null || d6 != num2.intValue() || (num = this.x1AD7) == null || d7 != num.intValue()) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) d7, (byte) d6, 0, 540, 16);
            updateParkUi(null, getContext());
        }
        this.x1AD6 = Integer.valueOf(d6);
        this.x1AD7 = Integer.valueOf(d7);
    }

    private final void popAirActivity() {
        if (SystemUtil.isForeground(getContext(), AirActivity.class.getName())) {
            realKeyClick4(getContext(), 50);
        } else {
            updateAirActivity(getContext(), 1001);
        }
    }
}
