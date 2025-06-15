package com.hzbhd.canbus.car._337;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SetAlertView;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int nowValue = -1;
    String songTitle = "NOT YET";
    String model = "NOT YET";
    String song = "NOT YET";

    private int getEspResult(int i, int i2) {
        return (int) (((i & 255) << 8) + (i2 * 0.05d));
    }

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getRadarDistance(int i) {
        if (i == 1) {
            return 10;
        }
        if (i == 2) {
            return 7;
        }
        if (i == 3) {
            return 4;
        }
        return i == 4 ? 1 : 0;
    }

    private int getTenData(int i) {
        if (i == 16) {
            return 10;
        }
        if (i == 17) {
            return 11;
        }
        if (i == 18) {
            return 12;
        }
        if (i == 19) {
            return 13;
        }
        if (i == 20) {
            return 14;
        }
        if (i == 21) {
            return 15;
        }
        if (i == 22) {
            return 16;
        }
        if (i == 23) {
            return 17;
        }
        if (i == 24) {
            return 18;
        }
        if (i == 25) {
            return 19;
        }
        if (i == 32) {
            return 20;
        }
        return i;
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
        getUigMgr(this.mContext).makeConnection();
        getUigMgr(this.mContext).selectCarModel();
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initOriginalCarDevice();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i != 127) {
            switch (i) {
                case 33:
                    set0x21WheelKeyInfo();
                    break;
                case 34:
                    setOx22PanelKeyInfo();
                    break;
                case 35:
                    set0x23AirInfo();
                    break;
                default:
                    switch (i) {
                        case 38:
                            set0x26RearRadarInfo();
                            break;
                        case 39:
                            set0x27FrontRadarInfo();
                            break;
                        case 40:
                            set0x28BasicInfo();
                            break;
                        case 41:
                            set0x29CarInfo();
                            break;
                        default:
                            switch (i) {
                                case 48:
                                    set0x30EspInfo();
                                    break;
                                case 49:
                                    set0x31SettingInfo();
                                    break;
                                case 50:
                                    set0x32ScreenBrightness();
                                    break;
                                case 51:
                                    set0x33IDriveInfo();
                                    break;
                                case 52:
                                    set0x34RearAirInfo();
                                    break;
                                case 53:
                                    set0x35SeatInfo();
                                    break;
                                case 54:
                                    set0x36CarInfo();
                                    break;
                                case 55:
                                    set0x37AmplifierInfo();
                                    break;
                                case 56:
                                    set0x38CDInfo();
                                    break;
                                case 57:
                                    sei0x39CarInfo();
                                    break;
                                default:
                                    switch (i) {
                                        case 63:
                                            set0x03FConfigInfo();
                                            break;
                                        case 64:
                                            setRightCameraInfo();
                                            break;
                                        case 65:
                                            set360PanoramaInfo();
                                            break;
                                        case 66:
                                            set0x42PanoramaInfo();
                                            break;
                                        case 67:
                                            set0x43AlertInfo();
                                            break;
                                    }
                            }
                    }
            }
        }
        set0x7FVersionInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(1, 1);
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
            sendSourceInfo1Line(str2, str);
            sendSourceInfo2LineUnknown();
            sendRadioInfo(str2, str, i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(0, 1);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 0}, "Radio OFF".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineOFF();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(2, 16);
            getUigMgr(this.mContext).sendSourceInfo3Line(i4, i2, 0, 0, i / 60, i % 60);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 6}, str.getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2Line(str3);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(0, 16);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 0}, " switchiSourceng...".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineOFF();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(3, 34);
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "ATV Playing".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineUnknown();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        super.atvDestdroy();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(0, 33);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 0}, "ATV OFF".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineOFF();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(3, 33);
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "DTV Playing".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineUnknown();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(5, 64);
            String str = "";
            for (byte b : bArr) {
                str = str + (b - 48);
            }
            byte[] bytes = ("Incoming" + str).getBytes(StandardCharsets.UTF_8);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bytes));
            getUigMgr(this.mContext).sendPhoneInfo(SplicingByte(new byte[]{22, -59, 1, 1}, bytes));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(5, 64);
            String str = "";
            for (byte b : bArr) {
                str = str + (b - 48);
            }
            byte[] bytes = ("Dial" + str).getBytes(StandardCharsets.UTF_8);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bytes));
            getUigMgr(this.mContext).sendPhoneInfo(SplicingByte(new byte[]{22, -59, 2, 1}, bytes));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(5, 64);
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, i / 60, i % 60);
            String str = "";
            for (byte b : bArr) {
                str = str + (b - 48);
            }
            byte[] bytes = ("Talking With" + str).getBytes(StandardCharsets.UTF_8);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bytes));
            getUigMgr(this.mContext).sendPhoneInfo(SplicingByte(new byte[]{22, -59, 4, 1}, bytes));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(0, 64);
            String str = "";
            for (byte b : bArr) {
                str = str + (b - 48);
            }
            final byte[] bytes = ("Hang Up" + str).getBytes(StandardCharsets.UTF_8);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bytes));
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._337.MsgMgr.1
                /* JADX WARN: Type inference failed for: r6v0, types: [com.hzbhd.canbus.car._337.MsgMgr$1$1] */
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._337.MsgMgr.1.1
                        @Override // android.os.CountDownTimer
                        public void onTick(long j) {
                            MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneInfo(MsgMgr.this.SplicingByte(new byte[]{22, -59, 6, 1}, bytes));
                        }

                        @Override // android.os.CountDownTimer
                        public void onFinish() {
                            MsgMgr.this.getUigMgr(MsgMgr.this.mContext).sendPhoneInfo(MsgMgr.this.SplicingByte(new byte[]{22, -59, 6, 1}, bytes));
                        }
                    }.start();
                }
            });
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (getUigMgr(this.mContext).isCarModel8()) {
            if (b == 9) {
                getUigMgr(this.mContext).sendSourceCmd(9, 16);
                getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 10}, str4.getBytes(StandardCharsets.UTF_8)));
            } else {
                getUigMgr(this.mContext).sendSourceCmd(8, 16);
                getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 9}, str4.getBytes(StandardCharsets.UTF_8)));
            }
            int i4 = ((int) j2) / 1000;
            getUigMgr(this.mContext).sendSourceInfo3Line(i2, i, i4 / 60, i4 % 60, b4, b5);
            sendSourceInfo2Line(str6);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (getUigMgr(this.mContext).isCarModel8()) {
            if (b == 9) {
                getUigMgr(this.mContext).sendSourceCmd(9, 17);
                getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 10}, "Video Playing".getBytes(StandardCharsets.UTF_8)));
            } else {
                getUigMgr(this.mContext).sendSourceCmd(8, 17);
                getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 9}, "Video Playing".getBytes(StandardCharsets.UTF_8)));
            }
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, i, 0, b4, b5);
            sendSourceInfo2LineUnknown();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(7, 48);
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 8}, "Aux Playing".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineUnknown();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(0, 48);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 0}, "Aux OFF".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineOFF();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        if (getUigMgr(this.mContext).isCarModel8()) {
            getUigMgr(this.mContext).sendSourceCmd(11, 48);
            getUigMgr(this.mContext).sendSourceInfo3Line(0, 0, 0, 0, 0, 0);
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 11}, "A2DP Playing".getBytes(StandardCharsets.UTF_8)));
            sendSourceInfo2LineUnknown();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUigMgr(this.mContext).send0x83CarSettingInfo(3, i5, i6);
    }

    private void set0x43AlertInfo() {
        startMainActivity(this.mContext);
        if (getAlertContent().equals(this.mContext.getString(R.string._337_alert_0))) {
            return;
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._337.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new SetAlertView().showDialog(MsgMgr.this.getActivity(), MsgMgr.this.getAlertContent());
            }
        });
    }

    private void set360PanoramaInfo() {
        if (isPanoramicInfoChange()) {
            ArrayList arrayList = new ArrayList();
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
            } else if (i == 1) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, true));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
            }
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRightCameraInfo() {
        if (isPanoramicInfoChange()) {
            ArrayList arrayList = new ArrayList();
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, false));
            } else if (i == 1) {
                arrayList.add(new PanoramicBtnUpdateEntity(0, false));
                arrayList.add(new PanoramicBtnUpdateEntity(1, true));
            }
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x42PanoramaInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 0, getPanoramaInfo1()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 1, getPanoramaInfo2()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 2, getPanoramaInfo3()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 3, getPanoramaInfo4()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 4, getPanoramaInfo5()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_panorama_info"), 5, getPanoramaInfo6()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x7FVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x03FConfigInfo() {
        ArrayList arrayList = new ArrayList();
        if (!is404("_337_car_config", "_337_car_config0")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_config"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_config", "_337_car_config0"), getAmplifierState()).setValueStr(true));
        }
        if (!is404("_337_car_config", "_337_car_config1")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_config"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_config", "_337_car_config1"), getCdState()).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void sei0x39CarInfo() {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(1);
        GeneralOriginalCarDeviceData.mList = null;
        GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList();
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x38CDInfo() {
        OriginalDeviceData originalDeviceData;
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2) == 0) {
            originalDeviceData = this.mOriginalDeviceDataArray.get(0);
        } else {
            originalDeviceData = this.mOriginalDeviceDataArray.get(1);
        }
        GeneralOriginalCarDeviceData.mList = null;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        if (intFromByteWithBit == 0) {
            GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
            GeneralOriginalCarDeviceData.runningState = "OFF";
        } else if (intFromByteWithBit == 1) {
            GeneralOriginalCarDeviceData.cdStatus = "CD DISC";
            GeneralOriginalCarDeviceData.runningState = getJobState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList();
        } else if (intFromByteWithBit == 2) {
            GeneralOriginalCarDeviceData.cdStatus = "AUX";
            GeneralOriginalCarDeviceData.runningState = getJobState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 42));
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList();
        } else if (intFromByteWithBit == 3) {
            GeneralOriginalCarDeviceData.cdStatus = "MISC";
            GeneralOriginalCarDeviceData.runningState = getJobState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList();
        }
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
        originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
        originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2) == 0) {
            originalCarDevicePageUiSet.setHavePlayTimeSeekBar(false);
        } else {
            int[] iArr = this.mCanBusInfoInt;
            int msbLsbResult = getMsbLsbResult(iArr[7], iArr[8]);
            int[] iArr2 = this.mCanBusInfoInt;
            int msbLsbResult2 = getMsbLsbResult(iArr2[9], iArr2[10]);
            originalCarDevicePageUiSet.setHavePlayTimeSeekBar(true);
            int i = msbLsbResult2 / 60;
            GeneralOriginalCarDeviceData.startTime = this.df_2Integer.format(i / 60) + ":" + this.df_2Integer.format(i % 60) + ":" + this.df_2Integer.format(msbLsbResult2 % 60);
            int i2 = msbLsbResult / 60;
            GeneralOriginalCarDeviceData.endTime = this.df_2Integer.format(i2 / 60) + ":" + this.df_2Integer.format(i2 % 60) + ":" + this.df_2Integer.format(msbLsbResult % 60);
            if (msbLsbResult2 == 0) {
                GeneralOriginalCarDeviceData.progress = 0;
            } else {
                GeneralOriginalCarDeviceData.progress = (msbLsbResult2 * 100) / msbLsbResult;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x37AmplifierInfo() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.bandTreble = getTenData(this.mCanBusInfoInt[3]);
        GeneralAmplifierData.bandMiddle = getTenData(this.mCanBusInfoInt[4]);
        GeneralAmplifierData.bandBass = getTenData(this.mCanBusInfoInt[5]);
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[6] - 10;
        GeneralAmplifierData.frontRear = (-this.mCanBusInfoInt[7]) + 10;
        updateAmplifierActivity(new Bundle());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2) - 1)));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info4"), Integer.valueOf(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info5"), getAmplifierVoltage()).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_amplifier_info"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_amplifier_info", "_337_amplifier_info6"), getAmplifierTemperature()).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x36CarInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 0, getSlopeData()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 1, getTorqueState()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 2, getDipAngle()));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), 3, getTrailer()));
        int drivingPageIndexes = getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, 4, sb.append(getMsbLsbResult(iArr[6], iArr[7])).append("").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x34RearAirInfo() {
        if (getUigMgr(this.mContext).isH9()) {
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_dual = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            int i = this.mCanBusInfoInt[3];
            if (i == 1) {
                GeneralAirData.rear_left_blow_foot = false;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_left_blow_head = true;
                GeneralAirData.rear_right_blow_foot = false;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_right_blow_head = true;
            } else if (i == 2) {
                GeneralAirData.rear_left_blow_foot = true;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_left_blow_head = true;
                GeneralAirData.rear_right_blow_foot = true;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_right_blow_head = true;
            } else if (i == 3) {
                GeneralAirData.rear_left_blow_foot = true;
                GeneralAirData.rear_left_blow_window = false;
                GeneralAirData.rear_left_blow_head = false;
                GeneralAirData.rear_right_blow_foot = true;
                GeneralAirData.rear_right_blow_window = false;
                GeneralAirData.rear_right_blow_head = false;
            }
            GeneralAirData.rear_wind_level = this.mCanBusInfoInt[4];
            int i2 = this.mCanBusInfoInt[5];
            if (i2 == 0) {
                GeneralAirData.rear_temperature = "LOW";
            } else if (i2 == 255) {
                GeneralAirData.rear_temperature = "HIGH";
            } else {
                GeneralAirData.rear_temperature = this.df_2Decimal.format(((this.mCanBusInfoInt[5] - 116) * 0.5d) + 18.0d) + getTempUnitC(this.mContext);
            }
            updateAirActivity(this.mContext, 1002);
        }
    }

    private void set0x33IDriveInfo() {
        int i = this.nowValue;
        if (i == -1) {
            this.nowValue = this.mCanBusInfoInt[2];
            return;
        }
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = new int[iArr.length + 1];
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[1];
        int i2 = iArr[2];
        iArr2[2] = i2;
        iArr2[3] = 1;
        if (i > i2) {
            iArr2[3] = 1;
            realKeyLongClick1(this.mContext, 8, 1);
            iArr2[3] = 0;
            realKeyLongClick1(this.mContext, 0, 0);
        } else if (i < i2) {
            iArr2[3] = 1;
            realKeyLongClick1(this.mContext, 7, 1);
            iArr2[3] = 0;
            realKeyLongClick1(this.mContext, 0, 0);
        }
        this.nowValue = iArr2[2];
    }

    private void set0x35SeatInfo() {
        ArrayList arrayList = new ArrayList();
        if (!is404("_337_air", "_337_air_2")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_2"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_3")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_3"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_4")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_4"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_5")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_5"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_6")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_6"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_7")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_7"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_8")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_8"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_9")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_9"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        if (!is404("_337_air", "_337_air_12")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_12"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + this.mContext.getString(R.string._337_unit_level)).setValueStr(true));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x32ScreenBrightness() {
        if (getUigMgr(this.mContext).isH9()) {
            int i = this.mCanBusInfoInt[2];
            if (i >= 15 && i < 19) {
                FutureUtil.instance.setBrightness(1);
            } else if (i >= 19 && i < 38) {
                FutureUtil.instance.setBrightness(2);
            } else if (i >= 38 && i < 57) {
                FutureUtil.instance.setBrightness(3);
            } else if (i >= 57 && i < 76) {
                FutureUtil.instance.setBrightness(4);
            } else if (i >= 76 && i < 95) {
                FutureUtil.instance.setBrightness(5);
            }
        }
        if (getUigMgr(this.mContext).isCarModel3() || getUigMgr(this.mContext).isGreatWall() || getUigMgr(this.mContext).isH7()) {
            int i2 = this.mCanBusInfoInt[2];
            if (i2 >= 0 && i2 < 51) {
                FutureUtil.instance.setBrightness(1);
                return;
            }
            if (i2 >= 51 && i2 < 102) {
                FutureUtil.instance.setBrightness(2);
                return;
            }
            if (i2 >= 102 && i2 < 153) {
                FutureUtil.instance.setBrightness(3);
                return;
            }
            if (i2 >= 153 && i2 < 204) {
                FutureUtil.instance.setBrightness(4);
            } else {
                if (i2 < 204 || i2 >= 255) {
                    return;
                }
                FutureUtil.instance.setBrightness(5);
            }
        }
    }

    private void set0x31SettingInfo() {
        int length = this.mCanBusInfoInt.length;
        ArrayList arrayList = new ArrayList();
        if (length < 3) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_0")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 1)));
        }
        if (!is404("_337_car_setting", "_337_setting_1")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4) - 1)));
        }
        if (length < 4) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_2")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 1)));
        }
        if (!is404("_337_car_setting", "_337_setting_3")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 4)));
        }
        if (!is404("_337_car_setting", "_337_setting_4")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_5")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_6")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_7")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
        }
        if (length < 5) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_8")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_9")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_10")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        }
        if (!is404("_337_car_setting", "_337_setting_11")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_11"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_12")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_12"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_13")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_13"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_14")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_14"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
        }
        if (length < 6) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_15")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_15"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_16")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_16"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_17")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_17"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_18")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_18"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_19")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_19"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_20")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_20"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
        }
        if (length < 7) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_21")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_21"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_22")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_22"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_23")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_23"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2))));
        }
        if (!is404("_337_car_setting", "_337_setting_24")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_24"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_25")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_25"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_26")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_26"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_26_on_27")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_26_on_27"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
        }
        if (length < 8) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_27")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_27"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_28")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_28"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_29")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_29"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_30")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_30"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_31")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_31"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_32")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_32"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3))));
        }
        if (length < 9) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_33")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_33"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_34")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_34"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_35")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_35"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_36")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_36"), (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 5) - 10) + "").setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 5)));
        }
        if (length < 10) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_37")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_37"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_38")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_38"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_39")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_39"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_40")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_40"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_41")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_41"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2))));
        }
        if (!is404("_337_car_setting", "_337_setting_42")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_42"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2))));
        }
        if (length < 11) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_43")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_43"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 7, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_44")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_44"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_45")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_45"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2))));
        }
        if (!is404("_337_car_setting", "_337_setting_46")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_46"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2))));
        }
        if (!is404("_337_car_setting", "_337_setting_47")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_47"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1))));
        }
        if (length < 12) {
            updateSetting(arrayList);
            return;
        }
        if (!is404("_337_car_setting", "_337_setting_48")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_48"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_49")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_49"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 5, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_50")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_50"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 1))));
        }
        if (!is404("_337_car_setting", "_337_setting_51")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_car_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", "_337_setting_51"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x30EspInfo() {
        if (isTrackInfoChange()) {
            int i = this.eachId;
            if (i != 4 && i != 6 && i != 8 && i != 9 && i != 16) {
                byte[] bArr = this.mCanBusInfoByte;
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 5500, 16);
                updateParkUi(null, this.mContext);
            } else {
                if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
                    byte[] bArr2 = this.mCanBusInfoByte;
                    GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr2[3], bArr2[2], 0, 9994, 16);
                } else {
                    byte[] bArr3 = this.mCanBusInfoByte;
                    GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr3[3], bArr3[2], 0, 9994, 16);
                }
                updateParkUi(null, this.mContext);
            }
        }
    }

    private void set0x29CarInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 0, this.df_2Decimal.format((this.mCanBusInfoInt[2] * 0.75d) - 48.0d) + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 1, this.df_2Decimal.format(this.mCanBusInfoInt[3] - 40) + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 2, this.df_2Decimal.format(this.mCanBusInfoInt[4] * 0.25d) + "V"));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 3, this.df_2Decimal.format(this.mCanBusInfoInt[5] * 0.59d) + "Kpa"));
        arrayList.add(new DriverUpdateEntity(getUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info"), 4, this.mCanBusInfoInt[6] + "%"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x28BasicInfo() {
        if (isBasicInfoChange()) {
            GeneralDoorData.isShowHandBrake = true;
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x27FrontRadarInfo() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x26RearRadarInfo() {
        if (isRearRadarDataChange()) {
            return;
        }
        if (this.eachId == 3) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, getRadarDistance(this.mCanBusInfoInt[2]), getRadarDistance(this.mCanBusInfoInt[3]), getRadarDistance(this.mCanBusInfoInt[4]), getRadarDistance(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void set0x23AirInfo() {
        ArrayList arrayList = new ArrayList();
        if (!is404("_337_air", "_337_air_1")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_1"), ""));
        }
        if (!is404("_337_air", "_337_air_2")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_2"), ""));
        }
        if (!is404("_337_air", "_337_air_3")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_3"), ""));
        }
        if (!is404("_337_air", "_337_air_4")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_4"), ""));
        }
        if (!is404("_337_air", "_337_air_5")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_5"), ""));
        }
        if (!is404("_337_air", "_337_air_6")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_6"), ""));
        }
        if (!is404("_337_air", "_337_air_7")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_7"), ""));
        }
        if (!is404("_337_air", "_337_air_8")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_8"), ""));
        }
        if (!is404("_337_air", "_337_air_9")) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_337_air"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", "_337_air_9"), ""));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        if (this.mCanBusInfoInt[7] != 255) {
            updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[7] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        }
        this.mCanBusInfoInt[7] = 0;
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        switch (this.mCanBusInfoInt[3]) {
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_foot = false;
                break;
            case 7:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        int i = this.mCanBusInfoInt[5];
        if (i == 0) {
            GeneralAirData.front_right_temperature = "LOW";
        } else if (i == 255) {
            GeneralAirData.front_right_temperature = "HI";
        } else if (i < 112) {
            GeneralAirData.front_left_temperature = this.mCanBusInfoInt[5] + this.mContext.getString(R.string._337_air_level);
        } else {
            GeneralAirData.front_left_temperature = (((this.mCanBusInfoInt[5] - 112) * 0.5d) + 16.0d) + getTempUnitC(this.mContext);
        }
        int i2 = this.mCanBusInfoInt[6];
        if (i2 == 0) {
            GeneralAirData.front_right_temperature = "LOW";
        } else if (i2 == 255) {
            GeneralAirData.front_right_temperature = "HI";
        } else if (i2 < 112) {
            GeneralAirData.front_right_temperature = this.mCanBusInfoInt[6] + this.mContext.getString(R.string._337_air_level);
        } else {
            GeneralAirData.front_right_temperature = (((this.mCanBusInfoInt[6] - 112) * 0.5d) + 16.0d) + getTempUnitC(this.mContext);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setOx22PanelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 7) {
            buttonKey(129);
            return;
        }
        if (i == 9) {
            buttonKey(3);
            return;
        }
        if (i == 41) {
            buttonKey(21);
            return;
        }
        if (i == 33) {
            buttonKey(7);
            return;
        }
        if (i == 34) {
            buttonKey(8);
            return;
        }
        switch (i) {
            case 48:
                buttonKey(20);
                break;
            case 49:
                buttonKey(2);
                break;
            case 50:
                buttonKey(151);
                break;
            case 51:
                buttonKey(14);
                break;
            case 52:
                buttonKey(15);
                break;
            case 53:
                buttonKey(58);
                break;
            case 54:
                buttonKey(128);
                break;
            case 55:
                if (getUigMgr(this.mContext).isPortrait()) {
                    getUigMgr(this.mContext).sendPortraitAirInfo(10);
                    break;
                } else {
                    getUigMgr(this.mContext).sendAirInfo(28);
                    break;
                }
            case 56:
                if (getUigMgr(this.mContext).isPortrait()) {
                    getUigMgr(this.mContext).sendPortraitAirInfo(9);
                    break;
                } else {
                    getUigMgr(this.mContext).sendAirInfo(29);
                    break;
                }
            case 57:
                if (getUigMgr(this.mContext).isPortrait()) {
                    getUigMgr(this.mContext);
                    if (UiMgr.seatStatePortrait == 0) {
                        getUigMgr(this.mContext).sendPortraitAirInfo(7);
                        getUigMgr(this.mContext);
                        UiMgr.seatStatePortrait = 1;
                        break;
                    } else {
                        getUigMgr(this.mContext);
                        if (UiMgr.seatStatePortrait == 1) {
                            getUigMgr(this.mContext).sendPortraitAirInfo(8);
                            getUigMgr(this.mContext);
                            UiMgr.seatStatePortrait = 2;
                            break;
                        } else {
                            getUigMgr(this.mContext);
                            if (UiMgr.seatStatePortrait == 2) {
                                getUigMgr(this.mContext).sendPortraitAirInfo(32);
                                getUigMgr(this.mContext);
                                UiMgr.seatStatePortrait = 3;
                                break;
                            } else {
                                getUigMgr(this.mContext);
                                if (UiMgr.seatStatePortrait == 3) {
                                    getUigMgr(this.mContext).sendPortraitAirInfo(33);
                                    getUigMgr(this.mContext);
                                    UiMgr.seatStatePortrait = 4;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    getUigMgr(this.mContext);
                    if (UiMgr.seatState == 0) {
                        getUigMgr(this.mContext).sendAirInfo(21);
                        getUigMgr(this.mContext);
                        UiMgr.seatState = 1;
                        break;
                    } else {
                        getUigMgr(this.mContext);
                        if (UiMgr.seatState == 1) {
                            getUigMgr(this.mContext).sendAirInfo(24);
                            getUigMgr(this.mContext);
                            UiMgr.seatState = 2;
                            break;
                        } else {
                            getUigMgr(this.mContext);
                            if (UiMgr.seatState == 2) {
                                getUigMgr(this.mContext).sendAirInfo(25);
                                getUigMgr(this.mContext);
                                UiMgr.seatState = 3;
                                break;
                            } else {
                                getUigMgr(this.mContext);
                                if (UiMgr.seatState == 3) {
                                    getUigMgr(this.mContext).sendAirInfo(26);
                                    getUigMgr(this.mContext);
                                    UiMgr.seatState = 4;
                                    break;
                                } else {
                                    getUigMgr(this.mContext);
                                    if (UiMgr.seatState == 4) {
                                        getUigMgr(this.mContext).sendAirInfo(27);
                                        getUigMgr(this.mContext);
                                        UiMgr.seatState = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 58:
                buttonKey(50);
                break;
            case 59:
                buttonKey(27);
                break;
            case 60:
                buttonKey(4);
                break;
            default:
                switch (i) {
                    case 64:
                        buttonKey(45);
                        break;
                    case 65:
                        buttonKey(46);
                        break;
                    case 66:
                        buttonKey(47);
                        break;
                    case 67:
                        buttonKey(48);
                        break;
                    case 68:
                        buttonKey(49);
                        break;
                    case 69:
                        buttonKey(45);
                        break;
                    case 70:
                        buttonKey(45);
                        break;
                    case 71:
                        buttonKey(46);
                        break;
                    case 72:
                        buttonKey(46);
                        break;
                    case 73:
                        buttonKey(2);
                        break;
                }
        }
    }

    private void set0x21WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 7) {
            buttonKey(2);
            return;
        }
        switch (i) {
            case 9:
                buttonKey(14);
                break;
            case 10:
                buttonKey(15);
                break;
            case 11:
                buttonKey(45);
                break;
            case 12:
                buttonKey(46);
                break;
            case 13:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 14:
                buttonKey(3);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAlertContent() {
        int i = this.mCanBusInfoInt[2];
        if (i != 21) {
            switch (i) {
                case 1:
                    return this.mContext.getString(R.string._337_alert_1);
                case 2:
                    return this.mContext.getString(R.string._337_alert_2);
                case 3:
                    return this.mContext.getString(R.string._337_alert_3);
                case 4:
                    return this.mContext.getString(R.string._337_alert_4);
                case 5:
                    return this.mContext.getString(R.string._337_alert_5);
                case 6:
                    return this.mContext.getString(R.string._337_alert_6);
                case 7:
                    return this.mContext.getString(R.string._337_alert_7);
                case 8:
                    return this.mContext.getString(R.string._337_alert_8);
                case 9:
                    return this.mContext.getString(R.string._337_alert_9);
                default:
                    switch (i) {
                        case 16:
                            return this.mContext.getString(R.string._337_alert_10);
                        case 17:
                            return this.mContext.getString(R.string._337_alert_11);
                        case 18:
                            return this.mContext.getString(R.string._337_alert_12);
                        case 19:
                            return this.mContext.getString(R.string._337_alert_13);
                        default:
                            return this.mContext.getString(R.string._337_alert_0);
                    }
            }
        }
        return this.mContext.getString(R.string._337_alert_15);
    }

    private void sendRadioInfo(String str, String str2, int i) {
        int i2;
        int lsb;
        int msb;
        if (str2.trim().equals("FM1")) {
            i2 = 1;
            lsb = getLsb((int) (Double.parseDouble(str) * 100.0d));
            msb = getMsb((int) (Double.parseDouble(str) * 100.0d));
            MyLog.temporaryTracking(lsb + ":" + msb);
        } else if (str2.trim().equals("FM2")) {
            i2 = 2;
            lsb = getLsb((int) (Double.parseDouble(str) * 100.0d));
            msb = getMsb((int) (Double.parseDouble(str) * 100.0d));
        } else if (str2.trim().equals("FM3")) {
            i2 = 3;
            lsb = getLsb((int) (Double.parseDouble(str) * 100.0d));
            msb = getMsb((int) (Double.parseDouble(str) * 100.0d));
        } else if (str2.trim().equals("AM")) {
            i2 = 16;
            lsb = getLsb(Integer.parseInt(str));
            msb = getMsb(Integer.parseInt(str));
        } else if (str2.trim().equals("AM1")) {
            i2 = 17;
            lsb = getLsb(Integer.parseInt(str));
            msb = getMsb(Integer.parseInt(str));
        } else if (str2.trim().equals("AM2")) {
            i2 = 18;
            lsb = getLsb(Integer.parseInt(str));
            msb = getMsb(Integer.parseInt(str));
        } else if (str2.trim().equals("AM3")) {
            i2 = 19;
            lsb = getLsb(Integer.parseInt(str));
            msb = getMsb(Integer.parseInt(str));
        } else {
            i2 = 0;
            lsb = getLsb((int) (Double.parseDouble(str) * 100.0d));
            msb = getMsb((int) (Double.parseDouble(str) * 100.0d));
        }
        getUigMgr(this.mContext).sendRadioInfo(i2, lsb, msb, i);
    }

    private void sendSourceInfo1Line(String str, String str2) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        if (str2.trim().equals("FM1")) {
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 1}, bytes));
            return;
        }
        if (str2.trim().equals("FM2")) {
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 2}, bytes));
            return;
        }
        if (str2.trim().equals("FM3")) {
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 3}, bytes));
        } else if (str2.trim().equals("AM1")) {
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 4}, bytes));
        } else if (str2.trim().equals("AM2")) {
            getUigMgr(this.mContext).sendSourceInfo1Line(SplicingByte(new byte[]{22, -60, 5}, bytes));
        }
    }

    private void sendSourceInfo2Line(String str) {
        getUigMgr(this.mContext).sendSourceInfo2Line(SplicingByte(new byte[]{22, -58, 3}, str.getBytes(StandardCharsets.UTF_8)));
    }

    private void sendSourceInfo2LineUnknown() {
        getUigMgr(this.mContext).sendSourceInfo2Line(SplicingByte(new byte[]{22, -58, 3}, "unknown".getBytes(StandardCharsets.UTF_8)));
    }

    private void sendSourceInfo2LineOFF() {
        getUigMgr(this.mContext).sendSourceInfo2Line(SplicingByte(new byte[]{22, -58, 0}, "OFF".getBytes(StandardCharsets.UTF_8)));
    }

    private String getPanoramaInfo1() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._337_panorama_info1_1);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._337_panorama_info1_2);
        }
        return this.mContext.getString(R.string._337_panorama_info1_0);
    }

    private String getPanoramaInfo2() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._337_panorama_info2_1);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._337_panorama_info2_2);
        }
        if (intFromByteWithBit == 3) {
            return this.mContext.getString(R.string._337_panorama_info2_3);
        }
        return this.mContext.getString(R.string._337_panorama_info2_0);
    }

    private String getPanoramaInfo3() {
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 1) {
            return this.mContext.getString(R.string._337_panorama_info3_1);
        }
        return this.mContext.getString(R.string._337_panorama_info3_0);
    }

    private String getPanoramaInfo4() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._337_panorama_info4_1);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._337_panorama_info4_2);
        }
        if (intFromByteWithBit == 3) {
            return this.mContext.getString(R.string._337_panorama_info4_3);
        }
        if (intFromByteWithBit == 4) {
            return this.mContext.getString(R.string._337_panorama_info4_4);
        }
        return this.mContext.getString(R.string._337_panorama_info4_0);
    }

    private String getPanoramaInfo5() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._337_panorama_info5_1);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._337_panorama_info5_2);
        }
        return this.mContext.getString(R.string._337_panorama_info5_0);
    }

    private String getPanoramaInfo6() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._337_panorama_info2_1);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._337_panorama_info2_2);
        }
        if (intFromByteWithBit == 3) {
            return this.mContext.getString(R.string._337_panorama_info2_3);
        }
        return this.mContext.getString(R.string._337_panorama_info2_0);
    }

    private String getAmplifierState() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._337_car_config0_1);
        }
        return this.mContext.getString(R.string._337_car_config0_0);
    }

    private String getCdState() {
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            return this.mContext.getString(R.string._337_car_config1_1);
        }
        return this.mContext.getString(R.string._337_car_config1_0);
    }

    private String getJobState(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._337_cd_state_2);
            case 2:
                return this.mContext.getString(R.string._337_cd_state_3);
            case 3:
                return this.mContext.getString(R.string._337_cd_state_4);
            case 4:
                return this.mContext.getString(R.string._337_cd_state_5);
            case 5:
                return this.mContext.getString(R.string._337_cd_state_6);
            case 6:
                return this.mContext.getString(R.string._337_cd_state_7);
            case 7:
            default:
                return this.mContext.getString(R.string._337_cd_state_1);
            case 8:
                return this.mContext.getString(R.string._337_cd_state_8);
            case 9:
                return this.mContext.getString(R.string._337_cd_state_9);
        }
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceCdDvdUsbUpdateEntityList() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[1] == 56) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 2);
            if (intFromByteWithBit == 0) {
                this.model = this.mContext.getString(R.string._337_cd_state_10);
            } else if (intFromByteWithBit == 1) {
                this.model = this.mContext.getString(R.string._337_cd_state_11);
            } else if (intFromByteWithBit == 2) {
                this.model = this.mContext.getString(R.string._337_cd_state_12);
            }
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            StringBuilder sbAppend = sb.append(getMsbLsbResult(iArr2[5], iArr2[6])).append("/");
            int[] iArr3 = this.mCanBusInfoInt;
            this.song = sbAppend.append(getMsbLsbResult(iArr3[3], iArr3[4])).toString();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.songTitle));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.model));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.song));
        } else {
            this.songTitle = get0x38Result();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.songTitle));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.model));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.song));
        }
        return arrayList;
    }

    private String get0x38Result() {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            string = this.mContext.getString(R.string._337_cd_state_13);
        } else if (i == 2) {
            string = this.mContext.getString(R.string._337_cd_state_14);
        } else if (i == 3) {
            string = this.mContext.getString(R.string._337_cd_state_15);
        } else {
            string = i != 128 ? "-1" : this.mContext.getString(R.string._337_cd_state_16);
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        String string2 = sb.append(getMsbLsbResult(iArr[3], iArr[4])).append("").toString();
        String str = "%";
        for (int i2 = 5; i2 < this.mCanBusInfoInt.length; i2++) {
            if (i2 != 5) {
                str = str + "%";
            }
            str = str + String.format("%02x", Integer.valueOf(this.mCanBusInfoInt[i2]));
        }
        String uTF8Result = !str.equals("%") ? getUTF8Result(str) : "-1";
        if (string.equals("-1") && string2.equals("-1") && uTF8Result.equals("-1")) {
            return "-1";
        }
        if (string2.equals("-1")) {
            return string + "-" + uTF8Result;
        }
        if (string.equals("-1")) {
            return string2 + "." + uTF8Result;
        }
        if (uTF8Result.equals("-1")) {
            return string2 + "." + string;
        }
        return string2 + "." + string + "-" + uTF8Result;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._337.MsgMgr$3] */
    private void initOriginalCarDevice() {
        new Thread() { // from class: com.hzbhd.canbus.car._337.MsgMgr.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
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
            this.mOriginalCarDevicePageUiSet = getUigMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_337_Original_equipment2", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_337_Original_equipment0", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_337_Original_equipment1", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(0, new OriginalDeviceData(arrayList, new String[0]));
        this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(arrayList2, new String[]{OriginalBtnAction.PREV_DISC, "left", OriginalBtnAction.RANDOM, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.STOP, "right", OriginalBtnAction.NEXT_DISC}));
    }

    private Object getAmplifierVoltage() {
        if (this.mCanBusInfoInt[10] == 255) {
            return this.mContext.getString(R.string._337_drive_car_other1);
        }
        return (this.mCanBusInfoInt[10] * 0.25d) + "V";
    }

    private String getAmplifierTemperature() {
        if (this.mCanBusInfoInt[11] == 255) {
            return this.mContext.getString(R.string._337_drive_car_other1);
        }
        return (this.mCanBusInfoInt[11] - 40) + getTempUnitC(this.mContext);
    }

    private String getSlopeData() {
        String string;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            string = this.mContext.getString(R.string._337_drive_car_other7);
        } else {
            string = this.mContext.getString(R.string._337_drive_car_other6);
        }
        return string + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7) + "°";
    }

    private String getTorqueState() {
        if (this.mCanBusInfoInt[3] == 255) {
            return this.mContext.getString(R.string._337_drive_car_other1);
        }
        return this.mCanBusInfoInt[3] + "%";
    }

    private String getDipAngle() {
        String string;
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4])) {
            string = this.mContext.getString(R.string._337_drive_car_other2);
        } else {
            string = this.mContext.getString(R.string._337_drive_car_other3);
        }
        return string + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7) + "°";
    }

    private String getTrailer() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            return this.mContext.getString(R.string._337_drive_car_other5);
        }
        return this.mContext.getString(R.string._337_drive_car_other4);
    }

    private void updateSetting(List<SettingUpdateEntity> list) {
        updateGeneralSettingData(list);
        updateSettingActivity(null);
    }

    private boolean getCarSettingItemIsVisibility(String str) {
        return getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_setting", str) != -1;
    }

    private boolean getAirSettingItemIsVisibility(String str) {
        return getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_air", str) != -1;
    }

    private boolean getCarConfigInfoSettingItemIsVisibility(String str) {
        return getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_337_car_config", str) != -1;
    }

    public void showDialogAndRestartApp(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._337.MsgMgr.4
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new SetAlertView().showDialog(MsgMgr.this.getActivity(), str);
                new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._337.MsgMgr.4.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        System.exit(0);
                    }
                }.start();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUigMgr(Context context) {
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

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
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

    private void adjustBrightness() {
        int brightness = FutureUtil.instance.getBrightness();
        if (brightness == 5) {
            FutureUtil.instance.setBrightness(0);
        } else {
            FutureUtil.instance.setBrightness(brightness + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private String getUTF8Result(String str) {
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isAirDataChange() {
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
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
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

    public boolean is404(String str, String str2) {
        return getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUigMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }
}
