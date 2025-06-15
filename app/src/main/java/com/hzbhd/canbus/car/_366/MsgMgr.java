package com.hzbhd.canbus.car._366;

import android.content.Context;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;
    private byte lastKnobValue;

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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        setContext(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
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
        if (i != 34) {
            if (i != 240) {
                return;
            }
            set0xF0Data(canbusInfo);
            return;
        }
        byte b = canbusInfo[3];
        byte b2 = this.lastKnobValue;
        int iAbs = Math.abs(b - b2);
        int i2 = getFrame()[2];
        if (i2 != 1) {
            if (i2 == 2) {
                if (b > b2) {
                    DataHandleUtils.knob(context, 46, iAbs);
                } else if (b < b2) {
                    DataHandleUtils.knob(context, 45, iAbs);
                }
            }
        } else if (b > b2) {
            DataHandleUtils.knob(context, 7, iAbs);
        } else if (b < b2) {
            DataHandleUtils.knob(context, 8, iAbs);
        }
        this.lastKnobValue = canbusInfo[3];
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x21Data() {
        Context context = getContext();
        int i = 2;
        int i2 = getFrame()[2];
        if (i2 == 1) {
            i = 1;
        } else if (i2 == 2) {
            i = 45;
        } else if (i2 == 3) {
            i = 46;
        } else if (i2 == 42) {
            i = 49;
        } else if (i2 != 43) {
            switch (i2) {
                case 6:
                    i = 50;
                    break;
                case 9:
                case 95:
                    i = 3;
                    break;
                case 16:
                    i = 95;
                    break;
                case 32:
                    i = 128;
                    break;
                case 36:
                    i = 130;
                    break;
                case 48:
                    i = 68;
                    break;
                case 57:
                    i = HotKeyConstant.K_DISP;
                    break;
                case 59:
                    break;
                case 66:
                    i = 4;
                    break;
                case 75:
                    i = 76;
                    break;
                default:
                    switch (i2) {
                        case 51:
                            i = HotKeyConstant.K_ACTION_RADIO;
                            break;
                        case 52:
                            i = 14;
                            break;
                        case 53:
                            i = 15;
                            break;
                        default:
                            i = 0;
                            break;
                    }
            }
        } else {
            i = 52;
        }
        realKeyLongClick1(context, i, getFrame()[3]);
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        updateDoorView(getContext());
    }

    private final void set0x11Data() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D_Speed_1");
        int i = 3;
        if (item != null) {
            item.setValue(getFrame()[3] + " km/h");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D_Speed_2");
        if (item2 != null) {
            item2.setValue(String.valueOf((getFrame()[10] * 256) + getFrame()[11]));
        }
        updateDriveDataActivity(null);
        Context context = getContext();
        switch (getFrame()[4]) {
            case 0:
            case 7:
            default:
                i = 0;
                break;
            case 1:
                i = 7;
                break;
            case 2:
                i = 8;
                break;
            case 3:
                break;
            case 4:
                i = HotKeyConstant.K_VOICE_PICKUP;
                break;
            case 5:
                i = 14;
                break;
            case 6:
                i = 15;
                break;
            case 8:
                i = 45;
                break;
            case 9:
                i = 46;
                break;
            case 10:
                i = 2;
                break;
        }
        realKeyLongClick1(context, i, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, getContext());
    }
}
