package com.hzbhd.canbus.car._360;

import android.content.Context;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frameData;
    private int lastD10 = 255;
    private int lastD9 = 255;
    private HashMap<String, DriverDataPageUiSet.Page.Item> mDriveItemIndexHashMap = new HashMap<>();

    public final int[] getFrameData() {
        int[] iArr = this.frameData;
        if (iArr != null) {
            return iArr;
        }

        return null;
    }

    public final void setFrameData(int[] iArr) {

        this.frameData = iArr;
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
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (context == null || canbusInfo == null) {
            return;
        }
        setContext(context);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrameData(byteArrayToIntArray);
        int i = getFrameData()[1];
        if (i == 1) {
            carBodyStatus();
        } else {
            if (i != 32) {
                return;
            }
            steeringWheelKeys();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        initItemsIndexHashMap(context);
    }

    private final void steeringWheelKeys() {
        int i = getFrameData()[3];
        int i2 = getFrameData()[2];
        if (i2 != 128) {
            switch (i2) {
                case 0:
                    realKeyLongClick1(getContext(), 0, i);
                    break;
                case 1:
                    realKeyLongClick1(getContext(), 7, i);
                    break;
                case 2:
                    realKeyLongClick1(getContext(), 8, i);
                    break;
                case 3:
                    realKeyLongClick1(getContext(), 45, i);
                    break;
                case 4:
                    realKeyLongClick1(getContext(), 46, i);
                    break;
                case 5:
                    realKeyLongClick1(getContext(), 2, i);
                    break;
                case 6:
                    DataHandleUtils.knob(getContext(), 45, i);
                    break;
                case 7:
                    DataHandleUtils.knob(getContext(), 46, i);
                    break;
            }
        }
        startDrivingDataActivity(getContext(), 0);
    }

    public final int getLastD10() {
        return this.lastD10;
    }

    public final void setLastD10(int i) {
        this.lastD10 = i;
    }

    public final int getLastD9() {
        return this.lastD9;
    }

    public final void setLastD9(int i) {
        this.lastD9 = i;
    }

    private final void carBodyStatus() {
        updateSpeedInfo(getFrameData()[2]);
        int i = getFrameData()[2];
        int i2 = (getFrameData()[3] * 256) + getFrameData()[4];
        int i3 = (getFrameData()[5] * 256) + getFrameData()[6];
        int i4 = (getFrameData()[7] * 256) + getFrameData()[8];
        int i5 = (getFrameData()[9] * 256) + getFrameData()[10];
        int i6 = getFrameData()[11];
        int i7 = getFrameData()[12];
        boolean boolBit7 = DataHandleUtils.getBoolBit7(getFrameData()[12]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(getFrameData()[12]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(getFrameData()[12]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(getFrameData()[12]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(getFrameData()[12]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(getFrameData()[12]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrameData()[13], 0, 4);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(getFrameData()[13]);
        DriverDataPageUiSet.Page.Item item = this.mDriveItemIndexHashMap.get("D360_d0");

        item.setValue(i + " Km/H");
        DriverDataPageUiSet.Page.Item item2 = this.mDriveItemIndexHashMap.get("D360_d1t2");

        item2.setValue(i2 != 65535 ? i2 + " Km" : "----");
        DriverDataPageUiSet.Page.Item item3 = this.mDriveItemIndexHashMap.get("D360_d3t4");

        item3.setValue(i3 + " Km/H");
        DriverDataPageUiSet.Page.Item item4 = this.mDriveItemIndexHashMap.get("D360_d5t6");

        item4.setValue(i4 != 65535 ? (i4 / 10) + " L/100Km" : "----");
        DriverDataPageUiSet.Page.Item item5 = this.mDriveItemIndexHashMap.get("D360_d7t8");

        item5.setValue(i5 != 65535 ? (i5 / 10) + " L/100Km" : "----");
        if (!carBodyStatus$isOutTempMsgRepeat(i6, this)) {
            updateOutDoorTemp(getContext(), ((int) ((byte) i6)) + " °C");
        }
        if (!carBodyStatus$isDoorMsgRepeat(i7, this)) {
            GeneralDoorData.isRightFrontDoorOpen = boolBit7;
            GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
            GeneralDoorData.isRightRearDoorOpen = boolBit5;
            GeneralDoorData.isLeftRearDoorOpen = boolBit4;
            GeneralDoorData.isBackOpen = boolBit3;
            GeneralDoorData.isFrontOpen = boolBit2;
            updateDoorView(getContext());
        }
        DriverDataPageUiSet.Page.Item item6 = this.mDriveItemIndexHashMap.get("D360_d11b0t3");

        item6.setValue(intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? intFromByteWithBit != 3 ? intFromByteWithBit != 4 ? "无效" : "START" : "ON" : "ACC" : "OFF");
        DriverDataPageUiSet.Page.Item item7 = this.mDriveItemIndexHashMap.get("D360_d11b4");

        item7.setValue(boolBit42 ? "行驶状态" : "静止状态");
        updateDriveDataActivity(null);
    }

    private static final boolean carBodyStatus$isDoorMsgRepeat(int i, MsgMgr msgMgr) {
        if (i == msgMgr.lastD10) {
            return true;
        }
        msgMgr.lastD10 = i;
        return false;
    }

    private static final boolean carBodyStatus$isOutTempMsgRepeat(int i, MsgMgr msgMgr) {
        if (i == msgMgr.lastD9) {
            return true;
        }
        msgMgr.lastD9 = i;
        return false;
    }

    public final HashMap<String, DriverDataPageUiSet.Page.Item> getMDriveItemIndexHashMap() {
        return this.mDriveItemIndexHashMap;
    }

    public final void setMDriveItemIndexHashMap(HashMap<String, DriverDataPageUiSet.Page.Item> map) {

        this.mDriveItemIndexHashMap = map;
    }

    public final void initItemsIndexHashMap(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        List<DriverDataPageUiSet.Page> list = ((UiMgr) canUiMgr).getDriverDataPageUiSet(context).getList();
        Iterator<DriverDataPageUiSet.Page> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (DriverDataPageUiSet.Page.Item item : it.next().getItemList()) {
                int i4 = i3 + 1;
                HashMap<String, DriverDataPageUiSet.Page.Item> map = this.mDriveItemIndexHashMap;
                String titleSrn = item.getTitleSrn();

                DriverDataPageUiSet.Page.Item item2 = list.get(i).getItemList().get(i3);

                map.put(titleSrn, item2);
                i3 = i4;
            }
            i = i2;
        }
    }
}
