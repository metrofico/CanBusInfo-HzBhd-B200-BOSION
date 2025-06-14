package com.hzbhd.canbus.car._16;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.receiver.AMapBroadcastReceiver;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int SHARE_16_AMPLIFIER_BAND_OFFSET = 2;
    static final int SHARE_16_AMPLIFIER_FADE_OFFSET = 7;
    static final String SHARE_16_IS_HAVE_SPARE_TIRE = "share_16_is_have_spare_tire";
    static final String SHARE_16_IS_SUPPOT_HYBRID = "share_16_is_suppot_hybrid";
    static final String SHARE_16_IS_SUPPOT_TIRE = "share_16_is_suppot_tire";
    private String btMusicAlbum;
    private String btMusicArtist;
    private String btMusicTitle;
    private int[] m0x62DataNow;
    private int[] m0x66DataNow;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackOpen;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusAirInfoCopy3;
    private Context mContext;
    private byte mCurrBandByte;
    private int[] mFrontRadarDataNow;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private String mMileageUnit;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private boolean mPanoramicStatusNow;
    private int[] mRearRadarDataNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private int mStoagePath;
    private TireAlramView mTireAlramView;
    private int mTireStatus;
    private UiMgr mUiMgr;
    private byte[] mVersionInfoNow;
    private int mdifferentId;
    private String musicAlbum;
    private String musicArtist;
    private int musicCount;
    private int musicIndex;
    private String musicTitle;
    private boolean tempRear;
    private static final Charset CHARSETNAME = StandardCharsets.UTF_16LE;
    private static String SHARE_16_LANGUAGE = "share_16_language";
    static boolean DevicePower = false;
    static boolean DeviceRearLock = false;
    static int mOriginalInt = -1;
    private boolean mIsAirFirst = true;
    private int videoPlayIndex = -1;
    private int videoTotalCount = -1;
    private boolean isStartAMap = false;

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getNaviDirection(int i) {
        if (i == 19) {
            return 9;
        }
        switch (i) {
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 2;
            case 5:
                return 3;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 1;
            default:
                return 0;
        }
    }

    private float getTireRule(int i) {
        if (i != 0) {
            return i != 2 ? 1.0f : 0.4f;
        }
        return 10.0f;
    }

    private String getTireUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : " KPA" : " PSI" : " BAR";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        initAmplifierData(context);
        portraitCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -125, 50, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mdifferentId = getCurrentCanDifferentId();
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        registerAMap(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        unRegisterAMap(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i11 = this.mdifferentId;
        if (i11 == 1 || i11 == 2 || i11 == 9) {
            Log.d("Lai5", this.mdifferentId + "" + this.mCanId);
            if (z) {
                CanbusMsgSender.sendMsg(new byte[]{22, -99, (byte) i5, (byte) i6, 0});
            } else {
                CanbusMsgSender.sendMsg(new byte[]{22, -99, (byte) i5, (byte) i6, 1});
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        str.hashCode();
        switch (str) {
            case "rear.windows.close":
                sendVoiceMsg(4, false);
                sendVoiceMsg(5, false);
                break;
            case "rear.right.window.close":
                sendVoiceMsg(5, false);
                break;
            case "front_defog":
                sendAirCommand(19);
                break;
            case "air.ac.on":
            case "air.ac.off":
                sendAirCommand(23);
                break;
            case "skylight.open":
                sendVoiceMsg(6, true);
                break;
            case "front.right.window.close":
                sendVoiceMsg(3, false);
                break;
            case "front.windows.close":
                sendVoiceMsg(2, false);
                sendVoiceMsg(3, false);
                break;
            case "rear.right.window.open":
                sendVoiceMsg(5, true);
                break;
            case "front.windows.open":
                sendVoiceMsg(2, true);
                sendVoiceMsg(3, true);
                break;
            case "air_right_temperature_up":
                sendAirCommand(5);
                break;
            case "rear_defog":
                sendAirCommand(20);
                break;
            case "skylight.close":
                sendVoiceMsg(6, false);
                break;
            case "auto":
                sendAirCommand(21);
                break;
            case "dual":
                sendAirCommand(16);
                break;
            case "air_left_temperature_down":
                sendAirCommand(2);
                break;
            case "rear.left.window.close":
                sendVoiceMsg(4, false);
                break;
            case "front.left.window.open":
                sendVoiceMsg(2, true);
                break;
            case "in_out_cycle":
                sendAirCommand(25);
                break;
            case "front.right.window.open":
                sendVoiceMsg(3, true);
                break;
            case "ac.windlevel.down":
                sendAirCommand(9);
                break;
            case "air_left_temperature_up":
                sendAirCommand(3);
                break;
            case "ac.windlevel.up":
                sendAirCommand(10);
                break;
            case "all.windows.close":
                sendVoiceMsg(1, false);
                break;
            case "rear.windows.open":
                sendVoiceMsg(4, true);
                sendVoiceMsg(5, true);
                break;
            case "rear.left.window.open":
                sendVoiceMsg(4, true);
                break;
            case "all.windows.open":
                sendVoiceMsg(1, true);
                break;
            case "air_right_temperature_down":
                sendAirCommand(4);
                break;
            case "front.left.window.close":
                sendVoiceMsg(2, false);
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._16.MsgMgr$1] */
    private void sendAirCommand(final int i) {
        new Thread() { // from class: com.hzbhd.canbus.car._16.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 1});
                try {
                    sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte) i, 0});
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        try {
            int i = byteArrayToIntArray[1];
            if (i != 88) {
                if (i == 102) {
                    setOriginal0x66();
                } else if (i == 97) {
                    setCDState0x61();
                } else if (i != 98) {
                    switch (i) {
                        case 29:
                            setFrontRadarData0x1D();
                            break;
                        case 30:
                            setRearRadarData0x1E();
                            break;
                        case 31:
                            setHybrid0x1F();
                            break;
                        case 32:
                            setKeyEvent0x20();
                            break;
                        case 33:
                            setMileageInfo0x21();
                            break;
                        case 34:
                            setInstantOilInfo0x22();
                            break;
                        case 35:
                            setHistoryOilInfo0x23();
                            break;
                        case 36:
                            setCarInfo0x24();
                            break;
                        case 37:
                            setTPMSInfo0x25();
                            break;
                        case 38:
                            setVehicleSettings0x26();
                            break;
                        case 39:
                            set15minOilInfo0x27();
                            break;
                        case 40:
                            if (!isAirMsgRepeat(bArr)) {
                                setAirData0x28();
                                break;
                            } else {
                                return;
                            }
                        case 41:
                            setTrackData0x29();
                            break;
                        case 42:
                            setPanelInfoBtn0x2a();
                            break;
                        case 43:
                            setElectric0x2B();
                            break;
                        default:
                            switch (i) {
                                case 47:
                                    setMediaCommand0x2F();
                                    break;
                                case 48:
                                    setVersionInfo0x30();
                                    break;
                                case 49:
                                    setAmplifierData0x31();
                                    break;
                                case 50:
                                    setPanoramic0x32();
                                    break;
                                case 51:
                                    setPanelInfo0x33();
                                    break;
                                default:
                                    switch (i) {
                                        case 80:
                                            setSettingStatus0x50();
                                            break;
                                        case 81:
                                            setKeyAirEvent0x51();
                                            break;
                                        case 82:
                                            setSettingStatus0x52();
                                            break;
                                    }
                            }
                    }
                } else {
                    setOriginal0x62();
                }
            } else if (isAirMsgRepeat2(bArr)) {
            } else {
                setAirData0x58();
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setPanelInfo0x33() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick4(this.mContext, 0);
            return;
        }
        if (i == 1) {
            realKeyClick4(this.mContext, 45);
            return;
        }
        if (i == 2) {
            realKeyClick4(this.mContext, 46);
            return;
        }
        if (i == 3) {
            realKeyClick4(this.mContext, 47);
        } else if (i == 4) {
            realKeyClick4(this.mContext, 48);
        } else {
            if (i != 5) {
                return;
            }
            realKeyClick4(this.mContext, 49);
        }
    }

    private void setOriginal0x66() {
        Context context;
        int i;
        Context context2;
        int i2;
        if (is0x66DataChange()) {
            GeneralOriginalCarDeviceData.cdStatus = getPlayMode0x66();
            GeneralOriginalCarDeviceData.runningState = getRunningState0x66();
            DevicePower = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            DeviceRearLock = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            setOriginalPage0x66();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._16_title_12) + ": " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)));
            StringBuilder sbAppend = new StringBuilder().append(this.mContext.getString(R.string._16_title_13)).append(": ");
            if (DevicePower) {
                context = this.mContext;
                i = R.string.english_on;
            } else {
                context = this.mContext;
                i = R.string.english_off;
            }
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sbAppend.append(context.getString(i)).toString()));
            StringBuilder sbAppend2 = new StringBuilder().append(this.mContext.getString(R.string._16_title_14)).append(": ");
            if (DeviceRearLock) {
                context2 = this.mContext;
                i2 = R.string._16_value_16;
            } else {
                context2 = this.mContext;
                i2 = R.string._16_value_15;
            }
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, sbAppend2.append(context2.getString(i2)).toString()));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, ""));
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, ""));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setOriginal0x62() {
        Context context;
        int i;
        Context context2;
        int i2;
        if (is0x62DataChange()) {
            GeneralOriginalCarDeviceData.cdStatus = getPlayMode();
            int i3 = this.mCanBusInfoInt[2];
            int i4 = 0;
            if (i3 == 1) {
                mOriginalInt = 1;
                setRadioPage();
                int[] iArr = this.mCanBusInfoInt;
                if (iArr.length >= 4) {
                    int i5 = iArr[3];
                    if (i5 == 0) {
                        GeneralOriginalCarDeviceData.runningState = getRadioStatus();
                        ArrayList arrayList = new ArrayList();
                        StringBuilder sbAppend = new StringBuilder().append(this.mContext.getString(R.string._16_title_1)).append(": ");
                        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
                            context = this.mContext;
                            i = R.string._16_value_1;
                        } else {
                            context = this.mContext;
                            i = R.string._16_value_2;
                        }
                        arrayList.add(new OriginalCarDeviceUpdateEntity(0, sbAppend.append(context.getString(i)).toString()));
                        StringBuilder sbAppend2 = new StringBuilder().append(this.mContext.getString(R.string._16_title_2)).append(": ");
                        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                            context2 = this.mContext;
                            i2 = R.string._16_value_3;
                        } else {
                            context2 = this.mContext;
                            i2 = R.string._16_value_4;
                        }
                        arrayList.add(new OriginalCarDeviceUpdateEntity(1, sbAppend2.append(context2.getString(i2)).toString()));
                        arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.mContext.getString(R.string._16_title_3) + ": " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)));
                        int[] iArr2 = this.mCanBusInfoInt;
                        if (iArr2[7] != 255 && iArr2[6] != 255) {
                            if (iArr2[4] == 16) {
                                StringBuilder sbAppend3 = new StringBuilder().append(this.mContext.getString(R.string._16_title_4)).append(": ");
                                int[] iArr3 = this.mCanBusInfoInt;
                                arrayList.add(new OriginalCarDeviceUpdateEntity(3, sbAppend3.append((iArr3[7] * 256) + iArr3[6]).append("KHz").toString()));
                            } else {
                                StringBuilder sbAppend4 = new StringBuilder().append(this.mContext.getString(R.string._16_title_4)).append(": ");
                                int[] iArr4 = this.mCanBusInfoInt;
                                arrayList.add(new OriginalCarDeviceUpdateEntity(3, sbAppend4.append(((iArr4[7] * 256) + iArr4[6]) / 100).append("MHz").toString()));
                            }
                        }
                        arrayList.add(new OriginalCarDeviceUpdateEntity(4, ""));
                        arrayList.add(new OriginalCarDeviceUpdateEntity(5, ""));
                        arrayList.add(new OriginalCarDeviceUpdateEntity(6, ""));
                        GeneralOriginalCarDeviceData.mList = arrayList;
                    } else if (i5 == 1) {
                        GeneralOriginalCarDeviceData.runningState = getRadioStatus();
                        ArrayList arrayList2 = new ArrayList();
                        int i6 = 3;
                        while (i6 < 14) {
                            int[] iArr5 = this.mCanBusInfoInt;
                            i6 += 2;
                            int i7 = i6 + 1;
                            if (iArr5[i7] != 255 && iArr5[i6] != 255) {
                                if (iArr5[4] == 16) {
                                    StringBuilder sb = new StringBuilder();
                                    int[] iArr6 = this.mCanBusInfoInt;
                                    arrayList2.add(new SongListEntity(sb.append((iArr6[i7] * 256) + iArr6[i6]).append("KHz").toString()));
                                } else {
                                    StringBuilder sb2 = new StringBuilder();
                                    int[] iArr7 = this.mCanBusInfoInt;
                                    arrayList2.add(new SongListEntity(sb2.append(((iArr7[i7] * 256) + iArr7[i6]) / 100).append("MHz").toString()));
                                }
                            }
                        }
                        GeneralOriginalCarDeviceData.songList = arrayList2;
                    }
                }
            } else if (i3 == 2) {
                mOriginalInt = 2;
                setCdPage();
                ArrayList arrayList3 = new ArrayList();
                for (int i8 = 1; i8 <= 6; i8++) {
                    arrayList3.add(new SongListEntity("DISC" + i8));
                }
                GeneralOriginalCarDeviceData.songList = arrayList3;
                ArrayList arrayList4 = new ArrayList();
                int[] iArr8 = this.mCanBusInfoInt;
                if (iArr8.length >= 4) {
                    int i9 = iArr8[3];
                    if (i9 == 0) {
                        GeneralOriginalCarDeviceData.runningState = "CD";
                        arrayList4.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._16_title_5) + ": " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)));
                        arrayList4.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._16_title_6) + ": " + getCDState1(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))));
                        arrayList4.add(new OriginalCarDeviceUpdateEntity(2, this.mContext.getString(R.string._16_title_7) + ": " + getCDState2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
                        int[] iArr9 = this.mCanBusInfoInt;
                        if (iArr9[8] != 255 && iArr9[9] != 255) {
                            StringBuilder sbAppend5 = new StringBuilder().append(this.mContext.getString(R.string._16_title_8)).append(": ");
                            int[] iArr10 = this.mCanBusInfoInt;
                            arrayList4.add(new OriginalCarDeviceUpdateEntity(3, sbAppend5.append(iArr10[8] + (iArr10[9] * 256)).toString()));
                        }
                        int[] iArr11 = this.mCanBusInfoInt;
                        if (iArr11[13] != 255 && iArr11[14] != 255 && iArr11[15] != 255) {
                            arrayList4.add(new OriginalCarDeviceUpdateEntity(4, this.mContext.getString(R.string._16_title_9) + ": " + this.mCanBusInfoInt[13] + ":" + this.mCanBusInfoInt[14] + ":" + this.mCanBusInfoInt[15]));
                        }
                        GeneralOriginalCarDeviceData.mList = arrayList4;
                    } else if (i9 == 1) {
                        try {
                            byte[] bArr = new byte[this.mCanBusInfoByte.length - 4];
                            while (true) {
                                byte[] bArr2 = this.mCanBusInfoByte;
                                if (i4 >= bArr2.length - 4) {
                                    break;
                                }
                                bArr[i4] = bArr2[i4 + 4];
                                i4++;
                            }
                            arrayList4.add(new OriginalCarDeviceUpdateEntity(5, this.mContext.getString(R.string._16_title_10) + ": " + new String(bArr, "gbk")));
                            GeneralOriginalCarDeviceData.mList = arrayList4;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else if (i9 == 2) {
                        try {
                            byte[] bArr3 = new byte[this.mCanBusInfoByte.length - 4];
                            while (true) {
                                byte[] bArr4 = this.mCanBusInfoByte;
                                if (i4 >= bArr4.length - 4) {
                                    break;
                                }
                                bArr3[i4] = bArr4[i4 + 4];
                                i4++;
                            }
                            arrayList4.add(new OriginalCarDeviceUpdateEntity(6, this.mContext.getString(R.string._16_title_11) + ": " + new String(bArr3, "gbk")));
                            GeneralOriginalCarDeviceData.mList = arrayList4;
                        } catch (UnsupportedEncodingException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            } else if (i3 == 4) {
                mOriginalInt = 3;
                setUsbPage();
                GeneralOriginalCarDeviceData.cdStatus = "USB";
                int[] iArr12 = this.mCanBusInfoInt;
                if (iArr12.length >= 5) {
                    int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr12[4], 0, 4);
                    if (intFromByteWithBit == 0) {
                        GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._16_value_36);
                    } else if (intFromByteWithBit == 1) {
                        GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._16_value_37);
                    } else if (intFromByteWithBit == 2) {
                        GeneralOriginalCarDeviceData.runningState = this.mContext.getString(R.string._16_value_38);
                    }
                }
                ArrayList arrayList5 = new ArrayList();
                int[] iArr13 = this.mCanBusInfoInt;
                if (iArr13.length >= 6) {
                    int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr13[5], 4, 4);
                    if (intFromByteWithBit2 == 0) {
                        arrayList5.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._16_value_39) + ": " + this.mContext.getString(R.string._16_value_40)));
                    } else if (intFromByteWithBit2 == 1) {
                        arrayList5.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._16_value_39) + ": " + this.mContext.getString(R.string._16_value_41)));
                    } else if (intFromByteWithBit2 == 2) {
                        arrayList5.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._16_value_39) + ": " + this.mContext.getString(R.string._16_value_42)));
                    }
                }
                int[] iArr14 = this.mCanBusInfoInt;
                if (iArr14.length >= 6) {
                    int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr14[5], 0, 4);
                    if (intFromByteWithBit3 == 0) {
                        arrayList5.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._16_value_43) + ": " + this.mContext.getString(R.string._16_value_44)));
                    } else if (intFromByteWithBit3 == 1) {
                        arrayList5.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._16_value_43) + ": " + this.mContext.getString(R.string._16_value_45)));
                    } else if (intFromByteWithBit3 == 2) {
                        arrayList5.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._16_value_43) + ": " + this.mContext.getString(R.string._16_value_46)));
                    }
                }
                if (this.mCanBusInfoInt.length >= 10) {
                    StringBuilder sbAppend6 = new StringBuilder().append(this.mContext.getString(R.string._16_title_8)).append(": ");
                    int[] iArr15 = this.mCanBusInfoInt;
                    arrayList5.add(new OriginalCarDeviceUpdateEntity(2, sbAppend6.append(getMsbLsbResult(iArr15[9], iArr15[8])).toString()));
                }
                if (this.mCanBusInfoInt.length >= 13) {
                    arrayList5.add(new OriginalCarDeviceUpdateEntity(3, this.mContext.getString(R.string._330_Total_time) + ": " + this.mCanBusInfoInt[11] + ":" + this.mCanBusInfoInt[12]));
                }
                if (this.mCanBusInfoInt.length >= 16) {
                    arrayList5.add(new OriginalCarDeviceUpdateEntity(4, this.mContext.getString(R.string._16_title_9) + ": " + this.mCanBusInfoInt[14] + ":" + this.mCanBusInfoInt[15]));
                }
                arrayList5.add(new OriginalCarDeviceUpdateEntity(5, ""));
                arrayList5.add(new OriginalCarDeviceUpdateEntity(6, ""));
                GeneralOriginalCarDeviceData.mList = arrayList5;
            }
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCDState0x61() {
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralOriginalCarDeviceData.cdStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + "";
        GeneralOriginalCarDeviceData.runningState = getCDStateStr(intFromByteWithBit);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, this.mContext.getString(R.string._16_min_value_8) + " : " + (boolBit0 ? this.mContext.getString(R.string._16_min_value_19) : this.mContext.getString(R.string._16_min_value_20))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, this.mContext.getString(R.string._16_min_value_7) + " : " + (boolBit1 ? this.mContext.getString(R.string._16_min_value_19) : this.mContext.getString(R.string._16_min_value_20))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, this.mContext.getString(R.string._16_min_value_6) + " : " + (boolBit2 ? this.mContext.getString(R.string._16_min_value_19) : this.mContext.getString(R.string._16_min_value_20))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, this.mContext.getString(R.string._16_min_value_5) + " : " + (boolBit3 ? this.mContext.getString(R.string._16_min_value_19) : this.mContext.getString(R.string._16_min_value_20))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, this.mContext.getString(R.string._16_min_value_4) + " : " + (boolBit4 ? this.mContext.getString(R.string._16_min_value_19) : this.mContext.getString(R.string._16_min_value_20))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(5, this.mContext.getString(R.string._16_min_value_3) + " : " + (boolBit5 ? this.mContext.getString(R.string._16_min_value_19) : this.mContext.getString(R.string._16_min_value_20))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(6, this.mContext.getString(R.string._16_min_value_18) + " : " + intFromByteWithBit2));
        GeneralOriginalCarDeviceData.mList = arrayList;
        this.mOriginalCarDevicePageUiSet.setHaveSongList(false);
        GeneralOriginalCarDeviceData.songList = new ArrayList();
        updateOriginalCarDeviceActivity(null);
    }

    private void setKeyAirEvent0x51() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 22) {
            realKeyLongClick1(this.mContext, 49, iArr[3]);
        }
        if (i == 129) {
            realKeyClick3(this.mContext, 7, 0, iArr[3]);
            return;
        }
        if (i == 130) {
            realKeyClick3(this.mContext, 8, 0, iArr[3]);
            return;
        }
        switch (i) {
            case 133:
                realKeyClick3(this.mContext, 48, 0, iArr[3]);
                break;
            case HotKeyConstant.K_SLEEP /* 134 */:
                realKeyClick3(this.mContext, 47, 0, iArr[3]);
                break;
            case 135:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SLEEP, iArr[3]);
                break;
        }
    }

    private void setHybrid0x1F() {
        SharePreUtil.setBoolValue(this.mContext, SHARE_16_IS_SUPPOT_HYBRID, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        updateHybirdActivity(null);
    }

    private void setPanoramic0x32() {
        if (isPanoramicStatusChange()) {
            forceReverse(this.mContext, this.mPanoramicStatusNow);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getAmplifierSettingsIndex(), 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        switchFCamera(this.mContext, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
    }

    private void setAmplifierData0x31() {
        GeneralAmplifierData.frontRear = -(resolveAmpData(2, 4) - 7);
        GeneralAmplifierData.leftRight = resolveAmpData(2, 0) - 7;
        GeneralAmplifierData.bandBass = resolveAmpData(3, 4) - 2;
        GeneralAmplifierData.bandTreble = resolveAmpData(3, 0) - 2;
        GeneralAmplifierData.bandMiddle = resolveAmpData(4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        saveAmplifierData(this.mContext, this.mCanId);
        updateAmplifierActivity(null);
        if (getUiMgr(this.mContext).hasAmplifier()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getAmplifierSettingsIndex(), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getAmplifierSettingsIndex(), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_title_21"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_title_21", "_16_ampl_settings_Position"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private int getAmplifierSettingsIndex() {
        return getUiMgr(this.mContext).hasAmplifierNoSettings() ? 0 : 5;
    }

    private void setVersionInfo0x30() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setMediaCommand0x2F() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(14);
            return;
        }
        if (i == 2 || i == 4) {
            realKeyClick(15);
            return;
        }
        if (i != 32) {
            switch (i) {
                case 17:
                    realKeyClick(77);
                    break;
                case 18:
                    realKeyClick(76);
                    break;
                case 19:
                    realKeyClick(68);
                    break;
                case 20:
                    realKeyClick(59);
                    break;
                case 21:
                    realKeyClick(141);
                    break;
            }
            return;
        }
        realKeyClick(50);
    }

    private void setElectric0x2B() {
        String electricUnit = getElectricUnit(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[3] * 256) + iArr[4];
        int i2 = (iArr[5] * 256) + iArr[6];
        if (TextUtils.isEmpty(this.mMileageUnit)) {
            this.mMileageUnit = "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, String.format("%.1f", Double.valueOf(i * 0.1d)) + electricUnit));
        arrayList.add(new DriverUpdateEntity(0, 1, String.format("%.1f", Double.valueOf(i2 * 0.1d)) + this.mMileageUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getElectricUnit(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string.unit_kwh_km);
        }
        return this.mContext.getString(R.string.unit_kWh_100km);
    }

    private void setPanelInfoBtn0x2a() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick(0);
                break;
            case 1:
                realKeyClick(7);
                break;
            case 2:
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(HotKeyConstant.K_SLEEP);
                break;
            case 4:
                realKeyClick(45);
                break;
            case 5:
                realKeyClick(46);
                break;
            case 6:
                realKeyClick(47);
                break;
            case 7:
                realKeyClick(48);
                break;
            case 8:
                realKeyClick(49);
                break;
            case 9:
                realKeyClick(50);
                break;
            case 10:
                realKeyClick(129);
                break;
            case 11:
                realKeyClick(52);
                break;
            case 12:
                realKeyClick(130);
                break;
            case 13:
                realKeyClick(HotKeyConstant.K_TIME);
                break;
            case 14:
                realKeyClick(59);
                break;
            case 15:
                realKeyClick(128);
                break;
            case 16:
                realKeyClick(4);
                break;
            case 17:
                startDrivingDataActivity(this.mContext, 1);
                break;
            case 18:
                setBrightness(1);
                break;
            case 19:
                setBrightness(-1);
                break;
            case 20:
                realKeyClick(58);
                break;
            case 21:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 22:
                realKeyClick(2);
                break;
            case 23:
                realKeyClick(14);
                break;
            case 24:
                realKeyClick(15);
                break;
            case 25:
                realKeyClick(3);
                break;
            case 26:
                realKeyClick(52);
                break;
            case 27:
                realKeyClick(151);
                break;
            case 32:
                startMainActivity(this.mContext);
                getActivity().startActivity(new Intent(getActivity(), (Class<?>) AmplifierActivity.class));
                break;
            case 33:
                realKeyClick(17);
                break;
            case 34:
                realKeyClick(53);
                break;
            case 35:
                startOtherActivity(this.mContext, HzbhdComponentName.CanBusAirActivity);
                break;
        }
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 380, 12);
        updateParkUi(null, this.mContext);
    }

    private void setAirData0x58() {
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        GeneralAirData.swing = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        GeneralAirData.econ = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
        if (this.tempRear != GeneralAirData.rear) {
            updateAirActivity(this.mContext, 1002);
            this.tempRear = GeneralAirData.rear;
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setAirData0x28() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        int airWhat = getAirWhat();
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        bArrCopyOf[3] = (byte) (bArrCopyOf[3] & 239);
        bArrCopyOf[7] = 0;
        if (isAirMsgRepeat3(bArrCopyOf) || isFirst()) {
            return;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_wind_strong = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_wind_light = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemp_Front(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp_Front(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.auto_manual = !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_left_temperature = resolveAirTemp_Rear(this.mCanBusInfoInt[8]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
        GeneralAirData.rear_right_temperature = resolveAirTemp_Rear(this.mCanBusInfoInt[10]);
        if (getUiMgr(this.mContext).isLanKu()) {
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            if (boolBit4) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else {
                if (intFromByteWithBit == 1) {
                    GeneralAirData.front_right_blow_head = true;
                } else if (intFromByteWithBit == 2) {
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                } else if (intFromByteWithBit == 3) {
                    GeneralAirData.front_right_blow_foot = true;
                }
                if (intFromByteWithBit2 == 1) {
                    GeneralAirData.front_left_blow_head = true;
                } else if (intFromByteWithBit2 == 2) {
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                } else if (intFromByteWithBit2 == 3) {
                    GeneralAirData.front_left_blow_foot = true;
                }
            }
        }
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]);
        boolean boolBit42 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[12]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[12]);
        if (boolBit42 && !boolBit2) {
            GeneralAirData.rear_temperature = "LOW";
        } else if (!boolBit42 && boolBit2) {
            GeneralAirData.rear_temperature = "HI";
        }
        GeneralAirData.rear_ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[12]);
        if (GeneralAirData.rear_auto) {
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2) == 1) {
                GeneralAirData.rear_wind_level = 2;
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2) == 2) {
                GeneralAirData.rear_wind_level = 5;
            } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2) == 3) {
                GeneralAirData.rear_wind_level = 7;
            }
        }
        updateAirActivity(this.mContext, airWhat);
    }

    private void setSettingStatus0x52() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3);
        String str = intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? intFromByteWithBit != 3 ? " " : "_16_radiant_orange" : "_16_deep_orange" : "_16_clear_turquoise" : "_16_clear_blue";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3))));
        arrayList.add(new SettingUpdateEntity(3, 4, str));
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) == 1) {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_0"), "KM/L").setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_0"), "L/100KM").setValueStr(true));
        }
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) - 3)));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) - 3)));
        arrayList.add(new SettingUpdateEntity(getUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_16_add_setting"), getUigMgr(this.mContext).getSettingRightIndex(this.mContext, "_16_add_setting", "_16_add_setting_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSettingStatus0x50() {
        SharePreUtil.setIntValue(this.mContext, SHARE_16_LANGUAGE, this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[3];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRearRadarData0x1E() {
        if (isRearRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(5, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
            arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3) - 1));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setFrontRadarData0x1D() {
        if (isFrontRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set15minOilInfo0x27() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr.length < 33) {
            return;
        }
        String oilUnit = getOilUnit(iArr[2]);
        int[] iArr2 = this.mCanBusInfoInt;
        int i = (iArr2[3] * 256) + iArr2[4];
        int i2 = (iArr2[5] * 256) + iArr2[6];
        int i3 = (iArr2[7] * 256) + iArr2[8];
        int i4 = (iArr2[9] * 256) + iArr2[10];
        int i5 = (iArr2[11] * 256) + iArr2[12];
        int i6 = (iArr2[13] * 256) + iArr2[14];
        int i7 = (iArr2[15] * 256) + iArr2[16];
        int i8 = (iArr2[17] * 256) + iArr2[18];
        int i9 = (iArr2[19] * 256) + iArr2[20];
        int i10 = (iArr2[21] * 256) + iArr2[22];
        int i11 = (iArr2[23] * 256) + iArr2[24];
        int i12 = (iArr2[25] * 256) + iArr2[26];
        int i13 = (iArr2[27] * 256) + iArr2[28];
        int i14 = (iArr2[29] * 256) + iArr2[30];
        int i15 = (iArr2[31] * 256) + iArr2[32];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(2, 0, showOilStr(i15, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 1, showOilStr(i14, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 2, showOilStr(i13, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 3, showOilStr(i12, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 4, showOilStr(i11, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 5, showOilStr(i10, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 6, showOilStr(i9, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 7, showOilStr(i8, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 8, showOilStr(i7, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 9, showOilStr(i6, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 10, showOilStr(i5, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 11, showOilStr(i4, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 12, showOilStr(i3, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 13, showOilStr(i2, oilUnit)));
        arrayList.add(new DriverUpdateEntity(2, 14, showOilStr(i, oilUnit)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVehicleSettings0x26() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
        arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(1, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3))));
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(1, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTPMSInfo0x25() {
        SharePreUtil.setBoolValue(this.mContext, SHARE_16_IS_SUPPOT_TIRE, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            SharePreUtil.setBoolValue(this.mContext, SHARE_16_IS_HAVE_SPARE_TIRE, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
            String tireUnit = getTireUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
            float tireRule = getTireRule(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
            GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new TireUpdateEntity(0, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[3] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(1, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[4] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(2, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[5] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(3, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[6] / tireRule) + tireUnit}));
            if (GeneralTireData.isHaveSpareTire) {
                arrayList.add(new TireUpdateEntity(4, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[7] / tireRule) + tireUnit}));
            }
            GeneralTireData.dataList = arrayList;
            updateTirePressureActivity(null);
            if (this.mTireStatus != intFromByteWithBit) {
                this.mTireStatus = intFromByteWithBit;
                if (intFromByteWithBit != 1 || SystemUtil.isForeground(this.mContext, Constant.TireInfoActivity.getClassName())) {
                    return;
                }
                getTireAlramView().showWindow();
            }
        }
    }

    private void setCarInfo0x24() {
        Context context;
        int i;
        Context context2;
        int i2;
        Context context3;
        int i3;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        boolean boolBit0 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        boolean boolBit12 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        ArrayList arrayList = new ArrayList();
        if (boolBit0) {
            context = this.mContext;
            i = R.string.pull_up;
        } else {
            context = this.mContext;
            i = R.string.put_down;
        }
        arrayList.add(new DriverUpdateEntity(0, 3, context.getString(i)));
        if (boolBit12) {
            context2 = this.mContext;
            i2 = R.string.english_on;
        } else {
            context2 = this.mContext;
            i2 = R.string.english_off;
        }
        arrayList.add(new DriverUpdateEntity(0, 4, context2.getString(i2)));
        if (boolBit2) {
            if (boolBit1) {
                context3 = this.mContext;
                i3 = R.string._16_door_move_in;
            } else {
                context3 = this.mContext;
                i3 = R.string._16_door_move_out;
            }
            arrayList.add(new DriverUpdateEntity(0, 5, context3.getString(i3)));
        } else {
            arrayList.add(new DriverUpdateEntity(0, 5, this.mContext.getString(R.string.set_default)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setHistoryOilInfo0x23() {
        String oilUnit = getOilUnit(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[3] * 256) + iArr[4];
        int i2 = (iArr[5] * 256) + iArr[6];
        int i3 = (iArr[7] * 256) + iArr[8];
        int i4 = (iArr[9] * 256) + iArr[10];
        int i5 = (iArr[11] * 256) + iArr[12];
        int i6 = (iArr[13] * 256) + iArr[14];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 1, showOilStr(i, oilUnit)));
        arrayList.add(new DriverUpdateEntity(1, 2, showOilStr(i2, oilUnit)));
        arrayList.add(new DriverUpdateEntity(1, 3, showOilStr(i3, oilUnit)));
        arrayList.add(new DriverUpdateEntity(1, 4, showOilStr(i4, oilUnit)));
        arrayList.add(new DriverUpdateEntity(1, 5, showOilStr(i5, oilUnit)));
        arrayList.add(new DriverUpdateEntity(1, 6, showOilStr(i6, oilUnit)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new SettingUpdateEntity(3, 3, Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    private void setInstantOilInfo0x22() {
        String oilUnit = getOilUnit(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        float f = (iArr[3] * 256) + iArr[4];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, String.format("%.1f", Double.valueOf(f * 0.1d)) + oilUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setMileageInfo0x21() {
        int[] iArr = this.mCanBusInfoInt;
        float f = (iArr[2] * 256) + iArr[3];
        int i = (iArr[4] * 256) + iArr[5];
        int i2 = (iArr[6] * 256) + iArr[7];
        String str = (i / 60) + this.mContext.getString(R.string.hour) + (i % 60) + this.mContext.getString(R.string.min);
        this.mMileageUnit = getMileageInfoUnit(this.mCanBusInfoInt[8]);
        int i3 = this.mCanBusInfoInt[8];
        String str2 = i3 == 1 ? "MILE" : i3 == 2 ? "KM" : "";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, String.format("%.1f", Double.valueOf(f * 0.1d)) + this.mMileageUnit));
        arrayList.add(new DriverUpdateEntity(0, 1, str));
        arrayList.add(new DriverUpdateEntity(0, 2, i2 + this.mMileageUnit));
        StringBuilder sb = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 5, sb.append(DataHandleUtils.getMsbLsbResult(iArr2[9], iArr2[10])).append(str2).toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setKeyEvent0x20() {
        /*
            r3 = this;
            int[] r0 = r3.mCanBusInfoInt
            r1 = 2
            r0 = r0[r1]
            if (r0 == 0) goto L7a
            r2 = 1
            if (r0 == r2) goto L75
            if (r0 == r1) goto L6f
            r2 = 3
            if (r0 == r2) goto L69
            r2 = 4
            if (r0 == r2) goto L63
            switch(r0) {
                case 7: goto L5f;
                case 8: goto L59;
                case 9: goto L53;
                case 10: goto L4d;
                case 11: goto L47;
                default: goto L15;
            }
        L15:
            switch(r0) {
                case 19: goto L41;
                case 20: goto L3b;
                case 21: goto L35;
                case 22: goto L2f;
                default: goto L18;
            }
        L18:
            switch(r0) {
                case 129: goto L75;
                case 130: goto L6f;
                case 131: goto L41;
                case 132: goto L3b;
                case 133: goto L29;
                case 134: goto L23;
                case 135: goto L1d;
                case 136: goto L5f;
                default: goto L1b;
            }
        L1b:
            goto L7e
        L1d:
            r0 = 134(0x86, float:1.88E-43)
            r3.realKeyClick(r0)
            goto L7e
        L23:
            r0 = 20
            r3.realKeyClick(r0)
            goto L7e
        L29:
            r0 = 21
            r3.realKeyClick(r0)
            goto L7e
        L2f:
            r0 = 49
            r3.realKeyClick(r0)
            goto L7e
        L35:
            r0 = 50
            r3.realKeyClick(r0)
            goto L7e
        L3b:
            r0 = 46
            r3.realKeyClick(r0)
            goto L7e
        L41:
            r0 = 45
            r3.realKeyClick(r0)
            goto L7e
        L47:
            r0 = 52
            r3.realKeyClick(r0)
            goto L7e
        L4d:
            r0 = 15
            r3.realKeyClick(r0)
            goto L7e
        L53:
            r0 = 14
            r3.realKeyClick(r0)
            goto L7e
        L59:
            r0 = 187(0xbb, float:2.62E-43)
            r3.realKeyClick(r0)
            goto L7e
        L5f:
            r3.realKeyClick(r1)
            goto L7e
        L63:
            r0 = 47
            r3.realKeyClick(r0)
            goto L7e
        L69:
            r0 = 48
            r3.realKeyClick(r0)
            goto L7e
        L6f:
            r0 = 8
            r3.realKeyClick(r0)
            goto L7e
        L75:
            r0 = 7
            r3.realKeyClick(r0)
            goto L7e
        L7a:
            r0 = 0
            r3.realKeyClick(r0)
        L7e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._16.MsgMgr.setKeyEvent0x20():void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, final String str, final String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr.2
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                if (str.contains("FM2")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 7});
                } else if (str.contains("FM")) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 6});
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 3});
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MsgMgr.this.mCurrBandByte = (byte) -127;
                if (str.contains("AM")) {
                    MsgMgr.this.mCurrBandByte = (byte) -126;
                }
                try {
                    CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, MsgMgr.this.mCurrBandByte, 1, 0, 0}, str2.getBytes(MsgMgr.CHARSETNAME.name())), 32), new byte[]{0, 0}));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                try {
                    CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, MsgMgr.this.mCurrBandByte, 2}, " ".getBytes(MsgMgr.CHARSETNAME.name())), 32), new byte[]{0, 0}));
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e5) {
                    e5.printStackTrace();
                }
                try {
                    CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, MsgMgr.this.mCurrBandByte, 3}, " ".getBytes(MsgMgr.CHARSETNAME.name())), 32), new byte[]{0, 0}));
                } catch (UnsupportedEncodingException e6) {
                    e6.printStackTrace();
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e7) {
                    e7.printStackTrace();
                }
                try {
                    CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, MsgMgr.this.mCurrBandByte, 4}, " ".getBytes(MsgMgr.CHARSETNAME.name())), 32), new byte[]{0, 0}));
                } catch (UnsupportedEncodingException e8) {
                    e8.printStackTrace();
                }
            }
        }).start();
        if (str.equals("AM1") || str.equals("AM2") || str.equals("AM")) {
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, 103}, ("AM " + str2 + "KHz").getBytes(StandardCharsets.UTF_8)));
        } else {
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, 103}, ("FM " + str2 + "MHz").getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, this.mCurrBandByte, 1, 0, 0}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr.3
            @Override // java.lang.Runnable
            public void run() throws InterruptedException {
                CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 5});
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -103, -121, 1}, new byte[31]));
            }
        }).start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(final byte b, byte b2, int i, final int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, final int i3, boolean z, long j2, final String str4, final String str5, final String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (this.musicIndex == i3 && this.musicCount == i2 && !TextUtils.isEmpty(this.musicTitle) && this.musicTitle.equals(str4) && !TextUtils.isEmpty(this.musicArtist) && this.musicArtist.equals(str6) && !TextUtils.isEmpty(this.musicAlbum)) {
            if (this.musicAlbum.equals(str5)) {
                return;
            }
        }
        this.mStoagePath = b;
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m133lambda$musicInfoChange$0$comhzbhdcanbuscar_16MsgMgr(b, i3, i2, str4, str6, str5);
            }
        }).start();
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, 103}, str4.getBytes(StandardCharsets.UTF_8)));
    }

    /* renamed from: lambda$musicInfoChange$0$com-hzbhd-canbus-car-_16-MsgMgr, reason: not valid java name */
    /* synthetic */ void m133lambda$musicInfoChange$0$comhzbhdcanbuscar_16MsgMgr(byte b, int i, int i2, String str, String str2, String str3) throws InterruptedException {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte b2 = (byte) (b == 9 ? HotKeyConstant.K_SLEEP : 132);
        byte[] bArr = {22, -103, b2, 1};
        try {
            String strValueOf = String.valueOf(i / 10);
            Charset charset = CHARSETNAME;
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(bArr, DataHandleUtils.byteMerger(strValueOf.getBytes(charset.name()), String.valueOf(i % 10).getBytes(charset.name()))), DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(String.valueOf(i2 / 10).getBytes(charset.name()), String.valueOf(i2 % 10).getBytes(charset.name())), new byte[27])));
            this.musicIndex = i;
            this.musicCount = i2;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, b2, 2}, str.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicTitle = str;
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e5) {
            e5.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, b2, 3}, str2.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicArtist = str2;
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e7) {
            e7.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, b2, 4}, str3.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicAlbum = str3;
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m132lambda$musicDestroy$1$comhzbhdcanbuscar_16MsgMgr();
            }
        }).start();
    }

    /* renamed from: lambda$musicDestroy$1$com-hzbhd-canbus-car-_16-MsgMgr, reason: not valid java name */
    /* synthetic */ void m132lambda$musicDestroy$1$comhzbhdcanbuscar_16MsgMgr() throws InterruptedException {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = this.mStoagePath == 9 ? HotKeyConstant.K_SLEEP : 132;
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i, 1}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicIndex = 0;
            this.musicCount = 0;
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i, 2}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicTitle = " ";
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e5) {
            e5.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i, 3}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicArtist = " ";
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e7) {
            e7.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i, 4}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicAlbum = " ";
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(final byte b, byte b2, final int i, final int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (this.videoPlayIndex == i && this.videoTotalCount == i2) {
            return;
        }
        Log.d("scyscyscy", i + "<-----videoInfoChange----->" + i2);
        final String string = this.mContext.getString(R.string.video_title);
        this.mStoagePath = b;
        this.videoPlayIndex = i;
        this.videoTotalCount = i2;
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m135lambda$videoInfoChange$2$comhzbhdcanbuscar_16MsgMgr(b, i, i2, string);
            }
        }).start();
        CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, 103}, string.getBytes(StandardCharsets.UTF_8)));
    }

    /* renamed from: lambda$videoInfoChange$2$com-hzbhd-canbus-car-_16-MsgMgr, reason: not valid java name */
    /* synthetic */ void m135lambda$videoInfoChange$2$comhzbhdcanbuscar_16MsgMgr(byte b, int i, int i2, String str) throws InterruptedException {
        int i3 = b == 9 ? HotKeyConstant.K_SLEEP : 132;
        try {
            String strValueOf = String.valueOf(i / 10);
            Charset charset = CHARSETNAME;
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i3, 1}, DataHandleUtils.byteMerger(strValueOf.getBytes(charset.name()), String.valueOf(i % 10).getBytes(charset.name()))), DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(String.valueOf(i2 / 10).getBytes(charset.name()), String.valueOf(i2 % 10).getBytes(charset.name())), new byte[27])));
            this.musicIndex = i;
            this.musicCount = i2;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i3, 2}, str.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicTitle = str;
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        Log.d("scyscyscy", "---------->videoDestroy");
        this.videoPlayIndex = -1;
        this.videoTotalCount = -1;
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m134lambda$videoDestroy$3$comhzbhdcanbuscar_16MsgMgr();
            }
        }).start();
    }

    /* renamed from: lambda$videoDestroy$3$com-hzbhd-canbus-car-_16-MsgMgr, reason: not valid java name */
    /* synthetic */ void m134lambda$videoDestroy$3$comhzbhdcanbuscar_16MsgMgr() throws InterruptedException {
        int i = this.mStoagePath == 9 ? HotKeyConstant.K_SLEEP : 132;
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i, 1}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicIndex = 0;
            this.musicCount = 0;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, (byte) i, 2}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.musicTitle = " ";
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(final String str, final String str2, final String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        if (TextUtils.isEmpty(this.btMusicTitle) || !this.btMusicTitle.equals(str) || TextUtils.isEmpty(this.btMusicArtist) || !this.btMusicArtist.equals(str2) || TextUtils.isEmpty(this.btMusicAlbum) || !this.btMusicAlbum.equals(str3)) {
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    this.f$0.m130lambda$btMusicId3InfoChange$4$comhzbhdcanbuscar_16MsgMgr(str, str2, str3);
                }
            }).start();
        }
    }

    /* renamed from: lambda$btMusicId3InfoChange$4$com-hzbhd-canbus-car-_16-MsgMgr, reason: not valid java name */
    /* synthetic */ void m130lambda$btMusicId3InfoChange$4$comhzbhdcanbuscar_16MsgMgr(String str, String str2, String str3) throws InterruptedException {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 1}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 2}, str.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.btMusicTitle = str;
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e5) {
            e5.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 3}, str2.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.btMusicArtist = str2;
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e7) {
            e7.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 4}, str3.getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.btMusicAlbum = str3;
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        Log.d("scyscyscy", "---------->btMusiceDestdroy");
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._16.MsgMgr$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m131lambda$btMusiceDestdroy$5$comhzbhdcanbuscar_16MsgMgr();
            }
        }).start();
    }

    /* renamed from: lambda$btMusiceDestdroy$5$com-hzbhd-canbus-car-_16-MsgMgr, reason: not valid java name */
    /* synthetic */ void m131lambda$btMusiceDestdroy$5$comhzbhdcanbuscar_16MsgMgr() throws InterruptedException {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 1}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 2}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.btMusicTitle = " ";
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e5) {
            e5.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 3}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.btMusicArtist = " ";
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e7) {
            e7.printStackTrace();
        }
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -103, -123, 4}, " ".getBytes(CHARSETNAME.name())), 32), new byte[]{0, 0}));
            this.btMusicAlbum = " ";
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -123, 32, 4});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendPhoneChange(bArr, 1);
        ContactPerson(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendPhoneChange(bArr, 2);
        ContactPerson(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        sendPhoneChange(bArr, 3);
        ContactPerson(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        sendPhoneChange(bArr, 4);
        ContactPerson(bArr);
    }

    private void ContactPerson(byte[] bArr) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -102}, DataHandleUtils.makeBytesFixedLength(new String(bArr).getBytes(CHARSETNAME.name()), 23)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoneChange(byte[] bArr, int i) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -101, (byte) i}, DataHandleUtils.makeBytesFixedLength(new String(bArr).getBytes(CHARSETNAME.name()), 23)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void aMapCallBack(Bundle bundle) {
        super.aMapCallBack(bundle);
        int i = bundle.getInt("EXTRA_STATE");
        int i2 = bundle.getInt("ICON");
        String string = bundle.getString("NEXT_ROAD_NAME");
        int i3 = bundle.getInt("SEG_REMAIN_DIS");
        int i4 = bundle.getInt("CAR_DIRECTION");
        if (i == 8 || i == 10) {
            this.isStartAMap = true;
        } else if (i == 9 || i == 12) {
            this.isStartAMap = false;
        }
        int naviDirection = getNaviDirection(i2);
        int carDirection = AMapBroadcastReceiver.getCarDirection(i4, "南");
        byte b = (byte) (i3 >> 8);
        byte b2 = (byte) i3;
        try {
            if (!TextUtils.isEmpty(string) && this.isStartAMap) {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -100, (byte) naviDirection, (byte) carDirection, b, b2, (byte) 0}, DataHandleUtils.makeBytesFixedLength(string.getBytes(CHARSETNAME.name()), 17)));
            } else if (i == 9 || i == 12) {
                CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -100, 0, 0, 0, 0, 0}, new byte[17]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String showOilStr(int i, String str) {
        return i == 4095 ? this.mContext.getString(R.string.set_default) : String.format("%.1f", Double.valueOf(i * 0.1d)) + str;
    }

    private String getOilUnit(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string.hiworld_jeep_123_item_33);
        }
        if (i == 2) {
            return this.mContext.getString(R.string.hiworld_jeep_123_item_32);
        }
        return this.mContext.getString(R.string.hiworld_jeep_123_item_40);
    }

    private String getMileageInfoUnit(int i) {
        if (i == 1) {
            return this.mContext.getString(R.string._96_range_unit_mile);
        }
        return i == 2 ? this.mContext.getString(R.string.unit_km) : "";
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackOpen == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mBackOpen = GeneralDoorData.isBackOpen;
        return true;
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoByte[3]);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[7];
        return (i < 0 || i > 180) ? "" : ((this.mCanBusInfoByte[7] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = 0;
        int i2 = iArr[6];
        int[] iArr2 = {iArr[2], iArr[3] & 239, iArr[4], iArr[5], i2 & com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE, iArr[7]};
        int[] iArr3 = this.mCanBusInfoInt;
        int[] iArr4 = {DataHandleUtils.getBoolBit2(i2) ? 1 : 0, iArr3[8], iArr3[9], iArr3[10]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr4)) {
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 6);
        this.mAirRearDataNow = Arrays.copyOf(iArr4, 4);
        return i;
    }

    private String resolveAirTemp_Front(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            if (i != 0) {
                if (i == 255) {
                    return "HIGH";
                }
                if (i >= 1 && i <= 254) {
                    return i + getTempUnitF(this.mContext);
                }
                return " ";
            }
            return "LOW";
        }
        if (i != 0) {
            if (i == 31) {
                return "HIGH";
            }
            if (i >= 1 && i <= 29) {
                return (((i - 1) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
            }
            if (i >= 32 && i <= 35) {
                return (i * 0.5f) + getTempUnitC(this.mContext);
            }
            if (i >= 36 && i <= 37) {
                return ((i * 0.5f) - 3.0f) + getTempUnitC(this.mContext);
            }
            return " ";
        }
        return "LOW";
    }

    private String resolveAirTemp_Rear(int i) {
        if (i == 0) {
            return "LOW";
        }
        if (i == 31) {
            return "HIGH";
        }
        if (i < 1 || i > 29) {
            return (i < 33 || i > 38) ? " " : (((i - 33) * 0.5d) + 15.0d) + getTempUnitC(this.mContext);
        }
        return (((i - 1) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
    }

    private void setBrightness(int i) {
        setBacklightLevel(DataHandleUtils.rangeNumber(MediaShareData.Screen.INSTANCE.getScreenBacklight() + i, 1, 5));
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private int resolveAmpData(int i, int i2) {
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[i], i2, 4);
    }

    private boolean isPanoramicStatusChange() {
        boolean z = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (this.mPanoramicStatusNow == z) {
            return false;
        }
        this.mPanoramicStatusNow = z;
        return true;
    }

    private boolean is0x62DataChange() {
        if (Arrays.equals(this.m0x62DataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x62DataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x66DataChange() {
        if (Arrays.equals(this.m0x66DataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x66DataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String getCDState2(int i) {
        if (i != 0) {
            return i != 1 ? "" : this.mContext.getString(R.string._16_value_9);
        }
        return this.mContext.getString(R.string._16_value_8);
    }

    private String getCDState1(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._16_value_5);
        }
        if (i != 1) {
            return i != 2 ? "" : this.mContext.getString(R.string._16_value_7);
        }
        return this.mContext.getString(R.string._16_value_6);
    }

    private String getRadioStatus() {
        int i = this.mCanBusInfoInt[4];
        return i != 1 ? i != 2 ? i != 16 ? " " : "AM" : "FM2" : "FM1";
    }

    private String getPlayMode() {
        int i = this.mCanBusInfoInt[2];
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 255 ? " " : "Others" : "USB" : "AUX" : "CD" : "Radio" : "Media OFF";
    }

    private String getRunningState0x66() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 7);
        if (intFromByteWithBit == 0) {
            return this.mContext.getString(R.string._16_value_10);
        }
        if (intFromByteWithBit == 1) {
            return this.mContext.getString(R.string._16_value_11);
        }
        if (intFromByteWithBit == 2) {
            return this.mContext.getString(R.string._16_value_12);
        }
        if (intFromByteWithBit == 3) {
            return this.mContext.getString(R.string._16_value_13);
        }
        if (intFromByteWithBit == 4) {
            return this.mContext.getString(R.string._16_value_14);
        }
        if (intFromByteWithBit != 6) {
            return intFromByteWithBit != 7 ? " " : this.mContext.getString(R.string._16_value_16);
        }
        return this.mContext.getString(R.string._16_value_15);
    }

    private String getPlayMode0x66() {
        int i = this.mCanBusInfoInt[2];
        if (i == 255) {
            return "Others";
        }
        switch (i) {
            case 0:
                return "OFF";
            case 1:
                return "DISC";
            case 2:
                return "DISC CD";
            case 3:
                return "DISC DVD";
            case 4:
                return "SD";
            case 5:
                return "USB";
            case 6:
                return "A/V";
            default:
                return " ";
        }
    }

    protected boolean isAirMsgRepeat2(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void setOriginalPage0x66() {
        changeOriginalDevicePage(new String[]{"power", "lock"}, new String[]{"mode", OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.STOP}, false);
    }

    private void setRadioPage() {
        changeOriginalDevicePage(null, new String[]{"up", "left", OriginalBtnAction.PLAY_PAUSE, "right", "down"}, true);
    }

    private void setCdPage() {
        changeOriginalDevicePage(null, new String[]{OriginalBtnAction.PREV_DISC, "up", "left", OriginalBtnAction.PLAY_PAUSE, "right", "down", OriginalBtnAction.NEXT_DISC, OriginalBtnAction.RANDOM, OriginalBtnAction.REPEAT}, true);
    }

    private void setUsbPage() {
        changeOriginalDevicePage(null, new String[]{OriginalBtnAction.PREV_DISC, "up", "left", OriginalBtnAction.PLAY_PAUSE, "right", "down", OriginalBtnAction.NEXT_DISC, "cycle", OriginalBtnAction.RANDOM}, true);
    }

    private String getCDStateStr(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._16_min_value_10);
        }
        if (i == 1) {
            return this.mContext.getString(R.string._16_min_value_11);
        }
        if (i == 2) {
            return this.mContext.getString(R.string._16_min_value_12);
        }
        if (i == 3) {
            return this.mContext.getString(R.string._16_min_value_13);
        }
        if (i == 4) {
            return this.mContext.getString(R.string._16_min_value_14);
        }
        if (i == 6) {
            return this.mContext.getString(R.string._16_min_value_15);
        }
        if (i == 7) {
            return this.mContext.getString(R.string._16_min_value_16);
        }
        return this.mContext.getString(R.string._16_min_value_10);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, getUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._16.MsgMgr.4
            final byte[][] bytesArray;
            int i = 0;

            {
                this.bytesArray = new byte[][]{new byte[]{22, -125, 38, (byte) SharePreUtil.getIntValue(MsgMgr.this.mContext, MsgMgr.SHARE_16_LANGUAGE, 0)}, new byte[]{22, -124, 1, (byte) ((-GeneralAmplifierData.frontRear) + 7)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}};
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr = this.bytesArray;
                if (i < bArr.length) {
                    CanbusMsgSender.sendMsg(bArr[i]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 100L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private void changeOriginalDevicePage(String[] strArr, String[] strArr2, boolean z) {
        this.mOriginalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        this.mOriginalCarDevicePageUiSet.setRowBottomBtnAction(strArr2);
        this.mOriginalCarDevicePageUiSet.setHaveSongList(z);
        GeneralOriginalCarDeviceData.mList = null;
        updateOriginalDeviceWithInit();
    }

    private TireAlramView getTireAlramView() {
        if (this.mTireAlramView == null) {
            this.mTireAlramView = new TireAlramView();
        }
        return this.mTireAlramView;
    }

    private class TireAlramView implements View.OnClickListener {
        private boolean isShowing;
        private View mFloatView;
        private WindowManager.LayoutParams mLayoutParams;
        private TextView mTvBtnNo;
        private TextView mTvBtnYes;
        private WindowManager mWindowManager;

        public TireAlramView() {
            initWindow();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showWindow() {
            if (this.isShowing) {
                return;
            }
            this.isShowing = true;
            MsgMgr.this.runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._16.MsgMgr.TireAlramView.1
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    TireAlramView.this.mWindowManager.addView(TireAlramView.this.mFloatView, TireAlramView.this.mLayoutParams);
                }
            });
        }

        private void initWindow() {
            this.mWindowManager = (WindowManager) MsgMgr.this.mContext.getSystemService("window");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = 2002;
            this.mLayoutParams.gravity = 17;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(MsgMgr.this.mContext).inflate(R.layout.layout_16_tire_alarm, (ViewGroup) null);
            this.mFloatView = relativeLayout;
            this.mTvBtnNo = (TextView) relativeLayout.findViewById(R.id.tv_no);
            this.mTvBtnYes = (TextView) this.mFloatView.findViewById(R.id.tv_yes);
            this.mTvBtnNo.setOnClickListener(this);
            this.mTvBtnYes.setOnClickListener(this);
        }

        private void dismissView() {
            View view;
            WindowManager windowManager = this.mWindowManager;
            if (windowManager == null || (view = this.mFloatView) == null) {
                return;
            }
            windowManager.removeView(view);
            this.isShowing = false;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.tv_no) {
                dismissView();
            } else {
                if (id != R.id.tv_yes) {
                    return;
                }
                dismissView();
                MsgMgr.this.startTireInfo();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTireInfo() {
        Intent intent = new Intent();
        intent.setComponent(Constant.TireInfoActivity);
        intent.setFlags(268435456);
        this.mContext.startActivity(intent);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void updateOriginalDeviceWithInit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void portraitCommand(android.content.Context r14) {
        /*
            r13 = this;
            android.content.res.Resources r14 = r14.getResources()
            android.content.res.Configuration r14 = r14.getConfiguration()
            int r14 = r14.orientation
            r0 = 1
            if (r14 != r0) goto L96
            int r14 = r13.getEachCanId()
            r1 = 130(0x82, float:1.82E-43)
            r2 = 3
            r3 = 0
            r4 = 22
            r5 = 20
            r6 = 15
            r7 = 12
            r8 = 6
            r9 = 5
            r10 = 255(0xff, float:3.57E-43)
            r11 = 2
            if (r14 == r0) goto L85
            if (r14 == r11) goto L82
            r12 = 4
            if (r14 == r12) goto L80
            if (r14 == r9) goto L7d
            if (r14 == r8) goto L7a
            if (r14 == r7) goto L80
            if (r14 == r6) goto L77
            if (r14 == r5) goto L74
            r12 = 32
            if (r14 == r12) goto L71
            r12 = 58
            if (r14 == r12) goto L86
            r12 = 59
            if (r14 == r12) goto L86
            switch(r14) {
                case 34: goto L6f;
                case 35: goto L6f;
                case 36: goto L6d;
                case 37: goto L6b;
                case 38: goto L6b;
                case 39: goto L69;
                case 40: goto L67;
                case 41: goto L64;
                case 42: goto L61;
                case 43: goto L5e;
                case 44: goto L5c;
                case 45: goto L59;
                case 46: goto L57;
                case 47: goto L54;
                case 48: goto L51;
                case 49: goto L4e;
                case 50: goto L4b;
                case 51: goto L49;
                case 52: goto L45;
                default: goto L42;
            }
        L42:
            r1 = r10
            goto L86
        L45:
            r1 = 129(0x81, float:1.81E-43)
            goto L86
        L49:
            r1 = r5
            goto L86
        L4b:
            r1 = 19
            goto L86
        L4e:
            r1 = 18
            goto L86
        L51:
            r1 = 17
            goto L86
        L54:
            r1 = 16
            goto L86
        L57:
            r1 = r6
            goto L86
        L59:
            r1 = 14
            goto L86
        L5c:
            r1 = r7
            goto L86
        L5e:
            r1 = 11
            goto L86
        L61:
            r1 = 10
            goto L86
        L64:
            r1 = 9
            goto L86
        L67:
            r1 = r8
            goto L86
        L69:
            r1 = r9
            goto L86
        L6b:
            r1 = r2
            goto L86
        L6d:
            r1 = r11
            goto L86
        L6f:
            r1 = r0
            goto L86
        L71:
            r1 = 128(0x80, float:1.8E-43)
            goto L86
        L74:
            r1 = 23
            goto L86
        L77:
            r1 = 21
            goto L86
        L7a:
            r1 = 8
            goto L86
        L7d:
            r1 = 24
            goto L86
        L80:
            r1 = r3
            goto L86
        L82:
            r1 = 13
            goto L86
        L85:
            r1 = r4
        L86:
            if (r1 == r10) goto L96
            byte[] r14 = new byte[r2]
            r14[r3] = r4
            r2 = -30
            r14[r0] = r2
            byte r0 = (byte) r1
            r14[r11] = r0
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r14)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._16.MsgMgr.portraitCommand(android.content.Context):void");
    }

    private void sendVoiceMsg(int i, boolean z) {
        if (z) {
            CanbusMsgSender.sendMsg(new byte[]{24, -121, (byte) i, 1});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{24, -121, (byte) i, 0});
        }
    }

    public int getEachCanId() {
        return getCurrentEachCanId();
    }

    protected boolean isAirMsgRepeat3(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy3)) {
            return true;
        }
        this.mCanbusAirInfoCopy3 = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
