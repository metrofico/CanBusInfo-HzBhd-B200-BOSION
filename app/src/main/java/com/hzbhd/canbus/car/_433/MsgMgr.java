package com.hzbhd.canbus.car._433;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car._433.BluetoothView;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    BluetoothView bluetoothView;
    int differentId;
    int eachId;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    private UiMgr mUiMgr;
    byte[] songAlbumBytes;
    byte[] songArtistBytes;
    byte[] songNameBytes;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    int radioData0 = 0;
    int radioData1 = 0;
    int radioData2 = 0;
    int nowLight = 0;

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
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
        if (SharePreUtil.getIntValue(context, getUiMgr(this.mContext).KEY_BT_SWITCH, 0) == 1) {
            getUiMgr(context).send0xCABTCmd(3, 1);
        } else {
            getUiMgr(context).send0xCABTCmd(3, 2);
        }
        if (SharePreUtil.getIntValue(context, getUiMgr(this.mContext).KEY_BT_MEDIA_SWITCH, 0) == 1) {
            getUiMgr(context).send0xCABTCmd(2, 1);
        } else {
            getUiMgr(context).send0xCABTCmd(2, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            getUiMgr(this.mContext).send0xC0SourceType(0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        getUiMgr(this.mContext).send0xC0SourceType(1, 1);
        if (str.equals("FM")) {
            this.radioData0 = 0;
            this.radioData1 = getLsb((int) (Double.parseDouble(str2) * 100.0d));
            this.radioData2 = getMsb((int) (Double.parseDouble(str2) * 100.0d));
        } else if (str.equals("FM1")) {
            this.radioData0 = 1;
            this.radioData1 = getLsb((int) (Double.parseDouble(str2) * 100.0d));
            this.radioData2 = getMsb((int) (Double.parseDouble(str2) * 100.0d));
        } else if (str.equals("FM2")) {
            this.radioData0 = 2;
            this.radioData1 = getLsb((int) (Double.parseDouble(str2) * 100.0d));
            this.radioData2 = getMsb((int) (Double.parseDouble(str2) * 100.0d));
        } else if (str.equals("FM3")) {
            this.radioData0 = 0;
            this.radioData1 = getLsb((int) (Double.parseDouble(str2) * 100.0d));
            this.radioData2 = getMsb((int) (Double.parseDouble(str2) * 100.0d));
        } else {
            this.radioData0 = 16;
            this.radioData1 = getLsb((int) Double.parseDouble(str2));
            this.radioData2 = getMsb((int) Double.parseDouble(str2));
        }
        getUiMgr(this.mContext).send0xC2RadioInfo(this.radioData0, this.radioData1, this.radioData2, i);
        getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).send0xC0SourceType(2, 16);
        getUiMgr(this.mContext).send0xC3MediaInfo(i4, i2, 0, 0, i / 60, i % 60);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        getUiMgr(this.mContext).send0xC0SourceType(5, 64);
        getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
        getUiMgr(this.mContext).send0xCBSendString(1, SplicingByte(new byte[]{22, -53, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).send0xC0SourceType(6, 48);
        getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0xC0SourceType(7, 48);
        getUiMgr(this.mContext).send0xC3MediaInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).send0xC0SourceType(8, 19);
        getUiMgr(this.mContext).send0xC3MediaInfo(1, 101, getLsb(i), getMsb(i), b4, b5);
        try {
            this.songNameBytes = str4.getBytes("UTF-16LE");
            getUiMgr(this.mContext).send0xCBSendString(2, SplicingByte(new byte[]{22, -53, 2}, this.songNameBytes));
            this.songAlbumBytes = str5.getBytes("UTF-16LE");
            getUiMgr(this.mContext).send0xCBSendString(3, SplicingByte(new byte[]{22, -53, 3}, this.songAlbumBytes));
            this.songArtistBytes = str6.getBytes("UTF-16LE");
            getUiMgr(this.mContext).send0xCBSendString(4, SplicingByte(new byte[]{22, -53, 4}, this.songArtistBytes));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        getUiMgr(this.mContext).send0xC0SourceType(8, 19);
        getUiMgr(this.mContext).send0xC3MediaInfo(1, 101, getLsb(i), getMsb(i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        if (z) {
            getUiMgr(this.mContext).send0xC4VolInfo(0);
        } else {
            getUiMgr(this.mContext).send0xC4VolInfo(i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).send0xC8TimeInfo(0, !z ? 1 : 0, i5, i6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        try {
            setCanInfo();
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setCanInfo() {
        int i = this.mCanBusInfoInt[1];
        if (i == 20) {
            setBackLight();
            return;
        }
        if (i == 36) {
            set0x24BasicInfo();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo();
            return;
        }
        if (i == 211) {
            set0xD3BtInfo();
        } else if (i == 32) {
            set0x20WheelKeyInfo();
        } else {
            if (i != 33) {
                return;
            }
            set0x21OriginalInfo();
        }
    }

    private void set0xD3BtInfo() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            int i = iArr[3];
            if (i == 1) {
                mToast(this.mContext.getString(R.string._433_BT_Toast1));
            } else if (i == 2) {
                mToast(this.mContext.getString(R.string._433_BT_Toast2));
            }
        }
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2[2] == 2) {
            int i2 = iArr2[3];
            if (i2 == 1) {
                mToast(this.mContext.getString(R.string._433_BT_Toast3));
            } else if (i2 == 2) {
                mToast(this.mContext.getString(R.string._433_BT_Toast4));
            }
        }
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3[2] == 3) {
            int i3 = iArr3[3];
            if (i3 == 0) {
                mToast(this.mContext.getString(R.string._433_BT_Toast5));
                return;
            }
            if (i3 == 1) {
                mToast(this.mContext.getString(R.string._433_BT_Toast6));
                return;
            }
            if (i3 == 2) {
                mToast(this.mContext.getString(R.string._433_BT_Toast7));
                return;
            }
            if (i3 == 3) {
                mToast(this.mContext.getString(R.string._433_BT_Toast8));
            } else if (i3 == 4) {
                mToast(this.mContext.getString(R.string._433_BT_Toast9));
            } else {
                if (i3 != 5) {
                    return;
                }
                mToast(this.mContext.getString(R.string._433_BT_Toast10));
            }
        }
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x24BasicInfo() {
        this.mCanBusInfoInt[3] = 0;
        if (isBasicInfoChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x21OriginalInfo() {
        GeneralOriginalCarDeviceData.mList = getOriginalDeviceUsbUpdateEntityList();
        updateOriginalCarDeviceActivity(null);
        GeneralOriginalCarDeviceData.cdStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 1 ? "IPOD" : "USB";
        GeneralOriginalCarDeviceData.runningState = getRunningState();
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(33);
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._433.MsgMgr$1] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._433.MsgMgr.1
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
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_433_Original_divice1", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_433_Original_divice2", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_433_Original_divice3", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(33, new OriginalDeviceData(arrayList, new String[]{OriginalBtnAction.PREV_DISC, "left", OriginalBtnAction.PLAY, OriginalBtnAction.STOP, "right", OriginalBtnAction.NEXT_DISC}));
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

    private String getRunningState() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        if (intFromByteWithBit == 0) {
            return this.mContext.getString(R.string._433_Original_state0);
        }
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._433_Original_state1);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._433_Original_state2);
        }
        if (intFromByteWithBit == 3) {
            return this.mContext.getString(R.string._433_Original_state3);
        }
        if (intFromByteWithBit == 4) {
            return this.mContext.getString(R.string._433_Original_state4);
        }
        if (intFromByteWithBit == 5) {
            return this.mContext.getString(R.string._433_Original_state5);
        }
        return this.mContext.getString(R.string._433_Original_state6);
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceUsbUpdateEntityList() {
        String str = this.df_2Integer.format(this.mCanBusInfoInt[4]) + ":" + this.df_2Integer.format(this.mCanBusInfoInt[5]);
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        StringBuilder sbAppend = sb.append(getMsbLsbResult(iArr[6], iArr[7])).append("/");
        int[] iArr2 = this.mCanBusInfoInt;
        String string = sbAppend.append(getMsbLsbResult(iArr2[8], iArr2[9])).toString();
        String str2 = this.mCanBusInfoInt[11] + "%";
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        }
        if (string != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, string));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str2));
        }
        return arrayList;
    }

    private void set0x20WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(this.mContext, 45, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(this.mContext, 2, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, iArr[3]);
                break;
            case 10:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_OFF_RETURN, iArr[3]);
                break;
            case 11:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 12:
                realKeyLongClick1(this.mContext, 14, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_OFF_RETURN, iArr[3]);
                break;
        }
    }

    private void setBackLight() {
        int i = this.mCanBusInfoInt[2];
        if (i == this.nowLight) {
            return;
        }
        this.nowLight = i;
        setBacklightLevel((i / 51) + 1);
    }

    public void startPanel(final boolean z) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._433.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                if (MsgMgr.this.bluetoothView == null) {
                    MsgMgr.this.bluetoothView = new BluetoothView(MsgMgr.this.getActivity(), "", new BluetoothView.PanoramaListener() { // from class: com.hzbhd.canbus.car._433.MsgMgr.2.1
                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent1() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 1});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent2() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 6});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent3() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 2});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent4() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 9});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent5() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 5});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent6() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 8});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent7() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 16});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent8() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 7});
                        }

                        @Override // com.hzbhd.canbus.car._433.BluetoothView.PanoramaListener
                        public void clickEvent9() {
                            CanbusMsgSender.sendMsg(new byte[]{22, -52, 17});
                            MsgMgr.this.updateSettings(MsgMgr.this.getUiMgr(MsgMgr.this.mContext).getSettingLeftIndexes(MsgMgr.this.mContext, "_433_BT_Switch"), MsgMgr.this.getUiMgr(MsgMgr.this.mContext).getSettingRightIndex(MsgMgr.this.mContext, "_433_BT_Switch", "_433_BT_Switch3"), 0);
                        }
                    });
                }
                if (z) {
                    MsgMgr.this.bluetoothView.show();
                } else {
                    MsgMgr.this.bluetoothView.hide();
                }
            }
        });
    }

    private void mToast(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._433.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Toast.makeText(MsgMgr.this.mContext, str, 0).show();
            }
        });
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

    public void updateSettings(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
