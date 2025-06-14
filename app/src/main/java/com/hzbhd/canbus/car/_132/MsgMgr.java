package com.hzbhd.canbus.car._132;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mData1;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, -1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            if (getCurrentCanDifferentId() == 1) {
                setDrivingData();
                return;
            }
            return;
        }
        if (i == 32) {
            setWheelKey0x20();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i != 64) {
            switch (i) {
                case 36:
                    setDoorData0x02();
                    break;
                case 37:
                    setCdStatus();
                    break;
                case 38:
                    setCdTime();
                    break;
                case 39:
                    setCdTrackInfo();
                    break;
                case 40:
                    setCdTrackList();
                    break;
                case 41:
                    setTrackInfo();
                    break;
            }
            return;
        }
        if (getCurrentCanDifferentId() == 1) {
            setSettingData0x40();
        }
    }

    private void setDrivingData() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(((iArr[3] * 256) + iArr[2]) / 100).append("Km/h").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[5] * 256) + iArr2[4]).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(((iArr3[3] * 256) + iArr3[2]) / 100);
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick(20);
            return;
        }
        if (i == 4) {
            realKeyClick(21);
            return;
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        switch (i) {
            case 8:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick(14);
                break;
            case 10:
                realKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            default:
                switch (i) {
                    case 32:
                        realKeyClick(2);
                        break;
                    case 33:
                        realKeyClick(79);
                        break;
                    case 34:
                        realKeyClick(52);
                        break;
                    case 35:
                        realKeyClick(50);
                        break;
                    case 36:
                        realKeyClick(128);
                        break;
                    case 37:
                        realKeyClick(49);
                        break;
                    case 38:
                        realKeyClick(47);
                        break;
                    case 39:
                        realKeyClick(48);
                        break;
                    case 40:
                        realKeyClick(45);
                        break;
                    case 41:
                        realKeyClick(46);
                        break;
                    case 42:
                        realKeyClick(3);
                        break;
                    case 43:
                        realKeyClick2(46);
                        break;
                    case 44:
                        realKeyClick2(45);
                        break;
                    case 45:
                        realKeyClick2(7);
                        break;
                    case 46:
                        realKeyClick2(8);
                        break;
                }
        }
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
        if (getCurrentCanDifferentId() == 1) {
            updateParkUi(null, this.mContext);
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setSettingData0x40() {
        int[] iArr = {DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]) ? 1 : 0, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]) ? 1 : 0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) - 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) - 1, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new SettingUpdateEntity(0, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDoorData0x02() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        if (getCurrentCanDifferentId() == 1) {
            arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? "Not P" : "P"));
            arrayList.add(new DriverUpdateEntity(0, 3, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ? "ON" : "OFF"));
        } else {
            arrayList.add(new DriverUpdateEntity(0, 0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]) ? "Not P" : "P"));
            arrayList.add(new DriverUpdateEntity(0, 1, DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]) ? "ON" : "OFF"));
        }
        updateGeneralDriveData(arrayList);
        int i = this.mData1;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mData1 = i2;
            if (DataHandleUtils.getBoolBit0(i2)) {
                updateDoorView(this.mContext);
                return;
            }
            return;
        }
        updateDriveDataActivity(null);
    }

    private void setCdStatus() {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        if (originalCarDevicePageUiSet == null) {
            return;
        }
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            GeneralOriginalCarDeviceData.runningState = "DISC OUT";
        } else if (i == 4) {
            GeneralOriginalCarDeviceData.runningState = "READING";
        } else if (i == 5) {
            GeneralOriginalCarDeviceData.runningState = "PLAY";
            originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", OriginalBtnAction.PAUSE, "right"});
        } else if (i == 6) {
            GeneralOriginalCarDeviceData.runningState = "PAUSE";
            originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", OriginalBtnAction.PLAY, "right"});
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) == 0) {
            GeneralOriginalCarDeviceData.cdStatus = "CD";
        } else {
            cleanDevice();
            cleanSongList();
            GeneralOriginalCarDeviceData.cdStatus = "AUX";
            if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
                GeneralOriginalCarDeviceData.cdStatus = "AUX ON";
            } else {
                GeneralOriginalCarDeviceData.cdStatus = "AUX OFF";
            }
        }
        GeneralOriginalCarDeviceData.rpt_track = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralOriginalCarDeviceData.rdm_disc = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setCdTime() {
        ArrayList arrayList = new ArrayList();
        try {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr[10] * 256) + iArr[11]).append("").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append((iArr2[2] * 256) + iArr2[3]).append("").toString()));
        GeneralOriginalCarDeviceData.mList = arrayList;
        int[] iArr3 = this.mCanBusInfoInt;
        int i = (iArr3[4] * 3600) + (iArr3[5] * 60) + iArr3[6];
        int i2 = (iArr3[7] * 3600) + (iArr3[8] * 60) + iArr3[9];
        DecimalFormat decimalFormat = new DecimalFormat("00");
        if (i2 >= 3600) {
            GeneralOriginalCarDeviceData.startTime = decimalFormat.format((i2 / 60) / 60) + ":" + decimalFormat.format((i2 % 3600) / 60) + ":" + decimalFormat.format(i2 % 60);
        } else {
            GeneralOriginalCarDeviceData.startTime = decimalFormat.format((i2 % 3600) / 60) + ":" + decimalFormat.format(i2 % 60);
        }
        if (i >= 3600) {
            GeneralOriginalCarDeviceData.endTime = decimalFormat.format((i / 60) / 60) + ":" + decimalFormat.format((i % 3600) / 60) + ":" + decimalFormat.format(i % 60);
        } else {
            GeneralOriginalCarDeviceData.endTime = decimalFormat.format((i % 3600) / 60) + ":" + decimalFormat.format(i % 60);
        }
        if (i == 0) {
            GeneralOriginalCarDeviceData.progress = 0;
        } else {
            GeneralOriginalCarDeviceData.progress = (i2 * 100) / i;
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setCdTrackInfo() {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[2] == 1) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, getCdTrackInfo(this.mCanBusInfoByte)));
        } else {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, getCdTrackInfo(this.mCanBusInfoByte)));
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setCdTrackList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SongListEntity(getCdTrackInfo(this.mCanBusInfoByte)));
        GeneralOriginalCarDeviceData.songList = arrayList;
        if (getCurrentCanDifferentId() == 1) {
            updateOriginalCarDeviceActivity(null);
        }
    }

    private String getCdTrackInfo(byte[] bArr) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = bArr[i + 2];
        }
        return new String(bArr2);
    }

    private void cleanDevice() {
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.rdm_disc = false;
        GeneralOriginalCarDeviceData.rpt_track = false;
        GeneralOriginalCarDeviceData.startTime = "     ";
        GeneralOriginalCarDeviceData.endTime = "     ";
        GeneralOriginalCarDeviceData.progress = 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, " "));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, " "));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, " "));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, " "));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void cleanSongList() {
        if (GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
        }
    }

    void updateOriginalCarDevice(Bundle bundle) {
        updateOriginalCarDeviceActivity(bundle);
    }
}
