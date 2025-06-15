package com.hzbhd.canbus.car._369;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
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
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;




public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;

    private final void set0x39Data() {
    }

    private final void set0x3FData() {
    }

    private static final int set0x41Data$restrictValue(int i) {
        if (i == 255) {
            return 0;
        }
        return i;
    }

    private final void set0x77Data() {
    }

    private final void set0xC2Data() {
    }

    private final void set0xF0Data() {
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
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = (byte) bYear2Dig;
        bArr[3] = (byte) bMonth;
        bArr[4] = (byte) bDay;
        if (isFormat24H) {
            bHours = bHours24H;
        }
        bArr[5] = (byte) bHours;
        bArr[6] = (byte) bMins;
        bArr[7] = isFormat24H ? (byte) 1 : (byte) 0;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = 0;
        bArr[11] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        UiMgrInterface canUiMgr2 = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initSettingItemsIndexHashMap$default(context, (UiMgr) canUiMgr2, null, 4, null);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
            return;
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 33) {
            set0x21Data();
            return;
        }
        if (i == 34) {
            byte b = canbusInfo[3];
            byte lastKnobValue = MsgMgrKt.getLastKnobValue();
            int iAbs = Math.abs(b - lastKnobValue);
            int i2 = getFrame()[2];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (b > lastKnobValue) {
                        DataHandleUtils.knob(context, 46, iAbs);
                    } else if (b < lastKnobValue) {
                        DataHandleUtils.knob(context, 45, iAbs);
                    }
                }
            } else if (b > lastKnobValue) {
                DataHandleUtils.knob(context, 7, iAbs);
            } else if (b < lastKnobValue) {
                DataHandleUtils.knob(context, 8, iAbs);
            }
            MsgMgrKt.setLastKnobValue(canbusInfo[3]);
            return;
        }
        if (i == 57) {
            set0x39Data();
            return;
        }
        if (i == 63) {
            set0x3FData();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 112) {
            set0x70Data();
            return;
        }
        if (i == 119) {
            set0x77Data();
            return;
        }
        if (i == 194) {
            set0xC2Data();
            return;
        }
        if (i != 240) {
            switch (i) {
                case 49:
                    set0x31Data();
                    break;
                case 50:
                    set0x32Data();
                    break;
                case 51:
                    set0x33Data();
                    break;
                case 52:
                    set0x34Data();
                    break;
                case 53:
                    set0x35Data();
                    break;
                case 54:
                    set0x36Data();
                    break;
                default:
                    switch (i) {
                        case 101:
                            set0x65Data();
                            break;
                        case 102:
                            set0x66Data();
                            break;
                        case 103:
                            set0x67Data();
                            break;
                    }
            }
            return;
        }
        set0xF0Data();
    }

    private final void set0x35Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S369_Air_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S369_Air_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S369_Air_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 3, 1)));
        }
        updateSettingActivity(null);
    }

    private final void set0x36Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_1");
        if (item != null) {
            item.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit7(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_2");
        if (item2 != null) {
            item2.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit6(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_3");
        if (item3 != null) {
            item3.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit5(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_4");
        if (item4 != null) {
            item4.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit4(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_5");
        if (item5 != null) {
            item5.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit3(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_6");
        if (item6 != null) {
            item6.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit2(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_7");
        if (item7 != null) {
            item7.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit1(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_8");
        if (item8 != null) {
            item8.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit0(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item9 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_9");
        if (item9 != null) {
            item9.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit7(getFrame()[3])));
        }
        DriverDataPageUiSet.Page.Item item10 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_10");
        if (item10 != null) {
            item10.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit6(getFrame()[3])));
        }
        DriverDataPageUiSet.Page.Item item11 = InitUtilsKt.getMDrivingItemIndex().get("D369_Light_11");
        if (item11 != null) {
            item11.setValue(MsgMgrKt.getOpenOrClose(DataHandleUtils.getBoolBit3(getFrame()[3])));
        }
        updateDriveDataActivity(null);
    }

    private final void set0x34Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_1");
        if (item != null) {
            item.setValue(String.valueOf(MsgMgrKt.accurateTo(((getFrame()[6] * 256 * 256) + (getFrame()[7] * 256) + getFrame()[8]) * 0.1d, 10)));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(MsgMgrKt.accurateTo(((getFrame()[9] * 256) + getFrame()[10]) * 0.1d, 10)));
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_3");
        if (item3 != null) {
            item3.setValue(String.valueOf((getFrame()[14] * 256) + getFrame()[15]));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_4");
        if (item4 != null) {
            item4.setValue(new StringBuilder().append((getFrame()[19] * 256) + getFrame()[20]).append(' ').toString());
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_5");
        if (item5 != null) {
            item5.setValue(DataHandleUtils.getBoolBit2(getFrame()[24]) ? "mile" : "km");
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("D369_Oil_6");
        if (item6 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[24], 0, 2);
            item6.setValue(intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? "--" : "l/100km" : "km/l" : "mpg");
        }
        updateDriveDataActivity(null);
    }

    private final void set0x33Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    private final void set0x32Data() {
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]));
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getBoolBit0(getFrame()[2])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_2");
        if (item2 != null) {
            int i = getFrame()[3];
            item2.setValue(i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "--" : "S" : "D" : "R" : "N" : "P");
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_3");
        if (item3 != null) {
            item3.setValue(String.valueOf((getFrame()[4] * 256) + getFrame()[5]));
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_4");
        if (item4 != null) {
            item4.setValue(String.valueOf((getFrame()[6] * 256) + getFrame()[7]));
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_5");
        if (item5 != null) {
            item5.setValue(MsgMgrKt.accurateTo(getFrame()[8] * 0.1d, 10) + " V");
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_6");
        if (item6 != null) {
            item6.setValue(getFrame()[9] + " %");
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_7");
        if (item7 != null) {
            item7.setValue(getFrame()[10] + " %");
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_8");
        if (item8 != null) {
            item8.setValue(((getFrame()[11] * 0.5d) - 40) + " °C");
        }
        DriverDataPageUiSet.Page.Item item9 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_9");
        if (item9 != null) {
            item9.setValue(((getFrame()[12] * 256) - getFrame()[13]) + " °C");
        }
        DriverDataPageUiSet.Page.Item item10 = InitUtilsKt.getMDrivingItemIndex().get("D369_Data_10");
        if (item10 != null) {
            item10.setValue(String.valueOf((getFrame()[14] * 256) - getFrame()[13]));
        }
        updateDriveDataActivity(null);
    }

    private final void set0x70Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S369_LightTime_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 4)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S369_LightTime_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 4)));
        }
        updateSettingActivity(null);
    }

    private final void set0x67Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S369_Light_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit7(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit6(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit5(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit4(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit3(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_6");
        if (itemListBean6 != null) {
            itemListBean6.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit2(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit1(getFrame()[3]))));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S369_Light_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(MsgMgrKt.getValueOfBoolean(DataHandleUtils.getBoolBit0(getFrame()[3]))));
        }
        updateSettingActivity(null);
    }

    private final void set0x66Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S369_RemoteControl_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 7, 1)));
        }
        updateSettingActivity(null);
    }

    private final void set0x65Data() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S369_Door_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 6, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S369_Door_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S369_Door_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 2, 1)));
        }
        updateSettingActivity(null);
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(7, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(getFrame()[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        int i = getFrame()[5];
        GeneralAirData.center_wheel = (i == 254 || i == 255) ? "--" : ((i * 0.5d) - 40) + " °C";
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i2 = getFrame()[6];
        if (i2 == 1) {
            GeneralAirData.front_left_auto_wind = true;
        } else if (i2 == 2) {
            GeneralAirData.front_defog = true;
        } else if (i2 == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i2 == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i2 == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else {
            switch (i2) {
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
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i3 = getFrame()[8];
        String str2 = "High";
        if (i3 != 254) {
            str = i3 != 255 ? (i3 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        int i4 = getFrame()[9];
        if (i4 == 254) {
            str2 = "Low";
        } else if (i4 != 255) {
            str2 = (i4 * 0.5d) + " °C";
        }
        GeneralAirData.front_right_temperature = str2;
        updateOutDoorTemp(InitUtilsKt.getMContext(), ((getFrame()[13] * 0.5d) - 40) + " °C");
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x21Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = 2;
        int i2 = getFrame()[2];
        if (i2 == 1) {
            i = HotKeyConstant.K_SLEEP;
        } else if (i2 == 2) {
            i = 21;
        } else if (i2 == 3) {
            i = 20;
        } else if (i2 == 6) {
            i = 50;
        } else if (i2 == 9) {
            i = 3;
        } else if (i2 != 32) {
            if (i2 == 47) {
                i = 151;
            } else if (i2 == 43) {
                i = 52;
            } else if (i2 != 44) {
                i = 0;
            }
        }
        realKeyLongClick1(mContext, i, getFrame()[3]);
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

    private final void set0x11Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S362_vehicleSpeedInfo_1");
        int i = 3;
        if (item != null) {
            item.setValue(getFrame()[3] + " km/h");
        }
        updateDriveDataActivity(null);
        Context mContext = InitUtilsKt.getMContext();
        int i2 = getFrame()[4];
        int i3 = 14;
        if (i2 == 8) {
            i3 = 21;
        } else if (i2 == 9) {
            i3 = 20;
        } else if (i2 == 11) {
            i3 = 2;
        } else if (i2 == 24) {
            i3 = 63;
        } else if (i2 != 64) {
            switch (i2) {
                case 0:
                default:
                    i3 = 0;
                    break;
                case 1:
                    i3 = 7;
                    break;
                case 2:
                    i3 = 8;
                    break;
                case 3:
                case 4:
                    i3 = 3;
                    break;
                case 5:
                    break;
                case 6:
                    i3 = 15;
                    break;
            }
        }
        realKeyLongClick1(mContext, i3, getFrame()[5]);
        int i4 = getFrame()[7];
        if (i4 >= 0 && i4 < 21) {
            i = 1;
        } else {
            if (!(21 <= i4 && i4 < 41)) {
                if (!(41 <= i4 && i4 < 61)) {
                    if (61 <= i4 && i4 < 81) {
                        i = 4;
                    } else {
                        if (81 <= i4 && i4 < 101) {
                            i = 5;
                        }
                    }
                }
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
                updateParkUi(null, InitUtilsKt.getMContext());
            }
            i = 2;
        }
        setBacklightLevel(i);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
