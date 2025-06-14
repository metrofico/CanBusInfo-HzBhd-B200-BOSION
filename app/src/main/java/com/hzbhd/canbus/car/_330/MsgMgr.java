package com.hzbhd.canbus.car._330;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
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
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static String mediaTag = "";
    DecimalFormat decimalFormat;
    int differentId;
    int eachCanId;
    private int[] m0x1DData;
    private int[] m0x1EData;
    private int[] m0x21AirData;
    private int[] m0x24Data;
    private int[] mAmplifierDataInt;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private int mData1Info;
    private int mMediaType;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    private int mOutdoorTemperature;
    private byte[] mTrackData;
    private UiMgr mUiMgr;
    private int[] speedInfo;
    private int[] tireTurnSpeed;
    private int[] trafficInfo;
    private final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
    private final String MEDIA_TYPE_RADIO = "RADIO";
    private final String MEDIA_TYPE_CD_DVD = "CD/DVD";
    private final String MEDIA_TYPE_AUX = "AUX";
    private final String MEDIA_REAR_DISC = "后排DISC";
    private final String MEDIA_USB = "USB";
    int lightAndBacklight = 0;
    private int airData6 = 0;
    private int airData7 = 0;
    private int airData8 = 0;
    int lowSate = 0;
    int hiState = 0;
    int data2FrontLeft = 0;
    int data3FrontRight = 0;
    int data6RearLeft = 0;
    int data8RearRight = 0;
    int data5OurDoorTem = 0;

    private int assignRadarProgress(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 4;
        }
        if (i == 3) {
            return 7;
        }
        return i == 4 ? 10 : 0;
    }

    private String getAppointmentBand(int i) {
        if (i == 0) {
            return "FM";
        }
        if (i == 1) {
            return "FM1";
        }
        if (i == 2) {
            return "FM2";
        }
        switch (i) {
            case 16:
                return "AM";
            case 17:
                return "AM1";
            case 18:
                return "AM2";
            default:
                return "无";
        }
    }

    private int getAslState(int i) {
        return i == 8 ? 1 : 0;
    }

    private int getCarType(int i) {
        if (i == 32) {
            return 0;
        }
        if (i == 33) {
            return 1;
        }
        if (i == 48) {
            return 2;
        }
        if (i == 49) {
            return 3;
        }
        if (i == 64) {
            return 4;
        }
        if (i == 65) {
            return 5;
        }
        if (i == 80) {
            return 6;
        }
        if (i == 81) {
            return 7;
        }
        switch (i) {
            case 96:
                return 8;
            case 97:
                return 9;
            case 98:
                return 10;
            case 99:
                return 11;
            case 100:
                return 12;
            default:
                return 13;
        }
    }

    private String getDiscLanguage(int i) {
        switch (i) {
            case 1:
                return "英语";
            case 2:
                return "法语";
            case 3:
                return "西班牙语";
            case 4:
                return "德语";
            case 5:
                return "意大利语";
            case 6:
                return "日语";
            default:
                return "中文";
        }
    }

    private String getDiscState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 15 ? "空闲状态" : "ERROR 状态" : "EJECT状态" : "播放状态" : "DISC READING状态" : "WAIT状态" : "LOAD状态";
    }

    private String getDiscTypoe(boolean z) {
        return !z ? "CD" : "DVD";
    }

    private String getDiscYesOrNo(boolean z) {
        return z ? "有碟" : "无碟";
    }

    private int getMsbLsbResult(int i, int i2) {
        return (i & 255) | ((i2 & 255) << 8);
    }

    private String getNowDisc(int i) {
        switch (i) {
            case 1:
                return "DISC 1";
            case 2:
                return "DISC 2";
            case 3:
                return "DISC 3";
            case 4:
                return "DISC 4";
            case 5:
                return "DISC 5";
            case 6:
                return "DISC 6";
            default:
                return "无";
        }
    }

    private String getRadarDisplayState(boolean z) {
        return z ? "打开" : "关闭";
    }

    private String getRadarDistance(boolean z) {
        return z ? "远" : "近";
    }

    private String getRadarSwitch(boolean z) {
        return z ? "开" : "关";
    }

    private String getRearLockState(int i) {
        return i != 1 ? "未锁止" : "锁止";
    }

    private String getRearMediaState(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 15 ? "空闲状态" : "ERROR状态" : "EJECT状态" : "播放状态" : "DISC READING " : "WAIT状态" : "LOAD状态";
    }

    private String getScanState(boolean z) {
        return z ? "扫描状态" : "非扫描状态";
    }

    private String getStereoState(boolean z) {
        return z ? "立体声" : "非立体声";
    }

    private String getUsbState1(int i) {
        switch (i) {
            case 1:
                return "LAODING";
            case 2:
                return "没有连接 USB 设备";
            case 3:
                return "设备已经连接";
            case 4:
                return "播放中";
            case 5:
                return "暂停(LOAD IMAGE)";
            case 6:
                return "其它状态";
            default:
                return "关闭/停止状态";
        }
    }

    private String getUsbState2(int i) {
        return i != 1 ? i != 2 ? "无媒体信息" : "USB" : "IPOD";
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.hzbhd.canbus.car._330.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.decimalFormat = new DecimalFormat("########00.00");
        new Thread() { // from class: com.hzbhd.canbus.car._330.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.initOriginalDeviceDataArray();
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachCanId = getCurrentEachCanId();
        initAmplifier(context);
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._330.MsgMgr.2
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -124, 1, (byte) ((-GeneralAmplifierData.frontRear) + 7)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}}).iterator();

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            set0x16Speed(byteArrayToIntArray);
        }
        if (i == 36) {
            set0x24BaseInfo();
            return;
        }
        if (i == 53) {
            set0x35TrafficInfo(byteArrayToIntArray);
            return;
        }
        if (i == 80) {
            set0x50CarSpeed(byteArrayToIntArray);
            return;
        }
        if (i == 29) {
            set0x1dFrontRadarInfo();
            return;
        }
        if (i == 30) {
            set0x1ERearRadarInfo();
            return;
        }
        if (i == 32) {
            set0x20WheelKey();
            return;
        }
        if (i == 33) {
            set0x21UsbInfo();
            return;
        }
        if (i == 40) {
            set0x28AirInfo(byteArrayToIntArray);
            return;
        }
        if (i == 41) {
            set0x29TrackData();
            return;
        }
        switch (i) {
            case 48:
                set0x30VersionInfo();
                break;
            case 49:
                set0x31AmplifierInfo();
                break;
            case 50:
                set0x32SystemInfo();
                break;
            default:
                switch (i) {
                    case 96:
                        set0x60SteeringWheelControlMode();
                        break;
                    case 97:
                        set0x61MediaState();
                        break;
                    case 98:
                        set0x62MediaSourceInfo();
                        break;
                    case 99:
                        set0x63ModelSetting();
                        break;
                    case 100:
                        set0x64WheelKey();
                        break;
                    case 101:
                        set0x65RockerData(this.mContext);
                        break;
                }
        }
    }

    private void set0x65RockerData(Context context) {
        int i = this.eachCanId;
        if (i == 3 || i == 11) {
            int[] iArr = this.mCanBusInfoInt;
            int i2 = iArr[2];
            if (i2 == 0) {
                realKeyLongClick1(context, 0, iArr[3]);
                return;
            }
            if (i2 == 1) {
                realKeyLongClick1(context, 45, iArr[3]);
                return;
            }
            if (i2 == 3) {
                realKeyLongClick1(context, 48, iArr[3]);
                return;
            }
            if (i2 == 5) {
                realKeyLongClick1(context, 46, iArr[3]);
                return;
            }
            if (i2 == 7) {
                realKeyLongClick1(context, 47, iArr[3]);
                return;
            }
            switch (i2) {
                case 16:
                    if (iArr[3] != 0) {
                        realKeyClick(context, 7);
                        break;
                    }
                    break;
                case 17:
                    if (iArr[3] != 0) {
                        realKeyClick(context, 8);
                        break;
                    }
                    break;
                case 18:
                    realKeyLongClick1(context, 49, iArr[3]);
                    break;
                case 19:
                    realKeyLongClick1(context, 50, iArr[3]);
                    break;
                case 20:
                    realKeyLongClick1(context, 151, iArr[3]);
                    break;
                case 21:
                    realKeyLongClick1(context, 4, iArr[3]);
                    break;
                case 22:
                    realKeyLongClick1(context, 128, iArr[3]);
                    break;
            }
        }
    }

    private void set0x64WheelKey() {
        int i = this.eachCanId;
        if (i == 1 || i == 4 || i == 6 || i == 8 || i == 11) {
            int[] iArr = this.mCanBusInfoInt;
            int i2 = iArr[2];
            if (i2 == 16) {
                realKeyLongClick1(this.mContext, 53, iArr[3]);
                return;
            }
            if (i2 != 17) {
                switch (i2) {
                    case 0:
                        realKeyLongClick1(this.mContext, 0, iArr[3]);
                        break;
                    case 1:
                        realKeyLongClick1(this.mContext, 77, iArr[3]);
                        break;
                    case 2:
                        changeBandFm1();
                        break;
                    case 3:
                        changeBandFm2();
                        break;
                    case 4:
                        realKeyLongClick1(this.mContext, 141, iArr[3]);
                        break;
                    case 5:
                        realKeyLongClick1(this.mContext, 128, iArr[3]);
                        break;
                    case 6:
                        realKeyLongClick1(this.mContext, 4, iArr[3]);
                        break;
                    case 7:
                        realKeyLongClick1(this.mContext, 151, iArr[3]);
                        break;
                    case 8:
                        realKeyLongClick1(this.mContext, 68, iArr[3]);
                        break;
                    case 9:
                        realKeyLongClick1(this.mContext, 57, iArr[3]);
                        break;
                }
            }
            updateAirActivity(this.mContext, 0);
        }
    }

    private void set0x63ModelSetting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_setting_info"), 0, Integer.valueOf(getCarType(this.mCanBusInfoInt[2]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x62MediaSourceInfo() {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(this.mCanBusInfoInt[2]);
        Bundle bundle = null;
        GeneralOriginalCarDeviceData.mList = null;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            mediaTag = "MEDIA OFF";
            GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
            GeneralOriginalCarDeviceData.runningState = "MEDIA OFF";
        } else if (i == 1) {
            mediaTag = "RADIO";
            GeneralOriginalCarDeviceData.cdStatus = "RADIO";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceRadioUpdateEntityList(this.mCanBusInfoInt);
        } else if (i == 2) {
            mediaTag = "CD";
            GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanBusInfoInt);
        } else if (i == 3) {
            mediaTag = "AUX";
            GeneralOriginalCarDeviceData.cdStatus = "AUX";
            GeneralOriginalCarDeviceData.runningState = "AUX运行正常";
        } else if (i == 4) {
            mediaTag = "DISC";
            GeneralOriginalCarDeviceData.cdStatus = "后排DISC";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceRearDiscUpdateEntityList(this.mCanBusInfoInt);
        }
        int i2 = this.mMediaType;
        int i3 = this.mCanBusInfoInt[2];
        if (i2 != i3) {
            this.mMediaType = i3;
            OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
            originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
            originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
            bundle = new Bundle();
            bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        }
        updateOriginalCarDeviceActivity(bundle);
    }

    private void set0x61MediaState() {
        int i = this.eachCanId;
        if ((i == 1 || i == 3 || i == 4 || i == 6 || i == 8 || i == 9 || i == 11) && getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state") != -1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_1"), getDiscYesOrNo(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_2"), getDiscYesOrNo(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_3"), getDiscYesOrNo(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_4"), getDiscYesOrNo(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_5"), getDiscYesOrNo(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_6"), getDiscYesOrNo(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_disc_status"), getDiscState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_currently_selected_disc"), getNowDisc(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_1"), getDiscTypoe(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_2"), getDiscTypoe(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_3"), getDiscTypoe(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_4"), getDiscTypoe(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_5"), getDiscTypoe(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Disc_type_6"), getDiscTypoe(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]))));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Subtitle_language"), getDiscLanguage(this.mCanBusInfoInt[6])));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Voice_language"), getDiscLanguage(this.mCanBusInfoInt[7])));
            arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Multimedia_global_state"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_dvd_language"), getDiscLanguage(this.mCanBusInfoInt[8])));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x60SteeringWheelControlMode() {
        int i = this.eachCanId;
        if (i == 1 || i == 3 || i == 4 || i == 5 || i == 6 || i == 8 || i != 11) {
            ArrayList arrayList = new ArrayList();
            if (getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_Mode_selection") != -1) {
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_Mode_selection"), 0, Integer.valueOf(this.mCanBusInfoInt[2])));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x50CarSpeed(int[] iArr) {
        int i = this.eachCanId;
        if (i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 10 || i == 11) {
            this.tireTurnSpeed = iArr;
            if (getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_rotate_speed") != -1) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DriverUpdateEntity(0, getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_rotate_speed"), ((iArr[3] * 256) + iArr[2]) + " RPM"));
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
            }
        }
    }

    private void set0x35TrafficInfo(int[] iArr) {
        this.trafficInfo = iArr;
        int i = this.eachCanId;
        if (i == 3 || i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11) {
            ArrayList arrayList = new ArrayList();
            if (SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R1", 0) == 1) {
                arrayList.add(new DriverUpdateEntity(0, 1, this.decimalFormat.format(getMsbLsbResult(iArr[3], iArr[2]) * 0.1d) + " L/100Km"));
                arrayList.add(new DriverUpdateEntity(0, 2, this.decimalFormat.format(getMsbLsbResult(iArr[5], iArr[4]) * 0.1d) + " L/100Km"));
                arrayList.add(new DriverUpdateEntity(0, 3, this.decimalFormat.format(getMsbLsbResult(iArr[7], iArr[6])) + " KM"));
                arrayList.add(new DriverUpdateEntity(0, 4, this.decimalFormat.format(getMsbLsbResult(iArr[9], iArr[8])) + " KM/h"));
                arrayList.add(new DriverUpdateEntity(0, 5, this.decimalFormat.format(getMsbLsbResult(iArr[11], iArr[10])) + " KM"));
            } else {
                arrayList.add(new DriverUpdateEntity(0, 1, this.decimalFormat.format(getMsbLsbResult(iArr[3], iArr[2]) * 0.1d * 1.609344d) + " L/100MI"));
                arrayList.add(new DriverUpdateEntity(0, 2, this.decimalFormat.format(getMsbLsbResult(iArr[5], iArr[4]) * 0.1d * 1.609344d) + " L/100MI"));
                arrayList.add(new DriverUpdateEntity(0, 3, this.decimalFormat.format(getMsbLsbResult(iArr[7], iArr[6]) * 1000) + " M"));
                arrayList.add(new DriverUpdateEntity(0, 4, this.decimalFormat.format(getMsbLsbResult(iArr[9], iArr[8]) * 0.6213712d) + " MPH"));
                arrayList.add(new DriverUpdateEntity(0, 5, this.decimalFormat.format(getMsbLsbResult(iArr[11], iArr[10]) * 1000) + " M"));
            }
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x32SystemInfo() {
        ArrayList arrayList = new ArrayList();
        if (getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info") != -1) {
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 2, getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x31AmplifierInfo() {
        GeneralAmplifierData.frontRear = (-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4)) + 7;
        GeneralAmplifierData.leftRight = -((-DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)) + 7);
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        updateAmplifierActivity(new Bundle());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 3, Integer.valueOf(getAslState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 4, getNullHaveState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_amplifier_info"), 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x29TrackData() {
        int i = this.eachCanId;
        if (i == 8 || i == 9 || !isTrackDataChange()) {
            return;
        }
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 380, 12);
        updateParkUi(null, this.mContext);
    }

    private void set0x28AirInfo(int[] iArr) {
        if (iArr == null) {
            return;
        }
        MyLog.temporaryTracking("更新了Air数据");
        int[] iArr2 = this.mCanBusInfoInt;
        this.data2FrontLeft = iArr2[4];
        this.data3FrontRight = iArr2[5];
        this.data6RearLeft = iArr2[8];
        this.data8RearRight = iArr2[10];
        this.data5OurDoorTem = iArr2[7];
        airOfNewVersion(iArr);
    }

    private void airOfNewVersion(int[] iArr) {
        int i = this.mOutdoorTemperature;
        int i2 = iArr[7];
        if (i != i2) {
            this.mOutdoorTemperature = i2;
            if (DataHandleUtils.getBoolBit0(iArr[6])) {
                MyLog.temporaryTracking("外温：直接走了华氏度");
                Context context = this.mContext;
                updateOutDoorTemp(context, getOutDoorTemperatureF(context, iArr[7]));
            } else {
                MyLog.temporaryTracking("外温：走了摄氏度");
                if (SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
                    MyLog.temporaryTracking("外温：还是走了摄氏度");
                    Context context2 = this.mContext;
                    updateOutDoorTemp(context2, getOutDoorTemperatureC(context2, iArr[7]));
                } else {
                    MyLog.temporaryTracking("外温：然后走了华氏度");
                    Context context3 = this.mContext;
                    updateOutDoorTemp(context3, getOutDoorTemperatureF(context3, iArr[7]));
                }
            }
        }
        iArr[7] = 0;
        this.mData1Info = iArr[3];
        emptyAirData1Bit4();
        if (is0x28DataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mData1Info);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mData1Info);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mData1Info);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mData1Info, 0, 4);
            if (DataHandleUtils.getBoolBit0(iArr[6])) {
                MyLog.temporaryTracking("内温：直接走了华氏度");
                GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, iArr[4]);
                GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, iArr[5]);
            } else {
                MyLog.temporaryTracking("内温：走了摄氏度");
                if (SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
                    MyLog.temporaryTracking("内温：还是走了摄氏度");
                    GeneralAirData.front_left_temperature = getAirTemperatureC(this.mContext, iArr[4]);
                    GeneralAirData.front_right_temperature = getAirTemperatureC(this.mContext, iArr[5]);
                } else {
                    MyLog.temporaryTracking("内温：然后走了华氏度");
                    GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, iArr[4]);
                    GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, iArr[5]);
                }
            }
            GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(iArr[6]);
            GeneralAirData.rear_auto_wind_model = DataHandleUtils.getBoolBit7(iArr[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(iArr[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(iArr[6]);
            GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(iArr[6]);
            if (!DataHandleUtils.getBoolBit0(iArr[6]) && SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
                GeneralAirData.rear_left_temperature = getAirTemperatureC(this.mContext, iArr[8]);
                GeneralAirData.rear_right_temperature = getAirTemperatureC(this.mContext, iArr[10]);
            } else {
                GeneralAirData.rear_left_temperature = getAirTemperatureF(this.mContext, iArr[8]);
                GeneralAirData.rear_right_temperature = getAirTemperatureF(this.mContext, iArr[10]);
            }
            GeneralAirData.rear_temperature = modeIsLowOrHi();
            GeneralAirData.rear_ac = DataHandleUtils.getBoolBit6(iArr[9]);
            GeneralAirData.manual = judgeManual();
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(iArr[9]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 3);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(iArr[9]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(iArr[11]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(iArr[11]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(iArr[11]);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[12], 4, 4);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[12], 0, 4);
            if (intFromByteWithBit == 1 || intFromByteWithBit == 2 || intFromByteWithBit == 3) {
                GeneralAirData.front_left_seat_cold_level = 0;
                GeneralAirData.rear_left_seat_cold_level = 0;
                GeneralAirData.rear_left_seat_heat_level = intFromByteWithBit;
                GeneralAirData.front_left_seat_heat_level = intFromByteWithBit;
            } else if (intFromByteWithBit == 13 || intFromByteWithBit == 14 || intFromByteWithBit == 15) {
                GeneralAirData.front_left_seat_heat_level = 0;
                GeneralAirData.rear_left_seat_heat_level = 0;
                int i3 = -(intFromByteWithBit - 16);
                GeneralAirData.front_left_seat_cold_level = i3;
                GeneralAirData.rear_left_seat_cold_level = i3;
            } else if (intFromByteWithBit == 0) {
                GeneralAirData.front_left_seat_heat_level = 0;
                GeneralAirData.rear_left_seat_heat_level = 0;
                GeneralAirData.front_left_seat_cold_level = 0;
                GeneralAirData.rear_left_seat_cold_level = 0;
            }
            if (intFromByteWithBit2 == 1 || intFromByteWithBit2 == 2 || intFromByteWithBit2 == 3) {
                GeneralAirData.front_right_seat_cold_level = 0;
                GeneralAirData.rear_right_seat_cold_level = 0;
                GeneralAirData.front_right_seat_heat_level = intFromByteWithBit2;
                GeneralAirData.rear_right_seat_heat_level = intFromByteWithBit2;
            } else if (intFromByteWithBit2 == 13 || intFromByteWithBit2 == 14 || intFromByteWithBit2 == 15) {
                GeneralAirData.front_right_seat_heat_level = 0;
                GeneralAirData.rear_right_seat_heat_level = 0;
                int i4 = -(intFromByteWithBit2 - 16);
                GeneralAirData.front_right_seat_cold_level = i4;
                GeneralAirData.rear_right_seat_cold_level = i4;
            } else if (intFromByteWithBit2 == 0) {
                GeneralAirData.front_right_seat_heat_level = 0;
                GeneralAirData.rear_right_seat_heat_level = 0;
                GeneralAirData.front_right_seat_cold_level = 0;
                GeneralAirData.rear_right_seat_cold_level = 0;
            }
            int i5 = this.airData6;
            int i6 = iArr[8];
            if (i5 != i6 || this.airData7 != iArr[9] || this.airData8 != iArr[10]) {
                this.airData6 = i6;
                this.airData7 = iArr[9];
                this.airData8 = iArr[10];
                updateAirActivity(this.mContext, 1002);
                return;
            }
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void airOfOldVersion(int[] iArr) {
        int i = this.mOutdoorTemperature;
        int i2 = iArr[7];
        if (i != i2) {
            this.mOutdoorTemperature = i2;
            if (DataHandleUtils.getBoolBit0(iArr[6])) {
                MyLog.temporaryTracking("外温：直接走了华氏度");
                Context context = this.mContext;
                updateOutDoorTemp(context, getOutDoorTemperatureF(context, iArr[7]));
            } else {
                MyLog.temporaryTracking("外温：走了摄氏度");
                if (SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
                    MyLog.temporaryTracking("外温：还是走了摄氏度");
                    Context context2 = this.mContext;
                    updateOutDoorTemp(context2, getOutDoorTemperatureC(context2, iArr[7]));
                } else {
                    MyLog.temporaryTracking("外温：然后走了华氏度");
                    Context context3 = this.mContext;
                    updateOutDoorTemp(context3, getOutDoorTemperatureF(context3, iArr[7]));
                }
            }
        }
        iArr[7] = 0;
        this.mData1Info = iArr[3];
        emptyAirData1Bit4();
        if (is0x28DataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mData1Info);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mData1Info);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mData1Info);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mData1Info, 0, 4);
            if (DataHandleUtils.getBoolBit0(iArr[6])) {
                MyLog.temporaryTracking("内温：直接走了华氏度");
                GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, iArr[4]);
                GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, iArr[5]);
            } else {
                MyLog.temporaryTracking("内温：走了摄氏度");
                if (SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
                    MyLog.temporaryTracking("内温：还是走了摄氏度");
                    GeneralAirData.front_left_temperature = getAirTemperatureC(this.mContext, iArr[4]);
                    GeneralAirData.front_right_temperature = getAirTemperatureC(this.mContext, iArr[5]);
                } else {
                    MyLog.temporaryTracking("内温：然后走了华氏度");
                    GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, iArr[4]);
                    GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, iArr[5]);
                }
            }
            GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(iArr[6]);
            GeneralAirData.rear_auto_wind_model = DataHandleUtils.getBoolBit7(iArr[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(iArr[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(iArr[6]);
            GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(iArr[6]);
            if (!DataHandleUtils.getBoolBit0(iArr[6]) && SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0) == 1) {
                GeneralAirData.rear_left_temperature = getAirTemperatureC(this.mContext, iArr[8]);
            } else {
                GeneralAirData.rear_left_temperature = getAirTemperatureF(this.mContext, iArr[8]);
            }
            GeneralAirData.rear_temperature = modeIsLowOrHi();
            GeneralAirData.rear_ac = DataHandleUtils.getBoolBit6(iArr[9]);
            GeneralAirData.manual = judgeManual();
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(iArr[9]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[9], 0, 3);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(iArr[9]);
            int i3 = this.airData6;
            int i4 = iArr[8];
            if (i3 != i4 || this.airData7 != iArr[9]) {
                this.airData6 = i4;
                this.airData7 = iArr[9];
                updateAirActivity(this.mContext, 1002);
                return;
            }
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x24BaseInfo() {
        if (is0x24DataChange()) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x21UsbInfo() {
        int i = this.eachCanId;
        if (i == 2 || i == 5 || i == 11) {
            mediaTag = "USB";
            OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(this.mCanBusInfoInt[1]);
            Bundle bundle = null;
            GeneralOriginalCarDeviceData.mList = null;
            GeneralOriginalCarDeviceData.cdStatus = "USB";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceUsbUpdateEntityList(this.mCanBusInfoInt);
            int i2 = this.mMediaType;
            int i3 = this.mCanBusInfoInt[2];
            if (i2 != i3) {
                this.mMediaType = i3;
                OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(this.mContext);
                originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
                originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
                bundle = new Bundle();
                bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
            }
            updateOriginalCarDeviceActivity(bundle);
        }
    }

    private void set0x20WheelKey() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyLongClick1(this.mContext, 0, iArr[3]);
            return;
        }
        if (i == 1) {
            realKeyLongClick1(this.mContext, 7, iArr[3]);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(this.mContext, 8, iArr[3]);
            return;
        }
        if (i == 19) {
            realKeyLongClick1(this.mContext, 45, iArr[3]);
            return;
        }
        if (i == 20) {
            realKeyLongClick1(this.mContext, 46, iArr[3]);
            return;
        }
        if (i != 135) {
            switch (i) {
                case 7:
                    realKeyClick2(this.mContext, 2, i, iArr[3]);
                    break;
                case 8:
                    realKeyClick2(this.mContext, 3, i, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 10:
                    realKeyClick2(this.mContext, 15, i, iArr[3]);
                    break;
            }
            return;
        }
        realKeyClick2(this.mContext, 3, i, iArr[3]);
    }

    private void set0x1ERearRadarInfo() {
        int i = this.eachCanId;
        if (i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 7 || i == 11) {
            if (is0x1EDataChange()) {
                RadarInfoUtil.mMinIsClose = true;
                RadarInfoUtil.setRearRadarLocationData(10, assignRadarProgress(this.mCanBusInfoInt[2]), assignRadarProgress(this.mCanBusInfoInt[3]), assignRadarProgress(this.mCanBusInfoInt[4]), assignRadarProgress(this.mCanBusInfoInt[5]));
                GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                updateParkUi(null, this.mContext);
            }
            if (getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status") != -1) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Display_switch_status"), getRadarDisplayState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]))));
                arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_distance"), getRadarDistance(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]))));
                arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Radar_switch_status"), getRadarSwitch(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]))));
                arrayList.add(new DriverUpdateEntity(getmUigMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_330_Radar_setting_status"), getmUigMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_330_Volume_intensity"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3) + ""));
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
            }
        }
    }

    private void set0x1dFrontRadarInfo() {
        int i = this.eachCanId;
        if ((i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 7 || i == 11) && is0x1DDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, assignRadarProgress(this.mCanBusInfoInt[2]), assignRadarProgress(this.mCanBusInfoInt[3]), assignRadarProgress(this.mCanBusInfoInt[4]), assignRadarProgress(this.mCanBusInfoInt[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x16Speed(int[] iArr) {
        int i = this.eachCanId;
        if (i == 2 || i == 4 || i == 11) {
            this.speedInfo = iArr;
            ArrayList arrayList = new ArrayList();
            if (SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R1", 0) == 1) {
                arrayList.add(new DriverUpdateEntity(0, 0, this.decimalFormat.format((iArr[2] + (iArr[3] * 256)) / 16) + " KM/h"));
            } else {
                arrayList.add(new DriverUpdateEntity(0, 0, this.decimalFormat.format(((iArr[2] + (iArr[3] * 256)) / 16) * 0.6213712d) + " MPH"));
            }
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]));
        }
    }

    private boolean is0x1DDataChange() {
        if (Arrays.equals(this.m0x1DData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x1DData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x1EDataChange() {
        if (Arrays.equals(this.m0x1EData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x1EData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x24DataChange() {
        if (Arrays.equals(this.m0x24Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x24Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x28DataChange() {
        if (Arrays.equals(this.m0x21AirData, this.mCanBusInfoInt)) {
            return false;
        }
        this.m0x21AirData = this.mCanBusInfoInt;
        return true;
    }

    private String getAirTemperatureC(Context context, int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (i <= 0 || i >= 30) ? "" : this.decimalFormat.format(((i - 1) * 0.5d) + 18.0d) + getTempUnitC(context);
    }

    private String getAirTemperatureF(Context context, int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (i <= 0 || i >= 30) ? "" : this.decimalFormat.format(((((i - 1) * 0.5d) + 18.0d) * 1.8d) + 32.0d) + getTempUnitF(context);
    }

    private String getOutDoorTemperatureC(Context context, int i) {
        return this.decimalFormat.format((i * 0.5d) - 40.0d) + getTempUnitC(context);
    }

    private String getOutDoorTemperatureF(Context context, int i) {
        return this.decimalFormat.format((((i * 0.5d) - 40.0d) * 1.8d) + 32.0d) + getTempUnitF(context);
    }

    private String modeIsLowOrHi() {
        String str = (this.lowSate == DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1) || DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1) == 0) ? "" : "LOW";
        if (this.hiState != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1) && DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1) != 0) {
            str = "HI";
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
            this.lowSate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
        } else {
            this.lowSate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 7, 1);
        }
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9])) {
            this.hiState = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
        } else {
            this.hiState = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1);
        }
        return str;
    }

    private boolean judgeManual() {
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 3) != 0;
    }

    private boolean isTrackDataChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getmUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void emptyAirData1Bit4() {
        this.mCanBusInfoInt[3] = Integer.parseInt(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1) + "" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1) + "" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1) + "0" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1) + "" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1) + "" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1) + "" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1) + "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        OriginalDeviceData originalDeviceData = new OriginalDeviceData(new ArrayList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_band", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_frequency", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_band", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_1", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_2", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_3", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_4", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_5", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Presupposition_6", null));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_currently_selected_disc", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_state", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_time", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", null));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Back_row_media_status", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Rear_lock_status", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList3.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_current_time", null));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_sub_status", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_media_status", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Current_track", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Total_repertoire", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_folaer_status", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_play_time", null));
        arrayList4.add(new OriginalCarDevicePageUiSet.Item("music_list", "_330_Playback_progress", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(0, originalDeviceData);
        this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(arrayList, new String[]{"left", "up", OriginalBtnAction.PLAY_PAUSE, "down", "right"}));
        this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(arrayList2, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", OriginalBtnAction.RANDOM, OriginalBtnAction.REPEAT, "down", "right", OriginalBtnAction.NEXT_DISC}));
        this.mOriginalDeviceDataArray.put(3, originalDeviceData);
        this.mOriginalDeviceDataArray.put(4, new OriginalDeviceData(arrayList3));
        this.mOriginalDeviceDataArray.put(33, new OriginalDeviceData(arrayList4, new String[]{OriginalBtnAction.PREV_DISC, OriginalBtnAction.PLAY_PAUSE, OriginalBtnAction.NEXT_DISC}));
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
            this.mOriginalCarDevicePageUiSet = getmUigMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceRadioUpdateEntityList(int[] iArr) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        if (iArr[3] == 0) {
            if (iArr[4] == 16) {
                String str11 = getStereoState(DataHandleUtils.getBoolBit7(iArr[5])) + "/" + getScanState(DataHandleUtils.getBoolBit5(iArr[5])) + "/" + getPresetStationNumber(DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4));
                str10 = getMsbLsbResult(iArr[6], iArr[7]) + "KHz";
                str9 = str11;
                str6 = "AM";
            } else {
                str6 = "FM" + iArr[4];
                str10 = this.decimalFormat.format(getMsbLsbResult(iArr[6], iArr[7]) * 0.01d) + "MHz";
                str9 = null;
            }
            str7 = null;
            str5 = null;
            str = null;
            str8 = null;
            str2 = null;
            str4 = null;
            str3 = null;
        } else {
            int i = iArr[4];
            if (i == 16 || i == 17 || i == 18) {
                String appointmentBand = getAppointmentBand(i);
                str = getMsbLsbResult(iArr[5], iArr[6]) + "KHz";
                String str12 = getMsbLsbResult(iArr[7], iArr[8]) + "KHz";
                String str13 = getMsbLsbResult(iArr[9], iArr[10]) + "KHz";
                str2 = getMsbLsbResult(iArr[11], iArr[12]) + "KHz";
                String str14 = getMsbLsbResult(iArr[13], iArr[14]) + "KHz";
                str3 = getMsbLsbResult(iArr[15], iArr[16]) + "KHz";
                str4 = str14;
                str5 = str13;
                str6 = null;
                str7 = appointmentBand;
                str8 = str12;
                str9 = null;
                str10 = null;
            } else {
                String appointmentBand2 = getAppointmentBand(i);
                String str15 = this.decimalFormat.format(getMsbLsbResult(iArr[5], iArr[6]) * 0.01d) + "MHz";
                str8 = this.decimalFormat.format(getMsbLsbResult(iArr[7], iArr[8]) * 0.01d) + "MHz";
                String str16 = this.decimalFormat.format(getMsbLsbResult(iArr[9], iArr[10]) * 0.01d) + "MHz";
                String str17 = this.decimalFormat.format(getMsbLsbResult(iArr[11], iArr[12]) * 0.01d) + "MHz";
                String str18 = this.decimalFormat.format(getMsbLsbResult(iArr[13], iArr[14]) * 0.01d) + "MHz";
                str3 = this.decimalFormat.format(getMsbLsbResult(iArr[15], iArr[16]) * 0.01d) + "MHz";
                str4 = str18;
                str5 = str16;
                str2 = str17;
                str = str15;
                str6 = null;
                str10 = null;
                str7 = appointmentBand2;
                str9 = null;
            }
        }
        ArrayList arrayList = new ArrayList();
        if (str6 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str6));
        }
        if (str9 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, str9));
        }
        if (str10 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str10));
        }
        if (str7 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str7));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str));
        }
        if (str8 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, str8));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, str5));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(7, str2));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(8, str4));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(9, str3));
        }
        GeneralOriginalCarDeviceData.runningState = "Radio运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceCdDvdUsbUpdateEntityList(int[] iArr) {
        String str = DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 4) + "";
        String cdOrDvdSate = getCdOrDvdSate(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4), DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4));
        String str2 = getMsbLsbResult(iArr[6], iArr[7]) + "";
        String str3 = getMsbLsbResult(iArr[8], iArr[9]) + "";
        String str4 = iArr[10] + ":" + iArr[11] + ":" + iArr[12];
        String str5 = iArr[13] + ":" + iArr[14] + ":" + iArr[15];
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        }
        if (cdOrDvdSate != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, cdOrDvdSate));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str2));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str3));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str4));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, str5));
        }
        GeneralOriginalCarDeviceData.runningState = "CD/DVD运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceRearDiscUpdateEntityList(int[] iArr) {
        String rearMediaState = getRearMediaState(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 4));
        String rearLockState = getRearLockState(DataHandleUtils.getIntFromByteWithBit(iArr[6], 6, 1));
        String str = getMsbLsbResult(iArr[7], iArr[8]) + "";
        String str2 = iArr[9] + ":" + iArr[10] + ":" + iArr[11];
        ArrayList arrayList = new ArrayList();
        if (rearMediaState != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, rearMediaState));
        }
        if (rearLockState != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, rearLockState));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str2));
        }
        GeneralOriginalCarDeviceData.runningState = "DISC运行正常";
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceUsbUpdateEntityList(int[] iArr) {
        String usbState1 = getUsbState1(DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 4));
        String usbState2 = getUsbState2(DataHandleUtils.getIntFromByteWithBit(iArr[2], 4, 2));
        String str = getMsbLsbResult(iArr[7], iArr[6]) + "";
        String str2 = getMsbLsbResult(iArr[9], iArr[8]) + "";
        String str3 = iArr[10] + "";
        String str4 = iArr[4] + ":" + iArr[5];
        String str5 = iArr[11] + "%";
        ArrayList arrayList = new ArrayList();
        if (usbState1 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, usbState1));
        }
        if (usbState2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, usbState2));
        }
        if (str != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, str));
        }
        if (str2 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, str2));
        }
        if (str3 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(4, str3));
        }
        if (str4 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(5, str4));
        }
        if (str5 != null) {
            arrayList.add(new OriginalCarDeviceUpdateEntity(6, str5));
        }
        GeneralOriginalCarDeviceData.runningState = "SUB运行正常";
        return arrayList;
    }

    private String getPresetStationNumber(int i) {
        return i == 0 ? "无预设电台" : "预设电台" + i;
    }

    private String getCdOrDvdSate(int i, int i2) {
        return (i == 1 ? "碟循环" : i == 2 ? "单曲循环" : "循环关") + "/" + (i2 == 1 ? "乱序" : "顺序");
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String getNullHaveState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._330_no);
        }
        return this.mContext.getString(R.string._330_yes);
    }

    public void startSettingActivity(int i, int i2) {
        startSettingActivity(this.mContext, i, i2);
    }

    public void updateAirInfo() {
        int intValue = SharePreUtil.getIntValue(this.mContext, "C" + getCurrentCanDifferentId() + "M" + getCurrentEachCanId() + "L" + getmUigMgr(this.mContext).getLeftIndexes(this.mContext, "_330_unit_selection") + "R0", 0);
        if (intValue == 1) {
            int i = this.data2FrontLeft;
            if (i != 0) {
                GeneralAirData.front_left_temperature = getAirTemperatureC(this.mContext, i);
            }
            int i2 = this.data3FrontRight;
            if (i2 != 0) {
                GeneralAirData.front_right_temperature = getAirTemperatureC(this.mContext, i2);
            }
        } else {
            int i3 = this.data2FrontLeft;
            if (i3 != 0) {
                GeneralAirData.front_left_temperature = getAirTemperatureF(this.mContext, i3);
            }
            int i4 = this.data3FrontRight;
            if (i4 != 0) {
                GeneralAirData.front_right_temperature = getAirTemperatureF(this.mContext, i4);
            }
        }
        if (intValue == 1) {
            int i5 = this.data6RearLeft;
            if (i5 != 0) {
                GeneralAirData.rear_left_temperature = getAirTemperatureC(this.mContext, i5);
            }
            int i6 = this.data8RearRight;
            if (i6 != 0) {
                GeneralAirData.rear_right_temperature = getAirTemperatureC(this.mContext, i6);
            }
        } else {
            int i7 = this.data6RearLeft;
            if (i7 != 0) {
                GeneralAirData.rear_left_temperature = getAirTemperatureF(this.mContext, i7);
            }
            int i8 = this.data8RearRight;
            if (i8 != 0) {
                GeneralAirData.rear_right_temperature = getAirTemperatureF(this.mContext, i8);
            }
        }
        if (intValue == 1) {
            MyLog.temporaryTracking("摄氏度数据1：" + this.data5OurDoorTem);
            MyLog.temporaryTracking("摄氏度数据2：" + getOutDoorTemperatureC(this.mContext, this.data5OurDoorTem));
            Context context = this.mContext;
            updateOutDoorTemp(context, getOutDoorTemperatureC(context, this.data5OurDoorTem));
        } else {
            MyLog.temporaryTracking("华氏度数据1：" + this.data5OurDoorTem);
            MyLog.temporaryTracking("华氏度数据2：" + getOutDoorTemperatureF(this.mContext, this.data5OurDoorTem));
            Context context2 = this.mContext;
            updateOutDoorTemp(context2, getOutDoorTemperatureF(context2, this.data5OurDoorTem));
        }
        updateAirActivity(this.mContext, 1001);
    }

    public void uadateTrafficInfo() {
        int[] iArr = this.trafficInfo;
        if (iArr != null) {
            set0x35TrafficInfo(iArr);
        }
        int[] iArr2 = this.speedInfo;
        if (iArr2 != null) {
            set0x16Speed(iArr2);
        }
        int[] iArr3 = this.tireTurnSpeed;
        if (iArr3 != null) {
            set0x50CarSpeed(iArr3);
        }
    }

    public void SendBroadcast() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._330.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(5000L, 1000L) { // from class: com.hzbhd.canbus.car._330.MsgMgr.3.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        Intent intent = new Intent();
                        intent.setAction("CAN_ID330_GX470_UNIT_TYPE");
                        intent.putExtra("TYPE", SharePreUtil.getIntValue(MsgMgr.this.mContext, "C" + MsgMgr.this.getCurrentCanDifferentId() + "M" + MsgMgr.this.getCurrentEachCanId() + "L" + MsgMgr.this.getmUigMgr(MsgMgr.this.mContext).getLeftIndexes(MsgMgr.this.mContext, "_330_unit_selection") + "R1", 0));
                        MsgMgr.this.mContext.sendBroadcast(intent);
                        MyLog.temporaryTracking("单位设置：发出了单位广播");
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        Intent intent = new Intent();
                        intent.setAction("CAN_ID330_GX470_UNIT_TYPE");
                        intent.putExtra("TYPE", SharePreUtil.getIntValue(MsgMgr.this.mContext, "C" + MsgMgr.this.getCurrentCanDifferentId() + "M" + MsgMgr.this.getCurrentEachCanId() + "L" + MsgMgr.this.getmUigMgr(MsgMgr.this.mContext).getLeftIndexes(MsgMgr.this.mContext, "_330_unit_selection") + "R1", 0));
                        MsgMgr.this.mContext.sendBroadcast(intent);
                        MyLog.temporaryTracking("单位设置：发出了单位广播");
                    }
                }.start();
            }
        });
    }
}
