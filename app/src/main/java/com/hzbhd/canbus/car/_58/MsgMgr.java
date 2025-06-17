package com.hzbhd.canbus.car._58;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    TextView content;
    CountDownTimer countDownTimer;
    boolean currentPresetStore;
    AlertDialog dialog;
    int[] m0xB1Info;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mIsDoorFirst = true;
    private boolean mIsUsbFirst_0xa4 = true;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private byte[] mOriginalUsb_0xa4;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private byte[] mTrackDataNow;
    private String mUsbSonTimeNow;
    private String mUsbSongAlbumNow;
    private String mUsbSongArtistNow;
    private String mUsbSongTitleNow;
    private byte[] mVersionInfoNow;
    private int mWheelKeyNow;
    private byte[] mediaDataNow;
    private byte naviStatus;
    View view;

    private boolean isHaveMedia() {
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        AMapUtils.getInstance().startAMap(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        super.onAMapCallBack(aMapEntity);
        switch (aMapEntity.getCarDirection()) {
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 3});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 4});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 5});
                break;
            case 4:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 6});
                break;
            case 5:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 7});
                break;
            case 6:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 8});
                break;
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 1});
                break;
            case 8:
                CanbusMsgSender.sendMsg(new byte[]{22, -74, 2});
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setTrack0x11();
            return;
        }
        if (i == 18) {
            setDoorData0x12();
            return;
        }
        if (i == 114) {
            setKey0x72();
            setDriveData();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        } else if (i == 164) {
            if (is0xa4Repeat(bArr)) {
                return;
            }
            setMultimediaInfo();
        } else if (i == 177) {
            set0xB1Info();
        } else {
            if (i != 240) {
                return;
            }
            setVersionInfo0xF0();
        }
    }

    private void set0xB1Info() {
        if (is0xB1InfoNoChange()) {
            return;
        }
        if (this.view == null) {
            this.view = LayoutInflater.from(this.mContext).inflate(R.layout.dialog_text, (ViewGroup) null, true);
        }
        if (this.dialog == null) {
            this.dialog = new AlertDialog.Builder(this.mContext).setView(this.view).create();
        }
        if (this.content == null) {
            this.content = (TextView) this.view.findViewById(R.id.alert_content);
        }
        this.content.setText(getStr());
        this.dialog.setCancelable(false);
        this.dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.dialog.getWindow().setType(2003);
        this.dialog.show();
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._58.MsgMgr.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.dialog.dismiss();
            }
        };
        this.countDownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    private boolean is0xB1InfoNoChange() {
        if (Arrays.equals(this.m0xB1Info, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xB1Info = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private int getStr() {
        int i = this.mCanBusInfoInt[2];
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? R.string._58_new_function6 : R.string._58_new_function5 : R.string._58_new_function4 : R.string._58_new_function3 : R.string._58_new_function2 : R.string._58_new_function1;
    }

    private boolean isWheelKeyDataChange() {
        int i = this.mWheelKeyNow;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return i2 == 1 || i2 == 2 || i2 == 8 || i2 == 9 || i2 == 13 || i2 == 14;
        }
        this.mWheelKeyNow = i2;
        return true;
    }

    private void setKey0x72() {
        if (isWheelKeyDataChange()) {
            int i = this.mCanBusInfoInt[4];
            if (i == 0) {
                wheelKeyLongClick(0);
            }
            if (i == 1) {
                wheelKeyLongClick(7);
                return;
            }
            if (i == 2) {
                wheelKeyLongClick(8);
                return;
            }
            if (i == 5) {
                wheelKeyLongClick(14);
                return;
            }
            if (i == 6) {
                wheelKeyLongClick(HotKeyConstant.K_PHONE_OFF_RETURN);
                return;
            }
            switch (i) {
                case 8:
                    wheelKeyLongClick(46);
                    break;
                case 9:
                    wheelKeyLongClick(45);
                    break;
                case 10:
                    wheelKeyLongClick(2);
                    break;
                case 11:
                    wheelKeyLongClick(HotKeyConstant.K_SPEECH);
                    break;
            }
        }
    }

    private void setDriveData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + "km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrack0x11() {
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(bArr[9], bArr[8], 0, 5200, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setDoorData0x12() {
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setMultimediaInfo() throws Resources.NotFoundException {
        String string;
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string._55_0xa4_item1);
        } else {
            string = this.mContext.getResources().getString(R.string._55_0xa4_item2);
        }
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, string));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb.append((iArr[8] * 256) + iArr[7]).append("").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, sb2.append((iArr2[6] * 256) + iArr2[5]).append("").toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, sb3.append(startEndTimeMethod((iArr3[9] * 60) + iArr3[10])).append("").toString()));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.mCanBusInfoInt[11] + "%"));
        GeneralOriginalCarDeviceData.runningState = runningStateUsb(this.mCanBusInfoInt[12]);
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) == 13) {
            GeneralOriginalCarDeviceData.cdStatus = "Usb";
            this.naviStatus = (byte) 21;
        } else {
            GeneralOriginalCarDeviceData.cdStatus = "Ipod";
            this.naviStatus = (byte) 22;
        }
        int i = this.mCanBusInfoInt[12];
        if (i == 6) {
            GeneralOriginalCarDeviceData.power = false;
        } else if (i == 2) {
            GeneralOriginalCarDeviceData.power = true;
        }
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
        enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(new byte[]{22, -31, this.naviStatus}, 15));
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte) i5, (byte) i6, (byte) i7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (isHaveMedia()) {
            sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.toString(), new byte[]{22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            reportID3Info("", "", "", true, "ascii");
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i == 256) {
            i = 0;
        }
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = new DecimalFormat("00").format(i) + " " + str2 + "     ";
            } else {
                str4 = new DecimalFormat("00").format(i) + " 0" + str2 + "     ";
            }
        } else if (str2.length() == 5) {
            str4 = new DecimalFormat("00").format(i) + "  " + str2.substring(0, str2.length() - 1) + "    ";
        } else {
            str4 = new DecimalFormat("00").format(i) + " " + str2.substring(0, str2.length() - 1) + "    ";
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, allBandTypeData}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        Log.d("_55_aux", "  .");
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("AUX".getBytes(), 0, bArr, 0, "AUX".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        Log.d("_55_atv", "  .");
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("A2DP".getBytes(), 0, bArr, 0, "A2DP".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = new DecimalFormat("00").format(b4);
        String str8 = new DecimalFormat("00").format(b5);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format(((b7 & 255) * 256) + (i & 255)), new Object[0]) + " " + str7 + str8 + "    ").getBytes()));
        if (!str8.equals(this.mUsbSonTimeNow)) {
            this.mUsbSonTimeNow = str8;
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), new byte[]{22, -104, 0, 0, 0, 0, b4, b5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        }
        reportID3Info(str4, str5, str6, false, "unicode");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        reportID3Info("", "", "", true, "ascii");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        String str5 = new DecimalFormat("00").format(b4);
        String str6 = new DecimalFormat("00").format(b5);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + " " + str5 + str6 + "    ").getBytes()));
        if (str6.equals(this.mUsbSonTimeNow)) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, b4, b5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        this.mUsbSonTimeNow = str6;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -79, 1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -79, 2, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        super.cameraInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -79, 4, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{22, -79, 3, 0});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -79, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        String str4 = b == 16 ? "LOADING " : b == 17 ? "EJECTING" : "";
        if (i7 == 48) {
            str4 = "PAUSE   ";
        } else if (i7 == 65) {
            str4 = "STOP    ";
        } else if (i7 == 241) {
            str4 = "ERROR   ";
        }
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 6}, DataHandleUtils.byteMerger(str4.getBytes(), new byte[]{0, 0, 0, 0})));
        DecimalFormat decimalFormat = new DecimalFormat("00");
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 6}, DataHandleUtils.byteMerger((new DecimalFormat("000").format(i4) + " " + decimalFormat.format(i / 60) + decimalFormat.format(i & 60)).getBytes(), new byte[]{0, 0, 0, 0})));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, ("    " + new DecimalFormat("00").format((i / 60) % 60) + new DecimalFormat("00").format(i % 60) + "    ").getBytes()));
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    private void wheelKeyShortClick(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void wheelKeyLongClick(int i) {
        realKeyLongClick2(this.mContext, i);
    }

    private boolean isDoorDataChange() {
        if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[8], bArr[9]};
        if (Arrays.equals(this.mTrackDataNow, bArr2)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(bArr2, 2);
        return true;
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [com.hzbhd.canbus.car._58.MsgMgr$2] */
    private void reportID3Info(final String str, final String str2, final String str3, final boolean z, final String str4) {
        new Thread() { // from class: com.hzbhd.canbus.car._58.MsgMgr.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    if (z) {
                        sleep(1000L);
                    }
                    if (str.equals(MsgMgr.this.mUsbSongTitleNow) && str2.equals(MsgMgr.this.mUsbSongAlbumNow) && str3.equals(MsgMgr.this.mUsbSongArtistNow)) {
                        return;
                    }
                    MsgMgr.this.mUsbSongTitleNow = str;
                    MsgMgr.this.mUsbSongAlbumNow = str2;
                    MsgMgr.this.mUsbSongArtistNow = str3;
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -30, str, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -29, str2, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -28, str3, str4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str, String str2) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, b}, DataHandleUtils.exceptBOMHead(str.getBytes(str2))), 34));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean is0xa4Repeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mOriginalUsb_0xa4)) {
            return true;
        }
        this.mOriginalUsb_0xa4 = Arrays.copyOf(bArr, bArr.length);
        if (!this.mIsUsbFirst_0xa4) {
            return false;
        }
        this.mIsUsbFirst_0xa4 = false;
        return true;
    }

    private String runningStateUsb(int i) {
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._123_divice_status_5);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._123_divice_status_1);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._55_0xa4_item3);
        }
        if (i == 6) {
            return this.mContext.getResources().getString(R.string._118_setting_value_95);
        }
        if (i == 9) {
            return this.mContext.getResources().getString(R.string._55_0xa4_item4);
        }
        if (i == 255) {
            return this.mContext.getResources().getString(R.string.invalid);
        }
        if (i != 12) {
            return i != 13 ? "" : this.mContext.getResources().getString(R.string._118_setting_value_91);
        }
        return this.mContext.getResources().getString(R.string._123_divice_status_4);
    }

    private void sendMediaMsg1(Context context, String str, byte[] bArr) {
        Log.i("AbstractMsgMgr", "sendMediaMsg: context: " + context + ", media: " + str);
        if (context == null) {
            return;
        }
        String string = FutureUtil.instance.getCurrentValidSource().toString();
        Log.i("AbstractMsgMgr", "sendMediaMsg: frontSeat:" + string);
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if ((str.equals(string) || SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name().equals(str)) && !Arrays.equals(bArr, this.mediaDataNow)) {
                CanbusMsgSender.sendMsg(bArr);
                this.mediaDataNow = Arrays.copyOf(bArr, bArr.length);
                if (SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str)) {
                    return;
                }
                Settings.System.putString(context.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(bArr, 0));
                if (SourceConstantsDef.SOURCE_ID.MPEG.toString().equals(string)) {
                    return;
                }
                Settings.System.putString(context.getContentResolver(), "reportForDiscEject", Base64.encodeToString(bArr, 0));
            }
        }
    }
}
