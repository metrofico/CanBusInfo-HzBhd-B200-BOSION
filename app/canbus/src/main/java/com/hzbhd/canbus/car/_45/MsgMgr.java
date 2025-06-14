package com.hzbhd.canbus.car._45;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int DoorIndoData0;
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarEpuInfo;
    Context mContext;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    private UiMgr mUiMgr;

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
        getUiMgr(this.mContext).makeConnection();
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
        initOriginalCarDevice();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        getUiMgr(this.mContext).send0x82MediaType(0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        int i3;
        int i4;
        super.radioInfoChange(i, str, str2, str3, i2);
        getUiMgr(this.mContext).send0x82MediaType(1);
        if (str.equals("FM1")) {
            i3 = 1;
        } else {
            i3 = (!str.equals("FM2") && (str.equals("FM3") || !(str.equals("AM1") || str.equals("AM2")))) ? 0 : 2;
        }
        if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3")) {
            i4 = Integer.parseInt(str2) * 100;
        } else {
            i4 = Integer.parseInt(str2);
        }
        getUiMgr(this.mContext).send0x83RadioInfo(i3, getMsb(i4), getLsb(i4), i, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).send0x82MediaType(2);
        if (z2) {
            int i8 = i / 60;
            getUiMgr(this.mContext).send0x84DiscInfo(0, 1, 0, 0, getMsb(i3), getLsb(i3), i8 / 60, i8 % 60, i % 60, 255, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0x82MediaType(3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            getUiMgr(this.mContext).send0x82MediaType(2);
        } else {
            getUiMgr(this.mContext).send0x82MediaType(4);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            getUiMgr(this.mContext).send0x82MediaType(2);
        } else {
            getUiMgr(this.mContext).send0x82MediaType(4);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).send0x82MediaType(5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        getUiMgr(this.mContext).send0x86VolControl(i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).send0x92TimeInfo(z ? 128 : 0, i5, i6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            set0x01WheelKeyInfo();
            return;
        }
        if (i == 2) {
            set0x02DoorInfo();
        } else if (i == 3) {
            set0x03CarEquipmentInfo();
        } else {
            if (i != 4) {
                return;
            }
            set0x04CarMediaInfo();
        }
    }

    private void set0x04CarMediaInfo() {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(2);
        GeneralOriginalCarDeviceData.cdStatus = getMediaType(this.mCanBusInfoInt[2]);
        GeneralOriginalCarDeviceData.runningState = getWordState(this.mCanBusInfoInt[3]);
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x03CarEquipmentInfo() {
        if (is0x03CarInfoChange()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment1"), getUsbState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment2"), getBtEpuState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment3"), getBtConnState(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_45_car_equipment"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_45_car_equipment4"), getBtPhoneState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x02DoorInfo() {
        int i = this.DoorIndoData0;
        int i2 = this.mCanBusInfoInt[2];
        if (i == i2) {
            return;
        }
        this.DoorIndoData0 = i2;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i2);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void set0x01WheelKeyInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(46);
                break;
            case 4:
                buttonKey(45);
                break;
            case 5:
                buttonKey(2);
                break;
            case 8:
                buttonKey(3);
                break;
            case 9:
                buttonKey(14);
                break;
            case 10:
                buttonKey(15);
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._45.MsgMgr$1] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._45.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment7", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment8", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment9", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment10", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_45_car_equipment11", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(2, new OriginalDeviceData(arrayList, new String[]{OriginalBtnAction.PREV_DISC, "left", OriginalBtnAction.PLAY_PAUSE, "right", OriginalBtnAction.NEXT_DISC}));
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

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceCdDvdUsbUpdateEntityList(int[] iArr) {
        String palyModel = getPalyModel(iArr[4]);
        String nowSongNumber = getNowSongNumber();
        String mediaTime = getMediaTime();
        String str = iArr[11] == 255 ? "100%" : Integer.toHexString(iArr[11]) + "%";
        String fileNumber = getFileNumber();
        ArrayList arrayList = new ArrayList();
        if (palyModel != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, palyModel));
        }
        if (nowSongNumber != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, nowSongNumber));
        }
        if (mediaTime != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, mediaTime));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str));
        }
        if (fileNumber != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, fileNumber));
        }
        return arrayList;
    }

    private String getMediaTime() {
        return (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4) == 15 ? "0" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4) == 15 ? "0" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4) + "") + ":" + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4) == 15 ? "0" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4) == 15 ? "0" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4) + "") + ":" + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 4) == 15 ? "0" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 4) != 15 ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 4) + "" : "0");
    }

    private String getFileNumber() {
        return (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 4) == 15 ? " " : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 4) == 15 ? " " : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 4, 4) == 15 ? " " : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 4) != 15 ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 4) + "" : " ");
    }

    private String getNowSongNumber() {
        return (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4) == 15 ? " " : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4) == 15 ? " " : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) == 15 ? " " : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4) + "") + (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4) != 15 ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4) + "" : " ");
    }

    private String getPalyModel(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._45_car_equipment7_0);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._45_car_equipment7_1);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._45_car_equipment7_2);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._45_car_equipment7_3);
        }
        return i == 4 ? this.mContext.getString(R.string._45_car_equipment7_4) : "NULL INFO";
    }

    private String getWordState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._45_car_equipment6_0);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._45_car_equipment6_1);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._45_car_equipment6_5);
        }
        if (i == 5) {
            return this.mContext.getString(R.string._45_car_equipment6_2);
        }
        if (i == 6) {
            return this.mContext.getString(R.string._45_car_equipment6_3);
        }
        if (i == 7) {
            return this.mContext.getString(R.string._45_car_equipment6_4);
        }
        return i == 128 ? "NO DATA" : "NO INFO";
    }

    private String getMediaType(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._45_car_equipment5_0);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._45_car_equipment5_1);
        }
        return i == 128 ? this.mContext.getString(R.string._45_car_equipment5_2) : "NO MEDIA";
    }

    private String getBtPhoneState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._45_car_equipment4_1);
        }
        return this.mContext.getString(R.string._45_car_equipment4_2);
    }

    private String getBtConnState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._45_car_equipment3_1);
        }
        return this.mContext.getString(R.string._45_car_equipment3_2);
    }

    private String getBtEpuState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._45_car_equipment2_1);
        }
        return this.mContext.getString(R.string._45_car_equipment2_2);
    }

    private String getUsbState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._45_car_equipment1_1);
        }
        return this.mContext.getString(R.string._45_car_equipment1_2);
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

    private boolean is0x03CarInfoChange() {
        if (Arrays.equals(this.mCarEpuInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarEpuInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
